// 
// Decompiled by Procyon v0.5.30
// 

package jap.forward;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import forward.client.ClientForwardException;
import anon.transport.address.Endpoint;
import java.io.ByteArrayInputStream;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import javax.swing.text.Document;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.JButton;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import gui.JAPHtmlMultiLineLabel;
import javax.swing.JPanel;
import gui.dialog.WorkerContentPane;
import forward.client.ForwarderInformationGrabber;
import java.util.Vector;
import gui.dialog.JAPDialog;
import anon.util.JAPMessages;
import jap.JAPController;
import logging.LogHolder;
import logging.LogType;
import anon.transport.address.IAddress;
import anon.infoservice.MixCascade;
import forward.client.ForwardConnectionDescriptor;
import anon.infoservice.ListenerInterface;
import anon.util.captcha.IImageEncodedCaptcha;
import jap.JAPModel;
import anon.client.TrustModel;
import java.awt.Font;
import java.awt.Component;

public class JAPRoutingEstablishForwardedConnectionDialog
{
    private boolean m_bForwardingSuccessful;
    private Component m_parentComponent;
    private Font m_fontSetting;
    
    public JAPRoutingEstablishForwardedConnectionDialog(final Component parentComponent) {
        this.m_bForwardingSuccessful = false;
        this.m_parentComponent = parentComponent;
        TrustModel.setCurrentTrustModel(0L);
        boolean bForwardingSuccessful = false;
        while (!bForwardingSuccessful) {
            IImageEncodedCaptcha imageEncodedCaptcha = null;
            if (!JAPModel.getInstance().getRoutingSettings().getForwardInfoService()) {
                imageEncodedCaptcha = this.showConfigClientDialogGetForwarderInfo();
            }
            if (imageEncodedCaptcha == null) {
                imageEncodedCaptcha = this.showConfigClientDialogViaMail();
            }
            if (imageEncodedCaptcha == null) {
                bForwardingSuccessful = true;
            }
            else {
                final ListenerInterface showConfigClientDialogCaptcha = this.showConfigClientDialogCaptcha(imageEncodedCaptcha);
                if (showConfigClientDialogCaptcha == null) {
                    bForwardingSuccessful = true;
                }
                else {
                    JAPModel.getInstance().getRoutingSettings().setForwarder(showConfigClientDialogCaptcha.getHost(), showConfigClientDialogCaptcha.getPort());
                    if (!this.showConfigClientDialogConnectToForwarder()) {
                        continue;
                    }
                    final ForwardConnectionDescriptor showConfigClientDialogGetOffer = this.showConfigClientDialogGetOffer();
                    if (showConfigClientDialogGetOffer == null) {
                        continue;
                    }
                    final MixCascade showConfigClientDialogStep2 = this.showConfigClientDialogStep2(showConfigClientDialogGetOffer);
                    if (showConfigClientDialogStep2 == null) {
                        continue;
                    }
                    this.m_bForwardingSuccessful = this.showConfigClientDialogAnnounceCascade(showConfigClientDialogStep2);
                    bForwardingSuccessful = this.m_bForwardingSuccessful;
                }
            }
        }
    }
    
    public JAPRoutingEstablishForwardedConnectionDialog(final Component parentComponent, final IAddress forwarderAddress) {
        this.m_bForwardingSuccessful = false;
        LogHolder.log(7, LogType.NET, "Start establishing forward connection with a given address");
        this.m_parentComponent = parentComponent;
        TrustModel.setCurrentTrustModel(0L);
        JAPModel.getInstance().getRoutingSettings().setForwarderAddress(forwarderAddress);
        if (!this.showConfigClientDialogConnectToForwarder()) {
            return;
        }
        final ForwardConnectionDescriptor showConfigClientDialogGetOffer = this.showConfigClientDialogGetOffer();
        if (showConfigClientDialogGetOffer == null) {
            return;
        }
        MixCascade mixCascade = JAPController.getInstance().getCurrentMixCascade();
        if (mixCascade == null || !mixCascade.isUserDefined()) {
            mixCascade = this.showConfigClientDialogStep2(showConfigClientDialogGetOffer);
        }
        if (mixCascade == null) {
            return;
        }
        this.m_bForwardingSuccessful = this.showConfigClientDialogAnnounceCascade(mixCascade);
    }
    
    public boolean isForwardingSuccessful() {
        return this.m_bForwardingSuccessful;
    }
    
    private Component getRootComponent() {
        return this.m_parentComponent;
    }
    
