// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload;

import jmaster.jumploader.model.api.config.UploaderConfig;
import java.util.regex.Pattern;
import java.io.File;
import jmaster.jumploader.model.api.B;
import javax.swing.filechooser.FileFilter;

public class UploadFileFilter extends FileFilter
{
    protected B A;
    
    public UploadFileFilter(final B a) {
        this.A = a;
    }
    
    public boolean accept(final File file) {
        boolean b = true;
        final UploaderConfig b2 = this.A.B();
        if (file.isFile() && b2.getFileNamePattern() != null && !Pattern.matches(b2.getFileNamePattern(), file.getName())) {
            b = false;
        }
        return b;
    }
    
    public String getDescription() {
        return null;
    }
}
