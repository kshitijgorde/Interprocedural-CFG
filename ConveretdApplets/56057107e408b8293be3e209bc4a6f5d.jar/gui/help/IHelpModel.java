// 
// Decompiled by Procyon v0.5.30
// 

package gui.help;

import java.net.URL;
import java.util.Observable;
import java.io.File;

public interface IHelpModel
{
    void setHelpPath(final File p0);
    
    boolean isHelpPathDefined();
    
    String getHelpPath();
    
    Observable getHelpFileStorageObservable();
    
    boolean isHelpPathChangeable();
    
    String helpPathValidityCheck(final File p0);
    
    URL getHelpURL(final String p0);
}
