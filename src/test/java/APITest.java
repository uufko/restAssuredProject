import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class APITest {
String URL = "https://jsonplaceholder.typicode.com/posts";

    @Test(priority = 1)
    public void statusCodeTest()  {
        Response response = given().get(URL);
        //response.prettyPrint();
        RestAssured.given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200);
    }

    @Test(priority = 2)
    public void JSONStructureValidationTest()  {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .body("[0]", hasKey("userId"))
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("title"))
                .body("[0]", hasKey("body"));
    }
    @Test(priority = 3)
    public void ValidationOfaSpecificValueTest()  {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
    }
    @Test(priority = 4)
    public void ListLengthValidationTest()  {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200)
                .body("$", hasSize(greaterThanOrEqualTo(10)));
    }
    @Test(priority = 5)
    public void DynamicDataValidationTest()  {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200)
                .body("userId", everyItem(greaterThan(0)));
    }

}
