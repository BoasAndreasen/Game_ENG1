package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.model.World;
import com.mygdx.game.controller.AuberController;

public class GameScreen implements Screen {
    private World world;
    private AuberController auberController;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    
    //IMAGES
    private Texture bucketImage;
    private Texture systemImage;
    private Texture healthImg;
    private Texture infiltratorImage;
    
    private boolean isRoom1 = true;
    private boolean isRoom2, isRoom3, isRoom4 = false;

    // Called when this screen becomes active
    @Override
    public void show() {
        world = new World();
        auberController = new AuberController(world);
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));
        systemImage= new Texture(Gdx.files.internal("systemsImage.jpg"));
        healthImg= new Texture(Gdx.files.internal("health.png"));
        
        //INFILTRATOR IMAGE - Brian
        infiltratorImage = new Texture(Gdx.files.internal("Infiltrator.png"));
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 600);
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(auberController);
    }

    // Game loop. Update game logic and draw onto the screen.
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(bucketImage, world.getAuber().getX(), world.getAuber().getY());

        // render systems if they arent destroyed
        if (!world.getSystems().get(0).isDestroyed()) {
            batch.draw(systemImage,world.getSystems().get(0).getX(),world.getSystems().get(0).getY());
        }
        if (!world.getSystems().get(1).isDestroyed()) {
            batch.draw(systemImage,world.getSystems().get(1).getX(),world.getSystems().get(1).getY());
        }

        // draw system1 health bar at bottom. Green when health 70% or more. Orange when 40% or more. Red otherwise
        if (world.getSystems().get(0).getHealth()>=70){ batch.setColor(Color.GREEN); }
        else if (world.getSystems().get(0).getHealth()>=40){batch.setColor(Color.ORANGE);}
        else {batch.setColor(Color.RED);}
        batch.draw(healthImg,world.getHB().getX(),world.getHB().getY());
        batch.setColor(Color.WHITE);

        //INFILTRATOR - Brian
        batch.draw(infiltratorImage, world.getInfiltrator().getX(),world.getInfiltrator().getY());
        
        batch.end();
        
        updateInfiltratorLocationX();
        updateInfiltratorLocationY();
    
        updateAuberLocation();
        updateCameraRoomLocation();
        camera.update();
    }

    private void updateAuberLocation() {
        if (auberController.isUpPressed()) {
            if (!(world.getAuber().getY() >= 1100)) {
                world.getAuber().addY(10);
            }
        } else if (auberController.isDownPressed()) {
            if (!(world.getAuber().getY() <= 0)) {
                world.getAuber().addY(-10);
            }
        } else if (auberController.isLeftPressed()) {
            if (!(world.getAuber().getX() <= 0)) {
                world.getAuber().addX(-10);
            }
        } else if (auberController.isRightPressed()) {
            if (!(world.getAuber().getX() >= 2300)) {
                world.getAuber().addX(10);
            }
        }
    }
    
    //INFILTRATOR - Brian
    
    private void updateInfiltratorLocationX() {
    	
    	if (world.getInfiltrator().getX() > world.getSystems().get(0).getX())
    	{
    		world.getInfiltrator().addX(-5);
    	}
    	
    	if (world.getInfiltrator().getX() < world.getSystems().get(0).getX())
    	{
    		world.getInfiltrator().addX(5);
    	}
    	
    }
    
    private void updateInfiltratorLocationY() {
    	
    	if (world.getInfiltrator().getY() > world.getSystems().get(0).getY())
    	{
    		world.getInfiltrator().addY(-5);
    	}
    	
    	if (world.getInfiltrator().getY() < world.getSystems().get(0).getY())
    	{
    		world.getInfiltrator().addY(5);
    	}
    }

    private void updateCameraRoomLocation() {
        if (isRoom1 && world.getAuber().getY() >= 600) {
            camera.translate(0,600,0);
            isRoom2 = true;
            isRoom1 = false;
        } if (isRoom1 && world.getAuber().getX() >= 1200) {
            camera.translate(1200,0,0);
            isRoom3 = true;
            isRoom1 = false;
        } if (isRoom2 && world.getAuber().getY() <= 600) {
            camera.translate(0,-600,0);
            isRoom2 = false;
            isRoom1 = true;
        } if (isRoom2 && world.getAuber().getX() >= 1200) {
            camera.translate(1200,0,0);
            isRoom2 = false;
            isRoom4 = true;
        } if (isRoom3 && world.getAuber().getX() <= 1200) {
            camera.translate(-1200,0,0);
            isRoom3 = false;
            isRoom1 = true;
        } if (isRoom3 && world.getAuber().getY() > 600) {
            camera.translate(0,600,0);
            isRoom3 = false;
            isRoom4 = true;
        } if (isRoom4 && world.getAuber().getX() < 1200) {
            camera.translate(-1200,0,0);
            isRoom4 = false;
            isRoom2 = true;
        } if (isRoom4 && world.getAuber().getY() < 600) {
            camera.translate(0,-600,0);
            isRoom4 = false;
            isRoom3 = true;
        }
    }

    // Called once when gate starts and when resized
    public void resize(int width, int height) {
        // TODO
    }

    // Called when game ends, just before dispose. Save game state here.
    public void pause() {
        // TODO
    }

    @Override
    public void resume() {
        // TODO
    }

    // Called when another screen becomes active
    @Override
    public void hide() {
        // TODO
    }

    // Called once when the application exits
    @Override
    public void dispose() {
        //TODO
    }
}
