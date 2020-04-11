package game;

import skengine2.core.GameEngine;
import skengine2.utils.Debug;


public class GameManager {
    public static void main(String args[]) {

        Debug debug = new Debug();
        GameEngine gc = new GameEngine(new SceneController());//new ScenesController());
        gc.start();


    }
}
