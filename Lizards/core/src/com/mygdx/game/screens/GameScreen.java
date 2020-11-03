package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Auber;
import com.mygdx.game.model.World;

public class GameScreen implements Screen, InputProcessor {
    private World world = new World();
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture bucketImage;
    int x = 100;

    // Called when this screen becomes active
    @Override
    public void show() {
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        batch = new SpriteBatch();
    }

    // Game loop. Update game logic and draw onto the screen.
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(bucketImage, world.getAuber().getX(), world.getAuber().getY());
        world.getAuber().setX(x);
        x += 5;
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

    @Override
    public boolean keyDown(int keycode) {
        // if left then controller.leftKeyPressed....

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
