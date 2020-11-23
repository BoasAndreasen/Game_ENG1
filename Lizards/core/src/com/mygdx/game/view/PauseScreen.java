package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGame;

public class PauseScreen implements Screen {

    MyGame game;

    public PauseScreen(MyGame game) {
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
        notify_label = new Label("Enter: exit game | Backspace: restart game",notify_style);
        notify_label.setSize(900,100);
        notify_label.setAlignment(Align.center);
        notify_label.setPosition(Gdx.graphics.getWidth()/2-450,0);

        stage.addActor(notify_label);

        Gdx.input.setInputProcessor(new InputAdapter() {

            public boolean keyDown (int keycode) {

                if(keycode == 66) //ENTER
                {
                    Gdx.app.exit();
                    System.exit(0);
                }
                else if(keycode == 67) //BACKSPACE
                {
                    game.setScreen(new MainScreen(game));
                }

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

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
