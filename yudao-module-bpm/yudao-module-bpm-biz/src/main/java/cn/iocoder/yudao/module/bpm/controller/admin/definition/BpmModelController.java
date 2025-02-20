package cn.iocoder.yudao.module.bpm.controller.admin.definition;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
<<<<<<< HEAD
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.io.IoUtils;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
=======
>>>>>>> master-jdk17
import cn.iocoder.yudao.module.bpm.controller.admin.definition.vo.model.*;
import cn.iocoder.yudao.module.bpm.convert.definition.BpmModelConvert;
import cn.iocoder.yudao.module.bpm.dal.dataobject.definition.BpmCategoryDO;
import cn.iocoder.yudao.module.bpm.dal.dataobject.definition.BpmFormDO;
import cn.iocoder.yudao.module.bpm.service.definition.BpmCategoryService;
import cn.iocoder.yudao.module.bpm.service.definition.BpmFormService;
import cn.iocoder.yudao.module.bpm.service.definition.BpmModelService;
import cn.iocoder.yudao.module.bpm.service.definition.BpmProcessDefinitionService;
import cn.iocoder.yudao.module.bpm.service.definition.dto.BpmModelMetaInfoRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.Model;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.io.IOException;
import java.util.HashSet;
=======
import java.util.Collections;
>>>>>>> master-jdk17
import java.util.List;
import java.util.Map;
import java.util.Set;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertMap;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;

@Tag(name = "管理后台 - 流程模型")
@RestController
@RequestMapping("/bpm/model")
@Validated
public class BpmModelController {

    @Resource
    private BpmModelService modelService;
    @Resource
    private BpmFormService formService;
    @Resource
    private BpmCategoryService categoryService;
    @Resource
    private BpmProcessDefinitionService processDefinitionService;

<<<<<<< HEAD
    @GetMapping("/page")
=======
    @Resource
    private AdminUserApi adminUserApi;

