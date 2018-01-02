import java.net.MalformedURLException;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.AudioClip;
import java.net.URLConnection;
import java.net.URL;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class tetris extends Applet implements Runnable, KeyListener, MouseListener
{
    public Thread animationthread;
    Image bufferimage;
    Graphics bufferg;
    Random rand;
    Image[] images;
    URL urlb;
    URLConnection connection;
    AudioClip tap;
    AudioClip pop;
    AudioClip rot;
    boolean sound;
    boolean up;
    boolean down;
    boolean left;
    boolean undo;
    boolean pressed;
    String s;
    String sscore;
    boolean right;
    boolean ah;
    boolean over;
    boolean canspace;
    boolean alarm;
    boolean k;
    boolean ctrl;
    boolean space;
    boolean name;
    boolean place;
    int game;
    int[][] board;
    int piece;
    int[] pieceX;
    int[] pieceY;
    int[] ppieceX;
    int[] ppieceY;
    int pos;
    int lines;
    int totallines;
    int speed;
    int scounter;
    int score;
    int ppiece;
    boolean la;
    
    public tetris() {
        this.animationthread = null;
        this.rand = new Random();
        this.sound = true;
        this.up = false;
        this.down = false;
        this.left = false;
        this.undo = false;
        this.pressed = false;
        this.s = "";
        this.sscore = "";
        this.right = false;
        this.ah = false;
        this.over = false;
        this.canspace = true;
        this.alarm = false;
        this.k = false;
        this.ctrl = false;
        this.space = false;
        this.name = false;
        this.game = 0;
        this.board = new int[12][22];
        this.pieceX = new int[4];
        this.pieceY = new int[4];
        this.ppieceX = new int[4];
        this.ppieceY = new int[4];
        this.lines = 0;
        this.totallines = 0;
        this.speed = 20;
        this.scounter = 0;
        this.score = 0;
        this.ppiece = 0;
        this.la = false;
    }
    
    public void init() {
        try {
            this.tap = this.getAudioClip(this.getCodeBase(), "tap.aiff");
        }
        catch (Exception ex) {}
        try {
            this.pop = this.getAudioClip(this.getCodeBase(), "pop.au");
        }
        catch (Exception ex2) {}
        try {
            this.rot = this.getAudioClip(this.getCodeBase(), "rot.aiff");
        }
        catch (Exception ex3) {}
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.images = new Image[20];
        final MediaTracker mediaTracker = new MediaTracker(this);
        for (int i = 0; i < 20; ++i) {
            mediaTracker.addImage(this.images[i] = this.getImage(this.getDocumentBase(), "image" + i + ".JPG"), i);
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex4) {
            System.out.println("Something happened while reading images...");
        }
        this.bufferimage = this.createImage(this.getSize().width, this.getSize().height);
        this.bufferg = this.bufferimage.getGraphics();
    }
    
    public void start() {
        if (this.animationthread == null) {
            (this.animationthread = new Thread(this, "animationthread")).start();
        }
    }
    
    public void run() {
        while (true) {
            this.update(this.getGraphics());
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.game == 0) {
            this.bufferg.setColor(Color.black);
            this.bufferg.fillRect(0, 0, this.getSize().width, this.getSize().height);
            this.bufferg.setColor(Color.white);
            this.bufferg.drawString("Tetris", 120, 100);
            this.bufferg.drawString("Made by Ari Ronen", 120, 150);
            this.bufferg.drawString("Click to Start", 120, 175);
            if (this.pressed) {
                this.game = 1;
            }
        }
        if (this.game == 1) {
            this.clear();
            this.piece = this.ppiece;
            this.ppiece = Math.abs(this.rand.nextInt()) % 7 + 1;
            this.game = 2;
        }
        else if (this.game == 2) {
            this.piece = this.ppiece;
            this.ppiece = Math.abs(this.rand.nextInt()) % 7 + 1;
            this.bufferg.setColor(Color.black);
            this.bufferg.fillRect(193, 0, 100, this.getSize().height);
            this.bufferg.setColor(Color.white);
            this.bufferg.drawString("Score:", 220, 100);
            this.bufferg.drawString("" + this.score, 220, 125);
            this.bufferg.drawString("Lines:", 220, 175);
            this.bufferg.drawString("" + this.totallines, 220, 200);
            this.bufferg.drawString("Level: " + this.totallines / 10, 220, 250);
            this.bufferg.drawString("arigames.com", 195, 300);
            if (this.ppiece == 1) {
                this.ppieceX[0] = 64;
                this.ppieceX[1] = 80;
                this.ppieceX[2] = 96;
                this.ppieceX[3] = 112;
                this.ppieceY[0] = 0;
                this.ppieceY[1] = 0;
                this.ppieceY[2] = 0;
                this.ppieceY[3] = 0;
            }
            if (this.ppiece == 2) {
                this.ppieceX[0] = 80;
                this.ppieceX[1] = 80;
                this.ppieceX[2] = 96;
                this.ppieceX[3] = 96;
                this.ppieceY[0] = 16;
                this.ppieceY[1] = 0;
                this.ppieceY[2] = 16;
                this.ppieceY[3] = 0;
            }
            if (this.ppiece == 3) {
                this.ppieceX[0] = 80;
                this.ppieceX[1] = 96;
                this.ppieceX[2] = 96;
                this.ppieceX[3] = 112;
                this.ppieceY[0] = 0;
                this.ppieceY[1] = 16;
                this.ppieceY[2] = 0;
                this.ppieceY[3] = 0;
            }
            if (this.ppiece == 4) {
                this.ppieceX[0] = 80;
                this.ppieceX[1] = 96;
                this.ppieceX[2] = 96;
                this.ppieceX[3] = 112;
                this.ppieceY[0] = 16;
                this.ppieceY[1] = 16;
                this.ppieceY[2] = 0;
                this.ppieceY[3] = 0;
            }
            if (this.ppiece == 5) {
                this.ppieceX[0] = 80;
                this.ppieceX[1] = 96;
                this.ppieceX[2] = 96;
                this.ppieceX[3] = 112;
                this.ppieceY[0] = 0;
                this.ppieceY[1] = 16;
                this.ppieceY[2] = 0;
                this.ppieceY[3] = 16;
            }
            if (this.ppiece == 6) {
                this.ppieceX[0] = 80;
                this.ppieceX[1] = 80;
                this.ppieceX[2] = 96;
                this.ppieceX[3] = 112;
                this.ppieceY[0] = 16;
                this.ppieceY[1] = 0;
                this.ppieceY[2] = 0;
                this.ppieceY[3] = 0;
            }
            if (this.ppiece == 7) {
                this.ppieceX[0] = 80;
                this.ppieceX[1] = 96;
                this.ppieceX[2] = 112;
                this.ppieceX[3] = 112;
                this.ppieceY[0] = 0;
                this.ppieceY[1] = 0;
                this.ppieceY[2] = 0;
                this.ppieceY[3] = 16;
            }
            for (int i = 0; i < 4; ++i) {
                final int[] ppieceX = this.ppieceX;
                final int n = i;
                ppieceX[n] += 145;
                final int[] ppieceY = this.ppieceY;
                final int n2 = i;
                ppieceY[n2] += 20;
                this.bufferg.drawImage(this.images[this.ppiece], this.ppieceX[i], this.ppieceY[i], this);
            }
            this.choosepiece();
            this.game = 3;
            for (int j = 0; j < 4; ++j) {
                this.bufferg.drawImage(this.images[this.piece], this.pieceX[j], this.pieceY[j], this);
                if (this.board[this.pieceX[j] / 16][this.pieceY[j] / 16] != 0) {
                    this.game = 7;
                }
            }
        }
        else if (this.game == 3) {
            for (int k = 0; k < 4; ++k) {
                this.bufferg.drawImage(this.images[0], this.pieceX[k], this.pieceY[k], this);
            }
            this.scounter += this.totallines / 10 + 1;
            if (this.up || this.ctrl) {
                if (this.up) {
                    this.clockrotate();
                }
                else {
                    this.rotate();
                }
                for (int l = 0; l < 4; ++l) {
                    if (this.pieceX[l] < 0 || this.pieceX[l] > 176) {
                        this.undo = true;
                    }
                    else if (this.board[this.pieceX[l] / 16][this.pieceY[l] / 16] != 0) {
                        this.undo = true;
                    }
                }
                if (this.undo) {
                    if (this.ctrl) {
                        this.rotate();
                        this.rotate();
                        this.rotate();
                    }
                    else {
                        this.clockrotate();
                        this.clockrotate();
                        this.clockrotate();
                    }
                }
                else if (this.sound) {
                    this.rot.play();
                }
                this.up = false;
                this.ctrl = false;
            }
            if (this.left) {
                for (int n3 = 0; n3 < 4; ++n3) {
                    final int[] pieceX = this.pieceX;
                    final int n4 = n3;
                    pieceX[n4] -= 16;
                    if (this.pieceX[n3] < 0) {
                        this.undo = true;
                    }
                    else if (this.board[this.pieceX[n3] / 16][this.pieceY[n3] / 16] != 0) {
                        this.undo = true;
                    }
                }
                if (this.undo) {
                    for (int n5 = 0; n5 < 4; ++n5) {
                        final int[] pieceX2 = this.pieceX;
                        final int n6 = n5;
                        pieceX2[n6] += 16;
                    }
                    this.undo = false;
                }
            }
            if (this.right) {
                for (int n7 = 0; n7 < 4; ++n7) {
                    final int[] pieceX3 = this.pieceX;
                    final int n8 = n7;
                    pieceX3[n8] += 16;
                    if (this.pieceX[n7] > 176) {
                        this.undo = true;
                    }
                    else if (this.board[this.pieceX[n7] / 16][this.pieceY[n7] / 16] != 0) {
                        this.undo = true;
                    }
                }
                if (this.undo) {
                    for (int n9 = 0; n9 < 4; ++n9) {
                        final int[] pieceX4 = this.pieceX;
                        final int n10 = n9;
                        pieceX4[n10] -= 16;
                    }
                    this.undo = false;
                }
            }
            if (this.down) {
                for (int n11 = 0; n11 < 4; ++n11) {
                    final int[] pieceY = this.pieceY;
                    final int n12 = n11;
                    pieceY[n12] += 16;
                    if (this.pieceY[n11] > 336) {
                        this.place = true;
                        this.undo = true;
                    }
                    else if (this.board[this.pieceX[n11] / 16][this.pieceY[n11] / 16] != 0) {
                        this.undo = true;
                    }
                }
                if (this.undo) {
                    for (int n13 = 0; n13 < 4; ++n13) {
                        final int[] pieceY2 = this.pieceY;
                        final int n14 = n13;
                        pieceY2[n14] -= 16;
                    }
                    this.undo = false;
                }
            }
            if (this.scounter > this.speed) {
                for (int n15 = 0; n15 < 4; ++n15) {
                    final int[] pieceY3 = this.pieceY;
                    final int n16 = n15;
                    pieceY3[n16] += 16;
                    if (this.pieceY[n15] > 336) {
                        this.place = true;
                        this.undo = true;
                    }
                    else if (this.board[this.pieceX[n15] / 16][this.pieceY[n15] / 16] != 0) {
                        this.place = true;
                        this.undo = true;
                    }
                }
                if (this.undo) {
                    for (int n17 = 0; n17 < 4; ++n17) {
                        final int[] pieceY4 = this.pieceY;
                        final int n18 = n17;
                        pieceY4[n18] -= 16;
                    }
                    this.undo = false;
                }
                this.scounter = 0;
            }
            if (this.space) {
                while (!this.place) {
                    for (int n19 = 0; n19 < 4; ++n19) {
                        final int[] pieceY5 = this.pieceY;
                        final int n20 = n19;
                        pieceY5[n20] += 16;
                        if (this.pieceY[n19] > 336) {
                            this.place = true;
                            this.undo = true;
                            this.space = false;
                        }
                        else if (this.board[this.pieceX[n19] / 16][this.pieceY[n19] / 16] != 0) {
                            this.place = true;
                            this.undo = true;
                            this.space = false;
                        }
                    }
                    if (this.undo) {
                        for (int n21 = 0; n21 < 4; ++n21) {
                            final int[] pieceY6 = this.pieceY;
                            final int n22 = n21;
                            pieceY6[n22] -= 16;
                        }
                        this.undo = false;
                    }
                }
            }
            for (int n23 = 0; n23 < 4; ++n23) {
                this.bufferg.drawImage(this.images[this.piece], this.pieceX[n23], this.pieceY[n23], this);
            }
        }
        if (this.place) {
            this.score += 10;
            this.game = 2;
            this.place = false;
            this.alarm = true;
            this.lines = 0;
            for (int n24 = 0; n24 < 4; ++n24) {
                this.board[this.pieceX[n24] / 16][this.pieceY[n24] / 16] = this.piece;
            }
            for (int n25 = 0; n25 < 22; ++n25) {
                this.alarm = true;
                for (int n26 = 0; n26 < 12; ++n26) {
                    if (this.board[n26][n25] == 0) {
                        this.alarm = false;
                    }
                }
                if (this.alarm) {
                    this.shiftdown(n25);
                    ++this.lines;
                }
            }
            if (this.sound) {
                if (this.lines == 0) {
                    this.tap.play();
                }
                else {
                    this.pop.play();
                }
            }
            this.totallines += this.lines;
            if (this.lines == 1) {
                this.score += 40;
            }
            if (this.lines == 2) {
                this.score += 100;
            }
            if (this.lines == 3) {
                this.score += 300;
            }
            if (this.lines == 4) {
                this.score += 1200;
            }
            for (int n27 = 0; n27 < 12; ++n27) {
                for (int n28 = 0; n28 < 22; ++n28) {
                    this.bufferg.drawImage(this.images[this.board[n27][n28]], n27 * 16, n28 * 16, this);
                }
            }
        }
        if (this.game == 7) {
            this.name = true;
            this.bufferg.setColor(Color.black);
            this.bufferg.fillRect(0, 0, this.getSize().width, this.getSize().height);
            this.bufferg.setColor(Color.white);
            this.bufferg.drawString("... Game Over ...", 75, 52);
            this.bufferg.drawString("Score:" + this.score, 75, 77);
            this.bufferg.drawString(" Click to continue", 50, 127);
            this.name = false;
            this.game = 8;
            this.pressed = false;
        }
        if (this.game == 8 && this.pressed) {
            this.game = 0;
            this.pressed = false;
        }
        graphics.drawImage(this.bufferimage, 0, 0, this);
    }
    
    public void clear() {
        this.up = false;
        this.down = false;
        this.left = false;
        this.undo = false;
        this.right = false;
        this.name = false;
        this.ah = false;
        this.s = "";
        this.over = false;
        this.canspace = true;
        this.alarm = false;
        this.k = false;
        this.ctrl = false;
        this.space = false;
        this.place = false;
        this.game = 0;
        this.piece = 0;
        this.pos = 0;
        this.lines = 0;
        this.totallines = 0;
        this.speed = 20;
        this.scounter = 0;
        this.score = 0;
        this.la = false;
        for (int i = 0; i < 12; ++i) {
            for (int j = 0; j < 22; ++j) {
                this.board[i][j] = 0;
            }
        }
        this.bufferg.setColor(Color.black);
        this.bufferg.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.clearpiece();
        this.bufferg.setColor(Color.white);
        this.bufferg.fillRect(192, 0, 1, this.getSize().height);
    }
    
    public void clearpiece() {
        for (int i = 0; i < 4; ++i) {
            this.pieceX[i] = 0;
            this.pieceY[i] = 0;
        }
    }
    
    public void shiftdown(final int n) {
        for (int i = n; i > -1; --i) {
            for (int j = 0; j < 12; ++j) {
                if (i == 0) {
                    this.board[j][i] = 0;
                }
                else {
                    this.board[j][i] = this.board[j][i - 1];
                }
            }
        }
    }
    
    public void choosepiece() {
        if (this.piece == 1) {
            this.pieceX[0] = 64;
            this.pieceX[1] = 80;
            this.pieceX[2] = 96;
            this.pieceX[3] = 112;
            this.pieceY[0] = 0;
            this.pieceY[1] = 0;
            this.pieceY[2] = 0;
            this.pieceY[3] = 0;
        }
        if (this.piece == 2) {
            this.pieceX[0] = 80;
            this.pieceX[1] = 80;
            this.pieceX[2] = 96;
            this.pieceX[3] = 96;
            this.pieceY[0] = 16;
            this.pieceY[1] = 0;
            this.pieceY[2] = 16;
            this.pieceY[3] = 0;
        }
        if (this.piece == 3) {
            this.pieceX[0] = 80;
            this.pieceX[1] = 96;
            this.pieceX[2] = 96;
            this.pieceX[3] = 112;
            this.pieceY[0] = 0;
            this.pieceY[1] = 16;
            this.pieceY[2] = 0;
            this.pieceY[3] = 0;
        }
        if (this.piece == 4) {
            this.pieceX[0] = 80;
            this.pieceX[1] = 96;
            this.pieceX[2] = 96;
            this.pieceX[3] = 112;
            this.pieceY[0] = 16;
            this.pieceY[1] = 16;
            this.pieceY[2] = 0;
            this.pieceY[3] = 0;
        }
        if (this.piece == 5) {
            this.pieceX[0] = 80;
            this.pieceX[1] = 96;
            this.pieceX[2] = 96;
            this.pieceX[3] = 112;
            this.pieceY[0] = 0;
            this.pieceY[1] = 16;
            this.pieceY[2] = 0;
            this.pieceY[3] = 16;
        }
        if (this.piece == 6) {
            this.pieceX[0] = 80;
            this.pieceX[1] = 80;
            this.pieceX[2] = 96;
            this.pieceX[3] = 112;
            this.pieceY[0] = 16;
            this.pieceY[1] = 0;
            this.pieceY[2] = 0;
            this.pieceY[3] = 0;
        }
        if (this.piece == 7) {
            this.pieceX[0] = 80;
            this.pieceX[1] = 96;
            this.pieceX[2] = 112;
            this.pieceX[3] = 112;
            this.pieceY[0] = 0;
            this.pieceY[1] = 0;
            this.pieceY[2] = 0;
            this.pieceY[3] = 16;
        }
        this.pos = 1;
    }
    
    public void rotate() {
        if (this.piece == 1) {
            if (this.pos == 1) {
                final int[] pieceX = this.pieceX;
                final int n = 0;
                pieceX[n] += 32;
                final int[] pieceX2 = this.pieceX;
                final int n2 = 1;
                pieceX2[n2] += 16;
                final int[] pieceX3 = this.pieceX;
                final int n3 = 3;
                pieceX3[n3] -= 16;
                final int[] pieceY = this.pieceY;
                final int n4 = 1;
                pieceY[n4] += 16;
                final int[] pieceY2 = this.pieceY;
                final int n5 = 2;
                pieceY2[n5] += 32;
                final int[] pieceY3 = this.pieceY;
                final int n6 = 3;
                pieceY3[n6] += 48;
                this.pos = 2;
            }
            else {
                final int[] pieceX4 = this.pieceX;
                final int n7 = 0;
                pieceX4[n7] -= 32;
                final int[] pieceX5 = this.pieceX;
                final int n8 = 1;
                pieceX5[n8] -= 16;
                final int[] pieceX6 = this.pieceX;
                final int n9 = 3;
                pieceX6[n9] += 16;
                final int[] pieceY4 = this.pieceY;
                final int n10 = 1;
                pieceY4[n10] -= 16;
                final int[] pieceY5 = this.pieceY;
                final int n11 = 2;
                pieceY5[n11] -= 32;
                final int[] pieceY6 = this.pieceY;
                final int n12 = 3;
                pieceY6[n12] -= 48;
                this.pos = 1;
            }
        }
        if (this.piece == 2) {}
        if (this.piece == 3) {
            if (this.pos == 1) {
                final int[] pieceX7 = this.pieceX;
                final int n13 = 0;
                pieceX7[n13] += 0;
                final int[] pieceX8 = this.pieceX;
                final int n14 = 1;
                pieceX8[n14] += 0;
                final int[] pieceX9 = this.pieceX;
                final int n15 = 2;
                pieceX9[n15] -= 16;
                final int[] pieceX10 = this.pieceX;
                final int n16 = 3;
                pieceX10[n16] -= 32;
                final int[] pieceY7 = this.pieceY;
                final int n17 = 0;
                pieceY7[n17] += 32;
                final int[] pieceY8 = this.pieceY;
                final int n18 = 1;
                pieceY8[n18] += 0;
                final int[] pieceY9 = this.pieceY;
                final int n19 = 2;
                pieceY9[n19] += 16;
                final int[] pieceY10 = this.pieceY;
                final int n20 = 3;
                pieceY10[n20] += 0;
                this.pos = 2;
            }
            else if (this.pos == 2) {
                final int[] pieceX11 = this.pieceX;
                final int n21 = 0;
                pieceX11[n21] += 0;
                final int[] pieceX12 = this.pieceX;
                final int n22 = 1;
                pieceX12[n22] += 0;
                final int[] pieceX13 = this.pieceX;
                final int n23 = 2;
                pieceX13[n23] += 16;
                final int[] pieceX14 = this.pieceX;
                final int n24 = 3;
                pieceX14[n24] += 32;
                final int[] pieceY11 = this.pieceY;
                final int n25 = 0;
                pieceY11[n25] -= 16;
                final int[] pieceY12 = this.pieceY;
                final int n26 = 1;
                pieceY12[n26] -= 0;
                final int[] pieceY13 = this.pieceY;
                final int n27 = 2;
                pieceY13[n27] -= 16;
                final int[] pieceY14 = this.pieceY;
                final int n28 = 3;
                pieceY14[n28] += 16;
                this.pos = 3;
            }
            else if (this.pos == 3) {
                final int[] pieceX15 = this.pieceX;
                final int n29 = 0;
                pieceX15[n29] += 32;
                final int[] pieceX16 = this.pieceX;
                final int n30 = 1;
                pieceX16[n30] -= 0;
                final int[] pieceX17 = this.pieceX;
                final int n31 = 2;
                pieceX17[n31] += 16;
                final int[] pieceX18 = this.pieceX;
                final int n32 = 3;
                pieceX18[n32] += 0;
                final int[] pieceY15 = this.pieceY;
                final int n33 = 0;
                pieceY15[n33] += 16;
                final int[] pieceY16 = this.pieceY;
                final int n34 = 1;
                pieceY16[n34] -= 0;
                final int[] pieceY17 = this.pieceY;
                final int n35 = 2;
                pieceY17[n35] -= 0;
                final int[] pieceY18 = this.pieceY;
                final int n36 = 3;
                pieceY18[n36] -= 0;
                this.pos = 4;
            }
            else {
                final int[] pieceX19 = this.pieceX;
                final int n37 = 0;
                pieceX19[n37] -= 32;
                final int[] pieceX20 = this.pieceX;
                final int n38 = 1;
                pieceX20[n38] -= 0;
                final int[] pieceX21 = this.pieceX;
                final int n39 = 2;
                pieceX21[n39] -= 16;
                final int[] pieceX22 = this.pieceX;
                final int n40 = 3;
                pieceX22[n40] += 0;
                final int[] pieceY19 = this.pieceY;
                final int n41 = 0;
                pieceY19[n41] -= 32;
                final int[] pieceY20 = this.pieceY;
                final int n42 = 1;
                pieceY20[n42] -= 0;
                final int[] pieceY21 = this.pieceY;
                final int n43 = 2;
                pieceY21[n43] -= 0;
                final int[] pieceY22 = this.pieceY;
                final int n44 = 3;
                pieceY22[n44] -= 16;
                this.pos = 1;
            }
        }
        if (this.piece == 4) {
            if (this.pos == 1) {
                final int[] pieceX23 = this.pieceX;
                final int n45 = 2;
                pieceX23[n45] -= 16;
                final int[] pieceX24 = this.pieceX;
                final int n46 = 3;
                pieceX24[n46] -= 16;
                final int[] pieceY23 = this.pieceY;
                final int n47 = 3;
                pieceY23[n47] += 32;
                this.pos = 2;
            }
            else {
                final int[] pieceX25 = this.pieceX;
                final int n48 = 2;
                pieceX25[n48] += 16;
                final int[] pieceX26 = this.pieceX;
                final int n49 = 3;
                pieceX26[n49] += 16;
                final int[] pieceY24 = this.pieceY;
                final int n50 = 3;
                pieceY24[n50] -= 32;
                this.pos = 1;
            }
        }
        if (this.piece == 5) {
            if (this.pos == 1) {
                final int[] pieceX27 = this.pieceX;
                final int n51 = 3;
                pieceX27[n51] -= 32;
                final int[] pieceY25 = this.pieceY;
                final int n52 = 0;
                pieceY25[n52] += 32;
                this.pos = 2;
            }
            else {
                final int[] pieceX28 = this.pieceX;
                final int n53 = 3;
                pieceX28[n53] += 32;
                final int[] pieceY26 = this.pieceY;
                final int n54 = 0;
                pieceY26[n54] -= 32;
                this.pos = 1;
            }
        }
        if (this.piece == 6) {
            if (this.pos == 1) {
                final int[] pieceX29 = this.pieceX;
                final int n55 = 3;
                pieceX29[n55] -= 32;
                final int[] pieceY27 = this.pieceY;
                final int n56 = 2;
                pieceY27[n56] += 32;
                final int[] pieceY28 = this.pieceY;
                final int n57 = 3;
                pieceY28[n57] += 32;
                this.pos = 2;
            }
            else if (this.pos == 2) {
                final int[] pieceX30 = this.pieceX;
                final int n58 = 1;
                pieceX30[n58] -= 16;
                final int[] pieceX31 = this.pieceX;
                final int n59 = 3;
                pieceX31[n59] += 16;
                final int[] pieceY29 = this.pieceY;
                final int n60 = 1;
                pieceY29[n60] += 16;
                final int[] pieceY30 = this.pieceY;
                final int n61 = 2;
                pieceY30[n61] -= 16;
                final int[] pieceY31 = this.pieceY;
                final int n62 = 3;
                pieceY31[n62] -= 32;
                this.pos = 3;
            }
            else if (this.pos == 3) {
                final int[] pieceX32 = this.pieceX;
                final int n63 = 1;
                pieceX32[n63] += 32;
                final int[] pieceX33 = this.pieceX;
                final int n64 = 3;
                pieceX33[n64] += 0;
                final int[] pieceY32 = this.pieceY;
                final int n65 = 0;
                pieceY32[n65] -= 16;
                final int[] pieceY33 = this.pieceY;
                final int n66 = 1;
                pieceY33[n66] += 16;
                this.pos = 4;
            }
            else {
                final int[] pieceX34 = this.pieceX;
                final int n67 = 1;
                pieceX34[n67] -= 16;
                final int[] pieceX35 = this.pieceX;
                final int n68 = 3;
                pieceX35[n68] += 16;
                final int[] pieceY34 = this.pieceY;
                final int n69 = 1;
                pieceY34[n69] -= 32;
                final int[] pieceY35 = this.pieceY;
                final int n70 = 2;
                pieceY35[n70] -= 16;
                final int[] pieceY36 = this.pieceY;
                final int n71 = 0;
                pieceY36[n71] += 16;
                this.pos = 1;
            }
        }
        if (this.piece == 7) {
            if (this.pos == 1) {
                final int[] pieceX36 = this.pieceX;
                final int n72 = 2;
                pieceX36[n72] -= 32;
                final int[] pieceX37 = this.pieceX;
                final int n73 = 3;
                pieceX37[n73] -= 32;
                final int[] pieceY37 = this.pieceY;
                final int n74 = 2;
                pieceY37[n74] += 32;
                this.pos = 2;
            }
            else if (this.pos == 2) {
                final int[] pieceX38 = this.pieceX;
                final int n75 = 2;
                pieceX38[n75] += 32;
                final int[] pieceY38 = this.pieceY;
                final int n76 = 1;
                pieceY38[n76] += 16;
                final int[] pieceY39 = this.pieceY;
                final int n77 = 2;
                pieceY39[n77] -= 16;
                this.pos = 3;
            }
            else if (this.pos == 3) {
                final int[] pieceX39 = this.pieceX;
                final int n78 = 2;
                pieceX39[n78] -= 16;
                final int[] pieceX40 = this.pieceX;
                final int n79 = 0;
                pieceX40[n79] += 16;
                final int[] pieceY40 = this.pieceY;
                final int n80 = 2;
                pieceY40[n80] += 16;
                final int[] pieceY41 = this.pieceY;
                final int n81 = 3;
                pieceY41[n81] += 16;
                this.pos = 4;
            }
            else {
                final int[] pieceX41 = this.pieceX;
                final int n82 = 0;
                pieceX41[n82] -= 16;
                final int[] pieceX42 = this.pieceX;
                final int n83 = 2;
                pieceX42[n83] += 16;
                final int[] pieceX43 = this.pieceX;
                final int n84 = 3;
                pieceX43[n84] += 32;
                final int[] pieceY42 = this.pieceY;
                final int n85 = 1;
                pieceY42[n85] -= 16;
                final int[] pieceY43 = this.pieceY;
                final int n86 = 2;
                pieceY43[n86] -= 32;
                final int[] pieceY44 = this.pieceY;
                final int n87 = 3;
                pieceY44[n87] -= 16;
                this.pos = 1;
            }
        }
    }
    
    public void clockrotate() {
        if (this.piece == 1 || this.piece == 2 || this.piece == 4 || this.piece == 5) {
            this.rotate();
        }
        else {
            if (this.piece == 3) {
                if (this.pos == 1) {
                    final int[] pieceX = this.pieceX;
                    final int n = 0;
                    pieceX[n] += 32;
                    final int[] pieceX2 = this.pieceX;
                    final int n2 = 2;
                    pieceX2[n2] += 16;
                    final int[] pieceY = this.pieceY;
                    final int n3 = 0;
                    pieceY[n3] += 32;
                    final int[] pieceY2 = this.pieceY;
                    final int n4 = 3;
                    pieceY2[n4] += 16;
                    this.pos = 4;
                }
                else if (this.pos == 2) {
                    final int[] pieceX3 = this.pieceX;
                    final int n5 = 2;
                    pieceX3[n5] += 16;
                    final int[] pieceX4 = this.pieceX;
                    final int n6 = 3;
                    pieceX4[n6] += 32;
                    final int[] pieceY3 = this.pieceY;
                    final int n7 = 0;
                    pieceY3[n7] -= 32;
                    final int[] pieceY4 = this.pieceY;
                    final int n8 = 2;
                    pieceY4[n8] -= 16;
                    this.pos = 1;
                }
                else if (this.pos == 3) {
                    final int[] pieceX5 = this.pieceX;
                    final int n9 = 2;
                    pieceX5[n9] -= 16;
                    final int[] pieceX6 = this.pieceX;
                    final int n10 = 3;
                    pieceX6[n10] -= 32;
                    final int[] pieceY5 = this.pieceY;
                    final int n11 = 0;
                    pieceY5[n11] += 16;
                    final int[] pieceY6 = this.pieceY;
                    final int n12 = 2;
                    pieceY6[n12] += 16;
                    final int[] pieceY7 = this.pieceY;
                    final int n13 = 3;
                    pieceY7[n13] -= 16;
                    this.pos = 2;
                }
                else {
                    final int[] pieceX7 = this.pieceX;
                    final int n14 = 0;
                    pieceX7[n14] -= 32;
                    final int[] pieceX8 = this.pieceX;
                    final int n15 = 2;
                    pieceX8[n15] -= 16;
                    final int[] pieceY8 = this.pieceY;
                    final int n16 = 0;
                    pieceY8[n16] -= 16;
                    this.pos = 3;
                }
            }
            if (this.piece == 6) {
                if (this.pos == 1) {
                    final int[] pieceX9 = this.pieceX;
                    final int n17 = 1;
                    pieceX9[n17] += 16;
                    final int[] pieceX10 = this.pieceX;
                    final int n18 = 3;
                    pieceX10[n18] -= 16;
                    final int[] pieceY9 = this.pieceY;
                    final int n19 = 1;
                    pieceY9[n19] += 32;
                    final int[] pieceY10 = this.pieceY;
                    final int n20 = 2;
                    pieceY10[n20] += 16;
                    final int[] pieceY11 = this.pieceY;
                    final int n21 = 0;
                    pieceY11[n21] -= 16;
                    this.pos = 4;
                }
                else if (this.pos == 2) {
                    final int[] pieceX11 = this.pieceX;
                    final int n22 = 3;
                    pieceX11[n22] += 32;
                    final int[] pieceY12 = this.pieceY;
                    final int n23 = 2;
                    pieceY12[n23] -= 32;
                    final int[] pieceY13 = this.pieceY;
                    final int n24 = 3;
                    pieceY13[n24] -= 32;
                    this.pos = 1;
                }
                else if (this.pos == 3) {
                    final int[] pieceX12 = this.pieceX;
                    final int n25 = 1;
                    pieceX12[n25] += 16;
                    final int[] pieceX13 = this.pieceX;
                    final int n26 = 3;
                    pieceX13[n26] -= 16;
                    final int[] pieceY14 = this.pieceY;
                    final int n27 = 1;
                    pieceY14[n27] -= 16;
                    final int[] pieceY15 = this.pieceY;
                    final int n28 = 2;
                    pieceY15[n28] += 16;
                    final int[] pieceY16 = this.pieceY;
                    final int n29 = 3;
                    pieceY16[n29] += 32;
                    this.pos = 2;
                }
                else {
                    final int[] pieceX14 = this.pieceX;
                    final int n30 = 1;
                    pieceX14[n30] -= 32;
                    final int[] pieceX15 = this.pieceX;
                    final int n31 = 3;
                    pieceX15[n31] -= 0;
                    final int[] pieceY17 = this.pieceY;
                    final int n32 = 0;
                    pieceY17[n32] += 16;
                    final int[] pieceY18 = this.pieceY;
                    final int n33 = 1;
                    pieceY18[n33] -= 16;
                    this.pos = 3;
                }
            }
            if (this.piece == 7) {
                if (this.pos == 1) {
                    final int[] pieceX16 = this.pieceX;
                    final int n34 = 0;
                    pieceX16[n34] += 16;
                    final int[] pieceX17 = this.pieceX;
                    final int n35 = 2;
                    pieceX17[n35] -= 16;
                    final int[] pieceX18 = this.pieceX;
                    final int n36 = 3;
                    pieceX18[n36] -= 32;
                    final int[] pieceY19 = this.pieceY;
                    final int n37 = 1;
                    pieceY19[n37] += 16;
                    final int[] pieceY20 = this.pieceY;
                    final int n38 = 2;
                    pieceY20[n38] += 32;
                    final int[] pieceY21 = this.pieceY;
                    final int n39 = 3;
                    pieceY21[n39] += 16;
                    this.pos = 4;
                }
                else if (this.pos == 2) {
                    final int[] pieceX19 = this.pieceX;
                    final int n40 = 2;
                    pieceX19[n40] += 32;
                    final int[] pieceX20 = this.pieceX;
                    final int n41 = 3;
                    pieceX20[n41] += 32;
                    final int[] pieceY22 = this.pieceY;
                    final int n42 = 2;
                    pieceY22[n42] -= 32;
                    this.pos = 1;
                }
                else if (this.pos == 3) {
                    final int[] pieceX21 = this.pieceX;
                    final int n43 = 2;
                    pieceX21[n43] -= 32;
                    final int[] pieceY23 = this.pieceY;
                    final int n44 = 1;
                    pieceY23[n44] -= 16;
                    final int[] pieceY24 = this.pieceY;
                    final int n45 = 2;
                    pieceY24[n45] += 16;
                    this.pos = 2;
                }
                else {
                    final int[] pieceX22 = this.pieceX;
                    final int n46 = 2;
                    pieceX22[n46] += 16;
                    final int[] pieceX23 = this.pieceX;
                    final int n47 = 0;
                    pieceX23[n47] -= 16;
                    final int[] pieceY25 = this.pieceY;
                    final int n48 = 2;
                    pieceY25[n48] -= 16;
                    final int[] pieceY26 = this.pieceY;
                    final int n49 = 3;
                    pieceY26[n49] -= 16;
                    this.pos = 3;
                }
            }
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 17) {
            this.ctrl = true;
        }
        if (keyEvent.getKeyCode() == 38) {
            this.up = true;
        }
        if (keyEvent.getKeyCode() == 37) {
            this.left = true;
        }
        if (keyEvent.getKeyCode() == 39) {
            this.right = true;
        }
        if (keyEvent.getKeyCode() == 40) {
            this.down = true;
        }
        if (keyEvent.getKeyCode() == 32 && this.canspace) {
            this.space = true;
            this.canspace = false;
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 17) {
            this.ctrl = false;
        }
        if (keyEvent.getKeyCode() == 38) {
            this.up = false;
        }
        if (keyEvent.getKeyCode() == 37) {
            this.left = false;
        }
        if (keyEvent.getKeyCode() == 39) {
            this.right = false;
        }
        if (keyEvent.getKeyCode() == 40) {
            this.down = false;
        }
        if (keyEvent.getKeyCode() == 32) {
            this.canspace = true;
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        if (this.name) {
            final char keyChar = keyEvent.getKeyChar();
            if (keyChar != '\uffff' && !Character.isISOControl(keyChar) && keyChar != '|') {
                this.s += keyChar;
                keyEvent.consume();
            }
        }
        else if (keyEvent.getKeyChar() == 's') {
            if (this.sound) {
                this.sound = false;
            }
            else {
                this.sound = true;
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.pressed = true;
        try {
            this.urlb = new URL("http://www.arigames.com");
        }
        catch (MalformedURLException ex) {}
        if (mouseEvent.getX() > 193 && mouseEvent.getY() > 275) {
            this.getAppletContext().showDocument(this.urlb, "_blank");
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.pressed = false;
    }
}
