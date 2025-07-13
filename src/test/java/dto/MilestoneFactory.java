package dto;

import com.github.javafaker.Faker;
import tests.ui.BaseTest;
import java.util.concurrent.TimeUnit;

public class MilestoneFactory extends BaseTest {

    public static Milestone getMilestone() {
        Faker faker = new Faker();
        return new Milestone(faker.animal().name(),
                faker.color().name(),
                faker.witcher().monster(),
                faker.date().past(12, TimeUnit.DAYS),
                faker.date().future(12, TimeUnit.DAYS)
        );
    }
}