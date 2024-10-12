package cn.iocoder.mayrain.module.lab.service.file;

import cn.iocoder.mayrain.module.lab.controller.admin.file.vo.FileProjectPageReqVO;
import cn.iocoder.mayrain.module.lab.controller.admin.file.vo.FileProjectReqVO;
import cn.iocoder.mayrain.module.lab.dal.dataobject.file.FileProjectDO;
import cn.iocoder.mayrain.module.lab.dal.dataobject.file.FileDO;
import cn.iocoder.mayrain.module.lab.dal.dataobject.file.FileProjectDetailDO;
import cn.iocoder.mayrain.module.lab.dal.mysql.file.FileMapper;
import cn.iocoder.mayrain.module.lab.dal.mysql.file.FileProjectMapper;
import cn.iocoder.mayrain.module.lab.dal.mysql.file.FileProjectDetailMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: yudao-cloud
 * @BelongsPackage: cn.iocoder.mayrain.module.lab.service.file
 * @Author: mayrain
 * @CreateTime: 2024-10-01 18:48
 * @Description:
 */
@Service
@Slf4j
public class FileProjectServiceImpl implements FileProjectService {

    @Resource
    private FileMapper fileMapper;

    @Resource
    private FileProjectMapper fileProjectMapper;

    @Resource
    private FileProjectDetailMapper fileProjectDetailMapper;

    @Override
    public void assignFileProject(FileProjectReqVO reqVO) {
        FileProjectDO fileProjectDO = new FileProjectDO();
        fileProjectDO.setFileId(fileMapper.selectOne(FileDO::getUrl, reqVO.getFilePath()).getId());
        fileProjectDO.setProjectId(reqVO.getProjectId());
        fileProjectMapper.insert(fileProjectDO);
    }

    @Override
    public PageResult<FileProjectDetailDO> getFileProjectPage(FileProjectPageReqVO reqVO) {
        return fileProjectDetailMapper.selectPage(reqVO);
    }

    @Override
    public void deleteFile(Long id) throws Exception {
        fileProjectMapper.deleteById(id);
    }
}
