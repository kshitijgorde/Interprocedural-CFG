// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.file.tree.dnd;

import jmaster.jumploader.view.impl.file.tree.FileTree;
import java.awt.datatransfer.Transferable;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class FileTreeTransferHandler extends TransferHandler
{
    private static final long A = 3319543398978127344L;
    
    public int getSourceActions(final JComponent component) {
        return 1;
    }
    
    protected Transferable createTransferable(final JComponent component) {
        return new FileTransferable(((FileTree)component).getSelectedFiles());
    }
}
