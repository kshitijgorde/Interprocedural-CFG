import java.util.Comparator;

// 
// Decompiled by Procyon v0.5.30
// 

public class NetCompare implements Comparator
{
    public int compare(final Object o1, final Object o2) {
        final Network left = (Network)o1;
        final Network right = (Network)o2;
        final int intLeft = left.getNetworkChar();
        final int intRight = right.getNetworkChar();
        if (intLeft == intRight) {
            return 0;
        }
        return (intLeft > intRight) ? 1 : -1;
    }
}
