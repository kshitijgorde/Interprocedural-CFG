import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageProducer;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.event.MouseListener;
import java.applet.AudioClip;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class GemCanvas extends Canvas implements Runnable
{
    Graphics OSG;
    Image OSI;
    int tmpScore;
    Image[] gems;
    GemGame parent;
    Font msgFont;
    int[][] board;
    boolean mouseSelected;
    boolean outOfTime;
    int shiftMode;
    int mouseX;
    int mouseY;
    int lastAX;
    int lastAY;
    int lastBX;
    int lastBY;
    int maxTime;
    long timerStart;
    int level;
    int score;
    boolean showHint;
    boolean gameOver;
    Color hintColor;
    int hxa;
    int hya;
    int hxb;
    int hyb;
    Vector shiftingGems;
    AudioClip OKsound;
    AudioClip badsound;
    Font hugeFont;
    boolean[][] goners;
    int shrinked;
    boolean dieoff;
    
    public GemCanvas(final GemGame parent) {
        this.dieoff = false;
        this.parent = parent;
        this.board = new int[10][10];
        this.hintColor = new Color(255, 128, 128);
        this.msgFont = new Font("Serif", 3, 32);
        this.hugeFont = new Font("Serif", 3, 72);
        new Thread(this).start();
        this.addMouseListener(new SymMouse());
    }
    
    public void paint(final Graphics graphics) {
        if (this.OSI != null) {
            graphics.drawImage(this.OSI, 0, 0, null);
            return;
        }
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 300, 300);
        graphics.setFont(this.msgFont);
        graphics.setColor(Color.blue);
        graphics.drawString("Loading Graphics...", 30, 200);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void drawBoard() {
        this.OSG.setColor(Color.black);
        this.OSG.fillRect(0, 0, 300, 300);
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                if (this.board[i][j] != -1) {
                    if (this.goners != null && this.goners[i][j]) {
                        this.OSG.drawImage(this.gems[this.board[i][j]], i * 30 + this.shrinked, j * 30 + this.shrinked, 30 - this.shrinked * 2, 30 - this.shrinked * 2, null);
                    }
                    else {
                        this.OSG.drawImage(this.gems[this.board[i][j]], i * 30, j * 30, null);
                    }
                }
            }
        }
        if (this.goners != null) {
            this.shrinked += 2;
            if (this.shrinked > 14) {
                this.wipeBoard();
            }
        }
        if (this.mouseSelected) {
            final int n = this.mouseX * 30;
            final int n2 = this.mouseY * 30;
            this.OSG.setColor(Color.white);
            this.OSG.drawRect(n, n2, 30, 30);
            this.OSG.setColor(Color.lightGray);
            this.OSG.drawRect(n + 1, n2 + 1, 28, 28);
            this.OSG.setColor(Color.gray);
            this.OSG.drawRect(n + 2, n2 + 2, 26, 26);
            this.OSG.setColor(Color.darkGray);
            this.OSG.drawRect(n + 3, n2 + 3, 24, 24);
        }
        if (this.shiftingGems != null) {
            int n3 = 1;
            for (int k = 0; k < this.shiftingGems.size(); ++k) {
                final shiftingGem shiftingGem = this.shiftingGems.elementAt(k);
                shiftingGem.shiftPiece();
                this.OSG.drawImage(this.gems[shiftingGem.gemNumber], shiftingGem.showX, shiftingGem.showY, null);
                if (!shiftingGem.inPlace) {
                    n3 = 0;
                }
            }
            if (n3 == 1) {
                for (int l = 0; l < this.shiftingGems.size(); ++l) {
                    final shiftingGem shiftingGem2 = this.shiftingGems.elementAt(l);
                    this.board[shiftingGem2.destinationX][shiftingGem2.destinationY] = shiftingGem2.gemNumber;
                }
                this.shiftingGems = null;
                if (this.shiftMode == 3 && !this.checkBoard()) {
                    this.shiftMode = 0;
                    this.score += this.tmpScore * this.tmpScore;
                    this.tmpScore = 0;
                    this.parent.scoreLabel.setText(String.valueOf(this.score));
                    if (this.isGameOver()) {
                        this.gameOver = true;
                        this.sendScore();
                    }
                }
                if (this.shiftMode == 1 && !this.checkBoard()) {
                    this.badsound.play();
                    this.shiftingGems = new Vector();
                    final shiftingGem shiftingGem3 = new shiftingGem(this.lastAX, this.lastAY, this.lastBX, this.lastBY, this.board[this.lastAX][this.lastAY]);
                    final shiftingGem shiftingGem4 = new shiftingGem(this.lastBX, this.lastBY, this.lastAX, this.lastAY, this.board[this.lastBX][this.lastBY]);
                    this.shiftingGems.addElement(shiftingGem3);
                    this.shiftingGems.addElement(shiftingGem4);
                    this.shiftMode = 2;
                    this.board[this.lastAX][this.lastAY] = -1;
                    this.board[this.lastBX][this.lastBY] = -1;
                }
            }
        }
        if (this.showHint || this.gameOver) {
            final float[] array = new float[3];
            Color.RGBtoHSB(this.hintColor.getRed(), this.hintColor.getGreen(), this.hintColor.getBlue(), array);
            final float[] array2 = array;
            final int n4 = 0;
            array2[n4] += 0.03f;
            if (array[0] > 1.0f) {
                final float[] array3 = array;
                final int n5 = 0;
                --array3[n5];
            }
            this.hintColor = Color.getHSBColor(array[0], array[1], array[2]);
            this.OSG.setColor(this.hintColor);
        }
        if (this.showHint && !this.gameOver) {
            this.OSG.setColor(this.hintColor);
            this.OSG.drawRect(this.hxa, this.hya, this.hxb, this.hyb);
            this.OSG.drawRect(this.hxa + 1, this.hya + 1, this.hxb - 2, this.hyb - 2);
            this.OSG.drawRect(this.hxa + 2, this.hya + 2, this.hxb - 4, this.hyb - 4);
        }
        if (this.gameOver) {
            this.OSG.setColor(this.hintColor.brighter());
            this.OSG.setFont(this.hugeFont);
            if (this.outOfTime) {
                this.OSG.drawString("Out", 110, 50);
                this.OSG.drawString("Of", 125, 125);
                this.OSG.drawString("Time!", 80, 200);
                return;
            }
            this.OSG.drawString("No", 120, 50);
            this.OSG.drawString("Moves", 70, 125);
            this.OSG.drawString("Left!", 80, 200);
        }
    }
    
    public boolean checkBoard() {
        this.goners = new boolean[10][10];
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (this.board[i][j] == this.board[i][j + 1] && this.board[i][j] == this.board[i][j + 2]) {
                    this.goners[i][j] = true;
                    this.goners[i][j + 1] = true;
                    this.goners[i][j + 2] = true;
                }
            }
        }
        for (int k = 0; k < 8; ++k) {
            for (int l = 0; l < 10; ++l) {
                if (this.board[k][l] == this.board[k + 1][l] && this.board[k][l] == this.board[k + 2][l]) {
                    this.goners[k][l] = true;
                    this.goners[k + 1][l] = true;
                    this.goners[k + 2][l] = true;
                }
            }
        }
        int n = 0;
        for (int n2 = 0; n2 < 10; ++n2) {
            for (int n3 = 0; n3 < 10; ++n3) {
                if (this.goners[n2][n3]) {
                    ++n;
                }
            }
        }
        if (n == 0) {
            this.goners = null;
            this.shrinked = 1;
            return false;
        }
        this.tmpScore += n;
        this.shrinked = 1;
        this.OKsound.play();
        return true;
    }
    
    public void wipeBoard() {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                if (this.goners[i][j]) {
                    this.board[i][j] = -1;
                }
            }
        }
        this.shrinked = 0;
        this.shiftingGems = new Vector();
        for (int k = 0; k < 10; ++k) {
            int n = 11;
            for (int l = 9; l >= 0; --l) {
                if (this.board[k][l] == -1) {
                    int n2 = l - 1;
                    if (n < n2) {
                        n2 = n;
                    }
                    int n3 = -1;
                    do {
                        if (n2 < 0) {
                            n3 = (int)(Math.random() * 7.0);
                        }
                        else if (this.board[k][n2] != -1) {
                            n3 = this.board[k][n2];
                        }
                        else {
                            --n2;
                        }
                    } while (n3 == -1);
                    n = n2 - 1;
                    this.shiftingGems.addElement(new shiftingGem(k, n2, k, l, n3, 10));
                    if (n2 > -1) {
                        this.board[k][n2] = -1;
                    }
                }
            }
        }
        this.timerStart += (6 - this.level) * 1000;
        this.shiftMode = 3;
        this.goners = null;
        this.shrinked = 0;
    }
    
    public void newGame(final int n) {
        this.level = n + 1;
        this.gameOver = false;
        this.outOfTime = false;
        if (this.level > 1) {
            this.parent.label3.setVisible(true);
            this.parent.timeLabel.setVisible(true);
            this.parent.button2.setVisible(false);
            this.parent.button2.setEnabled(false);
            switch (this.level) {
                case 2: {
                    this.maxTime = 180;
                    break;
                }
                case 3: {
                    this.maxTime = 90;
                    break;
                }
                case 4: {
                    this.maxTime = 60;
                    break;
                }
                case 5: {
                    this.maxTime = 30;
                    break;
                }
            }
        }
        else {
            this.parent.label3.setVisible(false);
            this.parent.timeLabel.setVisible(false);
            this.parent.button2.setVisible(true);
            this.parent.button2.setEnabled(true);
        }
        this.score = 0;
        this.parent.scoreLabel.setText("0");
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                this.board[i][j] = (int)(Math.random() * 7.0);
            }
        }
        boolean b;
        do {
            b = true;
            for (int k = 0; k < 10; ++k) {
                for (int l = 0; l < 8; ++l) {
                    if (this.board[k][l] == this.board[k][l + 1] && this.board[k][l] == this.board[k][l + 2]) {
                        this.board[k][l + 1] = (int)(Math.random() * 7.0);
                        b = false;
                    }
                }
            }
            for (int n2 = 0; n2 < 8; ++n2) {
                for (int n3 = 0; n3 < 10; ++n3) {
                    if (this.board[n2][n3] == this.board[n2 + 1][n3] && this.board[n2][n3] == this.board[n2 + 2][n3]) {
                        this.board[n2 + 1][n3] = (int)(Math.random() * 7.0);
                        b = false;
                    }
                }
            }
        } while (!b);
        if (this.isGameOver()) {
            this.newGame(n);
        }
        this.timerStart = System.currentTimeMillis();
    }
    
    public void run() {
        this.OKsound = this.parent.getAudioClip(this.parent.getCodeBase(), "oksound.au");
        this.badsound = this.parent.getAudioClip(this.parent.getCodeBase(), "badsound.au");
        this.OKsound.play();
        this.OKsound.stop();
        this.badsound.play();
        this.badsound.stop();
        final MediaTracker mediaTracker = new MediaTracker(this);
        try {
            final Image image = this.parent.getImage(this.parent.getCodeBase(), "gems.gif");
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForAll();
            final croppedImage croppedImage = new croppedImage(image);
            this.gems = new Image[7];
            for (int i = 0; i < 7; ++i) {
                this.gems[i] = this.createImage(croppedImage.getCrop(i * 30, 0, 30, 30));
            }
        }
        catch (Exception ex) {}
        this.OSI = this.createImage(300, 300);
        this.OSG = this.OSI.getGraphics();
        this.newGame(0);
        do {
            this.drawBoard();
            this.repaint();
            try {
                Thread.sleep(20L);
                if (this.level <= 1 || this.gameOver) {
                    continue;
                }
                final int n = this.maxTime - (int)(System.currentTimeMillis() - this.timerStart) / 1000;
                if (n < 0) {
                    this.gameOver = true;
                    this.outOfTime = true;
                    this.sendScore();
                }
                else {
                    this.parent.timeLabel.setText(String.valueOf(n));
                }
            }
            catch (Exception ex2) {}
        } while (!this.dieoff);
    }
    
    public void sendScore() {
    }
    
    void GemCanvas_MouseClicked(final MouseEvent mouseEvent) {
        if (this.shiftingGems != null || this.gameOver) {
            return;
        }
        this.showHint = false;
        if (!this.mouseSelected) {
            this.mouseSelected = true;
            this.mouseX = mouseEvent.getX() / 30;
            this.mouseY = mouseEvent.getY() / 30;
            return;
        }
        final int n = mouseEvent.getX() / 30;
        final int n2 = mouseEvent.getY() / 30;
        if ((n == this.mouseX - 1 || n == this.mouseX + 1) && n2 == this.mouseY) {
            this.doSwap(n, n2);
            return;
        }
        if ((n2 == this.mouseY - 1 || n2 == this.mouseY + 1) && n == this.mouseX) {
            this.doSwap(n, n2);
            return;
        }
        this.mouseSelected = false;
    }
    
    public boolean isGameOver() {
        final Vector vector = new Vector<shiftingGem>();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 10; ++j) {
                if (i < 7 && this.board[i][j] == this.board[i + 1][j] && this.board[i][j] == this.board[i + 3][j]) {
                    vector.addElement(new shiftingGem(i + 3, j, i + 2, j, 0));
                }
                if (i > 0 && this.board[i - 1][j] == this.board[i + 1][j] && this.board[i - 1][j] == this.board[i + 2][j]) {
                    vector.addElement(new shiftingGem(i - 1, j, i, j, 0));
                }
                if (j > 0) {
                    if (this.board[i][j - 1] == this.board[i + 1][j] && this.board[i][j - 1] == this.board[i + 2][j]) {
                        vector.addElement(new shiftingGem(i, j - 1, i, j, 0));
                    }
                    if (this.board[i][j] == this.board[i + 1][j - 1] && this.board[i][j] == this.board[i + 2][j]) {
                        vector.addElement(new shiftingGem(i + 1, j - 1, i + 1, j, 0));
                    }
                    if (this.board[i][j] == this.board[i + 1][j] && this.board[i][j] == this.board[i + 2][j - 1]) {
                        vector.addElement(new shiftingGem(i + 2, j - 1, i + 2, j, 0));
                    }
                }
                if (j < 9) {
                    if (this.board[i][j + 1] == this.board[i + 1][j] && this.board[i][j + 1] == this.board[i + 2][j]) {
                        vector.addElement(new shiftingGem(i, j + 1, i, j, 0));
                    }
                    if (this.board[i][j] == this.board[i + 1][j + 1] && this.board[i][j] == this.board[i + 2][j]) {
                        vector.addElement(new shiftingGem(i + 1, j + 1, i + 1, j, 0));
                    }
                    if (this.board[i][j] == this.board[i + 1][j] && this.board[i][j] == this.board[i + 2][j + 1]) {
                        vector.addElement(new shiftingGem(i + 2, j + 1, i + 2, j, 0));
                    }
                }
            }
        }
        for (int k = 0; k < 8; ++k) {
            for (int l = 0; l < 10; ++l) {
                if (k < 7 && this.board[l][k] == this.board[l][k + 1] && this.board[l][k] == this.board[l][k + 3]) {
                    vector.addElement(new shiftingGem(l, k + 2, l, k + 3, 0));
                }
                if (k > 0 && this.board[l][k - 1] == this.board[l][k + 1] && this.board[l][k - 1] == this.board[l][k + 2]) {
                    vector.addElement(new shiftingGem(l, k - 1, l, k, 0));
                }
                if (l > 0) {
                    if (this.board[l - 1][k] == this.board[l][k + 1] && this.board[l - 1][k] == this.board[l][k + 2]) {
                        vector.addElement(new shiftingGem(l - 1, k, l, k, 0));
                    }
                    if (this.board[l][k] == this.board[l - 1][k + 1] && this.board[l][k] == this.board[l][k + 2]) {
                        vector.addElement(new shiftingGem(l - 1, k + 1, l, k + 1, 0));
                    }
                    if (this.board[l][k] == this.board[l][k + 1] && this.board[l][k] == this.board[l - 1][k + 2]) {
                        vector.addElement(new shiftingGem(l - 1, k + 2, l, k + 2, 0));
                    }
                }
                if (l < 9) {
                    if (this.board[l + 1][k] == this.board[l][k + 1] && this.board[l + 1][k] == this.board[l][k + 2]) {
                        vector.addElement(new shiftingGem(l + 1, k, l, k, 0));
                    }
                    if (this.board[l][k] == this.board[l + 1][k + 1] && this.board[l][k] == this.board[l][k + 2]) {
                        vector.addElement(new shiftingGem(l + 1, k + 1, l, k + 1, 0));
                    }
                    if (this.board[l][k] == this.board[l][k + 1] && this.board[l][k] == this.board[l + 1][k + 2]) {
                        vector.addElement(new shiftingGem(l + 1, k + 2, l, k + 2, 0));
                    }
                }
            }
        }
        final int size = vector.size();
        if (size == 0) {
            return true;
        }
        final shiftingGem shiftingGem = vector.elementAt((int)(Math.random() * size));
        int n = shiftingGem.showX / 30;
        int n2 = shiftingGem.showY / 30;
        int destinationX = shiftingGem.destinationX;
        int destinationY = shiftingGem.destinationY;
        if (destinationX < n) {
            final int n3 = destinationX;
            destinationX = n;
            n = n3;
        }
        if (destinationY < n2) {
            final int n4 = destinationY;
            destinationY = n2;
            n2 = n4;
        }
        this.hxa = n * 30;
        this.hya = n2 * 30;
        this.hxb = 30;
        this.hyb = 30;
        if (n != destinationX) {
            this.hxb = 60;
        }
        if (n2 != destinationY) {
            this.hyb = 60;
        }
        return false;
    }
    
    public void doSwap(final int lastAX, final int lastAY) {
        this.shiftMode = 1;
        this.mouseSelected = false;
        this.shiftingGems = new Vector();
        final shiftingGem shiftingGem = new shiftingGem(this.mouseX, this.mouseY, lastAX, lastAY, this.board[this.mouseX][this.mouseY]);
        final shiftingGem shiftingGem2 = new shiftingGem(lastAX, lastAY, this.mouseX, this.mouseY, this.board[lastAX][lastAY]);
        this.shiftingGems.addElement(shiftingGem);
        this.shiftingGems.addElement(shiftingGem2);
        this.board[lastAX][lastAY] = -1;
        this.board[this.mouseX][this.mouseY] = -1;
        this.lastAX = lastAX;
        this.lastAY = lastAY;
        this.lastBX = this.mouseX;
        this.lastBY = this.mouseY;
    }
    
    class SymMouse extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            if (mouseEvent.getSource() == GemCanvas.this) {
                GemCanvas.this.GemCanvas_MouseClicked(mouseEvent);
            }
        }
    }
}
