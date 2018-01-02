import java.awt.image.ImageObserver;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class Speelveld extends Panel implements KeyListener
{
    int score;
    int rij;
    int kol;
    int x;
    int y;
    int holdy;
    int kleur;
    int hoogte;
    SpeelVeldDef veld;
    SpeelVeldDef veldkleur;
    boolean beneden;
    boolean gameover;
    boolean vallen;
    boolean pause;
    Figuur blokje;
    int bb;
    int bb1;
    int ytop;
    int xxx;
    int b3d;
    Timer timer;
    Image picture;
    Image cache;
    Image gameOverImage;
    Image gameOnImage;
    Graphics graphics;
    static boolean printDebug;
    int kleurRijCount;
    boolean kleurRij;
    int kleurRijRij;
    int kleurRijKleur;
    
    public Speelveld(final Image gameOnImage, final Image gameOverImage) {
        this.veld = new SpeelVeldDef();
        this.veldkleur = new SpeelVeldDef();
        this.pause = false;
        this.blokje = new Figuur();
        this.bb = 18;
        this.bb1 = 1;
        this.ytop = 28;
        this.xxx = 10;
        this.b3d = 10;
        this.kleurRij = false;
        this.kleurRijRij = -1;
        this.kleurRijKleur = -1;
        this.gameOnImage = gameOnImage;
        this.gameOverImage = gameOverImage;
        this.addKeyListener(this);
    }
    
    public void init() {
    }
    
    public static void debug(final String s) {
        if (Speelveld.printDebug) {
            System.out.println(s);
        }
    }
    
    public Color getColor(final int n) {
        if (n == 0) {
            return Color.cyan;
        }
        if (n == 1) {
            return Color.pink;
        }
        if (n == 2) {
            return Color.magenta;
        }
        if (n == 3) {
            return Color.green;
        }
        if (n == 4) {
            return Color.yellow;
        }
        if (n == 5) {
            return Color.lightGray;
        }
        if (n == 6) {
            return Color.orange;
        }
        if (n == 7) {
            return Color.white;
        }
        if (n == 8) {
            return Color.blue;
        }
        if (n == 9) {
            return Color.red;
        }
        if (n == 10) {
            return Color.gray;
        }
        return Color.white;
    }
    
    public void setcolor(final int n) {
        this.graphics.setColor(this.getColor(n));
    }
    
    public void bar(final int n, final int n2, final int n3, final int n4) {
        final Color color = this.graphics.getColor();
        this.graphics.setColor(color);
        this.graphics.fill3DRect(n, n2, n3 - n, n4 - n2, true);
        this.graphics.setColor(color.brighter());
        this.graphics.fillPolygon(new int[] { n, n + this.b3d, n3 + this.b3d, n3 }, new int[] { n2, n2 - this.b3d, n2 - this.b3d, n2 }, 4);
        this.graphics.setColor(color.darker());
        this.graphics.fillPolygon(new int[] { n3, n3 + this.b3d, n3 + this.b3d, n3 }, new int[] { n2, n2 - this.b3d, n4 - this.b3d, n4 }, 4);
        this.graphics.setColor(color);
    }
    
    public void bar(int n, int n2, final int n3, final boolean b, final boolean b2, final boolean b3) {
        n += this.bb1;
        n2 += this.bb1;
        final int n4 = n + this.bb - 2 * this.bb1;
        final int n5 = n2 + this.bb - 2 * this.bb1;
        final Color color = this.graphics.getColor();
        this.graphics.setColor(color);
        this.graphics.fill3DRect(n, n2, n4 - n, n5 - n2, true);
        this.graphics.setColor(color.brighter());
        if (b3 && b2) {
            this.graphics.fillPolygon(new int[] { n, n + 2 * this.bb1, n4, n4 + this.b3d - 2 * this.bb1, n4 + 2 * this.bb1, n4 + 2 * this.bb1, n4 }, new int[] { n2, n2 - 2 * this.bb1, n2 - 2 * this.bb1, n2 - this.b3d, n2 - this.b3d, n2 - 2 * this.bb1, n2 }, 7);
        }
        else if (b3) {
            this.graphics.fillPolygon(new int[] { n, n + 2 * this.bb1, n4, n4 + this.b3d - 2 * this.bb1, n4 + this.b3d, n4 }, new int[] { n2, n2 - 2 * this.bb1, n2 - 2 * this.bb1, n2 - this.b3d, n2 - this.b3d, n2 }, 6);
        }
        else if (b2) {
            this.graphics.fillPolygon(new int[] { n, n + this.b3d, n4 + 2 * this.bb1, n4 + 2 * this.bb1, n4 }, new int[] { n2, n2 - this.b3d, n2 - this.b3d, n2 - 2 * this.bb1, n2 }, 5);
        }
        else {
            this.graphics.fillPolygon(new int[] { n, n + this.b3d, n4 + this.b3d, n4 }, new int[] { n2, n2 - this.b3d, n2 - this.b3d, n2 }, 4);
        }
        this.graphics.setColor(color.darker());
        if (b && b2) {
            this.graphics.fillPolygon(new int[] { n4, n4 + 2 * this.bb1, n4 + 4 * this.bb1, n4 + 2 * this.bb1, n4 + 2 * this.bb1, n4 }, new int[] { n2, n2 - 2 * this.bb1, n2 - 2 * this.bb1, n2, n5 - 2 * this.bb1, n5 }, 6);
        }
        else if (b) {
            this.graphics.fillPolygon(new int[] { n4, n4 + this.b3d, n4 + this.b3d, n4 + 2 * this.bb1, n4 + 2 * this.bb1, n4 }, new int[] { n2, n2 - this.b3d, n2 - this.b3d + 2 * this.bb1, n2, n5 - 2 * this.bb1, n5 }, 6);
        }
        else if (b2) {
            this.graphics.fillPolygon(new int[] { n4, n4 + 2 * this.bb1, n4 + this.b3d, n4 + this.b3d, n4 }, new int[] { n2, n2 - 2 * this.bb1, n2 - 2 * this.bb1, n5 - this.b3d, n5 }, 5);
        }
        else {
            this.graphics.fillPolygon(new int[] { n4, n4 + this.b3d, n4 + this.b3d, n4 }, new int[] { n2, n2 - this.b3d, n5 - this.b3d, n5 }, 4);
        }
        this.graphics.setColor(color);
    }
    
    public void outtextxy(final int n, final int n2, final String s) {
        this.graphics.drawString(s, n, n2);
    }
    
    public void line(final int n, final int n2, final int n3, final int n4) {
        this.graphics.drawLine(n, n2, n3, n4);
    }
    
    public void maakspeelveldleeg() {
        for (int i = 0; i <= 14; ++i) {
            for (int j = -2; j <= 24; ++j) {
                this.veld.setVeld(j, i, 0);
            }
        }
        for (int k = 0; k <= 14; ++k) {
            this.veld.setVeld(25, k, 1);
        }
    }
    
    public void tekenspeelveldlinks() {
        if (this.picture != null) {
            this.graphics.setColor(Color.white);
            this.graphics.fillRect(0, 0, 2 * this.xxx + 15 * this.bb + this.b3d, this.ytop + this.b3d);
            this.graphics.setColor(Color.darkGray);
            this.graphics.fillRect(this.xxx + this.b3d, this.ytop - this.b3d, 15 * this.bb, 24 * this.bb);
            this.graphics.setColor(Color.black);
            for (int i = 1; i < 15; ++i) {
                final int n = this.xxx + this.b3d + i * this.bb;
                this.graphics.drawLine(n, this.ytop - this.b3d, n, this.ytop - this.b3d + 24 * this.bb);
            }
        }
        this.setcolor(6);
        this.bar(0, this.ytop, this.xxx, this.ytop + 24 * this.bb + this.xxx);
    }
    
    public void tekenspeelveldonder() {
        this.setcolor(6);
        this.bar(this.xxx, this.ytop + 24 * this.bb, this.xxx + 15 * this.bb, this.ytop + 24 * this.bb + this.xxx);
    }
    
    public void tekenspeelveldrechts() {
        this.graphics.setColor(Color.white);
        this.graphics.fillRect(2 * this.xxx + 15 * this.bb, 0, 2 * this.xxx + 15 * this.bb + this.b3d, this.ytop + this.b3d);
        this.setcolor(6);
        this.bar(this.xxx + 15 * this.bb, this.ytop, 2 * this.xxx + 15 * this.bb, this.ytop + 24 * this.bb + this.xxx);
    }
    
    public double random() {
        debug("random : " + Math.random());
        return Math.random();
    }
    
    public void kiesblokje() {
        this.kleur = (int)Math.round(10.0 * this.random());
        debug("kleur : " + this.kleur);
        this.timer.setInterval(10 * Math.round(90.0f - (float)(57.297469361769856 * (float)Math.atan(this.score / 1500.0f))));
        final int n = (int)Math.round(14.0 * this.random()) + 2;
        debug("blokje : " + n);
        for (int i = 1; i <= this.rij; ++i) {
            for (int j = 1; j <= this.kol; ++j) {
                this.blokje.figuur[i][j] = 0;
            }
        }
        if (n == 2 || n == 3) {
            this.rij = 2;
            this.kol = 3;
            this.x = 6;
            this.y = 0;
            this.blokje.figuur[2][1] = 1;
            this.blokje.figuur[2][2] = 1;
            this.blokje.figuur[2][3] = 1;
            this.blokje.figuur[1][2] = 1;
        }
        if (n == 4 || n == 5) {
            this.rij = 2;
            this.kol = 2;
            this.x = 7;
            this.y = 0;
            this.blokje.figuur[1][1] = 1;
            this.blokje.figuur[1][2] = 1;
            this.blokje.figuur[2][1] = 1;
            this.blokje.figuur[2][2] = 1;
        }
        if (n == 6 || n == 7) {
            this.rij = 3;
            this.kol = 2;
            this.x = 7;
            this.y = 0;
            this.blokje.figuur[1][2] = 1;
            this.blokje.figuur[2][2] = 1;
            this.blokje.figuur[2][1] = 1;
            this.blokje.figuur[3][1] = 1;
        }
        if (n == 8 || n == 9) {
            this.rij = 3;
            this.kol = 2;
            this.x = 7;
            this.y = 0;
            this.blokje.figuur[1][1] = 1;
            this.blokje.figuur[1][2] = 1;
            this.blokje.figuur[2][1] = 1;
            this.blokje.figuur[3][1] = 1;
        }
        if (n == 10) {
            this.rij = 3;
            this.kol = 3;
            this.x = 7;
            this.y = 0;
            this.blokje.figuur[1][2] = 1;
            this.blokje.figuur[2][1] = 1;
            this.blokje.figuur[2][2] = 1;
            this.blokje.figuur[2][3] = 1;
            this.blokje.figuur[3][1] = 1;
            this.blokje.figuur[3][3] = 1;
        }
        if (n == 11) {
            this.rij = 3;
            this.kol = 3;
            this.x = 7;
            this.y = 0;
            this.blokje.figuur[1][3] = 1;
            this.blokje.figuur[2][1] = 1;
            this.blokje.figuur[2][2] = 1;
            this.blokje.figuur[2][3] = 1;
            this.blokje.figuur[3][1] = 1;
            this.blokje.figuur[3][3] = 1;
        }
        if (n == 12) {
            this.rij = 3;
            this.kol = 1;
            this.x = 7;
            this.y = 0;
            this.blokje.figuur[1][1] = 1;
            this.blokje.figuur[2][1] = 1;
            this.blokje.figuur[3][1] = 1;
        }
        if (n == 13) {
            this.rij = 2;
            this.kol = 3;
            this.x = 7;
            this.y = 0;
            this.blokje.figuur[1][1] = 1;
            this.blokje.figuur[1][2] = 1;
            this.blokje.figuur[1][3] = 1;
            this.blokje.figuur[2][1] = 1;
            this.blokje.figuur[2][3] = 1;
        }
        if (n == 14) {
            this.rij = 3;
            this.kol = 3;
            this.x = 7;
            this.y = 0;
            this.blokje.figuur[1][2] = 1;
            this.blokje.figuur[2][1] = 1;
            this.blokje.figuur[2][2] = 1;
            this.blokje.figuur[2][3] = 1;
            this.blokje.figuur[3][1] = 1;
            this.blokje.figuur[3][2] = 1;
        }
        if (n == 15) {
            this.rij = 3;
            this.kol = 3;
            this.x = 7;
            this.y = 0;
            this.blokje.figuur[1][1] = 1;
            this.blokje.figuur[1][2] = 1;
            this.blokje.figuur[2][2] = 1;
            this.blokje.figuur[2][3] = 1;
            this.blokje.figuur[3][2] = 1;
            this.blokje.figuur[3][3] = 1;
        }
        if (n == 16) {
            this.kol = 3;
            this.rij = 2;
            this.x = 7;
            this.y = 0;
            this.blokje.figuur[1][1] = 1;
            this.blokje.figuur[1][2] = 1;
            this.blokje.figuur[1][3] = 1;
            this.blokje.figuur[2][2] = 1;
            this.blokje.figuur[2][3] = 1;
        }
    }
    
    public void showscore() {
        this.setcolor(7);
        this.outtextxy(this.xxx + this.b3d + 2, this.ytop + this.bb, String.valueOf(this.score));
    }
    
    public void printrij(final int n, final int n2, final boolean b) {
        final int n3 = this.y - n + 1;
        final int n4 = this.ytop + (n - 1) * this.bb;
        for (int i = 0; i <= 14; ++i) {
            final int n5 = this.xxx + i * this.bb;
            if (!b) {
                final int n6 = i - this.x + 1;
                if (n6 >= 1 && n6 <= this.kol && n3 >= 1 && n3 <= this.rij && !this.kleurRij && this.blokje.figuur[n3][n6] == 1) {
                    this.setcolor(this.kleur);
                    this.bar(n5, n4, this.kleur, this.veld.getVeld(n, i + 1) == 1, this.veld.getVeld(n - 1, i + 1) == 1, this.veld.getVeld(n - 1, i) == 1);
                }
            }
            if (n2 < 0) {
                this.setcolor(-n2);
                this.bar(n5, n4, -n2, false, false, false);
            }
            else if (this.veld.getVeld(n, i) == 1 && b) {
                this.setcolor(this.veldkleur.getVeld(n, i));
                this.bar(n5, n4, this.veldkleur.getVeld(n, i), false, false, false);
            }
        }
    }
    
    public synchronized void roteer() {
        if (this.kol != 1) {
            final Figuur figuur = new Figuur();
            int n;
            if (this.rij > this.kol) {
                n = this.rij;
            }
            else {
                n = this.kol;
            }
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n; ++j) {
                    figuur.figuur[i][j] = this.blokje.figuur[i][j];
                }
            }
            for (int k = 1; k <= n; ++k) {
                for (int l = 1; l <= n; ++l) {
                    this.blokje.figuur[k][l] = figuur.figuur[l][this.kol - k + 1];
                }
            }
            final int rij = this.rij;
            this.rij = this.kol;
            this.kol = rij;
            int x = this.x;
            if (this.x <= 0) {
                x = 0;
            }
            if (this.x > 15 - this.kol) {
                x = 15 - this.kol;
            }
            boolean b = true;
            for (int n2 = 0; n2 <= this.rij - 1; ++n2) {
                for (int n3 = 0; n3 <= this.kol - 1; ++n3) {
                    if (this.blokje.figuur[n2 + 1][n3 + 1] == 1 && this.veld.getVeld(this.y - n2 + 1, x + n3) == 1) {
                        b = false;
                    }
                }
            }
            if (!b) {
                for (int n4 = 1; n4 <= n; ++n4) {
                    for (int n5 = 1; n5 <= n; ++n5) {
                        this.blokje.figuur[n4][n5] = figuur.figuur[n4][n5];
                    }
                }
                this.kol = this.rij;
                this.rij = rij;
            }
            else {
                this.x = x;
            }
            this.repaint();
        }
    }
    
    public synchronized void roteerhorz() {
        final Figuur figuur = new Figuur();
        for (int i = 1; i <= this.rij; ++i) {
            for (int j = 1; j <= this.kol; ++j) {
                figuur.figuur[i][j] = this.blokje.figuur[i][j];
            }
        }
        for (int k = 1; k <= this.rij; ++k) {
            for (int l = 1; l <= this.kol; ++l) {
                this.blokje.figuur[k][l] = figuur.figuur[k][this.kol - l + 1];
            }
        }
        this.repaint();
    }
    
    public void checkbeneden(final int n) {
        for (int i = 0; i <= this.kol - 1; ++i) {
            for (int j = 0; j <= this.rij - 1; ++j) {
                if (this.blokje.figuur[j + 1][i + 1] == 1 && this.veld.getVeld(n - j + 1, this.x + i) == 1) {
                    this.beneden = true;
                }
            }
        }
    }
    
    public void markeerblok() {
        if (this.beneden) {
            for (int i = 0; i <= this.kol - 1; ++i) {
                for (int j = 0; j <= this.rij - 1; ++j) {
                    if (this.blokje.figuur[j + 1][i + 1] == 1) {
                        if (this.y - j <= 1 && !this.gameover) {
                            this.gameover = true;
                            debug("Game over");
                            this.timer.setEnabled(false);
                        }
                        this.veld.setVeld(this.y - j, this.x + i, 1);
                        if (this.y - j < this.hoogte) {
                            this.hoogte = this.y - j;
                        }
                        this.veldkleur.setVeld(this.y - j, this.x + i, this.kleur);
                    }
                }
            }
        }
        debug("fill cache");
        this.fillCache();
    }
    
    public void delay(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void haalrijweg(final int kleurRijRij) {
        this.score += 50;
        this.timer.setEnabled(false);
        for (int i = 1; i <= 10; ++i) {
            this.delay(100);
            this.kleurRij = true;
            this.kleurRijRij = kleurRijRij;
            this.kleurRijKleur = -i;
            this.paint(this.getGraphics());
        }
        this.kleurRij = false;
        ++this.hoogte;
        for (int j = kleurRijRij; j >= 1; --j) {
            for (int k = 0; k <= 14; ++k) {
                this.veld.setVeld(j, k, this.veld.getVeld(j - 1, k));
                this.veldkleur.setVeld(j, k, this.veldkleur.getVeld(j - 1, k));
            }
        }
        debug("fill cache");
        this.fillCache();
        this.paint(this.getGraphics());
        this.timer.setEnabled(true);
    }
    
    public void checkrij() {
        for (int i = this.y; i >= this.y - this.rij; --i) {
            boolean b = true;
            for (int n = -1; b && n < 14; b = false) {
                ++n;
                if (this.veld.getVeld(i, n) == 0) {}
            }
            if (b) {
                this.haalrijweg(i);
                ++i;
            }
        }
    }
    
    public synchronized void keyPressed(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        if (keyCode == 83 && this.gameover) {
            this.restart();
            this.gameover = false;
            return;
        }
        if (keyCode == 82) {
            this.restart();
            this.gameover = false;
            return;
        }
        if (keyCode == 80 && this.pause) {
            debug("continue");
            this.timer.setEnabled(true);
            this.pause = false;
            return;
        }
        if (!this.vallen && !this.gameover && this.timer.isEnabled()) {
            if (keyCode == 37) {
                boolean b = true;
                for (int i = 0; i <= this.kol - 1; ++i) {
                    for (int j = 0; j <= this.rij - 1; ++j) {
                        if (this.blokje.figuur[j + 1][i + 1] == 1 && this.veld.getVeld(this.y - j, this.x + i - 1) == 1) {
                            b = false;
                        }
                    }
                }
                if (this.x <= 0) {
                    b = false;
                }
                if (b) {
                    --this.x;
                }
                this.repaint();
            }
            if (keyCode == 39) {
                boolean b2 = true;
                for (int k = 0; k <= this.kol - 1; ++k) {
                    for (int l = 0; l <= this.rij - 1; ++l) {
                        if (this.blokje.figuur[l + 1][this.kol - k] == 1 && this.veld.getVeld(this.y - l, this.x + this.kol - k) == 1) {
                            b2 = false;
                        }
                    }
                }
                if (this.x >= 15 - this.kol) {
                    b2 = false;
                }
                if (b2) {
                    ++this.x;
                }
                this.repaint();
            }
            if (keyCode == 40) {
                this.roteer();
            }
            if (keyCode == 38) {
                this.roteerhorz();
            }
            if (keyCode == 32) {
                this.timer.setEnabled(false);
                this.vallen = true;
                this.holdy = this.y;
                this.checkbeneden(this.y);
                while (!this.beneden) {
                    this.checkbeneden(++this.y);
                }
                this.blokjebeneden();
                this.timer.setEnabled(true);
            }
            if (keyCode == 80 && !this.pause) {
                debug("pause");
                this.timer.setEnabled(false);
                this.pause = true;
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void blokjebeneden() {
        debug("blokje beneden");
        this.vallen = false;
        this.score = this.score + 30 - this.holdy;
        this.holdy = 5;
        this.markeerblok();
        this.checkrij();
        this.beneden = false;
        if (!this.gameover) {
            this.kiesblokje();
        }
        this.repaint();
    }
    
    public void timerTimer() {
        if (!this.gameover) {
            this.checkbeneden(this.y);
            if (!this.beneden) {
                ++this.y;
                this.repaint();
            }
            if (this.beneden) {
                this.blokjebeneden();
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paintAllBlokjes(final boolean b) {
        for (int i = 24; i >= 0; --i) {
            if (this.kleurRij && i == this.kleurRijRij) {
                this.printrij(i, this.kleurRijKleur, b);
            }
            else {
                this.printrij(i, 0, b);
            }
        }
    }
    
    public synchronized void fillCache() {
        this.graphics = this.cache.getGraphics();
        this.tekenspeelveldlinks();
        this.tekenspeelveldonder();
        if (!this.gameover) {
            this.graphics.drawImage(this.gameOnImage, 40, 70, null);
        }
        this.paintAllBlokjes(true);
        if (this.gameover) {
            debug("game over : painting game over??");
            this.graphics.drawImage(this.gameOverImage, 40, 40, null);
        }
    }
    
    public void paint(final Graphics graphics) {
        debug("in paint");
        if (this.timer == null) {
            this.picture = this.createImage(15 * this.bb + 2 * this.xxx + this.b3d, this.ytop + 24 * this.bb + this.xxx);
            this.cache = this.createImage(15 * this.bb + 2 * this.xxx, this.ytop + 24 * this.bb + this.xxx);
            this.gameover = true;
            (this.timer = new Timer(this)).start();
            this.restart();
        }
        (this.graphics = this.picture.getGraphics()).drawImage(this.cache, 0, 0, null);
        this.showscore();
        this.paintAllBlokjes(false);
        this.tekenspeelveldrechts();
        graphics.drawImage(this.picture, 0, 0, this);
    }
    
    public void restart() {
        this.maakspeelveldleeg();
        debug("fill cache");
        this.score = 0;
        this.gameover = false;
        this.hoogte = 25;
        this.y = 1;
        this.fillCache();
        this.kiesblokje();
        this.beneden = false;
        this.vallen = false;
        this.holdy = 5;
        this.timer.setEnabled(true);
    }
}
