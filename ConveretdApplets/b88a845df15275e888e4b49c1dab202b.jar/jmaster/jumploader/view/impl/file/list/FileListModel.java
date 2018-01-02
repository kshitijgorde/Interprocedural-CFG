// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.file.list;

import jmaster.jumploader.model.api.config.ViewConfig;
import jmaster.jumploader.model.api.C.C;
import jmaster.jumploader.model.api.file.IFile;
import jmaster.jumploader.model.api.C.A;
import jmaster.jumploader.model.api.file.IFileBrowser;
import jmaster.jumploader.model.api.C.B;
import jmaster.jumploader.model.api.file.IFileBrowserListener;
import jmaster.util.swing.easylist.EasyListModel;

public class FileListModel extends EasyListModel implements IFileBrowserListener, B
{
    private static final long \u00ca = 2671009035681750297L;
    private jmaster.jumploader.model.api.B \u00c9;
    static /* synthetic */ Class class$jmaster$jumploader$model$api$file$IFile;
    
    public FileListModel(final jmaster.jumploader.model.api.B \u00e9) {
        this.\u00c9 = \u00e9;
        \u00e9.G().addListener(this);
        \u00e9.A().A(this);
    }
    
    public void fileBrowserLocationChanged(final IFileBrowser fileBrowser) {
        this.reset();
    }
    
    public void fileBrowserLocationFilesListed(final IFileBrowser fileBrowser) {
        this.reset();
    }
    
    public void thumbnailManagerThumbnailChanged(final A a, final IFile file, final C c) {
        this.updateFile(file);
    }
    
    public void thumbnailManagerStatusChanged(final A a) {
    }
    
    public void reset() {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.C(this, "reset");
            return;
        }
        this.clear();
        final IFileBrowser g = this.\u00c9.G();
        final ViewConfig h = this.\u00c9.H();
        for (int i = 0; i < g.getFileCount(); ++i) {
            final IFile file = g.getFile(i);
            int n = (!file.isHidden() || h.isFileListViewShowHiddenFiles()) ? 1 : 0;
            if (n != 0 && !h.isFileListViewShowFolders() && (file.isDirectory() || file.isDrive() || !file.isFileSystem())) {
                n = 0;
            }
            if (n != 0) {
                this.addItem(file);
            }
        }
    }
    
    public void updateFile(final IFile file) {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.C(this, "updateFile", new Class[] { (FileListModel.class$jmaster$jumploader$model$api$file$IFile == null) ? (FileListModel.class$jmaster$jumploader$model$api$file$IFile = class$("jmaster.jumploader.model.api.file.IFile")) : FileListModel.class$jmaster$jumploader$model$api$file$IFile }, new Object[] { file });
            return;
        }
        final int indexOfItem = this.indexOfItem(file);
        if (indexOfItem != -1) {
            this.fireContentsChanged(this, indexOfItem, indexOfItem);
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
