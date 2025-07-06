package dto;

import com.github.javafaker.Faker;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class TestCasesFactory extends BaseTest {

    public static TestCases getTestCases(String section, String template, String type, String priority, String status,
                                         String assignedTo, String automationType, List<Step> steps) {
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

    public static List<Step> generateSteps(int count) {
        Faker faker = new Faker();
        List<Step> steps = new ArrayList<>();
        for(int i = 0; i< count; i++) {
            steps.add(new Step(
                    faker.lorem().sentence(),
                    faker.lorem().sentence()
            ));
        }
        return steps;
    }
}