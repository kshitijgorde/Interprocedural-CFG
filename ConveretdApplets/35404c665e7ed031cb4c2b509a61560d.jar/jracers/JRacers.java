// 
// Decompiled by Procyon v0.5.30
// 

package jracers;

import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.applet.Applet;

public class JRacers extends Applet implements KeyListener
{
    protected Graphics buffer;
    protected Image bufferImage;
    protected Graphics statusBuffer;
    protected Image statusBufferImage;
    protected Graphics trackBuffer1;
    protected Image trackImage1;
    protected Graphics trackBuffer2;
    protected Image trackImage2;
    protected int validTrackBuffer;
    protected Applet theApplet;
    protected CGraphicsConstants gfxElements;
    protected CStatusPanel statusPanel;
    protected boolean gameStarted;
    protected CGameThread gameThread;
    protected CLoadingThread loadingThread;
    protected CKickerThread kickerThread;
    protected CRaceTrack track;
    protected CRaceCar car;
    protected boolean movingLeft;
    protected boolean movingRight;
    protected boolean movingUp;
    protected boolean movingDown;
    protected CPickableObject[] fuelCan;
    protected int remainingFuelCanisters;
    protected int canisterDelay;
    protected int lastLap;
    protected CPickableObject[] clock;
    protected CPickableObject[] tire;
    protected CPickableObject[] boost;
    protected CPickableObject[] manhole;
    protected CBike[] bike;
    protected final int maxBikes = 100;
    protected CPickableObject[] drawableObjects;
    protected final int maxDrawableObjects = 100;
    protected int numberDrawableObjects;
    protected CVehicle[] vehicles;
    protected final int maxVehicles = 150;
    protected boolean inGame;
    protected boolean afterGame;
    protected boolean inLoading;
    protected boolean inIntro;
    protected boolean graphicsLoaded;
    protected boolean firstRun;
    protected int afterGameCounter;
    protected int lastScore;
    protected long token;
    protected long maxPaintTime;
    protected boolean validPaintRequest;
    protected boolean gamePaused;
    protected boolean loadingPaintComplete;
    protected Random randomGenerator;
    protected long startTime;
    protected long elapsedTime;
    private String gameMessage;
    private int messageDisplayDelay;
    private boolean gameMessageBlinks;
    private boolean gameMessageIsWarning;
    private boolean gameMessageConstantDisplay;
    
    public JRacers() {
        this.buffer = null;
        this.bufferImage = null;
        this.statusBuffer = null;
        this.statusBufferImage = null;
        this.trackBuffer1 = null;
        this.trackImage1 = null;
        this.trackBuffer2 = null;
        this.trackImage2 = null;
        this.validTrackBuffer = 1;
        this.theApplet = this;
        this.gameStarted = false;
        this.gameThread = null;
        this.loadingThread = null;
        this.kickerThread = null;
        this.remainingFuelCanisters = 0;
        this.canisterDelay = 0;
        this.lastScore = 0;
        this.loadingPaintComplete = false;
        this.graphicsLoaded = false;
        this.inGame = false;
        this.afterGame = false;
        this.inLoading = false;
        this.inIntro = false;
        this.firstRun = true;
    }
    
    public void init() {
    }
    
    public void initGFX() {
        this.inGame = false;
        this.afterGame = false;
        this.inLoading = false;
        this.inIntro = false;
        this.gameMessage = new String("JRacers");
        this.messageDisplayDelay = 0;
        this.gameMessageBlinks = false;
        this.gameMessageIsWarning = false;
        this.gameMessageConstantDisplay = false;
        this.bufferImage = this.createImage(600, 450);
        this.buffer = this.bufferImage.getGraphics();
        this.statusBufferImage = this.createImage(200, 450);
        this.statusBuffer = this.statusBufferImage.getGraphics();
        this.trackImage1 = this.createImage(400, 570);
        this.trackBuffer1 = this.trackImage1.getGraphics();
        this.trackImage2 = this.createImage(400, 570);
        this.trackBuffer2 = this.trackImage2.getGraphics();
        this.validTrackBuffer = 1;
        this.gfxElements.init(this);
    }
    
    public void startGame() {
        this.stopThreads();
        this.loadGame();
        this.inGame = true;
        this.afterGame = false;
        this.inLoading = false;
        this.inIntro = false;
        this.gamePaused = false;
        this.startMainThreads();
    }
    
    public void startIntro() {
        this.stopThreads();
        this.loadGame();
        if (this.gfxElements.playSound) {
            this.gfxElements.introClip.play();
        }
        this.inGame = false;
        this.afterGame = false;
        this.inLoading = false;
        this.inIntro = true;
        this.gamePaused = false;
        this.gfxElements.frameNumber = 0;
        this.startMainThreads();
    }
    
