package com.captnb.proceduralrpg.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class TextureAsset
{
    protected String file;

    public void load(AssetManager assets)
    {
        assets.load(file, Texture.class);
    }

}
