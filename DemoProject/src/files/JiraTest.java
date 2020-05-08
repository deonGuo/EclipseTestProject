package files;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

//these 3 should be statically imported
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;


public class JiraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://goblinworkshop.atlassian.net";
		
		//add comment
		String response = given().log().all().pathParam("key", "JA-5").header("Authorization", "Basic ZGVvbmxvbDIwMTFAZ21haWwuY29tOjNUdWVmM2NnTUdUb0ljeHFSWXlCRDA2NA==").header("Content-Type","application/json").body("{\r\n" + 
				"    \"body\": \"The new comment added via RestAssured\"\r\n" + 
				"}")
		.when().post("/rest/api/2/issue/{key}/comment")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		//System.out.println("------- The extracted response: " + response);
		
		//add attachment
		given().log().all().pathParam("issueKey", "JA-5").header("X-Atlassian-Token", "no-check").header("Authorization", "Basic ZGVvbmxvbDIwMTFAZ21haWwuY29tOjNUdWVmM2NnTUdUb0ljeHFSWXlCRDA2NA==").header("Content-Type","multipart/form-data")
		.multiPart("file", new File("Attachment for issue JA-5.txt"))
		.when().post("/rest/api/2/issue/{issueKey}/attachments")
		.then().log().all().assertThat().statusCode(200);
	}

}
