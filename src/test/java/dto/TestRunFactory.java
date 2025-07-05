package dto;

import com.github.javafaker.Faker;
import tests.BaseTest;

public class TestRunFactory extends BaseTest {

    public static TestRun getTestRun() {
        Faker faker = new Faker();
        return new TestRun(faker.animal().name(),
                faker.color().name()
        );
    }
}
