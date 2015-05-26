package com.captnb.proceduralrpg.assets;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.assets.AssetManager;

public class AssetPackage
{
    protected List<FontAsset> fonts = new ArrayList<FontAsset>();
    protected List<TextureAsset> textures = new ArrayList<TextureAsset>();
    protected List<TextureAtlasAsset> atlases = new ArrayList<TextureAtlasAsset>();
    
    public void load(AssetManager assets)
    {
        for(FontAsset font : fonts)
        {
            font.load(assets);
        }
        
        for(TextureAsset texture : textures)
        {
            texture.load(assets);
        }
        
        for(TextureAtlasAsset atlas : atlases)
        {
            atlas.load(assets);
        }
    }

}
