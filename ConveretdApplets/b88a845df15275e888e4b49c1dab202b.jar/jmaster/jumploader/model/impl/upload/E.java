// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.upload;

import java.awt.Image;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import mediautil.image.jpeg.LLJTran;
import jmaster.jumploader.model.api.common.IAttribute;
import java.io.IOException;
import java.net.SocketException;
import jmaster.jumploader.model.api.common.ITransferProgress;
import java.util.Iterator;
import java.awt.Dimension;
import jmaster.jumploader.model.api.config.UploaderConfig;
import z.A.A.A.G;
import java.util.Date;
import java.io.File;
import java.util.regex.Pattern;
import jmaster.jumploader.model.api.file.IFile;
import jmaster.jumploader.model.api.upload.D;
import jmaster.jumploader.model.api.common.IListSelection;
import jmaster.jumploader.model.api.common.IAttributeSet;
import java.util.ArrayList;
import jmaster.jumploader.model.api.upload.IUploadFile;
import java.text.SimpleDateFormat;
import jmaster.jumploader.model.impl.C.B;
import jmaster.jumploader.model.impl.C.C;
import java.util.List;
import jmaster.jumploader.app.JumpLoaderMain;
import javax.swing.filechooser.FileSystemView;
import jmaster.jumploader.model.api.upload.A;
import jmaster.jumploader.model.api.upload.IUploader;

public class E implements IUploader, A, Runnable
{
    private static final String A = "uploaderUpdater";
    private static final long Y = 200L;
    static final int N = 50;
    public static final String T = "lastModified";
    public static final String I = "imageMetadataXml";
    public static final String M = "relativePath";
    public static final String _ = "retryCount";
    protected jmaster.util.log.A L;
    protected jmaster.util.C.A P;
    protected jmaster.util.C.A W;
    private FileSystemView X;
    private JumpLoaderMain K;
    private boolean B;
    private boolean F;
    private List J;
    private int V;
    private List Z;
    private jmaster.jumploader.model.impl.C.A U;
    private C C;
    private jmaster.jumploader.model.impl.C.E b;
    private B R;
    private boolean G;
    protected SimpleDateFormat H;
    protected Thread S;
    private boolean O;
    private boolean E;
    private boolean D;
    private Object Q;
    private IUploadFile a;
    static /* synthetic */ Class class$jmaster$jumploader$model$api$upload$B;
    static /* synthetic */ Class class$jmaster$jumploader$model$api$upload$C;
    
    public E(final JumpLoaderMain k) {
        this.L = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.P = new jmaster.util.C.A((jmaster.jumploader.model.impl.upload.E.class$jmaster$jumploader$model$api$upload$B == null) ? (jmaster.jumploader.model.impl.upload.E.class$jmaster$jumploader$model$api$upload$B = class$("jmaster.jumploader.model.api.upload.B")) : jmaster.jumploader.model.impl.upload.E.class$jmaster$jumploader$model$api$upload$B);
        this.W = new jmaster.util.C.A((jmaster.jumploader.model.impl.upload.E.class$jmaster$jumploader$model$api$upload$C == null) ? (jmaster.jumploader.model.impl.upload.E.class$jmaster$jumploader$model$api$upload$C = class$("jmaster.jumploader.model.api.upload.C")) : jmaster.jumploader.model.impl.upload.E.class$jmaster$jumploader$model$api$upload$C);
        this.X = FileSystemView.getFileSystemView();
        this.B = true;
        this.F = true;
        this.J = new ArrayList();
        this.V = 0;
        this.Z = new ArrayList();
        this.C = new C();
        this.b = new jmaster.jumploader.model.impl.C.E();
        this.R = new B();
        this.G = true;
        this.H = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        this.O = false;
        this.E = false;
        this.D = false;
        this.Q = new Object();
        this.K = k;
    }
    
    public String toString() {
        return "uploader, status:" + this.V;
    }
    
    public IAttributeSet getAttributeSet() {
        return this.C;
    }
    
    public IListSelection getSelection() {
        return this.b;
    }
    
    public boolean isFileAdditionEnabled() {
        return this.B;
    }
    
    public void setFileAdditionEnabled(final boolean b) {
        if (this.B ^ b) {
            this.B = b;
            this.L();
        }
    }
    
    public boolean isFileRemovalEnabled() {
        return this.F;
    }
    
    public void setFileRemovalEnabled(final boolean f) {
        if (this.F ^ f) {
            this.F = f;
            this.F();
        }
    }
    
    public boolean isUploadEnabled() {
        return this.G;
    }
    
    public void setUploadEnabled(final boolean g) {
        if (this.G ^ g) {
            this.G = g;
            this.C();
        }
    }
    
    public jmaster.jumploader.model.api.B K() {
        return this.K.getModel();
    }
    
    public void addListener(final jmaster.jumploader.model.api.upload.B b) {
        this.P.C(b);
    }
    
    public void removeListener(final jmaster.jumploader.model.api.upload.B b) {
        this.P.A(b);
    }
    
    public void addAddListener(final jmaster.jumploader.model.api.upload.C c) {
        this.W.C(c);
    }
    
    public void removeAddListener(final jmaster.jumploader.model.api.upload.C c) {
        this.W.A(c);
    }
    
