// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.tools.files;

import javax.swing.UIManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;

public class FileChooser
{
    private static JFileChooser fileChooser;
    private static boolean directoryMode;
    private static File historyFile;
    
    public static void setHistoryFile(final File historyFile) {
        FileChooser.historyFile = historyFile;
    }
    
    public static void directoryMode() {
        FileChooser.fileChooser.setFileSelectionMode(1);
        FileChooser.directoryMode = true;
    }
    
    public static File showSaveDialog(final Component parent, final String title) {
        return showFileDialog(parent, title, false, "", "");
    }
    
    public static File showSaveDialog(final Component parent, final String title, final String extensions, final String defaultExtension) {
        return showFileDialog(parent, title, false, extensions, defaultExtension);
    }
    
    public static File showOpenDialog(final Component parent, final String title) {
        return showFileDialog(parent, title, true, "", "");
    }
    
    public static File showOpenDialog(final Component parent, final String title, final String extensions, final String defaultExtension) {
        return showFileDialog(parent, title, true, extensions, defaultExtension);
    }
    
    private static File showFileDialog(final Component parent, final String title, final boolean open, final String extensions, final String defaultExtension) {
        FileChooser.fileChooser.setDialogTitle(title);
        FileChooser.fileChooser.resetChoosableFileFilters();
        if (FileChooser.directoryMode) {
            FileChooser.fileChooser.setFileSelectionMode(1);
            FileChooser.directoryMode = false;
        }
        else {
            FileChooser.fileChooser.setFileSelectionMode(0);
        }
        if (extensions.length() > 0) {
            SingleFileFilterAdapter defaultFileFilterAdapter = null;
            final MultiFileFilterAdapter multiFileFilterAdapter = new MultiFileFilterAdapter(extensions);
            for (final SingleFileFilterAdapter singleFileFilterAdapter : multiFileFilterAdapter.toSingleFileFilterArray()) {
                FileChooser.fileChooser.addChoosableFileFilter(singleFileFilterAdapter);
                if (singleFileFilterAdapter.getExtension().equalsIgnoreCase(defaultExtension)) {
                    defaultFileFilterAdapter = singleFileFilterAdapter;
                }
            }
            FileChooser.fileChooser.addChoosableFileFilter(multiFileFilterAdapter);
            if (defaultFileFilterAdapter != null) {
                FileChooser.fileChooser.setFileFilter(defaultFileFilterAdapter);
            }
        }
        if (open) {
            updateFileChooserLocation();
        }
        else {
            final File temp = FileChooser.fileChooser.getSelectedFile();
        }
        File result = null;
        if (open) {
            if (FileChooser.fileChooser.showOpenDialog(parent) == 0) {
                result = FileChooser.fileChooser.getSelectedFile();
            }
        }
        else {
            if (FileChooser.fileChooser.showSaveDialog(parent) == 0) {
                result = FileChooser.fileChooser.getSelectedFile();
            }
            if (result != null) {
                if (FileChooser.fileChooser.getFileFilter() instanceof SingleFileFilterAdapter) {
                    final String ext = ((SingleFileFilterAdapter)FileChooser.fileChooser.getFileFilter()).getExtension();
                    result = FileTools.setExtension(result, ext);
                    FileChooser.fileChooser.setSelectedFile(result);
                }
                if (result.exists() && FileChooser.fileChooser.getFileSelectionMode() != 1 && JOptionPane.showConfirmDialog(parent, "Are you sure you want to write over " + result.getName() + "?", "Confirm File Overwrite", 0, 3) == 1) {
                    result = null;
                }
            }
        }
        updateLastFile();
        return result;
    }
    
    private static void updateLastFile() {
        try {
            if (FileChooser.fileChooser.getSelectedFile() != null) {
                final FileWriter writer = new FileWriter(FileChooser.historyFile);
                writer.write(FileChooser.fileChooser.getSelectedFile().getAbsolutePath());
                writer.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void updateFileChooserLocation() {
        for (File file = FileChooser.fileChooser.getSelectedFile(); file != null; file = file.getParentFile()) {
            if (file.exists()) {
                FileChooser.fileChooser.setSelectedFile(file);
                return;
            }
        }
    }
    
    private static void initHistoryFile() {
        if (FileChooser.historyFile.exists()) {
            try {
                final File file = new File(FileTools.getText(FileChooser.historyFile).trim());
                FileChooser.fileChooser.setSelectedFile(file);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            final File parent = FileChooser.historyFile.getParentFile();
            parent.mkdirs();
        }
    }
    
    private FileChooser() {
        throw new UnsupportedOperationException("Utility class should not use contructor.");
    }
    
    static {
        FileChooser.fileChooser = new JFileChooser();
        FileChooser.directoryMode = false;
        FileChooser.historyFile = new File(System.getProperty("user.home") + "/.oTnip/FileChooser.properties");
        initHistoryFile();
        UIManager.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(final PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("lookAndFeel")) {
                    FileChooser.fileChooser = new JFileChooser();
                    initHistoryFile();
                }
            }
        });
    }
}
