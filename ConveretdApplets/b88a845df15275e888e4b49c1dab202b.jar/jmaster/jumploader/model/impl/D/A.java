// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.D;

import java.io.File;
import jmaster.jumploader.model.api.common.IAttribute;
import java.awt.Image;
import jmaster.jumploader.model.api.upload.IUploadFile;
import jmaster.jumploader.model.api.file.IFileBrowser;
import jmaster.jumploader.model.api.upload.IUploader;
import jmaster.jumploader.model.api.C.C;
import jmaster.jumploader.model.api.file.IFile;
import jmaster.jumploader.model.api.config.ViewConfig;
import jmaster.jumploader.model.api.file.IFileBrowserListener;
import jmaster.jumploader.model.api.upload.B;

public class A implements jmaster.jumploader.model.api.C.A, Runnable, B, IFileBrowserListener
{
    public static final String º = "thumbnail";
    private static final String \u00c1 = "thumbnailManager";
    protected jmaster.util.log.A \u00c0;
    protected jmaster.util.C.A \u00c5;
    private jmaster.jumploader.model.api.B \u00c2;
    private jmaster.jumploader.model.api.B.A \u00c7;
    private ViewConfig \u00c3;
    private Thread \u00c4;
    private boolean \u00c6;
    private boolean \u00c8;
    static /* synthetic */ Class class$jmaster$jumploader$model$api$C$B;
    
    public A(final jmaster.jumploader.model.api.B \u00e2) {
        this.\u00c0 = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.\u00c5 = new jmaster.util.C.A((A.class$jmaster$jumploader$model$api$C$B == null) ? (A.class$jmaster$jumploader$model$api$C$B = class$("jmaster.jumploader.model.api.C.B")) : A.class$jmaster$jumploader$model$api$C$B);
        this.\u00c4 = null;
        this.\u00c6 = false;
        this.\u00c8 = false;
        this.\u00c2 = \u00e2;
        this.\u00c7 = this.\u00c2.L();
        this.\u00c3 = this.\u00c2.H();
        this.A(this.\u00c2.D());
        this.A(this.\u00c2.G());
        this.\u00c2.D().addListener(this);
        this.\u00c2.G().addListener(this);
        final jmaster.util.B.B b = new jmaster.util.B.B();
        b.A(this);
        b.A("thumbnailManager");
        b.A(true);
        (this.\u00c4 = \u00e2.C().A(b)).start();
    }
    
    public void A(final jmaster.jumploader.model.api.C.B b) {
        this.\u00c5.C(b);
    }
    
    public void B(final jmaster.jumploader.model.api.C.B b) {
        this.\u00c5.A(b);
    }
    
    private void E() {
        for (int i = 0; i < this.\u00c5.C(); ++i) {
            try {
                ((jmaster.jumploader.model.api.C.B)this.\u00c5.A(i)).thumbnailManagerStatusChanged(this);
            }
            catch (Exception ex) {
                this.\u00c0.E(ex, ex);
            }
        }
    }
    
    private void B(final IFile file, final C c) {
        for (int i = 0; i < this.\u00c5.C(); ++i) {
            try {
                ((jmaster.jumploader.model.api.C.B)this.\u00c5.A(i)).thumbnailManagerThumbnailChanged(this, file, c);
            }
            catch (Exception ex) {
                this.\u00c0.E(ex, ex);
            }
        }
    }
    
    public void run() {
        if (this.\u00c0.B()) {
            this.\u00c0.D("" + Thread.currentThread().getName() + " started");
        }
        try {
            final IUploader d = this.\u00c2.D();
            final IFileBrowser g = this.\u00c2.G();
            while (!this.\u00c8 && this.\u00c4 != null && !this.\u00c4.isInterrupted()) {
                for (int i = 0; i < d.getFileCount(); ++i) {
                    final IUploadFile file = d.getFile(i);
                    final jmaster.jumploader.model.impl.D.B b = (jmaster.jumploader.model.impl.D.B)this.B(file);
                    if (b != null && b.D()) {
                        try {
                            this.A(true);
                            final Image d2 = this.D(file);
                            b.A(d2);
                            b.A(1);
                            if (d2 != null) {
                                ((jmaster.jumploader.model.impl.upload.B)file).F(true);
                            }
                        }
                        catch (Exception ex) {
                            this.\u00c0.E("Thumbnail generation failed for " + file, ex);
                            b.A(2);
                        }
                        this.B(file, b);
                    }
                }
                for (int j = 0; j < g.getFileCount(); ++j) {
                    final IFile file2 = g.getFile(j);
                    final jmaster.jumploader.model.impl.D.B b2 = (jmaster.jumploader.model.impl.D.B)this.B(file2);
                    if (b2 != null && b2.D()) {
                        try {
                            this.A(true);
                            b2.A(this.D(file2));
                            b2.A(1);
                        }
                        catch (Exception ex2) {
                            this.\u00c0.E("Thumbnail generation failed for " + file2, ex2);
                            b2.A(2);
                        }
                        this.B(file2, b2);
                    }
                }
                this.A(false);
                synchronized (this.\u00c4) {
                    this.\u00c4.wait(1000L);
                }
            }
        }
        catch (InterruptedException ex3) {}
        finally {
            if (this.\u00c0.B()) {
                this.\u00c0.D("" + Thread.currentThread().getName() + " terminated");
            }
        }
    }
    
    private void A(final boolean \u00e6) {
        if (this.\u00c6 ^ \u00e6) {
            this.\u00c6 = \u00e6;
            this.E();
        }
    }
    
