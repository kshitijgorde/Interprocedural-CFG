// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import javax.swing.JComponent;
import java.net.MalformedURLException;
import javax.swing.JScrollBar;
import java.net.URL;
import java.util.Properties;
import java.util.Enumeration;
import de.mud.jta.event.ReturnFocusListener;
import de.mud.jta.PluginConfig;
import de.mud.jta.event.ConfigurationListener;
import de.mud.jta.event.LocalEchoListener;
import java.awt.Dimension;
import de.mud.jta.event.WindowSizeListener;
import de.mud.jta.event.TerminalTypeListener;
import de.mud.jta.PluginListener;
import de.mud.jta.event.OnlineStatusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import de.mud.jta.event.FocusStatus;
import java.awt.Cursor;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import de.mud.terminal.VDUBuffer;
import de.mud.jta.event.SetWindowSizeRequest;
import de.mud.jta.event.TelnetCommandRequest;
import de.mud.jta.PluginMessage;
import java.io.IOException;
import de.mud.jta.PluginBus;
import java.awt.Color;
import java.awt.datatransfer.Clipboard;
import java.util.Hashtable;
import javax.swing.JMenu;
import javax.swing.JPanel;
import de.mud.jta.event.SoundRequest;
import de.mud.terminal.vt320;
import de.mud.terminal.SwingTerminal;
import java.awt.datatransfer.ClipboardOwner;
import de.mud.jta.VisualTransferPlugin;
import de.mud.jta.FilterPlugin;
import de.mud.jta.Plugin;

public class Terminal extends Plugin implements FilterPlugin, VisualTransferPlugin, ClipboardOwner, Runnable
{
    private static final boolean personalJava = false;
    private static final int debug = 0;
    protected SwingTerminal terminal;
    protected vt320 emulation;
    protected String encoding;
    protected SoundRequest audioBeep;
    protected JPanel tPanel;
    protected JMenu menu;
    private Thread reader;
    private Hashtable colors;
    private boolean localecho_overridden;
    private Clipboard clipboard;
    protected FilterPlugin source;
    
    private Color codeToColor(final String code) {
        if (this.colors.get(code) != null) {
            return this.colors.get(code);
        }
        try {
            if (Color.getColor(code) != null) {
                return Color.getColor(code);
            }
            return Color.decode(code);
        }
        catch (Exception e) {
            try {
                return Color.decode(code);
            }
            catch (Exception ex) {
                this.error("ignoring unknown color code: " + code);
                return null;
            }
        }
    }
    
