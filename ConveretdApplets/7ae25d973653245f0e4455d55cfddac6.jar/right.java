import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.awt.image.ImageObserver;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.image.ImageProducer;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class right extends Applet implements Runnable, MouseListener
{
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int x;
    int y;
    int dbi;
    int mpi;
    int mpj;
    int counter;
    int leaves;
    int numchecks;
    int seed;
    long[] playerMasks;
    long[] computerMasks;
    long tl;
    long lbase;
    final long pmask = 384307168202282325L;
    final long cmask = 768614336404564650L;
    long[] posMasks;
    long currentBoard;
    long li;
    long lj;
    int[] xdirs;
    int[] ydirs;
    int[] zdirs;
    int skill;
    boolean computerToMove;
    Image background;
    Image collection;
    Image but1;
    Image but2;
    Graphics bg;
    long nextTime;
    MediaTracker mt;
    Thread runner;
    ImageProducer improd;
    cluster clusterApplet;
    
    public void init() {
        this.mt = new MediaTracker(this);
        final InputStream resourceAsStream = this.getClass().getResourceAsStream("row2c.gif");
        try {
            final byte[] array = new byte[resourceAsStream.available()];
            resourceAsStream.read(array);
            this.collection = Toolkit.getDefaultToolkit().createImage(array);
        }
        catch (IOException ex) {}
        this.mt.addImage(this.collection, 0);
        try {
            this.mt.waitForID(0);
        }
        catch (InterruptedException ex2) {}
        this.background = this.createImage(51, 126);
        this.bg = this.background.getGraphics();
        this.improd = this.collection.getSource();
        this.but1 = this.createImage(new FilteredImageSource(this.improd, new CropImageFilter(0, 126, 30, 19)));
        this.mt.addImage(this.but1, 1);
        this.but2 = this.createImage(new FilteredImageSource(this.improd, new CropImageFilter(0, 145, 30, 19)));
        this.mt.addImage(this.but2, 1);
        try {
            this.mt.waitForID(1);
        }
        catch (InterruptedException ex3) {}
        this.bg.drawImage(this.collection, 0, 0, this);
        this.drawButtons();
        this.seed = 1 + (int)(System.currentTimeMillis() & 0x3FFFL);
        this.l = 0;
        this.i = -1;
        while (this.i < 2) {
            this.j = -1;
            while (this.j < 2) {
                this.k = -1;
                while (this.k < 2) {
                    this.xdirs[this.l] = this.i;
                    this.ydirs[this.l] = this.j;
                    this.zdirs[this.l++] = this.k;
                    ++this.k;
                }
                ++this.j;
            }
            ++this.i;
        }
        final long[] array2 = new long[70];
        this.numchecks = 0;
        this.i = 0;
        while (this.i < 3) {
            this.j = 0;
            while (this.j < 3) {
                this.k = 0;
                while (this.k < 3) {
                    this.l = 0;
                    while (this.l < 27) {
                        if (this.i + 2 * this.xdirs[this.l] >= 0 && this.i + 2 * this.xdirs[this.l] < 3 && this.j + 2 * this.ydirs[this.l] >= 0 && this.j + 2 * this.ydirs[this.l] < 3 && this.k + 2 * this.zdirs[this.l] >= 0 && this.k + 2 * this.zdirs[this.l] < 3 && this.l != 13) {
                            this.m = this.l + 1;
                            while (this.m < 27) {
                                if (this.i + 2 * this.xdirs[this.m] >= 0 && this.i + 2 * this.xdirs[this.m] < 3 && this.j + 2 * this.ydirs[this.m] >= 0 && this.j + 2 * this.ydirs[this.m] < 3 && this.k + 2 * this.zdirs[this.m] >= 0 && this.k + 2 * this.zdirs[this.m] < 3 && this.xdirs[this.l] * this.xdirs[this.m] + this.ydirs[this.l] * this.ydirs[this.m] + this.zdirs[this.l] * this.zdirs[this.m] == 0 && this.m != 13) {
                                    this.tl = this.lbase << 2 * (this.i + 3 * this.j + 9 * this.k);
                                    this.tl |= this.lbase << 2 * (this.i + this.xdirs[this.l] + 3 * (this.j + this.ydirs[this.l]) + 9 * (this.k + this.zdirs[this.l]));
                                    this.tl |= this.lbase << 2 * (this.i + 2 * this.xdirs[this.l] + 3 * (this.j + 2 * this.ydirs[this.l]) + 9 * (this.k + 2 * this.zdirs[this.l]));
                                    this.tl |= this.lbase << 2 * (this.i + this.xdirs[this.m] + 3 * (this.j + this.ydirs[this.m]) + 9 * (this.k + this.zdirs[this.m]));
                                    this.tl |= this.lbase << 2 * (this.i + 2 * this.xdirs[this.m] + 3 * (this.j + 2 * this.ydirs[this.m]) + 9 * (this.k + 2 * this.zdirs[this.m]));
                                    array2[this.numchecks++] = this.tl;
                                }
                                ++this.m;
                            }
                        }
                        ++this.l;
                    }
                    ++this.k;
                }
                ++this.j;
            }
            ++this.i;
        }
        this.playerMasks = new long[this.numchecks];
        this.computerMasks = new long[this.numchecks];
        this.i = 0;
        while (this.i < this.numchecks) {
            this.playerMasks[this.i] = (array2[this.i] & 0x555555555555555L);
            this.computerMasks[this.i] = (array2[this.i] & 0xAAAAAAAAAAAAAAAL);
            ++this.i;
        }
        this.tl = 3L;
        this.i = 0;
        while (this.i < 27) {
            this.posMasks[this.i] = this.tl;
            this.tl <<= 2;
            ++this.i;
        }
        this.clearBoard();
        this.addMouseListener(this);
    }
    
    public void drawButtons() {
        this.dbi = 0;
        while (this.dbi < 4) {
            if (this.dbi + 1 == this.skill) {
                this.bg.drawImage(this.but2, 21, 107 - this.dbi * 27, this);
            }
            else {
                this.bg.drawImage(this.but1, 21, 107 - this.dbi * 27, this);
            }
            ++this.dbi;
        }
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null && this.runner.isAlive()) {
            this.runner.stop();
        }
        this.runner = null;
    }
    
    public void run() {
        while (this.runner != null) {
            try {
                Thread.sleep(Math.max(25L, this.nextTime - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
            this.nextTime = System.currentTimeMillis() + 100L;
            if (this.computerToMove) {
                this.computerToMove = false;
                this.makeComputerMove();
            }
        }
    }
    
    public void registerMove(final int n) {
        int n2 = -1;
        if (this.clusterApplet == null) {
            this.clusterApplet = (cluster)this.getAppletContext().getApplet("cluster");
        }
        this.currentBoard |= (this.posMasks[n] & 0x555555555555555L);
        for (int i = 0; i < this.numchecks; ++i) {
            if ((this.currentBoard & this.playerMasks[i]) == this.playerMasks[i]) {
                n2 = i;
            }
        }
        if (n2 >= 0) {
            this.clusterApplet.playerWins(this.playerMasks[n2]);
            return;
        }
        this.computerToMove = true;
    }
    
    public void clearBoard() {
        this.currentBoard = 0L;
        this.computerToMove = false;
    }
    
    public void makeComputerMove() {
        int n = -2000;
        int n2 = 0;
        this.leaves = 0;
        int n3 = this.randi() % 27;
        for (int i = 0; i < 27; ++i) {
            if ((this.currentBoard & this.posMasks[n3]) == 0x0L) {
                final int evaluateMove = this.evaluateMove(this.currentBoard | (this.posMasks[n3] & 0xAAAAAAAAAAAAAAAL), 1, n);
                if (evaluateMove > n) {
                    n = evaluateMove;
                    n2 = n3;
                }
            }
            n3 = (n3 + 1) % 27;
        }
        this.currentBoard |= (this.posMasks[n2] & 0xAAAAAAAAAAAAAAAL);
        int n4 = -1;
        for (int j = 0; j < this.numchecks; ++j) {
            if ((this.currentBoard & this.computerMasks[j]) == this.computerMasks[j]) {
                n4 = j;
            }
        }
        if (n4 >= 0) {
            this.clusterApplet.computerWins(this.computerMasks[n4]);
            return;
        }
        this.clusterApplet.placeComputerBall(n2);
    }
    
    public int evaluateMove(final long n, final int n2, final int n3) {
        if (n2 >= this.skill) {
            ++this.leaves;
            int n4 = 0;
            for (int i = 0; i < this.numchecks; ++i) {
                if ((n & this.playerMasks[i]) == 0x0L) {
                    ++n4;
                }
                else if ((n & this.playerMasks[i]) == this.playerMasks[i]) {
                    n4 -= 1000;
                }
                if ((n & this.computerMasks[i]) == 0x0L) {
                    --n4;
                }
                else if ((n & this.computerMasks[i]) == this.computerMasks[i]) {
                    n4 += 1000;
                }
            }
            return n4;
        }
        if ((n2 & 0x1) == 0x1) {
            int n5 = 2000;
            for (int j = 0; j < 27; ++j) {
                if ((n & this.posMasks[j]) == 0x0L) {
                    final int evaluateMove = this.evaluateMove(n | (this.posMasks[j] & 0x555555555555555L), n2 + 1, n5);
                    if (evaluateMove < n5 && (n5 = evaluateMove) <= n3) {
                        j = 27;
                    }
                }
            }
            return n5;
        }
        int n6 = -2000;
        for (int k = 0; k < 27; ++k) {
            if ((n & this.posMasks[k]) == 0x0L) {
                final int evaluateMove2 = this.evaluateMove(n | (this.posMasks[k] & 0xAAAAAAAAAAAAAAAL), n2 + 1, n6);
                if (evaluateMove2 > n6 && (n6 = evaluateMove2) >= n3) {
                    k = 27;
                }
            }
        }
        return n6;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.x = mouseEvent.getX();
        this.y = mouseEvent.getY();
        this.mpj = -1;
        this.mpi = 0;
        while (this.mpi < 4) {
            if (this.y > 109 - 27 * this.mpi && this.y < 122 - 27 * this.mpi && this.x > 25 && this.x < 45) {
                this.mpj = this.mpi;
            }
            ++this.mpi;
        }
        if (this.mpj >= 0) {
            this.skill = this.mpj + 1;
            this.drawButtons();
            this.repaint();
        }
    }
    
    public int randi() {
        return this.seed = this.seed * 171 % 30269;
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.background, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.background, 0, 0, this);
    }
    
    public right() {
        this.lbase = 3L;
        this.posMasks = new long[27];
        this.xdirs = new int[27];
        this.ydirs = new int[27];
        this.zdirs = new int[27];
        this.skill = 2;
    }
}
