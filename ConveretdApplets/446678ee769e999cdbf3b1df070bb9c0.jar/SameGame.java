import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SameGame extends Applet
{
    Font f;
    Image imageA;
    Image imageB;
    Image imageC;
    Image imageD;
    Image imageAPress;
    Image imageBPress;
    Image imageCPress;
    Image imageDPress;
    Image imageE;
    Image imageEPress;
    Image imageNewGame;
    char[][] position;
    int score;
    int ClickScore;
    int temp;
    int i;
    int j;
    int clickx;
    int clicky;
    int count;
    int blinkflag;
    int firstempty;
    int displaycount;
    int firstzeroX;
    int lastABCDX;
    int successX;
    int havezeroX;
    int countzeroX;
    int firstzeroY;
    int lastABCDY;
    int successY;
    int havezeroY;
    int countzeroY;
    double randomnumber;
    String tempchar;
    AudioClip ClickSound;
    AudioClip ReduceSound;
    
    public SameGame() {
        this.f = new Font("TimesRoman", 1, 30);
        this.position = new char[21][10];
    }
    
    public void init() {
        this.score = 0;
        for (int i9 = 0; i9 <= 9; ++i9) {
            this.position[20][i9] = '0';
        }
        this.firstempty = 0;
        this.tempchar = "xxx";
        this.displaycount = 0;
        this.ClickScore = 0;
        this.count = 0;
        this.setBackground(Color.white);
        this.imageA = this.getImage(this.getCodeBase(), "imagea.gif");
        this.imageB = this.getImage(this.getCodeBase(), "imageb.gif");
        this.imageC = this.getImage(this.getCodeBase(), "imagec.gif");
        this.imageD = this.getImage(this.getCodeBase(), "imaged.gif");
        this.imageE = this.getImage(this.getCodeBase(), "imagee.gif");
        this.imageAPress = this.getImage(this.getCodeBase(), "imageapress.gif");
        this.imageBPress = this.getImage(this.getCodeBase(), "imagebpress.gif");
        this.imageCPress = this.getImage(this.getCodeBase(), "imagecpress.gif");
        this.imageDPress = this.getImage(this.getCodeBase(), "imagedpress.gif");
        this.imageEPress = this.getImage(this.getCodeBase(), "imageepress.gif");
        this.imageNewGame = this.getImage(this.getCodeBase(), "newgame.gif");
        this.ClickSound = this.getAudioClip(this.getCodeBase(), "click.au");
        this.ReduceSound = this.getAudioClip(this.getCodeBase(), "reduce.au");
        this.i = 0;
        while (this.i <= 9) {
            this.j = 0;
            while (this.j <= 19) {
                this.randomnumber = Math.random();
                if (this.randomnumber >= 0.0 && this.randomnumber < 0.2) {
                    this.temp = 1;
                }
                if (this.randomnumber >= 0.2 && this.randomnumber < 0.4) {
                    this.temp = 2;
                }
                if (this.randomnumber >= 0.4 && this.randomnumber < 0.6) {
                    this.temp = 3;
                }
                if (this.randomnumber >= 0.6 && this.randomnumber < 0.8) {
                    this.temp = 4;
                }
                if (this.randomnumber >= 0.8 && this.randomnumber <= 1.0) {
                    this.temp = 5;
                }
                switch (this.temp) {
                    case 1: {
                        this.position[this.j][this.i] = 'A';
                        break;
                    }
                    case 2: {
                        this.position[this.j][this.i] = 'B';
                        break;
                    }
                    case 3: {
                        this.position[this.j][this.i] = 'C';
                        break;
                    }
                    case 4: {
                        this.position[this.j][this.i] = 'D';
                        break;
                    }
                    case 5: {
                        this.position[this.j][this.i] = 'E';
                        break;
                    }
                }
                ++this.j;
            }
            ++this.i;
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        g.setColor(Color.white);
        this.i = 0;
        while (this.i <= 9) {
            this.j = 0;
            while (this.j <= 19) {
                switch (this.position[this.j][this.i]) {
                    case 'A': {
                        g.drawImage(this.imageA, this.j * 30 + 1, this.i * 30 + 1, 30, 30, this);
                        break;
                    }
                    case 'B': {
                        g.drawImage(this.imageB, this.j * 30 + 1, this.i * 30 + 1, 30, 30, this);
                        break;
                    }
                    case 'C': {
                        g.drawImage(this.imageC, this.j * 30 + 1, this.i * 30 + 1, 30, 30, this);
                        break;
                    }
                    case 'D': {
                        g.drawImage(this.imageD, this.j * 30 + 1, this.i * 30 + 1, 30, 30, this);
                        break;
                    }
                    case 'E': {
                        g.drawImage(this.imageE, this.j * 30 + 1, this.i * 30 + 1, 30, 30, this);
                        break;
                    }
                    case '1': {
                        g.drawImage(this.imageAPress, this.j * 30 + 1, this.i * 30 + 1, 30, 30, this);
                        break;
                    }
                    case '2': {
                        g.drawImage(this.imageBPress, this.j * 30 + 1, this.i * 30 + 1, 30, 30, this);
                        break;
                    }
                    case '3': {
                        g.drawImage(this.imageCPress, this.j * 30 + 1, this.i * 30 + 1, 30, 30, this);
                        break;
                    }
                    case '4': {
                        g.drawImage(this.imageDPress, this.j * 30 + 1, this.i * 30 + 1, 30, 30, this);
                        break;
                    }
                    case '5': {
                        g.drawImage(this.imageEPress, this.j * 30 + 1, this.i * 30 + 1, 30, 30, this);
                        break;
                    }
                    case '0': {
                        g.fillRect(this.j * 30 + 1, this.i * 30 + 1, 30, 30);
                        break;
                    }
                }
                ++this.j;
            }
            ++this.i;
        }
        g.setColor(Color.black);
        g.fillRect(0, 305, 600, 50);
        g.drawImage(this.imageNewGame, 5, 310, 100, 40, this);
        g.setColor(Color.white);
        g.drawString("SCORE", 450, 330);
        g.drawString(String.valueOf(this.score), 500, 330);
        if (this.count == 0) {
            this.displaycount = 0;
        }
        else {
            this.displaycount = this.count + 1;
        }
        g.drawString("THIS TOTAL", 250, 320);
        g.drawString(String.valueOf(this.displaycount), 400, 320);
        g.drawString("THIS CLICK SCORE", 250, 340);
        g.drawString(String.valueOf(this.ClickScore), 400, 340);
        if (!this.endgame()) {
            g.setFont(this.f);
            g.setColor(Color.black);
            g.drawString("GAME OVER", 200, 200);
        }
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        if (x >= 5 && x <= 105 && y >= 310 && y <= 350) {
            this.init();
            this.repaint();
            return true;
        }
        this.clickx = x / 30;
        this.clicky = y / 30;
        if ((this.position[this.clickx][this.clicky] >= 'A' && this.position[this.clickx][this.clicky] <= 'E') || this.position[this.clickx][this.clicky] == '0') {
            if (this.blinkflag == 1) {
                this.i = 0;
                while (this.i <= 9) {
                    this.j = 0;
                    while (this.j <= 19) {
                        switch (this.position[this.j][this.i]) {
                            case '1': {
                                this.position[this.j][this.i] = 'A';
                                break;
                            }
                            case '2': {
                                this.position[this.j][this.i] = 'B';
                                break;
                            }
                            case '3': {
                                this.position[this.j][this.i] = 'C';
                                break;
                            }
                            case '4': {
                                this.position[this.j][this.i] = 'D';
                                break;
                            }
                            case '5': {
                                this.position[this.j][this.i] = 'E';
                                break;
                            }
                        }
                        this.blinkflag = 0;
                        ++this.j;
                    }
                    ++this.i;
                }
            }
            if (this.position[this.clickx][this.clicky] != '0') {
                this.count = 0;
                this.findconnect(this.clickx, this.clicky, this.position[this.clickx][this.clicky]);
                if (this.count > 0) {
                    this.ClickSound.play();
                    this.ClickScore = this.CalculateScore(this.count + 1);
                }
            }
        }
        else if (this.blinkflag == 1) {
            this.ReduceSound.play();
            this.rearrange();
            this.score += this.ClickScore;
            this.blinkflag = 0;
        }
        this.repaint();
        return true;
    }
    
    public void rearrange() {
        for (int i = 0; i <= 19; ++i) {
            for (int j = 0; j <= 9; ++j) {
                if (this.position[i][j] >= '1' && this.position[i][j] <= '5') {
                    this.position[i][j] = '0';
                }
            }
        }
        for (int i2 = 0; i2 <= 19; ++i2) {
            this.havezeroX = 0;
            do {
                for (int j2 = 9; j2 >= 0; --j2) {
                    if (this.position[i2][j2] == '0') {
                        if (j2 != 0) {
                            this.position[i2][j2] = this.position[i2][j2 - 1];
                            this.position[i2][j2 - 1] = '0';
                        }
                        else {
                            this.position[i2][j2] = '0';
                        }
                    }
                }
                for (int j3 = 9; j3 >= 0; --j3) {
                    if (this.position[i2][j3] == '0') {
                        this.firstzeroX = j3;
                        this.havezeroX = 1;
                        break;
                    }
                }
                for (int j4 = 9; j4 >= 0; --j4) {
                    if (this.position[i2][j4] >= 'A' && this.position[i2][j4] <= 'E') {
                        this.lastABCDX = j4;
                    }
                }
                this.countzeroX = 0;
                for (int j5 = 9; j5 >= 0; --j5) {
                    if (this.position[i2][j5] == '0') {
                        ++this.countzeroX;
                    }
                }
                if (this.firstzeroX == this.lastABCDX - 1 || this.havezeroX == 0 || this.countzeroX == 10) {
                    this.successX = 1;
                }
                else {
                    this.successX = 0;
                }
            } while (this.successX == 0);
        }
        do {
            this.havezeroY = 0;
            for (int i3 = 0; i3 <= 19; ++i3) {
                if (this.position[i3][9] == '0') {
                    for (int i4 = i3; i4 <= 19; ++i4) {
                        for (int j6 = 0; j6 <= 9; ++j6) {
                            this.position[i4][j6] = this.position[i4 + 1][j6];
                            this.position[i4 + 1][j6] = '0';
                        }
                    }
                }
            }
            this.countzeroY = 0;
            for (int i5 = 0; i5 <= 19; ++i5) {
                if (this.position[i5][9] == '0') {
                    this.firstzeroY = i5;
                    this.havezeroY = 1;
                    break;
                }
            }
            for (int i6 = 0; i6 <= 19; ++i6) {
                if (this.position[i6][9] >= 'A' && this.position[i6][9] <= 'E') {
                    this.lastABCDY = i6;
                }
            }
            this.countzeroY = 0;
            for (int i6 = 9; i6 >= 0; --i6) {
                if (this.position[i6][9] == '0') {
                    ++this.countzeroY;
                }
            }
            if (this.firstzeroY == this.lastABCDY + 1 || this.havezeroY == 0 || this.countzeroY == 20) {
                this.successY = 1;
            }
            else {
                this.successY = 0;
            }
        } while (this.successY == 0);
    }
    
    public int CalculateScore(final int thiscount) {
        int Calculated = 0;
        switch (thiscount) {
            case 0: {
                Calculated = 0;
                break;
            }
            case 1: {
                Calculated = 0;
                break;
            }
            case 2: {
                Calculated = 2;
                break;
            }
            case 3: {
                Calculated = 4;
                break;
            }
            case 4: {
                Calculated = 6;
                break;
            }
            case 5: {
                Calculated = 10;
                break;
            }
            case 6: {
                Calculated = 16;
                break;
            }
            case 7: {
                Calculated = 26;
                break;
            }
            case 8: {
                Calculated = 42;
                break;
            }
            case 9: {
                Calculated = 68;
                break;
            }
            case 10: {
                Calculated = 110;
                break;
            }
            case 11: {
                Calculated = 178;
                break;
            }
            case 12: {
                Calculated = 288;
                break;
            }
            default: {
                Calculated = this.CalculateScore(thiscount - 1) + this.CalculateScore(thiscount - 2);
                break;
            }
        }
        return Calculated;
    }
    
    public void findconnect(final int cx, final int cy, final char clickvalue) {
        switch (clickvalue) {
            case 'A': {
                this.position[cx][cy] = '1';
                break;
            }
            case 'B': {
                this.position[cx][cy] = '2';
                break;
            }
            case 'C': {
                this.position[cx][cy] = '3';
                break;
            }
            case 'D': {
                this.position[cx][cy] = '4';
                break;
            }
            case 'E': {
                this.position[cx][cy] = '5';
                break;
            }
        }
        if (cx < 19 && cy >= 0 && cy <= 9 && this.position[cx + 1][cy] == clickvalue) {
            ++this.count;
            this.findconnect(cx + 1, cy, clickvalue);
        }
        if (cx > 0 && cy >= 0 && cy <= 9 && this.position[cx - 1][cy] == clickvalue) {
            ++this.count;
            this.findconnect(cx - 1, cy, clickvalue);
        }
        if (cx >= 0 && cx <= 19 && cy < 9 && this.position[cx][cy + 1] == clickvalue) {
            ++this.count;
            this.findconnect(cx, cy + 1, clickvalue);
        }
        if (cx >= 0 && cx <= 19 && cy > 0 && this.position[cx][cy - 1] == clickvalue) {
            ++this.count;
            this.findconnect(cx, cy - 1, clickvalue);
        }
        if (this.count == 0) {
            this.position[cx][cy] = clickvalue;
        }
        else {
            this.blinkflag = 1;
        }
    }
    
    public boolean endgame() {
        for (int i = 0; i <= 19; ++i) {
            for (int j = 0; j <= 9; ++j) {
                if (this.position[i][j] != '0') {
                    if (j != 9 && this.position[i][j + 1] == this.position[i][j]) {
                        return true;
                    }
                    if (i != 19 && this.position[i + 1][j] == this.position[i][j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
