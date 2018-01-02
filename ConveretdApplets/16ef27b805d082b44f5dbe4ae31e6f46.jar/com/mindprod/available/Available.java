// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.available;

import com.mindprod.common11.FontFactory;
import com.mindprod.common13.HybridJ;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Cursor;
import com.mindprod.common13.CMPAboutJBox;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import com.mindprod.common15.Laf;
import javax.swing.JMenuBar;
import com.mindprod.common13.JEButton;
import java.awt.Insets;
import javax.swing.event.ChangeEvent;
import java.net.UnknownHostException;
import com.mindprod.http.Probe;
import javax.swing.Icon;
import java.io.IOException;
import java.net.NetworkInterface;
import javax.swing.SwingUtilities;
import com.mindprod.common11.ST;
import java.net.InetAddress;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common11.VersionCheck;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

public final class Available extends JApplet implements ActionListener, ChangeListener, Runnable
{
    private static final int APPLET_HEIGHT = 410;
    private static final int APPLET_WIDTH = 600;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2010-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String INSTRUCTIONS1 = "Key the URL or IP of the server without the";
    private static final String INSTRUCTIONS2 = "lead http:// e.g. mindprod.com or 65.110.21.43";
    private static final String INSTRUCTIONS3 = "then click PROBE.";
    private static final String RELEASE_DATE = "2010-03-22";
    private static final String TITLE_STRING = "Available";
    private static final String VERSION_STRING = "1.1";
    private static final Color BACKGROUND_FOR_APPLET;
    private static final Color BACKGROUND_FOR_INPUT;
    private static final Color BACKGROUND_FOR_INSTRUCTIONS;
    private static final Color BACKGROUND_FOR_LABEL;
    private static final Color BACKGROUND_FOR_RESULT;
    private static final Color BACKGROUND_FOR_TITLE;
    private static final Color FOREGROUND_FOR_INPUT;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_RESULT;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_EDITABLE_FIELDS;
    private static final Font FONT_FOR_INSTRUCTIONS;
    private static final Font FONT_FOR_LABELS;
    private static final Font FONT_FOR_RESULTS;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private static final ImageIcon HTTP_DOWN_ICON;
    private static final ImageIcon HTTP_UP_ICON;
    private static final ImageIcon PING_DOWN_ICON;
    private static final ImageIcon PING_UP_ICON;
    private static final ImageIcon SERVER_NOT_FOUND_ICON;
    private static final int[] margin;
    private JButton probe;
    private JLabel domainLabel;
    private JLabel httpState;
    private JLabel httpStateIcon;
    private JLabel instructions1;
    private JLabel instructions2;
    private JLabel instructions3;
    private JLabel ipLabel;
    private JLabel pingState;
    private JLabel pingStateIcon;
    private JLabel title;
    private JLabel title2;
    private JLabel urlLabel;
    private JTextField domain;
    private JTextField ip;
    private JTextField url;
    
    public void actionPerformed(final ActionEvent e) {
        this.hit();
    }
    
    public void destroy() {
        this.domain = null;
        this.domainLabel = null;
        this.httpState = null;
        this.httpStateIcon = null;
        this.instructions1 = null;
        this.instructions2 = null;
        this.instructions3 = null;
        this.ip = null;
        this.ipLabel = null;
        this.pingState = null;
        this.pingStateIcon = null;
        this.probe = null;
        this.title = null;
        this.title2 = null;
        this.url = null;
        this.urlLabel = null;
    }
    
