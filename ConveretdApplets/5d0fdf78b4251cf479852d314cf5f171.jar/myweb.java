import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class myweb extends Applet
{
    Color TEXT_HILIGHT;
    Color TEXT_NORMAL;
    Color BACK_GROUND;
    Color CONNECTORS;
    Color UNDER_LINE;
    Font NAME_FONT;
    int SPACING;
    int Num_Entries;
    int BufferX;
    int BufferY;
    int MaxLevel;
    int AppletY;
    int AppletX;
    int oldItem;
    String string;
    Graphics b;
    Image buffer;
    Image[] Icon;
    Entry[] Item;
    int[] I;
    
    public void init() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        this.AppletX = this.size().width;
        this.AppletY = this.size().height;
        this.buffer = this.createImage(this.AppletX, 1000);
        this.b = this.buffer.getGraphics();
        this.TEXT_NORMAL = this.setParmColor(this.getParameter("color-text"), Color.black);
        this.BACK_GROUND = this.setParmColor(this.getParameter("color-background"), Color.white);
        this.CONNECTORS = this.setParmColor(this.getParameter("color-connectors"), this.BACK_GROUND);
        this.TEXT_HILIGHT = this.setParmColor(this.getParameter("color-highlight"), this.TEXT_NORMAL);
        this.UNDER_LINE = this.setParmColor(this.getParameter("color-under-line"), this.BACK_GROUND);
        this.SPACING = ((this.getParameter("spacing") == null) ? this.SPACING : Integer.parseInt(this.getParameter("spacing")));
        final String parameter = this.getParameter("font-name-size");
        if (parameter != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
            this.NAME_FONT = new Font(stringTokenizer.nextToken(), 0, Integer.parseInt(stringTokenizer.nextToken()));
            this.b.setFont(this.NAME_FONT);
        }
        this.Num_Entries = 0;
        while (this.getParameter("icon" + this.Num_Entries) != null) {
            ++this.Num_Entries;
        }
        this.Icon = new Image[this.Num_Entries];
        this.Num_Entries = 0;
        while (true) {
            this.string = this.getParameter("icon" + this.Num_Entries);
            if (this.string == null) {
                break;
            }
            this.Icon[this.Num_Entries] = this.getImage(this.getDocumentBase(), this.string);
            this.showStatus("Loading " + this.string + ".gif");
            mediaTracker.addImage(this.Icon[this.Num_Entries], 0);
            ++this.Num_Entries;
        }
        this.Num_Entries = 0;
        this.addFolder(-1, "");
        this.initConnectors();
        for (int i = 1; i < 500; ++i) {
            this.I[i] = -1;
        }
        this.I[0] = 0;
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        final String parameter2 = this.getParameter("start-page");
        for (int j = this.Num_Entries - 1; j > 0; --j) {
            if (parameter2.equals("?" + this.Item[j].Location) && this.Item[j].Location != "") {
                this.Item[j].showDocument();
                int n = this.Item[j].Folder - 1;
                for (int k = j; k >= 0; --k) {
                    if (this.Item[k].Folder == n && this.Item[k].Icon < 2) {
                        this.Item[k].Icon = 1;
                        n = this.Item[k].Folder - 1;
                    }
                }
                break;
            }
        }
        this.createTree();
    }
    
    public void addFolder(final int n, final String s) {
        int n2 = 1;
        while (true) {
            this.string = this.getParameter(s + "/" + n2);
            if (this.string == null) {
                break;
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(this.string, ",");
            final String nextToken = stringTokenizer.nextToken();
            final String nextToken2 = stringTokenizer.nextToken();
            String nextToken3;
            if (stringTokenizer.hasMoreTokens()) {
                nextToken3 = stringTokenizer.nextToken();
            }
            else {
                nextToken3 = "";
            }
            String nextToken4;
            if (stringTokenizer.hasMoreTokens()) {
                nextToken4 = stringTokenizer.nextToken();
            }
            else {
                nextToken4 = "";
            }
            String nextToken5;
            if (stringTokenizer.hasMoreTokens()) {
                nextToken5 = stringTokenizer.nextToken();
            }
            else {
                nextToken5 = "";
            }
            this.Item[this.Num_Entries] = new Entry(String.valueOf(n), nextToken, nextToken3, nextToken4, nextToken2, nextToken5);
            ++this.Num_Entries;
            ++n2;
            if (Integer.parseInt(nextToken) >= 2) {
                continue;
            }
            this.addFolder(n + 1, s + "/" + nextToken2);
        }
    }
    
    public void initConnectors() {
        for (int i = this.Num_Entries - 1; i > 0; --i) {
            int n;
            try {
                n = this.Item[i + 1].Connector[this.Item[i].Folder];
            }
            catch (Exception ex) {
                n = 0;
            }
            if (n == 0) {
                this.Item[i].Connector[this.Item[i].Folder] = 3;
            }
            else {
                this.Item[i].Connector[this.Item[i].Folder] = 2;
            }
            for (int n2 = i - 1; n2 > 0 && this.Item[i].Folder <= this.Item[n2].Folder; --n2) {
                this.Item[n2].Connector[this.Item[i].Folder] = 1;
            }
        }
    }
    
    public void createTree() {
        int n = 1;
        int maxLevel = 0;
        while (n < this.Num_Entries && this.Item[0].Icon != 0) {
            ++maxLevel;
            this.I[maxLevel] = n;
            if (this.Item[n].Icon == 0) {
                while (this.Item[n].Folder > this.Item[n++].Folder) {
                    if (++n >= this.Num_Entries) {
                        break;
                    }
                }
            }
            else {
                ++n;
            }
        }
        this.MaxLevel = maxLevel;
        for (int i = maxLevel + 1; i <= this.Num_Entries; ++i) {
            this.I[i] = -1;
        }
        this.paintTree();
    }
    
    public void paintTree() {
        this.b.setColor(this.BACK_GROUND);
        this.b.fillRect(0, 0, this.AppletX, this.AppletY);
        this.b.drawImage(this.Icon[this.Item[0].Icon], this.BufferX, this.BufferY, null);
        if (this.Item[0].Icon == 1) {
            for (int n = 1; this.I[n] != -1; ++n) {
                this.b.setColor(this.CONNECTORS);
                for (int i = 0; i <= this.Item[this.I[n]].Folder; ++i) {
                    switch (this.Item[this.I[n]].Connector[i]) {
                        case 1: {
                            this.b.drawLine(i * this.SPACING + this.SPACING / 2 + this.BufferX, this.SPACING * n + this.BufferY, i * this.SPACING + this.SPACING / 2 + this.BufferX, this.SPACING * n + this.SPACING + this.BufferY);
                            break;
                        }
                        case 2: {
                            this.b.drawLine(i * this.SPACING + this.SPACING / 2 + this.BufferX, this.SPACING * n + this.BufferY, i * this.SPACING + this.SPACING / 2 + this.BufferX, this.SPACING * n + this.SPACING + this.BufferY);
                            this.b.drawLine(i * this.SPACING + this.SPACING / 2 + this.BufferX, this.SPACING * n + this.SPACING / 2 + this.BufferY, i * this.SPACING + this.SPACING + this.BufferX, this.SPACING * n + this.SPACING / 2 + this.BufferY);
                            break;
                        }
                        case 3: {
                            this.b.drawLine(i * this.SPACING + this.SPACING / 2 + this.BufferX, this.SPACING * n + this.BufferY, i * this.SPACING + this.SPACING / 2 + this.BufferX, this.SPACING * n + this.SPACING / 2 + this.BufferY);
                            this.b.drawLine(i * this.SPACING + this.SPACING / 2 + this.BufferX, this.SPACING * n + this.SPACING / 2 + this.BufferY, i * this.SPACING + this.SPACING + this.BufferX, this.SPACING * n + this.SPACING / 2 + this.BufferY);
                            break;
                        }
                    }
                }
                this.b.drawImage(this.Icon[this.Item[this.I[n]].Icon], this.Item[this.I[n]].Folder * this.SPACING + this.SPACING + this.BufferX, this.SPACING * n + this.BufferY, this);
                if (this.oldItem == n) {
                    if (this.Item[this.I[this.oldItem]].Location != "") {
                        this.b.setColor(this.UNDER_LINE);
                        this.b.drawLine(this.Item[this.I[this.oldItem]].Folder * this.SPACING + this.SPACING + 20 + this.BufferX, this.SPACING * this.oldItem + 14 + this.BufferY, this.Item[this.I[this.oldItem]].Folder * this.SPACING + this.SPACING + 20 + this.BufferX + this.b.getFontMetrics().stringWidth(this.Item[this.I[this.oldItem]].Name), this.SPACING * this.oldItem + 14 + this.BufferY);
                    }
                    this.b.setColor(this.TEXT_HILIGHT);
                }
                else {
                    this.b.setColor(this.TEXT_NORMAL);
                }
                this.b.drawString(this.Item[this.I[n]].Name, this.Item[this.I[n]].Folder * this.SPACING + this.SPACING + 20 + this.BufferX, this.SPACING * n + 13 + this.BufferY);
            }
        }
        else {
            this.b.setColor(this.TEXT_NORMAL);
            this.b.drawString("Code by: Matthew Wilson", 5, this.AppletY - 25);
            this.b.setColor(this.TEXT_HILIGHT);
            this.b.drawString("mlavwilson@hotmail.com", 5, this.AppletY - 10);
        }
        this.repaint();
    }
    
    public boolean mouseUp(final Event event, final int n, int n2) {
        n2 -= this.BufferY;
        if (this.Item[0].Icon == 0 && n2 > 350) {
            this.Item[0].showDocument("mailto:mlavwilson@hotmail.com", "main");
        }
        if (n2 / this.SPACING > this.Num_Entries) {
            return true;
        }
        if (this.I[n2 / this.SPACING] != -1 || n2 / this.SPACING == 0) {
            if (this.Item[this.I[n2 / this.SPACING]].Icon < 2) {
                if (n / this.SPACING == this.Item[this.I[n2 / this.SPACING]].Folder + 1) {
                    if (this.Item[this.I[n2 / this.SPACING]].Icon == 1) {
                        this.Item[this.I[n2 / this.SPACING]].Icon = 0;
                    }
                    else {
                        this.Item[this.I[n2 / this.SPACING]].Icon = 1;
                    }
                }
                else if (this.Item[this.I[n2 / this.SPACING]].Location != "") {
                    this.Item[this.I[n2 / this.SPACING]].showDocument();
                }
                else if (this.Item[this.I[n2 / this.SPACING]].Icon == 1) {
                    this.Item[this.I[n2 / this.SPACING]].Icon = 0;
                }
                else {
                    this.Item[this.I[n2 / this.SPACING]].Icon = 1;
                }
                this.createTree();
            }
            else {
                this.Item[this.I[n2 / this.SPACING]].showDocument();
            }
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.I[(n2 - this.BufferY) / this.SPACING] != -1 && (n2 - this.BufferY) / this.SPACING != this.oldItem) {
            if (this.oldItem != 0) {
                this.b.setColor(this.BACK_GROUND);
                this.b.drawLine(this.Item[this.I[this.oldItem]].Folder * this.SPACING + this.SPACING + 20 + this.BufferX, this.SPACING * this.oldItem + 14 + this.BufferY, this.Item[this.I[this.oldItem]].Folder * this.SPACING + this.SPACING + 20 + this.BufferX + this.b.getFontMetrics().stringWidth(this.Item[this.I[this.oldItem]].Name), this.SPACING * this.oldItem + 14 + this.BufferY);
                this.b.setColor(this.TEXT_NORMAL);
                this.b.drawString(this.Item[this.I[this.oldItem]].Name, this.Item[this.I[this.oldItem]].Folder * this.SPACING + this.SPACING + 20 + this.BufferX, this.SPACING * this.oldItem + 13 + this.BufferY);
            }
            this.oldItem = (n2 - this.BufferY) / this.SPACING;
            if (this.oldItem != 0) {
                if (this.Item[this.I[this.oldItem]].Location != "") {
                    this.b.setColor(this.UNDER_LINE);
                    this.b.drawLine(this.Item[this.I[this.oldItem]].Folder * this.SPACING + this.SPACING + 20 + this.BufferX, this.SPACING * this.oldItem + 14 + this.BufferY, this.Item[this.I[this.oldItem]].Folder * this.SPACING + this.SPACING + 20 + this.BufferX + this.b.getFontMetrics().stringWidth(this.Item[this.I[this.oldItem]].Name), this.SPACING * this.oldItem + 14 + this.BufferY);
                }
                this.b.setColor(this.TEXT_HILIGHT);
                this.b.drawString(this.Item[this.I[this.oldItem]].Name, this.Item[this.I[this.oldItem]].Folder * this.SPACING + this.SPACING + 20 + this.BufferX, this.SPACING * this.oldItem + 13 + this.BufferY);
            }
            this.repaint();
            this.showStatus(this.Item[this.I[(n2 - this.BufferY) / this.SPACING]].Status);
        }
        if (this.Item[0].Icon == 1) {
            if (n2 > this.AppletY - this.SPACING && this.BufferY > -(this.SPACING * this.MaxLevel - (this.AppletY - this.SPACING))) {
                this.BufferY -= this.SPACING;
                this.paintTree();
                this.repaint();
            }
            if (n2 < 10 && this.BufferY < 0) {
                this.BufferY += this.SPACING;
                this.paintTree();
                this.repaint();
            }
        }
        else if (n2 > this.AppletY - 20) {
            this.showStatus("Contact the author of this menu applet:  mlavwilson@hotmail.com");
        }
        return true;
    }
    
    public Color setParmColor(final String s, Color color) {
        final int[] array = new int[6];
        final String s2 = "0123456789abcdef";
        if (s != null && s.length() == 6) {
            for (int i = 0; i < 6; ++i) {
                for (int j = 0; j < 16; ++j) {
                    if (Character.toLowerCase(s.charAt(i)) == s2.charAt(j)) {
                        array[i] = j;
                    }
                }
            }
            color = new Color(array[0] * 16 + array[1], array[2] * 16 + array[3], array[4] * 16 + array[5]);
        }
        return color;
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "color-highlight", "COLOR", "#123456" }, { "color-text", "COLOR", "#123456" }, { "color-background", "COLOR", "#123456" }, { "color-connectors", "COLOR", "#123456" }, { "color-under-line", "COLOR", "#123456" }, { "!font-name-size", "FONT ", "Helvetica,12" }, { "spacing", "int  ", "18" }, { "icon[0-?]", "IMAGE", "icons/me.gif" }, { "[folder name]/[1-?]", "Item in menu", "Icon,Name,URL,Target,Status bar" } };
    }
    
    public void setTEXT_NORMAL(final String s) {
        this.TEXT_NORMAL = this.setParmColor(s, this.TEXT_NORMAL);
        this.paintTree();
    }
    
    public void setBACK_GROUND(final String s) {
        this.BACK_GROUND = this.setParmColor(s, this.BACK_GROUND);
        this.paintTree();
    }
    
    public void setCONNECTORS(final String s) {
        this.CONNECTORS = this.setParmColor(s, this.CONNECTORS);
        this.paintTree();
    }
    
    public void setTEXT_HILIGHT(final String s) {
        this.TEXT_HILIGHT = this.setParmColor(s, this.TEXT_HILIGHT);
        this.paintTree();
    }
    
    public void setUNDER_LINE(final String s) {
        this.UNDER_LINE = this.setParmColor(s, this.UNDER_LINE);
    }
    
    public void setSPACING(final String s) {
        try {
            this.SPACING = Integer.parseInt(s);
            final boolean b = false;
            this.BufferY = (b ? 1 : 0);
            this.BufferX = (b ? 1 : 0);
            this.paintTree();
        }
        catch (Exception ex) {}
    }
    
    public void setFONT_SIZE(final String s) {
        this.NAME_FONT = new Font("Helvetica", 0, (s == null) ? 14 : Integer.parseInt(s));
        this.b.setFont(this.NAME_FONT);
        this.paintTree();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void debug(final String s) {
        System.out.println(s);
    }
    
    public String getAppletInfo() {
        return "\t   applet menu v1.5\nby Matthew Wilson:  mlavwilson@hotmail.com";
    }
    
    public void paint(final Graphics graphics) {
        if (this.AppletY != this.size().height) {
            this.AppletY = this.size().height;
            this.createTree();
        }
        graphics.drawImage(this.buffer, 0, 0, null);
    }
    
    public myweb() {
        this.SPACING = 16;
        this.Num_Entries = 500;
        this.Item = new Entry[this.Num_Entries];
        this.I = new int[this.Num_Entries];
    }
    
    public class Entry
    {
        URL url;
        int[] Connector;
        int Folder;
        int Icon;
        String Status;
        String Location;
        String Target;
        String Name;
        
        public Entry(final String s, final String s2, final String location, final String target, final String name, final String status) {
            this.Status = status;
            this.Folder = Integer.parseInt(s);
            this.Icon = Integer.parseInt(s2);
            this.Location = location;
            this.Target = target;
            this.Name = name;
            this.Connector = new int[this.Folder + 1];
        }
        
        public void showDocument() {
            this.showDocument(this.Location, this.Target);
        }
        
        public void showDocument(final String s, final String s2) {
            String value;
            if (s.startsWith("http") || s.startsWith("mail")) {
                value = "";
            }
            else {
                value = String.valueOf(myweb.this.getCodeBase());
            }
            try {
                this.url = new URL(String.valueOf(value) + s);
            }
            catch (MalformedURLException ex) {
                myweb.this.showStatus("Malformed URL");
            }
            myweb.this.getAppletContext().showDocument(this.url, s2);
        }
    }
}
