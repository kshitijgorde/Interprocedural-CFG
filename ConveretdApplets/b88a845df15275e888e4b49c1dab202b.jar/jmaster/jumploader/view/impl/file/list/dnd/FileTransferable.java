// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.file.list.dnd;

import java.awt.datatransfer.Clipboard;
import java.io.IOException;
import java.awt.datatransfer.UnsupportedFlavorException;
import jmaster.jumploader.model.api.file.IFile;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;

public class FileTransferable implements Transferable, ClipboardOwner
{
    public static final String MIME_TYPE;
    private static DataFlavor B;
    private DataFlavor[] C;
    private IFile[] A;
    static /* synthetic */ Class class$jmaster$jumploader$view$impl$file$list$dnd$FileTransferable;
    
    public FileTransferable(final IFile[] a) {
        this.A = a;
        this.C = new DataFlavor[] { FileTransferable.B };
    }
    
    public static DataFlavor getRosterItemDataFlavor() {
        return FileTransferable.B;
    }
    
    public Object getTransferData(final DataFlavor dataFlavor) throws UnsupportedFlavorException, IOException {
        return this.A;
    }
    
    public DataFlavor[] getTransferDataFlavors() {
        return this.C;
    }
    
    public boolean isDataFlavorSupported(final DataFlavor dataFlavor) {
        return dataFlavor.equals(FileTransferable.B);
    }
    
    public void lostOwnership(final Clipboard clipboard, final Transferable transferable) {
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        MIME_TYPE = "application/x-java-jvm-local-objectref;class=" + ((FileTransferable.class$jmaster$jumploader$view$impl$file$list$dnd$FileTransferable == null) ? (FileTransferable.class$jmaster$jumploader$view$impl$file$list$dnd$FileTransferable = class$("jmaster.jumploader.view.impl.file.list.dnd.FileTransferable")) : FileTransferable.class$jmaster$jumploader$view$impl$file$list$dnd$FileTransferable).getName();
        try {
            FileTransferable.B = new DataFlavor(FileTransferable.MIME_TYPE);
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
