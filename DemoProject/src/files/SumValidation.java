package files;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void SumOfCourses(){
	
		JsonPath js = new JsonPath(payload.CourseContent()); 
		
		//1. Print No of courses returned by API
		int numCourses = js.getInt("courses.size()");
		//2.Print Purchase Amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		int actualTotal =0;
		for(int i=0;i<numCourses;i++) {
			actualTotal += js.getInt("courses[" + i + "].price") * js.getInt("courses[" + i + "].copies") ;
			//System.out.println(actualTotal);
		}
		
		Assert.assertEquals(actualTotal, totalAmount);
	}
}
