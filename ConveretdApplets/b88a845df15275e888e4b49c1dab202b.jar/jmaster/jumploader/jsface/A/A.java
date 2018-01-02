// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.jsface.A;

import java.lang.reflect.InvocationTargetException;
import jmaster.jumploader.model.api.A.C;
import jmaster.jumploader.model.api.common.IListSelection;
import jmaster.jumploader.model.api.common.IAttributeSet;
import jmaster.jumploader.model.api.common.ITransferProgress;
import jmaster.jumploader.model.api.upload.IUploadFile;
import java.util.ArrayList;
import jmaster.jumploader.model.api.B;
import java.util.List;
import jmaster.jumploader.model.api.upload.IUploader;
import jmaster.jumploader.jsface.api.IJSUploader;

public class A implements IJSUploader, Runnable
{
    private static final String C = "UploaderJSProxy";
    private jmaster.util.log.A E;
    private IUploader D;
    private List B;
    private Thread A;
    private boolean F;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$jmaster$jumploader$model$api$upload$IUploadFile;
    
    public A(final B b, final IUploader d) {
        this.E = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.B = new ArrayList();
        this.F = false;
        this.D = d;
        final jmaster.util.B.B b2 = new jmaster.util.B.B();
        b2.A(this);
        b2.A("UploaderJSProxy");
        b2.A(true);
        (this.A = b.C().A(b2)).start();
    }
    
    public void destroy() {
        this.F = true;
        if (this.A != null) {
            this.A.interrupt();
            this.A = null;
        }
        synchronized (this.B) {
            this.B.notifyAll();
        }
    }
    
    public boolean isFileAdditionEnabled() {
        return this.D.isFileAdditionEnabled();
    }
    
    public boolean isFileRemovalEnabled() {
        return this.D.isFileRemovalEnabled();
    }
    
    public void setFileAdditionEnabled(final boolean fileAdditionEnabled) {
        this.D.setFileAdditionEnabled(fileAdditionEnabled);
    }
    
    public void setFileRemovalEnabled(final boolean fileRemovalEnabled) {
        this.D.setFileRemovalEnabled(fileRemovalEnabled);
    }
    
    public String addFile(final String s) {
        return this.A("addFile", new Class[] { (jmaster.jumploader.jsface.A.A.class$java$lang$String == null) ? (jmaster.jumploader.jsface.A.A.class$java$lang$String = class$("java.lang.String")) : jmaster.jumploader.jsface.A.A.class$java$lang$String }, new Object[] { s });
    }
    
    public String addFile(final String s, final String s2) {
        return this.A("addFile", new Class[] { (jmaster.jumploader.jsface.A.A.class$java$lang$String == null) ? (jmaster.jumploader.jsface.A.A.class$java$lang$String = class$("java.lang.String")) : jmaster.jumploader.jsface.A.A.class$java$lang$String, Integer.TYPE }, new Object[] { s, new Integer(s2) });
    }
    
    public String addFile(final String s, final String s2, final String s3) {
        return this.A("addFile", new Class[] { (jmaster.jumploader.jsface.A.A.class$java$lang$String == null) ? (jmaster.jumploader.jsface.A.A.class$java$lang$String = class$("java.lang.String")) : jmaster.jumploader.jsface.A.A.class$java$lang$String, Integer.TYPE, (jmaster.jumploader.jsface.A.A.class$java$lang$String == null) ? (jmaster.jumploader.jsface.A.A.class$java$lang$String = class$("java.lang.String")) : jmaster.jumploader.jsface.A.A.class$java$lang$String }, new Object[] { s, new Integer(s2), s3 });
    }
    
    public String removeFile(final IUploadFile uploadFile) {
        return this.A("removeFile", new Class[] { (jmaster.jumploader.jsface.A.A.class$jmaster$jumploader$model$api$upload$IUploadFile == null) ? (jmaster.jumploader.jsface.A.A.class$jmaster$jumploader$model$api$upload$IUploadFile = class$("jmaster.jumploader.model.api.upload.IUploadFile")) : jmaster.jumploader.jsface.A.A.class$jmaster$jumploader$model$api$upload$IUploadFile }, new Object[] { uploadFile });
    }
    
    public String retryFileUpload(final IUploadFile uploadFile) {
        return this.A("retryFileUpload", new Class[] { (jmaster.jumploader.jsface.A.A.class$jmaster$jumploader$model$api$upload$IUploadFile == null) ? (jmaster.jumploader.jsface.A.A.class$jmaster$jumploader$model$api$upload$IUploadFile = class$("jmaster.jumploader.model.api.upload.IUploadFile")) : jmaster.jumploader.jsface.A.A.class$jmaster$jumploader$model$api$upload$IUploadFile }, new Object[] { uploadFile });
    }
    
