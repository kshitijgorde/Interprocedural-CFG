// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.examples;

import java.awt.Component;
import com.fluendo.player.Cortado;
import java.awt.Frame;

public class Player extends Frame
{
    private static final long serialVersionUID = 1L;
    Cortado applet;
    
    public Player(final String url) {
        (this.applet = new Cortado()).setSize(352, 270);
        this.setSize(352, 270);
        this.applet.setParam("url", url);
        this.applet.setParam("local", "false");
        this.applet.setParam("framerate", "60");
        this.applet.setParam("keepaspect", "true");
        this.applet.setParam("video", "true");
        this.applet.setParam("audio", "true");
        this.applet.setParam("bufferSize", "200");
        this.applet.setParam("userId", "wim");
        this.applet.setParam("password", "taymans");
        this.add(this.applet);
        this.show();
        this.applet.init();
        this.applet.start();
    }
    
    public static void main(final String[] args) {
        if (args.length < 1) {
            System.out.println("usage: Player <uri>");
            return;
        }
        final Player p = new Player(args[0]);
        synchronized (p) {
            try {
                p.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
}
