package net.thoughtmachine;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

public class BlackSailsIntegrationTest {
	@Test
	public void testInputProvidesCorrectOutput() throws ParseException, IOException {
		assertEquals(runWithInput("/input.txt"), "(1, 3, N)\n(9, 2, E) SUNK");
	}
	
	@Test
	public void testMediumInputProvidesCorrectOutput() throws ParseException, IOException {
		assertEquals(runWithInput("/mediumInput.txt"), "(5, 4, W)\n(4, 4, S) SUNK\n(9, 5, E)\n(3, 1, E) SUNK");
	}
	
	@Test(expected = RuntimeException.class)
	public void testInputWithEndOfOperationCollisionsThrowsException() throws ParseException, IOException {
		// TODO: This should be less brittle and test a more specific exception is thrown
		runWithInput("/endOfOperationCollision.txt");
	}
	
	@Test(expected = RuntimeException.class)
	public void testMalformedInputThrowsException() throws ParseException, IOException {
		// TODO: This should be less brittle and test a more specific exception is thrown
		runWithInput("/malformedInput.txt");
	}
	
	/**
	 * Runs the main application on the given file and returns the contents of the output file.
	 * 
	 * TODO: This is a hack, should stub the output service to return a string when testing 
	 * instead of creating a file.
	 */
	private String runWithInput(String inputPath) throws ParseException, IOException {
		// -------------------------------------------------------
		BlackSails.main(inputPath);
		// -------------------------------------------------------
		File file = new File("output.txt");
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();
		return new String(data, "UTF-8");
	}
}
