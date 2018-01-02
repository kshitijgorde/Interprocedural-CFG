import java.awt.Event;
import java.awt.MenuBar;
import java.awt.CheckboxMenuItem;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

final class a extends Frame
{
    private Menu a;
    private MenuItem b;
    private MenuItem c;
    private Menu d;
    private MenuItem e;
    private CheckboxMenuItem f;
    private MenuItem g;
    private Menu h;
    private MenuItem i;
    
    public a(final String s) {
        super(s);
        final MenuBar menuBar = new MenuBar();
        this.a = new Menu("File");
        this.b = new MenuItem("Open cartridge ROM...");
        this.c = new MenuItem("Exit");
        this.a.add(this.b);
        this.a.add(this.c);
        this.d = new Menu("Emulation");
        this.e = new MenuItem("Reset");
        this.f = new CheckboxMenuItem("Pause");
        this.g = new MenuItem("Properties...");
        this.d.add(this.e);
        this.d.add(this.f);
        this.d.add(new MenuItem("-"));
        this.d.add(this.g);
        this.h = new Menu("Help");
        this.i = new MenuItem("About...");
        this.h.add(this.i);
        menuBar.add(this.a);
        menuBar.add(this.d);
        menuBar.add(this.h);
        this.setMenuBar(menuBar);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.dispose();
                System.exit(0);
                return true;
            }
            case 1001: {
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
}
