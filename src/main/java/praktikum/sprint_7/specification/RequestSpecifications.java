package praktikum.sprint_7.specification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public class RequestSpecifications {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    protected io.restassured.specification.RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }
}
