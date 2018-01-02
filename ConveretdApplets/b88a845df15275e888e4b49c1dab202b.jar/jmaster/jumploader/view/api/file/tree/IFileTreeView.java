// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.api.file.tree;

import java.io.File;
import jmaster.jumploader.view.api.IGenericView;

public interface IFileTreeView extends IGenericView
{
    File getPath();
    
    void setPath(final File p0);
    
    String getPathString();
    
    void setPathString(final String p0);
    
    void updateView();
}