    private Font getFontSetting() {
        return this.m_fontSetting;
    }
    
    private IImageEncodedCaptcha showConfigClientDialogGetForwarderInfo() {
        final JAPDialog japDialog = new JAPDialog(this.getRootComponent(), JAPMessages.getString("settingsRoutingClientConfigDialogInfoServiceTitle"));
        japDialog.setResizable(false);
        japDialog.setDefaultCloseOperation(2);
        final Vector vector = new Vector<String>();
        final Vector vector2 = new Vector<IImageEncodedCaptcha>();
        final WorkerContentPane workerContentPane = new WorkerContentPane(japDialog, JAPMessages.getString("settingsRoutingClientConfigDialogInfoServiceLabel"), new Runnable() {
            private final /* synthetic */ Vector val$fetchedCaptcha = vector2;
            private final /* synthetic */ Vector val$occuredError = vector;
            
            public void run() {
                final ForwarderInformationGrabber forwarderInformationGrabber = new ForwarderInformationGrabber();
                Thread.interrupted();
                if (forwarderInformationGrabber.getErrorCode() == 0) {
                    this.val$fetchedCaptcha.addElement(forwarderInformationGrabber.getCaptcha());
                }
                else if (forwarderInformationGrabber.getErrorCode() == 1) {
                    this.val$occuredError.addElement(JAPMessages.getString("settingsRoutingClientGrabCapchtaInfoServiceError"));
                }
                else if (forwarderInformationGrabber.getErrorCode() == 3) {
                    this.val$occuredError.addElement(JAPMessages.getString("settingsRoutingClientGrabCapchtaImplementationError"));
                }
                else {
                    this.val$occuredError.addElement(JAPMessages.getString("settingsRoutingClientGrabCaptchaUnknownError"));
                }
            }
        });
        workerContentPane.setInterruptThreadSafe(false);
        workerContentPane.updateDialog();
        japDialog.pack();
        japDialog.setVisible(true);
        IImageEncodedCaptcha imageEncodedCaptcha = null;
        if (vector2.size() > 0) {
            imageEncodedCaptcha = vector2.firstElement();
        }
        else if (vector.size() > 0) {
            LogHolder.log(3, LogType.NET, vector.firstElement());
        }
        return imageEncodedCaptcha;
    }
    
    private IImageEncodedCaptcha showConfigClientDialogViaMail() {
        final JAPDialog japDialog = new JAPDialog(this.getRootComponent(), JAPMessages.getString("settingsRoutingClientConfigDialog1MailTitle"));
        final JPanel panel = new JPanel();
        japDialog.getContentPane().add(panel);
        final JAPHtmlMultiLineLabel japHtmlMultiLineLabel = new JAPHtmlMultiLineLabel(JAPMessages.getString("settingsRoutingClientConfigDialog1MailInstructions1") + "japmailsystem@infoservice.inf.tu-dresden.de" + JAPMessages.getString("settingsRoutingClientConfigDialog1MailInstructions2"), this.getFontSetting());
        final JLabel label = new JLabel(JAPMessages.getString("settingsRoutingClientConfigDialog1MailAnswerLabel"));
        label.setFont(this.getFontSetting());
        final JTextArea textArea = new JTextArea();
        textArea.setFont(this.getFontSetting());
        textArea.setRows(7);
        final JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                this.handlePopupEvent(mouseEvent);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                this.handlePopupEvent(mouseEvent);
            }
            
