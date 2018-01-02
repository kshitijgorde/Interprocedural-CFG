// 
// Decompiled by Procyon v0.5.30
// 

public class ax implements z
{
    public final int a(final av av, final av av2) {
        if (av.s().toLowerCase().compareTo(av2.s().toLowerCase()) > 0) {
            return -1;
        }
        if (av.s().toLowerCase().compareTo(av2.s().toLowerCase()) < 0) {
            return 1;
        }
        return 0;
    }
}
