// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.common11;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Button;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dialog;

public final class CMPAboutBox extends Dialog
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
    private Button dismissButton;
    private Label showAddr1;
    private Label showAddr2;
    private Label showAuthor;
    private Label showCMP;
    private Label showCopyright;
    private Label showDownloadURL;
    private Label showMailTo;
    private Label showMinJdkVersion;
    private Label showPhone;
    private Label showProgramVersionBuild;
    private Label showPurpose1;
    private Label showPurpose2;
    private Label showReleaseDate;
    private Label showRunningJdkVersion;
    private Label showStatus;
    private Label showVmName;
    
    public CMPAboutBox(final String progname, final String version, final String purpose1, final String purpose2, final String status, final String released, final int firstCopyrightYear, final String author, final String masterSite, final String minJdkVersion) {
        this(new Frame(progname + " " + version), progname, version, purpose1, purpose2, status, released, firstCopyrightYear, author, masterSite, minJdkVersion);
    }
    
    public CMPAboutBox(final Frame parent, final String progname, final String version, final String purpose1, final String purpose2, final String status, final String released, final int firstCopyrightYear, final String author, final String masterSite, final String minJdkVersion) {
        super(parent, progname + " " + version, false);
        this.guts(progname, version, purpose1, purpose2, status, released, firstCopyrightYear, author, masterSite, minJdkVersion);
    }
    
    public void addNotify() {
        super.addNotify();
        this.setBackground(CMPAboutBox.WHITE);
    }
    
    private void dismiss() {
        this.setVisible(false);
        this.dispose();
    }
    
    private void guts(final String progname, final String version, final String purpose1, final String purpose2, final String status, final String released, final int firstCopyrightYear, final String author, final String masterSite, final String minJdkVersion) {
        this.setSize(506, 436);
        this.setLocation(0, 0);
        this.setBackground(CMPAboutBox.WHITE);
        (this.showProgramVersionBuild = new Label(progname + " " + version + " build " + 9410, 1)).setFont(CMPAboutBox.FONT_FOR_TITLE);
        this.showProgramVersionBuild.setForeground(CMPAboutBox.FOREGROUND_FOR_TITLE);
        this.showProgramVersionBuild.setBackground(CMPAboutBox.WHITE);
        (this.showPurpose1 = new Label(purpose1, 1)).setFont(FontFactory.build("Dialog", 2, 12));
        this.showPurpose1.setForeground(CMPAboutBox.BLACK);
        this.showPurpose1.setBackground(CMPAboutBox.WHITE);
        if (purpose2 != null && purpose2.length() > 0) {
            (this.showPurpose2 = new Label(purpose2, 1)).setFont(FontFactory.build("Dialog", 2, 12));
            this.showPurpose2.setForeground(CMPAboutBox.BLACK);
            this.showPurpose2.setBackground(CMPAboutBox.WHITE);
        }
        (this.showStatus = new Label(status, 1)).setFont(FontFactory.build("Dialog", 1, 12));
        this.showStatus.setForeground(CMPAboutBox.FOREGROUND_FOR_LABEL);
        this.showStatus.setBackground(CMPAboutBox.WHITE);
        (this.showReleaseDate = new Label("released: " + released, 1)).setFont(FontFactory.build("Dialog", 0, 11));
        this.showReleaseDate.setForeground(CMPAboutBox.FOREGROUND_FOR_LABEL);
        this.showReleaseDate.setBackground(CMPAboutBox.WHITE);
        String copyright;
        if (firstCopyrightYear == 2011) {
            copyright = Integer.toString(2011);
        }
        else {
            copyright = firstCopyrightYear + "-" + 2011;
        }
        (this.showCopyright = new Label("copyright " + copyright, 0)).setFont(FontFactory.build("Dialog", 2, 11));
        this.showCopyright.setForeground(CMPAboutBox.DARK_GREEN);
        this.showCopyright.setBackground(CMPAboutBox.WHITE);
        (this.showAuthor = new Label(author, 0)).setFont(FontFactory.build("Dialog", 3, 11));
        this.showAuthor.setForeground(CMPAboutBox.DARK_GREEN);
        this.showAuthor.setBackground(CMPAboutBox.WHITE);
        (this.showCMP = new Label("Canadian Mind Products", 0)).setFont(FontFactory.build("Dialog", 3, 11));
        this.showCMP.setForeground(CMPAboutBox.DARK_GREEN);
        this.showCMP.setBackground(CMPAboutBox.WHITE);
        (this.showAddr1 = new Label("#101 - 2536 Wark Street", 0)).setFont(FontFactory.build("Dialog", 2, 11));
        this.showAddr1.setForeground(CMPAboutBox.DARK_GREEN);
        this.showAddr1.setBackground(CMPAboutBox.WHITE);
        (this.showAddr2 = new Label("Victoria, BC Canada V8T 4G8", 0)).setFont(FontFactory.build("Dialog", 2, 11));
        this.showAddr2.setForeground(CMPAboutBox.DARK_GREEN);
        this.showAddr2.setBackground(CMPAboutBox.WHITE);
        (this.showMinJdkVersion = new Label("requires: Java " + minJdkVersion + "+", 2)).setFont(FontFactory.build("Dialog", 2, 11));
        this.showMinJdkVersion.setForeground(CMPAboutBox.DARK_GREEN);
        this.showMinJdkVersion.setBackground(CMPAboutBox.WHITE);
        (this.showRunningJdkVersion = new Label("running: Java " + System.getProperty("java.version", "unknown"), 2)).setFont(FontFactory.build("Dialog", 2, 11));
        this.showRunningJdkVersion.setForeground(CMPAboutBox.DARK_GREEN);
        this.showRunningJdkVersion.setBackground(CMPAboutBox.WHITE);
        (this.showVmName = new Label(System.getProperty("java.vm.name", "unknown"), 2)).setFont(FontFactory.build("Dialog", 2, 11));
        this.showVmName.setForeground(CMPAboutBox.DARK_GREEN);
        this.showVmName.setBackground(CMPAboutBox.WHITE);
        (this.showPhone = new Label("phone: (250) 361-9093", 2)).setFont(FontFactory.build("Dialog", 2, 11));
        this.showPhone.setForeground(CMPAboutBox.DARK_GREEN);
        this.showPhone.setBackground(CMPAboutBox.WHITE);
        (this.showMailTo = new Label("roedyg@mindprod.com", 0)).setFont(FontFactory.build("Dialog", 2, 11));
        this.showMailTo.setForeground(CMPAboutBox.DARK_GREEN);
        this.showMailTo.setBackground(CMPAboutBox.WHITE);
        (this.showDownloadURL = new Label("http://mindprod.com/products.html#" + masterSite, 2)).setFont(FontFactory.build("Dialog", 2, 10));
        this.showDownloadURL.setForeground(CMPAboutBox.DARK_GREEN);
        this.showDownloadURL.setBackground(CMPAboutBox.WHITE);
        (this.dismissButton = new Button("Dismiss")).setFont(FontFactory.build("Dialog", 1, 15));
        this.layoutComponents();
        this.addFocusListener(new FocusAdapter() {
            public void focusGained(final FocusEvent e) {
                CMPAboutBox.this.dismissButton.requestFocus();
            }
        });
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                CMPAboutBox.this.dismiss();
            }
        });
        this.dismissButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final Object object = e.getSource();
                if (object == CMPAboutBox.this.dismissButton) {
                    CMPAboutBox.this.dismiss();
                }
            }
        });
        this.dismissButton.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent e) {
                CMPAboutBox.this.dismiss();
            }
            
            public void keyTyped(final KeyEvent e) {
                CMPAboutBox.this.dismiss();
            }
        });
        this.validate();
        this.setVisible(true);
    }
    
    private void layoutComponents() {
        this.setLayout(new GridBagLayout());
        this.add(this.showProgramVersionBuild, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, 10, 0, new Insets(10, 10, 0, 10), 0, 0));
        this.add(this.showPurpose1, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, 10, 0, new Insets(5, 10, 0, 10), 0, 0));
        if (this.showPurpose2 != null) {
            this.add(this.showPurpose2, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, 10, 0, new Insets(1, 10, 0, 10), 0, 0));
        }
        this.add(this.showStatus, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, 10, 0, new Insets(3, 10, 0, 10), 0, 0));
        this.add(this.showReleaseDate, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, 10, 0, new Insets(2, 10, 0, 10), 0, 0));
        this.add(this.showCopyright, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, 17, 0, new Insets(3, 10, 0, 3), 0, 0));
        this.add(this.showAuthor, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, 17, 0, new Insets(3, 10, 0, 3), 0, 0));
        this.add(this.showCMP, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, 17, 0, new Insets(3, 10, 0, 3), 0, 0));
        this.add(this.showAddr1, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, 17, 0, new Insets(3, 10, 0, 3), 0, 0));
        this.add(this.showAddr2, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0, 17, 0, new Insets(3, 10, 0, 3), 0, 0));
        this.add(this.showMailTo, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0, 17, 0, new Insets(3, 10, 0, 3), 0, 0));
        this.add(this.dismissButton, new GridBagConstraints(0, 11, 2, 1, 0.0, 0.0, 10, 0, new Insets(10, 10, 10, 10), 0, 0));
        this.add(this.showMinJdkVersion, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, 13, 0, new Insets(3, 3, 0, 10), 0, 0));
        this.add(this.showRunningJdkVersion, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, 13, 0, new Insets(3, 3, 0, 10), 0, 0));
        this.add(this.showVmName, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, 13, 0, new Insets(3, 3, 0, 10), 0, 0));
        this.add(this.showPhone, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0, 13, 0, new Insets(3, 3, 0, 10), 0, 0));
        this.add(this.showDownloadURL, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0, 13, 0, new Insets(3, 3, 0, 10), 0, 0));
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