    public void loadGame() {
        this.gamePaused = false;
        this.lastLap = 1;
        for (int i = 0; i < 50; ++i) {
            this.fuelCan[i] = null;
        }
        for (int i = 0; i < 50; ++i) {
            this.tire[i] = null;
        }
        for (int i = 0; i < 50; ++i) {
            this.clock[i] = null;
        }
        for (int i = 0; i < 50; ++i) {
            this.boost[i] = null;
        }
        for (int i = 0; i < 50; ++i) {
            this.manhole[i] = null;
        }
        for (int i = 0; i < 100; ++i) {
            this.bike[i] = null;
        }
        for (int i = 0; i < 100; ++i) {
            this.drawableObjects[i] = null;
        }
        for (int i = 0; i < 150; ++i) {
            this.vehicles[i] = null;
        }
        if (this.track == null) {
            this.track = new CRaceTrack();
        }
        this.track.init(400, 500, this);
        this.track.loadDefaultTrack();
        this.setGameMessage("JRacers!!!", 2);
        if (this.car == null) {
            this.car = new CRaceCar();
        }
        this.movingLeft = false;
        this.movingRight = false;
        this.movingUp = false;
        this.movingDown = false;
        this.car.init(0, 200, 0, this.track, this.gfxElements.carImage, this, this.gfxElements);
        this.remainingFuelCanisters = 0;
        this.canisterDelay = 0;
        this.numberDrawableObjects = 0;
        this.addPickableObjects();
        this.addBike();
        this.validTrackBuffer = 1;
        this.track.drawFull(this.trackBuffer1, this.trackBuffer2, this.validTrackBuffer, 0, this.gfxElements.trackTextureImage, this.theApplet);
    }
    
    public void stopThreads() {
        this.inGame = false;
        this.afterGame = false;
        this.inIntro = false;
        this.inLoading = false;
        while (this.token != 0) {}
    }
    
    public void startMainThreads() {
        this.token = 0L;
        this.maxPaintTime = 0L;
        if (this.gameThread == null) {
            (this.gameThread = new CGameThread()).setPriority(10);
            this.gameThread.start();
        }
        if (this.kickerThread == null) {
            (this.kickerThread = new CKickerThread()).setPriority(10);
            this.kickerThread.start();
        }
    }
    
    public void stop() {
        if (this.gameThread != null) {
            this.gameThread.interrupt();
            this.gameThread = null;
        }
        if (this.loadingThread != null) {
            this.loadingThread.interrupt();
            this.loadingThread = null;
        }
        if (this.kickerThread != null) {
            this.kickerThread.interrupt();
            this.kickerThread = null;
        }
    }
    
    public void destroy() {
        this.stop();
    }
    
    public void start() {
        if (this.firstRun) {
            this.addKeyListener(this);
            this.fuelCan = new CPickableObject[50];
            this.tire = new CPickableObject[50];
            this.clock = new CPickableObject[50];
            this.boost = new CPickableObject[50];
            this.manhole = new CPickableObject[50];
            this.bike = new CBike[100];
            this.drawableObjects = new CPickableObject[100];
            this.vehicles = new CVehicle[150];
            this.gfxElements = new CGraphicsConstants();
            this.statusPanel = new CStatusPanel();
            this.randomGenerator = new Random();
            this.firstRun = false;
        }
        if (!this.graphicsLoaded) {
            this.initGFX();
            this.inLoading = true;
            (this.loadingThread = new CLoadingThread()).start();
        }
        else {
            this.startIntro();
        }
    }
    
