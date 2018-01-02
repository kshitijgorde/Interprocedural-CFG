// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class bQ extends ao
{
    public bQ(final String s) {
        super(-999, s);
        super.b = -1;
    }
    
    public bQ(final ao ao) {
        super(ao.i, new String(ao.d));
        super.b = ao.b;
        super.b = ao.b;
        super.a = ao.a;
        super.c = ao.c;
        super.d = ao.d;
        super.e = ao.e;
        super.a = new String(ao.a);
    }
    
    public final boolean a(final String s, final Object o) {
        if ("restricted".equals(s)) {
            final boolean booleanValue = (boolean)o;
            if (this.a(36) != booleanValue) {
                super.j = 1;
            }
            this.a(36, booleanValue);
            return true;
        }
        return false;
    }
    
    public final String a() {
        return bm.a(aS.a(654), new String[] { " (ID=" + super.i + ")" });
    }
    
    public final Object a(final String s) {
        if ("type".equals(s)) {
            if (this.a(6) && !this.a(7)) {
                return aS.a(421);
            }
            if (!this.a(6) && this.a(7)) {
                return aS.a(420);
            }
            return aS.a(419);
        }
        else {
            if ("restricted".equals(s)) {
                return new Boolean(this.a(36));
            }
            return super.a(s);
        }
    }
}
