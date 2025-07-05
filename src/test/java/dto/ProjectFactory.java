package dto;


import com.github.javafaker.Faker;
import tests.BaseTest;

public class ProjectFactory extends BaseTest {

    public static Project getProject() {
        Faker faker = new Faker();
        String protocol = faker.bool().bool() ? "https" : "http";
        String domain = faker.internet().domainName();
        String randomUrl = protocol + "://" + domain + "/%id%";

        return new Project(faker.company().name(), randomUrl, randomUrl,
                randomUrl, randomUrl, faker.witcher().monster());
    }
}