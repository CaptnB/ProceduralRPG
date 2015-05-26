package com.captnb.proceduralrpg.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;

public class FontAsset
{
    protected String file;
    protected String name;
    protected int size;
    protected float r;
    protected float g;
    protected float b;
    protected float a;

    public void load(AssetManager assets)
    {
        FreeTypeFontLoaderParameter param = new FreeTypeFontLoaderParameter();
        param.fontFileName = file;
        param.fontParameters.size = size;
        param.fontParameters.color = new Color(r, g, b, a);

        assets.load(name, BitmapFont.class, param);
    }

    @Override
    public String toString()
    {
        return name + " [file : " + file + ", size : " + size + ", color : (" + r
                + ", " + g + ", " + b + ", " + a + ")]";
    }
}
