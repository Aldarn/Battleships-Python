package net.blacksails;

import java.io.IOException;
import java.io.InputStream;

import net.blacksails.domain.GameBoard;
import net.blacksails.domain.GameModel;
import net.blacksails.service.ClassicInputParserService;
import net.blacksails.service.ClassicSimulationService;
import net.blacksails.service.FileOutputService;
import net.blacksails.service.InputParserService;
import net.blacksails.service.OutputService;
import net.blacksails.service.SimulationService;
import net.blacksails.strategy.ClassicOutputStrategy;

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
		// TODO: This won't work with Gradle, fix it
		// CommandLine clArgs = getCommandLineArguments(args);
		// String inputFile = clArgs.getOptionValue('f');
		String inputFile = args[0];
		
		// Let's go!
  		BlackSails app = new BlackSails(inputParser, simulationService, outputService, inputFile);
  		app.sail();
    }
}
