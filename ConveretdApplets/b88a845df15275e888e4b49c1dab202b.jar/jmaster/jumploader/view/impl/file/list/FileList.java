// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.file.list;

import jmaster.jumploader.model.api.file.IFile;
import jmaster.jumploader.model.api.file.IFileBrowser;
import jmaster.jumploader.model.api.config.ViewConfig;
import javax.swing.TransferHandler;
import jmaster.jumploader.view.impl.file.list.dnd.FileListTransferHandler;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.model.api.B;
import jmaster.jumploader.model.api.file.IFileBrowserListener;
import jmaster.util.swing.easylist.EasyList;

public class FileList extends EasyList implements IFileBrowserListener
{
    private static final long µ = 3364162494800377496L;
    private static final String ¥ = "fileList";
    private B ¤;
    private FileListModel ª;
    
    public FileList(final B ¤, final IMainView mainView) {
        this.¤ = ¤;
        this.setModel(this.ª = new FileListModel(¤));
        this.setCellRenderer(new FileListCellRenderer(¤, mainView));
        final ViewConfig h = ¤.H();
        this.setLayoutOrientation(2);
        final String string = "fileList" + ((h.getUploadListViewName() == null) ? "" : h.getUploadListViewName());
        this.l.injectProperties(this, string);
        this.l.injectProperties(this.i, string, "emptyListLabel");
        this.¤.G().addListener(this);
        this.setDragEnabled(true);
        this.setTransferHandler(new FileListTransferHandler());
    }
    
    public void fileBrowserLocationChanged(final IFileBrowser fileBrowser) {
        this.setBackgroundVisible(true);
    }
    
    public void fileBrowserLocationFilesListed(final IFileBrowser fileBrowser) {
        this.setBackgroundVisible(false);
    }
    
    public IFile[] getSelectedFiles() {
        IFile[] array = null;
        final Object[] selectedValues = this.getSelectedValues();
        if (selectedValues != null) {
            array = new IFile[selectedValues.length];
            for (int i = 0; i < selectedValues.length; ++i) {
                array[i] = (IFile)selectedValues[i];
            }
        }
        return array;
    }
}
