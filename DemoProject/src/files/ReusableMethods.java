package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {

	public static JsonPath RawToJson(String response) {
		JsonPath js = new JsonPath(response);
		return js;
	}
}
