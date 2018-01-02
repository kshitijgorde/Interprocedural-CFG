// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list.renderer;

import jmaster.jumploader.model.api.C.C;
import java.awt.image.BufferedImage;
import jmaster.jumploader.view.api.image.IImageView;
import jmaster.jumploader.model.api.file.IFile;
import jmaster.jumploader.view.api.IGenericView;
import jmaster.util.C.B;
import jmaster.jumploader.model.api.upload.IUploadFile;

public class ImageEditActionRenderer extends AbstractUploadFileRendererComponent
{
    public void prepare() {
        final IUploadFile uploadFile = this.getUploadFile();
        this.setVisible(this.V.B().isImageEditorEnabled() && this.V.D().isReady() && uploadFile.isEditableImage());
        super.prepare();
    }
    
    protected void A() {
        jmaster.util.C.B.A(this, "editImage");
    }
    
    public void editImage() {
        try {
            final IUploadFile uploadFile = this.getUploadFile();
            this.V.D().applyTransformations(uploadFile);
            final IImageView imageView = this.getView().getImageView();
            this.getView().setCurrentView(imageView);
            final BufferedImage d = this.V.L().D(this.V.L().C(uploadFile.getFile()));
            final C b = this.V.A().B(uploadFile);
            BufferedImage d2 = null;
            if (b != null && b.A() != null) {
                d2 = this.V.L().D(b.A());
            }
            imageView.setImage(uploadFile, d, d2);
            imageView.setZoomToFit();
        }
        catch (Exception ex) {
            this.F.E(ex, ex);
        }
    }
}
