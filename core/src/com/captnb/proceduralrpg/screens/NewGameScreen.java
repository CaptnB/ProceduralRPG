package com.captnb.proceduralrpg.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class NewGameScreen extends GameScreen
{
    private Sound sound;
    private Music music;

    public NewGameScreen(Game game, AssetManager manager, SpriteBatch batch)
    {
        super(game, manager, batch);
    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(stage);
        
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/test_sound.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music/test_music.mp3"));
        music.setLooping(true);
        

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        
        TextButtonStyle style = new TextButtonStyle();
        style.font = assets.get("text1_30.ttf", BitmapFont.class);
        
        TextButton soundBtn = new TextButton("Sound", style);
        soundBtn.addListener(new ClickListener()
            {
                @Override
                public void clicked (InputEvent event, float x, float y)
                {
                    sound.play();
                }
            }
        );

        TextButton musicBtn = new TextButton("Music", style);
        musicBtn.addListener(new ClickListener()
            {
                @Override
                public void clicked (InputEvent event, float x, float y)
                {
                    if(music.isPlaying())
                    {
                        music.pause();
                    }
                    else
                    {
                        music.play();
                    }
                }
            }
        );
        
        TextButton newGameBtn = new TextButton("New Game", style);
        newGameBtn.addListener(new ClickListener()
            {
                @Override
                public void clicked(InputEvent event, float x, float y)
                {
                    game.setScreen(new MapScreen(game, assets, batch));
                }
            }
        );

        table.add(soundBtn).align(Align.topRight).pad(10).expand();
        table.add(musicBtn).align(Align.topRight).pad(10);
        table.row().expand();
        table.add(newGameBtn);
        table.setDebug(true);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
//
//        batch.begin();
//        batch.end();
    }
    
    @Override
    public void resize(int width, int height)
    {
        stage.getViewport().update(width, height);
    }

    @Override
    public void dispose()
    {
        stage.dispose();
    }
}
