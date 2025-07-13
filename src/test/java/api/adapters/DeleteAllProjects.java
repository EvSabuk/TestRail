package api.adapters;

import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;

@Log4j2
public class DeleteAllProjects extends BaseApi{

    final String BASE_URI = "index.php?/api/v2/",
            DELETE_URI = "delete_project/",
            GET_PROJECT_URI = "get_projects",
            PROJECTS_URI = "projects";


    public void deleteAllProjects() {
        log.info("Deleting all Projects");
        Response response = request(Method.GET, BASE_URI + GET_PROJECT_URI).getResponse();
        List<Map<String, Object>> projects = response.jsonPath().getList(PROJECTS_URI);
        String keyId = "id";
        for (Map<String, Object> project : projects) {
            if (project != null) {
                project.get(keyId).toString();
                request(Method.POST, BASE_URI + DELETE_URI + keyId);
            } else {
                System.out.println("Project " + project + " has null id, skipping deletion.");
            }
        }
    }
}
