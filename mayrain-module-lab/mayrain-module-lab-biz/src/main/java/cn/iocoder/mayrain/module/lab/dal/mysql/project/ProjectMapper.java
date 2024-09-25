package cn.iocoder.mayrain.module.lab.dal.mysql.project;

import cn.iocoder.mayrain.module.lab.controller.admin.project.vo.project.ProjectListReqVO;
import cn.iocoder.mayrain.module.lab.dal.dataobject.project.ProjectDO;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @BelongsProject: yudao-cloud
 * @BelongsPackage: cn.iocoder.mayrain.module.lab.dal.mysql.project
 * @Author: mayrain
 * @CreateTime: 2024-09-25 15:12
 * @Description:
 */
@Mapper
public interface ProjectMapper extends BaseMapperX<ProjectDO> {

    default List<ProjectDO> selectList(ProjectListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProjectDO>()
                .likeIfPresent(ProjectDO::getName, reqVO.getName())
                .eqIfPresent(ProjectDO::getStatus, reqVO.getStatus()));
    }

    default ProjectDO selectByParentIdAndName(Long parentId, String name) {
        return selectOne(ProjectDO::getParentId, parentId, ProjectDO::getName, name);
    }

    default Long selectCountByParentId(Long parentId) {
        return selectCount(ProjectDO::getParentId, parentId);
    }
}
