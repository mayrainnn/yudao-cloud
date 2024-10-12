package cn.iocoder.mayrain.module.lab.controller.admin.file;

import cn.hutool.db.Page;
import cn.iocoder.mayrain.module.lab.controller.admin.file.vo.FileProjectPageReqVO;
import cn.iocoder.mayrain.module.lab.controller.admin.file.vo.FileProjectReqVO;
import cn.iocoder.mayrain.module.lab.controller.admin.file.vo.FileProjectRespVO;
import cn.iocoder.mayrain.module.lab.dal.dataobject.file.FileProjectDetailDO;
import cn.iocoder.mayrain.module.lab.service.file.FileProjectService;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


/**
 * @BelongsProject: yudao-cloud
 * @BelongsPackage: cn.iocoder.mayrain.module.lab.controller.admin.file.vo
 * @Author: mayrain
 * @CreateTime: 2024-10-01 18:35
 * @Description:
 */

@Tag(name = "实验室 - 文件")
@RestController
@RequestMapping("/lab/file")
@Validated
public class FileProjectController {

    @Resource
    private FileProjectService fileProjectService;

    /**
     * 为项目分配文件
     * @Author: mayrain
     * @param reqVO 创建信息
     * @return 编号
     */
    @PostMapping("/assign-file-project")
    @Operation(summary = "为项目分配文件")
    public CommonResult<Boolean> assignFileProject(@Validated @RequestBody FileProjectReqVO reqVO) {
        fileProjectService.assignFileProject(reqVO);
        return success(true);
    }

    /**
     * 获得文件分页
     * @Author: mayrain
     * @param reqVO 分页信息
     * @return 文件分页信息
     */
    @GetMapping("/page")
    @Operation(summary = "获得文件分页")
    public CommonResult<PageResult<FileProjectRespVO>> getFilePage(@Valid FileProjectPageReqVO reqVO) {
        PageResult<FileProjectDetailDO> pageResult = fileProjectService.getFileProjectPage(reqVO);
        return success(BeanUtils.toBean(pageResult, FileProjectRespVO.class));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除文件和项目绑定关系")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteFile(@RequestParam("id") Long id) throws Exception {
        fileProjectService.deleteFile(id);
        return success(true);
    }
}
