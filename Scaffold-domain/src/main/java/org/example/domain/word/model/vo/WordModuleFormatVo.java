package org.example.domain.word.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.example.domain.word.model.entity.WordModuleFormatPo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author zhoupengcheng
 * @since 2024-07-10
 */
@Data
public class WordModuleFormatVo implements Serializable{


    private Long id;

    private Long msgId;
    /**
     * 字体加粗
     */
    private Integer blod;

    /**
     * 字体名称
     */
    private String fontName;

    /**
     * 字体颜色
     */
    private String fontColor;

    /**
     * 字体大小
     */
    private BigDecimal fontSize;

    /**
     * 行间距
     */
    private BigDecimal lineSpacing;

    /**
     * 首行缩进
     */
    private BigDecimal firstLineIndent;

    /**
     * 图片高
     */
    private BigDecimal imgHeight;

    /**
     * 图片宽
     */
    private BigDecimal imgWidth;

    public WordModuleFormatPo toPo() {
        return WordModuleFormatPo.builder()
                .id(this.id)
                .msgId(this.msgId)
                .blod(this.blod)
                .fontName(this.fontName)
                .fontColor(this.fontColor)
                .fontSize(this.fontSize)
                .lineSpacing(this.lineSpacing)
                .firstLineIndent(this.firstLineIndent)
                .imgHeight(this.imgHeight)
                .imgWidth(this.imgWidth)
                .build();
    }

}
