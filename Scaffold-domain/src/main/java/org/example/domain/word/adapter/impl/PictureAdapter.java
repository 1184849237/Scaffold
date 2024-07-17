package org.example.domain.word.adapter.impl;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.example.domain.utils.CustomXWPFDocument;
import org.example.domain.word.adapter.WordModuleAdapter;
import org.example.domain.word.model.entity.WordModuleFormatPo;
import org.example.domain.word.model.entity.WordModuleMsgPo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * @ClassName: PictureAdapter
 * @Description: 文本实现
 * @Create by: 周鹏程
 * @Date: 2024/7/9 9:43
 */
@Slf4j
public class PictureAdapter implements WordModuleAdapter {
    @Override
    public void detailFormat() {

    }

    @Override
    public void writeDocument(CustomXWPFDocument doc, XWPFParagraph paragraph, WordModuleMsgPo wordModuleMsgPo) {
        try {
            String imageUrl = wordModuleMsgPo.getModuleValue();
            if (StrUtil.isEmpty(imageUrl)) {
                return;
            }
            URL imgUrl = new URL(imageUrl);
            //读取图片对象
            BufferedImage image = ImageIO.read(imgUrl);
            WordModuleFormatPo wordModuleFormatPo = wordModuleMsgPo.getWordModuleFormatPo();
            //获取图片宽高
            int imgHeight = wordModuleFormatPo.getImgHeight().intValue();
            int imgWidth = wordModuleFormatPo.getImgWidth().intValue();
            InputStream imgInputStream = imgUrl.openStream();
            byte[] imageByte = inputStream2ByteArray(imgInputStream, true);
            //获取图片类型id
            int imgType = getPictureType(imageUrl);
            //获取word对象中图片id
            int imgId = doc.getNextPicNameNumber(imgType);
            String bilId = doc.addPictureData(imageByte, imgType);
            //向word中添加图片
            doc.createPicture(bilId, imgId, imgWidth, imgHeight, paragraph);
        }catch (Exception e){
            log.error("插入图片失败{}", e.getMessage());
        }
    }



    /**
     * 根据图片类型，取得对应的图片类型代码
     *
     * @param picType
     * @return int
     */
    private static int getPictureType(String picType) {
        int res = XWPFDocument.PICTURE_TYPE_PICT;
        if (picType != null) {
            if ("png".equalsIgnoreCase(picType)) {
                res = XWPFDocument.PICTURE_TYPE_PNG;
            } else if ("dib".equalsIgnoreCase(picType)) {
                res = XWPFDocument.PICTURE_TYPE_DIB;
            } else if ("emf".equalsIgnoreCase(picType)) {
                res = XWPFDocument.PICTURE_TYPE_EMF;
            } else if ("jpg".equalsIgnoreCase(picType) || "jpeg".equalsIgnoreCase(picType)) {
                res = XWPFDocument.PICTURE_TYPE_JPEG;
            } else if ("wmf".equalsIgnoreCase(picType)) {
                res = XWPFDocument.PICTURE_TYPE_WMF;
            }
        }
        return res;
    }

    /**
     * 将输入流中的数据写入字节数组
     *
     * @param in
     * @return
     */
    public static byte[] inputStream2ByteArray(InputStream in, boolean isClose) throws Exception {
        byte[] byteArray = null;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int len = 0;
        while ((len = in.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        in.close();
        byteArray = outStream.toByteArray();
        return byteArray;
    }
}
