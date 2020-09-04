package cn.durian.kryodemo.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoPool;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Liuluhao
 * @Date 2020/9/3
 */
@Component
public class KryoSerializer implements Serializer {

    @Resource
    private KryoPool kryoPool;

    private final ThreadLocal<Output> outputThreadLocal = new ThreadLocal<>();

    private final ThreadLocal<Input> inputThreadLocal = new ThreadLocal<>();

    /**
     * 序列化
     *
     * @param object 需要进行序列化的对象
     * @param bytes  字节数组
     */
    @Override
    public void serialize(Object object, byte[] bytes) {

        Kryo kryo = kryoPool.borrow();

    }

    /**
     * 带偏移量参数的序列化
     *
     * @param object 需要进行序列化的对象
     * @param bytes  字节数组
     * @param offset 偏移量
     * @param count  指定长度
     */
    @Override
    public void serialize(Object object, byte[] bytes, int offset, int count) {

        Kryo kryo = kryoPool.borrow();
        Output outPut = getOutPut(bytes, offset, count);
        kryo.writeObjectOrNull(outPut, object, object.getClass());
        outPut.flush();
    }

    /**
     * 反序列化
     *
     * @param bytes 字节数组
     * @return 泛型类型的对象
     */
    @Override
    public <T> T deserialize(byte[] bytes) {
        return null;
    }

    /**
     * 带偏移量参数的反序列化
     *
     * @param bytes  字节数组
     * @param offset 偏移量
     * @param count  指定长度
     * @return 泛型类型的对象
     */
    @Override
    public <T> T deserialize(byte[] bytes, int offset, int count) {
        return null;
    }

    /**
     * 获取Output对象
     *
     * @param bytes  字节数组
     * @param offset 偏移量
     * @param count  指定长度
     * @return
     */
    private Output getOutPut(byte[] bytes, int offset, int count) {
        Output output = outputThreadLocal.get();
        if (output == null) {
            output = new Output();
            outputThreadLocal.set(output);
            outputThreadLocal.remove();
        }
        if (bytes != null) {
            output.writeBytes(bytes, offset, count);
//            output.write(bytes, offset, count);
        }
        return output;
    }
}
