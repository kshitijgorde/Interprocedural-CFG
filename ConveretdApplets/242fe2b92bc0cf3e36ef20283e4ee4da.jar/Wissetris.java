import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Wissetris extends Applet
{
    public void init() {
        this.setLayout(new BorderLayout());
        this.add("Center", new Speelveld(this.getImage(this.getCodeBase(), "gameon.gif"), this.getImage(this.getCodeBase(), "gameover.gif")));
    }
    
    public static void main(final String[] array) {
    }
}
