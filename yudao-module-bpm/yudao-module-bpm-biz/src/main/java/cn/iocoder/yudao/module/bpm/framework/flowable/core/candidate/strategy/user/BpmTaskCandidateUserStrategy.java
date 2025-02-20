package cn.iocoder.yudao.module.bpm.framework.flowable.core.candidate.strategy.user;

import cn.iocoder.yudao.framework.common.util.string.StrUtils;
import cn.iocoder.yudao.module.bpm.framework.flowable.core.candidate.BpmTaskCandidateStrategy;
import cn.iocoder.yudao.module.bpm.framework.flowable.core.enums.BpmTaskCandidateStrategyEnum;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import jakarta.annotation.Resource;
<<<<<<< HEAD:yudao-module-bpm/yudao-module-bpm-biz/src/main/java/cn/iocoder/yudao/module/bpm/framework/flowable/core/candidate/strategy/BpmTaskCandidateUserStrategy.java
import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

import java.util.Set;
=======
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
>>>>>>> master-jdk17:yudao-module-bpm/yudao-module-bpm-biz/src/main/java/cn/iocoder/yudao/module/bpm/framework/flowable/core/candidate/strategy/user/BpmTaskCandidateUserStrategy.java

/**
 * 用户 {@link BpmTaskCandidateStrategy} 实现类
 *
 * @author kyle
 */
@Component
public class BpmTaskCandidateUserStrategy implements BpmTaskCandidateStrategy {

    @Resource
    private AdminUserApi adminUserApi;

    @Override
    public BpmTaskCandidateStrategyEnum getStrategy() {
        return BpmTaskCandidateStrategyEnum.USER;
    }

    @Override
    public void validateParam(String param) {
        adminUserApi.validateUserList(StrUtils.splitToLongSet(param)).checkError();
    }

    @Override
<<<<<<< HEAD:yudao-module-bpm/yudao-module-bpm-biz/src/main/java/cn/iocoder/yudao/module/bpm/framework/flowable/core/candidate/strategy/BpmTaskCandidateUserStrategy.java
    public Set<Long> calculateUsers(DelegateExecution execution, String param) {
        return StrUtils.splitToLongSet(param);
=======
    public LinkedHashSet<Long> calculateUsers(String param) {
        return new LinkedHashSet<>(StrUtils.splitToLong(param, StrPool.COMMA));
>>>>>>> master-jdk17:yudao-module-bpm/yudao-module-bpm-biz/src/main/java/cn/iocoder/yudao/module/bpm/framework/flowable/core/candidate/strategy/user/BpmTaskCandidateUserStrategy.java
    }

}