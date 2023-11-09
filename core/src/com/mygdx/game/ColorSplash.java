package com.mygdx.game;

import Screens.SplashScreen;
import com.badlogic.gdx.Game;

public class ColorSplash extends Game { // Note que agora estende Game
	@Override
	public void create() {
		// Define a tela inicial do jogo para a SplashScreen
		this.setScreen(new SplashScreen(this));
	}

	@Override
	public void render() {
		super.render(); // Essa chamada delega a renderização para a tela atual
	}

	@Override
	public void dispose() {
		// Se você tiver outras telas, elas também devem ser descartadas.
		if (getScreen() != null) {
			getScreen().dispose();
			//MainMenuScreen.dispose();
			//SplashScreen.dispose();
		}
	}
}
