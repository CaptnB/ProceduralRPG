package com.captnb.proceduralrpg.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class NewGameScreen extends GameScreen
{
    Sound sound;
    Music music;
    Boolean init;

    public NewGameScreen(Game game, AssetManager manager, SpriteBatch batch)
    {
        super(game, manager, batch);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void show()
    {
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/test_sound.mp3"));
        sound.play();
        music = Gdx.audio.newMusic(Gdx.files.internal("music/test_music.mp3"));
        music.setLooping(true);
        music.play();
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        TextureAtlas characters = assets.get("images/characters/characters.pack", TextureAtlas.class);
        BitmapFont font = assets.get("text1_20.ttf", BitmapFont.class);
        
        int i = 0;
        for(AtlasRegion character : characters.getRegions())
        {
            int colorI = i % 4;
            switch(colorI)
            {
            case 0:
                batch.setColor(Color.WHITE);
                break;
            case 1:
                batch.setColor(Color.RED);
                break;
            case 2:
                batch.setColor(Color.GREEN);
                break;
            case 3:
                batch.setColor(Color.BLUE);
                break;
            default:
                batch.setColor(Color.WHITE);
                break;
            }
            int row = ((i % 7) * 225) + 25;
            int col = ((i / 7) * 220) + 20;
            batch.draw(character, row, col, 200, 200);
            
            font.draw(batch, character.name, row, col - 5 );

            i += 1;
        }
        batch.end();
    }

}
