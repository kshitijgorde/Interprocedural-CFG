// 
// Decompiled by Procyon v0.5.30
// 

package b;

public abstract class d
{
    public static String a;
    
    static {
        d.a = System.getProperty("line.separator");
    }
    
    public abstract c[] if();
    
    public abstract String do();
    
    public String for() {
        return String.valueOf(this.do()) + " Version: " + this.int().do();
    }
    
    public c int() {
        return this.if()[0];
    }
    
    public String a() {
        String s = "";
        final c[] if1 = this.if();
        for (int i = 0; i < if1.length; ++i) {
            if (i > 0) {
                s = String.valueOf(s) + d.a;
            }
            s = String.valueOf(s) + if1[i].do() + d.a + "========================" + d.a + if1[i].if() + d.a;
        }
        return s;
    }
}
