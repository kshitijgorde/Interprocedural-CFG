// 
// Decompiled by Procyon v0.5.30
// 

package net.formis.fstech.efs.filechooser;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class PDFFilter extends FileFilter
{
    String pdf_ext;
    
    public PDFFilter(final String pdfext) {
        this.pdf_ext = "pdf";
        if (pdfext != null && pdfext.trim().length() > 0) {
            this.pdf_ext = pdfext.toLowerCase();
        }
    }
    
    public boolean accept(final File f) {
        if (f.isDirectory()) {
            return true;
        }
        final String extension = Utils.getExtension(f);
        if (extension != null) {
            System.out.print("Extension : " + extension);
            return this.pdf_ext.indexOf(extension.toLowerCase()) >= 0;
        }
        return false;
    }
    
    public String getDescription() {
        return this.pdf_ext + " File";
    }
}