    public void uploaderFileAdded(final IUploader uploader, final IUploadFile uploadFile) {
        if (this.\u00c3.isUseThumbs() && this.\u00c3.isUploadViewUseThumbs()) {
            final IFileBrowser g = this.\u00c2.G();
            int i = 0;
            while (i < g.getFileCount()) {
                final IFile file = g.getFile(i);
                if (file.getFile().equals(uploadFile.getFile())) {
                    final C b = this.B(file);
                    if (b != null) {
                        uploadFile.getAttributeSet().setAttribute("thumbnail", b).setSendToServer(false);
                        final jmaster.jumploader.model.impl.upload.B b2 = (jmaster.jumploader.model.impl.upload.B)uploadFile;
                        b2.F(b.B() && this.\u00c2.L().B(b2.getFile()));
                        break;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
            if (this.B(uploadFile) == null) {
                this.E(uploadFile);
            }
            synchronized (this.\u00c4) {
                this.\u00c4.notify();
            }
        }
    }
    
    public void uploaderFileAdditionEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileRemovalEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileRemoved(final IUploader uploader, final IUploadFile uploadFile) {
    }
    
    public void uploaderFileMoved(final IUploader uploader, final IUploadFile uploadFile, final int n) {
    }
    
    public void uploaderFileUpdated(final IUploader uploader, final IUploadFile uploadFile) {
        this.C(uploadFile);
        this.A(this.\u00c2.D());
    }
    
    public void uploaderFileStatusChanged(final IUploader uploader, final IUploadFile uploadFile) {
    }
    
    public void uploaderFilesReset(final IUploader uploader) {
    }
    
    public void uploaderStatusChanged(final IUploader uploader) {
    }
    
    public void uploaderUploadEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileAddFailed(final IUploader uploader, final jmaster.jumploader.model.api.A.C c) {
    }
    
    public void fileBrowserLocationChanged(final IFileBrowser fileBrowser) {
    }
    
    public void fileBrowserLocationFilesListed(final IFileBrowser fileBrowser) {
        if (this.\u00c3.isUseThumbs() && this.\u00c3.isFileListViewUseThumbs()) {
            this.A(fileBrowser);
        }
    }
    
    public C B(final IFile file) {
        final IAttribute attributeByName = file.getAttributeSet().getAttributeByName("thumbnail");
        return (attributeByName == null) ? null : ((C)attributeByName.getValue());
    }
    
    protected void A(final IUploader uploader) {
        for (int i = 0; i < uploader.getFileCount(); ++i) {
            final IUploadFile file = uploader.getFile(i);
            if (this.B(file) == null) {
                this.E(file);
            }
        }
    }
    
    protected void A(final IFileBrowser fileBrowser) {
        for (int i = 0; i < fileBrowser.getFileCount(); ++i) {
            final IFile file = fileBrowser.getFile(i);
            if (this.B(file) == null) {
                this.E(file);
            }
        }
    }
    
    protected C E(final IFile file) {
        jmaster.jumploader.model.impl.D.B b = null;
        if (file.isFile()) {
            b = new jmaster.jumploader.model.impl.D.B();
            b.A(0);
            file.getAttributeSet().setAttribute("thumbnail", b).setSendToServer(false);
            if (this.\u00c4 != null) {
                synchronized (this.\u00c4) {
                    this.\u00c4.notify();
                }
            }
        }
        return b;
    }
    
    protected Image D(final IFile file) {
        Object a = null;
        if (this.\u00c3.isUseThumbs()) {
            if (this.\u00c0.B()) {
                this.\u00c0.D("About to generate thumbnail for " + file);
            }
            final File file2 = new File(file.getPath());
            if (this.\u00c7.B(file2)) {
                try {
                    a = this.\u00c7.A(file2, this.\u00c3.getThumbWidth(), this.\u00c3.getThumbHeight(), "bilinear");
                }
                catch (Throwable t) {
                    this.\u00c0.E("loadThumbnailImage(" + file + " failed", t);
                }
            }
            if (this.\u00c0.B()) {
                this.\u00c0.D("Thumbnail result image for " + file + " is " + a);
            }
        }
        return (Image)a;
    }
    
    public void C(final IFile file) {
        final IAttribute attributeByName = file.getAttributeSet().getAttributeByName("thumbnail");
        if (attributeByName != null) {
            file.getAttributeSet().removeAttribute(attributeByName);
        }
    }
    
    public C A(final C c) {
        final jmaster.jumploader.model.impl.D.B b = new jmaster.jumploader.model.impl.D.B();
        b.A(c.E());
        b.A(c.A());
        return b;
    }
    
    public void A(final IFile file, final C value) {
        IAttribute attribute = file.getAttributeSet().getAttributeByName("thumbnail");
        if (attribute != null) {
            attribute = file.getAttributeSet().createAttribute("thumbnail", null);
            attribute.setSendToServer(false);
        }
        attribute.setValue(value);
    }
    
    public void A(final IFile file) {
        this.C(file);
        this.E(file);
    }
    
    public double D() {
        double n = 1.0;
        final IUploader d = this.\u00c2.D();
        final IFileBrowser g = this.\u00c2.G();
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i < d.getFileCount(); ++i) {
            final jmaster.jumploader.model.impl.D.B b = (jmaster.jumploader.model.impl.D.B)this.B(d.getFile(i));
            if (b != null && b.D()) {
                ++n3;
            }
            ++n2;
        }
        for (int j = 0; j < g.getFileCount(); ++j) {
            final jmaster.jumploader.model.impl.D.B b2 = (jmaster.jumploader.model.impl.D.B)this.B(g.getFile(j));
            if (b2 != null && b2.D()) {
                ++n3;
            }
            ++n2;
        }
        if (n2 != 0) {
            n = (n2 - n3) / n2;
        }
        return n;
    }
    
    public boolean B() {
        return this.\u00c6;
    }
    
    public void C() {
        this.\u00c8 = true;
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
