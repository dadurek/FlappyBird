package com.dadurek.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dadurek.game.FlappyBird;

public class MenuState extends State {

    private Texture background;
    private Texture playbt;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false,FlappyBird.WIDTH,FlappyBird.HEIGHT);
        background = new Texture("bg.png");
        playbt = new Texture("playbtn.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayStates(gsm));
            dispose();
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
        sb.draw(playbt, (FlappyBird.WIDTH / 2 - playbt.getWidth() / 2), FlappyBird.HEIGHT / 2);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playbt.dispose();
    }
}
