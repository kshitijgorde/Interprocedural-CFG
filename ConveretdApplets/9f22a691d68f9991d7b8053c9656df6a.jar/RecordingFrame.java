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
        final SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            try {
                securityManager.checkPropertyAccess("user.dir");
                securityManager.checkPropertyAccess("file.separator");
                System.getProperty("user.dir");
            }
            catch (SecurityException ex) {
                System.out.println("SecurityManager restricts session recording.");
                return false;
            }
        }
        return true;
    }
    
    RecordingFrame(final VncViewer viewer) {
        super("TightVNC Session Recording");
        this.viewer = viewer;
        final String nextNewFilename = this.nextNewFilename(System.getProperty("user.dir") + System.getProperty("file.separator") + "vncsession.fbs");
        final Panel panel = new Panel();
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 4.0;
        layout.setConstraints(this.fnameField = new TextField(nextNewFilename, 64), gridBagConstraints);
        panel.add(this.fnameField);
        this.fnameField.addActionListener(this);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(this.browseButton = new Button("Browse"), gridBagConstraints);
        panel.add(this.browseButton);
        this.browseButton.addActionListener(this);
        final GridBagLayout layout2 = new GridBagLayout();
        this.setLayout(layout2);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.fill = 1;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.insets = new Insets(10, 0, 0, 0);
        final Label label = new Label("File name to save next recorded session in:", 1);
        layout2.setConstraints(label, gridBagConstraints2);
        this.add(label);
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
        layout2.setConstraints(panel, gridBagConstraints2);
        this.add(panel);
        gridBagConstraints2.fill = 1;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.insets = new Insets(10, 0, 10, 0);
        layout2.setConstraints(this.statusLabel = new Label("", 1), gridBagConstraints2);
        this.add(this.statusLabel);
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
        layout2.setConstraints(this.recordButton = new Button("Record"), gridBagConstraints2);
        this.add(this.recordButton);
        this.recordButton.addActionListener(this);
        layout2.setConstraints(this.nextButton = new Button("Next file"), gridBagConstraints2);
        this.add(this.nextButton);
        this.nextButton.addActionListener(this);
        layout2.setConstraints(this.closeButton = new Button("Close"), gridBagConstraints2);
        this.add(this.closeButton);
        this.closeButton.addActionListener(this);
        this.stopRecording();
        this.pack();
        this.addWindowListener(this);
    }
    
    protected String nextFilename(final String s) {
        int length;
        final int n = length = s.length();
        int n2 = 1;
        if (n > 4 && s.charAt(n - 4) == '.') {
            try {
                n2 = Integer.parseInt(s.substring(n - 3, n)) + 1;
                length = n - 4;
            }
            catch (NumberFormatException ex) {}
        }
        final char[] array = { '0', '0', '0' };
        String s2 = String.valueOf(n2);
        if (s2.length() < 3) {
            s2 = new String(array, 0, 3 - s2.length()) + s2;
        }
        return s.substring(0, length) + '.' + s2;
    }
    
    protected String nextNewFilename(final String s) {
        String nextFilename = s;
        try {
            do {
                nextFilename = this.nextFilename(nextFilename);
            } while (new File(nextFilename).exists());
        }
        catch (SecurityException ex) {}
        return nextFilename;
    }
    
    protected boolean browseFile() {
        final File file = new File(this.fnameField.getText());
        final FileDialog fileDialog = new FileDialog(this, "Save next session as...", 1);
        fileDialog.setDirectory(file.getParent());
        fileDialog.setVisible(true);
        if (fileDialog.getFile() != null) {
            String s = fileDialog.getDirectory();
            final String property = System.getProperty("file.separator");
            if (s.length() > 0 && !property.equals(s.substring(s.length() - property.length()))) {
                s += property;
            }
            final String string = s + fileDialog.getFile();
            if (string.equals(this.fnameField.getText())) {
                this.fnameField.setText(string);
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
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.browseButton) {
            if (this.browseFile() && this.recording) {
                this.startRecording();
            }
        }
        else if (actionEvent.getSource() == this.recordButton) {
            if (!this.recording) {
                this.startRecording();
            }
            else {
                this.stopRecording();
                this.fnameField.setText(this.nextNewFilename(this.fnameField.getText()));
            }
        }
        else if (actionEvent.getSource() == this.nextButton) {
            this.fnameField.setText(this.nextNewFilename(this.fnameField.getText()));
            if (this.recording) {
                this.startRecording();
            }
        }
        else if (actionEvent.getSource() == this.closeButton) {
            this.setVisible(false);
        }
    }
}
