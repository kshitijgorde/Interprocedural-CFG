// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class aS extends as implements bk
{
    public Object a(final String s) {
        if ("replace".equals(s)) {
            return super.a;
        }
        if ("name".equals(s)) {
            return this.g();
        }
        return super.a(s);
    }
    
    public String b(final String s) {
        return aG.a("Click here to edit this item.");
    }
    
    public aS(final as as) {
        this(as.b(), as.g());
        this.a(as.d());
        super.a = as.a;
    }
    
    public aS(final int n, final String s) {
        super(n, s);
    }
}
