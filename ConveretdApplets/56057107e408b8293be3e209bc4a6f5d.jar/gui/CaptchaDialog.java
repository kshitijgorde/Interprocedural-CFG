// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import logging.LogHolder;
import logging.LogType;
import java.awt.event.ActionEvent;
import java.awt.Container;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import javax.swing.text.Document;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import anon.util.JAPMessages;
import java.awt.Window;
import anon.util.captcha.IImageEncodedCaptcha;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import gui.dialog.JAPDialog;

public class CaptchaDialog extends JAPDialog implements ActionListener
{
    private static final String MSG_TITLE;
    private static final String MSG_SOLVE;
    private static final String MSG_OK;
    private static final String MSG_CANCEL;
    private static final String MSG_WRONGCHARNUM;
    private JTextField m_tfSolution;
    private JButton m_btnOk;
    private JButton m_btnCancel;
    private byte[] m_solution;
    private IImageEncodedCaptcha m_captcha;
    private String m_beginsWith;
    static /* synthetic */ Class class$gui$CaptchaDialog;
    
    public CaptchaDialog(final IImageEncodedCaptcha captcha, final String beginsWith, final Window window) {
        super(window, JAPMessages.getString(CaptchaDialog.MSG_TITLE), true);
        this.m_captcha = captcha;
        this.m_beginsWith = beginsWith;
        final Container contentPane = this.getContentPane();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        contentPane.setLayout(new GridBagLayout());
        gridBagConstraints.anchor = 18;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        contentPane.add(new JLabel(new ImageIcon(captcha.getImage())), gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        contentPane.add(new JLabel("<html>" + JAPMessages.getString(CaptchaDialog.MSG_SOLVE) + "</html>"), gridBagConstraints);
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridy;
        contentPane.add(this.m_tfSolution = new JTextField() {
            protected Document createDefaultModel() {
                return new PlainDocument() {
                    public void insertString(final int n, final String s, final AttributeSet set) throws BadLocationException {
                        if (this.getLength() + s.length() <= captcha.getCharacterNumber()) {
                            boolean b = false;
                            for (int n2 = 0; n2 < s.length() && !b; ++n2) {
                                if (captcha.getCharacterSet().indexOf(s.toUpperCase().substring(n2, n2 + 1)) < 0) {
                                    b = true;
                                }
                            }
                            if (!b) {
                                super.insertString(n, s.toUpperCase(), set);
                            }
                        }
                    }
                };
            }
        }, gridBagConstraints);
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridy;
        gridBagConstraints.gridwidth = 1;
        (this.m_btnCancel = new JButton(JAPMessages.getString(CaptchaDialog.MSG_CANCEL))).addActionListener(this);
        contentPane.add(this.m_btnCancel, gridBagConstraints);
        final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
        ++gridBagConstraints5.gridx;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 1.0;
        (this.m_btnOk = new JButton(JAPMessages.getString(CaptchaDialog.MSG_OK))).addActionListener(this);
        contentPane.add(this.m_btnOk, gridBagConstraints);
        this.pack();
        this.setLocationCenteredOnOwner();
        this.setVisible(true);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.m_btnCancel) {
            this.dispose();
        }
        else if (source == this.m_btnOk) {
            if (this.m_captcha.getCharacterNumber() == this.m_tfSolution.getText().length()) {
                try {
                    this.m_solution = this.m_captcha.solveCaptcha(this.m_tfSolution.getText().trim(), this.m_beginsWith.getBytes());
                    this.dispose();
                }
                catch (Exception ex) {
                    LogHolder.log(2, LogType.MISC, "Error solving captcha!");
                }
            }
            else {
                JAPDialog.showErrorDialog(this, JAPMessages.getString(CaptchaDialog.MSG_WRONGCHARNUM) + " " + this.m_captcha.getCharacterNumber() + ".", LogType.MISC);
            }
        }
    }
    
    public byte[] getSolution() {
        return this.m_solution;
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
        MSG_TITLE = ((CaptchaDialog.class$gui$CaptchaDialog == null) ? (CaptchaDialog.class$gui$CaptchaDialog = class$("gui.CaptchaDialog")) : CaptchaDialog.class$gui$CaptchaDialog).getName() + "_title";
        MSG_SOLVE = ((CaptchaDialog.class$gui$CaptchaDialog == null) ? (CaptchaDialog.class$gui$CaptchaDialog = class$("gui.CaptchaDialog")) : CaptchaDialog.class$gui$CaptchaDialog).getName() + "_solve";
        MSG_OK = ((CaptchaDialog.class$gui$CaptchaDialog == null) ? (CaptchaDialog.class$gui$CaptchaDialog = class$("gui.CaptchaDialog")) : CaptchaDialog.class$gui$CaptchaDialog).getName() + "_ok";
        MSG_CANCEL = ((CaptchaDialog.class$gui$CaptchaDialog == null) ? (CaptchaDialog.class$gui$CaptchaDialog = class$("gui.CaptchaDialog")) : CaptchaDialog.class$gui$CaptchaDialog).getName() + "_cancel";
        MSG_WRONGCHARNUM = ((CaptchaDialog.class$gui$CaptchaDialog == null) ? (CaptchaDialog.class$gui$CaptchaDialog = class$("gui.CaptchaDialog")) : CaptchaDialog.class$gui$CaptchaDialog).getName() + "_wrongcharnum";
    }
}
