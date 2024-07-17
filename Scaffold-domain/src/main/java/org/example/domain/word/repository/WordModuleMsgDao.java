package org.example.domain.word.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.domain.word.model.entity.WordModuleMsgPo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoupengcheng
 * @since 2024-07-09
 */
@Mapper
public interface WordModuleMsgDao extends BaseMapper<WordModuleMsgPo> {

    List<WordModuleMsgPo> getMsgByModuleId(Long moduleId);
}
