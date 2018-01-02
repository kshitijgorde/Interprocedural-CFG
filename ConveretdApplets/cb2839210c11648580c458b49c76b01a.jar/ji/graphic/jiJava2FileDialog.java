// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import ji.v1event.af;
import ji.util.d;
import ji.awt.bb;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.io.File;
import java.awt.Component;

public class jiJava2FileDialog implements ec
{
    private zt fileChooser;
    private int chooserMode;
    private boolean cancelled;
    private Component parent;
    private String parentId;
    
    public jiJava2FileDialog() throws Exception {
        this.cancelled = false;
        this.fileChooser = new zt((aej)null);
    }
    
    public void setParent(final Component parent, final String parentId) {
        this.parent = parent;
        this.parentId = parentId;
    }
    
    public void intialise() {
        this.cancelled = false;
        if (this.chooserMode == 3) {
            this.fileChooser.setFileSelectionMode(1);
        }
        else {
            this.fileChooser.setFileSelectionMode(0);
        }
    }
    
    public void dispose() {
        this.fileChooser = null;
    }
    
    public String getDirectory() {
        if (!this.cancelled) {
            return String.valueOf(String.valueOf(this.fileChooser.getCurrentDirectory().getAbsolutePath())).concat(String.valueOf(String.valueOf(File.separator)));
        }
        return null;
    }
    
    public String getFile() {
        if (!this.cancelled) {
            return this.fileChooser.getSelectedFile().getName();
        }
        return null;
    }
    
    public void setBackground(final Color background) {
        this.fileChooser.setBackground(background);
    }
    
    public void setDirectory(final String s) {
        this.fileChooser.setCurrentDirectory(new File(s));
    }
    
    public void setFile(final String s) {
        this.fileChooser.setSelectedFile(new File(s));
    }
    
    public void setMode(final int chooserMode) {
        this.chooserMode = chooserMode;
    }
    
    public void setTitle(final String dialogTitle) {
        this.fileChooser.setDialogTitle(dialogTitle);
    }
    
    public void show() {
        int n;
        if (this.chooserMode == 1) {
            this.fileChooser.setAcceptAllFileFilterUsed(true);
            n = this.fileChooser.showOpenDialog(this.parent);
        }
        else if (this.chooserMode == 3) {
            this.fileChooser.setAcceptAllFileFilterUsed(false);
            n = this.fileChooser.showOpenDialog(this.parent);
        }
        else {
            this.fileChooser.setAcceptAllFileFilterUsed(true);
            n = this.fileChooser.showSaveDialog(this.parent);
        }
        if (n != 0) {
            this.cancelled = true;
        }
    }
    
    private class zt extends JFileChooser implements Runnable
    {
        public void approveSelection() {
            if (this.getSelectedFile().exists() && jiJava2FileDialog.this.chooserMode == 2) {
                new bb(jiJava2FileDialog.this.parentId, this).start();
            }
            else {
                super.approveSelection();
            }
        }
        
        public void run() {
            if (d.a(d.b(1191, jiJava2FileDialog.this.parentId), String.valueOf(String.valueOf(this.getSelectedFile().getAbsolutePath())).concat(String.valueOf(String.valueOf(d.b(1192, jiJava2FileDialog.this.parentId)))), this, null, jiJava2FileDialog.this.parentId)) {
                super.approveSelection();
            }
        }
    }
    
    interface aej
    {
    }
}
