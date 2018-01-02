import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.io.InputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Container;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Frame;
import java.awt.Component;
import java.applet.AudioClip;
import java.util.Random;
import java.awt.Rectangle;
import java.awt.MediaTracker;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class cliffie extends Applet implements Runnable
{
    private Thread CliffieThread;
    private final int numberOfIndSteps = 12;
    private boolean[] loadingIndicator;
    private hero Cliff;
    private final int numberOfLives = 4;
    private int CliffLives;
    private long CliffTime;
    private final int CliffGameTime = 302;
    private int score;
    private int beerOMeter;
    private long beerTime;
    private final int timeBetweenBeers = 15;
    private int gameStatus;
    private long pauseTime;
    private final int numberOfSprites = 11;
    private final int numberOfCars = 8;
    private car[] cars;
    private car[] carQueue;
    private int carsInQueue;
    private sprite[] zOrder;
    private intro gameIntro;
    private Color hiscoreColor;
    private final int maxNumberOfScores = 10;
    private String[] hiscoreTable;
    private int numberOfScores;
    private hiscoredialog hiscoreDialog;
    private boolean hasEnteredHiscore;
    private help helpWindow;
    private Color quotesColor;
    private quotes CliffQuotes;
    private String theQuote;
    private Font quoteFont;
    private Image cityPic;
    private Image displayPic;
    private Image gameOverPic;
    private Image lifesPic;
    private Image[] mugPics;
    private Image mugs;
    private Image arrowPic;
    private Image hiscorePic;
    private Image atCheersPic;
    private Image quotePic;
    private Image offScrImage;
    private Graphics offScrGC;
    private Graphics unclippedGC;
    private Polygon dogLimits;
    private dog Fido;
    private dog Rufus;
    private MediaTracker tracker;
    private Polygon roadLimits;
    private final int[] roadPositions;
    private final double[][] carColors;
    private Rectangle Cheers;
    private final int numberOfMailBoxes = 17;
    private int activeMailBox;
    private final int[][] mailBoxes;
    private int arrowMove;
    private boolean arrowUp;
    private Random random;
    private Font displayFont;
    private Font commentFont;
    private Font hiscoreFont;
    private AudioClip mailSound;
    private AudioClip yipeeSound;
    private AudioClip agonySound;
    private AudioClip burpSound;
    private final String MIDI_intro = "intromusic.htm";
    private final String MIDI_game = "gamemusic.htm";
    private final String MIDI_cheers = "cheersmusic.htm";
    private final String MIDI_nomusic = "nomusic.htm";
    private boolean musicRequested;
    private boolean keyReleased;
    
    public void init() {
        this.setBackground(new Color(255, 255, 255));
        this.random = new Random();
        this.tracker = new MediaTracker(this);
        this.hiscoreColor = new Color(61, 115, 181);
        this.Cheers = new Rectangle(385, 33, 27, 10);
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        this.hiscoreDialog = new hiscoredialog((Frame)container);
        this.helpWindow = new help("Cliffie Help");
        for (int i = 0; i < 12; ++i) {
            this.loadingIndicator[i] = false;
        }
        try {
            this.cityPic = this.getImage(this.getCodeBase(), "boston.gif");
            this.displayPic = this.getImage(this.getCodeBase(), "display.gif");
            this.atCheersPic = this.getImage(this.getCodeBase(), "cliffcheers.jpg");
            this.lifesPic = this.getImage(this.getCodeBase(), "lifes.gif");
            this.mugs = this.getImage(this.getCodeBase(), "mugs.gif");
            this.arrowPic = this.getImage(this.getCodeBase(), "arrow.gif");
            this.hiscorePic = this.getImage(this.getCodeBase(), "hiscores.gif");
            this.quotePic = this.getImage(this.getCodeBase(), "quotes.gif");
            this.tracker.addImage(this.cityPic, 0);
            this.tracker.addImage(this.displayPic, 1);
            this.tracker.addImage(this.atCheersPic, 2);
            this.tracker.addImage(this.lifesPic, 3);
            this.tracker.addImage(this.mugs, 4);
            this.tracker.addImage(this.arrowPic, 5);
            this.tracker.addImage(this.hiscorePic, 6);
            this.tracker.addImage(this.quotePic, 7);
            if (this.tracker.isErrorAny()) {
                this.showStatus("Feil ved henting av grafikk!!");
                System.out.println("Finner ikke grafikk-fil!..");
                System.exit(0);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.offScrImage = this.createImage(this.size().width + 1, this.size().height * 3 + 1);
        (this.offScrGC = this.offScrImage.getGraphics()).clipRect(4, 4, 596, 390);
        this.unclippedGC = this.offScrImage.getGraphics();
        this.roadLimits = this.strToPolyParse("385,33,385,56,2,56,2,110,65,111,65,200,0,200,0,247,63,247,65,382,600,384,600,56,561,56,561,342,354,342,354,248,561,248,561,200,356,200,356,111,307,111,307,200,107,200,107,248,308,248,308,341,107,338,107,111,561,111,561,56,415,56,415,33,385,33");
        this.dogLimits = this.strToPolyParse("563,247,599,247,599,384,65,384,65,250,104,250,105,344,307,344,308,248,354,248,355,344,562,344,563,247");
        this.Cliff = new hero(this.roadLimits, this.tracker, 8, this.offScrGC, this);
        this.Rufus = new dog(this.dogLimits, this.tracker, 9, this.offScrGC, this);
        this.Fido = new dog(this.dogLimits, this.tracker, 9, this.offScrGC, this);
        this.gameIntro = new intro(800, this.tracker, 10, this.unclippedGC, this);
        this.CliffQuotes = new quotes(this);
        this.quotesColor = new Color(252, 174, 13);
        this.displayFont = new Font("helvetica", 1, 14);
        this.commentFont = new Font("helvetica", 1, 28);
        this.hiscoreFont = new Font("Courier", 0, 14);
        this.quoteFont = new Font("Courier", 1, 16);
        for (int j = 0; j < 8; ++j) {
            this.cars[j] = new car(this.carColors[j][0], this.carColors[j][1], this.carColors[j][2], this.offScrGC, this);
        }
        for (int k = 0; k < 6; ++k) {
            this.mugPics[k] = this.createImage(new FilteredImageSource(this.mugs.getSource(), new CropImageFilter(k * 50, 0, 50, 63)));
            this.tracker.addImage(this.mugPics[k], 10);
        }
        this.mailSound = this.getAudioClip(this.getCodeBase(), "mail.au");
        this.yipeeSound = this.getAudioClip(this.getCodeBase(), "yipee.au");
        this.agonySound = this.getAudioClip(this.getCodeBase(), "agony.au");
        this.burpSound = this.getAudioClip(this.getCodeBase(), "burp.au");
        this.mailSound.play();
        this.mailSound.stop();
        this.yipeeSound.play();
        this.yipeeSound.stop();
        this.agonySound.play();
        this.agonySound.stop();
        this.burpSound.play();
        this.burpSound.stop();
    }
    
    private void pushCarToQueue(final car car) {
        this.carQueue[this.carsInQueue++] = car;
    }
    
    private car popCarFromQueue() {
        final car car = this.carQueue[0];
        for (int i = 1; i < this.carsInQueue; ++i) {
            this.carQueue[i - 1] = this.carQueue[i];
        }
        --this.carsInQueue;
        return car;
    }
    
    public void zOrderSort() {
        for (int i = 1; i < 11; ++i) {
            sprite sprite;
            int n;
            for (sprite = this.zOrder[i], n = i; n > 0 && sprite.getPosY() < this.zOrder[n - 1].getPosY(); --n) {
                this.zOrder[n] = this.zOrder[n - 1];
            }
            this.zOrder[n] = sprite;
        }
    }
    
    public void readHiscores() {
        InputStream openStream = null;
        try {
            openStream = new URL(this.getCodeBase(), "hiscores.dat").openStream();
        }
        catch (Exception ex) {
            System.out.println("Finner ikke fil!");
            System.exit(1);
        }
        final DataInputStream dataInputStream = new DataInputStream(openStream);
        this.numberOfScores = 0;
        try {
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                if (this.numberOfScores > 10) {
                    break;
                }
                this.hiscoreTable[this.numberOfScores++] = line;
            }
        }
        catch (Exception ex2) {
            System.out.println("Feil ved fil-lesing!");
            System.exit(1);
        }
        try {
            dataInputStream.close();
        }
        catch (IOException ex3) {}
    }
    
    public void manageTraffic() {
        for (int i = 0; i < 8; ++i) {
            if (this.cars[i].getStatus() != 0) {
                if (this.cars[i].getStatus() == 1 && this.cars[i].getPosX() > 661) {
                    this.popCarFromQueue().initDrive(1, -61, this.cars[i].getPosY(), this.randomCarSpeed());
                    this.cars[i].stop();
                    this.pushCarToQueue(this.cars[i]);
                }
                else if (this.cars[i].getStatus() == 2 && this.cars[i].getPosX() < -61) {
                    this.popCarFromQueue().initDrive(2, 661, this.cars[i].getPosY(), this.randomCarSpeed());
                    this.cars[i].stop();
                    this.pushCarToQueue(this.cars[i]);
                }
                else {
                    this.cars[i].drive();
                }
            }
        }
    }
    
    public void writeScoreToFile() {
        final String s = new String(this.hiscoreDialog.getName());
        System.out.println("Name: " + s);
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(40);
        final String s2 = new String();
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            String value = null;
            if (char1 > ',' && char1 < '}') {
                value = String.valueOf(char1);
            }
            else {
                switch (char1) {
                    case '\u00e6': {
                        value = "%E6";
                        break;
                    }
                    case '\u00f8': {
                        value = "%F8";
                        break;
                    }
                    case '\u00e5': {
                        value = "%E5";
                        break;
                    }
                    case '\u00c6': {
                        value = "%C6";
                        break;
                    }
                    case '\u00d8': {
                        value = "%D8";
                        break;
                    }
                    case '\u00c5': {
                        value = "%C5";
                        break;
                    }
                    default: {
                        value = "+";
                        break;
                    }
                }
            }
            sb.append(value);
        }
        final String string = "http://www.iu.hioslo.no/cgi-bin-nilsenr/cliffie.cgi?" + Integer.toString(this.score) + "&" + sb.toString();
        try {
            this.getAppletContext().showDocument(new URL(this.getCodeBase(), string), "cgi");
        }
        catch (MalformedURLException ex) {
            System.out.println("Kunne ikke \u00e5pne script!");
        }
    }
    
    public void playMusic(final String s) {
        try {
            this.getAppletContext().showDocument(new URL(this.getCodeBase(), s), "music");
        }
        catch (MalformedURLException ex) {
            System.out.println("Kunne ikke spille midi!");
        }
    }
    
    public boolean doneLoading() {
        return this.tracker.checkAll(true) && this.doneLoadingSounds();
    }
    
    public boolean doneLoadingSounds() {
        return this.mailSound != null && this.burpSound != null && this.agonySound != null && this.yipeeSound != null;
    }
    
    public void resetGame() {
        this.gameStatus = 0;
        this.carsInQueue = 0;
        this.beerOMeter = 5;
        this.beerTime = System.currentTimeMillis();
        this.score = 0;
        this.hasEnteredHiscore = false;
        this.activeMailBox = Math.abs(this.random.nextInt()) % 17;
        this.CliffLives = 4;
        this.Cliff.resetPos();
        this.Cliff.startBlinking();
        for (int i = 0; i < 8; ++i) {
            this.cars[i].setStatus(0);
            if (i > 3) {
                this.pushCarToQueue(this.cars[i]);
            }
            this.zOrder[i] = this.cars[i];
        }
        this.zOrder[8] = this.Fido;
        this.zOrder[9] = this.Rufus;
        this.zOrder[10] = this.Cliff;
        this.cars[0].initDrive(2, this.randomCarPosX(), this.roadPositions[0], this.randomCarSpeed());
        this.cars[1].initDrive(1, this.randomCarPosX(), this.roadPositions[1], this.randomCarSpeed());
        this.cars[2].initDrive(2, this.randomCarPosX(), this.roadPositions[2], this.randomCarSpeed());
        this.cars[3].initDrive(1, this.randomCarPosX(), this.roadPositions[3], this.randomCarSpeed());
        this.CliffTime = System.currentTimeMillis();
        this.pauseTime = this.CliffTime;
    }
    
    public int randomCarSpeed() {
        return Math.abs(this.random.nextInt()) % 7 + 3;
    }
    
    public int randomCarPosX() {
        return Math.abs(this.random.nextInt()) % 661 - 61;
    }
    
    public void stop() {
        if (this.CliffieThread != null) {
            this.CliffieThread.stop();
            this.CliffieThread = null;
        }
    }
    
    public void start() {
        if (this.CliffieThread == null) {
            (this.CliffieThread = new Thread(this)).start();
        }
    }
    
    public void finalize() {
        this.offScrGC.dispose();
        this.unclippedGC.dispose();
    }
    
    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        while (Thread.currentThread() == this.CliffieThread) {
            try {
                if (this.gameStatus == 1 || this.gameStatus == 7 || this.gameIntro.inTransition()) {
                    Thread.sleep(Math.max(currentTimeMillis - System.currentTimeMillis(), 40L));
                }
                else {
                    Thread.sleep(300L);
                }
            }
            catch (InterruptedException ex) {}
            currentTimeMillis = System.currentTimeMillis() + 35L;
            switch (this.gameStatus) {
                case -1: {
                    this.loading();
                    break;
                }
                case 0: {
                    this.intro();
                    break;
                }
                case 1: {
                    this.playing();
                    break;
                }
                case 2: {
                    this.pause();
                    break;
                }
                case 3: {
                    this.inCheers();
                    break;
                }
                case 4: {
                    this.lifeLost();
                    break;
                }
                case 5: {
                    this.gameOver();
                    break;
                }
                case 6: {
                    this.endGame();
                    break;
                }
                case 7: {
                    this.hiscores();
                    break;
                }
                case 8: {
                    this.quote();
                    break;
                }
            }
            this.repaint();
        }
    }
    
    public void drawShadowString(final String s, final int n, final int n2, final int n3, final boolean b, final Graphics graphics) {
        final Color color = graphics.getColor();
        final int stringWidth = graphics.getFontMetrics().stringWidth(s);
        graphics.setColor(Color.darkGray);
        graphics.drawString(s, n + (b ? (n3 / 2 - stringWidth / 2) : 0) + 1, n2 + 1);
        graphics.setColor(color);
        graphics.drawString(s, n + (b ? (n3 / 2 - stringWidth / 2) : 0), n2);
    }
    
    public void drawCenterString(final String s, final int n, final int n2, final int n3, final Graphics graphics) {
        graphics.drawString(s, n + n3 / 2 - graphics.getFontMetrics().stringWidth(s) / 2, n2);
    }
    
    public void drawQuote() {
        final int length = this.theQuote.length();
        int n = 1;
        int n2 = 0;
        final char[] charArray = this.theQuote.toCharArray();
        this.unclippedGC.setFont(this.quoteFont);
        for (int i = 0; i < length; ++i) {
            if (++n2 > 30 && charArray[i] == ' ') {
                ++n;
                n2 = 0;
            }
            this.unclippedGC.setColor(Color.white);
            this.drawShadowString(String.valueOf(charArray[i]), 101 + 12 * n2, 101 + n * 17, 0, false, this.unclippedGC);
        }
        n += 2;
        this.drawShadowString("- Cliff Clavin", 450, 100 + n * 17, 0, false, this.unclippedGC);
    }
    
    public void quote() {
        if (this.gameIntro.inClosingTransition()) {
            return;
        }
        if (this.gameIntro.transitionClosed()) {
            this.gameStatus = 0;
            return;
        }
        this.unclippedGC.setColor(this.quotesColor);
        this.unclippedGC.fillRect(0, 0, 668, 400);
        this.unclippedGC.drawImage(this.quotePic, 198, 90, this);
        this.drawQuote();
        this.drawFrame(0, 0, 667, 399, this.unclippedGC);
    }
    
    public void hiscores() {
        if (this.gameIntro.inClosingTransition()) {
            return;
        }
        if (this.gameIntro.transitionClosed()) {
            this.gameStatus = 0;
            return;
        }
        this.unclippedGC.setColor(this.hiscoreColor);
        this.unclippedGC.fillRect(0, 0, 668, 400);
        this.unclippedGC.drawImage(this.hiscorePic, 199, 20, this);
        this.drawFrame(0, 0, 667, 399, this.unclippedGC);
        this.offScrGC.setFont(this.hiscoreFont);
        this.offScrGC.setColor(new Color(Math.abs(this.random.nextInt()) % 155 + 100, Math.abs(this.random.nextInt()) % 155 + 100, Math.abs(this.random.nextInt()) % 155 + 100));
        this.offScrGC.drawString(this.hiscoreTable[0], 200, 115);
        this.offScrGC.setColor(Color.white);
        for (int i = 1; i < this.numberOfScores; ++i) {
            this.offScrGC.drawString(this.hiscoreTable[i], 200, 115 + 18 * i);
        }
        this.drawShadowString("Try reload/update if your", 0, 325, 668, true, this.offScrGC);
        this.drawShadowString("score is missing..", 0, 340, 668, true, this.offScrGC);
    }
    
    public void pause() {
        this.unclippedGC.setFont(this.commentFont);
        this.unclippedGC.setColor(Color.white);
        this.drawShadowString("PAUSE (PRESS ANY KEY TO CONTINUE)", 0, 230, 600, true, this.unclippedGC);
    }
    
    public void endGame() {
        this.unclippedGC.setFont(this.commentFont);
        this.unclippedGC.setColor(Color.white);
        this.drawShadowString("DO YOU REALLY WANT TO QUIT? (Y/N)", 0, 230, 600, true, this.unclippedGC);
    }
    
    public void loading() {
        this.unclippedGC.setColor(Color.black);
        this.unclippedGC.drawString("Loading, please wait..", 285, 150);
        this.unclippedGC.drawRect(239, 169, 186, 16);
        int n = 0;
        for (int i = 0; i < 12; ++i) {
            if (this.loadingIndicator[i]) {
                ++n;
            }
            else if (this.tracker.checkID(i)) {
                this.loadingIndicator[i] = true;
                ++n;
            }
        }
        this.unclippedGC.setColor(Color.blue);
        this.unclippedGC.fillRect(240, 170, n * 15 + 5, 15);
        if (this.doneLoading()) {
            this.unclippedGC.drawImage(this.cityPic, 0, 400, this);
            this.unclippedGC.drawImage(this.displayPic, 600, 405, this);
            this.drawFrame(0, 400, 667, 399, this.unclippedGC);
            this.resetGame();
            if (this.gameIntro.musicEnabled()) {
                this.playMusic("intromusic.htm");
            }
        }
    }
    
    public void intro() {
        if (this.musicRequested && this.gameIntro.transitionClosed()) {
            this.playMusic("intromusic.htm");
            this.musicRequested = false;
        }
    }
    
    public void gameOver() {
        this.unclippedGC.setFont(this.commentFont);
        this.unclippedGC.drawImage(this.displayPic, 600, 5, this);
        this.unclippedGC.setFont(this.commentFont);
        this.unclippedGC.setColor(Color.white);
        this.drawShadowString("GAME OVER", 0, 224, 600, true, this.unclippedGC);
        this.unclippedGC.setFont(this.displayFont);
        this.unclippedGC.setColor(Color.white);
        if (this.score <= 100) {
            this.drawShadowString("Nice try...", 0, 240, 600, true, this.unclippedGC);
        }
        else if (this.score > 100 && this.score <= 200) {
            this.drawShadowString("Not bad...", 0, 240, 600, true, this.unclippedGC);
        }
        else if (this.score > 200 && this.score <= 300) {
            this.drawShadowString("Well done!", 0, 240, 600, true, this.unclippedGC);
        }
        else if (this.score > 300 && this.score <= 400) {
            this.drawShadowString("Very good!!", 0, 240, 600, true, this.unclippedGC);
        }
        else {
            this.drawShadowString("Your're the champ!!!", 0, 240, 600, true, this.unclippedGC);
        }
        if (!this.hasEnteredHiscore) {
            this.readHiscores();
            if (this.score > 0 && (this.numberOfScores < 10 || this.score > this.strToScore(this.hiscoreTable[this.numberOfScores - 1]))) {
                this.repaint();
                this.yipeeSound.play();
                this.hiscoreDialog.enterHiscore(this.score);
                this.writeScoreToFile();
            }
            this.hasEnteredHiscore = true;
        }
    }
    
    public int strToScore(final String s) {
        int n = '\0';
        int n2 = '\u0001';
        for (int i = 3; i >= 0; --i) {
            final char c = (char)(s.charAt(i) - '0');
            if (c > -1 && c < '\n') {
                n += c * n2;
            }
            n2 *= '\n';
        }
        return n;
    }
    
    public boolean CliffAtCheers() {
        final Rectangle collision = this.Cliff.getCollision();
        return new Rectangle(this.Cliff.getPosX() + collision.x, this.Cliff.getPosY() + collision.y, collision.width, collision.height).intersects(this.Cheers);
    }
    
    public void inCheers() {
        if (System.currentTimeMillis() - this.beerTime > 300L && this.beerOMeter < 5) {
            ++this.beerOMeter;
            this.beerTime = System.currentTimeMillis();
        }
        this.offScrGC.drawImage(this.atCheersPic, 195, 105, this);
        this.drawFrame(195, 105, 215, 145, this.offScrGC);
    }
    
    public void playing() {
        if (this.musicRequested && this.gameIntro.transitionOpen()) {
            this.playMusic("gamemusic.htm");
            this.musicRequested = false;
        }
        for (int i = 0; i < 8; ++i) {
            if (this.cars[i].getStatus() != 0) {
                if (this.Cliff.collisionDetected(this.cars[i])) {
                    this.gameStatus = 4;
                    this.agonySound.play();
                }
                else if (this.cars[i].inHonkRange(this.Cliff)) {
                    this.cars[i].honk();
                }
            }
        }
        if (this.Cliff.collisionDetected(this.Fido) || this.Cliff.collisionDetected(this.Rufus)) {
            this.gameStatus = 4;
            this.agonySound.play();
        }
        if (this.Fido.inBarkRange(this.Cliff)) {
            this.Fido.bark();
        }
        if (this.Rufus.inBarkRange(this.Cliff)) {
            this.Rufus.bark();
        }
        if (this.Fido.collisionDetected(this.Rufus)) {
            this.Fido.changeCourse();
        }
        if (System.currentTimeMillis() - this.CliffTime > 302000L) {
            this.gameStatus = 5;
            this.pauseTime = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() - this.beerTime > 15000L) {
            --this.beerOMeter;
            this.beerTime = System.currentTimeMillis();
            if (this.beerOMeter == -1) {
                this.agonySound.play();
                this.gameStatus = 4;
            }
        }
        this.Rufus.checkMoves();
        this.Fido.checkMoves();
        this.Rufus.walk();
        this.Fido.walk();
        this.Cliff.walk();
        this.manageTraffic();
        this.unclippedGC.copyArea(0, 400, 668, 400, 0, -400);
        this.zOrderSort();
        for (int j = 0; j < 11; ++j) {
            if (this.zOrder[j] instanceof car) {
                if (((car)this.zOrder[j]).getStatus() != 0) {
                    this.zOrder[j].draw();
                }
            }
            else {
                this.zOrder[j].draw();
            }
        }
        this.drawArrow();
    }
    
    public void drawArrow() {
        this.arrowMove += (this.arrowUp ? -2 : 2);
        if (this.arrowMove < 0) {
            this.arrowUp = false;
            this.arrowMove = 0;
        }
        else if (this.arrowMove > 10) {
            this.arrowUp = true;
            this.arrowMove = 10;
        }
        this.unclippedGC.drawImage(this.arrowPic, this.mailBoxes[this.activeMailBox][0] + 8 + this.arrowMove / 2, this.mailBoxes[this.activeMailBox][1] - 10 - this.arrowMove, this);
    }
    
    public void lifeLost() {
        this.beerOMeter = 5;
        this.beerTime = System.currentTimeMillis();
        --this.CliffLives;
        this.Cliff.resetPos();
        this.Cliff.startBlinking();
        if (this.CliffLives == 0) {
            this.gameStatus = 5;
            this.pauseTime = System.currentTimeMillis();
            return;
        }
        this.gameStatus = 1;
    }
    
    public void drawDisplayInfo() {
        if (this.beerOMeter > 0) {
            this.unclippedGC.drawImage(this.mugPics[5 - this.beerOMeter], 608, 25, this);
        }
        else {
            this.unclippedGC.drawImage(this.mugPics[5], 608, 25, this);
        }
        for (int i = 0; i < this.CliffLives; ++i) {
            this.unclippedGC.drawImage(this.lifesPic, 632, 90 + 30 * i, this);
        }
        this.unclippedGC.setColor(Color.white);
        this.unclippedGC.setFont(this.displayFont);
        this.drawCenterString(String.valueOf(this.score), 602, 250, 60, this.unclippedGC);
        int n;
        int n2;
        if (this.gameStatus == 1 || this.gameStatus == 4) {
            n = (int)((302000L - (System.currentTimeMillis() - this.CliffTime)) / 60000L);
            n2 = (int)((302000L - (System.currentTimeMillis() - this.CliffTime)) / 1000L) - n * 60;
        }
        else {
            n = (int)((302000L - (this.pauseTime - this.CliffTime)) / 60000L);
            n2 = (int)((302000L - (this.pauseTime - this.CliffTime)) / 1000L) - n * 60;
        }
        this.drawCenterString(n + ":" + ((n2 < 10) ? "0" : "") + n2, 602, 293, 60, this.unclippedGC);
    }
    
    public void update(final Graphics graphics) {
        if (this.doneLoading()) {
            if (this.gameStatus != 0 && this.gameStatus != 7 && this.gameStatus != 8) {
                this.drawDisplayInfo();
            }
            if (this.gameIntro.inTransition() || this.gameStatus == 0) {
                this.gameIntro.draw();
                this.drawFrame(0, 0, 667, 399, this.unclippedGC);
            }
        }
        this.paint(graphics);
    }
    
    public Polygon strToPolyParse(final String s) {
        final int length = s.length();
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        final Polygon polygon = new Polygon();
        for (int i = 0; i <= length; ++i) {
            if (i == length || s.charAt(i) == ',') {
                final int int1 = Integer.parseInt(s.substring(n, i));
                n = i + 1;
                if (n3 != 0) {
                    polygon.addPoint(n2, int1);
                    n3 = 0;
                }
                else {
                    n2 = int1;
                    n3 = 1;
                }
            }
        }
        return polygon;
    }
    
    private void checkMailBoxes() {
        final Rectangle collision = this.Cliff.getCollision();
        if (new Rectangle(this.Cliff.getPosX() + collision.x, this.Cliff.getPosY() + collision.y, collision.width, collision.height).intersects(new Rectangle(this.mailBoxes[this.activeMailBox][0], this.mailBoxes[this.activeMailBox][1], 30, 30))) {
            this.mailSound.play();
            this.score += 10;
            this.CliffTime += 5000L;
            int i;
            for (i = Math.abs(this.random.nextInt()) % 17; i == this.activeMailBox; i = Math.abs(this.random.nextInt()) % 17) {}
            this.activeMailBox = i;
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        this.Cliff.checkMoves(event);
        if (this.gameIntro.inTransition()) {
            return true;
        }
        if (this.keyReleased) {
            this.keyReleased = false;
            if (this.gameStatus == 5) {
                this.resetGame();
                if (this.gameIntro.musicEnabled()) {
                    this.musicRequested = true;
                }
                this.gameIntro.doTransition();
                return true;
            }
            if ((n == 104 || n == 72 || n == 1008) && !this.helpWindow.isShowing()) {
                this.helpWindow.show();
            }
            if (this.gameStatus == 2) {
                this.gameStatus = 1;
                this.CliffTime += System.currentTimeMillis() - this.pauseTime;
                this.beerTime += System.currentTimeMillis() - this.pauseTime;
                return true;
            }
            if (this.gameStatus == 3) {
                this.gameStatus = 1;
                this.beerOMeter = 5;
                this.Cliff.resetPos();
                this.CliffTime += System.currentTimeMillis() - this.pauseTime;
                this.beerTime = System.currentTimeMillis();
                this.burpSound.play();
                if (this.gameIntro.musicEnabled()) {
                    this.musicRequested = true;
                }
                return true;
            }
            if (this.gameStatus == 6) {
                if (n == 121 || n == 89) {
                    this.gameIntro.doTransition();
                    this.resetGame();
                    this.gameStatus = 0;
                    if (this.gameIntro.musicEnabled()) {
                        this.musicRequested = true;
                    }
                }
                else {
                    this.gameStatus = 1;
                }
                this.CliffTime += System.currentTimeMillis() - this.pauseTime;
                this.beerTime += System.currentTimeMillis() - this.pauseTime;
                return true;
            }
            if (n == 13 || (n == 10 && this.gameStatus != 3)) {
                if (this.CliffAtCheers()) {
                    this.gameStatus = 3;
                    this.beerTime = System.currentTimeMillis();
                    this.pauseTime = System.currentTimeMillis();
                    if (this.gameIntro.musicEnabled()) {
                        this.playMusic("cheersmusic.htm");
                    }
                }
                else {
                    this.checkMailBoxes();
                }
            }
            else if (this.gameStatus == 1 && (n == 113 || n == 81 || n == 27)) {
                this.gameStatus = 6;
                this.pauseTime = System.currentTimeMillis();
            }
            else if (this.gameStatus == 1 && (n == 112 || n == 80)) {
                this.gameStatus = 2;
                this.pauseTime = System.currentTimeMillis();
            }
        }
        return true;
    }
    
    public boolean keyUp(final Event event, final int n) {
        this.Cliff.stop();
        return this.keyReleased = true;
    }
    
    public void drawFrame(final int n, final int n2, final int n3, final int n4, final Graphics graphics) {
        graphics.setColor(Color.lightGray);
        graphics.fillRect(n, n2, 5, n4);
        graphics.fillRect(n + n3 - 5, n2, 5, n4);
        graphics.fillRect(n, n2, n3, 5);
        graphics.fillRect(n, n2 + n4 - 5, n3, 5);
        graphics.setColor(Color.white);
        graphics.fillRect(n, n2 + 1, 1, n4 - 1);
        graphics.fillRect(n + n3 - 4, n2 + 4, 1, n4 - 8);
        graphics.fillRect(n + 4, n2 + n4 - 4, n3 - 8, 1);
        graphics.fillRect(n, n2, n3 - 1, 1);
        graphics.setColor(Color.darkGray);
        graphics.fillRect(n + 4, n2 + 4, 1, n4 - 8);
        graphics.fillRect(n + 4, n2 + 4, n3 - 8, 1);
        graphics.fillRect(n, n2 + n4, n3 + 1, 1);
        graphics.fillRect(n + n3, n2, 1, n4);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offScrImage, 0, 0, this);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.gameIntro.inTransition()) {
            return true;
        }
        if (this.gameStatus == 0) {
            final int checkButton = this.gameIntro.checkButton(n, n2);
            if (checkButton == 4) {
                if (!this.helpWindow.isShowing()) {
                    this.helpWindow.show();
                }
            }
            else if (checkButton == 2) {
                if (this.gameIntro.musicEnabled()) {
                    this.musicRequested = true;
                }
                this.gameIntro.doTransition();
                this.resetGame();
                this.gameStatus = 1;
            }
            else if (checkButton == 1) {
                this.gameIntro.changeMusic();
                this.playMusic(this.gameIntro.musicEnabled() ? "intromusic.htm" : "nomusic.htm");
            }
            else if (checkButton == 0) {
                this.readHiscores();
                this.gameIntro.doTransition();
                this.gameStatus = 7;
            }
            else if (checkButton == 3) {
                this.theQuote = this.CliffQuotes.getQuote();
                this.gameIntro.doTransition();
                this.gameStatus = 8;
            }
            return true;
        }
        if (this.gameStatus == 5) {
            this.resetGame();
            if (this.gameIntro.musicEnabled()) {
                this.musicRequested = true;
            }
            this.gameIntro.doTransition();
            return true;
        }
        if (this.gameStatus == 7 || this.gameStatus == 8) {
            this.gameIntro.doTransition();
            return true;
        }
        return true;
    }
    
    public cliffie() {
        this.loadingIndicator = new boolean[12];
        this.CliffLives = 4;
        this.beerOMeter = 5;
        this.gameStatus = -1;
        this.cars = new car[8];
        this.carQueue = new car[10];
        this.zOrder = new sprite[13];
        this.hiscoreTable = new String[10];
        this.hasEnteredHiscore = false;
        this.mugPics = new Image[6];
        this.roadPositions = new int[] { 61, 88, 200, 222 };
        this.carColors = new double[][] { { 0.5, 0.5, 1.5 }, { 0.3, 0.7, 0.1 }, { 1.0, 0.5, 0.3 }, { 0.2, 0.9, 0.8 }, { 0.8, 0.3, 0.7 }, { 0.8, 0.8, 0.1 }, { 0.4, 0.9, 1.5 }, { 0.3, 1.3, 1.0 } };
        this.activeMailBox = 3;
        this.mailBoxes = new int[][] { { 18, 32 }, { 107, 32 }, { 194, 32 }, { 282, 32 }, { 454, 32 }, { 542, 32 }, { 11, 175 }, { 39, 293 }, { 39, 361 }, { 176, 317 }, { 279, 317 }, { 434, 317 }, { 530, 317 }, { 528, 176 }, { 430, 176 }, { 280, 176 }, { 181, 176 } };
        this.arrowUp = true;
        this.musicRequested = false;
        this.keyReleased = true;
    }
}
