package core.objects;

import core.GameEngine;
import core.component.Component;

public interface IObjComponentWorkable {
    void setupComponents(GameEngine engine);
    void updateComponents(GameEngine engine);
    void renderComponents(GameEngine engine);

    void addComponent(Component component);
    void removeComponent(String tag);
    Component findComponent(String tag);
}
