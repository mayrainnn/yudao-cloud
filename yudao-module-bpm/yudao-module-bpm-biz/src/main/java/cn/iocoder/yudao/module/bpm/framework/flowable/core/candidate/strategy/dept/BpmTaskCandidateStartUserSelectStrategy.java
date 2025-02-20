package cn.iocoder.yudao.module.bpm.framework.flowable.core.candidate.strategy.dept;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
<<<<<<< HEAD:yudao-module-bpm/yudao-module-bpm-biz/src/main/java/cn/iocoder/yudao/module/bpm/framework/flowable/core/candidate/strategy/BpmTaskCandidateStartUserSelectStrategy.java
import cn.iocoder.yudao.module.bpm.framework.flowable.core.candidate.BpmTaskCandidateStrategy;
=======
import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.module.bpm.framework.flowable.core.candidate.strategy.user.BpmTaskCandidateUserStrategy;
>>>>>>> master-jdk17:yudao-module-bpm/yudao-module-bpm-biz/src/main/java/cn/iocoder/yudao/module/bpm/framework/flowable/core/candidate/strategy/dept/BpmTaskCandidateStartUserSelectStrategy.java
import cn.iocoder.yudao.module.bpm.framework.flowable.core.enums.BpmTaskCandidateStrategyEnum;
import cn.iocoder.yudao.module.bpm.framework.flowable.core.util.BpmnModelUtils;
import cn.iocoder.yudao.module.bpm.framework.flowable.core.util.FlowableUtils;
import cn.iocoder.yudao.module.bpm.service.task.BpmProcessInstanceService;
<<<<<<< HEAD:yudao-module-bpm/yudao-module-bpm-biz/src/main/java/cn/iocoder/yudao/module/bpm/framework/flowable/core/candidate/strategy/BpmTaskCandidateStartUserSelectStrategy.java
=======
import com.google.common.collect.Sets;
>>>>>>> master-jdk17:yudao-module-bpm/yudao-module-bpm-biz/src/main/java/cn/iocoder/yudao/module/bpm/framework/flowable/core/candidate/strategy/dept/BpmTaskCandidateStartUserSelectStrategy.java
import jakarta.annotation.Resource;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.ServiceTask;
import org.flowable.bpmn.model.Task;
import org.flowable.bpmn.model.UserTask;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 发起人自选 {@link BpmTaskCandidateUserStrategy} 实现类
 *
 * @author 芋道源码
 */
@Component
<<<<<<< HEAD:yudao-module-bpm/yudao-module-bpm-biz/src/main/java/cn/iocoder/yudao/module/bpm/framework/flowable/core/candidate/strategy/BpmTaskCandidateStartUserSelectStrategy.java
public class BpmTaskCandidateStartUserSelectStrategy implements BpmTaskCandidateStrategy {
=======
public class BpmTaskCandidateStartUserSelectStrategy extends AbstractBpmTaskCandidateDeptLeaderStrategy {
>>>>>>> master-jdk17:yudao-module-bpm/yudao-module-bpm-biz/src/main/java/cn/iocoder/yudao/module/bpm/framework/flowable/core/candidate/strategy/dept/BpmTaskCandidateStartUserSelectStrategy.java

    @Resource
    @Lazy // 延迟加载，避免循环依赖
    private BpmProcessInstanceService processInstanceService;

    @Override
    public BpmTaskCandidateStrategyEnum getStrategy() {
        return BpmTaskCandidateStrategyEnum.START_USER_SELECT;
    }

    @Override
    public void validateParam(String param) {}

    @Override
    public boolean isParamRequired() {
        return false;
    }

    @Override
    public LinkedHashSet<Long> calculateUsersByTask(DelegateExecution execution, String param) {
        ProcessInstance processInstance = processInstanceService.getProcessInstance(execution.getProcessInstanceId());
        Assert.notNull(processInstance, "流程实例({})不能为空", execution.getProcessInstanceId());
        Map<String, List<Long>> startUserSelectAssignees = FlowableUtils.getStartUserSelectAssignees(processInstance);
        Assert.notNull(startUserSelectAssignees, "流程实例({}) 的发起人自选审批人不能为空",
                execution.getProcessInstanceId());
        // 获得审批人
        List<Long> assignees = startUserSelectAssignees.get(execution.getCurrentActivityId());
        return new LinkedHashSet<>(assignees);
    }

    @Override
<<<<<<< HEAD:yudao-module-bpm/yudao-module-bpm-biz/src/main/java/cn/iocoder/yudao/module/bpm/framework/flowable/core/candidate/strategy/BpmTaskCandidateStartUserSelectStrategy.java
    public boolean isParamRequired() {
        return false;
=======
    public LinkedHashSet<Long> calculateUsersByActivity(BpmnModel bpmnModel, String activityId, String param,
                                                        Long startUserId, String processDefinitionId, Map<String, Object> processVariables) {
        if (processVariables == null) {
            return Sets.newLinkedHashSet();
        }
        Map<String, List<Long>> startUserSelectAssignees = FlowableUtils.getStartUserSelectAssignees(processVariables);
        if (startUserSelectAssignees == null) {
            return Sets.newLinkedHashSet();
        }
        // 获得审批人
        List<Long> assignees = startUserSelectAssignees.get(activityId);
        return new LinkedHashSet<>(assignees);
>>>>>>> master-jdk17:yudao-module-bpm/yudao-module-bpm-biz/src/main/java/cn/iocoder/yudao/module/bpm/framework/flowable/core/candidate/strategy/dept/BpmTaskCandidateStartUserSelectStrategy.java
    }

    /**
     * 获得发起人自选审批人或抄送人的 Task 列表
     *
     * @param bpmnModel BPMN 模型
     * @return Task 列表
     */
    public static List<Task> getStartUserSelectTaskList(BpmnModel bpmnModel) {
        if (bpmnModel == null) {
            return Collections.emptyList();
        }
        List<Task> tasks = new ArrayList<>();
        tasks.addAll(BpmnModelUtils.getBpmnModelElements(bpmnModel, UserTask.class));
        tasks.addAll(BpmnModelUtils.getBpmnModelElements(bpmnModel, ServiceTask.class));
        if (CollUtil.isEmpty(tasks)) {
            return Collections.emptyList();
        }
        tasks.removeIf(task -> ObjectUtil.notEqual(BpmnModelUtils.parseCandidateStrategy(task),
                BpmTaskCandidateStrategyEnum.START_USER_SELECT.getStrategy()));
        return tasks;
    }

}
