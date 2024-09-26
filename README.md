# 100ms SDET Assignment

# Project Structure
SDETAssignment
├── src
│   └── test
│       └── java
│           ├── tests
│           │   └── RecordingTests.java  <!-- Holds the test methods used for testing various API functionalities -->
│           └── Utils
│               └── Constants.java  <!-- Contains static final values (constants) used across the test suite -->
├── JRE System Library [JavaSE-1.8]
├── Maven Dependencies
├── src
├── target
├── test-output
└── pom.xml



## Constants

The following constants are used and managed in `Constants.java`:
- **BASE_URL**: The base URL for the API.
- **AUTH_TOKEN**: The authentication token required for API requests.
- **ROOM_ID**: The unique room identifier for starting/stopping recordings.
- **JSON_RESPONSE**: The JSON body used for starting a recording.

## Test Cases

The suite contains a variety of tests to validate the recording functionality:

1. **Invalid Room ID Test**: Verifies that starting a recording with an invalid room ID returns a `403 Forbidden` error.
2. **Invalid Authorization Token Test**: Tests the API response when an invalid authentication token is provided (`401 Unauthorized`).
3. **Start Recording Successfully**: Verifies that the recording starts successfully with valid inputs (`200 OK`).
4. **Multiple Start Recording Attempts**: Checks that attempting to start the recording multiple times returns a `409 Conflict` error.
5. **Stop Recording Successfully**: Verifies that stopping the recording after it has started returns a successful response (`200 OK`).
6. **Stop Recording Without Starting**: Ensures that attempting to stop a recording without starting one returns a `404 Not Found` error.
7. **Empty Payload Test**: Ensures that sending an empty payload when starting a recording returns a `400 Bad Request` error.
8. **Incorrect Payload Input Test**: Verifies that sending an invalid payload when starting a recording returns a `400 Bad Request` error.
9. **Invalid JSON Payload Test**: Tests that a request with badly-formed JSON returns a `400 Bad Request` error.

## Prerequisites

Before running the tests, ensure that you have the following setup:
- JDK 1.8 or higher installed.
- Maven installed.
- A valid `BASE_URL`, `AUTH_TOKEN`, and `ROOM_ID` configured in the `Constants.java` file.

## How to Run the Tests

1. Clone this repository.
   ```bash
   git clone <repo-url>
   cd <repo-directory>
