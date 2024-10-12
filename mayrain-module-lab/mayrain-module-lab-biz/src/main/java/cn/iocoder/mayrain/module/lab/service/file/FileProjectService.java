package cn.iocoder.mayrain.module.lab.service.file;

import cn.iocoder.mayrain.module.lab.controller.admin.file.vo.FileProjectPageReqVO;
import cn.iocoder.mayrain.module.lab.controller.admin.file.vo.FileProjectReqVO;
import cn.iocoder.mayrain.module.lab.dal.dataobject.file.FileProjectDetailDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * @BelongsProject: yudao-cloud
 * @BelongsPackage: cn.iocoder.mayrain.module.lab.service.file
 * @Author: mayrain
 * @CreateTime: 2024-10-01 18:47
 * @Description:
 */
public interface FileProjectService {

    void assignFileProject(FileProjectReqVO reqVO);

    PageResult<FileProjectDetailDO> getFileProjectPage(FileProjectPageReqVO reqVO);

    void deleteFile(Long id) throws Exception;
}