    public String startUpload() {
        return this.A("startUpload", new Class[0], new Object[0]);
    }
    
    public String stopUpload() {
        return this.A("stopUpload", new Class[0], new Object[0]);
    }
    
    public String stopFileUpload(final IUploadFile uploadFile) {
        return this.A("stopFileUpload", new Class[] { (jmaster.jumploader.jsface.A.A.class$jmaster$jumploader$model$api$upload$IUploadFile == null) ? (jmaster.jumploader.jsface.A.A.class$jmaster$jumploader$model$api$upload$IUploadFile = class$("jmaster.jumploader.model.api.upload.IUploadFile")) : jmaster.jumploader.jsface.A.A.class$jmaster$jumploader$model$api$upload$IUploadFile }, new Object[] { uploadFile });
    }
    
    public boolean canStartUpload() {
        return this.D.canStartUpload();
    }
    
    public boolean canStopUpload() {
        return this.D.canStopUpload();
    }
    
    public IUploadFile[] getAllFiles() {
        IUploadFile[] allFiles = null;
        try {
            allFiles = this.D.getAllFiles();
        }
        catch (Exception ex) {
            this.E.E(ex, ex);
        }
        return allFiles;
    }
    
    public IUploadFile getFile(final String s) {
        IUploadFile file = null;
        try {
            file = this.D.getFile(Integer.parseInt(s));
        }
        catch (Exception ex) {
            this.E.E(ex, ex);
        }
        return file;
    }
    
    public IUploadFile getFileByPath(final String s) {
        IUploadFile fileByPath = null;
        try {
            fileByPath = this.D.getFileByPath(s);
        }
        catch (Exception ex) {
            this.E.E(ex, ex);
        }
        return fileByPath;
    }
    
    public int getFileCount() {
        return this.D.getFileCount();
    }
    
    public int getFileCountByStatus(final String s) {
        return this.D.getFileCountByStatus(Integer.parseInt(s));
    }
    
    public IUploadFile[] getFilesByStatus(final String s) {
        IUploadFile[] filesByStatus = null;
        try {
            filesByStatus = this.D.getFilesByStatus(Integer.parseInt(s));
        }
        catch (Exception ex) {
            this.E.E(ex, ex);
        }
        return filesByStatus;
    }
    
    public long getFilesLength() {
        return this.D.getFilesLength();
    }
    
    public int getStatus() {
        return this.D.getStatus();
    }
    
    public ITransferProgress getTransferProgress() {
        return this.D.getTransferProgress();
    }
    
    public int indexOfFile(final IUploadFile uploadFile) {
        return this.D.indexOfFile(uploadFile);
    }
    
    public boolean isReady() {
        return this.D.isReady();
    }
    
    public boolean isUploading() {
        return this.D.isUploading();
    }
    
    public IAttributeSet getAttributeSet() {
        return this.D.getAttributeSet();
    }
    
    public IListSelection getSelection() {
        return this.D.getSelection();
    }
    
    public boolean isDownloading() {
        return this.D.isDownloading();
    }
    
    public IUploadFile processDocument(final String s, final String s2, final String s3, final String s4, final String s5) throws C {
        IUploadFile uploadFile;
        try {
            final _A a = this.A(this.D, "processDocument", new Class[] { (jmaster.jumploader.jsface.A.A.class$java$lang$String == null) ? (jmaster.jumploader.jsface.A.A.class$java$lang$String = class$("java.lang.String")) : jmaster.jumploader.jsface.A.A.class$java$lang$String, (jmaster.jumploader.jsface.A.A.class$java$lang$String == null) ? (jmaster.jumploader.jsface.A.A.class$java$lang$String = class$("java.lang.String")) : jmaster.jumploader.jsface.A.A.class$java$lang$String, (jmaster.jumploader.jsface.A.A.class$java$lang$String == null) ? (jmaster.jumploader.jsface.A.A.class$java$lang$String = class$("java.lang.String")) : jmaster.jumploader.jsface.A.A.class$java$lang$String, (jmaster.jumploader.jsface.A.A.class$java$lang$String == null) ? (jmaster.jumploader.jsface.A.A.class$java$lang$String = class$("java.lang.String")) : jmaster.jumploader.jsface.A.A.class$java$lang$String, (jmaster.jumploader.jsface.A.A.class$java$lang$String == null) ? (jmaster.jumploader.jsface.A.A.class$java$lang$String = class$("java.lang.String")) : jmaster.jumploader.jsface.A.A.class$java$lang$String }, new Object[] { s, s2, s3, s4, s5 });
            if (a.D != null) {
                this.E.E(a.D, a.D);
                throw a.D;
            }
            uploadFile = (IUploadFile)a.A;
        }
        catch (Throwable t) {
            if (t instanceof C) {
                throw (C)t;
            }
            if (t instanceof RuntimeException) {
                throw (RuntimeException)t;
            }
            throw new RuntimeException(t);
        }
        return uploadFile;
    }
    
