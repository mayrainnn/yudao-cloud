package cn.iocoder.mayrain.module.lab.dal.dataobject.project;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @BelongsProject: yudao-cloud
 * @BelongsPackage: cn.iocoder.mayrain.module.lab.dal.dataobject.project
 * @Author: mayrain
 * @CreateTime: 2024-09-24 22:21
 * @Description:
 */
@TableName("lab_project")
@KeySequence("lab_project_seq")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectDO extends TenantBaseDO {

    public static final Long PARENT_ID_ROOT = 0L;

    /**
     * 项目id
     */
    @TableId
    private Long id;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 父项目id
     */
    private Long parentId;
    /**
     * 显示顺序
     */
    private Integer sort;
    /**
     * 负责人
     */
    private Long leaderUserId;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 地址
     */
    private String address;
    /**
     * 项目头像
     */
    private String logo;
    /**
     * 简介
     */
    private String description;
    /**
     * 项目状态（0正常 1停用）
     */
    private Integer status;
}