    public void init() {
        final Container contentPane = this.getContentPane();
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, contentPane)) {
            return;
        }
        this.buildMenu();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(Available.BACKGROUND_FOR_APPLET);
        this.buildComponents();
        this.layoutComponents(contentPane);
        this.installHooks();
    }
    
    public void run() {
        boolean httpReachable;
        try {
            final InetAddress address = InetAddress.getByName(this.url.getText());
            String host = address.getHostName();
            if (ST.isLegal(host, "0123456789.")) {
                host = address.getCanonicalHostName();
            }
            final String fHost = host;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Available.this.ip.setText(address.getHostAddress());
                    Available.this.domain.setText(fHost);
                }
            });
            boolean pingReachable;
            try {
                pingReachable = address.isReachable(null, 64, 10000);
            }
            catch (IOException e) {
                pingReachable = false;
            }
            final boolean fPingReachable = pingReachable;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    if (fPingReachable) {
                        Available.this.pingStateIcon.setIcon(Available.PING_UP_ICON);
                        Available.this.pingState.setText("ping reachable");
                    }
                    else {
                        Available.this.pingStateIcon.setIcon(Available.PING_DOWN_ICON);
                        Available.this.pingState.setText("ping not reachable");
                    }
                }
            });
            final int response = new Probe().send(this.url.getText(), 80, null);
            httpReachable = (0 <= response && response <= 399);
        }
        catch (UnknownHostException e2) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Available.this.ip.setText("Server not registered in DNS");
                    Available.this.domain.setText("unknown host");
                    Available.this.httpStateIcon.setIcon(Available.SERVER_NOT_FOUND_ICON);
                    Available.this.httpState.setText("server not found");
                    Available.this.pingStateIcon.setIcon(null);
                    Available.this.pingState.setText("");
                    Available.this.thaw();
                }
            });
            return;
        }
        catch (IOException e3) {
            httpReachable = false;
        }
        final boolean fHttpReachable = httpReachable;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (fHttpReachable) {
                    Available.this.httpStateIcon.setIcon(Available.HTTP_UP_ICON);
                    Available.this.httpState.setText("http is up");
                }
                else {
                    Available.this.httpStateIcon.setIcon(Available.HTTP_DOWN_ICON);
                    Available.this.httpState.setText("http is down");
                }
                Available.this.thaw();
            }
        });
    }
    
    public void start() {
        this.validate();
    }
    
    public void stateChanged(final ChangeEvent e) {
        this.hit();
    }
    
    public void stop() {
    }
    
    private void buildComponents() {
        (this.title = new JLabel("Available 1.1")).setForeground(Available.FOREGROUND_FOR_TITLE);
        this.title.setBackground(Available.BACKGROUND_FOR_TITLE);
        this.title.setFont(Available.FONT_FOR_TITLE);
        (this.title2 = new JLabel("released:2010-03-22 build:9411")).setFont(Available.FONT_FOR_TITLE2);
        this.title2.setForeground(Available.FOREGROUND_FOR_TITLE);
        this.title2.setBackground(Available.BACKGROUND_FOR_TITLE);
        (this.urlLabel = new JLabel("Server URL:")).setForeground(Available.FOREGROUND_FOR_LABEL);
        this.urlLabel.setBackground(Available.BACKGROUND_FOR_LABEL);
        this.urlLabel.setFont(Available.FONT_FOR_LABELS);
        (this.ipLabel = new JLabel("IP:")).setForeground(Available.FOREGROUND_FOR_LABEL);
        this.ipLabel.setBackground(Available.BACKGROUND_FOR_LABEL);
        this.ipLabel.setFont(Available.FONT_FOR_LABELS);
        (this.domainLabel = new JLabel("Domain:")).setForeground(Available.FOREGROUND_FOR_LABEL);
        this.domainLabel.setBackground(Available.BACKGROUND_FOR_LABEL);
        this.domainLabel.setFont(Available.FONT_FOR_LABELS);
        (this.url = new JTextField("", 2)).setEditable(true);
        this.url.setForeground(Available.FOREGROUND_FOR_INPUT);
        this.url.setBackground(Available.BACKGROUND_FOR_INPUT);
        this.url.setOpaque(true);
        this.url.setFont(Available.FONT_FOR_EDITABLE_FIELDS);
        this.url.setMargin(new Insets(2, 2, 2, 2));
        this.url.setToolTipText("Key the domain or IP of a server then hit Probe.");
        (this.probe = new JEButton("Probe")).setToolTipText("Probe the server.");
        (this.ip = new JTextField("", 2)).setEditable(false);
        this.ip.setForeground(Available.FOREGROUND_FOR_RESULT);
        this.ip.setBackground(Available.BACKGROUND_FOR_RESULT);
        this.ip.setOpaque(true);
        this.ip.setFont(Available.FONT_FOR_RESULTS);
        this.ip.setMargin(new Insets(2, 2, 2, 2));
        (this.domain = new JTextField("", 2)).setEditable(false);
        this.domain.setForeground(Available.FOREGROUND_FOR_RESULT);
        this.domain.setBackground(Available.BACKGROUND_FOR_RESULT);
        this.domain.setOpaque(true);
        this.domain.setFont(Available.FONT_FOR_RESULTS);
        this.domain.setMargin(new Insets(2, 2, 2, 2));
        this.httpStateIcon = new JLabel();
        (this.httpState = new JLabel()).setForeground(Available.FOREGROUND_FOR_LABEL);
        this.httpState.setBackground(Available.BACKGROUND_FOR_LABEL);
        this.httpState.setFont(Available.FONT_FOR_LABELS);
        this.pingStateIcon = new JLabel();
        (this.pingState = new JLabel()).setForeground(Available.FOREGROUND_FOR_LABEL);
        this.pingState.setBackground(Available.BACKGROUND_FOR_LABEL);
        this.pingState.setFont(Available.FONT_FOR_LABELS);
        (this.instructions1 = new JLabel("Key the URL or IP of the server without the")).setBackground(Available.BACKGROUND_FOR_INSTRUCTIONS);
        this.instructions1.setForeground(Available.FOREGROUND_FOR_INSTRUCTIONS);
        this.instructions1.setFont(Available.FONT_FOR_INSTRUCTIONS);
        (this.instructions2 = new JLabel("lead http:// e.g. mindprod.com or 65.110.21.43")).setBackground(Available.BACKGROUND_FOR_INSTRUCTIONS);
        this.instructions2.setForeground(Available.FOREGROUND_FOR_INSTRUCTIONS);
        this.instructions2.setFont(Available.FONT_FOR_INSTRUCTIONS);
        (this.instructions3 = new JLabel("then click PROBE.")).setBackground(Available.BACKGROUND_FOR_INSTRUCTIONS);
        this.instructions3.setForeground(Available.FOREGROUND_FOR_INSTRUCTIONS);
        this.instructions3.setFont(Available.FONT_FOR_INSTRUCTIONS);
    }
    
    private void buildMenu() {
        final JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);
        menubar.add(Laf.buildLookAndFeelMenu());
        final JMenu menuHelp = new JMenu("Help");
        menubar.add(menuHelp);
        final JMenuItem aboutItem = new JMenuItem("About");
        menuHelp.add(aboutItem);
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox("Available", "1.1", "Probe the availability of a server.", "Discover a server's domain and IP.", "freeware", "2010-03-22", 2010, "Roedy Green", "AVAILABLE", "1.5");
            }
        });
    }
    
    private void freeze() {
        this.setCursor(Cursor.getPredefinedCursor(3));
        this.probe.setEnabled(false);
        this.url.setEnabled(false);
        this.instructions1.setText("Wait . . .");
        this.instructions2.setText("");
        this.instructions3.setText("");
        this.pingStateIcon.setIcon(null);
        this.pingState.setText("");
        this.httpStateIcon.setIcon(null);
        this.httpState.setText("");
    }
    
    private void hit() {
        this.freeze();
        new Thread(this).start();
    }
    
    private void installHooks() {
        this.probe.addActionListener(this);
        this.url.addActionListener(this);
    }
    
    private void layoutComponents(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(Available.margin[0], Available.margin[1], 5, 5), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(1, 0, 3, 1, 0.0, 0.0, 17, 0, new Insets(Available.margin[0], 5, 5, Available.margin[3]), 0, 0));
        contentPane.add(this.urlLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, Available.margin[1], 5, 5), 0, 0));
        contentPane.add(this.url, new GridBagConstraints(1, 2, 3, 1, 1.0, 0.0, 17, 2, new Insets(5, 5, 5, Available.margin[3]), 300, 0));
        contentPane.add(this.probe, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 5, Available.margin[3]), 20, 10));
        contentPane.add(this.ipLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, Available.margin[1], 5, 5), 0, 0));
        contentPane.add(this.ip, new GridBagConstraints(1, 4, 1, 1, 1.0, 0.0, 17, 2, new Insets(5, 5, 5, 5), 0, 0));
        contentPane.add(this.domainLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, Available.margin[0], 5, 5), 0, 0));
        contentPane.add(this.domain, new GridBagConstraints(1, 5, 1, 1, 1.0, 0.0, 13, 2, new Insets(5, 5, 5, 5), 0, 0));
        contentPane.add(this.httpStateIcon, new GridBagConstraints(2, 4, 1, 2, 0.0, 0.0, 10, 0, new Insets(5, 5, 5, Available.margin[3]), 0, 0));
        contentPane.add(this.httpState, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0, 10, 0, new Insets(5, 5, 5, Available.margin[3]), 0, 0));
        contentPane.add(this.pingStateIcon, new GridBagConstraints(3, 4, 1, 2, 0.0, 0.0, 10, 0, new Insets(5, 5, 5, Available.margin[3]), 0, 0));
        contentPane.add(this.pingState, new GridBagConstraints(3, 6, 1, 1, 0.0, 0.0, 10, 0, new Insets(5, 5, 5, Available.margin[3]), 0, 0));
        contentPane.add(this.instructions1, new GridBagConstraints(1, 7, 3, 1, 1.0, 0.0, 17, 2, new Insets(5, 5, 1, Available.margin[3]), 0, 0));
        contentPane.add(this.instructions2, new GridBagConstraints(1, 8, 3, 1, 1.0, 0.0, 17, 2, new Insets(5, 5, 5, Available.margin[3]), 0, 0));
        contentPane.add(this.instructions3, new GridBagConstraints(1, 9, 3, 1, 1.0, 0.0, 17, 2, new Insets(5, 5, Available.margin[2], Available.margin[3]), 0, 0));
    }
    
    private void thaw() {
        this.probe.setEnabled(true);
        this.url.setEnabled(true);
        this.instructions1.setText("Key the URL or IP of the server without the");
        this.instructions2.setText("lead http:// e.g. mindprod.com or 65.110.21.43");
        this.instructions3.setText("then click PROBE.");
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new Available(), "Available 1.1", 600, 410);
    }
    
    static {
        BACKGROUND_FOR_APPLET = new Color(16445670);
        BACKGROUND_FOR_INPUT = new Color(16777215);
        BACKGROUND_FOR_INSTRUCTIONS = Available.BACKGROUND_FOR_APPLET;
        BACKGROUND_FOR_LABEL = Available.BACKGROUND_FOR_APPLET;
        BACKGROUND_FOR_RESULT = Color.WHITE;
        BACKGROUND_FOR_TITLE = Available.BACKGROUND_FOR_APPLET;
        FOREGROUND_FOR_INPUT = new Color(2003199);
        FOREGROUND_FOR_INSTRUCTIONS = new Color(25600);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_RESULT = new Color(1602765);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_EDITABLE_FIELDS = FontFactory.build("Dialog", 1, 17);
        FONT_FOR_INSTRUCTIONS = FontFactory.build("Dialog", 0, 14);
        FONT_FOR_LABELS = FontFactory.build("Dialog", 1, 15);
        FONT_FOR_RESULTS = FontFactory.build("Dialog", 1, 15);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
        HTTP_DOWN_ICON = new ImageIcon(Available.class.getResource("image/httpdown.png"));
        HTTP_UP_ICON = new ImageIcon(Available.class.getResource("image/httpup.png"));
        PING_DOWN_ICON = new ImageIcon(Available.class.getResource("image/pingdown.png"));
        PING_UP_ICON = new ImageIcon(Available.class.getResource("image/pingup.png"));
        SERVER_NOT_FOUND_ICON = new ImageIcon(Available.class.getResource("image/servernotfound.png"));
        margin = new int[] { 9, 12, 9, 12 };
    }
}
