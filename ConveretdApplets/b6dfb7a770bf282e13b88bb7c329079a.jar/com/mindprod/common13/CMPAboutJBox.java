// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.common13;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import com.mindprod.common11.FontFactory;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JDialog;

public final class CMPAboutJBox extends JDialog
{
    private static final boolean DEBUGGING = false;
    private static final int HEIGHT = 400;
    private static final int WIDTH = 490;
    private static final Color BLACK;
    private static final Color DARK_GREEN;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Color WHITE;
    private static final Font FONT_FOR_TITLE;
    private JButton dismissButton;
    private JLabel showAddr1;
    private JLabel showAddr2;
    private JLabel showAuthor;
    private JLabel showCMP;
    private JLabel showCopyright;
    private JLabel showDownloadURL;
    private JLabel showMailTo;
    private JLabel showMinJdkVersion;
    private JLabel showPhone;
    private JLabel showProgramVersionBuild;
    private JLabel showPurpose1;
    private JLabel showPurpose2;
    private JLabel showReleaseDate;
    private JLabel showRunningJdkVersion;
    private JLabel showStatus;
    private JLabel showVmName;
    
    public CMPAboutJBox(final String progname, final String version, final String purpose1, final String purpose2, final String status, final String released, final int firstCopyrightYear, final String author, final String masterSite, final String minJdkVersion) {
        this(new JFrame(progname + " " + version + " build " + 9411), progname, version, purpose1, purpose2, status, released, firstCopyrightYear, author, masterSite, minJdkVersion);
    }
    
    public CMPAboutJBox(final Frame parent, final String progname, final String version, final String purpose1, final String purpose2, final String status, final String released, final int firstCopyrightYear, final String author, final String masterSite, final String minJkdVersion) {
        super(parent, progname + " " + version + " build " + 9411, false);
        this.guts(progname, version, purpose1, purpose2, status, released, firstCopyrightYear, author, masterSite, minJkdVersion);
    }
    
    private void dismiss() {
        this.dispose();
    }
    
