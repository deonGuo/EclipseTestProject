package files;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js = new JsonPath(payload.CourseContent()); 
		
		//1. Print No of courses returned by API
		int numCourses = js.getInt("courses.size()");
		System.out.println(numCourses);

		//2.Print Purchase Amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);

		//3. Print Title of the first course
		System.out.println(js.getString("courses[0].title"));
		
		//4. Print All course titles and their respective Prices
		for(int i=0; i<numCourses; i++) {
			System.out.println(js.getString("courses[" + i + "].title") + " - " + js.getString("courses[" + i + "].price"));
		}
		
		//5. Print no of copies sold by RPA Course
		for(int i=0; i<numCourses; i++) {
			if(js.getString("courses[" + i + "].title").equals("Cypress")) {
				System.out.println(js.getInt("courses[" + i + "].copies"));
				break;
			}
		}

		//6. Verify if Sum of all Course prices matches with Purchase Amount
		int actualTotal =0;
		for(int i=0;i<numCourses;i++) {
			actualTotal += js.getInt("courses[" + i + "].price") * js.getInt("courses[" + i + "].copies") ;
		}
		
		Assert.assertEquals(actualTotal, totalAmount);
		
		//System.out.println(js.)
	}

}
