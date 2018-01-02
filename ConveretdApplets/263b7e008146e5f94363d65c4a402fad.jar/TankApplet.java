import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import gamelib.AVEntry;
import gamelib.SimpleRandom;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TankApplet extends Applet
{
    static SimpleRandom gen;
    boolean local;
    private Field field;
    
    static {
        TankApplet.gen = new SimpleRandom();
    }
    
    public String getAppletInfo() {
        return "TankGame - a ballistic shooting game \nby Jens Tinz <jens@gamemakers.de>.";
    }
    
    public void init() {
        System.out.println("TankGame (11/05/00)\nby Jens Tinz <jens@gamemakers.de>.\n");
        AVEntry.init(this);
        this.setLayout(new BorderLayout());
        this.field = new Field(this);
        this.field.licensed = this.local;
        this.add(this.field, "Center");
        this.field.requestFocus();
        this.field.init();
    }
    
    public void start() {
        this.field.start();
    }
    
    public void stop() {
        this.field.stop();
    }
}
