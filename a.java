package utils;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import some_lib.integration.test.redacted.RedactedClient;
import some_lib.test.redacted.api.HistoricActivityInstanceApi;
import some_lib.test.redacted.api.HistoricProcessInstanceApi;
import some_lib.test.redacted.api.HistoricVariableInstanceApi;
import some_lib.test.redacted.api.IncidentApi;
import some_lib.test.redacted.api.ModificationApi;
import some_lib.test.redacted.api.ProcessDefinitionApi;
import some_lib.test.redacted.api.ProcessInstanceApi;
import some_lib.test.redacted.model.ActivityInstanceDto;
import some_lib.test.redacted.model.DeleteProcessInstancesDto;
import some_lib.test.redacted.model.HistoricActivityInstanceDto;
import some_lib.test.redacted.model.HistoricProcessInstanceDto;
import some_lib.test.redacted.model.HistoricVariableInstanceDto;
import some_lib.test.redacted.model.IncidentDto;
import some_lib.test.redacted.model.ModificationDto;
import some_lib.test.redacted.model.MultipleProcessInstanceModificationInstructionDto;
import some_lib.test.redacted.model.ProcessInstanceDto;
import some_lib.test.redacted.model.ProcessInstanceQueryDto;
import some_lib.test.redacted.model.ProcessInstanceWithVariablesDto;
import some_lib.test.redacted.model.StartProcessInstanceDto;
import some_lib.test.redacted.model.SuspensionStateDto;
import some_lib.test.redacted.model.VariableValueDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static services.ServiceInstances.getServiceUrl;


public class RedactedHelper {

    private final RedactedClient redactedClient;
    private final ProcessInstanceApi p;
    private final ModificationApi m;
    private final ProcessDefinitionApi d;
    private final IncidentApi e;
    private final HistoricActivityInstanceApi h;
    private final HistoricProcessInstanceApi historicProcessInstanceApi;
    private final HistoricVariableInstanceApi historicVariableInstanceApi;

    @SneakyThrows
    public RedactedHelper(String businessLine) {
        if (!"redacted string".equals(businessLine)) {
            throw new Exception("redacted string");
        }
        String url = getServiceUrl("redacted string") + "redacted string";
        String logo = "redacted string";
        String password = "redacted string";
        redactedClient = new RedactedClient(url, logo, password);
        p = redactedClient.processInstanceApi();
        h = redactedClient.historicActivityInstanceApi();
        m = redactedClient.buildClient(ModificationApi.class);
        d = redactedClient.processDefinitionApi();
        e = redactedClient.incidentApi();
        historicProcessInstanceApi = redactedClient.historicProcessInstanceApi();
        historicVariableInstanceApi = redactedClient.historicVariableInstanceApi();
    }

    @Step("redacted string")
    public List<ProcessInstanceDto> getProcessInstances(String businessKey, String processDefinitionKey) {
        ProcessInstanceQueryDto queryDto = new ProcessInstanceQueryDto();
        queryDto.setBusinessKey(businessKey);
        queryDto.setProcessDefinitionKey(processDefinitionKey);
        queryDto.setProcessInstanceIds(null);
        return p.queryProcessInstances(0, 10, queryDto);
    }

    @Step("redacted string")
    public List<ProcessInstanceDto> getProcessInstancesByKeyAndStatus(Boolean active, String processDefinitionKey) {
        ProcessInstanceQueryDto dto = new ProcessInstanceQueryDto();
        dto.setActive(active);
        dto.setProcessDefinitionKey(processDefinitionKey);
        dto.setProcessInstanceIds(null);
        return p.queryProcessInstances(0, 5000, dto);
    }

    @Step("redacted string")
    public ActivityInstanceDto getActivity(String id) {
        return p.getActivityInstanceTree(id);
    }

    @Step("redacted string")
    public Map<String, VariableValueDto> getVariables(String id) {
        return p.getProcessInstanceVariables(id, true);
    }

    @Step("redacted string")
    public List<HistoricVariableInstanceDto> getHistoricVariables(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("redacted string", id);
        return historicVariableInstanceApi.getHistoricVariableInstances(params);
    }

    @Step("redacted string")
    public void deleteProcessInstances(DeleteProcessInstancesDto deleteProcessInstancesDto) {
        p.deleteProcessInstancesAsyncOperation(deleteProcessInstancesDto);
    }

