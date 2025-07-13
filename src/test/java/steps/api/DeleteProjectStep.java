package steps.api;

import api.adapters.BaseApi;
import io.qameta.allure.Step;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import java.util.List;
import java.util.Map;

@Log4j2
public class DeleteProjectStep extends BaseApi {

    final String BASE_URI = "index.php?/api/v2/",
            DELETE_URI = "delete_project/",
            GET_PROJECT_URI = "get_projects",
            PROJECTS_URI = "projects";

    @Step("Deleting a Project via API.")
    public Response deleteProjectAPI(String projectName) {
        String id = getProjectID(projectName);
        return request(Method.POST, BASE_URI + DELETE_URI + id).getResponse();
    }

    private String getProjectID(String projectName) {
        Response response = request(Method.GET, BASE_URI + GET_PROJECT_URI).getResponse();
        log.info("Deleting a Project with the title '{}' and open it.", projectName);
        String keyName = "name";
        String keyId = "id";
        List<Map<String, Object>> projects = response.jsonPath().getList(PROJECTS_URI);
        for (Map<String, Object> project : projects) {
            if (project.get(keyName).toString().contains(projectName)) {
                return project.get(keyId).toString();
            }
        }
        return null;
    }
}