import java.awt.Cursor;
import java.awt.Component;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class X_MenuT extends Applet implements Runnable
{
    Color TEXT_COLOR;
    Color SELECTED_TEXT_COLOR;
    Color LINE_COLOR;
    Color ALT_UST_COLOR;
    Color BACKGROUND_COLOR;
    String FONT_NAME;
    int FONT_SIZE;
    int FONT_TYPE;
    Font mFont;
    String string;
    FontMetrics X_FontMetrics;
    Graphics b;
    Image buffer;
    String BACKGROUND_FILE;
    Image BACKGROUND_IMAGE;
    Image[] Icon;
    URL url;
    Thread thread;
    MediaTracker downloadTrack;
    boolean ready;
    int Num_Entries;
    int X_Bosluk;
    int Y_Bosluk;
    int X_Level_Arasi;
    int Y_Level_Arasi;
    int Applet_Width;
    int Applet_Height;
    X_MenuItem[] menuItems;
    private boolean mValidRegCode;
    
    public void stop() {
        try {
            this.thread.stop();
            this.thread = null;
        }
        catch (Exception ex) {}
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.menuItems.length; ++i) {
            this.menuItems[i].X_Active = false;
        }
        this.showStatus("");
        this.DrawMenu();
        return true;
    }
    
    public X_MenuT() {
        this.TEXT_COLOR = this.stringToColor("FFBB11");
        this.SELECTED_TEXT_COLOR = this.stringToColor("AABBCC");
        this.LINE_COLOR = this.stringToColor("001122");
        this.ALT_UST_COLOR = this.stringToColor("223344");
        this.BACKGROUND_COLOR = this.stringToColor("FFFFFF");
        this.FONT_NAME = "Tahoma";
        this.FONT_SIZE = 10;
        this.FONT_TYPE = 0;
        this.mFont = null;
        this.buffer = null;
        this.ready = false;
        this.X_Bosluk = 5;
        this.Y_Bosluk = 15;
        this.X_Level_Arasi = 20;
        this.Y_Level_Arasi = 16;
        this.mValidRegCode = false;
        System.out.println("X_MenuT version 2.1\r\nCopyright 2001 by AppletWorld (appletworld@usa.net)");
    }
    
    public void paint(final Graphics graphics) {
        try {
            if (this.Applet_Height != this.size().height) {
                this.Applet_Height = ((this.size().height <= 474) ? 474 : this.size().height);
                this.DrawMenu();
            }
            graphics.drawImage(this.buffer, 0, 0, null);
        }
        catch (Exception ex) {
            this.showStatus("paint Exception");
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        try {
            final int fnActive_Index;
            if ((fnActive_Index = this.fnActive_Index(n2)) == -1) {
                return true;
            }
            if (this.menuItems[fnActive_Index].X_Icon == 1) {
                this.menuItems[fnActive_Index].X_Icon = 0;
                for (int i = 0; i < this.menuItems.length; ++i) {
                    if (this.menuItems[fnActive_Index].X_Name.equals(this.menuItems[i].X_Ust)) {
                        this.menuItems[i].X_Visible = false;
                    }
                }
            }
            else if (this.menuItems[fnActive_Index].X_Icon == 0) {
                this.menuItems[fnActive_Index].X_Icon = 1;
                for (int j = 0; j < this.menuItems.length; ++j) {
                    if (this.menuItems[fnActive_Index].X_Name.equals(this.menuItems[j].X_Ust)) {
                        this.menuItems[j].X_Visible = true;
                    }
                }
            }
            else {
                this.showURL(this.menuItems[fnActive_Index].X_URL, this.menuItems[fnActive_Index].X_TargetFrame);
            }
            this.DrawMenu();
        }
        catch (Exception ex) {
            this.showStatus("MouseUP Exception");
        }
        return true;
    }
    
    public void destroy() {
        try {
            this.b.dispose();
            System.gc();
        }
        catch (Exception ex) {
            System.out.println("error on destroy() -> " + ex.toString());
        }
    }
    
    private int fnAktif_Eleman_Sayisi(final int n, final String s) {
        int n2 = 0;
        for (int n3 = n; n3 >= 0 && !this.menuItems[n3].X_Name.equals(s) && !this.menuItems[n3].X_Name.equals(s); --n3) {
            if (this.WillDisplay(n3)) {
                ++n2;
            }
        }
        return n2;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void RegulateLevels() {
        try {
            for (int i = 0; i < this.menuItems.length; ++i) {
                for (int j = 0; j < this.menuItems.length; ++j) {
                    if (this.menuItems[i].X_Name.equals(this.menuItems[j].X_Ust)) {
                        this.menuItems[j].X_Level = this.menuItems[i].X_Level + 1;
                    }
                }
            }
            for (int k = 0; k < this.menuItems.length; ++k) {
                if (this.menuItems[k].X_Level == 0) {
                    this.menuItems[k].X_Visible = true;
                }
                else {
                    this.menuItems[k].X_Visible = false;
                }
            }
            for (int l = 0; l < this.menuItems.length; ++l) {
                if (this.menuItems[l].X_Icon == 0) {
                    for (int n = 0; n < this.menuItems.length; ++n) {
                        if (this.menuItems[l].X_Name.equals(this.menuItems[n].X_Ust)) {
                            this.menuItems[n].X_Visible = false;
                        }
                    }
                }
                else if (this.menuItems[l].X_Icon == 1) {
                    for (int n2 = 0; n2 < this.menuItems.length; ++n2) {
                        if (this.menuItems[l].X_Name.equals(this.menuItems[n2].X_Ust)) {
                            this.menuItems[n2].X_Visible = true;
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            this.showStatus("RegulateLevels Exception");
        }
    }
    
    public void ReadMenuItems() {
        final int int1 = Integer.parseInt(this.getParameter("MENU_COUNT"));
        this.menuItems = new X_MenuItem[int1];
        for (int i = 0; i < int1; ++i) {
            String nextToken = "";
            String nextToken2 = "";
            String nextToken3 = "";
            String nextToken4 = "";
            this.string = this.getParameter("m" + Integer.toString(i + 1));
            if (this.string == null || this.string.equalsIgnoreCase("")) {
                this.RegulateLevels();
                return;
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(this.string, ",");
            final String nextToken5 = stringTokenizer.nextToken();
            final String nextToken6 = stringTokenizer.nextToken();
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                nextToken2 = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                nextToken3 = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                nextToken4 = stringTokenizer.nextToken();
            }
            this.menuItems[i] = new X_MenuItem(nextToken5, nextToken6, nextToken, nextToken2, nextToken3, nextToken4);
        }
        this.RegulateLevels();
    }
    
    public void start() {
        try {
            (this.thread = new Thread(this)).start();
        }
        catch (Exception ex) {}
    }
    
    public void DrawMenu() {
        final int y_Bosluk = this.Y_Bosluk;
        this.b.setFont(this.mFont);
        if (!this.ready) {
            return;
        }
        if (this.BACKGROUND_FILE == null || this.BACKGROUND_FILE == "") {
            this.b.setColor(this.BACKGROUND_COLOR);
            this.b.fillRect(0, 0, this.Applet_Width, this.Applet_Height);
        }
        else {
            this.b.drawImage(this.BACKGROUND_IMAGE, 0, 0, this.Applet_Width, this.Applet_Height, null);
        }
        int n = this.Y_Bosluk + this.Y_Level_Arasi;
        try {
            for (int i = 0; i < this.menuItems.length; ++i) {
                if (this.menuItems[i].X_Visible && this.WillDisplay(i)) {
                    if (this.menuItems[i].X_Level != 0) {
                        final int fnAktif_Eleman_Sayisi = this.fnAktif_Eleman_Sayisi(i, this.menuItems[i].X_Ust);
                        this.b.setColor(this.LINE_COLOR);
                        this.b.drawLine(this.X_Bosluk + this.X_Level_Arasi * this.menuItems[i].X_Level, n - this.X_FontMetrics.getDescent(), this.X_Bosluk + this.X_Level_Arasi * this.menuItems[i].X_Level - this.X_Level_Arasi / 2, n - this.X_FontMetrics.getDescent());
                        this.b.drawLine(this.X_Bosluk + this.X_Level_Arasi * this.menuItems[i].X_Level - this.X_Level_Arasi / 2, n - this.X_FontMetrics.getDescent(), this.X_Bosluk + this.X_Level_Arasi * this.menuItems[i].X_Level - this.X_Level_Arasi / 2, n - this.Y_Level_Arasi * fnAktif_Eleman_Sayisi);
                    }
                    try {
                        this.b.drawImage(this.Icon[this.menuItems[i].X_Icon], this.X_Bosluk + this.X_Level_Arasi * this.menuItems[i].X_Level, n - this.Icon[this.menuItems[i].X_Icon].getHeight(this) + 2, this);
                    }
                    catch (Exception ex) {}
                    this.b.setColor(this.menuItems[i].X_Active ? this.SELECTED_TEXT_COLOR : this.TEXT_COLOR);
                    this.b.drawString(this.menuItems[i].X_Name, this.X_Bosluk + this.X_Level_Arasi * this.menuItems[i].X_Level + this.Icon[this.menuItems[i].X_Icon].getWidth(this) + 3, n);
                    if (this.menuItems[i].X_Active) {
                        this.b.setColor(this.ALT_UST_COLOR);
                        this.b.drawLine(this.X_Bosluk + this.X_Level_Arasi * this.menuItems[i].X_Level + this.Icon[this.menuItems[i].X_Icon].getWidth(this) + 3, n + 2, this.X_Bosluk + this.X_Level_Arasi * this.menuItems[i].X_Level + this.Icon[this.menuItems[i].X_Icon].getWidth(this) + this.X_FontMetrics.stringWidth(this.menuItems[i].X_Name), n + 2);
                        this.b.drawLine(this.X_Bosluk + this.X_Level_Arasi * this.menuItems[i].X_Level + this.Icon[this.menuItems[i].X_Icon].getWidth(this) + 3, n - this.X_FontMetrics.getHeight() + 3, this.X_Bosluk + this.X_Level_Arasi * this.menuItems[i].X_Level + this.Icon[this.menuItems[i].X_Icon].getWidth(this) + this.X_FontMetrics.stringWidth(this.menuItems[i].X_Name), n - this.X_FontMetrics.getHeight() + 3);
                    }
                    n += this.Y_Level_Arasi;
                }
            }
        }
        catch (Exception ex2) {
            this.showStatus("Draw Menu Exception");
        }
        if (this.menuItems.length != 0) {
            this.repaint();
        }
    }
    
    public void showURL(final String s, final String s2) {
        String s3;
        if (s.startsWith("http") || s.startsWith("mail")) {
            s3 = "";
        }
        else {
            s3 = "" + this.getDocumentBase();
            if (!s3.substring(s3.length() - 1).equals("/")) {
                s3 = s3.substring(0, s3.lastIndexOf("/")) + "/";
            }
        }
        try {
            this.url = new URL(s3 + s);
        }
        catch (Exception ex) {
            this.showStatus("ShowURL Exception");
        }
        this.getAppletContext().showDocument(this.url, s2);
    }
    
    private void GiveLoadingMessage() {
        this.getGraphics().setFont(new Font("Courier", 1, 12));
        this.getGraphics().setColor(Color.red);
        this.getGraphics().drawString("Loading ...", 20, 20);
    }
    
    private int fnReturnUst(final int n) {
        if (this.menuItems[n].X_Ust == "") {
            return -1;
        }
        for (int i = n - 1; i >= 0; --i) {
            if (this.menuItems[i].X_Name.equals(this.menuItems[n].X_Ust)) {
                return i;
            }
            if (this.menuItems[i].X_Ust == "") {
                break;
            }
        }
        return -1;
    }
    
    private void RegCodeCheck(final String s, String s2) {
        final String s3 = new String("");
        int n = 210;
        s2 = ((s2 == null) ? "" : s2);
        final String s4 = (s == null) ? "" : s;
        final String s5 = s4.startsWith("http://") ? s4.substring(7) : s4;
        final String s6 = s5.startsWith("https://") ? s5.substring(8) : s5;
        String substring = s6.startsWith("www.") ? s6.substring(4) : s6;
        if (substring.indexOf("/") >= 0) {
            substring = substring.substring(0, substring.indexOf("/"));
        }
        final String lowerCase = substring.toLowerCase();
        if (lowerCase.indexOf("tskb") >= 0) {
            this.mValidRegCode = true;
            return;
        }
        if (lowerCase.startsWith("file")) {
            this.mValidRegCode = true;
            return;
        }
        char c;
        for (c = '\0'; c < lowerCase.length(); ++c) {
            n = ((n + lowerCase.charAt(c) * '\u0003') * (c + '\u0001') + (c - '\u0001')) * '\u0002' % 1000000 + c;
        }
        final int n2 = ((n / c + 123654) * c % 1000000 * (c + '\n') + c - 1) % 10000000;
        if (("AW" + String.valueOf(n2)).equalsIgnoreCase(s2)) {
            this.mValidRegCode = true;
            return;
        }
        final String s7 = new String("");
        int int1 = Integer.parseInt(String.valueOf(n2 * 2 / 3).replace('1', '5').replace('3', '1').replace('6', '3').replace('9', '6'));
        final String s8 = "X_MenuT";
        for (char c2 = '\0'; c2 < s8.length(); ++c2) {
            int1 += s8.charAt(c2) * c2 * '\u00c7';
        }
        if (("AWX" + String.valueOf(int1 % 10000000)).equalsIgnoreCase(s2)) {
            this.mValidRegCode = true;
        }
    }
    
    public void run() {
        this.GiveLoadingMessage();
        try {
            this.downloadTrack.waitForID(0);
        }
        catch (InterruptedException ex) {
            return;
        }
        this.ready = true;
        this.DrawMenu();
    }
    
    public void init() {
        this.RegCodeCheck(this.getCodeBase().toString(), this.getParameter("regcode"));
        this.downloadTrack = new MediaTracker(this);
        this.Applet_Width = ((this.size().width == 0) ? 200 : this.size().width);
        this.Applet_Height = ((this.size().height == 0) ? 500 : this.size().height);
        try {
            this.buffer = this.createImage(this.Applet_Width, this.Applet_Height * 2);
            this.b = this.buffer.getGraphics();
        }
        catch (Exception ex) {}
        final String parameter = this.getParameter("TEXT_COLOR");
        if (parameter != "" && parameter != null) {
            this.TEXT_COLOR = this.stringToColor(parameter);
        }
        final String parameter2 = this.getParameter("SELECTED_TEXT_COLOR");
        if (parameter2 != "" && parameter2 != null) {
            this.SELECTED_TEXT_COLOR = this.stringToColor(parameter2);
        }
        final String parameter3 = this.getParameter("LINE_COLOR");
        if (parameter3 != "" && parameter3 != null) {
            this.LINE_COLOR = this.stringToColor(parameter3);
        }
        final String parameter4 = this.getParameter("UP_DOWN_COLOR");
        if (parameter4 != "" && parameter4 != null) {
            this.ALT_UST_COLOR = this.stringToColor(parameter4);
        }
        final String parameter5 = this.getParameter("BACKGROUND_COLOR");
        if (parameter5 != "" && parameter5 != null) {
            this.BACKGROUND_COLOR = this.stringToColor(parameter5);
        }
        final String parameter6 = this.getParameter("BACKGROUND_FILE");
        if (parameter6 != "" && parameter6 != null) {
            this.BACKGROUND_FILE = parameter6;
        }
        final String parameter7 = this.getParameter("Font");
        if (parameter7 != null & parameter7 != "") {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter7, "#");
            if (stringTokenizer.hasMoreTokens()) {
                this.FONT_NAME = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.equalsIgnoreCase("BOLD")) {
                    this.FONT_TYPE = 1;
                }
                else if (nextToken.equalsIgnoreCase("ITALIC")) {
                    this.FONT_TYPE = 2;
                }
                else if (nextToken.equalsIgnoreCase("PLAIN")) {
                    this.FONT_TYPE = 0;
                }
            }
            if (stringTokenizer.hasMoreTokens()) {
                this.FONT_SIZE = Integer.parseInt(stringTokenizer.nextToken());
            }
            this.mFont = new Font(this.FONT_NAME, this.FONT_TYPE, this.FONT_SIZE);
        }
        try {
            this.setCursor(new Cursor(12));
            this.b.setFont(this.mFont);
            this.X_FontMetrics = this.getFontMetrics(this.mFont);
        }
        catch (Exception ex2) {}
        this.Num_Entries = 0;
        while (this.getParameter("i" + this.Num_Entries) != null) {
            ++this.Num_Entries;
        }
        this.Icon = new Image[this.Num_Entries];
        this.Num_Entries = 0;
        while (true) {
            this.string = this.getParameter("i" + this.Num_Entries);
            if (this.string == null) {
                break;
            }
            this.Icon[this.Num_Entries] = this.getImage(this.getDocumentBase(), this.string);
            this.showStatus("Loading icons " + Integer.toString(this.Num_Entries));
            this.downloadTrack.addImage(this.Icon[this.Num_Entries], 0);
            ++this.Num_Entries;
        }
        if (this.BACKGROUND_FILE != null) {
            this.BACKGROUND_IMAGE = this.getImage(this.getDocumentBase(), this.BACKGROUND_FILE);
            this.showStatus("Loading Background picture");
            this.downloadTrack.addImage(this.BACKGROUND_IMAGE, 0);
        }
        this.ReadMenuItems();
        (this.thread = new Thread(this)).start();
    }
    
    private boolean WillDisplay(int fnReturnUst) {
        final int n = fnReturnUst;
        do {
            fnReturnUst = this.fnReturnUst(fnReturnUst);
            if (fnReturnUst != -1) {
                continue;
            }
            return this.menuItems[n].X_Visible;
        } while (this.menuItems[fnReturnUst].X_Icon == 1);
        return false;
    }
    
    private Color stringToColor(final String s) {
        int intValue;
        int intValue2;
        int intValue3;
        try {
            intValue = Integer.decode("0x" + s.substring(0, 2));
            intValue2 = Integer.decode("0x" + s.substring(2, 4));
            intValue3 = Integer.decode("0x" + s.substring(4, 6));
        }
        catch (Exception ex) {
            return new Color(255, 255, 255);
        }
        return new Color(intValue, intValue2, intValue3);
    }
    
    private int fnActive_Index(final int n) {
        try {
            int n2 = this.Y_Bosluk + this.Y_Level_Arasi;
            for (int i = 0; i < this.menuItems.length; ++i) {
                if (this.menuItems[i].X_Visible && this.WillDisplay(i)) {
                    if (n2 >= n) {
                        return i;
                    }
                    n2 += this.Y_Level_Arasi;
                }
            }
        }
        catch (Exception ex) {
            this.showStatus("fnActive_Index Exception");
        }
        return -1;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        try {
            for (int i = 0; i < this.menuItems.length; ++i) {
                this.menuItems[i].X_Active = false;
            }
            final int fnActive_Index;
            if ((fnActive_Index = this.fnActive_Index(n2)) != -1) {
                this.menuItems[fnActive_Index].X_Active = true;
            }
            this.showStatus(this.menuItems[fnActive_Index].X_Message);
            this.DrawMenu();
        }
        catch (Exception ex) {}
        if (!this.mValidRegCode) {
            this.showStatus(" <<->> (C)2000 AppletWorld -> Web : http://go.to/appletworld, E-Mail : appletworld@usa.net <<->>");
        }
        return true;
    }
}
