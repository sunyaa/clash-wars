package com.clashwars.side;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.clashwars.game.Connection;
import com.clashwars.screens.GameScreen;

public class MenuInputHandler implements InputProcessor {
    public Connection connection = null;
    public Game game;

    public MenuInputHandler(Game game){
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
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
        // After connection, display 'waiting for matchmaking message'.
        if(connection == null) {
            connection = new Connection();
            String confirmMsg = new String("");

            while(true) {
                confirmMsg = connection.read();
                if("200".equals(confirmMsg)) {
                    break;
                }
            }
            Gdx.app.log("read()", "END OF WHILE");
            GameScreen s = new GameScreen(game, connection);
            game.setScreen(new GameScreen(game,connection));
            Gdx.input.setInputProcessor(new GameInputHandler(game,connection,s.renderer));
            Gdx.app.log("read()", confirmMsg);
        }
        return true;
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