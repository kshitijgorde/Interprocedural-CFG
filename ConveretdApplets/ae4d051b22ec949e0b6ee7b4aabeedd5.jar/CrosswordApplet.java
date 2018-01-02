import mika.application.Application;
import mika.application.ApplicationApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CrosswordApplet extends ApplicationApplet
{
    public Application createApplication() {
        return new Crossword();
    }
}
