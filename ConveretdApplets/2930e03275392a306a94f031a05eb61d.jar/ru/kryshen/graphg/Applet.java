// 
// Decompiled by Procyon v0.5.30
// 

package ru.kryshen.graphg;

import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Container;

public class Applet extends java.applet.Applet
{
    private RenderingManager rm;
    private Game game;
    private boolean loaded;
    private boolean loading;
    
    public Applet() {
        this.rm = new RenderingManager();
        this.game = new Game();
        this.loaded = false;
        this.loading = false;
    }
    
    public void init() {
        this.game.init(this);
        if (!this.loaded && !this.loading) {
            this.loading = true;
            new Thread() {
                public void run() {
                    try {
                        Applet.this.game.load(new URL(Applet.this.getCodeBase(), "skins/default.bin").openStream());
                        Applet.this.loaded = true;
                    }
                    catch (Exception ex) {
                        Applet.this.game.displayError(ex);
                    }
                    finally {
                        Applet.this.loading = false;
                    }
                }
            }.start();
        }
    }
    
    public void destroy() {
        this.game.destroy();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.rm.updateBuffer(this, graphics, this.getSize());
        this.game.render(this.rm.offscreen);
        super.paint(this.rm.offscreen);
        graphics.drawImage(this.rm.buffer, 0, 0, null);
    }
}
