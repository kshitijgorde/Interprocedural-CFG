import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Event;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class boxes extends Applet implements Runnable
{
    int pause;
    int score;
    Thread animate;
    Image offScreenImg;
    Image sheet;
    Image instructions;
    Image numbers;
    Graphics g;
    MediaTracker tracker;
    int goc;
    boolean gameOver;
    boolean begin;
    float numberBounce;
    int bounceDirection;
    int[] numberW;
    int conveyorAni;
    int add;
    int count;
    int unload;
    int getRid;
    float rot;
    boolean[] stacked;
    float[] boxX;
    float[] boxY;
    float[] boxW;
    float[] boxH;
    float[] boxD;
    float[] boxZ;
    int[] design;
    int[] boxC;
    int total;
    int[] sort;
    int gridW;
    int gridSize;
    int gridX;
    int gridY;
    float centreX;
    float centreY;
    int moveBoxNumber;
    int[] topWallX;
    int[] topWallY;
    int[] leftWallX;
    int[] leftWallY;
    int[] bottomWallX;
    int[] bottomWallY;
    
    public boxes() {
        this.pause = 100;
        this.score = 0;
        this.animate = null;
        this.goc = 0;
        this.gameOver = false;
        this.begin = false;
        this.numberBounce = 0.0f;
        this.bounceDirection = 1;
        this.numberW = new int[] { 6, 18, 25, 40, 47, 62, 68, 82, 87, 103, 108, 124, 130, 144, 150, 165, 171, 185, 191, 207 };
        this.conveyorAni = 0;
        this.add = 100;
        this.count = 0;
        this.unload = -1;
        this.getRid = -1;
        this.rot = 0.0f;
        this.stacked = new boolean[40];
        this.boxX = new float[40];
        this.boxY = new float[40];
        this.boxW = new float[40];
        this.boxH = new float[40];
        this.boxD = new float[40];
        this.boxZ = new float[40];
        this.design = new int[40];
        this.boxC = new int[40];
        this.total = 0;
        this.sort = new int[40];
        this.gridW = 10;
        this.gridSize = 260;
        this.gridX = 50;
        this.gridY = 50;
        this.centreX = this.gridX + this.gridSize / 2;
        this.centreY = this.gridY + this.gridSize / 2;
        this.moveBoxNumber = -1;
        this.topWallX = new int[] { this.gridX, 0, this.gridX * 2 + this.gridSize, this.gridX + this.gridSize };
        this.topWallY = new int[] { this.gridY, 0, 0, this.gridY };
        this.leftWallX = new int[] { this.gridX, 0, 0, this.gridX };
        this.leftWallY = new int[] { this.gridY, 0, this.gridY * 2 + this.gridSize, this.gridY + this.gridSize };
        this.bottomWallX = new int[] { this.gridX, 0, this.gridX * 2 + this.gridSize, this.gridX + this.gridSize };
        this.bottomWallY = new int[] { this.gridY + this.gridSize, 2 * this.gridY + this.gridSize, 2 * this.gridY + this.gridSize, this.gridY + this.gridSize };
    }
    
    public void init() {
        this.offScreenImg = this.createImage(this.size().width, this.size().height);
        this.g = this.offScreenImg.getGraphics();
        this.boxW[0] = 80.0f;
        this.boxH[0] = 80.0f;
        this.boxD[0] = 80.0f;
        this.design[0] = 1;
        this.boxW[1] = 60.0f;
        this.boxH[1] = 40.0f;
        this.boxD[1] = 40.0f;
        this.design[1] = 2;
        this.boxW[2] = 40.0f;
        this.boxH[2] = 40.0f;
        this.boxD[2] = 40.0f;
        this.design[2] = 0;
        this.boxW[3] = 80.0f;
        this.boxH[3] = 60.0f;
        this.boxD[3] = 60.0f;
        this.design[3] = 1;
        this.boxY[4] = 125.0f;
        this.boxW[4] = 40.0f;
        this.boxH[4] = 40.0f;
        this.boxD[4] = 40.0f;
        this.design[4] = 0;
        this.boxW[5] = 60.0f;
        this.boxH[5] = 80.0f;
        this.boxD[5] = 80.0f;
        this.design[5] = 2;
        this.boxW[6] = 80.0f;
        this.boxH[6] = 60.0f;
        this.boxD[6] = 60.0f;
        this.design[6] = 0;
        this.boxW[7] = 40.0f;
        this.boxH[7] = 40.0f;
        this.boxD[7] = 80.0f;
        this.design[7] = 2;
        this.boxW[8] = 60.0f;
        this.boxH[8] = 80.0f;
        this.boxD[8] = 80.0f;
        this.design[8] = 1;
        this.boxW[9] = 60.0f;
        this.boxH[9] = 40.0f;
        this.boxD[9] = 80.0f;
        this.design[9] = 2;
        this.boxW[10] = 80.0f;
        this.boxH[10] = 80.0f;
        this.boxD[10] = 80.0f;
        this.design[10] = 1;
        this.boxW[11] = 60.0f;
        this.boxH[11] = 40.0f;
        this.boxD[11] = 40.0f;
        this.design[11] = 0;
        this.boxW[12] = 40.0f;
        this.boxH[12] = 40.0f;
        this.boxD[12] = 40.0f;
        this.design[12] = 0;
        this.boxW[13] = 80.0f;
        this.boxH[13] = 60.0f;
        this.boxD[13] = 60.0f;
        this.design[13] = 0;
        this.boxW[14] = 40.0f;
        this.boxH[14] = 40.0f;
        this.boxD[14] = 40.0f;
        this.design[14] = 0;
        this.boxW[15] = 60.0f;
        this.boxH[15] = 80.0f;
        this.boxD[15] = 80.0f;
        this.design[15] = 0;
        this.boxW[16] = 80.0f;
        this.boxH[16] = 60.0f;
        this.boxD[16] = 60.0f;
        this.design[16] = 0;
        this.boxW[17] = 40.0f;
        this.boxH[17] = 40.0f;
        this.boxD[17] = 80.0f;
        this.design[17] = 0;
        this.boxW[18] = 60.0f;
        this.boxH[18] = 80.0f;
        this.boxD[18] = 80.0f;
        this.design[18] = 1;
        this.boxW[19] = 60.0f;
        this.boxH[19] = 40.0f;
        this.boxD[19] = 80.0f;
        this.design[19] = 0;
        this.sheet = this.getImage(this.getCodeBase(), "sheet.gif");
        this.numbers = this.getImage(this.getCodeBase(), "numbers.gif");
        this.instructions = this.getImage(this.getCodeBase(), "instructions.gif");
        (this.tracker = new MediaTracker(this)).addImage(this.sheet, 0);
        this.tracker.addImage(this.numbers, 0);
        this.tracker.addImage(this.instructions, 0);
    }
    
    public void start() {
        if (this.animate == null) {
            (this.animate = new Thread(this)).start();
        }
    }
    
    public boolean mouseDrag(final Event e, int x, int y) {
        if (this.moveBoxNumber != -1 && this.boxZ[this.moveBoxNumber] == 0) {
            for (int i = 0; i < this.total; ++i) {
                if (i != this.moveBoxNumber) {
                    final float factor2 = (this.boxD[i] + this.boxZ[i]) / 400 + 1;
                    final float nx2 = this.centreX - (this.centreX - this.boxX[i]) * factor2;
                    final float ny2 = this.centreY - (this.centreY - this.boxY[i]) * factor2;
                    final float dist = this.distance(this.boxX[this.moveBoxNumber], this.boxY[this.moveBoxNumber], this.boxX[i], this.boxY[i]);
                    if (dist <= Math.max(this.boxW[this.moveBoxNumber], this.boxW[i]) / 2 + Math.max(this.boxH[this.moveBoxNumber], this.boxH[i]) / 2 + this.gridW * 3 && !this.stacked[i] && this.distance(x, y, nx2, ny2) < Math.max(this.boxW[i], this.boxH[i]) / 3 && this.boxW[i] >= this.boxW[this.moveBoxNumber] && this.boxH[i] >= this.boxH[this.moveBoxNumber]) {
                        this.boxZ[this.moveBoxNumber] = this.boxD[i];
                        this.boxX[this.moveBoxNumber] = this.boxX[i];
                        this.boxY[this.moveBoxNumber] = this.boxY[i];
                        this.stacked[this.moveBoxNumber] = true;
                        this.stacked[i] = true;
                        return false;
                    }
                }
            }
        }
        if (this.moveBoxNumber != -1 && this.boxZ[this.moveBoxNumber] > 0 && this.boxZ[this.moveBoxNumber] > 0) {
            int bottomBox = -1;
            for (int j = 0; j < this.total; ++j) {
                if (j != this.moveBoxNumber) {
                    if (this.boxX[j] == this.boxX[this.moveBoxNumber] && this.boxY[j] == this.boxY[this.moveBoxNumber]) {
                        bottomBox = j;
                    }
                }
            }
            for (int j = 0; j < this.total; ++j) {
                if (j != this.moveBoxNumber && j != bottomBox) {
                    final float factor3 = (this.boxD[j] + this.boxZ[j]) / 400 + 1;
                    final float nx3 = this.centreX - (this.centreX - this.boxX[j]) * factor3;
                    final float ny3 = this.centreY - (this.centreY - this.boxY[j]) * factor3;
                    final float dist2 = this.distance(this.boxX[this.moveBoxNumber], this.boxY[this.moveBoxNumber], this.boxX[j], this.boxY[j]);
                    if (dist2 <= Math.max(this.boxW[bottomBox], this.boxW[j]) / 2 + Math.max(this.boxH[bottomBox], this.boxH[j]) / 2 + this.gridW * 3 && !this.stacked[j] && this.distance(x, y, nx3, ny3) < Math.max(this.boxW[j], this.boxH[j]) / 3 && this.boxW[j] >= this.boxW[this.moveBoxNumber] && this.boxH[j] >= this.boxH[this.moveBoxNumber]) {
                        this.boxZ[this.moveBoxNumber] = this.boxD[j];
                        this.boxX[this.moveBoxNumber] = this.boxX[j];
                        this.boxY[this.moveBoxNumber] = this.boxY[j];
                        this.stacked[this.moveBoxNumber] = true;
                        this.stacked[j] = true;
                        return this.stacked[bottomBox] = false;
                    }
                }
            }
        }
        if (this.moveBoxNumber != -1 && this.boxZ[this.moveBoxNumber] > 0) {
            int bottomBox = -1;
            for (int j = 0; j < this.total; ++j) {
                if (j != this.moveBoxNumber) {
                    if (this.boxX[j] == this.boxX[this.moveBoxNumber] && this.boxY[j] == this.boxY[this.moveBoxNumber]) {
                        bottomBox = j;
                    }
                }
            }
            final int tx = x;
            final int ty = y;
            if (x - this.boxW[this.moveBoxNumber] / 2 < this.gridX + this.gridW / 2) {
                x = (int)(this.gridX + this.boxW[this.moveBoxNumber] / 2 + this.gridW / 2);
            }
            if (x + this.boxW[this.moveBoxNumber] / 2 > this.gridX + this.gridSize - this.gridW / 2) {
                x = (int)(this.gridX + this.gridSize - this.boxW[this.moveBoxNumber] / 2 - this.gridW / 2);
            }
            if (y - this.boxH[this.moveBoxNumber] / 2 < this.gridY + this.gridW / 2) {
                y = (int)(this.gridY + this.boxH[this.moveBoxNumber] / 2 + this.gridW / 2);
            }
            if (y + this.boxH[this.moveBoxNumber] / 2 > this.gridY + this.gridSize - this.gridW / 2) {
                y = (int)(this.gridY + this.gridSize - this.boxH[this.moveBoxNumber] / 2 - this.gridW / 2);
            }
            boolean found = false;
            for (int k = 0; k < this.total; ++k) {
                final float dist2 = this.distance(this.boxX[this.moveBoxNumber], this.boxY[this.moveBoxNumber], x, y);
                if (dist2 <= Math.max(this.boxW[bottomBox], this.boxW[this.moveBoxNumber]) / 2 + Math.max(this.boxH[this.moveBoxNumber], this.boxH[bottomBox]) / 2 + 3 * this.gridW && this.checkBounds(this.moveBoxNumber, x, y)) {
                    found = true;
                    this.boxX[this.moveBoxNumber] = x;
                    this.boxY[this.moveBoxNumber] = y;
                    this.stacked[bottomBox] = false;
                    this.stacked[this.moveBoxNumber] = false;
                    this.boxZ[this.moveBoxNumber] = 0.0f;
                    return true;
                }
            }
            if (!found) {
                for (int k = -2; k < 3; ++k) {
                    for (int l = -2; l < 3; ++l) {
                        final float dist3 = this.distance(this.boxX[this.moveBoxNumber], this.boxY[this.moveBoxNumber], x + this.gridW * k, y + this.gridW * l);
                        if (y + this.gridW * l + this.boxH[this.moveBoxNumber] / 2 <= this.gridY + this.gridSize - this.gridW / 2 && y + this.gridW * l - this.boxH[this.moveBoxNumber] / 2 >= this.gridY + this.gridW / 2 && x + this.gridW * k - this.boxW[this.moveBoxNumber] / 2 >= this.gridX + this.gridW / 2 && x + this.gridW * k + this.boxW[this.moveBoxNumber] / 2 <= this.gridX + this.gridSize - this.gridW / 2 && !found && dist3 <= Math.max(this.boxW[bottomBox], this.boxW[this.moveBoxNumber]) / 2 + Math.max(this.boxH[this.moveBoxNumber], this.boxH[bottomBox]) / 2 + 3 * this.gridW && this.checkBounds(this.moveBoxNumber, x + this.gridW * k, y + this.gridW * l)) {
                            found = true;
                            this.boxX[this.moveBoxNumber] = x + this.gridW * k;
                            this.boxY[this.moveBoxNumber] = y + this.gridW * l;
                            this.stacked[bottomBox] = false;
                            this.stacked[this.moveBoxNumber] = false;
                            this.boxZ[this.moveBoxNumber] = 0.0f;
                            return true;
                        }
                    }
                }
            }
            x = tx;
            y = ty;
            final int ux = this.gridX + this.gridSize + 65;
            final int uy = this.gridY + this.gridSize - 45;
            if (this.moveBoxNumber == this.unload && this.boxY[this.moveBoxNumber] - this.boxH[this.moveBoxNumber] / 2 >= this.gridY + this.gridSize - this.gridW - 80 && this.distance(x, y, this.boxX[bottomBox], this.boxY[bottomBox]) < this.boxW[bottomBox] + 40 && x > this.gridX + this.gridSize + this.boxW[bottomBox] / 2 + 10) {
                this.score += (int)(this.boxW[this.moveBoxNumber] * this.boxH[this.moveBoxNumber] / 500);
                this.score %= 10000;
                this.getRid = this.moveBoxNumber;
                this.unload = -1;
                this.boxX[this.moveBoxNumber] = ux;
                this.boxY[this.moveBoxNumber] = uy;
                this.stacked[bottomBox] = false;
                this.stacked[this.moveBoxNumber] = false;
                this.boxZ[this.moveBoxNumber] = -10.0f;
                return true;
            }
        }
        if (this.moveBoxNumber != -1 && this.boxZ[this.moveBoxNumber] == 0) {
            final int ux2 = this.gridX + this.gridSize + 65;
            final int uy2 = this.gridY + this.gridSize - 45;
            if (this.moveBoxNumber == this.unload && y - this.boxH[this.moveBoxNumber] / 2 >= this.gridY + this.gridSize - this.gridW - 80 && this.distance(x, y, this.boxX[this.moveBoxNumber], this.boxY[this.moveBoxNumber]) < this.boxW[this.moveBoxNumber] + 40 && x > this.gridX + this.gridSize + this.boxW[this.moveBoxNumber] / 2 + 10) {
                this.score += (int)(this.boxW[this.moveBoxNumber] * this.boxH[this.moveBoxNumber] / 500);
                this.score %= 10000;
                this.getRid = this.moveBoxNumber;
                this.unload = -1;
                this.boxX[this.moveBoxNumber] = ux2;
                this.boxY[this.moveBoxNumber] = uy2;
                this.boxZ[this.moveBoxNumber] = -10.0f;
                return true;
            }
        }
        if (this.moveBoxNumber != -1 && this.boxZ[this.moveBoxNumber] == 0) {
            float tempx = 0.0f;
            float tempy = 0.0f;
            tempx = this.boxX[this.moveBoxNumber];
            tempy = this.boxY[this.moveBoxNumber];
            if (this.boxZ[this.moveBoxNumber] >= 0 && x - this.gridW > this.boxX[this.moveBoxNumber]) {
                x = (int)(this.boxX[this.moveBoxNumber] + this.gridW);
            }
            if (this.boxZ[this.moveBoxNumber] >= 0 && x + this.gridW < this.boxX[this.moveBoxNumber]) {
                x = (int)(this.boxX[this.moveBoxNumber] - this.gridW);
            }
            if (this.boxZ[this.moveBoxNumber] >= 0 && y - this.gridW > this.boxY[this.moveBoxNumber]) {
                y = (int)(this.boxY[this.moveBoxNumber] + this.gridW);
            }
            if (this.boxZ[this.moveBoxNumber] >= 0 && y + this.gridW < this.boxY[this.moveBoxNumber]) {
                y = (int)(this.boxY[this.moveBoxNumber] - this.gridW);
            }
            if (x - this.boxW[this.moveBoxNumber] / 2 < this.gridX + this.gridW / 2) {
                x = (int)(this.gridX + this.boxW[this.moveBoxNumber] / 2 + this.gridW / 2);
            }
            if (x + this.boxW[this.moveBoxNumber] / 2 > this.gridX + this.gridSize - this.gridW / 2) {
                x = (int)(this.gridX + this.gridSize - this.boxW[this.moveBoxNumber] / 2 - this.gridW / 2);
            }
            if (!this.checkBounds(this.moveBoxNumber, x, (int)this.boxY[this.moveBoxNumber])) {
                x = (int)this.boxX[this.moveBoxNumber];
            }
            if (y - this.boxH[this.moveBoxNumber] / 2 < this.gridY + this.gridW / 2) {
                y = (int)(this.gridY + this.boxH[this.moveBoxNumber] / 2 + this.gridW / 2);
            }
            if (y + this.boxH[this.moveBoxNumber] / 2 > this.gridY + this.gridSize - this.gridW / 2) {
                y = (int)(this.gridY + this.gridSize - this.boxH[this.moveBoxNumber] / 2 - this.gridW / 2);
            }
            if (!this.checkBounds(this.moveBoxNumber, (int)this.boxX[this.moveBoxNumber], y)) {
                y = (int)this.boxY[this.moveBoxNumber];
            }
            if (this.distance(x - (x - this.gridX) % this.gridW + this.gridW / 2, y - (y - this.gridY) % this.gridW + this.gridW / 2, tempx, tempy) <= 4 * this.gridW || this.boxZ[this.moveBoxNumber] < 0) {
                this.boxX[this.moveBoxNumber] = x - (x - this.gridX) % this.gridW + this.gridW / 2;
                this.boxY[this.moveBoxNumber] = y - (y - this.gridY) % this.gridW + this.gridW / 2;
                if (x < this.gridX + this.gridSize) {
                    this.boxZ[this.moveBoxNumber] = 0.0f;
                }
            }
        }
        if (this.moveBoxNumber != -1 && this.boxZ[this.moveBoxNumber] < 0) {
            boolean load = false;
            final int tx = x;
            final int ty = y;
            if (x + this.boxW[this.moveBoxNumber] / 2 > this.gridX + this.gridSize - this.gridW / 2 && x + this.boxW[this.moveBoxNumber] / 2 < this.gridX + this.gridSize - this.gridW / 2 + 30) {
                load = true;
                x = (int)(this.gridX + this.gridSize - this.boxW[this.moveBoxNumber] / 2 - this.gridW / 2);
            }
            if (y - this.boxH[this.moveBoxNumber] / 2 < this.gridY + this.gridW / 2) {
                y = (int)(this.gridY + this.boxH[this.moveBoxNumber] / 2 + this.gridW / 2);
            }
            if (load && this.distance(x, y, this.boxX[this.moveBoxNumber], this.boxY[this.moveBoxNumber]) < this.boxW[this.moveBoxNumber] + 40 && this.checkBounds(this.moveBoxNumber, x, y)) {
                this.boxX[this.moveBoxNumber] = x;
                this.boxY[this.moveBoxNumber] = y;
                this.boxZ[this.moveBoxNumber] = 0.0f;
                return true;
            }
            x = tx;
            y = ty;
            for (int m = 0; m < this.total; ++m) {
                if (m != this.moveBoxNumber) {
                    final float factor4 = (this.boxD[m] + this.boxZ[m]) / 400 + 1;
                    final float nx4 = this.centreX - (this.centreX - this.boxX[m]) * factor4;
                    final float ny4 = this.centreY - (this.centreY - this.boxY[m]) * factor4;
                    final float dist4 = this.distance(this.boxX[this.moveBoxNumber], this.boxY[this.moveBoxNumber], this.boxX[m], this.boxY[m]);
                    if (this.boxX[m] - this.boxW[this.moveBoxNumber] / 2 >= this.gridX + this.gridSize - 90 && this.boxY[m] + this.boxH[this.moveBoxNumber] / 2 <= this.gridY + 90 && !this.stacked[m] && this.distance(x, y, nx4, ny4) < Math.max(this.boxW[m], this.boxH[m]) / 3 && this.boxW[m] >= this.boxW[this.moveBoxNumber] && this.boxH[m] >= this.boxH[this.moveBoxNumber]) {
                        this.boxZ[this.moveBoxNumber] = this.boxD[m];
                        this.boxX[this.moveBoxNumber] = this.boxX[m];
                        this.boxY[this.moveBoxNumber] = this.boxY[m];
                        this.stacked[this.moveBoxNumber] = true;
                        this.stacked[m] = true;
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public boolean mouseDown(final Event e, final int x, final int y) {
        if (this.gameOver) {
            return true;
        }
        if (!this.begin) {
            this.begin = true;
            this.count = -40;
            this.score = 0;
            return true;
        }
        for (int i = 0; i < this.total; ++i) {
            if (i != this.getRid) {
                final float factor2 = (this.boxD[i] + this.boxZ[i]) / 400 + 1;
                final float nx2 = this.centreX - (this.centreX - this.boxX[i] + this.boxW[i] / 2) * factor2;
                final float ny2 = this.centreY - (this.centreY - this.boxY[i] + this.boxH[i] / 2) * factor2;
                if ((this.boxZ[i] != 0 || !this.stacked[i]) && x < nx2 + this.boxW[i] * factor2 && x > nx2 && y < ny2 + this.boxH[i] * factor2 && y > ny2) {
                    this.moveBoxNumber = i;
                }
                if (this.moveBoxNumber == this.total - 1 && this.boxX[this.moveBoxNumber] > this.gridX + this.gridSize && this.count <= 20) {
                    this.moveBoxNumber = -1;
                }
            }
        }
        return true;
    }
    
    public boolean mouseUp(final Event e, final int x, final int y) {
        this.moveBoxNumber = -1;
        return true;
    }
    
    public void paint(final Graphics f) {
        try {
            this.tracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.g.setColor(new Color(200, 200, 200));
        this.g.fillRect(this.gridX, this.gridY, this.gridSize, this.gridSize);
        this.g.setColor(new Color(230, 230, 230));
        this.g.fillRect(this.gridX + this.gridSize + 1, 0, 130, this.gridSize + 200);
        this.drawLoadArrow(2 * this.gridX + this.gridSize, 2 * this.gridY);
        this.drawScore();
        if (!this.begin) {
            this.g.drawImage(this.instructions, this.gridX + 20, this.gridY + 20, this);
        }
        this.g.setColor(new Color(50, 50, 50));
        this.g.fillRect(this.gridX + this.gridSize + 12, this.gridY + this.gridSize - 90, 5, 141);
        this.g.setColor(Color.black);
        for (int i = 1; i < 17; ++i) {
            this.g.drawLine(this.gridX + this.gridSize + 12, this.gridY + this.gridSize - 90 + 9 * i - this.conveyorAni, this.gridX + this.gridSize + 18, this.gridY + this.gridSize - 90 + 9 * i - this.conveyorAni);
        }
        this.g.setColor(new Color(100, 100, 100));
        this.g.fillRect(this.gridX + this.gridSize + 15, this.gridY + this.gridSize - 90, 91, 141);
        this.g.setColor(Color.black);
        for (int i = 0; i < 16; ++i) {
            this.g.drawLine(this.gridX + this.gridSize + 15, this.gridY + this.gridSize - 90 + 9 * i + this.conveyorAni, this.gridX + this.gridSize + 105, this.gridY + this.gridSize - 90 + 9 * i + this.conveyorAni);
        }
        if (!this.gameOver) {
            this.conveyorAni += 3;
            this.conveyorAni %= 9;
        }
        this.g.setColor(new Color(230, 230, 230));
        this.g.drawLine(this.gridX + this.gridSize + 105, this.gridY + this.gridSize - 90, this.gridX + this.gridSize + 105, this.gridY + this.gridSize - 90);
        this.g.drawLine(this.gridX + this.gridSize + 12, this.gridY + this.gridSize - 90, this.gridX + this.gridSize + 12, this.gridY + this.gridSize - 90);
        for (int i = 0; i < this.total; ++i) {
            this.drawBox(this.sort[i], this.moveBoxNumber, this.boxX[this.sort[i]], this.boxY[this.sort[i]], this.boxW[this.sort[i]], this.boxH[this.sort[i]], this.boxD[this.sort[i]], this.boxZ[this.sort[i]], this.boxC[this.sort[i]], 1, this.design[this.sort[i]]);
        }
        this.g.setColor(Color.black);
        this.g.drawRect(this.gridX, this.gridY, this.gridSize, this.gridSize);
        this.g.setColor(new Color(120, 120, 120));
        this.g.fillPolygon(this.topWallX, this.topWallY, 4);
        this.g.setColor(new Color(150, 150, 150));
        this.g.fillPolygon(this.leftWallX, this.leftWallY, 4);
        this.g.fillPolygon(this.bottomWallX, this.bottomWallY, 4);
        this.g.setColor(Color.black);
        this.g.drawPolygon(this.topWallX, this.topWallY, 4);
        this.g.drawImage(this.sheet, this.gridX + this.gridSize / 2 - 10, this.gridY - 40, this);
        this.g.drawPolygon(this.leftWallX, this.leftWallY, 4);
        this.g.drawPolygon(this.bottomWallX, this.bottomWallY, 4);
        if (this.total > 0) {
            this.sortBoxes();
        }
        for (int i = 0; i < this.total; ++i) {
            this.drawBox(this.sort[i], this.moveBoxNumber, this.boxX[this.sort[i]], this.boxY[this.sort[i]], this.boxW[this.sort[i]], this.boxH[this.sort[i]], this.boxD[this.sort[i]], this.boxZ[this.sort[i]], this.boxC[this.sort[i]], 0, this.design[this.sort[i]]);
        }
        if (this.unload != -1) {
            this.drawUnloadTriangle();
        }
        this.g.setColor(Color.black);
        this.g.drawString("© http://www.kevinbertman.co.uk", 85, 2 * this.gridY + this.gridSize - 20);
        f.drawImage(this.offScreenImg, 0, 0, this);
    }
    
    public void drawScore() {
        this.g.setColor(new Color(255, 204, 0));
        this.g.fillRect(this.gridX + this.gridSize + 16, this.gridY + this.gridSize / 2 - 6, 18, 18);
        this.g.setColor(Color.black);
        this.g.drawRect(this.gridX + this.gridSize + 16, this.gridY + this.gridSize / 2 - 6, 18, 18);
        this.g.setColor(Color.white);
        this.g.fillRect(this.gridX + this.gridSize + 18, this.gridY + this.gridSize / 2 - 4, 5, 4);
        this.g.setColor(Color.black);
        this.g.drawRect(this.gridX + this.gridSize + 18, this.gridY + this.gridSize / 2 - 4, 5, 4);
        int shiftX = 20;
        final int thousands = this.score / 1000;
        this.g.drawImage(this.numbers, this.gridX + this.gridSize + 20 + shiftX, (int)(this.gridY - 10 + this.gridSize / 2 + this.numberBounce), this.gridX + this.gridSize + 20 + this.numberW[2 * ((thousands + 9) % 10) + 1] - this.numberW[2 * ((thousands + 9) % 10)] + shiftX, (int)(this.gridY - 10 + this.gridSize / 2 + this.numberBounce) + 27, this.numberW[2 * ((thousands + 9) % 10)], 0, this.numberW[2 * ((thousands + 9) % 10) + 1], 27, this);
        shiftX += this.numberW[2 * ((thousands + 9) % 10) + 1] - this.numberW[2 * ((thousands + 9) % 10)];
        final int hundreds = this.score / 100;
        this.g.drawImage(this.numbers, this.gridX + this.gridSize + 20 + shiftX, (int)(this.gridY - 10 + this.gridSize / 2 - this.numberBounce), this.gridX + this.gridSize + 20 + this.numberW[2 * ((hundreds + 9) % 10) + 1] - this.numberW[2 * ((hundreds + 9) % 10)] + shiftX, (int)(this.gridY - 10 + this.gridSize / 2 - this.numberBounce) + 27, this.numberW[2 * ((hundreds + 9) % 10)], 0, this.numberW[2 * ((hundreds + 9) % 10) + 1], 27, this);
        shiftX += this.numberW[2 * ((hundreds + 9) % 10) + 1] - this.numberW[2 * ((hundreds + 9) % 10)];
        final int tens = this.score / 10;
        this.g.drawImage(this.numbers, this.gridX + this.gridSize + 20 + shiftX, (int)(this.gridY - 10 + this.gridSize / 2 + this.numberBounce), this.gridX + this.gridSize + 20 + this.numberW[2 * ((tens + 9) % 10) + 1] - this.numberW[2 * ((tens + 9) % 10)] + shiftX, (int)(this.gridY - 10 + this.gridSize / 2 + this.numberBounce) + 27, this.numberW[2 * ((tens + 9) % 10)], 0, this.numberW[2 * ((tens + 9) % 10) + 1], 27, this);
        shiftX += this.numberW[2 * ((tens + 9) % 10) + 1] - this.numberW[2 * ((tens + 9) % 10)];
        final int unit = this.score % 10;
        this.g.drawImage(this.numbers, this.gridX + this.gridSize + 20 + shiftX, (int)(this.gridY - 10 + this.gridSize / 2 - this.numberBounce), this.gridX + this.gridSize + 20 + this.numberW[2 * ((unit + 9) % 10) + 1] - this.numberW[2 * ((unit + 9) % 10)] + shiftX, (int)(this.gridY - 10 + this.gridSize / 2 - this.numberBounce) + 27, this.numberW[2 * ((unit + 9) % 10)], 0, this.numberW[2 * ((unit + 9) % 10) + 1], 27, this);
    }
    
    public void drawUnloadTriangle() {
        final float factor2 = (this.boxD[this.unload] + this.boxZ[this.unload]) / 400 + 1;
        final float nx2 = this.centreX - (this.centreX - this.boxX[this.unload]) * factor2;
        final float ny2 = this.centreY - (this.centreY - this.boxY[this.unload]) * factor2;
        final int[] x = { (int)(nx2 + 20 * Math.sin(this.rot)), (int)(nx2 + 20 * Math.sin(this.rot + 2.0943951023931953)), (int)(nx2 + 20 * Math.sin(this.rot + 4.1887902047863905)) };
        final int[] y = { (int)(ny2 + 20 * Math.cos(this.rot)), (int)(ny2 + 20 * Math.cos(this.rot + 2.0943951023931953)), (int)(ny2 + 20 * Math.cos(this.rot + 4.1887902047863905)) };
        this.g.setColor(new Color(200, 0, 0));
        this.g.fillPolygon(x, y, 3);
        this.g.setColor(Color.black);
        this.g.drawPolygon(x, y, 3);
        this.rot += 0.1;
        if (this.rot > 6.283185307179586) {
            this.rot = 0.0f;
        }
    }
    
    public void drawLoadArrow(final int x, final int y) {
        final int[] X = { x - 20, x, x, x + 15, x + 15, x, x };
        final int[] Y = { y, y - 20, y - 8, y - 8, y + 8, y + 8, y + 20 };
        this.g.setColor(new Color(245, 245, 245));
        this.g.fillPolygon(X, Y, 7);
        this.g.setColor(Color.black);
        this.g.drawPolygon(X, Y, 7);
    }
    
    public void addBox() {
        if (this.count == 0 && this.total < 20) {
            this.boxX[this.total] = this.gridX + this.gridSize + 5 + this.boxW[this.total] / 2 + 20;
            this.boxY[this.total] = this.gridY + this.boxH[this.total] / 2 - 90;
            this.boxZ[this.total] = -30.0f;
            this.stacked[this.total] = false;
            this.boxC[this.total] = (int)((Math.random() - 1.0E-7) * 6 + 1);
            if (this.total > 2) {
                while (this.boxC[this.total] == this.boxC[this.total - 1] || this.boxC[this.total] == this.boxC[this.total - 2] || this.boxC[this.total] == this.boxC[this.total - 3]) {
                    this.boxC[this.total] = (int)((Math.random() - 1.0E-7) * 6 + 1);
                }
            }
            else {
                this.boxC[this.total] = (int)((Math.random() - 1.0E-7) * 6 + 1);
            }
            ++this.total;
        }
        final float move = (20 - this.count) / 2;
        if (this.checkBounds(this.total - 1, (int)this.boxX[this.total - 1], (int)this.boxY[this.total - 1])) {
            final float[] boxY = this.boxY;
            final int n = this.total - 1;
            boxY[n] += move;
        }
        else {
            this.gameOver = true;
        }
    }
    
    public void sortBoxes() {
        int boxCount = 0;
        final boolean[] check = new boolean[this.total];
        for (int i = 0; i < this.total; ++i) {
            check[i] = false;
        }
        boolean finished = false;
        while (!finished) {
            int current = 0;
            float dist = -1.0f;
            for (int j = 0; j < this.total; ++j) {
                if (!check[j]) {
                    final float temp = this.distance(this.boxX[j], this.boxY[j], this.centreX, this.centreY);
                    if (this.distance(temp, 0.0f, 0.0f, 400 - this.boxZ[j]) > dist) {
                        current = j;
                        dist = this.distance(temp, 0.0f, 0.0f, 400 - this.boxZ[j]);
                    }
                }
            }
            check[this.sort[boxCount] = current] = true;
            ++boxCount;
            finished = true;
            for (int j = 0; j < this.total; ++j) {
                if (!check[j]) {
                    finished = false;
                }
            }
        }
    }
    
    public void drawBox(final int i, final int moveBox, final float x, final float y, final float w, final float h, final float d, final float z, final int col, final int type, final int design) {
        Color c = Color.white;
        if (col == 1) {
            c = new Color(255, 204, 0);
        }
        if (col == 2) {
            c = new Color(204, 153, 0);
        }
        if (col == 3) {
            c = new Color(255, 255, 153);
        }
        if (col == 4) {
            c = new Color(135, 142, 72);
        }
        if (col == 5) {
            c = new Color(232, 255, 255);
        }
        if (col == 6) {
            c = new Color(250, 240, 200);
        }
        final float factor1 = z / 400 + 1;
        final float nx1 = this.centreX - (this.centreX - x + w / 2) * factor1;
        final float ny1 = this.centreY - (this.centreY - y + h / 2) * factor1;
        final float factor2 = (d + z) / 400 + 1;
        final float nx2 = this.centreX - (this.centreX - x + w / 2) * factor2;
        final float ny2 = this.centreY - (this.centreY - y + h / 2) * factor2;
        final int[] polyX = { (int)(nx1 + 0.5), (int)(nx1 + factor1 * w + 0.5), (int)(nx2 + factor2 * w + 0.5), (int)(nx2 + 0.5) };
        final int[] polyY = { (int)(ny1 + 0.5), (int)(ny1 + 0.5), (int)(ny2 + 0.5), (int)(ny2 + 0.5) };
        final int[] polyX2 = { (int)(nx1 + factor1 * w + 0.5), (int)(nx2 + factor2 * w + 0.5), (int)(nx2 + factor2 * w + 0.5), (int)(nx1 + factor1 * w + 0.5) };
        final int[] polyY2 = { (int)(ny1 + 0.5), (int)(ny2 + 0.5), (int)(ny2 + factor2 * h + 0.5), (int)(ny1 + factor1 * h + 0.5) };
        final int[] polyX3 = { (int)(nx1 + 0.5), (int)(nx1 + factor1 * w + 0.5), (int)(nx2 + factor2 * w + 0.5), (int)(nx2 + 0.5) };
        final int[] polyY3 = { (int)(ny1 + factor1 * h + 0.5), (int)(ny1 + factor1 * h + 0.5), (int)(ny2 + factor2 * h + 0.5), (int)(ny2 + factor2 * h + 0.5) };
        final int[] polyX4 = { (int)(nx1 + 0.5), (int)(nx2 + 0.5), (int)(nx2 + 0.5), (int)(nx1 + 0.5) };
        final int[] polyY4 = { (int)(ny1 + 0.5), (int)(ny2 + 0.5), (int)(ny2 + factor2 * h + 0.5), (int)(ny1 + factor1 * h + 0.5) };
        this.g.setColor(c);
        if ((type == 0 && this.boxX[i] < this.gridX + this.gridSize) || (type == 1 && this.boxX[i] > this.gridX + this.gridSize)) {
            if (y - h / 2 >= this.centreY) {
                this.g.fillPolygon(polyX, polyY, 4);
            }
            if (x + w / 2 <= this.centreX) {
                this.g.fillPolygon(polyX2, polyY2, 4);
            }
            if (col == 1) {
                this.g.setColor(new Color(229, 183, 0));
            }
            if (col == 2) {
                this.g.setColor(new Color(183, 137, 0));
            }
            if (col == 3) {
                this.g.setColor(new Color(229, 229, 137));
            }
            if (col == 4) {
                this.g.setColor(new Color(121, 127, 64));
            }
            if (col == 5) {
                this.g.setColor(new Color(208, 229, 229));
            }
            if (col == 6) {
                this.g.setColor(new Color(225, 216, 180));
            }
            if (y + h / 2 <= this.centreY) {
                this.g.fillPolygon(polyX3, polyY3, 4);
            }
            if (x - w / 2 >= this.centreX) {
                this.g.fillPolygon(polyX4, polyY4, 4);
            }
            this.g.setColor(Color.black);
            if (y - h / 2 >= this.centreY) {
                this.g.drawPolygon(polyX, polyY, 4);
            }
            if (x + w / 2 <= this.centreX) {
                this.g.drawPolygon(polyX2, polyY2, 4);
            }
            if (y + h / 2 <= this.centreY) {
                this.g.drawPolygon(polyX3, polyY3, 4);
            }
            if (x - w / 2 >= this.centreX) {
                this.g.drawPolygon(polyX4, polyY4, 4);
            }
            this.g.setColor(c);
            if (i == moveBox) {
                this.g.setColor(new Color(200, 0, 0));
            }
            this.g.fillRect((int)(nx2 + 0.5), (int)(ny2 + 0.5), (int)(factor2 * w + 1 + 0.5), (int)(factor2 * h + 1 + 0.5));
            this.g.setColor(Color.black);
            this.g.drawRect((int)(nx2 + 0.5), (int)(ny2 + 0.5), (int)(factor2 * w + 0.5), (int)(factor2 * h + 0.5));
            if (design == 2) {
                this.g.setColor(new Color(200, 0, 200));
                this.g.fillRect((int)(nx2 + (w - 8) * factor2), (int)(ny2 + 5 * factor2), (int)(5 * factor2), (int)(5 * factor2));
                this.g.setColor(new Color(0, 0, 200));
                this.g.fillRect((int)(nx2 + (w - 16) * factor2), (int)(ny2 + 5 * factor2), (int)(5 * factor2), (int)(5 * factor2));
            }
            if (design == 1) {
                this.g.setColor(Color.black);
                this.g.fillRect((int)(nx2 + 10 * factor2) - 1, (int)(ny2 + 10 * factor2) - 1, (int)(20 * factor2) + 2, (int)(15 * factor2) + 2);
                this.g.setColor(Color.white);
                this.g.fillRect((int)(nx2 + 10 * factor2), (int)(ny2 + 10 * factor2), (int)(20 * factor2), (int)(15 * factor2));
            }
        }
    }
    
    public boolean checkBounds(final int j, final int x, final int y) {
        final int x2 = (int)(x - this.boxW[j] / 2 - this.gridW / 2);
        final int y2 = (int)(y - this.boxH[j] / 2 - this.gridW / 2);
        final int xa = (int)(x + this.boxW[j] / 2 + this.gridW / 2);
        final int ya = (int)(y + this.boxH[j] / 2 + this.gridW / 2);
        final Rectangle r1 = new Rectangle(x2, y2, (int)this.boxW[j] + this.gridW, (int)this.boxH[j] + this.gridW);
        for (int i = 0; i < this.total; ++i) {
            if (i != j) {
                final int x3 = (int)(this.boxX[i] - this.boxW[i] / 2 - this.gridW / 2);
                final int y3 = (int)(this.boxY[i] - this.boxH[i] / 2 - this.gridW / 2);
                final int xb = (int)(this.boxX[i] + this.boxW[i] / 2 + this.gridW / 2);
                final int yb = (int)(this.boxY[i] + this.boxH[i] / 2 + this.gridW / 2);
                final Rectangle r2 = new Rectangle(x3, y3, (int)this.boxW[i] + this.gridW, (int)this.boxH[i] + this.gridW);
                if (r1.intersects(r2)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void run() {
        while (this.animate != null) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
            if (this.begin) {
                if (this.count >= 0 && this.count <= 20 && this.total <= 20 && !this.gameOver) {
                    this.addBox();
                }
                if (this.count == 100) {
                    this.unload = (int)(Math.random() * (this.total - 1));
                }
                if (this.count == 30) {
                    this.unload = -1;
                }
                if (this.getRid != -1) {
                    final float[] boxY = this.boxY;
                    final int getRid = this.getRid;
                    boxY[getRid] += 3;
                    if (this.boxY[this.getRid] > 2 * this.gridY + this.gridSize + 100) {
                        this.deleteBox();
                    }
                }
                ++this.count;
                this.count %= this.add;
                if (this.count == 0 && this.add > 160) {
                    this.add -= 5;
                }
                if (this.numberBounce > 1) {
                    this.bounceDirection = -1;
                }
                if (this.numberBounce < -1) {
                    this.bounceDirection = 1;
                }
                this.numberBounce += (float)(this.bounceDirection * 0.25);
                if (this.total == 10 && this.add == 100) {
                    this.add = 300;
                }
                this.repaint();
            }
            if (this.gameOver) {
                ++this.goc;
                this.unload = -1;
                this.getRid = -1;
            }
            if (this.goc == 100) {
                this.goc = 0;
                this.gameOver = false;
                this.begin = false;
                this.total = 0;
                this.numberBounce = 0.0f;
            }
        }
    }
    
    public void deleteBox() {
        this.boxX[20] = this.boxX[this.getRid];
        this.boxY[20] = this.boxY[this.getRid];
        this.boxW[20] = this.boxW[this.getRid];
        this.boxH[20] = this.boxH[this.getRid];
        this.boxD[20] = this.boxD[this.getRid];
        this.boxZ[20] = this.boxZ[this.getRid];
        this.boxC[20] = this.boxC[this.getRid];
        this.stacked[20] = false;
        this.design[20] = this.design[this.getRid];
        for (int i = this.getRid + 1; i < 21; ++i) {
            this.boxX[i - 1] = this.boxX[i];
            this.boxY[i - 1] = this.boxY[i];
            this.boxW[i - 1] = this.boxW[i];
            this.boxH[i - 1] = this.boxH[i];
            this.boxD[i - 1] = this.boxD[i];
            this.boxZ[i - 1] = this.boxZ[i];
            this.boxC[i - 1] = this.boxC[i];
            this.stacked[i - 1] = this.stacked[i];
            this.design[i - 1] = this.design[i];
        }
        if (this.moveBoxNumber >= this.getRid) {
            --this.moveBoxNumber;
        }
        if (this.moveBoxNumber >= this.unload && this.unload != -1) {
            --this.unload;
        }
        this.getRid = -1;
        --this.total;
    }
    
    public void update(final Graphics screen) {
        this.paint(screen);
    }
    
    public float distance(final float x1, final float y1, final float x2, final float y2) {
        return (float)Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }
}
