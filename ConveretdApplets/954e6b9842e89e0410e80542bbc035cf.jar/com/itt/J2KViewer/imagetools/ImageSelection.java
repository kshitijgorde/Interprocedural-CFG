// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.imagetools;

import java.io.IOException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.datatransfer.DataFlavor;
import java.awt.Image;
import java.awt.datatransfer.Transferable;

public class ImageSelection implements Transferable
{
    private Image image;
    
    public ImageSelection(final Image image) {
        this.image = image;
    }
    
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] { DataFlavor.imageFlavor };
    }
    
    public boolean isDataFlavorSupported(final DataFlavor dataFlavor) {
        return DataFlavor.imageFlavor.equals(dataFlavor);
    }
    
    public Object getTransferData(final DataFlavor dataFlavor) throws UnsupportedFlavorException, IOException {
        if (!DataFlavor.imageFlavor.equals(dataFlavor)) {
            throw new UnsupportedFlavorException(dataFlavor);
        }
        return this.image;
    }
}
