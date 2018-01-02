// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

public final class k
{
    private h a;
    
    public k(final h a) {
        this.a = a;
    }
    
    public final void a(final String s) {
        if (s != null) {
            final String[] split = s.split("\t");
            if (!s.startsWith("CALLBACK") || split.length < 2) {
                return;
            }
            if (split.length == 2) {
                this.a.a(split[1], new String[0]);
                return;
            }
            final String[] array = new String[split.length - 2];
            System.arraycopy(split, 2, array, 0, array.length);
            this.a.a(split[1], array);
        }
    }
}
