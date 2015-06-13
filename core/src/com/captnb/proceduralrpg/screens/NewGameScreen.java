package com.captnb.proceduralrpg.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class NewGameScreen extends AbstractScreen
{
    private Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/test_sound.mp3"));
    private Music music = Gdx.audio.newMusic(Gdx.files.internal("music/test_music.mp3"));

    public NewGameScreen(Game game, AssetManager assets)
    {
        super(game, assets);
    }

    @Override
    public void build()
    {
        music.setLooping(true);

        Table table = new Table();
        table.setFillParent(true);
        addActor(table);
        
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
                    game.setScreen(new MapScreen(game, assets));
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
    public void dispose()
    {
        super.dispose();
        music.dispose();
        sound.dispose();
    }

}
