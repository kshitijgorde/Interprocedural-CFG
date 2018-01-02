import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Vivace extends Applet
{
    public void init() {
        this.play(this.getCodeBase(), this.getParameter("uLaw8000AU"));
    }
}
