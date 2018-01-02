// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.api.image;

import jmaster.jumploader.view.api.IGenericViewListener;

public interface IImageViewListener extends IGenericViewListener
{
    void imageViewCloseAction(final IImageView p0);
    
    void imageViewSaveAction(final IImageView p0);
}
