// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list.dnd;

import java.util.Iterator;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.awt.datatransfer.UnsupportedFlavorException;
import jmaster.jumploader.model.api.file.IFile;
import java.util.List;
import java.util.Vector;
import jmaster.util.C.B;
import java.net.URLDecoder;
import java.net.URL;
import java.io.File;
import java.util.StringTokenizer;
import java.awt.datatransfer.DataFlavor;
import jmaster.jumploader.view.impl.upload.list.UploadFileList;
import jmaster.jumploader.model.api.upload.IUploadFile;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetListener;

public class FileDropListener implements DropTargetListener
{
    private IFileDropTarget A;
    static /* synthetic */ Class array$Ljava$io$File;
    
    public FileDropListener(final IFileDropTarget a) {
        this.A = a;
    }
    
    public void dragEnter(final DropTargetDragEvent dropTargetDragEvent) {
        this.A.dragEnter(dropTargetDragEvent);
    }
    
    public void dragOver(final DropTargetDragEvent dropTargetDragEvent) {
        this.A.dragOver(dropTargetDragEvent);
    }
    
    public void dropActionChanged(final DropTargetDragEvent dropTargetDragEvent) {
        this.A.dropActionChanged(dropTargetDragEvent);
    }
    
    public void dragExit(final DropTargetEvent dropTargetEvent) {
        this.A.dragExit(dropTargetEvent);
    }
    
    public void drop(final DropTargetDropEvent dropTargetDropEvent) {
        final Transferable transferable = dropTargetDropEvent.getTransferable();
        final DataFlavor[] transferDataFlavors = transferable.getTransferDataFlavors();
        Label_0267: {
            if (transferable.isDataFlavorSupported(UploadFileTransferable.getDataFlavor())) {
                try {
                    dropTargetDropEvent.acceptDrop(dropTargetDropEvent.getDropAction());
                    final IUploadFile[] array = (IUploadFile[])transferable.getTransferData(UploadFileTransferable.getDataFlavor());
                    if (this.A != null && this.A instanceof UploadFileList) {
                        ((UploadFileList)this.A).moveFiles(array);
                    }
                    return;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    break Label_0267;
                }
            }
            if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    dropTargetDropEvent.acceptDrop(dropTargetDropEvent.getDropAction());
                    final StringTokenizer stringTokenizer = new StringTokenizer((String)transferable.getTransferData(DataFlavor.stringFlavor));
                    final int countTokens = stringTokenizer.countTokens();
                    final File[] array2 = new File[countTokens];
                    for (int i = 0; i < countTokens; ++i) {
                        array2[i] = new File(URLDecoder.decode(new URL(stringTokenizer.nextToken()).getFile(), "UTF-8"));
                    }
                    B.B(this.A, "filesDropped", new Class[] { (FileDropListener.array$Ljava$io$File == null) ? (FileDropListener.array$Ljava$io$File = class$("[Ljava.io.File;")) : FileDropListener.array$Ljava$io$File }, new Object[] { array2 });
                    return;
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        final Vector vector = new Vector<File>();
        dropTargetDropEvent.acceptDrop(dropTargetDropEvent.getDropAction());
        for (int j = 0; j < transferDataFlavors.length; ++j) {
            try {
                final Object transferData = transferable.getTransferData(transferDataFlavors[j]);
                if (transferData instanceof List) {
                    final Iterator<Object> iterator = ((List<Object>)transferData).iterator();
                    while (iterator.hasNext()) {
                        vector.addElement(iterator.next());
                    }
                }
                if (transferData instanceof File[]) {
                    final File[] array3 = (File[])transferData;
                    for (int k = 0; k < array3.length; ++k) {
                        vector.add(array3[k]);
                    }
                }
                if (transferData instanceof IFile[]) {
                    final IFile[] array4 = (IFile[])transferData;
                    for (int l = 0; l < array4.length; ++l) {
                        vector.add(array4[l].getFile());
                    }
                }
            }
            catch (UnsupportedFlavorException ex3) {
                ex3.printStackTrace();
            }
            catch (IOException ex4) {
                ex4.printStackTrace();
            }
        }
        final File[] array5 = new File[vector.size()];
        for (int n = 0; n < array5.length; ++n) {
            array5[n] = vector.get(n);
        }
        B.B(this.A, "filesDropped", new Class[] { (FileDropListener.array$Ljava$io$File == null) ? (FileDropListener.array$Ljava$io$File = class$("[Ljava.io.File;")) : FileDropListener.array$Ljava$io$File }, new Object[] { array5 });
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
