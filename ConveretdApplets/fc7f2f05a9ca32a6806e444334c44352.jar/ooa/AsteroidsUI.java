// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.applet.Applet;

public class AsteroidsUI extends Applet implements Runnable
{
    protected Thread controllerThread;
    protected AsteroidsGame asteroidsGame;
    protected AsteroidsDemo asteroidsDemo;
    protected SoundManager soundManager;
    protected boolean playingGame;
    protected boolean quitGame;
    
    public void start() {
        System.out.println("Andrews Asteroids - Copyright Andrew Reid 2001/2002");
        Thread.currentThread().setName("Andrews Asteroids User Interface Main Thread");
        this.addKeyListener(new UIKeyAdapter());
        this.quitGame = false;
        this.asteroidsGame = new AsteroidsGame(this);
        this.asteroidsDemo = new AsteroidsDemo(this);
        this.soundManager = new SoundManager(this);
        (this.controllerThread = new Thread(this)).setName("Andrews Asteroids Game Controller Thread");
        this.controllerThread.start();
    }
    
    public void stop() {
        this.quitGame = true;
        this.asteroidsGame.quitGame = true;
        this.asteroidsDemo.quitGame = true;
        try {
            this.soundManager.soundManagerThread.join();
            this.controllerThread.join();
        }
        catch (InterruptedException e) {
            System.out.println("Abnormal termination -- interrupted before SoundManager and Controller Threads joined");
        }
    }
    
    public void run() {
        do {
            this.playingGame = false;
            this.asteroidsDemo.run();
            if (this.quitGame) {
                break;
            }
            this.playingGame = true;
            this.asteroidsGame.run();
        } while (!this.quitGame);
    }
    
    class UIKeyAdapter extends KeyAdapter
    {
        public void keyPressed(final KeyEvent e) {
            if (AsteroidsUI.this.playingGame) {
                switch (Character.toLowerCase(e.getKeyChar())) {
                    case 'z': {
                        final AsteroidsGame asteroidsGame = AsteroidsUI.this.asteroidsGame;
                        final AsteroidsUI this$0 = AsteroidsUI.this;
                        asteroidsGame.setUIInput(1);
                        break;
                    }
                    case 'x': {
                        final AsteroidsGame asteroidsGame2 = AsteroidsUI.this.asteroidsGame;
                        final AsteroidsUI this$2 = AsteroidsUI.this;
                        asteroidsGame2.setUIInput(2);
                        break;
                    }
                    case 'm': {
                        final AsteroidsGame asteroidsGame3 = AsteroidsUI.this.asteroidsGame;
                        final AsteroidsUI this$3 = AsteroidsUI.this;
                        asteroidsGame3.setUIInput(6);
                        break;
                    }
                    case 'l': {
                        final AsteroidsGame asteroidsGame4 = AsteroidsUI.this.asteroidsGame;
                        final AsteroidsUI this$4 = AsteroidsUI.this;
                        asteroidsGame4.setUIInput(12);
                        break;
                    }
                    case '!': {
                        AsteroidsUI.this.asteroidsGame.spawnEnemyShip();
                        break;
                    }
                    case '@': {
                        AsteroidsUI.this.asteroidsGame.addGameObject(new Powerup(new HighPrecisionPoint(400.0, 300.0)));
                        break;
                    }
                    case '~': {
                        if (AsteroidsUI.this.asteroidsGame.playerShip != null) {
                            AsteroidsUI.this.asteroidsGame.playerShip.missileGuidanceActive = true;
                            break;
                        }
                        break;
                    }
                }
            }
        }
        
        public void keyReleased(final KeyEvent e) {
            if (AsteroidsUI.this.playingGame) {
                switch (Character.toLowerCase(e.getKeyChar())) {
                    case 'z': {
                        final AsteroidsGame asteroidsGame = AsteroidsUI.this.asteroidsGame;
                        final AsteroidsUI this$0 = AsteroidsUI.this;
                        asteroidsGame.setUIInput(3);
                        break;
                    }
                    case 'x': {
                        final AsteroidsGame asteroidsGame2 = AsteroidsUI.this.asteroidsGame;
                        final AsteroidsUI this$2 = AsteroidsUI.this;
                        asteroidsGame2.setUIInput(4);
                        break;
                    }
                    case 'm': {
                        final AsteroidsGame asteroidsGame3 = AsteroidsUI.this.asteroidsGame;
                        final AsteroidsUI this$3 = AsteroidsUI.this;
                        asteroidsGame3.setUIInput(7);
                        break;
                    }
                    case 'k': {
                        final AsteroidsGame asteroidsGame4 = AsteroidsUI.this.asteroidsGame;
                        final AsteroidsUI this$4 = AsteroidsUI.this;
                        asteroidsGame4.setUIInput(8);
                        break;
                    }
                    case 'l': {
                        final AsteroidsGame asteroidsGame5 = AsteroidsUI.this.asteroidsGame;
                        final AsteroidsUI this$5 = AsteroidsUI.this;
                        asteroidsGame5.setUIInput(10);
                        break;
                    }
                    case 'p': {
                        final AsteroidsGame asteroidsGame6 = AsteroidsUI.this.asteroidsGame;
                        final AsteroidsUI this$6 = AsteroidsUI.this;
                        asteroidsGame6.setUIInput(50);
                        break;
                    }
                    case 's': {
                        AsteroidsUI.this.soundManager.toggleSpeaker();
                        break;
                    }
                }
            }
            else {
                switch (Character.toLowerCase(e.getKeyChar())) {
                    case ' ': {
                        if (AsteroidsUI.this.soundManager.soundLoadComplete) {
                            AsteroidsUI.this.asteroidsDemo.quitGame = true;
                            break;
                        }
                        break;
                    }
                    case 's': {
                        AsteroidsUI.this.soundManager.toggleSpeaker();
                        break;
                    }
                }
            }
        }
    }
}
