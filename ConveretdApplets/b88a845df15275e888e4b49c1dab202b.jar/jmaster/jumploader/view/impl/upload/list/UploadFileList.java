// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list;

import java.awt.event.MouseEvent;
import java.awt.datatransfer.Clipboard;
import java.util.List;
import java.awt.datatransfer.DataFlavor;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.io.File;
import java.awt.Rectangle;
import java.awt.Point;
import jmaster.jumploader.view.impl.upload.list.dnd.UploadFileTransferable;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.event.KeyEvent;
import jmaster.jumploader.model.api.A.C;
import jmaster.jumploader.model.api.upload.IUploadFile;
import java.util.TooManyListenersException;
import java.awt.dnd.DropTargetListener;
import jmaster.jumploader.view.impl.upload.list.dnd.FileDropListener;
import java.awt.Component;
import javax.swing.TransferHandler;
import jmaster.jumploader.view.impl.upload.list.dnd.UploadFileListTransferHandler;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.model.api.config.ViewConfig;
import javax.swing.Icon;
import java.awt.dnd.DropTarget;
import jmaster.util.C.A;
import jmaster.jumploader.model.api.upload.IUploader;
import java.awt.event.KeyListener;
import jmaster.jumploader.view.impl.upload.list.dnd.IFileDropTarget;
import jmaster.jumploader.model.api.upload.B;
import jmaster.util.swing.easylist.EasyList;

public class UploadFileList extends EasyList implements B, Runnable, IFileDropTarget, KeyListener
{
    private static final long p = 9017089770972464908L;
    private static final String s = "uploadFileList";
    private static final String z = "uploadFileListUpdater";
    private static final long r = 500L;
    private IUploader u;
    protected A w;
    private UploadFileListModel t;
    private long q;
    private DropTarget o;
    private int £;
    private Icon ¢;
    private ViewConfig v;
    static /* synthetic */ Class class$jmaster$jumploader$view$impl$upload$list$IUploadFileListListener;
    static /* synthetic */ Class array$Ljmaster$jumploader$model$api$upload$IUploadFile;
    static /* synthetic */ Class class$jmaster$jumploader$model$api$upload$IUploadFile;
    
    public UploadFileList(final jmaster.jumploader.model.api.B b, final IMainView mainView) {
        this.w = new A((UploadFileList.class$jmaster$jumploader$view$impl$upload$list$IUploadFileListListener == null) ? (UploadFileList.class$jmaster$jumploader$view$impl$upload$list$IUploadFileListListener = class$("jmaster.jumploader.view.impl.upload.list.IUploadFileListListener")) : UploadFileList.class$jmaster$jumploader$view$impl$upload$list$IUploadFileListListener);
        this.q = 500L;
        this.£ = -1;
        this.v = b.H();
        this.setModel(new UploadFileListModel(b));
        this.u = b.D();
        this.t = (UploadFileListModel)super.getModel();
        this.setCellRenderer(this.m = new UploadFileListCellRenderer(b, mainView));
        this.setLayoutOrientation(2);
        final String string = "uploadFileList" + ((this.v.getUploadListViewName() == null) ? "" : this.v.getUploadListViewName());
        this.l.injectProperties(this, string);
        this.l.injectProperties(this.i, string, "emptyListLabel");
        this.addKeyListener(this);
        this.u.addListener(this);
        this.addKeyListener(this);
        this.setTransferHandler(new UploadFileListTransferHandler());
        this.setDragEnabled(b.B().isUploadQueueReorderingAllowed());
        this.setDropTarget(this.o = new DropTarget());
        this.o.setComponent(this);
        this.o.setActive(this.u.isFileAdditionEnabled());
        try {
            this.o.addDropTargetListener(new FileDropListener(this));
        }
        catch (TooManyListenersException ex) {
            ex.printStackTrace();
        }
        final jmaster.util.B.B b2 = new jmaster.util.B.B();
        b2.A(this);
        b2.A("uploadFileListUpdater");
        b2.A(true);
        b2.B(true);
        b.C().A(b2);
        this.updateView();
    }
    
    public long getUpdateInterval() {
        return this.q;
    }
    
    public void setUpdateInterval(final long q) {
        this.q = q;
    }
    
    public Icon getInsertIcon() {
        return this.¢;
    }
    
    public void setInsertIcon(final Icon ¢) {
        this.¢ = ¢;
    }
    
