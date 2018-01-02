// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.controller;

import java.awt.Image;
import jmaster.jumploader.model.api.file.IFileBrowser;
import jmaster.jumploader.view.api.IGenericView;
import java.awt.image.BufferedImage;
import jmaster.jumploader.view.api.image.IImageView;
import java.io.File;
import jmaster.jumploader.view.api.file.list.IFileListView;
import jmaster.jumploader.view.api.file.tree.IFileTreeView;
import jmaster.jumploader.model.api.upload.IUploadFile;
import jmaster.jumploader.view.api.upload.IUploadView;
import jmaster.jumploader.model.api.upload.IUploader;
import jmaster.util.property.D;
import jmaster.jumploader.view.api.IGenericViewListener;
import java.util.MissingResourceException;
import jmaster.util.property.C;
import jmaster.jumploader.model.api.B;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.util.log.A;
import jmaster.jumploader.view.api.image.IImageViewListener;
import jmaster.jumploader.view.api.file.list.IFileListViewListener;
import jmaster.jumploader.view.api.file.tree.IFileTreeViewListener;
import jmaster.jumploader.view.api.upload.IUploadViewListener;

public class Controller implements IUploadViewListener, IFileTreeViewListener, IFileListViewListener, IImageViewListener
{
    private static final String E = "Controller.properties";
    protected A B;
    protected IMainView D;
    protected B C;
    private String[] A;
    static /* synthetic */ Class class$jmaster$jumploader$view$api$upload$IUploadView;
    static /* synthetic */ Class array$Ljava$lang$String;
    static /* synthetic */ Class array$Ljmaster$jumploader$model$api$upload$IUploadFile;
    static /* synthetic */ Class class$java$io$File;
    static /* synthetic */ Class class$jmaster$jumploader$view$api$image$IImageView;
    
    public Controller(final B c, final IMainView d) {
        this.B = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.C = c;
        this.D = d;
        try {
            final D g = jmaster.util.property.B.C().G("Controller.properties");
            jmaster.util.property.C.A().A(this, g, null);
            this.A = g.H("addFiles");
        }
        catch (MissingResourceException ex) {}
        d.getUploadView().addListener(this);
        if (d.getFileTreeView() != null) {
            d.getFileTreeView().addListener(this);
        }
        if (d.getFileListView() != null) {
            d.getFileListView().addListener(this);
        }
        if (d.getImageView() != null) {
            d.getImageView().addListener(this);
        }
        final IUploader d2 = this.C.D();
        if (this.A != null) {
            for (int i = 0; i < this.A.length; ++i) {
                try {
                    d2.addFile(this.A[i]);
                }
                catch (jmaster.jumploader.model.api.A.C c2) {
                    this.B.E(c2, c2);
                }
            }
        }
    }
    
    public void uploadViewAddFilesAction(final IUploadView uploadView, final String[] array) {
        if (jmaster.util.C.B.A()) {
            jmaster.util.C.B.B(this, "uploadViewAddFilesAction", new Class[] { (Controller.class$jmaster$jumploader$view$api$upload$IUploadView == null) ? (Controller.class$jmaster$jumploader$view$api$upload$IUploadView = class$("jmaster.jumploader.view.api.upload.IUploadView")) : Controller.class$jmaster$jumploader$view$api$upload$IUploadView, (Controller.array$Ljava$lang$String == null) ? (Controller.array$Ljava$lang$String = class$("[Ljava.lang.String;")) : Controller.array$Ljava$lang$String }, new Object[] { uploadView, array });
            return;
        }
        StringBuffer sb = null;
        for (int i = 0; i < array.length; ++i) {
            try {
                this.A().addFile(array[i]);
            }
            catch (jmaster.jumploader.model.api.A.C c) {
                this.B.E("Failed to add file: " + array[i], c);
                if (sb == null) {
                    sb = new StringBuffer("<html><body>");
                }
                sb.append(c.getMessage() + "<br>");
            }
        }
        if (sb != null) {
            sb.append("</body></html>");
            this.D.showWarning(sb.toString());
        }
    }
    
