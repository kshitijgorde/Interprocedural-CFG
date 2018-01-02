import java.awt.Font;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class MSGame extends Panel implements Runnable, KeyListener
{
    private static final int WIDTH = 320;
    private static final int HEIGHT = 320;
    private static final int BLOCKLENGTH = 20;
    private static final int MINIMUMCOMBINATION = 3;
    private Image gbuffer;
    private Graphics gbuf;
    private Thread runner;
    private boolean running;
    private boolean alive;
    private boolean playing;
    public boolean loadingLevel;
    public MoonStar moonStar;
    private Image background;
    private Image[] blocks;
    private String pauseString;
    private String gameOverString;
    private int[] keyCodes;
    private MSController mSController;
    public MSCampaign mSCampaign;
    private int[][] data;
    private int[][] worm;
    private int direction;
    private int sleepTime;
    private int numberBlocks;
    private int[] pressedKeys;
    
    public MSGame(final MoonStar moonStar, final String pauseString, final String gameOverString, final Image background, final Image image, final Image image2, final Image image3) {
        this.running = false;
        this.alive = true;
        this.playing = false;
        this.loadingLevel = false;
        this.blocks = new Image[16];
        this.keyCodes = new int[] { 37, 38, 39, 40, 32 };
        this.data = new int[1][1];
        this.worm = new int[3][3];
        this.direction = 3;
        this.sleepTime = 200;
        this.numberBlocks = 3;
        this.pressedKeys = new int[4];
        this.moonStar = moonStar;
        this.pauseString = pauseString;
        this.gameOverString = gameOverString;
        this.background = background;
        this.setBackground(moonStar.colors[0]);
        for (int i = 0; i < this.blocks.length; ++i) {
            this.blocks[i] = moonStar.createImage(20, 20);
            this.blocks[i].getGraphics().drawImage(image, 0, 0, 20, 20, i % 8 * 20, i / 8 * 20, (i % 8 + 1) * 20, (i / 8 + 1) * 20, moonStar);
        }
        (this.mSCampaign = new MSCampaign(this, image2, image3)).setBounds(0, 0, 320, 320);
        this.addKeyListener(this);
        (this.runner = new Thread(this)).start();
    }
    
    public synchronized void init(final int[][] data, final int sleepTime, final int numberBlocks, final int n, final MSController msController) {
        this.data = data;
        this.sleepTime = sleepTime;
        this.numberBlocks = numberBlocks;
        this.worm = new int[n][3];
        this.mSController = msController;
        if (this.loadingLevel) {
            this.loadingLevel = false;
            this.mSCampaign.remove();
        }
        this.moonStar.mSMenu.actionPerformed(0);
        this.resetWorm();
        this.running = true;
        this.playing = true;
        this.notify();
        this.repaint();
    }
    
    public synchronized MSController startCampaign() {
        this.moonStar.mSMenu.actionPerformed(0);
        this.mSCampaign.reset();
        this.running = false;
        this.playing = true;
        this.loadingLevel = true;
        this.mSController = this.mSCampaign;
        this.mSCampaign.add();
        return this.mSController;
    }
    
    public void arcadeLevelup() {
        int n = this.worm.length + 1;
        if (n > 7 && this.numberBlocks < 7) {
            n = 3;
            ++this.numberBlocks;
        }
        this.worm = new int[n][3];
    }
    
    public void run() {
        while (true) {
            synchronized (this) {
                if (!this.alive) {
                    return;
                }
                while (!this.running || this.loadingLevel) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex) {
                        return;
                    }
                }
                this.doRun();
            }
            this.repaint();
            this.requestFocus();
            try {
                Thread.sleep(this.sleepTime);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void remove() {
        this.moonStar.remove(this);
    }
    
    public void add() {
        this.moonStar.add(this);
        this.repaint();
    }
    
    public synchronized void destroy() {
        this.alive = false;
        this.running = false;
        this.playing = false;
        this.loadingLevel = false;
        this.runner.interrupt();
    }
    
    public synchronized void pause() {
        this.running = false;
        this.repaint();
    }
    
    public synchronized void resume() {
        this.running = true;
        this.notify();
    }
    
    public void setKeyCodes(final int[] keyCodes) {
        synchronized (keyCodes) {
            this.keyCodes = keyCodes;
        }
    }
    
    public int[] getKeyCodes() {
        synchronized (this.keyCodes) {
            final int[] array = new int[this.keyCodes.length];
            for (int i = 0; i < array.length; ++i) {
                array[i] = this.keyCodes[i];
            }
            return array;
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        synchronized (this.keyCodes) {
            final int keyCode = keyEvent.getKeyCode();
            if (keyCode == this.keyCodes[this.keyCodes.length - 1] && this.playing) {
                if (this.running) {
                    this.moonStar.mSMenu.actionPerformed(1);
                }
                else {
                    this.moonStar.mSMenu.actionPerformed(0);
                }
            }
            else {
                for (int i = 0; i < this.keyCodes.length - 1; ++i) {
                    if (keyCode == this.keyCodes[i]) {
                        for (int j = 0; j < this.pressedKeys.length; ++j) {
                            if (this.pressedKeys[j] < 0) {
                                this.pressedKeys[j] = i;
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
    
    private void doRun() {
        synchronized (this.keyCodes) {
            while (this.pressedKeys[0] >= 0 && this.pressedKeys[0] % 2 == this.direction % 2) {
                for (int i = 0; i < this.pressedKeys.length - 1; ++i) {
                    this.pressedKeys[i] = this.pressedKeys[i + 1];
                }
                this.pressedKeys[this.pressedKeys.length - 1] = -1;
            }
            if (this.pressedKeys[0] >= 0 && this.worm[0][2] >= 0) {
                this.direction = this.pressedKeys[0];
                for (int j = 0; j < this.pressedKeys.length - 1; ++j) {
                    this.pressedKeys[j] = this.pressedKeys[j + 1];
                }
                this.pressedKeys[this.pressedKeys.length - 1] = -1;
            }
        }
        int n = this.worm[0][1];
        int n2 = this.worm[0][2];
        if (this.direction == 0) {
            --n;
        }
        if (this.direction == 1) {
            --n2;
        }
        if (this.direction == 2) {
            ++n;
        }
        if (this.direction == 3) {
            ++n2;
        }
        if (n < 0 || n2 < 0 || n >= this.data.length || n2 >= this.data[0].length || this.data[n][n2] > 0) {
            this.doWormStop();
        }
        else {
            if (this.worm[this.worm.length - 1][2] >= 0) {
                this.data[this.worm[this.worm.length - 1][1]][this.worm[this.worm.length - 1][2]] = 0;
            }
            for (int k = this.worm.length - 1; k > 0; --k) {
                this.worm[k][1] = this.worm[k - 1][1];
                this.worm[k][2] = this.worm[k - 1][2];
                if (this.worm[k][2] >= 0) {
                    this.data[this.worm[k][1]][this.worm[k][2]] = this.worm[k][0];
                }
            }
            this.worm[0][1] = n;
            this.worm[0][2] = n2;
            this.data[this.worm[0][1]][this.worm[0][2]] = this.worm[0][0];
        }
    }
    
    public void endGame() {
    }
    
    private void doWormStop() {
        if (this.worm[this.worm.length - 1][2] < 0) {
            this.running = false;
            this.playing = false;
            this.mSController.gameOver();
        }
        else {
            int n = 0;
            for (int i = 0; i < this.worm.length; ++i) {
                for (int j = 0; j < 4; ++j) {
                    boolean b = true;
                    int n2 = this.worm[i][1];
                    int n3 = this.worm[i][2];
                    int n4 = 1;
                    int n5 = 0;
                    while (b) {
                        switch (j) {
                            case 0: {
                                ++n2;
                                break;
                            }
                            case 1: {
                                ++n2;
                                ++n3;
                                break;
                            }
                            case 2: {
                                ++n3;
                                break;
                            }
                            case 3: {
                                --n2;
                                ++n3;
                                break;
                            }
                        }
                        if (n2 >= 0 && n3 >= 0 && n2 < this.data.length && n3 < this.data[0].length && this.data[n2][n3] % 8 == this.worm[i][0] % 8) {
                            ++n5;
                        }
                        else {
                            b = false;
                        }
                    }
                    boolean b2 = true;
                    int n6 = this.worm[i][1];
                    int n7 = this.worm[i][2];
                    while (b2) {
                        switch (j) {
                            case 0: {
                                --n6;
                                break;
                            }
                            case 1: {
                                --n6;
                                --n7;
                                break;
                            }
                            case 2: {
                                --n7;
                                break;
                            }
                            case 3: {
                                ++n6;
                                --n7;
                                break;
                            }
                        }
                        if (n6 >= 0 && n7 >= 0 && n6 < this.data.length && n7 < this.data[0].length && this.data[n6][n7] % 8 == this.worm[i][0] % 8) {
                            ++n4;
                        }
                        else {
                            b2 = false;
                        }
                    }
                    int k = n4 + n5;
                    if (k >= 3) {
                        n += k * this.worm.length * this.numberBlocks * this.numberBlocks * 1000 / this.sleepTime;
                        while (k > 0) {
                            switch (j) {
                                case 0: {
                                    ++n6;
                                    break;
                                }
                                case 1: {
                                    ++n6;
                                    ++n7;
                                    break;
                                }
                                case 2: {
                                    ++n7;
                                    break;
                                }
                                case 3: {
                                    --n6;
                                    ++n7;
                                    break;
                                }
                            }
                            if (this.data[n6][n7] < 16) {
                                final int[] array = this.data[n6];
                                final int n8 = n7;
                                array[n8] += 16;
                            }
                            --k;
                        }
                    }
                }
            }
            this.moonStar.mSMenu.addScore(n);
            boolean b3 = true;
            for (int l = 0; l < this.data.length; ++l) {
                for (int n9 = 0; n9 < this.data[0].length; ++n9) {
                    if (this.data[l][n9] > 16) {
                        this.data[l][n9] = 16;
                    }
                    if (this.data[l][n9] > 8 && this.data[l][n9] < 16) {
                        b3 = false;
                    }
                }
            }
            this.repaint();
            try {
                Thread.sleep(this.sleepTime);
            }
            catch (InterruptedException ex) {}
            for (int n10 = 0; n10 < this.data.length; ++n10) {
                for (int n11 = 0; n11 < this.data[0].length; ++n11) {
                    if (this.data[n10][n11] == 16) {
                        this.data[n10][n11] = 0;
                    }
                }
            }
            if (!this.mSController.doWormStop() && b3) {
                this.running = false;
                if (this.mSCampaign.add()) {
                    this.loadingLevel = true;
                }
                else {
                    this.playing = false;
                    this.mSController.gameOver();
                }
            }
            this.resetWorm();
        }
    }
    
    private void resetWorm() {
        final Random random = new Random();
        for (int i = 0; i < this.worm.length; ++i) {
            this.worm[i][0] = Math.abs(random.nextInt() % this.numberBlocks) + 1;
            this.worm[i][1] = this.data.length / 2;
            this.worm[i][2] = -1;
        }
        this.pressedKeys[0] = 3;
        for (int j = 1; j < this.pressedKeys.length; ++j) {
            this.pressedKeys[j] = -1;
        }
        this.direction = 3;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics gbuf) {
        if (this.gbuffer == null) {
            try {
                this.gbuffer = this.createImage(320, 320);
                this.gbuf = this.gbuffer.getGraphics();
            }
            catch (Exception ex) {
                this.gbuf = gbuf;
                this.gbuffer = null;
            }
            this.gbuf.setFont(new Font("Ariel", 1, 16));
        }
        this.gbuf.drawImage(this.background, 0, 0, 320, 320, this);
        if (this.running) {
            final int n = (320 - this.data.length * 20) / 2;
            final int n2 = (320 - this.data[0].length * 20) / 2;
            this.gbuf.setColor(this.moonStar.colors[6]);
            this.gbuf.drawRect(n - 1, n2 - 1, this.data.length * 20 + 1, this.data[0].length * 20 + 1);
            this.gbuf.drawRect(n - 2, n2 - 2, this.data.length * 20 + 3, this.data[0].length * 20 + 3);
            this.gbuf.drawRect(n - 3, n2 - 3, this.data.length * 20 + 5, this.data[0].length * 20 + 5);
            for (int i = 0; i < this.data.length; ++i) {
                for (int j = 0; j < this.data[0].length; ++j) {
                    if (this.data[i][j] == 16) {
                        this.gbuf.drawImage(this.blocks[0], n + i * 20, n2 + j * 20, 20, 20, this);
                    }
                    else if (this.data[i][j] > 0) {
                        this.gbuf.drawImage(this.blocks[this.data[i][j]], n + i * 20, n2 + j * 20, 20, 20, this);
                    }
                }
            }
        }
        else {
            this.gbuf.setColor(this.moonStar.colors[6]);
            if (this.playing) {
                this.gbuf.drawString(this.pauseString, (320 - this.gbuf.getFontMetrics().stringWidth(this.pauseString)) / 2, 164);
            }
            else {
                this.gbuf.drawString(this.gameOverString, (320 - this.gbuf.getFontMetrics().stringWidth(this.gameOverString)) / 2, 164);
            }
        }
        if (this.gbuffer != null) {
            gbuf.drawImage(this.gbuffer, 0, 0, 320, 320, this);
        }
    }
}
