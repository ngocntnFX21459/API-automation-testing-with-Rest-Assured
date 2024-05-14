package ThucHanh;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class GET_Invalid {
private Response response;
private ResponseBody resBody;
private JsonPath bodyJson;
int userId;
@BeforeClass
public void init() {
	userId=40;
	//thiết lập EndPoint
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users/";
	RequestSpecification request = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.pathParam("userId",userId);
	//Gọi get và lấy response
	response = request.get("{userId}");
	resBody = response.getBody();
	bodyJson = resBody.jsonPath();
	System.out.println(resBody.asPrettyString());
}
@Test
public void TC01_checkStatus() {
	assertEquals(404, response.getStatusCode(),"Status check fail.");
}
@Test
public void TC02_checkMessage() {
	assertEquals(true,resBody.asString().contains("message"),"Result havenot message");
	
}
@Test
public void TC03_MessageRes() {
	String resultMes = bodyJson.getString("message");
	System.out.println(resultMes);
	assertEquals(resultMes,"User not found!","message not match.");
	
}
}
