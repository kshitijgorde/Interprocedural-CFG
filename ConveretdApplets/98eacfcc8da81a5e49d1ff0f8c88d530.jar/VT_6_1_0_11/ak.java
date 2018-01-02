// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.IOException;
import com.hw.client.util.a;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public final class ak implements Transferable
{
    private aR[] a;
    
    public ak() {
    }
    
    public ak(final aR[] a) {
        this.a = a;
    }
    
    public final int a() {
        if (this.a != null) {
            return this.a.length;
        }
        return 0;
    }
    
    public final String[] b() {
        if (this.a == null) {
            return new String[0];
        }
        final String[] array = new String[this.a.length];
        for (int i = 0; i < this.a.length; ++i) {
            array[i] = this.a[i].a().a();
        }
        return array;
    }
    
    public final Object getTransferData(final DataFlavor dataFlavor) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VBTreeNodes.getTransferData: " + dataFlavor.toString());
        }
        try {
            if (dataFlavor.match(new DataFlavor("application/x-java-serialized-object; class=com.wimba.clients.vboard.tree.VBTreeNodes"))) {
                return this.a;
            }
            return null;
        }
        catch (ClassNotFoundException ex2) {
            final ClassNotFoundException ex = ex2;
            com.hw.client.util.a.e(ex2.toString());
            throw new IOException(ex.getMessage());
        }
    }
    
    public final DataFlavor[] getTransferDataFlavors() {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VBTreeNodes.getTransferDataFlavors");
        }
        try {
            return new DataFlavor[] { new DataFlavor("application/x-java-serialized-object; class=com.wimba.clients.vboard.tree.VBTreeNodes") };
        }
        catch (ClassNotFoundException ex) {
            return null;
        }
    }
    
    public final boolean isDataFlavorSupported(final DataFlavor dataFlavor) {
        try {
            if (com.hw.client.util.a.a()) {
                com.hw.client.util.a.b("VBTreeNodes.isDataFlavorSupported");
            }
            final boolean match = dataFlavor.match(new DataFlavor("application/x-java-serialized-object; class=com.wimba.clients.vboard.tree.VBTreeNodes"));
            if (com.hw.client.util.a.a()) {
                com.hw.client.util.a.b("VBTreeNodes.isDataFlavorSupported: return : " + match);
            }
            return match;
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
