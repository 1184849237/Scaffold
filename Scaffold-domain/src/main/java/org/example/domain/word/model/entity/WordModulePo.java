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
 * @since 2024-07-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("word_module")
public class WordModulePo extends Model<WordModulePo> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    private String moduleName;


    public static final String ID = "id";

    public static final String MODULE_NAME = "module_name";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
