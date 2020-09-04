package cn.durian.kryodemo.config;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.pool.KryoPool;
import org.objenesis.strategy.StdInstantiatorStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Liuluhao
 * @Date 2020/9/3
 */
@Configuration
public class PoolConfig {

    @Bean
    public KryoPool newKryoPool() {
        return new KryoPool.Builder(() -> {
            final Kryo kryo = new Kryo();
            kryo.setReferences(false);
            kryo.setRegistrationRequired(false);
            kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(
                    new StdInstantiatorStrategy()));
            return kryo;
        }).softReferences().build();
    }

}
