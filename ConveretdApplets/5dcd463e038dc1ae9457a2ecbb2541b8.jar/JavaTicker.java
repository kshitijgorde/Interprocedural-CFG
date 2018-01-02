import java.awt.Event;
import java.awt.MediaTracker;
import java.awt.Image;
import java.util.StringTokenizer;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Component;
import java.util.Hashtable;
import java.awt.Button;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JavaTicker extends Applet implements Runnable
{
    private Ticker t;
    private Thread me;
    private boolean loaded;
    private Color background;
    private Color linkcolor;
    private String clientId;
    private String linkTo;
    private boolean reload;
    private boolean valid;
    private int tickby;
    private int mode;
    private int myWidth;
    private int myHeight;
    private String linkTarget;
    private int speedDelayInt;
    private int heightOverride;
    private Button b;
    private Button l;
    private Button r;
    private int numClicks;
    private String side;
    public Hashtable imgtable;
    public Hashtable fonttable;
    public String TextColor;
    public String TickerUpdate;
    public String TickerSymbols;
    public String Recycle;
    public String Extra;
    public String defaultMessage;
    public String defaultLink;
    
    private void checkValidity() {
        String nextreq = this.getParameter("copyright");
        if (nextreq == null || !nextreq.equals("Copyright and Patent Pending. Unauthorized users will be prosecuted.")) {
            this.valid = false;
        }
        nextreq = this.getParameter("warning");
        if (nextreq == null || !nextreq.equals("By using this software in a web or intranet site you acknowledge that you are a paying client licensing this software.")) {
            this.valid = false;
        }
        nextreq = this.getParameter("owner");
        if (nextreq == null || !nextreq.equals("Ticker Technologies Inc.")) {
            this.valid = false;
        }
        nextreq = this.getParameter("address");
        if (nextreq == null || (!nextreq.equals("725 Glen Cove Avenue, Glen Head, NY 11545") && !nextreq.equals("27B1 The Plaza, Locust Valley, NY 11560"))) {
            this.valid = false;
        }
        nextreq = this.getParameter("telephone");
        if (nextreq == null || (!nextreq.equals("1-888-908-4545") && !nextreq.equals("1-866-515-2082"))) {
            this.valid = false;
        }
        nextreq = this.getParameter("website");
        if (nextreq == null || !nextreq.equals("http://www.tickertech.com/")) {
            this.valid = false;
        }
    }
    
    public Rectangle myBounds(final Component c) {
        return this.myBounds(c, true);
    }
    
    public Rectangle myBounds(final Component c, final boolean override) {
        try {
            final Rectangle toreturn = c.getBounds();
            if (override && this.heightOverride > 0) {
                toreturn.height = this.heightOverride;
            }
            return toreturn;
        }
        catch (NoSuchMethodError noSuchMethodError) {
            final Rectangle toreturn = c.bounds();
            if (override && this.heightOverride > 0) {
                toreturn.height = this.heightOverride;
            }
            return toreturn;
        }
        catch (Exception ex) {
            final Rectangle toreturn = c.bounds();
            if (override && this.heightOverride > 0) {
                toreturn.height = this.heightOverride;
            }
            return toreturn;
        }
    }
    
    public Color parseHexColor(final String paramName) {
        String hexColor = this.getParameter(paramName);
        if (hexColor == null) {
            hexColor = "000000";
        }
        if (hexColor.indexOf("#") >= 0) {
            hexColor = hexColor.substring(1);
        }
        int blue;
        int red;
        int green;
        if (hexColor.equals("") || hexColor.length() != 6) {
            green = (red = (blue = 0));
        }
        else {
            red = Integer.parseInt(hexColor.substring(0, 2), 16);
            green = Integer.parseInt(hexColor.substring(2, 4), 16);
            blue = Integer.parseInt(hexColor.substring(4, 6), 16);
        }
        return new Color(red, green, blue);
    }
    
    private void initializeGUI() {
        final String displayMode = this.getParameter("displayMode");
        this.mode = 1;
        if (displayMode != null && displayMode.equals("cycle")) {
            this.mode = 5;
        }
        if (displayMode != null && displayMode.equals("left")) {
            this.mode = 1;
        }
        if (displayMode != null && displayMode.equals("right")) {
            this.mode = 2;
        }
        if (displayMode != null && displayMode.equals("up")) {
            this.mode = 3;
        }
        if (displayMode != null && displayMode.equals("down")) {
            this.mode = 4;
        }
        this.side = null;
        final String gSetting = this.getParameter("guiSetting");
        final String gLocation = this.getParameter("guiLocation");
        final String gOrientation = this.getParameter("guiOrientation");
        int orientation = 1;
        if (gOrientation != null && gOrientation.toLowerCase().equals("horizontal")) {
            orientation = 0;
        }
        if (this.mode != 5 && gSetting != null && gSetting.equals("buttons")) {
            this.setLayout(null);
            int x;
            int y = x = 0;
            int w = 13;
            int h = 17;
            final int wid = this.myBounds(this).width;
            final int hei = this.myBounds(this).height;
            if (orientation == 1) {
                h = 13;
                w = 17;
                h *= 3;
                if (wid < w) {
                    w = wid;
                }
            }
            if (orientation == 0) {
                w *= 3;
                if (hei < h) {
                    h = hei;
                }
            }
            if (gLocation != null) {
                if (gLocation.toLowerCase().equals("north")) {
                    this.side = "North";
                    y = 0;
                    x = (wid - w) / 2;
                }
                else if (gLocation.toLowerCase().equals("south")) {
                    this.side = "South";
                    y = hei - h;
                    x = (wid - w) / 2;
                }
                else if (gLocation.toLowerCase().equals("west")) {
                    this.side = "West";
                    y = (hei - h) / 2;
                    x = 0;
                }
                else if (gLocation.toLowerCase().equals("east")) {
                    this.side = "East";
                    y = (hei - h) / 2;
                    x = wid - w;
                }
                else if (gLocation.toLowerCase().equals("northwest")) {
                    this.side = "West";
                    y = 0;
                    x = 0;
                }
                else if (gLocation.toLowerCase().equals("northeast")) {
                    this.side = "East";
                    y = 0;
                    x = wid - w;
                }
                else if (gLocation.toLowerCase().equals("southwest")) {
                    this.side = "West";
                    y = hei - h;
                    x = 0;
                }
                else {
                    this.side = "East";
                    y = hei - h;
                    x = wid - w;
                }
            }
            this.b = new Button("||");
            this.l = new Button("<");
            this.r = new Button(">");
            this.b.setBackground(this.parseHexColor("guiColor"));
            this.l.setBackground(this.parseHexColor("guiColor"));
            this.r.setBackground(this.parseHexColor("guiColor"));
            if (orientation == 1) {
                this.l.reshape(x, y + h / 3 * 0, w, h / 3);
                this.r.reshape(x, y + h / 3, w, h / 3);
                this.b.reshape(x, y + h / 3 * 2, w, h / 3);
            }
            else {
                this.l.reshape(x + w / 3 * 0, y, w / 3, h);
                this.r.reshape(x + w / 3, y, w / 3, h);
                this.b.reshape(x + w / 3 * 2, y, w / 3, h);
            }
            this.add(this.b);
            this.add(this.l);
            this.add(this.r);
        }
    }
    
    public void init() {
        this.heightOverride = this.parseIntParam("heightOverride", -1);
        if (!this.reload) {
            this.initializeGUI();
        }
        final String codebase = this.getCodeBase().getHost().toString();
        if (codebase.indexOf(".tickertech.") < 0 && codebase.indexOf(".javaticker.") < 0 && codebase.indexOf("209.10.248.217") < 0 && codebase.indexOf("128.241.244.123") < 0) {
            this.valid = false;
        }
        this.clientId = this.getParameter("clientId");
        if (this.clientId == null) {
            this.clientId = "";
            this.valid = false;
        }
        if (this.valid) {
            try {
                this.linkTo = "-OFF-";
                this.loaded = false;
                this.setBackground(this.background = this.parseHexColor("backgroundColor"));
                if (this.getParameter("linkColor") == null) {
                    this.linkcolor = null;
                }
                else {
                    this.linkcolor = this.parseHexColor("linkColor");
                }
                (this.me = new Thread(this)).start();
                this.reload = false;
            }
            catch (Exception e) {
                System.out.println("Error initializing: ");
                e.printStackTrace();
            }
        }
    }
    
    public void start() {
        try {
            if (this.reload) {
                this.init();
            }
        }
        catch (Exception e) {
            System.out.println("Error starting: ");
            e.printStackTrace();
        }
    }
    
    private int parseIntParam(final String param, final int def) {
        int toreturn;
        try {
            toreturn = Integer.parseInt(this.getParameter(param));
        }
        catch (Exception ex) {
            return def;
        }
        return toreturn;
    }
    
    public String getCharWidths() {
        if (this.mode != 3 && this.mode != 4) {
            return "";
        }
        String face = this.getParameter("fontFace");
        if (face == null) {
            face = "Arial";
        }
        int size = 0;
        try {
            size = Integer.parseInt(this.getParameter("fontSize"));
        }
        catch (Exception ex) {}
        size += 9;
        final Font f = new Font(face, 0, size);
        final Graphics g = this.getGraphics();
        g.setFont(f);
        final FontMetrics fm = g.getFontMetrics();
        String toreturn = "";
        for (int i = 97; i <= 122; ++i) {
            toreturn = String.valueOf(toreturn) + "" + fm.charWidth((char)i) + ",";
        }
        for (int j = 65; j <= 90; ++j) {
            toreturn = String.valueOf(toreturn) + "" + fm.charWidth((char)j) + ",";
        }
        for (int k = 48; k <= 57; ++k) {
            toreturn = String.valueOf(toreturn) + "" + fm.charWidth((char)k) + ",";
        }
        toreturn = String.valueOf(toreturn) + "" + fm.charWidth(' ');
        return toreturn;
    }
    
    public void run() {
        final Rectangle rect = this.myBounds(this);
        this.myWidth = rect.width;
        this.myHeight = rect.height;
        try {
            this.setCursor(new Cursor(3));
        }
        catch (Exception ex) {}
        try {
            this.checkValidity();
            this.repaint();
            if (!this.loaded && this.valid) {
                String tickerSymbols = this.getParameter("tickerSymbols");
                String tickerUpdate = this.getParameter("tickerUpdate");
                if (this.TickerSymbols != null) {
                    tickerSymbols = this.TickerSymbols;
                }
                if (this.TickerUpdate != null) {
                    tickerUpdate = this.TickerUpdate;
                }
                final String dataFields = this.getParameter("dataFields");
                final String location = this.getParameter("location");
                String allCaps = this.getParameter("allCaps");
                if (allCaps == null || !allCaps.equals("off")) {
                    allCaps = "on";
                }
                else {
                    allCaps = "off";
                }
                int updateDelayInt = 300000;
                final StringTokenizer allticks = new StringTokenizer(tickerSymbols, ",");
                if (allticks.countTokens() < 100) {
                    updateDelayInt = 720000;
                }
                if (this.clientId != null && this.clientId.equals("zoo")) {
                    updateDelayInt = 60000;
                }
                if (this.clientId != null && this.clientId.equals("cimedia")) {
                    updateDelayInt = 120000;
                }
                if (this.clientId != null && this.clientId.equals("bmc")) {
                    updateDelayInt = 90000;
                }
                if (tickerUpdate != null && tickerUpdate.equals("news")) {
                    updateDelayInt = 1200000;
                }
                final int updateMode = 0;
                String updateURL = tickerUpdate;
                boolean local = true;
                if (location == null || !location.equals("local")) {
                    updateURL = "http://" + this.getCodeBase().getHost().toString() + "/perl/" + tickerUpdate + ".cgi?ticker=" + tickerSymbols;
                    local = false;
                }
                boolean showline = false;
                final String showLine = this.getParameter("showLine");
                if (showLine != null && showLine.equals("on")) {
                    showline = true;
                }
                String textColor = this.getParameter("textColor");
                if (this.TextColor != null) {
                    textColor = this.TextColor;
                }
                if (textColor.indexOf("#") >= 0) {
                    textColor = textColor.substring(1);
                }
                this.speedDelayInt = this.parseIntParam("speedDelay", (this.mode == 2) ? 2000 : 20);
                final int headlineBufferInt = this.parseIntParam("headlineBuffer", 30);
                this.tickby = this.parseIntParam("tickBy", 1);
                int tickerX = 0;
                int tickerY = 0;
                int tickerWid = this.myWidth;
                int tickerHei = this.myHeight;
                if (this.side != null && this.mode != 5) {
                    if (this.side.equals("North")) {
                        tickerY += this.myBounds(this.b).height;
                        tickerHei -= this.myBounds(this.b).height;
                    }
                    else if (this.side.equals("South")) {
                        tickerHei -= this.myBounds(this.b).height;
                    }
                    else if (this.side.equals("West")) {
                        tickerX += this.myBounds(this.b).width;
                        tickerWid -= this.myBounds(this.b).width;
                    }
                    else if (this.side.equals("East")) {
                        tickerWid -= this.myBounds(this.b).width;
                    }
                }
                this.linkTarget = this.getParameter("linkTarget");
                if (this.linkTarget == null) {
                    this.linkTarget = "_blank";
                }
                this.linkTo = this.getParameter("linkTo");
                if (this.linkTo != null) {
                    this.linkTo = this.linkTo.replace('&', '*');
                }
                if (!local) {
                    String extra = this.getParameter("extra");
                    if (this.Extra != null) {
                        extra = this.Extra;
                    }
                    updateURL = String.valueOf(updateURL) + "&fields=" + dataFields + "&client=" + this.clientId + "&color=" + textColor + "&face=" + this.getParameter("fontFace") + "&size=" + this.getParameter("fontSize") + "&allcaps=" + allCaps + "&linkTo=" + this.linkTo + "&extra=" + extra + "&width=" + (tickerWid - 10) + "&height=" + tickerHei + "&background=" + this.getParameter("backgroundColor") + "&cw=" + this.getCharWidths() + "&codebase=http://" + this.getCodeBase().getHost().toString();
                    updateURL = updateURL.replace(' ', '+');
                    updateURL = updateURL.replace('#', '+');
                }
                if (this.imgtable == null) {
                    this.imgtable = new Hashtable(2);
                }
                if (this.fonttable == null) {
                    this.fonttable = new Hashtable(5);
                }
                try {
                    String msg = this.getParameter("defaultMessage");
                    if (msg != null) {
                        this.defaultMessage = msg;
                    }
                    msg = this.getParameter("defaultLink");
                    if (msg != null) {
                        this.defaultLink = msg;
                    }
                    String src = this.getParameter("defaultImg");
                    if (src != null) {
                        final StringTokenizer st = new StringTokenizer(src, "|");
                        while (st.hasMoreTokens()) {
                            src = st.nextToken();
                            Image img = this.imgtable.get(src);
                            if (img == null) {
                                img = this.getImage(this.getCodeBase(), src);
                                MediaTracker mt = new MediaTracker(this);
                                mt.addImage(img, 0);
                                mt.waitForAll();
                                mt = null;
                                this.imgtable.put(src, img);
                            }
                        }
                    }
                }
                catch (Exception ex2) {}
                (this.t = new Ticker(this.mode, updateURL, local, updateDelayInt, this.speedDelayInt, this.tickby, headlineBufferInt, this.background, this.linkcolor, showline, this, tickerX, tickerY, tickerWid, tickerHei, this.linkTarget, updateMode)).start();
                this.loaded = true;
                this.repaint();
            }
        }
        catch (Exception e) {
            System.out.println("Error during run: ");
            e.printStackTrace();
        }
    }
    
    public void setRecycle() {
        if (this.t != null) {
            try {
                String recycle = this.getParameter("recycle");
                if (this.Recycle != null) {
                    recycle = this.Recycle;
                }
                if (recycle != null && recycle.equals("no")) {
                    this.t.recycle = false;
                }
                else {
                    this.t.recycle = true;
                }
            }
            catch (Exception ex) {
                this.t.recycle = true;
            }
        }
    }
    
    private void setMyCursor(final int x, final int y) {
        try {
            if (this.linkTo.equals("-OFF-")) {
                this.setCursor(new Cursor(0));
            }
            else if (this.t == null || !this.t.isLoaded()) {
                this.setCursor(new Cursor(3));
            }
            else if (!this.linkTo.equals("-OFF-")) {
                if (this.t != null && this.t.isLoaded() && this.t.hasLinkWithin(x, y)) {
                    this.setCursor(new Cursor(12));
                }
                else {
                    this.setCursor(new Cursor(0));
                }
            }
            else {
                this.setCursor(new Cursor(0));
            }
        }
        catch (Exception ex) {}
    }
    
    public void stop() {
        this.reload = true;
        try {
            this.t.stopUpdating();
        }
        catch (Exception ex) {}
        try {
            this.t.stop();
        }
        catch (Exception ex2) {}
        try {
            this.me.stop();
        }
        catch (Exception ex3) {}
    }
    
    public void destroy() {
        this.reload = false;
        try {
            this.t.stopUpdating();
        }
        catch (Exception ex) {}
        try {
            this.t.stop();
        }
        catch (Exception ex2) {}
        try {
            this.me.stop();
        }
        catch (Exception ex3) {}
    }
    
    public void paint(final Graphics g) {
        try {
            if (this.loaded && this.t.isLoaded()) {
                this.t.draw(g);
            }
            else {
                this.setMyCursor(0, 0);
                g.setColor(new Color(255 - this.background.getRed(), 255 - this.background.getGreen(), 255 - this.background.getBlue()));
                String msg1 = "Loading Ticker,";
                String msg2 = "Powered By TickerTech.com";
                if (!this.valid) {
                    msg1 = "Copyright";
                    msg2 = "verification failure";
                }
                if (this.myBounds(this).height <= 20) {
                    msg1 = (msg2 = String.valueOf(msg1) + " " + msg2);
                }
                g.setFont(new Font("Verdana", 3, 10));
                final FontMetrics fm = g.getFontMetrics();
                int x = (this.myBounds(this).width - fm.stringWidth(msg2)) / 2;
                if (x < 17) {
                    x = 17;
                }
                final int y = (this.myBounds(this).height - fm.getAscent() * 2) / 2 + fm.getAscent();
                g.drawString(msg1, x, y);
                if (this.myBounds(this).height > 20) {
                    g.drawString(msg2, x, y + fm.getAscent());
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error painting screen: ");
            e.printStackTrace();
        }
    }
    
    public void pauseTicker() {
        if (this.loaded && this.t.isLoaded()) {
            this.t.paused = true;
        }
    }
    
    public void resumeTicker() {
        if (this.loaded && this.t.isLoaded()) {
            this.t.paused = false;
        }
    }
    
    public void slowDownTicker() {
        if (this.loaded && this.t.isLoaded()) {
            if (this.t.tickBy > this.tickby) {
                final Ticker t = this.t;
                --t.tickBy;
            }
            else {
                int chgby = 5;
                if (this.t.mode == 5) {
                    chgby = 100;
                }
                final int currentSpeedDelay = this.t.speedDelay;
                if (currentSpeedDelay < 60000) {
                    this.t.speedDelay = currentSpeedDelay + chgby;
                }
            }
        }
    }
    
    public void speedUpTicker() {
        if (this.loaded && this.t.isLoaded()) {
            int chgby = 5;
            if (this.t.mode == 5) {
                chgby = 100;
            }
            int newSpeedDelay = this.t.speedDelay - chgby;
            if (newSpeedDelay <= 5) {
                newSpeedDelay = 5;
                if (this.t.tickBy < 100) {
                    final Ticker t = this.t;
                    ++t.tickBy;
                }
            }
            this.t.speedDelay = newSpeedDelay;
        }
    }
    
    public void goLeftUp() {
        this.t.paused = false;
        int newmode;
        if (this.mode == 3 || this.mode == 4) {
            newmode = 3;
        }
        else {
            newmode = 1;
        }
        if (newmode != this.t.mode) {
            this.t.scrollingNow = false;
            this.t.tickBy = this.tickby;
            this.t.speedDelay = this.speedDelayInt;
            this.t.mode = newmode;
        }
    }
    
    public void goRightDown() {
        this.t.paused = false;
        int newmode;
        if (this.mode == 3 || this.mode == 4) {
            newmode = 4;
        }
        else {
            newmode = 2;
        }
        if (newmode != this.t.mode) {
            this.t.scrollingNow = false;
            this.t.tickBy = this.tickby;
            this.t.speedDelay = this.speedDelayInt;
            this.t.mode = newmode;
        }
    }
    
    public boolean mouseEnter(final Event e, final int x, final int y) {
        try {
            this.setMyCursor(x, y);
        }
        catch (Exception ex) {}
        return true;
    }
    
    public boolean mouseExit(final Event e, final int x, final int y) {
        try {
            this.setMyCursor(-1, -1);
        }
        catch (Exception ex) {}
        return true;
    }
    
    public boolean mouseMove(final Event e, final int x, final int y) {
        try {
            this.setMyCursor(x, y);
        }
        catch (Exception ex) {}
        return true;
    }
    
    public boolean mouseDown(final Event e, final int x, final int y) {
        try {
            if (!this.linkTo.equals("-OFF-")) {
                this.setMyCursor(x, y);
                this.t.processMouseClick(x, y);
            }
        }
        catch (Exception ex) {}
        return true;
    }
    
    public boolean action(final Event e, final Object o) {
        if (e.target == this.b && this.t != null && this.t.isLoaded()) {
            this.t.paused ^= true;
        }
        else {
            this.t.paused = false;
            final boolean firstButton = e.target == this.r;
            int newmode;
            if (this.mode == 3 || this.mode == 4) {
                if (firstButton) {
                    newmode = 4;
                }
                else {
                    newmode = 3;
                }
            }
            else if (firstButton) {
                newmode = 2;
            }
            else {
                newmode = 1;
            }
            if (newmode != this.t.mode) {
                this.t.scrollingNow = false;
                this.t.tickBy = this.tickby;
                this.t.speedDelay = this.speedDelayInt;
                this.t.mode = newmode;
            }
            else {
                this.speedUpTicker();
            }
        }
        return true;
    }
    
    public JavaTicker() {
        this.loaded = false;
        this.reload = false;
        this.valid = true;
        this.imgtable = null;
        this.fonttable = null;
        this.TextColor = null;
        this.TickerUpdate = null;
        this.TickerSymbols = null;
        this.Recycle = null;
        this.Extra = null;
        this.defaultMessage = "Sorry, could not connect to the server.  Please verify your internet connection and try again.";
        this.defaultLink = "";
    }
}
