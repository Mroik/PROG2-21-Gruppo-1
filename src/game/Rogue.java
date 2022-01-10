package game;

import base_classes.Settings;

public class Rogue {
    
    public Rogue() {

        MainFrame mf = new MainFrame(Settings.GAME_TITLE, 60, 240);

        GameEngine ge = new GameEngine(mf);

        mf.setGameEngine(ge);

        mf.startGameLoop();

    }
    
    //
    // TEST AND DEBUG
    //
    public static void main(String[] args) {
        new Rogue();
    }
}
