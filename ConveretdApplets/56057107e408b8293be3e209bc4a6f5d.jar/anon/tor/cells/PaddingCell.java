// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor.cells;

public class PaddingCell extends Cell
{
    public PaddingCell() {
        super(0);
    }
    
    public PaddingCell(final int n) {
        super(0, n);
    }
    
    public PaddingCell(final int n, final byte[] array, final int n2) {
        super(0, n, array, n2);
    }
}