    public void addListener(final IUploadFileListListener uploadFileListListener) {
        this.w.C(uploadFileListListener);
    }
    
    public void removeListener(final IUploadFileListListener uploadFileListListener) {
        this.w.A(uploadFileListListener);
    }
    
    private void A(final IUploadFile[] array) {
        for (int i = 0; i < this.w.C(); ++i) {
            ((IUploadFileListListener)this.w.A(i)).uflRemoveFilesAction(this, array);
        }
    }
    
    private void A(final String[] array) {
        for (int i = 0; i < this.w.C(); ++i) {
            ((IUploadFileListListener)this.w.A(i)).uflAddFilesAction(this, array);
        }
    }
    
    public void uploaderFileAdditionEnabledChanged(final IUploader uploader) {
        this.o.setActive(uploader.isFileAdditionEnabled());
    }
    
    public void uploaderFileRemovalEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileAdded(final IUploader uploader, final IUploadFile uploadFile) {
        this.updateView();
    }
    
    public void uploaderFileRemoved(final IUploader uploader, final IUploadFile uploadFile) {
        this.updateView();
    }
    
    public void uploaderFileMoved(final IUploader uploader, final IUploadFile uploadFile, final int n) {
        this.updateView();
    }
    
    public void uploaderFileStatusChanged(final IUploader uploader, final IUploadFile uploadFile) {
        if (this.v.isUploadViewAutoscrollToUploadingFile() && uploadFile.isUploading()) {
            this.scrollToFile(uploadFile);
        }
    }
    
    public void uploaderFilesReset(final IUploader uploader) {
        this.updateView();
    }
    
    public void uploaderStatusChanged(final IUploader uploader) {
    }
    
    public void uploaderUploadEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileAddFailed(final IUploader uploader, final C c) {
    }
    
    public void uploaderFileUpdated(final IUploader uploader, final IUploadFile uploadFile) {
        this.updateView();
    }
    
