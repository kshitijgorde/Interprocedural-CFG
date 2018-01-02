import java.awt.Component;
import java.awt.TextArea;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class InfoWindow extends Frame
{
    TextArea \u0120;
    
    InfoWindow(final String s) {
        super(s);
        this.add("Center", this.\u0120 = new TextArea(40, 40));
        this.resize(200, 200);
    }
    
    public void addText(final String s) {
        this.\u0120.appendText(s);
    }
}
