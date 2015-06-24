package net.thoughtmachine.service;

import java.io.InputStream;

import net.thoughtmachine.domain.GameModel;

public interface InputParserService {
	GameModel parseInput(InputStream inputStream);
}
