package com.captnb.proceduralrpg.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class ScreenManager
{
    private Game game;
    
    public ScreenManager(Game game)
    {
        this.game = game;
    }

    public void setScreen(Screen screen)
    {
        game.setScreen(screen);
    }

}
