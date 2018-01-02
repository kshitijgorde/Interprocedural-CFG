// 
// Decompiled by Procyon v0.5.30
// 

package ooa;

import java.awt.image.ImageObserver;
import java.awt.Color;
import java.util.Vector;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Font;

public class AsteroidsGame
{
    public static final String OOA_RELEASE_NUMBER = "12.1";
    public static final int UI_INPUT_ROTATE_LEFT = 1;
    public static final int UI_INPUT_ROTATE_RIGHT = 2;
    public static final int UI_INPUT_ROTATE_LEFT_STOP = 3;
    public static final int UI_INPUT_ROTATE_RIGHT_STOP = 4;
    public static final int UI_INPUT_ROTATE_STOP = 5;
    public static final int UI_INPUT_THRUST = 6;
    public static final int UI_INPUT_THRUST_STOP = 7;
    public static final int UI_INPUT_FIRE_MISSILE = 8;
    public static final int UI_INPUT_FIRE_MISSILE_STOP = 9;
    public static final int UI_INPUT_FIRE_GUN = 10;
    public static final int UI_INPUT_FIRE_GUN_STOP = 11;
    public static final int UI_INPUT_AUTOFIRE_ON = 12;
    public static final int UI_INPUT_PAUSE_GAME = 50;
    public static final int GAME_SCREEN_WIDTH = 800;
    public static final int GAME_SCREEN_HEIGHT = 600;
    public static final int GAME_SCREEN_EXTRA = 50;
    public static final int PLAYER_SHIP_INTRODUCTION_DELAY = 3000;
    public static final int ROUND_FINISHED_DELAY = 5000;
    public static final int GAME_OVER_DELAY = 5000;
    public static final int EXTRA_LIFE_INTERVAL = 10000;
    protected static final Font smallFont;
    protected static final Font standardFont;
    protected static final Font roundIntroFont;
    protected static final Font biggerStandardFont;
    protected static boolean enableFastJVMSlowdown;
    protected static long playerHighScore;
    protected static Point[] starField;
    protected Graphics gameScreenGraphics;
    protected Image offScreenImage;
    protected Vector collidableObjectList;
    protected Vector nonCollidableObjectList;
    protected AsteroidsUI asteroidsUI;
    protected PlayerShip playerShip;
    protected long objectCount;
    protected long livesRemaining;
    protected long playerScore;
    protected int roundNumber;
    protected long projectilesFired;
    protected long projectilesMissed;
    protected int roundAccuracyPercentage;
    protected int roundAccuracyBonus;
    protected boolean quitGame;
    protected boolean gamePaused;
    protected int fpsCurrent;
    protected int fpsLast;
    protected long nextLifeScore;
    protected long roundIntroTimer;
    protected long playerShipIntroTimer;
    protected long roundEndTimer;
    protected long gameOverTimer;
    
    public AsteroidsGame(final AsteroidsUI a) {
        this.asteroidsUI = a;
        this.offScreenImage = this.asteroidsUI.createImage(800, 600);
        (this.gameScreenGraphics = this.offScreenImage.getGraphics()).setColor(Color.black);
        AsteroidsGame.playerHighScore = 0L;
    }
    
    protected void drawStarField() {
        for (int i = 0; i < 100; ++i) {
            final int starColor = 150 + (int)(Math.random() * 80);
            this.gameScreenGraphics.setColor(new Color(starColor, starColor, starColor));
            this.gameScreenGraphics.drawLine(AsteroidsGame.starField[i].x, AsteroidsGame.starField[i].y, AsteroidsGame.starField[i].x, AsteroidsGame.starField[i].y);
        }
    }
    
    public void run() {
        GameObject.init(this);
        this.livesRemaining = 3L;
        this.playerScore = 0L;
        this.gameOverTimer = 0L;
        this.quitGame = false;
        this.gamePaused = false;
        this.nextLifeScore = 10000L;
        this.roundNumber = 1;
        while (true) {
            this.playGameRound();
            if (this.quitGame || this.gameOverTimer != 0) {
                break;
            }
            ++this.livesRemaining;
            ++this.roundNumber;
        }
        if (this.playerScore > AsteroidsGame.playerHighScore) {
            AsteroidsGame.playerHighScore = this.playerScore;
        }
    }
    
