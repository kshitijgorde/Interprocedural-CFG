import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class MSConfig extends Panel implements KeyListener, MenuListener
{
    private static final int WIDTH = 320;
    private static final int HEIGHT = 320;
    private Image gbuffer;
    private Graphics gbuf;
    private FontMetrics gfm;
    private MoonStar moonStar;
    private Image background;
    private MSButton buttonBegin;
    private MSButton buttonEnd;
    private String askString;
    private String[] directions;
    private int[] keyCodes;
    private int state;
    
    public MSConfig(final MoonStar moonStar, final String s, final String s2, final String askString, final String[] directions, final Image background) {
        this.keyCodes = new int[] { 37, 38, 39, 40, 32 };
        this.state = -2;
        this.moonStar = moonStar;
        this.background = background;
        this.askString = askString;
        this.directions = directions;
        this.setBackground(moonStar.colors[0]);
        this.setLayout(null);
        this.buttonBegin = new MSButton(this, 0, s);
        this.buttonEnd = new MSButton(this, 1, s2);
        this.buttonBegin.setBounds(105, 280, 110, 20);
        this.buttonEnd.setBounds(105, 280, 110, 20);
        this.add(this.buttonBegin);
        this.addKeyListener(this);
    }
    
    public synchronized void add() {
        if (this.state == -1) {
            this.remove(this.buttonEnd);
        }
        this.keyCodes = this.moonStar.mSGame.getKeyCodes();
        this.moonStar.add(this);
        this.repaint();
        if (this.state != -2) {
            this.add(this.buttonBegin);
        }
        this.buttonBegin.repaint();
        this.state = -2;
    }
    
    public void remove() {
        this.moonStar.remove(this);
    }
    
    public synchronized boolean actionPerformed(final int n) {
        if (this.state < 0) {
            if (n == 0) {
                this.remove(this.buttonBegin);
                this.state = 0;
                this.repaint();
                this.requestFocus();
            }
            else {
                final int[] keyCodes = new int[this.keyCodes.length];
                for (int i = 0; i < keyCodes.length; ++i) {
                    keyCodes[i] = this.keyCodes[i];
                }
                this.moonStar.mSGame.setKeyCodes(keyCodes);
                this.remove(this.buttonEnd);
                this.state = -2;
                this.add(this.buttonBegin);
            }
            return true;
        }
        return false;
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public synchronized void keyPressed(final KeyEvent keyEvent) {
        if (this.state >= 0) {
            this.keyCodes[this.state] = keyEvent.getKeyCode();
            if (this.check()) {
                ++this.state;
            }
            if (this.state == this.keyCodes.length) {
                this.state = -1;
                this.add(this.buttonEnd);
                this.buttonEnd.repaint();
            }
            this.repaint();
            this.requestFocus();
        }
    }
    
    private boolean check() {
        for (int i = this.state - 1; i >= 0; --i) {
            if (this.keyCodes[this.state] == this.keyCodes[i]) {
                return false;
            }
        }
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
            this.gbuf.setFont(new Font("Ariel", 0, 20));
            this.gfm = this.gbuf.getFontMetrics();
        }
        this.gbuf.drawImage(this.background, 0, 0, 320, 320, this);
        this.gbuf.setColor(this.moonStar.colors[6]);
        for (int i = 0; i < ((this.state < 0) ? this.keyCodes.length : this.state); ++i) {
            final String string = this.directions[i] + ": " + KeyEvent.getKeyText(this.keyCodes[i]);
            this.gbuf.drawString(string, (320 - this.gfm.stringWidth(string)) / 2, 100 + i * 30);
        }
        if (this.state >= 0) {
            final String string2 = this.askString + " " + this.directions[this.state];
            this.gbuf.drawString(string2, (320 - this.gfm.stringWidth(string2)) / 2, 296);
        }
        if (this.gbuffer != null) {
            gbuf.drawImage(this.gbuffer, 0, 0, 320, 320, this);
        }
    }
}
