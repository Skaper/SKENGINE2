package skengine2.objects;

import skengine2.core.GameEngine;
import skengine2.component.Component;

public interface IObjComponentWorkable {
    void setupComponents(GameEngine engine);
    void updateComponents(GameEngine engine);
    void renderComponents(GameEngine engine);

    void addComponent(Component component);
    void removeComponent(String tag);
    Component findComponent(String tag);
}
