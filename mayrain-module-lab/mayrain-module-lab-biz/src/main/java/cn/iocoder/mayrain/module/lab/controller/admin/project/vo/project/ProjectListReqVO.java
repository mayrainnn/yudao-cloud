package cn.iocoder.mayrain.module.lab.controller.admin.project.vo.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @BelongsProject: yudao-cloud
 * @BelongsPackage: cn.iocoder.mayrain.module.lab.controller.admin.project.vo.project
 * @Author: mayrain
 * @CreateTime: 2024-09-25 16:30
 * @Description:
 */
@Schema(description = "管理后台 - 实验室项目列表 Request VO")
@Data
public class ProjectListReqVO {
    @Schema(description = "项目名称，模糊匹配", example = "实验室项目")
    private String name;

    @Schema(description = "展示状态，参见 CommonStatusEnum 枚举类", example = "1")
    private Integer status;
}
