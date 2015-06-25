package net.thoughtmachine;

import java.io.IOException;
import java.io.InputStream;

import net.thoughtmachine.domain.GameBoard;
import net.thoughtmachine.domain.GameModel;
import net.thoughtmachine.service.ClassicInputParserService;
import net.thoughtmachine.service.ClassicSimulationService;
import net.thoughtmachine.service.FileOutputService;
import net.thoughtmachine.service.InputParserService;
import net.thoughtmachine.service.OutputService;
import net.thoughtmachine.service.SimulationService;
import net.thoughtmachine.strategy.ClassicOutputStrategy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class BlackSails {
	InputParserService inputParser;
	SimulationService simulationService;
	OutputService outputService;
	private String inputPath;
	
	public BlackSails(InputParserService inputParser, SimulationService simulationService,
			OutputService outputService, String inputPath) {
		this.inputParser = inputParser;
		this.simulationService = simulationService;
		this.outputService = outputService;
		this.inputPath = inputPath;
	}
	
	public void sail() throws IOException {
		// Parse the input and get the game model
		GameModel gameModel = inputParser.parseInput(openInputStream(inputPath));
		
		// Simulate the model
		GameBoard finalGameBoard = simulationService.play(gameModel);
		
		// Output the results
		outputService.createOutput(new ClassicOutputStrategy(), finalGameBoard);
	}
  
  	public InputStream openInputStream(String filePath) {
  		return getClass().getResourceAsStream(filePath);
  	}
  	
  	public static CommandLine getCommandLineArguments(String... args) throws ParseException {
  		Options options = new Options();
  		options.addOption("f", false, "path to input file");
  		
  		CommandLineParser parser = new DefaultParser();
  		return parser.parse(options, args);
  	}

  	public static void main(String... args) throws ParseException, IOException {
  		// Create the services... this could be done by dependency injection
		InputParserService inputParser = new ClassicInputParserService();
		SimulationService simulationService = new ClassicSimulationService();
		OutputService outputService = new FileOutputService();
		
		// Get command line arguments
		CommandLine clArgs = getCommandLineArguments(args);
		
		// Let's go!
  		BlackSails app = new BlackSails(inputParser, simulationService, outputService, clArgs.getOptionValue('f'));
  		app.sail();
    }
}
