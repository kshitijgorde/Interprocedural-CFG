import java.util.Properties;

// 
// Decompiled by Procyon v0.5.30
// 

public class f extends Properties
{
    public String a;
    public byte[][] c;
    public String b;
    
    public String getProperty(final String s) {
        return new String(this.c[Integer.parseInt(s)]);
    }
    
    public int size() {
        return this.c.length;
    }
}
