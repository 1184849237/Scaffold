package org.example.domain.obs;

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
@Component
public class ObsConfig {

    private String accessKeyId = "BMFD1IJBFEZD2RYZWCAN";

    private String accessKeySecret = "vEazJAQo5AsHs48La0WEyNw3HGlj8nG3dU1WEaUE";

    private String bucketName = "zpc";

    private String callbackUrl = "";

    private String endpoint = "obs.cn-east-3.myhuaweicloud.com";

    private String obsUrl = "https://zpc.obs.cn-east-3.myhuaweicloud.com/zpc/";

    @Bean
    public ObsClient obsClient(){
       return new ObsClient(accessKeyId,accessKeySecret,endpoint);
    }
}
