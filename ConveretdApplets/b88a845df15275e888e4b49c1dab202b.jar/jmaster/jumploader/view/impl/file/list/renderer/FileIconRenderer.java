// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.file.list.renderer;

import jmaster.jumploader.view.impl.upload.UploadView;
import jmaster.util.C.B;
import javax.swing.Icon;

public class FileIconRenderer extends AbstractFileRendererComponent
{
    protected Icon £;
    protected Icon ¢;
    
    public Icon getFileIcon() {
        return this.£;
    }
    
    public void setFileIcon(final Icon £) {
        this.£ = £;
    }
    
    public Icon getFolderIcon() {
        return this.¢;
    }
    
    public void setFolderIcon(final Icon ¢) {
        this.¢ = ¢;
    }
    
    public void prepare() {
        super.prepare();
        if (this.getFile().isFile()) {
            this.setIcon(this.£);
        }
        else {
            this.setIcon(this.¢);
        }
    }
    
    protected void A() {
        jmaster.util.C.B.A(this, "addFiles");
    }
    
    public void addFiles() {
        try {
            ((UploadView)this.U.getUploadView()).fireAddFilesAction(new String[] { this.getFile().getPath() });
        }
        catch (Exception ex) {
            this.F.E(ex, ex);
        }
    }
}