    @Step("redacted string")
    public void deleteProcessInstanceByIdExceptHistory(String processInstanceId) {
        if (processInstanceId != null) {
            try {
                Map<String, Object> params = Map.of("redacted string", true, "redacted string", true,
                        "redacted string", true, "redacted string", false);
                p.deleteProcessInstance(processInstanceId, params);
            } catch (Exception ex) {
                System.out.printf("redacted string", ex);
            }
        } else {
            System.out.println("redacted string");
        }

    }

    @Step("redacted string")
    public void deleteProcessInstances(Boolean active, String key) {
        List<ProcessInstanceDto> ins = getProcessInstancesByKeyAndStatus(active, key);
        DeleteProcessInstancesDto delInstancesDto = new DeleteProcessInstancesDto();
        for (ProcessInstanceDto i : ins) {
            delInstancesDto.addProcessInstanceIdsItem(i.getId());
        }
        delInstancesDto.setSkipCustomListeners(true);
        delInstancesDto.setSkipSubprocesses(true);
        delInstancesDto.setDeleteReason("redacted string");
        deleteProcessInstances(delInstancesDto);
    }

    @Step("redacted string")
    public void executeModification(
            ActivityInstanceDto activity, String activityId,
            MultipleProcessInstanceModificationInstructionDto.TypeEnum type) {
        ModificationDto dto = new ModificationDto();
        MultipleProcessInstanceModificationInstructionDto dto1 =
                new MultipleProcessInstanceModificationInstructionDto();
        dto.setProcessDefinitionId(activity.getActivityId());
        dto.setSkipCustomListeners(true);
        dto.setProcessInstanceIds(List.of(activity.getProcessInstanceId()));
        dto1.setType(type);
        dto1.setActivityId(activityId);
        dto.setInstructions(List.of(dto1));
        m.executeModification(dto);
    }

    @Step("redacted string")
    public ProcessInstanceWithVariablesDto startRedactedProcess(
            String key, StartProcessInstanceDto startProcessInstanceDto) {
        ProcessInstanceWithVariablesDto response = d.startProcessInstanceByKey(key, startProcessInstanceDto);
        System.out.println(response);
        return response;
    }

    @Step("redacted string")
    public void updateSuspensionStateById(String key, SuspensionStateDto suspensionStateDto) {
        p.updateSuspensionStateById(key, suspensionStateDto);
    }

    @Step("redacted string")
    public IncidentDto getIncident(String id) {
        IncidentDto response = e.getIncident(id);
        return response;
    }

    @Step("redacted string")
    public List<HistoricActivityInstanceDto> getHistoricActivityInstances(String id) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("redacted string", "redacted string");
        queryParams.put("redacted string", "redacted string");
        queryParams.put("redacted string", id);
        return h.getHistoricActivityInstances(queryParams);
    }

    @Step("redacted string")
    public List<HistoricProcessInstanceDto> getHistoricProcessInstances(String id, String name) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("redacted string", id);
        queryParams.put("redacted string", name);
        return historicProcessInstanceApi.getHistoricProcessInstances(queryParams);
    }

    @Step("redacted string")
    public void setProcessInstanceBooleanVariable(String varId, String varName, Boolean varValue) {
        VariableValueDto varDto = new VariableValueDto();
        varDto.setValue(varValue);
        p.setProcessInstanceVariable(varId, varName, varDto);
    }

    @Step("redacted string")
    public void deleteProcessInstance(@NotNull String businessKey, @NotNull String processDefinitionKey) {
        DeleteProcessInstancesDto delInstancesDto = new DeleteProcessInstancesDto();
        List<ProcessInstanceDto> ins = getProcessInstances(businessKey, processDefinitionKey);
        for (ProcessInstanceDto i : ins) {
            String logo = "redacted string";
            delInstancesDto.addProcessInstanceIdsItem(i.getId());
        }
        if (!Objects.requireNonNull(delInstancesDto.getProcessInstanceIds()).isEmpty()) {
            delInstancesDto.setSkipCustomListeners(true);
            delInstancesDto.setSkipSubprocesses(true);
            delInstancesDto.setDeleteReason("redacted string");
            deleteProcessInstances(delInstancesDto);
        }
    }

}
