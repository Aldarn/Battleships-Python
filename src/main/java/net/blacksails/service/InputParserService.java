package net.blacksails.service;

import java.io.InputStream;

import net.blacksails.domain.GameModel;

public interface InputParserService {
	GameModel parseInput(InputStream inputStream);
}
