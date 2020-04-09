package game;

import skengine2.core.GameEngine;

public class GameManager {
    public static void main(String args[]) {
        GameEngine gc = new GameEngine(new SceneController());//new ScenesController());
        gc.start();
    }

}
