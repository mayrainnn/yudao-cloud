package cn.iocoder.mayrain.module.lab.service.project;

import cn.iocoder.mayrain.module.lab.controller.admin.project.vo.project.ProjectListReqVO;
import cn.iocoder.mayrain.module.lab.controller.admin.project.vo.project.ProjectSaveReqVO;
import cn.iocoder.mayrain.module.lab.dal.dataobject.project.ProjectDO;

import java.util.List;

/**
 * @BelongsProject: yudao-cloud
 * @BelongsPackage: cn.iocoder.mayrain.module.lab.service.project
 * @Author: mayrain
 * @CreateTime: 2024-09-24 22:04
 * @Description:
 */
public interface ProjectService {
    /**
     * 创建部门
     * @Author: mayrain
     * @param createReqVO 项目信息
     * @return 项目编号
     */
    Long createProject(ProjectSaveReqVO createReqVO);

    /**
     * 获得部门列表
     * @Author: mayrain
     * @param reqVO 项目信息
     * @return 项目列表
     */
    List<ProjectDO> getProjectList(ProjectListReqVO reqVO);

    /**
     * 更新部门
     * @Author: mayrain
     * @param updateReqVO 项目信息
     */
    void updateProject(ProjectSaveReqVO updateReqVO);

    /**
     * 删除部门
     * @Author: mayrain
     * @param id 项目编号
     */
    void deleteProject(Long id);

    /**
     * 获得部门信息
     * @Author: mayrain
     * @param id 项目编号
     * @return 项目信息
     */
    ProjectDO getProject(Long id);
}