    private void L() {
        for (int i = 0; i < this.P.C(); ++i) {
            try {
                ((jmaster.jumploader.model.api.upload.B)this.P.A(i)).uploaderFileAdditionEnabledChanged(this);
            }
            catch (Exception ex) {
                this.L.E(ex, ex);
            }
        }
    }
    
    private void F() {
        for (int i = 0; i < this.P.C(); ++i) {
            try {
                ((jmaster.jumploader.model.api.upload.B)this.P.A(i)).uploaderFileRemovalEnabledChanged(this);
            }
            catch (Exception ex) {
                this.L.E(ex, ex);
            }
        }
    }
    
    private void B(final IUploadFile uploadFile) {
        for (int i = 0; i < this.P.C(); ++i) {
            try {
                ((jmaster.jumploader.model.api.upload.B)this.P.A(i)).uploaderFileAdded(this, uploadFile);
            }
            catch (Exception ex) {
                this.L.E(ex, ex);
            }
        }
    }
    
    private void D(final IUploadFile uploadFile) {
        for (int i = 0; i < this.P.C(); ++i) {
            try {
                ((jmaster.jumploader.model.api.upload.B)this.P.A(i)).uploaderFileRemoved(this, uploadFile);
            }
            catch (Exception ex) {
                this.L.E(ex, ex);
            }
        }
    }
    
    private void A(final IUploadFile uploadFile, final int n) {
        for (int i = 0; i < this.P.C(); ++i) {
            try {
                ((jmaster.jumploader.model.api.upload.B)this.P.A(i)).uploaderFileMoved(this, uploadFile, n);
            }
            catch (Exception ex) {
                this.L.E(ex, ex);
            }
        }
    }
    
    private void A(final IUploadFile uploadFile) {
        for (int i = 0; i < this.P.C(); ++i) {
            try {
                ((jmaster.jumploader.model.api.upload.B)this.P.A(i)).uploaderFileStatusChanged(this, uploadFile);
            }
            catch (Exception ex) {
                this.L.E(ex, ex);
            }
        }
    }
    
    private void C(final IUploadFile uploadFile) {
        for (int i = 0; i < this.P.C(); ++i) {
            try {
                ((jmaster.jumploader.model.api.upload.B)this.P.A(i)).uploaderFileUpdated(this, uploadFile);
            }
            catch (Exception ex) {
                this.L.E(ex, ex);
            }
        }
    }
    
    private void I() {
        for (int i = 0; i < this.P.C(); ++i) {
            try {
                ((jmaster.jumploader.model.api.upload.B)this.P.A(i)).uploaderFilesReset(this);
            }
            catch (Exception ex) {
                this.L.E(ex, ex);
            }
        }
    }
    
    private void D() {
        for (int i = 0; i < this.P.C(); ++i) {
            try {
                ((jmaster.jumploader.model.api.upload.B)this.P.A(i)).uploaderStatusChanged(this);
            }
            catch (Exception ex) {
                this.L.E(ex, ex);
            }
        }
    }
    
    private void C() {
        for (int i = 0; i < this.P.C(); ++i) {
            try {
                ((jmaster.jumploader.model.api.upload.B)this.P.A(i)).uploaderUploadEnabledChanged(this);
            }
            catch (Exception ex) {
                this.L.E(ex, ex);
            }
        }
    }
    
    private void A(final jmaster.jumploader.model.api.A.C c) {
        for (int i = 0; i < this.P.C(); ++i) {
            try {
                ((jmaster.jumploader.model.api.upload.B)this.P.A(i)).uploaderFileAddFailed(this, c);
            }
            catch (Exception ex) {
                this.L.E(ex, ex);
            }
        }
    }
    
    private void H() {
        for (int i = 0; i < this.W.C(); ++i) {
            try {
                ((jmaster.jumploader.model.api.upload.C)this.W.A(i)).uploaderFileAddStarted(this);
            }
            catch (Exception ex) {
                this.L.E(ex, ex);
            }
        }
    }
    
    private void G() {
        for (int i = 0; i < this.W.C(); ++i) {
            try {
                ((jmaster.jumploader.model.api.upload.C)this.W.A(i)).uploaderFileAddFinished(this);
            }
            catch (Exception ex) {
                this.L.E(ex, ex);
            }
        }
    }
    
    public void A(final D d, final Exception ex) {
        d.B(this);
        this.Z.remove(d);
        if (this.getUploadThreadCount() == 0) {
            this.B(0);
        }
    }
    
    public void A(final D d) {
    }
    
    public void A(final D d, final IUploadFile uploadFile) {
    }
    
