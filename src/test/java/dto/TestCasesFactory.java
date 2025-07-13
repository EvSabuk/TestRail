package dto;

import com.github.javafaker.Faker;
import tests.ui.BaseTest;
import java.util.ArrayList;
import java.util.List;

public class TestCasesFactory extends BaseTest {

    public static TestCases getTestCases(String section, String template, String type, String priority, String status,
                                         String assignedTo, String automationType, List<TestCaseStep> steps) {
        Faker faker = new Faker();
        return new TestCases(faker.lorem().sentence(),
                section,
                template,
                type,
                priority,
                status,
                assignedTo,
                String.valueOf(faker.number().randomDigit()),
                automationType,
                faker.lorem().sentence(),
                steps
        );
    }

    public static List<TestCaseStep> generateSteps(int count) {
        Faker faker = new Faker();
        List<TestCaseStep> steps = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            steps.add(new TestCaseStep(
                    faker.lorem().sentence(),
                    faker.lorem().sentence()
            ));
        }
        return steps;
    }
}