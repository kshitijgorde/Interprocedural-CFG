// 
// Decompiled by Procyon v0.5.30
// 

package gui.dialog;

import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import anon.pay.PayAccount;
import anon.pay.xml.XMLErrorMessage;
import anon.infoservice.MixCascade;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import logging.LogType;
import javax.swing.JComponent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import anon.util.JAPMessages;
import anon.util.captcha.ICaptchaSender;
import javax.swing.JLabel;
import anon.util.captcha.IImageEncodedCaptcha;
import javax.swing.JTextField;
import anon.pay.IPaymentListener;

public class CaptchaContentPane extends DialogContentPane implements IWizardSuitable, IPaymentListener
{
    private static final String MSG_TITLE;
    private static final String MSG_SOLVE;
    private static final String MSG_WRONGCHARNUM;
    private static final String MSG_CAPTCHAERROR;
    private JTextField m_tfSolution;
    private byte[] m_solution;
    private IImageEncodedCaptcha m_captcha;
    private String m_beginsWith;
    private JLabel m_imageLabel;
    private Object m_syncObject;
    private ICaptchaSender m_captchaSource;
    static /* synthetic */ Class class$gui$dialog$CaptchaContentPane;
    
    public CaptchaContentPane(final JAPDialog japDialog, final DialogContentPane dialogContentPane) {
        super(japDialog, JAPMessages.getString(CaptchaContentPane.MSG_SOLVE), new Layout(JAPMessages.getString(CaptchaContentPane.MSG_TITLE), -1), new DialogContentPaneOptions(2, dialogContentPane));
        this.setDefaultButtonOperation(386);
        final JComponent contentPane = this.getContentPane();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        contentPane.setLayout(new GridBagLayout());
        gridBagConstraints.anchor = 10;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        (this.m_imageLabel = new JLabel()).setPreferredSize(new Dimension(300, 100));
        contentPane.add(this.m_imageLabel, gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        this.m_tfSolution = new JTextField();
        gridBagConstraints.fill = 2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 1.0;
        contentPane.add(this.m_tfSolution, gridBagConstraints);
    }
    
    public CheckError[] checkNo() {
        this.m_captchaSource.getNewCaptcha();
        synchronized (this.m_syncObject) {
            this.m_syncObject.notifyAll();
        }
        return null;
    }
    
    public CheckError[] checkYesOK() {
        final CheckError[] array = { null };
        if (this.m_captcha.getCharacterNumber() == this.m_tfSolution.getText().length()) {
            try {
                this.m_solution = this.m_captcha.solveCaptcha(this.m_tfSolution.getText().trim(), this.m_beginsWith.getBytes());
                Label_0120: {
                    if (this.m_solution != null) {
                        this.m_captchaSource.setCaptchaSolution(this.m_solution);
                        synchronized (this.m_syncObject) {
                            this.m_syncObject.notifyAll();
                            break Label_0120;
                        }
                    }
                    array[0] = new CheckError(JAPMessages.getString(CaptchaContentPane.MSG_CAPTCHAERROR), LogType.PAY);
                }
            }
            catch (Exception ex) {
                array[0] = new CheckError(null, LogType.PAY, ex);
            }
        }
        else {
            array[0] = new CheckError(JAPMessages.getString(CaptchaContentPane.MSG_WRONGCHARNUM, new Integer(this.m_captcha.getCharacterNumber())), LogType.PAY);
        }
        if (array[0] == null) {
            return null;
        }
        return array;
    }
    
    public CheckError[] checkCancel() {
        synchronized (this.m_syncObject) {
            this.m_syncObject.notifyAll();
        }
        return null;
    }
    
    private void setCaptcha(final IImageEncodedCaptcha captcha, final String beginsWith) {
        this.m_beginsWith = beginsWith;
        this.m_captcha = captcha;
        this.m_imageLabel.setIcon(new ImageIcon(this.m_captcha.getImage()));
        final MyDocument document = new MyDocument();
        document.setCaptcha(this.m_captcha);
        this.m_tfSolution.setDocument(document);
    }
    
    public byte[] getSolution() {
        return this.m_solution;
    }
    
    public void gotCaptcha(final ICaptchaSender captchaSource, final IImageEncodedCaptcha imageEncodedCaptcha) {
        this.getPreviousContentPane().getButtonCancel().setEnabled(true);
        this.setCaptcha(imageEncodedCaptcha, "<Don");
        this.m_captchaSource = captchaSource;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CaptchaContentPane.this.updateDialog();
                CaptchaContentPane.this.m_tfSolution.requestFocus();
            }
        });
        synchronized (this.m_syncObject = new Object()) {
            try {
                this.m_syncObject.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean isSkippedAsNextContentPane() {
        return true;
    }
    
    public boolean isSkippedAsPreviousContentPane() {
        return true;
    }
    
    public int accountCertRequested(final MixCascade mixCascade) {
        return 0;
    }
    
    public void accountError(final XMLErrorMessage xmlErrorMessage, final boolean b) {
    }
    
    public void accountActivated(final PayAccount payAccount) {
    }
    
    public void accountRemoved(final PayAccount payAccount) {
    }
    
    public void accountAdded(final PayAccount payAccount) {
    }
    
    public void creditChanged(final PayAccount payAccount) {
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
        MSG_TITLE = ((CaptchaContentPane.class$gui$dialog$CaptchaContentPane == null) ? (CaptchaContentPane.class$gui$dialog$CaptchaContentPane = class$("gui.dialog.CaptchaContentPane")) : CaptchaContentPane.class$gui$dialog$CaptchaContentPane).getName() + "_title";
        MSG_SOLVE = ((CaptchaContentPane.class$gui$dialog$CaptchaContentPane == null) ? (CaptchaContentPane.class$gui$dialog$CaptchaContentPane = class$("gui.dialog.CaptchaContentPane")) : CaptchaContentPane.class$gui$dialog$CaptchaContentPane).getName() + "_solve";
        MSG_WRONGCHARNUM = ((CaptchaContentPane.class$gui$dialog$CaptchaContentPane == null) ? (CaptchaContentPane.class$gui$dialog$CaptchaContentPane = class$("gui.dialog.CaptchaContentPane")) : CaptchaContentPane.class$gui$dialog$CaptchaContentPane).getName() + "_wrongcharnum";
        MSG_CAPTCHAERROR = ((CaptchaContentPane.class$gui$dialog$CaptchaContentPane == null) ? (CaptchaContentPane.class$gui$dialog$CaptchaContentPane = class$("gui.dialog.CaptchaContentPane")) : CaptchaContentPane.class$gui$dialog$CaptchaContentPane).getName() + "_captchaerror";
    }
    
    private class MyDocument extends PlainDocument
    {
        private IImageEncodedCaptcha m_captcha;
        
        public void setCaptcha(final IImageEncodedCaptcha captcha) {
            this.m_captcha = captcha;
        }
        
        public void insertString(final int n, final String s, final AttributeSet set) throws BadLocationException {
            if (this.getLength() + s.length() <= this.m_captcha.getCharacterNumber()) {
                boolean b = false;
                for (int n2 = 0; n2 < s.length() && !b; ++n2) {
                    if (this.m_captcha.getCharacterSet().indexOf(s.toUpperCase().substring(n2, n2 + 1)) < 0) {
                        b = true;
                    }
                }
                if (!b) {
                    super.insertString(n, s.toUpperCase(), set);
                }
            }
        }
    }
}
