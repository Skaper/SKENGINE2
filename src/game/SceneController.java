package game;

import core.GameEngine;
import core.scene.Scene;
import core.scene.ScenesManager;
import game.Level.GameScene;

public class SceneController extends ScenesManager {
    @Override
    public void setup(GameEngine engine) {
        Scene gameScene = new GameScene();
        addScene("game", gameScene);
        setMainScene("game");
    }
}