    public void run() {
        while (!this.u.isDestroyed() && !Thread.currentThread().isInterrupted()) {
            this.updateUploadingCells();
            try {
                Thread.sleep(this.q);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (!keyEvent.isConsumed()) {
            if (keyEvent.getKeyCode() == 127) {
                final IUploadFile[] selectedFiles = this.getSelectedFiles();
                if (selectedFiles != null) {
                    this.A(selectedFiles);
                    keyEvent.consume();
                }
            }
            if (keyEvent.getKeyCode() == 65487 || keyEvent.getKeyCode() == 86) {
                this.pasteFromClipoboard();
                keyEvent.consume();
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void dragEnter(final DropTargetDragEvent dropTargetDragEvent) {
    }
    
    public void dragExit(final DropTargetEvent dropTargetEvent) {
        if (this.£ != -1) {
            this.£ = -1;
            this.repaint();
        }
    }
    
    public void dragOver(final DropTargetDragEvent dropTargetDragEvent) {
        if (dropTargetDragEvent.isDataFlavorSupported(UploadFileTransferable.getDataFlavor())) {
            final Point location = dropTargetDragEvent.getLocation();
            int n = this.getCellAtLocation(location);
            if (n == -1) {
                Point point;
                for (Rectangle visibleRect = this.getVisibleRect(); n == -1 && visibleRect.contains(location); n = this.getCellAtLocation(location), point = location, --point.x) {}
            }
            int size;
            if (n != -1) {
                if (this.getCellBounds(n, n).getCenterX() > location.x) {
                    size = n;
                }
                else {
                    size = n + 1;
                }
            }
            else {
                size = this.getModel().getSize();
            }
            if (size != this.£) {
                this.£ = size;
                this.j.D("cell=" + n + ", insertIndex=" + this.£);
                this.repaint();
            }
        }
    }
    
    public void dropActionChanged(final DropTargetDragEvent dropTargetDragEvent) {
        if (this.£ != -1) {
            this.£ = -1;
            this.repaint();
        }
    }
    
    public void filesDropped(final File[] array) {
        if (this.£ != -1) {
            this.£ = -1;
            this.repaint();
        }
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[i].getAbsolutePath();
        }
        this.A(array2);
    }
    
    public void moveFiles(final IUploadFile[] array) {
        try {
            jmaster.util.C.B.B(this.u, "moveFiles", new Class[] { (UploadFileList.array$Ljmaster$jumploader$model$api$upload$IUploadFile == null) ? (UploadFileList.array$Ljmaster$jumploader$model$api$upload$IUploadFile = class$("[Ljmaster.jumploader.model.api.upload.IUploadFile;")) : UploadFileList.array$Ljmaster$jumploader$model$api$upload$IUploadFile, Integer.TYPE }, new Object[] { array, new Integer(this.£) });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (this.£ != -1) {
            this.£ = -1;
            this.repaint();
        }
    }
    
    public void updateUploadingCells() {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.C(this, "updateUploadingCells");
            return;
        }
        for (int i = 0; i < this.t.getSize(); ++i) {
            final IUploadFile uploadFile = this.t.getUploadFile(i);
            if (uploadFile.isDownloading() || uploadFile.isUploading() || uploadFile.isPreprocessing() || uploadFile.isServerProcessing()) {
                this.repaintCell(i);
            }
        }
    }
    
    public IUploadFile[] getSelectedFiles() {
        IUploadFile[] array = null;
        final int[] selectedIndices = this.getSelectedIndices();
        if (selectedIndices != null && selectedIndices.length > 0) {
            array = new IUploadFile[selectedIndices.length];
            for (int i = 0; i < selectedIndices.length; ++i) {
                array[i] = this.t.getUploadFile(selectedIndices[i]);
            }
        }
        return array;
    }
    
    public void updateView() {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.A(this);
            return;
        }
        this.setBackgroundVisible(this.getModel().getSize() == 0);
    }
    
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        if (this.£ != -1 && this.¢ != null) {
            Rectangle rectangle;
            int n;
            int n2;
            if (this.£ < this.t.getSize()) {
                rectangle = this.getCellBounds(this.£, this.£);
                n = rectangle.x - this.¢.getIconWidth() / 2;
                n2 = rectangle.y + rectangle.height / 2 - this.¢.getIconHeight() / 2;
            }
            else {
                rectangle = this.getCellBounds(this.t.getSize() - 1, this.t.getSize() - 1);
                n = rectangle.x + rectangle.width - this.¢.getIconWidth() / 2;
                n2 = rectangle.y + rectangle.height / 2 - this.¢.getIconHeight() / 2;
            }
            this.j.D("i=" + this.£ + ", x=" + n + ", y=" + n2 + ", b=" + rectangle);
            this.¢.paintIcon(this, graphics, n, n2);
            int n3 = -1;
            int n4 = -1;
            if (this.£ > 0 && this.£ < this.t.getSize()) {
                final Rectangle cellBounds = this.getCellBounds(this.£ - 1, this.£ - 1);
                n3 = cellBounds.x + cellBounds.width - this.¢.getIconWidth() / 2;
                n4 = cellBounds.y + cellBounds.height / 2 - this.¢.getIconHeight() / 2;
            }
            if (n3 != -1 && n4 != -1 && n != n3 && n2 != n4) {
                this.¢.paintIcon(this, graphics, n3, n4);
            }
        }
    }
    
    public void pasteFromClipoboard() {
        final Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        if (systemClipboard.isDataFlavorAvailable(DataFlavor.javaFileListFlavor)) {
            try {
                final List<File> list = (List<File>)systemClipboard.getData(DataFlavor.javaFileListFlavor);
                final File[] array = new File[list.size()];
                for (int i = 0; i < list.size(); ++i) {
                    array[i] = list.get(i);
                }
                this.filesDropped(array);
            }
            catch (Exception ex) {
                this.j.E("Failed to get clipboard data", ex);
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        super.mouseClicked(mouseEvent);
    }
    
    public void scrollToFile(final IUploadFile uploadFile) {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.C(this, "scrollToFile", new Class[] { (UploadFileList.class$jmaster$jumploader$model$api$upload$IUploadFile == null) ? (UploadFileList.class$jmaster$jumploader$model$api$upload$IUploadFile = class$("jmaster.jumploader.model.api.upload.IUploadFile")) : UploadFileList.class$jmaster$jumploader$model$api$upload$IUploadFile }, new Object[] { uploadFile });
            return;
        }
        final int indexOfItem = this.t.indexOfItem(uploadFile);
        if (indexOfItem != -1) {
            this.scrollRectToVisible(this.getCellBounds(indexOfItem, indexOfItem));
        }
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
