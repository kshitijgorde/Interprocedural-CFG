import mika.application.Application;
import mika.application.ApplicationWindow;

// 
// Decompiled by Procyon v0.5.30
// 

public class CrosswordWindow extends ApplicationWindow
{
    public Application createApplication() {
        return new Crossword();
    }
}
