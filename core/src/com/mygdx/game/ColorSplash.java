package com.mygdx.game;

import Screens.SplashScreen;
import com.badlogic.gdx.Game;
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
		manager.load("Characters/Character38.png", Texture.class);
		manager.load("Bottles/BLACK.png", Texture.class);
		manager.load("Bottles/BLUE.png", Texture.class);
		manager.load("Bottles/BLUE_LIGHT.png", Texture.class);
		manager.load("Bottles/RED.png", Texture.class);
		manager.load("Puddles/puddle_black.png", Texture.class);
		manager.load("Puddles/puddle_red.png", Texture.class);
		manager.load("Puddles/puddle_blue.png", Texture.class);
		manager.load("Puddles/puddle_blue_light.png", Texture.class);
		manager.load("Characters/Character1.png", Texture.class);
		manager.load("Characters/Character2.png", Texture.class);
		manager.load("Characters/Character3.png", Texture.class);
		manager.load("Characters/Character4.png", Texture.class);
		manager.load("Characters/Character5.png", Texture.class);
		manager.load("Characters/Character6.png", Texture.class);
		manager.load("Characters/Character7.png", Texture.class);
		manager.load("Characters/Character8.png", Texture.class);
		manager.load("Characters/Character9.png", Texture.class);
		manager.load("Characters/Character10.png", Texture.class);
		manager.load("Characters/Character11.png", Texture.class);
		manager.load("Characters/Character12.png", Texture.class);
		manager.load("Characters/Character13.png", Texture.class);
		manager.load("Characters/Character14.png", Texture.class);
		manager.load("Characters/Character15.png", Texture.class);
		manager.load("Characters/Character16.png", Texture.class);
		manager.load("Characters/Character17.png", Texture.class);
		manager.load("Characters/Character18.png", Texture.class);
		manager.load("Characters/Character19.png", Texture.class);
		manager.load("Characters/Character20.png", Texture.class);
		manager.load("Characters/Character21.png", Texture.class);
		manager.load("Characters/Character22.png", Texture.class);
		manager.load("Characters/Character23.png", Texture.class);
		manager.load("Characters/Character24.png", Texture.class);
		manager.load("Characters/Character25.png", Texture.class);
		manager.load("Characters/Character26.png", Texture.class);
		manager.load("Characters/Character27.png", Texture.class);
		manager.load("Characters/Character28.png", Texture.class);
		manager.load("Characters/Character29.png", Texture.class);
		manager.load("Characters/Character30.png", Texture.class);
		manager.load("Characters/Character31.png", Texture.class);
		manager.load("Characters/Character32.png", Texture.class);
		manager.load("Characters/Character33.png", Texture.class);
		manager.load("Characters/Character34.png", Texture.class);
		manager.load("Characters/Character35.png", Texture.class);
		manager.load("Characters/Character36.png", Texture.class);
		manager.load("Characters/Character37.png", Texture.class);
		manager.load("Characters/Character0.png", Texture.class);
		manager.load("Characters/Character0.png", Texture.class);
		manager.load("bomb.png", Texture.class);
		manager.load("sounds/bomb.wav", Sound.class);
		manager.load("sounds/fire.wav", Sound.class);
		manager.load("sounds/freezing.mp3", Sound.class);
		manager.load("sounds/splash2.wav", Sound.class);
		manager.load("AnimationEffects/ExplosionAnimation.png", Texture.class);
		manager.load("AnimationEffects/FireAnimation.png", Texture.class);
		manager.load("AnimationEffects/FreezeAnimation.png", Texture.class);
		manager.load("AnimationEffects/WaterAnimation.png", Texture.class);
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
