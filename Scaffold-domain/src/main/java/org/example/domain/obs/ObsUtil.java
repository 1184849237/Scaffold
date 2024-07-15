package org.example.domain.utils;

import cn.hutool.core.util.StrUtil;
import com.obs.services.ObsClient;
import com.obs.services.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;

/**
 * @author Admin
 * @ClassName: ObsUtil
 * @Description: TODO
 * @Create by: 周鹏程
 * @Date: 2022/8/2 17:26
 */
@Component
public class ObsUtil {
    public static HashSet<String> hashSet;

    static {
        //doc,docx,xls,xlsx,pdf,jpg,png,gif
        hashSet = new HashSet<>();
        hashSet.add("doc");
        hashSet.add("docx");
        hashSet.add("xls");
        hashSet.add("xlsx");
        hashSet.add("pdf");
        hashSet.add("jpg");
        hashSet.add("png");
        hashSet.add("gif");
    }

    @Resource
    ObsConfig obsConfig;

    private static ObsConfig config;

    private static ObsClient client;

    @PostConstruct
    public void init() {
        config = this.obsConfig;
        client = this.obsConfig.obsClient();
    }

    public static void check(String fileName){
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!ObsUtil.hashSet.contains(fileSuffix)) {
            throw new RuntimeException("文件格式错误！");
        }
    }

    public static String uploadFile(String fileName, byte[] data) {
        uploadBucketFile(config.getBucketName(), fileName, data.length, new ByteArrayInputStream(data));
        return "https://zpc.obs.cn-east-3.myhuaweicloud.com/zpc/" + fileName;
    }

    public static String getAttachmentUrl(String fileName) {
        return "https://zpc.obs.cn-east-3.myhuaweicloud.com/zpc/" + fileName;
    }

    /**
     * 上传文件
     *
     * @param bucket   存储空间名
     * @param fileName 文件名(包括文件夹名称和“/”)
     * @param length   流的长度
     * @param content  输入流
     */
    public static void uploadBucketFile(String bucket, String fileName, long length, InputStream content) {
        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();
        // 必须设置ContentLength
        meta.setContentLength(length);
        // 上传Object.
        client.putObject(bucket, fileName, content, meta);
    }

    /**
     * 下载文件
     */
    public static InputStream downFile(String objectKey) {
        ObsObject object = client.getObject(config.getBucketName(), objectKey);
        InputStream inputStream = object.getObjectContent();
        return inputStream;
    }

    /**
     * 获取url
     *
     * @param fileName
     * @param invalidTime
     * @return
     */
    public static String getUrl(String fileName, Date invalidTime) {
        long time = invalidTime.getTime();
        Long expireSeconds = time - System.currentTimeMillis();
        TemporarySignatureRequest request = new TemporarySignatureRequest(HttpMethodEnum.GET, expireSeconds);
        request.setBucketName(config.getBucketName());
        request.setObjectKey(fileName);
        TemporarySignatureResponse response = client.createTemporarySignature(request);
        String url = response.getSignedUrl();
        if (StrUtil.isNotBlank(url)) {
            return url;
        }
        return null;
    }
}
