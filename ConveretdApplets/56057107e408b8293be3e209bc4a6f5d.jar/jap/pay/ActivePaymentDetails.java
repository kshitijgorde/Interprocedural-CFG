// 
// Decompiled by Procyon v0.5.30
// 

package jap.pay;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import platform.AbstractOS;
import jap.JAPController;
import javax.swing.Icon;
import jap.JAPUtil;
import anon.util.Util;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import gui.LinkMouseListener;
import javax.swing.JLabel;
import jap.pay.wizardnew.PaymentInfoPane;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.Box;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.util.Enumeration;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import gui.GUIUtils;
import java.util.Hashtable;
import javax.swing.JPanel;
import java.awt.Component;
import gui.JAPHtmlMultiLineLabel;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Insets;
import logging.LogHolder;
import logging.LogType;
import anon.util.JAPMessages;
import java.util.Vector;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import gui.dialog.JAPDialog;

public class ActivePaymentDetails extends JAPDialog implements ActionListener
{
    private static final String MSG_HEADING;
    private static final String MSG_TITLE;
    private static final String MSG_CLOSEBUTTON;
    private static final String MSG_COPYBUTTON;
    private static final String MSG_PAYBUTTON;
    private GridBagConstraints m_c;
    private JButton m_closeButton;
    static /* synthetic */ Class class$jap$pay$ActivePaymentDetails;
    
