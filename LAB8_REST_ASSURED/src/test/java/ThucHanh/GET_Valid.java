package ThucHanh;

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

public class GET_Valid {
private Response response;//lưu response trả về (stt,body,..)
private ResponseBody resBody;// lưu body response trả về
private JsonPath bodyJson; // chuyển body response sang dạng json
int userID;
@BeforeClass
public void init() {
	userID=2;
	// thiết lập EndPoint
	//Endpoint: https://reqres.in/api/users/{userId}
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users/";
	RequestSpecification request = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.pathParam("userId", userID);
//gọi GET và lấy về response
	response = request.get("{userId}");
	resBody = response.getBody();
	bodyJson = resBody.jsonPath();
	System.out.println(resBody.asPrettyString());
}
@Test
public void TC01_checkStatus() {
	assertEquals(200,response.getStatusCode(),"Check status failed.");
	
}

@Test
public void TC02_checkFirstName() {
	assertEquals(true,resBody.asString().contains("first_name"),"check firstname failed.");
}
@Test
public void TC03_checkLastName() {
	assertEquals(true,resBody.asString().contains("last_name"),"check lastname failed");
}
@Test
public void TC04_checkAvatar() {
	assertEquals(true,resBody.asString().contains("avatar"),"check avatar failed.");
}
@Test
public void TC05_checkUserIdMatch() {
	int resultID=bodyJson.getInt("data.id");
	System.out.println(resultID);
	assertEquals(userID, resultID,"userID not match.");
	
}
@Test
public void TC06_checkEmail() {
	assertEquals(true,resBody.asString().contains("email"),"check Email failed.");
}
@AfterClass
public void closeAPI() {
	//Reset EndPoint
	RestAssured.baseURI=null;
	RestAssured.basePath=null;
}
}
