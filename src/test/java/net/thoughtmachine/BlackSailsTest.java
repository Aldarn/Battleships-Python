package net.thoughtmachine;

import static org.mockito.Mockito.doReturn;

import java.io.IOException;
import java.io.InputStream;

import net.thoughtmachine.domain.GameBoard;
import net.thoughtmachine.domain.GameModel;
import net.thoughtmachine.service.InputParserService;
import net.thoughtmachine.service.OutputService;
import net.thoughtmachine.service.SimulationService;
import net.thoughtmachine.strategy.OutputStrategy;

import org.junit.Test;
import org.mockito.Mockito;

public class BlackSailsTest {
	@Test
	public void testGameLoop() throws IOException {
		InputParserService inputParser = Mockito.mock(InputParserService.class);
		SimulationService simulationService = Mockito.mock(SimulationService.class);
		OutputService outputService = Mockito.mock(OutputService.class);
		
		BlackSails app = new BlackSails(inputParser, simulationService, outputService, "/input.txt");
		BlackSails appSpy = Mockito.spy(app);
		InputStream inputStream = Mockito.mock(InputStream.class);
		doReturn(inputStream).when(appSpy).openInputStream("/input.txt");
		
		// TODO: Should use concrete instances of domain objects when testing
		GameModel model = Mockito.mock(GameModel.class);
		GameBoard board = Mockito.mock(GameBoard.class);
		
		Mockito.when(inputParser.parseInput(inputStream)).thenReturn(model);
		Mockito.when(simulationService.play(model)).thenReturn(board);
		// -------------------------------------------------------
		appSpy.sail();
		// -------------------------------------------------------
		Mockito.verify(inputParser).parseInput(inputStream);
		Mockito.verify(simulationService).play(model);
		Mockito.verify(outputService).createOutput((OutputStrategy)Mockito.anyObject(), (GameBoard)Mockito.anyObject());
	}
}
