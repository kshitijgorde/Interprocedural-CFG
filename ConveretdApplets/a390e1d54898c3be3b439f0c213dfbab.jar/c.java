import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class c
{
    int a;
    Applet do;
    int if;
    
    public c(final int a, final Applet do1, final int n, final String s) {
        this.a = a;
        this.do = do1;
        this.a(n, s);
    }
    
    public void a(final int n, final String s) {
        try {
            final String string = this.do.getDocumentBase().toString();
            final String s2 = "http://www.G5.dk/highscore/sk_score.asp";
            this.if = (n * 3 + 3) * 2 + (this.a + 5);
            final int n2 = s.length() + string.length();
            this.a(n);
            this.do.getAppletContext().showDocument(new URL(s2 + "?n=" + s + "&s=" + n + "&i=" + this.a + "&c=" + this.if + "&c2=" + n2 + "&u=" + string), "_blank");
        }
        catch (Exception ex) {}
    }
    
    private void a(final int n) {
        this.if = ((under)this.do).a(n, this.a);
    }
}
