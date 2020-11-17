package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.view.MainScreen;

public class MyGame extends Game {
    @Override
    public void create() {
        setScreen(new MainScreen(this));
    }
}