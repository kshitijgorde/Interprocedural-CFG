// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.upload;

import java.awt.Image;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import jmaster.jumploader.model.api.B.A;
import jmaster.util.C.B;
import jmaster.jumploader.model.api.upload.IUploadFile;
import jmaster.jumploader.model.api.upload.IUploader;

public class C extends D
{
    protected double \u015e;
    protected double \u0160;
    protected double \u015f;
    private double \u015d;
    static /* synthetic */ Class class$jmaster$jumploader$model$api$upload$IUploader;
    static /* synthetic */ Class class$jmaster$jumploader$model$api$upload$IUploadFile;
    
    public C() {
        this.\u015e = 2.0;
        this.\u0160 = 7.5;
        this.\u015f = 4.0;
        this.\u015d = 900.0;
    }
    
    public double Y() {
        return this.\u015e;
    }
    
    public void B(final double \u015f) {
        this.\u015e = \u015f;
    }
    
    public double X() {
        return this.\u0160;
    }
    
    public void A(final double \u0161) {
        this.\u0160 = \u0161;
    }
    
    public double V() {
        return this.\u015f;
    }
    
    public void C(final double \u015f) {
        this.\u015f = \u015f;
    }
    
    public double W() {
        return this.\u015d;
    }
    
    public void D(final double \u015d) {
        this.\u015d = \u015d;
    }
    
    public void uploaderFileAdded(final IUploader uploader, final IUploadFile uploadFile) {
        try {
            final A l = ((E)uploader).K().L();
            if (l.B(uploadFile.getFile())) {
                final Dimension a = l.A(uploadFile.getFile());
                final double n = a.width * a.height / 1048576.0;
                final double n2 = uploadFile.getLength() / 1048576.0;
                if (n <= this.\u015e) {
                    uploadFile.setCheckedUpdatable(false);
                    uploadFile.setChecked(false);
                }
                else if (n >= this.\u015e && n2 <= this.\u0160) {
                    uploadFile.setCheckedUpdatable(true);
                    uploadFile.setChecked(true);
                }
                else if (n2 > this.\u0160) {
                    jmaster.util.C.B.B(this, "adjustImage", new Class[] { (C.class$jmaster$jumploader$model$api$upload$IUploader == null) ? (C.class$jmaster$jumploader$model$api$upload$IUploader = class$("jmaster.jumploader.model.api.upload.IUploader")) : C.class$jmaster$jumploader$model$api$upload$IUploader, (C.class$jmaster$jumploader$model$api$upload$IUploadFile == null) ? (C.class$jmaster$jumploader$model$api$upload$IUploadFile = class$("jmaster.jumploader.model.api.upload.IUploadFile")) : C.class$jmaster$jumploader$model$api$upload$IUploadFile }, new Object[] { uploader, uploadFile });
                }
            }
        }
        catch (Exception ex) {
            this.\u015c.E(ex, ex);
        }
    }
    
    public void A(final IUploader uploader, final IUploadFile uploadFile) {
        final long currentTimeMillis = System.currentTimeMillis();
        try {
            if (this.\u015c.B()) {
                this.\u015c.D("adjusting image: " + uploadFile);
            }
            uploader.updateFileStatus(uploadFile, 4);
            final A l = ((E)uploader).K().L();
            final Image c = l.C(uploadFile.getFile());
            final Dimension dimension = new Dimension(c.getWidth(null), c.getHeight(null));
            final Image a = l.A(c, Math.sqrt((this.\u015f * 1000000.0 + dimension.width * dimension.height) / (dimension.width * dimension.height) - 1.0), null, false);
            final File tempFile = File.createTempFile("jumploader", ".jpg");
            tempFile.deleteOnExit();
            final FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            jmaster.util.D.A.D(l.D(a), this.\u015d / 1000.0, fileOutputStream);
            jmaster.util.F.A.A(fileOutputStream);
            uploadFile.setChecked(true);
            uploader.updateFile(uploadFile, tempFile, true);
        }
        catch (Throwable t) {
            this.\u015c.E(t, t);
        }
        finally {
            final long currentTimeMillis2 = System.currentTimeMillis();
            uploader.updateFileStatus(uploadFile, 0);
            if (this.\u015c.B()) {
                this.\u015c.D("finished adjusting image, t=" + (currentTimeMillis2 - currentTimeMillis) + ", : " + uploadFile);
            }
        }
    }
    
    public String R() {
        return "uploadImagePreprocessor";
    }
    
    public String T() {
        return "uip_";
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
