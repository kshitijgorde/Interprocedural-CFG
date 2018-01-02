import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ryEVItem extends Applet
{
    String \u016e;
    String \u016d;
    String \u016c;
    String \u016b;
    URL \u016a;
    int \u0169;
    int \u0168;
    ryElevator \u0167;
    boolean \u0166;
    
    public ryEVItem(final ryElevator \u0167, final String \u016f, final String \u016b, final String \u016d, final String \u016d2, final int \u0169, final int \u01692) {
        this.\u0167 = \u0167;
        this.\u016e = \u016f;
        this.\u0166 = this.\u016e.equals("-");
        this.\u016b = \u016b;
        this.\u016d = \u016d;
        if (!this.\u0166) {
            this.\u016c = \u016d2;
        }
        else {
            this.\u016c = null;
        }
        this.\u0169 = \u0169;
        this.\u0168 = \u01692;
        if (!this.\u0166 && \u016b != null && !\u016b.startsWith("$") && !\u016b.toLowerCase().startsWith("javascript")) {
            try {
                this.\u016a = new URL(this.\u0167.getDocumentBase(), \u016b);
                return;
            }
            catch (Exception ex) {
                this.\u016a = null;
                return;
            }
        }
        this.\u016a = null;
    }
}
