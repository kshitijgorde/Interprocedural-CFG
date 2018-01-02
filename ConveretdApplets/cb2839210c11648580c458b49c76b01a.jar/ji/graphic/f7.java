// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.Component;
import java.awt.Color;
import java.awt.Frame;
import java.awt.FileDialog;

public class f7 implements ec
{
    FileDialog a;
    
    public f7(final Frame frame) {
        this.a = null;
        this.a = new FileDialog(frame);
    }
    
    public void intialise() {
    }
    
    public void dispose() {
        if (this.a != null) {
            this.a.dispose();
        }
    }
    
    public String getDirectory() {
        if (this.a != null) {
            return this.a.getDirectory();
        }
        return null;
    }
    
    public String getFile() {
        if (this.a != null) {
            return this.a.getFile();
        }
        return null;
    }
    
    public void setBackground(final Color background) {
        if (this.a != null) {
            this.a.setBackground(background);
        }
    }
    
    public void setDirectory(final String directory) {
        if (this.a != null) {
            this.a.setDirectory(directory);
        }
    }
    
    public void setFile(final String file) {
        if (this.a != null) {
            this.a.setFile(file);
        }
    }
    
    public void setMode(final int n) {
        int mode = 0;
        if (n == 2) {
            mode = 1;
        }
        if (this.a != null) {
            this.a.setMode(mode);
        }
    }
    
    public void setParent(final Component component, final String s) {
    }
    
    public void setTitle(final String title) {
        if (this.a != null) {
            this.a.setTitle(title);
        }
    }
    
    public void show() {
        if (this.a != null) {
            this.a.show();
        }
    }
}
