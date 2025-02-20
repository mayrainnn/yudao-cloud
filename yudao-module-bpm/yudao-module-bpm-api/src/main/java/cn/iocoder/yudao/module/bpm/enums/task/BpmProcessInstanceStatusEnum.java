package cn.iocoder.yudao.module.bpm.enums.task;

<<<<<<< HEAD
import cn.iocoder.yudao.framework.common.core.IntArrayValuable;
=======
import cn.iocoder.yudao.framework.common.core.ArrayValuable;
import cn.iocoder.yudao.framework.common.util.object.ObjectUtils;
>>>>>>> master-jdk17
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 流程实例 ProcessInstance 的状态
 *
 * @author 芋道源码
 */
@Getter
@AllArgsConstructor
public enum BpmProcessInstanceStatusEnum implements ArrayValuable<Integer> {

    RUNNING(1, "审批中"),
    APPROVE(2, "审批通过"),
    REJECT(3, "审批不通过"),
    CANCEL(4, "已取消");

    public static final Integer[] ARRAYS = Arrays.stream(values()).map(BpmProcessInstanceStatusEnum::getStatus).toArray(Integer[]::new);

    /**
     * 状态
     */
    private final Integer status;
    /**
     * 描述
     */
    private final String desc;

    @Override
    public Integer[] array() {
        return ARRAYS;
    }

<<<<<<< HEAD
=======
    public static boolean isRejectStatus(Integer status) {
        return REJECT.getStatus().equals(status);
    }

    public static boolean isProcessEndStatus(Integer status) {
        return ObjectUtils.equalsAny(status,
                APPROVE.getStatus(), REJECT.getStatus(), CANCEL.getStatus());
    }

>>>>>>> master-jdk17
}
