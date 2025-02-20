package cn.iocoder.yudao.module.bpm.service.task;

import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.bpm.controller.admin.task.vo.instance.BpmProcessInstanceCopyPageReqVO;
import cn.iocoder.yudao.module.bpm.dal.dataobject.task.BpmProcessInstanceCopyDO;
import cn.iocoder.yudao.module.bpm.dal.mysql.task.BpmProcessInstanceCopyMapper;
import cn.iocoder.yudao.module.bpm.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.bpm.service.definition.BpmProcessDefinitionService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;

/**
 * 流程抄送 Service 实现类
 *
 * @author kyle
 */
@Service
@Validated
@Slf4j
public class BpmProcessInstanceCopyServiceImpl implements BpmProcessInstanceCopyService {

    @Resource
    private BpmProcessInstanceCopyMapper processInstanceCopyMapper;

    @Resource
    @Lazy // 延迟加载，避免循环依赖
    private BpmTaskService taskService;

    @Resource
    @Lazy // 延迟加载，避免循环依赖
    private BpmProcessInstanceService processInstanceService;
    @Resource
    @Lazy // 延迟加载，避免循环依赖
    private BpmProcessDefinitionService processDefinitionService;

    @Override
<<<<<<< HEAD
    public void createProcessInstanceCopy(Collection<Long> userIds, String taskId) {
        // 1.1 校验任务存在
=======
    public void createProcessInstanceCopy(Collection<Long> userIds, String reason, String taskId) {
>>>>>>> master-jdk17
        Task task = taskService.getTask(taskId);
        if (ObjectUtil.isNull(task)) {
            throw exception(ErrorCodeConstants.TASK_NOT_EXISTS);
        }
<<<<<<< HEAD
        // 1.2 校验流程实例存在
        String processInstanceId = task.getProcessInstanceId();
=======
        // 执行抄送
        createProcessInstanceCopy(userIds, reason,
                task.getProcessInstanceId(), task.getTaskDefinitionKey(), task.getId(), task.getName());
    }

    @Override
    public void createProcessInstanceCopy(Collection<Long> userIds, String reason, String processInstanceId,
                                          String activityId, String activityName, String taskId) {
        // 1.1 校验流程实例存在
>>>>>>> master-jdk17
        ProcessInstance processInstance = processInstanceService.getProcessInstance(processInstanceId);
        if (processInstance == null) {
            throw exception(ErrorCodeConstants.PROCESS_INSTANCE_NOT_EXISTS);
        }
        // 1.3 校验流程定义存在
        ProcessDefinition processDefinition = processDefinitionService.getProcessDefinition(
                processInstance.getProcessDefinitionId());
        if (processDefinition == null) {
            throw exception(ErrorCodeConstants.PROCESS_DEFINITION_NOT_EXISTS);
        }

        // 2. 创建抄送流程
        List<BpmProcessInstanceCopyDO> copyList = convertList(userIds, userId -> new BpmProcessInstanceCopyDO()
                .setUserId(userId).setReason(reason).setStartUserId(Long.valueOf(processInstance.getStartUserId()))
                .setProcessInstanceId(processInstanceId).setProcessInstanceName(processInstance.getName())
<<<<<<< HEAD
                .setCategory(processDefinition.getCategory()).setTaskId(taskId).setTaskName(task.getName()));
=======
                .setCategory(processDefinition.getCategory()).setTaskId(taskId)
                .setActivityId(activityId).setActivityName(activityName)
                .setProcessDefinitionId(processInstance.getProcessDefinitionId()));
>>>>>>> master-jdk17
        processInstanceCopyMapper.insertBatch(copyList);
    }

    @Override
    public PageResult<BpmProcessInstanceCopyDO> getProcessInstanceCopyPage(Long userId,
                                                                           BpmProcessInstanceCopyPageReqVO pageReqVO) {
        return processInstanceCopyMapper.selectPage(userId, pageReqVO);
    }

<<<<<<< HEAD
=======
    @Override
    public void deleteProcessInstanceCopy(String processInstanceId) {
        processInstanceCopyMapper.deleteByProcessInstanceId(processInstanceId);
    }

>>>>>>> master-jdk17
}
