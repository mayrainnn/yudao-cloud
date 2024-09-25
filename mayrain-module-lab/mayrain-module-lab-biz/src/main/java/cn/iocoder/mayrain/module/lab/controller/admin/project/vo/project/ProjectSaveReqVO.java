package cn.iocoder.mayrain.module.lab.controller.admin.project.vo.project;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.validation.InEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @BelongsProject: yudao-cloud
 * @BelongsPackage: cn.iocoder.mayrain.module.lab.controller.admin.project.vo.project
 * @Author: mayrain
 * @CreateTime: 2024-09-24 21:55
 * @Description:
 */
@Schema(description = "实验室 - 项目创建/修改 Request VO")
@Data
public class ProjectSaveReqVO {
    @Schema(description = "项目编号", example = "1024")
    private Long id;

    @Schema(description = "项目名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋道")
    @NotBlank(message = "项目名称不能为空")
    @Size(max = 30, message = "项目名称长度不能超过 30 个字符")
    private String name;

    @Schema(description = "父项目 ID", example = "1024")
    private Long parentId;

    @Schema(description = "显示顺序", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;

    @Schema(description = "负责人的用户编号", example = "2048")
    private Long leaderUserId;

    @Schema(description = "联系电话", example = "15601691000")
    @Size(max = 11, message = "联系电话长度不能超过11个字符")
    private String phone;

    @Schema(description = "邮箱", example = "yudao@iocoder.cn")
    @Email(message = "邮箱格式不正确")
    @Size(max = 50, message = "邮箱长度不能超过 50 个字符")
    private String email;

    @Schema(description = "地址", example = "http://127.0.0.1:9180")
    @Size(max = 255, message = "地址长度不能超过 255 个字符")
    private String address;

    @Schema(description = "项目照片", example = "http://127.0.0.1:9180")
    @Size(max = 255, message = "项目照片长度不能超过 255 个字符")
    private String logo;

    @Schema(description = "描述", example = "这是一个伟大的项目")
    @Size(max = 255, message = "描述长度不能超过 255 个字符")
    private String description;

    @Schema(description = "状态,见 CommonStatusEnum 枚举", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;
}
