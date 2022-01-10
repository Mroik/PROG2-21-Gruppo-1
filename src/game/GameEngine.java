package game;

import java.awt.event.KeyEvent;

import entities.Player;

public class GameEngine {

    private MainFrame mf;

    private Player player;
    
    public GameEngine(MainFrame mf) {
        this.mf = mf;

        gameSetup();
    }

    private void gameSetup() {
        player = new Player(mf.getMainWindow().getCols() / 2, mf.getMainWindow().getRows() / 2);

        mf.getMainWindow().updateLevel(player);
        mf.getMainWindow().renderWindow();
    }

    public void GameLoop(KeyEvent e) {
        mf.getMainWindow().clearLevel();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                moveUp();
                break;
            
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
            
            case KeyEvent.VK_RIGHT:
                moveRight();
                break;

            case KeyEvent.VK_LEFT:
                moveLeft();
                break;
        
            default:
                System.out.println("Not typed an arrow key: " + e.getKeyCode() + " = <" + e.getKeyChar() + ">");
                break;
        }

        mf.getMainWindow().updateLevel(player);

        mf.getMainWindow().renderWindow();
    }

    private void moveUp() {
        player.moveUp();
    }

    private void moveDown() {
        player.moveDown();
    }

    private void moveRight() {
        player.moveRight();
    }

    private void moveLeft() {
        player.moveLeft();
    }

}
