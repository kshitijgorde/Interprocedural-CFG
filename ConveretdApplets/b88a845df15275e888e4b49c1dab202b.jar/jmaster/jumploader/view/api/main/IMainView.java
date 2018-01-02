// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.api.main;

import jmaster.jumploader.view.api.image.IImageView;
import jmaster.jumploader.view.api.file.list.IFileListView;
import jmaster.jumploader.view.api.file.tree.IFileTreeView;
import jmaster.jumploader.view.api.upload.IUploadView;
import jmaster.jumploader.view.api.IGenericView;

public interface IMainView extends IGenericView
{
    IProgramView getProgramView();
    
    IUploadView getUploadView();
    
    IFileTreeView getFileTreeView();
    
    IFileListView getFileListView();
    
    IImageView getImageView();
    
    void showError(final String p0);
    
    void showWarning(final String p0);
    
    void showInfo(final String p0);
    
    void updateView();
    
    void setCurrentView(final IGenericView p0);
    
    IGenericView getCurrentView();
    
    void showGlassView(final boolean p0);
}
