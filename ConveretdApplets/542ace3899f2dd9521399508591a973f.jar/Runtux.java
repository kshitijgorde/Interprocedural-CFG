import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.FontMetrics;
import java.applet.AudioClip;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Runtux extends Applet implements KeyListener, Runnable, MouseListener
{
    private Map map;
    private Image buff;
    private Image tuxImg;
    private Image tileImg;
    private Image coinImg;
    private Image titleImg;
    private Image scoreImg;
    private Image badGuyImg;
    private Image fishImg;
    private Image pointyImg;
    private Image trapImg;
    private Image keyImg;
    private Image heartImg;
    private Image lockImg;
    private Image gemImg;
    private Image exitImg;
    private Image loadImg;
    private Image infoImg;
    private Image textImg;
    private Image logo2Img;
    private int tuxFrame;
    private int tuxFacing;
    private int tuxDir;
    private int downSpeed;
    private int rightSpeed;
    private int stunOffs;
    private int score;
    private int keyCount;
    private int heartCount;
    private int lifeCount;
    private static final int MAX_HEART_COUNT = 3;
    private Color bg;
    private static final int COUNT_IMAGES = 17;
    private int countImagesLoaded;
    private static final int COUNT_SOUNDS = 9;
    private int countSoundsLoaded;
    private Rectangle tuxBounds;
    private Sprite coin;
    private LinkedList sprites;
    private LinkedList decoList;
    private static final int INIT = 1;
    private static final int PLAYING = 2;
    private static final int TITLE = 3;
    private static final int QUIT = 4;
    private static final int DEAD = 5;
    private static final int LEVEL_END = 6;
    private static final int STOPPED = 7;
    private static final int LEFT = 2;
    private static final int RIGHT = 0;
    private static final int GRAVITY = 3;
    private static final int JUMP = -18;
    private static final int FRACTION = 1;
    private static final int FRAME_WAIT_MAX = 3;
    private static final int MAX_HORIZ_SPEED = 16;
    private static final int MAX_INC_VALUE = 4;
    private static final int COUNTER_DELAY = 230;
    private boolean imagesLoaded;
    private int frameWait;
    private boolean up;
    private boolean left;
    private boolean right;
    private MediaTracker mt;
    private int state;
    private int subState;
    private int incCounter;
    private boolean stunned;
    private boolean musicPlaying;
    private int stunFrame;
    private AudioClip outchSnd;
    private AudioClip jumpSnd;
    private AudioClip coinSnd;
    private AudioClip crackSnd;
    private AudioClip lockSnd;
    private AudioClip lock2Snd;
    private AudioClip keySnd;
    private AudioClip powerSnd;
    private AudioClip exitSnd;
    private boolean foundExit;
    private int jump_key;
    private int left_key;
    private int right_key;
    private int sit_key;
    private FontMetrics fm;
    private Toolkit tk;
    Point tuxPos;
    private boolean isGhost;
    private boolean isDead;
    private int ghostCounter;
    private int deadCounter;
    boolean exitFound;
    int count;
    private boolean jumping;
    private int oldDwnSpeed;
    int oldY;
    
    public Runtux() {
        this.tuxFacing = 0;
        this.tuxDir = 0;
        this.downSpeed = 0;
        this.rightSpeed = 0;
        this.stunOffs = 4;
        this.score = 0;
        this.keyCount = 0;
        this.heartCount = 4;
        this.lifeCount = 4;
        this.bg = new Color(131, 146, 189);
        this.countImagesLoaded = 0;
        this.countSoundsLoaded = 0;
        this.tuxBounds = new Rectangle(0, 0, 26, 32);
        this.frameWait = 3;
        this.up = false;
        this.left = false;
        this.right = false;
        this.state = 1;
        this.subState = 0;
        this.incCounter = 0;
        this.musicPlaying = false;
        this.foundExit = false;
        this.jump_key = 38;
        this.left_key = 37;
        this.right_key = 39;
        this.sit_key = 10;
        this.fm = null;
        this.tk = null;
        this.tuxPos = new Point(0, 0);
        this.isGhost = false;
        this.isDead = false;
        this.ghostCounter = 0;
        this.deadCounter = 0;
        this.exitFound = false;
        this.count = 0;
        this.jumping = false;
        this.oldDwnSpeed = -1;
        this.oldY = -1;
        System.out.println("There might be a bug... somtimes the tux gets stuck next to a plattform:\n");
        System.out.println("########P");
        System.out.println("        #");
        System.out.println("######################");
        System.out.println("# : plattform");
        System.out.println("P : tux");
    }
    
    public void init() {
        this.setLayout(null);
        (this.map = new Map(80, 10)).create();
        this.buff = this.createImage(352, 320);
        this.addKeyListener(this);
        this.requestFocus();
        new Thread(this).start();
    }
    
    private void drawTile(final Graphics g, final int t, final int x, final int y) {
        final int x_ = t << 5;
        g.drawImage(this.tileImg, x, y, x + 32, y + 32, x_, 0, x_ + 32, 32, null);
    }
    
    private void drawTux(final Graphics g, final int frame, final int x, final int y) {
        final int x_ = frame << 5;
        g.drawImage(this.tuxImg, x, y, x + 32, y + 32, x_, 0, x_ + 32, 32, null);
    }
    
    private void insertSprite(final Sprite spr) {
        final LinkedList node = new LinkedList(spr);
        LinkedList cur = this.sprites;
        LinkedList nxt = null;
        Sprite s = null;
        final int x = spr.getX();
        while (cur != null) {
            s = (Sprite)cur.getData();
            if (s != null && s.getX() > x) {
                if (cur.getPrev() == null) {
                    this.sprites.insert(node);
                }
                else {
                    cur.getPrev().insert(node);
                }
                return;
            }
            nxt = cur.getNext();
            if (nxt == null) {
                cur.insert(node);
            }
            cur = nxt;
        }
    }
    
    private void placeBarrelAt(int x, int y) {
        Actor actor = null;
        Point[] pa = null;
        actor = new Actor(this.fishImg);
        this.map.setAt(x, y, (short)4);
        x <<= 5;
        y <<= 5;
        pa = new Point[] { new Point(x, y), new Point(x, 32) };
        final Point[] sa = { new Point(0, 16), new Point(0, 8) };
        actor.setId(1);
        actor.setWayPoints(pa);
        actor.setSpeeds(sa);
        actor.setDelayTime(100);
        actor.setAnimationDirection(true, 2);
        actor.setCollisionGap(3);
        this.insertSprite(actor);
    }
    
    private void placeIceTrapAt(int x, int y, int tx, int ty) {
        Actor actor = null;
        Point[] pa = null;
        actor = new Actor(this.pointyImg);
        if (this.map.getAt(x, y - 1) == 0) {
            this.map.setAt(x, y - 1, (short)2);
        }
        int y2;
        for (y2 = y; this.map.isWalkable(x, y2); ++y2) {}
        y2 -= 2;
        x <<= 5;
        y <<= 5;
        y2 <<= 5;
        tx <<= 5;
        ty <<= 5;
        pa = new Point[] { new Point(x, y), new Point(x, y2), new Point(x, y2) };
        final Point[] sa = { new Point(0, 1), new Point(0, 12), new Point(0, 1) };
        actor.setId(1);
        actor.setCurFrame(2);
        actor.setWayPoints(pa);
        actor.setSpeeds(sa);
        actor.setDelayTime(50);
        actor.setAnimationDirection(true, 2);
        actor.setCollisionGap(10);
        actor.setLooping(false);
        actor.setMoving(false);
        this.insertSprite(actor);
        final Trap trap = new Trap(this.trapImg, actor);
        trap.setPos(tx, ty);
        this.insertSprite(trap);
    }
    
    private void placeCoinAt(int x, int y) {
        x <<= 5;
        y <<= 5;
        final Sprite s = (Sprite)this.coin.clone();
        s.setPos(x, y);
        this.insertSprite(s);
    }
    
    private void placeExitAt(int x, int y) {
        this.placeExit_At(x - 2, y);
        x <<= 5;
        y <<= 5;
        final Sprite s = new Sprite(this.exitImg);
        s.setId(10);
        s.setPos(x, y);
        this.insertSprite(s);
    }
    
    private void placeExit_At(int x, int y) {
        x <<= 5;
        y <<= 5;
        final Sprite s = new Sprite(this.trapImg);
        s.setId(11);
        s.setPos(x, y);
        this.insertSprite(s);
    }
    
    private void placePatrol(int x, int y, int l) {
        x <<= 5;
        y <<= 5;
        l <<= 5;
        final Actor actor = new Actor(this.badGuyImg);
        final Point[] pa = { new Point(x, y), new Point(x + l, y) };
        actor.setId(2);
        actor.setWayPoints(pa);
        actor.setDelayTime(200);
        actor.setSpeed(4);
        actor.setAnimationDirection(false, 2);
        actor.setCollisionGap(3);
        this.insertSprite(actor);
    }
    
    private void placeLockAt(int x, int y) {
        x <<= 5;
        y <<= 5;
        final Sprite lock = new Sprite(this.lockImg);
        lock.setId(6);
        lock.setMoving(false);
        lock.setPos(x, y);
        this.insertSprite(lock);
    }
    
    private void placeGemAt(int x, int y) {
        x <<= 5;
        y <<= 5;
        final Sprite gem = new Sprite(this.gemImg);
        gem.setId(8);
        gem.setMoving(false);
        gem.setPos(x, y);
        this.insertSprite(gem);
    }
    
    private void placeHeartAt(int x, int y) {
        x <<= 5;
        y <<= 5;
        final Sprite heart = new Sprite(this.heartImg);
        heart.setId(9);
        heart.setMoving(false);
        heart.setPos(x, y);
        this.insertSprite(heart);
    }
    
    private void placeKeyAt(int x, int y) {
        x <<= 5;
        y <<= 5;
        final Sprite key = new Sprite(this.keyImg);
        key.setId(7);
        key.setMoving(false);
        key.setPos(x, y);
        this.insertSprite(key);
    }
    
    private void createSpriteList() {
        this.sprites = new LinkedList();
        this.placeBarrelAt(15, 8);
        this.placePatrol(8, 8, 5);
        this.placePatrol(12, 1, 2);
        for (int a = 0; a < 6; ++a) {
            this.placeCoinAt(15, a);
            this.placeIceTrapAt(22 + a, 6, 22 + a, 8);
        }
        this.placeCoinAt(3, 2);
        this.placeCoinAt(1, 1);
        this.placeCoinAt(27, 4);
        this.placeCoinAt(28, 3);
        this.placeCoinAt(29, 2);
        this.placeCoinAt(30, 2);
        this.placeCoinAt(31, 2);
        this.placeCoinAt(32, 3);
        this.placeCoinAt(33, 4);
        this.placeCoinAt(34, 5);
        this.placeCoinAt(35, 6);
        this.placeCoinAt(36, 7);
        this.placePatrol(26, 0, 5);
        this.placePatrol(31, 0, -5);
        this.placeKeyAt(29, 0);
        this.placeLockAt(45, 1);
        this.placeGemAt(46, 1);
        this.placeHeartAt(47, 1);
        this.placePatrol(42, 3, 5);
        this.placePatrol(53, 4, 3);
        this.placeCoinAt(54, 1);
        this.placeCoinAt(56, 6);
        this.placeCoinAt(59, 2);
        this.placeCoinAt(60, 2);
        this.placeCoinAt(61, 2);
        this.placeCoinAt(63, 4);
        this.placeCoinAt(69, 4);
        for (int a2 = 0; a2 < 3; ++a2) {
            this.placeGemAt(68 - a2, 1);
        }
        this.placeKeyAt(78, 1);
        this.placeLockAt(76, 8);
        this.placeExitAt(78, 8);
    }
    
    private void stun() {
        if (!this.stunned) {
            this.outchSnd.play();
            this.stunned = true;
        }
    }
    
    private void tuxHit() {
        if (this.isGhost) {
            return;
        }
        this.stun();
        if (this.rightSpeed > 0) {
            this.rightSpeed = -8;
        }
        else {
            this.rightSpeed = 8;
        }
        --this.heartCount;
        if (this.heartCount < 0) {
            this.tuxDie();
        }
        else {
            this.isGhost = true;
            this.ghostCounter = 121;
        }
    }
    
    private void tuxDie() {
        --this.lifeCount;
        this.isDead = true;
        this.deadCounter = 12;
        this.tuxFrame = 8;
        this.state = 5;
    }
    
    private void reanimate() {
        this.isDead = false;
        this.isGhost = false;
        this.ghostCounter = 121;
        this.heartCount = 3;
        this.state = 2;
        this.map.ofs = 0;
        this.map.pos = 0;
        this.tuxFrame = 0;
        this.tuxFacing = 0;
        this.tuxPos.x = 32;
        this.tuxPos.y = 128;
    }
    
    private void collision(final LinkedList node, final Sprite sprite) {
        switch (sprite.getId()) {
            case 0: {
                this.coinSnd.play();
                this.score += 50;
                node.remove();
                break;
            }
            case 1: {
                this.tuxHit();
                break;
            }
            case 8: {
                this.coinSnd.play();
                this.score += 250;
                node.remove();
                break;
            }
            case 9: {
                this.powerSnd.play();
                this.heartCount = 3;
                node.remove();
                break;
            }
            case 7: {
                ++this.keyCount;
                node.remove();
                this.keySnd.play();
                break;
            }
            case 6: {
                if (this.keyCount == 0) {
                    if (!this.stunned) {
                        this.stun();
                        this.lockSnd.play();
                        if (this.rightSpeed >= 0) {
                            this.rightSpeed = -4;
                        }
                        else {
                            this.rightSpeed = 4;
                        }
                    }
                }
                else {
                    --this.keyCount;
                    this.lock2Snd.play();
                    sprite.setAlive(false);
                }
                break;
            }
            case 2: {
                if (this.downSpeed > 0 && this.tuxPos.y < sprite.getY()) {
                    this.coinSnd.play();
                    this.score += 100;
                    if (node == this.sprites) {
                        this.sprites = node.remove();
                    }
                    else {
                        node.remove();
                    }
                    final ScoreSprite score = new ScoreSprite(this.scoreImg);
                    score.setPos(sprite.getX() - 10, sprite.getY() - 16);
                    this.decoList.add(score);
                }
                else {
                    this.tuxHit();
                }
                break;
            }
            case 5: {
                ((Trap)sprite).getAction().setMoving(true);
                sprite.setAlive(false);
                this.crackSnd.play();
                break;
            }
            case 10: {
                if (!this.foundExit) {
                    this.foundExit = true;
                    this.exitSnd.play();
                    this.state = 6;
                    this.subState = 230;
                }
                break;
            }
            case 11: {
                this.foundExit = false;
                break;
            }
        }
    }
    
    private void drawDecoList(final Graphics g, int x) {
        final int state = 0;
        x -= 32;
        final int ex = x + 384;
        int cx = 0;
        Sprite cur = null;
        LinkedList node = this.decoList.getNext();
        while (node != null) {
            cur = (Sprite)node.getData();
            if (cur.isAlive()) {
                cx = cur.getX() + 32;
                if (cx >= x && cx <= ex) {
                    cur.paint(g, x);
                }
                cur.incFrame();
                node = node.getNext();
            }
            else {
                final LinkedList tmp = node.getNext();
                node.remove();
                node = tmp;
            }
        }
    }
    
    private void drawSpriteList(final Graphics g, final int x) {
        final int state = 0;
        final int ex = x + 384;
        int cx = 0;
        final int minX = x - 352;
        final int maxX = ex + 352;
        Sprite cur = null;
        LinkedList node;
        for (node = this.sprites.getNext(); node != null; node = node.getNext()) {
            cur = (Sprite)node.getData();
            if (cur.getX() + 32 >= minX) {
                break;
            }
        }
        while (node != null) {
            cur = (Sprite)node.getData();
            if (cur.isAlive()) {
                cx = cur.getX() + 32;
                if (cx > maxX) {
                    return;
                }
                cur.incFrame();
                if (cx >= x && cx <= ex) {
                    cur.paint(g, x);
                    if (this.tuxBounds.intersects(cur.getBoundingRect())) {
                        final LinkedList tmp = node.getNext();
                        this.collision(node, cur);
                        node = tmp;
                    }
                    else {
                        node = node.getNext();
                    }
                }
                else {
                    node = node.getNext();
                }
            }
            else {
                final LinkedList tmp = node.getNext();
                node.remove();
                node = tmp;
            }
        }
    }
    
    private void drawCentered(final Graphics g, final FontMetrics fm, final int y, final int width, final String str) {
        g.drawString(str, width - fm.stringWidth(str) >> 1, y);
    }
    
    public void update(final Graphics g) {
        final Graphics db = this.buff.getGraphics();
        db.setColor(this.bg);
        db.fillRect(0, 0, 352, 320);
        this.paint(db);
        g.drawImage(this.buff, -this.map.ofs, 0, 352, 320, null);
        db.dispose();
    }
    
    public void paint(final Graphics g) {
        if (this.state == 2) {
            this.paintWorld(g);
            g.drawImage(this.infoImg, this.map.ofs + 20, 296, null);
            g.setColor(Color.white);
            g.drawString(String.valueOf("").concat(String.valueOf(this.score)), this.map.ofs + 36, 310);
            g.drawString(String.valueOf("").concat(String.valueOf(this.keyCount)), this.map.ofs + 120, 310);
            g.drawString(String.valueOf("").concat(String.valueOf(this.heartCount)), this.map.ofs + 205, 310);
            g.drawString(String.valueOf("").concat(String.valueOf(this.lifeCount)), this.map.ofs + 290, 310);
        }
        else if (this.state == 5) {
            this.paintDeadWorld(g);
        }
        else if (this.state == 6) {
            this.map.ofs = 0;
            final Dimension size = this.getSize();
            g.setColor(Color.black);
            g.fillRect(0, 0, size.width, size.height);
            g.setColor(Color.orange);
            final int x_ = 320 - this.fm.stringWidth("Level Done!") >> 1;
            g.drawString("Level Done!", x_, 150);
            g.setColor(Color.gray);
            for (int a = 0; a < 30; ++a) {
                if ((a & 0x1) == 0x1) {
                    g.drawLine(160 - a, 238 - a, 160 + a, 238 - a);
                }
            }
            g.drawImage(this.logo2Img, 144, 174, null);
        }
        else if (this.state == 1) {
            final Dimension size = this.getSize();
            g.setColor(Color.black);
            g.fillRect(0, 0, size.width, size.height);
            g.setColor(Color.white);
            if (this.fm == null) {
                if (this.tk == null) {
                    this.tk = Toolkit.getDefaultToolkit();
                }
                this.fm = this.tk.getFontMetrics(this.getFont());
            }
            switch (this.subState) {
                case 0: {
                    this.drawCentered(g, this.fm, 160, size.width, "Please wait...");
                    break;
                }
                case 1: {
                    g.drawImage(this.loadImg, 0, 110, null);
                    this.drawCentered(g, this.fm, 180, size.width, "Loading images");
                    g.drawRect(17, 200, 286, 10);
                    g.fillRect(19, 202, 16 * this.countImagesLoaded, 7);
                    break;
                }
                case 2: {
                    g.drawImage(this.loadImg, 0, 110, null);
                    this.drawCentered(g, this.fm, 180, size.width, "Loading sounds");
                    g.drawRect(17, 200, 286, 10);
                    g.fillRect(19, 202, 31 * this.countSoundsLoaded, 7);
                    break;
                }
            }
        }
        else if (this.state == 3) {
            final Dimension size = this.getSize();
            int y = 20;
            this.map.ofs = 0;
            g.setColor(Color.black);
            switch (this.subState) {
                case 0: {
                    g.drawImage(this.titleImg, 0, 0, null);
                    break;
                }
                case 1: {
                    g.fillRect(0, 0, 320, 320);
                    g.drawImage(this.textImg, 123, y, 196, y + 20, 0, 0, 73, 20, null);
                    y += 40;
                    g.setColor(Color.orange);
                    this.drawCentered(g, this.fm, y, size.width, "Coding");
                    y += this.fm.getHeight();
                    g.setColor(Color.yellow);
                    this.drawCentered(g, this.fm, y, size.width, "Lennart Steinke");
                    y += this.fm.getHeight();
                    this.drawTux(g, 0, 144, y);
                    y += 44;
                    g.setColor(Color.orange);
                    this.drawCentered(g, this.fm, y, size.width, "Penguin Sprites");
                    y += this.fm.getHeight();
                    g.setColor(Color.yellow);
                    this.drawCentered(g, this.fm, y, size.width, "Karl H\u00f6rnell");
                    y += this.fm.getHeight();
                    g.drawImage(this.logo2Img, 144, y, null);
                    y += 50;
                    g.setColor(Color.orange);
                    this.drawCentered(g, this.fm, y, size.width, "Penguin on title");
                    y += this.fm.getHeight();
                    g.setColor(Color.yellow);
                    this.drawCentered(g, this.fm, y, size.width, "Larry Ewing");
                    y += this.fm.getHeight();
                    g.drawImage(this.badGuyImg, 144, y, 176, y + 32, 0, 0, 32, 32, null);
                    y += 44;
                    g.setColor(Color.orange);
                    this.drawCentered(g, this.fm, y, size.width, "Enemy Graphics");
                    y += this.fm.getHeight();
                    g.setColor(Color.yellow);
                    this.drawCentered(g, this.fm, y, size.width, "Ari Feldman");
                    y += this.fm.getHeight();
                    y += 20;
                    break;
                }
                case 2: {
                    g.fillRect(0, 0, 320, 320);
                    g.drawImage(this.textImg, 99, y, 221, y + 20, 72, 0, 194, 20, null);
                    y += 40;
                    g.setColor(Color.orange);
                    this.drawCentered(g, this.fm, y, size.width, "Walking");
                    y += this.fm.getHeight();
                    g.setColor(Color.yellow);
                    this.drawCentered(g, this.fm, y, size.width, "Use the cursor keys to move Tux.");
                    y += this.fm.getHeight();
                    this.drawCentered(g, this.fm, y, size.width, "The longer you move in one direction,");
                    y += this.fm.getHeight();
                    this.drawCentered(g, this.fm, y, size.width, "the faster you run.");
                    y += this.fm.getHeight();
                    this.drawCentered(g, this.fm, y, size.width, "Note: Ice is very slippery...");
                    y += this.fm.getHeight();
                    y += 10;
                    g.setColor(Color.orange);
                    this.drawCentered(g, this.fm, y, size.width, "Junping");
                    y += this.fm.getHeight();
                    g.setColor(Color.yellow);
                    this.drawCentered(g, this.fm, y, size.width, "Press the UP key, and Tux will jump.");
                    y += this.fm.getHeight();
                    y += 10;
                    g.setColor(Color.orange);
                    this.drawCentered(g, this.fm, y, size.width, "Scoring");
                    y += this.fm.getHeight();
                    g.setColor(Color.yellow);
                    this.drawCentered(g, this.fm, y, size.width, "Coins are worth 50 points.");
                    y += this.fm.getHeight();
                    this.drawCentered(g, this.fm, y, size.width, "Enemies are worth 100 points.");
                    y += this.fm.getHeight();
                    this.drawCentered(g, this.fm, y, size.width, "Gems are worth 250 points.");
                    y += this.fm.getHeight();
                    y += 10;
                    g.setColor(Color.orange);
                    this.drawCentered(g, this.fm, y, size.width, "Enemies");
                    y += this.fm.getHeight();
                    g.setColor(Color.yellow);
                    this.drawCentered(g, this.fm, y, size.width, "You can jump on the walking guys to crush them.");
                    y += this.fm.getHeight();
                    this.drawCentered(g, this.fm, y, size.width, "Avoid the fish.");
                    y += this.fm.getHeight();
                    break;
                }
            }
        }
        else if (this.state == 7) {
            g.fillRect(this.map.ofs = 0, 0, 320, 320);
            g.drawImage(this.logo2Img, 144, 140, null);
            g.setColor(Color.orange);
            this.drawCentered(g, this.fm, 180, 320, "Just a moment...");
        }
    }
    
    public void start() {
        super.start();
        if (this.state == 7) {
            this.state = 3;
            this.subState = 0;
        }
        System.out.println("start");
    }
    
    public void stop() {
        this.state = 7;
        System.out.println("stop");
        super.stop();
    }
    
    public void destroy() {
        this.state = 4;
        System.out.println("destroy");
        super.destroy();
    }
    
    public void paintDeadWorld(final Graphics g) {
        this.map.centerAround(this.tuxPos.x);
        int spos = this.map.pos;
        int tuxX = 0;
        if (spos <= 0) {
            spos = 0;
        }
        else {
            spos >>>= 5;
        }
        tuxX = this.tuxPos.x - spos * 32;
        int pos = spos;
        final int d = this.map.width - 11;
        int tile = 0;
        this.tuxBounds.setLocation(this.tuxPos);
        for (int y = 0; y < 10; ++y) {
            for (int x = 0; x < 11; ++x) {
                tile = this.map.data[pos];
                if (tile != 0) {
                    this.drawTile(g, tile, x << 5, y << 5);
                }
                ++pos;
            }
            pos += d;
        }
        this.drawTux(g, this.tuxFrame, tuxX, this.tuxPos.y);
    }
    
    public void paintWorld(final Graphics g) {
        this.map.centerAround(this.tuxPos.x);
        int spos = this.map.pos;
        int tuxX = 0;
        if (spos <= 0) {
            spos = 0;
        }
        else {
            spos >>>= 5;
        }
        tuxX = this.tuxPos.x - (spos << 5);
        int pos = spos;
        final int d = this.map.width - 11;
        int tile = 0;
        this.tuxBounds.setLocation(this.tuxPos);
        this.drawSpriteList(g, this.map.pos - this.map.ofs);
        int xp = 0;
        int yp = 0;
        for (int y = 0; y < 10; ++y) {
            xp = 0;
            for (int x = 0; x < 11; ++x) {
                tile = this.map.data[pos];
                if (tile != 0) {
                    this.drawTile(g, tile, xp, yp);
                }
                ++pos;
                xp += 32;
            }
            yp += 32;
            pos += d;
        }
        if (!this.isGhost) {
            this.drawTux(g, this.tuxFrame, tuxX, this.tuxPos.y);
            this.drawDecoList(g, this.map.pos - this.map.ofs);
            if (this.stunned) {
                if (this.tuxFrame < this.stunOffs) {
                    this.tuxFrame = this.stunOffs + this.tuxFacing;
                    this.frameWait = 4;
                }
                --this.frameWait;
                if (this.frameWait <= 0) {
                    this.frameWait = 3;
                    if (this.tuxFrame - this.tuxFacing <= this.stunOffs) {
                        ++this.tuxFrame;
                    }
                }
            }
            else if (this.rightSpeed != 0) {
                if (this.frameWait > 0) {
                    --this.frameWait;
                }
                else {
                    ++this.tuxFrame;
                    this.frameWait = 3;
                    if (this.tuxFrame - this.tuxFacing >= 2) {
                        this.tuxFrame = this.tuxFacing;
                    }
                }
            }
            else {
                this.tuxFrame = this.tuxFacing;
            }
        }
        else {
            this.drawDecoList(g, this.map.pos - this.map.ofs);
            if ((this.ghostCounter & 0x2) == 0x2) {
                this.drawTux(g, this.tuxFrame, tuxX, this.tuxPos.y);
            }
            if (this.stunned) {
                if (this.tuxFrame < this.stunOffs) {
                    this.tuxFrame = this.stunOffs + this.tuxFacing;
                    this.frameWait = 4;
                }
                --this.frameWait;
                if (this.frameWait <= 0) {
                    this.frameWait = 3;
                    if (this.tuxFrame - this.tuxFacing <= this.stunOffs) {
                        ++this.tuxFrame;
                    }
                }
            }
            else if (this.rightSpeed != 0) {
                if (this.frameWait > 0) {
                    --this.frameWait;
                }
                else {
                    ++this.tuxFrame;
                    this.frameWait = 3;
                    if (this.tuxFrame - this.tuxFacing >= 2) {
                        this.tuxFrame = this.tuxFacing;
                    }
                }
            }
            else {
                this.tuxFrame = this.tuxFacing;
            }
        }
    }
    
    private void getImagesFromZip() {
        try {
            final URL url = new URL(this.getDocumentBase(), "gfx.zip");
            final DataInputStream bi = new DataInputStream(url.openStream());
            final ZipInputStream zi = new ZipInputStream(bi);
            ZipEntry zip = zi.getNextEntry();
            String name = zip.getName();
            int size = (int)zip.getSize();
            byte[] buffer = null;
            if (this.tk == null) {
                this.tk = Toolkit.getDefaultToolkit();
            }
            while (zip != null) {
                name = zip.getName();
                size = (int)zip.getSize();
                buffer = new byte[size];
                int pos = 0;
                while (size > 0) {
                    final int bytesRead = zi.read(buffer, pos, size);
                    pos += bytesRead;
                    size -= bytesRead;
                }
                if ("tiles.gif".equals(name)) {
                    this.tileImg = this.tk.createImage(buffer);
                }
                else if ("tux.gif".equals(name)) {
                    this.tuxImg = this.tk.createImage(buffer);
                }
                else if ("title.jpg".equals(name)) {
                    this.titleImg = this.tk.createImage(buffer);
                }
                else if ("coin.gif".equals(name)) {
                    this.coinImg = this.tk.createImage(buffer);
                }
                else if ("fish.gif".equals(name)) {
                    this.fishImg = this.tk.createImage(buffer);
                }
                else if ("badguy.gif".equals(name)) {
                    this.badGuyImg = this.tk.createImage(buffer);
                }
                else if ("score.gif".equals(name)) {
                    this.scoreImg = this.tk.createImage(buffer);
                }
                else if ("pointy.gif".equals(name)) {
                    this.pointyImg = this.tk.createImage(buffer);
                }
                else if ("empty.gif".equals(name)) {
                    this.trapImg = this.tk.createImage(buffer);
                }
                else if ("key.gif".equals(name)) {
                    this.keyImg = this.tk.createImage(buffer);
                }
                else if ("heart.gif".equals(name)) {
                    this.heartImg = this.tk.createImage(buffer);
                }
                else if ("lock.gif".equals(name)) {
                    this.lockImg = this.tk.createImage(buffer);
                }
                else if ("gem.gif".equals(name)) {
                    this.gemImg = this.tk.createImage(buffer);
                }
                else if ("exit.gif".equals(name)) {
                    this.exitImg = this.tk.createImage(buffer);
                }
                else if ("info.gif".equals(name)) {
                    this.infoImg = this.tk.createImage(buffer);
                }
                else if ("text.gif".equals(name)) {
                    this.textImg = this.tk.createImage(buffer);
                }
                else if ("logo2.gif".equals(name)) {
                    this.logo2Img = this.tk.createImage(buffer);
                }
                zip = zi.getNextEntry();
                ++this.countImagesLoaded;
                this.repaint();
            }
        }
        catch (Exception anyException) {
            anyException.printStackTrace();
        }
    }
    
    public AudioClip getAudioClip(final URL url) {
        final AudioClip ac = super.getAudioClip(url);
        ac.play();
        ++this.countSoundsLoaded;
        this.repaint();
        ac.stop();
        return ac;
    }
    
    private void loadImages() {
        if (this.mt == null) {
            this.mt = new MediaTracker(this);
        }
        try {
            this.loadImg = this.getImage(this.getDocumentBase(), "load.gif");
            this.mt.addImage(this.loadImg, 0);
            try {
                this.mt.waitForID(0);
                this.subState = 1;
                this.repaint();
            }
            catch (Exception ex) {}
            this.getImagesFromZip();
            this.mt.addImage(this.tileImg, 0);
            this.mt.addImage(this.titleImg, 0);
            this.mt.addImage(this.tuxImg, 0);
            this.mt.addImage(this.coinImg, 0);
            this.mt.addImage(this.fishImg, 0);
            this.mt.addImage(this.badGuyImg, 0);
            this.mt.addImage(this.scoreImg, 0);
            this.mt.addImage(this.pointyImg, 0);
            this.mt.addImage(this.trapImg, 0);
            this.mt.addImage(this.keyImg, 0);
            this.mt.addImage(this.lockImg, 0);
            this.mt.addImage(this.heartImg, 0);
            this.mt.addImage(this.gemImg, 0);
            this.mt.addImage(this.exitImg, 0);
            this.mt.addImage(this.infoImg, 0);
            this.mt.addImage(this.textImg, 0);
            this.mt.addImage(this.logo2Img, 0);
        }
        catch (Exception o) {
            System.out.println(String.valueOf("ex:").concat(String.valueOf(o)));
        }
        try {
            this.mt.waitForID(0);
            this.imagesLoaded = true;
            this.subState = 2;
            this.repaint();
        }
        catch (Exception ex2) {}
        this.outchSnd = this.getAudioClip(this.getDocumentBase(), "outch.au");
        this.jumpSnd = this.getAudioClip(this.getDocumentBase(), "jump.au");
        this.coinSnd = this.getAudioClip(this.getDocumentBase(), "coin.au");
        this.crackSnd = this.getAudioClip(this.getDocumentBase(), "crack.au");
        this.lockSnd = this.getAudioClip(this.getDocumentBase(), "lock.au");
        this.lock2Snd = this.getAudioClip(this.getDocumentBase(), "lock2.au");
        this.keySnd = this.getAudioClip(this.getDocumentBase(), "key.au");
        this.powerSnd = this.getAudioClip(this.getDocumentBase(), "power.au");
        this.exitSnd = this.getAudioClip(this.getDocumentBase(), "exit.au");
        (this.coin = new Sprite(this.coinImg)).setDelayTime(125);
        this.state = 3;
        this.subState = 0;
        this.repaint();
    }
    
    public void keyTyped(final KeyEvent e) {
    }
    
    public void keyPressed(final KeyEvent e) {
        switch (e.getKeyCode()) {
            case 38: {
                if (!this.stunned) {
                    this.up = true;
                }
                else if (!this.up) {
                    this.stunned = false;
                }
                break;
            }
            case 39: {
                if (!this.stunned) {
                    this.right = true;
                    this.tuxFacing = 0;
                    this.frameWait = -1;
                }
                else if (!this.right) {
                    this.stunned = false;
                }
                break;
            }
            case 37: {
                if (!this.stunned) {
                    this.left = true;
                    this.tuxFacing = 2;
                    if (!this.stunned) {
                        this.frameWait = -1;
                    }
                    break;
                }
                if (!this.left) {
                    this.stunned = false;
                    break;
                }
                break;
            }
        }
    }
    
    public void keyReleased(final KeyEvent e) {
        Label_0174: {
            switch (e.getKeyCode()) {
                case 38: {
                    this.up = false;
                    break;
                }
                case 39: {
                    this.right = false;
                    break;
                }
                case 37: {
                    this.left = false;
                }
                case 10: {
                    this.stun();
                    break;
                }
                case 123: {
                    this.tuxDie();
                    break;
                }
                case 32: {
                    switch (this.state) {
                        case 3: {
                            this.state = 2;
                            this.resetGame();
                            this.repaint();
                            break Label_0174;
                        }
                        case 2: {
                            this.state = 3;
                            this.subState = 0;
                            this.repaint();
                            break Label_0174;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    private void resetGame() {
        this.map.ofs = 0;
        this.map.pos = 0;
        this.tuxFrame = 0;
        this.tuxFacing = 0;
        this.tuxPos.x = 32;
        this.tuxPos.y = 128;
        this.createSpriteList();
        this.score = 0;
    }
    
    public void run() {
        this.requestFocus();
        this.loadImages();
        this.createSpriteList();
        int counter = 230;
        this.decoList = new LinkedList();
        this.requestFocus();
        do {
            try {
                Thread.sleep(33L);
            }
            catch (Exception ex) {}
            if (this.state == 2) {
                final int ty = this.tuxPos.y + 33 >>> 5;
                final int tx = this.tuxPos.x + 2 >>> 5;
                final int tx_ = this.tuxPos.x + 17 >>> 5;
                if (this.isGhost) {
                    if (this.ghostCounter > 0) {
                        --this.ghostCounter;
                    }
                    else {
                        this.isGhost = false;
                    }
                }
                if (this.jumping || this.downSpeed < 0 || (!this.map.canStandOn(tx, ty) && !this.map.canStandOn(tx_, ty))) {
                    this.downSpeed += 3;
                    this.downSpeed = ((this.downSpeed > 24) ? 24 : this.downSpeed);
                }
                int nty = this.tuxPos.y + this.downSpeed + 31 >>> 5;
                final int oty = this.tuxPos.y + 31 >>> 5;
                final int ny = this.tuxPos.y + this.downSpeed >>> 5;
                if (this.downSpeed >= 0) {
                    if (nty > oty && (this.map.canStandOn(tx, nty) || this.map.canStandOn(tx_, nty))) {
                        this.tuxPos.y = nty * 32 - 31;
                        this.downSpeed = 0;
                        this.jumping = false;
                    }
                }
                else if (this.map.isBlocking(tx, ny) || this.map.isBlocking(tx_, ny)) {
                    this.downSpeed = -3;
                    this.stun();
                }
                final Point tuxPos = this.tuxPos;
                tuxPos.y += this.downSpeed;
                this.oldY = this.tuxPos.y >>> 5;
                if (this.downSpeed == 0 && !this.jumping && this.up) {
                    this.jumpSnd.play();
                    this.jumping = true;
                    this.stunned = false;
                    this.downSpeed = -18 - (((this.rightSpeed > 0) ? this.rightSpeed : (-this.rightSpeed)) >> 1);
                }
                if (this.left) {
                    --this.incCounter;
                    if (this.incCounter <= 0) {
                        this.stunned = false;
                        this.incCounter = 4;
                        this.rightSpeed -= 2;
                        this.rightSpeed = ((this.rightSpeed < -16) ? -16 : this.rightSpeed);
                    }
                }
                else if (this.right) {
                    --this.incCounter;
                    if (this.incCounter <= 0) {
                        this.stunned = false;
                        this.incCounter = 4;
                        this.rightSpeed += 2;
                        this.rightSpeed = ((this.rightSpeed > 16) ? 16 : this.rightSpeed);
                    }
                }
                else if (this.rightSpeed > 1) {
                    --this.rightSpeed;
                }
                else if (this.rightSpeed < -1) {
                    ++this.rightSpeed;
                }
                else {
                    this.rightSpeed = 0;
                    this.incCounter = 0;
                }
                int ntx = 0;
                if (this.rightSpeed > 0) {
                    ntx = this.tuxPos.x + 28 + this.rightSpeed >> 5;
                }
                else {
                    ntx = this.tuxPos.x + this.rightSpeed >> 5;
                }
                nty = this.tuxPos.y >> 5;
                final int nty_ = this.tuxPos.y + 20 >> 5;
                if (!this.map.isBlocking(ntx, nty) && !this.map.isBlocking(ntx, nty_)) {
                    final Point tuxPos2 = this.tuxPos;
                    tuxPos2.x += this.rightSpeed;
                }
                else {
                    if ((this.downSpeed == 0 && this.rightSpeed > 8) || this.rightSpeed < -8) {
                        this.stun();
                        this.incCounter = 16;
                    }
                    this.rightSpeed = 0;
                }
                this.repaint();
            }
            else if (this.state == 5) {
                --this.deadCounter;
                if (this.deadCounter < 0) {
                    this.reanimate();
                }
                else if ((this.deadCounter & 0x2) != 0x0) {
                    ++this.tuxFrame;
                }
                this.repaint();
            }
            else if (this.state == 3) {
                if (--counter <= 0) {
                    counter = 230;
                    ++this.subState;
                    if (this.subState >= 3) {
                        this.subState = 0;
                    }
                    this.outchSnd.play();
                    this.repaint();
                }
            }
            else if (this.state == 6) {
                --this.subState;
                if (this.subState <= 0) {
                    this.subState = 0;
                    this.state = 3;
                    this.outchSnd.play();
                }
                this.repaint();
            }
            else if (this.state == 7) {
                try {
                    Thread.sleep(500L);
                }
                catch (Exception ex2) {}
            }
        } while (this.state != 4);
    }
    
    public void mouseClicked(final MouseEvent parm1) {
        this.requestFocus();
    }
    
    public void mouseEntered(final MouseEvent parm1) {
    }
    
    public void mouseExited(final MouseEvent parm1) {
    }
    
    public void mousePressed(final MouseEvent parm1) {
        this.requestFocus();
    }
    
    public void mouseReleased(final MouseEvent parm1) {
        this.requestFocus();
    }
    
    class Timer extends Thread
    {
        private boolean show;
        
        public Timer() {
            this.show = false;
        }
        
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex) {}
                Runtux.this.repaint();
                Runtux.access$056(Runtux.this, 2);
                this.show = !this.show;
            }
        }
    }
}