    public void uploadViewRemoveFilesAction(final IUploadView uploadView, final IUploadFile[] array) {
        if (jmaster.util.C.B.A()) {
            jmaster.util.C.B.B(this, "uploadViewRemoveFilesAction", new Class[] { (Controller.class$jmaster$jumploader$view$api$upload$IUploadView == null) ? (Controller.class$jmaster$jumploader$view$api$upload$IUploadView = class$("jmaster.jumploader.view.api.upload.IUploadView")) : Controller.class$jmaster$jumploader$view$api$upload$IUploadView, (Controller.array$Ljmaster$jumploader$model$api$upload$IUploadFile == null) ? (Controller.array$Ljmaster$jumploader$model$api$upload$IUploadFile = class$("[Ljmaster.jumploader.model.api.upload.IUploadFile;")) : Controller.array$Ljmaster$jumploader$model$api$upload$IUploadFile }, new Object[] { uploadView, array });
            return;
        }
        try {
            for (int i = 0; i < array.length; ++i) {
                if (array[i].isRemovable()) {
                    this.A().removeFile(array[i]);
                }
            }
        }
        catch (jmaster.jumploader.model.api.A.C c) {
            this.A(c);
        }
    }
    
    public void uploadViewStartAction(final IUploadView uploadView) {
        if (jmaster.util.C.B.A()) {
            jmaster.util.C.B.B(this, "uploadViewStartAction", new Class[] { (Controller.class$jmaster$jumploader$view$api$upload$IUploadView == null) ? (Controller.class$jmaster$jumploader$view$api$upload$IUploadView = class$("jmaster.jumploader.view.api.upload.IUploadView")) : Controller.class$jmaster$jumploader$view$api$upload$IUploadView }, new Object[] { uploadView });
            return;
        }
        try {
            this.A().startUpload();
        }
        catch (jmaster.jumploader.model.api.A.C c) {
            this.A(c);
        }
    }
    
    public void uploadViewStopAction(final IUploadView uploadView) {
        if (jmaster.util.C.B.A()) {
            jmaster.util.C.B.B(this, "uploadViewStopAction", new Class[] { (Controller.class$jmaster$jumploader$view$api$upload$IUploadView == null) ? (Controller.class$jmaster$jumploader$view$api$upload$IUploadView = class$("jmaster.jumploader.view.api.upload.IUploadView")) : Controller.class$jmaster$jumploader$view$api$upload$IUploadView }, new Object[] { uploadView });
            return;
        }
        try {
            this.A().stopUpload();
        }
        catch (jmaster.jumploader.model.api.A.C c) {
            this.A(c);
        }
    }
    
    public void uploadViewStopFilesAction(final IUploadView uploadView, final IUploadFile[] array) {
        if (jmaster.util.C.B.A()) {
            jmaster.util.C.B.B(this, "uploadViewStopFilesAction", new Class[] { (Controller.class$jmaster$jumploader$view$api$upload$IUploadView == null) ? (Controller.class$jmaster$jumploader$view$api$upload$IUploadView = class$("jmaster.jumploader.view.api.upload.IUploadView")) : Controller.class$jmaster$jumploader$view$api$upload$IUploadView, (Controller.array$Ljmaster$jumploader$model$api$upload$IUploadFile == null) ? (Controller.array$Ljmaster$jumploader$model$api$upload$IUploadFile = class$("[Ljmaster.jumploader.model.api.upload.IUploadFile;")) : Controller.array$Ljmaster$jumploader$model$api$upload$IUploadFile }, new Object[] { uploadView, array });
            return;
        }
        try {
            for (int i = 0; i < array.length; ++i) {
                if (array[i].isStoppable()) {
                    this.A().stopFileUpload(array[i]);
                }
            }
        }
        catch (jmaster.jumploader.model.api.A.C c) {
            this.A(c);
        }
    }
    
