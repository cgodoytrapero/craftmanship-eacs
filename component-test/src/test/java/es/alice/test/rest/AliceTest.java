package es.alice.test.rest;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import es.alice.rest.Name;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

public class AliceTest {

	static String alice;

	@BeforeClass
	public static void setup() {
		// alice = "https://192.168.122.88:31494";
		alice = "https://192.168.122.88:31494";
	}

	@AfterClass
	public static void teardown() {
		//RestApp.stop();
	}

	@Test
	public void healthCheckTest() {
		String connection = alice + "/health";

		given().
			relaxedHTTPSValidation().
		when().
			get(connection).
		then().
			statusCode(200);
	}

	@Test
	public void testAliceIncorrectUrl() {
		String connection = alice + "/wrong_url";

		given().
			relaxedHTTPSValidation().
		when().
			get(connection).
		then().
			statusCode(404);
	}

	@Test
	public void testAliceSingleRequestBob() {

		// This would not be needed if we were returning content type header
		RestAssured.defaultParser = Parser.JSON;

		String connection = alice + "/greeter";

		given().
			contentType("application/json").
			body(new Name("Dani")).
			relaxedHTTPSValidation().
		when().
			put(connection).
		then().
			statusCode(200).and().
			contentType(isEmptyOrNullString()). // we should probably be returning a content type header...
			// contentType(equalTo("application/json")).
			body("status", equalTo("ok")).and().
			body("message", equalTo("Hello Dani"));
	}


}
