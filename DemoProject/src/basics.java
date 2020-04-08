import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReusableMethods;
import files.payload;

public class basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//validate if add place API is working as expected
		//add place --> update pace with new address --> get place to validate if new address is present
		
		//given - all input details
		//when - submit to the API - resource and http method
		//then - validate the response
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123")
				//.header("Content-Type", "application/json")
		.body(payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)")
		.extract().response().asString();
		 
		System.out.println(response);
		JsonPath js = new JsonPath(response);	//for parsing Json
		String placeId = js.getString("place_id");
		
		System.out.println(placeId);
		
		//update address
		String newAddress = "70 Summer walk, Canada";
		System.out.println(payload.UpdatePlace(placeId, newAddress));
		String updateResponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(payload.UpdatePlace(placeId, newAddress))
		.when().put("maps/api/place/update/json")
		.then().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated"))
		//.body("Scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)")
		.extract().response().asString();
		
		System.out.println(updateResponse);
	
		//get the address
		String getResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.when().get("/maps/api/place/get/json")
				.then().assertThat().statusCode(200)
				.body("address", equalTo(newAddress))
				.extract().response().asString();
		
		System.out.println(getResponse);
		
		JsonPath jsGet = ReusableMethods.RawToJson(getResponse);
		String responseAddress = jsGet.getString("address");
		System.out.println(responseAddress);
		
		//this is using Testng framework - requires Testng jar added to the project
		Assert.assertEquals(responseAddress, newAddress);
		
	}

}
