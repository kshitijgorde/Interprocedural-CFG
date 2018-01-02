import java.text.FieldPosition;
import java.net.MalformedURLException;
import java.text.ParsePosition;
import java.util.Hashtable;
import java.text.Format;

// 
// Decompiled by Procyon v0.5.30
// 

public final class at extends Format
{
    public d a;
    public boolean b;
    public String c;
    public final Hashtable d;
    
    public at(final d a, final String s, final boolean b) {
        this.d = new Hashtable();
        this.a = a;
        this.b = b;
        final String c = (s == null) ? "" : s.trim();
        this.c = c;
        if (c.length() == 0) {
            this.c = null;
        }
    }
    
    public final Object parseObject(String s, final ParsePosition parsePosition) {
        Object b = null;
        if (s == null) {
            s = "";
        }
        s = s.trim();
        if (this.c != null) {
            s = this.c + s;
        }
        if (s.length() > 0) {
            try {
                b = this.a.d().b(s);
                parsePosition.setIndex(s.length() - ((this.c != null) ? this.c.length() : 0) + 1);
            }
            catch (MalformedURLException ex) {}
        }
        return b;
    }
    
    public final StringBuffer format(final Object o, final StringBuffer sb, final FieldPosition fieldPosition) {
        return sb;
    }
}
