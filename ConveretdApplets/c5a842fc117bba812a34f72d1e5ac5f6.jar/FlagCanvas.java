import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.awt.Color;
import java.applet.Applet;
import java.util.Hashtable;
import java.net.URL;
import java.awt.Image;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public final class FlagCanvas extends Canvas
{
    static LogicUnit logicUnit;
    Dimension size;
    Container owner;
    String Flag;
    Image imageFlag;
    URL url;
    int pos_x;
    int pos_y;
    String op;
    static Hashtable HS;
    Applet applet;
    
    public FlagCanvas(final String flag, final Container owner, final Applet applet, final String op) {
        FlagCanvas.logicUnit = ((EuroCalc)applet).getLogicUnit();
        applet.getCodeBase().getFile();
        this.op = op;
        this.applet = applet;
        this.size = new Dimension(40, 28);
        this.owner = owner;
        this.resize(this.size);
        this.owner.validate();
        this.setBackground(Color.black);
        this.Flag = flag;
        try {
            this.url = new URL(String.valueOf(EuroCalc.URLBASE) + "flags/");
        }
        catch (MalformedURLException ex) {
            System.out.println("Cannot find the currencies flags");
            System.exit(0);
        }
        this.imageFlag = applet.getImage(this.url, String.valueOf(flag) + ".gif");
        this.repaint();
        FlagCanvas.HS.put(op, this.imageFlag);
    }
    
    public Dimension getPreferredSize() {
        return this.size;
    }
    
    public Dimension getMinimumSize() {
        return this.size;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.imageFlag, this.pos_x, this.pos_y, this);
    }
    
    public Image getImage() {
        return this.imageFlag;
    }
    
    public static Image getImage(final String s) {
        return FlagCanvas.HS.get(s);
    }
    
    public String getOp() {
        return this.op;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.pos_x = 2;
        this.pos_y = 2;
        this.repaint();
        FlagCanvas.logicUnit.setCurrentCurrency(this.op);
        ((EuroCalc)this.applet).setFlagCurr2(this.imageFlag);
        this.applet.requestFocus();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.pos_x = 0;
        this.pos_y = 0;
        this.repaint();
        this.applet.requestFocus();
        return true;
    }
    
    static {
        FlagCanvas.HS = new Hashtable();
    }
}
