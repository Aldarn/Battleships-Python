package net.thoughtmachine.service;

import java.io.FileOutputStream;
import java.io.IOException;

import net.thoughtmachine.domain.GameBoard;
import net.thoughtmachine.strategy.OutputStrategy;

public class FileOutputService implements OutputService {
	@Override
	public void createOutput(OutputStrategy strategy, GameBoard board) throws IOException {
		FileOutputStream out = new FileOutputStream("output.txt");
		out.write(strategy.generateOutput(board).getBytes());
		out.close();
	}
}
