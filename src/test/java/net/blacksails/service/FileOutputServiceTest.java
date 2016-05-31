package net.blacksails.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import net.blacksails.domain.Coord;
import net.blacksails.domain.GameBoard;
import net.blacksails.domain.Orientation;
import net.blacksails.domain.Ship;
import net.blacksails.strategy.OutputStrategy;

import org.junit.After;
import org.junit.Test;
import org.mockito.Mockito;

public class FileOutputServiceTest {
	private static String originalOutputFileName;
	private static final String outputFileName = "testOutput.txt";
	
	@Test
	public void testCreateOutput() throws IOException {
		GameBoard board = new GameBoard(10);
		Coord coord = new Coord(3, 5);
		Ship ship = new Ship(coord, Orientation.EAST);
		List<Ship> shipList = new ArrayList<Ship>();
		shipList.add(ship);
		
		OutputStrategy outputStrategy = Mockito.mock(OutputStrategy.class);
		Mockito.when(outputStrategy.generateOutput(board)).thenReturn("output");
		
		originalOutputFileName = FileOutputService.outputFileName;
		FileOutputService.outputFileName = outputFileName;
		// -------------------------------------------------------
		FileOutputService fileOutputService = new FileOutputService();
		fileOutputService.createOutput(outputStrategy, board);
		// -------------------------------------------------------
		assertEquals(getOutputFileContents(), "output");
	}
	
	private String getOutputFileContents() throws IOException {
		File file = new File(outputFileName);
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();
		return new String(data, "UTF-8");
	}
	
	@After
	public void cleanupOutputFile() throws IOException {
		Files.delete(FileSystems.getDefault().getPath(".", outputFileName));
		FileOutputService.outputFileName = originalOutputFileName;
	}
}
