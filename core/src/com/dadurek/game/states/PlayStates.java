package com.dadurek.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.dadurek.game.sprites.Bird;
import com.dadurek.game.sprites.Ground;
import com.dadurek.game.sprites.Tube;

import static com.dadurek.game.FlappyBird.HEIGHT;
import static com.dadurek.game.FlappyBird.WIDTH;

public class PlayStates extends State {

    private static final int TUBE_SPACING = 125;
    private static final int START_SPACE = 100;
    private static final int TUBE_COUNT = 4;

    private final Bird bird;
    private final Ground ground;
    private final Texture bg;

    private final Array<Tube> tubes;

    public PlayStates(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        cam.setToOrtho(false, WIDTH / 2, HEIGHT / 2);
        bg = new Texture("bg.png");
        ground = new Ground(cam);
        tubes = new Array<Tube>();
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(START_SPACE + i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;
        for (Tube tube : tubes) {
            if (cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if (tube.overlaps(bird.getBounds())) {
                gsm.set(new MenuState(gsm));
                return;
            }
        }
        if(ground.overlaps(bird.getBounds())){
            gsm.set(new MenuState(gsm));
        }
        ground.updateGround();
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);

        for (Tube tube : tubes) {
            sb.draw(tube.getBotTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
        }

        sb.draw(ground.getGround(), ground.getPosGround1().x, ground.getPosGround1().y);
        sb.draw(ground.getGround(), ground.getPosGround2().x, ground.getPosGround2().y);

//        tubes.forEach(tube->{
//            sb.draw(tube.getBotTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
//            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
//        });

        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for (Tube tube : tubes)
            tube.dispose();
        System.out.println("Play state disposed!");
    }
}
