package com.captnb.proceduralrpg.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TextureAtlasAsset
{
    protected String file;
   
    public void load(AssetManager assets)
    {
        assets.load(file, TextureAtlas.class);
    }

}
