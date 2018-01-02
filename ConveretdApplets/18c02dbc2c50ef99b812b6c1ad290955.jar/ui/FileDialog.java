// 
// Decompiled by Procyon v0.5.30
// 

package ui;

import java.awt.Component;
import javax.swing.filechooser.FileSystemView;
import jsutil.JSUtil;
import jsonutil.JSONUtil;
import java.util.Vector;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import java.applet.Applet;
import main.AppletHandler;

public class FileDialog
{
    static boolean blLinux;
    private String remoteDir;
    boolean blOverwrite;
    
    public FileDialog(final AppletHandler appletHandler, final String remoteDir, final boolean blOverwrite) {
        this.remoteDir = remoteDir;
        this.blOverwrite = blOverwrite;
        this.createDialog(appletHandler);
    }
    
    public static void initDialog(final AppletHandler appletHandler) {
        new UIString(appletHandler).initStirng();
        try {
            if (FileDialog.blLinux) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.MetalLookAndFeel");
            }
            else {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            UIManager.put("FileChooser.openButtonToolTipText", UIString._OKText);
        }
        catch (UnsupportedLookAndFeelException ex) {}
        catch (IllegalAccessException ex2) {}
        catch (ClassNotFoundException ex3) {}
        catch (InstantiationException ex4) {}
    }
    
    private void checkFiles(final AppletHandler appletHandler, final JFileChooser fileChooser, final File[] array) {
        final Vector<File> selectError = new Vector<File>();
        final Vector<File> vector = new Vector<File>();
        final FileSystemView fileSystemView = fileChooser.getFileSystemView();
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null) {
                if (!array[i].exists() || fileSystemView.isFileSystemRoot(array[i])) {
                    selectError.add(array[i]);
                }
                else {
                    vector.add(array[i]);
                }
            }
        }
        if (selectError.size() > 0) {
            JSUtil.eval(appletHandler, "AppletProgram.", "onSelectError", JSONUtil.setSelectError(selectError));
        }
        if (vector.size() > 0) {
            JSUtil.eval(appletHandler, "AppletProgram.", "uploadFile", JSONUtil.setUploadFile(this.remoteDir, vector, this.blOverwrite));
        }
    }
    
    private void createDialog(final AppletHandler appletHandler) {
        final JFileChooser fileChooser = new JFileChooser(appletHandler.getLastSelectedFile());
        fileChooser.setFileHidingEnabled(true);
        fileChooser.setFileFilter(fileChooser.getAcceptAllFileFilter());
        fileChooser.setFileSelectionMode(2);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setDialogTitle(UIString._UploadText);
        if (fileChooser.showDialog(null, UIString._OKText) == 0) {
            this.checkFiles(appletHandler, fileChooser, fileChooser.getSelectedFiles());
        }
        appletHandler.setLastSelectedFile(fileChooser.getCurrentDirectory());
    }
    
    static {
        FileDialog.blLinux = System.getProperty("os.name").startsWith("Linux");
    }
}
