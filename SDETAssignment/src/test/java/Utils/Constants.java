package Utils;

public class Constants {
	public static final String BASE_URL = "https://api.100ms.live/v2/recordings/room";
	public static final String AUTH_TOKEN = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MjcwMTA2MTgsImV4cCI6MTcyNzYxNTQxOCwianRpIjoiOWVhOTZmNDktMTI4OC00MDczLTliY2MtODNkZDgyZmY0ZmFjIiwidHlwZSI6Im1hbmFnZW1lbnQiLCJ2ZXJzaW9uIjoyLCJuYmYiOjE3MjcwMTA2MTgsImFjY2Vzc19rZXkiOiI2NmYwMTU4ODMzY2U3NGFiOWJlOTNmNzMifQ.jd9Gcc5z_H2pcbF4oE2_fJkowB7MUMnck7BhAxoHaqc";
	public static final String ROOM_ID = "66f0168442698df205882a46";

	//Start Recording API PayLoad
	public static final String JSON_BODY = "{"
			+ "\"meeting_url\": \"https://satyam-livestream-1836.app.100ms.live/streaming/meeting/oeh-ntyr-pkz\","
			+ "\"resolution\": {\"width\": 1280,\"height\": 720},"
			+ "\"transcription\": {\"enabled\": true,\"output_modes\": [\"txt\", \"srt\", \"json\"],"
			+ "\"custom_vocabulary\": [\"100ms\", \"WebSDK\", \"Flutter\", \"Sundar\", \"Pichai\", \"DALL-E\"],"
			+ "\"summary\": {\"enabled\": true,\"context\": \"this is a general call\","
			+ "\"sections\": [{\"title\": \"Agenda\",\"format\": \"bullets\"},"
			+ "{\"title\": \"Key Points\",\"format\": \"bullets\"},"
			+ "{\"title\": \"Action Items\",\"format\": \"bullets\"},"
			+ "{\"title\": \"Short Summary\",\"format\": \"paragraph\"}],"
			+ "\"temperature\": 0.5}}}";
}
