// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.util.Vector;

public class AsteroidsDemo extends AsteroidsGame
{
    private long nextTextTimer;
    private int nextTextSequence;
    
    public AsteroidsDemo(final AsteroidsUI a) {
        super(a);
    }
    
    protected void spawnMediumAsteroid() {
        final double heading = Math.random() * 360;
        final double speed = Math.random() * 150;
        final double rotationalSpeed = Math.random() * 180 - 90;
        int x = (int)(Math.random() * 900) - 50;
        int y = (int)(Math.random() * 700) - 50;
        final int z = (int)(Math.random() * 3);
        if (z == 0) {
            x = -49;
        }
        else if (z == 1) {
            x = 849;
        }
        else if (z == 2) {
            y = -49;
        }
        else if (z == 3) {
            y = 649;
        }
        this.addGameObject(new MediumAsteroid(new HighPrecisionPoint(x, y), heading, speed, rotationalSpeed));
    }
    
    protected void spawnSmallAsteroid() {
        final double heading = Math.random() * 360;
        final double speed = Math.random() * 200;
        final double rotationalSpeed = Math.random() * 200 - 100;
        int x = (int)(Math.random() * 900) - 50;
        int y = (int)(Math.random() * 700) - 50;
        final int z = (int)(Math.random() * 3);
        if (z == 0) {
            x = -49;
        }
        else if (z == 1) {
            x = 849;
        }
        else if (z == 2) {
            y = -49;
        }
        else if (z == 3) {
            y = 649;
        }
        this.addGameObject(new SmallAsteroid(new HighPrecisionPoint(x, y), heading, speed, rotationalSpeed));
    }
    
    protected void playGameRound() {
        double gameTickInterval = 0.001;
        int fpsCount = 0;
        long fpsLastMarkTime = System.currentTimeMillis();
        long enemyShipTriggerTimer = 0L;
        super.playerShip = null;
        this.nextTextTimer = System.currentTimeMillis() + 2000;
        this.nextTextSequence = 0;
        super.collidableObjectList = new Vector();
        super.nonCollidableObjectList = new Vector();
        super.roundIntroTimer = System.currentTimeMillis() + 3000;
        super.roundEndTimer = 0L;
        this.spawnBigAsteroid();
        for (int i = 0; i < 2; ++i) {
            this.spawnMediumAsteroid();
        }
        for (int i = 0; i < 2; ++i) {
            this.spawnSmallAsteroid();
        }
        enemyShipTriggerTimer = System.currentTimeMillis() + 15000;
        do {
            if (System.currentTimeMillis() - fpsLastMarkTime > 1000) {
                fpsLastMarkTime = System.currentTimeMillis();
                super.fpsLast = super.fpsCurrent;
                super.fpsCurrent = fpsCount;
                fpsCount = 0;
                if (super.fpsCurrent > 80) {
                    AsteroidsGame.enableFastJVMSlowdown = true;
                    System.out.println("Fast JVM Detected.  Will attempt to introduce a slowdown of 20ms / game frame.");
                }
                else if (super.fpsCurrent < 30 && AsteroidsGame.enableFastJVMSlowdown) {
                    AsteroidsGame.enableFastJVMSlowdown = false;
                    System.out.println("Fast JVM No Longer Detected.  Switching off slowdown.");
                }
            }
            else {
                ++fpsCount;
            }
            if ((super.playerShip == null || !super.playerShip.isAlive) && super.asteroidsUI.soundManager.soundLoadComplete) {
                this.spawnPlayerShip();
            }
            if (super.objectCount < 5) {
                this.spawnMediumAsteroid();
            }
            if (System.currentTimeMillis() > enemyShipTriggerTimer) {
                this.spawnEnemyShip();
                enemyShipTriggerTimer = System.currentTimeMillis() + 15000;
            }
            super.gameScreenGraphics.setColor(Color.black);
            super.gameScreenGraphics.fillRect(0, 0, 800, 600);
            this.drawStarField();
            this.displayFrame(gameTickInterval);
            this.displayDemoText();
            super.asteroidsUI.getGraphics().drawImage(super.offScreenImage, 0, 0, null);
            if (super.fpsCurrent > 0) {
                gameTickInterval = 1.0 / super.fpsCurrent;
            }
            if (AsteroidsGame.enableFastJVMSlowdown) {
                try {
                    Thread.currentThread();
                    Thread.sleep(20L);
                }
                catch (Exception ex) {}
            }
            else {
                Thread.currentThread();
                Thread.yield();
            }
        } while (!super.quitGame);
    }
    
