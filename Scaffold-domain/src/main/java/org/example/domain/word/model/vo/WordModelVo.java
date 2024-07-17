package org.example.domain.word.model.vo;

import lombok.Data;
import org.example.types.validated.GroupAdd;
import org.example.types.validated.GroupUpdate;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName: WordModelVo
 * @Description: TODO
 * @Create by: 周鹏程
 * @Date: 2024/7/15 14:54
 */
@Data
public class WordModelVo implements Serializable {

    @NotNull(message = "模板id不能为空！", groups = {GroupUpdate.class})
    private Long id;

    @NotNull(message = "模板名称不能为空！", groups = {GroupAdd.class})
    private String modelName;

    @NotNull(message = "模板文件不能为空！", groups = {GroupAdd.class})
    private MultipartFile modelFile;
}
