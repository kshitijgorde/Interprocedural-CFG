// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.api.main;

import jmaster.jumploader.view.api.file.list.IFileListView;
import jmaster.jumploader.view.api.file.tree.IFileTreeView;
import jmaster.jumploader.view.api.upload.IUploadView;
import jmaster.jumploader.view.api.IGenericView;

public interface IProgramView extends IGenericView
{
    IUploadView getUploadView();
    
    IFileTreeView getFileTreeView();
    
    IFileListView getFileListView();
    
    void updateView();
}
