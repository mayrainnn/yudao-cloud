package cn.iocoder.mayrain.module.lab.dal.mysql.file;

import cn.iocoder.mayrain.module.lab.dal.dataobject.file.FileDO;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件操作 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface FileMapper extends BaseMapperX<FileDO> {
}
