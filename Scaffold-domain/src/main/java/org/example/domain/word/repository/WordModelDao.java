package org.example.domain.word.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.domain.word.model.entity.WordModelPo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoupengcheng
 * @since 2024-07-15
 */
@Mapper
public interface WordModelDao extends BaseMapper<WordModelPo> {

    WordModelPo queryById(Long id);
}
