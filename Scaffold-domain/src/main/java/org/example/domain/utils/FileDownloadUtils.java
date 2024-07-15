package com.lvzuan.meetmanager.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Admin
 * @ClassName: FileDownload
 * @Description: TODO
 * @Create by: 周鹏程
 * @Date: 2022/3/21 11:19
 */
@Slf4j
public class FileDownloadUtils {

    public static void fileDownload(String filePath, String fileName, HttpServletResponse response) throws Exception {
        InputStream in = null;
        OutputStream out = null;
        try {
            setDownloadHeader(fileName, response);
            in = new FileInputStream(filePath);
            out = response.getOutputStream();
            //获取要下载的文件输入流
            int len = 0;
            //创建数据缓冲区
            byte[] buffer = new byte[1024];
            //将FileInputStream流写入到buffer缓冲区
            while ((len = in.read(buffer)) > 0) {
                //使用OutputStream将缓冲区的数据输出到浏览器
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            log.error("文件下载错误", e);
        } finally {
            closeStream(in, out);
        }
    }

    public static void fileDownload(InputStream in, String fileName, HttpServletResponse response) throws Exception {
        OutputStream out = null;
        try {
            //插入头部信息
            setDownloadHeader(fileName, response);
            out = response.getOutputStream();
            //获取要下载的文件输入流
            int len = 0;
            //创建数据缓冲区
            byte[] buffer = new byte[1024];
            //将FileInputStream流写入到buffer缓冲区
            while ((len = in.read(buffer)) > 0) {
                //使用OutputStream将缓冲区的数据输出到浏览器
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            log.error("文件下载错误", e);
        } finally {
            closeStream(in, out);
        }
    }

    public static void setDownloadHeader(String fileName, HttpServletResponse response) throws Exception {
        fileName = URLEncoder.encode(fileName, "UTF-8");
        //跨域处理
//        response.reset();
//        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition,filename");
        // 设置类型为.docx
        response.setContentType("application/docx");
        // 导出名称，但Postman统一为response,网页上下载会以此命名
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";" + "filename*=utf-8''" + fileName);
    }

    public static void closeStream(InputStream in, OutputStream out) throws Exception {
        if (out != null) {
            out.flush();
        }
        if (in != null) {
            in.close();
        }
    }

    // 文件下载到/home/meetmanager/wpsFile/+当前日期+文件名，文件名以  -version结尾
    public static File upload(File file, String fileRoot, Integer version) throws IOException {

        // 获取上传文件的原文件名
//        String fileName = file.getOriginalFilename();
        String fileName = file.getName();
        log.info("文件名称{}",fileName);
        String fileNameReg = fileName.substring(0, fileName.lastIndexOf("."));
        Pattern pattern = Pattern.compile("^(.*?)-\\d$");
        Matcher m = pattern.matcher(fileNameReg);

        File packageFile = new File(fileRoot);
        if (!packageFile.exists()) {
            packageFile.mkdirs();
        }
        File targetFile = null;
        if (m.matches()) {
            log.info("匹配{}",m.matches());
            targetFile = new File(fileRoot + "/" + fileName.substring(0, fileName.lastIndexOf("-")) + "-" + version + fileName.substring(fileName.lastIndexOf(".")));
        } else {
            targetFile = new File(fileRoot + "/" + fileName.substring(0, fileName.lastIndexOf(".")) + "-" + version + fileName.substring(fileName.lastIndexOf(".")));
        }

//        // 如果目标文件存在，删除
//        if (targetFile.exists()) {
//            targetFile.delete();
//        }
//        // 将目标文件进行转移
//        file.transferTo(targetFile);
        Files.copy(file.toPath(), targetFile.toPath());
        return targetFile;
    }

}
