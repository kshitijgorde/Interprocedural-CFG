// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.ResourceBundle;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JDialog;

public class MyAboutDialog extends JDialog
{
    private JLabel appName;
    private JLabel authorName;
    private JLabel company;
    private JLabel company2;
    private JLabel email;
    private JButton jButtonOK;
    private JPanel jPanel1;
    private JPanel jPanel10;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JPanel jPanel8;
    private JPanel jPanel9;
    private JLabel url;
    private JLabel version;
    private JLabel versionNumber;
    private JLabel year;
    
    public MyAboutDialog(final Frame parent, final boolean modal) {
        super(parent, modal);
        if (parent != null) {
            final Dimension frmSize = parent.getSize();
            final Point loc = parent.getLocation();
            final Dimension dlgSize = this.getPreferredSize();
            this.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
        }
        this.initComponents();
    }
    
    public MyAboutDialog(final Frame parent) {
        this(parent, true);
    }
    
    public MyAboutDialog() {
        this((Frame)null, true);
    }
    
    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.jPanel3 = new JPanel();
        this.appName = new JLabel();
        this.jPanel4 = new JPanel();
        this.version = new JLabel();
        this.versionNumber = new JLabel();
        this.jPanel5 = new JPanel();
        this.year = new JLabel();
        this.jPanel6 = new JPanel();
        this.authorName = new JLabel();
        this.jPanel7 = new JPanel();
        this.email = new JLabel();
        this.jPanel8 = new JPanel();
        this.url = new JLabel();
        this.jPanel9 = new JPanel();
        this.company2 = new JLabel();
        this.jPanel10 = new JPanel();
        this.company = new JLabel();
        this.jPanel2 = new JPanel();
        this.jButtonOK = new JButton();
        this.setTitle(ResourceBundle.getBundle("org.namekata.fundamental/MyAboutDialogNew").getString("Title"));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent evt) {
                MyAboutDialog.this.closeDialog(evt);
            }
        });
        this.jPanel1.setLayout(new BoxLayout(this.jPanel1, 1));
        this.appName.setText(ResourceBundle.getBundle("org.namekata.fundamental/MyAboutDialogNew").getString("AppName"));
        this.jPanel3.add(this.appName);
        this.jPanel1.add(this.jPanel3);
        this.version.setText(ResourceBundle.getBundle("org.namekata.fundamental/MyAboutDialogNew").getString("Version"));
        this.jPanel4.add(this.version);
        this.versionNumber.setText("1.0");
        this.jPanel4.add(this.versionNumber);
        this.jPanel1.add(this.jPanel4);
        this.year.setText("2007");
        this.jPanel5.add(this.year);
        this.jPanel1.add(this.jPanel5);
        this.authorName.setText("Tsuneyuki Namekata");
        this.jPanel6.add(this.authorName);
        this.jPanel1.add(this.jPanel6);
        this.email.setText("e-mail: namekata@res.otaru-uc.ac.jp");
        this.jPanel7.add(this.email);
        this.jPanel1.add(this.jPanel7);
        this.url.setText("(private) URL: http://www.namekata.org/");
        this.jPanel8.add(this.url);
        this.jPanel1.add(this.jPanel8);
        this.company2.setText("Department of Information and Management Science");
        this.jPanel9.add(this.company2);
        this.jPanel1.add(this.jPanel9);
        this.company.setText("Otaru University of Commerce");
        this.jPanel10.add(this.company);
        this.jPanel1.add(this.jPanel10);
        this.getContentPane().add(this.jPanel1, "Center");
        this.jButtonOK.setText("OK");
        this.jButtonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                MyAboutDialog.this.jButtonOKActionPerformed(evt);
            }
        });
        this.jPanel2.add(this.jButtonOK);
        this.getContentPane().add(this.jPanel2, "South");
        this.pack();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension dialogSize = this.getSize();
        this.setLocation((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2);
    }
    
    private void jButtonOKActionPerformed(final ActionEvent evt) {
        this.dispose();
    }
    
    private void closeDialog(final WindowEvent evt) {
        this.setVisible(false);
        this.dispose();
    }
    
    public void setAppName(final String appName) {
        this.appName.setText(appName);
    }
    
    public void setVersion(final String versionNumber) {
        this.versionNumber.setText(versionNumber);
    }
    
    public void setYear(final String year) {
        this.year.setText(year);
    }
}
