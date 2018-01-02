import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Cursor;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class money extends Applet implements Runnable
{
    Interface myInterface;
    MML mml;
    ML ml;
    Thread Money;
    
    public money() {
        this.myInterface = null;
        this.mml = null;
    }
    
    public void init() {
        this.myInterface = new Interface(this);
        this.mml = new MML(this.myInterface);
        this.ml = new ML(this.myInterface);
        this.setCursor(new Cursor(12));
    }
    
    public void run() {
        this.myInterface.LoadImages();
        this.myInterface.Start();
        this.addMouseMotionListener(this.mml);
        this.addMouseListener(this.ml);
        while (true) {
            this.myInterface.Paint();
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
        (this.Money = new Thread(this)).start();
    }
}
