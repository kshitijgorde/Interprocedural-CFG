// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list.renderer;

import jmaster.jumploader.model.api.common.IAttribute;
import java.awt.image.BufferedImage;
import jmaster.jumploader.model.api.B.A;
import java.awt.Image;
import jmaster.jumploader.model.api.C.C;
import jmaster.jumploader.model.api.file.IFile;
import jmaster.util.C.B;
import jmaster.jumploader.model.api.upload.IUploadFile;

public class ImageRotateActionRenderer extends AbstractUploadFileRendererComponent
{
    boolean z;
    
    public ImageRotateActionRenderer() {
        this.z = true;
    }
    
    public boolean isRotateCw() {
        return this.z;
    }
    
    public void setRotateCw(final boolean z) {
        this.z = z;
    }
    
    public void prepare() {
        final IUploadFile uploadFile = this.getUploadFile();
        this.setVisible(this.V.B().isImageRotateEnabled() && this.V.D().isReady() && uploadFile.isEditableImage());
        super.prepare();
    }
    
    protected void A() {
        jmaster.util.C.B.A(this, "rotateImage");
    }
    
    public void rotateImage() {
        try {
            final A l = this.V.L();
            final jmaster.jumploader.model.api.C.A a = this.V.A();
            final IUploadFile uploadFile = this.getUploadFile();
            final jmaster.jumploader.model.impl.D.B b = (jmaster.jumploader.model.impl.D.B)a.B(uploadFile);
            if (b != null && b.A() != null) {
                final jmaster.jumploader.model.impl.D.B b2 = (jmaster.jumploader.model.impl.D.B)a.A(b);
                a.A(uploadFile, b2);
                final BufferedImage b3 = l.B(b2.A());
                b2.A(l.D(this.z ? l.F(b3) : l.A(b3)));
                IAttribute attribute = uploadFile.getAttributeSet().getAttributeByName("rotateAngle");
                if (attribute == null) {
                    attribute = uploadFile.getAttributeSet().createAttribute("rotateAngle", new Long(0L));
                    attribute.setSendToServer(false);
                }
                attribute.setValue(new Long((long)attribute.getValue() + (this.z ? -90L : 90L)));
                this.S.repaint();
            }
        }
        catch (Exception ex) {
            this.F.E(ex, ex);
        }
    }
}
