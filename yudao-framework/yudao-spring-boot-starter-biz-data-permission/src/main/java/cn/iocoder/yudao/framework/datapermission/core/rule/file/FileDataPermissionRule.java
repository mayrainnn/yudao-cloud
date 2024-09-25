package cn.iocoder.yudao.framework.datapermission.core.rule.file;

import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.datapermission.core.rule.DataPermissionRule;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @BelongsProject: yudao-cloud
 * @BelongsPackage: cn.iocoder.yudao.framework.datapermission.core.rule.file
 * @Author: mayrain
 * @CreateTime: 2024-09-24 19:16
 * @Description:
 */
@Component
public class FileDataPermissionRule implements DataPermissionRule {
    @Override
    public Set<String> getTableNames() {
        return Set.of("infra_file");
    }

    @Override
    public Expression getExpression(String tableName, Alias tableAlias) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        if (loginUser == null) {
            return null;
        }
        if (ObjectUtil.equal(loginUser.getUserType(), UserTypeEnum.ADMIN.getValue())) {
            return null;
        }
        switch (tableName) {
            case "infra_file":
                return new EqualsTo(MyBatisUtils.buildColumn(tableName, tableAlias, "creator"), new LongValue((userId)));
            default:return null;
        }
    }
}
