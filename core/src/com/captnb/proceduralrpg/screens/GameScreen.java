package com.captnb.proceduralrpg.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameScreen implements Screen
{
    protected Game game; // So we can transition to other screens
    protected AssetManager assets; // So we can load or retrieved loaded assets
    protected SpriteBatch batch; // So we can render the screen

    public GameScreen(Game game, AssetManager assets, SpriteBatch batch)
    {
        this.game = game;
        this.assets = assets;
        this.batch = batch;
    }

    @Override
    public void resize(int width, int height)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose()
    {
        // TODO Auto-generated method stub

    }

}
