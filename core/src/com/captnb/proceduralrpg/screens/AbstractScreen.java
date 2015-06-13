package com.captnb.proceduralrpg.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.captnb.proceduralrpg.utils.ArgCheck;

public abstract class AbstractScreen extends Stage implements Screen
{
    protected Game game;
    protected AssetManager assets;
    
    protected AbstractScreen(Game game, AssetManager assets)
    {
        super(new FillViewport(1600.0f, 900.0f, new OrthographicCamera()));

        ArgCheck.notNull(game);
        ArgCheck.notNull(assets);
        
        this.game = game;
        this.assets = assets;
    }

    @Override
    public final void show()
    {
        Gdx.input.setInputProcessor(this);
        build();
    }
    
    protected abstract void build();

    @Override
    public final void render(float delta)
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        act(delta);
        draw();
    }

    @Override
    public final void resize(int width, int height)
    {
        ArgCheck.isExclusivelyPositive(width);
        ArgCheck.isExclusivelyPositive(height);
        
        getViewport().update(width, height, true);
    }
    
    @Override
    public void pause(){}

    @Override
    public void resume(){}

    @Override
    public void hide(){}
    
}
