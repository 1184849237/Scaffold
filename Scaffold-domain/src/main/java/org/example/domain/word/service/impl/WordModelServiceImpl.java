package mybatis.service.impl;

import mybatis.entity.WordModelPo;
import mybatis.mapper.WordModelDao;
import mybatis.service.WordModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoupengcheng
 * @since 2024-07-15
 */
@Service
public class WordModelServiceImpl extends ServiceImpl<WordModelDao, WordModelPo> implements WordModelService {

}