    protected void displayDemoText() {
        super.gameScreenGraphics.setColor(Color.white);
        if (super.fpsCurrent < 30 && super.fpsCurrent > 0) {
            super.gameScreenGraphics.setFont(AsteroidsGame.smallFont);
            super.gameScreenGraphics.drawString(String.valueOf(String.valueOf(new StringBuffer("Poor frame rate: ").append(String.valueOf(super.fpsCurrent)).append(" fps."))), 5, 20);
        }
        else {
            super.gameScreenGraphics.setFont(AsteroidsGame.smallFont);
            super.gameScreenGraphics.drawString(String.valueOf(String.valueOf(new StringBuffer("Good frame rate: ").append(String.valueOf(super.fpsCurrent)).append(" fps."))), 5, 20);
        }
        this.displayCenteredMessage(AsteroidsGame.roundIntroFont, "A N D R E W ' S   A S T E R O I D S", 250);
        this.displayCenteredMessage(AsteroidsGame.smallFont, "Release 12.1 - Copyright Andrew Reid, 2002", 270);
        if (!super.asteroidsUI.soundManager.soundLoadComplete) {
            super.gameScreenGraphics.drawRect(200, 370, 400, 20);
            final Graphics gameScreenGraphics = super.gameScreenGraphics;
            final int n = 200;
            final int n2 = 370;
            final int n3 = 400 * super.asteroidsUI.soundManager.nbrSoundsLoaded;
            final AsteroidsUI asteroidsUI = super.asteroidsUI;
            gameScreenGraphics.fillRect(n, n2, n3 / 7, 20);
            super.gameScreenGraphics.setXORMode(Color.black);
            this.displayCenteredMessage(AsteroidsGame.biggerStandardFont, "Loading Sounds - Please Wait...", 385);
            super.gameScreenGraphics.setPaintMode();
        }
        else {
            if (System.currentTimeMillis() > this.nextTextTimer) {
                this.nextTextTimer = System.currentTimeMillis() + 2000;
                ++this.nextTextSequence;
                if (this.nextTextSequence > 6) {
                    this.nextTextSequence = 0;
                }
            }
            if (this.nextTextSequence == 0) {
                this.displayCenteredMessage(AsteroidsGame.biggerStandardFont, "Z / X = Rotate Ship", 370);
            }
            else if (this.nextTextSequence == 1) {
                this.displayCenteredMessage(AsteroidsGame.biggerStandardFont, "M = Fire Thrusters", 370);
            }
            else if (this.nextTextSequence == 2) {
                this.displayCenteredMessage(AsteroidsGame.biggerStandardFont, "K = Fire Gun", 370);
            }
            else if (this.nextTextSequence == 3) {
                this.displayCenteredMessage(AsteroidsGame.biggerStandardFont, "L = Fire Missile", 370);
            }
            else if (this.nextTextSequence == 4) {
                this.displayCenteredMessage(AsteroidsGame.biggerStandardFont, "S = Toggle Sound On/Off", 370);
            }
            else if (this.nextTextSequence == 5) {
                this.displayCenteredMessage(AsteroidsGame.biggerStandardFont, "P = Pause Game", 370);
            }
            else if (this.nextTextSequence == 6) {
                this.displayCenteredMessage(AsteroidsGame.biggerStandardFont, "High Score:".concat(String.valueOf(String.valueOf(String.valueOf(AsteroidsGame.playerHighScore)))), 370);
            }
            this.displayCenteredMessage(AsteroidsGame.biggerStandardFont, "Hit Space To Start", 450);
        }
        super.gameScreenGraphics.setFont(AsteroidsGame.standardFont);
        if (super.playerShip != null) {
            super.gameScreenGraphics.drawString(String.valueOf(String.valueOf(new StringBuffer("R0 / L0 / M").append(String.valueOf(super.playerShip.missilesRemaining)).append(" / O").append(String.valueOf(super.objectCount)))), 5, 590);
            super.gameScreenGraphics.drawString("E", 100, 590);
            super.gameScreenGraphics.drawRect(110, 580, 50, 10);
            super.gameScreenGraphics.fillRect(110, 580, (int)(50 * super.playerShip.shipEnergy / 100.0), 10);
            super.gameScreenGraphics.drawString("S", 170, 590);
            super.gameScreenGraphics.drawRect(180, 580, 50, 10);
            super.gameScreenGraphics.fillRect(180, 580, 50 * super.playerShip.healthLevel / super.playerShip.maxHealthLevel, 10);
            super.gameScreenGraphics.drawString("DEMO", 700, 590);
        }
    }
    
    protected void displayCenteredMessage(final Font font, final String message, final int MessagePosY) {
        final int MessagePosX = (800 - super.offScreenImage.getGraphics().getFontMetrics(font).stringWidth(message)) / 2;
        super.gameScreenGraphics.setFont(font);
        super.gameScreenGraphics.drawString(message, MessagePosX, MessagePosY);
    }
    
    protected void spawnPlayerShip() {
        if (super.playerShipIntroTimer == 0) {
            super.playerShipIntroTimer = System.currentTimeMillis() + 3000;
        }
        else if (System.currentTimeMillis() > super.playerShipIntroTimer) {
            this.addGameObject(super.playerShip = new DemoPlayerShip(new HighPrecisionPoint(400.0, 300.0)));
            super.playerShipIntroTimer = 0L;
        }
    }
}
