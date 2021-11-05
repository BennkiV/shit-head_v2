package com.mygdx.shithead;

import com.badlogic.gdx.InputProcessor;

import javax.smartcardio.Card;

public class CardCollisionDetection implements InputProcessor {
    // Keyboard events
    public boolean keyDown (int keycode) {
        return false;
    }

    public boolean keyUp (int keycode) {
        return false;
    }

    public boolean keyTyped (char character) {
        return false;
    }

    // Mouse and touch events
    public boolean touchDown (int x, int y, int pointer, int button) {


        return true;
    }

    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    public boolean mouseMoved (int x, int y) {
        return false;
    }

    public boolean scrolled (float amountX, float amountY) {
        return false;
    }
}
