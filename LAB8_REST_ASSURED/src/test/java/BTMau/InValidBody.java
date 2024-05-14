package BTMau;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import data.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class InValidBody {
private Response response;
private ResponseBody resBody;
private JsonPath bodyJson;
private User user;
@BeforeClass
public void init() {
	//Khởi tạo user
	user = new User(null,"nam", 24, "Ky su");
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	RequestSpecification request = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.body(user);
	response = request.post();
	resBody = response.getBody();
	bodyJson = resBody.jsonPath();
}
@Test
public void TC01_CheckStatus() {
	assertEquals(400,response.getStatusCode(),"Check status failed.");
}
@Test
public void TC02_CheckMessage() {
	assertEquals(true,resBody.asString().contains("message"),"Response dont have message.");
}
@Test
public void T03_verifyOnMessageContainName() {
String resName = bodyJson.get("message");
if (null == resName) resName = "";
assertEquals(true, resName.contains("name"),"Message not contain invalid field!");
}
@AfterClass
public void afterTest() {
//Reset Values
RestAssured.baseURI = null; //Setup Base URI
RestAssured.basePath = null;
}
}