            private void handlePopupEvent(final MouseEvent mouseEvent) {
                if (mouseEvent.isPopupTrigger()) {
                    final JPopupMenu popupMenu = new JPopupMenu();
                    final JMenuItem menuItem = new JMenuItem(JAPMessages.getString("settingsRoutingClientConfigDialog1MailAnswerPopupPaste"));
                    menuItem.addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent actionEvent) {
                            textArea.paste();
                        }
                    });
                    popupMenu.add(menuItem);
                    popupMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
                }
            }
        });
        final JButton button = new JButton(JAPMessages.getString("settingsRoutingClientConfigDialog1MailInsertButton"));
        button.setFont(this.getFontSetting());
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                textArea.setText("");
                textArea.paste();
            }
        });
        final Vector vector = new Vector<IImageEncodedCaptcha>();
        final JButton button2 = new JButton(JAPMessages.getString("settingsRoutingClientConfigDialog1MailNextButton"));
        button2.setFont(this.getFontSetting());
        button2.setEnabled(false);
        button2.addActionListener(new ActionListener() {
            private final /* synthetic */ Vector val$parsedCaptcha = vector;
            
            public void actionPerformed(final ActionEvent actionEvent) {
                final ForwarderInformationGrabber forwarderInformationGrabber = new ForwarderInformationGrabber(textArea.getText());
                if (forwarderInformationGrabber.getErrorCode() == 0) {
                    this.val$parsedCaptcha.addElement(forwarderInformationGrabber.getCaptcha());
                    japDialog.dispose();
                }
                if (forwarderInformationGrabber.getErrorCode() == 3) {
                    JAPDialog.showErrorDialog(panel, JAPMessages.getString("settingsRoutingClientGrabCapchtaImplementationError"), LogType.MISC);
                    japDialog.dispose();
                }
                if (forwarderInformationGrabber.getErrorCode() == 2) {
                    JAPDialog.showErrorDialog(panel, JAPMessages.getString("settingsRoutingClientConfigDialog1MailParseError"), LogType.MISC);
                    textArea.setText("");
                }
            }
        });
        textArea.addCaretListener(new CaretListener() {
            public void caretUpdate(final CaretEvent caretEvent) {
                if (!textArea.getText().equals("")) {
                    button2.setEnabled(true);
                }
                else {
                    button2.setEnabled(false);
                }
            }
        });
        final JButton button3 = new JButton(JAPMessages.getString("cancelButton"));
        button3.setFont(this.getFontSetting());
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                japDialog.dispose();
            }
        });
        final TitledBorder border = new TitledBorder(JAPMessages.getString("settingsRoutingClientConfigDialog1MailBorder"));
        border.setTitleFont(this.getFontSetting());
        panel.setBorder(border);
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 0, 5);
        layout.setConstraints(japHtmlMultiLineLabel, gridBagConstraints);
        panel.add(japHtmlMultiLineLabel);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(15, 5, 0, 5);
        layout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 5, 2, 5);
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(scrollPane, gridBagConstraints);
        panel.add(scrollPane);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 5, 20, 5);
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(button, gridBagConstraints);
        panel.add(button);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0, 5, 10, 5);
        layout.setConstraints(button3, gridBagConstraints);
        panel.add(button3);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(0, 5, 10, 5);
        layout.setConstraints(button2, gridBagConstraints);
        panel.add(button2);
        japDialog.pack();
        japDialog.setVisible(true);
        IImageEncodedCaptcha imageEncodedCaptcha = null;
        if (vector.size() > 0) {
            imageEncodedCaptcha = vector.firstElement();
        }
        return imageEncodedCaptcha;
    }
    
    private ListenerInterface showConfigClientDialogCaptcha(final IImageEncodedCaptcha imageEncodedCaptcha) {
        final JAPDialog japDialog = new JAPDialog(this.getRootComponent(), JAPMessages.getString("settingsRoutingClientConfigDialogCaptchaTitle"));
        final JPanel panel = new JPanel();
        japDialog.getContentPane().add(panel);
        final JLabel label = new JLabel(new ImageIcon(imageEncodedCaptcha.getImage()));
        final JLabel label2 = new JLabel(JAPMessages.getString("settingsRoutingClientConfigDialogCaptchaCharacterSetLabel") + " " + imageEncodedCaptcha.getCharacterSet());
        label2.setFont(this.getFontSetting());
        final JLabel label3 = new JLabel(JAPMessages.getString("settingsRoutingClientConfigDialogCaptchaCharacterNumberLabel") + " " + Integer.toString(imageEncodedCaptcha.getCharacterNumber()));
        label3.setFont(this.getFontSetting());
        final JLabel label4 = new JLabel(JAPMessages.getString("settingsRoutingClientConfigDialogCaptchaInsertCaptchaLabel"));
        label4.setFont(this.getFontSetting());
        final JButton button = new JButton(JAPMessages.getString("settingsRoutingClientConfigDialogCaptchaNextButton"));
        button.setFont(this.getFontSetting());
        final JTextField textField = new JTextField() {
            private static final long serialVersionUID = 1L;
            
            protected Document createDefaultModel() {
                return new PlainDocument() {
                    private static final long serialVersionUID = 1L;
                    
                    public void insertString(final int n, final String s, final AttributeSet set) throws BadLocationException {
                        if (this.getLength() + s.length() <= imageEncodedCaptcha.getCharacterNumber()) {
                            boolean b = false;
                            for (int n2 = 0; n2 < s.length() && !b; ++n2) {
                                if (imageEncodedCaptcha.getCharacterSet().indexOf(s.toUpperCase().substring(n2, n2 + 1)) < 0) {
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
        };
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(final DocumentEvent documentEvent) {
            }
            
            public void insertUpdate(final DocumentEvent documentEvent) {
                if (documentEvent.getDocument().getLength() == imageEncodedCaptcha.getCharacterNumber()) {
                    button.setEnabled(true);
                }
                else {
                    button.setEnabled(false);
                }
            }
            
            public void removeUpdate(final DocumentEvent documentEvent) {
                if (documentEvent.getDocument().getLength() == imageEncodedCaptcha.getCharacterNumber()) {
                    button.setEnabled(true);
                }
                else {
                    button.setEnabled(false);
                }
            }
        });
        textField.setFont(this.getFontSetting());
        final Vector vector = new Vector<ListenerInterface>();
        if (textField.getText().length() != imageEncodedCaptcha.getCharacterNumber()) {
            button.setEnabled(false);
        }
        button.addActionListener(new ActionListener() {
            private final /* synthetic */ JTextField val$captchaField = textField;
            private final /* synthetic */ Vector val$forwarderInterface = vector;
            
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    final byte[] solveCaptcha = imageEncodedCaptcha.solveCaptcha(this.val$captchaField.getText().trim(), new byte[10]);
                    final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(solveCaptcha, 10, 4);
                    String s = Integer.toString(byteArrayInputStream.read());
                    for (int i = 0; i < 3; ++i) {
                        s = s + "." + Integer.toString(byteArrayInputStream.read());
                    }
                    final ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(solveCaptcha, 14, 2);
                    this.val$forwarderInterface.addElement(new ListenerInterface(s, byteArrayInputStream2.read() * 256 + byteArrayInputStream2.read()));
                    japDialog.dispose();
                }
                catch (Exception ex) {
                    JAPDialog.showErrorDialog(panel, JAPMessages.getString("settingsRoutingClientConfigDialogCaptchaError"), LogType.MISC);
                    this.val$captchaField.setText("");
                }
            }
        });
        final JButton button2 = new JButton(JAPMessages.getString("cancelButton"));
        button2.setFont(this.getFontSetting());
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                japDialog.dispose();
            }
        });
        final TitledBorder border = new TitledBorder(JAPMessages.getString("settingsRoutingClientConfigDialogCaptchaBorder"));
        border.setTitleFont(this.getFontSetting());
        panel.setBorder(border);
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 0, 5);
        layout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10, 5, 0, 5);
        layout.setConstraints(label2, gridBagConstraints);
        panel.add(label2);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 5, 0, 5);
        layout.setConstraints(label3, gridBagConstraints);
        panel.add(label3);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(10, 5, 0, 5);
        layout.setConstraints(label4, gridBagConstraints);
        panel.add(label4);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        layout.setConstraints(textField, gridBagConstraints);
        panel.add(textField);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(20, 5, 5, 5);
        layout.setConstraints(button2, gridBagConstraints);
        panel.add(button2);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new Insets(20, 5, 5, 5);
        layout.setConstraints(button, gridBagConstraints);
        panel.add(button);
        japDialog.pack();
        japDialog.setVisible(true);
        ListenerInterface listenerInterface = null;
        if (vector.size() > 0) {
            listenerInterface = vector.firstElement();
        }
        return listenerInterface;
    }
    
    private boolean showConfigClientDialogConnectToForwarder() {
        final JAPDialog japDialog = new JAPDialog(this.getRootComponent(), JAPMessages.getString("settingsRoutingClientConfigConnectToForwarderTitle"));
        japDialog.setResizable(false);
        japDialog.setDefaultCloseOperation(2);
        final Vector vector = new Vector<String>();
        final Runnable runnable = new Runnable() {
            private final /* synthetic */ Vector val$occuredError = vector;
            
            public void run() {
                if (!JAPModel.getInstance().getRoutingSettings().setRoutingMode(1)) {
                    this.val$occuredError.addElement(JAPMessages.getString("settingsRoutingClientConfigConnectToForwarderError"));
                }
            }
        };
        final IAddress forwarderAddress = JAPModel.getInstance().getRoutingSettings().getForwarderAddress();
        String urn = "";
        if (forwarderAddress != null) {
            urn = Endpoint.toURN(forwarderAddress);
        }
        final WorkerContentPane workerContentPane = new WorkerContentPane(japDialog, JAPMessages.getString("settingsRoutingClientConfigDialogConnectToForwarderLabel"), JAPMessages.getString("settingsRoutingClientConfigDialogConnectToForwarderInfoLabel") + " " + urn, runnable);
        workerContentPane.setInterruptThreadSafe(false);
        workerContentPane.updateDialog();
        japDialog.pack();
        japDialog.setVisible(true);
        boolean b = false;
        if (workerContentPane.hasValidValue() && vector.size() == 0) {
            b = true;
        }
        else if (vector.size() > 0) {
            JAPDialog.showErrorDialog(this.getRootComponent(), vector.firstElement(), LogType.NET);
        }
        return b;
    }
    
    private ForwardConnectionDescriptor showConfigClientDialogGetOffer() {
        final JAPDialog japDialog = new JAPDialog(this.getRootComponent(), JAPMessages.getString("settingsRoutingClientConfigGetOfferTitle"));
        japDialog.setResizable(false);
        japDialog.setDefaultCloseOperation(0);
        final Vector vector = new Vector<String>();
        final Vector vector2 = new Vector<ForwardConnectionDescriptor>();
        final WorkerContentPane workerContentPane = new WorkerContentPane(japDialog, JAPMessages.getString("settingsRoutingClientConfigDialogGetOfferLabel"), new Runnable() {
            private final /* synthetic */ Vector val$fetchedDescriptor = vector2;
            private final /* synthetic */ Vector val$occuredError = vector;
            
            public void run() {
                try {
                    this.val$fetchedDescriptor.addElement(JAPModel.getInstance().getRoutingSettings().getConnectionDescriptor());
                }
                catch (ClientForwardException ex) {
                    LogHolder.log(3, LogType.NET, ex);
                    if (ex.getErrorCode() == 1) {
                        this.val$occuredError.addElement(JAPMessages.getString("settingsRoutingClientGetOfferConnectError"));
                    }
                    else if (ex.getErrorCode() == 3) {
                        this.val$occuredError.addElement(JAPMessages.getString("settingsRoutingClientGetOfferVersionError"));
                    }
                    else {
                        this.val$occuredError.addElement(JAPMessages.getString("settingsRoutingClientGetOfferUnknownError"));
                    }
                }
            }
        });
        workerContentPane.getButtonCancel().addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPModel.getInstance().getRoutingSettings().setRoutingMode(0);
            }
        });
        workerContentPane.setInterruptThreadSafe(false);
        workerContentPane.updateDialog();
        japDialog.pack();
        japDialog.setVisible(true);
        japDialog.dispose();
        ForwardConnectionDescriptor forwardConnectionDescriptor = null;
        if (vector2.size() > 0) {
            forwardConnectionDescriptor = vector2.firstElement();
        }
        else if (vector.size() > 0) {
            JAPDialog.showErrorDialog(this.getRootComponent(), vector.firstElement(), LogType.NET);
        }
        return forwardConnectionDescriptor;
    }
    
    private MixCascade showConfigClientDialogStep2(final ForwardConnectionDescriptor forwardConnectionDescriptor) {
        final JAPDialog japDialog = new JAPDialog(this.getRootComponent(), JAPMessages.getString("settingsRoutingClientConfigDialog2Title"));
        japDialog.setDefaultCloseOperation(0);
        final JPanel panel = new JPanel();
        japDialog.getContentPane().add(panel);
        final JLabel label = new JLabel(JAPMessages.getString("settingsRoutingClientConfigDialog2GuaranteedBandwidthLabel") + " " + Integer.toString(forwardConnectionDescriptor.getGuaranteedBandwidth()));
        label.setFont(this.getFontSetting());
        final JLabel label2 = new JLabel(JAPMessages.getString("settingsRoutingClientConfigDialog2MaxBandwidthLabel") + " " + Integer.toString(forwardConnectionDescriptor.getMaximumBandwidth()));
        label2.setFont(this.getFontSetting());
        final JLabel label3 = new JLabel();
        label3.setFont(this.getFontSetting());
        if (forwardConnectionDescriptor.getMinDummyTrafficInterval() != -1) {
            label3.setText(JAPMessages.getString("settingsRoutingClientConfigDialog2DummyTrafficLabel") + " " + Integer.toString(forwardConnectionDescriptor.getMinDummyTrafficInterval() / 1000));
        }
        else {
            label3.setText(JAPMessages.getString("settingsRoutingClientConfigDialog2DummyTrafficLabel") + " " + JAPMessages.getString("settingsRoutingClientConfigDialog2DummyTrafficLabelNoNeed"));
        }
        final JButton button = new JButton(JAPMessages.getString("settingsRoutingClientConfigDialog2FinishButton"));
        final JLabel label4 = new JLabel(JAPMessages.getString("settingsRoutingClientConfigDialog2MixCascadesLabel"));
        label4.setFont(this.getFontSetting());
        final JList list = new JList(forwardConnectionDescriptor.getMixCascadeList());
        list.setSelectionMode(0);
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(final ListSelectionEvent listSelectionEvent) {
                if (list.getSelectedIndex() != -1) {
                    button.setEnabled(true);
                }
                else {
                    button.setEnabled(false);
                }
            }
        });
        final JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setFont(this.getFontSetting());
        final Vector vector = new Vector<MixCascade>();
        button.setFont(this.getFontSetting());
        if (list.getSelectedIndex() != -1) {
            button.setEnabled(true);
        }
        else {
            button.setEnabled(false);
        }
        button.addActionListener(new ActionListener() {
            private final /* synthetic */ Vector val$selectedMixCascade = vector;
            
            public void actionPerformed(final ActionEvent actionEvent) {
                this.val$selectedMixCascade.addElement(list.getSelectedValue());
                japDialog.dispose();
            }
        });
        final JButton button2 = new JButton(JAPMessages.getString("cancelButton"));
        button2.setFont(this.getFontSetting());
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPModel.getInstance().getRoutingSettings().setRoutingMode(0);
                japDialog.dispose();
            }
        });
        final TitledBorder border = new TitledBorder(JAPMessages.getString("settingsRoutingClientConfigDialog2Border"));
        border.setTitleFont(this.getFontSetting());
        panel.setBorder(border);
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 5, 10, 5);
        layout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(label2, gridBagConstraints);
        panel.add(label2);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        layout.setConstraints(label3, gridBagConstraints);
        panel.add(label3);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        layout.setConstraints(label4, gridBagConstraints);
        panel.add(label4);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 5, 20, 5);
        layout.setConstraints(scrollPane, gridBagConstraints);
        panel.add(scrollPane);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        layout.setConstraints(button2, gridBagConstraints);
        panel.add(button2);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        layout.setConstraints(button, gridBagConstraints);
        panel.add(button);
        japDialog.pack();
        japDialog.setVisible(true);
        MixCascade mixCascade = null;
        if (vector.size() > 0) {
            mixCascade = vector.firstElement();
        }
        return mixCascade;
    }
    
    private boolean showConfigClientDialogAnnounceCascade(final MixCascade currentMixCascade) {
        final JAPDialog japDialog = new JAPDialog(this.getRootComponent(), JAPMessages.getString("settingsRoutingClientConfigDialogAnnounceCascadeTitle"));
        japDialog.setResizable(false);
        japDialog.setDefaultCloseOperation(2);
        final Vector vector = new Vector<String>();
        final WorkerContentPane workerContentPane = new WorkerContentPane(japDialog, JAPMessages.getString("settingsRoutingClientConfigDialogAnnounceCascadeLabel"), new Runnable() {
            private final /* synthetic */ Vector val$occuredError = vector;
            
            public void run() {
                try {
                    JAPModel.getInstance().getRoutingSettings().selectMixCascade(currentMixCascade);
                }
                catch (ClientForwardException ex) {
                    LogHolder.log(3, LogType.NET, "JAPConfRouting: showConfigClientDialogAnnounceCascade: " + ex.toString());
                    if (ex.getErrorCode() == 1) {
                        this.val$occuredError.addElement(JAPMessages.getString("settingsRoutingClientAnnounceCascadeConnectError"));
                    }
                    else {
                        this.val$occuredError.addElement(JAPMessages.getString("settingsRoutingClientAnnounceCascadeUnknownError"));
                    }
                }
            }
        });
        workerContentPane.getButtonCancel().addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPModel.getInstance().getRoutingSettings().setRoutingMode(0);
            }
        });
        workerContentPane.setInterruptThreadSafe(false);
        workerContentPane.updateDialog();
        japDialog.pack();
        japDialog.setVisible(true);
        boolean b = false;
        if (vector.size() == 0) {
            JAPController.getInstance().setCurrentMixCascade(currentMixCascade);
            JAPController.getInstance().setAnonMode(true);
            b = true;
        }
        else if (vector.size() > 0) {
            JAPDialog.showErrorDialog(this.m_parentComponent, vector.firstElement(), LogType.NET);
        }
        return b;
    }
}
