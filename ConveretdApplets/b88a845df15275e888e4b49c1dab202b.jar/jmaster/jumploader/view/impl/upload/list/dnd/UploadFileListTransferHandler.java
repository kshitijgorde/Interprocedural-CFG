// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list.dnd;

import jmaster.jumploader.model.api.file.IFile;
import jmaster.jumploader.view.impl.upload.list.UploadFileList;
import java.awt.datatransfer.Transferable;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class UploadFileListTransferHandler extends TransferHandler
{
    private static final long A = 3319543398978127344L;
    
    public int getSourceActions(final JComponent component) {
        return 2;
    }
    
    protected Transferable createTransferable(final JComponent component) {
        return new UploadFileTransferable(((UploadFileList)component).getSelectedFiles());
    }
}
