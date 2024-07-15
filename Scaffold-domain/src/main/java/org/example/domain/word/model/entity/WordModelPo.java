package mybatis.entity;

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
 * @since 2024-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("word_model")
public class WordModelPo extends Model<WordModelPo> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    private String modelName;

    private String modelUrl;


    public static final String ID = "id";

    public static final String MODEL_NAME = "model_name";

    public static final String MODEL_URL = "model_url";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
