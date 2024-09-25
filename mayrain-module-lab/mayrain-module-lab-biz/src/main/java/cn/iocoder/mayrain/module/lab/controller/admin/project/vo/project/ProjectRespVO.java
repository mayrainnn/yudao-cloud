package cn.iocoder.mayrain.module.lab.controller.admin.project.vo.project;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @BelongsProject: yudao-cloud
 * @BelongsPackage: cn.iocoder.mayrain.module.lab.controller.admin.project.vo.project
 * @Author: mayrain
 * @CreateTime: 2024-09-25 16:26
 * @Description:
 */
@Schema(description = "管理后台 - 实验室项目 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ProjectRespVO {
    @Schema(description = "项目id", requiredMode = Schema.RequiredMode.REQUIRED, example = "26183")
    @ExcelProperty("项目id")
    private Long id;

    @Schema(description = "项目名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("项目名称")
    private String name;

    @Schema(description = "父项目id", requiredMode = Schema.RequiredMode.REQUIRED, example = "19320")
    @ExcelProperty("父项目id")
    private Long parentId;

    @Schema(description = "显示顺序", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("显示顺序")
    private Integer sort;

    @Schema(description = "负责人", example = "15464")
    @ExcelProperty("负责人")
    private Long leaderUserId;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String phone;

    @Schema(description = "邮箱")
    @ExcelProperty("邮箱")
    private String email;

    @Schema(description = "地址")
    @ExcelProperty("地址")
    private String address;

    @Schema(description = "项目头像")
    @ExcelProperty("项目头像")
    private String logo;

    @Schema(description = "简介", example = "你猜")
    @ExcelProperty("简介")
    private String description;

    @Schema(description = "项目状态（0正常 1停用）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("项目状态（0正常 1停用）")
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;
}
