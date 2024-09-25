package cn.iocoder.mayrain.module.lab.controller.admin.project.vo.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: yudao-cloud
 * @BelongsPackage: cn.iocoder.mayrain.module.lab.controller.admin.project.vo.project
 * @Author: mayrain
 * @CreateTime: 2024-09-25 17:13
 * @Description:
 */
@Schema(description = "管理后台 - 实验室项目精简信息列表 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSimpleRespVO {

    @Schema(description = "项目编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "项目名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "mayrain")
    private String name;

    @Schema(description = "父项目 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long parentId;
}
