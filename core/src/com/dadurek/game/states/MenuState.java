package com.dadurek.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dadurek.game.FlappyBird;

public class MenuState extends State {

    private final Texture background;
    private final Texture playbtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false,FlappyBird.WIDTH,FlappyBird.HEIGHT);
        background = new Texture("bg.png");
        playbtn = new Texture("playbtn.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayStates(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);
        sb.draw(playbtn, (FlappyBird.WIDTH / 2 - playbtn.getWidth() / 2), FlappyBird.HEIGHT / 2);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playbtn.dispose();
        System.out.println("MenuState disposed!");
    }
}