    @GetMapping("/list")
>>>>>>> master-jdk17
    @Operation(summary = "获得模型分页")
    @Parameter(name = "name", description = "模型名称", example = "芋艿")
    public CommonResult<List<BpmModelRespVO>> getModelPage(@RequestParam(value = "name", required = false) String name) {
        List<Model> list = modelService.getModelList(name);
        if (CollUtil.isEmpty(list)) {
            return success(Collections.emptyList());
        }

        // 获得 Form 表单
<<<<<<< HEAD
        Set<Long> formIds = convertSet(pageResult.getList(), model -> {
            BpmModelMetaInfoRespDTO metaInfo = JsonUtils.parseObject(model.getMetaInfo(), BpmModelMetaInfoRespDTO.class);
=======
        Set<Long> formIds = convertSet(list, model -> {
            BpmModelMetaInfoVO metaInfo = BpmModelConvert.INSTANCE.parseMetaInfo(model);
>>>>>>> master-jdk17
            return metaInfo != null ? metaInfo.getFormId() : null;
        });
        Map<Long, BpmFormDO> formMap = formService.getFormMap(formIds);
        // 获得 Category Map
        Map<String, BpmCategoryDO> categoryMap = categoryService.getCategoryMap(
                convertSet(list, Model::getCategory));
        // 获得 Deployment Map
        Map<String, Deployment> deploymentMap = processDefinitionService.getDeploymentMap(
                convertSet(list, Model::getDeploymentId));
        // 获得 ProcessDefinition Map
        List<ProcessDefinition> processDefinitions = processDefinitionService.getProcessDefinitionListByDeploymentIds(
                deploymentMap.keySet());
        Map<String, ProcessDefinition> processDefinitionMap = convertMap(processDefinitions, ProcessDefinition::getDeploymentId);
<<<<<<< HEAD
        return success(BpmModelConvert.INSTANCE.buildModelPage(pageResult, formMap, categoryMap, deploymentMap, processDefinitionMap));
=======
        // 获得 User Map
        Set<Long> userIds = convertSetByFlatMap(list, model -> {
            BpmModelMetaInfoVO metaInfo = BpmModelConvert.INSTANCE.parseMetaInfo(model);
            return metaInfo != null ? metaInfo.getStartUserIds().stream() : Stream.empty();
        });
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);
        return success(BpmModelConvert.INSTANCE.buildModelList(list,
                formMap, categoryMap, deploymentMap, processDefinitionMap, userMap));
>>>>>>> master-jdk17
    }

    @GetMapping("/get")
    @Operation(summary = "获得模型")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('bpm:model:query')")
    public CommonResult<BpmModelRespVO> getModel(@RequestParam("id") String id) {
        Model model = modelService.getModel(id);
        if (model == null) {
            return null;
        }
        byte[] bpmnBytes = modelService.getModelBpmnXML(id);
        BpmSimpleModelNodeVO simpleModel = modelService.getSimpleModel(id);
        return success(BpmModelConvert.INSTANCE.buildModel(model, bpmnBytes, simpleModel));
    }

    @PostMapping("/create")
    @Operation(summary = "新建模型")
    @PreAuthorize("@ss.hasPermission('bpm:model:create')")
    public CommonResult<String> createModel(@Valid @RequestBody BpmModelCreateReqVO createRetVO) {
        return success(modelService.createModel(createRetVO, null));
    }

    @PutMapping("/update")
    @Operation(summary = "修改模型")
    @PreAuthorize("@ss.hasPermission('bpm:model:update')")
    public CommonResult<Boolean> updateModel(@Valid @RequestBody BpmModelUpdateReqVO modelVO) {
        modelService.updateModel(modelVO);
        return success(true);
    }

<<<<<<< HEAD
    @PostMapping("/import")
    @Operation(summary = "导入模型")
    @PreAuthorize("@ss.hasPermission('bpm:model:import')")
    public CommonResult<String> importModel(@Valid BpmModeImportReqVO importReqVO) throws IOException {
        BpmModelCreateReqVO createReqVO = BeanUtils.toBean(importReqVO, BpmModelCreateReqVO.class);
        // 读取文件
        String bpmnXml = IoUtils.readUtf8(importReqVO.getBpmnFile().getInputStream(), false);
        return success(modelService.createModel(createReqVO, bpmnXml));
=======
    @PutMapping("/update-sort-batch")
    @Operation(summary = "批量修改模型排序")
    @Parameter(name = "ids", description = "编号数组", required = true, example = "1,2,3")
    public CommonResult<Boolean> updateModelSortBatch(@RequestParam("ids") List<String> ids) {
        modelService.updateModelSortBatch(getLoginUserId(), ids);
        return success(true);
>>>>>>> master-jdk17
    }

    @PostMapping("/deploy")
    @Operation(summary = "部署模型")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('bpm:model:deploy')")
    public CommonResult<Boolean> deployModel(@RequestParam("id") String id) {
        modelService.deployModel(id);
        return success(true);
    }

    @PutMapping("/update-state")
    @Operation(summary = "修改模型的状态", description = "实际更新的部署的流程定义的状态")
    @PreAuthorize("@ss.hasPermission('bpm:model:update')")
    public CommonResult<Boolean> updateModelState(@Valid @RequestBody BpmModelUpdateStateReqVO reqVO) {
<<<<<<< HEAD
        modelService.updateModelState(reqVO.getId(), reqVO.getState());
=======
        modelService.updateModelState(getLoginUserId(), reqVO.getId(), reqVO.getState());
        return success(true);
    }

    @Deprecated
    @PutMapping("/update-bpmn")
    @Operation(summary = "修改模型的 BPMN")
    @PreAuthorize("@ss.hasPermission('bpm:model:update')")
    public CommonResult<Boolean> updateModelBpmn(@Valid @RequestBody BpmModeUpdateBpmnReqVO reqVO) {
        modelService.updateModelBpmnXml(reqVO.getId(), reqVO.getBpmnXml());
>>>>>>> master-jdk17
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除模型")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('bpm:model:delete')")
    public CommonResult<Boolean> deleteModel(@RequestParam("id") String id) {
        modelService.deleteModel(id);
        return success(true);
    }

<<<<<<< HEAD
=======
    @DeleteMapping("/clean")
    @Operation(summary = "清理模型")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('bpm:model:clean')")
    public CommonResult<Boolean> cleanModel(@RequestParam("id") String id) {
        modelService.cleanModel(getLoginUserId(), id);
        return success(true);
    }

    // ========== 仿钉钉/飞书的精简模型 =========

    @GetMapping("/simple/get")
    @Operation(summary = "获得仿钉钉流程设计模型")
    @Parameter(name = "modelId", description = "流程模型编号", required = true, example = "a2c5eee0-eb6c-11ee-abf4-0c37967c420a")
    public CommonResult<BpmSimpleModelNodeVO> getSimpleModel(@RequestParam("id") String modelId){
        return success(modelService.getSimpleModel(modelId));
    }

    @Deprecated
    @PostMapping("/simple/update")
    @Operation(summary = "保存仿钉钉流程设计模型")
    @PreAuthorize("@ss.hasPermission('bpm:model:update')")
    public CommonResult<Boolean> updateSimpleModel(@Valid @RequestBody BpmSimpleModelUpdateReqVO reqVO) {
        modelService.updateSimpleModel(getLoginUserId(), reqVO);
        return success(Boolean.TRUE);
    }

>>>>>>> master-jdk17
}
