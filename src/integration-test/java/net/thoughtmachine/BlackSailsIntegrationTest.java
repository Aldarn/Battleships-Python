package net.thoughtmachine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

public class BlackSailsIntegrationTest {
	@Test
	public void testInputProvidesCorrectOutput() throws ParseException, IOException {
		// -------------------------------------------------------
		BlackSails.main("-f input.txt");
		// -------------------------------------------------------
		File file = new File("output.txt");
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();
		
		assertEquals(String.valueOf(data), "(1, 3, N)\n(9, 2, E) SUNK");
	}
	
	@Test
	public void testMediumInputProvidesCorrectOutput() {
		fail();
	}	
	
	@Test
	public void testLargeInputProvidesCorrectOutput() {
		fail();
	}	
	
	@Test
	public void testInputWithEndOfMoveCollisionsThrowsException() {
		fail();
	}
	
	@Test
	public void testMalformedInputThrowsException() {
		fail();
	}
}
