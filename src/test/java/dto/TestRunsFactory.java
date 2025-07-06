package dto;

import com.github.javafaker.Faker;
import tests.BaseTest;

public class TestRunsFactory extends BaseTest {

    public static TestRuns getTestRun() {
        Faker faker = new Faker();
        return new TestRuns(faker.animal().name(),
                faker.color().name()
        );
    }
}
