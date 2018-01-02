import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Component;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Scrabble3D extends Applet
{
    public int ext;
    public int playersNr;
    Font f1;
    FontMetrics fm1;
    Font f2;
    FontMetrics fm2;
    Font f3;
    FontMetrics fm3;
    Graphics g;
    Image offscreenImage;
    int[][][] cell;
    Piece[][][] fieldPiece;
    int x1;
    int x2;
    int x3;
    int nx1;
    int nx2;
    int nx3;
    int dy1;
    int dy2;
    int cursor1;
    int cursor2;
    int sliceAxis;
    int slice1;
    int slice2;
    int slice3;
    int curSlice;
    int gameState;
    Piece curPiece;
    Piece[][] panelPiece;
    int curPlayer;
    int[] score;
    int victoryScore;
    int[] letterFreq;
    int[] letBorder;
    Image[] pieceImage;
    Image[][] letterImage;
    Image allPieces;
    Image allLetters;
    MediaTracker tracker;
    ImageFilter filter;
    ImageProducer piecesProducer;
    ImageProducer lettersProducer;
    
    public Scrabble3D() {
        this.ext = 9;
        this.playersNr = 4;
        this.f1 = new Font("Arial", 1, 24);
        this.fm1 = this.getFontMetrics(this.f1);
        this.f2 = new Font("Arial", 1, 12);
        this.fm2 = this.getFontMetrics(this.f2);
        this.f3 = new Font("Verdana", 0, 8);
        this.fm3 = this.getFontMetrics(this.f3);
        this.cell = new int[this.ext][this.ext][this.ext];
        this.fieldPiece = new Piece[this.ext][this.ext][this.ext];
        this.x1 = -1;
        this.x2 = 4;
        this.x3 = -1;
        this.nx1 = 4;
        this.nx2 = 4;
        this.nx3 = 4;
        this.dy1 = -30;
        this.dy2 = -30;
        this.sliceAxis = 2;
        this.slice1 = 0;
        this.slice2 = 8;
        this.slice3 = 0;
        this.curSlice = 4;
        this.gameState = 0;
        this.panelPiece = new Piece[7][this.playersNr];
        this.curPlayer = 0;
        this.score = new int[this.playersNr];
        this.victoryScore = 150;
        this.letterFreq = new int[] { 20, 8, 6, 8, 30, 8, 8, 6, 20, 3, 2, 10, 8, 10, 20, 8, 1, 8, 10, 8, 10, 6, 6, 2, 6, 2 };
        this.letBorder = new int[26];
        this.pieceImage = new Image[this.ext];
        this.letterImage = new Image[90][2];
    }
    
    public void init() {
        this.tracker = new MediaTracker(this);
        this.allPieces = this.getImage(this.getCodeBase(), "Pieces.gif");
        this.tracker.addImage(this.allPieces, 0);
        try {
            this.tracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        this.piecesProducer = this.allPieces.getSource();
        for (int i = 0; i < 9; ++i) {
            this.filter = new CropImageFilter(i * 50, 0, 50, 52);
            this.pieceImage[i] = this.createImage(new FilteredImageSource(this.piecesProducer, this.filter));
            this.tracker.addImage(this.pieceImage[i], 1);
        }
        this.allLetters = this.getImage(this.getCodeBase(), "Letters.gif");
        this.tracker.addImage(this.allLetters, 0);
        try {
            this.tracker.waitForID(0);
        }
        catch (InterruptedException ex2) {}
        this.lettersProducer = this.allLetters.getSource();
        for (int j = 0; j < 2; ++j) {
            for (int k = 0; k < 4; ++k) {
                for (int l = 0; l < 7; ++l) {
                    this.filter = new CropImageFilter(l * 50, j * 208 + k * 52, 50, 52);
                    this.letterImage[k * 7 + l][j] = this.createImage(new FilteredImageSource(this.lettersProducer, this.filter));
                    this.tracker.addImage(this.letterImage[k * 7 + l][j], 1);
                }
            }
        }
        this.allToStartValue();
        this.letBorder[0] = this.letterFreq[0];
        for (int n = 1; n < 26; ++n) {
            this.letBorder[n] = this.letBorder[n - 1] + this.letterFreq[n];
        }
        this.newPieces();
        this.gameState = 10;
        this.repaint();
    }
    
    public void emptyField() {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                for (int k = 0; k < 9; ++k) {
                    this.cell[i][j][k] = -1;
                    this.fieldPiece[i][j][k] = null;
                }
            }
        }
    }
    
    public void allToStartValue() {
        this.emptyField();
        this.cell[4][1][4] = 18;
        this.cell[4][2][4] = 2;
        this.cell[4][3][4] = 17;
        this.cell[4][4][4] = 0;
        this.cell[4][5][4] = 1;
        this.cell[4][6][4] = 1;
        this.cell[4][7][4] = 11;
        this.cell[4][8][4] = 4;
        this.cell[4][4][1] = 18;
        this.cell[4][4][2] = 2;
        this.cell[4][4][3] = 17;
        this.cell[4][4][5] = 1;
        this.cell[4][4][6] = 1;
        this.cell[4][4][7] = 11;
        this.cell[4][4][8] = 4;
        this.cell[0][5][4] = 18;
        this.cell[1][5][4] = 2;
        this.cell[2][5][4] = 17;
        this.cell[3][5][4] = 0;
        this.cell[5][5][4] = 1;
        this.cell[6][5][4] = 11;
        this.cell[7][5][4] = 4;
        this.dy1 = -95;
        this.dy2 = -30;
        this.x1 = -1;
        this.x2 = 8;
        this.x3 = -1;
        for (int i = 0; i < this.playersNr; ++i) {
            this.score[i] = 0;
            for (int j = 0; j < 7; ++j) {
                this.panelPiece[j][i] = null;
            }
        }
        this.sliceAxis = 2;
        this.slice1 = 0;
        this.slice2 = 8;
        this.slice3 = 0;
        this.curSlice = 4;
        this.curPlayer = 0;
    }
    
    public void start() {
    }
    
    public void run() {
    }
    
    public boolean mouseDown(final Event event, final int n, int n2) {
        if (event.metaDown()) {
            final int n3 = n - 150;
            final int n4 = n2 - 150;
            this.dy1 -= n3;
            this.dy2 -= n4;
            if (this.dy1 > 5) {
                this.dy1 = 5;
            }
            if (this.dy1 < -206) {
                this.dy1 = -206;
            }
            if (this.dy2 > 86) {
                this.dy2 = 86;
            }
            if (this.dy2 < -85) {
                this.dy2 = -85;
            }
            this.repaint();
        }
        else {
            switch (this.gameState) {
                case 0: {
                    if (n <= 240) {
                        this.pickPiece();
                        break;
                    }
                    if (n2 < 213) {
                        int n5 = (n2 - 31) / 26;
                        if (n5 < 0) {
                            n5 = 0;
                        }
                        if (n5 > 6) {
                            n5 = 6;
                        }
                        if (this.panelPiece[n5][this.curPlayer] != null) {
                            this.curPiece = this.panelPiece[n5][this.curPlayer];
                            for (int i = n5; i > 0; --i) {
                                this.panelPiece[i][this.curPlayer] = this.panelPiece[i - 1][this.curPlayer];
                                this.panelPiece[i - 1][this.curPlayer] = null;
                            }
                            if (n5 == 0) {
                                this.panelPiece[0][this.curPlayer] = null;
                            }
                            this.gameState = 1;
                            this.repaint();
                            break;
                        }
                        break;
                    }
                    else {
                        if (n2 > 280) {
                            this.endTurn();
                            break;
                        }
                        break;
                    }
                    break;
                }
                case 1: {
                    if (n > 240) {
                        for (int j = 6; j >= 0; --j) {
                            if (this.panelPiece[j][this.curPlayer] == null) {
                                this.panelPiece[j][this.curPlayer] = this.curPiece;
                                this.curPiece = null;
                                this.gameState = 0;
                                this.repaint();
                                break;
                            }
                        }
                        break;
                    }
                    break;
                }
                case 2: {
                    if (n < 240) {
                        this.gameState = 0;
                        this.fieldPiece[this.x1][this.x2][this.x3] = this.curPiece;
                        this.curPiece = null;
                        this.xToDefault();
                        this.repaint();
                        break;
                    }
                    break;
                }
                case 3: {
                    this.allToStartValue();
                    this.gameState = 10;
                    this.repaint();
                    break;
                }
                case 10: {
                    if (n2 < 75 && n2 > 15) {
                        n2 -= 15;
                        n2 /= 15;
                        this.playersNr = n2 + 1;
                        this.newPieces();
                        this.emptyField();
                        this.x2 = 4;
                        this.gameState = 0;
                        this.repaint();
                        break;
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (this.gameState < 3) {
            switch (n) {
                case 27:
                case 113: {
                    this.allToStartValue();
                    this.gameState = 10;
                    this.repaint();
                    break;
                }
                case 10: {
                    this.endTurn();
                    break;
                }
                case 119: {
                    this.sliceAxis = 2;
                    this.curSlice = this.x2;
                    this.repaint();
                    break;
                }
                case 101: {
                    this.sliceAxis = 3;
                    this.curSlice = this.x3;
                    this.repaint();
                    break;
                }
                case 114: {
                    this.sliceAxis = 1;
                    this.curSlice = this.x1;
                    this.repaint();
                    break;
                }
                case 1004: {
                    if (this.gameState == 2) {
                        this.cell[this.x1][this.x2][this.x3] = -1;
                    }
                    switch (this.sliceAxis) {
                        case 1: {
                            ++this.x1;
                            if (this.x1 > 8) {
                                this.x1 = 8;
                            }
                            this.curSlice = this.x1;
                            break;
                        }
                        case 2: {
                            --this.x2;
                            if (this.x2 < 0) {
                                this.x2 = 0;
                            }
                            this.curSlice = this.x2;
                            break;
                        }
                        case 3: {
                            --this.x3;
                            if (this.x3 < 0) {
                                this.x3 = 0;
                            }
                            this.curSlice = this.x3;
                            break;
                        }
                    }
                    if (this.cursor1 < 240 && (this.gameState == 1 || this.gameState == 2)) {
                        this.countCell();
                    }
                    this.repaint();
                    break;
                }
                case 1005: {
                    if (this.gameState == 2) {
                        this.cell[this.x1][this.x2][this.x3] = -1;
                    }
                    switch (this.sliceAxis) {
                        case 1: {
                            --this.x1;
                            if (this.x1 < 0) {
                                this.x1 = 0;
                            }
                            this.curSlice = this.x1;
                            break;
                        }
                        case 2: {
                            ++this.x2;
                            if (this.x2 > 8) {
                                this.x2 = 8;
                            }
                            this.curSlice = this.x2;
                            break;
                        }
                        case 3: {
                            ++this.x3;
                            if (this.x3 > 8) {
                                this.x3 = 8;
                            }
                            this.curSlice = this.x3;
                            break;
                        }
                    }
                    if (this.cursor1 < 240 && (this.gameState == 1 || this.gameState == 2)) {
                        this.countCell();
                    }
                    this.repaint();
                    break;
                }
            }
        }
        else if (this.gameState == 3) {
            this.allToStartValue();
            this.gameState = 10;
            this.repaint();
        }
        else if (this.gameState == 10) {
            switch (n) {
                case 49: {
                    this.playersNr = 1;
                    break;
                }
                case 50: {
                    this.playersNr = 2;
                    break;
                }
                case 51: {
                    this.playersNr = 3;
                    break;
                }
                case 52: {
                    this.playersNr = 4;
                    break;
                }
                default: {
                    this.playersNr = 1;
                    break;
                }
            }
            this.emptyField();
            this.x2 = 4;
            this.gameState = 0;
            this.newPieces();
            this.repaint();
        }
        return false;
    }
    
    public boolean mouseMove(final Event event, final int cursor1, final int cursor2) {
        this.cursor1 = cursor1;
        this.cursor2 = cursor2;
        if (this.gameState == 1 || this.gameState == 2) {
            if (this.cursor1 < 240) {
                if (this.gameState == 2) {
                    this.cell[this.x1][this.x2][this.x3] = -1;
                }
                this.countCell();
            }
            else {
                if (this.inside(this.x1, this.x2, this.x3)) {
                    this.cell[this.x1][this.x2][this.x3] = -1;
                    this.xToDefault();
                }
                this.gameState = 1;
                this.repaint();
            }
        }
        else {
            this.repaint();
        }
        return false;
    }
    
    public void endTurn() {
        int n = 0;
        for (int i = 0; i < 7; ++i) {
            if (this.panelPiece[i][this.curPlayer] == null) {
                ++n;
            }
        }
        switch (n) {
            case 0: {
                final int[] score = this.score;
                final int curPlayer = this.curPlayer;
                --score[curPlayer];
                for (int j = 0; j < 7; ++j) {
                    this.panelPiece[j][this.curPlayer] = null;
                }
                break;
            }
            case 1: {
                final int[] score2 = this.score;
                final int curPlayer2 = this.curPlayer;
                ++score2[curPlayer2];
                break;
            }
            case 2: {
                final int[] score3 = this.score;
                final int curPlayer3 = this.curPlayer;
                score3[curPlayer3] += 3;
                break;
            }
            case 3: {
                final int[] score4 = this.score;
                final int curPlayer4 = this.curPlayer;
                score4[curPlayer4] += 5;
                break;
            }
            case 4: {
                final int[] score5 = this.score;
                final int curPlayer5 = this.curPlayer;
                score5[curPlayer5] += 8;
                break;
            }
            case 5: {
                final int[] score6 = this.score;
                final int curPlayer6 = this.curPlayer;
                score6[curPlayer6] += 12;
                break;
            }
            case 6: {
                final int[] score7 = this.score;
                final int curPlayer7 = this.curPlayer;
                score7[curPlayer7] += 18;
                break;
            }
            case 7: {
                final int[] score8 = this.score;
                final int curPlayer8 = this.curPlayer;
                score8[curPlayer8] += 30;
                break;
            }
        }
        if (this.score[this.curPlayer] >= this.victoryScore) {
            this.gameState = 3;
        }
        else {
            ++this.curPlayer;
            this.curPlayer %= this.playersNr;
            this.newPieces();
            for (int k = 0; k < 9; ++k) {
                for (int l = 0; l < 9; ++l) {
                    for (int n2 = 0; n2 < 9; ++n2) {
                        if (this.fieldPiece[k][l][n2] != null) {
                            this.fieldPiece[k][l][n2].player = -1;
                        }
                    }
                }
            }
        }
    }
    
    public void xToDefault() {
        switch (this.sliceAxis) {
            case 1: {
                final int n = -1;
                this.x3 = n;
                this.x2 = n;
                if (this.x1 < 0) {
                    this.x1 = 4;
                }
                this.curSlice = this.x1;
                break;
            }
            case 2: {
                final int n2 = -1;
                this.x3 = n2;
                this.x1 = n2;
                if (this.x2 < 0) {
                    this.x2 = 4;
                }
                this.curSlice = this.x2;
                break;
            }
            case 3: {
                final int n3 = -1;
                this.x2 = n3;
                this.x1 = n3;
                if (this.x3 < 0) {
                    this.x3 = 4;
                }
                this.curSlice = this.x3;
                break;
            }
        }
    }
    
    public boolean inside(final int n, final int n2, final int n3) {
        return n >= 0 && n2 >= 0 && n3 >= 0 && n < 9 && n2 < 9 && n3 < 9;
    }
    
    public void countCell() {
        this.defineCell();
        if (this.inside(this.nx1, this.nx2, this.nx3)) {
            boolean b = false;
            if (this.x1 != this.nx1 || this.x2 != this.nx2 || this.x3 != this.nx3) {
                b = true;
            }
            if (this.cell[this.nx1][this.nx2][this.nx3] < 0) {
                this.gameState = 2;
                this.x1 = this.nx1;
                this.x2 = this.nx2;
                this.x3 = this.nx3;
                this.cell[this.x1][this.x2][this.x3] = this.curPiece.letter;
            }
            else {
                this.gameState = 1;
            }
            if (b) {
                this.repaint();
            }
        }
        else {
            this.xToDefault();
            this.gameState = 1;
            this.repaint();
        }
    }
    
    public void defineCell() {
        switch (this.sliceAxis) {
            case 1: {
                this.nx1 = this.x1;
                this.nx2 = (this.cursor1 - this.dy1 - this.nx1 * 30) / 19;
                this.nx3 = (this.cursor2 - this.dy2 + this.nx1 * 9 - this.nx2 * 15) / 26;
                break;
            }
            case 2: {
                this.nx2 = this.x2;
                this.nx1 = (this.cursor1 - this.dy1 - this.nx2 * 19) / 30;
                this.nx3 = (this.cursor2 - this.dy2 + this.nx1 * 9 - this.nx2 * 15) / 26;
                break;
            }
            case 3: {
                this.nx3 = this.x3;
                this.nx2 = (3 * this.cursor1 + 10 * this.cursor2 - 3 * this.dy1 - 10 * this.dy2 - this.nx3 * 260) / 207;
                this.nx1 = (this.cursor1 - this.dy1 - this.nx2 * 19) / 30;
                break;
            }
        }
    }
    
    public void pickPiece() {
        this.defineCell();
        if (this.inside(this.nx1, this.nx2, this.nx3) && this.cell[this.nx1][this.nx2][this.nx3] >= 0 && this.fieldPiece[this.nx1][this.nx2][this.nx3].player == this.curPlayer) {
            this.curPiece = this.fieldPiece[this.nx1][this.nx2][this.nx3];
            this.fieldPiece[this.nx1][this.nx2][this.nx3] = null;
            this.x1 = this.nx1;
            this.x2 = this.nx2;
            this.x3 = this.nx3;
            this.gameState = 2;
            this.repaint();
        }
    }
    
    public void newPieces() {
        for (int i = 0; i < 7; ++i) {
            if (this.panelPiece[i][this.curPlayer] == null) {
                final int n = (int)(Math.random() * this.letBorder[25]);
                int n2 = 0;
                for (int n3 = 0; n3 < 26 && n > this.letBorder[n3]; ++n3) {
                    ++n2;
                }
                this.panelPiece[i][this.curPlayer] = new Piece(this.curPlayer, n2, 0);
            }
        }
        this.repaint();
    }
    
    public void stop() {
    }
    
    public void paint(final Graphics graphics) {
        this.offscreenImage = this.createImage(300, 300);
        this.g = this.offscreenImage.getGraphics();
        this.offPaint();
        if (this.offscreenImage != null) {
            graphics.drawImage(this.offscreenImage, 0, 0, this);
        }
    }
    
    public void repaint() {
        this.paint(this.getGraphics());
    }
    
    public void offPaint() {
        this.g.setColor(Color.black);
        this.g.fillRect(0, 0, 300, 300);
        this.defineSlice();
        for (int i = 8; i >= 0; --i) {
            for (int j = 0; j <= 8; ++j) {
                for (int k = 8; k >= 0; --k) {
                    if (this.cell[k][j][i] >= 0) {
                        final int n = this.dy1 + k * 30 + j * 19;
                        final int n2 = this.dy2 + k * -9 + j * 15 + i * 26;
                        int n3 = 0;
                        if (k == this.x1 && j == this.x2 && i == this.x3) {
                            n3 = 1;
                        }
                        boolean b = false;
                        if ((i < this.slice3 || j > this.slice2 || k < this.slice1) && n > this.cursor1 - 80 && n < this.cursor1 + 30 && n2 > this.cursor2 - 80 && n2 < this.cursor2 + 30) {
                            b = true;
                        }
                        switch (this.sliceAxis) {
                            case 1: {
                                this.drawCell(n, n2, k, this.cell[k][j][i], n3, b);
                                break;
                            }
                            case 2: {
                                this.drawCell(n, n2, j, this.cell[k][j][i], n3, b);
                                break;
                            }
                            case 3: {
                                this.drawCell(n, n2, i, this.cell[k][j][i], n3, b);
                                break;
                            }
                        }
                    }
                }
            }
        }
        this.drawPanel();
        if (this.curPiece != null) {
            this.drawCurPiece();
        }
        if (this.gameState < 10) {
            this.drawInterface();
        }
        if (this.gameState == 3) {
            this.g.setColor(Color.red);
            this.g.setFont(this.f2);
            this.g.drawString("Player " + this.curPlayer + " wins!", 5, 295);
        }
        if (this.gameState == 10) {
            this.g.setColor(Color.gray);
            this.g.setFont(this.f2);
            this.g.drawString("How many players?", 5, 15);
            this.g.drawString("1", 5, 30);
            this.g.drawString("2", 5, 45);
            this.g.drawString("3", 5, 60);
            this.g.drawString("4", 5, 75);
            this.g.drawString("Scrabble3D by Igor Galochkin 2004", 5, 280);
            this.g.drawString("http://www.iseeall.i6networks.com", 5, 295);
        }
    }
    
    public void defineSlice() {
        this.slice1 = 0;
        this.slice2 = 8;
        this.slice3 = 0;
        switch (this.sliceAxis) {
            case 1: {
                this.slice1 = this.x1;
                break;
            }
            case 2: {
                this.slice2 = this.x2;
                break;
            }
            case 3: {
                this.slice3 = this.x3;
                break;
            }
        }
    }
    
    public void drawCell(final int n, final int n2, int n3, final int n4, final int n5, final boolean b) {
        if (n3 > 8 || n3 < 0) {
            n3 = 4;
        }
        if (!b) {
            this.g.drawImage(this.pieceImage[n3], n, n2, this);
            this.g.drawImage(this.letterImage[n4][n5], n, n2, this);
        }
        else {
            this.g.setColor(Color.gray);
            this.g.setFont(this.f2);
            this.g.drawString("" + (char)(n4 + 65), n + 25, n2 + 30);
        }
    }
    
    public void drawPanel() {
        this.g.setColor(Color.black);
        this.g.fillRect(240, 1, 59, 298);
        this.g.setColor(Color.darkGray);
        this.g.drawRect(240, 1, 58, 298);
        for (int i = 6; i >= 0; --i) {
            if (this.panelPiece[i][this.curPlayer] != null) {
                this.drawCell(245, 5 + 26 * i, this.curSlice, this.panelPiece[i][this.curPlayer].letter, 0, false);
            }
        }
    }
    
    public void drawCurPiece() {
        if (this.gameState == 1) {
            this.drawCell(this.cursor1 - 25, this.cursor2 - 26, this.curSlice, this.curPiece.letter, 0, false);
        }
    }
    
    public void drawInterface() {
        this.g.setFont(this.f2);
        for (int i = 0; i < this.playersNr; ++i) {
            if (i == this.curPlayer) {
                this.g.setColor(Color.yellow);
            }
            else {
                this.g.setColor(Color.gray);
            }
            this.g.drawString("Pl." + (i + 1) + ": " + this.score[i], 245, 250 + i * 10);
        }
        this.g.setColor(Color.gray);
        this.g.drawString("End turn", 245, 295);
    }
}
