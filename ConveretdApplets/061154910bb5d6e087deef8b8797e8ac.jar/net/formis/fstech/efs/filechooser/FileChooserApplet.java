// 
// Decompiled by Procyon v0.5.30
// 

package net.formis.fstech.efs.filechooser;

import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.awt.EventQueue;
import javax.swing.UIManager;
import java.io.File;
import javax.swing.JApplet;

public class FileChooserApplet extends JApplet
{
    File selectedFile;
    File selectedFileLoc;
    File selecteCertFileLoc;
    File selecteCertFile;
    
    public FileChooserApplet() {
        this.selectedFile = null;
        this.selectedFileLoc = null;
        this.selecteCertFileLoc = null;
        this.selecteCertFile = null;
    }
    
    public void init() {
        try {
            System.setSecurityManager(null);
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (Exception ex2) {}
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    FileChooserApplet.this.initComponents();
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void initComponents() {
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 75, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 23, 32767));
    }
    
    public void choosePDFFile() {
        JFileChooser chooser = null;
        if (this.selectedFileLoc != null) {
            chooser = new JFileChooser(this.selectedFileLoc.getParent());
        }
        else {
            chooser = new JFileChooser();
        }
        chooser.setDialogTitle("Select a PDF document to sign");
        chooser.setDialogType(0);
        chooser.setFileSelectionMode(0);
        final PDFFilter imf = new PDFFilter("pdf");
        chooser.setFileFilter(imf);
        try {
            if (chooser.showOpenDialog(this) == 0) {
                this.selectedFile = chooser.getSelectedFile();
                this.selectedFileLoc = this.selectedFile;
            }
            else {
                this.selectedFile = null;
            }
        }
        catch (Exception exp) {
            this.showErrorMsg("Error : " + exp.getMessage());
        }
    }
    
    public void chooseCertFile() {
        JFileChooser chooser = null;
        if (this.selecteCertFileLoc != null) {
            chooser = new JFileChooser(this.selecteCertFileLoc.getParent());
        }
        else {
            chooser = new JFileChooser();
        }
        chooser.setDialogTitle("Locate your soft certificate");
        chooser.setDialogType(0);
        chooser.setFileSelectionMode(0);
        final PDFFilter imf = new PDFFilter("p12");
        chooser.setFileFilter(imf);
        try {
            if (chooser.showOpenDialog(this) == 0) {
                this.selecteCertFile = chooser.getSelectedFile();
                this.selecteCertFileLoc = this.selecteCertFile;
            }
            else {
                this.selecteCertFile = null;
            }
        }
        catch (Exception exp) {
            exp.printStackTrace();
            this.showErrorMsg("Error : " + exp.getMessage());
        }
    }
    
    public String getFile() {
        if (this.selectedFile != null) {
            return this.selectedFile.getAbsolutePath();
        }
        return null;
    }
    
    public String getFileName() {
        if (this.selectedFile != null) {
            return this.selectedFile.getName();
        }
        return null;
    }
    
    public String getCertFile() {
        if (this.selecteCertFile != null) {
            return this.selecteCertFile.getAbsolutePath();
        }
        return null;
    }
    
    public String getCertFileName() {
        if (this.selecteCertFile != null) {
            return this.selecteCertFile.getName();
        }
        return null;
    }
    
    public boolean isFileExisting(final String fullpath) {
        return fullpath != null && new File(fullpath).isFile() && new File(fullpath).exists();
    }
    
    private void showErrorMsg(final String msg) {
        JOptionPane.showMessageDialog(this, msg, "Exception", 0);
    }
    
    public void clear() {
        this.selectedFile = null;
        this.selecteCertFile = null;
    }
}
