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
    final int scaleCursorIndex = 8;
    final int shareDesktopIndex = 9;
    Label[] labels;
    Choice[] choices;
    Button closeButton;
    VncViewer viewer;
    int preferredEncoding;
    int compressLevel;
    int jpegQuality;
    boolean useCopyRect;
    boolean requestCursorUpdates;
    boolean ignoreCursorUpdates;
    boolean eightBitColors;
    boolean reverseMouseButtons2And3;
    boolean shareDesktop;
    boolean viewOnly;
    int scaleCursor;
    boolean autoScale;
    int scalingFactor;
    
    OptionsFrame(final VncViewer viewer) {
        super("TightVNC Options");
        this.labels = new Label[OptionsFrame.names.length];
        this.choices = new Choice[OptionsFrame.names.length];
        this.viewer = viewer;
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 1;
        for (int i = 0; i < OptionsFrame.names.length; ++i) {
            this.labels[i] = new Label(OptionsFrame.names[i]);
            gridBagConstraints.gridwidth = 1;
            layout.setConstraints(this.labels[i], gridBagConstraints);
            this.add(this.labels[i]);
            this.choices[i] = new Choice();
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.choices[i], gridBagConstraints);
            this.add(this.choices[i]);
            this.choices[i].addItemListener(this);
            for (int j = 0; j < OptionsFrame.values[i].length; ++j) {
                this.choices[i].addItem(OptionsFrame.values[i][j]);
            }
        }
        this.closeButton = new Button("Close");
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.closeButton, gridBagConstraints);
        this.add(this.closeButton);
        this.closeButton.addActionListener(this);
        this.pack();
        this.addWindowListener(this);
        this.choices[0].select("Auto");
        this.choices[1].select("Default");
        this.choices[2].select("6");
        this.choices[3].select("Enable");
        this.choices[4].select("Yes");
        this.choices[5].select("No");
        this.choices[6].select("Normal");
        this.choices[7].select("No");
        this.choices[8].select("No");
        this.choices[9].select("Yes");
        for (int k = 0; k < OptionsFrame.names.length; ++k) {
            final String parameter = this.viewer.readParameter(OptionsFrame.names[k], false);
            if (parameter != null) {
                for (int l = 0; l < OptionsFrame.values[k].length; ++l) {
                    if (parameter.equalsIgnoreCase(OptionsFrame.values[k][l])) {
                        this.choices[k].select(l);
                    }
                }
            }
        }
        this.autoScale = false;
        this.scalingFactor = 100;
        String s = this.viewer.readParameter("Scaling Factor", false);
        if (s != null) {
            if (s.equalsIgnoreCase("Auto")) {
                this.autoScale = true;
            }
            else {
                if (s.charAt(s.length() - 1) == '%') {
                    s = s.substring(0, s.length() - 1);
                }
                try {
                    this.scalingFactor = Integer.parseInt(s);
                }
                catch (NumberFormatException ex) {
                    this.scalingFactor = 100;
                }
                if (this.scalingFactor < 1) {
                    this.scalingFactor = 1;
                }
                else if (this.scalingFactor > 1000) {
                    this.scalingFactor = 1000;
                }
            }
        }
        this.setEncodings();
        this.setColorFormat();
        this.setOtherOptions();
    }
    
    void disableShareDesktop() {
        this.labels[9].setEnabled(false);
        this.choices[9].setEnabled(false);
    }
    
    void setEncodings() {
        this.useCopyRect = this.choices[4].getSelectedItem().equals("Yes");
        this.preferredEncoding = 0;
        boolean b = false;
        if (this.choices[0].getSelectedItem().equals("RRE")) {
            this.preferredEncoding = 2;
        }
        else if (this.choices[0].getSelectedItem().equals("CoRRE")) {
            this.preferredEncoding = 4;
        }
        else if (this.choices[0].getSelectedItem().equals("Hextile")) {
            this.preferredEncoding = 5;
        }
        else if (this.choices[0].getSelectedItem().equals("ZRLE")) {
            this.preferredEncoding = 16;
        }
        else if (this.choices[0].getSelectedItem().equals("Zlib")) {
            this.preferredEncoding = 6;
            b = true;
        }
        else if (this.choices[0].getSelectedItem().equals("Tight")) {
            this.preferredEncoding = 7;
            b = true;
        }
        else if (this.choices[0].getSelectedItem().equals("Auto")) {
            this.preferredEncoding = -1;
        }
        try {
            this.compressLevel = Integer.parseInt(this.choices[1].getSelectedItem());
        }
        catch (NumberFormatException ex) {
            this.compressLevel = -1;
        }
        if (this.compressLevel < 1 || this.compressLevel > 9) {
            this.compressLevel = -1;
        }
        this.labels[1].setEnabled(b);
        this.choices[1].setEnabled(b);
        try {
            this.jpegQuality = Integer.parseInt(this.choices[2].getSelectedItem());
        }
        catch (NumberFormatException ex2) {
            this.jpegQuality = -1;
        }
        if (this.jpegQuality < 0 || this.jpegQuality > 9) {
            this.jpegQuality = -1;
        }
        this.requestCursorUpdates = !this.choices[3].getSelectedItem().equals("Disable");
        if (this.requestCursorUpdates) {
            this.ignoreCursorUpdates = this.choices[3].getSelectedItem().equals("Ignore");
        }
        this.viewer.setEncodings();
    }
    
    void setColorFormat() {
        this.eightBitColors = this.choices[5].getSelectedItem().equals("Yes");
        final boolean b = !this.eightBitColors;
        this.labels[2].setEnabled(b);
        this.choices[2].setEnabled(b);
    }
    
    void setOtherOptions() {
        this.reverseMouseButtons2And3 = this.choices[6].getSelectedItem().equals("Reversed");
        this.viewOnly = this.choices[7].getSelectedItem().equals("Yes");
        if (this.viewer.vc != null) {
            this.viewer.vc.enableInput(!this.viewOnly);
        }
        this.shareDesktop = this.choices[9].getSelectedItem().equals("Yes");
        String s = this.choices[8].getSelectedItem();
        if (s.endsWith("%")) {
            s = s.substring(0, s.length() - 1);
        }
        try {
            this.scaleCursor = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            this.scaleCursor = 0;
        }
        if (this.scaleCursor < 10 || this.scaleCursor > 500) {
            this.scaleCursor = 0;
        }
        if (this.requestCursorUpdates && !this.ignoreCursorUpdates && !this.viewOnly) {
            this.labels[8].setEnabled(true);
            this.choices[8].setEnabled(true);
        }
        else {
            this.labels[8].setEnabled(false);
            this.choices[8].setEnabled(false);
        }
        if (this.viewer.vc != null) {
            this.viewer.vc.createSoftCursor();
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final Object source = itemEvent.getSource();
        if (source == this.choices[0] || source == this.choices[1] || source == this.choices[2] || source == this.choices[3] || source == this.choices[4]) {
            this.setEncodings();
            if (source == this.choices[3]) {
                this.setOtherOptions();
            }
        }
        else if (source == this.choices[5]) {
            this.setColorFormat();
        }
        else if (source == this.choices[6] || source == this.choices[9] || source == this.choices[7] || source == this.choices[8]) {
            this.setOtherOptions();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.closeButton) {
            this.setVisible(false);
        }
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
    
    static {
        OptionsFrame.names = new String[] { "Encoding", "Compression level", "JPEG image quality", "Cursor shape updates", "Use CopyRect", "Restricted colors", "Mouse buttons 2 and 3", "View only", "Scale remote cursor", "Share desktop" };
        OptionsFrame.values = new String[][] { { "Auto", "Raw", "RRE", "CoRRE", "Hextile", "Zlib", "Tight", "ZRLE" }, { "Default", "1", "2", "3", "4", "5", "6", "7", "8", "9" }, { "JPEG off", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }, { "Enable", "Ignore", "Disable" }, { "Yes", "No" }, { "Yes", "No" }, { "Normal", "Reversed" }, { "Yes", "No" }, { "No", "50%", "75%", "125%", "150%" }, { "Yes", "No" } };
    }
}