    public void keyReleased(final KeyEvent e) {
        final int key = e.getKeyCode();
        if (this.inGame) {
            switch (key) {
                case 37: {
                    this.movingLeft = false;
                    break;
                }
                case 39: {
                    this.movingRight = false;
                    break;
                }
                case 38: {
                    this.movingUp = false;
                    break;
                }
                case 40: {
                    this.movingDown = false;
                    break;
                }
                case 80: {
                    this.gamePaused = !this.gamePaused;
                    this.gfxElements.stopSounds();
                    this.repaint();
                    break;
                }
                case 82: {
                    this.startGame();
                    break;
                }
                case 83: {
                    if (!(this.gfxElements.playSound = !this.gfxElements.playSound)) {
                        this.gfxElements.stopSounds();
                        break;
                    }
                    break;
                }
            }
        }
        if (this.inIntro || this.afterGame) {
            switch (key) {
                case 82: {
                    this.startGame();
                    break;
                }
                case 83: {
                    if (!(this.gfxElements.playSound = !this.gfxElements.playSound)) {
                        this.gfxElements.stopSounds();
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    public void keyTyped(final KeyEvent e) {
    }
    
    public void keyPressed(final KeyEvent e) {
        final int key = e.getKeyCode();
        if (this.inGame) {
            switch (key) {
                case 112: {}
                case 113: {}
                case 37: {
                    this.movingLeft = true;
                    break;
                }
                case 39: {
                    this.movingRight = true;
                    break;
                }
                case 38: {
                    this.movingUp = true;
                    break;
                }
                case 40: {
                    this.movingDown = true;
                    break;
                }
            }
        }
    }
    
    public void addDrawableObject(final CPickableObject object) {
        int i;
        for (i = 0; i < 100 && this.drawableObjects[i] != null; ++i) {}
        if (i < 100) {
            this.drawableObjects[i] = object;
            ++this.numberDrawableObjects;
        }
    }
    
    public void addVehicle(final CVehicle object) {
        int i;
        for (i = 0; i < 150 && this.vehicles[i] != null; ++i) {}
        if (i < 150) {
            this.vehicles[i] = object;
        }
    }
    
    public void reactivateCanisters() {
        for (int i = 0; i < 50; ++i) {
            if (this.fuelCan[i] != null) {
                this.fuelCan[i].activate();
                ++this.remainingFuelCanisters;
            }
        }
    }
    
    public boolean gameIsOver() {
        return (this.car.outOfGas() || this.car.tireBlown() || this.car.getRemainingTime() == 0 || this.car.inLap() == 3) && this.car.getSpeed() == 0;
    }
    
    public void addPickableObjects() {
        this.remainingFuelCanisters = 0;
        for (int i = 0; i < this.track.numberFuelCans; ++i) {
            if (this.fuelCan[i] == null) {
                this.fuelCan[i] = new CPickableObject();
            }
            this.fuelCan[i].init(this.track.fuelCans[i].xPosition, this.track.fuelCans[i].trackPosition, this.gfxElements.canisterImage, this, this.track.fuelCans[i].validAsOfTrack, 0);
            this.fuelCan[i].activate();
            this.addDrawableObject(this.fuelCan[i]);
            ++this.remainingFuelCanisters;
        }
        for (int i = 0; i < this.track.numberClocks; ++i) {
            if (this.clock[i] == null) {
                this.clock[i] = new CPickableObject();
            }
            this.clock[i].init(this.track.clocks[i].xPosition, this.track.clocks[i].trackPosition, this.gfxElements.clockImage, this, this.track.clocks[i].validAsOfTrack, 2);
            this.clock[i].activate();
            this.addDrawableObject(this.clock[i]);
        }
        for (int i = 0; i < this.track.numberTires; ++i) {
            if (this.tire[i] == null) {
                this.tire[i] = new CPickableObject();
            }
            this.tire[i].init(this.track.tires[i].xPosition, this.track.tires[i].trackPosition, this.gfxElements.tiresImage, this, this.track.tires[i].validAsOfTrack, 1);
            this.tire[i].activate();
            this.addDrawableObject(this.tire[i]);
        }
        for (int i = 0; i < this.track.numberBoosts; ++i) {
            if (this.boost[i] == null) {
                this.boost[i] = new CPickableObject();
            }
            this.boost[i].init(this.track.boosts[i].xPosition, this.track.boosts[i].trackPosition, this.gfxElements.boostImage, this, this.track.boosts[i].validAsOfTrack, 3);
            this.boost[i].activate();
            this.addDrawableObject(this.boost[i]);
        }
        for (int i = 0; i < this.track.numberManholes; ++i) {
            if (this.manhole[i] == null) {
                this.manhole[i] = new CPickableObject();
            }
            this.manhole[i].init(this.track.manholes[i].xPosition, this.track.manholes[i].trackPosition, this.gfxElements.manholeImage, this, this.track.manholes[i].validAsOfTrack, 4);
            this.manhole[i].activate();
            this.addDrawableObject(this.manhole[i]);
        }
    }
    
    public void addBike() {
        for (int i = 0; i < 60; ++i) {
            if (this.bike[i] == null) {
                this.bike[i] = new CBike();
            }
            final int bikeTrackPosition = 400 + (this.track.getLength() - 4000) / 60 * i;
            this.bike[i].init(this.randomGenerator.nextLong(), 0, this.track.getLeftBorder(bikeTrackPosition) + this.randomGenerator.nextInt(this.track.trackWidth(bikeTrackPosition)), 0, this.track, this.gfxElements.bikeImage, this, bikeTrackPosition, this.gfxElements);
            this.bike[i].bringToBounds();
            this.bike[i].activate();
            this.addVehicle(this.bike[i]);
        }
    }
    
    public void moveVehicles() {
        for (int i = 0; i < 150; ++i) {
            if (this.vehicles[i] != null) {
                if (this.vehicles[i].isActive()) {
                    this.vehicles[i].autoPilot();
                }
                this.vehicles[i].drive();
            }
        }
    }
    
    public void checkVehicleHit() {
        boolean slowDown = false;
        for (int i = 0; i < 150; ++i) {
            if (this.vehicles[i] != null && this.vehicles[i].isVisible(this.track.getPreviousPosition(), 500, this.track.getLength())) {
                final CCollisionCode ccode = this.car.collisionOverlaps(this.vehicles[i], this.track.getLength());
                if (ccode.isAnythingHit()) {
                    if (this.gfxElements.playSound) {
                        this.gfxElements.carRam.play();
                    }
                    this.car.consumeTires(ccode, 1);
                    if (ccode.isLeftHit()) {
                        this.vehicles[i].pushLeft(this.car);
                        this.vehicles[i].collisionSlowDown();
                        slowDown = true;
                    }
                    if (ccode.isRightHit()) {
                        this.vehicles[i].pushRight(this.car);
                        this.vehicles[i].collisionSlowDown();
                        slowDown = true;
                    }
                    if (ccode.isUpperHit()) {
                        this.vehicles[i].pushUp(this.car);
                        slowDown = true;
                    }
                    if (ccode.isLowerHit()) {
                        this.vehicles[i].pushDown(this.car);
                    }
                    if (slowDown) {
                        this.car.collisionSlowDown();
                    }
                }
            }
        }
    }
    
    public void checkVehicleToVehicleHit() {
        boolean slowDown = false;
        boolean undoMove = false;
        for (int k = 0; k < 150; ++k) {
            for (int i = k + 1; i < 150; ++i) {
                if (this.vehicles[i] != null && this.vehicles[k] != null && this.vehicles[i].isVisible(this.track.getPreviousPosition(), 500, this.track.getLength()) && this.vehicles[k].isVisible(this.track.getPreviousPosition(), 500, this.track.getLength())) {
                    final CCollisionCode ccode = this.vehicles[k].collisionOverlaps(this.vehicles[i], this.track.getLength());
                    if (ccode.isAnythingHit()) {
                        if (ccode.isLeftHit()) {
                            this.vehicles[i].pushLeft(this.vehicles[k]);
                            undoMove = true;
                            slowDown = true;
                        }
                        if (ccode.isRightHit()) {
                            this.vehicles[i].pushRight(this.vehicles[k]);
                            undoMove = true;
                            slowDown = true;
                        }
                        if (ccode.isUpperHit()) {
                            this.vehicles[i].pushUp(this.vehicles[k]);
                            slowDown = true;
                        }
                        if (ccode.isLowerHit()) {
                            this.vehicles[i].pushDown(this.vehicles[k]);
                        }
                        if (undoMove) {
                            this.vehicles[i].undoMove();
                        }
                        if (slowDown) {
                            this.vehicles[i].collisionSlowDown();
                            this.vehicles[i].collisionSlowDown();
                        }
                    }
                }
            }
        }
    }
    
    public void checkPickableObjectHit() {
        for (int i = 0; i < this.numberDrawableObjects; ++i) {
            if (this.drawableObjects[i] != null && this.drawableObjects[i].isVisible(this.track.getPreviousPosition(), 500, this.track.getLength(), this.car.inLap())) {
                switch (this.drawableObjects[i].type) {
                    case 0: {
                        if (this.car.overlaps(this.drawableObjects[i])) {
                            if (this.gfxElements.playSound) {
                                this.gfxElements.refuel.play();
                            }
                            this.car.canisterHit();
                            this.drawableObjects[i].deactivate();
                            --this.remainingFuelCanisters;
                            this.setGameMessage("REFUEL", 2);
                            break;
                        }
                        break;
                    }
                    case 3: {
                        if (this.car.overlaps(this.drawableObjects[i])) {
                            if (this.gfxElements.playSound) {
                                this.gfxElements.boost.play();
                            }
                            this.car.activateBoost(300);
                            this.drawableObjects[i].deactivate();
                            this.setGameMessage(">BOOOOOOST<", 10);
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if (this.car.overlaps(this.drawableObjects[i])) {
                            if (this.gfxElements.playSound) {
                                this.gfxElements.refuel.play();
                            }
                            this.car.addTime(150);
                            this.drawableObjects[i].deactivate();
                            this.setGameMessage("+ 5 seconds", 2);
                            break;
                        }
                        break;
                    }
                    case 4: {
                        if (this.car.getSpeed() == 0) {
                            break;
                        }
                        final CCollisionCode ccode = this.car.collisionOverlaps(this.drawableObjects[i], this.track.getLength());
                        this.car.consumeTires(ccode, 3);
                        if (!ccode.isAnythingHit()) {
                            break;
                        }
                        this.car.bump();
                        if (this.gfxElements.playSound) {
                            this.gfxElements.manhole.play();
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if (this.car.overlaps(this.drawableObjects[i])) {
                            if (this.gfxElements.playSound) {
                                this.gfxElements.tires.play();
                            }
                            this.car.tireHit();
                            this.drawableObjects[i].deactivate();
                            this.setGameMessage("NEW TIRES", 2);
                            break;
                        }
                        break;
                    }
                }
            }
        }
    }
    
    public void paintDrawableObjects(final Graphics g) {
        for (int i = 0; i < 100; ++i) {
            if (this.drawableObjects[i] != null && this.drawableObjects[i].isVisible(this.track.getPreviousPosition(), 500, this.track.getLength(), this.car.inLap())) {
                this.drawableObjects[i].draw(g, 0, 0, this.track.getPreviousPosition(), this.track.getLength(), this);
            }
        }
    }
    
    public void paintVehicles(final Graphics g) {
        for (int i = 0; i < 150; ++i) {
            if (this.vehicles[i] != null && this.vehicles[i].isVisible(this.track.getPreviousPosition(), 500, this.track.getLength())) {
                this.vehicles[i].draw(g, 0, 0, this.track.getPreviousPosition(), this.track.getLength(), this);
            }
        }
    }
    
    public void paintMessage(final Graphics g) {
        if (this.messageDisplayDelay > 0 || this.gameMessageConstantDisplay) {
            if (this.messageDisplayDelay > 0) {
                --this.messageDisplayDelay;
            }
            if (!this.gameMessageBlinks || this.gfxElements.frameNumber * 2 / 30 % 2 > 0) {
                g.setFont(CGraphicsConstants.FONT_BIGTIME);
                final FontMetrics fm = this.getFontMetrics(CGraphicsConstants.FONT_BIGTIME);
                if (!this.gameMessageIsWarning) {
                    g.setColor(Color.orange);
                }
                else {
                    g.setColor(Color.yellow);
                }
                g.drawString(this.gameMessage, (400 - fm.stringWidth(this.gameMessage)) / 2, 50 + fm.getHeight());
                if (!this.gameMessageIsWarning) {
                    g.setColor(Color.yellow);
                }
                else {
                    g.setColor(Color.orange);
                }
                g.drawString(this.gameMessage, (400 - fm.stringWidth(this.gameMessage)) / 2 - 2, 48 + fm.getHeight());
                if (!this.gameMessageIsWarning) {
                    g.setColor(Color.green);
                }
                else {
                    g.setColor(Color.red);
                }
                g.drawString(this.gameMessage, (400 - fm.stringWidth(this.gameMessage)) / 2 - 4, 46 + fm.getHeight());
            }
        }
    }
    
    public void paintBigGameMessage(final Graphics g, final String s) {
        final FontMetrics fm = this.getFontMetrics(CGraphicsConstants.FONT_BIGTIME);
        g.setFont(CGraphicsConstants.FONT_BIGTIME);
        g.setColor(Color.orange);
        g.drawString(s, 200 + (400 - fm.stringWidth(s)) / 2, (500 + fm.getHeight()) / 2 - 50);
        g.setColor(Color.yellow);
        g.drawString(s, 200 + (400 - fm.stringWidth(s)) / 2 - 2, (500 + fm.getHeight()) / 2 - 52);
        g.setColor(Color.green);
        g.drawString(s, 200 + (400 - fm.stringWidth(s)) / 2 - 4, (500 + fm.getHeight()) / 2 - 54);
    }
    
    public void paintSmallGameMessage(final Graphics g, final String s) {
        final FontMetrics fm = this.getFontMetrics(CGraphicsConstants.FONT_BIGMONOBOLD);
        g.setFont(CGraphicsConstants.FONT_BIGMONOBOLD);
        g.setColor(Color.yellow);
        g.drawString(s, 200 + (400 - fm.stringWidth(s)) / 2, (500 + fm.getHeight()) / 2 - 10);
        g.setColor(Color.orange);
        g.drawString(s, 200 + (400 - fm.stringWidth(s)) / 2 - 1, (500 + fm.getHeight()) / 2 - 11);
        if (this.gfxElements.frameNumber % 2 == 0) {
            g.setColor(Color.red);
        }
        else {
            g.setColor(Color.pink);
        }
        g.drawString(s, 200 + (400 - fm.stringWidth(s)) / 2 - 2, (500 + fm.getHeight()) / 2 - 12);
    }
    
    public void setGameMessage(final String msg, final int time) {
        if (!this.gameMessageConstantDisplay || !this.gameMessageIsWarning) {
            this.gameMessage = msg;
            this.messageDisplayDelay = time * 30;
            this.gameMessageBlinks = true;
            this.gameMessageIsWarning = false;
            this.gameMessageConstantDisplay = false;
            this.gfxElements.frameNumber = 15;
        }
    }
    
    public void setGameWarning() {
        if (this.car.tireBlown()) {
            this.gameMessage = new String("TIRE BLOWN");
            this.gameMessageBlinks = false;
            this.gameMessageIsWarning = true;
            this.gameMessageConstantDisplay = true;
        }
        else if (this.car.outOfGas()) {
            this.gameMessage = new String("OUT OF FUEL");
            this.gameMessageBlinks = false;
            this.gameMessageIsWarning = true;
            this.gameMessageConstantDisplay = true;
        }
        else if ((this.car.getRemainingTime() + 30 - 1) / 30 <= 10) {
            if ((this.car.getRemainingTime() + 30 - 1) / 30 == 0) {
                this.gameMessage = "OUT OF TIME";
            }
            else {
                this.gameMessage = new String(String.valueOf((this.car.getRemainingTime() + 30 - 1) / 30));
            }
            this.messageDisplayDelay = 0;
            this.gameMessageBlinks = false;
            this.gameMessageIsWarning = true;
            this.gameMessageConstantDisplay = true;
        }
        else if (this.car.fuel < 2000 * 0.2) {
            this.gameMessage = new String("LOW ON FUEL");
            this.messageDisplayDelay = 0;
            if (this.car.fuel < 2000 * 0.1) {
                this.gameMessageBlinks = true;
            }
            else {
                this.gameMessageBlinks = false;
            }
            this.gameMessageIsWarning = true;
            this.gameMessageConstantDisplay = true;
        }
        else if (this.car.giveMinTireStatus() < 100 * 0.2) {
            this.gameMessage = new String("TIRES LOW");
            this.gameMessageBlinks = true;
            this.messageDisplayDelay = 0;
            if (this.car.giveMinTireStatus() < 100 * 0.1) {
                this.gameMessageBlinks = true;
            }
            else {
                this.gameMessageBlinks = false;
            }
            this.gameMessageIsWarning = true;
            this.gameMessageConstantDisplay = true;
        }
        else {
            this.gameMessageConstantDisplay = false;
        }
    }
    
    public void gamePaint() {
        this.setGameWarning();
        if (this.lastLap != this.car.inLap()) {
            if (this.car.inLap() == 2) {
                this.setGameMessage("LAST LAP", 2);
            }
            else if (this.car.inLap() == 3) {
                this.setGameMessage("YOU MADE IT!!!", 10);
                if (this.gfxElements.playSound) {
                    this.gfxElements.won.play();
                }
            }
            else {
                this.setGameMessage("EXTENDED PLAY", 2);
            }
        }
        this.statusPanel.drawStatusPanel(this.theApplet, this.gfxElements, this.buffer, this.car, this.track, this.remainingFuelCanisters, this.canisterDelay);
        if (this.validTrackBuffer == 1) {
            this.paintDrawableObjects(this.trackBuffer2);
            this.paintVehicles(this.trackBuffer2);
            if (this.car != null) {
                this.car.draw(this.trackBuffer2, 0, 0, this.track.getPreviousPosition(), this.track.getLength(), this);
            }
            this.paintMessage(this.trackBuffer2);
        }
        else {
            this.paintDrawableObjects(this.trackBuffer1);
            this.paintVehicles(this.trackBuffer1);
            if (this.car != null) {
                this.car.draw(this.trackBuffer1, 0, 0, this.track.getPreviousPosition(), this.track.getLength(), this);
            }
            this.paintMessage(this.trackBuffer1);
        }
        if (this.validTrackBuffer == 1) {
            this.buffer.drawImage(this.trackImage2, 200, 0, 600, 450, 0, 50, 400, 500, this);
        }
        else {
            this.buffer.drawImage(this.trackImage1, 200, 0, 600, 450, 0, 50, 400, 500, this);
        }
    }
    
    public void introPaint() {
        final int secondsInIntro = this.gfxElements.frameNumber / 30;
        this.statusPanel.paintIntroStatus(this.theApplet, this.gfxElements, this.buffer);
        if (this.validTrackBuffer == 1) {
            this.buffer.drawImage(this.trackImage2, 200, 0, 600, 450, 0, 50, 400, 500, this);
        }
        else {
            this.buffer.drawImage(this.trackImage1, 200, 0, 600, 450, 0, 50, 400, 500, this);
        }
        this.paintBigGameMessage(this.buffer, "Game Over");
        if (secondsInIntro < 3) {
            this.paintSmallGameMessage(this.buffer, "Play the full game at");
        }
        else if (secondsInIntro < 8) {
            this.paintSmallGameMessage(this.buffer, "www.lunchbreakgames.com");
        }
        else if (secondsInIntro < 10) {
            this.paintSmallGameMessage(this.buffer, "Press R to Restart");
        }
        else if (secondsInIntro < 15) {
            this.paintSmallGameMessage(this.buffer, "Steer with cursor keys");
        }
        else if (secondsInIntro < 18) {
            this.paintSmallGameMessage(this.buffer, "Last Score: ".concat(String.valueOf(String.valueOf(String.valueOf(this.lastScore)))));
        }
        else {
            this.paintSmallGameMessage(this.buffer, "This is a demo over two laps");
        }
    }
    
    public void loadingPaint() {
        this.buffer.setColor(Color.black);
        this.buffer.fillRect(0, 0, 600, 450);
        final int pc = this.gfxElements.numberLoadedImages / this.gfxElements.numberImages * 100;
        String loading;
        if (pc == 0) {
            loading = "preparing...";
        }
        else if (!this.gfxElements.soundsLoaded) {
            loading = "loading sounds...";
        }
        else {
            loading = String.valueOf(String.valueOf(new StringBuffer("transferring graphics... (").append(pc).append("%)")));
        }
        this.buffer.setFont(CGraphicsConstants.FONT_MONO);
        this.buffer.setColor(Color.gray);
        this.buffer.drawString(loading, 45, 45);
        this.buffer.setColor(Color.white);
        this.buffer.drawString(loading, 44, 44);
        this.buffer.setFont(CGraphicsConstants.FONT_BIGFONT);
        FontMetrics fm = this.getFontMetrics(CGraphicsConstants.FONT_BIGFONT);
        String sg = "JRacers";
        this.buffer.setColor(Color.yellow);
        this.buffer.drawString(sg, (600 - fm.stringWidth(sg)) / 2, (450 - fm.getHeight()) / 2);
        this.buffer.setColor(Color.green);
        this.buffer.drawString(sg, (600 - fm.stringWidth(sg)) / 2 - 2, (450 - fm.getHeight()) / 2 - 2);
        this.buffer.setColor(Color.yellow);
        for (int i = 100; i < 120; ++i) {
            this.buffer.setColor(new Color(100 + (i - 100) * 5, 255, 0));
            this.buffer.drawArc(200 - (i - 100), 80 - (i - 100), i * 2, i * 2, 0, 359 * (1 - (this.gfxElements.numberImages - this.gfxElements.numberLoadedImages) / this.gfxElements.numberImages));
        }
        this.buffer.setFont(CGraphicsConstants.FONT_MONO);
        fm = this.getFontMetrics(CGraphicsConstants.FONT_MONO);
        sg = "Copyright (c) 2003 Tobias Eckert";
        this.buffer.setColor(Color.gray);
        this.buffer.drawString(sg, (600 - fm.stringWidth(sg)) / 2, 450 - fm.getHeight() - 80);
        this.buffer.setColor(Color.white);
        this.buffer.drawString(sg, (600 - fm.stringWidth(sg)) / 2 - 1, 450 - fm.getHeight() - 81);
        this.buffer.setFont(CGraphicsConstants.FONT_BIGMONO);
        fm = this.getFontMetrics(CGraphicsConstants.FONT_BIGMONO);
        sg = "www.LunchBreakGames.com";
        this.buffer.setColor(Color.gray);
        this.buffer.drawString(sg, (600 - fm.stringWidth(sg)) / 2, 450 - fm.getHeight() - 60);
        this.buffer.setColor(Color.white);
        this.buffer.drawString(sg, (600 - fm.stringWidth(sg)) / 2 - 1, 450 - fm.getHeight() - 61);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        if (this.inLoading) {
            this.loadingPaint();
            this.loadingPaintComplete = true;
        }
        if (((!this.inGame && !this.inIntro && !this.afterGame) || this.validPaintRequest) && this.bufferImage != null) {
            g.drawImage(this.bufferImage, 0, 0, this);
        }
        if (this.inGame && this.gamePaused && this.bufferImage != null) {
            g.drawImage(this.bufferImage, 0, 0, this);
            this.paintBigGameMessage(g, "Paused");
        }
    }
    
    class CLoadingThread extends Thread
    {
        public void run() {
            while (!this.isInterrupted()) {
                Label_0168: {
                    if (JRacers.this.gfxElements.numberLoadedImages < JRacers.this.gfxElements.numberImages) {
                        if (JRacers.this.gfxElements.tracker != null && JRacers.this.gfxElements.tracker.checkID(JRacers.this.gfxElements.numberLoadedImages)) {
                            final CGraphicsConstants gfxElements = JRacers.this.gfxElements;
                            ++gfxElements.numberLoadedImages;
                        }
                        JRacers.this.repaint();
                        try {
                            Thread.sleep(100L);
                            break Label_0168;
                        }
                        catch (InterruptedException e) {
                            return;
                        }
                    }
                    JRacers.this.inLoading = false;
                    JRacers.this.graphicsLoaded = true;
                    JRacers.this.statusPanel.paintStatusBackground(JRacers.this.theApplet, JRacers.this.gfxElements, JRacers.this.statusBuffer, JRacers.this.statusBufferImage);
                    JRacers.this.startIntro();
                    this.interrupt();
                }
                if (!JRacers.this.gfxElements.soundsLoaded && JRacers.this.loadingPaintComplete) {
                    JRacers.this.gfxElements.loadSounds();
                }
                else {
                    if (JRacers.this.gfxElements.imagesLoaded || !JRacers.this.gfxElements.soundsLoaded) {
                        continue;
                    }
                    JRacers.this.gfxElements.loadImages();
                }
            }
        }
    }
    
    class CKickerThread extends Thread
    {
        public void run() {
            while (!this.isInterrupted()) {
                if ((JRacers.this.inGame && !JRacers.this.gamePaused) || JRacers.this.inIntro || JRacers.this.afterGame) {
                    final JRacers this$0 = JRacers.this;
                    ++this$0.token;
                }
                try {
                    Thread.sleep(33L);
                    continue;
                }
                catch (InterruptedException e) {
                    return;
                }
                break;
            }
        }
    }
    
    class CGameThread extends Thread
    {
        public void doIntroLoop() {
            JRacers.this.track.scrollUp(JRacers.this.trackBuffer1, JRacers.this.trackBuffer2, JRacers.this.trackImage1, JRacers.this.trackImage2, JRacers.this.validTrackBuffer, JRacers.this.theApplet, 1, JRacers.this.gfxElements.trackTextureImage);
            if (JRacers.this.validTrackBuffer == 1) {
                JRacers.this.validTrackBuffer = 2;
            }
            else {
                JRacers.this.validTrackBuffer = 1;
            }
            JRacers.this.introPaint();
            JRacers.this.validPaintRequest = true;
            JRacers.this.repaint();
        }
        
        public void doGameLoop() {
            if (JRacers.this.gameIsOver() && JRacers.this.inGame) {
                if (JRacers.this.car.inLap() != 3 && JRacers.this.gfxElements.playSound) {
                    JRacers.this.gfxElements.introClip.play();
                }
                JRacers.this.gfxElements.engine.stop();
                JRacers.this.gfxElements.engine2.stop();
                JRacers.this.gfxElements.engine3.stop();
                JRacers.this.gfxElements.engine4.stop();
                JRacers.this.inGame = false;
                JRacers.this.afterGame = true;
                JRacers.this.afterGameCounter = 150;
                JRacers.this.lastScore = JRacers.this.car.getTotalScore();
            }
            else if (JRacers.this.afterGame && JRacers.this.afterGameCounter == 0) {
                JRacers.this.afterGame = false;
                JRacers.this.inIntro = true;
            }
            else if (JRacers.this.afterGame) {
                final JRacers this$0 = JRacers.this;
                --this$0.afterGameCounter;
            }
            if (JRacers.this.car.getScrollSpeed() >= 0) {
                JRacers.this.track.scrollUp(JRacers.this.trackBuffer1, JRacers.this.trackBuffer2, JRacers.this.trackImage1, JRacers.this.trackImage2, JRacers.this.validTrackBuffer, JRacers.this.theApplet, JRacers.this.car.getScrollSpeed(), JRacers.this.gfxElements.trackTextureImage);
                if (JRacers.this.validTrackBuffer == 1) {
                    JRacers.this.validTrackBuffer = 2;
                }
                else {
                    JRacers.this.validTrackBuffer = 1;
                }
            }
            else if (JRacers.this.car.getScrollSpeed() < 0) {
                JRacers.this.track.scrollDown(JRacers.this.trackBuffer1, JRacers.this.trackBuffer2, JRacers.this.trackImage1, JRacers.this.trackImage2, JRacers.this.validTrackBuffer, JRacers.this.theApplet, -JRacers.this.car.getScrollSpeed(), JRacers.this.gfxElements.trackTextureImage);
                if (JRacers.this.validTrackBuffer == 1) {
                    JRacers.this.validTrackBuffer = 2;
                }
                else {
                    JRacers.this.validTrackBuffer = 1;
                }
            }
            JRacers.this.checkPickableObjectHit();
            if (JRacers.this.remainingFuelCanisters == 0 && JRacers.this.canisterDelay == 0) {
                JRacers.this.canisterDelay = 150;
            }
            JRacers.this.gamePaint();
            JRacers.this.validPaintRequest = true;
            JRacers.this.repaint();
            if (JRacers.this.car.inLap() != 3) {
                if (JRacers.this.movingLeft) {
                    JRacers.this.car.moveLeft(JRacers.this.car.getScrollSpeed() / 2);
                }
                if (JRacers.this.movingRight) {
                    JRacers.this.car.moveRight(JRacers.this.car.getScrollSpeed() / 2);
                }
                if (!JRacers.this.movingLeft && !JRacers.this.movingRight) {
                    JRacers.this.car.moveStraight();
                }
                if (JRacers.this.movingUp) {
                    JRacers.this.car.accelarate();
                }
                if (JRacers.this.movingDown) {
                    JRacers.this.car.slowDown();
                }
            }
            JRacers.this.car.bringToBounds();
            JRacers.this.lastLap = JRacers.this.car.inLap();
            if (JRacers.this.remainingFuelCanisters == 0 && JRacers.this.canisterDelay > 0) {
                --JRacers.this.canisterDelay;
                if (JRacers.this.canisterDelay < 0) {
                    JRacers.this.canisterDelay = 0;
                }
            }
            if (JRacers.this.remainingFuelCanisters == 0 && JRacers.this.canisterDelay == 0) {
                JRacers.this.reactivateCanisters();
            }
            JRacers.this.moveVehicles();
            JRacers.this.checkVehicleHit();
            JRacers.this.checkVehicleToVehicleHit();
            JRacers.this.car.drive();
            if (JRacers.this.elapsedTime < 50) {
                JRacers.this.track.setHighDetail();
            }
            else {
                JRacers.this.track.setLowDetail();
            }
        }
        
        public void run() {
            while (!this.isInterrupted()) {
                while (JRacers.this.token == 0) {
                    try {
                        Thread.sleep(5L);
                        continue;
                    }
                    catch (InterruptedException e) {
                        return;
                    }
                    break;
                }
                JRacers.this.startTime = System.currentTimeMillis();
                final CGraphicsConstants gfxElements = JRacers.this.gfxElements;
                ++gfxElements.frameNumber;
                if (JRacers.this.gfxElements.frameNumber > 300000) {
                    JRacers.this.gfxElements.frameNumber = 0;
                }
                if (JRacers.this.inGame || JRacers.this.afterGame) {
                    this.doGameLoop();
                }
                else if (JRacers.this.inIntro) {
                    this.doIntroLoop();
                }
                JRacers.this.elapsedTime = System.currentTimeMillis() - JRacers.this.startTime;
                if (JRacers.this.elapsedTime > JRacers.this.maxPaintTime) {
                    JRacers.this.maxPaintTime = JRacers.this.elapsedTime;
                }
                final JRacers this$0 = JRacers.this;
                --this$0.token;
            }
        }
    }
}