    public void B(final D d, final IUploadFile uploadFile) {
    }
    
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    this.A();
                }
                catch (NullPointerException ex) {}
                Thread.sleep(200L);
            }
        }
        catch (InterruptedException ex2) {}
    }
    
    public D getUploadThread(final int n) {
        return this.Z.get(n);
    }
    
    public int getUploadThreadCount() {
        return this.Z.size();
    }
    
    public IUploadFile getFile(final int n) {
        return this.J.get(n);
    }
    
    public IUploadFile getFileByPath(final String s) {
        IUploadFile uploadFile = null;
        for (int n = this.getFileCount() - 1; uploadFile == null && n >= 0; --n) {
            final IUploadFile file = this.getFile(n);
            if (jmaster.util.B.A.A(file.getPath(), s) == 0) {
                uploadFile = file;
            }
        }
        return uploadFile;
    }
    
    public int getFileCount() {
        return this.J.size();
    }
    
    public IUploadFile[] getAllFiles() {
        final IUploadFile[] array = new IUploadFile[this.getFileCount()];
        for (int i = this.getFileCount() - 1; i >= 0; --i) {
            array[i] = this.getFile(i);
        }
        return array;
    }
    
    public int getFileCountByStatus(final int n) {
        int n2 = 0;
        for (int i = this.getFileCount() - 1; i >= 0; --i) {
            if (this.getFile(i).getStatus() == n) {
                ++n2;
            }
        }
        return n2;
    }
    
    public IUploadFile[] getFilesByStatus(final int n) {
        IUploadFile[] array = null;
        final ArrayList<IUploadFile> list = new ArrayList<IUploadFile>();
        for (int i = this.getFileCount() - 1; i >= 0; --i) {
            final IUploadFile file = this.getFile(i);
            if (file.getStatus() == n) {
                list.add(file);
            }
        }
        if (list.size() > 0) {
            array = new IUploadFile[list.size()];
            for (int j = 0; j < list.size(); ++j) {
                array[j] = (IUploadFile)list.get(j);
            }
        }
        return array;
    }
    
    public long getFilesLength() {
        long n = 0L;
        for (int i = 0; i < this.getFileCount(); ++i) {
            n += this.getFile(i).getLength();
        }
        return n;
    }
    
    public IUploadFile addFile(final String s) throws jmaster.jumploader.model.api.A.C {
        return this.addFile(s, 0);
    }
    
    public IUploadFile addFile(final String s, final int n) throws jmaster.jumploader.model.api.A.C {
        return this.addFile(s, n, null);
    }
    
    public IUploadFile addFile(final String s, final int n, final String s2) throws jmaster.jumploader.model.api.A.C {
        IUploadFile a = null;
        synchronized (this.Q) {
            final int priority = Thread.currentThread().getPriority();
            try {
                Thread.currentThread().setPriority(1);
                this.D = false;
                this.E = true;
                this.H();
                a = this.A(null, s, false, n, s2);
            }
            finally {
                this.E = false;
                this.a = null;
                this.G();
                Thread.currentThread().setPriority(priority);
            }
        }
        return a;
    }
    
    protected IUploadFile A(final jmaster.jumploader.model.impl.upload.B b, final String s, final boolean b2, final int n, final String s2) throws jmaster.jumploader.model.api.A.C {
        IUploadFile uploadFile = null;
        try {
            if (this.L.B()) {
                this.L.D("addFile: " + s);
            }
            if (!this.isFileAdditionEnabled()) {
                throw new jmaster.jumploader.model.api.A.C("File addition not enabled");
            }
            final File fileObject = this.X.createFileObject(s);
            final jmaster.jumploader.model.impl.upload.B a = new jmaster.jumploader.model.impl.upload.B(this, fileObject);
            this.a = a;
            this.K().A(a);
            final UploaderConfig b3 = this.B();
            if (!a.B()) {
                throw new jmaster.jumploader.model.api.A.C(this.R.A(this, this.B(), a, "uploader.errorFileNotFound"));
            }
            if (a.isFile() && b3.getMaxFileLength() != -1L && b3.getMaxFileLength() < a.getLength()) {
                throw new jmaster.jumploader.model.api.A.C(this.R.A(this, this.B(), a, "uploader.errorFileTooLengthy"));
            }
            if (a.isFile() && b3.getMinFileLength() != -1L && b3.getMinFileLength() > a.getLength()) {
                throw new jmaster.jumploader.model.api.A.C(this.R.A(this, this.B(), a, "uploader.errorFileTooSmall"));
            }
            if (b3.getMaxFiles() != -1 && b3.getMaxFiles() <= this.getFileCount()) {
                throw new jmaster.jumploader.model.api.A.C(this.R.A(this, this.B(), a, "uploader.errorTooManyFiles"));
            }
            if (a.isFile() && b3.getFileNamePattern() != null && !Pattern.matches(b3.getFileNamePattern(), a.getName())) {
                throw new jmaster.jumploader.model.api.A.C(this.R.A(this, this.B(), a, "uploader.errorFileNamePatternMismatch"));
            }
            if (b3.getMaxLength() != -1L && b3.getMaxLength() < this.getFilesLength() + a.getLength()) {
                throw new jmaster.jumploader.model.api.A.C(this.R.A(this, this.B(), a, "uploader.errorFilesLengthQuota"));
            }
            if (!b3.isDirectoriesEnabled() && !a.isFile()) {
                throw new jmaster.jumploader.model.api.A.C(this.R.A(this, this.B(), a, "uploader.errorDirectoriesNotAllowed"));
            }
            if (!b3.isDuplicateFileEnabled() && this.getFileByPath(a.getPath()) != null) {
                throw new jmaster.jumploader.model.api.A.C(this.R.A(this, this.B(), a, "uploader.errorDuplicateFileNotAllowed"));
            }
            if (a.isFile() && !jmaster.util.B.A.C(b3.getMimeTypePattern()) && !Pattern.matches(b3.getMimeTypePattern(), a.getMimeType())) {
                throw new jmaster.jumploader.model.api.A.C(this.R.A(this, this.B(), a, "uploader.errorMimeTypePatternMismatch"));
            }
            if (this.K().B().isPreserveRelativePath()) {
                String s3 = a.getName();
                if (b != null) {
                    s3 = this.A(b) + "/" + s3;
                }
                this.A(a, s3);
            }
            if (a.isFile()) {
                try {
                    final boolean b4 = this.K().L().B(a.getFile());
                    if (b3.isAddImagesOnly() && !b4) {
                        throw new jmaster.jumploader.model.api.A.C(this.R.A(this, this.B(), a, "uploader.errorNotImage"));
                    }
                    if (b4 && (!jmaster.util.B.A.C(b3.getMinimumImageDimension()) || !jmaster.util.B.A.C(b3.getMaximumImageDimension()) || b3.getImageMaxMpx() > 0.0)) {
                        final Dimension a2 = this.K().L().A(a.getFile());
                        if (b3.isAddImagesOnly() && a2 == null) {
                            throw new jmaster.jumploader.model.api.A.C(this.R.A(this, this.B(), a, "uploader.errorNotImage"));
                        }
                        if (!jmaster.util.B.A.C(b3.getMinimumImageDimension())) {
                            final Dimension a3 = jmaster.util.B.A.A(b3.getMinimumImageDimension());
                            if (a2.width < a3.width || a2.height < a3.height) {
                                throw new jmaster.jumploader.model.api.A.C(this.R.A(this, this.B(), a, "uploader.errorImageTooSmall"));
                            }
                        }
                        if (!jmaster.util.B.A.C(b3.getMaximumImageDimension())) {
                            final Dimension a4 = jmaster.util.B.A.A(b3.getMaximumImageDimension());
                            if (a2.width > a4.width || a2.height > a4.height) {
                                throw new jmaster.jumploader.model.api.A.C(this.R.A(this, this.B(), a, "uploader.errorImageTooBig"));
                            }
                        }
                        if (b3.getImageMaxMpx() > 0.0 && a2.width * a2.height / 1000000.0 > b3.getImageMaxMpx()) {
                            throw new jmaster.jumploader.model.api.A.C(this.R.A(this, this.B(), a, "uploader.errorImageTooBig"));
                        }
                    }
                }
                catch (Exception ex) {
                    if (ex instanceof jmaster.jumploader.model.api.A.C) {
                        throw (jmaster.jumploader.model.api.A.C)ex;
                    }
                    throw new jmaster.jumploader.model.api.A.C(ex);
                }
            }
            if (a.isDirectory()) {
                final File file = new File(a.getPath());
                if (b3.isZipDirectoriesOnAdd()) {
                    this.J.add(a);
                    if (this.K().B().isSendFileLastModified()) {
                        a.getAttributeSet().setAttribute("lastModified", this.H.format(new Date(a.getFile().lastModified()))).setSendToServer(true);
                    }
                    uploadFile = a;
                    this.J();
                }
                else {
                    final File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        for (int n2 = 0; !this.D && n2 < listFiles.length; ++n2) {
                            try {
                                this.A(a, listFiles[n2].getAbsolutePath(), true, n, s2);
                            }
                            catch (jmaster.jumploader.model.api.A.C c) {}
                        }
                    }
                }
            }
            else {
                a.B(n);
                if (!jmaster.util.B.A.C(s2)) {
                    a.A(new Exception(s2));
                }
                this.J.add(a);
                if (this.K().B().isSendFileLastModified()) {
                    a.getAttributeSet().setAttribute("lastModified", this.H.format(new Date(a.getFile().lastModified()))).setSendToServer(true);
                }
                if (this.K().B().isSendImageMetadata()) {
                    try {
                        final z.A.A.A.E a5 = z.A.A.B.A.D.A(fileObject);
                        final StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                        sb.append("<metadata>\n");
                        final Iterator a6 = a5.A();
                        while (a6.hasNext()) {
                            final z.A.A.A.A a7 = a6.next();
                            sb.append("  <directory name=\"" + jmaster.util.B.A.D(a7.F()) + "\">\n");
                            final Iterator e = a7.E();
                            while (e.hasNext()) {
                                final G g = e.next();
                                sb.append("    <tag type=\"" + jmaster.util.B.A.D(g.E()) + "\"" + " name=\"" + jmaster.util.B.A.D(g.A()) + "\"" + " desc=\"" + jmaster.util.B.A.D(g.D()) + "\"" + "/>\n");
                            }
                            sb.append("  </directory>\n");
                        }
                        sb.append("</metadata>\n");
                        a.getAttributeSet().setAttribute("imageMetadataXml", sb.toString()).setSendToServer(true);
                        if (this.L.B()) {
                            this.L.D("Image metadata for file " + fileObject + " is:\r\n" + sb.toString());
                        }
                    }
                    catch (z.A.A.B.A.B b5) {}
                    catch (Exception ex2) {
                        this.L.E("Failed to load metadata from file: " + fileObject, ex2);
                    }
                }
            }
            if (this.J.contains(a)) {
                this.B((IUploadFile)a);
                uploadFile = a;
                this.J();
            }
        }
        catch (Exception ex3) {
            if (!b2) {
                this.A((ex3 instanceof jmaster.jumploader.model.api.A.C) ? ((jmaster.jumploader.model.api.A.C)ex3) : new jmaster.jumploader.model.api.A.C(ex3));
                this.A(ex3);
            }
        }
        return uploadFile;
    }
    
    public void removeFile(final IUploadFile uploadFile) throws jmaster.jumploader.model.api.A.C {
        try {
            if (this.L.B()) {
                this.L.D("removeFile: " + uploadFile);
            }
            if (!this.isFileRemovalEnabled()) {
                throw new jmaster.jumploader.model.api.A.C("File removal not enabled");
            }
            final jmaster.jumploader.model.impl.upload.B e = this.E(uploadFile);
            if (!uploadFile.isRemovable()) {
                throw new jmaster.jumploader.model.api.A.C("Cannot remove file (" + uploadFile.getPath() + ")");
            }
            final int index = e.getIndex();
            this.J.remove(e);
            if (this.b.isIndexSelected(index)) {
                this.b.A(index);
            }
            this.D((IUploadFile)e);
            this.J();
        }
        catch (Exception ex) {
            this.A(ex);
        }
    }
    
    public int indexOfFile(final IUploadFile uploadFile) {
        return this.J.indexOf(uploadFile);
    }
    
    public int getStatus() {
        return this.V;
    }
    
    public boolean isReady() {
        return this.V == 0;
    }
    
    public boolean isUploading() {
        return this.V == 1;
    }
    
    public boolean canStartUpload() {
        boolean a = this.G && this.isReady() && this.getFileCountByStatus(0) > 0;
        final UploaderConfig b = this.B();
        if (a && b.getMinFiles() > 0) {
            a = (b.getMinFiles() <= this.getFileCount());
        }
        if (a && b.isUseMetadata() && b.isMetadataCheckRequiredFields()) {
            for (int n = 0; a && n < this.getFileCount(); ++n) {
                final IUploadFile file = this.getFile(n);
                if (file.isReady()) {
                    a = this.K.getModel().K().A(file);
                }
            }
        }
        return a;
    }
    
    public boolean canStopUpload() {
        return this.isUploading();
    }
    
    public void startUpload() throws jmaster.jumploader.model.api.A.C {
        if (!this.canStartUpload()) {
            throw new jmaster.jumploader.model.api.A.C("Can't start upload");
        }
        this.A(0);
        this.B(1);
        synchronized (this.Z) {
            for (int i = 0; i < this.B().getUploadThreadCount(); ++i) {
                final jmaster.jumploader.model.impl.upload.A a = new jmaster.jumploader.model.impl.upload.A(this.K(), this, this.B(), "uploadThread_" + i);
                a.A(this);
                a.B();
                this.Z.add(a);
            }
        }
    }
    
    public void stopUpload() throws jmaster.jumploader.model.api.A.C {
        this.A(1);
        synchronized (this.Z) {
            for (int i = 0; i < this.getUploadThreadCount(); ++i) {
                ((jmaster.jumploader.model.impl.upload.A)this.getUploadThread(i)).C();
            }
        }
        while (!this.isReady()) {
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void stopFileUpload(final IUploadFile uploadFile) throws jmaster.jumploader.model.api.A.C {
        final jmaster.jumploader.model.impl.upload.B e = this.E(uploadFile);
        this.B(e, 1);
        try {
            jmaster.jumploader.model.impl.upload.A a = null;
            for (int n = 0; a == null && n < this.getUploadThreadCount(); ++n) {
                final jmaster.jumploader.model.impl.upload.A a2 = (jmaster.jumploader.model.impl.upload.A)this.getUploadThread(n);
                if (e.equals(a2.A())) {
                    a = a2;
                }
            }
            a.H();
        }
        catch (Exception ex) {
            this.A(ex);
        }
    }
    
    public void retryFileUpload(final IUploadFile uploadFile) throws jmaster.jumploader.model.api.A.C {
        final jmaster.jumploader.model.impl.upload.B e = this.E(uploadFile);
        e.A((Exception)null);
        this.updateFileStatus(e, 0);
        if (this.canStartUpload()) {
            this.startUpload();
        }
    }
    
    public ITransferProgress getTransferProgress() {
        return this.U;
    }
    
    public void destroy() {
        this.O = true;
        if (this.isUploading()) {
            try {
                this.stopUpload();
            }
            catch (jmaster.jumploader.model.api.A.C c) {
                this.L.E(c, c);
            }
        }
        this.J.clear();
        this.I();
    }
    
    private void A(final Exception ex) throws jmaster.jumploader.model.api.A.C {
        this.L.E(ex, ex);
        if (ex instanceof jmaster.jumploader.model.api.A.C) {
            throw (jmaster.jumploader.model.api.A.C)ex;
        }
        throw new jmaster.jumploader.model.api.A.C(ex);
    }
    
    private UploaderConfig B() {
        return this.K().B();
    }
    
    private void A(final int n) throws jmaster.jumploader.model.api.A.C {
        if (this.V != n) {
            throw new jmaster.jumploader.model.api.A.C("Status assertion failed, required: " + n + ", current: " + this.V);
        }
    }
    
    private void B(final int v) {
        this.V = v;
        if (this.L.B()) {
            this.L.D("uploader status change: " + this);
        }
        if (this.isUploading()) {
            this.A();
        }
        this.D();
        if (this.isReady()) {
            this.A();
        }
    }
    
    private jmaster.jumploader.model.impl.upload.B E(final IUploadFile uploadFile) throws jmaster.jumploader.model.api.A.C {
        if (!this.J.contains(uploadFile)) {
            throw new jmaster.jumploader.model.api.A.C("Invalid file: " + uploadFile);
        }
        return (jmaster.jumploader.model.impl.upload.B)uploadFile;
    }
    
    private void B(final IUploadFile uploadFile, final int n) throws jmaster.jumploader.model.api.A.C {
        if (uploadFile.getStatus() != n) {
            throw new jmaster.jumploader.model.api.A.C("File status assertion failed for " + uploadFile + ", required: " + n + ", current: " + uploadFile.getStatus());
        }
    }
    
    public void updateFileStatus(final IUploadFile uploadFile, final int n) {
        final jmaster.jumploader.model.impl.upload.B b = (jmaster.jumploader.model.impl.upload.B)uploadFile;
        if (this.L.B()) {
            this.L.D("file status change: " + b);
        }
        b.B(n);
        this.A((IUploadFile)b);
    }
    
    protected synchronized jmaster.jumploader.model.impl.upload.B E() {
        jmaster.jumploader.model.impl.upload.B b = null;
        for (int n = 0; b == null && n < this.getFileCount(); ++n) {
            final IUploadFile file = this.getFile(n);
            if (file.isReady()) {
                b = (jmaster.jumploader.model.impl.upload.B)file;
            }
        }
        if (b != null) {
            final jmaster.jumploader.model.impl.C.A a = new jmaster.jumploader.model.impl.C.A();
            a.B(50);
            a.E(System.currentTimeMillis());
            a.D(b.getLength());
            a.C(System.currentTimeMillis());
            a.B(0L);
            b.A(a);
            this.updateFileStatus(b, 1);
        }
        return b;
    }
    
    protected void A(final IUploadFile uploadFile, final Exception ex) throws jmaster.jumploader.model.api.A.C {
        final jmaster.jumploader.model.impl.upload.B e = this.E(uploadFile);
        String s = null;
        if (ex != null) {
            if (ex instanceof jmaster.util.http.C) {
                s = this.R.A(this, this.B(), e, "uploader.errorBadServerResponse");
            }
            else if (ex instanceof SocketException) {
                this.L.E(ex, ex);
                s = this.R.A(this, this.B(), e, "uploader.errorNetworkFailure");
            }
            else if (ex instanceof jmaster.jumploader.model.api.A.A) {
                s = this.R.A(this, this.B(), e, "uploader.errorAborted");
            }
            else if (ex instanceof IOException) {
                s = this.R.A(this, this.B(), e, "uploader.errorFileAccessFailure");
            }
            else if (ex instanceof jmaster.jumploader.model.api.A.B) {
                s = ((jmaster.jumploader.model.api.A.B)ex).getMessage();
            }
            else {
                s = this.R.A(this, this.B(), e, "uploader.errorUnexpectedFailure");
            }
        }
        e.A((ITransferProgress)null);
        e.A((ex == null) ? null : new Exception(s));
        this.updateFileStatus(e, (ex == null) ? 2 : 3);
        if (ex != null && ex instanceof IOException && this.B().getAutoRetryCount() > 0) {
            int int1 = 0;
            try {
                int1 = Integer.parseInt("" + e.getAttributeSet().getAttributeByName("retryCount").getValue());
            }
            catch (Exception ex2) {}
            if (this.L.B()) {
                this.L.D("Retry count=" + int1 + " for file " + e);
            }
            if (int1 < this.B().getAutoRetryCount()) {
                if (this.L.B()) {
                    this.L.D("Autoretry for " + e);
                }
                e.getAttributeSet().createStringAttribute("retryCount", "" + (int1 + 1));
                this.retryFileUpload(e);
            }
        }
    }
    
    protected boolean D(final jmaster.jumploader.model.impl.upload.B b) {
        return b.isUploading();
    }
    
    protected boolean E(final jmaster.jumploader.model.impl.upload.B b) {
        return this.isFileRemovalEnabled() && !b.isUploading();
    }
    
    protected boolean C(final jmaster.jumploader.model.impl.upload.B b) {
        return b.isFailed();
    }
    
    private void A() {
        if (this.isUploading()) {
            final long currentTimeMillis = System.currentTimeMillis();
            for (int i = 0; i < this.getUploadThreadCount(); ++i) {
                final jmaster.jumploader.model.impl.upload.B b = (jmaster.jumploader.model.impl.upload.B)((jmaster.jumploader.model.impl.upload.A)this.getUploadThread(i)).A();
                final jmaster.jumploader.model.impl.C.A a = (b == null) ? null : ((jmaster.jumploader.model.impl.C.A)b.getTransferProgress());
                if (a != null) {
                    final long n = currentTimeMillis - a.B();
                    if (n > 200L) {
                        a.A((int)(1000L * (a.getBytesTransferred() - a.D()) / n));
                        a.B(a.getBytesTransferred());
                        a.C(currentTimeMillis);
                    }
                }
            }
            if (this.U == null) {
                (this.U = new jmaster.jumploader.model.impl.C.A()).B(50);
                this.U.E(System.currentTimeMillis());
            }
            if (this.S == null) {
                final jmaster.util.B.B b2 = new jmaster.util.B.B();
                b2.A(this);
                b2.A("uploaderUpdater");
                b2.A(true);
                (this.S = this.K.getModel().C().A(b2)).start();
            }
            this.U.D(this.getFilesLength());
            long n2 = 0L;
            for (int j = 0; j < this.getFileCount(); ++j) {
                final IUploadFile file = this.getFile(j);
                if (file.isFailed() || file.isFinished()) {
                    n2 += file.getLength();
                }
                else if (file.isUploading()) {
                    n2 += file.getTransferProgress().getBytesTransferred();
                }
            }
            this.U.A(n2);
            int n3 = 0;
            for (int k = 0; k < this.getUploadThreadCount(); ++k) {
                final jmaster.jumploader.model.impl.upload.A a2 = (jmaster.jumploader.model.impl.upload.A)this.getUploadThread(k);
                final IUploadFile a3 = a2.A();
                final ITransferProgress transferProgress = (a3 == null) ? null : a3.getTransferProgress();
                if (transferProgress != null && transferProgress.getRate() > 0) {
                    n3 += transferProgress.getRate();
                }
                else {
                    n3 += a2.I();
                }
            }
            this.U.A(n3);
        }
        else if (this.isReady()) {
            if (this.S != null) {
                final Thread s = this.S;
                this.S = null;
                s.interrupt();
            }
            if (this.U != null) {
                this.U = null;
            }
        }
    }
    
    public boolean canAddFile(final String s) {
        if (jmaster.util.B.A.C(s)) {
            return false;
        }
        final jmaster.jumploader.model.impl.upload.B b = new jmaster.jumploader.model.impl.upload.B(this, this.X.createFileObject(s));
        final UploaderConfig b2 = this.B();
        return b.B() && b.isFileSystem() && (b2.getMaxFileLength() == -1L || b2.getMaxFileLength() >= b.getLength()) && (b2.getMaxFiles() == -1 || b2.getMaxFiles() > this.getFileCount()) && (!b.isFile() || b2.getFileNamePattern() == null || Pattern.matches(b2.getFileNamePattern(), b.getName())) && (b2.getMaxLength() == -1L || b2.getMaxLength() >= this.getFilesLength() + b.getLength()) && (b2.isDirectoriesEnabled() || b.isFile()) && (b2.isDuplicateFileEnabled() || this.getFileByPath(b.getPath()) == null);
    }
    
    public void updateFile(final IUploadFile uploadFile, final File file, final boolean b) {
        final jmaster.jumploader.model.impl.upload.B b2 = (jmaster.jumploader.model.impl.upload.B)uploadFile;
        final String name = b2.getName();
        b2.B(file);
        if (b) {
            b2.A(name);
        }
        this.C((IUploadFile)b2);
    }
    
    public void moveFiles(final IUploadFile[] array, final int n) throws jmaster.jumploader.model.api.A.C {
        if (n < 0) {
            throw new jmaster.jumploader.model.api.A.C("Invalid insert index: " + n);
        }
        for (int i = 0; i < array.length; ++i) {
            final jmaster.jumploader.model.impl.upload.B e = this.E(array[i]);
            final int index = e.getIndex();
            final boolean indexSelected = this.b.isIndexSelected(index);
            if (indexSelected) {
                this.b.A(index);
            }
            this.J.remove(e);
            int size = n + i;
            if (size > this.J.size()) {
                size = this.J.size();
            }
            this.J.add(size, e);
            if (indexSelected) {}
            this.A(e, index);
        }
    }
    
    public void setMainFile(final IUploadFile uploadFile) throws jmaster.jumploader.model.api.A.C {
        for (int i = 0; i < this.getFileCount(); ++i) {
            final IUploadFile file = this.getFile(i);
            final IAttributeSet attributeSet = file.getAttributeSet();
            final IAttribute attributeByName = attributeSet.getAttributeByName("mainFile");
            if (attributeByName != null) {
                attributeSet.removeAttribute(attributeByName);
                this.C(file);
            }
        }
        final jmaster.jumploader.model.impl.upload.B e = this.E(uploadFile);
        e.getAttributeSet().setAttribute("mainFile", "true");
        this.C((IUploadFile)e);
    }
    
    protected void J() throws jmaster.jumploader.model.api.A.C {
        if (this.B().isUseMainFile()) {
            int n = 0;
            for (int n2 = 0; n == 0 && n2 < this.getFileCount(); ++n2) {
                final IAttribute attributeByName = this.getFile(n2).getAttributeSet().getAttributeByName("mainFile");
                if (attributeByName != null && "true".equals(attributeByName.getValue())) {
                    n = 1;
                }
            }
            if (n == 0 && this.getFileCount() > 0) {
                this.setMainFile(this.getFile(0));
            }
        }
    }
    
    private String A(final jmaster.jumploader.model.impl.upload.B b) {
        String stringValue = null;
        final IAttribute attributeByName = b.getAttributeSet().getAttributeByName("relativePath");
        if (attributeByName != null) {
            stringValue = attributeByName.getStringValue();
        }
        return stringValue;
    }
    
    private void A(final jmaster.jumploader.model.impl.upload.B b, final String stringValue) {
        final IAttribute attributeByName = b.getAttributeSet().getAttributeByName("relativePath");
        if (attributeByName == null) {
            b.getAttributeSet().createAttribute("relativePath", stringValue).setSendToServer(true);
        }
        else {
            attributeByName.setStringValue(stringValue);
        }
    }
    
    public IUploadFile processDocument(final String key, final String s, final String s2, final String s3, final String s4) throws jmaster.jumploader.model.api.A.C {
        IUploadFile uploadFile = null;
        try {
            if (this.L.B()) {
                this.L.D("processDocument, key=" + key + ", downloadLocation=" + s + ", uploadLocation=" + s2 + ", filename=" + s3);
            }
            final jmaster.jumploader.model.impl.upload.B b = new jmaster.jumploader.model.impl.upload.B(this, null);
            b.A(1);
            b.setKey(key);
            b.D(s);
            b.F(s2);
            b.A(s3);
            b.B(5);
            final F f = new F(this.K, "documentProcessor_" + s3);
            f.C(s4);
            f.A(b);
            this.J.add(b);
            this.B((IUploadFile)b);
            uploadFile = b;
        }
        catch (Exception ex) {
            this.A(ex);
        }
        return uploadFile;
    }
    
    public boolean isDownloading() {
        boolean downloading = false;
        for (int n = 0; !downloading && n < this.getFileCount(); downloading = this.getFile(n).isDownloading(), ++n) {}
        return downloading;
    }
    
    public void processDocument(final IUploadFile uploadFile) {
        final F f = new F(this.K, "documentProcessor_" + uploadFile.getName());
        f.A(true);
        f.A((jmaster.jumploader.model.impl.upload.B)uploadFile);
    }
    
    public void B(final jmaster.jumploader.model.impl.upload.B b) {
        this.J.add(b);
        this.B((IUploadFile)b);
    }
    
    public void applyTransformations(final IUploadFile uploadFile) throws jmaster.jumploader.model.api.A.C {
        try {
            final IAttribute attributeByName = uploadFile.getAttributeSet().getAttributeByName("rotateAngle");
            if (attributeByName != null) {
                long longValue;
                for (longValue = (long)attributeByName.getValue(); longValue < 0L; longValue += 360L) {}
                while (longValue >= 360L) {
                    longValue -= 360L;
                }
                if (longValue != 0L) {
                    boolean b = false;
                    if (this.B().isUseLosslessJpegTransformations()) {
                        try {
                            final LLJTran lljTran = new LLJTran(uploadFile.getFile());
                            lljTran.read(3, true);
                            final int n = 797;
                            int n2 = 0;
                            switch ((int)longValue) {
                                case 270: {
                                    n2 = 7;
                                    break;
                                }
                                case 180: {
                                    n2 = 6;
                                    break;
                                }
                                case 90: {
                                    n2 = 5;
                                    break;
                                }
                            }
                            lljTran.transform(n2, n);
                            final File tempFile = File.createTempFile("jumploader_", ".jpg");
                            if (this.L.B()) {
                                this.L.D("Saving image to temp file: " + tempFile.getAbsolutePath());
                            }
                            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(tempFile));
                            lljTran.save((OutputStream)bufferedOutputStream, 768);
                            bufferedOutputStream.close();
                            lljTran.freeMemory();
                            this.updateFile(uploadFile, tempFile, true);
                            b = true;
                        }
                        catch (Exception ex2) {
                            this.L.C("Lossless jpeg rotate failed for " + uploadFile);
                        }
                    }
                    if (!b) {
                        final jmaster.jumploader.model.api.B.A l = this.K().L();
                        Image image = l.C(uploadFile.getFile());
                        switch ((int)longValue) {
                            case 270: {
                                image = this.K().L().F(image);
                                break;
                            }
                            case 180: {
                                image = this.K().L().C(image);
                                break;
                            }
                            case 90: {
                                image = this.K().L().A(image);
                                break;
                            }
                        }
                        final File tempFile2 = File.createTempFile("jumploader_", ".jpg");
                        if (this.L.B()) {
                            this.L.D("Saving image to temp file: " + tempFile2.getAbsolutePath());
                        }
                        l.A(image, tempFile2, this.K().J().getJpegQuality());
                        this.updateFile(uploadFile, tempFile2, true);
                    }
                }
                uploadFile.getAttributeSet().removeAttribute(attributeByName);
            }
        }
        catch (Exception ex) {
            this.A(ex);
        }
    }
    
    public void metadataChanged(final IUploadFile uploadFile) {
        this.C(uploadFile);
    }
    
    public boolean isDestroyed() {
        return this.O;
    }
    
    public boolean isAddingFiles() {
        return this.E;
    }
    
    public void stopAddingFiles() {
        this.D = true;
    }
    
    public IUploadFile getAddFileCurrent() {
        return this.a;
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
