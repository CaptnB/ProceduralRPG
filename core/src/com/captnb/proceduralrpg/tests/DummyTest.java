package com.captnb.proceduralrpg.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.captnb.proceduralrpg.assets.FontAsset;

public class DummyTest
{

    @Test
    public void testToString()
    {
        FontAsset test = new FontAsset();
        assertTrue(test.toString().length() > 0);
    }

}
