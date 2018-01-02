import java.awt.Cursor;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Button;
import java.awt.MediaTracker;
import java.util.Hashtable;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ShootingGallery extends Applet
{
    static Image background;
    static Graphics g;
    static int score;
    static int loadX;
    static int loadY;
    static int loadXStep;
    static int loadYStep;
    Target tempTarget;
    Bullet tempBullet;
    static Hashtable targetHashtable;
    static Hashtable keyHashtable;
    MediaTracker imageTracker;
    Button newGameButton;
    
    public void init() {
        this.imageTracker = new MediaTracker(this);
        for (int i = 1; i < 37; ++i) {
            Gun.gunArray[i - 1] = this.getImage(this.getCodeBase(), "gun" + i + ".gif");
            this.imageTracker.addImage(Gun.gunArray[i - 1], 0);
        }
        ShootingGallery.background = this.getImage(this.getCodeBase(), "ShootingGalleryBg.gif");
        this.imageTracker.addImage(ShootingGallery.background, 0);
        Target.duck_left = this.getImage(this.getCodeBase(), "duckLeft.gif");
        this.imageTracker.addImage(Target.duck_left, 0);
        Target.duck_right = this.getImage(this.getCodeBase(), "duckRight.gif");
        this.imageTracker.addImage(Target.duck_right, 0);
        for (int j = 1; j < 5; ++j) {
            Target.duckFallLeft[j - 1] = this.getImage(this.getCodeBase(), "duckLeft" + j + ".jpg");
            this.imageTracker.addImage(Target.duckFallLeft[j - 1], 0);
        }
        for (int k = 1; k < 5; ++k) {
            Target.duckFallRight[k - 1] = this.getImage(this.getCodeBase(), "duckRight" + k + ".jpg");
            this.imageTracker.addImage(Target.duckFallRight[k - 1], 0);
        }
        this.imageTracker.checkID(0, true);
        ShootingGallery.g = this.getGraphics();
        Target.g = this.getGraphics();
        Bullet.g = this.getGraphics();
        ShootingGallery.score = 0;
        Bullet.ammo = 15;
        this.setLayout(new BorderLayout());
        this.add("South", this.newGameButton = new Button("New Game"));
        ShootingGallery.g.setColor(Color.white);
        ShootingGallery.g.fillRect(0, 0, 300, 350);
        while (!this.imageTracker.checkAll()) {
            ShootingGallery.g.setColor(Color.black);
            ShootingGallery.g.drawString("Loading Images...", ShootingGallery.loadX, ShootingGallery.loadY);
            System.out.println("Loading images...");
            try {
                Thread.sleep(250L);
            }
            catch (Exception ex) {}
            ShootingGallery.g.setColor(Color.white);
            ShootingGallery.g.drawString("Loading Images...", ShootingGallery.loadX, ShootingGallery.loadY);
            if (ShootingGallery.loadX >= 290 || ShootingGallery.loadX <= 0) {
                ShootingGallery.loadX = -ShootingGallery.loadX;
            }
            ShootingGallery.loadX += ShootingGallery.loadX;
            if (ShootingGallery.loadY >= 340 || ShootingGallery.loadY <= 0) {
                ShootingGallery.loadY = -ShootingGallery.loadY;
            }
            ShootingGallery.loadY += ShootingGallery.loadY;
        }
        ShootingGallery.g.drawImage(ShootingGallery.background, 0, 0, this);
        ShootingGallery.g.setColor(Color.black);
        ShootingGallery.g.drawString("Score: " + ShootingGallery.score, 10, 310);
        this.tempTarget = new Target(0, true);
        ShootingGallery.g.drawImage(ShootingGallery.background, 0, 0, this);
        ShootingGallery.g.setColor(Color.black);
        ShootingGallery.g.drawString("Score: " + ShootingGallery.score, 10, 310);
        this.tempTarget = new Target(40, false);
        ShootingGallery.g.drawImage(ShootingGallery.background, 0, 0, this);
        ShootingGallery.g.setColor(Color.black);
        ShootingGallery.g.drawString("Score: " + ShootingGallery.score, 10, 310);
        try {
            Thread.sleep(Target.delay * 110);
        }
        catch (Exception ex2) {}
        this.tempTarget = new Target(160, true);
        ShootingGallery.g.drawImage(ShootingGallery.background, 0, 0, this);
        ShootingGallery.g.setColor(Color.black);
        ShootingGallery.g.drawString("Score: " + ShootingGallery.score, 10, 310);
        this.tempTarget = new Target(200, false);
        ShootingGallery.g.drawImage(ShootingGallery.background, 0, 0, this);
        ShootingGallery.g.setColor(Color.black);
        ShootingGallery.g.drawString("Score: " + ShootingGallery.score, 10, 310);
        try {
            Thread.sleep(Target.delay * 200);
        }
        catch (Exception ex3) {}
        this.tempTarget = new Target(80, true);
        ShootingGallery.g.drawImage(ShootingGallery.background, 0, 0, this);
        ShootingGallery.g.setColor(Color.black);
        ShootingGallery.g.drawString("Score: " + ShootingGallery.score, 10, 310);
        this.tempTarget = new Target(120, false);
        ShootingGallery.g.drawImage(ShootingGallery.background, 0, 0, this);
        ShootingGallery.g.setColor(Color.black);
        ShootingGallery.g.drawString("Score: " + ShootingGallery.score, 10, 310);
    }
    
    public void stop() {
        for (int i = 0; i < 6; ++i) {
            if (ShootingGallery.keyHashtable.get(new Integer(i)) != null) {
                (this.tempTarget = (Target)ShootingGallery.targetHashtable.get(new Integer(i).toString())).stop();
            }
        }
    }
    
    public void start() {
        for (int i = 0; i < 6; ++i) {
            if (ShootingGallery.keyHashtable.get(new Integer(i)) != null) {
                (this.tempTarget = (Target)ShootingGallery.targetHashtable.get(new Integer(i).toString())).start();
            }
        }
    }
    
    public void destroy() {
        for (int i = 0; i < 6; ++i) {
            if (ShootingGallery.keyHashtable.get(new Integer(i)) != null) {
                (this.tempTarget = (Target)ShootingGallery.targetHashtable.get(new Integer(i).toString())).stop();
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(ShootingGallery.background, 0, 0, this);
        graphics.setColor(Color.black);
        graphics.drawString("Score: " + ShootingGallery.score, 10, 310);
        graphics.drawString("Ammo: " + Bullet.ammo, 240, 310);
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (n2 <= 40) {
            if (n <= 50) {
                ShootingGallery.g.drawImage(Gun.gunArray[0], 140, 241, this);
            }
            else if (n <= 100) {
                ShootingGallery.g.drawImage(Gun.gunArray[1], 140, 241, this);
            }
            else if (n <= 150) {
                ShootingGallery.g.drawImage(Gun.gunArray[2], 140, 241, this);
            }
            else if (n <= 200) {
                ShootingGallery.g.drawImage(Gun.gunArray[3], 140, 241, this);
            }
            else if (n <= 250) {
                ShootingGallery.g.drawImage(Gun.gunArray[4], 140, 241, this);
            }
            else {
                ShootingGallery.g.drawImage(Gun.gunArray[5], 140, 241, this);
            }
        }
        else if (n2 <= 80) {
            if (n <= 50) {
                ShootingGallery.g.drawImage(Gun.gunArray[6], 140, 241, this);
            }
            else if (n <= 100) {
                ShootingGallery.g.drawImage(Gun.gunArray[7], 140, 241, this);
            }
            else if (n <= 150) {
                ShootingGallery.g.drawImage(Gun.gunArray[8], 140, 241, this);
            }
            else if (n <= 200) {
                ShootingGallery.g.drawImage(Gun.gunArray[9], 140, 241, this);
            }
            else if (n <= 250) {
                ShootingGallery.g.drawImage(Gun.gunArray[10], 140, 241, this);
            }
            else {
                ShootingGallery.g.drawImage(Gun.gunArray[11], 140, 241, this);
            }
        }
        else if (n2 <= 120) {
            if (n <= 50) {
                ShootingGallery.g.drawImage(Gun.gunArray[12], 140, 241, this);
            }
            else if (n <= 100) {
                ShootingGallery.g.drawImage(Gun.gunArray[13], 140, 241, this);
            }
            else if (n <= 150) {
                ShootingGallery.g.drawImage(Gun.gunArray[14], 140, 241, this);
            }
            else if (n <= 200) {
                ShootingGallery.g.drawImage(Gun.gunArray[15], 140, 241, this);
            }
            else if (n <= 250) {
                ShootingGallery.g.drawImage(Gun.gunArray[16], 140, 241, this);
            }
            else {
                ShootingGallery.g.drawImage(Gun.gunArray[17], 140, 241, this);
            }
        }
        else if (n2 <= 160) {
            if (n <= 50) {
                ShootingGallery.g.drawImage(Gun.gunArray[18], 140, 241, this);
            }
            else if (n <= 100) {
                ShootingGallery.g.drawImage(Gun.gunArray[19], 140, 241, this);
            }
            else if (n <= 150) {
                ShootingGallery.g.drawImage(Gun.gunArray[20], 140, 241, this);
            }
            else if (n <= 200) {
                ShootingGallery.g.drawImage(Gun.gunArray[21], 140, 241, this);
            }
            else if (n <= 250) {
                ShootingGallery.g.drawImage(Gun.gunArray[22], 140, 241, this);
            }
            else {
                ShootingGallery.g.drawImage(Gun.gunArray[23], 140, 241, this);
            }
        }
        else if (n2 <= 200) {
            if (n <= 50) {
                ShootingGallery.g.drawImage(Gun.gunArray[24], 140, 241, this);
            }
            else if (n <= 100) {
                ShootingGallery.g.drawImage(Gun.gunArray[25], 140, 241, this);
            }
            else if (n <= 150) {
                ShootingGallery.g.drawImage(Gun.gunArray[26], 140, 241, this);
            }
            else if (n <= 200) {
                ShootingGallery.g.drawImage(Gun.gunArray[27], 140, 241, this);
            }
            else if (n <= 250) {
                ShootingGallery.g.drawImage(Gun.gunArray[28], 140, 241, this);
            }
            else {
                ShootingGallery.g.drawImage(Gun.gunArray[29], 140, 241, this);
            }
        }
        else if (n2 <= 240) {
            if (n <= 50) {
                ShootingGallery.g.drawImage(Gun.gunArray[30], 140, 241, this);
            }
            else if (n <= 100) {
                ShootingGallery.g.drawImage(Gun.gunArray[31], 140, 241, this);
            }
            else if (n <= 150) {
                ShootingGallery.g.drawImage(Gun.gunArray[32], 140, 241, this);
            }
            else if (n <= 200) {
                ShootingGallery.g.drawImage(Gun.gunArray[33], 140, 241, this);
            }
            else if (n <= 250) {
                ShootingGallery.g.drawImage(Gun.gunArray[34], 140, 241, this);
            }
            else {
                ShootingGallery.g.drawImage(Gun.gunArray[35], 140, 241, this);
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (n2 <= 40) {
            if (n <= 50) {
                ShootingGallery.g.drawImage(Gun.gunArray[0], 140, 241, this);
            }
            else if (n <= 100) {
                ShootingGallery.g.drawImage(Gun.gunArray[1], 140, 241, this);
            }
            else if (n <= 150) {
                ShootingGallery.g.drawImage(Gun.gunArray[2], 140, 241, this);
            }
            else if (n <= 200) {
                ShootingGallery.g.drawImage(Gun.gunArray[3], 140, 241, this);
            }
            else if (n <= 250) {
                ShootingGallery.g.drawImage(Gun.gunArray[4], 140, 241, this);
            }
            else {
                ShootingGallery.g.drawImage(Gun.gunArray[5], 140, 241, this);
            }
        }
        else if (n2 <= 80) {
            if (n <= 50) {
                ShootingGallery.g.drawImage(Gun.gunArray[6], 140, 241, this);
            }
            else if (n <= 100) {
                ShootingGallery.g.drawImage(Gun.gunArray[7], 140, 241, this);
            }
            else if (n <= 150) {
                ShootingGallery.g.drawImage(Gun.gunArray[8], 140, 241, this);
            }
            else if (n <= 200) {
                ShootingGallery.g.drawImage(Gun.gunArray[9], 140, 241, this);
            }
            else if (n <= 250) {
                ShootingGallery.g.drawImage(Gun.gunArray[10], 140, 241, this);
            }
            else {
                ShootingGallery.g.drawImage(Gun.gunArray[11], 140, 241, this);
            }
        }
        else if (n2 <= 120) {
            if (n <= 50) {
                ShootingGallery.g.drawImage(Gun.gunArray[12], 140, 241, this);
            }
            else if (n <= 100) {
                ShootingGallery.g.drawImage(Gun.gunArray[13], 140, 241, this);
            }
            else if (n <= 150) {
                ShootingGallery.g.drawImage(Gun.gunArray[14], 140, 241, this);
            }
            else if (n <= 200) {
                ShootingGallery.g.drawImage(Gun.gunArray[15], 140, 241, this);
            }
            else if (n <= 250) {
                ShootingGallery.g.drawImage(Gun.gunArray[16], 140, 241, this);
            }
            else {
                ShootingGallery.g.drawImage(Gun.gunArray[17], 140, 241, this);
            }
        }
        else if (n2 <= 160) {
            if (n <= 50) {
                ShootingGallery.g.drawImage(Gun.gunArray[18], 140, 241, this);
            }
            else if (n <= 100) {
                ShootingGallery.g.drawImage(Gun.gunArray[19], 140, 241, this);
            }
            else if (n <= 150) {
                ShootingGallery.g.drawImage(Gun.gunArray[20], 140, 241, this);
            }
            else if (n <= 200) {
                ShootingGallery.g.drawImage(Gun.gunArray[21], 140, 241, this);
            }
            else if (n <= 250) {
                ShootingGallery.g.drawImage(Gun.gunArray[22], 140, 241, this);
            }
            else {
                ShootingGallery.g.drawImage(Gun.gunArray[23], 140, 241, this);
            }
        }
        else if (n2 <= 200) {
            if (n <= 50) {
                ShootingGallery.g.drawImage(Gun.gunArray[24], 140, 241, this);
            }
            else if (n <= 100) {
                ShootingGallery.g.drawImage(Gun.gunArray[25], 140, 241, this);
            }
            else if (n <= 150) {
                ShootingGallery.g.drawImage(Gun.gunArray[26], 140, 241, this);
            }
            else if (n <= 200) {
                ShootingGallery.g.drawImage(Gun.gunArray[27], 140, 241, this);
            }
            else if (n <= 250) {
                ShootingGallery.g.drawImage(Gun.gunArray[28], 140, 241, this);
            }
            else {
                ShootingGallery.g.drawImage(Gun.gunArray[29], 140, 241, this);
            }
        }
        else if (n2 <= 240) {
            if (n <= 50) {
                ShootingGallery.g.drawImage(Gun.gunArray[30], 140, 241, this);
            }
            else if (n <= 100) {
                ShootingGallery.g.drawImage(Gun.gunArray[31], 140, 241, this);
            }
            else if (n <= 150) {
                ShootingGallery.g.drawImage(Gun.gunArray[32], 140, 241, this);
            }
            else if (n <= 200) {
                ShootingGallery.g.drawImage(Gun.gunArray[33], 140, 241, this);
            }
            else if (n <= 250) {
                ShootingGallery.g.drawImage(Gun.gunArray[34], 140, 241, this);
            }
            else {
                ShootingGallery.g.drawImage(Gun.gunArray[35], 140, 241, this);
            }
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (Bullet.ammo > 0 && n2 < 238 && n2 >= 0 && n >= 0 && n <= 300) {
            (this.tempBullet = new Bullet(n, n2)).start();
            for (int i = 0; i < 6; ++i) {
                if (ShootingGallery.keyHashtable.get(new Integer(i)) != null) {
                    this.tempTarget = (Target)ShootingGallery.targetHashtable.get(new Integer(i).toString());
                    if (n + Bullet.radius >= this.tempTarget.x && n - Bullet.radius <= this.tempTarget.x + 46 && n2 + Bullet.radius > this.tempTarget.y && n2 - Bullet.radius < this.tempTarget.y + 39) {
                        this.tempTarget.fall();
                        if (ShootingGallery.targetHashtable.size() == 0) {
                            this.newLevel();
                        }
                    }
                }
            }
            try {
                Thread.sleep(300L);
            }
            catch (Exception ex) {}
            --Bullet.ammo;
            this.repaint();
        }
        return true;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.newGame();
        }
        return super.action(event, o);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.setCursor(new Cursor(1));
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.setCursor(new Cursor(0));
        return true;
    }
    
    public void newGame() {
        for (int i = 0; i < 6; ++i) {
            if (ShootingGallery.keyHashtable.get(new Integer(i)) != null) {
                (this.tempTarget = (Target)ShootingGallery.targetHashtable.get(new Integer(i).toString())).fall();
                this.tempTarget.stop();
            }
        }
        ShootingGallery.score = 0;
        Bullet.ammo = 15;
        Target.masterKey = 0;
        Target.delay = 15;
        Target.step = 1;
        ShootingGallery.keyHashtable = new Hashtable();
        (this.tempTarget = new Target(0, true)).start();
        (this.tempTarget = new Target(40, false)).start();
        try {
            Thread.sleep(Target.delay * 70);
        }
        catch (Exception ex) {}
        (this.tempTarget = new Target(80, true)).start();
        (this.tempTarget = new Target(120, false)).start();
        try {
            Thread.sleep(Target.delay * 70);
        }
        catch (Exception ex2) {}
        (this.tempTarget = new Target(160, true)).start();
        (this.tempTarget = new Target(200, false)).start();
        this.repaint();
    }
    
    public void newLevel() {
        Bullet.ammo = 15;
        Target.masterKey = 0;
        if (ShootingGallery.score % 1800 == 0) {
            ++Target.step;
        }
        else {
            --Target.delay;
        }
        ShootingGallery.keyHashtable = new Hashtable();
        (this.tempTarget = new Target(0, true)).start();
        (this.tempTarget = new Target(40, false)).start();
        try {
            Thread.sleep(Target.delay * (long)(Math.random() * 90.0 + 90.0));
        }
        catch (Exception ex) {}
        (this.tempTarget = new Target(80, true)).start();
        (this.tempTarget = new Target(120, false)).start();
        try {
            Thread.sleep(Target.delay * (long)(Math.random() * 90.0 + 90.0));
        }
        catch (Exception ex2) {}
        (this.tempTarget = new Target(160, true)).start();
        (this.tempTarget = new Target(200, false)).start();
        this.repaint();
    }
    
    static {
        ShootingGallery.loadX = 10;
        ShootingGallery.loadY = 10;
        ShootingGallery.loadXStep = 5;
        ShootingGallery.loadYStep = 5;
        ShootingGallery.targetHashtable = new Hashtable();
        ShootingGallery.keyHashtable = new Hashtable();
    }
}
