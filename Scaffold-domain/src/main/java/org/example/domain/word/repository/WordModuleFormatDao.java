package org.example.domain.word.repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.domain.word.model.entity.WordModuleFormatPo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoupengcheng
 * @since 2024-07-10
 */
@Mapper
public interface WordModuleFormatDao extends BaseMapper<WordModuleFormatPo> {

    WordModuleFormatPo queryListByMsgId(Long msgId);
}
