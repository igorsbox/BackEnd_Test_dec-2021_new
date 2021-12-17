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
    @Test
    void deleteImageTest() {
        given()
                .auth()
                .oauth2(TOKEN)
                .log()
                .all()
                .expect()
                .body("data", is(true))
                .body("success", is(true))
                .body("status", is(200))
                .log()
                .all()
                .when()
                .delete("image/" + "BOl0cmX");
    }
    @Test
    void testCreateComment() {
        given()
                .auth()
                .oauth2(TOKEN)
                .formParam("image_id", "b34pw0r")
                .formParam("comment", "Yes, It's me!!! ;)")
                .log()
                .all()
                .expect()
                .body("success", is(true))
                .body("status", is(200))
                .log()
                .all()
                .when()
                .post("comment");
    }
    @Test
    void getImageTest() {
        given()
                .auth()
                .oauth2(TOKEN)
                .log()
                .all()
                .expect()
                .body("data.name", is("Picture"))
                .body("data.favorite", is(true))
                .body("data.type", is("image/jpeg"))
                .body("data.title", is("The best actress from Ukrain"))
                .body("success", is(true))
                .body("status", is(200))
                .log()
                .all()
                .when()
                .get("image/" + "b34pw0r");
    }

}