    public ActivePaymentDetails(final JAPDialog japDialog, final Vector vector, final String s, final long n, final String s2) {
        super(japDialog, JAPMessages.getString(ActivePaymentDetails.MSG_TITLE));
        try {
            this.setDefaultCloseOperation(2);
            this.buildDialog(vector, s, n, s2);
            this.setResizable(false);
            this.pack();
            this.setVisible(true);
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.PAY, "Could not create ActivePaymentDetails: ", ex);
        }
    }
    
    private void buildDialog(final Vector vector, final String s, final long n, final String s2) {
        this.m_c = new GridBagConstraints();
        this.m_c.anchor = 11;
        this.m_c.insets = new Insets(10, 30, 10, 30);
        this.m_c.gridx = 0;
        this.m_c.gridy = 0;
        this.m_c.weighty = 0.0;
        this.m_c.weightx = 0.0;
        this.getContentPane().setLayout(new GridBagLayout());
        this.getContentPane().add(new JAPHtmlMultiLineLabel("<h3>" + JAPMessages.getString(ActivePaymentDetails.MSG_HEADING) + "</h3>"), this.m_c);
        final GridBagConstraints c = this.m_c;
        ++c.gridy;
        this.m_c.weightx = 0.0;
        final JPanel viewportView = new JPanel();
        viewportView.setLayout(new GridBagLayout());
        final Vector<JPanel> vector2 = new Vector<JPanel>();
        final Enumeration<Hashtable> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final Hashtable hashtable = elements.nextElement();
            final GridBagConstraints c2 = this.m_c;
            ++c2.gridy;
            final JPanel buildOptionPanel = this.buildOptionPanel(hashtable, s, n, s2);
            vector2.addElement(buildOptionPanel);
            viewportView.add(buildOptionPanel, this.m_c);
        }
        final Dimension maxSize = GUIUtils.getMaxSize(vector2);
        GUIUtils.setEqualWidths(vector2, maxSize);
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(viewportView);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension((int)(Object)new Double(maxSize.width) + 80, Math.min(GUIUtils.getTotalSize(vector2).height + 80, (int)Math.round(GUIUtils.getCurrentScreen(GUIUtils.getParentWindow(this.getContentPane())).getHeight() * 0.8) - 100)));
        this.getContentPane().add(scrollPane, this.m_c);
        scrollPane.revalidate();
        (this.m_closeButton = new JButton(JAPMessages.getString(ActivePaymentDetails.MSG_CLOSEBUTTON))).addActionListener(this);
        final GridBagConstraints c3 = this.m_c;
        ++c3.gridy;
        this.getContentPane().add(this.m_closeButton, this.m_c);
    }
    
    private JPanel buildOptionPanel(final Hashtable hashtable, final String s, final long n, final String s2) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.add(new JAPHtmlMultiLineLabel("<b>" + hashtable.get("heading") + "</b>"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        final JAPHtmlMultiLineLabel japHtmlMultiLineLabel = new JAPHtmlMultiLineLabel(hashtable.get("detailedInfo"));
        japHtmlMultiLineLabel.setPreferredWidth(600);
        japHtmlMultiLineLabel.setAlignmentX(0.0f);
        panel.add(japHtmlMultiLineLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        final Enumeration<String> elements = (Enumeration<String>)((Vector)hashtable.get("extraInfos")).elements();
        while (elements.hasMoreElements()) {
            final String s3 = elements.nextElement();
            boolean b = true;
            try {
                new URL(s3);
            }
            catch (MalformedURLException ex) {
                b = false;
            }
            if (b) {
                String s4;
                if (s3.toUpperCase().indexOf("PAYPAL") != -1) {
                    s4 = PaymentInfoPane.createPaypalLink(s3, n, s2, s);
                }
                else if (s3.toUpperCase().indexOf("E-GOLD") != -1) {
                    s4 = PaymentInfoPane.createEgoldLink(s3, n, s2, s);
                }
                else if (s3.toUpperCase().indexOf("PAYSAFECARD") != -1) {
                    s4 = PaymentInfoPane.createPaysafecardLink(s3, n, s);
                }
                else {
                    s4 = PaymentInfoPane.createPaysafecardLink(s3, n, s);
                }
                final String s5 = s4;
                final String methodImageFilename = PaymentInfoPane.getMethodImageFilename(hashtable.get("name"));
                Icon loadImageIcon = null;
                if (methodImageFilename != null) {
                    loadImageIcon = GUIUtils.loadImageIcon(methodImageFilename, false, false);
                }
                if (loadImageIcon != null) {
                    final JPanel panel2 = new JPanel();
                    panel2.setLayout(new BoxLayout(panel2, 0));
                    final JLabel label = new JLabel(loadImageIcon);
                    if (s5 != null) {
                        label.addMouseListener(new LinkMouseListener(s5));
                    }
                    panel2.add(label);
                    panel2.setAlignmentX(0.0f);
                    panel.add(panel2);
                    panel.add(Box.createRigidArea(new Dimension(0, 5)));
                }
                final JPanel panel3 = new JPanel();
                final JButton button = new JButton(JAPMessages.getString(ActivePaymentDetails.MSG_PAYBUTTON));
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent actionEvent) {
                        ActivePaymentDetails.this.openURL(s5);
                    }
                });
                panel3.add(button);
                final JButton button2 = new JButton(JAPMessages.getString(ActivePaymentDetails.MSG_COPYBUTTON));
                button2.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent actionEvent) {
                        ActivePaymentDetails.this.copyToClipboard(s5, true);
                    }
                });
                panel3.add(button2);
                panel3.setAlignmentX(0.0f);
                panel.add(panel3);
            }
            else {
                final String replaceAll = Util.replaceAll(Util.replaceAll(Util.replaceAll(s3, "%t", s), "%a", JAPUtil.formatEuroCentValue(n)), "%c", "");
                final JAPHtmlMultiLineLabel japHtmlMultiLineLabel2 = new JAPHtmlMultiLineLabel(replaceAll);
                japHtmlMultiLineLabel2.setAlignmentX(0.0f);
                panel.add(japHtmlMultiLineLabel2);
                panel.add(Box.createRigidArea(new Dimension(0, 5)));
                final String s6 = replaceAll;
                final JPanel panel4 = new JPanel();
                final JButton button3 = new JButton(JAPMessages.getString(ActivePaymentDetails.MSG_COPYBUTTON));
                button3.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent actionEvent) {
                        ActivePaymentDetails.this.copyToClipboard(s6, false);
                    }
                });
                panel4.add(button3);
                panel4.setAlignmentX(0.0f);
                panel.add(panel4);
            }
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
            panel.setSize(panel.getPreferredSize().width, panel.getPreferredSize().height);
        }
        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        return panel;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.m_closeButton) {
            this.setVisible(false);
        }
    }
    
    public void openURL(String cleanupLink) {
        if (!JAPController.getInstance().isAnonConnected() && JAPController.getInstance().getAnonMode()) {
            JAPController.getInstance().stopAnonModeWait();
        }
        final AbstractOS instance = AbstractOS.getInstance();
        cleanupLink = this.cleanupLink(cleanupLink);
        try {
            instance.openURL(new URL(cleanupLink));
        }
        catch (MalformedURLException ex) {
            LogHolder.log(2, LogType.PAY, "Malformed URL");
        }
    }
    
    private void copyToClipboard(String s, final boolean b) {
        final Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        if (b) {
            s = this.cleanupLink(s);
        }
        else {
            s = this.cleanupText(s);
        }
        systemClipboard.setContents(new StringSelection(s), null);
    }
    
    private String cleanupLink(String s) {
        s = Util.replaceAll(s, "<br>", "");
        s = Util.replaceAll(s, "<p>", "");
        s = Util.replaceAll(s, "<html>", " ");
        s = Util.replaceAll(s, "</html>", " ");
        s = Util.replaceAll(s, "&nbsp;", "%20");
        s = Util.replaceAll(s, " ", "%20");
        s = Util.replaceAll(s, "<font color=blue><u>", "");
        s = Util.replaceAll(s, "</u></font>", "");
        s = s.trim();
        return s;
    }
    
    private String cleanupText(String s) {
        s = Util.replaceAll(s, "<br>", "\n");
        s = Util.replaceAll(s, "<p>", "\n\n");
        s = Util.replaceAll(s, "&uuml;", "\u00fc");
        s = Util.replaceAll(s, "&Uuml;", "\u00dc");
        s = Util.replaceAll(s, "&auml;", "\u00e4");
        s = Util.replaceAll(s, "&Auml;", "\u00c4");
        s = Util.replaceAll(s, "&ouml;", "\u00f6");
        s = Util.replaceAll(s, "&Ouml;", "\u00d6");
        s = Util.replaceAll(s, "&szlig;", "\u00df");
        s = Util.replaceAll(s, "&nbsp;", " ");
        return s;
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
        MSG_HEADING = ((ActivePaymentDetails.class$jap$pay$ActivePaymentDetails == null) ? (ActivePaymentDetails.class$jap$pay$ActivePaymentDetails = class$("jap.pay.ActivePaymentDetails")) : ActivePaymentDetails.class$jap$pay$ActivePaymentDetails).getName() + "_heading";
        MSG_TITLE = ((ActivePaymentDetails.class$jap$pay$ActivePaymentDetails == null) ? (ActivePaymentDetails.class$jap$pay$ActivePaymentDetails = class$("jap.pay.ActivePaymentDetails")) : ActivePaymentDetails.class$jap$pay$ActivePaymentDetails).getName() + "_title";
        MSG_CLOSEBUTTON = ((ActivePaymentDetails.class$jap$pay$ActivePaymentDetails == null) ? (ActivePaymentDetails.class$jap$pay$ActivePaymentDetails = class$("jap.pay.ActivePaymentDetails")) : ActivePaymentDetails.class$jap$pay$ActivePaymentDetails).getName() + "_closebutton";
        MSG_COPYBUTTON = ((ActivePaymentDetails.class$jap$pay$ActivePaymentDetails == null) ? (ActivePaymentDetails.class$jap$pay$ActivePaymentDetails = class$("jap.pay.ActivePaymentDetails")) : ActivePaymentDetails.class$jap$pay$ActivePaymentDetails).getName() + "_copybutton";
        MSG_PAYBUTTON = ((ActivePaymentDetails.class$jap$pay$ActivePaymentDetails == null) ? (ActivePaymentDetails.class$jap$pay$ActivePaymentDetails = class$("jap.pay.ActivePaymentDetails")) : ActivePaymentDetails.class$jap$pay$ActivePaymentDetails).getName() + "_paybutton";
    }
}
