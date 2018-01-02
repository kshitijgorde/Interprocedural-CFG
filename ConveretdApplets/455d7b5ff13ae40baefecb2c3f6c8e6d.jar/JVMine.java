import java.awt.Component;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JVMine extends Applet
{
    private int[] bStr;
    private int iEtat;
    private int iCpt;
    private Image buffer;
    private Graphics offScreen;
    private long startTick;
    private Image[] img;
    private int aWidth;
    private int aHeight;
    private int m_bgcolor;
    public int m_mine;
    public int m_ligne;
    public int m_col;
    private int iMax;
    private Font wFont;
    private FontMetrics wMetrics;
    private int iPas;
    private int iX;
    
    public JVMine() {
        this.m_mine = 99;
        this.m_ligne = 16;
        this.m_col = 30;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "bgImg", "String", "bgImage" }, { "bgcolor", "int", "bgColor" }, { "nbmine", "int", "nb de mine" }, { "nbcol", "int", "nb de col" }, { "nbligne", "int", "nb de ligne" }, { "font", "String", "Font Name" }, { "fontsize", "int", "Font Size" } };
    }
    
    public void verifPos(final int n) {
        final int n2 = n % this.m_col;
        if (n2 > 0) {
            final int n3 = n - this.m_col - 1;
            if (n3 >= 0 && this.bStr[n3] > 9 && this.bStr[n3] < 20) {
                final int[] bStr = this.bStr;
                final int n4 = n3;
                bStr[n4] -= 10;
                if (this.bStr[n3] == 0) {
                    this.verifPos(n3);
                }
            }
            final int n5 = n - 1;
            if (n5 >= 0 && this.bStr[n5] > 9 && this.bStr[n5] < 20) {
                final int[] bStr2 = this.bStr;
                final int n6 = n5;
                bStr2[n6] -= 10;
                if (this.bStr[n5] == 0) {
                    this.verifPos(n5);
                }
            }
            final int n7 = n + this.m_col - 1;
            if (n7 < this.iMax && this.bStr[n7] > 9 && this.bStr[n7] < 20) {
                final int[] bStr3 = this.bStr;
                final int n8 = n7;
                bStr3[n8] -= 10;
                if (this.bStr[n7] == 0) {
                    this.verifPos(n7);
                }
            }
        }
        final int n9 = n - this.m_col;
        if (n9 >= 0 && this.bStr[n9] > 9 && this.bStr[n9] < 20) {
            final int[] bStr4 = this.bStr;
            final int n10 = n9;
            bStr4[n10] -= 10;
            if (this.bStr[n9] == 0) {
                this.verifPos(n9);
            }
        }
        final int n11 = n + this.m_col;
        if (n11 < this.iMax && this.bStr[n11] > 9 && this.bStr[n11] < 20) {
            final int[] bStr5 = this.bStr;
            final int n12 = n11;
            bStr5[n12] -= 10;
            if (this.bStr[n11] == 0) {
                this.verifPos(n11);
            }
        }
        if (n2 < this.m_col - 1) {
            final int n13 = n - this.m_col + 1;
            if (n13 >= 0 && this.bStr[n13] > 9 && this.bStr[n13] < 20) {
                final int[] bStr6 = this.bStr;
                final int n14 = n13;
                bStr6[n14] -= 10;
                if (this.bStr[n13] == 0) {
                    this.verifPos(n13);
                }
            }
            final int n15 = n + this.m_col + 1;
            if (n15 < this.iMax && this.bStr[n15] > 9 && this.bStr[n15] < 20) {
                final int[] bStr7 = this.bStr;
                final int n16 = n15;
                bStr7[n16] -= 10;
                if (this.bStr[n15] == 0) {
                    this.verifPos(n15);
                }
            }
            final int n17 = n + 1;
            if (n17 < this.iMax && this.bStr[n17] > 9 && this.bStr[n17] < 20) {
                final int[] bStr8 = this.bStr;
                final int n18 = n17;
                bStr8[n18] -= 10;
                if (this.bStr[n17] == 0) {
                    this.verifPos(n17);
                }
            }
        }
    }
    
    public void newGame() {
        final Random random = new Random();
        this.iEtat = 0;
        this.iCpt = this.m_mine;
        this.iMax = this.m_ligne * this.m_col;
        this.bStr = new int[this.iMax];
        this.iX = (this.aWidth - this.m_col * 15) / 2;
        if (this.m_col * 15 > this.aWidth) {
            this.iX = 0;
        }
        for (int i = 0; i < this.iMax; ++i) {
            this.bStr[i] = 10;
        }
        this.startTick = -1L;
        int j = 0;
        while (j < this.m_mine) {
            final int n = (int)(this.iMax * random.nextDouble());
            if (n < this.iMax && this.bStr[n] != 19) {
                final int n2 = n % this.m_col;
                this.bStr[n] = 19;
                ++j;
                if (n2 > 0) {
                    final int n3 = n - 1 - this.m_col;
                    if (n3 >= 0 && this.bStr[n3] != 19) {
                        final int[] bStr = this.bStr;
                        final int n4 = n3;
                        ++bStr[n4];
                    }
                    final int n5 = n - 1;
                    if (n5 >= 0 && this.bStr[n5] != 19) {
                        final int[] bStr2 = this.bStr;
                        final int n6 = n5;
                        ++bStr2[n6];
                    }
                    final int n7 = n + this.m_col - 1;
                    if (n7 < this.iMax && this.bStr[n7] != 19) {
                        final int[] bStr3 = this.bStr;
                        final int n8 = n7;
                        ++bStr3[n8];
                    }
                }
                final int n9 = n - this.m_col;
                if (n9 >= 0 && this.bStr[n9] != 19) {
                    final int[] bStr4 = this.bStr;
                    final int n10 = n9;
                    ++bStr4[n10];
                }
                final int n11 = n + this.m_col;
                if (n11 < this.iMax && this.bStr[n11] != 19) {
                    final int[] bStr5 = this.bStr;
                    final int n12 = n11;
                    ++bStr5[n12];
                }
                if (n2 >= this.m_col - 1) {
                    continue;
                }
                final int n13 = n - this.m_col + 1;
                if (n13 >= 0 && this.bStr[n13] != 19) {
                    final int[] bStr6 = this.bStr;
                    final int n14 = n13;
                    ++bStr6[n14];
                }
                final int n15 = n + this.m_col + 1;
                if (n15 < this.iMax && this.bStr[n15] != 19) {
                    final int[] bStr7 = this.bStr;
                    final int n16 = n15;
                    ++bStr7[n16];
                }
                final int n17 = n + 1;
                if (n17 >= this.iMax || this.bStr[n17] == 19) {
                    continue;
                }
                final int[] bStr8 = this.bStr;
                final int n18 = n17;
                ++bStr8[n18];
            }
        }
        this.repaint();
    }
    
    public boolean testCase(final int n) {
        final int n2 = n % this.m_col;
        int n3 = 0;
        if (n2 > 0) {
            final int n4 = n - this.m_col - 1;
            if (n4 >= 0 && this.bStr[n4] > 19) {
                ++n3;
            }
            final int n5 = n - 1;
            if (n5 >= 0 && this.bStr[n5] > 19) {
                ++n3;
            }
            final int n6 = n + this.m_col - 1;
            if (n6 < this.iMax && this.bStr[n6] > 19) {
                ++n3;
            }
        }
        final int n7 = n - this.m_col;
        if (n7 >= 0 && this.bStr[n7] > 19) {
            ++n3;
        }
        final int n8 = n + this.m_col;
        if (n8 < this.iMax && this.bStr[n8] > 19) {
            ++n3;
        }
        if (n2 < this.m_col - 1) {
            final int n9 = n - this.m_col + 1;
            if (n9 >= 0 && this.bStr[n9] > 19) {
                ++n3;
            }
            final int n10 = n + this.m_col + 1;
            if (n10 < this.iMax && this.bStr[n10] > 19) {
                ++n3;
            }
            final int n11 = n + 1;
            if (n11 < this.iMax && this.bStr[n11] > 19) {
                ++n3;
            }
        }
        if (n3 == this.bStr[n]) {
            this.verifPos(n);
            return true;
        }
        return false;
    }
    
    public synchronized void update(final Graphics graphics) {
        if (this.offScreen != null) {
            this.paintApplet(this.offScreen);
            graphics.drawImage(this.buffer, 0, 0, this);
            return;
        }
        this.paintApplet(graphics);
    }
    
    public void paintApplet(final Graphics graphics) {
        int n = 0;
        graphics.setColor(new Color(this.m_bgcolor));
        graphics.fillRect(0, 0, this.aWidth, this.aHeight);
        for (int i = 0; i < this.m_ligne; ++i) {
            for (int j = 0; j < this.m_col; ++j) {
                int n2 = this.bStr[i * this.m_col + j];
                if (this.iEtat == 0 && n2 == 9) {
                    this.iEtat = 1;
                }
                if (this.iEtat == 1) {
                    if (n2 == 19) {
                        n2 = 9;
                    }
                    if (n2 > 19 && n2 != 29) {
                        this.iEtat = 1;
                        n2 = 41;
                    }
                }
                if (n2 > 40) {
                    n2 = 12;
                }
                else if (n2 > 19) {
                    n2 = 11;
                }
                else if (n2 > 9) {
                    n2 = 10;
                    ++n;
                }
                if (this.img[n2] != null) {
                    graphics.drawImage(this.img[n2], this.iX + j * 15, this.iPas + i * 15, this);
                }
            }
        }
        long n3;
        if (this.startTick != -1L) {
            n3 = (System.currentTimeMillis() - this.startTick) / 1000L;
        }
        else {
            n3 = 0L;
        }
        graphics.setColor(new Color(0));
        graphics.fill3DRect(this.iPas, this.iPas / 4, this.iPas, this.iPas / 2, true);
        graphics.fill3DRect(this.aWidth - this.iPas - this.iPas, this.iPas / 4, this.iPas, this.iPas / 2, true);
        graphics.setFont(this.wFont);
        graphics.setColor(Color.green);
        graphics.drawString(" " + this.iCpt, this.iPas + 5, this.iPas * 2 / 3);
        graphics.drawString(" " + n3, this.aWidth - this.iPas - this.iPas + 5, this.iPas * 2 / 3);
        graphics.setColor(Color.black);
        if (n == 0) {
            this.iEtat = 1;
            graphics.drawString(" " + this.iCpt, this.iPas + 5, this.iPas * 2 / 3);
            graphics.drawString("Win", this.aWidth / 2 - 5, this.iPas * 2 / 3);
            return;
        }
        if (this.iEtat == 1) {
            graphics.drawString("Lose", this.aWidth / 2 - 5, this.iPas * 2 / 3);
            return;
        }
        graphics.setColor(new Color(12632256));
        graphics.fill3DRect(this.aWidth / 2 - 20, this.iPas / 4, 40, this.iPas / 2, true);
        graphics.setColor(new Color(0));
        graphics.drawString("?", this.aWidth / 2 - 3, this.iPas * 2 / 3);
    }
    
    public void start() {
        this.repaint();
    }
    
    public String getAppletInfo() {
        return "Name: JVMine V 1.02\r\n" + "Author: R. BERTHOU\r\n" + "E-Mail : rbl@berthou.com\r\n" + "URL : http://www.javaside.com/";
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int n3 = (n - this.iX) / 15;
        final int n4 = (n2 - this.iPas) / 15;
        boolean testCase = false;
        if (n > this.aWidth / 2 - 20 && n < this.aWidth / 2 + 40 && n2 > this.iPas / 4 && n2 < this.iPas * 3 / 4) {
            try {
                this.getAppletContext().showDocument(new URL("http://www.javaside.com/"), "rbl");
            }
            catch (MalformedURLException ex) {
                System.out.println("Bad URL!");
            }
            return true;
        }
        if (this.iEtat == 1) {
            this.newGame();
            this.repaint();
            return true;
        }
        if (n2 > this.iPas && n > this.iX && n < this.aWidth - this.iX) {
            if ((event.modifiers & 0x4) == 0x4) {
                if (this.bStr[n4 * this.m_col + n3] > 9) {
                    testCase = true;
                    if (this.bStr[n4 * this.m_col + n3] < 20) {
                        if (this.iCpt > 0) {
                            final int[] bStr = this.bStr;
                            final int n5 = n4 * this.m_col + n3;
                            bStr[n5] += 10;
                            --this.iCpt;
                        }
                        else {
                            this.showStatus("Erreur trop de mines...");
                        }
                    }
                    else {
                        final int[] bStr2 = this.bStr;
                        final int n6 = n4 * this.m_col + n3;
                        bStr2[n6] -= 10;
                        ++this.iCpt;
                    }
                }
                else {
                    testCase = this.testCase(n4 * this.m_col + n3);
                }
            }
            else if (this.bStr[n4 * this.m_col + n3] > 9) {
                if (this.startTick == -1L) {
                    this.startTick = System.currentTimeMillis();
                }
                final int[] bStr3 = this.bStr;
                final int n7 = n4 * this.m_col + n3;
                bStr3[n7] -= 10;
                testCase = true;
                if (this.bStr[n4 * this.m_col + n3] == 9) {
                    this.iEtat = 1;
                }
                if (this.bStr[n4 * this.m_col + n3] == 0) {
                    this.verifPos(n4 * this.m_col + n3);
                }
            }
        }
        if (testCase) {
            this.repaint();
        }
        return true;
    }
    
    public void init() {
        try {
            this.aWidth = this.size().width;
            this.aHeight = this.size().height;
            this.buffer = this.createImage(this.aWidth, this.aHeight);
            this.offScreen = this.buffer.getGraphics();
        }
        catch (Exception ex) {
            this.offScreen = null;
        }
        final String parameter = this.getParameter("nbmine");
        if (parameter != null) {
            this.m_mine = Integer.parseInt(parameter);
        }
        final String parameter2 = this.getParameter("nbcol");
        if (parameter2 != null) {
            this.m_col = Integer.parseInt(parameter2);
        }
        final String parameter3 = this.getParameter("nbligne");
        if (parameter3 != null) {
            this.m_ligne = Integer.parseInt(parameter3);
        }
        final String parameter4 = this.getParameter("bgcolor");
        if (parameter4 != null) {
            this.m_bgcolor = Integer.parseInt(parameter4);
        }
        final MediaTracker mediaTracker = new MediaTracker(this);
        final String parameter5 = this.getParameter("bImg");
        this.img = new Image[13];
        if (parameter5 != null) {
            int n = 0;
            do {
                mediaTracker.addImage(this.img[n] = this.getImage(this.getCodeBase(), parameter5 + n + ".gif"), 0);
            } while (++n < 13);
        }
        else {
            int n2 = 0;
            do {
                this.img[n2] = null;
            } while (++n2 < 13);
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex2) {}
        final String parameter6 = this.getParameter("fontsize");
        int int1 = 12;
        if (parameter6 != null) {
            int1 = Integer.parseInt(parameter6);
        }
        String parameter7 = this.getParameter("font");
        if (parameter7 == null) {
            parameter7 = new String("Arial");
        }
        this.wFont = new Font(parameter7, 1, int1);
        if (this.wFont == null) {
            this.wFont = this.getFont();
        }
        this.wMetrics = this.getFontMetrics(this.wFont);
        this.iPas = this.wMetrics.getHeight() * 2;
        this.newGame();
    }
}
