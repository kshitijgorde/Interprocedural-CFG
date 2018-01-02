// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.api.file.list;

import java.io.File;
import jmaster.jumploader.view.api.IGenericViewListener;

public interface IFileListViewListener extends IGenericViewListener
{
    void fileListViewPathChanged(final IFileListView p0, final File p1);
    
    void fileListViewFileDblClicked(final IFileListView p0, final File p1);
}
