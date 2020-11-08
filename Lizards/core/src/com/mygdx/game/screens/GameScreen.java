package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
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
    private Texture bucketImage;
    private Texture systemImage;

    // Called when this screen becomes active
    @Override
    public void show() {
        world = new World();
        auberController = new AuberController(world);
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));
        systemImage= new Texture(Gdx.files.internal("systemsImage.jpg"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(auberController);
        //TODO hello
    }

    // Game loop. Update game logic and draw onto the screen.
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(bucketImage, world.getAuber().getX(), world.getAuber().getY());
        batch.draw(systemImage,world.getSys1().getX(),world.getSys1().getY());
        batch.draw(systemImage,world.getSys2().getX(),world.getSys2().getY());
        batch.end();
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
