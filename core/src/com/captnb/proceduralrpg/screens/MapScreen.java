package com.captnb.proceduralrpg.screens;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.captnb.proceduralrpg.OpenSimplexNoise;

public class MapScreen extends AbstractScreen
{
    private class ColorMap
    {
        private class ColorPair
        {
            private Color low;
            private Color high;
            
            public ColorPair(Color low, Color high)
            {
                this.low = low;
                this.high = high;
            }
            
            public Color interpolate(double ratio)
            {
                Color color = new Color(low);
                return color.lerp(high, (float)ratio);
            }
        }
        
        private TreeMap<Double, ColorPair> colorMap = new TreeMap<Double, ColorPair>();
        
        public void addColorLevel(double level, Color low, Color high)
        {
            colorMap.put(level, new ColorPair(low, high));
        }
        
        public Color getColor(double level)
        {
            Color color = Color.BLACK;
            
            Map.Entry<Double, ColorPair> floorPair = colorMap.floorEntry(level);
            Map.Entry<Double, ColorPair> ceilPair = colorMap.ceilingEntry(level);
            ColorPair pair;
            double ratio;
            
            if(ceilPair != null)
            {
                pair = ceilPair.getValue();
                
                if(floorPair != null)
                {
                    ratio = (level - floorPair.getKey()) / (ceilPair.getKey() - floorPair.getKey());
                }
                else
                {
                    ratio = level / ceilPair.getKey();
                }
                
                color = pair.interpolate(ratio);
            }
            
            return color;
        }

    }
    
    private int seed;
    private OpenSimplexNoise terrainMap;
    private OpenSimplexNoise hostilityMap;
    private ColorMap colorMap;
    private ColorMap grayScale;
    private Texture texture;
    private TextureRegion region;
    
    private boolean showTerrain = true;
    private boolean showHostility = true;
    private int zoom = 1;
    private double scale = 0.01;

    public MapScreen(Game game, AssetManager assets)
    {
        super(game, assets);
        
        grayScale = new ColorMap();
        grayScale.addColorLevel(1.0, Color.BLACK, Color.WHITE);
        
        colorMap = new ColorMap();
        colorMap.addColorLevel(0.30, new Color(0x011F4BFF), new Color(0x005B96FF));
        colorMap.addColorLevel(0.35, new Color(0xD7C797FF), new Color(0xA47C48FF));
        colorMap.addColorLevel(0.60, new Color(0x789A5FFF), new Color(0x416624FF));
        colorMap.addColorLevel(0.75, new Color(0x605245FF), new Color(0x766E6EFF));
        colorMap.addColorLevel(1.00, new Color(0xCECECEFF), new Color(0xFFFFFFFF));
        
        generateMap();
    }

    @Override
    protected void build()
    {
        addListener(new ClickListener()
            {
                @Override
                public void clicked (InputEvent event, float x, float y)
                {
                    generateMap();
                }
            }
        );
        addListener(new InputListener()
            {
                @Override
                public boolean keyUp(InputEvent event, int keycode)
                {
                    switch(keycode)
                    {
                    case Input.Keys.I:
                        scale /= 2.0;
                        fillPixmap();
                        return true;
                    case Input.Keys.D:
                        scale *= 2.0;
                        fillPixmap();
                        return true;
                    case Input.Keys.T:
                        showTerrain = !showTerrain;
                        fillPixmap();
                        return true;
                    case Input.Keys.H:
                        showHostility = !showHostility;
                        fillPixmap();
                        return true;
                    case Input.Keys.PLUS:
                        if(zoom <= 32)
                        {
                            zoom *= 2;
                            rescale();
                        }
                        return true;
                    case Input.Keys.MINUS:
                        if(zoom >= 2)
                        {
                            zoom /= 2;
                            rescale();
                        }
                        return true;
                    default:
                        return false;
                    }
                }
            }
        );
    }

//    @Override
//    public void draw()
//    {
//        if(zoom > 1)
//        {
//            batch.draw(region, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        }
//        else
//        {
//            batch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        }
//    }
    
    private void generateMap()
    {
        Random rdm = new Random();
        terrainMap = new OpenSimplexNoise(rdm.nextInt());
        hostilityMap = new OpenSimplexNoise(rdm.nextInt());
        
        fillPixmap();
    }
    
    private void fillPixmap()
    {
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        for(int x = 0; x < pixmap.getWidth(); ++x)
        {
            for(int y = 0; y < pixmap.getHeight(); ++y)
            {
                double hostility = hostilityMap.eval_octaves(x, y, scale / 2.0, 4, 0.5);
                double terrain = terrainMap.eval_octaves(x, y, scale, 8, 0.5);
                terrain *= terrain;
                int color = getColor(hostility, terrain);
                pixmap.drawPixel(x, y, color);
            }
        }
        texture = new Texture(pixmap);
        pixmap.dispose();
        
        rescale();
    }
    
    private void rescale()
    {
        int w = texture.getWidth() / zoom;
        int h = texture.getHeight() / zoom;
        int x = (texture.getWidth() - w) / 2;
        int y = (texture.getHeight() - h) / 2;
        
        region = new TextureRegion(texture, x, y, w, h);
    }
    
    private int getColor(double hostility, double terrain)
    {
        Color color = Color.BLACK;
        if(showTerrain)
        {
            color = colorMap.getColor(terrain);
            
            if(showHostility)
            {
                double tint = 0.5 + ((1.0 - hostility) / 2.0);
                color = color.mul((float)tint);             
            }
        }
        else
        {
            if(showHostility)
            {
                color = grayScale.getColor(hostility);
            }
            else
            {
                color = grayScale.getColor(terrain);
            }
        }
        
        return Color.rgba8888(color);
    }
    
}
