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

	@Override
	public void create() {
		manager = new AssetManager();
		manager.load("blue-.png", Texture.class);
		manager.load("logo.png", Texture.class);
		manager.load("red-.png", Texture.class);
		manager.load("tileset.png", Texture.class);
		manager.load("Characters/Mage.png", Texture.class);
		manager.load("Bottles/BLACK.png", Texture.class);
		manager.load("Bottles/BLUE.png", Texture.class);
		manager.load("Bottles/BLUE_LIGHT.png", Texture.class);
		manager.load("Bottles/RED.png", Texture.class);
		manager.load("Puddles/puddle_black.png", Texture.class);
		manager.load("Puddles/puddle_red.png", Texture.class);
		manager.load("Puddles/puddle_blue.png", Texture.class);
		manager.load("Puddles/puddle_blue_light.png", Texture.class);
		manager.load("Characters/Fighter.png", Texture.class);

		//manager.load("sounds/music.mp3", Music.class); musica
		manager.load("sounds/Bottle Break.wav", Sound.class); //efeitos especiais

		manager.finishLoading();

//		music = manager.get("sounds/music.mp3");
//		music.setLooping(true);
//		music.setVolume(0.02f);
//		music.play();

		this.setScreen(new SplashScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		if (getScreen() != null) {
			getScreen().dispose();
		}
	}
}