    public IUploadFile processDocument(final String s, final String s2, final String s3, final String s4) throws C {
        return this.processDocument(s, s2, s3, s4, null);
    }
    
    public boolean isUploadEnabled() {
        return this.D.isUploadEnabled();
    }
    
    public void setUploadEnabled(final boolean uploadEnabled) {
        this.D.setUploadEnabled(uploadEnabled);
    }
    
    public void run() {
        if (this.E.B()) {
            this.E.D("" + Thread.currentThread().getName() + " started");
        }
        try {
            while (!this.F && this.A != null && !this.A.isInterrupted()) {
                final _A a = this.A();
                if (a != null) {
                    if (this.E.B()) {
                        this.E.D("About to invoke " + a);
                    }
                    try {
                        this.A(a);
                    }
                    catch (Throwable t) {
                        this.E.E("Invocation failed: " + a, t);
                        synchronized (a) {
                            a.notifyAll();
                        }
                    }
                    finally {
                        synchronized (a) {
                            a.notifyAll();
                        }
                    }
                }
            }
        }
        catch (Throwable t2) {
            if (this.A != null) {
                this.E.E("" + Thread.currentThread().getName() + " failed", t2);
            }
        }
        finally {
            if (this.E.B()) {
                this.E.D("" + Thread.currentThread().getName() + " terminated");
            }
        }
    }
    
    private void B(final _A a) {
        synchronized (this.B) {
            this.B.add(a);
            this.B.notifyAll();
        }
    }
    
    private _A A() {
        _A a = null;
        synchronized (this.B) {
            while (!this.F && a == null) {
                if (this.B.size() > 0) {
                    a = this.B.remove(0);
                    this.B.notifyAll();
                }
                else {
                    try {
                        this.B.wait();
                    }
                    catch (InterruptedException ex) {}
                }
            }
        }
        return a;
    }
    
    private _A A(final Object g, final String b, final Class[] e, final Object[] f) {
        final _A a = new _A();
        a.G = g;
        a.B = b;
        a.E = e;
        a.F = f;
        synchronized (a) {
            this.B(a);
            while (a.C == -1L) {
                try {
                    a.wait(100L);
                }
                catch (InterruptedException ex) {
                    this.E.D("Invocation interrupted " + a);
                }
            }
        }
        return a;
    }
    
    private Object A(final _A a) throws Exception {
        try {
            a.A = a.G.getClass().getMethod(a.B, (Class<?>[])a.E).invoke(a.G, a.F);
        }
        catch (Exception ex) {
            if (!(ex instanceof InvocationTargetException)) {
                throw ex;
            }
            a.D = ((InvocationTargetException)ex).getTargetException();
        }
        finally {
            a.C = System.currentTimeMillis();
        }
        return a.A;
    }
    
    private String A(final String s, final Class[] array, final Object[] array2) {
        try {
            this.B(s, array, array2);
        }
        catch (C c) {
            return "" + c;
        }
        return null;
    }
    
    private void B(final String s, final Class[] array, final Object[] array2) throws C {
        final _A a = this.A(this.D, s, array, array2);
        if (a.D != null) {
            if (a.D instanceof C) {
                throw (C)a.D;
            }
            this.E.E(a.D, a.D);
        }
    }
    
    public String removeFileAt(final String s) {
        return this.removeFile(this.getFile(s));
    }
    
    public String retryFileUploadAt(final String s) {
        return this.retryFileUpload(this.getFile(s));
    }
    
    public String stopFileUploadAt(final String s) {
        return this.stopFileUpload(this.getFile(s));
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    protected class _A
    {
        Object G;
        String B;
        Class[] E;
        Object[] F;
        Object A;
        Throwable D;
        long C;
        
        protected _A() {
            this.C = -1L;
        }
        
        public String toString() {
            return "Invocation, methodName=" + this.B + ", target=" + this.G + ", result=" + this.A + ", exception=" + this.D;
        }
    }
}
