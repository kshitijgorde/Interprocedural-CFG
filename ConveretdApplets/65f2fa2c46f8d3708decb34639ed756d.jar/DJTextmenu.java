import java.applet.Applet;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.applet.AppletContext;
import java.util.Enumeration;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.MenuItem;
import java.util.NoSuchElementException;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.PopupMenu;
import java.util.Hashtable;
import java.awt.Menu;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class DJTextmenu extends baseApplet implements MouseMotionListener, MouseListener, ActionListener
{
    static final boolean DEMO = true;
    static final int NUMENTRIES = 50;
    Panel pnl;
    int nmenu;
    Menu[] ss;
    Hashtable[] sshash;
    DJTextmenu.Cnv[] canvas;
    String[] menuitem;
    PopupMenu[] pu;
    int[] Xoffset;
    int[] Yoffset;
    int[] numEntries;
    int numitems;
    int direction;
    int lastentry;
    FontMetrics fm;
    Color barColor;
    Color statusbarTextColor;
    Color statusbarMouseoverTextColor;
    Color panelBackgroundColor;
    Color underlineTextColor;
    int entry;
    boolean threeD;
    boolean underline;
    StringTokenizer tok;
    Graphics thisG;
    int lastxpos;
    String frame;
    int selectedtextentry;
    
    public void init() {
        super.init();
        ((Container)this).setLayout(null);
        ((Component)this).setSize(super.width, super.height);
        final int bs = this.getParameterInt("borderSize", 0);
        this.pnl.setBounds(bs, bs, super.width - bs * 2, super.height - bs * 2);
        this.pnl.setLayout(null);
        ((Component)this).setFont(new Font("Dialog", 0, super.appletFontSize));
        this.thisG = ((Component)this).getGraphics();
        this.fm = this.thisG.getFontMetrics();
        ((Container)this).add(this.pnl);
        this.pnl.setBackground(Color.cyan);
        ((Component)this).setBackground(super.backgroundColor);
        this.barColor = this.getParameterColor("barColor", Color.black);
        this.panelBackgroundColor = this.getParameterColor("panelbackgroundcolor", Color.white);
        this.statusbarTextColor = this.getParameterColor("StatusBarTextColor", Color.black);
        this.statusbarMouseoverTextColor = this.getParameterColor("StatusBarMouseOverTextColor", Color.red);
        this.frame = this.getParameterString("frame", "_top");
        this.direction = this.getParameterInt("direction", 1);
        this.pnl.setBackground(this.panelBackgroundColor);
        if (this.getParameterString("threeD", "false").toLowerCase().equals("true")) {
            this.threeD = true;
        }
        else {
            this.threeD = false;
        }
        if (this.getParameterString("underlineText", "false").toLowerCase().equals("true")) {
            this.underline = true;
        }
        else {
            this.underline = false;
        }
        this.underlineTextColor = this.getParameterColor("underlineTextColor", Color.black);
        for (int i = 0; ((Applet)this).getParameter("menuitem" + i) != null; ++i, ++this.numitems) {
            this.Xoffset[i] = this.getParameterInt("menuitem" + i + "Xoffset", 0);
            this.Yoffset[i] = this.getParameterInt("menuitem" + i + "Yoffset", 0);
            this.tok = new StringTokenizer(((Applet)this).getParameter("menuitem" + i), "|");
            try {
                this.menuitem[i] = this.tok.nextToken();
            }
            catch (NoSuchElementException ex) {
                this.menuitem[i] = " ";
            }
        }
        for (int i = 0; i < this.numitems; ++i) {
            this.canvas[i] = new DJTextmenu.Cnv(this);
            final int imOff = this.getParameterInt("textOffset", 40);
            final int w = 5;
            if (this.direction == 1) {
                ((Component)this.canvas[i]).setBounds(5, i * imOff, this.fm.stringWidth(this.menuitem[i]) + this.fm.charWidth('W'), this.fm.getHeight() + this.fm.getDescent());
                if (this.Xoffset[i] == 0) {
                    this.Xoffset[i] = this.fm.stringWidth(this.menuitem[i]) + 20;
                }
            }
            else {
                ((Component)this.canvas[i]).setBounds(this.lastxpos, this.pnl.getSize().height / 2 - this.fm.getHeight() / 2 - this.fm.getDescent(), this.fm.stringWidth(this.menuitem[i]) + this.fm.charWidth('W'), this.fm.getHeight() + this.fm.getDescent());
                if (this.Yoffset[i] == 0) {
                    this.Yoffset[i] = super.height - 4;
                }
            }
            this.lastxpos = this.lastxpos + this.fm.stringWidth(this.menuitem[i]) + imOff;
            this.canvas[i].setText(this.menuitem[i]);
            ((Component)this.canvas[i]).addMouseListener(this);
            ((Component)this.canvas[i]).addMouseMotionListener(this);
            this.pnl.add((Component)this.canvas[i]);
            this.pu[i] = new PopupMenu();
            this.pu[i] = (PopupMenu)this.addItems(this.pu[i], "item" + i + "menu0", this.entry);
            ++this.entry;
            this.pu[i].addActionListener(this);
            ((Component)this.canvas[i]).add(this.pu[i]);
            this.canvas[i].sethilight(false);
            ((Component)this.canvas[i]).repaint();
        }
        ((Component)this).repaint();
    }
    
    public Menu addItems(final Menu m, final String name, final int entry) {
        int i = 0;
        final Hashtable dataHash = new Hashtable();
        while (((Applet)this).getParameter(String.valueOf(name) + "item" + i) != null) {
            final StringTokenizer tok = new StringTokenizer(((Applet)this).getParameter(String.valueOf(name) + "item" + i), "|");
            final String s = new String(tok.nextToken());
            String value;
            if (tok.hasMoreTokens()) {
                value = new String(tok.nextToken());
            }
            else {
                value = "-";
            }
            if (s.startsWith("item")) {
                final Menu x = new Menu();
                x.setLabel(((Applet)this).getParameter(s));
                x.setFont(new Font("Dialog", 0, this.getParameterInt("menuFontSize", 12)));
                x.addActionListener(this);
                m.add(this.addItems(x, s, entry));
            }
            else {
                final MenuItem mi = new MenuItem();
                mi.setLabel(s);
                mi.setFont(new Font("Dialog", 0, this.getParameterInt("menuFontSize", 12)));
                m.add(mi);
                if (!s.equals("-")) {
                    dataHash.put(s, value);
                }
            }
            ++i;
        }
        if (i == 1) {
            this.numEntries[entry] = this.nmenu;
        }
        this.ss[this.nmenu] = m;
        this.sshash[this.nmenu++] = dataHash;
        return m;
    }
    
    public void mouseEntered(final MouseEvent e) {
        final Object object = e.getSource();
        for (int i = 0; i < this.numitems; ++i) {
            if (object == this.canvas[i]) {
                this.canvas[i].sethilight(true);
            }
            else {
                this.canvas[i].sethilight(false);
            }
            ((Component)this.canvas[i]).repaint();
        }
        ((Applet)this).showStatus("Free DJTextmenu at DecafJava.com");
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
    }
    
    public void mouseDragged(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
    }
    
    public void mouseClicked(final MouseEvent e) {
        final Object obj = e.getSource();
        String p = null;
        URL pageURL = null;
        for (int i = 0; i < this.numitems; ++i) {
            if (obj == this.canvas[i]) {
                this.selectedtextentry = i;
                if (this.pu[i].getItemCount() > 1) {
                    this.pu[i].show((Component)this.canvas[i], this.Xoffset[i], this.Yoffset[i]);
                    break;
                }
                try {
                    final Enumeration enum1 = this.sshash[this.numEntries[i]].elements();
                    final String s = enum1.nextElement();
                    final StringTokenizer tok = new StringTokenizer(s, ">");
                    if (tok.countTokens() == 2) {
                        p = tok.nextToken();
                        this.frame = tok.nextToken();
                    }
                    else {
                        this.frame = this.getParameterString("frame", "_top");
                        p = s;
                    }
                    pageURL = new URL(((Applet)this).getDocumentBase(), p);
                }
                catch (MalformedURLException ex) {
                    return;
                }
                final AppletContext browser = ((Applet)this).getAppletContext();
                browser.showDocument(pageURL, this.frame);
            }
        }
    }
    
    public void mouseExited(final MouseEvent e) {
        final Object object = e.getSource();
        for (int i = 0; i < this.numitems; ++i) {
            this.canvas[i].sethilight(false);
            ((Component)this.canvas[i]).repaint();
        }
    }
    
    public void actionPerformed(final ActionEvent e) {
        URL pageURL = null;
        int i = 0;
        String p = new String();
        final Object object = e.getSource();
        for (i = 0; i < 100; ++i) {
            if (object == this.ss[i]) {
                break;
            }
        }
        try {
            final String s = this.sshash[i].get(e.getActionCommand());
            final StringTokenizer tok = new StringTokenizer(s, ">");
            if (tok.countTokens() == 2) {
                p = tok.nextToken();
                this.frame = tok.nextToken();
            }
            else {
                this.frame = this.getParameterString("frame", "_top");
                p = s;
            }
            pageURL = new URL(((Applet)this).getDocumentBase(), p);
        }
        catch (MalformedURLException ex) {
            return;
        }
        final AppletContext browser = ((Applet)this).getAppletContext();
        browser.showDocument(pageURL, this.frame);
    }
    
    public DJTextmenu() {
        this.pnl = new Panel();
        this.ss = new Menu[100];
        this.sshash = new Hashtable[100];
        this.canvas = new DJTextmenu.Cnv[50];
        this.menuitem = new String[50];
        this.pu = new PopupMenu[50];
        this.Xoffset = new int[50];
        this.Yoffset = new int[50];
        this.numEntries = new int[50];
        this.underline = true;
        this.lastxpos = 5;
    }
}