    protected void addGameObject(final GameObject o) {
        if (o instanceof DotObject) {
            this.nonCollidableObjectList.addElement(o);
        }
        else {
            this.collidableObjectList.addElement(o);
        }
    }
    
    protected void spawnBigAsteroid() {
        final int x = (int)(Math.random() * 900) - 50;
        final int y = (int)(Math.random() * 700) - 50;
        final double heading = Math.random() * 360;
        final double speed = Math.random() * 100;
        final double rotationalSpeed = Math.random() * 100 - 50;
        this.addGameObject(new BigAsteroid(new HighPrecisionPoint(x, y), heading, speed, rotationalSpeed));
    }
    
    protected void spawnEnemyShip() {
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
        this.addGameObject(new EnemyShip(new HighPrecisionPoint(x, y)));
    }
    
    protected void setUIInput(final int input) {
        if (input == 50) {
            this.gamePaused = !this.gamePaused;
        }
        else if (this.playerShip != null && this.playerShip.isAlive && !this.gamePaused) {
            this.playerShip.setUIInput(input);
        }
    }
    
    protected void displayFrame(final double gameTickInterval) {
        this.objectCount = 0L;
        int i = 0;
        while (true) {
            Label_0106: {
                try {
                    if (!this.gamePaused) {
                        this.collidableObjectList.elementAt(i).gameTick(gameTickInterval);
                    }
                    if (this.collidableObjectList.elementAt(i).isAlive) {
                        if (this.collidableObjectList.elementAt(i) instanceof EnemyShip || this.collidableObjectList.elementAt(i) instanceof Asteroid) {
                            ++this.objectCount;
                        }
                        this.collidableObjectList.elementAt(i).draw();
                    }
                    break Label_0106;
                }
                catch (IndexOutOfBoundsException e) {
                    Label_0226: {
                        if (!this.gamePaused) {
                            i = 0;
                            while (true) {
                                Label_0179: {
                                    try {
                                        final PolygonGameObject sourceObject = this.collidableObjectList.elementAt(i);
                                        int j = i + 1;
                                        while (true) {
                                            try {
                                                final PolygonGameObject checkObject = this.collidableObjectList.elementAt(j);
                                                sourceObject.checkCollision(checkObject);
                                            }
                                            catch (IndexOutOfBoundsException e2) {
                                                break Label_0179;
                                            }
                                            ++j;
                                        }
                                    }
                                    catch (IndexOutOfBoundsException e) {
                                        i = 0;
                                        while (true) {
                                            try {
                                                if (!this.collidableObjectList.elementAt(i).isAlive) {
                                                    this.collidableObjectList.removeElementAt(i);
                                                }
                                            }
                                            catch (IndexOutOfBoundsException e) {
                                                break Label_0226;
                                            }
                                            ++i;
                                        }
                                        ++i;
                                        continue;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    i = 0;
                    while (true) {
                        try {
                            if (!this.gamePaused) {
                                this.nonCollidableObjectList.elementAt(i).gameTick(gameTickInterval);
                            }
                            if (this.nonCollidableObjectList.elementAt(i).isAlive) {
                                this.nonCollidableObjectList.elementAt(i).draw();
                            }
                            else {
                                this.nonCollidableObjectList.removeElementAt(i);
                            }
                        }
                        catch (IndexOutOfBoundsException e) {
                            break;
                        }
                        ++i;
                    }
                    return;
                    ++i;
                    continue;
                }
            }
            break;
        }
    }
    
    protected void displayCenteredMessage(final Font font, final String message) {
        final int MessagePosX = (800 - this.offScreenImage.getGraphics().getFontMetrics(font).stringWidth(message)) / 2;
        final int MessagePosY = 300;
        this.gameScreenGraphics.setFont(font);
        this.gameScreenGraphics.drawString(message, MessagePosX, MessagePosY);
    }
    
    protected void displayGameInfo() {
        this.gameScreenGraphics.setColor(Color.white);
        if (System.currentTimeMillis() < this.roundIntroTimer) {
            this.displayCenteredMessage(AsteroidsGame.roundIntroFont, "R O U N D   ".concat(String.valueOf(String.valueOf(String.valueOf(this.roundNumber)))));
        }
        else if (this.gameOverTimer != 0) {
            this.displayCenteredMessage(AsteroidsGame.roundIntroFont, "G A M E   O V E R");
        }
        else if (System.currentTimeMillis() < this.roundEndTimer) {
            this.displayCenteredMessage(AsteroidsGame.roundIntroFont, String.valueOf(String.valueOf(new StringBuffer("ACCURACY: ").append(String.valueOf(this.roundAccuracyPercentage)).append("%, BONUS:").append(String.valueOf(this.roundAccuracyBonus)))));
        }
        else if (this.gamePaused) {
            this.displayCenteredMessage(AsteroidsGame.roundIntroFont, "P A U S E D");
        }
        if (this.fpsCurrent < 30 && this.fpsCurrent > 0) {
            this.gameScreenGraphics.setFont(AsteroidsGame.smallFont);
            this.gameScreenGraphics.drawString(String.valueOf(String.valueOf(new StringBuffer("Poor frame rate: ").append(String.valueOf(this.fpsCurrent)).append(" fps."))), 5, 20);
        }
        else if (Math.abs(this.fpsCurrent - this.fpsLast) > 5) {
            this.gameScreenGraphics.setFont(AsteroidsGame.smallFont);
            this.gameScreenGraphics.drawString(String.valueOf(String.valueOf(new StringBuffer("Unpredictable frame rate: ").append(String.valueOf(this.fpsCurrent)).append(" fps."))), 5, 20);
        }
        this.gameScreenGraphics.setFont(AsteroidsGame.standardFont);
        if (this.playerShip != null) {
            this.gameScreenGraphics.drawString(String.valueOf(String.valueOf(new StringBuffer("R").append(String.valueOf(this.roundNumber)).append(" / L").append(String.valueOf(this.livesRemaining)).append(" / M").append(String.valueOf(this.playerShip.missilesRemaining)).append(" / O").append(String.valueOf(this.objectCount)))), 5, 590);
            this.gameScreenGraphics.drawString("E", 100, 590);
            this.gameScreenGraphics.drawRect(110, 580, 50, 10);
            this.gameScreenGraphics.fillRect(110, 580, (int)(50 * this.playerShip.shipEnergy / 100.0), 10);
            this.gameScreenGraphics.drawString("S", 170, 590);
            this.gameScreenGraphics.drawRect(180, 580, 50, 10);
            this.gameScreenGraphics.fillRect(180, 580, 50 * this.playerShip.healthLevel / this.playerShip.maxHealthLevel, 10);
            this.gameScreenGraphics.drawString(String.valueOf(this.playerScore), 700, 590);
        }
    }
    
    protected void playGameRound() {
        double gameTickInterval = 0.001;
        int fpsCount = 0;
        long fpsLastMarkTime = System.currentTimeMillis();
        this.collidableObjectList = new Vector();
        this.nonCollidableObjectList = new Vector();
        this.playerShip = null;
        this.objectCount = 0L;
        this.projectilesFired = 0L;
        this.projectilesMissed = 0L;
        this.roundAccuracyPercentage = 0;
        this.roundAccuracyBonus = 0;
        this.roundIntroTimer = System.currentTimeMillis() + 3000;
        this.roundEndTimer = 0L;
        long enemyShipTriggerTimer = System.currentTimeMillis() + (long)(Math.random() * 60000);
        int enemyShipCount = 0;
        for (int i = 0; i < this.roundNumber + 1; ++i) {
            this.spawnBigAsteroid();
        }
        while (true) {
            if (System.currentTimeMillis() - fpsLastMarkTime > 1000) {
                fpsLastMarkTime = System.currentTimeMillis();
                this.fpsLast = this.fpsCurrent;
                this.fpsCurrent = fpsCount;
                fpsCount = 0;
            }
            else {
                ++fpsCount;
            }
            if (this.playerScore >= this.nextLifeScore) {
                ++this.livesRemaining;
                this.asteroidsUI.soundManager.playPowerupSound();
                this.nextLifeScore += 10000;
            }
            this.gameScreenGraphics.setColor(Color.black);
            this.gameScreenGraphics.fillRect(0, 0, 800, 600);
            this.drawStarField();
            if (this.playerShip == null || !this.playerShip.isAlive) {
                this.spawnPlayerShip();
            }
            if (System.currentTimeMillis() > enemyShipTriggerTimer && enemyShipCount < this.roundNumber && this.objectCount > 0) {
                this.spawnEnemyShip();
                ++enemyShipCount;
                enemyShipTriggerTimer = 10000 + System.currentTimeMillis() + (long)(Math.random() * 60000);
            }
            this.displayFrame(gameTickInterval);
            this.displayGameInfo();
            this.asteroidsUI.getGraphics().drawImage(this.offScreenImage, 0, 0, null);
            if (this.fpsCurrent > 0) {
                gameTickInterval = 1.0 / this.fpsCurrent;
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
            if (this.quitGame) {
                break;
            }
            if (this.gameOverTimer != 0 && System.currentTimeMillis() > this.gameOverTimer) {
                break;
            }
            if (this.objectCount > 0) {
                continue;
            }
            if (this.roundEndTimer == 0) {
                this.roundEndTimer = System.currentTimeMillis() + 5000;
                if (this.projectilesFired > 0) {
                    this.roundAccuracyPercentage = Math.round(100 * (this.projectilesFired - this.projectilesMissed) / this.projectilesFired);
                    if (this.roundAccuracyPercentage >= 70) {
                        this.roundAccuracyBonus = this.roundAccuracyPercentage * 10;
                    }
                    else {
                        this.roundAccuracyBonus = 0;
                    }
                    this.playerScore += this.roundAccuracyBonus;
                }
                else {
                    this.roundAccuracyPercentage = 0;
                    this.roundAccuracyBonus = 0;
                }
            }
            else {
                if (System.currentTimeMillis() > this.roundEndTimer && !this.gamePaused) {
                    break;
                }
                continue;
            }
        }
        this.asteroidsUI.soundManager.stopPlayingAllSounds();
    }
    
    protected void spawnPlayerShip() {
        if (!this.gamePaused) {
            if (this.livesRemaining > 0) {
                if (this.playerShipIntroTimer == 0) {
                    this.playerShipIntroTimer = System.currentTimeMillis() + 3000;
                }
                else if (System.currentTimeMillis() > this.playerShipIntroTimer) {
                    this.addGameObject(this.playerShip = new PlayerShip(new HighPrecisionPoint(400.0, 300.0)));
                    this.playerShipIntroTimer = 0L;
                    --this.livesRemaining;
                }
            }
            else if (this.gameOverTimer == 0) {
                this.gameOverTimer = System.currentTimeMillis() + 5000;
            }
        }
    }
    
    static {
        smallFont = new Font("SansSerif", 0, 10);
        standardFont = new Font("SansSerif", 0, 12);
        roundIntroFont = new Font("SansSerif", 0, 40);
        biggerStandardFont = new Font("Arial", 0, 14);
        AsteroidsGame.enableFastJVMSlowdown = false;
        AsteroidsGame.starField = new Point[100];
        for (int i = 0; i < 100; ++i) {
            AsteroidsGame.starField[i] = new Point((int)(Math.random() * 800), (int)(Math.random() * 600));
        }
    }
}
