// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.upload;

import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.zip.ZipOutputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import jmaster.jumploader.model.api.upload.IUploadFile;
import jmaster.jumploader.model.api.A.C;
import jmaster.jumploader.model.api.upload.IUploader;
import jmaster.util.log.A;
import jmaster.jumploader.model.api.upload.B;

public class UploadFilePreprocessor implements B
{
    protected A \u0162;
    private jmaster.jumploader.model.api.B \u0161;
    static /* synthetic */ Class class$jmaster$jumploader$model$api$upload$IUploader;
    static /* synthetic */ Class class$jmaster$jumploader$model$impl$upload$B;
    
    public UploadFilePreprocessor(final jmaster.jumploader.model.api.B \u0161) {
        this.\u0162 = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.\u0161 = \u0161;
        \u0161.D().addListener(this);
    }
    
    public void uploaderFileAddFailed(final IUploader uploader, final C c) {
    }
    
    public void uploaderFileAdded(final IUploader uploader, final IUploadFile uploadFile) {
        if (uploadFile.isFile() && "zipOnAdd".equals(this.\u0161.B().getCompressionMode())) {
            jmaster.util.C.B.B(this, "zipFile", new Class[] { (UploadFilePreprocessor.class$jmaster$jumploader$model$api$upload$IUploader == null) ? (UploadFilePreprocessor.class$jmaster$jumploader$model$api$upload$IUploader = class$("jmaster.jumploader.model.api.upload.IUploader")) : UploadFilePreprocessor.class$jmaster$jumploader$model$api$upload$IUploader, (UploadFilePreprocessor.class$jmaster$jumploader$model$impl$upload$B == null) ? (UploadFilePreprocessor.class$jmaster$jumploader$model$impl$upload$B = class$("jmaster.jumploader.model.impl.upload.B")) : UploadFilePreprocessor.class$jmaster$jumploader$model$impl$upload$B }, new Object[] { uploader, uploadFile });
        }
        if (uploadFile.isDirectory() && this.\u0161.B().isZipDirectoriesOnAdd()) {
            jmaster.util.C.B.B(this, "zipDirectory", new Class[] { (UploadFilePreprocessor.class$jmaster$jumploader$model$api$upload$IUploader == null) ? (UploadFilePreprocessor.class$jmaster$jumploader$model$api$upload$IUploader = class$("jmaster.jumploader.model.api.upload.IUploader")) : UploadFilePreprocessor.class$jmaster$jumploader$model$api$upload$IUploader, (UploadFilePreprocessor.class$jmaster$jumploader$model$impl$upload$B == null) ? (UploadFilePreprocessor.class$jmaster$jumploader$model$impl$upload$B = class$("jmaster.jumploader.model.impl.upload.B")) : UploadFilePreprocessor.class$jmaster$jumploader$model$impl$upload$B }, new Object[] { uploader, uploadFile });
        }
    }
    
    public void uploaderFileAdditionEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileMoved(final IUploader uploader, final IUploadFile uploadFile, final int n) {
    }
    
    public void uploaderFileRemovalEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileRemoved(final IUploader uploader, final IUploadFile uploadFile) {
    }
    
    public void uploaderFileStatusChanged(final IUploader uploader, final IUploadFile uploadFile) {
    }
    
    public void uploaderFileUpdated(final IUploader uploader, final IUploadFile uploadFile) {
    }
    
    public void uploaderFilesReset(final IUploader uploader) {
    }
    
    public void uploaderStatusChanged(final IUploader uploader) {
    }
    
    public void uploaderUploadEnabledChanged(final IUploader uploader) {
    }
    
    public synchronized void zipFile(final IUploader uploader, final jmaster.jumploader.model.impl.upload.B b) {
        this.\u0162.D("Preprocessing " + b);
        try {
            this.\u0161.D().updateFileStatus(b, 4);
            final long length = b.getLength();
            final File a = A(b);
            b.A(a, true);
            if (length > 0L) {
                b.A(new Double(a.length() / length));
            }
            b.A(b.getName() + ".zip");
            b.E(true);
        }
        catch (Exception ex) {
            this.\u0162.E(ex, ex);
        }
        finally {
            uploader.updateFileStatus(b, 0);
            this.\u0162.D("Preprocessing finished for " + b);
        }
    }
    
    public synchronized void zipDirectory(final IUploader uploader, final jmaster.jumploader.model.impl.upload.B b) {
        this.\u0162.D("Preprocessing " + b);
        try {
            this.\u0161.D().updateFileStatus(b, 4);
            final File tempFile = File.createTempFile("jumploader", ".zip");
            final jmaster.util.B.C c = new jmaster.util.B.C();
            jmaster.util.F.A.B.B().A().A(b.getPath(), tempFile.getAbsolutePath(), c);
            b.A(tempFile, true, true);
            if (c.A() > 0L) {
                b.A(new Double(tempFile.length() / c.A()));
            }
            b.E(true);
            b.getAttributeSet().setStringAttribute("zippedDirectory", "true");
        }
        catch (Exception ex) {
            this.\u0162.E(ex, ex);
        }
        finally {
            uploader.updateFileStatus(b, 0);
            this.\u0162.D("Preprocessing finished for " + b);
        }
    }
    
    private static File A(final IUploadFile uploadFile) throws Exception {
        final File tempFile = File.createTempFile("jumploader_", ".zip");
        tempFile.deleteOnExit();
        OutputStream outputStream = null;
        ZipOutputStream zipOutputStream = null;
        InputStream inputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(tempFile));
            zipOutputStream = new ZipOutputStream(outputStream);
            inputStream = new BufferedInputStream(new FileInputStream(uploadFile.getFile()));
            final ZipEntry zipEntry = new ZipEntry(uploadFile.getName());
            zipEntry.setSize(uploadFile.getLength());
            zipOutputStream.putNextEntry(zipEntry);
            jmaster.util.F.A.A(inputStream, zipOutputStream);
            zipOutputStream.closeEntry();
        }
        finally {
            jmaster.util.F.A.A(zipOutputStream);
            jmaster.util.F.A.A(outputStream);
            jmaster.util.F.A.A(inputStream);
        }
        return tempFile;
    }
    
    public void destroy() {
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
