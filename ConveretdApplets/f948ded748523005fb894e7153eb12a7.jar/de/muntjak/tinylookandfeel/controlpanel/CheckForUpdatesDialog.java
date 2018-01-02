// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.controlpanel;

import java.awt.Point;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.ConnectException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JSeparator;
import java.awt.FlowLayout;
import java.awt.Dialog;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JDialog;

public class CheckForUpdatesDialog extends JDialog
{
    private static final String CHECK_UPDATES_URL = "http://www.muntjak.de/hans/java/tinylaf/checkforupdate.html";
    
    private CheckForUpdatesDialog(final Frame frame) {
        super(frame, "Check for Updates", true);
        this.setupUI(frame);
    }
    
    static void showDialog(final Frame frame) {
        new CheckForUpdatesDialog(frame);
    }
    
    private void setupUI(final Frame frame) {
        this.getContentPane().setLayout(new BorderLayout(0, 0));
        final JPanel panel = new JPanel(new BorderLayout(0, 12));
        final JLabel label = new JLabel("<html>When checking for updates, TinyLaF will connect to <b>muntjak.de</b><br>via HTTP. No personal data will be transmitted.");
        label.setBorder(new EmptyBorder(8, 8, 0, 8));
        panel.add(label, "North");
        final JButton button = new JButton("Check for updates now");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final String access$000 = CheckForUpdatesDialog.this.checkForUpdates();
                final int index = access$000.indexOf("Exception was: ");
                if (index != -1) {
                    JOptionPane.showMessageDialog(CheckForUpdatesDialog.this, access$000, access$000.substring(index + 15), -1);
                }
                else if (access$000.startsWith("No ")) {
                    JOptionPane.showMessageDialog(CheckForUpdatesDialog.this, access$000, "Update Information", -1);
                }
                else {
                    new UpdateDialog(CheckForUpdatesDialog.this, access$000);
                }
            }
        });
        final JPanel panel2 = new JPanel(new FlowLayout(1, 0, 8));
        panel2.add(button);
        panel.add(panel2, "Center");
        panel.add(new JSeparator(), "South");
        this.getContentPane().add(panel, "Center");
        final JButton defaultButton = new JButton("Close");
        this.getRootPane().setDefaultButton(defaultButton);
        defaultButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                CheckForUpdatesDialog.this.dispose();
            }
        });
        final JPanel panel3 = new JPanel(new FlowLayout(1, 0, 8));
        panel3.add(defaultButton);
        this.getContentPane().add(panel3, "South");
        this.pack();
        this.getSize();
        this.setLocation(frame.getLocationOnScreen().x + (frame.getWidth() - this.getSize().width) / 2, frame.getLocationOnScreen().y + (frame.getHeight() - this.getSize().height) / 2);
        this.setVisible(true);
    }
    
    private String checkForUpdates() {
        final String checkForUpdate = this.checkForUpdate();
        if (checkForUpdate.indexOf("Exception") != -1) {
            return checkForUpdate;
        }
        if (!checkForUpdate.startsWith("TinyLaF ") || !checkForUpdate.endsWith(")") || checkForUpdate.indexOf("(") == -1 || checkForUpdate.indexOf("/") == -1) {
            return "An exception occured while checking for updates.\n\nException was: Invalid response.";
        }
        if (!"1.3.8".equals(checkForUpdate.substring(8, checkForUpdate.indexOf("(") - 1))) {
            return checkForUpdate;
        }
        return "No updated version of TinyLaF available.";
    }
    
    private String checkForUpdate() {
        InputStream inputStream;
        try {
            final URL url = new URL("http://www.muntjak.de/hans/java/tinylaf/checkforupdate.html");
            try {
                final HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestProperty("User-Agent", "TinyLaF");
                final Object content = httpURLConnection.getContent();
                if (!(content instanceof InputStream)) {
                    return "An exception occured while checking for updates.\n\nException was: Content is no InputStream";
                }
                inputStream = (InputStream)content;
            }
            catch (ConnectException ex) {
                return "An exception occured while checking for updates.\n\nException was: " + ex.getClass().getName();
            }
            catch (UnknownHostException ex2) {
                return "An exception occured while checking for updates.\n\nException was: " + ex2.getClass().getName();
            }
            catch (IOException ex3) {
                return "An exception occured while checking for updates.\n\nException was: " + ex3.getClass().getName();
            }
        }
        catch (MalformedURLException ex4) {
            return "An exception occured while checking for updates.\n\nException was: " + ex4.getClass().getName();
        }
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            final StringBuffer sb = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            bufferedReader.close();
            return sb.toString();
        }
        catch (IOException ex5) {
            return "An exception occured while checking for updates.\n\nException was: " + ex5.getClass().getName();
        }
    }
    
    private class UpdateDialog extends JDialog
    {
        UpdateDialog(final Dialog dialog, final String s) {
            super(CheckForUpdatesDialog.this, "Update Information", true);
            this.setDefaultCloseOperation(2);
            this.getContentPane().setLayout(new BorderLayout());
            final String string = "<html>An updated version of TinyLaF is available:<br>" + s + "<br>" + "It can be downloaded at www.muntjak.de/hans/java/tinylaf/.";
            final JPanel panel = new JPanel(new FlowLayout(1, 12, 8));
            panel.add(new JLabel(string));
            this.getContentPane().add(panel, "Center");
            final JPanel panel2 = new JPanel(new FlowLayout(1, 8, 10));
            final JButton button = new JButton("Copy Link");
            button.addActionListener(new ActionListener() {
                private final /* synthetic */ UpdateDialog this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    final Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    if (systemClipboard == null) {
                        JOptionPane.showMessageDialog(this.this$1, "System Clipboard not available.", "Error", 0);
                    }
                    else {
                        final StringSelection stringSelection = new StringSelection("http://www.muntjak.de/hans/java/tinylaf/");
                        systemClipboard.setContents(stringSelection, stringSelection);
                    }
                }
            });
            panel2.add(button);
            final JButton defaultButton = new JButton("Close");
            this.getRootPane().setDefaultButton(defaultButton);
            defaultButton.addActionListener(new ActionListener() {
                private final /* synthetic */ UpdateDialog this$1 = this$1;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    this.this$1.dispose();
                }
            });
            panel2.add(defaultButton);
            this.getContentPane().add(panel2, "South");
            this.pack();
            final Point locationOnScreen;
            final Point location = locationOnScreen = dialog.getLocationOnScreen();
            locationOnScreen.x += (dialog.getWidth() - this.getWidth()) / 2;
            final Point point = location;
            point.y += (dialog.getHeight() - this.getHeight()) / 2;
            this.setLocation(location);
            this.setVisible(true);
        }
    }
}
