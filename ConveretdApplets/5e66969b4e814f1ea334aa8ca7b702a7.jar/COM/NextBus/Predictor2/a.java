// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Predictor2;

public final class a
{
    private final String a;
    private final String b;
    private final String c;
    
    private a(final String a, final String b, final String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public final String a() {
        return this.c;
    }
    
    public static a a(final String s) {
        final int index = s.indexOf(45);
        final int lastIndex = s.lastIndexOf(45);
        final String[] array = new String[3];
        if (index == -1 || lastIndex == -1 || index == lastIndex) {
            array[1] = (array[0] = "");
            array[2] = s;
        }
        else {
            array[0] = s.substring(0, index);
            array[1] = s.substring(index + 1, lastIndex);
            array[2] = s.substring(lastIndex + 1);
        }
        return new a(array[0], array[1], array[2]);
    }
    
    private String b() {
        return this.a + "-" + this.b + "-" + this.c;
    }
    
    public final String toString() {
        return this.b();
    }
    
    public final int hashCode() {
        return this.b().hashCode();
    }
    
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        final a a = (a)o;
        if (this.c == null) {
            if (a.c != null) {
                return false;
            }
        }
        else if (!this.c.equals(a.c)) {
            return false;
        }
        if (this.a == null) {
            if (a.a != null) {
                return false;
            }
        }
        else if (!this.a.equals(a.a)) {
            return false;
        }
        if (this.b == null) {
            if (a.b != null) {
                return false;
            }
        }
        else if (!this.b.equals(a.b)) {
            return false;
        }
        return true;
    }
}
