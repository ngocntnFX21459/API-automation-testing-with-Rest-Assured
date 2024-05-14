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

public class ValidBody {
private User user;
private Response response;
private ResponseBody resBody;
private JsonPath bodyJson;
@BeforeClass
private void init() {
	// Khởi tạo user 
	user = new User("Minh", "nam", 24, "Ky su");
	//Khởi tạo EndPoint
	//https://reqres.in/api/users
	RestAssured.baseURI="https://reqres.in";
	RestAssured.basePath="/api/users";
	//Khởi tạo API
	RequestSpecification request = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.body(user);
    // gọi Post
	response = request.post();
	resBody  = response.getBody();
	bodyJson = resBody.jsonPath();
	
}
@Test
public void TC01_CheckStatus() {
	assertEquals(201,response.getStatusCode(),"Check status failed.");
}
@Test
public void TC02_CheckID() {
	assertEquals(true,resBody.asString().contains("id"),"check id failed.");
}
@Test
public void TC03_CheckCreatedAt() {
	assertEquals(true,resBody.asString().contains("createdAt"),"check createdAt failed.");
}
@Test
public void TC04_CheckName() {
	assertEquals(user.getName(),bodyJson.getString("name"),"Name not match.");

}
@Test
public void TC05_CheckGender() {
	assertEquals(user.getGender(),bodyJson.getString("gender"),"Gender not match.");

}
@Test
public void TC06_CheckAge() {
	assertEquals(user.getAge(),bodyJson.getInt("age"),"Age not match");

}
@Test
public void TC07_CheckJob() {
	assertEquals(user.getJob(),bodyJson.getString("job"),"Job not match.");
}
@AfterClass
public void afterTest() {
//Reset Values
RestAssured.baseURI = null; //Setup Base URI
RestAssured.basePath = null;
}
}
