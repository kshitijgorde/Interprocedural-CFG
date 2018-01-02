import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Polygon;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Iridium extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener
{
    volatile Thread runThread;
    Font defFont;
    Font infoFont;
    Font annFont;
    int counter;
    int width;
    int height;
    int mouseX;
    int mouseY;
    int pressedKey;
    boolean controlDown;
    boolean paused;
    boolean mouseEntered;
    Help help;
    Irue irue;
    Asteroid[] asteroids;
    int maxAsteroids;
    Interceptor[] interceptors;
    int maxInterceptors;
    HeavyFighter[] heavyFighters;
    int maxHeavyFighters;
    Zephi[] zephis;
    Image infoImg;
    boolean mouseOverInfoImg;
    boolean showShieldBars;
    int score;
    int highScore;
    int lastScore;
    int levelC;
    int level;
    int levelWait;
    int countSinceLevel;
    boolean cheated;
    Ship ship;
    int SFnumOfStars;
    int SFwidth;
    int SFheight;
    boolean[] SFshown;
    int[] SFposX;
    int[] SFposY;
    int[] SFspeedX;
    int[] SFspeedY;
    
    public void init() {
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.setBackground(new Color(2236962));
        final String parameter = this.getParameter("Sounds");
        if (parameter != null) {
            switch (parameter.charAt(0)) {
                case '1':
                case 'T':
                case 'Y':
                case 't':
                case 'y': {
                    Sounds.play = true;
                    break;
                }
                default: {
                    Sounds.play = false;
                    break;
                }
            }
        }
        this.highScore = 0;
        this.SFnumOfStars = 200;
        this.SFwidth = this.width;
        this.SFheight = this.height;
        this.SFshown = new boolean[200];
        this.SFposX = new int[200];
        this.SFposY = new int[200];
        this.SFspeedX = new int[200];
        this.SFspeedY = new int[200];
        this.restart();
        this.infoImg = this.getImage(this.getCodeBase(), "info.gif");
        this.help = new Help(this.width, this.height, this.infoImg, this);
        this.counter = 0;
        this.setBackground(new Color(1118481));
    }
    
    public void start() {
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.help.show(0);
        this.setBackground(new Color(0));
        if (this.runThread == null) {
            (this.runThread = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {
                return;
            }
            if (!this.paused && !this.help.visible) {
                if (this.ship.shieldCounter == 0) {
                    this.ship.usingShield = false;
                }
                else {
                    final Ship ship = this.ship;
                    --ship.shieldCounter;
                }
                if (this.levelWait != 0) {
                    --this.levelWait;
                    if (this.levelWait == 20) {
                        Sounds.play(Sounds.level);
                    }
                    if (this.level != 1 && this.levelWait != 1) {
                        this.incScore(1, 0);
                        if (this.levelWait % 10 == 0) {
                            if (this.ship.shield == 100) {
                                this.incScore(20, 0);
                            }
                            else {
                                this.ShChangeShield(1);
                            }
                        }
                        if (this.levelWait == 0 && this.level == 10) {
                            if (!this.cheated) {
                                this.help.show(10);
                            }
                            this.irue.start(this.getImage(this.getCodeBase(), "Irue.gif"), this.zephis);
                        }
                    }
                }
                this.irue.move();
                for (int i = 0; i < 20; ++i) {
                    if (i < this.maxAsteroids && this.levelWait == 0 && this.countSinceLevel > i * 20 + 100) {
                        this.asteroids[i].start(1);
                    }
                    this.asteroids[i].move();
                }
                for (int j = 0; j < 15; ++j) {
                    if (j < this.maxInterceptors && this.levelWait == 0 && this.countSinceLevel > j * 50 + 200 && Math.random() < 0.01) {
                        this.interceptors[j].start();
                    }
                    if (j < this.maxHeavyFighters && this.levelWait == 0 && this.countSinceLevel > j * 50 + 200 && Math.random() < 0.003) {
                        this.heavyFighters[j].start();
                    }
                    if (this.irue.alive && this.levelWait == 0 && this.countSinceLevel < 1600 && this.countSinceLevel > j * 200 + 50) {
                        this.zephis[j].start();
                    }
                    this.interceptors[j].move();
                    this.heavyFighters[j].move();
                    this.zephis[j].move();
                    if (this.zephis[j].hitting && this.irue.hit(20)) {
                        this.crashAll();
                    }
                }
                if (this.irue.nova.stadium > 2 && this.irue.nova.stadium > this.width - this.ship.middleX && this.irue.nova.stadium > this.ship.middleX - this.width) {
                    this.ShChangeShield(-100);
                }
                if (this.ship.angle != this.ship.goToAngle) {
                    if (this.ship.angle > this.ship.goToAngle) {
                        if (this.ship.wonTheGame) {
                            final Ship ship2 = this.ship;
                            ship2.angle -= 0.01;
                        }
                        else {
                            final Ship ship3 = this.ship;
                            ship3.angle -= 0.2;
                        }
                        if (this.ship.angle < this.ship.goToAngle) {
                            this.ship.angle = this.ship.goToAngle;
                        }
                        this.ShRender();
                    }
                    else {
                        if (this.ship.wonTheGame) {
                            final Ship ship4 = this.ship;
                            ship4.angle += 0.01;
                        }
                        else {
                            final Ship ship5 = this.ship;
                            ship5.angle += 0.2;
                        }
                        if (this.ship.angle > this.ship.goToAngle) {
                            this.ship.angle = this.ship.goToAngle;
                        }
                        this.ShRender();
                    }
                }
                for (int k = 0; k < 10; ++k) {
                    this.ship.shots[k].move();
                    if (this.ship.shots[k].hitting) {
                        final int inAsteroid = this.inAsteroid(this.ship.shots[k].targetX, this.ship.shots[k].targetY);
                        if (inAsteroid != -1) {
                            this.ship.shotExplosions[k].explode(this.ship.shots[k].targetX, this.ship.shots[k].targetY, 5);
                            if (this.asteroids[inAsteroid].hit(1)) {
                                this.incScore(50, 2);
                            }
                            this.incScore(1, 0);
                        }
                        final int inInterceptor = this.inInterceptor(this.ship.shots[k].targetX, this.ship.shots[k].targetY);
                        if (inInterceptor != -1) {
                            this.ship.shotExplosions[k].explode(this.ship.shots[k].targetX, this.ship.shots[k].targetY, 5);
                            if (this.interceptors[inInterceptor].hit(1)) {
                                this.incScore(100, 8);
                            }
                            this.incScore(1, 0);
                        }
                        final int inHeavyFighter = this.inHeavyFighter(this.ship.shots[k].targetX, this.ship.shots[k].targetY);
                        if (inHeavyFighter != -1) {
                            this.ship.shotExplosions[k].explode(this.ship.shots[k].targetX, this.ship.shots[k].targetY, 5);
                            if (this.heavyFighters[inHeavyFighter].hit(1)) {
                                this.incScore(350, 20);
                            }
                            this.incScore(1, 0);
                        }
                        final int inZephi = this.inZephi(this.ship.shots[k].targetX, this.ship.shots[k].targetY);
                        if (inZephi != -1) {
                            this.ship.shotExplosions[k].explode(this.ship.shots[k].targetX, this.ship.shots[k].targetY, 5);
                            if (this.zephis[inZephi].hit(1)) {
                                this.incScore(50, 1);
                            }
                            this.incScore(1, 0);
                        }
                        if (this.irue.alive && this.ship.shots[k].targetX > this.width / 2 - 50 && this.ship.shots[k].targetX < this.width / 2 + 50 && this.ship.shots[k].targetY > this.height / 2 - 50 && this.ship.shots[k].targetY < this.height / 2 + 50) {
                            this.ship.shotExplosions[k].explode(this.ship.shots[k].targetX, this.ship.shots[k].targetY, 5);
                            Sounds.play(Sounds.hit);
                            if ((k & 0x3) == 0x0 && this.irue.hit(1)) {
                                this.crashAll();
                            }
                        }
                    }
                    this.ship.shotExplosions[k].go();
                }
                this.ship.explosion.go();
                if (this.irue.shown && this.irue.shot.hitting) {
                    final int inZephi2 = this.inZephi(this.irue.shot.targetX, this.irue.shot.targetY);
                    if (inZephi2 != -1 && this.zephis[inZephi2].hit(1)) {
                        this.incScore(0, 1);
                    }
                }
                for (int l = 0; l < this.asteroids.length; ++l) {
                    for (int n = 0; n < this.ship.sprite.npoints; ++n) {
                        if (this.asteroids[l].zLevel >= 100 && this.asteroids[l].inside(this.ship.sprite.xpoints[n], this.ship.sprite.ypoints[n])) {
                            this.asteroids[l].crash();
                            this.ShChangeShield(-10);
                            this.incScore(0, 1);
                        }
                    }
                }
                for (int n2 = 0; n2 < 15; ++n2) {
                    for (int n3 = 0; n3 < this.interceptors[n2].shots.length; ++n3) {
                        if (this.interceptors[n2].shots[n3].hitting && this.ship.sprite.contains(this.interceptors[n2].shots[n3].targetX, this.interceptors[n2].shots[n3].targetY)) {
                            this.ShChangeShield(-1);
                            Sounds.play(Sounds.hit);
                        }
                    }
                    for (int n4 = 0; n4 < this.ship.sprite.npoints; ++n4) {
                        if (this.interceptors[n2].zLevel >= 100 && this.interceptors[n2].inside(this.ship.sprite.xpoints[n4], this.ship.sprite.ypoints[n4])) {
                            this.interceptors[n2].crash();
                            this.ShChangeShield(-10);
                            this.incScore(0, 4);
                        }
                    }
                    for (int n5 = 0; n5 < this.heavyFighters[n2].shots.length; ++n5) {
                        if (this.heavyFighters[n2].shots[n5].hitting && this.ship.sprite.contains(this.heavyFighters[n2].shots[n5].targetX, this.heavyFighters[n2].shots[n5].targetY)) {
                            this.ShChangeShield(-1);
                            Sounds.play(Sounds.hit);
                        }
                    }
                    for (int n6 = 0; n6 < this.heavyFighters[n2].missiles.length; ++n6) {
                        if (this.heavyFighters[n2].missiles[n6].hitting) {
                            this.heavyFighters[n2].missiles[n6].crash();
                            this.ShChangeShield(-15);
                            Sounds.play(Sounds.asteroid);
                        }
                    }
                    for (int n7 = 0; n7 < this.ship.sprite.npoints; ++n7) {
                        if (this.heavyFighters[n2].zLevel >= 100 && this.heavyFighters[n2].inside(this.ship.sprite.xpoints[n7], this.ship.sprite.ypoints[n7])) {
                            this.heavyFighters[n2].crash();
                            this.ShChangeShield(-20);
                            this.incScore(0, 7);
                        }
                    }
                }
                if (this.ship.alive && this.ship.middleY < this.height / 2) {
                    final Ship ship6 = this.ship;
                    ship6.middleY += this.ship.autoDownSpeed;
                    this.ShRender();
                }
                else if (!this.ship.alive && this.ship.middleY < this.height + 500) {
                    final Ship ship7 = this.ship;
                    ship7.middleY += this.ship.autoDownSpeed;
                    final Ship ship8 = this.ship;
                    ship8.angle += 0.1;
                    final Ship ship9 = this.ship;
                    ship9.goToAngle += 0.1;
                    if (this.score > this.lastScore && this.score > this.highScore) {
                        this.highScore = this.score;
                    }
                    this.ShRender();
                }
                else {
                    this.ship.autoDownSpeed = 0;
                    Label_2373: {
                        if (this.level == 10 && !this.ship.wonTheGame && this.countSinceLevel > 1500 && this.irue.alive) {
                            for (int n8 = 0; n8 < 15; ++n8) {
                                if (this.zephis[n8].shown) {
                                    break Label_2373;
                                }
                            }
                            this.incScore(1000 + (this.irue.shield + this.ship.shield) * 20, 0);
                            this.ship.wonTheGame = true;
                            this.ship.goToAngle = 0.0;
                        }
                    }
                    if (this.ship.wonTheGame && this.ship.angle == 0.0 && this.ship.landingStadium < 100) {
                        final Ship ship10 = this.ship;
                        ++ship10.landingStadium;
                        if (this.ship.middleX > this.width / 2) {
                            final Ship ship11 = this.ship;
                            ship11.middleX += (this.width / 2 - this.ship.middleX) / 80 - 1;
                        }
                        else if (this.ship.middleX < this.width / 2) {
                            final Ship ship12 = this.ship;
                            ship12.middleX += (this.width / 2 - this.ship.middleX) / 80 + 1;
                        }
                        if (this.ship.middleY > this.height / 2) {
                            final Ship ship13 = this.ship;
                            ship13.middleY += (this.height / 2 - this.ship.middleY) / 80 - 1;
                        }
                        else if (this.ship.middleY < this.height / 2) {
                            final Ship ship14 = this.ship;
                            ship14.middleY += (this.height / 2 - this.ship.middleY) / 80 + 1;
                        }
                        this.ShRender();
                    }
                }
                switch (this.pressedKey) {
                    case 38:
                    case 87:
                    case 104: {
                        this.ship.goToAngle = 0.0;
                        if (this.ship.angle == this.ship.goToAngle) {
                            this.ShMove(0, -25);
                            break;
                        }
                        break;
                    }
                    case 37:
                    case 65:
                    case 100: {
                        this.ship.goToAngle = 0.39269908169872414;
                        if (this.ship.angle == this.ship.goToAngle) {
                            this.ShMove(-25, 0);
                            break;
                        }
                        break;
                    }
                    case 40:
                    case 83:
                    case 101: {
                        this.ship.goToAngle = 0.0;
                        if (this.ship.angle == this.ship.goToAngle) {
                            this.ShMove(0, 25);
                            break;
                        }
                        break;
                    }
                    case 39:
                    case 68:
                    case 102: {
                        this.ship.goToAngle = -0.39269908169872414;
                        if (this.ship.angle == this.ship.goToAngle) {
                            this.ShMove(25, 0);
                            break;
                        }
                        break;
                    }
                    case 32: {
                        if ((this.counter & 0x3) == 0x0) {
                            this.ShShoot();
                            break;
                        }
                        break;
                    }
                }
                if (this.mouseEntered) {
                    this.moveTargeter(this.mouseX, this.mouseY);
                }
                if ((!this.irue.alive || this.irue.stadium < 120) && !this.ship.wonTheGame) {
                    for (int n9 = 0, n10 = 0; n10 < this.SFnumOfStars && n9 < 6 && this.levelWait == 0; ++n10) {
                        if (!this.SFshown[n10]) {
                            ++n9;
                            this.SFshown[n10] = true;
                            this.SFposX[n10] = this.SFwidth / 2;
                            this.SFposY[n10] = this.SFheight / 2;
                            this.SFspeedX[n10] = 0;
                            this.SFspeedY[n10] = 0;
                            while (this.SFspeedX[n10] == 0 || this.SFspeedY[n10] == 0) {
                                this.SFspeedX[n10] = (int)((Math.random() - 0.5) * 13.0);
                                this.SFspeedY[n10] = (int)((Math.random() - 0.5) * 13.0);
                            }
                        }
                    }
                    for (int n11 = 0; n11 < this.SFnumOfStars; ++n11) {
                        if (this.SFshown[n11]) {
                            final int[] sFposX = this.SFposX;
                            final int n12 = n11;
                            sFposX[n12] += this.SFspeedX[n11];
                            final int[] sFposY = this.SFposY;
                            final int n13 = n11;
                            sFposY[n13] += this.SFspeedY[n11];
                            if (this.SFposX[n11] < 0 || this.SFposY[n11] < 0 || this.SFposX[n11] > this.SFwidth || this.SFposY[n11] > this.SFheight) {
                                this.SFshown[n11] = false;
                            }
                        }
                    }
                }
                this.asteroids = Flying.sort(this.asteroids);
                if (this.ship.alive && !this.ship.wonTheGame) {
                    ++this.counter;
                    if (this.cheated && this.counter % 20 == 0) {
                        this.counter -= 4;
                    }
                }
                ++this.countSinceLevel;
                this.repaint(50L);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        if (Sounds.loaded || !Sounds.play) {
            this.paint(graphics);
        }
    }
    
    public void paint(final Graphics graphics) {
        final Image image = this.createImage(this.width, this.height);
        final Graphics graphics2 = image.getGraphics();
        graphics2.setFont(this.defFont);
        graphics2.setColor(Color.white);
        for (int i = 0; i < this.SFnumOfStars; ++i) {
            if (this.SFshown[i]) {
                graphics2.drawLine(this.SFposX[i], this.SFposY[i], this.SFposX[i] + this.SFspeedX[i] / 2, this.SFposY[i] + this.SFspeedY[i] / 2);
            }
        }
        this.irue.paint(graphics2);
        for (int j = 0; j < 15; ++j) {
            this.interceptors[j].paint(graphics2);
            this.heavyFighters[j].paint(graphics2);
            this.zephis[j].paint(graphics2);
        }
        for (int k = 0; k < 20; ++k) {
            this.asteroids[k].paint(graphics2);
        }
        this.ship.paint(graphics2);
        if (this.showShieldBars && this.levelWait == 0) {
            for (int l = 0; l < 15; ++l) {
                this.interceptors[l].paintBar(graphics2);
                this.heavyFighters[l].paintBar(graphics2);
                this.zephis[l].paintBar(graphics2);
            }
            for (int n = 0; n < 20; ++n) {
                this.asteroids[n].paintBar(graphics2);
            }
            this.irue.paintBar(graphics2);
        }
        if (this.levelWait != 0 && this.ship.alive && !this.ship.wonTheGame) {
            graphics2.setFont(this.annFont);
            graphics2.setColor(new Color(16777079));
            graphics2.drawString("Level " + this.level, this.width / 2 - 30, this.height / 2 + 7);
        }
        graphics2.setFont(this.defFont);
        graphics2.setColor(Color.green);
        graphics2.drawString("Time Elapsed: " + this.counter / 1200 + " min " + this.counter / 20 % 60 + " s", 5, this.height - 5);
        graphics2.drawString("Score: " + this.score, 5, 15);
        graphics2.drawString("Level: " + this.level, this.width / 4 + 5, 15);
        graphics2.drawString("Highscore: " + this.highScore, this.width / 2 + 5, 15);
        graphics2.setColor(this.mix(new Color(12255232), new Color(43520), 100 - this.ship.shield + this.ship.shield * (100 - this.ship.shield) / 70, this.ship.shield + this.ship.shield * (100 - this.ship.shield) / 50));
        graphics2.fillRect(this.width - 5 - this.ship.shield, 5, this.ship.shield, 10);
        graphics2.setColor(Color.lightGray);
        graphics2.draw3DRect(this.width - 105, 5, 100, 10, false);
        if (!this.ship.alive || this.ship.wonTheGame) {
            graphics2.setFont(this.annFont);
            graphics2.setColor(new Color(16777079));
            if (!this.ship.alive || this.cheated) {
                graphics2.drawString("G A M E   O V E R", this.width / 2 - 80, this.height / 2);
            }
            else {
                graphics2.drawString("YOU WON THE GAME", this.width / 2 - 90, this.height / 2);
            }
            graphics2.setFont(this.defFont);
            graphics2.drawString("Press F2 to restart", this.width / 2 - 35, this.height / 2 + 12);
            if (this.score > this.lastScore) {
                graphics2.drawString("You got the highscore!", this.width / 2 - 45, this.height / 2 - 22);
            }
        }
        if (this.paused) {
            graphics2.setColor(new Color(16777079));
            graphics2.setFont(this.defFont);
            graphics2.drawString("Paused", 5, this.height - 35);
        }
        if (Sounds.loaded && Sounds.play && Sounds.mute) {
            graphics2.setColor(new Color(16777079));
            graphics2.setFont(this.defFont);
            graphics2.drawString("Mute", 5, this.height - 20);
        }
        this.help.paint(graphics2);
        if (!this.help.visible) {
            if (graphics2.drawImage(this.infoImg, this.width - this.infoImg.getWidth(this) - 2, this.height - this.infoImg.getHeight(this) - 2, this)) {
                if (this.mouseOverInfoImg) {
                    graphics2.setColor(Color.gray);
                    graphics2.draw3DRect(this.width - this.infoImg.getWidth(this) - 4, this.height - this.infoImg.getHeight(this) - 4, this.infoImg.getWidth(this) + 2, this.infoImg.getHeight(this) + 2, true);
                }
            }
            else {
                graphics2.setFont(this.defFont);
                graphics2.setColor(Color.gray);
                if (this.mouseOverInfoImg) {
                    graphics2.draw3DRect(this.width - 26, this.height - 15, 23, 12, true);
                    graphics2.setColor(new Color(10066431));
                }
                else {
                    graphics2.setColor(Color.white);
                }
                graphics2.drawString("info", this.width - 24, this.height - 4);
            }
        }
        graphics.drawImage(image, 0, 0, this);
        if (!Sounds.loaded && Sounds.play) {
            graphics.setColor(new Color(16777079));
            graphics.setFont(this.defFont);
            graphics.drawString("Loading Sounds", 5, this.height - 20);
            Sounds.laser = this.getAudioClip(this.getCodeBase(), "Laser.au");
            graphics.fillRect(0, 0, this.width * 2 / 78, 3);
            Sounds.asteroid = this.getAudioClip(this.getCodeBase(), "Asteroid.au");
            graphics.fillRect(0, 0, this.width * 13 / 78, 3);
            Sounds.enemy = this.getAudioClip(this.getCodeBase(), "Enemy.au");
            graphics.fillRect(0, 0, this.width * 35 / 78, 3);
            Sounds.hit = this.getAudioClip(this.getCodeBase(), "Hit.au");
            graphics.fillRect(0, 0, this.width * 37 / 78, 3);
            Sounds.die = this.getAudioClip(this.getCodeBase(), "Die.au");
            graphics.fillRect(0, 0, this.width * 69 / 78, 3);
            Sounds.level = this.getAudioClip(this.getCodeBase(), "Level.au");
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.width, 3);
            Sounds.loaded = true;
        }
    }
    
    void moveTargeter(final int targetX, final int targetY) {
        this.ship.targetX = targetX;
        this.ship.targetY = targetY;
        if (this.ship.targetX < 5) {
            this.ship.targetX = 5;
        }
        if (this.ship.targetY < 5) {
            this.ship.targetY = 5;
        }
        if (this.ship.targetX > this.width - 5) {
            this.ship.targetX = this.width - 5;
        }
        if (this.ship.targetY > this.height - 5) {
            this.ship.targetY = this.height - 5;
        }
    }
    
    void ShMove(final int n, final int n2) {
        if (this.ship.alive && !this.ship.wonTheGame) {
            final Ship ship = this.ship;
            ship.middleX += n;
            final Ship ship2 = this.ship;
            ship2.middleY += n2;
            if (this.ship.middleX < 30) {
                this.ship.middleX = 30;
            }
            if (this.ship.middleY < 35) {
                this.ship.middleY = 35;
            }
            if (this.ship.middleX > this.width - 30) {
                this.ship.middleX = this.width - 30;
            }
            if (this.ship.middleY > this.height - 35) {
                this.ship.middleY = this.height - 35;
            }
            this.ship.autoDownSpeed = 0;
            this.ShRender();
        }
    }
    
    void ShRender() {
        this.ship.sprite = new Polygon();
        if (this.ship.landingStadium == 0) {
            for (int i = 0; i < this.ship.shape.npoints; ++i) {
                final double sin = Math.sin(this.ship.angle);
                final double cos = Math.cos(this.ship.angle);
                this.ship.sprite.addPoint((int)Math.round(this.ship.shape.xpoints[i] * cos + this.ship.shape.ypoints[i] * sin) + this.ship.middleX, (int)Math.round(this.ship.shape.ypoints[i] * cos - this.ship.shape.xpoints[i] * sin) + this.ship.middleY);
            }
            return;
        }
        for (int j = 0; j < this.ship.shape.npoints; ++j) {
            this.ship.sprite.addPoint(this.ship.shape.xpoints[j] * (101 - this.ship.landingStadium) / 100 + this.ship.middleX, this.ship.shape.ypoints[j] * (101 - this.ship.landingStadium) / 100 + this.ship.middleY);
        }
    }
    
    void ShShoot() {
        if (this.ship.alive && this.levelWait == 0 && !this.ship.wonTheGame) {
            Sounds.play(Sounds.laser);
            for (int i = 0; i < 20; i += 4) {
                if (!this.ship.shots[i].shooting) {
                    this.ship.shots[i].shoot(this.ship.sprite.xpoints[2], this.ship.sprite.ypoints[2], this.ship.targetX, this.ship.targetY);
                    this.ship.shots[i + 1].shoot(this.ship.sprite.xpoints[4], this.ship.sprite.ypoints[4], this.ship.targetX, this.ship.targetY);
                    this.ship.shots[i + 2].shoot(this.ship.sprite.xpoints[8], this.ship.sprite.ypoints[8], this.ship.targetX, this.ship.targetY);
                    this.ship.shots[i + 3].shoot(this.ship.sprite.xpoints[10], this.ship.sprite.ypoints[10], this.ship.targetX, this.ship.targetY);
                    this.incScore(-1, 0);
                    return;
                }
            }
        }
    }
    
    void ShChangeShield(final int n) {
        if (this.ship.alive) {
            if (n < 0) {
                this.ship.usingShield = true;
                final Ship ship = this.ship;
                ship.shieldCounter -= n;
            }
            final Ship ship2 = this.ship;
            ship2.shield += n;
            if (this.ship.shield <= 0) {
                this.ship.autoDownSpeed = 4;
                this.ship.shield = 0;
                this.ship.shieldCounter = 0;
                this.ship.alive = false;
                Sounds.play(Sounds.die);
            }
            if (this.ship.shield > 100) {
                this.ship.shield = 100;
            }
        }
    }
    
    void restart() {
        this.ship = new Ship(this.width, this.height, this);
        this.lastScore = this.highScore;
        this.irue = new Irue(this.width, this.height, this);
        this.asteroids = new Asteroid[20];
        for (int i = 0; i < 20; ++i) {
            this.asteroids[i] = new Asteroid(this.width, this.height);
        }
        this.interceptors = new Interceptor[15];
        this.heavyFighters = new HeavyFighter[15];
        this.zephis = new Zephi[15];
        for (int j = 0; j < 15; ++j) {
            this.interceptors[j] = new Interceptor(this.width, this.height, this.ship);
            this.heavyFighters[j] = new HeavyFighter(this.width, this.height, this.ship);
            this.zephis[j] = new Zephi(this.width, this.height, this.ship);
        }
        this.maxAsteroids = 8;
        this.maxInterceptors = 0;
        this.maxHeavyFighters = 0;
        this.score = 0;
        this.levelC = 0;
        this.level = 1;
        this.levelWait = (this.height / 2 - this.ship.middleY) / 3;
        this.countSinceLevel = 0;
        this.counter = 0;
        this.cheated = false;
        for (int k = 0; k < this.SFnumOfStars; ++k) {
            this.SFshown[k] = false;
            this.SFposX[k] = 0;
            this.SFposY[k] = 0;
            this.SFspeedX[k] = 0;
            this.SFspeedY[k] = 0;
        }
    }
    
    int inAsteroid(final int n, final int n2) {
        for (int i = 0; i < 20; ++i) {
            if (this.asteroids[i].inside(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    int inInterceptor(final int n, final int n2) {
        for (int i = 0; i < 15; ++i) {
            if (this.interceptors[i].inside(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    int inHeavyFighter(final int n, final int n2) {
        for (int i = 0; i < 15; ++i) {
            if (this.heavyFighters[i].inside(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    int inZephi(final int n, final int n2) {
        for (int i = 0; i < 15; ++i) {
            if (this.zephis[i].inside(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    void crashAll() {
        for (int i = 0; i < 20; ++i) {
            this.asteroids[i].crash();
        }
        for (int j = 0; j < 15; ++j) {
            this.interceptors[j].crash();
            this.heavyFighters[j].crash();
            this.zephis[j].crash();
        }
    }
    
    void incScore(final int n, final int n2) {
        if (!this.ship.alive || this.ship.wonTheGame) {
            return;
        }
        if (!this.cheated) {
            this.score += n;
        }
        this.levelC += n2;
        if (this.level == 1 && this.levelC >= 12) {
            this.level = 2;
            this.levelWait = 100;
            this.levelC = 0;
            this.maxAsteroids = 5;
            this.maxInterceptors = 1;
            this.countSinceLevel = 0;
            this.crashAll();
        }
        else if (this.level == 2 && this.levelC >= 35) {
            this.level = 3;
            this.levelWait = 100;
            this.levelC = 0;
            this.maxAsteroids = 9;
            this.maxInterceptors = 2;
            this.maxHeavyFighters = 0;
            this.countSinceLevel = 0;
            this.crashAll();
        }
        else if (this.level == 3 && this.levelC >= 45) {
            this.level = 4;
            this.levelWait = 100;
            this.levelC = 0;
            this.maxAsteroids = 20;
            this.maxInterceptors = 1;
            this.maxHeavyFighters = 0;
            this.countSinceLevel = 0;
            this.crashAll();
        }
        else if (this.level == 4 && this.levelC >= 20) {
            this.level = 5;
            this.levelWait = 100;
            this.levelC = 0;
            this.maxAsteroids = 10;
            this.maxInterceptors = 3;
            this.maxHeavyFighters = 0;
            this.countSinceLevel = 0;
            this.crashAll();
        }
        else if (this.level == 5 && this.levelC >= 45) {
            this.level = 6;
            this.levelWait = 100;
            this.levelC = 0;
            this.maxAsteroids = 6;
            this.maxInterceptors = 1;
            this.maxHeavyFighters = 1;
            this.countSinceLevel = 0;
            this.crashAll();
        }
        else if (this.level == 6 && this.levelC >= 50) {
            this.level = 7;
            this.levelWait = 100;
            this.levelC = 0;
            this.maxAsteroids = 15;
            this.maxInterceptors = 1;
            this.maxHeavyFighters = 3;
            this.countSinceLevel = 0;
            this.crashAll();
        }
        else if (this.level == 7 && this.levelC >= 80) {
            this.level = 8;
            this.levelWait = 100;
            this.levelC = 0;
            this.maxAsteroids = 15;
            this.maxInterceptors = 5;
            this.maxHeavyFighters = 2;
            this.countSinceLevel = 0;
            this.crashAll();
        }
        else if (this.level == 8 && this.levelC >= 60) {
            this.level = 9;
            this.levelWait = 100;
            this.levelC = 0;
            this.maxAsteroids = 10;
            this.maxInterceptors = 12;
            this.maxHeavyFighters = 0;
            this.countSinceLevel = 0;
            this.crashAll();
        }
        else if (this.level == 9 && this.levelC >= 40) {
            this.level = 10;
            this.levelWait = 100;
            this.levelC = 0;
            this.maxAsteroids = 0;
            this.maxInterceptors = 0;
            this.maxHeavyFighters = 0;
            this.countSinceLevel = 0;
            this.crashAll();
        }
        if (this.score < 0) {
            this.score = 0;
        }
        if (this.score >= this.lastScore && this.levelWait == 0) {
            this.highScore = this.score;
        }
    }
    
    Color mix(final Color color, final Color color2, final int n, final int n2) {
        return new Color((color.getRed() * n + color2.getRed() * n2) / 100, (color.getGreen() * n + color2.getGreen() * n2) / 100, (color.getBlue() * n + color2.getBlue() * n2) / 100);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 17) {
            this.controlDown = true;
            return;
        }
        if (keyEvent.getKeyCode() == 113) {
            this.restart();
            return;
        }
        if (keyEvent.getKeyCode() == 123) {
            this.cheated = true;
            if (this.level == 10) {
                if (!this.irue.alive) {
                    this.irue.alive = true;
                    this.irue.stadium = 100;
                }
                this.countSinceLevel = 2000;
                this.crashAll();
            }
            else {
                this.incScore(0, 1000);
            }
            if (this.levelWait > 20) {
                this.levelWait = 20;
            }
        }
        else {
            if (keyEvent.getKeyCode() == 80) {
                this.paused = !this.paused;
                if (this.paused || this.help.visible) {
                    this.repaint();
                }
                return;
            }
            if (keyEvent.getKeyCode() == 77) {
                Sounds.mute = !Sounds.mute;
                if (this.paused || this.help.visible) {
                    this.repaint();
                }
                return;
            }
            if (keyEvent.getKeyCode() == 66) {
                this.showShieldBars = !this.showShieldBars;
                if (this.paused) {
                    this.repaint();
                }
            }
            else if (!keyEvent.isActionKey()) {
                this.pressedKey = keyEvent.getKeyCode();
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 17) {
            this.controlDown = false;
            return;
        }
        if (keyEvent.getKeyCode() == this.pressedKey && !keyEvent.isActionKey()) {
            this.pressedKey = 0;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.mouseOverInfoImg) {
            this.help.show(0);
            this.repaint();
            return;
        }
        if (this.help.visible) {
            if (this.help.click(mouseEvent.getX(), mouseEvent.getY())) {
                this.repaint();
            }
        }
        else {
            if ((~mouseEvent.getModifiers() & 0x10) == 0x0) {
                this.ShShoot();
                return;
            }
            mouseEvent.getModifiers();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.mouseEntered = true;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.mouseEntered = false;
        this.mouseOverInfoImg = false;
        if (this.paused || this.help.visible) {
            this.repaint();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.mouseEntered = true;
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
        if ((this.counter & 0x3) == 0x0 && (~mouseEvent.getModifiers() & 0x10) == 0x0) {
            this.ShShoot();
            return;
        }
        mouseEvent.getModifiers();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.mouseEntered = true;
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
        if (!this.help.visible && ((mouseEvent.getX() > this.width - 26 && mouseEvent.getY() > this.height - 14) || (mouseEvent.getX() > this.width - this.infoImg.getWidth(this) - 4 && mouseEvent.getY() > this.height - this.infoImg.getHeight(this) - 4))) {
            this.setCursor(new Cursor(12));
            this.mouseOverInfoImg = true;
            if (this.paused || this.help.visible) {
                this.repaint();
            }
            return;
        }
        if (this.ship.alive && !this.help.visible && !this.ship.wonTheGame) {
            this.setCursor(new Cursor(1));
        }
        else {
            this.setCursor(new Cursor(0));
        }
        this.mouseOverInfoImg = false;
        if (this.paused || this.help.visible) {
            this.repaint();
        }
    }
    
    public void stop() {
        if (this.runThread != null) {
            this.runThread.interrupt();
        }
    }
    
    public void destroy() {
        if (this.runThread != null) {
            this.runThread = null;
        }
    }
    
    public Iridium() {
        this.defFont = new Font("Serif", 0, 12);
        this.infoFont = new Font("TimesRoman", 0, 11);
        this.annFont = new Font("Sans-Serif", 1, 20);
        this.mouseX = -1;
        this.mouseY = -1;
        this.controlDown = false;
        this.paused = false;
        this.mouseEntered = false;
        this.mouseOverInfoImg = false;
        this.showShieldBars = true;
    }
}