    private void guts(final String progname, final String version, final String purpose1, final String purpose2, final String status, final String released, final int firstCopyrightYear, final String author, final String masterSite, final String minJdkVersion) {
        this.setSize(512, 522);
        this.setLocation(0, 0);
        final Container contentPane = this.getContentPane();
        contentPane.setBackground(CMPAboutJBox.WHITE);
        (this.showProgramVersionBuild = new JLabel(progname + " " + version + " build " + 9411, 0)).setFont(CMPAboutJBox.FONT_FOR_TITLE);
        this.showProgramVersionBuild.setForeground(CMPAboutJBox.FOREGROUND_FOR_TITLE);
        this.showProgramVersionBuild.setBackground(CMPAboutJBox.WHITE);
        (this.showPurpose1 = new JLabel(purpose1, 0)).setFont(FontFactory.build("Dialog", 2, 12));
        this.showPurpose1.setForeground(CMPAboutJBox.BLACK);
        this.showPurpose1.setBackground(CMPAboutJBox.WHITE);
        if (purpose2 != null && purpose2.length() > 0) {
            (this.showPurpose2 = new JLabel(purpose2, 0)).setFont(FontFactory.build("Dialog", 2, 12));
            this.showPurpose2.setForeground(CMPAboutJBox.BLACK);
            this.showPurpose2.setBackground(CMPAboutJBox.WHITE);
        }
        (this.showStatus = new JLabel(status, 0)).setFont(FontFactory.build("Dialog", 1, 12));
        this.showStatus.setForeground(CMPAboutJBox.FOREGROUND_FOR_LABEL);
        this.showStatus.setBackground(CMPAboutJBox.WHITE);
        (this.showReleaseDate = new JLabel("released: " + released, 0)).setFont(FontFactory.build("Dialog", 0, 11));
        this.showReleaseDate.setForeground(CMPAboutJBox.FOREGROUND_FOR_LABEL);
        this.showReleaseDate.setBackground(CMPAboutJBox.WHITE);
        String copyright;
        if (firstCopyrightYear == 2011) {
            copyright = Integer.toString(2011);
        }
        else {
            copyright = firstCopyrightYear + "-" + 2011;
        }
        (this.showCopyright = new JLabel("copyright " + copyright, 2)).setFont(FontFactory.build("Dialog", 2, 11));
        this.showCopyright.setForeground(CMPAboutJBox.DARK_GREEN);
        this.showCopyright.setBackground(CMPAboutJBox.WHITE);
        (this.showAuthor = new JLabel(author, 2)).setFont(FontFactory.build("Dialog", 3, 11));
        this.showAuthor.setForeground(CMPAboutJBox.DARK_GREEN);
        this.showAuthor.setBackground(CMPAboutJBox.WHITE);
        (this.showCMP = new JLabel("Canadian Mind Products", 2)).setFont(FontFactory.build("Dialog", 3, 11));
        this.showCMP.setForeground(CMPAboutJBox.DARK_GREEN);
        this.showCMP.setBackground(CMPAboutJBox.WHITE);
        (this.showAddr1 = new JLabel("#101 - 2536 Wark Street", 2)).setFont(FontFactory.build("Dialog", 2, 11));
        this.showAddr1.setForeground(CMPAboutJBox.DARK_GREEN);
        this.showAddr1.setBackground(CMPAboutJBox.WHITE);
        (this.showAddr2 = new JLabel("Victoria, BC Canada V8T 4G8", 2)).setFont(FontFactory.build("Dialog", 2, 11));
        this.showAddr2.setForeground(CMPAboutJBox.DARK_GREEN);
        this.showAddr2.setBackground(CMPAboutJBox.WHITE);
        (this.showMinJdkVersion = new JLabel("requires: Java " + minJdkVersion + "+", 4)).setFont(FontFactory.build("Dialog", 2, 11));
        this.showMinJdkVersion.setForeground(CMPAboutJBox.DARK_GREEN);
        this.showMinJdkVersion.setBackground(CMPAboutJBox.WHITE);
        (this.showRunningJdkVersion = new JLabel("running: Java " + System.getProperty("java.version", "unknown"), 4)).setFont(FontFactory.build("Dialog", 2, 11));
        this.showRunningJdkVersion.setForeground(CMPAboutJBox.DARK_GREEN);
        this.showRunningJdkVersion.setBackground(CMPAboutJBox.WHITE);
        (this.showVmName = new JLabel(System.getProperty("java.vm.name", "unknown"), 4)).setFont(FontFactory.build("Dialog", 2, 11));
        this.showVmName.setForeground(CMPAboutJBox.DARK_GREEN);
        this.showVmName.setBackground(CMPAboutJBox.WHITE);
        (this.showPhone = new JLabel("phone: (250) 361-9093", 4)).setFont(FontFactory.build("Dialog", 2, 11));
        this.showPhone.setForeground(CMPAboutJBox.DARK_GREEN);
        this.showPhone.setBackground(CMPAboutJBox.WHITE);
        (this.showMailTo = new JLabel("roedyg@mindprod.com", 2)).setFont(FontFactory.build("Dialog", 2, 11));
        this.showMailTo.setForeground(CMPAboutJBox.DARK_GREEN);
        this.showMailTo.setBackground(CMPAboutJBox.WHITE);
        (this.showDownloadURL = new JLabel("http://mindprod.com/products.html#" + masterSite, 4)).setFont(FontFactory.build("Dialog", 2, 10));
        this.showDownloadURL.setForeground(CMPAboutJBox.DARK_GREEN);
        this.showDownloadURL.setBackground(CMPAboutJBox.WHITE);
        (this.dismissButton = new JEButton("Dismiss")).requestFocus();
        this.dismissButton.setToolTipText("Dismiss About dialog");
        this.layoutComponents(contentPane);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                CMPAboutJBox.this.dismiss();
            }
        });
        this.dismissButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final Object object = e.getSource();
                if (object == CMPAboutJBox.this.dismissButton) {
                    CMPAboutJBox.this.dismiss();
                }
            }
        });
        this.validate();
        this.setVisible(true);
    }
    
    private void layoutComponents(final Container contentPane) {
        contentPane.setLayout(new GridBagLayout());
        contentPane.add(this.showProgramVersionBuild, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, 10, 0, new Insets(10, 10, 0, 10), 0, 0));
        contentPane.add(this.showPurpose1, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, 10, 0, new Insets(5, 10, 0, 10), 0, 0));
        if (this.showPurpose2 != null) {
            contentPane.add(this.showPurpose2, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, 10, 0, new Insets(1, 10, 0, 10), 0, 0));
        }
        contentPane.add(this.showStatus, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, 10, 0, new Insets(3, 10, 0, 10), 0, 0));
        contentPane.add(this.showReleaseDate, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, 10, 0, new Insets(2, 10, 0, 10), 0, 0));
        contentPane.add(this.showCopyright, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, 17, 0, new Insets(3, 10, 0, 3), 0, 0));
        contentPane.add(this.showAuthor, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, 17, 0, new Insets(3, 10, 0, 3), 0, 0));
        contentPane.add(this.showCMP, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, 17, 0, new Insets(3, 10, 0, 3), 0, 0));
        contentPane.add(this.showAddr1, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, 17, 0, new Insets(3, 10, 0, 3), 0, 0));
        contentPane.add(this.showAddr2, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0, 17, 0, new Insets(3, 10, 0, 3), 0, 0));
        contentPane.add(this.showMailTo, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0, 17, 0, new Insets(3, 10, 0, 3), 0, 0));
        contentPane.add(this.dismissButton, new GridBagConstraints(0, 11, 2, 1, 0.0, 0.0, 10, 0, new Insets(10, 10, 10, 10), 0, 0));
        contentPane.add(this.showMinJdkVersion, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, 13, 0, new Insets(3, 3, 0, 10), 0, 0));
        contentPane.add(this.showRunningJdkVersion, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, 13, 0, new Insets(3, 3, 0, 10), 0, 0));
        contentPane.add(this.showVmName, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, 13, 0, new Insets(3, 3, 0, 10), 0, 0));
        contentPane.add(this.showPhone, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0, 13, 0, new Insets(3, 3, 0, 10), 0, 0));
        contentPane.add(this.showDownloadURL, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0, 13, 0, new Insets(3, 3, 0, 10), 0, 0));
    }
    
    public static void main(final String[] args) {
    }
    
    static {
        BLACK = Color.black;
        DARK_GREEN = new Color(32768);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        WHITE = Color.white;
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
    }
}
