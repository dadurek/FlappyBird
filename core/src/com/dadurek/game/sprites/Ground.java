package com.dadurek.game.sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ground {

    private static final int GROUND_OFFSET = - 50;

    private final Texture ground;
    private final Vector2 posGround1;
    private final Vector2 posGround2;
    private final Rectangle bounds1;
    private final Rectangle bounds2;
    private final OrthographicCamera cam;


    public Ground(OrthographicCamera camera){
        cam = camera;
        ground = new Texture("ground.png");
        posGround1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_OFFSET);
        posGround2 = new Vector2((cam.position.x - cam.viewportWidth /2) + ground.getWidth(), GROUND_OFFSET);
        bounds1 = new Rectangle(cam.position.x - cam.viewportWidth / 2, GROUND_OFFSET,ground.getWidth(),ground.getHeight());
        bounds2 = new Rectangle((cam.position.x - cam.viewportWidth /2) + ground.getWidth(), GROUND_OFFSET,ground.getWidth(),ground.getHeight());

    }

    public void updateGround(){
       if(cam.position.x - (cam.viewportWidth / 2) > posGround1.x + ground.getWidth()){
           posGround1.add(ground.getWidth() * 2,0);
           bounds1.setPosition(ground.getWidth() * 2,0);
       }
        if(cam.position.x - (cam.viewportWidth / 2) > posGround2.x + ground.getWidth()){
            posGround2.add(ground.getWidth() * 2,0);
            bounds2.setPosition(ground.getWidth() * 2,0);
        }
    }

    public boolean overlaps(Rectangle rectangle){
        return bounds1.overlaps(rectangle) || bounds2.overlaps(rectangle);
    }

    public void dispose(){
        ground.dispose();
    }

    public Texture getGround() {
        return ground;
    }

    public Vector2 getPosGround1() {
        return posGround1;
    }

    public Vector2 getPosGround2() {
        return posGround2;
    }
}
