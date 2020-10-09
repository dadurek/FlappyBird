package com.dadurek.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {

    private Texture topTube, botTube;
    private Vector2 posTopTube, posBotTube;
    private Random random;

    public Tube() {
        topTube = new Texture("toptube.png");
        botTube = new Texture("bottomtube.png");
        random = new Random();


    }
}
