import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BJ2 extends Applet
{
    int WIDTH;
    int HEIGHT;
    int CARDPILES;
    int MONEY;
    int MAXIMUMBET;
    int BACKIMG;
    int ICONIMG;
    int KARO;
    String[] globalImageNames;
    int[] globalImageSizes;
    MediaTracker mediaTracker;
    Image[] globalImages;
    Image doubleBuffer;
    Graphics doubleBufferG;
    State state;
    State game;
    Thread loader;
    Font normalFont;
    Color BACKGROUND;
    Color MIDDLECOLOR;
    Color FOREGROUND;
    String playerName;
    boolean englishLang;
    
    public BJ2() {
        this.WIDTH = 400;
        this.HEIGHT = 270;
        this.CARDPILES = 4;
        this.MONEY = 100;
        this.MAXIMUMBET = 50;
        this.BACKIMG = 0;
        this.ICONIMG = 1;
        this.KARO = 4;
        this.globalImageNames = new String[] { "back.jpg", "icons.gif", "buttons.jpg", "card1.gif", "card2.gif", "card3.gif", "card4.gif" };
        this.globalImageSizes = new int[] { 1, 1, 4, 11, 11, 11, 11 };
        this.mediaTracker = null;
        this.state = null;
        this.game = null;
        this.loader = null;
        this.normalFont = new Font("SansSerif", 1, 11);
        this.BACKGROUND = new Color(255, 255, 204);
        this.MIDDLECOLOR = new Color(60, 115, 210);
        this.FOREGROUND = Color.black;
        this.englishLang = false;
    }
    
    public void init() {
        final String language = this.getParameter("lang");
        if (language != null) {
            this.englishLang = language.equalsIgnoreCase("english");
        }
        final Dimension d = this.getSize();
        final int w = d.width;
        final int h = d.height;
        if (w == this.WIDTH && h == this.HEIGHT) {
            this.game = new Game();
        }
        this.setBackground(this.BACKGROUND);
        this.setFont(this.normalFont);
        this.doubleBuffer = this.createImage(this.WIDTH, this.HEIGHT);
        try {
            this.doubleBufferG = this.doubleBuffer.getGraphics();
        }
        catch (Exception ex) {}
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void start() {
        this.requestFocus();
        if (this.loader == null) {
            final Loader loaderState = new Loader();
            loaderState.init();
            this.loader = new Thread(loaderState);
            this.state = loaderState;
            this.loader.start();
        }
    }
    
    public void gotoURL(final String s) {
        try {
            final URL myurl = new URL(s);
            this.getAppletContext().showDocument(myurl, "_blank");
        }
        catch (MalformedURLException ex) {}
    }
    
    public void setState(final State state) {
        (this.state = state).init();
        this.repaint();
        Thread.yield();
    }
    
    public void update(final Graphics g) {
        this.paint(this.doubleBufferG);
        g.drawImage(this.doubleBuffer, 0, 0, this);
    }
    
    public void paint(final Graphics g) {
        if (this.state != null) {
            this.state.paint(g);
        }
    }
    
    public String[][] getParameterInfo() {
        return null;
    }
    
    public Image getImage(final String s) {
        if (this.mediaTracker == null) {
            this.mediaTracker = new MediaTracker(this);
        }
        Image img = null;
        try {
            final DataInputStream in = new DataInputStream(this.getClass().getResourceAsStream(s));
            final byte[] data = new byte[in.available()];
            in.readFully(data);
            in.close();
            img = Toolkit.getDefaultToolkit().createImage(data);
        }
        catch (Exception e) {
            img = this.getImage(this.getCodeBase(), "images/".concat(String.valueOf(String.valueOf(s))));
        }
        this.mediaTracker.addImage(img, 0);
        try {
            this.mediaTracker.waitForAll();
        }
        catch (Exception ex) {}
        return img;
    }
    
    private void jbInit() throws Exception {
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                BJ2.this.state.mousePressed(e);
            }
            
            public void mouseReleased(final MouseEvent e) {
                BJ2.this.state.mouseReleased(e);
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(final MouseEvent e) {
                BJ2.this.state.mouseMoved(e);
            }
        });
    }
    
    class Loader implements State, Runnable
    {
        int totalKb;
        int loaded;
        int loadedKb;
        long start;
        int barX;
        int barY;
        String mm;
        
        Loader() {
            this.totalKb = 0;
            this.loaded = 0;
            this.loadedKb = 0;
            this.start = 0L;
        }
        
        public void init() {
            for (int a = 0; a < BJ2.this.globalImageSizes.length; ++a) {
                this.totalKb += BJ2.this.globalImageSizes[a];
            }
            BJ2.this.globalImages = new Image[BJ2.this.globalImageNames.length];
            this.barX = (BJ2.this.WIDTH - 340) / 2;
            this.barY = (BJ2.this.HEIGHT - 19) / 2 - 2;
            this.mm = (BJ2.this.englishLang ? "Loading images..." : "Lade Bilder...");
        }
        
        public void run() {
            for (int a = 0; a < BJ2.this.globalImageNames.length; ++a) {
                BJ2.this.repaint();
                Thread.yield();
                BJ2.this.globalImages[a] = BJ2.this.getImage(BJ2.this.globalImageNames[a]);
                ++this.loaded;
                this.loadedKb += BJ2.this.globalImageSizes[a];
            }
            BJ2.this.repaint();
            Thread.yield();
            BJ2.this.setState(BJ2.this.game);
            BJ2.this.repaint();
            Thread.yield();
        }
        
        private void drawRect(final Graphics g, final int x, final int y, final int w, final int h, final Color fill, final Color outline, final boolean transparent) {
            if (!transparent) {
                g.setColor(fill);
                g.fillRect(x, y, w, h);
            }
            g.setColor(outline);
            g.drawRect(x, y, w, h);
        }
        
        public void paint(final Graphics g) {
            if (this.loaded > 2) {
                g.setClip(0, 0, 400, 270);
                for (int i = 0; i < 7; ++i) {
                    for (int a = 0; a < 4; ++a) {
                        g.drawImage(BJ2.this.globalImages[BJ2.this.BACKIMG], i * 70, a * 70, null);
                    }
                }
                g.setClip(0, 0, 400, 250);
                g.setColor(Color.black);
                for (int i = 11; i < 400; i += 15) {
                    g.drawImage(BJ2.this.globalImages[BJ2.this.ICONIMG], i, -4, i + 15, 11, 0, 0, 15, 15, null);
                    g.drawImage(BJ2.this.globalImages[BJ2.this.ICONIMG], i, 239, i + 15, 254, 0, 0, 15, 15, null);
                }
                for (int i = 11; i < 250; i += 15) {
                    g.drawImage(BJ2.this.globalImages[BJ2.this.ICONIMG], -4, i, 11, i + 15, 15, 0, 30, 15, null);
                    g.drawImage(BJ2.this.globalImages[BJ2.this.ICONIMG], 389, i, 404, i + 15, 15, 0, 30, 15, null);
                }
                g.drawImage(BJ2.this.globalImages[BJ2.this.ICONIMG], -4, -4, 11, 11, 30, 0, 45, 15, null);
                g.drawImage(BJ2.this.globalImages[BJ2.this.ICONIMG], 389, -4, 404, 11, 30, 0, 45, 15, null);
                g.drawImage(BJ2.this.globalImages[BJ2.this.ICONIMG], -4, 239, 11, 254, 30, 0, 45, 15, null);
                g.drawImage(BJ2.this.globalImages[BJ2.this.ICONIMG], 389, 239, 404, 254, 30, 0, 45, 15, null);
                g.drawRect(0, 0, BJ2.this.WIDTH - 1, 249);
            }
            int percent = 0;
            if (this.totalKb != 0) {
                percent = this.loadedKb * 100 / this.totalKb;
            }
            final int width = 332 * percent / 100;
            this.drawRect(g, this.barX, this.barY, 340, 19, BJ2.this.BACKGROUND, BJ2.this.FOREGROUND, true);
            this.drawRect(g, this.barX + 4, this.barY + 3, width, 13, BJ2.this.MIDDLECOLOR, BJ2.this.FOREGROUND, false);
            g.setColor(BJ2.this.FOREGROUND);
            g.drawString(this.mm, this.barX, this.barY - 5);
            g.setClip(0, 0, 400, 270);
        }
        
        public void actionPerformed(final ActionEvent e) {
        }
        
        public void mouseMoved(final MouseEvent e) {
        }
        
        public void mousePressed(final MouseEvent e) {
        }
        
        public void mouseReleased(final MouseEvent e) {
        }
    }
    
    class Game implements State, Runnable
    {
        int posd;
        int pos;
        int betint;
        int allint;
        int manyhome;
        int manyjava;
        int counthome;
        int countjava;
        int aces;
        int dispint;
        int jaces;
        int w;
        int h;
        int totalCards;
        int rightCards;
        int pileCards;
        int countsbox;
        int intInsurance;
        int manysbox;
        int numberBox;
        int sbaces;
        int firstBut;
        int lastBut;
        boolean goin;
        boolean gotBlackJack;
        boolean javaBlackJack;
        boolean boolDouble;
        boolean boolInsurance;
        boolean boolSplit;
        boolean secondBox;
        boolean boxdone;
        boolean gameLost;
        Thread athread;
        String boxResult;
        int[][] pRanCard;
        int[] javaRanCard;
        int[][] pRanColor;
        int[] javaRanColor;
        int[][] cardMatrix;
        String[] middle;
        String[] text;
        FButton[] buttons;
        FButtonG bFennex;
        FButtonG bAdd;
        Font bigFont;
        Font copyFont;
        
        Game() {
            this.pRanCard = new int[2][22];
            this.javaRanCard = new int[22];
            this.pRanColor = new int[2][22];
            this.javaRanColor = new int[22];
            this.cardMatrix = new int[4][13];
            this.middle = new String[12];
            this.text = new String[8];
            this.buttons = new FButton[10];
            this.bigFont = new Font("TimesRoman", 1, 27);
            this.copyFont = new Font("TimesRoman", 1, 15);
        }
        
        public void init() {
            this.resetInt();
            String[] butText;
            if (BJ2.this.englishLang) {
                butText = new String[] { "DEAL", "- 10", "+ 10", "MAX", "CARD", "STAND", "DOUBLE", "SPLIT", "INSURANCE", "NEW GAME" };
            }
            else {
                butText = new String[] { "GEBEN", "- 10", "+ 10", "MAX", "KARTE", "STAND", "DOPPEL", "TEILEN", "VERSICHERN", "NEUES SPIEL" };
            }
            this.buttons[0] = new FButton(butText[0], "deal", 72, 218, 60, 20);
            this.buttons[1] = new FButton(butText[1], "tenminus", 137, 218, 60, 20);
            this.buttons[2] = new FButton(butText[2], "tenplus", 202, 218, 60, 20);
            this.buttons[3] = new FButton(butText[3], "maxplus", 267, 218, 60, 20);
            this.buttons[4] = new FButton(butText[4], "card", 40, 218, 55, 20);
            this.buttons[5] = new FButton(butText[5], "stand", 100, 218, 55, 20);
            this.buttons[6] = new FButton(butText[6], "double", 160, 218, 55, 20);
            this.buttons[7] = new FButton(butText[7], "split", 220, 218, 55, 20);
            this.buttons[8] = new FButton(butText[8], "insurance", 280, 218, 75, 20);
            this.buttons[9] = new FButton(butText[9], "playagain", 160, 218, 80, 20);
            this.showButtons(0, 3);
            this.buttons[0].setEnabled(false);
            this.bFennex = new FButtonG(BJ2.this.globalImages[2], 0, 1, 2, 252);
            this.bAdd = new FButtonG(BJ2.this.globalImages[2], 2, 3, 90, 252);
            this.middle[0] = "Fennex BlackJack 2";
            this.middle[10] = "Box 1";
            this.middle[11] = "Box 2";
            if (BJ2.this.englishLang) {
                this.middle[1] = "You Won";
                this.middle[2] = "You Lost";
                this.middle[3] = "Game Over";
                this.middle[4] = "Stand Off";
                this.middle[5] = "Want to double up?";
                this.middle[6] = "Double up";
                this.middle[7] = "Insurance?";
                this.middle[8] = "Paid Insurance";
                this.middle[9] = "Split cards?";
                this.text[0] = "DEALER";
                this.text[1] = "COUNT:";
                this.text[2] = "PILES:";
                this.text[3] = "CARDS:";
                this.text[4] = "MONEY:";
                this.text[5] = "BET:";
                this.text[6] = "INSURANCE: ";
                this.text[7] = "YOUR TABLE";
            }
            else {
                this.middle[1] = "Gewonnen";
                this.middle[2] = "Verloren";
                this.middle[3] = "Bankrott";
                this.middle[4] = "Gleichstand";
                this.middle[5] = "Betrag verdoppeln?";
                this.middle[6] = "Verdoppelt";
                this.middle[7] = "Versicherung?";
                this.middle[8] = "Versicherung bezahlt";
                this.middle[9] = "Karten teilen?";
                this.text[0] = "BANKIER";
                this.text[1] = "STAND:";
                this.text[2] = "STAPEL:";
                this.text[3] = "KARTEN:";
                this.text[4] = "GELD:";
                this.text[5] = "EINSATZ:";
                this.text[6] = "VERSICHERT: ";
                this.text[7] = "DEIN TISCH";
            }
            this.goin = false;
            this.boolDouble = false;
            this.boolInsurance = false;
            this.boolSplit = false;
            this.secondBox = false;
            this.boxdone = false;
            this.gameLost = false;
            this.dispint = 0;
            this.numberBox = 0;
            this.betint = 0;
            this.intInsurance = 0;
            this.totalCards = 52 * BJ2.this.CARDPILES;
            this.pileCards = this.totalCards - 78;
            this.rightCards = 78;
            if (BJ2.this.playerName == null) {
                BJ2.this.playerName = this.text[7];
            }
            for (int i = 0; i < 4; ++i) {
                for (int a = 0; a < 13; ++a) {
                    this.cardMatrix[i][a] = BJ2.this.CARDPILES;
                }
            }
            this.allint = BJ2.this.MONEY;
            this.counthome = 0;
            this.countjava = 0;
            this.manyhome = 0;
            this.manyjava = 0;
            this.pos = 12;
            this.jaces = 0;
            this.aces = 0;
            this.sbaces = 0;
            this.w = 381;
            this.h = 34;
        }
        
        public void drawBlackJack(final Graphics g, final int y) {
            g.setColor(BJ2.this.BACKGROUND);
            g.fillRect(45, y, 79, 15);
            g.setColor(BJ2.this.MIDDLECOLOR);
            g.drawRect(45, y, 79, 15);
            g.drawString("BLACK JACK", 51, y + 12);
        }
        
        public void drawPlayerCards(final Graphics g, final int startPos, int spacing) {
            this.pos = startPos;
            if (this.secondBox) {
                if (this.manyhome >= 4) {
                    spacing = 15;
                }
            }
            else {
                if (this.manyhome >= 5) {
                    spacing = 40;
                }
                if (this.manyhome >= 7) {
                    spacing = 25;
                }
            }
            for (int ii = 1; ii <= this.manyhome; ++ii) {
                g.drawImage(BJ2.this.globalImages[this.pRanColor[0][ii] + 3], this.pos, 130, this.pos + 55, 210, this.pRanCard[0][ii] * 55, 0, this.pRanCard[0][ii] * 55 + 55, 80, null);
                this.pos += spacing;
            }
            if (this.secondBox) {
                spacing = ((this.manysbox >= 4) ? 15 : 30);
                this.pos = 165;
                for (int ii = 1; ii <= this.manysbox; ++ii) {
                    g.drawImage(BJ2.this.globalImages[this.pRanColor[1][ii] + 3], this.pos, 130, this.pos + 55, 210, this.pRanCard[1][ii] * 55, 0, this.pRanCard[1][ii] * 55 + 55, 80, null);
                    this.pos += spacing;
                }
                if (!this.boxdone) {
                    g.drawRoundRect(this.numberBox * 140 + 20, 126, 137, 86, 5, 4);
                }
            }
        }
        
        public void paint(final Graphics g) {
            g.setClip(0, 0, 400, 270);
            for (int i = 0; i < 7; ++i) {
                for (int a = 0; a < 5; ++a) {
                    g.drawImage(BJ2.this.globalImages[BJ2.this.BACKIMG], i * 70, a * 70, null);
                }
            }
            this.bFennex.paint(g);
            this.bAdd.paint(g);
            g.setColor(BJ2.this.MIDDLECOLOR);
            g.setFont(this.copyFont);
            g.drawString("Developed by Fennex 1999-2001", 178, 265);
            g.setClip(0, 0, 400, 250);
            g.setColor(Color.black);
            for (int i = 11; i < 400; i += 15) {
                g.drawImage(BJ2.this.globalImages[BJ2.this.ICONIMG], i, -4, i + 15, 11, 0, 0, 15, 15, null);
                g.drawImage(BJ2.this.globalImages[BJ2.this.ICONIMG], i, 239, i + 15, 254, 0, 0, 15, 15, null);
            }
            for (int i = 11; i < 250; i += 15) {
                g.drawImage(BJ2.this.globalImages[BJ2.this.ICONIMG], -4, i, 11, i + 15, 15, 0, 30, 15, null);
                g.drawImage(BJ2.this.globalImages[BJ2.this.ICONIMG], 389, i, 404, i + 15, 15, 0, 30, 15, null);
            }
            g.drawImage(BJ2.this.globalImages[BJ2.this.ICONIMG], -4, -4, 11, 11, 30, 0, 45, 15, null);
            g.drawImage(BJ2.this.globalImages[BJ2.this.ICONIMG], 389, -4, 404, 11, 30, 0, 45, 15, null);
            g.drawImage(BJ2.this.globalImages[BJ2.this.ICONIMG], -4, 239, 11, 254, 30, 0, 45, 15, null);
            g.drawImage(BJ2.this.globalImages[BJ2.this.ICONIMG], 389, 239, 404, 254, 30, 0, 45, 15, null);
            g.drawRect(0, 0, BJ2.this.WIDTH - 1, 249);
            for (int i = this.firstBut; i <= this.lastBut; ++i) {
                this.buttons[i].paint(g);
            }
            g.setColor(BJ2.this.FOREGROUND);
            g.setFont(BJ2.this.normalFont);
            g.drawString(this.text[0], 309, 25);
            g.drawString(this.text[1], 309, 90);
            g.drawString(String.valueOf(this.countjava), 364, 90);
            g.drawString(this.text[2], 309, 47);
            g.drawString(String.valueOf(BJ2.this.CARDPILES), 364, 47);
            g.drawString(this.text[3], 309, 62);
            g.drawString(String.valueOf(this.totalCards), 364, 62);
            g.drawString(BJ2.this.playerName, 309, 138);
            g.drawString(this.text[4], 309, 163);
            g.drawString(String.valueOf(this.allint), 364, 163);
            g.drawString(this.text[5], 309, 178);
            g.drawString(String.valueOf(this.betint), 364, 178);
            g.drawString(this.text[1], 309, 203);
            if (this.numberBox == 0) {
                g.drawString(String.valueOf(this.counthome), 364, 203);
            }
            if (this.numberBox == 1) {
                g.drawString(String.valueOf(this.countsbox), 364, 203);
            }
            if (this.intInsurance > 0) {
                g.drawString(String.valueOf(String.valueOf(this.text[6])).concat(String.valueOf(String.valueOf(String.valueOf(this.intInsurance)))), 309, 76);
            }
            g.setColor(BJ2.this.MIDDLECOLOR);
            g.draw3DRect(300, 17, 1, 73, false);
            g.draw3DRect(300, 130, 1, 73, false);
            if (!this.secondBox) {
                this.drawPlayerCards(g, 25, 65);
            }
            else {
                this.drawPlayerCards(g, 25, 30);
            }
            int sp = 65;
            this.pos = 25;
            if (this.manyjava >= 5) {
                sp = 40;
            }
            if (this.manyjava >= 7) {
                sp = 25;
            }
            for (int ii = 1; ii <= this.manyjava; ++ii) {
                g.drawImage(BJ2.this.globalImages[this.javaRanColor[ii] + 3], this.pos, 14, this.pos + 55, 94, this.javaRanCard[ii] * 55, 0, this.javaRanCard[ii] * 55 + 55, 80, null);
                this.pos += sp;
            }
            if (this.gotBlackJack) {
                this.drawBlackJack(g, 160);
            }
            if (this.javaBlackJack) {
                this.drawBlackJack(g, 44);
            }
            g.setFont(this.bigFont);
            g.setColor(BJ2.this.MIDDLECOLOR);
            if (this.boxdone) {
                final FontMetrics ff = g.getFontMetrics();
                final int posd = BJ2.this.WIDTH / 2 - ff.stringWidth(this.boxResult) / 2;
                g.drawString(this.boxResult, posd, 120);
            }
            else {
                final FontMetrics ff = g.getFontMetrics();
                final int posd = BJ2.this.WIDTH / 2 - ff.stringWidth(this.middle[this.dispint]) / 2;
                g.drawString(this.middle[this.dispint], posd, 120);
            }
        }
        
        public void run() {
            while (this.countjava < 17) {
                ++this.manyjava;
                this.getCard(this.numberBox, this.manyjava, false);
                if (this.manyjava == 2 && this.countjava == 21) {
                    this.javaBlackJack = true;
                }
                BJ2.this.repaint();
                try {
                    Thread.sleep(500L);
                }
                catch (InterruptedException ex) {}
            }
            this.goin = false;
            if (!this.secondBox) {
                if (this.gameLost) {
                    if (this.javaBlackJack) {
                        this.allint += 2 * this.intInsurance;
                    }
                    else {
                        this.allint -= this.intInsurance;
                    }
                    this.intInsurance = 0;
                    this.dispint = 2;
                    this.allint -= this.betint;
                    this.betint = 0;
                    this.gameLost = false;
                    this.showButtons(0, 3);
                }
                else {
                    if ((this.countjava > this.counthome && this.countjava < 22) || (!this.gotBlackJack && this.javaBlackJack)) {
                        this.dispint = 2;
                        this.allint -= this.betint;
                        this.showButtons(this.betint = 0, 3);
                    }
                    else if (this.countjava < this.counthome || this.countjava > 21 || (this.gotBlackJack && !this.javaBlackJack)) {
                        this.dispint = 1;
                        this.allint += this.betint;
                        if (this.gotBlackJack) {
                            this.allint += this.betint / 2;
                        }
                        this.showButtons(this.betint = 0, 3);
                    }
                    else if ((this.counthome == this.countjava && !this.gotBlackJack && !this.javaBlackJack) || (this.gotBlackJack && this.javaBlackJack)) {
                        this.dispint = 4;
                        this.showButtons(this.betint = 0, 3);
                    }
                    if (this.intInsurance > 0) {
                        if (this.javaBlackJack) {
                            this.allint += this.intInsurance * 2;
                        }
                        else {
                            this.allint -= this.intInsurance;
                        }
                        this.intInsurance = 0;
                    }
                }
            }
            else {
                if (this.javaBlackJack) {
                    this.allint -= 2 * this.betint;
                    this.boxResult = (BJ2.this.englishLang ? "Box 1 lost - Box 2 lost" : "1 verloren - 2 verloren");
                }
                else {
                    if (this.counthome > 21) {
                        this.boxResult = (BJ2.this.englishLang ? "Box 1 lost" : "1 verloren");
                        this.allint -= this.betint;
                    }
                    else if (this.counthome > this.countjava || this.countjava > 21) {
                        this.boxResult = (BJ2.this.englishLang ? "Box 1 won" : "1 gewonnen");
                        this.allint += this.betint;
                    }
                    else if (this.counthome == this.countjava) {
                        this.boxResult = (BJ2.this.englishLang ? "Box 1 stand" : "1 gleich");
                    }
                    else if (this.counthome < this.countjava) {
                        this.boxResult = (BJ2.this.englishLang ? "Box 1 lost" : "1 verloren");
                        this.allint -= this.betint;
                    }
                    this.boxResult = String.valueOf(String.valueOf(this.boxResult)).concat(" - ");
                    if (this.countsbox > 21) {
                        this.boxResult = String.valueOf(String.valueOf(this.boxResult)).concat(String.valueOf(String.valueOf(BJ2.this.englishLang ? "Box 2 lost" : "2 verloren")));
                        this.allint -= this.betint;
                    }
                    else if (this.countsbox > this.countjava || this.countjava > 21) {
                        this.boxResult = String.valueOf(String.valueOf(this.boxResult)).concat(String.valueOf(String.valueOf(BJ2.this.englishLang ? "Box 2 won" : "2 gewonnen")));
                        this.allint += this.betint;
                    }
                    else if (this.countsbox == this.countjava) {
                        this.boxResult = String.valueOf(String.valueOf(this.boxResult)).concat(String.valueOf(String.valueOf(BJ2.this.englishLang ? "Box 2 stand" : "2 gleich")));
                    }
                    else if (this.countsbox < this.countjava) {
                        this.boxResult = String.valueOf(String.valueOf(this.boxResult)).concat(String.valueOf(String.valueOf(BJ2.this.englishLang ? "Box 2 lost" : "2 verloren")));
                        this.allint -= this.betint;
                    }
                }
                this.boxdone = true;
                this.showButtons(this.betint = 0, 3);
            }
            if (this.allint < 1) {
                this.showButtons(9, 9);
                this.dispint = 3;
            }
            BJ2.this.repaint();
        }
        
        public void actionPerformed(String command) {
            if (this.gotBlackJack) {
                this.gotBlackJack = false;
            }
            if (this.javaBlackJack) {
                this.javaBlackJack = false;
            }
            if (command == "playagain") {
                this.init();
                this.showButtons(0, 3);
            }
            if (command == "tenplus") {
                if (this.secondBox) {
                    this.secondBox = false;
                }
                if (this.boxdone) {
                    this.boxdone = false;
                }
                this.resetInt();
                this.buttons[0].setEnabled(true);
                this.betint += 10;
                if (this.betint > BJ2.this.MAXIMUMBET) {
                    this.betint = BJ2.this.MAXIMUMBET;
                }
                if (this.betint > this.allint) {
                    this.betint = this.allint;
                }
            }
            if (command == "tenminus") {
                this.betint -= 10;
                if (this.betint < 0) {
                    this.betint = 0;
                }
                if (this.betint == 0) {
                    this.buttons[0].setEnabled(false);
                }
            }
            if (command == "maxplus") {
                if (this.boxdone) {
                    this.boxdone = false;
                }
                if (this.secondBox) {
                    this.secondBox = false;
                }
                this.resetInt();
                this.buttons[0].setEnabled(true);
                this.betint = BJ2.this.MAXIMUMBET;
                if (this.betint > this.allint) {
                    this.betint = this.allint;
                }
            }
            if (command == "deal") {
                this.showButtons(4, 8);
                this.buttons[4].setEnabled(true);
                this.buttons[5].setEnabled(true);
                this.manyhome = 2;
                this.manyjava = 1;
                this.getCard(this.numberBox, 1, true);
                this.getCard(this.numberBox, 2, true);
                this.getCard(this.numberBox, 1, false);
                if (this.countjava == 11 && this.betint != this.allint) {
                    this.boolInsurance = true;
                    this.dispint = 7;
                }
                if (this.counthome == 21) {
                    this.gotBlackJack = true;
                    command = "stand";
                }
                else if (this.counthome < 12 && this.counthome > 8) {
                    this.dispint = 5;
                    this.boolDouble = true;
                }
                if (this.pRanCard[0][1] == this.pRanCard[0][2]) {
                    this.dispint = 9;
                    this.boolSplit = true;
                }
            }
            if (command == "card") {
                if (this.dispint == 5) {
                    this.dispint = 0;
                }
                if (this.boolDouble) {
                    this.boolDouble = false;
                }
                if (this.boolInsurance) {
                    this.boolInsurance = false;
                }
                if (this.boolSplit) {
                    this.boolSplit = false;
                }
                if (this.numberBox == 0) {
                    ++this.manyhome;
                    this.getCard(this.numberBox, this.manyhome, true);
                }
                else {
                    ++this.manysbox;
                    this.getCard(this.numberBox, this.manysbox, true);
                }
                if (this.counthome > 21 && !this.secondBox) {
                    if (this.intInsurance > 0) {
                        this.gameLost = true;
                        if (!this.goin) {
                            (this.athread = new Thread(this, "blackj")).start();
                            this.goin = true;
                        }
                        else {
                            this.goin = false;
                            this.athread = null;
                        }
                    }
                    else {
                        this.dispint = 2;
                        this.allint -= this.betint;
                        this.showButtons(this.betint = 0, 3);
                    }
                }
                else if (this.counthome > 21 && this.secondBox && this.countsbox < 21) {
                    this.numberBox = 1;
                    this.dispint = 11;
                }
                else if (this.countsbox > 21) {
                    this.buttons[4].setEnabled(false);
                    this.buttons[5].setEnabled(false);
                    if (!this.goin) {
                        (this.athread = new Thread(this, "blackj")).start();
                        this.goin = true;
                    }
                    else {
                        this.goin = false;
                        this.athread = null;
                    }
                }
                if (this.allint < 1) {
                    this.dispint = 3;
                    this.showButtons(9, 9);
                }
            }
            if (command == "insurance") {
                this.boolInsurance = false;
                this.dispint = 8;
                if (this.allint - this.betint >= this.betint / 2) {
                    this.intInsurance = this.betint / 2;
                }
                else {
                    this.intInsurance = this.allint - this.betint;
                }
            }
            if (command == "double") {
                this.dispint = 6;
                ++this.manyhome;
                this.getCard(this.numberBox, this.manyhome, true);
                if (this.allint >= 2 * this.betint) {
                    this.betint *= 2;
                }
                else {
                    this.betint = this.allint;
                }
                command = "stand";
            }
            if (command == "stand") {
                if (this.boolDouble) {
                    this.boolDouble = false;
                }
                if (this.boolInsurance) {
                    this.boolInsurance = false;
                }
                if (this.boolSplit) {
                    this.boolSplit = false;
                }
                if (!this.secondBox || this.numberBox == 1) {
                    this.buttons[4].setEnabled(false);
                    this.buttons[5].setEnabled(false);
                }
                if (this.secondBox && this.numberBox == 0) {
                    this.numberBox = 1;
                    this.dispint = 11;
                }
                else if (!this.goin) {
                    (this.athread = new Thread(this, "blackj")).start();
                    this.goin = true;
                }
                else {
                    this.goin = false;
                    this.athread = null;
                }
            }
            if (command == "split") {
                this.secondBox = true;
                this.boolSplit = false;
                this.manysbox = 1;
                this.manyhome = 1;
                this.pRanCard[1][1] = this.pRanCard[0][2];
                this.pRanColor[1][1] = this.pRanColor[0][2];
                this.dispint = 10;
                this.numberBox = 0;
                this.counthome = 0;
                this.count(this.pRanCard[0][1], this.numberBox);
                this.countsbox = this.counthome;
                if (2 * this.betint > this.allint) {
                    this.betint = this.allint / 2;
                }
                if (this.aces == 2) {
                    this.aces = 1;
                    this.sbaces = 1;
                }
            }
            this.buttons[6].setEnabled(this.boolDouble);
            this.buttons[8].setEnabled(this.boolInsurance);
            this.buttons[7].setEnabled(this.boolSplit);
            BJ2.this.repaint();
        }
        
        public void mouseMoved(final MouseEvent e) {
            final int x = e.getX();
            final int y = e.getY();
            for (int i = 0; i < this.buttons.length; ++i) {
                this.buttons[i].checkMouseMoved(x, y);
            }
            BJ2.this.repaint(10, 218, 380, 20);
        }
        
        public void mousePressed(final MouseEvent e) {
            final int x = e.getX();
            final int y = e.getY();
            for (int i = 0; i < this.buttons.length; ++i) {
                this.buttons[i].checkMousePressed(x, y);
            }
            this.bFennex.checkMousePressed(x, y);
            this.bAdd.checkMousePressed(x, y);
            BJ2.this.repaint(0, 218, 400, 52);
        }
        
        public void mouseReleased(final MouseEvent e) {
            final int x = e.getX();
            final int y = e.getY();
            if (this.bFennex.checkMouseReleased(x, y) == 2) {
                BJ2.this.gotoURL("http://www.fennex.de/web/index.html");
            }
            if (this.bAdd.checkMouseReleased(x, y) == 2) {
                if (BJ2.this.englishLang) {
                    BJ2.this.gotoURL("http://www.fennex.de/web/english/add/blackjack.html");
                }
                else {
                    BJ2.this.gotoURL("http://www.fennex.de/web/blackjack/add.html");
                }
            }
            String s = null;
            for (int i = 0; i < this.buttons.length; ++i) {
                s = this.buttons[i].checkMouseReleased(x, y);
                if (s != null) {
                    this.actionPerformed(s);
                    break;
                }
            }
            BJ2.this.repaint(10, 218, 380, 20);
        }
        
        public void showButtons(final int erster, final int letzter) {
            for (int i = 0; i < this.buttons.length; ++i) {
                this.buttons[i].setEnabled(true);
            }
            for (int i = 0; i < erster; ++i) {
                this.buttons[i].setEnabled(false);
            }
            for (int i = letzter + 1; i < this.buttons.length; ++i) {
                this.buttons[i].setEnabled(false);
            }
            this.firstBut = erster;
            this.lastBut = letzter;
            if (erster == 0) {
                this.buttons[0].setEnabled(false);
            }
        }
        
        public void getCard(final int box, final int cardNumber, final boolean playersCard) {
            int y;
            int x;
            do {
                x = (int)(13 * Math.random());
                y = (int)(4 * Math.random());
            } while (this.cardMatrix[y][x] < 1);
            final int[] array = this.cardMatrix[y];
            final int n = x;
            --array[n];
            --this.totalCards;
            if (this.totalCards < 1) {
                for (int i = 0; i < 4; ++i) {
                    for (int a = 0; a < 13; ++a) {
                        this.cardMatrix[i][a] = BJ2.this.CARDPILES;
                    }
                }
                this.totalCards = 52 * BJ2.this.CARDPILES;
                this.pileCards = this.totalCards - 78;
                this.rightCards = 78;
            }
            if (playersCard) {
                this.pRanCard[box][cardNumber] = x;
                this.pRanColor[box][cardNumber] = y;
                this.count(this.pRanCard[box][cardNumber], box);
            }
            else {
                this.javaRanCard[cardNumber] = x;
                this.javaRanColor[cardNumber] = y;
                this.count(this.javaRanCard[cardNumber], 2);
            }
        }
        
        public void resetInt() {
            this.jaces = 0;
            this.aces = 0;
            this.sbaces = 0;
            this.dispint = 0;
            this.manyhome = 0;
            this.manyjava = 0;
            this.counthome = 0;
            this.countjava = 0;
            this.numberBox = 0;
            this.countsbox = 0;
        }
        
        public void count(int c, final int s) {
            c += 2;
            if (c > 10 && c < 14) {
                c = 10;
            }
            if (s == 0) {
                if (c == 14) {
                    ++this.aces;
                    c = 11;
                }
                this.counthome += c;
                while (this.counthome > 21 && this.aces > 0) {
                    this.counthome -= 10;
                    --this.aces;
                }
            }
            else if (s == 1) {
                if (c == 14) {
                    ++this.sbaces;
                    c = 11;
                }
                this.countsbox += c;
                while (this.countsbox > 21 && this.sbaces > 0) {
                    this.countsbox -= 10;
                    --this.sbaces;
                }
            }
            else {
                if (c == 14) {
                    ++this.jaces;
                    c = 11;
                }
                this.countjava += c;
                while (this.countjava > 21 && this.jaces > 0) {
                    this.countjava -= 10;
                    --this.jaces;
                }
            }
        }
    }
    
    interface State
    {
        void init();
        
        void paint(final Graphics p0);
        
        void mouseMoved(final MouseEvent p0);
        
        void mousePressed(final MouseEvent p0);
        
        void mouseReleased(final MouseEvent p0);
    }
}
