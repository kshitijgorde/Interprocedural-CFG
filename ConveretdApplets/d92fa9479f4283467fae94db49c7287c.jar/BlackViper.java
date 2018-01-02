import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.awt.Component;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.applet.AudioClip;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BlackViper extends Applet
{
    int WIDTH;
    int HEIGHT;
    int BACKIMG;
    int ICONIMG;
    int TITLEIMG;
    int BODYIMG;
    int CRASHIMG;
    int BUTIMG;
    int LEVELFILESIZE;
    boolean SOUND;
    boolean WIN;
    Color FOREGROUND;
    Color BACKGROUND;
    Color LIGHTBACK;
    Color ORANGE;
    Color GREEN;
    Color YELLOW;
    Color LIGHTBLUE;
    Color BLUE;
    Color DARKORANGE;
    Font normalFont;
    Font bigFont;
    Image offImg;
    Graphics offG;
    String[] globalImageNames;
    int[] globalImageSizes;
    String[] globalSoundNames;
    int[] globalSoundSizes;
    MediaTracker mediaTracker;
    Image[] globalImages;
    AudioClip[] globalSounds;
    String[] globalLines;
    String levelData;
    State state;
    State Game;
    Thread loader;
    
    public BlackViper() {
        this.WIDTH = 404;
        this.HEIGHT = 251;
        this.BACKIMG = 0;
        this.ICONIMG = 1;
        this.TITLEIMG = 2;
        this.BODYIMG = 3;
        this.CRASHIMG = 4;
        this.BUTIMG = 5;
        this.LEVELFILESIZE = 5;
        this.SOUND = true;
        this.WIN = false;
        this.FOREGROUND = Color.black;
        this.BACKGROUND = Color.orange;
        this.LIGHTBACK = new Color(255, 255, 200);
        this.ORANGE = new Color(255, 50, 5);
        this.GREEN = new Color(74, 174, 8);
        this.YELLOW = new Color(255, 240, 1);
        this.LIGHTBLUE = new Color(73, 176, 255);
        this.BLUE = new Color(43, 78, 255);
        this.DARKORANGE = new Color(158, 123, 0);
        this.normalFont = new Font("SansSerif", 1, 12);
        this.bigFont = new Font("SansSerif", 1, 43);
        this.globalImageNames = new String[] { "background.jpg", "icons.gif", "title.gif", "body.gif", "crash.gif", "buttons.jpg" };
        this.globalImageSizes = new int[] { 4, 3, 4, 2, 5, 6 };
        this.globalSoundNames = new String[] { "bvcherry.au", "bvcrash.au", "bvlevel.au", "bvlife.au", "bvnix.au", "bvover.au", "bvpopup.au", "bvscrape.au", "bvstart.au", "bvtick.au", "bvturn.au", "bvclick.au", "bvdrop.au" };
        this.globalSoundSizes = new int[] { 1, 9, 19, 8, 1, 15, 2, 3, 17, 1, 2, 1, 3 };
        this.mediaTracker = null;
        this.state = null;
        this.Game = null;
        this.loader = null;
    }
    
    public void init() {
        this.levelData = "down.gif";
        String s = this.getParameter("mylevel");
        if (s != null && s.equalsIgnoreCase("true")) {
            this.levelData = "bvlevel.dat";
        }
        s = this.getParameter("sound");
        if (s != null) {
            this.SOUND = !s.equalsIgnoreCase("false");
        }
        this.setFont(this.normalFont);
        this.offImg = this.createImage(this.WIDTH, this.HEIGHT);
        try {
            this.offG = this.offImg.getGraphics();
        }
        catch (Exception ex) {}
        this.Game = new Game();
        try {
            this.jbInit();
        }
        catch (Exception ex2) {}
        this.requestFocus();
        if (this.loader == null) {
            final Loader loaderState = new Loader();
            loaderState.init();
            this.loader = new Thread(loaderState);
            this.state = loaderState;
            this.loader.start();
        }
    }
    
    public void start() {
        this.requestFocus();
    }
    
    public void stop() {
        this.state.stop();
    }
    
    public void destroy() {
        this.Game = null;
        this.state = null;
    }
    
    private void jbInit() throws Exception {
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent e) {
                BlackViper.this.state.keyPressed(e);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                BlackViper.this.state.mousePressed(e);
            }
            
            public void mouseReleased(final MouseEvent e) {
                BlackViper.this.state.mouseReleased(e);
            }
        });
    }
    
    public void update(final Graphics g) {
        this.paint(this.offG);
        g.drawImage(this.offImg, 0, 0, this);
    }
    
    public void paint(final Graphics g) {
        if (this.state != null) {
            this.state.paint(g);
        }
    }
    
    public void setState(final State state) {
        (this.state = state).init();
        this.repaint();
        Thread.yield();
    }
    
    public void gotoURL(final String s) {
        try {
            final URL myurl = new URL(s);
            this.getAppletContext().showDocument(myurl, "_blank");
        }
        catch (MalformedURLException ex) {}
    }
    
    public void getLevel() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(this.levelData)));
            int num = 0;
            while (in.readLine() != null) {
                ++num;
            }
            in.close();
            in = null;
            in = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(this.levelData)));
            this.globalLines = new String[num];
            int i = 0;
            String l;
            while ((l = in.readLine()) != null) {
                this.globalLines[i] = l;
                ++i;
            }
            in.close();
        }
        catch (IOException ioe) {
            System.out.println(ioe);
        }
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
    
    class Loader implements State, Runnable
    {
        int totalKb;
        int loaded;
        int loadedKb;
        long start;
        int barWidth;
        int barX;
        int barY;
        String mm;
        
        Loader() {
            this.totalKb = 0;
            this.loaded = 0;
            this.loadedKb = 0;
            this.start = 0L;
            this.barWidth = 300;
        }
        
        public void init() {
            for (int a = 0; a < BlackViper.this.globalImageSizes.length; ++a) {
                this.totalKb += BlackViper.this.globalImageSizes[a];
            }
            if (BlackViper.this.SOUND) {
                for (int a = 0; a < BlackViper.this.globalSoundSizes.length; ++a) {
                    this.totalKb += BlackViper.this.globalSoundSizes[a];
                }
            }
            this.totalKb += BlackViper.this.LEVELFILESIZE;
            BlackViper.this.globalImages = new Image[BlackViper.this.globalImageNames.length];
            BlackViper.this.globalSounds = new AudioClip[BlackViper.this.globalSoundNames.length];
            this.barX = (BlackViper.this.WIDTH - this.barWidth) / 2;
            this.barY = (BlackViper.this.HEIGHT - 19) / 2 - 5;
            this.mm = "Loading images...";
        }
        
        public void run() {
            for (int a = 0; a < BlackViper.this.globalImageNames.length; ++a) {
                BlackViper.this.repaint();
                BlackViper.this.globalImages[a] = BlackViper.this.getImage(BlackViper.this.globalImageNames[a]);
                ++this.loaded;
                this.loadedKb += BlackViper.this.globalImageSizes[a];
            }
            this.mm = "Loading levels...";
            BlackViper.this.repaint();
            BlackViper.this.getLevel();
            this.loadedKb += BlackViper.this.LEVELFILESIZE;
            if (BlackViper.this.SOUND) {
                this.mm = "Loading sounds...";
                for (int a = 0; a < BlackViper.this.globalSoundNames.length; ++a) {
                    BlackViper.this.repaint();
                    BlackViper.this.globalSounds[a] = BlackViper.this.getAudioClip(BlackViper.this.getCodeBase(), "audio/".concat(String.valueOf(String.valueOf(BlackViper.this.globalSoundNames[a]))));
                    ++this.loaded;
                    this.loadedKb += BlackViper.this.globalSoundSizes[a];
                }
            }
            BlackViper.this.repaint();
            BlackViper.this.setState(BlackViper.this.Game);
            BlackViper.this.repaint();
        }
        
        private void drawRect(final Graphics g, final int x, final int y, final int w, final int h, final Color fill, final Color outline, final boolean innen) {
            if (innen) {
                g.setColor(fill);
                g.fillRect(x, y, w, h);
            }
            g.setColor(outline);
            g.drawRect(x, y, w, h);
        }
        
        public void paint(final Graphics g) {
            g.setFont(BlackViper.this.normalFont);
            g.setColor(BlackViper.this.BACKGROUND);
            g.fillRect(0, 0, BlackViper.this.WIDTH - 1, BlackViper.this.HEIGHT - 1);
            if (this.loaded > 1) {
                for (int i = 0; i < 5; ++i) {
                    for (int a = 0; a < 4; ++a) {
                        g.drawImage(BlackViper.this.globalImages[BlackViper.this.BACKIMG], 100 * i, 100 * a, null);
                    }
                }
                for (int i = 11; i < 404; i += 15) {
                    g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], i, -4, i + 15, 11, 15, 0, 30, 15, null);
                    g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], i, 240, i + 15, 255, 15, 0, 30, 15, null);
                }
                for (int i = 11; i < 260; i += 15) {
                    g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], -4, i, 11, i + 15, 30, 0, 45, 15, null);
                    g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], 393, i, 408, i + 15, 30, 0, 45, 15, null);
                }
                g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], -4, -4, 11, 11, 45, 0, 60, 15, null);
                g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], 393, -4, 408, 11, 45, 0, 60, 15, null);
                g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], -4, 240, 11, 255, 45, 0, 60, 15, null);
                g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], 393, 240, 408, 255, 45, 0, 60, 15, null);
                g.drawRect(0, 0, BlackViper.this.WIDTH - 1, BlackViper.this.HEIGHT - 1);
            }
            g.setColor(BlackViper.this.DARKORANGE);
            g.drawString("Copyright Fennex 2002", 265, 241);
            g.setColor(BlackViper.this.FOREGROUND);
            g.drawRect(0, 0, BlackViper.this.WIDTH - 1, BlackViper.this.HEIGHT - 1);
            int percent = 0;
            if (this.totalKb != 0) {
                percent = this.loadedKb * 100 / this.totalKb;
            }
            final int width = (this.barWidth - 6) * percent / 100;
            this.drawRect(g, this.barX, this.barY, this.barWidth, 16, BlackViper.this.BACKGROUND, BlackViper.this.FOREGROUND, false);
            this.drawRect(g, this.barX + 3, this.barY + 3, width, 10, BlackViper.this.FOREGROUND, BlackViper.this.FOREGROUND, true);
            g.setColor(BlackViper.this.FOREGROUND);
            g.drawString(this.mm, this.barX, this.barY - 5);
        }
        
        public void mousePressed(final MouseEvent e) {
        }
        
        public void mouseReleased(final MouseEvent e) {
        }
        
        public void keyPressed(final KeyEvent e) {
        }
        
        public void stop() {
        }
    }
    
    class Game implements State, Runnable
    {
        FButton bFennex;
        FButton bAdd;
        FButton bPlay;
        RepaintThread repainter;
        Thread thread;
        Vector bodyVector;
        Vector iconVector;
        Vector iconBufferVector;
        Tail tail;
        int cherry;
        int points;
        int lives;
        int level;
        int xHead;
        int yHead;
        int deltaX;
        int deltaY;
        int xMin;
        int xMax;
        int yMin;
        int yMax;
        int xCrash;
        int yCrash;
        int intCrash;
        int countDownNumber;
        int countText;
        int oilCount;
        int multiplier;
        int speedUp;
        int speedMulti;
        int cherryProLevel;
        int lifeBuffer;
        int dir;
        int dx;
        int dy;
        long threadDelay;
        int[] xMem;
        int[] yMem;
        boolean running;
        boolean runAnimation;
        boolean showText;
        boolean gameOver;
        boolean kingMode;
        boolean suddenDeath;
        boolean paused;
        boolean pausedOver;
        boolean collision;
        boolean soundOn;
        boolean crashLoop;
        String infoText;
        FontMetrics fm;
        Graphics g;
        
        Game() {
            this.xMem = new int[435];
            this.yMem = new int[435];
        }
        
        public void init() {
            this.cherryProLevel = 15;
            this.threadDelay = 20L;
            this.dir = 1;
            this.paused = false;
            this.pausedOver = false;
            this.suddenDeath = false;
            this.level = 0;
            this.points = 0;
            this.lives = 100;
            this.xMem[0] = 25;
            this.yMem[0] = 68;
            this.xHead = this.xMem[0] - 1;
            this.yHead = this.yMem[0] - 2;
            this.xMin = 0;
            this.xMax = 404;
            this.yMin = 0;
            this.yMax = 251;
            for (int i = 1; i < this.xMem.length; ++i) {
                this.xMem[i] = this.xMem[0];
                this.yMem[i] = this.yMem[0];
            }
            this.g = BlackViper.this.getGraphics();
            this.fm = this.g.getFontMetrics();
            this.runAnimation = false;
            this.kingMode = false;
            this.showText = false;
            this.gameOver = false;
            this.soundOn = true;
            this.bFennex = new FButton(BlackViper.this.globalImages[BlackViper.this.BUTIMG], 2, 3, 314, 9);
            this.bAdd = new FButton(BlackViper.this.globalImages[BlackViper.this.BUTIMG], 0, 1, 314, 25);
            this.bPlay = new FButton(BlackViper.this.globalImages[BlackViper.this.BUTIMG], 4, 5, 172, 95);
            this.tail = new Tail(this.xMem[0], this.yMem[0], BlackViper.this.globalImages[BlackViper.this.BODYIMG]);
            this.bodyVector = new Vector();
            this.addBody(3);
            this.iconVector = new Vector();
            this.iconBufferVector = new Vector();
            this.newLevel();
        }
        
        public void stop() {
            this.pause(true);
        }
        
        public void newLevel() {
            this.kingMode = false;
            this.speedMulti = 1;
            this.multiplier = 1;
            this.oilCount = 0;
            this.countText = 0;
            this.countDownNumber = 0;
            this.multiplier = 1;
            this.xMem[0] = 25;
            this.yMem[0] = 68;
            this.xHead = this.xMem[0] - 1;
            this.yHead = this.yMem[0] - 2;
            for (int i = 1; i < this.xMem.length; ++i) {
                this.xMem[i] = this.xMem[0];
                this.yMem[i] = this.yMem[0];
            }
            this.bodyVector.removeAllElements();
            this.iconVector.removeAllElements();
            this.addBody(3);
            this.tail = new Tail(this.xMem[0], this.yMem[0], BlackViper.this.globalImages[BlackViper.this.BODYIMG]);
            this.cherry = 0;
            ++this.level;
            BlackViper.this.WIN = this.setWalls(this.level);
            if (BlackViper.this.WIN) {
                --this.level;
            }
            boolean b = false;
            for (int j = 0; j < this.iconVector.size(); ++j) {
                final Icon ic = this.iconVector.elementAt(j);
                if (ic.getID() == 9) {
                    b = true;
                    break;
                }
            }
            if (!b) {
                this.addIcon(9);
            }
            BlackViper.this.repaint();
            if (!BlackViper.this.WIN) {
                this.thread = null;
                (this.thread = new Thread(this, "BlackViper Game Thread")).start();
            }
        }
        
        public void addBody(final int howMany) {
            final int s = this.bodyVector.size();
            for (int i = 7 * (s + 1) + 1; i < 7 * (howMany + s + 1) + 1; ++i) {
                this.xMem[i] = this.xMem[7 * (s + 1) + 1];
                this.yMem[i] = this.yMem[7 * (s + 1) + 1];
            }
            for (int i = 0; i < howMany; ++i) {
                final Body b = new Body(this.xMem[7 * s + 1], this.yMem[7 * s + 1], BlackViper.this.globalImages[BlackViper.this.BODYIMG]);
                this.bodyVector.addElement(b);
            }
        }
        
        public boolean detectCollision(final int x, final int y, final int width) {
            final boolean b = this.xHead < x + width && this.xHead + 15 > x && this.yHead < y + width && this.yHead + 15 > y;
            return b;
        }
        
        public boolean detectCollision(final int x, final int y, final int width, final int contractHead) {
            final boolean b = this.xHead + contractHead < x + width && this.xHead + 15 - contractHead > x && this.yHead + contractHead < y + width && this.yHead + 15 - contractHead > y;
            return b;
        }
        
        public void pause(final boolean b) {
            if (!(this.paused = b)) {
                (this.thread = new Thread(this, "BlackViper Game Thread")).start();
                this.pausedOver = true;
                this.showText = false;
                if (this.crashLoop && this.soundOn && BlackViper.this.SOUND) {
                    BlackViper.this.globalSounds[7].loop();
                }
                BlackViper.this.repaint(50, 64, 300, 16);
            }
            else {
                this.thread = null;
                if (this.crashLoop && BlackViper.this.SOUND) {
                    BlackViper.this.globalSounds[7].stop();
                }
                this.setText("Game paused. Press 'P' to continue");
            }
        }
        
        public void scanForRepaintRect() {
            this.xMin = this.xHead;
            this.xMax = this.xHead;
            this.yMin = this.yHead;
            this.yMax = this.yHead;
            for (int i = 1; i < this.bodyVector.size() + 2; ++i) {
                if (this.xMin > this.xMem[7 * i + 1]) {
                    this.xMin = this.xMem[7 * i + 1];
                }
                if (this.xMax < this.xMem[7 * i + 1]) {
                    this.xMax = this.xMem[7 * i + 1];
                }
                if (this.yMin > this.yMem[7 * i + 1]) {
                    this.yMin = this.yMem[7 * i + 1];
                }
                if (this.yMax < this.yMem[7 * i + 1]) {
                    this.yMax = this.yMem[7 * i + 1];
                }
            }
            this.xMin -= 10;
            this.yMin -= 10;
            this.xMax += 20;
            this.yMax += 20;
        }
        
        public void addIcon(final int iconID) {
            final int x = (int)(373 * Math.random() + 8);
            final int y = (int)(179 * Math.random() + 50);
            if (this.xHead < x + 15 && this.xHead + 15 > x && this.yHead < x + 15 && this.yHead > y) {
                this.addIcon(iconID);
                return;
            }
            for (int i = 1; i < this.bodyVector.size() + 2; ++i) {
                if (this.xMem[7 * i + 1] < x + 15 && this.xMem[7 * i + 1] + 15 > x && this.yMem[7 * i + 1] < y + 15 && this.yMem[7 * i + 1] + 15 > y) {
                    this.addIcon(iconID);
                    return;
                }
            }
            for (int i = 0; i < this.iconVector.size(); ++i) {
                final Icon ic = this.iconVector.elementAt(i);
                final int ix = ic.getX();
                final int iy = ic.getY();
                if (ix < x + 15 && ix + 15 > x && iy < y + 15 && iy + 15 > y) {
                    this.addIcon(iconID);
                    return;
                }
            }
            final Icon ic2 = new Icon(BlackViper.this.globalImages[BlackViper.this.ICONIMG], iconID, x, y);
            this.iconVector.addElement(ic2);
            BlackViper.this.repaint(x, y, 15, 15);
        }
        
        public void addIcon(final int iconID, final int howMany) {
            for (int i = 0; i < howMany; ++i) {
                this.addIcon(iconID);
            }
        }
        
        public void addIcon(final int x, final int y, final int id) {
            final Icon ic = new Icon(BlackViper.this.globalImages[BlackViper.this.ICONIMG], id, x + 7, y + 49);
            this.iconVector.addElement(ic);
        }
        
        public void gotAnIcon(int id, final int index) {
            if (this.kingMode && id != 9 && id != 6 && id != 7) {
                id = 0;
            }
            if (id > 3 && id != 13) {
                final Icon ic = this.iconVector.elementAt(index);
                final int x = ic.getX();
                final int y = ic.getY();
                this.iconVector.removeElementAt(index);
                BlackViper.this.repaint(x, y, 15, 15);
            }
            Label_1258: {
                switch (id) {
                    case 9: {
                        this.points += this.multiplier;
                        ++this.cherry;
                        this.playSound(0);
                        if (this.cherry >= this.cherryProLevel) {
                            this.running = false;
                            break;
                        }
                        for (int i = 0; i < this.iconBufferVector.size(); ++i) {
                            final IconBuffer ib = this.iconBufferVector.elementAt(i);
                            ib.randomCheck();
                        }
                        this.addBody(3);
                        this.addIcon(9);
                        break;
                    }
                    case 7: {
                        ++this.points;
                        this.playSound(3);
                        this.lives += (int)(40 * Math.random()) + 10;
                        if (this.lives > 500) {
                            this.lives = 500;
                        }
                        this.setText("More Health");
                        break;
                    }
                    case 8: {
                        this.playSound(12);
                        this.points += 5;
                        int height = 46;
                        if (this.yHead + height > 229) {
                            height = 229 - this.yHead + 1;
                        }
                        for (int j = 0; j < 7 * (this.bodyVector.size() + 1); ++j) {
                            this.yMem[j] += height;
                        }
                        this.yHead += height;
                        this.setText("2 inch Drop-Down");
                        BlackViper.this.repaint();
                        break;
                    }
                    case 6: {
                        this.playSound(3);
                        final int ii = (int)(Math.random() * 15) + 2;
                        this.points += this.multiplier * ii;
                        this.setText("Extra Cash");
                        break;
                    }
                    case 5: {
                        this.playSound(11);
                        this.dx = 1;
                        this.dy = 1;
                        if (Math.random() < 0.5) {
                            this.dx = -1;
                        }
                        if (Math.random() < 0.5) {
                            this.dy = -1;
                        }
                        ++this.points;
                        this.oilCount = ((this.oilCount >= 1) ? 2 : 1);
                        this.setText("Attention: Oil on road!");
                        break;
                    }
                    case 4: {
                        this.playSound(0);
                        this.points += 3;
                        this.speedUp = ((this.speedUp > 1) ? 2 : 1);
                        this.setText("Speed Up");
                        break;
                    }
                    case 11: {
                        this.playSound(3);
                        this.kingMode = true;
                        this.points += 2;
                        this.setText("You are the King");
                        break;
                    }
                    case 13: {
                        this.running = false;
                        this.runAnimation = true;
                        this.suddenDeath = true;
                        break;
                    }
                    case 10: {
                        this.playSound(9);
                        this.setText("Turn the Switch");
                        this.points += 2;
                        final int s = 7 * (this.bodyVector.size() + 1);
                        for (int sx = s / 2, k = 0; k < sx; ++k) {
                            int a = this.xMem[s - k];
                            this.xMem[s - k] = this.xMem[k];
                            this.xMem[k] = a;
                            a = this.yMem[s - k];
                            this.yMem[s - k] = this.yMem[k];
                            this.yMem[k] = a;
                        }
                        this.deltaX = 0;
                        this.deltaY = 0;
                        switch (this.tail.getDir()) {
                            case 12: {
                                this.deltaX = 2;
                                break Label_1258;
                            }
                            case 24: {
                                this.deltaX = -2;
                                break Label_1258;
                            }
                            case 36: {
                                this.deltaY = 2;
                                break Label_1258;
                            }
                            case 48: {
                                this.deltaY = -2;
                                break Label_1258;
                            }
                            default: {
                                break Label_1258;
                            }
                        }
                        break;
                    }
                    case 1: {
                        if (!this.kingMode) {
                            if (this.deltaY != 0) {
                                this.deltaY = 0;
                                this.deltaX = ((Math.random() > 0.5) ? 2 : -2);
                            }
                            --this.lives;
                            this.collision = true;
                        }
                        BlackViper.this.repaint(210, 27, 82, 8);
                        break;
                    }
                    case 2: {
                        if (!this.kingMode) {
                            if (this.deltaX != 0) {
                                this.deltaX = 0;
                                this.deltaY = ((Math.random() > 0.5) ? 2 : -2);
                            }
                            --this.lives;
                            this.collision = true;
                        }
                        BlackViper.this.repaint(210, 27, 82, 8);
                        break;
                    }
                    case 3: {
                        if (!this.kingMode) {
                            final Icon ic2 = this.iconVector.elementAt(index);
                            final int x2 = ic2.getX();
                            final int y2 = ic2.getY();
                            if (this.deltaY == 0) {
                                this.deltaX = 0;
                                if (this.deltaY - y2 < -11) {
                                    this.deltaY = -2;
                                }
                                else if (this.deltaY - y2 > 9) {
                                    this.deltaY = 2;
                                }
                                else {
                                    this.deltaY = ((Math.random() > 0.5) ? 2 : -2);
                                }
                            }
                            if (this.deltaX == 0) {
                                this.deltaY = 0;
                                if (this.deltaX - x2 < -11) {
                                    this.deltaX = -2;
                                }
                                else if (this.deltaX - x2 > 9) {
                                    this.deltaX = 2;
                                }
                                else {
                                    this.deltaX = ((Math.random() > 0.5) ? 2 : -2);
                                }
                            }
                            --this.lives;
                            this.collision = true;
                        }
                        BlackViper.this.repaint(210, 27, 82, 8);
                        break;
                    }
                }
            }
            BlackViper.this.repaint(120, 8, 190, 33);
            if (this.points % 100 == 0 && this.points > 0 && id > 3) {
                this.addIcon(11);
                this.playSound(6);
            }
        }
        
        public void setText(final String s) {
            this.infoText = s;
            this.showText = true;
            this.countText = 0;
            BlackViper.this.repaint(50, 64, 300, 16);
        }
        
        public void run() {
            if (!this.pausedOver) {
                if (this.level > 1) {
                    this.playSound(2);
                    this.setText(String.valueOf(String.valueOf(new StringBuffer("Level: ").append(String.valueOf(this.level)).append(" - Cherry: ").append(String.valueOf(this.cherryProLevel)))));
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (InterruptedException ex) {}
                }
                BlackViper.this.repaint();
                for (int i = 3; i > 0; --i) {
                    this.countDownNumber = i;
                    this.playSound(9);
                    BlackViper.this.repaint(180, 80, 50, 50);
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (InterruptedException ex2) {}
                }
                this.showText = false;
                this.countDownNumber = 0;
                BlackViper.this.repaint();
                this.running = true;
                switch (this.dir) {
                    case 1: {
                        this.lifeBuffer = 3;
                        break;
                    }
                    case 2: {
                        this.lifeBuffer = 6;
                        break;
                    }
                }
                for (int i = 0; i < this.iconBufferVector.size(); ++i) {
                    final IconBuffer ib = this.iconBufferVector.elementAt(i);
                    ib.randomCheck();
                }
                this.playSound(8);
            }
            (this.repainter = new RepaintThread(25)).startThread();
            this.pausedOver = false;
            this.crashLoop = false;
            while (this.running && !this.paused) {
                final long beforeTime = System.currentTimeMillis();
                if (this.speedUp == 1) {
                    ++this.multiplier;
                    this.speedMulti = 2;
                }
                if (this.speedUp > 0) {
                    ++this.speedUp;
                    if (this.speedUp > 120) {
                        --this.multiplier;
                        this.speedMulti = 1;
                        this.speedUp = 0;
                    }
                }
                for (int j = this.xMem.length - 1; j > 0; --j) {
                    this.xMem[j] = this.xMem[j - 1];
                    this.yMem[j] = this.yMem[j - 1];
                }
                final int[] xMem = this.xMem;
                final int n = 0;
                xMem[n] += this.deltaX * this.speedMulti;
                final int[] yMem = this.yMem;
                final int n2 = 0;
                yMem[n2] += this.deltaY * this.speedMulti;
                this.xHead = this.xMem[0] - 1;
                this.yHead = this.yMem[0] - 2;
                final int s = this.bodyVector.size();
                for (int k = 0; k < s; ++k) {
                    final Body b = this.bodyVector.elementAt(k);
                    b.move(this.xMem[7 * (k + 1) + 1], this.yMem[7 * (k + 1) + 1]);
                }
                this.tail.move(this.xMem[7 * (s + 1) + 1], this.yMem[7 * (s + 1) + 1], this.xMem[7 * (s + 1)], this.yMem[7 * (s + 1)]);
                if (this.oilCount == 1) {
                    this.multiplier += 2;
                }
                if (this.oilCount > 0) {
                    for (int k = 0; k < 7 * (this.bodyVector.size() + 1); ++k) {
                        this.xMem[k] += this.dx;
                        this.yMem[k] += this.dy;
                    }
                    this.xHead += this.dx;
                    this.yHead += this.dy;
                    ++this.oilCount;
                    if (this.oilCount > 40 || this.xHead < 6 || this.xHead > 382 || this.yHead > 229 || this.yHead < 48) {
                        this.oilCount = 0;
                        this.multiplier -= 2;
                    }
                }
                this.collision = false;
                for (int k = 2; k < s + 2; ++k) {
                    final boolean bb = this.detectCollision(this.xMem[7 * k + 1] + 4, this.yMem[7 * k + 1] + 4, 4, 5);
                    if (bb) {
                        if (this.lifeBuffer > 0) {
                            --this.lifeBuffer;
                        }
                        else {
                            if (!this.kingMode) {
                                --this.lives;
                                this.collision = true;
                            }
                            BlackViper.this.repaint(210, 27, 82, 8);
                        }
                    }
                }
                for (int k = 0; k < this.iconVector.size(); ++k) {
                    final Icon ic = this.iconVector.elementAt(k);
                    final int a = ic.detectCollision(this.xHead, this.yHead);
                    if (a > 0) {
                        this.gotAnIcon(a, k);
                    }
                }
                if (this.yHead > 229 || this.yHead < 48) {
                    if (!this.kingMode && this.deltaY != 0) {
                        this.deltaY = 0;
                        this.deltaX = ((Math.random() > 0.5) ? 2 : -2);
                    }
                    --this.lives;
                    this.collision = true;
                    BlackViper.this.repaint(210, 27, 82, 8);
                }
                if (this.xHead < 6 || this.xHead > 382) {
                    if (this.deltaX != 0) {
                        this.deltaX = 0;
                        this.deltaY = ((Math.random() > 0.5) ? 2 : -2);
                    }
                    --this.lives;
                    this.collision = true;
                    BlackViper.this.repaint(210, 27, 82, 8);
                }
                if (this.collision && !this.crashLoop && this.soundOn && BlackViper.this.SOUND) {
                    BlackViper.this.globalSounds[7].loop();
                    this.crashLoop = true;
                }
                if (!this.collision && this.crashLoop && BlackViper.this.SOUND) {
                    BlackViper.this.globalSounds[7].stop();
                    this.crashLoop = false;
                }
                if (this.showText) {
                    ++this.countText;
                    if (this.countText >= 50) {
                        this.countText = 0;
                        this.showText = false;
                        BlackViper.this.repaint(50, 64, 300, 16);
                    }
                }
                if (this.lives < 1) {
                    this.running = false;
                    this.runAnimation = true;
                }
                final long afterTime = System.currentTimeMillis();
                long delta = afterTime - beforeTime;
                if (delta > 10) {
                    delta = 10L;
                }
                try {
                    Thread.sleep(this.threadDelay - delta);
                    continue;
                }
                catch (InterruptedException ie) {}
                break;
            }
            this.repainter.stopThread();
            this.repainter = null;
            if (this.crashLoop && BlackViper.this.SOUND) {
                BlackViper.this.globalSounds[1].stop();
            }
            if (this.runAnimation) {
                if (BlackViper.this.SOUND) {
                    BlackViper.this.globalSounds[7].stop();
                }
                this.playSound(1);
                this.xCrash = this.xHead - 12;
                this.yCrash = this.yHead - 7;
                for (int j = 0; j < 22; ++j) {
                    this.intCrash = j;
                    BlackViper.this.repaint(this.xCrash, this.yCrash, 40, 30);
                    try {
                        Thread.sleep(60L);
                    }
                    catch (InterruptedException ie) {
                        break;
                    }
                }
                if (this.suddenDeath) {
                    this.setText("Sudden Death, Game Over");
                }
                else {
                    this.setText("Game Over");
                }
                this.runAnimation = false;
                this.gameOver = true;
                BlackViper.this.repaint(this.xCrash, this.yCrash, 40, 30);
                BlackViper.this.repaint(170, 95, 61, 16);
            }
            else if (!this.paused) {
                this.newLevel();
            }
        }
        
        public void paint(final Graphics g) {
            for (int i = 0; i < 5; ++i) {
                for (int a = 0; a < 4; ++a) {
                    g.drawImage(BlackViper.this.globalImages[BlackViper.this.BACKIMG], 100 * i, 100 * a, null);
                }
            }
            g.setFont(BlackViper.this.normalFont);
            g.setColor(BlackViper.this.DARKORANGE);
            g.drawString("Copyright Fennex 2002", 265, 241);
            g.setColor(BlackViper.this.FOREGROUND);
            for (int i = 11; i < 404; i += 15) {
                g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], i, -4, i + 15, 11, 15, 0, 30, 15, null);
                g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], i, 240, i + 15, 255, 15, 0, 30, 15, null);
                g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], i, 38, i + 15, 53, 15, 0, 30, 15, null);
            }
            for (int i = 11; i < 260; i += 15) {
                g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], -4, i, 11, i + 15, 30, 0, 45, 15, null);
                g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], 393, i, 408, i + 15, 30, 0, 45, 15, null);
            }
            g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], -4, -4, 11, 11, 45, 0, 60, 15, null);
            g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], 393, -4, 408, 11, 45, 0, 60, 15, null);
            g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], -4, 240, 11, 255, 45, 0, 60, 15, null);
            g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], 393, 240, 408, 255, 45, 0, 60, 15, null);
            g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], -4, 38, 11, 53, 45, 0, 60, 15, null);
            g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], 393, 38, 408, 53, 45, 0, 60, 15, null);
            g.drawRect(0, 0, BlackViper.this.WIDTH - 1, BlackViper.this.HEIGHT - 1);
            g.drawImage(BlackViper.this.globalImages[BlackViper.this.TITLEIMG], 9, 8, null);
            this.bFennex.paint(g);
            this.bAdd.paint(g);
            for (int i = 0; i < this.iconVector.size(); ++i) {
                final Icon ic = this.iconVector.elementAt(i);
                ic.paint(g);
            }
            g.setFont(BlackViper.this.normalFont);
            g.drawString("Points:", 125, 22);
            g.drawString(String.valueOf(this.points), 170, 22);
            g.drawString("Level:", 125, 36);
            g.drawString(String.valueOf(this.level), 170, 36);
            g.drawString("Cherry:", 210, 22);
            g.drawString(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(String.valueOf(this.cherry)))).append("/").append(String.valueOf(this.cherryProLevel)))), 255, 22);
            g.setColor(Color.black);
            g.drawRect(209, 27, 81, 8);
            final int z = (this.lives - 1) / 100;
            switch (z) {
                case 4:
                case 5: {
                    g.setColor(BlackViper.this.BLUE);
                    break;
                }
                case 3: {
                    g.setColor(BlackViper.this.LIGHTBLUE);
                    break;
                }
                case 2: {
                    g.setColor(BlackViper.this.GREEN);
                    break;
                }
                case 1: {
                    g.setColor(BlackViper.this.YELLOW);
                    break;
                }
                case 0: {
                    g.setColor(BlackViper.this.ORANGE);
                    break;
                }
            }
            if (this.lives > 0) {
                int l = this.lives % 100;
                if (l == 0) {
                    l = 100;
                }
                final int w = (int)(0.8 * l);
                g.fillRect(210, 28, w, 7);
            }
            if (!BlackViper.this.WIN) {
                this.tail.paint(g);
                for (int j = this.bodyVector.size() - 1; j >= 0; --j) {
                    final Body b = this.bodyVector.elementAt(j);
                    b.paint(g);
                }
                if (this.kingMode) {
                    g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], this.xHead, this.yHead, this.xHead + 15, this.yHead + 15, 180, 0, 195, 15, null);
                }
                else {
                    g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], this.xHead, this.yHead, this.xHead + 15, this.yHead + 15, 0, 0, 15, 15, null);
                }
            }
            if (this.showText) {
                final int wi = this.fm.stringWidth(this.infoText);
                final int x = (404 - wi) / 2;
                g.setColor(BlackViper.this.LIGHTBACK);
                g.fillRect(x - 7, 65, wi + 13, 14);
                g.setColor(BlackViper.this.BLUE);
                g.drawRect(x - 7, 65, wi + 13, 14);
                g.drawString(this.infoText, x, 77);
            }
            if (this.countDownNumber > 0) {
                g.setColor(BlackViper.this.BACKGROUND);
                g.fillOval(182, 84, 40, 40);
                g.setFont(BlackViper.this.bigFont);
                g.setColor(BlackViper.this.BLUE);
                final FontMetrics fm2 = g.getFontMetrics(BlackViper.this.bigFont);
                final String s = String.valueOf(this.countDownNumber);
                final int x2 = (BlackViper.this.WIDTH - fm2.stringWidth(s)) / 2;
                g.drawString(s, x2, 120);
                switch (this.dir) {
                    case 1: {
                        g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], this.xHead + 18, this.yHead + 1, this.xHead + 33, this.yHead + 16, 210, 0, 225, 15, null);
                        break;
                    }
                    case 2: {
                        g.drawImage(BlackViper.this.globalImages[BlackViper.this.ICONIMG], this.xHead, this.yHead + 18, this.xHead + 15, this.yHead + 33, 225, 0, 240, 15, null);
                        break;
                    }
                }
            }
            if (this.gameOver) {
                this.bPlay.paint(g);
                g.setColor(BlackViper.this.BACKGROUND);
                g.drawRect(171, 94, 61, 16);
            }
            if (this.runAnimation) {
                g.drawImage(BlackViper.this.globalImages[BlackViper.this.CRASHIMG], this.xCrash, this.yCrash, this.xCrash + 40, this.yCrash + 30, 40 * this.intCrash, 0, 40 * this.intCrash + 40, 30, null);
            }
            if (BlackViper.this.WIN) {
                g.setFont(new Font("TimesRoman", 1, 25));
                g.setColor(Color.black);
                g.drawString("You have won it all!", 95, 85);
                g.setFont(new Font("TimesRoman", 1, 20));
                g.setColor(BlackViper.this.BLUE);
                g.drawString("Your Score:", 120, 150);
                g.drawString(String.valueOf(this.points), 240, 150);
                g.setColor(Color.black);
                g.setFont(BlackViper.this.normalFont);
                g.drawString("Thanks for playing BlackViper", 118, 180);
                this.bPlay.paint(g);
            }
        }
        
        public void mousePressed(final MouseEvent e) {
            final int x = e.getX();
            final int y = e.getY();
            if (this.bFennex.checkMousePressed(x, y)) {}
            if (this.bAdd.checkMousePressed(x, y)) {}
            if (this.bPlay.checkMousePressed(x, y) && this.gameOver) {
                BlackViper.this.repaint(170, 95, 61, 16);
            }
            BlackViper.this.repaint(300, 8, 100, 34);
        }
        
        public void mouseReleased(final MouseEvent e) {
            final int x = e.getX();
            final int y = e.getY();
            if (this.bFennex.checkMouseReleased(x, y) == 2) {
                this.playSound(11);
                BlackViper.this.gotoURL("http://www.fennex.de");
            }
            if (this.bAdd.checkMouseReleased(x, y) == 2) {
                this.playSound(11);
                BlackViper.this.gotoURL("http://www.fennex.de/web/add/blackviper.html");
            }
            if (this.bPlay.checkMouseReleased(x, y) == 2 && (this.gameOver || BlackViper.this.WIN)) {
                BlackViper.this.repaint(170, 95, 61, 16);
                this.lives = 100;
                this.showText = false;
                this.gameOver = false;
                this.runAnimation = false;
                this.points = 0;
                this.level = 0;
                this.newLevel();
            }
            BlackViper.this.repaint(300, 8, 100, 34);
        }
        
        public void playSound(final int number) {
            if (this.soundOn && BlackViper.this.SOUND) {
                BlackViper.this.globalSounds[number].play();
                BlackViper.this.globalSounds[4].play();
            }
        }
        
        public void keyPressed(final KeyEvent e) {
            switch (e.getKeyCode()) {
                case 39: {
                    if (this.deltaX != -2 && this.running && !this.paused) {
                        this.deltaX = 2;
                        this.deltaY = 0;
                        this.playSound(10);
                        break;
                    }
                    break;
                }
                case 37: {
                    if (this.deltaX != 2 && this.running && !this.paused) {
                        this.deltaX = -2;
                        this.deltaY = 0;
                        this.playSound(10);
                        break;
                    }
                    break;
                }
                case 40: {
                    if (this.deltaY != -2 && this.running && !this.paused) {
                        this.deltaX = 0;
                        this.deltaY = 2;
                        this.playSound(10);
                        break;
                    }
                    break;
                }
                case 38: {
                    if (this.deltaY != 2 && this.running && !this.paused) {
                        this.deltaX = 0;
                        this.deltaY = -2;
                        this.playSound(10);
                        break;
                    }
                    break;
                }
                case 80: {
                    if (this.countDownNumber == 0 && !this.runAnimation && !this.gameOver && this.running) {
                        this.playSound(11);
                        this.pause(!this.paused);
                        break;
                    }
                    break;
                }
                case 83: {
                    if (BlackViper.this.SOUND) {
                        this.soundOn = !this.soundOn;
                        if (this.soundOn) {
                            this.setText("Sound: ON");
                        }
                        else {
                            this.setText("Sound: OFF");
                        }
                        this.playSound(11);
                        break;
                    }
                    break;
                }
                case 88: {
                    this.running = false;
                    break;
                }
                case 89: {
                    this.level -= 2;
                    this.running = false;
                    break;
                }
            }
        }
        
        public boolean setWalls(final int level) {
            this.iconVector.removeAllElements();
            this.iconBufferVector.removeAllElements();
            if (4 * (level - 1) < BlackViper.this.globalLines.length) {
                final String[] lines = new String[4];
                for (int i = 0; i < 4; ++i) {
                    lines[i] = BlackViper.this.globalLines[4 * (level - 1) + i];
                }
                try {
                    while (lines[0].length() > 0) {
                        final int x = Integer.parseInt(lines[0].substring(0, lines[0].indexOf(",")));
                        final int y = Integer.parseInt(lines[1].substring(0, lines[1].indexOf(",")));
                        final int t = Integer.parseInt(lines[2].substring(0, lines[2].indexOf(",")));
                        lines[0] = lines[0].substring(lines[0].indexOf(",") + 1, lines[0].length());
                        lines[1] = lines[1].substring(lines[1].indexOf(",") + 1, lines[1].length());
                        lines[2] = lines[2].substring(lines[2].indexOf(",") + 1, lines[2].length());
                        this.addIcon(x, y, t);
                    }
                }
                catch (Exception ex) {}
                final int x = Integer.parseInt(lines[0]);
                final int y = Integer.parseInt(lines[1]);
                final int t = Integer.parseInt(lines[2]);
                this.addIcon(x, y, t);
                final String s = lines[3].substring(0, 1);
                this.dir = Integer.parseInt(s);
                final String ch = lines[3].substring(2);
                this.cherryProLevel = Integer.parseInt(ch);
                if (this.dir == 1) {
                    this.deltaX = 2;
                    this.deltaY = 0;
                }
                else {
                    this.deltaX = 0;
                    this.deltaY = 2;
                }
                final IconBuffer ib = new IconBuffer(7, 1.0, this.cherryProLevel);
                final IconBuffer ib2 = new IconBuffer(6, 0.5, this.cherryProLevel);
                this.iconBufferVector.addElement(ib);
                this.iconBufferVector.addElement(ib2);
                return false;
            }
            return true;
        }
        
        public class IconBuffer implements Runnable
        {
            int iconID;
            double howMany;
            double bodies;
            Thread thread;
            
            public IconBuffer(final int iconID, double howMany, final double bodies) {
                this.iconID = iconID;
                this.howMany = howMany;
                this.bodies = bodies;
                if (howMany > bodies) {
                    howMany = bodies;
                }
            }
            
            public void randomCheck() {
                final double d = this.howMany / this.bodies;
                final double r = Math.random();
                if (r < d) {
                    (this.thread = new Thread(this, "BlackViper TimeOut Thread")).start();
                }
            }
            
            public void run() {
                final long ms = (long)(Math.random() * 3000) + 500;
                try {
                    Thread.sleep(ms);
                }
                catch (InterruptedException ex) {}
                Game.this.addIcon(this.iconID);
                Game.this.playSound(6);
            }
        }
        
        public class RepaintThread extends Thread
        {
            long delay;
            long delta;
            boolean running;
            
            public RepaintThread(final int fps) {
                this.delay = 1000 / fps;
                this.delta = 0L;
                this.running = false;
                this.setName("BlackViper Repaint Thread");
            }
            
            public void stopThread() {
                this.running = false;
            }
            
            public void startThread() {
                this.running = true;
                this.start();
            }
            
            public boolean isRunning() {
                return this.running;
            }
            
            public void run() {
                long lastTime = System.currentTimeMillis();
                long curTime = 0L;
                while (this.running) {
                    Game.this.scanForRepaintRect();
                    BlackViper.this.repaint(Game.this.xMin, Game.this.yMin, Game.this.xMax - Game.this.xMin, Game.this.yMax - Game.this.yMin);
                    Thread.yield();
                    curTime = System.currentTimeMillis();
                    this.delta = this.delay - (int)(curTime - lastTime);
                    if (this.delta < 10) {
                        this.delta = 10L;
                    }
                    try {
                        Thread.sleep(this.delta);
                    }
                    catch (InterruptedException ie) {
                        System.out.println(ie);
                        break;
                    }
                    lastTime = System.currentTimeMillis();
                }
            }
        }
    }
    
    interface State
    {
        void init();
        
        void stop();
        
        void paint(final Graphics p0);
        
        void mousePressed(final MouseEvent p0);
        
        void mouseReleased(final MouseEvent p0);
        
        void keyPressed(final KeyEvent p0);
    }
}
