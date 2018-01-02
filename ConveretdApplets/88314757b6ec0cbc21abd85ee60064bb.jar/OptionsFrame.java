import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Label;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class OptionsFrame extends Frame implements WindowListener, ActionListener, ItemListener
{
    static String[] names;
    static String[][] values;
    final int encodingIndex = 0;
    final int compressLevelIndex = 1;
    final int jpegQualityIndex = 2;
    final int cursorUpdatesIndex = 3;
    final int useCopyRectIndex = 4;
    final int eightBitColorsIndex = 5;
    final int mouseButtonIndex = 6;
    final int viewOnlyIndex = 7;
    final int shareDesktopIndex = 8;
    Label[] labels;
    Choice[] choices;
    Button closeButton;
    VncViewer viewer;
    int[] encodings;
    int nEncodings;
    int compressLevel;
    int jpegQuality;
    boolean eightBitColors;
    boolean requestCursorUpdates;
    boolean ignoreCursorUpdates;
    boolean reverseMouseButtons2And3;
    boolean shareDesktop;
    boolean viewOnly;
    
    OptionsFrame(final VncViewer v) {
        super("TightVNC Options");
        this.labels = new Label[OptionsFrame.names.length];
        this.choices = new Choice[OptionsFrame.names.length];
        this.encodings = new int[20];
        this.viewer = v;
        final GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = 1;
        for (int i = 0; i < OptionsFrame.names.length; ++i) {
            this.labels[i] = new Label(OptionsFrame.names[i]);
            gbc.gridwidth = 1;
            gridbag.setConstraints(this.labels[i], gbc);
            this.add(this.labels[i]);
            this.choices[i] = new Choice();
            gbc.gridwidth = 0;
            gridbag.setConstraints(this.choices[i], gbc);
            this.add(this.choices[i]);
            this.choices[i].addItemListener(this);
            for (int j = 0; j < OptionsFrame.values[i].length; ++j) {
                this.choices[i].addItem(OptionsFrame.values[i][j]);
            }
        }
        this.closeButton = new Button("Close");
        gbc.gridwidth = 0;
        gridbag.setConstraints(this.closeButton, gbc);
        this.add(this.closeButton);
        this.closeButton.addActionListener(this);
        this.pack();
        this.addWindowListener(this);
        this.choices[0].select("Tight");
        this.choices[1].select("Default");
        this.choices[2].select("6");
        this.choices[3].select("Enable");
        this.choices[4].select("Yes");
        this.choices[5].select("No");
        this.choices[6].select("Normal");
        this.choices[7].select("No");
        this.choices[8].select("Yes");
        for (int i = 0; i < OptionsFrame.names.length; ++i) {
            final String s = this.viewer.readParameter(OptionsFrame.names[i], false);
            if (s != null) {
                for (int k = 0; k < OptionsFrame.values[i].length; ++k) {
                    if (s.equalsIgnoreCase(OptionsFrame.values[i][k])) {
                        this.choices[i].select(k);
                    }
                }
            }
        }
        this.setEncodings();
        this.setColorFormat();
        this.setOtherOptions();
    }
    
    void disableShareDesktop() {
        this.labels[8].setEnabled(false);
        this.choices[8].setEnabled(false);
    }
    
    void setEncodings() {
        this.nEncodings = 0;
        if (this.choices[4].getSelectedItem().equals("Yes")) {
            this.encodings[this.nEncodings++] = 1;
        }
        int preferredEncoding = 0;
        boolean enableCompressLevel = false;
        if (this.choices[0].getSelectedItem().equals("RRE")) {
            preferredEncoding = 2;
        }
        else if (this.choices[0].getSelectedItem().equals("CoRRE")) {
            preferredEncoding = 4;
        }
        else if (this.choices[0].getSelectedItem().equals("Hextile")) {
            preferredEncoding = 5;
        }
        else if (this.choices[0].getSelectedItem().equals("Zlib")) {
            preferredEncoding = 6;
            enableCompressLevel = true;
        }
        else if (this.choices[0].getSelectedItem().equals("Tight")) {
            preferredEncoding = 7;
            enableCompressLevel = true;
        }
        if ((this.encodings[this.nEncodings++] = preferredEncoding) != 5) {
            this.encodings[this.nEncodings++] = 5;
        }
        if (preferredEncoding != 7) {
            this.encodings[this.nEncodings++] = 7;
        }
        if (preferredEncoding != 6) {
            this.encodings[this.nEncodings++] = 6;
        }
        if (preferredEncoding != 4) {
            this.encodings[this.nEncodings++] = 4;
        }
        if (preferredEncoding != 2) {
            this.encodings[this.nEncodings++] = 2;
        }
        if (enableCompressLevel) {
            this.labels[1].setEnabled(true);
            this.choices[1].setEnabled(true);
            try {
                this.compressLevel = Integer.parseInt(this.choices[1].getSelectedItem());
            }
            catch (NumberFormatException e) {
                this.compressLevel = -1;
            }
            if (this.compressLevel >= 1 && this.compressLevel <= 9) {
                this.encodings[this.nEncodings++] = -256 + this.compressLevel;
            }
            else {
                this.compressLevel = -1;
            }
        }
        else {
            this.labels[1].setEnabled(false);
            this.choices[1].setEnabled(false);
        }
        if (preferredEncoding == 7 && !this.eightBitColors) {
            this.labels[2].setEnabled(true);
            this.choices[2].setEnabled(true);
            try {
                this.jpegQuality = Integer.parseInt(this.choices[2].getSelectedItem());
            }
            catch (NumberFormatException e) {
                this.jpegQuality = -1;
            }
            if (this.jpegQuality >= 0 && this.jpegQuality <= 9) {
                this.encodings[this.nEncodings++] = -32 + this.jpegQuality;
            }
            else {
                this.jpegQuality = -1;
            }
        }
        else {
            this.labels[2].setEnabled(false);
            this.choices[2].setEnabled(false);
        }
        this.requestCursorUpdates = !this.choices[3].getSelectedItem().equals("Disable");
        if (this.requestCursorUpdates) {
            this.encodings[this.nEncodings++] = -240;
            this.encodings[this.nEncodings++] = -239;
            if (!(this.ignoreCursorUpdates = this.choices[3].getSelectedItem().equals("Ignore"))) {
                this.encodings[this.nEncodings++] = -232;
            }
        }
        this.encodings[this.nEncodings++] = -224;
        this.encodings[this.nEncodings++] = -223;
        this.viewer.setEncodings();
    }
    
    void setColorFormat() {
        this.eightBitColors = this.choices[5].getSelectedItem().equals("Yes");
        final boolean enableJPEG = !this.eightBitColors && this.choices[0].getSelectedItem().equals("Tight");
        this.labels[2].setEnabled(enableJPEG);
        this.choices[2].setEnabled(enableJPEG);
    }
    
    void setOtherOptions() {
        this.reverseMouseButtons2And3 = this.choices[6].getSelectedItem().equals("Reversed");
        this.viewOnly = this.choices[7].getSelectedItem().equals("Yes");
        if (this.viewer.vc != null) {
            this.viewer.vc.enableInput(!this.viewOnly);
        }
        this.shareDesktop = this.choices[8].getSelectedItem().equals("Yes");
    }
    
    public void itemStateChanged(final ItemEvent evt) {
        final Object source = evt.getSource();
        if (source == this.choices[0] || source == this.choices[1] || source == this.choices[2] || source == this.choices[3] || source == this.choices[4]) {
            this.setEncodings();
        }
        else if (source == this.choices[5]) {
            this.setColorFormat();
        }
        else if (source == this.choices[6] || source == this.choices[8] || source == this.choices[7]) {
            this.setOtherOptions();
        }
    }
    
    public void actionPerformed(final ActionEvent evt) {
        if (evt.getSource() == this.closeButton) {
            this.setVisible(false);
        }
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
    
    static {
        OptionsFrame.names = new String[] { "Encoding", "Compression level", "JPEG image quality", "Cursor shape updates", "Use CopyRect", "Restricted colors", "Mouse buttons 2 and 3", "View only", "Share desktop" };
        OptionsFrame.values = new String[][] { { "Raw", "RRE", "CoRRE", "Hextile", "Zlib", "Tight" }, { "Default", "1", "2", "3", "4", "5", "6", "7", "8", "9" }, { "JPEG off", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }, { "Enable", "Ignore", "Disable" }, { "Yes", "No" }, { "Yes", "No" }, { "Normal", "Reversed" }, { "Yes", "No" }, { "Yes", "No" } };
    }
}
