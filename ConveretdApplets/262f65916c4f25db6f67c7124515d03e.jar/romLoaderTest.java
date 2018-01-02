import java.awt.Component;
import IO.romLoader;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class romLoaderTest extends Applet
{
    private romLoader a;
    
    public void init() {
        (this.a = new romLoader(this)).romLoaderFile("http://www.i-console.com/emu/gameLoad.php?g=22");
    }
    
    public void start() {
        this.a.run();
    }
}
