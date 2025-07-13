package api.adapters;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import static org.testng.Assert.assertEquals;

@Getter
@Log4j2
public class ResponseWrapper {
    private final Response response;

    public ResponseWrapper(Response response) {
        this.response = response;
        logAndAttachResponse();
    }

    private void logAndAttachResponse() {
        String body = response.asPrettyString();
        int status = response.getStatusCode();
        log.info("Response Status: {}", status);
        log.info("Response Body:\n{}", body);
        Allure.addAttachment("Response Status", String.valueOf(status));
        Allure.addAttachment("Response Body", "application/json", body, ".json");
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    @Step("Check that status code: '{expectedStatusCode}'")
    public ResponseWrapper haveStatusCode(int expectedStatusCode) {
        assertEquals(getStatusCode(), expectedStatusCode, "Expected status " + expectedStatusCode +
                " isn't match with actual status: " + getStatusCode());
        return this;
    }

    @Step("Check field in the JSON: '{jsonPath}' = '{expectedValue}' ")
    public ResponseWrapper haveJsonField(String jsonPath, Object expectedValue) {
        Object actualValue = response.jsonPath().get(jsonPath);
        assertEquals(actualValue, expectedValue,
                "Value in the jsonPath '" + jsonPath + "' is not match. Expected: '"
                        + expectedValue + "', but is shown: '" + actualValue + "'");
        return this;
    }
}