import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Cselection extends Canvas implements Runnable
{
    private Graphics gbuf;
    private Image buf;
    private Image bg;
    private GConsole console;
    private Thread selThread;
    private boolean run;
    private int delay;
    private boolean up;
    private int offset;
    private Font font;
    private Color textcolor;
    private Color selcolor;
    private int index;
    private int var;
    private boolean atRest;
    int width;
    int height;
    private int maxVar;
    
    public Cselection(final GConsole console, final int width, final int height) {
        this.run = true;
        this.delay = 300;
        this.up = true;
        this.offset = 0;
        this.var = 0;
        this.atRest = true;
        this.console = console;
        this.buf = console.createImage(width, height);
        this.width = width;
        this.height = height;
        this.gbuf = this.buf.getGraphics();
        this.font = new Font("Arial", 1, 16);
        this.textcolor = new Color(0, 255, 255);
        this.selcolor = new Color(51, 51, 51);
        (this.selThread = new Thread(this)).setName("Selection Thread");
        this.selThread.start();
        this.maxVar = console.numberOfGames() * 26;
    }
    
    public void finalize() {
        this.run = false;
        this.selThread = null;
    }
    
    public void paint(final Graphics graphics) {
        if (this.bg != null) {
            this.gbuf.drawImage(this.bg, 0, 0, this);
        }
        this.gbuf.setFont(this.font);
        this.gbuf.setColor(this.textcolor);
        this.gbuf.fillRect(5, this.height / 2 - 10, this.width - 10, 20);
        this.gbuf.setColor(this.textcolor);
        final int n = this.console.numberOfGames() - 1;
        int maxVar = 0;
        if (this.var > 0) {
            maxVar = this.maxVar;
        }
        this.gbuf.drawString(this.console.gameAt(n).getName(), this.width / 2 - this.console.gameAt(n).getFontWidth() / 2, 40 + n * 26 + this.var % this.maxVar - this.maxVar);
        this.gbuf.drawString(this.console.gameAt(0).getName(), this.width / 2 - this.console.gameAt(0).getFontWidth() / 2, 40 + (n + 1) * 26 + this.var % this.maxVar - maxVar);
        this.gbuf.drawString(this.console.gameAt(1).getName(), this.width / 2 - this.console.gameAt(1).getFontWidth() / 2, 40 + (n + 2) * 26 + this.var % this.maxVar - maxVar);
        for (int i = 0; i < this.console.numberOfGames(); ++i) {
            final int n2 = 40 + i * 26 + this.var % this.maxVar - maxVar;
            if (n2 > -30 && n2 < 150) {
                this.gbuf.drawString(this.console.gameAt(i).getName(), this.width / 2 - this.console.gameAt(i).getFontWidth() / 2, n2);
            }
        }
        if (this.atRest) {
            this.gbuf.setColor(this.selcolor);
            this.gbuf.drawString(this.console.gameAt(this.index).getName(), this.width / 2 - this.console.gameAt(this.index).getFontWidth() / 2, 40 + this.index * 26 + this.var % this.maxVar - maxVar);
        }
        graphics.drawImage(this.buf, 0, 0, this);
    }
    
    public void quit() {
        this.run = false;
        this.selThread = null;
    }
    
    public void rest() {
        this.run = false;
    }
    
    public void run() {
        while (this.run) {
            while (this.offset != this.var && this.offset != this.var + 1) {
                this.atRest = false;
                if (this.up) {
                    this.var += 2;
                }
                else {
                    this.var -= 2;
                }
                this.repaint();
                try {
                    Thread.sleep(30L);
                }
                catch (Exception ex) {}
            }
            if (!this.atRest) {
                this.atRest = true;
                this.console.updateDescription();
                this.repaint();
            }
            try {
                Thread.sleep(this.delay);
            }
            catch (Exception ex2) {}
        }
    }
    
    public void selectDown() {
        if (this.index < this.console.numberOfGames() - 1) {
            ++this.index;
            this.console.setSelectedGame(this.index);
            this.offset -= 26;
            this.up = false;
        }
        else {
            this.index = 0;
            this.console.setSelectedGame(this.index);
            this.offset -= 26;
            this.up = false;
        }
    }
    
    public void selectUp() {
        if (this.index > 0) {
            --this.index;
            this.console.setSelectedGame(this.index);
            this.offset += 26;
            this.up = true;
        }
        else {
            this.index = this.console.numberOfGames() - 1;
            this.console.setSelectedGame(this.index);
            this.offset += 26;
            this.up = true;
        }
    }
    
    public void setImage(final Image bg) {
        this.bg = bg;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void wakeUp() {
        if (!this.run) {
            this.run = true;
            (this.selThread = new Thread(this)).setName("Selection Thread");
            this.selThread.start();
            this.delay = 300;
        }
    }
}
