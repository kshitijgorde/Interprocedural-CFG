// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.api.image;

import java.awt.image.BufferedImage;
import jmaster.jumploader.model.api.upload.IUploadFile;
import jmaster.jumploader.view.api.IGenericView;

public interface IImageView extends IGenericView
{
    void setImage(final IUploadFile p0, final BufferedImage p1, final BufferedImage p2);
    
    BufferedImage getImage();
    
    IUploadFile getUploadFile();
    
    void setZoomToFit();
}
