package Sceneries;

import javafx.scene.Scene;

public interface Scenery {
    Scene getScene();
    void setNextScene(Scenery nextScene);
    String getTitle();
    String getName();
}
