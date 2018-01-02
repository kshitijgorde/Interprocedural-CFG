// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.mimecheck;

import com.mindprod.common11.Build;
import com.mindprod.common13.HybridJ;
import java.net.MalformedURLException;
import java.awt.GridBagConstraints;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import com.mindprod.common13.JEButton;
import com.mindprod.common15.FontFactory;
import java.awt.Insets;
import com.mindprod.common13.CMPAboutJBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import com.mindprod.common15.Laf;
import javax.swing.JMenuBar;
import java.awt.Image;
import java.io.IOException;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.awt.Component;
import javax.swing.Icon;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Container;
import com.mindprod.common11.VersionCheck;
import java.net.URL;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.util.HashMap;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JApplet;

public final class MimeCheck extends JApplet implements Runnable
{
    private static final int APPLET_HEIGHT = 385;
    private static final int APPLET_WIDTH = 672;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2004-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2010-03-24";
    private static final String TITLE_STRING = "Mime Check";
    private static final String VERSION_STRING = "4.4";
    private static final Color BACKGROUND_FOR_APPLET;
    private static final Color BACKGROUND_FOR_FIELD;
    private static final Color BACKGROUND_FOR_RESULT;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_TEXT;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private static final Font FONT_FOR_USUAL;
    private static HashMap<String, String[]> eToM;
    private ImageIcon greenBall;
    private ImageIcon redBall;
    private JButton testButton;
    private JLabel ball;
    private JLabel instructions;
    private JLabel properMimeTypeLabel;
    private JLabel serverMimeTypeLabel;
    private JLabel title;
    private JLabel title2;
    private JLabel urlLabel;
    private JTextArea properMimeTypes;
    private JTextField serverMimeType;
    private JTextField urlField;
    private URL url;
    private boolean asApplet;
    
    public MimeCheck() {
        this.asApplet = true;
    }
    
