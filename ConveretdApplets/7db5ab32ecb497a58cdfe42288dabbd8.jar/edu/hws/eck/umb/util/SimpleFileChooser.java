// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb.util;

import javax.swing.JOptionPane;
import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;

public class SimpleFileChooser
{
    private JFileChooser dialog;
    
    public void setDefaultDirectory() {
        if (this.dialog != null) {
            this.dialog.setCurrentDirectory(null);
        }
    }
    
    public void setDefaultDirectory(final String s) {
        if (s == null) {
            this.setDefaultDirectory();
            return;
        }
        if (this.dialog == null) {
            this.dialog = new JFileChooser();
        }
        if (new File(s).isDirectory()) {
            this.dialog.setCurrentDirectory(new File(s));
        }
        else {
            this.dialog.setCurrentDirectory(null);
        }
    }
    
    public String getCurrentDirectory() {
        if (this.dialog == null) {
            return null;
        }
        final File currentDirectory = this.dialog.getCurrentDirectory();
        if (currentDirectory == null) {
            return null;
        }
        return currentDirectory.getAbsolutePath();
    }
    
    public void setDefaultDirectory(final File currentDirectory) {
        if (currentDirectory == null || !currentDirectory.isDirectory()) {
            this.setDefaultDirectory();
        }
        if (this.dialog == null) {
            this.dialog = new JFileChooser();
        }
        this.dialog.setCurrentDirectory(currentDirectory);
    }
    
    public File getInputFile() {
        return this.getInputFile(null, null);
    }
    
    public File getInputFile(final Component component) {
        return this.getInputFile(component, null);
    }
    
    public File getInputFile(final Component component, final String dialogTitle) {
        if (this.dialog == null) {
            this.dialog = new JFileChooser();
        }
        if (dialogTitle != null) {
            this.dialog.setDialogTitle(dialogTitle);
        }
        else {
            this.dialog.setDialogTitle(I18n.tr("simpleFileChooser.title.SelectInputFile", new Object[0]));
        }
        this.dialog.setSelectedFile(null);
        if (this.dialog.showOpenDialog(component) != 0) {
            return null;
        }
        return this.dialog.getSelectedFile();
    }
    
    public File getOutputFile() {
        return this.getOutputFile(null, null, null);
    }
    
    public File getOutputFile(final Component component) {
        return this.getOutputFile(component, null, null);
    }
    
    public File getOutputFile(final Component component, final String s) {
        return this.getOutputFile(component, s, null);
    }
    
    public File getOutputFile(final Component component, final String dialogTitle, final String s) {
        if (this.dialog == null) {
            this.dialog = new JFileChooser();
        }
        if (dialogTitle != null) {
            this.dialog.setDialogTitle(dialogTitle);
        }
        else {
            this.dialog.setDialogTitle(I18n.tr("simpleFileChooser.title.SelectOutputFile", new Object[0]));
        }
        if (s == null) {
            this.dialog.setSelectedFile(null);
        }
        else {
            this.dialog.setSelectedFile(new File(s));
        }
        while (this.dialog.showSaveDialog(component) == 0) {
            final File selectedFile = this.dialog.getSelectedFile();
            if (!selectedFile.exists()) {
                return selectedFile;
            }
            final int showConfirmDialog = JOptionPane.showConfirmDialog(component, I18n.tr("simpleFileChooser.question.ReplaceExistingFile", selectedFile.getName()), I18n.tr("simpleFileChooser.title.ConfrmSave", new Object[0]), 1, 2);
            if (showConfirmDialog == 2) {
                return null;
            }
            if (showConfirmDialog == 0) {
                return selectedFile;
            }
        }
        return null;
    }
}
