package org.example.domain.word.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.domain.word.model.entity.WordModulePo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoupengcheng
 * @since 2024-07-11
 */
@Mapper
public interface WordModuleDao extends BaseMapper<WordModulePo> {


    WordModulePo queryById(Long id);
}
