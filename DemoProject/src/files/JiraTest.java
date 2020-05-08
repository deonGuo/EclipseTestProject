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
		
		JiraRESTRequests jr = new JiraRESTRequests();
		//System.out.println(jr.AddComment());
		//jr.AddAttachment();
		//jr.GetIssue();
		System.out.println(jr.AddCommentAndCheckId());
		System.out.println(ReusableMethods.RawToJson(jr.GetIssueComments()).getInt("fields.comment.comments.size()") );
	}
	


}
