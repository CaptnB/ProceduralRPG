package com.captnb.proceduralrpg.actors;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.captnb.proceduralrpg.assets.AssetPackage;
import com.captnb.proceduralrpg.utils.ArgCheck;

public class SplashTable extends Table
{
    private Game game;
    private AssetManager assets;
    private Drawable background;
    private BitmapFont titleFont;
    private BitmapFont loadingFont;
    
    public SplashTable(Game game, AssetManager assets)
    {
        ArgCheck.notNull(game);
        ArgCheck.notNull(assets);
        
        this.game = game;
        this.assets = assets;
        
        // Loader for ttf
        FileHandleResolver resolver = new InternalFileHandleResolver();
        assets.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assets.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        // Load splash assets first to display something asap
        loadAssets("splash.json");
        
        // Then load everything else
        loadAssets("fonts.json");
        loadAssets("characters.json");
    }
    
    private void loadAssets(String packageFile)
    {
        Json json = new Json();
        AssetPackage assetPackage = json.fromJson(AssetPackage.class, Gdx.files.internal(packageFile));
        assetPackage.load(assets);
    }
    
    @Override
    public void act(float delta)
    {
        if(assets.update())
        {
            // Show new game / continue button
        }
        else
        {
            if(assets.isLoaded("images/splash.png") && background == null)
            {
                background = new TextureRegionDrawable(new TextureRegion(assets.get("images/splash.png", Texture.class)));
                setBackground(background);
                System.out.println("Background set");
            }
            
            if(assets.isLoaded("splash_title.ttf") && titleFont == null)
            {
                titleFont = assets.get("splash_title.ttf", BitmapFont.class);
            }
            
            if(assets.isLoaded("splash_loading.ttf") && loadingFont == null)
            {
                loadingFont = assets.get("splash_loading.ttf", BitmapFont.class);
            }
        }
    }
    
}
