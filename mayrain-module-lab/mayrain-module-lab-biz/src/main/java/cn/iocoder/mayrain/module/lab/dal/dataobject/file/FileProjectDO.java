package cn.iocoder.mayrain.module.lab.dal.dataobject.file;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @BelongsProject: yudao-cloud
 * @BelongsPackage: cn.iocoder.mayrain.module.lab.dal.dataobject.file
 * @Author: mayrain
 * @CreateTime: 2024-10-01 19:18
 * @Description:
 */
@TableName("lab_project_file")
@KeySequence("lab_project_file_seq")
@Data
@EqualsAndHashCode(callSuper = true)
public class FileProjectDO extends TenantBaseDO {

    /**
     * 自增主键
     */
    @TableId
    private Long id;
    /**
     * 项目ID
     */
    private Long projectId;
    /**
     * 文件ID
     */
    private Long fileId;
}