    public MimeCheck(final boolean asApplet) {
        this.asApplet = true;
        this.asApplet = asApplet;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this)) {
            return;
        }
        this.getMimesDat();
        this.buildMenu();
        final Container contentPane = this.getContentPane();
        contentPane.setBackground(MimeCheck.BACKGROUND_FOR_APPLET);
        contentPane.setLayout(new GridBagLayout());
        this.createComponents();
        this.layoutComponents(contentPane);
        this.hookComponents();
        if (this.asApplet) {
            final String defaultURL = this.getParameter("URL");
            if (defaultURL != null && defaultURL.length() != 0) {
                this.urlField.setText(defaultURL);
                this.test();
            }
        }
    }
    
    public void run() {
        String serverMime = this.getMimeTypeFromServer(this.url);
        System.out.println(serverMime);
        this.serverMimeType.setText(serverMime);
        this.serverMimeType.setBackground(MimeCheck.BACKGROUND_FOR_RESULT);
        final int place = serverMime.indexOf(59);
        if (place >= 0) {
            serverMime = serverMime.substring(0, place).trim();
        }
        boolean correct = false;
        final String[] arr$;
        final String[] expectedMimes = arr$ = getProperMimes(this.url);
        for (final String expectedMime : arr$) {
            if (serverMime.equals(expectedMime)) {
                correct = true;
                break;
            }
        }
        this.ball.setIcon(correct ? this.greenBall : this.redBall);
        this.testButton.setEnabled(true);
        this.repaint();
    }
    
    private static ImageIcon createImageIcon(final String imageResourceName, final Component target) {
        try {
            final URL url = MimeCheck.class.getResource(imageResourceName);
            if (url == null) {
                System.err.println("createImageIcon cannot find resource " + imageResourceName);
                return null;
            }
            final Image image = target.getToolkit().createImage((ImageProducer)url.getContent());
            try {
                final MediaTracker tracker = new MediaTracker(target);
                tracker.addImage(image, 0);
                tracker.waitForID(0);
            }
            catch (InterruptedException ex) {}
            return new ImageIcon(image);
        }
        catch (IOException e) {
            System.err.println();
            e.printStackTrace(System.err);
            System.err.println();
            return null;
        }
    }
    
    private static String[] getProperMimes(final URL url) {
        final String file = url.getFile();
        if (file == null || file.length() == 0) {
            return new String[] { "no file in the url, can't guess expected MIME" };
        }
        final int place = file.lastIndexOf(46);
        if (place < 0) {
            return new String[] { "no .extension, can't guess expected MIME" };
        }
        final String[] mimes = getProperMimes(file.substring(place + 1).toLowerCase());
        if (mimes == null) {
            return new String[] { "unknown .extension, unknown MIME" };
        }
        return mimes;
    }
    
    private static String[] getProperMimes(final String extension) {
        return MimeCheck.eToM.get(extension);
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
                new CMPAboutJBox("Mime Check", "4.4", "Shows MIME types various servers are sending,", "", "freeware", "2010-03-24", 2004, "Roedy Green", "MIMECHECK", "1.5");
            }
        });
    }
    
    private void createComponents() {
        (this.title = new JLabel("Mime Check 4.4", 2)).setFont(MimeCheck.FONT_FOR_TITLE);
        this.title.setForeground(MimeCheck.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2010-03-24 build:9411")).setFont(MimeCheck.FONT_FOR_TITLE2);
        this.title2.setForeground(MimeCheck.FOREGROUND_FOR_TITLE);
        (this.urlLabel = new JLabel("url")).setFont(MimeCheck.FONT_FOR_USUAL);
        this.urlLabel.setForeground(MimeCheck.FOREGROUND_FOR_LABEL);
        (this.urlField = new JTextField("http://mindprod.com/webstart/setclock.jnlp")).setEditable(true);
        this.urlField.setMargin(new Insets(2, 2, 2, 2));
        this.urlField.setFont(MimeCheck.FONT_FOR_USUAL);
        this.urlField.setForeground(MimeCheck.FOREGROUND_FOR_TEXT);
        this.urlField.setBackground(MimeCheck.BACKGROUND_FOR_FIELD);
        (this.serverMimeTypeLabel = new JLabel("server MIME type")).setFont(MimeCheck.FONT_FOR_USUAL);
        this.serverMimeTypeLabel.setForeground(MimeCheck.FOREGROUND_FOR_LABEL);
        (this.serverMimeType = new JTextField("application/x-java-jnlp-file")).setEditable(false);
        this.serverMimeType.setMargin(new Insets(2, 2, 2, 2));
        this.serverMimeType.setFont(MimeCheck.FONT_FOR_USUAL);
        this.serverMimeType.setForeground(MimeCheck.FOREGROUND_FOR_TITLE);
        this.serverMimeType.setBackground(MimeCheck.BACKGROUND_FOR_RESULT);
        (this.properMimeTypeLabel = new JLabel("proper MIME type")).setFont(MimeCheck.FONT_FOR_USUAL);
        this.properMimeTypeLabel.setForeground(MimeCheck.FOREGROUND_FOR_LABEL);
        (this.properMimeTypes = new JTextArea("application/x-java-jnlp-file")).setEditable(false);
        this.properMimeTypes.setMargin(new Insets(2, 2, 2, 2));
        this.properMimeTypes.setFont(MimeCheck.FONT_FOR_USUAL);
        this.properMimeTypes.setForeground(MimeCheck.FOREGROUND_FOR_TITLE);
        this.properMimeTypes.setBackground(MimeCheck.BACKGROUND_FOR_RESULT);
        this.ball = new JLabel();
        this.redBall = createImageIcon("redball.png", this.ball);
        this.greenBall = createImageIcon("greenball.png", this.ball);
        this.ball.setIcon(this.greenBall);
        (this.instructions = new JLabel("Enter URL of a document you want to check, and hit TEST.")).setFont(FontFactory.build("Dialog", 0, 13));
        this.instructions.setForeground(MimeCheck.FOREGROUND_FOR_INSTRUCTIONS);
        (this.testButton = new JEButton("Test")).setToolTipText("Test if the server is issuing the correct MIME type");
    }
    
    private String getMimeTypeFromServer(final URL url) {
        try {
            final HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
            if (urlc == null) {
                return "Error: Unable to make a connection";
            }
            urlc.setRequestMethod("GET");
            urlc.setRequestProperty("Connection", "close");
            urlc.setAllowUserInteraction(false);
            urlc.setDoInput(true);
            urlc.setDoOutput(false);
            urlc.setUseCaches(false);
            urlc.connect();
            final int responseCode = urlc.getResponseCode();
            if (responseCode != 200) {
                return "Error: response code " + responseCode + " : " + urlc.getResponseMessage();
            }
            final String mime = urlc.getContentType();
            if (mime == null || mime.length() == 0) {
                return "no MIME code specified";
            }
            urlc.disconnect();
            return mime;
        }
        catch (IOException e) {
            return "Error: server not responding.";
        }
    }
    
    private void getMimesDat() {
        try {
            final InputStream is = MimeCheck.class.getResourceAsStream("mimes.dat");
            final BufferedInputStream bis = new BufferedInputStream(is, 8192);
            final DataInputStream dis = new DataInputStream(bis);
            final int recCount = dis.readInt();
            MimeCheck.eToM = new HashMap<String, String[]>(recCount * 150 / 100);
            for (int rec = 0; rec < recCount; ++rec) {
                final String ext = dis.readUTF();
                final int mimeCount = dis.readInt();
                final String[] mimes = new String[mimeCount];
                for (int i = 0; i < mimeCount; ++i) {
                    mimes[i] = dis.readUTF();
                }
                MimeCheck.eToM.put(ext, mimes);
            }
            dis.close();
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unable to retrieve mime database resource in jar");
        }
    }
    
    private void hookComponents() {
        this.testButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final Object object = e.getSource();
                if (object == MimeCheck.this.testButton) {
                    MimeCheck.this.test();
                }
            }
        });
    }
    
    private void layoutComponents(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 5, 5), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(10, 5, 5, 5), 0, 0));
        contentPane.add(this.urlLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 10, 5, 5), 0, 0));
        contentPane.add(this.serverMimeTypeLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 10, 5, 5), 0, 0));
        contentPane.add(this.properMimeTypeLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, 12, 0, new Insets(5, 10, 5, 5), 0, 0));
        contentPane.add(this.urlField, new GridBagConstraints(1, 1, 1, 1, 100.0, 0.0, 17, 1, new Insets(5, 5, 5, 5), 0, 0));
        contentPane.add(this.serverMimeType, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, 17, 1, new Insets(5, 5, 5, 5), 0, 0));
        contentPane.add(this.ball, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 5, 5, 10), 0, 0));
        contentPane.add(this.properMimeTypes, new GridBagConstraints(1, 3, 1, 1, 0.0, 100.0, 17, 1, new Insets(5, 5, 5, 5), 0, 0));
        contentPane.add(this.instructions, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, 17, 1, new Insets(5, 10, 10, 5), 0, 0));
        contentPane.add(this.testButton, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 10, 10), 0, 0));
    }
    
    private void test() {
        this.url = null;
        try {
            this.url = new URL(this.urlField.getText());
        }
        catch (MalformedURLException e) {
            this.properMimeTypes.setText("malformed URL");
            this.serverMimeType.setText("malformed URL");
            this.ball.setIcon(this.redBall);
            return;
        }
        final String[] properMimes = getProperMimes(this.url);
        final StringBuilder sb = new StringBuilder(500);
        for (final String mime : properMimes) {
            sb.append(mime);
            sb.append('\n');
        }
        this.properMimeTypes.setText(sb.toString());
        this.serverMimeTypeLabel.setText("server MIME type" + ((properMimes.length > 1) ? "s" : ""));
        this.testButton.setEnabled(false);
        this.serverMimeType.setText("checking with the server...");
        this.serverMimeType.setBackground(Color.WHITE);
        this.repaint();
        Thread.yield();
        new Thread(this).start();
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new MimeCheck(false), "Mime Check 4.4", 672, 385);
    }
    
    static {
        BACKGROUND_FOR_APPLET = Build.BACKGROUND_FOR_BLENDING;
        BACKGROUND_FOR_FIELD = Color.WHITE;
        BACKGROUND_FOR_RESULT = new Color(16187391);
        FOREGROUND_FOR_INSTRUCTIONS = new Color(32768);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_TEXT = Color.blue;
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
        FONT_FOR_USUAL = FontFactory.build("Dialog", 0, 15);
    }
}