    public void uploadViewRetryFilesAction(final IUploadView uploadView, final IUploadFile[] array) {
        if (jmaster.util.C.B.A()) {
            jmaster.util.C.B.B(this, "uploadViewRetryFilesAction", new Class[] { (Controller.class$jmaster$jumploader$view$api$upload$IUploadView == null) ? (Controller.class$jmaster$jumploader$view$api$upload$IUploadView = class$("jmaster.jumploader.view.api.upload.IUploadView")) : Controller.class$jmaster$jumploader$view$api$upload$IUploadView, (Controller.array$Ljmaster$jumploader$model$api$upload$IUploadFile == null) ? (Controller.array$Ljmaster$jumploader$model$api$upload$IUploadFile = class$("[Ljmaster.jumploader.model.api.upload.IUploadFile;")) : Controller.array$Ljmaster$jumploader$model$api$upload$IUploadFile }, new Object[] { uploadView, array });
            return;
        }
        try {
            for (int i = 0; i < array.length; ++i) {
                if (array[i].isRetryable()) {
                    this.A().retryFileUpload(array[i]);
                }
            }
        }
        catch (jmaster.jumploader.model.api.A.C c) {
            this.A(c);
        }
    }
    
    public void fileTreeViewPathChanged(final IFileTreeView fileTreeView) {
        jmaster.util.C.B.B(this, "setPath", new Class[] { (Controller.class$java$io$File == null) ? (Controller.class$java$io$File = class$("java.io.File")) : Controller.class$java$io$File }, new Object[] { fileTreeView.getPath() });
    }
    
    public void fileListViewPathChanged(final IFileListView fileListView, final File file) {
        jmaster.util.C.B.B(this, "setPath", new Class[] { (Controller.class$java$io$File == null) ? (Controller.class$java$io$File = class$("java.io.File")) : Controller.class$java$io$File }, new Object[] { file });
    }
    
    public void fileListViewFileDblClicked(final IFileListView fileListView, final File file) {
        if (this.C.H().isFileListQueueFileOnDblClick()) {
            jmaster.util.C.B.B(this, "addFile", new Class[] { (Controller.class$java$io$File == null) ? (Controller.class$java$io$File = class$("java.io.File")) : Controller.class$java$io$File }, new Object[] { file });
        }
    }
    
    public void imageViewCloseAction(final IImageView imageView) {
        imageView.setImage(null, null, null);
        this.D.setCurrentView(this.D.getProgramView());
    }
    
    public void imageViewSaveAction(final IImageView imageView) {
        this.D.showGlassView(true);
        jmaster.util.C.B.B(this, "imageViewSaveActionDo", new Class[] { (Controller.class$jmaster$jumploader$view$api$image$IImageView == null) ? (Controller.class$jmaster$jumploader$view$api$image$IImageView = class$("jmaster.jumploader.view.api.image.IImageView")) : Controller.class$jmaster$jumploader$view$api$image$IImageView }, new Object[] { imageView });
    }
    
    public void imageViewSaveActionDo(final IImageView imageView) {
        try {
            this.A(imageView);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            this.D.showGlassView(false);
            this.imageViewCloseAction(imageView);
        }
    }
    
    protected void A(final Exception ex) {
        this.B.E("Controller exception", ex);
        this.D.showError(ex.getMessage());
    }
    
    private IUploader A() {
        return this.C.D();
    }
    
    public void setPath(final File path) {
        if (path != null) {
            final IFileBrowser g = this.C.G();
            if (!path.equals(g.getPath())) {
                g.setPath(path);
            }
        }
    }
    
    public void addFile(final File file) {
        if (file != null) {
            try {
                this.C.D().addFile(file.getAbsolutePath());
            }
            catch (jmaster.jumploader.model.api.A.C c) {
                this.B.E("Failed to add file: " + file, c);
            }
        }
    }
    
    private void A(final IImageView imageView) {
        try {
            final jmaster.jumploader.model.api.B.A l = this.C.L();
            final File tempFile = File.createTempFile("jumploader_", ".jpg");
            if (this.B.B()) {
                this.B.D("Saving image to temp file: " + tempFile.getAbsolutePath());
            }
            l.A(imageView.getImage(), tempFile, this.C.J().getJpegQuality());
            this.C.D().updateFile(imageView.getUploadFile(), tempFile, true);
        }
        catch (Exception ex) {
            this.B.E(ex, ex);
            this.D.showError(ex.getMessage());
        }
    }
    
    public void destroy() {
        this.D = null;
        this.C = null;
    }
    
    public void start() {
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
