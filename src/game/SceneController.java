package game;

import skengine2.core.GameEngine;
import skengine2.scene.Scene;
import skengine2.scene.ScenesManager;
import game.Level.GameScene;

public class SceneController extends ScenesManager {
    @Override
    public void setup(GameEngine engine) {
        Scene gameScene = new GameScene();
        addScene("game", gameScene);
        setMainScene("game");
    }
}