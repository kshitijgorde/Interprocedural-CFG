import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Label;
import java.util.Random;
import java.util.Vector;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SpaceMaze extends Applet implements ActionListener, Runnable
{
    Ship myShip;
    Image sketch;
    Image sketch3;
    Image backGround;
    Graphics g;
    Graphics g2;
    Graphics gBack;
    MediaTracker tracker;
    Image[] img;
    Thread mainThread;
    int level;
    int sleepFor;
    int maxLevel;
    boolean loaded;
    boolean paramsRead;
    Vector enemies;
    Vector objects;
    Random rand;
    boolean pause;
    boolean enemiesMade;
    boolean blueAccess;
    boolean redAccess;
    int diamondsNeeded;
    boolean onRedKey;
    boolean onBlueKey;
    boolean backDrawn;
    boolean levelStarted;
    int[] squareType;
    boolean[][] enemyCanLand;
    boolean[] myShipCanLand;
    boolean[] isLandedHere;
    int threadIndex;
    boolean restartEnemy;
    int bannerX;
    Label lblShields;
    Label lblScore;
    Label lblDiamonds;
    Label lblLevel;
    Button btnPause;
    Button btnRestart;
    Button btnQuit;
    
    public SpaceMaze() {
        this.sleepFor = 50;
        this.paramsRead = false;
        this.enemies = new Vector();
        this.objects = new Vector();
        this.rand = new Random(System.currentTimeMillis());
        this.pause = false;
        this.enemiesMade = false;
        this.blueAccess = false;
        this.redAccess = false;
        this.onRedKey = false;
        this.onBlueKey = false;
        this.levelStarted = false;
        this.squareType = new int[200];
        this.enemyCanLand = new boolean[17][5];
        this.myShipCanLand = new boolean[17];
        this.isLandedHere = new boolean[200];
        this.threadIndex = 0;
        this.restartEnemy = false;
        this.bannerX = 600;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Button button = (Button)actionEvent.getSource();
        if (button.getLabel().equals("Pause")) {
            this.pause = true;
            this.btnPause.setLabel("Continue");
        }
        else if (button.getLabel().equals("Continue")) {
            this.pause = false;
            this.btnPause.setLabel("Pause");
        }
        else if (button.getLabel().equals("Restart Level")) {
            this.lostLife();
        }
        else if (button.getLabel().equals("Quit")) {
            this.myShip.lives = 0;
            this.myShip.score = 0;
            this.lostLife();
        }
        this.requestFocus();
    }
    
    public void blueKeyLandedOn() {
        for (int i = 0; i < 200; ++i) {
            if (this.squareType[i] == 16) {
                this.squareType[i] = 10;
            }
        }
        System.out.println("Just landed on blue   " + this.blueAccess);
        if (this.blueAccess) {
            this.blueAccess = false;
            this.myShipCanLand[10] = false;
            this.enemyCanLand[10][1] = false;
            for (int j = 0; j < this.objects.size(); ++j) {
                final Object2 object2 = this.objects.elementAt(j);
                if (object2.type == 10) {
                    object2.picture = 10;
                }
            }
        }
        else {
            this.blueAccess = true;
            this.myShipCanLand[10] = true;
            this.enemyCanLand[10][1] = true;
            for (int k = 0; k < this.objects.size(); ++k) {
                final Object2 object3 = this.objects.elementAt(k);
                if (object3.type == 10) {
                    object3.picture = 14;
                }
            }
        }
    }
    
    public void createEnemy(final int n, final int n2) {
        this.enemies.addElement(new Enemy(this.getSquareX(n), this.getSquareY(n), n2));
    }
    
    public void createObject(final int n, final int n2, final int n3, final boolean b) {
        this.objects.addElement(new Object2(n, n2, n3, b));
    }
    
    public void demoDraw() {
        this.g.drawImage(this.backGround, 0, 0, this);
    }
    
    public void detectCollision() {
        for (int i = 0; i < this.enemies.size(); ++i) {
            final Enemy enemy = this.enemies.elementAt(i);
            if (((this.myShip.y < enemy.y + 15 && this.myShip.y > enemy.y - 10 && this.myShip.x + 28 > enemy.x && this.myShip.x < enemy.x + 28) || (this.myShip.y < enemy.y + 15 && this.myShip.y > enemy.y - 10 && this.myShip.x + 28 > enemy.x && this.myShip.x < enemy.x + 28) || (this.myShip.y + 14 > enemy.y && this.myShip.y < enemy.y - 15 && this.myShip.x + 28 > enemy.x && enemy.x > this.myShip.x - 30) || (this.myShip.y + 14 > enemy.y && this.myShip.y < enemy.y - 15 && this.myShip.x < enemy.x + 28 && enemy.x > this.myShip.x - 30)) && this.squareType[this.myShip.y / 30 * 20 + this.myShip.x / 30] != 9) {
                if (enemy.type == 0) {
                    this.myShip.shields -= 25;
                }
                else if (enemy.type == 1) {
                    this.myShip.shields -= 3;
                }
                else if (enemy.type == 2 && (this.squareType[this.myShip.y / 30 * 20 + this.myShip.x / 30] == 16 || this.squareType[this.myShip.y / 30 * 20 + this.myShip.x / 30] == 10 || this.squareType[this.myShip.y / 30 * 20 + this.myShip.x / 30] == 14)) {
                    this.myShip.shields -= 20;
                }
                this.updateDisplay();
                if (this.myShip.shields <= 0) {
                    this.lostLife();
                }
            }
        }
    }
    
    public void diamondLandedOn(final int n) {
        for (int i = 0; i < this.objects.size(); ++i) {
            final Object2 object2 = this.objects.elementAt(i);
            if (object2.visible && object2.type == 8 && object2.square == n) {
                --this.diamondsNeeded;
                final Ship myShip = this.myShip;
                myShip.score += 50;
                if (this.myShip.score % 1000 == 0) {
                    final Ship myShip2 = this.myShip;
                    ++myShip2.lives;
                }
                System.out.print("Diamond!!!!!   ");
                object2.visible = false;
                System.out.println(object2.visible);
                this.updateDisplay();
            }
        }
    }
    
    public void drawBack() {
        if (!this.backDrawn && this.paramsRead) {
            System.out.println("Drawn back");
            this.diamondsNeeded = 0;
            this.objects.removeAllElements();
            for (int i = 0; i < 200; ++i) {
                switch (this.squareType[i]) {
                    case 0: {
                        this.gBack.drawImage(this.img[this.squareType[i]], this.getSquareX(i), this.getSquareY(i), this);
                        break;
                    }
                    case 1: {
                        this.gBack.drawImage(this.img[this.squareType[i]], this.getSquareX(i), this.getSquareY(i), this);
                        break;
                    }
                    case 2: {
                        this.gBack.drawImage(this.img[1], this.getSquareX(i), this.getSquareY(i), this);
                        this.createObject(2, 2, i, true);
                        break;
                    }
                    case 3: {
                        this.gBack.drawImage(this.img[this.squareType[i]], this.getSquareX(i), this.getSquareY(i), this);
                        this.createObject(3, 3, i, true);
                        break;
                    }
                    case 4: {
                        this.gBack.drawImage(this.img[this.squareType[i]], this.getSquareX(i), this.getSquareY(i), this);
                        break;
                    }
                    case 5: {
                        this.gBack.drawImage(this.img[1], this.getSquareX(i), this.getSquareY(i), this);
                        this.myShip.start(this.getSquareX(i), this.getSquareY(i));
                        break;
                    }
                    case 6: {
                        this.gBack.drawImage(this.img[1], this.getSquareX(i), this.getSquareY(i), this);
                        if (!this.enemiesMade) {
                            this.createEnemy(i, 0);
                            break;
                        }
                        break;
                    }
                    case 7: {
                        this.gBack.drawImage(this.img[1], this.getSquareX(i), this.getSquareY(i), this);
                        this.createObject(7, 7, i, true);
                        break;
                    }
                    case 8: {
                        this.gBack.drawImage(this.img[1], this.getSquareX(i), this.getSquareY(i), this);
                        this.createObject(8, 8, i, true);
                        ++this.diamondsNeeded;
                        break;
                    }
                    case 9: {
                        this.gBack.drawImage(this.img[this.squareType[i]], this.getSquareX(i), this.getSquareY(i), this);
                        break;
                    }
                    case 10: {
                        this.gBack.drawImage(this.img[this.squareType[i]], this.getSquareX(i), this.getSquareY(i), this);
                        this.createObject(this.squareType[i], this.squareType[i], i, true);
                        break;
                    }
                    case 11: {
                        this.gBack.drawImage(this.img[1], this.getSquareX(i), this.getSquareY(i), this);
                        if (!this.enemiesMade) {
                            this.createEnemy(i, 1);
                            break;
                        }
                        break;
                    }
                    case 13: {
                        this.gBack.drawImage(this.img[this.squareType[i]], this.getSquareX(i), this.getSquareY(i), this);
                        this.createObject(this.squareType[i], this.squareType[i], i, true);
                        break;
                    }
                    case 14: {
                        this.gBack.drawImage(this.img[this.squareType[i]], this.getSquareX(i), this.getSquareY(i), this);
                        this.createObject(this.squareType[i], this.squareType[i], i, true);
                        break;
                    }
                    case 15: {
                        this.createObject(10, 10, i, true);
                        if (!this.enemiesMade) {
                            this.createEnemy(i, 2);
                            break;
                        }
                        break;
                    }
                }
            }
            this.enemiesMade = true;
            this.updateDisplay();
            this.backDrawn = true;
        }
    }
    
    public void drawBlueSquare(final int n) {
        this.g2.setColor(Color.blue);
        this.g2.drawRect(this.getSquareX(n), this.getSquareY(n), 30, 30);
        this.isLandedHere[n] = true;
    }
    
    public void drawEnemies() {
        for (int i = 0; i < this.enemies.size(); ++i) {
            final Enemy enemy = this.enemies.elementAt(i);
            if (enemy.type == 0) {
                if (this.restartEnemy) {
                    enemy.x = 120;
                    enemy.y = 60;
                    this.restartEnemy = false;
                }
                if (this.getRand(9) == 1) {
                    enemy.changeDirection();
                }
                if (this.enemyLegalMove0(enemy.getX(), enemy.getY(), enemy.speed, enemy.direction, 1, enemy.toIncX, enemy.toIncY)) {
                    enemy.move();
                }
                else {
                    enemy.changeDirection();
                    if (this.enemyLegalMove0(enemy.getX(), enemy.getY(), enemy.speed, enemy.direction, 1, enemy.toIncX, enemy.toIncY)) {
                        enemy.move();
                    }
                }
                this.g2.drawImage(this.img[6], enemy.getX(), enemy.getY(), this);
            }
            else if (enemy.type == 1) {
                if (enemy.changeTime == 0) {
                    enemy.toIncX = (this.myShip.x - enemy.getX()) / 10;
                    enemy.toIncY = (this.myShip.y - enemy.getY()) / 10;
                    enemy.changeTime = 10;
                }
                else {
                    final Enemy enemy2 = enemy;
                    --enemy2.changeTime;
                }
                if (this.enemyLegalMove1(enemy.getX(), enemy.getY(), enemy.speed, enemy.direction, enemy.type, enemy.toIncX, enemy.toIncY)) {
                    enemy.specMove();
                }
                else {
                    switch (this.getRand(8)) {
                        case 0: {
                            enemy.toIncX = 0;
                            enemy.toIncY = (this.myShip.y + 30 - enemy.getY()) / 20;
                            break;
                        }
                        case 1: {
                            if (this.myShip.x - enemy.getX() < 0) {
                                enemy.toIncX = -5;
                            }
                            else {
                                enemy.toIncX = 5;
                            }
                            enemy.toIncY = 0;
                            break;
                        }
                        case 2: {
                            if (this.myShip.y - enemy.getY() < 0) {
                                enemy.toIncY = -5;
                            }
                            else {
                                enemy.toIncY = 5;
                            }
                            enemy.toIncX = 0;
                            break;
                        }
                        case 3: {
                            enemy.toIncY = 0;
                            enemy.toIncX = (this.myShip.x + 30 - enemy.getX()) / 10;
                            break;
                        }
                        case 4: {
                            enemy.toIncY = 5;
                            enemy.toIncX = 5;
                            break;
                        }
                        case 5: {
                            enemy.toIncY = -5;
                            enemy.toIncX = -5;
                            break;
                        }
                        case 6: {
                            enemy.toIncX = 0;
                            enemy.toIncY = (this.myShip.y + 30 - enemy.getY()) / -10;
                            break;
                        }
                        case 7: {
                            enemy.toIncY = 0;
                            enemy.toIncX = (this.myShip.x + 30 - enemy.getX()) / -10;
                            break;
                        }
                    }
                    if (this.enemyLegalMove1(enemy.getX(), enemy.getY(), enemy.speed, enemy.direction, enemy.type, enemy.toIncX, enemy.toIncY)) {
                        enemy.specMove();
                    }
                    else {
                        enemy.changeTime = 0;
                    }
                }
                enemy.changeFrame();
                this.g2.drawImage(this.img[enemy.frame + 11], enemy.getX(), enemy.getY(), this);
            }
            else if (enemy.type == 2) {
                if (!this.enemyLegalMove2(enemy.getX(), enemy.getY(), enemy.speed, enemy.direction, enemy.type, enemy.toIncX, enemy.toIncY)) {
                    if (enemy.direction == 0) {
                        enemy.direction = 2;
                    }
                    else if (enemy.direction == 1) {
                        enemy.direction = 3;
                    }
                    else if (enemy.direction == 2) {
                        enemy.direction = 1;
                    }
                    else if (enemy.direction == 3) {
                        enemy.direction = 0;
                    }
                    else {
                        enemy.direction = 0;
                    }
                }
                else {
                    final int direction = enemy.direction;
                    if (enemy.direction == 0) {
                        enemy.direction = 3;
                    }
                    else if (enemy.direction == 1) {
                        enemy.direction = 2;
                    }
                    else if (enemy.direction == 2) {
                        enemy.direction = 0;
                    }
                    else if (enemy.direction == 3) {
                        enemy.direction = 1;
                    }
                    if (!this.enemyLegalMove2(enemy.getX(), enemy.getY(), enemy.speed, enemy.direction, enemy.type, enemy.toIncX, enemy.toIncY)) {
                        enemy.direction = direction;
                    }
                }
                if (this.enemyLegalMove2(enemy.getX(), enemy.getY(), enemy.speed, enemy.direction, enemy.type, enemy.toIncX, enemy.toIncY)) {
                    enemy.move();
                    if (enemy.direction == 3) {
                        enemy.frame = 1;
                    }
                    if (enemy.direction == 2) {
                        enemy.frame = 0;
                    }
                }
                this.g2.drawImage(this.img[enemy.frame + 15], enemy.getX(), enemy.getY(), this);
            }
        }
    }
    
    public void drawEnemiesNotMove() {
        for (int i = 0; i < this.enemies.size(); ++i) {
            final Enemy enemy = this.enemies.elementAt(i);
            if (enemy.type == 0) {
                this.g2.drawImage(this.img[6], enemy.getX(), enemy.getY(), this);
            }
            else if (enemy.type == 1) {
                this.g2.drawImage(this.img[enemy.frame + 11], enemy.getX(), enemy.getY(), this);
            }
            else if (enemy.type == 2) {
                this.g2.drawImage(this.img[enemy.frame + 15], enemy.getX(), enemy.getY(), this);
            }
        }
    }
    
    public void drawNetSquares(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.drawBlueSquare(n3);
        if (n5 >= 20) {
            this.drawBlueSquare(n3 + 20);
        }
        if (n4 >= 10) {
            this.drawBlueSquare(n3 + 1);
        }
        if (n4 >= 10 && n5 >= 20) {
            this.drawBlueSquare(n3 + 21);
        }
    }
    
    public void drawObjects() {
        for (int i = 0; i < this.objects.size(); ++i) {
            final Object2 object2 = this.objects.elementAt(i);
            if (object2.visible) {
                this.g2.drawImage(this.img[object2.picture], object2.x, object2.y, this);
            }
        }
    }
    
    public void drawShip() {
        this.g2.drawImage(this.img[5], this.myShip.x, this.myShip.y, this);
        this.landedOnSquare(this.myShip.x, this.myShip.y);
    }
    
    public void drawToBeginMessage() {
        if (!this.levelStarted) {
            if (this.level == 1) {
                if (this.myShip.score > 0) {
                    this.g2.setColor(Color.black);
                    this.g2.fillRect(0, 0, 600, 400);
                    this.g2.setColor(Color.white);
                    this.g2.setFont(new Font("Helvetica", 3, 24));
                    this.g2.drawString("Game Over! Your score: " + this.myShip.score, 20, 60);
                    this.g2.setColor(Color.yellow);
                    this.g2.setFont(new Font("Helvetica", 3, 48));
                    this.g2.drawString("SPACEMAZE", 140, 160);
                    this.g2.setFont(new Font("Helvetica", 3, 24));
                    this.g2.drawString("Type S to play again", 160, 220);
                    this.myShip.score = 0;
                    this.updateDisplay();
                }
                else {
                    this.g2.setColor(Color.black);
                    this.g2.fillRect(0, 0, 600, 400);
                    this.g2.setColor(Color.yellow);
                    this.g2.setFont(new Font("Helvetica", 3, 48));
                    this.g2.drawString("SPACEMAZE", 160, 100);
                    this.levelStarted = true;
                    this.g2.setColor(Color.blue);
                    this.g2.fillRect(100, 135, 400, 30);
                    this.g2.setColor(Color.white);
                    this.g2.setFont(new Font("Helvetica", 3, 24));
                    this.g2.drawString("Type S to start", 200, 160);
                }
            }
            else {
                this.levelStarted = true;
                this.g2.setColor(Color.blue);
                this.g2.fillRect(100, 135, 400, 30);
                this.g2.setColor(Color.white);
                this.g2.setFont(new Font("Helvetica", 3, 24));
                this.g2.drawString("Type S to start", 200, 160);
            }
        }
    }
    
    public boolean enemyLegalMove0(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        int n8 = n;
        int n9 = n2;
        if (n5 == 1) {
            n9 = n2 + n7;
            n8 = n + n6;
        }
        switch (n4) {
            case 0: {
                n9 = n2 - n3;
                break;
            }
            case 1: {
                n9 = n2 + n3;
                break;
            }
            case 2: {
                n8 = n - n3;
                break;
            }
            case 3: {
                n8 = n + n3;
                break;
            }
        }
        final int n10 = n9 / 30 * 20;
        final int n11 = n9 % 30;
        final int n12 = n8 / 30;
        final int n13 = n8 % 30;
        final int n14 = n10 + n12;
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        if (this.enemyCanLand[this.squareType[n14]][0]) {
            b = true;
        }
        if (n11 > 1) {
            if (n14 < 180 && this.enemyCanLand[this.squareType[n14 + 20]][0]) {
                b2 = true;
            }
        }
        else {
            b2 = true;
        }
        if (n13 > 5) {
            if (n14 < 199 && this.enemyCanLand[this.squareType[n14 + 1]][0]) {
                b3 = true;
            }
        }
        else {
            b3 = true;
        }
        if (n13 > 5 && n11 > 1) {
            if (n14 < 179 && this.enemyCanLand[this.squareType[n14 + 21]][0]) {
                b4 = true;
            }
        }
        else {
            b4 = true;
        }
        return b && b2 && b3 && b4;
    }
    
    public boolean enemyLegalMove1(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        if (n5 == 0) {
            switch (n4) {
                case 0: {}
                case 1: {}
                case 2: {}
            }
        }
        final int n8 = n2 + n7;
        final int n9 = n + n6;
        final int n10 = n8 / 30 * 20;
        final int n11 = n8 % 30;
        final int n12 = n9 / 30;
        final int n13 = n9 % 30;
        final int n14 = n10 + n12;
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        if (this.enemyCanLand[this.squareType[n14]][n5]) {
            b = true;
        }
        if (n11 > 15) {
            if (n14 < 180 && this.enemyCanLand[this.squareType[n14 + 20]][n5]) {
                b2 = true;
            }
        }
        else {
            b2 = true;
        }
        if (n13 > 5) {
            if (n14 < 199 && this.enemyCanLand[this.squareType[n14 + 1]][n5]) {
                b3 = true;
            }
        }
        else {
            b3 = true;
        }
        if (n13 > 5 && n11 > 15) {
            if (n14 < 179 && this.enemyCanLand[this.squareType[n14 + 21]][n5]) {
                b4 = true;
            }
        }
        else {
            b4 = true;
        }
        return b && b2 && b3 && b4;
    }
    
    public boolean enemyLegalMove2(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        int n8 = n;
        int n9 = n2;
        switch (n4) {
            case 0: {
                n9 = n2 - n3;
                break;
            }
            case 1: {
                n9 = n2 + n3;
                break;
            }
            case 2: {
                n8 = n - n3;
                break;
            }
            case 3: {
                n8 = n + n3;
                break;
            }
        }
        final int n10 = n9 / 30 * 20;
        final int n11 = n9 % 30;
        final int n12 = n8 / 30;
        final int n13 = n8 % 30;
        int n16;
        int n15;
        final int n14 = n15 = (n16 = n10 + n12);
        if (n13 > 8) {
            n16 = n14 + 1;
        }
        if (n11 > 8) {
            n15 = n14 + 20;
        }
        final boolean b = this.enemyCanLand[this.squareType[n16]][2];
        final boolean b2 = this.enemyCanLand[this.squareType[n15]][2];
        return b && b2;
    }
    
    public int getRand(final int n) {
        return Math.abs(this.rand.nextInt() % n);
    }
    
    public int getSquareX(final int n) {
        int n2;
        if (n > 179) {
            n2 = (n - 180) * 30;
        }
        else if (n > 159) {
            n2 = (n - 160) * 30;
        }
        else if (n > 139) {
            n2 = (n - 140) * 30;
        }
        else if (n > 119) {
            n2 = (n - 120) * 30;
        }
        else if (n > 99) {
            n2 = (n - 100) * 30;
        }
        else if (n > 79) {
            n2 = (n - 80) * 30;
        }
        else if (n > 59) {
            n2 = (n - 60) * 30;
        }
        else if (n > 39) {
            n2 = (n - 40) * 30;
        }
        else if (n > 19) {
            n2 = (n - 20) * 30;
        }
        else {
            n2 = n * 30;
        }
        return n2;
    }
    
    public int getSquareY(final int n) {
        int n2;
        if (n > 179) {
            n2 = 270;
        }
        else if (n > 159) {
            n2 = 240;
        }
        else if (n > 139) {
            n2 = 210;
        }
        else if (n > 119) {
            n2 = 180;
        }
        else if (n > 99) {
            n2 = 150;
        }
        else if (n > 79) {
            n2 = 120;
        }
        else if (n > 59) {
            n2 = 90;
        }
        else if (n > 39) {
            n2 = 60;
        }
        else if (n > 19) {
            n2 = 30;
        }
        else {
            n2 = 0;
        }
        return n2;
    }
    
    public void goToNext() {
        this.nextLevel();
        this.redAccess = false;
        this.blueAccess = false;
        this.pause = true;
        this.levelStarted = false;
    }
    
    public void init() {
        this.myShip = new Ship();
        this.addMouseListener(new MouseHandler());
        this.addKeyListener(new KeyHandler());
        this.requestFocus();
        this.level = 1;
        this.sketch = this.createImage(600, 300);
        this.backGround = this.createImage(600, 300);
        this.g = this.getGraphics();
        this.g2 = this.sketch.getGraphics();
        this.gBack = this.backGround.getGraphics();
        this.pause = true;
        this.levelStarted = false;
        this.img = new Image[17];
        this.tracker = new MediaTracker(this);
        for (int i = 0; i < 11; ++i) {
            this.img[i] = this.getImage(this.getCodeBase(), "img" + i + ".gif");
            this.tracker.addImage(this.img[i], i);
        }
        this.img[11] = this.getImage(this.getCodeBase(), "spider1.gif");
        this.img[12] = this.getImage(this.getCodeBase(), "spider2.gif");
        this.img[13] = this.getImage(this.getCodeBase(), "liftedRed.gif");
        this.img[14] = this.getImage(this.getCodeBase(), "ice.gif");
        this.img[15] = this.getImage(this.getCodeBase(), "sharkL.gif");
        this.img[16] = this.getImage(this.getCodeBase(), "sharkR.gif");
        this.tracker.addImage(this.img[11], 11);
        this.tracker.addImage(this.img[12], 12);
        this.tracker.addImage(this.img[13], 13);
        this.tracker.addImage(this.img[14], 14);
        this.tracker.addImage(this.img[15], 16);
        this.tracker.addImage(this.img[16], 16);
        this.setBackground(Color.black);
        this.layDownBoundaries();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Panel panel = new Panel(gridBagLayout);
        panel.setBackground(Color.black);
        this.lblScore = new Label("Score: 0");
        this.lblShields = new Label("Shields: 0");
        this.lblDiamonds = new Label("Diamonds: 0");
        this.lblLevel = new Label("Level1 : 1");
        this.btnPause = new Button("Pause");
        this.btnRestart = new Button("Restart Level");
        this.btnQuit = new Button("Quit");
        this.btnPause.addActionListener(this);
        this.btnRestart.addActionListener(this);
        this.btnQuit.addActionListener(this);
        this.lblDiamonds.setForeground(Color.white);
        this.lblLevel.setForeground(Color.white);
        this.lblScore.setForeground(Color.white);
        this.lblShields.setForeground(Color.white);
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(3, 7, 2, 2);
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagLayout.setConstraints(this.lblScore, gridBagConstraints);
        panel.add(this.lblScore);
        gridBagConstraints.gridy = 1;
        gridBagLayout.setConstraints(this.lblShields, gridBagConstraints);
        panel.add(this.lblShields);
        gridBagConstraints.gridy = 2;
        gridBagLayout.setConstraints(this.lblDiamonds, gridBagConstraints);
        panel.add(this.lblDiamonds);
        final Label label = new Label("SpaceMaze (c) Callum Richards");
        final Label label2 = new Label("http://www.gamebeat.co.uk");
        label.setForeground(Color.white);
        label2.setForeground(Color.white);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.gridy = 1;
        gridBagLayout.setConstraints(this.lblLevel, gridBagConstraints);
        panel.add(this.lblLevel);
        gridBagConstraints.gridy = 2;
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        panel.add(label2);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagLayout.setConstraints(this.btnPause, gridBagConstraints);
        panel.add(this.btnPause);
        gridBagConstraints.gridy = 1;
        gridBagLayout.setConstraints(this.btnRestart, gridBagConstraints);
        panel.add(this.btnRestart);
        gridBagConstraints.gridy = 2;
        gridBagLayout.setConstraints(this.btnQuit, gridBagConstraints);
        panel.add(this.btnQuit);
        this.setLayout(new BorderLayout());
        this.add("South", panel);
        this.requestFocus();
    }
    
    public void landedOnSquare(final int n, final int n2) {
        for (int i = 0; i < 200; ++i) {
            this.isLandedHere[i] = false;
        }
        this.drawNetSquares(n, n2, this.myShip.y / 30 * 20 + this.myShip.x / 30, this.myShip.x % 30, this.myShip.y % 30);
    }
    
    public void layDownBoundaries() {
        this.enemyCanLand[0][0] = false;
        this.enemyCanLand[1][0] = true;
        this.enemyCanLand[2][0] = true;
        this.enemyCanLand[3][0] = false;
        this.enemyCanLand[4][0] = true;
        this.enemyCanLand[5][0] = true;
        this.enemyCanLand[6][0] = true;
        this.enemyCanLand[7][0] = true;
        this.enemyCanLand[8][0] = true;
        this.enemyCanLand[9][0] = false;
        this.enemyCanLand[10][0] = true;
        this.enemyCanLand[11][0] = true;
        this.enemyCanLand[12][0] = true;
        this.enemyCanLand[15][0] = true;
        this.enemyCanLand[16][0] = true;
        this.enemyCanLand[0][1] = false;
        this.enemyCanLand[1][1] = true;
        this.enemyCanLand[2][1] = true;
        this.enemyCanLand[3][1] = false;
        this.enemyCanLand[4][1] = true;
        this.enemyCanLand[5][1] = true;
        this.enemyCanLand[6][1] = true;
        this.enemyCanLand[7][1] = true;
        this.enemyCanLand[8][1] = true;
        this.enemyCanLand[9][1] = false;
        this.enemyCanLand[10][1] = false;
        this.enemyCanLand[11][1] = true;
        this.enemyCanLand[12][1] = true;
        this.enemyCanLand[15][1] = false;
        this.enemyCanLand[16][1] = false;
        this.enemyCanLand[0][2] = false;
        this.enemyCanLand[1][2] = false;
        this.enemyCanLand[2][2] = false;
        this.enemyCanLand[3][2] = false;
        this.enemyCanLand[4][2] = false;
        this.enemyCanLand[5][2] = false;
        this.enemyCanLand[6][2] = false;
        this.enemyCanLand[7][2] = false;
        this.enemyCanLand[8][2] = false;
        this.enemyCanLand[9][2] = false;
        this.enemyCanLand[10][2] = true;
        this.enemyCanLand[11][2] = false;
        this.enemyCanLand[12][2] = false;
        this.enemyCanLand[15][2] = true;
        this.enemyCanLand[16][2] = true;
        this.myShipCanLand[0] = false;
        this.myShipCanLand[1] = true;
        this.myShipCanLand[2] = true;
        this.myShipCanLand[3] = false;
        this.myShipCanLand[4] = true;
        this.myShipCanLand[5] = true;
        this.myShipCanLand[6] = true;
        this.myShipCanLand[7] = true;
        this.myShipCanLand[8] = true;
        this.myShipCanLand[9] = true;
        this.myShipCanLand[10] = false;
        this.myShipCanLand[11] = true;
        this.myShipCanLand[12] = true;
        this.myShipCanLand[15] = true;
        this.myShipCanLand[16] = false;
    }
    
    public void lostLife() {
        if (this.myShip.lives <= 0) {
            this.level = 1;
            this.pause = true;
            this.levelStarted = false;
            this.setUpLevel();
            this.resetValues();
            this.repaint();
        }
        else {
            this.myShip.x = this.myShip.startX;
            this.myShip.y = this.myShip.startY;
            for (int i = 0; i < this.enemies.size(); ++i) {
                final Enemy enemy = this.enemies.elementAt(i);
                enemy.x = enemy.x1;
                enemy.y = enemy.y1;
            }
            final Ship myShip = this.myShip;
            --myShip.lives;
            this.myShip.shields = 100;
        }
        this.updateDisplay();
        this.repaint();
    }
    
    public void nextLevel() {
        if (this.level != this.maxLevel) {
            ++this.level;
        }
        else {
            this.level = 1;
        }
        this.setUpLevel();
        this.myShip.shields = 100;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.loaded) {
            graphics.setColor(Color.white);
            graphics.setFont(new Font("Helvetica", 1, 18));
            graphics.drawString("Loading....", 300, 275);
        }
        if (!this.backDrawn) {
            this.setUpBackground();
        }
        if (!this.levelStarted && this.backDrawn) {
            this.g2.drawImage(this.backGround, 0, 0, this);
            this.drawToBeginMessage();
            graphics.drawImage(this.sketch, 0, 0, this);
        }
        graphics.drawImage(this.sketch, 0, 0, this);
        this.requestFocus();
    }
    
    public void pickUp() {
        System.out.println("pickUp called");
        for (int i = 0; i < 200; ++i) {
            if (this.isLandedHere[i]) {
                if (this.squareType[i] == 4) {
                    if (this.diamondsNeeded == 0) {
                        this.nextLevel();
                        this.redAccess = false;
                        this.blueAccess = false;
                        this.pause = true;
                        this.levelStarted = false;
                        System.out.println("LANDED HOME WITH NO DIAMONDS LEFT!!!!" + this.redAccess);
                        this.repaint();
                    }
                }
                else if (this.squareType[i] == 2) {
                    this.redKeyLandedOn();
                }
                else if (this.squareType[i] == 7) {
                    this.blueKeyLandedOn();
                }
                else if (this.squareType[i] == 8) {
                    for (int j = 0; j < this.objects.size(); ++j) {
                        final Object2 object2 = this.objects.elementAt(j);
                        if (object2.square == i) {
                            object2.landedOn();
                        }
                    }
                }
            }
        }
    }
    
    public void readParams() {
        int i = 0;
        int n = 1;
        final char[] array = new char[20];
        final String[] array2 = new String[12];
        this.maxLevel = Integer.parseInt(this.getParameter("levels"));
        for (int j = 1; j < 11; j = (byte)(j + 1)) {
            array2[j] = this.getParameter("L" + this.level + "R" + j);
        }
        do {
            for (int k = 0; k < 20; ++k) {
                array[k] = array2[n].charAt(k);
                this.squareType[i] = this.translateParam(array[k]);
                ++i;
            }
            ++n;
        } while (i < 200);
        this.paramsRead = true;
    }
    
    public void redKeyLandedOn() {
        System.out.println("Just landed on red   " + this.redAccess);
        if (this.redAccess) {
            this.redAccess = false;
            this.myShipCanLand[3] = false;
            this.enemyCanLand[3][0] = false;
            this.enemyCanLand[3][1] = false;
            System.out.println("Just landed on red2");
            for (int i = 0; i < this.objects.size(); ++i) {
                final Object2 object2 = this.objects.elementAt(i);
                if (object2.type == 3) {
                    object2.picture = 3;
                }
            }
            System.out.println("Just landed on red3");
        }
        else {
            this.redAccess = true;
            this.myShipCanLand[3] = true;
            this.enemyCanLand[3][0] = true;
            this.enemyCanLand[3][1] = true;
            for (int j = 0; j < this.objects.size(); ++j) {
                final Object2 object3 = this.objects.elementAt(j);
                if (object3.type == 3) {
                    object3.picture = 13;
                }
            }
        }
    }
    
    public void redrawAfterShipMove() {
        this.g2.drawImage(this.backGround, 0, 0, this);
        this.drawObjects();
        this.drawEnemiesNotMove();
        this.drawShip();
        this.detectCollision();
        this.g2.setColor(Color.white);
        this.g2.drawString("Play more games at www.gamebeat.co.uk - Click Here!", this.bannerX, 290);
        this.repaint();
    }
    
    public void redrawBanner() {
        this.g2.setFont(new Font("Helvetica", 3, 16));
        if (this.bannerX > -400) {
            this.bannerX -= 3;
        }
        else {
            this.bannerX = 600;
        }
        this.g2.setColor(Color.white);
        this.g2.drawString("Play more games at www.gamebeat.co.uk - Click Here!", this.bannerX, 290);
    }
    
    public void resetValues() {
        this.myShip.lives = 5;
        this.myShip.shields = 100;
        this.level = 1;
        this.diamondsNeeded = 0;
    }
    
    public void run() {
        if (!this.loaded) {
            try {
                this.tracker.waitForAll();
            }
            catch (InterruptedException ex) {}
            this.loaded = true;
        }
        do {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex2) {
                System.out.println("interrupted");
            }
            if (!this.pause && this.backDrawn && this.levelStarted) {
                this.g2.drawImage(this.backGround, 0, 0, this);
                this.drawObjects();
                this.drawEnemies();
                this.drawShip();
                this.detectCollision();
                this.redrawBanner();
                this.repaint();
            }
        } while (Thread.currentThread() == this.mainThread);
    }
    
    public void setUpBackground() {
        this.layDownBoundaries();
        this.readParams();
        this.drawBack();
    }
    
    public void setUpLevel() {
        this.objects.removeAllElements();
        this.enemies.removeAllElements();
        this.enemiesMade = false;
        this.myShip.x = 0;
        this.myShip.y = 0;
        this.readParams();
        this.backDrawn = false;
        this.drawBack();
        this.pause = true;
        this.layDownBoundaries();
    }
    
    public boolean shipLegalMove(final int n, final int n2, final int n3, final int n4) {
        int n5 = n;
        int n6 = n2;
        switch (n4) {
            case 0: {
                n6 = n2 - n3;
                break;
            }
            case 1: {
                n6 = n2 + n3;
                break;
            }
            case 2: {
                n5 = n - n3;
                break;
            }
            case 3: {
                n5 = n + n3;
                break;
            }
        }
        final int n7 = n6 / 30 * 20;
        final int n8 = n6 % 30;
        final int n9 = n5 / 30;
        final int n10 = n5 % 30;
        final int n11 = n7 + n9;
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        if (this.myShipCanLand[this.squareType[n11]]) {
            b = true;
        }
        if (n8 > 15) {
            if (n11 < 180 && this.myShipCanLand[this.squareType[n11 + 20]]) {
                b2 = true;
            }
        }
        else {
            b2 = true;
        }
        if (n10 > 4) {
            if (n11 < 199 && this.myShipCanLand[this.squareType[n11 + 1]]) {
                b3 = true;
            }
        }
        else {
            b3 = true;
        }
        if (n10 > 4 && n8 > 15) {
            if (n11 < 179 && this.myShipCanLand[this.squareType[n11 + 21]]) {
                b4 = true;
            }
        }
        else {
            b4 = true;
        }
        return b && b2 && b3 && b4;
    }
    
    public void start() {
        if (this.mainThread == null) {
            (this.mainThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
    }
    
    public int translateParam(final char c) {
        int n = 0;
        switch (c) {
            case '.': {
                n = 1;
                break;
            }
            case '#': {
                n = 0;
                break;
            }
            case 'B': {
                n = 10;
                break;
            }
            case 'R': {
                n = 3;
                break;
            }
            case 'P': {
                n = 4;
                break;
            }
            case 'G': {
                n = 9;
                break;
            }
            case '*': {
                n = 8;
                break;
            }
            case 'E': {
                n = 6;
                break;
            }
            case 'S': {
                n = 5;
                break;
            }
            case 'b': {
                n = 7;
                break;
            }
            case 'r': {
                n = 2;
                break;
            }
            case 'V': {
                n = 11;
                break;
            }
            case 'K': {
                n = 15;
                break;
            }
            default: {
                n = 0;
                break;
            }
        }
        return n;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void updateDisplay() {
        this.lblDiamonds.setText("Diamonds Needed " + this.diamondsNeeded);
        this.lblShields.setText("Shields " + this.myShip.shields + "   Lives: " + this.myShip.lives);
        this.lblScore.setText("Score : " + this.myShip.score);
        this.lblLevel.setText("Level " + this.level);
    }
    
    public void updateSketch() {
        this.drawShip();
        this.repaint();
    }
    
    class KeyHandler extends KeyAdapter
    {
        public void keyPressed(final KeyEvent keyEvent) {
            System.out.println("KEY PRESSED : " + keyEvent.getKeyCode());
            if (!SpaceMaze.this.pause) {
                switch (keyEvent.getKeyCode()) {
                    case 38: {
                        SpaceMaze.this.myShip.moveUp();
                        break;
                    }
                    case 40: {
                        SpaceMaze.this.myShip.moveDown();
                        break;
                    }
                    case 37: {
                        SpaceMaze.this.myShip.moveLeft();
                        break;
                    }
                    case 39: {
                        SpaceMaze.this.myShip.moveRight();
                        break;
                    }
                    case 112: {
                        SpaceMaze.this.pause = true;
                        break;
                    }
                    case 113: {
                        SpaceMaze.this.pause = false;
                        break;
                    }
                    case 114: {
                        if (SpaceMaze.this.sleepFor > 50) {
                            SpaceMaze.this.sleepFor /= 2;
                            break;
                        }
                        break;
                    }
                    case 115: {
                        SpaceMaze.this.setUpLevel();
                        break;
                    }
                    case 116: {
                        SpaceMaze.this.restartEnemy = true;
                        break;
                    }
                    case 83: {
                        if (SpaceMaze.this.level == 1) {
                            SpaceMaze.this.setUpLevel();
                        }
                        SpaceMaze.this.levelStarted = true;
                        SpaceMaze.this.backDrawn = true;
                        SpaceMaze.this.pause = false;
                        System.out.println("typed s    " + SpaceMaze.this.loaded + SpaceMaze.this.backDrawn + SpaceMaze.this.levelStarted);
                        break;
                    }
                    case 84: {
                        SpaceMaze.this.demoDraw();
                        break;
                    }
                    case 50: {
                        SpaceMaze.this.level = 1;
                        SpaceMaze.this.goToNext();
                        break;
                    }
                    case 51: {
                        SpaceMaze.this.level = 2;
                        SpaceMaze.this.goToNext();
                        break;
                    }
                    case 52: {
                        SpaceMaze.this.level = 2;
                        SpaceMaze.this.goToNext();
                        break;
                    }
                    case 53: {
                        SpaceMaze.this.level = 4;
                        SpaceMaze.this.goToNext();
                        break;
                    }
                    case 54: {
                        SpaceMaze.this.level = 5;
                        SpaceMaze.this.goToNext();
                        break;
                    }
                    case 55: {
                        SpaceMaze.this.level = 6;
                        SpaceMaze.this.goToNext();
                        break;
                    }
                    case 56: {
                        SpaceMaze.this.level = 7;
                        SpaceMaze.this.goToNext();
                        break;
                    }
                    case 57: {
                        SpaceMaze.this.level = 8;
                        SpaceMaze.this.goToNext();
                        break;
                    }
                    case 48: {
                        SpaceMaze.this.level = 9;
                        SpaceMaze.this.goToNext();
                        break;
                    }
                    case 32: {
                        System.out.println("Pressed space");
                        SpaceMaze.this.pickUp();
                        break;
                    }
                }
            }
            else if (keyEvent.getKeyCode() == 83) {
                if (SpaceMaze.this.level == 1) {
                    SpaceMaze.this.setUpLevel();
                }
                SpaceMaze.this.levelStarted = true;
                SpaceMaze.this.backDrawn = true;
                SpaceMaze.this.pause = false;
            }
        }
    }
    
    class MouseHandler extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
            if (mouseEvent.getY() < 300 && mouseEvent.getY() > 270) {
                try {
                    SpaceMaze.this.getAppletContext().showDocument(new URL("http://www.gamebeat.co.uk/"));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    class Ship
    {
        int x;
        int startX;
        int startY;
        int y;
        int speed;
        int lives;
        int shields;
        int score;
        
        Ship() {
            this.speed = 10;
            this.lives = 5;
            this.shields = 100;
            this.score = 0;
        }
        
        public void moveDown() {
            if (SpaceMaze.this.shipLegalMove(this.x, this.y, this.speed, 1)) {
                this.y += this.speed;
                SpaceMaze.this.redrawAfterShipMove();
            }
        }
        
        public void moveLeft() {
            if (SpaceMaze.this.shipLegalMove(this.x, this.y, this.speed, 2)) {
                this.x -= this.speed;
                SpaceMaze.this.redrawAfterShipMove();
            }
        }
        
        public void moveRight() {
            if (SpaceMaze.this.shipLegalMove(this.x, this.y, this.speed, 3)) {
                this.x += this.speed;
                SpaceMaze.this.redrawAfterShipMove();
            }
        }
        
        public void moveUp() {
            if (SpaceMaze.this.shipLegalMove(this.x, this.y, this.speed, 0)) {
                this.y -= this.speed;
                SpaceMaze.this.redrawAfterShipMove();
            }
        }
        
        public void newLife() {
            ++this.lives;
        }
        
        public void restart() {
            this.x = this.startX;
            this.y = this.startY;
            --this.lives;
        }
        
        public void start(final int n, final int startY) {
            this.startX = n;
            this.x = n;
            this.startY = startY;
            this.y = this.startY;
        }
    }
    
    class Enemy
    {
        int x;
        int y;
        int x1;
        int y1;
        int direction;
        int speed;
        int type;
        int frame;
        int changeTime;
        int toIncY;
        int toIncX;
        
        public Enemy() {
            this.speed = 5;
        }
        
        public Enemy(final int n, final int n2) {
            this.speed = 5;
            this.x = n;
            this.x1 = n;
            this.y = n2;
            this.y1 = n2;
            this.type = 0;
        }
        
        public Enemy(final int n, final int n2, final int type) {
            this.speed = 5;
            this.x = n;
            this.y = n2;
            this.x1 = n;
            this.y1 = n2;
            this.type = type;
        }
        
        public void changeDirection() {
            this.direction = SpaceMaze.this.getRand(4);
        }
        
        public void changeFrame() {
            if (this.frame == 0) {
                this.frame = 1;
            }
            else {
                this.frame = 0;
            }
        }
        
        public void deIce(final int n, final int n2) {
            SpaceMaze.this.squareType[n / 30 + n2 / 30 * 20] = 16;
        }
        
        public int getX() {
            return this.x;
        }
        
        public int getY() {
            return this.y;
        }
        
        public void move() {
            switch (this.direction) {
                case 0: {
                    this.moveUp();
                    break;
                }
                case 1: {
                    this.moveDown();
                    break;
                }
                case 2: {
                    this.moveLeft();
                    break;
                }
                case 3: {
                    this.moveRight();
                    break;
                }
            }
            if (this.type == 2) {
                for (int i = 0; i < SpaceMaze.this.objects.size(); ++i) {
                    final Object2 object2 = SpaceMaze.this.objects.elementAt(i);
                    if (object2.picture == 14 && this.x - this.x % 30 == object2.x && this.y - this.y % 30 == object2.y) {
                        object2.changePic(10);
                        System.out.println("Changed One");
                        this.deIce(object2.x, object2.y);
                    }
                }
            }
        }
        
        public void moveDown() {
            if (this.y < 370) {
                this.y += this.speed;
            }
        }
        
        public void moveLeft() {
            if (this.x > 30) {
                this.x -= this.speed;
            }
        }
        
        public void moveRight() {
            if (this.x < 570) {
                this.x += this.speed;
            }
        }
        
        public void moveUp() {
            if (this.y > 30) {
                this.y -= this.speed;
            }
        }
        
        public void specMove() {
            this.y += this.toIncY;
            this.x += this.toIncX;
        }
        
        public boolean testForIce() {
            return false;
        }
    }
    
    class Object2
    {
        int type;
        int picture;
        int square;
        int x;
        int y;
        boolean visible;
        
        public Object2() {
            this.type = 8;
            this.picture = this.type;
            this.visible = true;
        }
        
        public Object2(final int type, final int picture, final int square) {
            this.type = type;
            this.picture = picture;
            this.square = square;
            this.x = SpaceMaze.this.getSquareX(square);
            this.y = SpaceMaze.this.getSquareY(square);
            this.visible = true;
        }
        
        public Object2(final int type, final int picture, final int n, final int n2) {
            this.type = type;
            this.picture = picture;
            this.square = n2 / 600 + n / 30;
            this.visible = true;
        }
        
        public Object2(final int type, final int picture, final int square, final boolean visible) {
            this.type = type;
            this.picture = picture;
            this.square = square;
            this.x = SpaceMaze.this.getSquareX(square);
            this.y = SpaceMaze.this.getSquareY(square);
            this.visible = visible;
        }
        
        public void changePic(final int picture) {
            this.picture = picture;
        }
        
        public void landedOn() {
            switch (this.type) {
                case 2: {
                    SpaceMaze.this.redKeyLandedOn();
                    break;
                }
                case 7: {
                    SpaceMaze.this.blueKeyLandedOn();
                    break;
                }
                case 8: {
                    SpaceMaze.this.diamondLandedOn(this.square);
                    SpaceMaze.this.onRedKey = false;
                    SpaceMaze.this.onBlueKey = false;
                    break;
                }
                default: {
                    SpaceMaze.this.onRedKey = false;
                    SpaceMaze.this.onBlueKey = false;
                    break;
                }
            }
        }
    }
}
