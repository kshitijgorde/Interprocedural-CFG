import java.awt.Toolkit;
import java.util.Set;
import java.io.IOException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.datatransfer.DataFlavor;
import java.util.Hashtable;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;

// 
// Decompiled by Procyon v0.5.30
// 

public class TransData implements Transferable
{
    public static final Clipboard clipboard;
    private Hashtable dataTable;
    
    public static final DataFlavor newDataFlavor(String string, final Class clazz, final String s) {
        try {
            string = string + "; class=" + clazz.getName();
            return new DataFlavor(string, s);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public TransData() {
        this.dataTable = new Hashtable();
    }
    
    public static final TransData makeWritable(final Transferable transferable) {
        if (transferable instanceof TransData) {
            return (TransData)transferable;
        }
        final TransData transData = new TransData();
        final DataFlavor[] transferDataFlavors = transferable.getTransferDataFlavors();
        for (int i = 0; i < transferDataFlavors.length; ++i) {
            try {
                transData.add(transferDataFlavors[i], transferable.getTransferData(transferDataFlavors[i]));
            }
            catch (UnsupportedFlavorException ex) {}
            catch (IOException ex2) {}
        }
        return transData;
    }
    
    public final Object add(final DataFlavor dataFlavor, final Object o) {
        if (dataFlavor == null || o == null) {
            throw new NullPointerException("parameter");
        }
        return this.dataTable.put(dataFlavor, o);
    }
    
    public final boolean isDataFlavorSupported(final DataFlavor dataFlavor) {
        return this.dataTable.containsKey(dataFlavor);
    }
    
    public final DataFlavor[] getTransferDataFlavors() {
        final Set keySet = this.dataTable.keySet();
        final DataFlavor[] array = new DataFlavor[keySet.size()];
        keySet.toArray(array);
        return array;
    }
    
    public final Object getTransferData(final DataFlavor dataFlavor) {
        if (!this.dataTable.containsKey(dataFlavor)) {
            throw new UnsupportedFlavorException(dataFlavor);
        }
        return this.dataTable.get(dataFlavor);
    }
    
    static {
        Clipboard systemClipboard = null;
        try {
            systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        }
        catch (Exception ex) {
            System.err.println("Can't get the system clipboard: " + ex.getClass().getName());
        }
        if (systemClipboard != null) {
            clipboard = systemClipboard;
        }
        else {
            clipboard = new Clipboard("Wiris local clipboard");
        }
    }
}
