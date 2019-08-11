package com.xishanqu.redislock.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 中文类名: 配置redission集群
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/8/9
 */
@Configuration
@Slf4j
public class RedissonManager {

//    @Value("${spring.redis.clusters}")
//    private  String cluster;

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private String port;
    @Value("${spring.redis.database}")
    private Integer database;

    @Bean
    public RedissonClient getRedisson(){
        RedissonClient redisson= null;
        try {
//            String[] nodes = cluster.split(",");
              //redisson版本是3.5，集群的ip前面要加上“redis://”，不然会报错，3.2版本可不加
//            for (int i=0; i< nodes.length; i++){
//                nodes[i] = "redis://" + nodes[i];
//            }
            Config config = new Config();
//            config
//                    //集群server
//                    .useClusterServers()
//                    //集群状态扫描时间
//                    .setScanInterval(2000)
//                    .addNodeAddress(nodes)
//                    .setPassword(password);
//

            String address = "redis://" + host + ":" + port;
            SingleServerConfig singleServerConfig = config.useSingleServer()
                    .setAddress(address)
                    .setDatabase(database);
            redisson = Redisson.create(config);
            log.info("redisson集群配置成功>>>>>>>>>>>>>>config={}", redisson.getConfig().toJSON());
            return redisson;
        }catch (Exception ex){
            log.error("redisson集群配置失败>>>>>>>>>>>>>>ex={}", ex);
        }
        return redisson;
    }

}
