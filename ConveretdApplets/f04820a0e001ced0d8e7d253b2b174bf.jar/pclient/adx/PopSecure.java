// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import pclient.shd.Config;
import javax.swing.Box;
import java.awt.Dimension;
import pclient.adv.CompUtil;
import javax.swing.border.Border;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.Icon;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import pclient.sec.SecCert;
import javax.net.ssl.SSLSession;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import pclient.sec.CertGeneral;
import pclient.adv.AppletSpice;
import pclient.adv.ComInter;
import javax.swing.JFrame;

public class PopSecure extends JFrame implements ComInter
{
    private AppletSpice paraApplet;
    private CertGeneral certGeneral;
    private CertGeneral certIssuer;
    private CertGeneral[] certDetails;
    private EmptyBorder border5;
    private EmptyBorder border10;
    private JLabel tunnelLabel;
    
    public PopSecure() {
        this.border5 = new EmptyBorder(5, 5, 5, 5);
        this.border10 = new EmptyBorder(10, 10, 10, 10);
    }
    
    private void clearFields() {
        this.certGeneral = new CertGeneral();
        this.certIssuer = new CertGeneral();
        this.certDetails = null;
    }
    
    public void setPara(final AppletSpice paraApplet) {
        this.paraApplet = paraApplet;
        try {
            if (!this.paraApplet.chatModel.cmIsTunnel()) {
                final SSLSession sslSession = (SSLSession)this.paraApplet.chatModel.cmSecSession();
                this.certGeneral = SecCert.getCert(sslSession);
                this.certIssuer = SecCert.getCertIssuer(sslSession);
                this.certDetails = SecCert.getCertChain(sslSession);
            }
            else {
                this.clearFields();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.clearFields();
        }
        this.paraApplet.paraConf.printer().print("cert=" + this.certGeneral);
        this.paraApplet.paraConf.printer().print("issuer=" + this.certIssuer);
        System.out.println("cert=" + this.certGeneral);
        System.out.println("ier=" + this.certIssuer);
        this.setTitle(this.paraApplet.paraConf.title());
        this.setSize(580, 560);
        this.setDefaultCloseOperation(2);
        this.buildGUI();
        WindowUtil.center(this);
    }
    
    public void process(final int n, final String[] array) {
    }
    
    public void restart() {
        this.setVisible(true);
    }
    
    public void destroy() {
        this.setVisible(false);
        this.dispose();
    }
    
    private void buildGUI() {
        final JTabbedPane tabbedPane = new JTabbedPane();
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(tabbedPane, "Center");
        final String s = "General";
        tabbedPane.addTab(s, null, this.createGeneral(), s);
        final String s2 = "Details";
        tabbedPane.addTab(s2, null, this.createDetails(), s2);
    }
    
    private JComponent createGeneral() {
        final JLabel tunnelLabel = new JLabel("");
        tunnelLabel.setForeground(Color.BLUE);
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(this.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(tunnelLabel);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 8)));
        verticalPanel.add(this.getIssuedTo(this.paraApplet.paraConf));
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        verticalPanel.add(this.getIssuedBy(this.paraApplet.paraConf, this.certIssuer));
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        verticalPanel.add(this.getValidity(this.paraApplet.paraConf));
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        verticalPanel.add(this.getEnc(this.paraApplet.paraConf));
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        this.tunnelLabel = tunnelLabel;
        if (this.paraApplet.chatModel.cmIsTunnel()) {
            tunnelLabel.setText("Secure Tunnel Session");
        }
        return panel;
    }
    
    private JPanel getIssuedTo(final Config config) {
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Issued To", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.setAlignmentX(0.0f);
        verticalPanel.add(this.boxIssuedTo(config));
        verticalPanel.add(Box.createRigidArea(new Dimension(2, 2)));
        return verticalPanel;
    }
    
    private JPanel boxIssuedTo(final Config config) {
        final JLabel label = new JLabel("Common Name:", 2);
        final JLabel label2 = new JLabel("Organization:", 2);
        final JLabel label3 = new JLabel("Organizational Unit:", 2);
        final JLabel label4 = new JLabel("Location:", 2);
        final JLabel label5 = new JLabel("Serial Number:", 2);
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 3, 5));
        panel.setAlignmentX(0.5f);
        panel.add(label);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        final JLabel label6 = new JLabel(this.certGeneral.cn, 2);
        final JLabel label7 = new JLabel(this.certGeneral.org, 2);
        final JLabel label8 = new JLabel(this.certGeneral.unit, 2);
        final JLabel label9 = new JLabel(this.certGeneral.state + "  " + this.certGeneral.country, 2);
        final JLabel label10 = new JLabel(this.certGeneral.serial, 2);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(5, 1, 3, 5));
        panel2.add(label6);
        panel2.add(label7);
        panel2.add(label8);
        panel2.add(label9);
        panel2.add(label10);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 0));
        panel3.add(Box.createHorizontalGlue());
        panel3.add(Box.createRigidArea(new Dimension(4, 4)));
        panel3.add(panel);
        panel3.add(Box.createRigidArea(new Dimension(4, 3)));
        panel3.add(panel2);
        panel3.add(Box.createRigidArea(new Dimension(4, 4)));
        panel3.add(Box.createHorizontalGlue());
        return panel3;
    }
    
    private JPanel getIssuedBy(final Config config, final CertGeneral certGeneral) {
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Issued By", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.setAlignmentX(0.0f);
        verticalPanel.add(this.boxIssuedBy(config, certGeneral));
        verticalPanel.add(Box.createRigidArea(new Dimension(2, 2)));
        return verticalPanel;
    }
    
    private JPanel boxIssuedBy(final Config config, final CertGeneral certGeneral) {
        final JLabel label = new JLabel("Common Name:", 2);
        final JLabel label2 = new JLabel("Organization:", 2);
        final JLabel label3 = new JLabel("Organizational Unit:", 2);
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 3, 5));
        panel.setAlignmentX(0.5f);
        panel.add(label);
        panel.add(label2);
        panel.add(label3);
        final JLabel label4 = new JLabel(certGeneral.cn, 2);
        final JLabel label5 = new JLabel(certGeneral.org, 2);
        final JLabel label6 = new JLabel(certGeneral.unit, 2);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3, 1, 3, 5));
        panel2.add(label4);
        panel2.add(label5);
        panel2.add(label6);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 0));
        panel3.add(Box.createHorizontalGlue());
        panel3.add(Box.createRigidArea(new Dimension(4, 4)));
        panel3.add(panel);
        panel3.add(Box.createRigidArea(new Dimension(4, 3)));
        panel3.add(panel2);
        panel3.add(Box.createRigidArea(new Dimension(4, 4)));
        panel3.add(Box.createHorizontalGlue());
        return panel3;
    }
    
    private JPanel getValidity(final Config config) {
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Validity", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.setAlignmentX(0.0f);
        verticalPanel.add(this.boxValidity(config));
        verticalPanel.add(Box.createRigidArea(new Dimension(2, 2)));
        return verticalPanel;
    }
    
    private JPanel boxValidity(final Config config) {
        final JLabel label = new JLabel("Issued On:", 2);
        final JLabel label2 = new JLabel("Expires On:", 2);
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 3, 5));
        panel.setAlignmentX(0.5f);
        panel.add(label);
        panel.add(label2);
        final JLabel label3 = new JLabel(this.certGeneral.validFrom, 2);
        final JLabel label4 = new JLabel(this.certGeneral.validTo, 2);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 1, 3, 5));
        panel2.add(label3);
        panel2.add(label4);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 0));
        panel3.add(Box.createHorizontalGlue());
        panel3.add(Box.createRigidArea(new Dimension(4, 4)));
        panel3.add(panel);
        panel3.add(Box.createRigidArea(new Dimension(4, 3)));
        panel3.add(panel2);
        panel3.add(Box.createRigidArea(new Dimension(4, 4)));
        panel3.add(Box.createHorizontalGlue());
        return panel3;
    }
    
    private JPanel getEnc(final Config config) {
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Encryption", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.setAlignmentX(0.0f);
        verticalPanel.add(this.boxEnc(config));
        verticalPanel.add(Box.createRigidArea(new Dimension(2, 2)));
        return verticalPanel;
    }
    
    private JPanel boxEnc(final Config config) {
        final JLabel label = new JLabel("Cipher Suite:", 2);
        final JLabel label2 = new JLabel("Protocol:", 2);
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 3, 5));
        panel.setAlignmentX(0.5f);
        panel.add(label);
        panel.add(label2);
        final JLabel label3 = new JLabel(this.certGeneral.sCipher, 2);
        final JLabel label4 = new JLabel(this.certGeneral.sProto, 2);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 1, 3, 5));
        panel2.add(label3);
        panel2.add(label4);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 0));
        panel3.add(Box.createHorizontalGlue());
        panel3.add(Box.createRigidArea(new Dimension(4, 4)));
        panel3.add(panel);
        panel3.add(Box.createRigidArea(new Dimension(4, 3)));
        panel3.add(panel2);
        panel3.add(Box.createRigidArea(new Dimension(4, 4)));
        panel3.add(Box.createHorizontalGlue());
        return panel3;
    }
    
    private JComponent createDetails() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(this.border5);
        if (this.certDetails == null) {
            return panel;
        }
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(new JLabel("Certificate Chain"));
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 8)));
        for (int i = 0; i < this.certDetails.length; ++i) {
            verticalPanel.add(this.getOneOfChain(this.paraApplet.paraConf, this.certDetails[i]));
            verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        }
        return panel;
    }
    
    private JPanel getOneOfChain(final Config config, final CertGeneral certGeneral) {
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Certificate", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(this.boxOneOfChain(config, certGeneral));
        return verticalPanel;
    }
    
    private JComponent boxOneOfChain(final Config config, final CertGeneral certGeneral) {
        final JTextArea textArea = new JTextArea();
        textArea.setRows(10);
        textArea.setColumns(20);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        final JScrollPane scrollPane = new JScrollPane(textArea, 20, 31);
        textArea.setText(this.certString(certGeneral));
        try {
            textArea.setCaretPosition(0);
        }
        catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return scrollPane;
    }
    
    private String certString(final CertGeneral certGeneral) {
        if (certGeneral == null) {
            return "";
        }
        return "CN: " + certGeneral.cn + "\n" + "OU: " + certGeneral.unit + "\n" + "O: " + certGeneral.org + "\n" + "L: " + certGeneral.state + "\n" + "C: " + certGeneral.country + "\n" + "Not Before: " + certGeneral.validFrom + "\n" + "Not After: " + certGeneral.validTo + "\n" + "Version: " + certGeneral.version + "\n" + "Serial Number: " + certGeneral.serial + "\n" + "Cert: " + certGeneral.xName + "\n" + "\n" + "Public Key: " + certGeneral.pubkey + "\n" + "\n" + "Signature Algorithm: " + certGeneral.sigAlgName + "\n" + "Signature: " + certGeneral.signature + "\n";
    }
}
