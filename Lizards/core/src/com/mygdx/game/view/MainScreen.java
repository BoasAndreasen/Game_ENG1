package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.controller.InfiltratorController;
import com.mygdx.game.model.World;
import com.mygdx.game.MyGame;
import com.mygdx.game.controller.AuberController;

import java.awt.*;

public class MainScreen implements Screen {
	
	MyGame game;
	
    public MainScreen(MyGame game) {
        this.game = game;
    }
	
    private Label.LabelStyle notify_style;
    private BitmapFont my_font;
    private Label notify_label;
    private Stage stage;
    private Skin skin;
    
    


	@Override
	public void show() {
		
        notify_style = new Label.LabelStyle();
        my_font = new BitmapFont(Gdx.files.internal("my_font.fnt"));
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("clean-crispy-ui.json"));
        notify_style.font = my_font;
        notify_style.background = skin.getDrawable("button");
        notify_label = new Label("Press Any Key to Start",notify_style);
        notify_label.setSize(900,100);
        notify_label.setAlignment(Align.center);
        notify_label.setPosition(Gdx.graphics.getWidth()/2-450,0);
        
        stage.addActor(notify_label);
        
        Gdx.input.setInputProcessor(new InputAdapter() {
        	
        	   public boolean keyDown (int keycode) {

        		  game.setScreen(new GameScreen(game));
        		  
        	      return false;
        	   }			
        });
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
        
        stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	
}

