import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.FileDialog;
import java.io.File;
import java.awt.Insets;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class RecordingFrame extends Frame implements WindowListener, ActionListener
{
    boolean recording;
    TextField fnameField;
    Button browseButton;
    Label statusLabel;
    Button recordButton;
    Button nextButton;
    Button closeButton;
    VncViewer viewer;
    
    public static boolean checkSecurity() {
        final SecurityManager security = System.getSecurityManager();
        if (security != null) {
            try {
                security.checkPropertyAccess("user.dir");
                security.checkPropertyAccess("file.separator");
                System.getProperty("user.dir");
            }
            catch (Throwable e) {
                System.out.println("SecurityManager restricts session recording.");
                return false;
            }
        }
        return true;
    }
    
    RecordingFrame(final VncViewer v) {
        super("TightVNC Session Recording");
        this.viewer = v;
        final String fname = this.nextNewFilename(System.getProperty("user.dir") + System.getProperty("file.separator") + "vncsession.fbs");
        final Panel fnamePanel = new Panel();
        final GridBagLayout fnameGridbag = new GridBagLayout();
        fnamePanel.setLayout(fnameGridbag);
        final GridBagConstraints fnameConstraints = new GridBagConstraints();
        fnameConstraints.gridwidth = -1;
        fnameConstraints.fill = 1;
        fnameConstraints.weightx = 4.0;
        fnameGridbag.setConstraints(this.fnameField = new TextField(fname, 64), fnameConstraints);
        fnamePanel.add(this.fnameField);
        this.fnameField.addActionListener(this);
        fnameConstraints.gridwidth = 0;
        fnameConstraints.weightx = 1.0;
        fnameGridbag.setConstraints(this.browseButton = new Button("Browse"), fnameConstraints);
        fnamePanel.add(this.browseButton);
        this.browseButton.addActionListener(this);
        final GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 0;
        gbc.fill = 1;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 0, 0, 0);
        final Label helpLabel = new Label("File name to save next recorded session in:", 1);
        gridbag.setConstraints(helpLabel, gbc);
        this.add(helpLabel);
        gbc.fill = 2;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(0, 0, 0, 0);
        gridbag.setConstraints(fnamePanel, gbc);
        this.add(fnamePanel);
        gbc.fill = 1;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 0, 10, 0);
        gridbag.setConstraints(this.statusLabel = new Label("", 1), gbc);
        this.add(this.statusLabel);
        gbc.fill = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        gridbag.setConstraints(this.recordButton = new Button("Record"), gbc);
        this.add(this.recordButton);
        this.recordButton.addActionListener(this);
        gridbag.setConstraints(this.nextButton = new Button("Next file"), gbc);
        this.add(this.nextButton);
        this.nextButton.addActionListener(this);
        gridbag.setConstraints(this.closeButton = new Button("Close"), gbc);
        this.add(this.closeButton);
        this.closeButton.addActionListener(this);
        this.stopRecording();
        this.pack();
        this.addWindowListener(this);
    }
    
    protected String nextFilename(final String fname) {
        int suffixPos;
        final int len = suffixPos = fname.length();
        int suffixNum = 1;
        if (len > 4 && fname.charAt(len - 4) == '.') {
            try {
                suffixNum = Integer.parseInt(fname.substring(len - 3, len)) + 1;
                suffixPos = len - 4;
            }
            catch (NumberFormatException ex) {}
        }
        final char[] zeroes = { '0', '0', '0' };
        String suffix = String.valueOf(suffixNum);
        if (suffix.length() < 3) {
            suffix = new String(zeroes, 0, 3 - suffix.length()) + suffix;
        }
        return fname.substring(0, suffixPos) + '.' + suffix;
    }
    
    protected String nextNewFilename(final String fname) {
        String newName = fname;
        try {
            File f;
            do {
                newName = this.nextFilename(newName);
                f = new File(newName);
            } while (f.exists());
        }
        catch (Throwable e) {
            e.printStackTrace(System.err);
            System.err.flush();
        }
        return newName;
    }
    
    protected boolean browseFile() {
        final File currentFile = new File(this.fnameField.getText());
        final FileDialog fd = new FileDialog(this, "Save next session as...", 1);
        fd.setDirectory(currentFile.getParent());
        fd.setVisible(true);
        if (fd.getFile() != null) {
            String newDir = fd.getDirectory();
            final String sep = System.getProperty("file.separator");
            if (newDir.length() > 0 && !sep.equals(newDir.substring(newDir.length() - sep.length()))) {
                newDir += sep;
            }
            final String newFname = newDir + fd.getFile();
            if (newFname.equals(this.fnameField.getText())) {
                this.fnameField.setText(newFname);
                return true;
            }
        }
        return false;
    }
    
    public void startRecording() {
        this.statusLabel.setText("Status: Recording...");
        this.statusLabel.setFont(new Font("Helvetica", 1, 12));
        this.statusLabel.setForeground(Color.red);
        this.recordButton.setLabel("Stop recording");
        this.recording = true;
        this.viewer.setRecordingStatus(this.fnameField.getText());
    }
    
    public void stopRecording() {
        this.statusLabel.setText("Status: Not recording.");
        this.statusLabel.setFont(new Font("Helvetica", 0, 12));
        this.statusLabel.setForeground(Color.black);
        this.recordButton.setLabel("Record");
        this.recording = false;
        this.viewer.setRecordingStatus(null);
    }
    
    public void windowClosing(final WindowEvent evt) {
        this.setVisible(false);
    }
    
    public void windowActivated(final WindowEvent evt) {
    }
    
    public void windowDeactivated(final WindowEvent evt) {
    }
    
    public void windowOpened(final WindowEvent evt) {
    }
    
    public void windowClosed(final WindowEvent evt) {
    }
    
    public void windowIconified(final WindowEvent evt) {
    }
    
    public void windowDeiconified(final WindowEvent evt) {
    }
    
    public void actionPerformed(final ActionEvent evt) {
        if (evt.getSource() == this.browseButton) {
            if (this.browseFile() && this.recording) {
                this.startRecording();
            }
        }
        else if (evt.getSource() == this.recordButton) {
            if (!this.recording) {
                this.startRecording();
            }
            else {
                this.stopRecording();
                this.fnameField.setText(this.nextNewFilename(this.fnameField.getText()));
            }
        }
        else if (evt.getSource() == this.nextButton) {
            this.fnameField.setText(this.nextNewFilename(this.fnameField.getText()));
            if (this.recording) {
                this.startRecording();
            }
        }
        else if (evt.getSource() == this.closeButton) {
            this.setVisible(false);
        }
    }
}
