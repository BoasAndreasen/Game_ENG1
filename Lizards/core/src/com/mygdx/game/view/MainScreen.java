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
	
    //Text style
    private Label.LabelStyle menu_style;
    private BitmapFont my_font;
    private Label menu_label;
    private Label move_label;
    private Label infiltrator_label;
    private Label teleport_label;
    private Label heal_label;
    private Label system_label;
    private Label shield_label;
    private Label bomb_label;
    private Label corrupt_label;
    private Stage stage;
    private Skin skin;
    
    //Images
    private Texture bucketImage; //Auber
    private Texture infiltratorImage; //Infiltrator
    private Texture systemImage; //System
    private Texture teleportPadImage; // TeleportPad
    private Texture bombImage; //bomb
    private Texture shieldImage; //shield
    private Texture corruptImage; //corrupt
    private Texture healPadImage; // Healing Pads
    
    private SpriteBatch batch;


	@Override
	public void show() {
		
		//Text style		
        menu_style = new Label.LabelStyle();
        my_font = new BitmapFont(Gdx.files.internal("my_font.fnt"));
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("clean-crispy-ui.json"));
        menu_style.font = my_font;
        menu_style.background = skin.getDrawable("button");
        
        //Images
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));
        systemImage = new Texture(Gdx.files.internal("System.png"));
        infiltratorImage = new Texture(Gdx.files.internal("Infiltrator.png"));
        teleportPadImage = new Texture(Gdx.files.internal("TeleportPad.png"));
        bombImage= new Texture(Gdx.files.internal("bomb2.jpg"));
        shieldImage= new Texture(Gdx.files.internal("shield.png"));
        corruptImage= new Texture(Gdx.files.internal("corrupt.jpg"));
        healPadImage = new Texture(Gdx.files.internal("HealPad.png"));
        
        //Movement text
        
        move_label = new Label("Use WASD or Arrow\nKeys to Move Auber",menu_style);
        move_label.setSize(450,100);
        move_label.setAlignment(Align.center);
        move_label.setPosition(Gdx.graphics.getWidth()/2 - 460, Gdx.graphics.getHeight()-110);
        stage.addActor(move_label);
        
        //Infiltrator text
        
        infiltrator_label = new Label("Click Infiltrators\nNearby to Arrest Them",menu_style);
        infiltrator_label.setSize(450,100);
        infiltrator_label.setAlignment(Align.center);
        infiltrator_label.setPosition(Gdx.graphics.getWidth()/2 - 460, Gdx.graphics.getHeight()-220);
        stage.addActor(infiltrator_label);
        
        //Teleporter text
        
        teleport_label = new Label("Use Teleporter Pads\nto Get About Quickly",menu_style);
        teleport_label.setSize(450,100);
        teleport_label.setAlignment(Align.center);
        teleport_label.setPosition(Gdx.graphics.getWidth()/2 - 460, Gdx.graphics.getHeight()-330);
        stage.addActor(teleport_label);
        
        //Heal pad text
        
        heal_label = new Label("Use the Infirmary's\nHeal Pad if Injured",menu_style);
        heal_label.setSize(450,100);
        heal_label.setAlignment(Align.center);
        heal_label.setPosition(Gdx.graphics.getWidth()/2 - 460, Gdx.graphics.getHeight()-440);
        stage.addActor(heal_label);
        
        //Systems text
        
        system_label = new Label("Protect the Ship's\nSystems",menu_style);
        system_label.setSize(450,100);
        system_label.setAlignment(Align.center);
        system_label.setPosition(Gdx.graphics.getWidth()- 460, Gdx.graphics.getHeight()-110);
        stage.addActor(system_label);
        
        //Shield text
        
        shield_label = new Label("Shields Block Auber\nFrom Arresting When Up",menu_style);
        shield_label.setSize(450,100);
        shield_label.setAlignment(Align.center);
        shield_label.setPosition(Gdx.graphics.getWidth()- 460, Gdx.graphics.getHeight()-220);
        stage.addActor(shield_label);
        
        //Bomb text
        
        bomb_label = new Label("Bombs Deal Greater\nDamage - Watch Out!",menu_style);
        bomb_label.setSize(450,100);
        bomb_label.setAlignment(Align.center);
        bomb_label.setPosition(Gdx.graphics.getWidth()- 460, Gdx.graphics.getHeight()-330);
        stage.addActor(bomb_label);
        
        //Corrupt text
        
        corrupt_label = new Label("Corrupt Makes Auber\nDamage Nearby Systems",menu_style);
        corrupt_label.setSize(450,100);
        corrupt_label.setAlignment(Align.center);
        corrupt_label.setPosition(Gdx.graphics.getWidth()- 460, Gdx.graphics.getHeight()-440);
        stage.addActor(corrupt_label);
        
        
        //Press to start text
        menu_label = new Label("Press Any Key to Start",menu_style);
        menu_label.setSize(900,100);
        menu_label.setAlignment(Align.center);
        menu_label.setPosition(Gdx.graphics.getWidth()/2-450,0);
        
        stage.addActor(menu_label);
        
        Gdx.input.setInputProcessor(new InputAdapter() {
        	
        	   public boolean keyDown (int keycode) {

        		  game.setScreen(new GameScreen(game));
        		  
        	      return false;
        	   }			
        });
        
        batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
        
        stage.draw();
        
        batch.begin();
        
        //Example image rendering
        
        batch.draw(bucketImage, (Gdx.graphics.getWidth()/2 - 460 - bucketImage.getWidth())/2, Gdx.graphics.getHeight()-60-(bucketImage.getHeight()/2));
        
        batch.draw(infiltratorImage, (Gdx.graphics.getWidth()/2 - 460 - infiltratorImage.getWidth())/2, Gdx.graphics.getHeight()-170-(infiltratorImage.getHeight()/2));

        batch.draw(teleportPadImage, (Gdx.graphics.getWidth()/2 - 460 - teleportPadImage.getWidth())/2, Gdx.graphics.getHeight()-280-(teleportPadImage.getHeight()/2));
        
        batch.draw(healPadImage, (Gdx.graphics.getWidth()/2 - 460 - healPadImage.getWidth())/2, Gdx.graphics.getHeight()-390-(healPadImage.getHeight()/2));
        
        batch.draw(systemImage, (Gdx.graphics.getWidth()/2+(Gdx.graphics.getWidth()/2 - 460 - systemImage.getWidth())/2), Gdx.graphics.getHeight()-60-(systemImage.getHeight()/2));

        batch.draw(shieldImage, (Gdx.graphics.getWidth()/2+(Gdx.graphics.getWidth()/2 - 460 - shieldImage.getWidth())/2), Gdx.graphics.getHeight()-170-(shieldImage.getHeight()/2));

        batch.draw(bombImage, (Gdx.graphics.getWidth()/2+(Gdx.graphics.getWidth()/2 - 460 - bombImage.getWidth())/2), Gdx.graphics.getHeight()-280-(bombImage.getHeight()/2));

        batch.draw(corruptImage, (Gdx.graphics.getWidth()/2+(Gdx.graphics.getWidth()/2 - 460 - corruptImage.getWidth())/2), Gdx.graphics.getHeight()-390-(corruptImage.getHeight()/2));

        batch.end();
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

