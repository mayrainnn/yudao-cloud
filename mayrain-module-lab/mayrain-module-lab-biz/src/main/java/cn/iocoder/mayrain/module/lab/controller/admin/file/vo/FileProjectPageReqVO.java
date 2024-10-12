package cn.iocoder.mayrain.module.lab.controller.admin.file.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * @BelongsProject: yudao-cloud
 * @BelongsPackage: cn.iocoder.mayrain.module.lab.controller.admin.file.vo
 * @Author: mayrain
 * @CreateTime: 2024-10-02 13:45
 * @Description:
 */

@Schema(description = "实验室 - 项目文件分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FileProjectPageReqVO extends PageParam {

    @Schema(description = "文件名，模糊匹配", example = "yudao")
    private String name;

    @Schema(description = "文件类型，模糊匹配", example = "jpg")
    private String type;

    @Schema(description = "项目id", example = "1")
    private Long projectId;

    @Schema(description = "创建时间", example = "[2022-07-01 00:00:00, 2022-07-01 23:59:59]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;
}
