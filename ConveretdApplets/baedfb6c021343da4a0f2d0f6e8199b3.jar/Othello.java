import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.Date;
import java.util.Random;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Othello extends Applet implements Runnable
{
    public Image offscreenImg;
    public Graphics offscreen;
    public Image currentImage;
    public Thread runner;
    public Font f1;
    public FontMetrics fm1;
    public Font f2;
    public FontMetrics fm2;
    public Font f3;
    public FontMetrics fm3;
    public Font f4;
    public FontMetrics fm4;
    public Font f5;
    public FontMetrics fm5;
    public Font f6;
    public FontMetrics fm6;
    public Font f7;
    public FontMetrics fm7;
    public Color color0;
    public Color color1;
    public Color color2;
    public Color color3;
    public Color color4;
    public Color color5;
    public Color color6;
    public AudioClip PTv030y;
    public AudioClip PTa031q;
    public Random PTf001l;
    public long PTr033f;
    public long PTr002a;
    public int PTb034v;
    public int PTg035f;
    public int PTs036w;
    public float PTb037u;
    public float PTw003q;
    public float PTz038x;
    public float PTs004e;
    public float PTf005d;
    public float PTv006z;
    public float PTp039o;
    public Date PTn040v;
    public int PTv041z;
    public int PTn007b;
    public int PTf042d;
    public int PTF043S;
    public int PTJ046Y;
    public int PTs044a;
    public int PTr045t;
    public int[] PTg008k;
    public int[] PTt009r;
    public float PTf010s;
    public float PTj047k;
    public int PTh048i;
    public int PTx011q;
    public int PTf012s;
    public float PTc013z;
    public float PTg049m;
    public float PTf050i;
    public int PTf014b;
    public int PTz051a;
    public int PTv052q;
    public int PTj053h;
    public int PTf060b;
    public int[][] PTd015z;
    public int PTo016i;
    public int PTj017k;
    public int PTv062e;
    public int PTT063F;
    public int PTF064G;
    public int PTc065v;
    public int PTj066g;
    public int PTg067r;
    public int[][] PTm018n;
    public float[][] PTd019h;
    public int[] PTf020d;
    public int PTd068g;
    public int PTv069u;
    public int PTm070k;
    public int PTx071q;
    public int PTd072c;
    
    public Othello() {
        this.f1 = new Font("Courier", 1, 14);
        this.fm1 = this.getFontMetrics(this.f1);
        this.f2 = new Font("Courier", 1, 18);
        this.fm2 = this.getFontMetrics(this.f2);
        this.f3 = new Font("Courier", 1, 20);
        this.fm3 = this.getFontMetrics(this.f3);
        this.f4 = new Font("Courier", 1, 25);
        this.fm4 = this.getFontMetrics(this.f4);
        this.f5 = new Font("Courier", 1, 35);
        this.fm5 = this.getFontMetrics(this.f5);
        this.f6 = new Font("Courier", 3, 80);
        this.fm6 = this.getFontMetrics(this.f6);
        this.f7 = new Font("Courier", 3, 170);
        this.fm7 = this.getFontMetrics(this.f7);
        this.color0 = new Color(255, 206, 156);
        this.color1 = new Color(200, 255, 200);
        this.color2 = new Color(255, 220, 176);
        this.color3 = new Color(50, 50, 50);
        this.color4 = new Color(200, 200, 200);
        this.color5 = new Color(235, 129, 41);
        this.color6 = new Color(0, 150, 0);
        this.PTf001l = new Random();
        this.PTr002a = 10000000L;
        this.PTw003q = 100.0f;
        this.PTs004e = 0.8f;
        this.PTf005d = 0.01745329f;
        this.PTv006z = 3.141593f;
        this.PTn007b = 4;
        this.PTg008k = new int[this.PTn007b + 1];
        this.PTt009r = new int[this.PTn007b + 1];
        this.PTf010s = 1.0f;
        this.PTx011q = 500;
        this.PTf012s = 800;
        this.PTc013z = 0.04f;
        this.PTf014b = 25;
        this.PTd015z = new int[this.PTn007b + 1][this.PTf014b + 1];
        this.PTo016i = 12;
        this.PTj017k = 40;
        this.PTm018n = new int[this.PTo016i][this.PTo016i];
        this.PTd019h = new float[this.PTo016i][this.PTo016i];
        this.PTf020d = new int[this.PTo016i];
    }
    
    public void destroy() {
        this.offscreen.dispose();
    }
    
    public final void paint(final Graphics graphics) {
        this.a(graphics);
        String s = "";
        if (this.PTj053h == 0) {
            this.setBackground(this.color0);
            if (this.PTf060b == 0) {
                this.i();
                for (int i = 0; i < this.PTo016i; ++i) {
                    for (int j = 0; j < this.PTo016i; ++j) {
                        this.PTv062e = i;
                        this.PTT063F = j;
                        this.e();
                    }
                }
                int n = (int)(this.PTg049m / this.PTc013z / 15.0f) % 7;
                this.offscreen.setFont(this.f7);
                final String s2 = "Othello";
                for (int k = 0; k < 7; ++k) {
                    n = ((int)(this.PTg049m / this.PTc013z / 15.0f) + k) % 7;
                    switch (n) {
                        case 0: {
                            this.offscreen.setColor(Color.red);
                            break;
                        }
                        case 1: {
                            this.offscreen.setColor(Color.yellow);
                            break;
                        }
                        case 2: {
                            this.offscreen.setColor(Color.green);
                            break;
                        }
                        case 3: {
                            this.offscreen.setColor(Color.magenta);
                            break;
                        }
                        case 4: {
                            this.offscreen.setColor(Color.orange);
                            break;
                        }
                        case 5: {
                            this.offscreen.setColor(Color.blue);
                            break;
                        }
                        case 6: {
                            this.offscreen.setColor(Color.cyan);
                            break;
                        }
                    }
                    this.offscreen.drawString(String.valueOf(s2.charAt(k)), 63 + 90 * k, 180);
                }
                this.offscreen.setFont(this.f4);
                this.offscreen.setColor(Color.magenta);
                this.offscreen.drawString("Press 'F1' for help", 265, 360);
                this.offscreen.setColor(Color.red);
                this.offscreen.drawString("Press 'space bar' to start the game", 145, 420);
                switch (n) {
                    case 3: {
                        this.offscreen.setColor(Color.magenta);
                        break;
                    }
                    case 4: {
                        this.offscreen.setColor(Color.yellow);
                        break;
                    }
                    case 0: {
                        this.offscreen.setColor(Color.orange);
                        break;
                    }
                    case 2: {
                        this.offscreen.setColor(Color.magenta);
                        break;
                    }
                    case 6: {
                        this.offscreen.setColor(Color.orange);
                        break;
                    }
                    case 5: {
                        this.offscreen.setColor(Color.yellow);
                        break;
                    }
                    case 1: {
                        this.offscreen.setColor(Color.cyan);
                        break;
                    }
                }
                this.offscreen.setFont(this.f2);
                this.offscreen.drawString("Another free game designed by dailyclassifieds.com.au", 120, 480);
            }
            else if (this.PTf060b == 1) {
                this.offscreen.setFont(this.f2);
                this.offscreen.setColor(this.color5);
                this.offscreen.drawString("Othello, a checkered board with up to four colors (black, white, red", 10, 20);
                this.offscreen.drawString("and blue pieces) for up to four players, has very simple rules: ", 10, 45);
                this.offscreen.drawString("(1) each player has his own color and put the pieces of his color into", 10, 70);
                this.offscreen.drawString("grids of valid move alternately; (2) valid move: a move joined a", 10, 95);
                this.offscreen.drawString("contiguous line of opposite-colored pieces followed by one similarly", 10, 120);
                this.offscreen.drawString("colored piece; (3) the pieces will be fliped to the similar color;", 10, 145);
                this.offscreen.drawString("(4) if there is no valid move for any player, the game is over.  The", 10, 170);
                this.offscreen.drawString("player with the most pieces wins the game.", 10, 195);
                this.offscreen.setColor(Color.blue);
                this.offscreen.drawString("The function keys are listed below:", 10, 230);
                this.offscreen.drawString("Left click your mouse to release the piece into a grid.", 10, 255);
                this.offscreen.setColor(Color.magenta);
                this.offscreen.drawString("Players: " + this.PTn007b, 10, 280);
                this.offscreen.drawString("(use 'down arrow' and 'up arrow' to change the number)", 10, 305);
                this.offscreen.setColor(this.color6);
                this.offscreen.drawString("Press digit key: '1', '2', '3' or '4' to set the corresponding player", 10, 330);
                this.offscreen.drawString("as computer player or human player;", 10, 355);
                for (int l = 1; l <= 2; ++l) {
                    String s3 = "Player " + l + ": ";
                    if (this.PTg008k[l] == 0) {
                        s3 = String.valueOf(s3) + "computer;";
                    }
                    else if (this.PTg008k[l] == 1) {
                        s3 = String.valueOf(s3) + "human;";
                    }
                    this.offscreen.drawString(s3, 10 + 300 * (l - 1), 380);
                }
                for (int n2 = 3; n2 <= this.PTn007b; ++n2) {
                    String s4 = "Player " + n2 + ": ";
                    if (this.PTg008k[n2] == 0) {
                        s4 = String.valueOf(s4) + "computer;";
                    }
                    else if (this.PTg008k[n2] == 1) {
                        s4 = String.valueOf(s4) + "human;";
                    }
                    this.offscreen.drawString(s4, 10 + 300 * (n2 - 3), 405);
                }
                this.offscreen.setColor(Color.red);
                this.offscreen.setFont(this.f2);
                this.offscreen.drawString("Press 'space bar' to start the game.", 195, 450);
                this.offscreen.setColor(Color.yellow);
                this.offscreen.setFont(this.f1);
                this.offscreen.drawString("Press 'F2' back to the beginning page.", 235, 490);
            }
        }
        else {
            this.setBackground(this.color1);
            this.offscreen.setFont(this.f1);
            this.offscreen.setColor(Color.red);
            if (this.PTj053h > 0) {
                final int n3 = 20;
                final int n4 = 50;
                if (this.PTv041z == 1) {
                    s = "Black moves!";
                }
                else if (this.PTv041z == 2) {
                    s = "White moves!";
                }
                else if (this.PTv041z == 3) {
                    s = "Red moves!";
                }
                else if (this.PTv041z == 4) {
                    s = "Blue moves!";
                }
                this.offscreen.drawString(s, n3, n4);
            }
            else if (this.PTj053h < 0) {
                final int n5 = 20;
                this.offscreen.drawString("No valid move,", n5, 50);
                this.offscreen.drawString("game over.", n5, 80);
            }
            if (this.PTj053h < 0) {
                final int n6 = 20;
                final int n7 = 200;
                this.offscreen.setColor(Color.blue);
                this.offscreen.drawString("Explanation:", n6, n7);
                for (int n8 = 1; n8 <= 4; ++n8) {
                    final int n9 = 20;
                    final int n10 = 200 + 30 * n8;
                    if (n8 == 1) {
                        this.offscreen.drawString("1:  black color;", n9, n10);
                    }
                    else if (n8 == 2) {
                        this.offscreen.drawString("2:  white color;", n9, n10);
                    }
                    else if (n8 == 3) {
                        this.offscreen.drawString("3:  red color;", n9, n10);
                    }
                    else if (n8 == 4) {
                        this.offscreen.drawString("4:  blue color;", n9, n10);
                    }
                }
            }
            this.i();
            for (int pTv062e = 0; pTv062e < this.PTo016i; ++pTv062e) {
                for (int ptt063F = 0; ptt063F < this.PTo016i; ++ptt063F) {
                    this.PTv062e = pTv062e;
                    this.PTT063F = ptt063F;
                    if (this.PTm018n[this.PTv062e][this.PTT063F] > 0) {
                        this.e();
                    }
                }
            }
            if (this.PTj053h > 0) {
                this.f();
            }
            if (this.PTJ046Y == 0) {
                final int n11 = this.PTF064G + this.PTs044a * this.PTj017k;
                final int n12 = this.PTc065v + this.PTr045t * this.PTj017k;
                this.offscreen.setColor(Color.red);
                this.offscreen.setFont(this.f1);
                this.offscreen.drawString("Not a valid move!", n11, n12);
            }
            if (this.PTj053h > 0) {
                final int n13 = 350;
                final int n14 = 200;
                this.offscreen.setColor(this.color5);
                this.offscreen.setFont(this.f2);
                if ((this.PTv041z == 1 | this.PTv041z == this.PTn007b) & this.PTt009r[this.PTn007b] == 0) {
                    if (this.PTn007b == 2) {
                        this.offscreen.drawString("No valid move for white, black moves!", n13, n14);
                    }
                    else if (this.PTn007b == 3) {
                        this.offscreen.drawString("No valid move for red, black moves!", n13, n14);
                    }
                    else if (this.PTn007b == 4) {
                        this.offscreen.drawString("No valid move for blue, black moves!", n13, n14);
                    }
                }
                else if ((this.PTv041z == 2 | this.PTv041z == 1) & this.PTt009r[1] == 0) {
                    this.offscreen.drawString("No valid move for black, white moves!", n13, n14);
                }
                else if ((this.PTv041z == 3 | this.PTv041z == 2) & this.PTt009r[2] == 0) {
                    this.offscreen.drawString("No valid move for white, red moves!", n13, n14);
                }
                else if ((this.PTv041z == 4 | this.PTv041z == 3) & this.PTt009r[3] == 0) {
                    this.offscreen.drawString("No valid move for red, blue moves!", n13, n14);
                }
            }
            for (int n15 = 0; n15 < this.PTo016i; ++n15) {
                for (int n16 = 0; n16 < this.PTo016i; ++n16) {
                    if (this.PTd019h[n15][n16] > 0.0f) {
                        this.offscreen.setColor(Color.red);
                    }
                }
            }
            if (this.PTj053h < 0) {
                final int n17 = (int)(this.PTg049m / 10.0f / this.PTc013z) % 3;
                final int[] array = new int[5];
                for (int n18 = 0; n18 <= this.PTn007b; ++n18) {
                    array[n18] = 0;
                }
                for (int n19 = 0; n19 < this.PTo016i; ++n19) {
                    for (int n20 = 0; n20 < this.PTo016i; ++n20) {
                        if (this.PTm018n[n19][n20] == 1) {
                            final int[] array2 = array;
                            final int n21 = 1;
                            ++array2[n21];
                        }
                        else if (this.PTm018n[n19][n20] == 2) {
                            final int[] array3 = array;
                            final int n22 = 2;
                            ++array3[n22];
                        }
                        else if (this.PTm018n[n19][n20] == 3) {
                            final int[] array4 = array;
                            final int n23 = 3;
                            ++array4[n23];
                        }
                        else if (this.PTm018n[n19][n20] == 4) {
                            final int[] array5 = array;
                            final int n24 = 4;
                            ++array5[n24];
                        }
                    }
                }
                if (n17 == 0) {
                    this.offscreen.setColor(Color.green);
                }
                else if (n17 == 1) {
                    this.offscreen.setColor(Color.orange);
                }
                else {
                    this.offscreen.setColor(Color.red);
                }
                this.offscreen.setFont(this.f2);
                if (this.PTn007b == 2) {
                    this.offscreen.drawString("Black: " + array[1] + ";  White: " + array[2] + ";", 165, 200);
                    this.PTd015z[1][this.PTz051a] = array[1];
                    this.PTd015z[2][this.PTz051a] = array[2];
                }
                else if (this.PTn007b == 3) {
                    this.offscreen.drawString("Black: " + array[1] + ";  White: " + array[2] + ";  Red: " + array[3] + ";", 165, 200);
                    this.PTd015z[1][this.PTz051a] = array[1];
                    this.PTd015z[2][this.PTz051a] = array[2];
                    this.PTd015z[3][this.PTz051a] = array[3];
                }
                else if (this.PTn007b == 4) {
                    this.offscreen.drawString("Black: " + array[1] + ";  White: " + array[2] + ";  Red: " + array[3] + ";  Blue: " + array[4] + ";", 165, 200);
                    this.PTd015z[1][this.PTz051a] = array[1];
                    this.PTd015z[2][this.PTz051a] = array[2];
                    this.PTd015z[3][this.PTz051a] = array[3];
                    this.PTd015z[4][this.PTz051a] = array[4];
                }
                int n25 = 0;
                int n26 = 144;
                int n27 = 0;
                for (int n28 = 1; n28 <= this.PTn007b; ++n28) {
                    if (array[n28] > n25) {
                        n25 = array[n28];
                        n27 = 1;
                    }
                    else if (array[n28] == n25) {
                        ++n27;
                    }
                    if (array[n28] < n26) {
                        n26 = array[n28];
                    }
                }
                if (n17 == 0) {
                    this.offscreen.setColor(Color.green);
                }
                else {
                    this.offscreen.setColor(this.color5);
                }
                this.offscreen.setFont(this.f4);
                if (n25 == n26) {
                    this.offscreen.drawString("Your scores are the same, no one is the winner.", 150, 270);
                }
                else {
                    final int n29 = 200;
                    final int n30 = 270;
                    if (n27 == 1) {
                        String s5 = "Congratulation, the winner: ";
                        this.offscreen.drawString(s5, n29, n30);
                        final int n31 = 250;
                        final int n32 = 310;
                        for (int n33 = 1; n33 <= this.PTn007b; ++n33) {
                            if (array[n33] == n25) {
                                switch (n33) {
                                    case 1: {
                                        s5 = "black color player;";
                                        break;
                                    }
                                    case 2: {
                                        s5 = "white color player;";
                                        break;
                                    }
                                    case 3: {
                                        s5 = "red color player;";
                                        break;
                                    }
                                    case 4: {
                                        s5 = "blue color player;";
                                        break;
                                    }
                                }
                            }
                        }
                        this.offscreen.drawString(s5, n31, n32);
                    }
                    else {
                        String s6 = "Congratulation, the winners: ";
                        this.offscreen.drawString(s6, n29, n30);
                        final int n34 = 250;
                        int n35 = 310;
                        for (int n36 = 1; n36 <= this.PTn007b; ++n36) {
                            if (array[n36] == n25) {
                                switch (n36) {
                                    case 1: {
                                        s6 = "black color player;";
                                        break;
                                    }
                                    case 2: {
                                        s6 = "white color player;";
                                        break;
                                    }
                                    case 3: {
                                        s6 = "red color player;";
                                        break;
                                    }
                                    case 4: {
                                        s6 = "blue color player;";
                                        break;
                                    }
                                }
                                this.offscreen.drawString(s6, n34, n35);
                                n35 += 40;
                            }
                        }
                    }
                }
                if (this.PTg049m - this.PTf050i > 50.0f * this.PTc013z && this.PTg049m - this.PTf050i > 50.0f * this.PTc013z) {
                    this.offscreen.setFont(this.f3);
                    this.offscreen.setColor(Color.blue);
                    if (n17 == 2) {
                        this.offscreen.setColor(Color.white);
                    }
                    this.offscreen.drawString("Press 'space bar' to play again.", 210, 450);
                    this.offscreen.setFont(this.f6);
                    this.offscreen.setColor(Color.red);
                    if (n17 == 0) {
                        this.offscreen.setColor(Color.yellow);
                    }
                    else if (n17 == 1) {
                        this.offscreen.setColor(Color.green);
                    }
                    this.offscreen.drawString("Game Over!", 165, 150);
                    this.offscreen.setFont(this.f5);
                    this.offscreen.setColor(Color.orange);
                    if (n17 == 1) {
                        this.offscreen.setColor(Color.orange);
                    }
                    this.offscreen.setFont(this.f3);
                    this.offscreen.setColor(Color.magenta);
                    this.offscreen.drawString("Score List: ", 640, 20);
                    this.offscreen.setFont(this.f1);
                    this.offscreen.setColor(Color.red);
                    final int n37 = 40;
                    for (int n38 = 1; n38 <= 4; ++n38) {
                        this.offscreen.drawString(String.valueOf(n38), 640 + 40 * (n38 - 1), n37);
                    }
                    this.offscreen.setColor(Color.orange);
                    for (int n39 = 0; n39 <= this.PTz051a; ++n39) {
                        final int n40 = 40 + 18 * (n39 + 1);
                        for (int n41 = 1; n41 <= this.PTd015z[0][n39]; ++n41) {
                            this.offscreen.drawString(String.valueOf(this.PTd015z[n41][n39]), 640 + 40 * (n41 - 1), n40);
                        }
                    }
                }
            }
        }
        graphics.drawImage(this.offscreenImg, 0, 0, this);
    }
    
    public final void a(final Graphics graphics) {
        this.offscreen.setColor(this.getBackground());
        this.offscreen.fillRect(0, 0, this.size().width, this.size().height);
        this.offscreen.setColor(this.getForeground());
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private final void b(final int n) {
        int n2 = this.PTo016i;
        if (this.PTf020d[this.PTm070k] == 0) {
            n2 = this.PTm070k;
        }
        else {
            this.PTd068g = this.PTm070k;
            this.PTv069u = this.PTm070k;
            for (int i = this.PTd068g - 1; i >= 0; --i) {
                if (this.PTf020d[i] == 0) {
                    this.PTd068g = i;
                    i = -1;
                }
            }
            for (int j = this.PTv069u + 1; j < this.PTo016i; ++j) {
                if (this.PTf020d[j] == 0) {
                    this.PTv069u = j;
                    j = this.PTo016i;
                }
            }
            if (this.PTd068g != this.PTm070k) {
                for (int k = this.PTd068g + 1; k < this.PTo016i; ++k) {
                    if (this.PTf020d[k] == 0) {
                        k = this.PTo016i;
                    }
                    else if (this.PTf020d[k] == n) {
                        if (k > this.PTm070k && k > this.PTm070k) {
                            n2 = this.PTd068g;
                        }
                        k = this.PTo016i;
                    }
                }
            }
            if (this.PTv069u != this.PTm070k) {
                for (int l = this.PTv069u - 1; l >= 0; --l) {
                    if (this.PTf020d[l] == 0) {
                        l = -1;
                    }
                    else if (this.PTf020d[l] == n) {
                        if (l < this.PTm070k && l < this.PTm070k) {
                            n2 = this.PTv069u;
                        }
                        l = -1;
                    }
                }
            }
        }
        if (n2 != this.PTo016i) {
            this.PTf020d[n2] = n;
            this.PTd068g = n2;
            this.PTv069u = n2;
            for (int n3 = n2 - 1; n3 >= 0; --n3) {
                if (this.PTf020d[n3] == 0) {
                    n3 = -1;
                }
                else if (this.PTf020d[n3] == n) {
                    for (int n4 = n3; n4 <= n2; ++n4) {
                        this.PTf020d[n4] = n;
                    }
                    n3 = -1;
                }
            }
            for (int n5 = n2 + 1; n5 < this.PTo016i; ++n5) {
                if (this.PTf020d[n5] == 0) {
                    n5 = this.PTo016i;
                }
                else if (this.PTf020d[n5] == n) {
                    for (int n6 = n2; n6 <= n5; ++n6) {
                        this.PTf020d[n6] = n;
                    }
                    n5 = this.PTo016i;
                }
            }
            for (int pTd068g = n2 - 1; pTd068g >= 0; --pTd068g) {
                if (this.PTf020d[pTd068g] == 0) {
                    this.PTd068g = pTd068g;
                    pTd068g = -1;
                }
            }
            for (int pTo016i = n2 + 1; pTo016i < this.PTo016i; ++pTo016i) {
                if (this.PTf020d[pTo016i] == 0) {
                    this.PTv069u = pTo016i;
                    pTo016i = this.PTo016i;
                }
            }
        }
    }
    
    private final void c() {
        float n = 0.0f;
        int pTo016i = this.PTo016i;
        int pTo016i2 = this.PTo016i;
        final int[] array = new int[this.PTo016i];
        for (int i = 0; i < this.PTo016i; ++i) {
            for (int j = 0; j < this.PTo016i; ++j) {
                if (this.PTd019h[i][j] >= 1.0f) {
                    this.PTd019h[i][j] = 1.0f;
                    if ((i == 0 | i == this.PTo016i - 1) & (j == 0 | j == this.PTo016i - 1)) {
                        final float[] array2 = this.PTd019h[i];
                        final int n2 = j;
                        array2[n2] += 30.0f;
                    }
                    else if (i == 0 | i == this.PTo016i - 1 | j == 0 | j == this.PTo016i - 1) {
                        boolean b = true;
                        this.PTx071q = this.PTv041z;
                        this.PTd072c = 1;
                        this.PTd068g = j;
                        this.PTv069u = j;
                        if (i == 0 | i == this.PTo016i - 1) {
                            for (int k = 0; k < this.PTo016i; ++k) {
                                if (k != j & this.PTm018n[i][k] == 0) {
                                    b = false;
                                }
                            }
                        }
                        if (j == 0 | j == this.PTo016i - 1) {
                            for (int l = 0; l < this.PTo016i; ++l) {
                                if (l != i & this.PTm018n[l][j] == 0) {
                                    b = false;
                                }
                            }
                        }
                        if (b) {
                            final float[] array3 = this.PTd019h[i];
                            final int n3 = j;
                            array3[n3] += 25.0f;
                        }
                        else {
                            if (i == 0 | i == this.PTo016i - 1) {
                                for (int n4 = 0; n4 < this.PTo016i; ++n4) {
                                    this.PTf020d[n4] = this.PTm018n[i][n4];
                                }
                                this.PTd068g = j;
                                this.PTx071q = this.PTf020d[j];
                                this.PTv069u = j;
                                this.PTd072c = this.PTf020d[j];
                                this.PTm070k = j;
                                if (i == 0) {
                                    if (this.PTm018n[1][j] > 0) {
                                        final float[] array4 = this.PTd019h[i];
                                        final int n5 = j;
                                        ++array4[n5];
                                    }
                                }
                                else if (i == this.PTo016i - 1 && this.PTm018n[this.PTo016i - 2][j] > 0) {
                                    final float[] array5 = this.PTd019h[i];
                                    final int n6 = j;
                                    ++array5[n6];
                                }
                            }
                            if (j == 0 | j == this.PTo016i - 1) {
                                for (int n7 = 0; n7 < this.PTo016i; ++n7) {
                                    this.PTf020d[n7] = this.PTm018n[n7][j];
                                }
                                this.PTd068g = i;
                                this.PTx071q = this.PTf020d[i];
                                this.PTv069u = i;
                                this.PTd072c = this.PTf020d[i];
                                this.PTm070k = i;
                                if (j == 0) {
                                    if (this.PTm018n[i][1] > 0) {
                                        final float[] array6 = this.PTd019h[i];
                                        final int n8 = j;
                                        ++array6[n8];
                                    }
                                }
                                else if (j == this.PTo016i - 1 && this.PTm018n[i][this.PTo016i - 2] > 0) {
                                    final float[] array7 = this.PTd019h[i];
                                    final int n9 = j;
                                    ++array7[n9];
                                }
                            }
                            int n10 = 0;
                            for (int n11 = 0; n11 < this.PTo016i; ++n11) {
                                if (this.PTf020d[n11] == this.PTv041z) {
                                    ++n10;
                                }
                                array[n11] = 2 * this.PTn007b;
                            }
                            int pTv041z = this.PTv041z;
                            while (pTv041z != 0) {
                                if (pTv041z == this.PTv041z) {
                                    boolean b2 = false;
                                    for (int n12 = 0; n12 < this.PTo016i; ++n12) {
                                        if (this.PTf020d[n12] != array[n12]) {
                                            b2 = true;
                                        }
                                    }
                                    for (int n13 = 0; n13 < this.PTo016i; ++n13) {
                                        array[n13] = this.PTf020d[n13];
                                    }
                                    if (!b2) {
                                        pTv041z = 0;
                                    }
                                }
                                if (pTv041z != 0) {
                                    this.b(pTv041z);
                                    if (++pTv041z <= this.PTn007b) {
                                        continue;
                                    }
                                    pTv041z -= this.PTn007b;
                                }
                            }
                            int n14 = 0;
                            boolean b3 = true;
                            for (int n15 = 0; n15 <= this.PTm070k; ++n15) {
                                if (this.PTf020d[n15] != this.PTv041z) {
                                    b3 = false;
                                }
                            }
                            for (int pTm070k = this.PTm070k; pTm070k < this.PTo016i; ++pTm070k) {
                                if (this.PTf020d[pTm070k] != this.PTv041z) {
                                    b3 = false;
                                }
                            }
                            if (b3) {
                                final float[] array8 = this.PTd019h[i];
                                final int n16 = j;
                                array8[n16] += 3.0f;
                            }
                            for (int n17 = 0; n17 < this.PTo016i; ++n17) {
                                if (this.PTf020d[n17] == this.PTv041z) {
                                    ++n14;
                                }
                            }
                            final float[] array9 = this.PTd019h[i];
                            final int n18 = j;
                            array9[n18] += 10.0f * (n14 - n10) / this.PTo016i;
                            if (n14 <= n10) {
                                final float[] array10 = this.PTd019h[i];
                                final int n19 = j;
                                ++array10[n19];
                            }
                            else if (this.PTd068g == this.PTm070k | this.PTv069u == this.PTm070k) {
                                final float[] array11 = this.PTd019h[i];
                                final int n20 = j;
                                array11[n20] += 20.0f;
                            }
                            else {
                                final float[] array12 = this.PTd019h[i];
                                final int n21 = j;
                                array12[n21] += 16.0f;
                                if ((this.PTd068g > 0 & this.PTd068g < this.PTo016i - 1) && (this.PTf020d[this.PTd068g - 1] == this.PTv041z & this.PTf020d[this.PTd068g + 1] == this.PTv041z)) {
                                    final float[] array13 = this.PTd019h[i];
                                    final int n22 = j;
                                    array13[n22] -= 8.0f;
                                    for (int pTd068g = this.PTd068g - 1; pTd068g >= 0; --pTd068g) {
                                        if (this.PTf020d[pTd068g] == 0) {
                                            this.PTd068g = pTd068g;
                                            pTd068g = -1;
                                        }
                                    }
                                    if (this.PTd068g == 0 | this.PTv069u == this.PTo016i - 1) {
                                        final float[] array14 = this.PTd019h[i];
                                        final int n23 = j;
                                        array14[n23] -= 8.0f;
                                    }
                                    else if (this.PTd068g == 1 | this.PTv069u == this.PTo016i - 2) {
                                        final float[] array15 = this.PTd019h[i];
                                        final int n24 = j;
                                        array15[n24] -= 6.0f;
                                    }
                                }
                                if ((this.PTv069u > 0 & this.PTv069u < this.PTo016i - 1) && (this.PTf020d[this.PTv069u - 1] == this.PTv041z & this.PTf020d[this.PTv069u + 1] == this.PTv041z)) {
                                    if (this.PTd019h[i][j] >= 16.0f) {
                                        final float[] array16 = this.PTd019h[i];
                                        final int n25 = j;
                                        array16[n25] -= 8.0f;
                                    }
                                    for (int pTo016i3 = this.PTv069u + 1; pTo016i3 < this.PTo016i; ++pTo016i3) {
                                        if (this.PTf020d[pTo016i3] == 0) {
                                            this.PTv069u = pTo016i3;
                                            pTo016i3 = this.PTo016i;
                                        }
                                    }
                                    if (this.PTd068g == 0 | this.PTv069u == this.PTo016i - 1) {
                                        if (this.PTd019h[i][j] > 8.0f) {
                                            final float[] array17 = this.PTd019h[i];
                                            final int n26 = j;
                                            array17[n26] -= 8.0f;
                                        }
                                        else {
                                            this.PTd019h[i][j] = 1.0f;
                                        }
                                    }
                                    else if (this.PTd068g == 1 | this.PTv069u == this.PTo016i - 2) {
                                        if (this.PTd019h[i][j] > 6.0f) {
                                            final float[] array18 = this.PTd019h[i];
                                            final int n27 = j;
                                            array18[n27] -= 6.0f;
                                        }
                                        else {
                                            this.PTd019h[i][j] = 3.0f;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else if (i == 1 | i == this.PTo016i - 2 | j == 1 | j == this.PTo016i - 2) {
                        if ((i == 1 | i == this.PTo016i - 2) & (j == 1 | j == this.PTo016i - 2)) {
                            final float[] array19 = this.PTd019h[i];
                            final int n28 = j;
                            array19[n28] += 0.0f;
                            if (i == 1 & j == 1) {
                                if (this.PTm018n[0][0] == this.PTv041z) {
                                    final float[] array20 = this.PTd019h[i];
                                    final int n29 = j;
                                    array20[n29] += 4.0f;
                                }
                            }
                            else if (i == 1 & j == this.PTo016i - 2) {
                                if (this.PTm018n[0][this.PTo016i - 1] == this.PTv041z) {
                                    final float[] array21 = this.PTd019h[i];
                                    final int n30 = j;
                                    array21[n30] += 4.0f;
                                }
                            }
                            else if (i == this.PTo016i - 2 & j == 1) {
                                if (this.PTm018n[this.PTo016i - 1][0] == this.PTv041z) {
                                    final float[] array22 = this.PTd019h[i];
                                    final int n31 = j;
                                    array22[n31] += 4.0f;
                                }
                            }
                            else if ((i == this.PTo016i - 2 & j == this.PTo016i - 2) && this.PTm018n[this.PTo016i - 1][this.PTo016i - 1] == this.PTv041z) {
                                final float[] array23 = this.PTd019h[i];
                                final int n32 = j;
                                array23[n32] += 4.0f;
                            }
                        }
                        else {
                            final float[] array24 = this.PTd019h[i];
                            final int n33 = j;
                            array24[n33] += 2.0f;
                            if (i == 1 && (j > 1 & j < this.PTo016i - 2) && (this.PTm018n[0][j - 1] == 0 & this.PTm018n[0][j] == 0 & this.PTm018n[0][j + 1] == 0)) {
                                if (this.PTm018n[0][j - 2] != this.PTv041z & this.PTm018n[0][j - 2] != 0) {
                                    final float[] array25 = this.PTd019h[i];
                                    final int n34 = j;
                                    ++array25[n34];
                                }
                                if (this.PTm018n[0][j + 2] != this.PTv041z & this.PTm018n[0][j + 2] != 0) {
                                    final float[] array26 = this.PTd019h[i];
                                    final int n35 = j;
                                    ++array26[n35];
                                }
                            }
                            if (i == this.PTo016i - 2 && (j > 1 & j < this.PTo016i - 2) && (this.PTm018n[this.PTo016i - 1][j - 1] == 0 & this.PTm018n[this.PTo016i - 1][j] == 0 & this.PTm018n[this.PTo016i - 1][j + 1] == 0)) {
                                if (this.PTm018n[this.PTo016i - 1][j - 2] != this.PTv041z & this.PTm018n[this.PTo016i - 1][j - 2] != 0) {
                                    final float[] array27 = this.PTd019h[i];
                                    final int n36 = j;
                                    ++array27[n36];
                                }
                                if (this.PTm018n[this.PTo016i - 1][j + 2] != this.PTv041z & this.PTm018n[this.PTo016i - 1][j + 2] != 0) {
                                    final float[] array28 = this.PTd019h[i];
                                    final int n37 = j;
                                    ++array28[n37];
                                }
                            }
                            if (j == 1 && (i > 1 & i < this.PTo016i - 2) && (this.PTm018n[i - 1][0] == 0 & this.PTm018n[i][0] == 0 & this.PTm018n[i + 1][0] == 0)) {
                                if (this.PTm018n[i - 2][0] != this.PTv041z & this.PTm018n[i - 2][0] != 0) {
                                    final float[] array29 = this.PTd019h[i];
                                    final int n38 = j;
                                    ++array29[n38];
                                }
                                if (this.PTm018n[i + 2][0] != this.PTv041z & this.PTm018n[i + 2][0] != 0) {
                                    final float[] array30 = this.PTd019h[i];
                                    final int n39 = j;
                                    ++array30[n39];
                                }
                            }
                            if (j == this.PTo016i - 2 && (i > 1 & i < this.PTo016i - 2) && (this.PTm018n[i - 1][this.PTo016i - 1] == 0 & this.PTm018n[i][this.PTo016i - 1] == 0 & this.PTm018n[i + 1][this.PTo016i - 1] == 0)) {
                                if (this.PTm018n[i - 2][this.PTo016i - 1] != this.PTv041z & this.PTm018n[i - 2][this.PTo016i - 1] != 0) {
                                    final float[] array31 = this.PTd019h[i];
                                    final int n40 = j;
                                    ++array31[n40];
                                }
                                if (this.PTm018n[i + 2][this.PTo016i - 1] != this.PTv041z & this.PTm018n[i + 2][this.PTo016i - 1] != 0) {
                                    final float[] array32 = this.PTd019h[i];
                                    final int n41 = j;
                                    ++array32[n41];
                                }
                            }
                        }
                    }
                    else if (i == 2 | i == this.PTo016i - 3 | j == 2 | j == this.PTo016i - 3) {
                        int n42 = 1;
                        this.PTx071q = this.PTv041z;
                        this.PTd072c = 1;
                        this.PTd068g = j;
                        this.PTv069u = j;
                        if (i == 2 | i == this.PTo016i - 3) {
                            for (int n43 = 0; n43 < this.PTo016i; ++n43) {
                                if (n43 != j & this.PTm018n[i][n43] == 0) {
                                    n42 = 0;
                                }
                            }
                        }
                        if (j == 2 | j == this.PTo016i - 3) {
                            for (int n44 = 0; n44 < this.PTo016i; ++n44) {
                                if (n44 != i & this.PTm018n[n44][j] == 0) {
                                    n42 = 0;
                                }
                            }
                        }
                        if ((i == 2 | i == this.PTo016i - 3) & (j == 2 | j == this.PTo016i - 3)) {
                            n42 = 2;
                        }
                        if (n42 == 1) {
                            final float[] array33 = this.PTd019h[i];
                            final int n45 = j;
                            array33[n45] += 18.0f;
                        }
                        else if (n42 == 2) {
                            final float[] array34 = this.PTd019h[i];
                            final int n46 = j;
                            array34[n46] += 20.0f;
                        }
                        else {
                            if (i == 2 | i == this.PTo016i - 3) {
                                for (int n47 = 0; n47 < this.PTo016i; ++n47) {
                                    this.PTf020d[n47] = this.PTm018n[i][n47];
                                }
                                this.PTd068g = j;
                                this.PTx071q = this.PTf020d[j];
                                this.PTv069u = j;
                                this.PTd072c = this.PTf020d[j];
                                this.PTm070k = j;
                                if (i == 2) {
                                    if (this.PTm018n[3][j] > 0) {
                                        final float[] array35 = this.PTd019h[i];
                                        final int n48 = j;
                                        ++array35[n48];
                                    }
                                }
                                else if (i == this.PTo016i - 3 && this.PTm018n[this.PTo016i - 4][j] > 0) {
                                    final float[] array36 = this.PTd019h[i];
                                    final int n49 = j;
                                    ++array36[n49];
                                }
                            }
                            if (j == 2 | j == this.PTo016i - 3) {
                                for (int n50 = 0; n50 < this.PTo016i; ++n50) {
                                    this.PTf020d[n50] = this.PTm018n[n50][j];
                                }
                                this.PTd068g = i;
                                this.PTx071q = this.PTf020d[i];
                                this.PTv069u = i;
                                this.PTd072c = this.PTf020d[i];
                                this.PTm070k = i;
                                if (j == 2) {
                                    if (this.PTm018n[i][3] > 0) {
                                        final float[] array37 = this.PTd019h[i];
                                        final int n51 = j;
                                        ++array37[n51];
                                    }
                                }
                                else if (j == this.PTo016i - 3 && this.PTm018n[i][this.PTo016i - 4] > 0) {
                                    final float[] array38 = this.PTd019h[i];
                                    final int n52 = j;
                                    ++array38[n52];
                                }
                            }
                            int n53 = 0;
                            for (int n54 = 0; n54 < this.PTo016i; ++n54) {
                                if (this.PTf020d[n54] == this.PTv041z) {
                                    ++n53;
                                }
                                array[n54] = 2 * this.PTn007b;
                            }
                            int pTv041z2 = this.PTv041z;
                            while (pTv041z2 != 0) {
                                if (pTv041z2 == this.PTv041z) {
                                    boolean b4 = false;
                                    for (int n55 = 0; n55 < this.PTo016i; ++n55) {
                                        if (this.PTf020d[n55] != array[n55]) {
                                            b4 = true;
                                        }
                                    }
                                    for (int n56 = 0; n56 < this.PTo016i; ++n56) {
                                        array[n56] = this.PTf020d[n56];
                                    }
                                    if (!b4) {
                                        pTv041z2 = 0;
                                    }
                                }
                                if (pTv041z2 != 0) {
                                    this.b(pTv041z2);
                                    if (++pTv041z2 <= this.PTn007b) {
                                        continue;
                                    }
                                    pTv041z2 -= this.PTn007b;
                                }
                            }
                            int n57 = 0;
                            for (int n58 = 0; n58 < this.PTo016i; ++n58) {
                                if (this.PTf020d[n58] == this.PTv041z) {
                                    ++n57;
                                }
                            }
                            final float[] array39 = this.PTd019h[i];
                            final int n59 = j;
                            array39[n59] += 10.0f * (n57 - n53) / this.PTo016i;
                            if (n57 <= n53) {
                                final float[] array40 = this.PTd019h[i];
                                final int n60 = j;
                                array40[n60] += 2.0f;
                            }
                            else if (this.PTd068g == this.PTm070k | this.PTv069u == this.PTm070k) {
                                final float[] array41 = this.PTd019h[i];
                                final int n61 = j;
                                array41[n61] += 16.0f;
                            }
                            else {
                                final float[] array42 = this.PTd019h[i];
                                final int n62 = j;
                                array42[n62] += 14.0f;
                                if ((this.PTd068g > 0 & this.PTd068g < this.PTo016i - 1) && (this.PTf020d[this.PTd068g - 1] == this.PTv041z & this.PTf020d[this.PTd068g + 1] == this.PTv041z)) {
                                    final float[] array43 = this.PTd019h[i];
                                    final int n63 = j;
                                    array43[n63] -= 11.0f;
                                    for (int pTd068g2 = this.PTd068g - 1; pTd068g2 >= 0; --pTd068g2) {
                                        if (this.PTf020d[pTd068g2] == 0) {
                                            this.PTd068g = pTd068g2;
                                            pTd068g2 = -1;
                                        }
                                    }
                                    if (this.PTd068g == 0 | this.PTv069u == this.PTo016i - 1) {
                                        final float[] array44 = this.PTd019h[i];
                                        final int n64 = j;
                                        array44[n64] -= 12.0f;
                                    }
                                }
                                if ((this.PTv069u > 0 & this.PTv069u < this.PTo016i - 1) && (this.PTf020d[this.PTv069u - 1] == this.PTv041z & this.PTf020d[this.PTv069u + 1] == this.PTv041z)) {
                                    if (this.PTd019h[i][j] > 14.0f) {
                                        final float[] array45 = this.PTd019h[i];
                                        final int n65 = j;
                                        array45[n65] -= 11.0f;
                                    }
                                    else {
                                        this.PTd019h[i][j] = 4.0f;
                                    }
                                    for (int pTo016i4 = this.PTv069u + 1; pTo016i4 < this.PTo016i; ++pTo016i4) {
                                        if (this.PTf020d[pTo016i4] == 0) {
                                            this.PTv069u = pTo016i4;
                                            pTo016i4 = this.PTo016i;
                                        }
                                    }
                                    if (this.PTd068g == 0 | this.PTv069u == this.PTo016i - 1) {
                                        this.PTd019h[i][j] = 3.0f;
                                    }
                                }
                            }
                        }
                    }
                    else if (i == 3 | i == this.PTo016i - 4 | j == 3 | j == this.PTo016i - 4) {
                        if ((i == 3 | i == this.PTo016i - 4) & (j == 3 | j == this.PTo016i - 4)) {
                            final float[] array46 = this.PTd019h[i];
                            final int n66 = j;
                            array46[n66] += 3.0f;
                            if (i == 3 & j == 3) {
                                if (this.PTm018n[2][2] == this.PTv041z) {
                                    final float[] array47 = this.PTd019h[i];
                                    final int n67 = j;
                                    array47[n67] += 5.0f;
                                }
                            }
                            else if (i == 3 & j == this.PTo016i - 4) {
                                if (this.PTm018n[2][this.PTo016i - 3] == this.PTv041z) {
                                    final float[] array48 = this.PTd019h[i];
                                    final int n68 = j;
                                    array48[n68] += 5.0f;
                                }
                            }
                            else if (i == this.PTo016i - 4 & j == 3) {
                                if (this.PTm018n[this.PTo016i - 3][2] == this.PTv041z) {
                                    final float[] array49 = this.PTd019h[i];
                                    final int n69 = j;
                                    array49[n69] += 5.0f;
                                }
                            }
                            else if ((i == this.PTo016i - 4 & j == this.PTo016i - 4) && this.PTm018n[this.PTo016i - 3][this.PTo016i - 3] == this.PTv041z) {
                                final float[] array50 = this.PTd019h[i];
                                final int n70 = j;
                                array50[n70] += 5.0f;
                            }
                        }
                        else {
                            final float[] array51 = this.PTd019h[i];
                            final int n71 = j;
                            array51[n71] += 4.0f;
                            if (i == 3 && (j > 3 & j < this.PTo016i - 4) && (this.PTm018n[2][j - 1] == 0 & this.PTm018n[2][j] == 0 & this.PTm018n[2][j + 1] == 0)) {
                                if (this.PTm018n[2][j - 2] != this.PTv041z & this.PTm018n[2][j - 2] != 0) {
                                    final float[] array52 = this.PTd019h[i];
                                    final int n72 = j;
                                    ++array52[n72];
                                }
                                if (this.PTm018n[2][j + 2] != this.PTv041z & this.PTm018n[2][j + 2] != 0) {
                                    final float[] array53 = this.PTd019h[i];
                                    final int n73 = j;
                                    ++array53[n73];
                                }
                            }
                            if (i == this.PTo016i - 4 && (j > 3 & j < this.PTo016i - 4) && (this.PTm018n[this.PTo016i - 3][j - 1] == 0 & this.PTm018n[this.PTo016i - 3][j] == 0 & this.PTm018n[this.PTo016i - 3][j + 1] == 0)) {
                                if (this.PTm018n[this.PTo016i - 3][j - 2] != this.PTv041z & this.PTm018n[this.PTo016i - 3][j - 2] != 0) {
                                    final float[] array54 = this.PTd019h[i];
                                    final int n74 = j;
                                    ++array54[n74];
                                }
                                if (this.PTm018n[this.PTo016i - 3][j + 2] != this.PTv041z & this.PTm018n[this.PTo016i - 3][j + 2] != 0) {
                                    final float[] array55 = this.PTd019h[i];
                                    final int n75 = j;
                                    ++array55[n75];
                                }
                            }
                            if (j == 3 && (i > 3 & i < this.PTo016i - 4) && (this.PTm018n[i - 1][2] == 0 & this.PTm018n[i][2] == 0 & this.PTm018n[i + 1][2] == 0)) {
                                if (this.PTm018n[i - 2][2] != this.PTv041z & this.PTm018n[i - 2][2] != 0) {
                                    final float[] array56 = this.PTd019h[i];
                                    final int n76 = j;
                                    ++array56[n76];
                                }
                                if (this.PTm018n[i + 2][2] != this.PTv041z & this.PTm018n[i + 2][2] != 0) {
                                    final float[] array57 = this.PTd019h[i];
                                    final int n77 = j;
                                    ++array57[n77];
                                }
                            }
                            if (j == this.PTo016i - 4 && (i > 3 & i < this.PTo016i - 4) && (this.PTm018n[i - 1][this.PTo016i - 3] == 0 & this.PTm018n[i][this.PTo016i - 3] == 0 & this.PTm018n[i + 1][this.PTo016i - 3] == 0)) {
                                if (this.PTm018n[i - 2][this.PTo016i - 3] != this.PTv041z & this.PTm018n[i - 2][this.PTo016i - 3] != 0) {
                                    final float[] array58 = this.PTd019h[i];
                                    final int n78 = j;
                                    ++array58[n78];
                                }
                                if (this.PTm018n[i + 2][this.PTo016i - 3] != this.PTv041z & this.PTm018n[i + 2][this.PTo016i - 3] != 0) {
                                    final float[] array59 = this.PTd019h[i];
                                    final int n79 = j;
                                    ++array59[n79];
                                }
                            }
                        }
                    }
                    else if (i == 4 | i == this.PTo016i - 5 | j == 4 | j == this.PTo016i - 5) {
                        int n80 = 1;
                        this.PTx071q = this.PTv041z;
                        this.PTd072c = 1;
                        this.PTd068g = j;
                        this.PTv069u = j;
                        if (i == 4 | i == this.PTo016i - 5) {
                            for (int n81 = 0; n81 < this.PTo016i; ++n81) {
                                if (n81 != j & this.PTm018n[i][n81] == 0) {
                                    n80 = 0;
                                }
                            }
                        }
                        if (j == 4 | j == this.PTo016i - 5) {
                            for (int n82 = 0; n82 < this.PTo016i; ++n82) {
                                if (n82 != i & this.PTm018n[n82][j] == 0) {
                                    n80 = 0;
                                }
                            }
                        }
                        if ((i == 4 | i == this.PTo016i - 5) & (j == 4 | j == this.PTo016i - 5)) {
                            n80 = 2;
                        }
                        if (n80 == 1) {
                            final float[] array60 = this.PTd019h[i];
                            final int n83 = j;
                            array60[n83] += 13.0f;
                        }
                        else if (n80 == 2) {
                            final float[] array61 = this.PTd019h[i];
                            final int n84 = j;
                            array61[n84] += 14.0f;
                        }
                        else {
                            if (i == 4 | i == this.PTo016i - 5) {
                                for (int n85 = 0; n85 < this.PTo016i; ++n85) {
                                    this.PTf020d[n85] = this.PTm018n[i][n85];
                                }
                                this.PTd068g = j;
                                this.PTx071q = this.PTf020d[j];
                                this.PTv069u = j;
                                this.PTd072c = this.PTf020d[j];
                                this.PTm070k = j;
                            }
                            if (j == 4 | j == this.PTo016i - 5) {
                                for (int n86 = 0; n86 < this.PTo016i; ++n86) {
                                    this.PTf020d[n86] = this.PTm018n[n86][j];
                                }
                                this.PTd068g = i;
                                this.PTx071q = this.PTf020d[i];
                                this.PTv069u = i;
                                this.PTd072c = this.PTf020d[i];
                                this.PTm070k = i;
                            }
                            int n87 = 0;
                            for (int n88 = 0; n88 < this.PTo016i; ++n88) {
                                if (this.PTf020d[n88] == this.PTv041z) {
                                    ++n87;
                                }
                                array[n88] = 2 * this.PTn007b;
                            }
                            int pTv041z3 = this.PTv041z;
                            while (pTv041z3 != 0) {
                                if (pTv041z3 == this.PTv041z) {
                                    boolean b5 = false;
                                    for (int n89 = 0; n89 < this.PTo016i; ++n89) {
                                        if (this.PTf020d[n89] != array[n89]) {
                                            b5 = true;
                                        }
                                    }
                                    for (int n90 = 0; n90 < this.PTo016i; ++n90) {
                                        array[n90] = this.PTf020d[n90];
                                    }
                                    if (!b5) {
                                        pTv041z3 = 0;
                                    }
                                }
                                if (pTv041z3 != 0) {
                                    this.b(pTv041z3);
                                    if (++pTv041z3 <= this.PTn007b) {
                                        continue;
                                    }
                                    pTv041z3 -= this.PTn007b;
                                }
                            }
                            int n91 = 0;
                            for (int n92 = 0; n92 < this.PTo016i; ++n92) {
                                if (this.PTf020d[n92] == this.PTv041z) {
                                    ++n91;
                                }
                            }
                            final float[] array62 = this.PTd019h[i];
                            final int n93 = j;
                            array62[n93] += 10.0f * (n91 - n87) / this.PTo016i;
                            if (n91 <= n87) {
                                final float[] array63 = this.PTd019h[i];
                                final int n94 = j;
                                array63[n94] += 6.0f;
                            }
                            else if (this.PTd068g == this.PTm070k | this.PTv069u == this.PTm070k) {
                                final float[] array64 = this.PTd019h[i];
                                final int n95 = j;
                                array64[n95] += 14.0f;
                            }
                            else {
                                final float[] array65 = this.PTd019h[i];
                                final int n96 = j;
                                array65[n96] += 12.0f;
                                if ((this.PTd068g > 0 & this.PTd068g < this.PTo016i - 1) && (this.PTf020d[this.PTd068g - 1] == this.PTv041z & this.PTf020d[this.PTd068g + 1] == this.PTv041z)) {
                                    this.PTd019h[i][j] = 10.0f;
                                    for (int pTd068g3 = this.PTd068g - 1; pTd068g3 >= 0; --pTd068g3) {
                                        if (this.PTf020d[pTd068g3] == 0) {
                                            this.PTd068g = pTd068g3;
                                            pTd068g3 = -1;
                                        }
                                    }
                                    if (this.PTd068g == 0 | this.PTv069u == this.PTo016i - 1) {
                                        this.PTd019h[i][j] = 9.0f;
                                    }
                                }
                                if ((this.PTv069u > 0 & this.PTv069u < this.PTo016i - 1) && (this.PTf020d[this.PTv069u - 1] == this.PTv041z & this.PTf020d[this.PTv069u + 1] == this.PTv041z)) {
                                    if (this.PTd019h[i][j] > 10.0f) {
                                        this.PTd019h[i][j] = 10.0f;
                                    }
                                    for (int pTo016i5 = this.PTv069u + 1; pTo016i5 < this.PTo016i; ++pTo016i5) {
                                        if (this.PTf020d[pTo016i5] == 0) {
                                            this.PTv069u = pTo016i5;
                                            pTo016i5 = this.PTo016i;
                                        }
                                    }
                                    if (this.PTd068g == 0 | this.PTv069u == this.PTo016i - 1) {
                                        this.PTd019h[i][j] = 9.0f;
                                    }
                                }
                            }
                        }
                    }
                    else if (i == 5 | i == this.PTo016i - 6 | j == 5 | j == this.PTo016i - 6) {
                        final float[] array66 = this.PTd019h[i];
                        final int n97 = j;
                        array66[n97] += 8.0f;
                    }
                    if (this.PTd019h[i][j] < 1.0f) {
                        this.PTd019h[i][j] = 1.0f;
                    }
                }
                if (Math.abs(n - this.PTd019h[i][j]) <= 1.0E-4) {
                    if (this.PTf001l.nextFloat() < 0.5f) {
                        n = this.PTd019h[i][j];
                        pTo016i = i;
                        pTo016i2 = j;
                    }
                }
                else if (this.PTd019h[i][j] > n + 1.0E-4) {
                    n = this.PTd019h[i][j];
                    pTo016i = i;
                    pTo016i2 = j;
                }
            }
        }
        this.PTf042d = pTo016i;
        this.PTF043S = pTo016i2;
    }
    
    private final void d() {
        final int pTv041z = this.PTv041z;
        boolean b = false;
        for (int i = 0; i < this.PTo016i; ++i) {
            for (int j = 0; j < this.PTo016i; ++j) {
                float n = 0.0f;
                this.PTd019h[i][j] = 0.0f;
                if (this.PTm018n[i][j] == 0) {
                    int n2 = i - 1;
                    int n3 = j - 1;
                    if ((n2 >= 0 & n3 >= 0) && this.PTm018n[n2][n3] != pTv041z) {
                        while (n2 >= 0 & n3 >= 0) {
                            if (this.PTm018n[n2][n3] == pTv041z) {
                                b = true;
                                ++n;
                                n2 = -1;
                                n3 = -1;
                            }
                            else if (this.PTm018n[n2][n3] == 0) {
                                n2 = -1;
                                n3 = -1;
                            }
                            --n2;
                            --n3;
                        }
                    }
                    int n4 = i - 1;
                    int n5 = j;
                    if (n4 >= 0 && this.PTm018n[n4][n5] != pTv041z) {
                        while (n4 >= 0 & n5 >= 0) {
                            if (this.PTm018n[n4][n5] == pTv041z) {
                                b = true;
                                ++n;
                                n4 = -1;
                                n5 = -1;
                            }
                            else if (this.PTm018n[n4][n5] == 0) {
                                n4 = -1;
                                n5 = -1;
                            }
                            --n4;
                        }
                    }
                    int n6 = i - 1;
                    int n7 = j + 1;
                    if ((n6 >= 0 & n7 < this.PTo016i) && this.PTm018n[n6][n7] != pTv041z) {
                        while (n6 >= 0 & n7 < this.PTo016i) {
                            if (this.PTm018n[n6][n7] == pTv041z) {
                                b = true;
                                ++n;
                                n6 = -1;
                                n7 = this.PTo016i;
                            }
                            else if (this.PTm018n[n6][n7] == 0) {
                                n6 = -1;
                                n7 = this.PTo016i;
                            }
                            --n6;
                            ++n7;
                        }
                    }
                    int n8 = i;
                    int n9 = j + 1;
                    if (n9 < this.PTo016i && this.PTm018n[n8][n9] != pTv041z) {
                        while (n8 >= 0 & n9 < this.PTo016i) {
                            if (this.PTm018n[n8][n9] == pTv041z) {
                                b = true;
                                ++n;
                                n8 = -1;
                                n9 = this.PTo016i;
                            }
                            else if (this.PTm018n[n8][n9] == 0) {
                                n8 = -1;
                                n9 = this.PTo016i;
                            }
                            ++n9;
                        }
                    }
                    int n10 = i + 1;
                    int n11 = j + 1;
                    if ((n10 < this.PTo016i & n11 < this.PTo016i) && this.PTm018n[n10][n11] != pTv041z) {
                        while (n10 < this.PTo016i & n11 < this.PTo016i) {
                            if (this.PTm018n[n10][n11] == pTv041z) {
                                b = true;
                                ++n;
                                n10 = this.PTo016i;
                                n11 = this.PTo016i;
                            }
                            else if (this.PTm018n[n10][n11] == 0) {
                                n10 = this.PTo016i;
                                n11 = this.PTo016i;
                            }
                            ++n10;
                            ++n11;
                        }
                    }
                    int n12 = i + 1;
                    int n13 = j;
                    if (n12 < this.PTo016i && this.PTm018n[n12][n13] != pTv041z) {
                        while (n12 < this.PTo016i & n13 >= 0) {
                            if (this.PTm018n[n12][n13] == pTv041z) {
                                b = true;
                                ++n;
                                n12 = this.PTo016i;
                                n13 = -1;
                            }
                            else if (this.PTm018n[n12][n13] == 0) {
                                n12 = this.PTo016i;
                                n13 = -1;
                            }
                            ++n12;
                        }
                    }
                    int n14 = i + 1;
                    int n15 = j - 1;
                    if ((n14 < this.PTo016i & n15 >= 0) && this.PTm018n[n14][n15] != pTv041z) {
                        while (n14 < this.PTo016i & n15 >= 0) {
                            if (this.PTm018n[n14][n15] == pTv041z) {
                                b = true;
                                ++n;
                                n14 = this.PTo016i;
                                n15 = -1;
                            }
                            else if (this.PTm018n[n14][n15] == 0) {
                                n14 = this.PTo016i;
                                n15 = -1;
                            }
                            ++n14;
                            --n15;
                        }
                    }
                    int n16 = i;
                    int n17 = j - 1;
                    if (n17 >= 0 && this.PTm018n[n16][n17] != pTv041z) {
                        while (n16 >= 0 & n17 >= 0) {
                            if (this.PTm018n[n16][n17] == pTv041z) {
                                b = true;
                                ++n;
                                n16 = -1;
                                n17 = -1;
                            }
                            else if (this.PTm018n[n16][n17] == 0) {
                                n16 = -1;
                                n17 = -1;
                            }
                            --n17;
                        }
                    }
                }
                if (n > 0.9f) {
                    this.PTd019h[i][j] = 1.0f + n / this.PTo016i;
                }
            }
        }
        if (!b) {
            this.PTt009r[this.PTv041z] = 0;
            return;
        }
        if (b) {
            for (int k = 0; k <= this.PTn007b; ++k) {
                this.PTt009r[k] = 1;
            }
        }
    }
    
    private final void e() {
        final int n = 18;
        switch (this.PTm018n[this.PTv062e][this.PTT063F]) {
            case 1: {
                this.offscreen.setColor(Color.orange);
                this.offscreen.fillOval((int)(this.PTF064G + (this.PTv062e + 0.5) * this.PTj017k - n), (int)(this.PTc065v + (this.PTT063F + 0.5) * this.PTj017k - n), 2 * n, 2 * n);
                this.offscreen.setColor(Color.black);
                this.offscreen.fillOval((int)(this.PTF064G + (this.PTv062e + 0.5) * this.PTj017k - n + 2.0), (int)(this.PTc065v + (this.PTT063F + 0.5) * this.PTj017k - n + 2.0), 2 * (n - 2), 2 * (n - 2));
            }
            case 2: {
                this.offscreen.setColor(Color.magenta);
                this.offscreen.fillOval((int)(this.PTF064G + (this.PTv062e + 0.5) * this.PTj017k - n), (int)(this.PTc065v + (this.PTT063F + 0.5) * this.PTj017k - n), 2 * n, 2 * n);
                this.offscreen.setColor(Color.white);
                this.offscreen.fillOval((int)(this.PTF064G + (this.PTv062e + 0.5) * this.PTj017k - n + 2.0), (int)(this.PTc065v + (this.PTT063F + 0.5) * this.PTj017k - n + 2.0), 2 * (n - 2), 2 * (n - 2));
            }
            case 3: {
                this.offscreen.setColor(Color.cyan);
                this.offscreen.fillOval((int)(this.PTF064G + (this.PTv062e + 0.5) * this.PTj017k - n), (int)(this.PTc065v + (this.PTT063F + 0.5) * this.PTj017k - n), 2 * n, 2 * n);
                this.offscreen.setColor(Color.red);
                this.offscreen.fillOval((int)(this.PTF064G + (this.PTv062e + 0.5) * this.PTj017k - n + 2.0), (int)(this.PTc065v + (this.PTT063F + 0.5) * this.PTj017k - n + 2.0), 2 * (n - 2), 2 * (n - 2));
            }
            case 4: {
                this.offscreen.setColor(Color.yellow);
                this.offscreen.fillOval((int)(this.PTF064G + (this.PTv062e + 0.5) * this.PTj017k - n), (int)(this.PTc065v + (this.PTT063F + 0.5) * this.PTj017k - n), 2 * n, 2 * n);
                this.offscreen.setColor(Color.blue);
                this.offscreen.fillOval((int)(this.PTF064G + (this.PTv062e + 0.5) * this.PTj017k - n + 2.0), (int)(this.PTc065v + (this.PTT063F + 0.5) * this.PTj017k - n + 2.0), 2 * (n - 2), 2 * (n - 2));
            }
            default: {}
        }
    }
    
    private final void f() {
        final int n = 18;
        final int n2 = this.PTv052q % 20;
        int pTv041z;
        if (this.PTm018n[this.PTf042d][this.PTF043S] == 0) {
            pTv041z = this.PTv041z;
        }
        else {
            pTv041z = this.PTm018n[this.PTf042d][this.PTF043S];
        }
        switch (pTv041z) {
            case 1: {
                if (n2 <= 10) {
                    this.offscreen.setColor(Color.magenta);
                }
                else {
                    this.offscreen.setColor(Color.orange);
                }
                this.offscreen.fillOval((int)(this.PTF064G + (this.PTf042d + 0.5) * this.PTj017k - n), (int)(this.PTc065v + (this.PTF043S + 0.5) * this.PTj017k - n), 2 * n, 2 * n);
                this.offscreen.setColor(Color.black);
                this.offscreen.fillOval((int)(this.PTF064G + (this.PTf042d + 0.5) * this.PTj017k - n + 2.0), (int)(this.PTc065v + (this.PTF043S + 0.5) * this.PTj017k - n + 2.0), 2 * (n - 2), 2 * (n - 2));
                break;
            }
            case 2: {
                if (n2 <= 10) {
                    this.offscreen.setColor(Color.orange);
                }
                else {
                    this.offscreen.setColor(Color.magenta);
                }
                this.offscreen.fillOval((int)(this.PTF064G + (this.PTf042d + 0.5) * this.PTj017k - n), (int)(this.PTc065v + (this.PTF043S + 0.5) * this.PTj017k - n), 2 * n, 2 * n);
                this.offscreen.setColor(Color.white);
                this.offscreen.fillOval((int)(this.PTF064G + (this.PTf042d + 0.5) * this.PTj017k - n + 2.0), (int)(this.PTc065v + (this.PTF043S + 0.5) * this.PTj017k - n + 2.0), 2 * (n - 2), 2 * (n - 2));
                break;
            }
            case 3: {
                if (n2 <= 10) {
                    this.offscreen.setColor(Color.magenta);
                }
                else {
                    this.offscreen.setColor(Color.orange);
                }
                this.offscreen.fillOval((int)(this.PTF064G + (this.PTf042d + 0.5) * this.PTj017k - n), (int)(this.PTc065v + (this.PTF043S + 0.5) * this.PTj017k - n), 2 * n, 2 * n);
                this.offscreen.setColor(Color.red);
                this.offscreen.fillOval((int)(this.PTF064G + (this.PTf042d + 0.5) * this.PTj017k - n + 2.0), (int)(this.PTc065v + (this.PTF043S + 0.5) * this.PTj017k - n + 2.0), 2 * (n - 2), 2 * (n - 2));
                break;
            }
            case 4: {
                if (n2 <= 10) {
                    this.offscreen.setColor(Color.orange);
                }
                else {
                    this.offscreen.setColor(Color.magenta);
                }
                this.offscreen.fillOval((int)(this.PTF064G + (this.PTf042d + 0.5) * this.PTj017k - n), (int)(this.PTc065v + (this.PTF043S + 0.5) * this.PTj017k - n), 2 * n, 2 * n);
                this.offscreen.setColor(Color.blue);
                this.offscreen.fillOval((int)(this.PTF064G + (this.PTf042d + 0.5) * this.PTj017k - n + 2.0), (int)(this.PTc065v + (this.PTF043S + 0.5) * this.PTj017k - n + 2.0), 2 * (n - 2), 2 * (n - 2));
                break;
            }
        }
        if (n2 < 5) {
            this.offscreen.setColor(Color.green);
            this.offscreen.fillOval((int)(this.PTF064G + (this.PTf042d + 0.5) * this.PTj017k - n + 2.0), (int)(this.PTc065v + (this.PTF043S + 0.5) * this.PTj017k - n + 2.0), 2 * (n - 2), 2 * (n - 2));
        }
    }
    
    private final void g() {
        this.PTf042d = (this.PTj066g - this.PTF064G) / this.PTj017k;
        if (this.PTf042d < 0) {
            this.PTf042d = 0;
        }
        else if (this.PTf042d >= this.PTo016i) {
            this.PTf042d = this.PTo016i - 1;
        }
        this.PTF043S = (this.PTg067r - this.PTc065v) / this.PTj017k;
        if (this.PTF043S < 0) {
            this.PTF043S = 0;
        }
        else if (this.PTF043S >= this.PTo016i) {
            this.PTF043S = this.PTo016i - 1;
        }
        if (this.PTf042d != this.PTs044a | this.PTF043S != this.PTr045t) {
            this.PTJ046Y = 1;
        }
    }
    
    private final void h() {
        if (this.PTg008k[this.PTv041z] == 0 | this.PTt009r[this.PTv041z] == 0) {
            this.PTh048i = 1;
        }
        if (this.PTh048i == 1 & this.PTg049m > this.PTj047k) {
            if (this.PTt009r[this.PTv041z] == 1) {
                if (this.PTm018n[this.PTf042d][this.PTF043S] == 0) {
                    this.PTJ046Y = 0;
                    int n = this.PTf042d - 1;
                    int n2 = this.PTF043S - 1;
                    if ((n >= 0 & n2 >= 0) && this.PTm018n[n][n2] != this.PTv041z) {
                        while (n >= 0 & n2 >= 0) {
                            if (this.PTm018n[n][n2] == this.PTv041z) {
                                for (int i = n; i <= this.PTf042d; ++i) {
                                    this.PTm018n[i][n2 + (i - n)] = this.PTv041z;
                                    this.PTJ046Y = 1;
                                }
                                n = -1;
                                n2 = -1;
                            }
                            else if (this.PTm018n[n][n2] == 0) {
                                n = -1;
                                n2 = -1;
                            }
                            --n;
                            --n2;
                        }
                    }
                    int n3 = this.PTf042d - 1;
                    int ptf043S = this.PTF043S;
                    if (n3 >= 0 && this.PTm018n[n3][ptf043S] != this.PTv041z) {
                        while (n3 >= 0 & ptf043S >= 0) {
                            if (this.PTm018n[n3][ptf043S] == this.PTv041z) {
                                for (int j = n3; j <= this.PTf042d; ++j) {
                                    this.PTm018n[j][ptf043S] = this.PTv041z;
                                    this.PTJ046Y = 1;
                                }
                                n3 = -1;
                                ptf043S = -1;
                            }
                            else if (this.PTm018n[n3][ptf043S] == 0) {
                                n3 = -1;
                                ptf043S = -1;
                            }
                            --n3;
                        }
                    }
                    int n4 = this.PTf042d - 1;
                    int n5 = this.PTF043S + 1;
                    if ((n4 >= 0 & n5 < this.PTo016i) && this.PTm018n[n4][n5] != this.PTv041z) {
                        while (n4 >= 0 & n5 < this.PTo016i) {
                            if (this.PTm018n[n4][n5] == this.PTv041z) {
                                for (int k = n4; k <= this.PTf042d; ++k) {
                                    this.PTm018n[k][n5 - (k - n4)] = this.PTv041z;
                                    this.PTJ046Y = 1;
                                }
                                n4 = -1;
                                n5 = this.PTo016i;
                            }
                            else if (this.PTm018n[n4][n5] == 0) {
                                n4 = -1;
                                n5 = this.PTo016i;
                            }
                            --n4;
                            ++n5;
                        }
                    }
                    int pTf042d = this.PTf042d;
                    int n6 = this.PTF043S + 1;
                    if (n6 < this.PTo016i && this.PTm018n[pTf042d][n6] != this.PTv041z) {
                        while (pTf042d >= 0 & n6 < this.PTo016i) {
                            if (this.PTm018n[pTf042d][n6] == this.PTv041z) {
                                for (int l = n6; l >= this.PTF043S; --l) {
                                    this.PTm018n[pTf042d][l] = this.PTv041z;
                                    this.PTJ046Y = 1;
                                }
                                pTf042d = -1;
                                n6 = this.PTo016i;
                            }
                            else if (this.PTm018n[pTf042d][n6] == 0) {
                                pTf042d = -1;
                                n6 = this.PTo016i;
                            }
                            ++n6;
                        }
                    }
                    int n7 = this.PTf042d + 1;
                    int n8 = this.PTF043S + 1;
                    if ((n7 < this.PTo016i & n8 < this.PTo016i) && this.PTm018n[n7][n8] != this.PTv041z) {
                        while (n7 < this.PTo016i & n8 < this.PTo016i) {
                            if (this.PTm018n[n7][n8] == this.PTv041z) {
                                for (int n9 = n7; n9 >= this.PTf042d; --n9) {
                                    this.PTm018n[n9][n8 + n9 - n7] = this.PTv041z;
                                    this.PTJ046Y = 1;
                                }
                                n7 = this.PTo016i;
                                n8 = this.PTo016i;
                            }
                            else if (this.PTm018n[n7][n8] == 0) {
                                n7 = this.PTo016i;
                                n8 = this.PTo016i;
                            }
                            ++n7;
                            ++n8;
                        }
                    }
                    int n10 = this.PTf042d + 1;
                    int ptf043S2 = this.PTF043S;
                    if (n10 < this.PTo016i && this.PTm018n[n10][ptf043S2] != this.PTv041z) {
                        while (n10 < this.PTo016i & ptf043S2 >= 0) {
                            if (this.PTm018n[n10][ptf043S2] == this.PTv041z) {
                                for (int n11 = n10; n11 >= this.PTf042d; --n11) {
                                    this.PTm018n[n11][ptf043S2] = this.PTv041z;
                                    this.PTJ046Y = 1;
                                }
                                n10 = this.PTo016i;
                                ptf043S2 = -1;
                            }
                            else if (this.PTm018n[n10][ptf043S2] == 0) {
                                n10 = this.PTo016i;
                                ptf043S2 = -1;
                            }
                            ++n10;
                        }
                    }
                    int n12 = this.PTf042d + 1;
                    int n13 = this.PTF043S - 1;
                    if ((n12 < this.PTo016i & n13 >= 0) && this.PTm018n[n12][n13] != this.PTv041z) {
                        while (n12 < this.PTo016i & n13 >= 0) {
                            if (this.PTm018n[n12][n13] == this.PTv041z) {
                                for (int n14 = n12; n14 >= this.PTf042d; --n14) {
                                    this.PTm018n[n14][n13 + (n12 - n14)] = this.PTv041z;
                                    this.PTJ046Y = 1;
                                }
                                n12 = this.PTo016i;
                                n13 = -1;
                            }
                            else if (this.PTm018n[n12][n13] == 0) {
                                n12 = this.PTo016i;
                                n13 = -1;
                            }
                            ++n12;
                            --n13;
                        }
                    }
                    int pTf042d2 = this.PTf042d;
                    int n15 = this.PTF043S - 1;
                    if (n15 >= 0 && this.PTm018n[pTf042d2][n15] != this.PTv041z) {
                        while (pTf042d2 >= 0 & n15 >= 0) {
                            if (this.PTm018n[pTf042d2][n15] == this.PTv041z) {
                                for (int n16 = n15; n16 <= this.PTF043S; ++n16) {
                                    this.PTm018n[pTf042d2][n16] = this.PTv041z;
                                    this.PTJ046Y = 1;
                                }
                                pTf042d2 = -1;
                                n15 = -1;
                            }
                            else if (this.PTm018n[pTf042d2][n15] == 0) {
                                pTf042d2 = -1;
                                n15 = -1;
                            }
                            --n15;
                        }
                    }
                    if (this.PTJ046Y == 0) {
                        this.PTs044a = this.PTf042d;
                        this.PTr045t = this.PTF043S;
                    }
                    else if (this.PTJ046Y == 1) {
                        ++this.PTv041z;
                        if (this.PTv041z > this.PTn007b) {
                            this.PTv041z -= this.PTn007b;
                        }
                    }
                }
                else {
                    this.PTJ046Y = 0;
                    this.PTs044a = this.PTf042d;
                    this.PTr045t = this.PTF043S;
                }
                if (this.PTJ046Y == 0) {
                    this.PTb034v = 1;
                    this.j();
                }
            }
            else if (this.PTt009r[this.PTv041z] == 0) {
                boolean b = false;
                for (int n17 = 1; n17 <= this.PTn007b; ++n17) {
                    if (this.PTt009r[n17] == 1) {
                        b = true;
                    }
                }
                if (!b) {
                    this.PTj053h = -1;
                    this.PTf050i = this.PTg049m;
                    for (int pTf014b = 0; pTf014b < this.PTf014b; ++pTf014b) {
                        if (this.PTd015z[0][pTf014b] == 0) {
                            this.PTd015z[0][pTf014b] = this.PTn007b;
                            this.PTz051a = pTf014b;
                            pTf014b = this.PTf014b;
                        }
                    }
                }
                else if (b) {
                    ++this.PTv041z;
                    if (this.PTv041z > this.PTn007b) {
                        this.PTv041z -= this.PTn007b;
                    }
                }
                this.PTj047k = this.PTg049m + this.PTf010s;
            }
            if (this.PTg008k[this.PTv041z] == 0) {
                this.PTj047k = this.PTg049m + this.PTf010s;
            }
        }
        this.PTh048i = 0;
    }
    
    private final void i() {
        this.PTF064G = (this.PTf012s - this.PTo016i * this.PTj017k) / 2;
        this.PTc065v = (this.PTx011q - this.PTo016i * this.PTj017k) / 2;
        this.offscreen.setColor(this.color2);
        this.offscreen.fillRect(this.PTF064G, this.PTc065v, this.PTo016i * this.PTj017k, this.PTo016i * this.PTj017k);
        this.offscreen.setColor(Color.blue);
        for (int i = 0; i <= this.PTo016i; ++i) {
            this.offscreen.drawLine(this.PTF064G + i * this.PTj017k, this.PTc065v, this.PTF064G + i * this.PTj017k, this.PTc065v + this.PTo016i * this.PTj017k);
        }
        for (int j = 0; j <= this.PTo016i; ++j) {
            this.offscreen.drawLine(this.PTF064G, this.PTc065v + j * this.PTj017k, this.PTF064G + this.PTo016i * this.PTj017k, this.PTc065v + j * this.PTj017k);
        }
    }
    
    private final void j() {
        this.PTw003q = this.PTg049m - this.PTb037u;
        this.PTr033f = Runtime.getRuntime().freeMemory();
        if (this.PTw003q >= this.PTc013z & this.PTr033f > 10000L) {
            switch (this.PTb034v) {
                case 0: {
                    if (this.PTv030y != null) {
                        this.PTv030y.play();
                        this.PTp039o = this.PTs004e;
                        this.PTb037u = this.PTg049m;
                        return;
                    }
                    break;
                }
                case 1: {
                    if (this.PTa031q != null) {
                        this.PTa031q.play();
                        this.PTb037u = this.PTg049m;
                        return;
                    }
                    break;
                }
            }
        }
    }
    
    private final void k() {
        this.PTb037u = 0.0f;
        this.PTw003q = 100.0f;
        this.PTj053h = 0;
        this.PTf060b = 0;
        for (int i = 0; i < this.PTo016i; ++i) {
            for (int j = 0; j < this.PTo016i; ++j) {
                this.PTm018n[i][j] = 0;
            }
        }
        for (int k = 0; k <= this.PTn007b; ++k) {
            this.PTt009r[k] = 1;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.PTj053h > 0) {
            this.PTh048i = 1;
        }
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int pTj066g, final int pTg067r) {
        this.PTj066g = pTj066g;
        this.PTg067r = pTg067r;
        return true;
    }
    
    public final boolean keyDown(final Event event, final int n) {
        Label_0183: {
            switch (n) {
                case 1008: {
                    if (this.PTj053h == 0) {
                        this.PTf060b = 1;
                    }
                    break Label_0183;
                }
                case 1009: {
                    if (this.PTj053h == 0) {
                        this.PTf060b = 0;
                    }
                    break Label_0183;
                }
                case 1004: {
                    if (!(this.PTj053h == 0 & this.PTf060b == 1)) {
                        break Label_0183;
                    }
                    ++this.PTn007b;
                    if (this.PTn007b > 4) {
                        this.PTn007b = 4;
                    }
                    break Label_0183;
                }
                case 1005: {
                    if (!(this.PTj053h == 0 & this.PTf060b == 1)) {
                        break Label_0183;
                    }
                    --this.PTn007b;
                    if (this.PTn007b < 2) {
                        this.PTn007b = 2;
                    }
                    break Label_0183;
                }
                default: {
                    final char c = (char)n;
                }
                case 1006:
                case 1007: {
                    final char c2 = (char)n;
                    if (this.PTj053h < 0) {
                        final String s = "kK ";
                        final String s2 = "kK ";
                        final String string = "kK" + c2;
                        if ((string.equals(s) | string.equals(s2)) && this.PTg049m - this.PTf050i > 50.0f * this.PTc013z) {
                            this.PTj053h = 0;
                            this.k();
                        }
                    }
                    else if (this.PTj053h == 0) {
                        final String value = String.valueOf(c2);
                        if (value.equals(" ")) {
                            this.PTj053h = 1;
                            this.PTv041z = 1;
                        }
                        if (this.PTf060b == 1) {
                            if (value.equals("1")) {
                                if (this.PTg008k[1] == 0) {
                                    this.PTg008k[1] = 1;
                                }
                                else if (this.PTg008k[1] == 1) {
                                    this.PTg008k[1] = 0;
                                }
                            }
                            else if (value.equals("2")) {
                                if (this.PTg008k[2] == 0) {
                                    this.PTg008k[2] = 1;
                                }
                                else if (this.PTg008k[2] == 1) {
                                    this.PTg008k[2] = 0;
                                }
                            }
                            else if (value.equals("3")) {
                                if (this.PTg008k[3] == 0) {
                                    this.PTg008k[3] = 1;
                                }
                                else if (this.PTg008k[3] == 1) {
                                    this.PTg008k[3] = 0;
                                }
                            }
                            else if (value.equals("4")) {
                                if (this.PTg008k[4] == 0) {
                                    this.PTg008k[4] = 1;
                                }
                                else if (this.PTg008k[4] == 1) {
                                    this.PTg008k[4] = 0;
                                }
                            }
                        }
                    }
                    else if (this.PTj053h > 0) {
                        final String string2 = "kK" + c2;
                        string2.equals("kK ");
                        string2.equals("kKy");
                        string2.equals("kKY");
                    }
                    return true;
                }
            }
        }
    }
    
    public final void l(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public final void run() {
        this.PTn040v = new Date();
        this.PTg035f = this.PTn040v.getSeconds();
        this.PTn040v = null;
        this.PTs036w = this.PTg035f;
        this.PTz038x = 0.0f;
        this.setBackground(Color.gray);
        while (this.runner == Thread.currentThread()) {
            this.PTg049m += this.PTc013z;
            this.PTv052q = (int)(this.PTg049m / this.PTc013z);
            if (this.PTj053h == 0) {
                for (int i = 0; i < this.PTo016i; ++i) {
                    for (int j = 0; j < this.PTo016i; ++j) {
                        this.PTm018n[i][j] = 0;
                    }
                }
                if (this.PTn007b == 2) {
                    if (this.PTf001l.nextFloat() < 0.5f) {
                        this.PTm018n[5][5] = 1;
                        this.PTm018n[6][5] = 2;
                        this.PTm018n[5][6] = 2;
                        this.PTm018n[6][6] = 1;
                    }
                    else {
                        this.PTm018n[5][5] = 2;
                        this.PTm018n[6][5] = 1;
                        this.PTm018n[5][6] = 1;
                        this.PTm018n[6][6] = 2;
                    }
                }
                else if (this.PTn007b == 3) {
                    int n = 0;
                    int n2 = 0;
                    int n3 = 0;
                    while (n < 3 | n2 < 3 | n3 < 3) {
                        for (int k = 4; k <= 7; ++k) {
                            for (int l = 4; l <= 7; ++l) {
                                if (this.PTm018n[k][l] == 0) {
                                    switch ((int)(10.0f * this.PTf001l.nextFloat())) {
                                        case 1: {
                                            if (n < 3) {
                                                this.PTm018n[k][l] = 1;
                                                ++n;
                                                break;
                                            }
                                            break;
                                        }
                                        case 2: {
                                            if (n2 < 3) {
                                                this.PTm018n[k][l] = 2;
                                                ++n2;
                                                break;
                                            }
                                            break;
                                        }
                                        case 3: {
                                            if (n3 < 3) {
                                                this.PTm018n[k][l] = 3;
                                                ++n3;
                                                break;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else if (this.PTn007b == 4) {
                    int n4 = 0;
                    int n5 = 0;
                    int n6 = 0;
                    int n7 = 0;
                    while (n4 < 4 | n5 < 4 | n6 < 4 | n7 < 4) {
                        for (int n8 = 4; n8 <= 7; ++n8) {
                            for (int n9 = 4; n9 <= 7; ++n9) {
                                if (this.PTm018n[n8][n9] == 0) {
                                    switch ((int)(10.0f * this.PTf001l.nextFloat())) {
                                        case 1: {
                                            if (n4 < 4) {
                                                this.PTm018n[n8][n9] = 1;
                                                ++n4;
                                                break;
                                            }
                                            break;
                                        }
                                        case 2: {
                                            if (n5 < 4) {
                                                this.PTm018n[n8][n9] = 2;
                                                ++n5;
                                                break;
                                            }
                                            break;
                                        }
                                        case 3: {
                                            if (n6 < 4) {
                                                this.PTm018n[n8][n9] = 3;
                                                ++n6;
                                                break;
                                            }
                                            break;
                                        }
                                        case 4: {
                                            if (n7 < 4) {
                                                this.PTm018n[n8][n9] = 4;
                                                ++n7;
                                                break;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                this.PTj047k = this.PTg049m + this.PTf010s;
            }
            else if (this.PTj053h > 0) {
                this.d();
                if (this.PTj053h > 0) {
                    if (this.PTg008k[this.PTv041z] == 0) {
                        this.c();
                    }
                    else if (this.PTg008k[this.PTv041z] == 1) {
                        this.g();
                    }
                    this.h();
                }
            }
            this.repaint();
            this.PTp039o -= this.PTc013z;
            if (this.PTp039o < 0.0f) {
                this.PTb034v = 0;
                this.j();
            }
            this.PTr033f = Runtime.getRuntime().freeMemory();
            this.l((int)(1000.0f * this.PTc013z));
            this.PTz038x += this.PTc013z;
            this.PTn040v = new Date();
            this.PTg035f = this.PTn040v.getSeconds();
            this.PTn040v = null;
            if (this.PTg035f == this.PTs036w & this.PTz038x > 10.0f) {
                this.PTs004e += this.PTz038x / 60.0f;
                this.PTs004e /= 2.0f;
                this.PTz038x = 0.0f;
            }
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner = null;
        }
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void init() {
        this.offscreenImg = this.createImage(this.size().width, this.size().height);
        this.offscreen = this.offscreenImg.getGraphics();
        this.PTv030y = this.getAudioClip(this.getCodeBase(), "sl.au");
        this.PTa031q = this.getAudioClip(this.getCodeBase(), "d.au");
        this.PTg049m = 0.0f;
        this.PTn007b = 2;
        this.PTb037u = 0.0f;
        this.PTw003q = 100.0f;
        this.PTj053h = 0;
        this.PTf060b = 0;
        for (int i = 0; i < this.PTo016i; ++i) {
            for (int j = 0; j < this.PTo016i; ++j) {
                this.PTm018n[i][j] = 0;
            }
        }
        for (int k = 0; k <= this.PTn007b; ++k) {
            this.PTt009r[k] = 1;
        }
        this.PTg008k[1] = 0;
        this.PTg008k[2] = 1;
        this.PTg008k[3] = 0;
        this.PTg008k[4] = 1;
        for (int l = 0; l <= this.PTn007b; ++l) {
            for (int n = 0; n <= this.PTf014b; ++n) {
                this.PTd015z[l][n] = 0;
            }
        }
        this.requestFocus();
    }
}
