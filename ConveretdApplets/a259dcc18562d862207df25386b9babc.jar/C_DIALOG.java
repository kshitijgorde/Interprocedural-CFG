import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.awt.Button;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class C_DIALOG extends Dialog implements WindowListener, ActionListener
{
    private TextArea info;
    private TextField edit;
    private Button buttonOK;
    private Button buttonCancel;
    private int infoTop;
    private int infoLeft;
    private dataSample output;
    private int dialogType;
    public static final int DIALOG_ERROR = 0;
    public static final int DIALOG_INPUT = 1;
    public static final int DIALOG_QUERY = 2;
    public static final int DIALOG_INFO = 3;
    
    public C_DIALOG(final Frame frame, final String text) {
        super(frame, true);
        this.info = new TextArea("", 15, 50, 3);
        this.edit = new TextField("", 32);
        this.buttonOK = new Button("OK");
        this.buttonCancel = new Button("Cancel");
        this.SetUpFields();
        this.dialogType = 0;
        this.setTitle("Message:");
        this.info.setText(text);
        this.buttonOK.setLabel("OK");
    }
    
    public C_DIALOG(final Frame frame, final String text, final dataSample output) {
        super(frame, true);
        this.info = new TextArea("", 15, 50, 3);
        this.edit = new TextField("", 32);
        this.buttonOK = new Button("OK");
        this.buttonCancel = new Button("Cancel");
        this.SetUpFields();
        this.dialogType = 1;
        this.output = output;
        this.setTitle("Dialog Input:");
        this.info.setText(text);
        this.edit.setText(this.output.str);
        this.buttonOK.setLabel("OK");
    }
    
    public C_DIALOG(final Frame frame, final String text, final String label, final dataSample output) {
        super(frame, true);
        this.info = new TextArea("", 15, 50, 3);
        this.edit = new TextField("", 32);
        this.buttonOK = new Button("OK");
        this.buttonCancel = new Button("Cancel");
        this.SetUpFields();
        this.dialogType = 2;
        this.output = output;
        this.setTitle("Dialog query:");
        this.info.setText(text);
        this.buttonOK.setLabel(label);
    }
    
    public void SetUpFields() {
        this.addWindowListener(this);
        this.setResizable(true);
        this.setModal(true);
        this.setLayout(null);
        this.add(this.edit);
        this.add(this.buttonOK);
        this.buttonOK.addActionListener(this);
        this.add(this.buttonCancel);
        this.buttonCancel.addActionListener(this);
        this.add(this.info);
        this.info.setEditable(false);
        this.edit.setEditable(true);
        this.setForeground(Color.black);
        this.info.setForeground(Color.black);
        this.edit.setForeground(Color.black);
        this.buttonOK.setForeground(Color.black);
        this.buttonCancel.setForeground(Color.black);
        this.setBackground(C_TOOL.gray);
        this.info.setBackground(C_TOOL.gray);
        this.edit.setBackground(C_TOOL.gray);
        this.buttonOK.setBackground(C_TOOL.gray);
        this.buttonCancel.setBackground(C_TOOL.gray);
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.QuitC_DIALOG();
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
        this.SetDialogConstants();
    }
    
    public void SetDialogConstants() {
        final FontMetrics fontMetrics = this.getFontMetrics(C_TOOL.fontNormal);
        final int fontHeight = C_TOOL.fontHeight;
        final int fontWidth = C_TOOL.fontWidth;
        final int boxHeight = C_TOOL.boxHeight;
        this.info.setFont(C_TOOL.fontNormal);
        this.edit.setFont(C_TOOL.fontNormal);
        this.buttonOK.setFont(C_TOOL.fontNormal);
        this.buttonCancel.setFont(C_TOOL.fontNormal);
        final int n = C_TOOL.windowLeft + fontWidth;
        final int n2 = fontWidth * 30;
        final int n3 = fontMetrics.stringWidth(this.info.getText()) / (n2 - fontWidth * 8);
        final int n4 = C_TOOL.windowTopNoMenu + 2;
        final int n5 = (n3 + 2) * fontHeight;
        this.info.setBounds(n, n4, n2, n5);
        this.info.setVisible(true);
        final int n6 = fontWidth * 8;
        int n8;
        if (this.dialogType == 1) {
            final int n7 = n4 + n5 + 5;
            this.edit.setBounds(n, n7, n2, boxHeight);
            this.edit.setEditable(true);
            this.edit.setVisible(true);
            n8 = n7 + (int)(boxHeight * 1.5);
        }
        else {
            this.edit.setVisible(false);
            n8 = n4 + n5 + fontHeight / 2;
        }
        int n9;
        if (this.dialogType == 0) {
            n9 = n + n2 / 2 - n6 / 2;
            this.buttonCancel.setVisible(false);
        }
        else {
            n9 = n;
            this.buttonCancel.setBounds(n + n2 - n6, n8, n6, boxHeight);
            this.buttonCancel.setVisible(true);
        }
        this.buttonOK.setVisible(true);
        this.buttonOK.setBounds(n9, n8, n6, boxHeight);
        final int n10 = n2 + C_TOOL.windowLeft + C_TOOL.windowRight + 2 * fontWidth;
        final int n11 = n8 + boxHeight + C_TOOL.windowBottom + fontHeight;
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - n10 / 2, screenSize.height / 2 - n11 / 2, n10, n11);
        this.validate();
        this.edit.requestFocus();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals(this.buttonOK.getLabel())) {
            if (this.dialogType == 1) {
                this.output.str = this.edit.getText();
            }
            else if (this.dialogType == 2) {
                this.output.flag = true;
            }
        }
        this.QuitC_DIALOG();
    }
    
    public void QuitC_DIALOG() {
        this.dispose();
    }
}
