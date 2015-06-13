package com.captnb.proceduralrpg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.captnb.proceduralrpg.screens.SplashScreen;

public class ProceduralRPGGame extends Game
{
    private AssetManager assets;

    @Override
    public void create()
    {
        assets = new AssetManager();
        setScreen(new SplashScreen(this, assets));
    }

    @Override
    public void dispose()
    {
        super.dispose();
        assets.dispose();
    }
}
