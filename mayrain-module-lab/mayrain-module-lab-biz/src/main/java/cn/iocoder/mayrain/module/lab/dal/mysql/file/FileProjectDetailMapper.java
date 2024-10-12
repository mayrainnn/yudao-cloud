package cn.iocoder.mayrain.module.lab.dal.mysql.file;

import cn.iocoder.mayrain.module.lab.controller.admin.file.vo.FileProjectPageReqVO;
import cn.iocoder.mayrain.module.lab.dal.dataobject.file.FileDO;
import cn.iocoder.mayrain.module.lab.dal.dataobject.file.FileProjectDO;
import cn.iocoder.mayrain.module.lab.dal.dataobject.file.FileProjectDetailDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.MPJLambdaWrapperX;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject: yudao-cloud
 * @BelongsPackage: cn.iocoder.mayrain.module.lab.dal.mysql.file
 * @Author: mayrain
 * @CreateTime: 2024-10-02 13:31
 * @Description:
 */
@Mapper
public interface FileProjectDetailMapper extends BaseMapperX<FileProjectDetailDO> {

    default PageResult<FileProjectDetailDO> selectPage(FileProjectPageReqVO reqVO) {
        MPJLambdaWrapperX<FileProjectDetailDO> query = new MPJLambdaWrapperX<FileProjectDetailDO>()
                .selectAll(FileDO.class)
                .selectAs(FileProjectDO::getProjectId, FileProjectDetailDO::getProjectId)
                .likeIfPresent(FileDO::getName, reqVO.getName())
                .likeIfPresent(FileDO::getType, reqVO.getType())
                .betweenIfPresent(FileDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FileDO::getId);
        query.innerJoin(FileProjectDO.class, on -> on.eq(FileProjectDO::getFileId, FileDO::getId))
                .eq(reqVO.getProjectId() !=null, FileProjectDO::getProjectId, reqVO.getProjectId());
        return selectJoinPage(reqVO, FileProjectDetailDO.class, query);
    }
}
