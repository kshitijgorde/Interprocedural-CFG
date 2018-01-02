// 
// Decompiled by Procyon v0.5.30
// 

package gui.dialog;

import logging.LogType;
import java.io.File;
import gui.GUIUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.JButton;
import anon.util.JAPMessages;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class FileChooserContentPane extends DialogContentPane implements IWizardSuitable
{
    private static final String MSG_CHOOSE_FILE;
    private static final String MSG_CHOOSE_DIR;
    private JTextField pathField;
    private JFileChooser chooser;
    private int m_fileSelectionMode;
    static /* synthetic */ Class class$gui$dialog$FileChooserContentPane;
    
    public FileChooserContentPane(final JAPDialog japDialog, final String s, final String s2, final int n) {
        this(japDialog, s, new Layout(""), s2, n);
    }
    
    public FileChooserContentPane(final JAPDialog japDialog, final String s, final Layout layout, final String text, final int fileSelectionMode) {
        super(japDialog, s, layout, new DialogContentPaneOptions(2));
        final JButton button = new JButton(JAPMessages.getString("bttnChoose"));
        (this.pathField = new JTextField(15)).setEditable(false);
        if (text != null) {
            this.pathField.setText(text);
        }
        (this.chooser = new JFileChooser()).setFileSelectionMode(fileSelectionMode);
        this.m_fileSelectionMode = this.chooser.getFileSelectionMode();
        this.getContentPane().add(this.pathField);
        this.getContentPane().add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final File file = FileChooserContentPane.this.getFile();
                if (file != null && file.isDirectory()) {
                    FileChooserContentPane.this.chooser.setCurrentDirectory(file);
                }
                if (GUIUtils.showMonitoredFileChooser(FileChooserContentPane.this.chooser, japDialog.getContentPane()) == 0) {
                    final File selectedFile = FileChooserContentPane.this.chooser.getSelectedFile();
                    if (selectedFile != null) {
                        FileChooserContentPane.this.pathField.setText(selectedFile.getPath());
                    }
                }
            }
        });
    }
    
    public File getFile() {
        String s = this.pathField.getText();
        if (s != null) {
            s = s.trim();
        }
        if (s.length() > 0) {
            return new File(s);
        }
        return null;
    }
    
    public CheckError[] checkYesOK() {
        String s;
        if (this.m_fileSelectionMode == 1) {
            s = JAPMessages.getString(FileChooserContentPane.MSG_CHOOSE_DIR);
        }
        else {
            s = JAPMessages.getString(FileChooserContentPane.MSG_CHOOSE_FILE);
        }
        final File file = this.getFile();
        if (file == null || (this.m_fileSelectionMode == 1 && !file.isDirectory()) || (this.m_fileSelectionMode == 0 && file.isDirectory())) {
            return new CheckError[] { new CheckError(s, LogType.GUI) };
        }
        return null;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        MSG_CHOOSE_FILE = ((FileChooserContentPane.class$gui$dialog$FileChooserContentPane == null) ? (FileChooserContentPane.class$gui$dialog$FileChooserContentPane = class$("gui.dialog.FileChooserContentPane")) : FileChooserContentPane.class$gui$dialog$FileChooserContentPane).getName() + "_errorChooseFile";
        MSG_CHOOSE_DIR = ((FileChooserContentPane.class$gui$dialog$FileChooserContentPane == null) ? (FileChooserContentPane.class$gui$dialog$FileChooserContentPane = class$("gui.dialog.FileChooserContentPane")) : FileChooserContentPane.class$gui$dialog$FileChooserContentPane).getName() + "_errorChooseDirectory";
    }
}
