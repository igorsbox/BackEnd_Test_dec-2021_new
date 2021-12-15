package com.geekbrains.backend.test.imgur;

import com.geekbrains.backend.test.FunctionalTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ImgurApiFunctionalTest extends FunctionalTest {

    private static Properties properties;
    private static String TOKEN;

    @BeforeAll
    static void beforeAll() throws Exception {
        properties = readProperties();
        RestAssured.baseURI = properties.getProperty("imgur-api-url");
        TOKEN = properties.getProperty("imgur-api-token");
    }

    @Test
    void getAccountBase() {
        String userName = "igorsbox74";
        given()
                .auth()
                .oauth2(TOKEN)
                .log()
                .all()
                .expect()
                .body("data.id", is(157917213))
                .log()
                .all()
                .when()
                .get("account/" + userName);
    }
    @Test
    void postImageTest() {
        given()
                .auth()
                .oauth2(TOKEN)
                .multiPart("image", getFileResource("4682533.jpg"))
                .formParam("name", "Picture")
                .formParam("title", "The best actress from Ukrain")
                .log()
                .all()
                .expect()
                .body("data.size", is(382047))
                .body("data.name", is("Picture"))
                .body("data.type", is("image/jpeg"))
                .body("data.title", is("The best actress from Ukrain"))
                .log()
                .all()
                .when()
                .post("image");
    }

}
