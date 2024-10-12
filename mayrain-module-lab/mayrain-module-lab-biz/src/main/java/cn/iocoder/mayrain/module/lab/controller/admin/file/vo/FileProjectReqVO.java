package cn.iocoder.mayrain.module.lab.controller.admin.file.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @BelongsProject: yudao-cloud
 * @BelongsPackage: cn.iocoder.mayrain.module.lab.controller.admin.file.vo
 * @Author: mayrain
 * @CreateTime: 2024-10-01 18:40
 * @Description:
 */
@Schema(description = "实验室 - 项目绑定文件 Request VO")
@Data
public class FileProjectReqVO {

    @Schema(description = "项目编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long projectId;

    @Schema(description = "文件名", requiredMode = Schema.RequiredMode.REQUIRED, example = "yudao.png")
    private String filePath;
}
