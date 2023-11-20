package com.mygdx.game;

import Screens.SplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class ColorSplash extends Game {
	public static AssetManager manager;
	public static Music music;

	public static InputMultiplexer multiplexer;

	public static void addInputProcessor(InputProcessor inputProcessor){
		if (multiplexer == null) multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(inputProcessor);
		Gdx.input.setInputProcessor(multiplexer);
	}
	@Override
	public void create() {
		manager = new AssetManager();
		manager.load("blue-.png", Texture.class);
		manager.load("logo.png", Texture.class);
		manager.load("red-.png", Texture.class);
		manager.load("tileset.png", Texture.class);
		manager.load("Mage.png", Texture.class);

		//manager.load("sounds/wavetable.mp3", Music.class);
		//manager.load("sounds/laser_gun.mp3", Sound.class);

		manager.finishLoading();

//		music = manager.get("sounds/wavetable.mp3");
//		music.setLooping(true);
//		music.setVolume(0.02f);
//		music.play();
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
