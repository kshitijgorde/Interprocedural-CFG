// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import javax.swing.JComponent;
import java.net.URLConnection;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import de.mud.jta.PluginListener;
import java.net.MalformedURLException;
import java.net.URL;
import de.mud.jta.PluginConfig;
import de.mud.jta.event.ConfigurationListener;
import java.net.URLEncoder;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Frame;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import de.mud.jta.PluginBus;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JDialog;
import javax.swing.JMenu;
import java.util.Hashtable;
import java.awt.event.ActionListener;
import de.mud.jta.VisualPlugin;
import de.mud.jta.FilterPlugin;
import de.mud.jta.Plugin;

public class Capture extends Plugin implements FilterPlugin, VisualPlugin, ActionListener
{
    private static final boolean personalJava = false;
    private static final int debug = 0;
    protected Hashtable remoteUrlList;
    protected JMenu menu;
    protected JDialog errorDialog;
    protected JDialog fileDialog;
    protected JDialog doneDialog;
    protected boolean captureEnabled;
    private JMenuItem start;
    private JMenuItem stop;
    private JMenuItem clear;
    private JFrame frame;
    private JTextArea textArea;
    private JTextField fileName;
    protected FilterPlugin source;
    
    public Capture(final PluginBus bus, final String id) {
        super(bus, id);
        this.remoteUrlList = new Hashtable();
        this.captureEnabled = false;
        this.frame = new JFrame("Java Telnet Applet: Captured Text");
        this.frame.getContentPane().setLayout(new BorderLayout());
        this.frame.getContentPane().add(this.textArea = new JTextArea(24, 80), "Center");
        this.textArea.setFont(new Font("Monospaced", 0, 12));
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                Capture.this.frame.setVisible(false);
            }
        });
        this.frame.pack();
        this.errorDialog = new JDialog(this.frame, "Error", true);
        this.errorDialog.getContentPane().setLayout(new BorderLayout());
        this.errorDialog.getContentPane().add(new JLabel("Cannot store data on remote server!"), "North");
        JPanel panel = new JPanel();
        JButton button = new JButton("Close Dialog");
        panel.add(button);
        this.errorDialog.getContentPane().add(panel, "South");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Capture.this.errorDialog.setVisible(false);
            }
        });
        this.doneDialog = new JDialog(this.frame, "Success", true);
        this.doneDialog.getContentPane().setLayout(new BorderLayout());
        this.doneDialog.getContentPane().add(new JLabel("Successfully saved data!"), "North");
        panel = new JPanel();
        button = new JButton("Close Dialog");
        panel.add(button);
        this.doneDialog.getContentPane().add(panel, "South");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Capture.this.errorDialog.setVisible(false);
            }
        });
        this.fileDialog = new JDialog(this.frame, "Enter File Name", true);
        this.fileDialog.getContentPane().setLayout(new BorderLayout());
        final ActionListener saveFileListener = new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String params = Capture.this.remoteUrlList.get("URL.file.params.orig");
                params = ((params == null) ? "" : (params + "&"));
                Capture.this.remoteUrlList.put("URL.file.params", params + "file=" + URLEncoder.encode(Capture.this.fileName.getText()));
                Capture.this.saveFile("URL.file");
                Capture.this.fileDialog.setVisible(false);
            }
        };
        panel = new JPanel();
        panel.add(new JLabel("File Name: "));
        panel.add(this.fileName = new JTextField(30));
        this.fileName.addActionListener(saveFileListener);
        this.fileDialog.getContentPane().add(panel, "Center");
        panel = new JPanel();
        panel.add(button = new JButton("Cancel"));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Capture.this.fileDialog.setVisible(false);
            }
        });
        panel.add(button = new JButton("Save File"));
        button.addActionListener(saveFileListener);
        this.fileDialog.getContentPane().add(panel, "South");
        this.fileDialog.pack();
        this.menu = new JMenu("Capture");
        (this.start = new JMenuItem("Start")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Capture.this.captureEnabled = true;
                Capture.this.start.setEnabled(false);
                Capture.this.stop.setEnabled(true);
            }
        });
        this.menu.add(this.start);
        (this.stop = new JMenuItem("Stop")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Capture.this.captureEnabled = false;
                Capture.this.start.setEnabled(true);
                Capture.this.stop.setEnabled(false);
            }
        });
        this.stop.setEnabled(false);
        this.menu.add(this.stop);
        (this.clear = new JMenuItem("Clear")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Capture.this.textArea.setText("");
            }
        });
        this.menu.add(this.clear);
        this.menu.addSeparator();
        final JMenuItem view = new JMenuItem("View/Hide Text");
        view.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (Capture.this.frame.isVisible()) {
                    Capture.this.frame.setVisible(false);
                    Capture.this.frame.hide();
                }
                else {
                    Capture.this.frame.setVisible(true);
                    Capture.this.frame.show();
                }
            }
        });
        this.menu.add(view);
        bus.registerPluginListener(new ConfigurationListener() {
            private final /* synthetic */ Capture this$0;
            
            public void setConfiguration(final PluginConfig config) {
                JMenuItem save = new JMenuItem("Save As File");
                Capture.this.menu.add(save);
                String tmp;
                if ((tmp = config.getProperty("Capture", id, "file.url")) != null) {
                    try {
                        Capture.this.remoteUrlList.put("URL.file", new URL(tmp));
                        if ((tmp = config.getProperty("Capture", id, "file.params")) != null) {
                            Capture.this.remoteUrlList.put("URL.file.params.orig", tmp);
                        }
                        save.addActionListener(new ActionListener() {
                            private final /* synthetic */ Capture$10 this$1 = this$1;
                            
                            public void actionPerformed(final ActionEvent e) {
                                this.this$1.this$0.fileDialog.setVisible(true);
                            }
                        });
                        save.setActionCommand("URL.file");
                    }
                    catch (MalformedURLException e) {
                        System.err.println("capture url invalid: " + e);
                    }
                }
                else {
                    save.setEnabled(false);
                }
                int i = 1;
                while ((tmp = config.getProperty("Capture", id, i + ".url")) != null) {
                    try {
                        final String urlID = "URL." + i;
                        final URL remoteURL = new URL(tmp);
                        Capture.this.remoteUrlList.put(urlID, remoteURL);
                        if ((tmp = config.getProperty("Capture", id, i + ".params")) != null) {
                            Capture.this.remoteUrlList.put(urlID + ".params", tmp);
                        }
                        if ((tmp = config.getProperty("Capture", id, i + ".name")) != null) {
                            save = new JMenuItem("Save As " + tmp);
                        }
                        else {
                            save = new JMenuItem("Save As " + remoteURL.toString());
                        }
                        save.setEnabled(true);
                        save.addActionListener(Capture.this);
                        save.setActionCommand(urlID);
                        Capture.this.menu.add(save);
                        ++i;
                    }
                    catch (MalformedURLException e2) {
                        System.err.println("capture url invalid: " + e2);
                    }
                }
            }
        });
    }
    
    public void actionPerformed(final ActionEvent e) {
        final String urlID = e.getActionCommand();
        this.saveFile(urlID);
    }
    
    private void saveFile(final String urlID) {
        final URL url = this.remoteUrlList.get(urlID);
        try {
            final URLConnection urlConnection = url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            final DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());
            String content = this.remoteUrlList.get(urlID + ".params");
            content = ((content == null) ? "" : (content + "&")) + "content=" + URLEncoder.encode(this.textArea.getText());
            out.writeBytes(content);
            out.flush();
            out.close();
            final BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String str;
            while (null != (str = in.readLine())) {
                System.out.println("Capture: " + str);
            }
            in.close();
            this.doneDialog.pack();
            this.doneDialog.setVisible(true);
        }
        catch (IOException ioe) {
            System.err.println("Capture: cannot store text on remote server: " + url);
            ioe.printStackTrace();
            final JTextArea errorMsg = new JTextArea(ioe.toString(), 5, 30);
            errorMsg.setEditable(false);
            this.errorDialog.add(errorMsg, "Center");
            this.errorDialog.pack();
            this.errorDialog.setVisible(true);
        }
    }
    
    public void setFilterSource(final FilterPlugin source) {
        this.source = source;
    }
    
    public FilterPlugin getFilterSource() {
        return this.source;
    }
    
    public int read(final byte[] b) throws IOException {
        final int size = this.source.read(b);
        if (this.captureEnabled && size > 0) {
            final String tmp = new String(b, 0, size);
            this.textArea.append(tmp);
        }
        return size;
    }
    
    public void write(final byte[] b) throws IOException {
        if (this.captureEnabled) {
            this.textArea.append(new String(b));
        }
        this.source.write(b);
    }
    
    public JComponent getPluginVisual() {
        return null;
    }
    
    public JMenu getPluginMenu() {
        return this.menu;
    }
}
