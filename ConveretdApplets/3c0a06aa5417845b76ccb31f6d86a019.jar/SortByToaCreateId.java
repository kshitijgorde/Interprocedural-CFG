import java.util.Comparator;

// 
// Decompiled by Procyon v0.5.30
// 

public class SortByToaCreateId implements Comparator
{
    public int compare(final Object o1, final Object o2) {
        if (!(o1 instanceof CpuProcess)) {
            return 0;
        }
        if (!(o2 instanceof CpuProcess)) {
            return 0;
        }
        final CpuProcess left = (CpuProcess)o1;
        final CpuProcess right = (CpuProcess)o2;
        if (left.getCreateId() == 0) {
            return -1;
        }
        if (right.getCreateId() == 0) {
            return 1;
        }
        if (left.getTOA() == right.getTOA()) {
            return (left.getCreateId() < right.getCreateId()) ? -1 : 1;
        }
        return (left.getTOA() < right.getTOA()) ? -1 : 1;
    }
}
