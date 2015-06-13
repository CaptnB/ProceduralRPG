package com.captnb.proceduralrpg.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.captnb.proceduralrpg.actors.SplashTable;

public class SplashScreen extends AbstractScreen
{

    public SplashScreen(Game game, AssetManager assets)
    {
        super(game, assets);
    }

    @Override
    protected void build()
    {
        addActor(new SplashTable(game, assets));
    }

}
