package com.captnb.proceduralrpg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.captnb.proceduralrpg.screens.SplashScreen;

public class ProceduralRPGGame extends Game
{
    protected AssetManager assets;
    protected SpriteBatch batch;

    @Override
    public void create()
    {
        assets = new AssetManager();
        batch = new SpriteBatch();

        setScreen(new SplashScreen(this, assets, batch));
    }

    @Override
    public void dispose()
    {
        super.dispose();
        assets.dispose();
        batch.dispose();
    }
}
