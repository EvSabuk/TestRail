package dto;

import com.github.javafaker.Faker;
import tests.BaseTest;

public class TestCasesFactory extends BaseTest {

    public static TestCases getTestCases(String section, String template, String type, String priority, String status,
                                         String assignedTo, String automationType) {
        Faker faker = new Faker();
        return new TestCases(faker.lorem().sentence(),
                section,
                template,
                type,
                priority,
                status,
                assignedTo,
                faker.dog().age(),
                faker.animal().name(),
                faker.color().name(),
                faker.lorem().sentence(),
                faker.lorem().sentence(),
                faker.lorem().sentence(),
                faker.lorem().sentence(),
                faker.lorem().sentence()
        );
    }
}
