import java.util.Vector;
import java.awt.Rectangle;
import gamelib.OffComponent;
import java.awt.event.ComponentEvent;
import java.applet.Applet;
import gamelib.ActionBuffer;

// 
// Decompiled by Procyon v0.5.30
// 

class Field extends ActionBuffer
{
    Applet applet;
    Ground ground;
    TankControls tc;
    Player[] players;
    Tank[] tanks;
    Tank actt;
    Sky sky;
    int pnum;
    int tpp;
    int sheme;
    int pcnt;
    private RoundWaiter waiter;
    private int pnum2;
    
    Field(final Applet applet) {
        this.pnum = 0;
        this.tpp = 2;
        this.pcnt = 7;
        this.applet = applet;
        super.licensed = true;
    }
    
    private synchronized boolean checkFinished() {
        boolean b = true;
        Player owner = null;
        for (int i = 0; i < this.tanks.length; ++i) {
            if (this.tanks[i].h > 0.0f) {
                if (owner == null) {
                    owner = this.tanks[i].owner;
                }
                else if (this.tanks[i].owner != owner) {
                    b = false;
                    break;
                }
            }
        }
        if (b) {
            if (owner != null) {
                final Player player = owner;
                ++player.vics;
            }
            int n = 0;
            for (int j = 0; j < this.players.length; ++j) {
                n += this.players[j].vics;
            }
            if (n % 5 == 0) {
                new StatsBox(this);
            }
            else {
                this.generate();
            }
        }
        return b;
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.generate();
        super.componentResized(componentEvent);
    }
    
    private void doNext() {
        if (!this.checkFinished()) {
            this.nextTank();
        }
    }
    
    synchronized void generate() {
        if (this.waiter != null) {
            this.waiter.stop = true;
        }
        for (int i = super.childs.size() - 1; i >= 0; --i) {
            ((OffComponent)super.childs.elementAt(i)).removeSelf();
        }
        System.gc();
        int width = this.getSize().width;
        if (width <= 0) {
            width = 640;
        }
        int height = this.getSize().height;
        if (height <= 0) {
            height = 480;
        }
        this.sheme = Math.abs(TankApplet.gen.nextInt() % this.pcnt);
        this.sky = new Sky(this, new Rectangle(0, 0, width, height));
        for (int j = Math.abs(TankApplet.gen.nextInt() % 3); j >= 0; --j) {
            new Cloud(this, j);
        }
        final Tank[] tanks = new Tank[this.pnum2 * this.tpp];
        for (int k = 0; k < this.pnum2; ++k) {
            for (int l = 0; l < this.tpp; ++l) {
                int abs;
                do {
                    abs = Math.abs(TankApplet.gen.nextInt() % (this.pnum2 * this.tpp));
                } while (tanks[abs] != null);
                if (k < this.pnum || k == 0) {
                    tanks[abs] = new Tank(this, this.players[k]);
                }
                else {
                    tanks[abs] = new SmartTank(this, this.players[k]);
                }
            }
        }
        this.tanks = tanks;
        this.ground = new Ground(this, new Rectangle(0, 0, width, height));
        for (int n = 0; n < this.tanks.length; ++n) {
            this.tanks[n].setPosition(width / (2 * this.tanks.length) * (2 * n + 1));
        }
        new PlayerControls(this);
        this.tc = new TankControls(this);
        new FieldControls(this);
        new ArsenalControl(this);
        this.tanks[0].activate();
    }
    
    synchronized void init() {
        this.requestFocus();
        this.pnum2 = this.pnum;
        if (this.pnum == 0) {
            this.pnum2 = 2;
        }
        if (this.pnum == 1) {
            this.pnum2 = 3;
        }
        this.players = new Player[this.pnum2];
        for (int i = 0; i < this.pnum2; ++i) {
            this.players[i] = new Player(i, this);
        }
    }
    
    void next() {
        this.waiter = new RoundWaiter();
        this.waiter.actors = super.actors;
        this.waiter.start();
    }
    
    private void nextTank() {
        int length;
        int n;
        for (length = this.tanks.length, n = 0; n < length && this.actt != this.tanks[n]; ++n) {}
        for (int n2 = 0; n2 < length && this.tanks[++n % length].h <= 0.0f; ++n2) {}
        this.tanks[n % length].activate();
    }
    
    private class RoundWaiter extends Thread
    {
        boolean stop;
        Vector actors;
        
        RoundWaiter() {
            this.stop = false;
        }
        
        public void run() {
            boolean b;
            do {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
                b = true;
                for (int i = 0; i < this.actors.size(); ++i) {
                    if (this.actors.elementAt(i) instanceof Missile || this.actors.elementAt(i) instanceof Warhead) {
                        b = false;
                        break;
                    }
                }
            } while (!this.stop && !b);
            if (!this.stop) {
                Field.this.doNext();
            }
        }
    }
}
