package rest.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class DisciplinaTest {
    @Test
    public void shouldReturnStatusCode200() {
        get("/disciplina").then().statusCode(200);
    }

    @Test
    public void shoudReturnAList() {
        get("/disciplina").then().body("codigo", hasItem(1));
    }
}
