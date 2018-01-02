import java.net.URLEncoder;
import java.awt.Component;
import java.awt.Event;
import java.awt.Cursor;
import java.awt.LayoutManager;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.image.PixelGrabber;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class moaghunter extends Applet implements Runnable
{
    static final double BUILD = 1.1;
    Dimension offDimension;
    Image offImage;
    Graphics g;
    Graphics offGraphics;
    MediaTracker tracker;
    private Thread tGameThread;
    static final int iGameDelay = 25;
    static final int pause = 70;
    boolean DEBUG;
    boolean bImagesLoaded;
    long timer;
    long sleeptime;
    float fps;
    int iDayEnd;
    String sHighScoreName;
    boolean bHighScoreOn;
    int iGameState;
    int iTickCount;
    int iLevel;
    static final int iMaxLevel = 5;
    Font GameFont;
    static Runtime rt;
    char[] cTemp;
    String sTemp;
    int iMoagsKilled;
    int iTotalMoagsKilled;
    int iArrowsLeft;
    int iStartingArrows;
    int iHitsinaRow;
    int iStreakDisplayCountDown;
    int iStreakLevel;
    boolean bComputedStreak;
    static final int GAMEWIDTH = 500;
    static final int GAMEHEIGHT = 450;
    static final int[] Moag1Speed;
    static final int[] Moag2Speed;
    static final int[] Moag3Speed;
    static final int[] Moag4Speed;
    static final int[] Moag5Speed;
    static final int MaxMoags = 5;
    static final int[] NumMoags;
    static final int[] MinMoags;
    String[] HighScoreNames;
    int[] HighScores;
    int[] Arrowx;
    int[] Arrowy;
    int[] Arrowtime;
    int iArrowDelay;
    int pyritex;
    int pyritey;
    static final int pyritewidth = 61;
    static final int pyriteheight = 38;
    static final int iPyriteDelay = 800;
    int iPyriteDelayCountdown;
    static final int iPyriteExplosionDuration = 500;
    int iPyriteExplosionCountdown;
    int Bonusx;
    int Bonusy;
    static final int Bonuswidth = 28;
    static final int Bonusheight = 47;
    static final int BonusVegaswidth = 68;
    static final int BonusVegasheight = 100;
    static final int iBonusDelay = 650;
    int iBonusDelayCountdown;
    static final int iBonusHitDuration = 1000;
    int iBonusHitCountdown;
    int iLastBonusAmount;
    int iBonusType;
    static final int iIntermissionTime = 3500;
    Image imgTitleImage;
    Image imgForestImage;
    Image imgDeadMoag;
    Image imgPanel;
    Image imgParchment;
    Image imgArrow;
    Image imgPyrite;
    Image imgQuiver;
    Image imgHourGlass;
    Image imgBonusVegas;
    Image[] imgBonuses;
    AudioClip sndCrossbow;
    AudioClip sndWelcome;
    static Image[] moagimages1;
    static Image[] moagimages2;
    static boolean[][] moaghitarray1;
    static int[] pixelarray;
    clsSprite[] moagsprites;
    static final int[] moagpath1x;
    static final int[] moagpath1y;
    static final int[] moagfacings1;
    static final int[] moagpath2x;
    static final int[] moagpath2y;
    static final int[] moagfacings2;
    static final int[] moagpath3x;
    static final int[] moagpath3y;
    static final int[] moagfacings3;
    static final int[] moagpath4x;
    static final int[] moagpath4y;
    static final int[] moagfacings4;
    static final int[] moagpath5x;
    static final int[] moagpath5y;
    static final int[] moagfacings5;
    static final int[] moagpath6x;
    static final int[] moagpath6y;
    static final int[] moagfacings6;
    
    static {
        Moag1Speed = new int[] { 20, 25, 25, 30, 30 };
        Moag2Speed = new int[] { 10, 20, 30, 35, 35 };
        Moag3Speed = new int[] { 25, 30, 35, 35, 35 };
        Moag4Speed = new int[] { 20, 30, 35, 35, 35 };
        Moag5Speed = new int[] { 17, 22, 27, 30, 30 };
        NumMoags = new int[] { 3, 4, 5, 5, 5 };
        MinMoags = new int[] { 20, 25, 30, 35, 40 };
        moaghunter.moagimages1 = new Image[8];
        moaghunter.moagimages2 = new Image[8];
        moaghunter.moaghitarray1 = new boolean[8][10000];
        moaghunter.pixelarray = new int[10000];
        moagpath1x = new int[] { -20, 300, 300, -20 };
        moagpath1y = new int[] { 50, 50, 300, 300 };
        moagfacings1 = new int[] { 2, 4, 6 };
        moagpath2x = new int[] { 100, 500, -100, 300 };
        moagpath2y = new int[] { -100, 300, 300, -100 };
        moagfacings2 = new int[] { 3, 6, 1 };
        moagpath3x = new int[] { 200, 200, 200, 200, -100 };
        moagpath3y = new int[] { 650, 50, 650, 200, 500 };
        moagfacings3 = new int[] { 0, 4, 0, 5 };
        moagpath4x = new int[] { 600, 100, 450, -100 };
        moagpath4y = new int[] { 50, 50, 400, -150 };
        moagfacings4 = new int[] { 6, 3, 7 };
        moagpath5x = new int[] { -50, 200, 250, 250, 200, 150, 100, 100, 150, 200, 250, 250, 200, 150, 100, 100, 150, 600 };
        moagpath5y = new int[] { 100, 100, 150, 200, 250, 250, 200, 150, 100, 100, 150, 200, 250, 250, 200, 150, 100, 100 };
        moagfacings5 = new int[] { 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2 };
        moagpath6x = new int[] { -50, 200, 250, 250, 200, 150, 100, 100, 150, 200, 250, 250, 200, 150, 100, 100 };
        moagpath6y = new int[] { 100, 100, 150, 200, 250, 250, 200, 150, 100, 100, 150, 200, 250, 250, 200, -150 };
        moagfacings6 = new int[] { 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7, 0 };
    }
    
    public moaghunter() {
        this.tGameThread = null;
        this.DEBUG = false;
        this.bImagesLoaded = false;
        this.timer = 0L;
        this.sleeptime = 0L;
        this.fps = 0.0f;
        this.iDayEnd = 30000;
        this.sHighScoreName = "";
        this.bHighScoreOn = true;
        this.iGameState = 0;
        this.iTickCount = 0;
        this.iLevel = 1;
        this.cTemp = new char[1];
        this.iMoagsKilled = 0;
        this.iTotalMoagsKilled = 0;
        this.iArrowsLeft = 50;
        this.iStartingArrows = 50;
        this.iHitsinaRow = 0;
        this.iStreakDisplayCountDown = 0;
        this.iStreakLevel = 0;
        this.bComputedStreak = false;
        this.HighScoreNames = new String[10];
        this.HighScores = new int[10];
        this.Arrowx = new int[10];
        this.Arrowy = new int[10];
        this.Arrowtime = new int[10];
        this.iArrowDelay = 2000;
        this.pyritex = 0;
        this.pyritey = 0;
        this.iPyriteDelayCountdown = 0;
        this.iPyriteExplosionCountdown = 0;
        this.Bonusx = 0;
        this.Bonusy = 0;
        this.iBonusDelayCountdown = 0;
        this.iBonusHitCountdown = 0;
        this.iLastBonusAmount = 0;
        this.iBonusType = 0;
        this.imgBonuses = new Image[3];
        this.moagsprites = new clsSprite[5];
    }
    
    private void ComputeStreakMessage() {
        if (this.iHitsinaRow == 3) {
            ++this.iMoagsKilled;
            ++this.iTotalMoagsKilled;
            this.iStreakDisplayCountDown = 1000;
            this.iStreakLevel = 1;
        }
        else if (this.iHitsinaRow == 6) {
            this.iMoagsKilled += 3;
            this.iTotalMoagsKilled += 3;
            this.iStreakDisplayCountDown = 1000;
            this.iStreakLevel = 2;
        }
        else if (this.iHitsinaRow == 9) {
            this.iMoagsKilled += 5;
            this.iTotalMoagsKilled += 5;
            this.iStreakDisplayCountDown = 1000;
            this.iStreakLevel = 3;
        }
        else if (this.iHitsinaRow > 9) {
            this.iHitsinaRow = 1;
        }
    }
    
    private boolean checkBonusHit(final int x, final int y) {
        if (this.iBonusDelayCountdown > 0) {
            final int x2 = this.Bonusx;
            final int y2 = this.Bonusy;
            int x3 = this.Bonusx + 28;
            int y3 = this.Bonusy + 47;
            if (this.iBonusType == 2) {
                x3 = this.Bonusx + 68;
                y3 = this.Bonusy + 100;
            }
            if (x >= x2 && x <= x3 && y >= y2 && y <= y3) {
                this.iBonusDelayCountdown = 0;
                this.iBonusHitCountdown = 1000;
                final int num = (short)Math.floor(Math.random() * 3.0);
                switch (this.iBonusType) {
                    case 0: {
                        this.iLastBonusAmount = num * 10 + 5;
                        this.iArrowsLeft += this.iLastBonusAmount;
                        break;
                    }
                    case 1: {
                        this.iLastBonusAmount = num * 5 + 5;
                        this.timer -= this.iLastBonusAmount * 1000;
                        if (this.timer < 0L) {
                            this.timer = 0L;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        this.iLastBonusAmount = num + 2;
                        this.iMoagsKilled += this.iLastBonusAmount;
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    private boolean checkPyriteHit(final int x, final int y) {
        if (this.iPyriteDelayCountdown > 0) {
            final int x2 = this.pyritex;
            final int y2 = this.pyritey;
            final int x3 = this.pyritex + 61;
            final int y3 = this.pyritey + 38;
            if (x >= x2 && x <= x3 && y >= y2 && y <= y3) {
                this.iPyriteDelayCountdown = 0;
                this.iPyriteExplosionCountdown = 500;
                for (int iNumMoags = moaghunter.NumMoags[this.iLevel - 1], i = 0; i < iNumMoags; ++i) {
                    if (!this.moagsprites[i].isDead()) {
                        ++this.iMoagsKilled;
                        ++this.iTotalMoagsKilled;
                        this.moagsprites[i].setDead();
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    private boolean checkforhighscore() {
        if (!this.bHighScoreOn) {
            return false;
        }
        for (int i = 0; i < 10; ++i) {
            if (this.iTotalMoagsKilled > this.HighScores[i]) {
                return true;
            }
        }
        return false;
    }
    
    private void debug(final String method, final String flag) {
    }
    
    private void drawArrows() {
        for (int iLength = this.Arrowx.length, i = 0; i < iLength; ++i) {
            if (this.Arrowtime[i] > 0) {
                final int[] arrowtime = this.Arrowtime;
                final int n = i;
                arrowtime[n] -= (int)this.sleeptime;
                this.offGraphics.drawImage(this.imgArrow, this.Arrowx[i], this.Arrowy[i], this);
            }
        }
    }
    
    private void drawInfoPanel() {
        this.offGraphics.setColor(Color.white);
        this.offGraphics.drawImage(this.imgPanel, 0, 400, this);
        this.offGraphics.drawString(String.valueOf(this.iMoagsKilled), 362, 436);
        this.offGraphics.drawString(String.valueOf(this.iArrowsLeft), 470, 436);
    }
    
    private void drawStreakMessage() {
        if (this.iStreakDisplayCountDown > 0) {
            this.iStreakDisplayCountDown -= (int)this.sleeptime;
            if (this.iStreakLevel == 1) {
                this.offGraphics.setColor(Color.white);
                this.offGraphics.drawString("The Moags Fear you! +1 points", 20, 20);
            }
            else if (this.iStreakLevel == 2) {
                this.offGraphics.setColor(Color.lightGray);
                this.offGraphics.drawString("You are death incarnate! +3 points", 20, 20);
            }
            else if (this.iStreakLevel == 3) {
                this.offGraphics.setColor(Color.yellow);
                this.offGraphics.drawString("MOAG SLAUGHTER!!! +5 points", 20, 20);
            }
        }
    }
    
    private void drawTimerBar() {
        final int iTimerk = 0;
        final int iBarWidthMax = 120;
        int iBarWidth = 0;
        if (this.iArrowsLeft < 1) {
            this.timer = this.iDayEnd;
        }
        if (this.timer < this.iDayEnd) {
            this.timer += this.sleeptime;
            final float f = this.timer / this.iDayEnd;
            iBarWidth = Math.round(iBarWidthMax - iBarWidthMax * f);
            this.offGraphics.fillRect(100, 418, iBarWidth, 18);
        }
        else {
            this.timer = 0L;
            if (this.iLevel == 5) {
                if (this.checkforhighscore()) {
                    this.iGameState = 5;
                }
                else {
                    this.iGameState = 6;
                }
            }
            else if (this.iMoagsKilled >= moaghunter.MinMoags[this.iLevel]) {
                ++this.iLevel;
                this.iGameState = 2;
                try {
                    moaghunter.rt.gc();
                }
                catch (Exception ex) {
                    System.out.println("Garbage Collection Error");
                }
            }
            else if (this.checkforhighscore()) {
                this.iGameState = 5;
            }
            else {
                this.iGameState = 6;
            }
        }
    }
    
    private void enterhighscore() {
        this.offGraphics.setColor(Color.black);
        this.offGraphics.fillRect(0, 0, 500, 450);
        this.offGraphics.setColor(Color.yellow);
        this.offGraphics.drawString("You've made the high score list!", 100, 100);
        this.offGraphics.drawString("Enter your Name, then hit return", 100, 200);
        this.offGraphics.setColor(Color.red);
        this.offGraphics.drawString(this.sHighScoreName, 100, 300);
    }
    
    private boolean[] fillCollisonArray(final Image img, final int width, final int height, final boolean[] hitarray) {
        final int transparentpixel = this.getpixel(img, 0, 0);
        final PixelGrabber pg = new PixelGrabber(img, 0, 0, width, height, moaghunter.pixelarray, 0, 100);
        try {
            pg.grabPixels();
        }
        catch (InterruptedException ex) {
            System.out.println("interrupted waiting for pixels!");
            System.exit(0);
        }
        if ((pg.getStatus() & 0x80) != 0x0) {
            System.out.println("image fetch aborted or errored");
            System.exit(0);
        }
        for (int iLength = moaghunter.pixelarray.length, i = 0; i < iLength; ++i) {
            if (transparentpixel == moaghunter.pixelarray[i]) {
                hitarray[i] = false;
            }
            else {
                hitarray[i] = true;
            }
        }
        return hitarray;
    }
    
    private void gameloop() {
        this.offGraphics.setColor(Color.black);
        this.offGraphics.fillRect(0, 0, 500, 450);
        this.offGraphics.drawImage(this.imgForestImage, 0, 0, this);
        final int iNumMoags = moaghunter.NumMoags[this.iLevel - 1];
        for (int i = 0; i < moaghunter.NumMoags[this.iLevel - 1]; ++i) {
            if (this.moagsprites[i].isDead()) {
                this.moagsprites[i].stillDead();
                this.moagsprites[i].drawFrameDead(this.offGraphics, this);
            }
        }
        for (int j = 0; j < moaghunter.NumMoags[this.iLevel - 1]; ++j) {
            if (!this.moagsprites[j].isDead()) {
                this.moagsprites[j].moveAlongPath();
                this.moagsprites[j].drawFrame(this.offGraphics, this.iTickCount, this);
            }
        }
        this.showPyriteBox();
        this.showBonuses();
        this.drawArrows();
        this.drawStreakMessage();
        this.showPyriteExplosion();
        this.showBonusHit();
        this.drawInfoPanel();
        this.drawTimerBar();
    }
    
    private void gethighscores() {
        final String theurl = "http://www.strifeshadow.net/moaghunter/_highscores.txt?blah=" + this.iTickCount;
        int i = 0;
        int iTmp = 0;
        if (this.DEBUG || !this.bHighScoreOn) {
            return;
        }
        try {
            final URLConnection uc = new URL(theurl).openConnection();
            uc.setUseCaches(false);
            final InputStream ins = uc.getInputStream();
            BufferedReader in;
            for (in = new BufferedReader(new InputStreamReader(ins)); in.ready() && i < 10; this.HighScores[i++] = iTmp) {
                this.HighScoreNames[i] = in.readLine();
                try {
                    iTmp = Integer.parseInt(in.readLine());
                }
                catch (NumberFormatException ex) {
                    iTmp = 0;
                }
            }
            in.close();
            ins.close();
        }
        catch (IOException ex2) {
            System.out.println("Error in gethighscores");
        }
        try {
            moaghunter.rt.gc();
        }
        catch (Exception ex3) {
            System.out.println("Garbage Collection Error");
        }
    }
    
    private int getpixel(final Image img, final int x, final int y) {
        final int[] pixels = { 0 };
        final PixelGrabber pg = new PixelGrabber(img, x, y, 1, 1, pixels, 0, 1);
        try {
            pg.grabPixels();
        }
        catch (InterruptedException ex) {
            System.out.println("interrupted waiting for pixels!");
            return 0;
        }
        if ((pg.getStatus() & 0x80) != 0x0) {
            System.out.println("image fetch aborted or errored");
            return 0;
        }
        return pixels[0];
    }
    
    public void init() {
        this.setLayout(null);
        this.setCursor(Cursor.getPredefinedCursor(1));
        this.setBackground(Color.black);
        this.setSize(500, 450);
        this.GameFont = new Font("Times New Roman", 1, 14);
        moaghunter.rt = Runtime.getRuntime();
        final String param = this.getParameter("HighScoreOn");
        if (param != null && param.equals("no")) {
            this.bHighScoreOn = false;
        }
        if (this.bHighScoreOn) {
            System.out.println("High Score Recording On");
        }
        else {
            System.out.println("High Score Recording off");
        }
        System.out.println("Build: " + 1.1);
        this.validate();
    }
    
    public boolean keyDown(final Event evt, final int key) {
        if (this.iGameState == 5) {
            if (key == 8) {
                if (this.sHighScoreName.length() > 0) {
                    this.sHighScoreName = this.sHighScoreName.substring(0, this.sHighScoreName.length() - 2);
                }
            }
            else if (key == 10) {
                this.submithighscore(this.sHighScoreName, this.iTotalMoagsKilled);
                this.iGameState = 6;
            }
            else {
                this.cTemp[0] = (char)key;
                this.sTemp = String.valueOf(this.cTemp[0]);
                this.sHighScoreName = String.valueOf(this.sHighScoreName) + this.sTemp;
            }
        }
        else {
            switch (key) {
                case 32: {
                    if (this.iGameState == 1 || this.iGameState == 6 || this.iGameState == 4) {
                        this.resetgame();
                        if (this.iGameState == 1) {
                            this.imgTitleImage = null;
                        }
                        this.iGameState = 2;
                        this.timer = 0L;
                        break;
                    }
                    break;
                }
                case 104: {
                    if (this.iGameState == 1 || this.iGameState == 6) {
                        this.gethighscores();
                        this.iGameState = 4;
                        this.timer = 0L;
                        break;
                    }
                    break;
                }
            }
        }
        return true;
    }
    
    private boolean loadimages() {
        this.tracker = new MediaTracker(this);
        int n = 0;
        for (int i = 0; i < 8; ++i) {
            n = i * 45;
            if (n == 0) {
                n = 360;
            }
            moaghunter.moagimages1[i] = this.getImage(this.getCodeBase(), "frames/moag" + n + "_1.gif");
            moaghunter.moagimages2[i] = this.getImage(this.getCodeBase(), "frames/moag" + n + "_2.gif");
            this.tracker.addImage(moaghunter.moagimages1[i], i);
            this.tracker.addImage(moaghunter.moagimages2[i], i + 8);
        }
        this.imgTitleImage = this.getImage(this.getCodeBase(), "images/title.jpg");
        this.tracker.addImage(this.imgTitleImage, 20);
        this.imgForestImage = this.getImage(this.getCodeBase(), "images/forest.jpg");
        this.tracker.addImage(this.imgForestImage, 21);
        this.imgDeadMoag = this.getImage(this.getCodeBase(), "images/deadmoag.gif");
        this.tracker.addImage(this.imgDeadMoag, 22);
        this.imgPanel = this.getImage(this.getCodeBase(), "images/panel.gif");
        this.tracker.addImage(this.imgPanel, 23);
        this.imgParchment = this.getImage(this.getCodeBase(), "images/parchment.gif");
        this.tracker.addImage(this.imgParchment, 23);
        this.imgArrow = this.getImage(this.getCodeBase(), "images/arrow.gif");
        this.tracker.addImage(this.imgArrow, 24);
        this.imgPyrite = this.getImage(this.getCodeBase(), "images/pyrite.gif");
        this.tracker.addImage(this.imgPyrite, 25);
        this.imgBonuses[0] = this.getImage(this.getCodeBase(), "images/quiver.gif");
        this.tracker.addImage(this.imgBonuses[0], 26);
        this.imgBonuses[1] = this.getImage(this.getCodeBase(), "images/hourglass.gif");
        this.tracker.addImage(this.imgBonuses[1], 27);
        this.imgBonuses[2] = this.getImage(this.getCodeBase(), "images/vegas.gif");
        this.tracker.addImage(this.imgBonuses[2], 27);
        try {
            this.getAppletContext().showStatus("Loading Images..");
            this.tracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.bImagesLoaded = true;
        this.sndWelcome = this.getAudioClip(this.getCodeBase(), "welcome.au");
        this.sndCrossbow = this.getAudioClip(this.getCodeBase(), "crossbow.au");
        this.tracker = null;
        final Dimension d = this.size();
        this.getAppletContext().showStatus("Images Loaded");
        this.offDimension = d;
        this.offImage = this.createImage(d.width, d.height);
        this.offGraphics = this.offImage.getGraphics();
        for (int j = 0; j < 8; ++j) {
            moaghunter.moaghitarray1[j] = this.fillCollisonArray(moaghunter.moagimages1[j], 100, 100, moaghunter.moaghitarray1[j]);
        }
        (this.moagsprites[0] = new clsSprite("moag1", moaghunter.moagimages1, moaghunter.moagimages2, moaghunter.moaghitarray1, this.imgDeadMoag, 100, 100)).setSpeed(moaghunter.Moag1Speed[0]);
        (this.moagsprites[1] = new clsSprite("moag2", moaghunter.moagimages1, moaghunter.moagimages2, moaghunter.moaghitarray1, this.imgDeadMoag, 100, 100)).setSpeed(moaghunter.Moag2Speed[0]);
        (this.moagsprites[2] = new clsSprite("moag3", moaghunter.moagimages1, moaghunter.moagimages2, moaghunter.moaghitarray1, this.imgDeadMoag, 100, 100)).setSpeed(moaghunter.Moag3Speed[0]);
        (this.moagsprites[3] = new clsSprite("moag4", moaghunter.moagimages1, moaghunter.moagimages2, moaghunter.moaghitarray1, this.imgDeadMoag, 100, 100)).setSpeed(moaghunter.Moag4Speed[0]);
        (this.moagsprites[4] = new clsSprite("moag5", moaghunter.moagimages1, moaghunter.moagimages2, moaghunter.moaghitarray1, this.imgDeadMoag, 100, 100)).setSpeed(moaghunter.Moag5Speed[0]);
        for (int k = 0; k < 5; ++k) {
            this.moagsprites[k].loadPath(moaghunter.moagpath1x, moaghunter.moagpath1y, moaghunter.moagfacings1);
            this.moagsprites[k].loadPath(moaghunter.moagpath2x, moaghunter.moagpath2y, moaghunter.moagfacings2);
            this.moagsprites[k].loadPath(moaghunter.moagpath3x, moaghunter.moagpath3y, moaghunter.moagfacings3);
            this.moagsprites[k].loadPath(moaghunter.moagpath4x, moaghunter.moagpath4y, moaghunter.moagfacings4);
            this.moagsprites[k].loadPath(moaghunter.moagpath5x, moaghunter.moagpath5y, moaghunter.moagfacings5);
            this.moagsprites[k].loadPath(moaghunter.moagpath6x, moaghunter.moagpath6y, moaghunter.moagfacings6);
            this.moagsprites[k].randomizePaths();
        }
        this.sndWelcome.play();
        this.gethighscores();
        this.iGameState = 1;
        this.sndWelcome = null;
        return true;
    }
    
    public boolean mouseDown(final Event e, final int x, final int y) {
        boolean bTemp1 = false;
        boolean bTemp2 = false;
        boolean bTemp3 = false;
        if (this.iArrowsLeft > 0 && this.iGameState == 3) {
            this.sndCrossbow.play();
            bTemp1 = this.checkPyriteHit(x, y);
            bTemp2 = this.checkBonusHit(x, y);
            this.newArrow(x, y);
            for (int iNumMoags = moaghunter.NumMoags[this.iLevel - 1], i = 0; i < iNumMoags; ++i) {
                if (!this.moagsprites[i].isDead() && this.moagsprites[i].collided(x, y, this.iTickCount)) {
                    ++this.iMoagsKilled;
                    ++this.iTotalMoagsKilled;
                    bTemp3 = true;
                    this.moagsprites[i].setDead();
                }
            }
            --this.iArrowsLeft;
        }
        if (bTemp1 || bTemp2 || bTemp3) {
            ++this.iHitsinaRow;
            this.ComputeStreakMessage();
        }
        else {
            this.iHitsinaRow = 0;
        }
        return false;
    }
    
    private void newArrow(final int x, final int y) {
        for (int iLength = this.Arrowx.length, i = 0; i < iLength; ++i) {
            if (this.Arrowtime[i] <= 0) {
                this.Arrowx[i] = x;
                this.Arrowy[i] = y;
                this.Arrowtime[i] = this.iArrowDelay;
                i = iLength;
            }
        }
    }
    
    public void paint(final Graphics g) {
        if (this.bImagesLoaded) {
            this.update(g);
        }
    }
    
    private void resetgame() {
        this.iLevel = 1;
        this.iTotalMoagsKilled = 0;
        this.Arrowtime[0] = -1;
        this.Arrowtime[1] = -1;
        this.Arrowtime[2] = -1;
        this.Arrowtime[3] = -1;
        this.Arrowtime[4] = -1;
        this.Arrowtime[5] = -1;
        this.Arrowtime[6] = -1;
        this.Arrowtime[7] = -1;
        this.Arrowtime[8] = -1;
        this.Arrowtime[9] = -1;
    }
    
    public void run() {
        long time = 0L;
        time = System.currentTimeMillis();
        while (this.tGameThread != null) {
            try {
                ++this.iTickCount;
                final long looptime = time - System.currentTimeMillis();
                Thread.sleep(this.sleeptime = Math.max(25L, looptime));
                time = System.currentTimeMillis() + 70L;
                switch (this.iGameState) {
                    case 0: {
                        this.loadimages();
                        break;
                    }
                    case 1: {
                        this.showtitle();
                        break;
                    }
                    case 2: {
                        this.showintermission();
                        break;
                    }
                    case 3: {
                        this.gameloop();
                        break;
                    }
                    case 4: {
                        this.showhighscores();
                        break;
                    }
                    case 5: {
                        this.enterhighscore();
                        break;
                    }
                    case 6: {
                        this.showgameover();
                        break;
                    }
                }
                this.repaint();
            }
            catch (InterruptedException ex) {
                this.stop();
            }
        }
    }
    
    private void showBonusHit() {
        if (this.iBonusHitCountdown > 0) {
            this.iBonusHitCountdown -= (int)this.sleeptime;
            if (this.iTickCount % 2 == 0) {
                this.offGraphics.setColor(Color.white);
                switch (this.iBonusType) {
                    case 0: {
                        this.offGraphics.drawString("+" + this.iLastBonusAmount + " Arrows", this.Bonusx - 10, this.Bonusy);
                        break;
                    }
                    case 1: {
                        this.offGraphics.drawString("+" + this.iLastBonusAmount + " Seconds", this.Bonusx - 10, this.Bonusy);
                        break;
                    }
                    case 2: {
                        this.offGraphics.drawString("+" + this.iLastBonusAmount + " Moags for shooting Vegas!", this.Bonusx - 10, this.Bonusy);
                        break;
                    }
                }
            }
        }
    }
    
    private void showBonuses() {
        if (this.iTickCount % 150 == 0 && this.iBonusDelayCountdown <= 0) {
            this.iBonusDelayCountdown = 650;
            int num = (short)Math.floor(Math.random() * 3.0);
            if (num == 0) {
                this.Bonusx = 150;
                this.Bonusy = 150;
            }
            else if (num == 1) {
                this.Bonusx = 350;
                this.Bonusy = 150;
            }
            else if (num == 2) {
                this.Bonusx = 50;
                this.Bonusy = 300;
            }
            else if (num == 3) {
                this.Bonusx = 75;
                this.Bonusy = 350;
            }
            num = (short)Math.floor(Math.random() * 3.0);
            this.iBonusType = num;
        }
        if (this.iBonusDelayCountdown > 0) {
            this.iBonusDelayCountdown -= (int)this.sleeptime;
            this.offGraphics.drawImage(this.imgBonuses[this.iBonusType], this.Bonusx, this.Bonusy, this);
        }
    }
    
    private void showPyriteBox() {
        if (this.iTickCount % 200 == 0 && this.iPyriteDelayCountdown <= 0) {
            this.iPyriteDelayCountdown = 800;
            final int num = (short)Math.floor(Math.random() * 3.0);
            if (num == 0) {
                this.pyritex = 100;
                this.pyritey = 100;
            }
            else if (num == 1) {
                this.pyritex = 300;
                this.pyritey = 100;
            }
            else if (num == 2) {
                this.pyritex = 100;
                this.pyritey = 300;
            }
            else if (num == 3) {
                this.pyritex = 300;
                this.pyritey = 300;
            }
        }
        if (this.iPyriteDelayCountdown > 0) {
            this.iPyriteDelayCountdown -= (int)this.sleeptime;
            this.offGraphics.drawImage(this.imgPyrite, this.pyritex, this.pyritey, this);
        }
    }
    
    private void showPyriteExplosion() {
        if (this.iPyriteExplosionCountdown > 0) {
            this.iPyriteExplosionCountdown -= (int)this.sleeptime;
            if (this.iTickCount % 2 == 0) {
                this.offGraphics.setColor(Color.red);
                this.offGraphics.fillRect(0, 0, 500, 450);
            }
            else {
                this.offGraphics.setColor(Color.white);
                this.offGraphics.fillRect(0, 0, 500, 450);
            }
        }
    }
    
    private void showgameover() {
        this.offGraphics.setColor(Color.black);
        this.offGraphics.fillRect(0, 0, 500, 450);
        this.offGraphics.setFont(this.GameFont);
        this.offGraphics.setColor(Color.white);
        this.offGraphics.drawString("GAME OVER", 100, 100);
        this.offGraphics.drawString("You bagged " + this.iTotalMoagsKilled + " moags.", 100, 200);
        this.offGraphics.drawString("Press Space bar to play again.", 100, 300);
        this.offGraphics.drawString("Press H to view high scores.", 100, 400);
    }
    
    private void showhighscores() {
        this.offGraphics.setColor(Color.black);
        this.offGraphics.fillRect(0, 0, 500, 450);
        this.offGraphics.setFont(this.GameFont);
        this.offGraphics.setColor(Color.white);
        this.offGraphics.drawString("HIGH SCORERS", 100, 50);
        this.offGraphics.fillRect(75, 50, 300, 2);
        for (int i = 0; i < this.HighScoreNames.length; ++i) {
            if (this.HighScoreNames[i] == null) {
                i = this.HighScoreNames.length;
            }
            else {
                this.offGraphics.drawString(String.valueOf(this.HighScores[i]) + "  " + this.HighScoreNames[i], 100, 100 + 20 * i);
            }
        }
        this.offGraphics.drawString("Press Space Bar to play again!", 100, 400);
    }
    
    private void showintermission() {
        if (this.iPyriteExplosionCountdown > 0) {
            this.iPyriteExplosionCountdown = 0;
        }
        for (int iNumMoags = moaghunter.NumMoags[this.iLevel - 1], i = 0; i < iNumMoags; ++i) {
            if (this.moagsprites[i].isDead()) {
                this.moagsprites[i].isDead = false;
                final clsSprite clsSprite = this.moagsprites[i];
                final clsSprite clsSprite2 = this.moagsprites[i];
                clsSprite.iNumFramesDead = 20;
            }
        }
        this.offGraphics.setColor(Color.black);
        this.offGraphics.fillRect(0, 0, 500, 450);
        this.offGraphics.drawImage(this.imgParchment, 130, 100, this);
        if (this.timer < 3500L) {
            this.timer += this.sleeptime;
            this.offGraphics.setFont(this.GameFont);
            this.offGraphics.setColor(Color.black);
            if (this.iLevel == 5) {
                this.offGraphics.drawString("This is the last and", 165, 200);
                this.offGraphics.drawString("final day of hunting.", 165, 230);
                this.offGraphics.drawString("Take aim for glory!", 165, 260);
            }
            else {
                this.offGraphics.drawString("You must kill at least " + moaghunter.MinMoags[this.iLevel], 155, 200);
                this.offGraphics.drawString("moags to go to day " + (this.iLevel + 1), 155, 230);
                this.offGraphics.drawString("of the Dark Elf", 155, 260);
                this.offGraphics.drawString("Marksmen challenge.", 155, 290);
                this.offGraphics.drawString("Good luck!", 155, 320);
            }
            this.drawInfoPanel();
        }
        else {
            this.iArrowsLeft = this.iStartingArrows;
            this.iMoagsKilled = 0;
            this.timer = 0L;
            this.iHitsinaRow = 0;
            this.iGameState = 3;
        }
    }
    
    private void showtitle() {
        this.offGraphics.setColor(Color.black);
        this.offGraphics.fillRect(0, 0, 500, 450);
        this.offGraphics.drawImage(this.imgTitleImage, 50, 25, this);
    }
    
    public void start() {
        if (this.tGameThread == null) {
            (this.tGameThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.tGameThread != null) {
            this.tGameThread.stop();
            this.tGameThread = null;
        }
    }
    
    private void submithighscore(final String Name, final int Score) {
        final String theurl = "http://www.strifeshadow.net/moaghunter/ffffggggg3333311111.php?bleh=23333423422342342112121&n=" + URLEncoder.encode(Name) + "&s=" + Score;
        final int i = 0;
        if (this.DEBUG || !this.bHighScoreOn) {
            return;
        }
        try {
            final URLConnection uc = new URL(theurl).openConnection();
            uc.setUseCaches(false);
            final InputStream ins = uc.getInputStream();
            final BufferedReader in = new BufferedReader(new InputStreamReader(ins));
            while (in.ready() && i < 10) {
                in.readLine();
            }
            in.close();
            ins.close();
        }
        catch (IOException ex) {
            System.out.println("Error in submithighscore");
        }
    }
    
    public void update(final Graphics g) {
        if (this.bImagesLoaded) {
            g.drawImage(this.offImage, 0, 0, this);
        }
    }
}
