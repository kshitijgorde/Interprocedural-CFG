// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.io.IOException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

class CClipboardData extends StringSelection
{
    private String[] cMimeTypes;
    private String[] cData;
    private byte[][] cDataBytes;
    private DataFlavor[] cFlavors;
    
    CClipboardData(final String s, final String[] cMimeTypes, final String[] cData) {
        super(s);
        this.cMimeTypes = cMimeTypes;
        this.cData = cData;
        this.cDataBytes = new byte[this.cData.length][];
    }
    
    private void makeFlavors() {
        if (this.cFlavors == null) {
            int n;
            for (int i = n = 0; i < this.cMimeTypes.length; ++i) {
                if (this.cMimeTypes[i].equals("text/plain")) {
                    n += 2;
                }
                else if (this.cMimeTypes[i].equals("text/html")) {
                    ++n;
                }
            }
            this.cFlavors = new DataFlavor[n];
            for (int j = 0; j < n; ++j) {
                if (this.cMimeTypes[j].equals("text/plain")) {
                    this.cFlavors[j++] = DataFlavor.stringFlavor;
                    this.cFlavors[j] = DataFlavor.plainTextFlavor;
                }
                else if (this.cMimeTypes[j].equals("text/html")) {
                    this.cFlavors[j] = new DataFlavor("text/html", "HTML");
                }
            }
        }
    }
    
    public DataFlavor[] getTransferDataFlavors() {
        this.makeFlavors();
        return this.cFlavors;
    }
    
    public boolean isDataFlavorSupported(final DataFlavor dataFlavor) {
        this.makeFlavors();
        for (int i = 0; i < this.cFlavors.length; ++i) {
            if (dataFlavor.equals(this.cFlavors[i])) {
                return true;
            }
        }
        return false;
    }
    
    public Object getTransferData(final DataFlavor dataFlavor) throws UnsupportedFlavorException, IOException {
        this.makeFlavors();
        final boolean equals = dataFlavor.equals(DataFlavor.stringFlavor);
        String s;
        boolean equals2;
        if (equals) {
            s = "text/plain";
            equals2 = false;
        }
        else {
            equals2 = dataFlavor.equals(DataFlavor.plainTextFlavor);
            s = (equals2 ? "text/plain" : dataFlavor.getMimeType());
        }
        int i = 0;
        while (i < this.cMimeTypes.length) {
            if (this.isBasicallyEqual(s, this.cMimeTypes[i])) {
                if (equals) {
                    return this.cData[i];
                }
                if (equals2) {
                    return new StringReader(new String(this.cData[i]));
                }
                if (this.cDataBytes[i] == null) {
                    this.cDataBytes[i] = this.cData[i].getBytes("UTF8");
                }
                return new ByteArrayInputStream(this.cDataBytes[i]);
            }
            else {
                ++i;
            }
        }
        throw new UnsupportedFlavorException(dataFlavor);
    }
    
    private boolean isBasicallyEqual(String s, String s2) {
        final int index = s.indexOf(59);
        s = ((index == -1) ? s : s.substring(0, index));
        final int index2 = s2.indexOf(59);
        s2 = ((index2 == -1) ? s2 : s2.substring(0, index2));
        return s.equals(s2);
    }
}