    public Terminal(final PluginBus bus, final String id) {
        super(bus, id);
        this.encoding = "latin1";
        this.audioBeep = null;
        this.reader = null;
        this.colors = new Hashtable();
        this.localecho_overridden = false;
        this.clipboard = null;
        this.emulation = new vt320() {
            public void write(final byte[] b) {
                try {
                    Terminal.this.write(b);
                }
                catch (IOException e) {
                    Terminal.this.reader = null;
                }
            }
            
            public void beep() {
                if (Terminal.this.audioBeep != null) {
                    bus.broadcast(Terminal.this.audioBeep);
                }
            }
            
            public void sendTelnetCommand(final byte cmd) {
                bus.broadcast(new TelnetCommandRequest(cmd));
            }
            
            public void setWindowSize(final int c, final int r) {
                bus.broadcast(new SetWindowSizeRequest(c, r));
            }
        };
        this.terminal = new SwingTerminal(this.emulation);
        this.colors.put("black", Color.black);
        this.colors.put("red", Color.red);
        this.colors.put("green", Color.green);
        this.colors.put("yellow", Color.yellow);
        this.colors.put("blue", Color.blue);
        this.colors.put("magenta", Color.magenta);
        this.colors.put("orange", Color.orange);
        this.colors.put("pink", Color.pink);
        this.colors.put("cyan", Color.cyan);
        this.colors.put("white", Color.white);
        this.colors.put("gray", Color.gray);
        this.colors.put("darkgray", Color.darkGray);
        this.menu = new JMenu("Terminal");
        final JMenu fgm = new JMenu("Foreground");
        final JMenu bgm = new JMenu("Background");
        final Enumeration cols = this.colors.keys();
        final ActionListener fgl = new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Terminal.this.terminal.setForeground(Terminal.this.colors.get(e.getActionCommand()));
                Terminal.this.tPanel.repaint();
            }
        };
        final ActionListener bgl = new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Terminal.this.terminal.setBackground(Terminal.this.colors.get(e.getActionCommand()));
                Terminal.this.tPanel.repaint();
            }
        };
        while (cols.hasMoreElements()) {
            final String color = cols.nextElement();
            JMenuItem item;
            fgm.add(item = new JMenuItem(color));
            item.addActionListener(fgl);
            bgm.add(item = new JMenuItem(color));
            item.addActionListener(bgl);
        }
        this.menu.add(fgm);
        this.menu.add(bgm);
        JMenuItem item;
        this.menu.add(item = new JMenuItem("Smaller Font"));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final Font font = Terminal.this.terminal.getFont();
                Terminal.this.terminal.setFont(new Font(font.getName(), font.getStyle(), font.getSize() - 1));
                if (Terminal.this.tPanel.getParent() != null) {
                    Container parent = Terminal.this.tPanel;
                    do {
                        parent = parent.getParent();
                    } while (parent != null && !(parent instanceof JFrame));
                    if (parent instanceof JFrame) {
                        ((Frame)parent).pack();
                    }
                    Terminal.this.tPanel.getParent().doLayout();
                    Terminal.this.tPanel.getParent().validate();
                }
            }
        });
        this.menu.add(item = new JMenuItem("Larger Font"));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final Font font = Terminal.this.terminal.getFont();
                Terminal.this.terminal.setFont(new Font(font.getName(), font.getStyle(), font.getSize() + 1));
                if (Terminal.this.tPanel.getParent() != null) {
                    Container parent = Terminal.this.tPanel;
                    do {
                        parent = parent.getParent();
                    } while (parent != null && !(parent instanceof JFrame));
                    if (parent instanceof JFrame) {
                        ((Frame)parent).pack();
                    }
                    Terminal.this.tPanel.getParent().doLayout();
                    Terminal.this.tPanel.getParent().validate();
                }
            }
        });
        this.menu.add(item = new JMenuItem("Buffer +50"));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Terminal.this.emulation.setBufferSize(Terminal.this.emulation.getBufferSize() + 50);
            }
        });
        this.menu.add(item = new JMenuItem("Buffer -50"));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Terminal.this.emulation.setBufferSize(Terminal.this.emulation.getBufferSize() - 50);
            }
        });
        this.menu.addSeparator();
        this.menu.add(item = new JMenuItem("Reset Terminal"));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Terminal.this.emulation.reset();
            }
        });
        (this.tPanel = new JPanel((LayoutManager)new BorderLayout()) {
            public void update(final Graphics g) {
                this.paint(g);
            }
            
            public void print(final Graphics g) {
                Terminal.this.terminal.print(g);
            }
        }).add("Center", this.terminal);
        this.terminal.addFocusListener(new FocusListener() {
            public void focusGained(final FocusEvent evt) {
                Terminal.this.terminal.setCursor(Cursor.getPredefinedCursor(2));
                bus.broadcast(new FocusStatus(Terminal.this, evt));
            }
            
            public void focusLost(final FocusEvent evt) {
                Terminal.this.terminal.setCursor(Cursor.getDefaultCursor());
                bus.broadcast(new FocusStatus(Terminal.this, evt));
            }
        });
        try {
            this.clipboard = this.tPanel.getToolkit().getSystemClipboard();
            System.out.println("Got the clipboard reference ok - copy & paste enabled");
        }
        catch (Exception ex) {
            System.out.println("Failed to get clipboard - copy and paste will not work");
        }
        this.terminal.addMouseListener(new MouseListener() {
            public void mouseClicked(final MouseEvent me) {
                if (me.getButton() == 3 && Terminal.this.clipboard != null) {
                    Terminal.this.paste(Terminal.this.clipboard);
                }
            }
            
            public void mouseExited(final MouseEvent arg0) {
            }
            
            public void mousePressed(final MouseEvent arg0) {
            }
            
            public void mouseReleased(final MouseEvent me) {
                if (me.getButton() == 1 && Terminal.this.clipboard != null) {
                    final String selection = Terminal.this.terminal.getSelection();
                    if (selection != null && selection.trim().length() > 0) {
                        Terminal.this.copy(Terminal.this.clipboard);
                    }
                }
            }
            
            public void mouseEntered(final MouseEvent arg0) {
            }
        });
        bus.registerPluginListener(new OnlineStatusListener() {
            public void online() {
                if (Terminal.this.reader == null) {
                    Terminal.this.reader = new Thread(Terminal.this);
                    Terminal.this.reader.start();
                }
            }
            
            public void offline() {
                if (Terminal.this.reader != null) {
                    Terminal.this.reader = null;
                }
            }
        });
        bus.registerPluginListener(new TerminalTypeListener() {
            public String getTerminalType() {
                return Terminal.this.emulation.getTerminalID();
            }
        });
        bus.registerPluginListener(new WindowSizeListener() {
            public Dimension getWindowSize() {
                return new Dimension(Terminal.this.emulation.getColumns(), Terminal.this.emulation.getRows());
            }
        });
        bus.registerPluginListener(new LocalEchoListener() {
            public void setLocalEcho(final boolean echo) {
                if (!Terminal.this.localecho_overridden) {
                    Terminal.this.emulation.setLocalEcho(echo);
                }
            }
        });
        bus.registerPluginListener(new ConfigurationListener() {
            public void setConfiguration(final PluginConfig config) {
                Terminal.this.configure(config);
            }
        });
        bus.registerPluginListener(new ReturnFocusListener() {
            public void returnFocus() {
                Terminal.this.terminal.requestFocus();
            }
        });
    }
    
    private void configure(final PluginConfig cfg) {
        String tmp;
        if ((tmp = cfg.getProperty("Terminal", this.id, "foreground")) != null) {
            this.terminal.setForeground(Color.decode(tmp));
        }
        if ((tmp = cfg.getProperty("Terminal", this.id, "background")) != null) {
            this.terminal.setBackground(Color.decode(tmp));
        }
        if ((tmp = cfg.getProperty("Terminal", this.id, "print.color")) != null) {
            try {
                this.terminal.setColorPrinting(Boolean.valueOf(tmp));
            }
            catch (Exception e5) {
                this.error("Terminal.color.print: must be either true or false, not " + tmp);
            }
        }
        System.err.print("colorSet: ");
        if ((tmp = cfg.getProperty("Terminal", this.id, "colorSet")) != null) {
            System.err.println(tmp);
            Properties colorSet = new Properties();
            try {
                colorSet.load(this.getClass().getResourceAsStream(tmp));
            }
            catch (Exception e) {
                try {
                    colorSet.load(new URL(tmp).openStream());
                }
                catch (Exception ue) {
                    this.error("cannot find colorSet: " + tmp);
                    this.error("resource access failed: " + e);
                    this.error("URL access failed: " + ue);
                    colorSet = null;
                }
            }
            if (colorSet != null) {
                final Color[] set = this.terminal.getColorSet();
                Color color = null;
                for (int i = 0; i < 8; ++i) {
                    if ((tmp = colorSet.getProperty("color" + i)) != null && (color = this.codeToColor(tmp)) != null) {
                        set[i] = color;
                    }
                }
                if ((tmp = colorSet.getProperty("bold")) != null && (color = this.codeToColor(tmp)) != null) {
                    set[8] = color;
                }
                if ((tmp = colorSet.getProperty("invert")) != null && (color = this.codeToColor(tmp)) != null) {
                    set[9] = color;
                }
                this.terminal.setColorSet(set);
            }
        }
        final String cFG = cfg.getProperty("Terminal", this.id, "cursor.foreground");
        final String cBG = cfg.getProperty("Terminal", this.id, "cursor.background");
        Label_0557: {
            if (cFG == null) {
                if (cBG == null) {
                    break Label_0557;
                }
            }
            try {
                final Color fg = (cFG == null) ? this.terminal.getBackground() : ((Color.getColor(cFG) != null) ? Color.getColor(cFG) : Color.decode(cFG));
                final Color bg = (cBG == null) ? this.terminal.getForeground() : ((Color.getColor(cBG) != null) ? Color.getColor(cBG) : Color.decode(cBG));
                this.terminal.setCursorColors(fg, bg);
            }
            catch (Exception e2) {
                this.error("ignoring unknown cursor color code: " + tmp);
            }
        }
        if ((tmp = cfg.getProperty("Terminal", this.id, "border")) != null) {
            final String size = tmp;
            boolean raised = false;
            if ((tmp = cfg.getProperty("Terminal", this.id, "borderRaised")) != null) {
                raised = Boolean.valueOf(tmp);
            }
            this.terminal.setBorder(Integer.parseInt(size), raised);
        }
        if ((tmp = cfg.getProperty("Terminal", this.id, "localecho")) != null) {
            this.emulation.setLocalEcho(Boolean.valueOf(tmp));
            this.localecho_overridden = true;
        }
        if ((tmp = cfg.getProperty("Terminal", this.id, "scrollBar")) != null) {
            String direction = tmp;
            if (!direction.equals("none")) {
                if (!direction.equals("East") && !direction.equals("West")) {
                    direction = "East";
                }
                final JScrollBar scrollBar = new JScrollBar();
                this.tPanel.add(direction, scrollBar);
                this.terminal.setScrollbar(scrollBar);
            }
        }
        if ((tmp = cfg.getProperty("Terminal", this.id, "id")) != null) {
            this.emulation.setTerminalID(tmp);
        }
        if ((tmp = cfg.getProperty("Terminal", this.id, "answerback")) != null) {
            this.emulation.setAnswerBack(tmp);
        }
        if ((tmp = cfg.getProperty("Terminal", this.id, "buffer")) != null) {
            this.emulation.setBufferSize(Integer.parseInt(tmp));
        }
        if ((tmp = cfg.getProperty("Terminal", this.id, "size")) != null) {
            try {
                final int idx = tmp.indexOf(44);
                final int width = Integer.parseInt(tmp.substring(1, idx).trim());
                final int height = Integer.parseInt(tmp.substring(idx + 1, tmp.length() - 1).trim());
                this.emulation.setScreenSize(width, height, false);
            }
            catch (Exception e2) {
                this.error("screen size is wrong: " + tmp);
                this.error("error: " + e2);
            }
        }
        if ((tmp = cfg.getProperty("Terminal", this.id, "resize")) != null) {
            if (tmp.equals("font")) {
                this.terminal.setResizeStrategy(1);
            }
            else if (tmp.equals("screen")) {
                this.terminal.setResizeStrategy(2);
            }
            else {
                this.terminal.setResizeStrategy(0);
            }
        }
        if ((tmp = cfg.getProperty("Terminal", this.id, "font")) != null) {
            final String font = tmp;
            int style = 0;
            int fsize = 12;
            if ((tmp = cfg.getProperty("Terminal", this.id, "fontSize")) != null) {
                fsize = Integer.parseInt(tmp);
            }
            final String fontStyle = cfg.getProperty("Terminal", this.id, "fontStyle");
            if (fontStyle == null || fontStyle.equals("plain")) {
                style = 0;
            }
            else if (fontStyle.equals("bold")) {
                style = 1;
            }
            else if (fontStyle.equals("italic")) {
                style = 2;
            }
            else if (fontStyle.equals("bold+italic")) {
                style = 3;
            }
            this.terminal.setFont(new Font(font, style, fsize));
        }
        if ((tmp = cfg.getProperty("Terminal", this.id, "keyCodes")) != null) {
            Properties keyCodes = new Properties();
            try {
                keyCodes.load(this.getClass().getResourceAsStream(tmp));
            }
            catch (Exception e3) {
                try {
                    keyCodes.load(new URL(tmp).openStream());
                }
                catch (Exception ue2) {
                    this.error("cannot find keyCodes: " + tmp);
                    this.error("resource access failed: " + e3);
                    this.error("URL access failed: " + ue2);
                    keyCodes = null;
                }
            }
            if (keyCodes != null) {
                this.emulation.setKeyCodes(keyCodes);
            }
        }
        if ((tmp = cfg.getProperty("Terminal", this.id, "VMS")) != null) {
            this.emulation.setVMS(Boolean.valueOf(tmp));
        }
        if ((tmp = cfg.getProperty("Terminal", this.id, "IBM")) != null) {
            this.emulation.setIBMCharset(Boolean.valueOf(tmp));
        }
        if ((tmp = cfg.getProperty("Terminal", this.id, "encoding")) != null) {
            this.encoding = tmp;
        }
        if ((tmp = cfg.getProperty("Terminal", this.id, "beep")) != null) {
            try {
                this.audioBeep = new SoundRequest(new URL(tmp));
            }
            catch (MalformedURLException e4) {
                this.error("incorrect URL for audio ping: " + e4);
            }
        }
        this.tPanel.setBackground(this.terminal.getBackground());
    }
    
    public void run() {
        final byte[] b = new byte[256];
        int n = 0;
        while (n >= 0) {
            try {
                n = this.read(b);
                if (n > 0) {
                    this.emulation.putString(new String(b, 0, n, this.encoding));
                }
                this.tPanel.repaint();
                continue;
            }
            catch (IOException e) {
                this.reader = null;
            }
            break;
        }
    }
    
    public void setFilterSource(final FilterPlugin source) {
        this.source = source;
    }
    
    public FilterPlugin getFilterSource() {
        return this.source;
    }
    
    public int read(final byte[] b) throws IOException {
        return this.source.read(b);
    }
    
    public void write(final byte[] b) throws IOException {
        this.source.write(b);
    }
    
    public JComponent getPluginVisual() {
        return this.tPanel;
    }
    
    public JMenu getPluginMenu() {
        return this.menu;
    }
    
    public void copy(final Clipboard clipboard) {
        final String data = this.terminal.getSelection();
        if (data == null) {
            return;
        }
        final StringSelection selection = new StringSelection(data);
        clipboard.setContents(selection, this);
    }
    
    public void paste(final Clipboard clipboard) {
        if (clipboard == null) {
            return;
        }
        final Transferable t = clipboard.getContents(this);
        try {
            final byte[] buffer = ((String)t.getTransferData(DataFlavor.stringFlavor)).getBytes();
            try {
                this.write(buffer);
            }
            catch (IOException e) {
                this.reader = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public void lostOwnership(final Clipboard clipboard, final Transferable contents) {
        this.terminal.clearSelection();
    }
}
