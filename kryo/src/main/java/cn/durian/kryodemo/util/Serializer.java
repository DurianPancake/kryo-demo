package cn.durian.kryodemo.util;

/**
 * @author Liuluhao
 * @Date 2020/9/3
 */
public interface Serializer {

    /**
     * 序列化
     *
     * @param object 需要进行序列化的对象
     * @param bytes  字节数组
     */
    void serialize(Object object, byte[] bytes);

    /**
     * 带偏移量参数的序列化
     *
     * @param object 需要进行序列化的对象
     * @param bytes  字节数组
     * @param offset 偏移量
     * @param count  指定长度
     */
    void serialize(Object object, byte[] bytes, int offset, int count);

    /**
     * 反序列化
     *
     * @param bytes 字节数组
     * @param <T>   类型泛型
     * @return 泛型类型的对象
     */
    <T> T deserialize(byte[] bytes);

    /**
     * 带偏移量参数的反序列化
     *
     * @param bytes  字节数组
     * @param offset 偏移量
     * @param count  指定长度
     * @param <T>    类型泛型
     * @return 泛型类型的对象
     */
    <T> T deserialize(byte[] bytes, int offset, int count);
}
