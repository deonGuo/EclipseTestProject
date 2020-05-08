package files;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.path.json.JsonPath;

public class JiraRESTRequests {

	public int AddComment() {
		// add comment
		String response = given().log().all().pathParam("key", "JA-5")
				.header("Authorization", "Basic ZGVvbmxvbDIwMTFAZ21haWwuY29tOjNUdWVmM2NnTUdUb0ljeHFSWXlCRDA2NA==")
				.header("Content-Type", "application/json")
				.body("{\r\n" + "    \"body\": \"The new comment added via RestAssured\"\r\n" + "}").when()
				.post("/rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201).extract()
				.response().asString();

	 return ReusableMethods.RawToJson(response).getInt("id");
		// System.out.println("------- The extracted response: " + response);
	}

	public void AddAttachment() {
		// add attachment
		given().log().all().pathParam("issueKey", "JA-5").header("X-Atlassian-Token", "no-check")
				.header("Authorization", "Basic ZGVvbmxvbDIwMTFAZ21haWwuY29tOjNUdWVmM2NnTUdUb0ljeHFSWXlCRDA2NA==")
				.header("Content-Type", "multipart/form-data")
				.multiPart("file", new File("Attachment for issue JA-5.txt")).when()
				.post("/rest/api/2/issue/{issueKey}/attachments").then().log().all().assertThat().statusCode(200);
	}

	public String GetIssueComments() {
		//queryParam limits the response for comment fields only!
		return given().log().all().pathParam("issueKey", "JA-5")
				.header("Authorization", "Basic ZGVvbmxvbDIwMTFAZ21haWwuY29tOjNUdWVmM2NnTUdUb0ljeHFSWXlCRDA2NA==")
				.queryParam("fields", "comment").when().get("/rest/api/2/issue/{issueKey}").then().log().all()
				.assertThat().statusCode(200).extract().response().asString();
	}

	public String AddCommentAndCheckId() {
		int idCreated = AddComment();
		JsonPath js = ReusableMethods.RawToJson(GetIssueComments());
		for(int i=0; i<js.getInt("fields.comment.comments.size()"); i++) {
			if(js.getInt("fields.comment.comments[" + i + "].id") == idCreated) {
				return js.get("fields.comment.comments[" + i + "].body");
			}
		}
		return "Unable to find the comment"; 
	  }
	 
}
