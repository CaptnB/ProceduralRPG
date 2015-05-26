package com.captnb.proceduralrpg.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Json;
import com.captnb.proceduralrpg.assets.AssetPackage;

public class SplashScreen extends GameScreen
{

    public SplashScreen(Game game, AssetManager manager, SpriteBatch batch)
    {
        super(game, manager, batch);
    }

    @Override
    public void show()
    {
        // Loader for ttf
        FileHandleResolver resolver = new InternalFileHandleResolver();
        assets.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assets.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        // Load splash assets first to display something asap
        loadAssets("splash.json");
        loadAssets("fonts.json");
        loadAssets("characters.json");
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (assets.update())
        {
            game.setScreen(new NewGameScreen(game, assets, batch));
        }
        else if (assets.isLoaded("splash_title.ttf") && assets.isLoaded("splash_loading.ttf") && assets.isLoaded("images/splash.png"))
        {
            batch.begin();
            
            // Splash image first
            Texture splash = assets.get("images/splash.png", Texture.class);
            batch.draw(splash, (Gdx.graphics.getWidth() - splash.getWidth()) / 2, (Gdx.graphics.getHeight() - splash.getHeight()) / 2);
            
            // Title on top
            BitmapFont titleFont = assets.get("splash_title.ttf", BitmapFont.class);
            GlyphLayout titleLayout = new GlyphLayout();
            titleLayout.setText(titleFont, "Procedural RPG", Color.BLACK, Gdx.graphics.getWidth(), Align.center, false);
            titleFont.draw(batch, titleLayout, 0, (9 * Gdx.graphics.getHeight() / 10) - (titleLayout.height / 2) );
            
            // Progress at the bottom
            BitmapFont loadingFont = assets.get("splash_loading.ttf", BitmapFont.class);
            GlyphLayout loadingLayout = new GlyphLayout();
            String progress = String.format("%.0f", assets.getProgress() * 100);
            loadingLayout.setText(loadingFont, "Loading " + progress + "%", Color.BLACK, Gdx.graphics.getWidth(), Align.center, false);
            loadingFont.draw(batch, loadingLayout, 0, (Gdx.graphics.getHeight() / 10) - (loadingLayout.height / 2) );

            batch.end();
        }
    }

    void loadAssets(String packageFile)
    {
        Json json = new Json();
        AssetPackage assetPackage = json.fromJson(AssetPackage.class, Gdx.files.internal(packageFile));
        assetPackage.load(assets);
    }

}
