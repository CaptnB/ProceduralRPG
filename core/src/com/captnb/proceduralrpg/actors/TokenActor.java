package com.captnb.proceduralrpg.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class TokenActor extends Actor
{
    Sprite sprite;
    
    public TokenActor(Texture texture)
    {
        this.sprite = new Sprite(texture);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        sprite.draw(batch);
    }

}
