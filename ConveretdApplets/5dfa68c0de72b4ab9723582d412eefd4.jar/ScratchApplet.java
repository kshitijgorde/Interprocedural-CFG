import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ScratchApplet extends JApplet
{
    LContext lc;
    
    public static void setSensorValue(final int n, final int n2) {
        if (n < 0 || n > 15) {
            return;
        }
        PlayerPrims.sensorValues[n] = n2;
    }
    
    public static int getSensorValue(final int n) {
        if (n < 0 || n > 15) {
            return 0;
        }
        return PlayerPrims.sensorValues[n];
    }
    
    public void init() {
        final String string = this.getCodeBase().toString();
        final String parameter = this.getParameter("project");
        final String string2;
        if (parameter != null) {
            string2 = string + parameter;
        }
        final String s = string2;
        final String parameter2 = this.getParameter("autostart");
        boolean b = true;
        if (parameter2 != null) {
            if (parameter2.equalsIgnoreCase("false")) {
                b = false;
            }
            if (parameter2.equalsIgnoreCase("no")) {
                b = false;
            }
        }
        try {
            Thread.sleep(50L);
        }
        catch (InterruptedException ex) {}
        this.lc = PlayerPrims.startup(string, s, this.getContentPane(), b);
        this.lc.tyo = System.out;
    }
    
    public void destroy() {
        PlayerPrims.shutdown(this.lc);
    }
}
