// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.datatransfer.DataFlavor;
import java.util.ArrayList;
import java.io.File;
import java.awt.datatransfer.Transferable;

public final class r implements Transferable
{
    private File a;
    private static final ArrayList b;
    
    public r(final File a) {
        this.a = a;
    }
    
    public final Object getTransferData(final DataFlavor dataFlavor) {
        if (dataFlavor.equals(DataFlavor.javaFileListFlavor)) {
            final ArrayList list;
            (list = new ArrayList()).add(this.a);
            return list;
        }
        throw new UnsupportedFlavorException(dataFlavor);
    }
    
    public final DataFlavor[] getTransferDataFlavors() {
        return r.b.toArray(new DataFlavor[r.b.size()]);
    }
    
    public final boolean isDataFlavorSupported(final DataFlavor dataFlavor) {
        return r.b.contains(dataFlavor);
    }
    
    static {
        final ArrayList b2;
        (b2 = new ArrayList()).add(DataFlavor.javaFileListFlavor);
        b = b2;
    }
}
