package org.example.infrastructure.obs;

import com.obs.services.ObsClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * 华为云OBS配置
 *
 * @author Admin
 * @since 2022-01-20
 */
@Data
@ConfigurationProperties(prefix = "obs")
@Component
public class ObsConfig {

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    private String callbackUrl;

    private String endpoint;

    @Bean
    public ObsClient obsClient(){
       return new ObsClient(accessKeyId,accessKeySecret,endpoint);
    }
}
