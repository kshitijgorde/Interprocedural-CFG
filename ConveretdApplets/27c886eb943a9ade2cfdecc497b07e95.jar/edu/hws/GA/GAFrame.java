// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.GA;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.Frame;

public class GAFrame extends Frame
{
    private GACanvas world;
    private Launcher owner;
    
    public static void main(final String[] array) {
        System.out.println("A simple genetic algorithms demo.\nDavid Eck, eck@hws.edu, http://math.hws.edu/eck/");
        if (array.length >= 1) {
            new GAFrame((Launcher)null);
            return;
        }
        new GAFrame((Launcher)null);
    }
    
    public GAFrame(final Launcher owner) {
        super("The World");
        this.owner = owner;
        this.add(this.world = new GACanvas(this), "Center");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                GAFrame.this.close();
            }
            
            public void windowClosed(final WindowEvent windowEvent) {
                if (GAFrame.this.owner == null) {
                    System.exit(0);
                    return;
                }
                GAFrame.this.owner.frameHasClosed();
            }
            
            public void windowOpened(final WindowEvent windowEvent) {
                GAFrame.this.world.start();
            }
        });
        this.setMenuBar(this.world.getMenuBar());
        this.pack();
        this.world.rWin = new ReportWin();
        this.show();
        this.setResizable(false);
        if (this.owner != null) {
            this.owner.frameHasOpened();
        }
    }
    
    void close() {
        this.world.stop();
        this.world.rWin.dispose();
        this.dispose();
        if (this.owner == null) {
            System.exit(0);
            return;
        }
        this.owner.frameHasClosed();
    }
}
