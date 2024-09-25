package cn.iocoder.mayrain.module.lab.controller.admin.project;

import cn.iocoder.mayrain.module.lab.controller.admin.project.vo.project.ProjectListReqVO;
import cn.iocoder.mayrain.module.lab.controller.admin.project.vo.project.ProjectRespVO;
import cn.iocoder.mayrain.module.lab.controller.admin.project.vo.project.ProjectSaveReqVO;
import cn.iocoder.mayrain.module.lab.controller.admin.project.vo.project.ProjectSimpleRespVO;
import cn.iocoder.mayrain.module.lab.dal.dataobject.project.ProjectDO;
import cn.iocoder.mayrain.module.lab.service.project.ProjectService;
import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


/**
 * @BelongsProject: yudao-cloud
 * @BelongsPackage: cn.iocoder.mayrain.module.lab.controller.admin.project
 * @Author: mayrain
 * @CreateTime: 2024-09-24 21:07
 * @Description:
 */
@Tag(name = "实验室 - 项目")
@RestController
@RequestMapping("/lab/project")
@Validated
public class ProjectController {

    @Resource
    private ProjectService projectService;

    /**
     * 创建项目
     * @Author: mayrain
     * @param createReqVO 创建信息
     * @return 编号
     */
    @PostMapping("/create")
    @Operation(summary = "创建项目")
    @PreAuthorize("@ss.hasPermission('lab:project:create')")
    public CommonResult<Long> createProject(@Valid @RequestBody ProjectSaveReqVO createReqVO) {
        Long projectId = projectService.createProject(createReqVO);
        return success(projectId);
    }

    /**
     * 获得项目列表
     * @Author: mayrain
     * @param reqVO 列表查询参数
     * @return 项目列表
     */
    @GetMapping("/list")
    @Operation(summary = "获得项目列表")
    @PreAuthorize("@ss.hasPermission('lab:project:query')")
    public CommonResult<List<ProjectRespVO>> getProjectList(ProjectListReqVO reqVO) {
        List<ProjectDO> list = projectService.getProjectList(reqVO);
        return success(BeanUtils.toBean(list, ProjectRespVO.class));
    }

    /**
     * 获得项目精简信息列表
     * @Author: mayrain
     * @return 项目精简信息列表
     */
    @GetMapping(value = {"/list-all-simple", "/simple-list"})
    @Operation(summary = "获取项目精简信息列表", description = "只包含被开启的项目，主要用于前端的下拉选项")
    public CommonResult<List<ProjectSimpleRespVO>> getSimpleProjectList() {
        // 获得项目列表，只要开启状态的
        List<ProjectDO> list = projectService.getProjectList(
                new ProjectListReqVO().setStatus(CommonStatusEnum.ENABLE.getStatus()));
        // 排序后，返回给前端
        return success(BeanUtils.toBean(list, ProjectSimpleRespVO.class));
    }

    /**
     * 修改项目
     * @Author: mayrain
     * @param updateReqVO 更新信息
     * @return 是否成功
     */
    @PutMapping("/update")
    @Operation(summary = "修改项目")
    @PreAuthorize("@ss.hasPermission('lab:project:update')")
    public CommonResult<Boolean> updateProject(@Valid @RequestBody ProjectSaveReqVO updateReqVO) {
        projectService.updateProject(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除项目")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('lab:project:delete')")
    public CommonResult<Boolean> deleteProject(@RequestParam("id") Long id) {
        projectService.deleteProject(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得项目信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('lab:project:query')")
    public CommonResult<ProjectRespVO> getProject(@RequestParam("id") Long id) {
        ProjectDO project = projectService.getProject(id);
        return success(BeanUtils.toBean(project, ProjectRespVO.class));
    }
}
