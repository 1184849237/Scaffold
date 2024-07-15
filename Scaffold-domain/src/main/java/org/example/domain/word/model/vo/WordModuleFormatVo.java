package org.example.domain.word.model.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author zhoupengcheng
 * @since 2024-07-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("word_module_format")
public class WordModuleFormatPo extends Model<WordModuleFormatPo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
