package org.example.domain.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Admin
 * @ClassName: FileDownload
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
            in = Files.newInputStream(Paths.get(filePath));
            out = response.getOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) > 0) {
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
            setDownloadHeader(fileName, response);
            out = response.getOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) > 0) {
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

}
