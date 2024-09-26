package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.*;
import Utils.Constants;

import static io.restassured.RestAssured.*;

public class RecordingTests {

	private static final String BASE_URL = Constants.BASE_URL;
	private static final String AUTH_TOKEN = Constants.AUTH_TOKEN;
	private static final String ROOM_ID = Constants.ROOM_ID;
	private String jsonBody = Constants.JSON_BODY;

	@BeforeClass
	public void setup() {
		RestAssured.baseURI = BASE_URL;
	}

	// Start recording method
	public Response startRecording() {
		// POST request to start recording
		return given()
				.header("Authorization", AUTH_TOKEN)
				.header("Content-Type", "application/json")
				.body(jsonBody).
				when()	
				.post(ROOM_ID + "/start");
	}

	// Stop recording method
	public Response stopRecording() {
		return given()
				.header("Authorization", AUTH_TOKEN)
				.header("Content-Type", "application/json").
				when()	
				.post(ROOM_ID + "/stop");
	}

	@Test (priority = 1 , description = "Test with an invalid room ID")
	public void invalidRoomIDTest() {
		// Test with an invalid room ID
		String invalidRoomID = "00000000000";
		Response response = given()
				.header("Authorization", AUTH_TOKEN)
				.header("Content-Type", "application/json")
				.body(jsonBody)
				.post( invalidRoomID + "/start");

		// Check if the API returns a 403 Forbidden
		Assert.assertEquals(response.statusCode(), 403);
		JsonPath stopJson = response.jsonPath();
		Assert.assertEquals(stopJson.get("message"), "user does not have required permission");
	}

	@Test (priority = 2 , description = "Test with an invalid auth token")
	public void invalidAuthTokenTest() {
		// Test with an invalid auth token
		String invalidToken = "Bearer AAAAAAAAAAA";
		Response response = given()
				.header("Authorization", invalidToken)
				.header("Content-Type", "application/json")
				.body(jsonBody)
				.post(ROOM_ID + "/start");

		// Check if the API returns a 401 Unauthorized
		Assert.assertEquals(response.statusCode(), 401);
	}

	@Test (priority = 3 , description = "Start recording successfully")
	public void startRecordingSuccessfully() {
		Response response = startRecording();
		JsonPath json = response.jsonPath();

		// Verifying that the first attempt to start the recording is successful (200 status)
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(json.get("status"), "starting");
	}

	@Test (priority = 4 , description = "Attempt to start recording multiple times")
	public void startRecordingMultipleTimes() {

		// Second call should fail with a 409 Conflict, indicating the recording is already active
		Response secondResponse = startRecording();
		JsonPath secondJson = secondResponse.jsonPath();
		Assert.assertEquals(secondResponse.statusCode(), 409);
		Assert.assertEquals(secondJson.get("message"), "beam already started");
	}

	@Test (priority = 5 , description = "Stop recording successfully")
	public void stopRecordingSuccessfully() {
		// Ensure the recording is started
		startRecording();

		// Call to stop the recording should succeed
		Response stopResponse = stopRecording();
		JsonPath stopJson = stopResponse.jsonPath();
		Assert.assertEquals(stopResponse.statusCode(), 200);
		Assert.assertEquals(stopJson.get("data[0].status"), "stopping");
	}

	@Test (priority = 6 , description = "Attempt to stop recording without starting")
	public void stopRecordingWithoutStarting() {
		// Attempt to stop the recording without starting should fail
		Response stopResponse = stopRecording();
		JsonPath stopJson = stopResponse.jsonPath();
		Assert.assertEquals(stopResponse.statusCode(), 404); 
		Assert.assertEquals(stopJson.get("message"), "beam not found");
	}

	@Test (priority = 7 , description = "Verify empty payload when starting recording")
	public void whenPayloadIsEmpty() {
		Response response = given()
				.header("Authorization",AUTH_TOKEN )
				.header("Content-Type", "application/json")
				.body("")
				.post(ROOM_ID + "/start");

		JsonPath EmptyJson = response.jsonPath();
		Assert.assertEquals(response.statusCode(), 400); 
		Assert.assertEquals(EmptyJson.get("message"), "request body must not be empty");
	}

	@Test (priority = 8 , description = "Verify wrong payload input when starting recording")
	public void whenPayloadInputIsWrong() {
		String payLoad =  "{ \"meeting_url\": \"\" }"; 
		Response response = given()
				.header("Authorization",AUTH_TOKEN )
				.header("Content-Type", "application/json")
				.body(payLoad)
				.post(ROOM_ID + "/start");

		JsonPath EmptyJson = response.jsonPath();
		Assert.assertEquals(response.statusCode(), 400); 
		Assert.assertEquals(EmptyJson.get("message"), "invalid input");
	}

	@Test (priority = 9 , description = "Verify payload with invalid json when starting recording")
	public void whenPayloadAsInvalidJson() {
		String payLoad =  "{ \"meeting_url\": \"https://satyam-livestream-1836.app.100ms.live\" ,}"; 
		Response response = given()
				.header("Authorization",AUTH_TOKEN )
				.header("Content-Type", "application/json")
				.body(payLoad)
				.post(ROOM_ID + "/start");

		JsonPath EmptyJson = response.jsonPath();
		Assert.assertEquals(response.statusCode(), 400); 
		Assert.assertEquals(EmptyJson.get("message"), "request body contains badly-formed JSON (at position 67)");
	}
}

