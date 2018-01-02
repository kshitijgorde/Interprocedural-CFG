import java.awt.Frame;
import java.awt.Component;
import java.awt.LayoutManager;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mand extends Applet
{
    MandWin mand;
    
    public Mand() {
        this.mand = new MandWin();
        this.setLayout(new AbsoluteLayout());
        this.add("North", this.mand);
        this.show();
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("Mandelbrot Test");
        final Mand mand = new Mand();
        mand.resize(410, 450);
        frame.resize(420, 420);
        frame.add("North", mand);
        frame.show();
    }
}
