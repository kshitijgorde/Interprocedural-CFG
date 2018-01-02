import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.image.ImageProducer;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class RunicCanvas extends Canvas implements Runnable
{
    Runic parent;
    Image[][] runes;
    Image[] fire;
    Image[] numbers;
    Image OSI;
    Image overlay;
    Image gameOverImage;
    Graphics OSG;
    int fireCount;
    int firePt;
    Image[] backdrop;
    int[][] board;
    int[][] boardColor;
    int mouseX;
    int mouseY;
    int rune;
    int runeColor;
    boolean runeHover;
    boolean[][] activated;
    boolean mouseExited;
    int score;
    int numNeighbors;
    Font ourFont;
    int level;
    boolean animMode;
    float animX;
    float animY;
    int animCt;
    float animXX;
    float animXY;
    int fireCtr;
    int fireCtrMax;
    int maxRunes;
    int maxColors;
    int wildOdds;
    boolean gameRunning;
    int wildCount;
    AudioClip badmove;
    AudioClip goodmove;
    AudioClip throwaway;
    AudioClip leveldone;
    Font labelFont;
    
    public RunicCanvas(final Runic parent) {
        this.runeHover = false;
        this.gameRunning = false;
        this.parent = parent;
        this.ourFont = new Font("Serif", 3, 16);
        this.labelFont = new Font("Serif", 3, 48);
        this.addMouseMotionListener(new SymMouseMotion());
        this.addMouseListener(new SymMouse());
    }
    
    public void prepImages() {
        this.board = new int[11][11];
        this.boardColor = new int[11][11];
        this.activated = new boolean[11][11];
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image image = this.parent.getImage(this.parent.getCodeBase(), "runes.gif");
        final Image image2 = this.parent.getImage(this.parent.getCodeBase(), "fire.gif");
        final Image image3 = this.parent.getImage(this.parent.getCodeBase(), "numbers.gif");
        this.overlay = this.parent.getImage(this.parent.getCodeBase(), "overlay.gif");
        this.gameOverImage = this.parent.getImage(this.parent.getCodeBase(), "gameover.gif");
        final Image image4 = this.parent.getImage(this.parent.getCodeBase(), "backdrop.gif");
        try {
            mediaTracker.addImage(image, 0);
            mediaTracker.addImage(image2, 0);
            mediaTracker.addImage(this.overlay, 0);
            mediaTracker.addImage(image3, 0);
            mediaTracker.addImage(this.gameOverImage, 0);
            mediaTracker.addImage(image4, 0);
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {}
        this.badmove = this.parent.getAudioClip(this.parent.getCodeBase(), "badmove.au");
        this.goodmove = this.parent.getAudioClip(this.parent.getCodeBase(), "goodmove.au");
        this.throwaway = this.parent.getAudioClip(this.parent.getCodeBase(), "throwaway.au");
        this.leveldone = this.parent.getAudioClip(this.parent.getCodeBase(), "leveldone.au");
        this.badmove.play();
        this.badmove.stop();
        this.goodmove.play();
        this.goodmove.stop();
        this.throwaway.play();
        this.throwaway.stop();
        this.leveldone.play();
        this.leveldone.stop();
        this.backdrop = new Image[2];
        final croppedImage croppedImage = new croppedImage(image4);
        this.backdrop[0] = this.createImage(croppedImage.getCrop(0, 0, 34, 34));
        this.backdrop[1] = this.createImage(croppedImage.getCrop(34, 0, 34, 34));
        this.runes = new Image[13][8];
        this.fire = new Image[4];
        final croppedImage croppedImage2 = new croppedImage(image);
        final croppedImage croppedImage3 = new croppedImage(image2);
        for (int i = 0; i < 13; ++i) {
            for (int j = 0; j < 7; ++j) {
                this.runes[i][j] = this.createImage(croppedImage2.getCrop(i * 32, j * 32, 32, 32));
            }
        }
        for (int k = 0; k < 4; ++k) {
            this.fire[k] = this.createImage(croppedImage3.getCrop(k * 60, 0, 60, 160));
        }
        final croppedImage croppedImage4 = new croppedImage(image3);
        this.numbers = new Image[10];
        for (int l = 0; l < 10; ++l) {
            this.numbers[l] = this.createImage(croppedImage4.getCrop(l * 10, 0, 10, 20));
        }
        this.OSI = this.createImage(474, 374);
        this.OSG = this.OSI.getGraphics();
        this.newGame();
    }
    
    public void newGame() {
        this.newBoard();
        this.score = 0;
        this.level = 1;
        this.firePt = 0;
        this.fireCtr = 0;
        this.fireCtrMax = 0;
        this.drawNextRune();
        this.setDifficulty();
        this.gameRunning = true;
    }
    
    public void run() {
        this.repaint();
        final long currentTimeMillis = System.currentTimeMillis();
        this.prepImages();
        this.drawNextRune();
        try {
            long n = 5000L - (System.currentTimeMillis() - currentTimeMillis);
            if (n < 50L) {
                n = 50L;
            }
            Thread.sleep(n);
        }
        catch (Exception ex) {}
        this.repaint();
        int n2 = 0;
        while (true) {
            this.drawImage();
            this.repaint();
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex2) {}
            if (this.gameRunning && ++n2 == 10) {
                n2 = 0;
                ++this.firePt;
            }
        }
    }
    
    public void newBoard() {
        for (int i = 0; i < 11; ++i) {
            for (int j = 0; j < 11; ++j) {
                this.board[i][j] = -1;
                this.activated[i][j] = false;
            }
        }
        this.board[5][5] = 0;
        this.boardColor[5][5] = 0;
        this.activated[5][5] = true;
        this.leveldone.play();
    }
    
    public void drawImage() {
        this.OSG.setColor(Color.black);
        this.OSG.fillRect(0, 0, 474, 374);
        if (this.fireCtrMax > this.fireCtr) {
            this.firePt += 2;
            this.fireCtr += 2;
            if (this.fireCtr >= this.fireCtrMax) {
                this.fireCtr = 0;
                this.fireCtrMax = 0;
            }
        }
        if (this.fireCtrMax < this.fireCtr) {
            this.firePt -= 2;
            this.fireCtr -= 2;
            if (this.fireCtr <= this.fireCtrMax) {
                this.fireCtr = 0;
                this.fireCtrMax = 0;
            }
        }
        ++this.fireCount;
        for (int i = 0; i < 11; ++i) {
            for (int j = 0; j < 11; ++j) {
                if (this.activated[i][j]) {
                    this.OSG.drawImage(this.backdrop[1], 100 + i * 34, j * 34, null);
                }
                else {
                    this.OSG.drawImage(this.backdrop[0], 100 + i * 34, j * 34, null);
                }
                if (this.board[i][j] != -1) {
                    this.OSG.drawImage(this.runes[this.board[i][j]][this.boardColor[i][j]], 101 + i * 34, 1 + j * 34, null);
                }
            }
        }
        if (this.fireCount == 4) {
            this.fireCount = 0;
        }
        if (this.firePt < 0) {
            this.firePt = 0;
        }
        if (this.firePt > 160) {
            this.firePt = 160;
            this.gameOver();
        }
        this.OSG.drawImage(this.fire[this.fireCount], 20, 360 - this.firePt, null);
        this.OSG.drawImage(this.overlay, 0, 0, null);
        if (this.gameRunning) {
            if (this.animMode) {
                this.OSG.drawImage(this.runes[this.rune][this.runeColor], (int)(this.animXX - 16.0f), (int)(this.animXY - 16.0f), null);
                this.doAnim();
            }
            else if (this.runeHover && !this.mouseExited) {
                this.OSG.drawImage(this.runes[this.rune][this.runeColor], this.mouseX - 16, this.mouseY - 16, null);
            }
        }
        else {
            this.OSG.drawImage(this.gameOverImage, 191, 133, null);
        }
        this.plotNumber(this.score, 53, 80);
        this.plotNumber(this.level, 53, 120);
    }
    
    public void doAnim() {
        this.animXX += this.animX;
        this.animXY += this.animY;
        ++this.animCt;
        if (this.animCt == 30) {
            this.animMode = false;
            this.animDone();
        }
        if (this.animCt == 20) {
            this.animX = 0.0f;
            this.animY = 10.0f;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.OSI != null) {
            graphics.drawImage(this.OSI, 0, 0, null);
            return;
        }
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 474, 374);
        graphics.setColor(Color.blue);
        graphics.setFont(this.labelFont);
        graphics.drawString("R U N I C", 120, 150);
        graphics.setFont(this.ourFont);
        graphics.drawString("Now Loading...", 160, 200);
        graphics.drawString("(C)2002 4WebGames.com", 130, 300);
        graphics.drawString("All Rights Reserved", 150, 320);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void gameOver() {
        if (this.gameRunning) {
            try {
                System.out.println("Sending score....");
                final URLConnection openConnection = new URL(String.valueOf(this.parent.getCodeBase()) + "/scores.php?score=" + this.score + "&level=" + this.level).openConnection();
                openConnection.setDoInput(true);
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
                String line;
                do {
                    line = bufferedReader.readLine();
                    System.out.println(line);
                } while (!line.equals(null));
                System.out.println("Done w/ score.");
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
            this.gameRunning = false;
        }
    }
    
    void RunicCanvas_MouseMoved(final MouseEvent mouseEvent) {
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
    }
    
    public void drawNextRune() {
        if (this.wildCount > this.wildOdds) {
            this.rune = 0;
            this.wildCount = 0;
            this.wildOdds = (int)(Math.random() * 10.0) + 5;
        }
        else {
            ++this.wildCount;
            this.rune = (int)(Math.random() * this.maxRunes) + 1;
            this.runeColor = (int)(Math.random() * this.maxColors);
        }
        this.runeHover = true;
    }
    
    void RunicCanvas_MouseExited(final MouseEvent mouseEvent) {
        this.mouseExited = true;
    }
    
    void RunicCanvas_MouseEntered(final MouseEvent mouseEvent) {
        this.mouseExited = false;
    }
    
    void RunicCanvas_MousePressed(final MouseEvent mouseEvent) {
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
        if (this.mouseX <= 99) {
            if (this.mouseX > 6 && this.mouseX < 85 && this.mouseY > 172 && this.mouseY < 183) {
                this.newGame();
            }
            if (this.mouseX > 25 && this.mouseX < 75 && this.mouseY > 200 && this.mouseY < 365) {
                this.discardRune();
            }
            return;
        }
        if (this.animMode || !this.gameRunning) {
            return;
        }
        if ((mouseEvent.getModifiers() & 0x4) != 0x0) {
            this.discardRune();
            return;
        }
        this.placeRune();
    }
    
    public void animDone() {
        this.fireCtrMax += 30;
        this.fireCtr = 0;
        this.drawNextRune();
    }
    
    public void discardRune() {
        this.animX = (50.0f - this.mouseX) / 20.0f;
        this.animY = (220.0f - this.mouseY) / 20.0f;
        this.animXX = this.mouseX;
        this.animXY = this.mouseY;
        this.animCt = 0;
        this.animMode = true;
        this.throwaway.play();
    }
    
    public boolean neighborOK(final int n, final int n2) {
        return n == -1 || (this.rune == 0 || n == 0) || (n == this.rune || n2 == this.runeColor);
    }
    
    public boolean hasNeighbor(final int n, final int n2) {
        int n3 = 0;
        if (n > 0 && this.board[n - 1][n2] != -1) {
            ++n3;
        }
        if (n < 10 && this.board[n + 1][n2] != -1) {
            ++n3;
        }
        if (n2 > 0 && this.board[n][n2 - 1] != -1) {
            ++n3;
        }
        if (n2 < 10 && this.board[n][n2 + 1] != -1) {
            ++n3;
        }
        return n3 != 0;
    }
    
    public void neighborTally(final int n, final int n2) {
        int numNeighbors = 0;
        if (n > 0) {
            if ((this.rune == 0 && this.board[n - 1][n2] != -1) || this.board[n - 1][n2] == 0) {
                numNeighbors += 2;
            }
            else {
                if (this.rune == this.board[n - 1][n2]) {
                    ++numNeighbors;
                }
                if (this.board[n - 1][n2] != -1 && this.runeColor == this.boardColor[n - 1][n2]) {
                    ++numNeighbors;
                }
            }
        }
        if (n < 10) {
            if ((this.rune == 0 && this.board[n + 1][n2] != -1) || this.board[n + 1][n2] == 0) {
                numNeighbors += 2;
            }
            else {
                if (this.rune == this.board[n + 1][n2]) {
                    ++numNeighbors;
                }
                if (this.board[n + 1][n2] != -1 && this.runeColor == this.boardColor[n + 1][n2]) {
                    ++numNeighbors;
                }
            }
        }
        if (n2 > 0) {
            if ((this.rune == 0 && this.board[n][n2 - 1] != -1) || this.board[n][n2 - 1] == 0) {
                numNeighbors += 2;
            }
            else {
                if (this.rune == this.board[n][n2 - 1]) {
                    ++numNeighbors;
                }
                if (this.board[n][n2 - 1] != -1 && this.runeColor == this.boardColor[n][n2 - 1]) {
                    ++numNeighbors;
                }
            }
        }
        if (n2 < 10) {
            if ((this.rune == 0 && this.board[n][n2 + 1] != -1) || this.board[n][n2 + 1] == 0) {
                numNeighbors += 2;
            }
            else {
                if (this.rune == this.board[n][n2 + 1]) {
                    ++numNeighbors;
                }
                if (this.board[n][n2 + 1] != -1 && this.runeColor == this.boardColor[n][n2 + 1]) {
                    ++numNeighbors;
                }
            }
        }
        this.numNeighbors = numNeighbors;
    }
    
    public boolean canPlace(final int n, final int n2) {
        return this.board[n][n2] == -1 && this.hasNeighbor(n, n2) && (this.rune == 11 || ((n <= 0 || this.neighborOK(this.board[n - 1][n2], this.boardColor[n - 1][n2])) && (n >= 10 || this.neighborOK(this.board[n + 1][n2], this.boardColor[n + 1][n2])) && (n2 <= 0 || this.neighborOK(this.board[n][n2 - 1], this.boardColor[n][n2 - 1])) && (n2 >= 10 || this.neighborOK(this.board[n][n2 + 1], this.boardColor[n][n2 + 1]))));
    }
    
    public void checkRemoves(final int n, final int n2) {
        int n3 = 1;
        for (int i = 0; i < 11; ++i) {
            if (this.board[n][i] == -1) {
                n3 = 0;
                i = 11;
            }
        }
        if (n3 == 1) {
            for (int j = 0; j < 11; ++j) {
                this.board[n][j] = -1;
            }
            this.score += 100 * this.level;
        }
        int n4 = 1;
        for (int k = 0; k < 11; ++k) {
            if (this.board[k][n2] == -1 && k != n) {
                n4 = 0;
                k = 11;
            }
        }
        if (n4 == 1) {
            for (int l = 0; l < 11; ++l) {
                this.board[l][n2] = -1;
            }
            this.score += 100 * this.level;
        }
    }
    
    public void placeRune() {
        final int n = (this.mouseX - 100) / 34;
        final int n2 = this.mouseY / 34;
        if (n >= 0 && n < 11 && n2 >= 0 && n2 < 11) {
            if (this.canPlace(n, n2)) {
                this.goodmove.play();
                this.neighborTally(n, n2);
                this.board[n][n2] = this.rune;
                this.boardColor[n][n2] = this.runeColor;
                if (!this.activated[n][n2]) {
                    this.score += this.numNeighbors * this.numNeighbors * 5;
                    this.activated[n][n2] = true;
                }
                else {
                    this.score += this.numNeighbors * this.numNeighbors;
                }
                this.drawNextRune();
                this.fireCtrMax -= 30;
                this.checkRemoves(n, n2);
                if (this.firePt < 0) {
                    this.firePt = 0;
                }
                int n3 = 1;
                for (int i = 0; i < 11; ++i) {
                    for (int j = 0; j < 11; ++j) {
                        if (!this.activated[i][j]) {
                            n3 = 0;
                        }
                    }
                }
                if (n3 == 1) {
                    this.levelUp();
                }
            }
            else {
                this.badmove.play();
            }
        }
    }
    
    public void levelUp() {
        ++this.level;
        this.score += 1000 * this.level;
        this.setDifficulty();
        this.newBoard();
    }
    
    public void setDifficulty() {
        this.wildOdds = (int)(Math.random() * 10.0) + 5;
        this.maxRunes = 8 + this.level;
        if (this.maxRunes > 12) {
            this.maxRunes = 12;
        }
        this.maxColors = 5;
        if (this.level > 4) {
            this.maxColors = 6;
        }
        if (this.level > 5) {
            this.maxColors = 7;
        }
    }
    
    public void plotNumber(final int n, int n2, final int n3) {
        int n4 = 1;
        if (n >= 10 || n < 0) {
            n4 = 2;
        }
        if (n >= 100 || n < -9) {
            n4 = 3;
        }
        if (n >= 1000 || n < -999) {
            n4 = 5;
        }
        if (n >= 10000) {
            n4 = 6;
        }
        if (n >= 100000) {
            n4 = 7;
        }
        n2 -= n4 * 5;
        final String value = String.valueOf(n);
        for (int i = 0; i < value.length(); ++i) {
            int n5 = value.charAt(i) - '1';
            if (n5 == -1) {
                n5 = 9;
            }
            if (value.charAt(i) == ',') {
                n5 = 10;
            }
            if (value.charAt(i) == '-') {
                n5 = 11;
            }
            this.OSG.drawImage(this.numbers[n5], n2 + i * 10, n3, null);
        }
    }
    
    class SymMouseMotion extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            if (mouseEvent.getSource() == RunicCanvas.this) {
                RunicCanvas.this.RunicCanvas_MouseMoved(mouseEvent);
            }
        }
    }
    
    class SymMouse extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            if (mouseEvent.getSource() == RunicCanvas.this) {
                RunicCanvas.this.RunicCanvas_MousePressed(mouseEvent);
            }
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            if (mouseEvent.getSource() == RunicCanvas.this) {
                RunicCanvas.this.RunicCanvas_MouseEntered(mouseEvent);
            }
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            if (mouseEvent.getSource() == RunicCanvas.this) {
                RunicCanvas.this.RunicCanvas_MouseExited(mouseEvent);
            }
        }
    }
}
