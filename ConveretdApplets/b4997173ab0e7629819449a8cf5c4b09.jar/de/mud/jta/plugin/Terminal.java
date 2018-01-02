// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.net.MalformedURLException;
import java.awt.Scrollbar;
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
import de.mud.jta.event.FocusStatus;
import java.awt.Cursor;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import de.mud.jta.PluginMessage;
import java.io.IOException;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import de.mud.jta.PluginBus;
import java.util.Hashtable;
import java.awt.Menu;
import java.awt.Panel;
import de.mud.jta.event.SoundRequest;
import de.mud.terminal.vt320;
import java.awt.datatransfer.ClipboardOwner;
import de.mud.jta.VisualTransferPlugin;
import de.mud.jta.FilterPlugin;
import de.mud.jta.Plugin;

public class Terminal extends Plugin implements FilterPlugin, VisualTransferPlugin, ClipboardOwner, Runnable
{
    private static final boolean personalJava = false;
    private static final int debug = 0;
    protected vt320 terminal;
    protected String encoding;
    protected SoundRequest audioBeep;
    protected Panel tPanel;
    protected Menu menu;
    private Thread reader;
    private Hashtable colors;
    private boolean localecho_overridden;
    protected FilterPlugin source;
    
    public Terminal(final PluginBus bus, final String id) {
        super(bus, id);
        this.encoding = "latin1";
        this.audioBeep = null;
        this.reader = null;
        this.colors = new Hashtable();
        this.localecho_overridden = false;
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
        this.colors.put("gray", Color.white);
        this.menu = new Menu("Terminal");
        final Menu fgm = new Menu("Foreground");
        final Menu bgm = new Menu("Background");
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
            MenuItem item;
            fgm.add(item = new MenuItem(color));
            item.addActionListener(fgl);
            bgm.add(item = new MenuItem(color));
            item.addActionListener(bgl);
        }
        this.menu.add(fgm);
        this.menu.add(bgm);
        MenuItem item;
        this.menu.add(item = new MenuItem("Smaller Font"));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final Font font = Terminal.this.terminal.getFont();
                Terminal.this.terminal.setFont(new Font(font.getName(), font.getStyle(), font.getSize() - 1));
                if (Terminal.this.tPanel.getParent() != null) {
                    final Component parent = Terminal.this.tPanel.getParent();
                    if (parent instanceof Frame) {
                        ((Frame)parent).pack();
                    }
                    Terminal.this.tPanel.getParent().doLayout();
                    Terminal.this.tPanel.getParent().validate();
                }
            }
        });
        this.menu.add(item = new MenuItem("Larger Font"));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final Font font = Terminal.this.terminal.getFont();
                Terminal.this.terminal.setFont(new Font(font.getName(), font.getStyle(), font.getSize() + 1));
                if (Terminal.this.tPanel.getParent() != null) {
                    final Component parent = Terminal.this.tPanel.getParent();
                    if (parent instanceof Frame) {
                        ((Frame)parent).pack();
                    }
                    Terminal.this.tPanel.getParent().doLayout();
                    Terminal.this.tPanel.getParent().validate();
                }
            }
        });
        this.menu.add(item = new MenuItem("Buffer +50"));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Terminal.this.terminal.setBufferSize(Terminal.this.terminal.getBufferSize() + 50);
            }
        });
        this.menu.add(item = new MenuItem("Buffer -50"));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Terminal.this.terminal.setBufferSize(Terminal.this.terminal.getBufferSize() - 50);
            }
        });
        this.menu.addSeparator();
        this.menu.add(item = new MenuItem("Reset Terminal"));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Terminal.this.terminal.reset();
            }
        });
        this.terminal = new vt320() {
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
        };
        (this.tPanel = new Panel((LayoutManager)new BorderLayout()) {
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
                return Terminal.this.terminal.getTerminalID();
            }
        });
        bus.registerPluginListener(new WindowSizeListener() {
            public Dimension getWindowSize() {
                return Terminal.this.terminal.getScreenSize();
            }
        });
        bus.registerPluginListener(new LocalEchoListener() {
            public void setLocalEcho(final boolean echo) {
                if (!Terminal.this.localecho_overridden) {
                    Terminal.this.terminal.setLocalEcho(echo);
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
        if ((tmp = cfg.getProperty("Terminal", super.id, "foreground")) != null) {
            this.terminal.setForeground(Color.decode(tmp));
        }
        if ((tmp = cfg.getProperty("Terminal", super.id, "background")) != null) {
            this.terminal.setBackground(Color.decode(tmp));
        }
        if ((tmp = cfg.getProperty("Terminal", super.id, "print.color")) != null) {
            try {
                this.terminal.setColorPrinting(Boolean.valueOf(tmp));
            }
            catch (Exception e5) {
                this.error("Terminal.color.print: must be either true or false, not " + tmp);
            }
        }
        if ((tmp = cfg.getProperty("Terminal", super.id, "colorSet")) != null) {
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
                for (int i = 0; i < 8; ++i) {
                    if ((tmp = colorSet.getProperty("color" + i)) != null) {
                        if (this.colors.get(tmp) != null) {
                            set[i] = this.colors.get(tmp);
                        }
                        else {
                            try {
                                if (Color.getColor(tmp) != null) {
                                    set[i] = Color.getColor(tmp);
                                }
                                else {
                                    set[i] = Color.decode(tmp);
                                }
                            }
                            catch (Exception e3) {
                                this.error("ignoring unknown color code: " + tmp);
                            }
                        }
                    }
                }
                this.terminal.setColorSet(set);
            }
        }
        final String cFG = cfg.getProperty("Terminal", super.id, "cursor.foreground");
        final String cBG = cfg.getProperty("Terminal", super.id, "cursor.background");
        Label_0549: {
            if (cFG == null) {
                if (cBG == null) {
                    break Label_0549;
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
        if ((tmp = cfg.getProperty("Terminal", super.id, "border")) != null) {
            final String size = tmp;
            boolean raised = false;
            if ((tmp = cfg.getProperty("Terminal", super.id, "borderRaised")) != null) {
                raised = Boolean.valueOf(tmp);
            }
            this.terminal.setBorder(Integer.parseInt(size), raised);
        }
        if ((tmp = cfg.getProperty("Terminal", super.id, "localecho")) != null) {
            this.terminal.setLocalEcho(Boolean.valueOf(tmp));
            this.localecho_overridden = true;
        }
        if ((tmp = cfg.getProperty("Terminal", super.id, "scrollBar")) != null) {
            String direction = tmp;
            if (!direction.equals("none")) {
                if (!direction.equals("East") && !direction.equals("West")) {
                    direction = "East";
                }
                final Scrollbar scrollBar = new Scrollbar();
                this.tPanel.add(direction, scrollBar);
                this.terminal.setScrollbar(scrollBar);
            }
        }
        if ((tmp = cfg.getProperty("Terminal", super.id, "id")) != null) {
            this.terminal.setTerminalID(tmp);
        }
        if ((tmp = cfg.getProperty("Terminal", super.id, "buffer")) != null) {
            this.terminal.setBufferSize(Integer.parseInt(tmp));
        }
        if ((tmp = cfg.getProperty("Terminal", super.id, "size")) != null) {
            try {
                final int idx = tmp.indexOf(44);
                final int width = Integer.parseInt(tmp.substring(1, idx).trim());
                final int height = Integer.parseInt(tmp.substring(idx + 1, tmp.length() - 1).trim());
                this.terminal.setScreenSize(width, height);
            }
            catch (Exception e2) {
                this.error("screen size is wrong: " + tmp);
                this.error("error: " + e2);
            }
        }
        if ((tmp = cfg.getProperty("Terminal", super.id, "resize")) != null) {
            if (tmp.equals("font")) {
                this.terminal.setResizeStrategy(2);
            }
            else if (tmp.equals("screen")) {
                this.terminal.setResizeStrategy(1);
            }
            else {
                this.terminal.setResizeStrategy(0);
            }
        }
        if ((tmp = cfg.getProperty("Terminal", super.id, "font")) != null) {
            final String font = tmp;
            int style = 0;
            int fsize = 12;
            if ((tmp = cfg.getProperty("Terminal", super.id, "fontSize")) != null) {
                fsize = Integer.parseInt(tmp);
            }
            final String fontStyle = cfg.getProperty("Terminal", super.id, "fontStyle");
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
        if ((tmp = cfg.getProperty("Terminal", super.id, "keyCodes")) != null) {
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
                this.terminal.setKeyCodes(keyCodes);
            }
        }
        if ((tmp = cfg.getProperty("Terminal", super.id, "VMS")) != null) {
            this.terminal.setVMS(Boolean.valueOf(tmp));
        }
        if ((tmp = cfg.getProperty("Terminal", super.id, "IBM")) != null) {
            this.terminal.setIBMCharset(Boolean.valueOf(tmp));
        }
        if ((tmp = cfg.getProperty("Terminal", super.id, "encoding")) != null) {
            this.encoding = tmp;
        }
        if ((tmp = cfg.getProperty("Terminal", super.id, "beep")) != null) {
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
                    this.terminal.putString(new String(b, 0, n, this.encoding));
                }
                this.tPanel.repaint();
            }
            catch (IOException e) {
                this.reader = null;
                break;
            }
        }
    }
    
    public void setFilterSource(final FilterPlugin source) {
        this.source = source;
    }
    
    public int read(final byte[] b) throws IOException {
        return this.source.read(b);
    }
    
    public void write(final byte[] b) throws IOException {
        this.source.write(b);
    }
    
    public Component getPluginVisual() {
        return this.tPanel;
    }
    
    public Menu getPluginMenu() {
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
