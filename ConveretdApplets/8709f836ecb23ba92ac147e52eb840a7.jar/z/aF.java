// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.Arrays;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.awt.Dimension;
import java.io.File;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.io.FileFilter;
import java.awt.dnd.DropTarget;
import java.util.List;

final class aF implements Runnable
{
    private /* synthetic */ List a;
    private /* synthetic */ ay b;
    
    aF(final ay b, final List a) {
        this.b = b;
        this.a = a;
    }
    
    public final void run() {
        this.b.a(this.a);
    }
}
