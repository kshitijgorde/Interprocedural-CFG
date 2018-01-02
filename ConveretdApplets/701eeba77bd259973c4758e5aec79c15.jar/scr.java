import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Color;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class scr extends Applet implements KeyListener, Runnable
{
    private int xsize;
    private int ysize;
    private Image img;
    private Image title;
    private Image gfx;
    private Image gfx2;
    private int xscalesize;
    private int yscalesize;
    private int xleft;
    private int xright;
    private int ytop;
    private int ybottom;
    private Image scoImg;
    private Graphics paper;
    private Graphics scores;
    AudioClip s_pickUp;
    AudioClip s_pain;
    AudioClip s_dead;
    AudioClip s_message;
    AudioClip s_spell;
    AudioClip s_banish;
    AudioClip s_explode;
    AudioClip s_won;
    private String msg1;
    private String msg2;
    private int gold;
    private int food;
    private int health;
    private int walking;
    private int gameState;
    private int traderMsgNum;
    private cauldron ourCauldron;
    private boolean nearCauldron;
    private items item;
    private int xOffset;
    private int yOffset;
    private int xOrigin;
    private int yOrigin;
    private ent ourEnt;
    private inv inventory;
    private int speed;
    private bigMsg trade;
    private boolean[] publicMsg;
    private lev curLev;
    private File curFile;
    Thread proc;
    
    public void stop() {
        if (this.proc != null) {
            this.proc.stop();
            this.proc = null;
        }
    }
    
    public boolean doDir(final ent ent) {
        boolean b = false;
        boolean b2 = false;
        final ent ent2 = new ent(ent.x, ent.y, ent.z);
        if (ent.dirD) {
            ent.y += ent.moveSpeed;
            b = true;
            if (!this.canWeMove(ent)) {
                ent.y = ent2.y;
                b = false;
            }
        }
        if (ent.dirU) {
            ent.y -= ent.moveSpeed;
            b = true;
            if (!this.canWeMove(ent)) {
                ent.y = ent2.y;
                b = false;
            }
        }
        if (ent.dirL) {
            ent.x -= ent.moveSpeed;
            if (ent.z < 110) {
                ent.i = ent.z + 1;
            }
            b2 = true;
            if (!this.canWeMove(ent)) {
                ent.x = ent2.x;
                b2 = false;
            }
        }
        if (ent.dirR) {
            ent.x += ent.moveSpeed;
            if (ent.z < 110) {
                ent.i = ent.z;
            }
            b2 = true;
            if (!this.canWeMove(ent)) {
                ent.x = ent2.x;
                b2 = false;
            }
        }
        if (b || b2) {
            ent.flip = !ent.flip;
            return true;
        }
        return false;
    }
    
    public int getXblockMod(final int n) {
        return n % 20;
    }
    
    public void doInit() {
        this.xsize = 240;
        this.ysize = 240;
        this.img = this.createImage(this.xsize, this.ysize);
        this.title = this.getImage(this.getCodeBase(), "title.jpg");
        this.gfx = this.getImage(this.getCodeBase(), "scr.jpg");
        this.gfx2 = this.getImage(this.getCodeBase(), "scr2.jpg");
        this.xscalesize = 200;
        this.yscalesize = 200;
        this.xleft = 5;
        this.ytop = 5;
        this.xright = this.xleft + this.xscalesize;
        this.ybottom = this.ytop + this.yscalesize;
        this.scoImg = this.createImage(this.xscalesize, this.yscalesize / 3);
        this.setBackground(Color.gray);
        this.setForeground(Color.white);
        (this.paper = this.img.getGraphics()).setFont(new Font("", 0, 10));
        (this.scores = this.scoImg.getGraphics()).setFont(new Font("", 0, 10));
        this.gold = 0;
        this.food = 20;
        this.health = 100;
        this.msg1 = "";
        this.msg2 = "* Click in window and press any key *";
        this.walking = 0;
        this.traderMsgNum = 1;
        this.ourCauldron = new cauldron();
        this.nearCauldron = false;
        this.item = new items();
        this.xOffset = 20;
        this.yOffset = 20;
        this.xOrigin = 7;
        this.yOrigin = 8;
        this.ourEnt = new ent(this.xOrigin * 20, this.yOrigin * 20, 98);
        this.ourEnt.dirL = false;
        this.ourEnt.dirR = false;
        this.ourEnt.dirU = false;
        this.ourEnt.dirD = false;
        this.ourEnt.moves = true;
        this.ourEnt.moveSpeed = 4;
        this.inventory = new inv(this.scores, this.gfx);
        this.speed = 40;
        this.trade = new bigMsg(this.paper, this.gfx);
        this.publicMsg = new boolean[20];
        int n = 0;
        do {
            this.publicMsg[n] = false;
        } while (++n < 10);
        this.updateScores();
        this.inventory.drawInv();
        this.gameState = -2;
    }
    
    private void scrollScreen() {
        if (this.ourEnt.dirD && this.getYscr(this.ourEnt.y) > 160) {
            this.yOffset += 4;
            if (this.yOffset == 40) {
                this.yOffset = 20;
                ++this.yOrigin;
                this.curLev.moveDown();
                this.curLev.drawBottom(this.xOrigin, this.yOrigin);
            }
        }
        if (this.ourEnt.dirU && this.getYscr(this.ourEnt.y) < 60) {
            this.yOffset -= 4;
            if (this.yOffset == 0) {
                this.yOffset = 20;
                --this.yOrigin;
                this.curLev.moveUp();
                this.curLev.drawTop(this.xOrigin, this.yOrigin);
            }
        }
        if (this.ourEnt.dirL && this.getXscr(this.ourEnt.x) < 60) {
            this.xOffset -= 4;
            if (this.xOffset == 0) {
                this.xOffset = 20;
                --this.xOrigin;
                this.curLev.moveLeft();
                this.curLev.drawLeft(this.xOrigin, this.yOrigin);
            }
        }
        if (this.ourEnt.dirR && this.getXscr(this.ourEnt.x) > 160) {
            this.xOffset += 4;
            if (this.xOffset == 40) {
                this.xOffset = 20;
                ++this.xOrigin;
                this.curLev.moveRight();
                this.curLev.drawRight(this.xOrigin, this.yOrigin);
            }
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        if (this.gameState == 0) {
            this.getLevel();
            this.gameState = 1;
        }
        switch (keyCode) {
            case 40: {
                this.ourEnt.dirD = true;
                break;
            }
            case 37: {
                this.ourEnt.dirL = true;
                break;
            }
            case 39: {
                this.ourEnt.dirR = true;
                break;
            }
            case 38: {
                this.ourEnt.dirU = true;
                break;
            }
            case 89: {
                this.gameState = 101;
                break;
            }
            case 78: {
                this.gameState = 1;
                this.curLev.updateWindow(this.xOrigin, this.yOrigin);
                break;
            }
            case 10: {
                this.gameState = 1;
                this.curLev.updateWindow(this.xOrigin, this.yOrigin);
                break;
            }
            case 49: {
                this.speed = 5;
                this.msg1 = "Speed = 5ms Sleep";
                this.msg2 = "(Fastest Mode)";
                this.updateScores();
                break;
            }
            case 50: {
                this.speed = 10;
                this.msg1 = "Speed = 10ms Sleep";
                this.updateScores();
                break;
            }
            case 51: {
                this.speed = 15;
                this.msg1 = "Speed = 15ms Sleep";
                this.updateScores();
                break;
            }
            case 52: {
                this.speed = 20;
                this.msg1 = "Speed = 20ms Sleep";
                this.updateScores();
                break;
            }
            case 53: {
                this.speed = 25;
                this.msg1 = "Speed = 25ms Sleep";
                this.updateScores();
                break;
            }
            case 54: {
                this.speed = 30;
                this.msg1 = "Speed = 30ms Sleep";
                this.updateScores();
                break;
            }
            case 55: {
                this.speed = 35;
                this.msg1 = "Speed = 40ms Sleep";
                this.updateScores();
                break;
            }
            case 56: {
                this.speed = 40;
                this.msg1 = "Speed = 50ms Sleep";
                this.updateScores();
                break;
            }
            case 57: {
                this.speed = 50;
                this.msg1 = "Speed = 60ms Sleep";
                this.msg2 = "(Slowest Mode)";
                this.updateScores();
                break;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        if (this.proc == null) {
            (this.proc = new Thread(this)).start();
        }
    }
    
    public void transform(final int z) {
        final int xblock = this.getXblock(this.ourEnt.x);
        for (int yblock = this.getYblock(this.ourEnt.y), i = yblock - 2; i < yblock + 2; ++i) {
            for (int j = xblock - 2; j < xblock + 2; ++j) {
                final int n = j + i * 200;
                if (this.curLev.curEnt[n].z < 50 && this.curLev.curEnt[n].z > 0) {
                    this.curLev.curEnt[n].z = z;
                }
            }
        }
    }
    
    public int getYblock(final int n) {
        return n / 20;
    }
    
    public void getLevel() {
        try {
            this.curFile = new File(new URL(this.getDocumentBase(), "level1.dat"));
        }
        catch (IOException ex) {}
        this.curLev = new lev(this.curFile.getData(), this.paper, this.gfx, this.gfx2);
        this.paper.setColor(Color.black);
        this.paper.fillRect(0, 0, this.xsize, this.ysize);
        this.curLev.setUpWindow(this.xOrigin, this.yOrigin);
        this.updateScores();
        this.inventory.drawInv();
    }
    
    public void dropItem() {
        final int id = this.inventory.getID();
        if (id > 0) {
            final int nearestSpace = this.curLev.getNearestSpace(this.getXblock(this.ourEnt.x), this.getYblock(this.ourEnt.y));
            if (nearestSpace > 0) {
                if (this.curLev.collide(this.curLev.curEnt[nearestSpace], this.ourEnt) == 0) {
                    this.curLev.curEnt[nearestSpace].z = id;
                    this.curLev.curEnt[nearestSpace].i = id;
                    this.drawEnt(this.curLev.curEnt[nearestSpace]);
                    this.inventory.removeFromInv();
                    this.msg1 = "You drop the " + this.item.getName(id);
                    this.s_pickUp.play();
                    this.updateScores();
                }
                else {
                    this.msg1 = "You can't drop that here.";
                    this.updateScores();
                }
            }
            else {
                this.msg1 = "There's no space to drop";
                this.msg2 = "that here.";
                this.updateScores();
            }
        }
    }
    
    public void moveOthers() {
        for (int i = 0; i < this.curLev.numInRm; ++i) {
            if (this.gameState == 1) {
                final ent asBlock = this.curLev.movInRm[i];
                this.delEnt(asBlock);
                if (this.offScreen(i)) {
                    this.setAsBlock(asBlock);
                    this.curLev.removeFromRm(i);
                }
                else {
                    if (asBlock.freeze != 0) {
                        if (asBlock.freeze < 0) {
                            this.curLev.removeFromRm(i);
                            asBlock.z = 0;
                            asBlock.i = 0;
                        }
                        else {
                            final ent ent = asBlock;
                            --ent.freeze;
                        }
                    }
                    else {
                        Label_0693: {
                            if (!this.doDir(asBlock)) {
                                switch (asBlock.z) {
                                    case 87: {
                                        if (asBlock.dirL) {
                                            asBlock.dirL = false;
                                            asBlock.dirR = true;
                                        }
                                        else {
                                            asBlock.dirL = true;
                                            asBlock.dirR = false;
                                        }
                                        break Label_0693;
                                    }
                                    case 88: {
                                        if (asBlock.dirU) {
                                            asBlock.dirU = false;
                                            asBlock.dirD = true;
                                        }
                                        else {
                                            asBlock.dirU = true;
                                            asBlock.dirD = false;
                                        }
                                        break Label_0693;
                                    }
                                    case 94: {
                                        if (asBlock.x < this.ourEnt.x) {
                                            asBlock.dirR = true;
                                            asBlock.dirL = false;
                                        }
                                        if (asBlock.x > this.ourEnt.x) {
                                            asBlock.dirL = true;
                                            asBlock.dirR = false;
                                        }
                                        if (asBlock.y < this.ourEnt.y) {
                                            asBlock.dirD = true;
                                            asBlock.dirU = false;
                                        }
                                        if (asBlock.y > this.ourEnt.y) {
                                            asBlock.dirU = true;
                                            asBlock.dirD = false;
                                        }
                                        break Label_0693;
                                    }
                                    case 102: {
                                        if (asBlock.x < this.ourEnt.x) {
                                            asBlock.dirR = true;
                                            asBlock.dirL = false;
                                        }
                                        if (asBlock.x > this.ourEnt.x) {
                                            asBlock.dirL = true;
                                            asBlock.dirR = false;
                                        }
                                        if (asBlock.y < this.ourEnt.y) {
                                            asBlock.dirD = true;
                                            asBlock.dirU = false;
                                        }
                                        if (asBlock.y > this.ourEnt.y) {
                                            asBlock.dirU = true;
                                            asBlock.dirD = false;
                                        }
                                        break Label_0693;
                                    }
                                    case 92: {
                                        if (asBlock.x < this.ourEnt.x) {
                                            asBlock.dirR = true;
                                            asBlock.dirL = false;
                                        }
                                        if (asBlock.x > this.ourEnt.x) {
                                            asBlock.dirL = true;
                                            asBlock.dirR = false;
                                        }
                                        if (asBlock.y < this.ourEnt.y) {
                                            asBlock.dirD = true;
                                            asBlock.dirU = false;
                                        }
                                        if (asBlock.y > this.ourEnt.y) {
                                            asBlock.dirU = true;
                                            asBlock.dirD = false;
                                        }
                                        if ((int)(6.0 * Math.random()) + 1 < 4) {
                                            break Label_0693;
                                        }
                                        break;
                                    }
                                }
                                switch ((int)(6.0 * Math.random()) + 1) {
                                    case 1: {
                                        asBlock.dirU = true;
                                        asBlock.dirD = false;
                                        break;
                                    }
                                    case 2: {
                                        asBlock.dirR = true;
                                        asBlock.dirL = false;
                                        break;
                                    }
                                    case 3: {
                                        asBlock.dirD = true;
                                        asBlock.dirU = false;
                                        break;
                                    }
                                    case 4: {
                                        asBlock.dirL = true;
                                        asBlock.dirR = false;
                                        break;
                                    }
                                    case 5: {
                                        asBlock.dirL = true;
                                        asBlock.dirR = false;
                                        asBlock.dirU = false;
                                        asBlock.dirD = false;
                                        break;
                                    }
                                    case 6: {
                                        asBlock.dirR = true;
                                        asBlock.dirL = false;
                                        asBlock.dirU = false;
                                        asBlock.dirD = false;
                                        break;
                                    }
                                }
                            }
                        }
                        if (asBlock.z == 85 || asBlock.z == 106) {
                            if (asBlock.x < this.ourEnt.x) {
                                asBlock.dirR = true;
                                asBlock.dirL = false;
                            }
                            if (asBlock.x > this.ourEnt.x) {
                                asBlock.dirL = true;
                                asBlock.dirR = false;
                            }
                            if (asBlock.y < this.ourEnt.y) {
                                asBlock.dirD = true;
                                asBlock.dirU = false;
                            }
                            if (asBlock.y > this.ourEnt.y) {
                                asBlock.dirU = true;
                                asBlock.dirD = false;
                            }
                        }
                        if (asBlock.z == 100) {
                            switch ((int)(4.0 * Math.random()) + 1) {
                                case 1: {
                                    asBlock.dirU = true;
                                    asBlock.dirD = false;
                                    break;
                                }
                                case 2: {
                                    asBlock.dirR = true;
                                    asBlock.dirL = false;
                                    break;
                                }
                                case 3: {
                                    asBlock.dirD = true;
                                    asBlock.dirU = false;
                                    break;
                                }
                                case 4: {
                                    asBlock.dirL = true;
                                    asBlock.dirR = false;
                                    break;
                                }
                            }
                        }
                    }
                    this.drawEnt(asBlock);
                }
            }
        }
    }
    
    public void blowUp(final ent ent) {
        final int xblock = this.getXblock(ent.x);
        for (int yblock = this.getYblock(ent.y), i = yblock - 2; i < yblock + 2; ++i) {
            for (int j = xblock - 2; j < xblock + 2; ++j) {
                final int n = j + i * 200;
                if (this.curLev.curEnt[n].z < 50 && this.curLev.curEnt[n].z > 0) {
                    this.curLev.curEnt[n].z = 0;
                }
            }
        }
    }
    
    public int getYscr(final int n) {
        return 100 + (n - this.yOrigin * 20);
    }
    
    public int ticker(int n) {
        this.paper.setColor(Color.black);
        this.paper.fillRect(65, 110, 150, 10);
        this.paper.setColor(Color.blue);
        this.paper.fillRect(65, 110, n, 10);
        if (++n > 130) {
            n = 0;
        }
        return n;
    }
    
    public void drawEnt(final ent ent) {
        this.curLev.doDraw(this.getXscr(ent.x), this.getYscr(ent.y), ent.i, ent.flip);
    }
    
    public void doCauldronThing() {
        final int id = this.inventory.getID();
        if (id > 69 && id < 85) {
            this.msg1 = "You read the " + this.inventory.getName();
            this.trade.doMsg(id, this.getXscr(this.ourEnt.x), this.getYscr(this.ourEnt.y));
            this.s_message.play();
            this.updateScores();
            this.gameState = 99;
        }
        if (id > 109) {
            if (id == 110) {
                this.msg1 = "You feel much better!";
                this.msg2 = "(Health +75)";
                this.health += 75;
            }
            if (id == 111) {
                this.msg1 = "You feel excellent!";
                this.msg2 = "(Health +125)";
                this.health += 125;
            }
            if (id > 111 && id < 120) {
                this.msg1 = "You cast the " + this.item.getName(id) + "!";
                final int n = this.getXblock(this.ourEnt.x) + this.getYblock(this.ourEnt.y) * 200;
                if (this.ourEnt.i == 99) {
                    this.curLev.curEnt[n].dirL = true;
                    this.curLev.curEnt[n].dirR = false;
                }
                else {
                    this.curLev.curEnt[n].dirL = false;
                    this.curLev.curEnt[n].dirR = true;
                }
                this.curLev.curEnt[n].z = id;
                this.curLev.curEnt[n].moves = true;
                this.curLev.addToMvArray(n);
            }
            if (id == 120) {
                int xOrigin = this.xOrigin;
                int yOrigin = this.yOrigin;
                while (this.curLev.curEnt[xOrigin + yOrigin * 200].z != 49) {
                    if (++xOrigin > 199) {
                        xOrigin = 0;
                        if (++yOrigin <= 135) {
                            continue;
                        }
                        yOrigin = 1;
                    }
                }
                this.xOrigin = xOrigin + 1;
                this.yOrigin = yOrigin;
                this.ourEnt.x = this.xOrigin * 20;
                this.ourEnt.y = this.yOrigin * 20;
                this.curLev.setUpWindow(this.xOrigin, this.yOrigin);
                this.msg2 = "You feel strange.";
            }
            if (id == 122 || id == 123) {
                if (id == 122) {
                    this.transform(7);
                }
                if (id == 123) {
                    this.transform(32);
                }
                this.curLev.updateWindow(this.xOrigin, this.yOrigin);
                this.msg2 = "Wow!  Quite an effect!";
            }
            if (id == 124) {
                for (int i = 0; i < this.curLev.numInRm; ++i) {
                    this.curLev.movInRm[i].z = 87;
                }
                this.msg2 = "Everyone turns into bats!";
            }
            this.inventory.removeFromInv();
            this.s_spell.play();
            this.updateScores();
        }
        if ((id > 49 & id < 70) && this.nearCauldron && id > 0) {
            this.s_pickUp.play();
            this.msg1 = "You add the " + this.inventory.getName();
            this.inventory.removeFromInv();
            this.curLev.reDist(id);
            final int addItem = this.ourCauldron.addItem(id);
            if (addItem > 0) {
                this.msg2 = "You made a " + this.item.getName(addItem) + "!";
                final int nearestSpace = this.curLev.getNearestSpace(this.getXblock(this.ourEnt.x), this.getYblock(this.ourEnt.y));
                if (nearestSpace > 0) {
                    this.s_spell.play();
                    this.curLev.curEnt[nearestSpace].z = addItem;
                    this.curLev.curEnt[nearestSpace].i = addItem;
                    this.drawEnt(this.curLev.curEnt[nearestSpace]);
                    if (addItem > 84 && addItem < 102) {
                        this.curLev.curEnt[nearestSpace].dirL = true;
                        this.curLev.curEnt[nearestSpace].dirU = true;
                        this.curLev.curEnt[nearestSpace].moves = true;
                        this.curLev.addToMvArray(nearestSpace);
                    }
                }
            }
            else {
                this.msg2 = "Nothing happens!";
            }
            this.updateScores();
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.img, this.xleft, this.ytop, this.xright, this.ybottom, this.xOffset, this.yOffset, this.xOffset + 200, this.yOffset + 200, this);
        graphics.drawImage(this.scoImg, this.xleft, this.ybottom + 5, this);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public boolean offScreen(final int n) {
        final int xscr = this.getXscr(this.curLev.movInRm[n].x);
        final int yscr = this.getYscr(this.curLev.movInRm[n].y);
        return xscr > this.xsize || xscr < 0 || yscr > this.ysize || yscr < 0;
    }
    
    public void updateScores() {
        this.scores.setColor(Color.black);
        this.scores.fillRect(0, 0, this.xscalesize, 36);
        this.scores.setColor(Color.lightGray);
        this.scores.drawString("Gold:", 5, 35);
        this.scores.drawString("Food:", 70, 35);
        this.scores.drawString("Health:", 140, 35);
        this.scores.setColor(Color.white);
        this.scores.drawString(this.msg1, 5, 10);
        this.scores.drawString(this.msg2, 5, 20);
        this.msg1 = "";
        this.msg2 = "";
        this.scores.setColor(Color.yellow);
        this.scores.drawString(this.gold + "", 37, 35);
        this.scores.drawString(this.food + "", 104, 35);
        this.scores.drawString(this.health + "", 180, 35);
    }
    
    public int getYblockMod(final int n) {
        return n % 20;
    }
    
    private void publicM(final int n, final int n2, final int n3) {
        this.publicMsg[n] = true;
        this.trade.doMsg(100 + n, n2, n3);
        this.s_message.play();
        this.gameState = 99;
    }
    
    public void delEnt(final ent ent) {
        this.paper.fillRect(this.getXscr(ent.x), this.getYscr(ent.y), 20, 20);
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 40: {
                this.ourEnt.dirD = false;
                break;
            }
            case 37: {
                this.ourEnt.dirL = false;
                break;
            }
            case 39: {
                this.ourEnt.dirR = false;
                break;
            }
            case 38: {
                this.ourEnt.dirU = false;
                break;
            }
            case 68: {
                this.dropItem();
                break;
            }
            case 9: {
                this.inventory.changeItem();
                this.msg1 = "Selected " + this.inventory.getName();
                this.updateScores();
                break;
            }
            case 32: {
                this.doCauldronThing();
                break;
            }
        }
    }
    
    public int getXscr(final int n) {
        return 100 + (n - this.xOrigin * 20);
    }
    
    public boolean canWeMove(final ent ent) {
        final int xblock = this.getXblock(ent.x);
        int i = 0;
        while (i <= (i = this.getYblock(ent.y)) + 1) {
            for (int j = xblock; j <= xblock + 1; ++j) {
                final int n = j + i * 200;
                final int collide = this.curLev.collide(ent, this.curLev.curEnt[n]);
                if (collide > 0) {
                    if (ent.z == 98) {
                        this.nearCauldron = false;
                        if (collide > 49 && collide < 59) {
                            if (collide == 50) {
                                this.msg1 = "You found some Gold!";
                                ++this.gold;
                                this.curLev.reDist(collide);
                                if (!this.publicMsg[1]) {
                                    this.publicM(1, this.getXscr(ent.x), this.getYscr(ent.y));
                                }
                            }
                            if (collide == 51) {
                                this.msg1 = "You found extra health! (+10)";
                                this.health += 10;
                                if (!this.publicMsg[6]) {
                                    this.publicM(6, this.getXscr(ent.x), this.getYscr(ent.y));
                                }
                            }
                            if (collide == 53) {
                                this.msg1 = "Mmm, you eat some food.";
                                this.msg2 = "(Food +10)";
                                this.food += 10;
                                if (!this.publicMsg[2]) {
                                    this.publicM(2, this.getXscr(ent.x), this.getYscr(ent.y));
                                }
                            }
                            if (collide == 54) {
                                this.msg1 = "Ugh! You eat bad food.";
                                this.msg2 = "(Health -15)";
                                this.health -= 15;
                            }
                            if (collide == 55 && !this.publicMsg[11]) {
                                this.publicM(11, this.getXscr(ent.x), this.getYscr(ent.y));
                            }
                            if (collide == 56) {
                                this.publicM(12, this.getXscr(ent.x), this.getYscr(ent.y));
                                this.s_won.play();
                                this.gameState = -10;
                                this.title = this.getImage(this.getCodeBase(), "end.jpg");
                                while (!this.paper.drawImage(this.title, 20, 20, this)) {}
                                this.msg1 = "Congratulations!";
                                this.msg2 = "* Game Over *";
                            }
                            this.delEnt(this.curLev.curEnt[n]);
                            this.curLev.curEnt[n].z = 0;
                            this.curLev.reDist(collide);
                            this.updateScores();
                            this.s_pickUp.play();
                            return true;
                        }
                        if (collide == 40) {
                            if (this.inventory.getID() == 42) {
                                this.inventory.removeFromInv();
                                this.curLev.reDist(42);
                                this.msg1 = "You open the door...";
                                this.delEnt(this.curLev.curEnt[n]);
                                this.curLev.curEnt[n].z = 0;
                                this.updateScores();
                                this.s_pickUp.play();
                                return true;
                            }
                            if (!this.publicMsg[8]) {
                                this.publicM(8, this.getXscr(ent.x), this.getYscr(ent.y));
                            }
                        }
                        if (collide == 59) {
                            this.nearCauldron = true;
                            this.msg1 = "In the cauldron is:";
                            this.msg2 = this.ourCauldron.whatsIn();
                            this.updateScores();
                            if (!this.publicMsg[4]) {
                                this.publicM(4, this.getXscr(ent.x), this.getYscr(ent.y));
                            }
                        }
                        if (collide == 20) {
                            this.doGateway();
                            return true;
                        }
                        if ((collide > 59 && collide < 85) || collide == 42 || collide > 109) {
                            if (this.inventory.addItem(collide)) {
                                this.delEnt(this.curLev.curEnt[n]);
                                this.curLev.curEnt[n].z = 0;
                                if (collide == 42) {
                                    this.msg1 = "You pick up " + this.item.getName(collide);
                                }
                                else {
                                    this.msg1 = "You get some " + this.item.getName(collide);
                                }
                                this.s_pickUp.play();
                                this.updateScores();
                                if (collide < 70 && !this.publicMsg[3]) {
                                    this.publicM(3, this.getXscr(ent.x), this.getYscr(ent.y));
                                }
                                if (collide > 69 && collide < 85 && !this.publicMsg[9]) {
                                    this.publicM(9, this.getXscr(ent.x), this.getYscr(ent.y));
                                }
                                if (collide > 85 && !this.publicMsg[10]) {
                                    this.publicM(10, this.getXscr(ent.x), this.getYscr(ent.y));
                                }
                                return true;
                            }
                            this.msg1 = "You can't carry any more.";
                            this.updateScores();
                            return false;
                        }
                        else if (collide == 41) {
                            if (!this.publicMsg[5]) {
                                this.publicM(5, this.getXscr(ent.x), this.getYscr(ent.y));
                            }
                            final int n2 = (int)(3.0 * Math.random()) + 1;
                            this.delEnt(this.curLev.curEnt[n]);
                            this.msg1 = "In the chest: ";
                            int n3 = 0;
                            switch (n2) {
                                case 1: {
                                    n3 = (int)(4.0 * Math.random()) + 50;
                                    this.msg2 = this.item.getName(n3);
                                    break;
                                }
                                case 2: {
                                    final int z = (int)(2.0 * Math.random()) + 87;
                                    this.msg2 = this.item.getName(z);
                                    this.curLev.curEnt[n].z = z;
                                    this.curLev.curEnt[n].moves = true;
                                    this.curLev.curEnt[n].dirL = true;
                                    this.curLev.addToMvArray(n);
                                    this.s_pain.play();
                                    this.updateScores();
                                    return false;
                                }
                                case 3: {
                                    n3 = (int)(9.0 * Math.random()) + 60;
                                    this.msg2 = this.item.getName(n3);
                                    break;
                                }
                                default: {
                                    n3 = 0;
                                    this.msg1 = "Nothing in the chest";
                                    break;
                                }
                            }
                            this.curLev.curEnt[n].z = n3;
                            this.curLev.curEnt[n].i = n3;
                            this.s_pickUp.play();
                            this.drawEnt(this.curLev.curEnt[n]);
                            this.updateScores();
                        }
                    }
                    if (ent.z == 118 || ent.z == 119) {
                        if (ent.z == 118 && (collide == 2 || collide == 7 || collide == 12)) {
                            this.blowUp(ent);
                            this.curLev.updateWindow(this.xOrigin, this.yOrigin);
                            ent.freeze = -1;
                            this.s_explode.play();
                        }
                        if (ent.z == 119 && (collide == 10 || collide == 16 || collide == 26 || collide == 27 || collide == 30 || collide == 31 || collide == 32 || collide == 33)) {
                            this.blowUp(ent);
                            this.curLev.updateWindow(this.xOrigin, this.yOrigin);
                            ent.freeze = -1;
                            this.s_explode.play();
                        }
                    }
                    return false;
                }
                if (ent.z < 98 || ent.z > 99) {
                    if (this.curLev.collide(ent, this.ourEnt) > 0 && ent.z < 110) {
                        if (ent.z != 90 && ent.z != 106) {
                            if (ent.z == 108) {
                                this.health = -1;
                            }
                            if (ent.z == 85) {
                                this.health -= 5;
                            }
                            if (ent.z == 94 || ent.z == 104) {
                                this.health -= 3;
                            }
                            if (ent.z == 100 || ent.z == 102) {
                                this.health -= 2;
                            }
                            if (ent.z == 92) {
                                this.health -= 3;
                            }
                            if (ent.z == 87 || ent.z == 88 || ent.z == 96) {
                                --this.health;
                            }
                            this.msg1 = "Argh! The " + this.item.getName(ent.z) + " attacks!";
                            this.updateScores();
                            this.s_pain.play();
                            if (!this.publicMsg[0]) {
                                this.publicM(0, this.getXscr(ent.x), this.getYscr(ent.y));
                            }
                            if (this.health < 0) {
                                this.s_dead.play();
                                this.doInit();
                            }
                        }
                        return false;
                    }
                    int k = 0;
                    while (k < this.curLev.numInRm) {
                        final ent ent2 = this.curLev.movInRm[k];
                        if (ent2 != ent && this.curLev.collide(ent, ent2) > 0) {
                            if (ent2.z > 109) {
                                if (ent2.z == 112) {
                                    ent.freeze = 150;
                                    this.msg1 = "The " + this.item.getName(ent.z) + " is frozen!";
                                    this.delEnt(ent2);
                                    this.curLev.removeFromRm(k);
                                    this.s_banish.play();
                                }
                                if ((ent2.z == 113 && ent.z == 87) || (ent2.z == 113 && ent.z == 88) || (ent2.z == 114 && ent.z == 96) || (ent2.z == 115 && ent.z == 94) || (ent2.z == 115 && ent.z == 104) || (ent2.z == 116 && ent.z == 92) || (ent2.z == 116 && ent.z == 102) || (ent2.z == 117 && ent.z == 85)) {
                                    ent.freeze = -1;
                                    this.msg1 = "The " + this.item.getName(ent.z) + " is banished!";
                                    this.delEnt(ent2);
                                    this.curLev.removeFromRm(k);
                                    this.s_banish.play();
                                }
                                this.updateScores();
                                return true;
                            }
                            return false;
                        }
                        else {
                            ++k;
                        }
                    }
                }
            }
            ++i;
        }
        if (ent.z == 98) {
            for (int l = 0; l < this.curLev.numInRm; ++l) {
                final ent ent3 = this.curLev.movInRm[l];
                final int collide2 = this.curLev.collide(ent, ent3);
                if (collide2 > 0 && collide2 < 110) {
                    if (collide2 == 90 && this.traderMsgNum < 16) {
                        this.trade.doMsg(this.traderMsgNum + 200, this.getXscr(ent3.x), this.getYscr(ent3.y));
                        this.s_message.play();
                        this.gameState = 100;
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    public void setAsBlock(final ent ent) {
        final int xblock = this.getXblock(ent.x);
        final int yblock = this.getYblock(ent.y);
        final int n = xblock + yblock * 200;
        this.curLev.curEnt[n].moves = ent.moves;
        this.curLev.curEnt[n].dirU = ent.dirU;
        this.curLev.curEnt[n].dirD = ent.dirD;
        this.curLev.curEnt[n].dirL = ent.dirL;
        this.curLev.curEnt[n].dirR = ent.dirR;
        this.curLev.curEnt[n].x = xblock * 20;
        this.curLev.curEnt[n].y = yblock * 20;
        this.curLev.curEnt[n].z = ent.z;
        this.curLev.curEnt[n].moveSpeed = ent.moveSpeed;
    }
    
    public int getXblock(final int n) {
        return n / 20;
    }
    
    public void loader() {
        int n = 0;
        this.paper.setColor(Color.black);
        this.paper.fillRect(0, 0, this.xsize, this.ysize);
        this.paper.setColor(Color.green);
        this.paper.drawString("Loading . . . Please Wait", 65, 100);
        while (!this.paper.drawImage(this.gfx, -200, 0, this)) {
            n = this.ticker(n);
        }
        while (!this.paper.drawImage(this.gfx2, -200, 0, this)) {
            n = this.ticker(n);
        }
        while (!this.paper.drawImage(this.title, 20, 20, this)) {
            n = this.ticker(n);
        }
        this.s_pickUp = this.getAudioClip(this.getDocumentBase(), "bleep.au");
        this.s_pain = this.getAudioClip(this.getDocumentBase(), "pain.au");
        this.s_dead = this.getAudioClip(this.getDocumentBase(), "dead.au");
        this.s_message = this.getAudioClip(this.getDocumentBase(), "message.au");
        this.s_spell = this.getAudioClip(this.getDocumentBase(), "spell.au");
        this.s_banish = this.getAudioClip(this.getDocumentBase(), "banish.au");
        this.s_explode = this.getAudioClip(this.getDocumentBase(), "explode.au");
        this.s_won = this.getAudioClip(this.getDocumentBase(), "won.au");
        this.gameState = 0;
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.speed);
            }
            catch (Exception ex) {}
            synchronized (this) {
                if (this.gameState == 1) {
                    this.delEnt(this.ourEnt);
                    if (this.doDir(this.ourEnt) && this.walking++ > 100) {
                        this.walking = 0;
                        --this.food;
                        if (this.food < 1) {
                            this.food = 0;
                            --this.health;
                            if (this.health < 0) {
                                this.s_dead.play();
                                this.doInit();
                            }
                        }
                        this.updateScores();
                    }
                    this.scrollScreen();
                    this.drawEnt(this.ourEnt);
                    this.moveOthers();
                }
                if (this.gameState == 0) {}
                if (this.gameState > 99 && this.gameState == 101) {
                    if (this.gold - this.traderMsgNum > -1) {
                        this.gold -= this.traderMsgNum;
                        this.trade.doMsg(this.traderMsgNum++, 0, 0);
                        this.updateScores();
                    }
                    else {
                        this.trade.doMsg(107, 0, 0);
                    }
                    this.s_message.play();
                    this.gameState = 99;
                }
                if (this.gameState == -2) {
                    this.gameState = -1;
                    this.loader();
                }
                this.repaint();
            }
        }
    }
    
    public void init() {
        this.addKeyListener(this);
        this.doInit();
    }
    
    private void doGateway() {
        final int xblock = this.getXblock(this.ourEnt.x);
        final int yblock = this.getYblock(this.ourEnt.y);
        this.msg2 = "" + xblock + " " + yblock;
        if (xblock == 40 && yblock == 10) {
            this.xOrigin = 155;
            this.yOrigin = 8;
        }
        if (xblock == 5 && yblock == 22) {
            this.xOrigin = 167;
            this.yOrigin = 8;
        }
        if (xblock == 136 && yblock == 9) {
            this.xOrigin = 179;
            this.yOrigin = 8;
        }
        if (xblock == 97 && yblock == 10) {
            this.xOrigin = 191;
            this.yOrigin = 8;
        }
        if (xblock == 39 && yblock == 48) {
            this.xOrigin = 155;
            this.yOrigin = 19;
        }
        if (xblock == 72 && yblock == 49) {
            this.xOrigin = 167;
            this.yOrigin = 19;
        }
        if (xblock == 162 && yblock == 62) {
            this.xOrigin = 179;
            this.yOrigin = 19;
        }
        if (xblock == 30 && yblock == 71) {
            this.xOrigin = 191;
            this.yOrigin = 19;
        }
        if (xblock == 10 && yblock == 72) {
            this.xOrigin = 155;
            this.yOrigin = 30;
        }
        if (xblock == 86 && yblock == 78) {
            this.xOrigin = 167;
            this.yOrigin = 30;
        }
        if (xblock == 164 && yblock == 90) {
            this.xOrigin = 179;
            this.yOrigin = 30;
        }
        if (xblock == 148 && yblock == 105) {
            this.xOrigin = 191;
            this.yOrigin = 30;
        }
        if (xblock == 155 && yblock == 7) {
            this.xOrigin = 40;
            this.yOrigin = 11;
        }
        if (xblock == 167 && yblock == 7) {
            this.xOrigin = 5;
            this.yOrigin = 23;
        }
        if (xblock == 179 && yblock == 7) {
            this.xOrigin = 136;
            this.yOrigin = 10;
        }
        if (xblock == 191 && yblock == 7) {
            this.xOrigin = 97;
            this.yOrigin = 11;
        }
        if (xblock == 155 && yblock == 18) {
            this.xOrigin = 39;
            this.yOrigin = 49;
        }
        if (xblock == 167 && yblock == 18) {
            this.xOrigin = 72;
            this.yOrigin = 50;
        }
        if (xblock == 179 && yblock == 18) {
            this.xOrigin = 162;
            this.yOrigin = 63;
        }
        if (xblock == 191 && yblock == 18) {
            this.xOrigin = 30;
            this.yOrigin = 72;
        }
        if (xblock == 155 && yblock == 29) {
            this.xOrigin = 10;
            this.yOrigin = 73;
        }
        if (xblock == 167 && yblock == 29) {
            this.xOrigin = 86;
            this.yOrigin = 79;
        }
        if (xblock == 179 && yblock == 29) {
            this.xOrigin = 164;
            this.yOrigin = 91;
        }
        if (xblock == 191 && yblock == 29) {
            this.xOrigin = 148;
            this.yOrigin = 106;
        }
        this.xOffset = 20;
        this.yOffset = 20;
        this.ourEnt.x = this.xOrigin * 20;
        this.ourEnt.y = this.yOrigin * 20;
        this.ourEnt.dirU = false;
        this.ourEnt.dirD = false;
        this.ourEnt.dirL = false;
        this.ourEnt.dirR = false;
        this.curLev.setUpWindow(this.xOrigin, this.yOrigin);
        this.msg1 = "You go through the gateway.";
        this.updateScores();
    }
}
