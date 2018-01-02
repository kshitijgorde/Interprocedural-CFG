import java.awt.image.ImageObserver;
import java.awt.Component;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class MSCampaign extends Canvas implements MSController, Runnable
{
    private static final int WIDTH = 320;
    private static final int HEIGHT = 320;
    private static final int MAXLEVELS = 16;
    private static final int PARTS = 4;
    private Image gbuffer;
    private Graphics gbuf;
    private MSGame mSGame;
    private MoonStar moonStar;
    private Image background;
    private Image foreground;
    private Thread thread;
    private int level;
    
    public MSCampaign(final MSGame msGame, final Image background, final Image foreground) {
        this.level = 0;
        this.mSGame = msGame;
        this.moonStar = msGame.moonStar;
        this.background = background;
        this.foreground = foreground;
    }
    
    public void gameOver() {
        this.moonStar.mSMenu.sendHighScore(false);
        this.moonStar.mSController = null;
        this.moonStar.mSMenu.actionPerformed(6);
    }
    
    public boolean doWormStop() {
        return false;
    }
    
    public void run() {
        this.mSGame.moonStar.mSMenu.addLevel();
        BufferedReader bufferedReader = null;
        try {
            final long currentTimeMillis = System.currentTimeMillis();
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.moonStar.getCodeBase(), "level" + this.level + ".dat").openStream()));
            final int[][] array = new int[bufferedReader.read()][bufferedReader.read()];
            for (int i = 0; i < array.length; ++i) {
                for (int j = 0; j < array[0].length; ++j) {
                    array[i][j] = bufferedReader.read();
                }
            }
            final long n = System.currentTimeMillis() - currentTimeMillis;
            if ((int)n < 4000) {
                Thread.sleep(4000 - (int)n);
            }
            this.moonStar.mSGame.init(array, 250, bufferedReader.read(), bufferedReader.read(), this);
        }
        catch (Exception ex) {
            this.mSGame.moonStar.errorString = "Problem loading the file level" + this.level + ".dat";
            System.out.println(this.mSGame.moonStar.errorString + "\n" + ex);
            this.mSGame.moonStar.repaint();
        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public void reset() {
        this.level = 0;
    }
    
    public void remove() {
        this.moonStar.mSMenu.actionPerformed(9);
        this.mSGame.remove(this);
    }
    
    public boolean add() {
        ++this.level;
        if (this.level > 16) {
            return false;
        }
        this.moonStar.mSMenu.actionPerformed(8);
        this.mSGame.add(this);
        this.repaint();
        this.requestFocus();
        (this.thread = new Thread(this)).start();
        return true;
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
        }
        this.gbuf.drawImage(this.background, 0, 0, 320, 320, this);
        for (int i = 0; i < this.level; ++i) {
            this.gbuf.drawImage(this.foreground, 10 + i % 4 * 300 / 4, 10 + i / 4 * 300 / 4, 10 + (i % 4 + 1) * 300 / 4, 10 + (i / 4 + 1) * 300 / 4, i % 4 * 300 / 4, i / 4 * 300 / 4, (i % 4 + 1) * 300 / 4, (i / 4 + 1) * 300 / 4, this);
        }
        if (this.gbuffer != null) {
            gbuf.drawImage(this.gbuffer, 0, 0, 320, 320, this);
        }
    }
}
