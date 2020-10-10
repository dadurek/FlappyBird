package com.dadurek.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {

    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    public static final int TUBE_WIDTH = 52;

    private final Texture topTube;
    private final Texture botTube;
    private final Vector2 posTopTube;
    private final Vector2 posBotTube;
    private final Rectangle boundsTop;
    private final Rectangle boundsBot;
    private final Random random;

    public Tube(float x) {
        topTube = new Texture("toptube.png");
        botTube = new Texture("bottomtube.png");
        random = new Random();

        posTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - botTube.getHeight());
        boundsTop = new Rectangle(posTopTube.x,posTopTube.y,topTube.getWidth(),topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x,posBotTube.y,botTube.getWidth(),botTube.getHeight());
    }

    public void reposition(float x) {
        posTopTube.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - botTube.getHeight());
        boundsTop.setPosition(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        boundsBot.setPosition(x, posTopTube.y - TUBE_GAP - botTube.getHeight());
    }

    public boolean overlaps(Rectangle rectangle){
        return boundsTop.overlaps(rectangle) || boundsBot.overlaps(rectangle);
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBotTube() {
        return botTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public Rectangle getBoundsTop() {
        return boundsTop;
    }

    public Rectangle getBoundsBot() {
        return boundsBot;
    }
}
