// 
// Decompiled by Procyon v0.5.30
// 

public class e
{
    public String a;
    public String b;
    public c c;
    
    public e(final String a) {
        final int index = a.indexOf(61);
        if (index >= 0) {
            this.a = a.substring(0, index);
            this.b = a.substring(index + 1);
            return;
        }
        this.a = a;
    }
}
