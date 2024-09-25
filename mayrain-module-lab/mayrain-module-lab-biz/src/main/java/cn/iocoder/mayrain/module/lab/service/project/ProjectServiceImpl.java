package cn.iocoder.mayrain.module.lab.service.project;

import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.mayrain.module.lab.controller.admin.project.vo.project.ProjectListReqVO;
import cn.iocoder.mayrain.module.lab.controller.admin.project.vo.project.ProjectSaveReqVO;
import cn.iocoder.mayrain.module.lab.dal.dataobject.project.ProjectDO;
import cn.iocoder.mayrain.module.lab.dal.mysql.project.ProjectMapper;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import com.google.common.annotations.VisibleForTesting;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * @BelongsProject: yudao-cloud
 * @BelongsPackage: cn.iocoder.mayrain.module.lab.service.project
 * @Author: mayrain
 * @CreateTime: 2024-09-24 22:05
 * @Description:
 */
@Service
@Validated
@Slf4j
public class ProjectServiceImpl implements ProjectService{

    @Resource
    private ProjectMapper projectMapper;

    @Override
    public Long createProject(ProjectSaveReqVO createReqVO) {
        if (createReqVO.getParentId() == null) {
            createReqVO.setParentId(ProjectDO.PARENT_ID_ROOT);
        }

        // 检验父项目的有效性
        validateParentProject(null, createReqVO.getParentId());
        // 检验项目名的唯一性
        validateProjectNameUnique(null, createReqVO.getParentId(), createReqVO.getName());
        // 插入项目
        ProjectDO project = BeanUtils.toBean(createReqVO, ProjectDO.class);
        projectMapper.insert(project);
        return project.getId();
    }

    /**
     * 获得项目列表
     * @Author: mayrain
     */
    @Override
    public List<ProjectDO> getProjectList(ProjectListReqVO reqVO) {
        List<ProjectDO> list = projectMapper.selectList(reqVO);
        list.sort(Comparator.comparing(ProjectDO::getSort));
        return list;
    }

    /**
     * 更新项目
     * @Author: mayrain
     */
    @Override
    public void updateProject(ProjectSaveReqVO updateReqVO) {
        if (updateReqVO.getParentId() == null) {
            updateReqVO.setParentId(ProjectDO.PARENT_ID_ROOT);
        }
        // 检验项目是否存在
        validateProjectExists(updateReqVO.getId());
        // 检验父项目的有效性
        validateParentProject(updateReqVO.getId(), updateReqVO.getParentId());
        // 检验项目名的唯一性
        validateProjectNameUnique(updateReqVO.getId(), updateReqVO.getParentId(), updateReqVO.getName());
        // 更新项目
        ProjectDO updateObj = BeanUtils.toBean(updateReqVO, ProjectDO.class);
        projectMapper.updateById(updateObj);
    }

    /**
     * 删除项目
     * @Author: mayrain
     */
    @Override
    public void deleteProject(Long id) {
        // 校验项目是否存在
        validateProjectExists(id);
        if (projectMapper.selectCountByParentId(id) > 0) {
            throw exception(PROJECT_EXITS_CHILDREN);
        }
        // 删除项目
        projectMapper.deleteById(id);
    }

    /**
     * 获得项目
     * @Author: mayrain
     */
    @Override
    public ProjectDO getProject(Long id) {
        return projectMapper.selectById(id);
    }

    /**
     * 检验项目是否存在
     * @Author: mayrain
     */
    @VisibleForTesting
    void validateProjectExists(Long id) {
        if (id == null) {
            return;
        }
        ProjectDO project = projectMapper.selectById(id);
        if (project == null) {
            throw exception(PROJECT_NOT_FOUND);
        }
    }

    /**
     * 校验项目父级项目是否合法
     * @Author: mayrain
     * @param id 项目编号
     * @param parentId 父项目编号
     */
    @VisibleForTesting
    void validateParentProject(Long id, Long parentId) {
        if (parentId == null || ProjectDO.PARENT_ID_ROOT.equals(parentId)){
            return;
        }
        // 1. 校验自己不能设置自己为父项目
        if (Objects.equals(id, parentId)) {
            throw exception(PROJECT_PARENT_ERROR);
        }
        // 2. 校验父项目是否存在
        ProjectDO parentProject = projectMapper.selectById(parentId);
        if (parentProject == null) {
            return;
        }
        // 3. 校验是否为父项目的子项目
        if (id == null) {
            return;
        }
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            // 3.1 校验环路
            parentId = parentProject.getParentId();
            if (Objects.equals(id, parentId)) {
                throw exception(PROJECT_PARENT_IS_CHILD);
            }
            // 3.2 继续递归下一级父项目
            if (parentId == null || ProjectDO.PARENT_ID_ROOT.equals(parentId)) {
                break;
            }
            parentProject = projectMapper.selectById(parentId);
            if (parentProject == null) {
                break;
            }
        }
    }

    /**
     * 校验项目名称是否重复
     * @Author: mayrain
     * @param id 项目编号
     * @param parentId 父项目编号
     * @param name 项目名称
     */
    @VisibleForTesting
    void validateProjectNameUnique(Long id, Long parentId, String name) {
        ProjectDO project = projectMapper.selectByParentIdAndName(parentId, name);
        if (project == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的项目
        if (id == null) {
            throw exception(PROJECT_NAME_DUPLICATE);
        }
        if (ObjectUtil.notEqual(project.getId(), id)) {
            throw exception(PROJECT_NAME_DUPLICATE);
        }
    }
}
