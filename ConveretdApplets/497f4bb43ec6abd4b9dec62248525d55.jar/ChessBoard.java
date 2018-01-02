import java.awt.event.WindowEvent;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.util.Enumeration;
import java.awt.event.KeyEvent;
import java.awt.Canvas;
import java.net.MalformedURLException;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Shape;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.CheckboxMenuItem;
import java.awt.Menu;
import java.awt.MenuItem;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipInputStream;
import java.io.File;
import java.net.URL;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import netscape.javascript.JSObject;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.LayoutManager;
import java.awt.PopupMenu;
import java.util.Hashtable;
import java.awt.Cursor;
import java.awt.TextArea;
import java.awt.Label;
import java.awt.Button;
import java.awt.List;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ItemListener;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ChessBoard extends Applet implements ActionListener, FocusListener, ItemListener, MouseListener, MouseMotionListener, WindowListener, Runnable
{
    final int C = 45;
    final int D = 45;
    final int aq = 25;
    final int ar = 25;
    final int as = 25;
    final int at = 25;
    final int au = 410;
    final int av = 410;
    final int aw = 410;
    final int ax = 0;
    final int ay = 220;
    final int az = 410;
    final int aA = 25;
    final int aB = 20;
    final int aC = 415;
    final int aD = 5;
    final int aE = 210;
    final int aF = 400;
    final int aG = 625;
    final int aH = 106;
    final int aI = 520;
    final int aJ = 17;
    final int aK = 15;
    final int aL = 44;
    final int aM = 432;
    final int aN = 537;
    final int aO = 476;
    final int aP = 581;
    final int aQ = 0;
    final int aR = 410;
    final int aS = 630;
    final int aT = 150;
    int aU;
    int aV;
    int aW;
    int aX;
    boolean aY;
    boolean aZ;
    boolean ba;
    boolean bb;
    boolean bc;
    boolean bd;
    int be;
    boolean bf;
    long bg;
    Thread bh;
    boolean bi;
    boolean bj;
    volatile boolean bk;
    private int bl;
    private char[] bm;
    private Image[] bn;
    private Image X;
    private Graphics bo;
    private Vector bp;
    private Vector bq;
    List br;
    List bs;
    Button bt;
    Label bu;
    d bv;
    g bw;
    e bx;
    k by;
    h bz;
    f bA;
    i bB;
    TextArea bC;
    TextArea bD;
    Cursor bE;
    Hashtable bF;
    String[] bG;
    PopupMenu bH;
    PopupMenu bI;
    PopupMenu bJ;
    private final Object[][] bK;
    private final int[] bL;
    
    public ChessBoard() {
        this.bh = null;
        this.bm = new char[] { 'Q', 'R', 'N', 'B', 'K', 'P' };
        this.bn = new Image[12];
        this.bD = null;
        this.bG = new String[] { "Event", "Site", "Date", "Round", "White", "Black", "Result", "WhiteElo", "BlackElo", "ECO", "SetUp" };
        this.bK = new Object[][] { { "Flip Board", new Integer(4166), "Ctrl+F" }, { "Refresh All Games", new Integer(116), "F5" }, { "Use Animation", new Integer(4161), "Ctrl+A" }, { "To Pre-Selected Game", new Integer(0) }, { "To Random Game", new Integer(115), "F4" }, { "Keep Games List on Top", new Integer(0) }, { "Copy Position (EPD)", new Integer(4176), "Ctrl+P" }, { "Copy Game (PGN)", new Integer(4163), "Ctrl+C" }, { "Step a Move Backward", new Integer(37), "Left" }, { "Step a Move Forward", new Integer(39), "Right" }, { "Start of Game (or Branch)", new Integer(36), "Home" }, { "End of Game (or Branch)", new Integer(35), "End" }, { "Display Warnings", new Integer(0) }, { "Use Game Editor", new Integer(0) }, { "Delete Current Branch", new Integer(127), "Del" }, { "Bring-Up Current Branch", new Integer(4134), "Ctrl+Up" }, { "Pawn Promotes into ...", new Integer(0) }, { "Queen", new Integer(0) }, { "Rook", new Integer(0) }, { "Knight", new Integer(0) }, { "Bishop", new Integer(0) }, { "Paste Game (PGN)", new Integer(4182), "Ctrl+V" }, { "To Next Game (or Branch)", new Integer(40), "Down" }, { "To Prior Game (or Branch)", new Integer(38), "Up" }, { "Paste Moves Sequence", new Integer(119), "F8" }, { "Game Navigation", new Integer(0) }, { "Automatic Game Replay", new Integer(0) }, { "Start/Stop Replay", new Integer(120), "F9" }, { "Replay Faster", new Integer(4177), "Ctrl+Q" }, { "Replay Slower", new Integer(4186), "Ctrl+Z" } };
        this.bL = new int[] { 27, 1, 10, 8, 9, 11 };
    }
    
    public void init() {
        this.bb = true;
        this.bc = true;
        this.bd = true;
        this.be = 0;
        this.bf = false;
        this.bj = false;
        this.aY = (this.getParameter("puzzlemode") != null && this.getParameter("puzzlemode").equals("on"));
        this.aZ = false;
        this.ba = false;
        this.bk = false;
        this.bi = true;
        this.bp = null;
        (this.bw = new g()).a(this.getParameter("lightSquares"), this.getParameter("darkSquares"), this.getParameter("background"));
        this.aU = 0;
        if (this.getParameter("RefreshInterval") != null) {
            try {
                this.aU = Integer.parseInt(this.getParameter("RefreshInterval"));
            }
            catch (Exception ex2) {}
        }
        this.aV = 0;
        if (this.getParameter("GameToSelect") != null) {
            try {
                this.aV = Integer.parseInt(this.getParameter("GameToSelect")) - 1;
            }
            catch (Exception ex3) {}
        }
        this.setLayout(null);
        this.bA = new f();
        final String parameter = this.getParameter("ImagesFolder");
        String string;
        if (parameter != null && parameter.length() > 0) {
            string = parameter + "/";
        }
        else {
            string = "";
        }
        final MediaTracker mediaTracker = new MediaTracker(this);
        try {
            for (int i = 0; i < 6; ++i) {
                final char lowerCase = Character.toLowerCase(this.bm[i]);
                mediaTracker.addImage(this.bn[i] = this.getImage(this.getCodeBase(), string + 'w' + lowerCase + ".gif"), 0);
                mediaTracker.addImage(this.bn[i + 6] = this.getImage(this.getCodeBase(), string + 'b' + lowerCase + ".gif"), 0);
            }
            this.bA.a(this, mediaTracker, string);
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            this.b(1, ex.toString());
            this.be = 1;
            return;
        }
        this.bA.setBounds(3, 413, 408, 23);
        this.add(this.bA, 0);
        this.bA.a("Games", "Annotations", this.bw.S());
        this.bA.addMouseListener(this);
        this.bA.addMouseMotionListener(this);
        this.aW = (45 - this.bn[5].getWidth(this) + 1) / 2;
        this.aX = 45 - this.bn[5].getHeight(this) - 1;
        this.bv = new d(this, 45, 45, this.bn[5].getWidth(this), this.bn[5].getHeight(this));
        this.bx = new e(null);
        this.addFocusListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.bF = new Hashtable(12);
        for (int j = 0; j < this.bG.length; ++j) {
            this.bF.put(this.bG[j], "");
        }
        (this.br = new List()).setVisible(false);
        this.br.setBounds(3, 436, 408, 121);
        this.br.addItemListener(this);
        this.add(this.br);
        (this.bC = new TextArea("", 1, 1, 1)).setVisible(false);
        this.bC.setBounds(3, 436, 408, 121);
        this.bC.setFont(new Font("SansSerif", 0, 13));
        this.bC.setEditable(false);
        this.bC.setBackground(Color.white);
        this.add(this.bC);
        (this.bs = new List()).setFont(new Font("SansSerif", 0, 12));
        this.bs.setSize(54, 66);
        this.bs.setVisible(false);
        this.bs.addItemListener(this);
        this.add(this.bs);
        (this.by = new k(410, 410, 25, 25, 25, 25)).a(45, 45, this.bw.Q(), this.bw.R());
        this.by.a(this);
        this.add(this.by);
        this.bz = new h(this, 23, 4, 385, this.bw.R());
        (this.bB = new i()).setFont(new Font("SansSerif", 0, 12));
        this.bB.setVisible(false);
        this.add(this.bB, 0);
        this.enableEvents(8L);
    }
    
    public void stop() {
    }
    
    public void destroy() {
        this.bk = true;
        if (this.bh != null && this.bh.isAlive()) {
            this.bh.interrupt();
            try {
                this.bh.join();
            }
            catch (Exception ex) {}
        }
        this.bh = null;
        final Thread x = this.bv.x();
        if (x != null && x.isAlive()) {
            x.interrupt();
            try {
                x.join();
            }
            catch (Exception ex2) {}
        }
    }
    
    private void e(final boolean b) {
        InputStream inputStream = null;
        boolean equals = false;
        String parameter;
        if (this.getParameter("Encoding") != null) {
            parameter = this.getParameter("Encoding");
        }
        else {
            parameter = "";
        }
        long n;
        InputStreamReader inputStreamReader;
        try {
            if (this.getParameter("pgngamefile").startsWith("javascript:")) {
                JSObject window = JSObject.getWindow((Applet)this);
                if (window == null) {
                    return;
                }
                final String substring = this.getParameter("pgngamefile").substring(11);
                final int index = substring.indexOf("(");
                final String[] array = { substring.substring(index + 1, substring.lastIndexOf(")")).trim() };
                String s;
                int index2;
                for (s = substring.substring(0, index).trim(); (index2 = s.indexOf(".")) > 0; s = s.substring(index2 + 1)) {
                    window = (JSObject)window.getMember(s.substring(0, index2));
                }
                final String s2 = (String)window.call(s, (Object[])array);
                this.a(b, new BufferedReader(new StringReader(s2)), s2.length());
                this.bg = 0L;
                return;
            }
            else {
                final URL url = new URL(this.getDocumentBase(), this.getParameter("pgngamefile"));
                long bg;
                if ("file".equals(url.getProtocol())) {
                    String s3 = url.getFile();
                    while (true) {
                        final int index3 = s3.indexOf("%20");
                        if (index3 == -1) {
                            break;
                        }
                        s3 = s3.substring(0, index3) + " " + s3.substring(index3 + 3);
                    }
                    final File file = new File(s3);
                    bg = file.lastModified();
                    n = file.length();
                    inputStream = url.openStream();
                }
                else {
                    final URLConnection openConnection = url.openConnection();
                    inputStream = openConnection.getInputStream();
                    bg = openConnection.getLastModified();
                    n = openConnection.getContentLength();
                    equals = openConnection.getContentType().equals("application/zip");
                }
                if (bg > 0L && bg <= this.bg) {
                    inputStream.close();
                    return;
                }
                this.bg = bg;
                if (equals || this.getParameter("pgngamefile").endsWith(".zip") || this.getParameter("pgngamefile").endsWith(".ZIP") || this.getParameter("pgngamefile").endsWith(".gz")) {
                    final ZipInputStream zipInputStream = new ZipInputStream(inputStream);
                    n = zipInputStream.getNextEntry().getSize();
                    if (parameter.length() > 0) {
                        inputStreamReader = new InputStreamReader(zipInputStream, parameter);
                    }
                    else {
                        inputStreamReader = new InputStreamReader(zipInputStream);
                    }
                }
                else if (parameter.length() > 0) {
                    inputStreamReader = new InputStreamReader(inputStream, parameter);
                }
                else {
                    inputStreamReader = new InputStreamReader(inputStream);
                }
            }
        }
        catch (Exception ex) {
            this.b(1, ex.toString());
            this.be = 1;
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (Exception ex2) {}
            }
            return;
        }
        this.a(b, new BufferedReader(inputStreamReader), n);
    }
    
    private void a(final boolean bf, final BufferedReader bufferedReader, final long n) {
        final Hashtable<Object, String> hashtable = new Hashtable<Object, String>(12);
        this.setCursor(this.bE = Cursor.getPredefinedCursor(3));
        final int selectedIndex = this.br.getSelectedIndex();
        final String s = (selectedIndex >= 0) ? this.br.getItem(selectedIndex) : "";
        this.bp.setSize(0);
        this.bq.setSize(0);
        this.remove(this.br);
        this.br.removeAll();
        int n2 = 1;
        long n3 = 0L;
        int n4 = 0;
        String s2 = null;
        this.bf = bf;
        long n5 = 0L;
    Label_1329:
        while (true) {
            String line;
            try {
                line = bufferedReader.readLine();
            }
            catch (Exception ex) {
                this.b(1, ex.toString());
                this.be = 1;
                break;
            }
            if (line == null) {
                break;
            }
            if (this.bk) {
                this.be = 3;
                break;
            }
            n5 += line.length();
            if (n > 0L && n5 > n3 + 5120L) {
                final Image a = this.bB.a(0, "PGN File: " + this.getParameter("pgngamefile") + "\n \n \nReceived: " + (int)(100L * n5 / n) + "% so far (" + (n5 >> 10) + " KB of " + (n >> 10) + " KB)", 272);
                if (n3 == 0L) {
                    this.bB.setBounds((410 - a.getWidth(this.bB)) / 2, bf ? ((410 - a.getHeight(this.bB)) / 2) : (410 + (150 - a.getHeight(this.bB)) / 2), a.getWidth(this.bB), a.getHeight(this.bB));
                    this.bB.setVisible(true);
                }
                n3 = n5;
                final Graphics graphics = a.getGraphics();
                this.a(graphics, 0, 0, a.getWidth(this.bB), a.getHeight(this.bB), true);
                this.a(graphics, 20, 64, 232, 16, false);
                graphics.setColor(new Color(15394525));
                graphics.fillRect(22, 66, 228, 12);
                graphics.setColor(new Color(2474640));
                graphics.fillRect(22, 66, 20 + (int)(208L * n5 / n), 12);
                graphics.dispose();
                this.bB.repaint();
            }
            String s3 = line.trim();
            if (s3.length() == 0) {
                continue;
            }
            if (n2 != 0) {
                if (s3.charAt(0) == '[') {
                    final int index = s3.indexOf(34);
                    final String substring = s3.substring(s3.indexOf(91) + 1, index);
                    final int index2 = s3.indexOf("\"]", index + 1);
                    if (index2 == -1) {
                        this.b(1, "Unable to Parse Tag-pairs section of game# " + (n4 + 1));
                        this.be = 1;
                        break;
                    }
                    hashtable.put(substring.trim(), s3.substring(index + 1, index2).trim());
                    continue;
                }
                else {
                    ++n4;
                    for (int i = 0; i < this.bG.length; ++i) {
                        if (!hashtable.containsKey(this.bG[i])) {
                            hashtable.put(this.bG[i], "");
                        }
                    }
                    String s4 = hashtable.get("Date");
                    while (true) {
                        final int index3 = s4.indexOf("??");
                        if (index3 == -1) {
                            break;
                        }
                        if (index3 > 0) {
                            s4 = s4.substring(0, index3 - 1);
                        }
                        else {
                            s4 = s4.substring(index3 + 3);
                        }
                    }
                    String string;
                    if (s4.length() > 0 && !s4.equals("?")) {
                        string = "; " + s4;
                    }
                    else {
                        string = "";
                    }
                    String s5 = n4 + ". " + hashtable.get("White") + " vs " + hashtable.get("Black") + "; \"" + hashtable.get("Result") + "\"" + string;
                    if (!hashtable.get("Event").equals("?") && !hashtable.get("Event").equals("")) {
                        s5 = s5 + "; " + hashtable.get("Event");
                    }
                    if (!hashtable.get("Site").equals("?") && !hashtable.get("Site").equals("")) {
                        s5 = s5 + "; " + hashtable.get("Site");
                    }
                    this.br.add(s5);
                    this.bq.addElement(hashtable.clone());
                    n2 = 0;
                    s2 = "";
                }
            }
            s2 = s2 + s3 + '\n';
            int n6 = -1;
            while (true) {
                final int index4 = s3.indexOf(123, n6 + 1);
                if (index4 == -1) {
                    if (s3.endsWith(hashtable.get("Result"))) {
                        this.bp.addElement(s2);
                        hashtable.clear();
                        n2 = 1;
                        break;
                    }
                    break;
                }
                else {
                    while (true) {
                        final int index5 = s3.indexOf(125, index4 + 1);
                        if (index5 > 0) {
                            s3 = (s3.substring(0, index4) + ' ' + s3.substring(index5 + 1, s3.length())).trim();
                            n6 = index4;
                            break;
                        }
                        String line2;
                        try {
                            line2 = bufferedReader.readLine();
                        }
                        catch (Exception ex2) {
                            this.b(1, ex2.toString());
                            this.be = 1;
                            break Label_1329;
                        }
                        s2 = s2 + line2 + '\n';
                        s3 = s3.substring(0, index4 + 1) + line2;
                    }
                }
            }
        }
        if (this.bp.size() < this.bq.size()) {
            this.bp.addElement(s2);
            this.b(1, "Unexpected end of input stream is reached. PGN data is not valid!");
            this.be = 1;
        }
        try {
            bufferedReader.close();
        }
        catch (Exception ex3) {
            this.b(1, ex3.toString());
            this.be = 1;
        }
        this.bE = Cursor.getPredefinedCursor(0);
        this.bf = false;
        this.add(this.br);
        if (this.be > 0) {
            return;
        }
        this.bB.setVisible(false);
        if (bf) {
            this.selectGameByListIndex(this.aV, false);
            return;
        }
        if (selectedIndex < 0 || selectedIndex > n4 - 1) {
            this.selectGameByListIndex(0, false);
        }
        else if (!this.aY || this.aZ) {
            this.selectGameByListIndex(selectedIndex, s.equals(this.br.getItem(selectedIndex)));
        }
        else {
            this.selectGameByListIndex(selectedIndex, false);
        }
    }
    
    private synchronized void W() {
        this.a(this.bH = new PopupMenu(), 6, new MenuItem());
        this.a(this.bH, 0, new MenuItem());
        this.bH.addSeparator();
        this.a(this.bH, 13, new CheckboxMenuItem());
        ((CheckboxMenuItem)this.bK[13][0]).setState(true);
        this.a(this.bH, 16, new Menu());
        this.a(this.bH, 12, new CheckboxMenuItem());
        ((CheckboxMenuItem)this.bK[12][0]).setState(true);
        this.a(this.bH, 2, new CheckboxMenuItem());
        ((CheckboxMenuItem)this.bK[2][0]).setState(true);
        this.add(this.bH);
        final Menu menu = (Menu)this.bK[16][0];
        this.a(menu, 17, new CheckboxMenuItem());
        ((CheckboxMenuItem)this.bK[17][0]).setState(true);
        this.bl = 17;
        this.a(menu, 18, new CheckboxMenuItem());
        this.a(menu, 19, new CheckboxMenuItem());
        this.a(menu, 20, new CheckboxMenuItem());
        this.a(this.bI = new PopupMenu(), 7, new MenuItem());
        this.a(this.bI, 21, new MenuItem());
        this.bI.addSeparator();
        this.a(this.bI, 24, new MenuItem());
        this.a(this.bI, 15, new MenuItem());
        this.a(this.bI, 14, new MenuItem());
        this.bI.addSeparator();
        this.a(this.bI, 25, new Menu());
        this.a(this.bI, 26, new Menu());
        this.add(this.bI);
        final Menu menu2 = (Menu)this.bK[25][0];
        this.a(menu2, 9, new MenuItem());
        this.a(menu2, 8, new MenuItem());
        this.a(menu2, 10, new MenuItem());
        this.a(menu2, 11, new MenuItem());
        final Menu menu3 = (Menu)this.bK[26][0];
        this.a(menu3, 27, new MenuItem());
        this.a(menu3, 28, new MenuItem());
        this.a(menu3, 29, new MenuItem());
        this.a(this.bJ = new PopupMenu(), 1, new MenuItem());
        ((MenuItem)this.bK[1][0]).setEnabled(this.aU == 0);
        this.a(this.bJ, 5, new CheckboxMenuItem());
        ((CheckboxMenuItem)this.bK[5][0]).setState(false);
        this.bJ.addSeparator();
        this.a(this.bJ, 22, new MenuItem());
        this.a(this.bJ, 23, new MenuItem());
        this.a(this.bJ, 3, new MenuItem());
        ((MenuItem)this.bK[3][0]).setEnabled(this.aV > 0);
        this.a(this.bJ, 4, new MenuItem());
        this.add(this.bJ);
        this.bp = new Vector();
        this.bq = new Vector();
        this.bg = 0L;
    }
    
    private void a(final Menu menu, final int n, final MenuItem menuItem) {
        String label = (String)this.bK[n][0];
        ((MenuItem)(this.bK[n][0] = menuItem)).deleteShortcut();
        menuItem.setFont(new Font("Dialog", 0, 12));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        if (menuItem instanceof CheckboxMenuItem) {
            ((CheckboxMenuItem)menuItem).addItemListener(this);
        }
        if (this.bK[n].length > 2) {
            final FontMetrics fontMetrics = this.getFontMetrics(menuItem.getFont());
            for (int i = 150 - fontMetrics.stringWidth(label), stringWidth = fontMetrics.stringWidth(" "); i > stringWidth; i -= stringWidth) {
                label += " ";
            }
            label += (String)this.bK[n][2];
        }
        menuItem.setLabel(label);
    }
    
    private void b(final int n, final boolean b) {
        String string = "";
        if (this.bp.size() == 0) {
            this.bx = null;
            this.bz.V();
            return;
        }
        this.bF = this.bq.elementAt(n);
        e bx;
        if (this.bF.containsKey("FEN") && this.bF.get("FEN").length() > 0) {
            bx = new e(this.bF.get("FEN"));
            final boolean h = bx.H();
            this.bd = h;
            this.bb = h;
        }
        else {
            bx = new e(null);
            final boolean b2 = true;
            this.bd = b2;
            this.bb = b2;
        }
        this.by.g(this.bd);
        this.be = 0;
        for (int i = 0; i < 7; ++i) {
            if (((String)this.bF.get(this.bG[i])).length() == 0) {
                string = "This game's PGN data does not meet the Seven Tag Roster requirement for mandatory set of tags.";
                this.be = 2;
                break;
            }
        }
        final String element = this.bp.elementAt(n);
        boolean b3;
        if (element instanceof String) {
            b3 = bx.f(element);
            this.bp.setElementAt(bx.I(), n);
        }
        else {
            b3 = bx.b(element);
        }
        if (b) {
            final int t = this.bz.T();
            if (t == -1 || t > this.bx.F()) {
                bx.k(this.bx.F());
            }
            else {
                bx.k(t);
            }
        }
        else {
            bx.k(bx.d(0).j() ? 1 : 0);
        }
        this.bx = bx;
        if (b3) {
            String string2;
            if (this.be == 2) {
                string2 = "\u25ba " + string + "\n\u25ba ";
            }
            else {
                string2 = "";
            }
            string = string2 + "This game (or its variation) contains illegal move(s). Those will be shown in red on the scoreboard and ignored during play.";
            this.be = 2;
        }
        if (this.be == 2 && ((CheckboxMenuItem)this.bK[12][0]).getState()) {
            this.b(2, string);
        }
        else {
            this.bB.setVisible(false);
        }
    }
    
    private void X() {
        while (!this.bk) {
            if (this.bp == null) {
                this.W();
                this.e(true);
            }
            else {
                this.e(false);
            }
            if (this.be != 1 && (this.be != 2 || !((CheckboxMenuItem)this.bK[12][0]).getState())) {
                this.bB.setVisible(false);
            }
            this.repaint();
            if (this.bv.x() == null) {
                Thread.sleep(50L);
                this.bv.w();
            }
            if (this.aU == 0) {
                return;
            }
            Thread.sleep(this.aU * 1000);
        }
    }
    
    public void run() {
        final String name = Thread.currentThread().getName();
        try {
            if (name.equals("AnimationThreadName")) {
                while (!this.bk) {
                    this.bv.y();
                    if (this.bv.b(1)) {
                        this.bv.A();
                        this.bx.k(this.bx.F() + 1);
                        synchronized (this.bu) {
                            this.bj = true;
                            this.repaint();
                            while (this.bj) {
                                this.bu.wait();
                            }
                        }
                    }
                    this.bv.a(this.bx);
                    if (this.bA.N()) {
                        if (this.bv.b(1)) {
                            continue;
                        }
                        synchronized (this.bu) {
                            this.bu.wait(this.bA.O());
                        }
                        if (!this.bA.N()) {
                            continue;
                        }
                        if (this.bv.b(1)) {
                            continue;
                        }
                        this.n((this.bx.F() > this.bx.G() - 2) ? 27 : 9);
                    }
                }
            }
            else if (name.equals("DownloadPGNThreadName")) {
                this.X();
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public void a(final Graphics graphics, final int n, final char[] array, Point o) {
        if (n < 0 || n > 63) {
            return;
        }
        if (o == null) {
            o = this.o(n);
        }
        final int n2 = n + (n >> 3) & 0x1;
        graphics.setColor((n2 == 0) ? this.bw.Q() : this.bw.R());
        graphics.fillRect(o.x, o.y, 45, 45);
        if (n2 == 1) {
            graphics.setColor(this.bw.Q());
            for (int i = 2; i < 41; i += 3) {
                while (i < i + 2) {
                    graphics.drawLine(o.x + 2, o.y + i, o.x + i, o.y + 2);
                    graphics.drawLine(o.x + 45 - 3, o.y + 45 - i - 5, o.x + 45 - i - 5, o.y + 45 - 3);
                    ++i;
                }
            }
            graphics.drawLine(o.x + 45 - 3, o.y + 45 - 3, o.x + 45 - 3, o.y + 45 - 3);
        }
        if (array[n] != '-') {
            this.a(graphics, array[n], o.x, o.y);
        }
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle clipBounds = graphics.getClipBounds();
        if (this.X == null) {
            this.X = this.createImage(this.getSize().width, this.getSize().height);
            this.bo = this.X.getGraphics();
        }
        if (this.by.a(clipBounds)) {
            return;
        }
        if (this.be == 1) {
            graphics.drawImage(this.X, 0, 0, this);
            return;
        }
        if (this.bv.v()) {
            final Rectangle a = this.bv.a(this.bo);
            graphics.setClip(a.x, a.y, a.width, a.height);
            graphics.drawImage(this.X, 0, 0, this);
            return;
        }
        this.setCursor(this.bE);
        if (this.bf || (clipBounds.x + clipBounds.width - 3 < 410 && clipBounds.y > 410)) {
            graphics.drawImage(this.X, 0, 0, this);
            return;
        }
        this.bo.setColor(Color.white);
        this.bo.drawRect(23, 23, 363, 363);
        this.bo.drawRect(24, 24, 361, 361);
        final char[] c = this.bx.C();
        for (int i = 0; i < 64; ++i) {
            this.a(this.bo, i, c, null);
        }
        this.m(this.bx.F());
        if (this.bj) {
            this.f(this.bo);
            graphics.drawImage(this.X, 0, 0, this);
            this.bv.B();
            synchronized (this.bu) {
                this.bj = false;
                this.bu.notify();
            }
            return;
        }
        if (this.bi) {
            this.requestFocus();
            this.bi = false;
        }
        this.c(this.bo);
        this.g(this.bo);
        graphics.drawImage(this.X, 0, 0, this);
        if (this.bc) {
            this.bc = false;
            (this.bh = new Thread(this, "DownloadPGNThreadName")).start();
        }
    }
    
    private void b(final Graphics graphics) {
        final int f = this.bx.F();
        if (f == -1 || this.bx.f(f) == 0) {
            this.d(graphics);
            return;
        }
        while (this.bs.getItemCount() > 0) {
            this.bs.remove(0);
        }
        final String[] h = this.bx.h(f);
        for (int i = 0; i < h.length; ++i) {
            this.bs.add(h[i]);
        }
        this.bs.select(this.bx.g(f));
        this.bs.setVisible(true);
        this.e(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    void c(final Graphics graphics) {
        graphics.setColor(this.bw.S());
        graphics.fillRect(410, 0, 220, 410);
        graphics.setColor(Color.black);
        graphics.drawRect(410, 0, 220, 410);
        graphics.setColor(new Color(16777200));
        graphics.fillRect(415, 5, 210, 401);
        graphics.setColor(Color.black);
        graphics.drawRect(415, 5, 210, 401);
        graphics.setFont(new Font("Serif", 0, 12));
        graphics.getFontMetrics();
        graphics.setColor(new Color(12605440));
        int n = 5;
        graphics.drawString("event", 417, n + 11);
        graphics.drawString("date", 557, n + 11);
        n += 25;
        graphics.drawString("site", 417, n + 11);
        graphics.drawString("rnd", 551, n + 11);
        graphics.drawString("score", 571, n + 11);
        graphics.drawString("eco", 600, n + 11);
        n += 25;
        graphics.drawString("white", 417, n + 11);
        graphics.drawString("rating", 591, n + 11);
        n += 25;
        graphics.drawString("black", 417, n + 11);
        graphics.drawString("rating", 591, n + 11);
        graphics.setColor(Color.black);
        int n2 = 5;
        graphics.drawRect(415, n2, 140, 25);
        graphics.drawRect(555, n2, 70, 25);
        n2 += 25;
        graphics.drawRect(415, n2, 134, 25);
        graphics.drawRect(549, n2, 20, 25);
        graphics.drawRect(569, n2, 29, 25);
        graphics.drawRect(598, n2, 27, 25);
        n2 += 25;
        graphics.drawRect(415, n2, 174, 25);
        graphics.drawRect(589, n2, 36, 25);
        n2 += 25;
        graphics.drawRect(415, n2, 174, 25);
        graphics.drawRect(589, n2, 36, 25);
        graphics.setFont(new Font("SansSerif", 0, 12));
        int n3 = 5;
        this.a(graphics, 415, n3, 140, 25, this.bF.get("Event"), 0, -3);
        this.a(graphics, 555, n3, 70, 25, this.bF.get("Date"), 0, -2);
        n3 += 25;
        this.a(graphics, 415, n3, 134, 25, this.bF.get("Site"), 0, -3);
        this.a(graphics, 549, n3, 20, 25, this.bF.get("Round"), 0, -2);
        String substring = this.bF.get("Result");
        if (substring.length() > 0) {
            if (substring.length() > 1) {
                substring = substring.substring(0, 3);
            }
            this.a(graphics, 569, n3, 29, 25, substring, 0, -2);
        }
        this.a(graphics, 598, n3, 27, 25, this.bF.get("ECO"), 0, -2);
        n3 += 25;
        this.a(graphics, 415, n3, 174, 25, this.bF.get("White"), 0, -3);
        this.a(graphics, 589, n3, 36, 25, this.bF.get("WhiteElo"), 0, -2);
        n3 += 25;
        this.a(graphics, 415, n3, 174, 25, this.bF.get("Black"), 0, -3);
        this.a(graphics, 589, n3, 36, 25, this.bF.get("BlackElo"), 0, -2);
        graphics.drawLine(415, 106, 624, 106);
        graphics.setFont(new Font("Serif", 0, 10));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n4 = 106;
        final int n5 = 80;
        final int n6 = (this.bx.F() - 1) / n5;
        int n7 = 1;
        while (true) {
            final int n8 = n6 * (n5 / 2) + n7;
            n4 += 15;
            graphics.setColor(Color.black);
            graphics.drawString(String.valueOf(n8), 423 - fontMetrics.stringWidth(String.valueOf(n8)) / 2 + 1, n4 - 3);
            graphics.drawString(String.valueOf(n8 + 20), 528 - fontMetrics.stringWidth(String.valueOf(n8 + 20)) / 2 + 1, n4 - 3);
            if (n7 == 20) {
                break;
            }
            graphics.setColor(new Color(13680800));
            graphics.drawLine(416, n4, 624, n4);
            ++n7;
        }
        graphics.drawLine(432, 107, 432, 405);
        graphics.drawLine(537, 107, 537, 405);
        graphics.drawLine(520, 107, 520, 405);
        graphics.setColor(new Color(13680800));
        graphics.drawLine(476, 107, 476, 405);
        graphics.drawLine(581, 107, 581, 405);
        if (this.aY && !this.aZ) {
            return;
        }
        for (int n9 = n6 * n5, n10 = 0; n10 < n5 && n9 < this.bx.G(); ++n10, ++n9) {
            if (n9 == this.bx.F() - 1) {
                graphics.setColor(new Color(16300132));
                this.a(graphics, n9, true);
            }
            else {
                this.a(graphics, n9, false);
            }
        }
        if (this.bx.G() > 0) {
            this.b(graphics);
        }
    }
    
    private void m(final int n) {
        b d = null;
        if (n > 0) {
            d = this.bx.d(n - 1);
        }
        String text;
        if (n == 0 || !d.l()) {
            if (n != this.bx.G()) {
                text = this.bx.d(n).m();
            }
            else {
                text = "";
            }
        }
        else {
            text = d.n();
        }
        this.bC.setText(text);
        if (text.length() == 0) {
            if (this.bp != null && (n == 0 || ((CheckboxMenuItem)this.bK[5][0]).getState())) {
                this.bA.a(0, this.br, this.bC);
            }
            return;
        }
        this.bA.a(1, this.br, this.bC);
    }
    
    private void d(final Graphics graphics) {
        final Point location = this.bs.getLocation();
        final Dimension size = this.bs.getSize();
        this.bs.setVisible(false);
        graphics.setColor(this.bw.S());
        graphics.fillRect(location.x - 70, location.y, size.width + 70, size.height);
    }
    
    public void a(final Graphics graphics, final int n, final int n2, int n3, int n4, final boolean b) {
        --n3;
        --n4;
        graphics.setColor(b ? new Color(14011579) : new Color(10653032));
        graphics.drawLine(n, n2, n + n3, n2);
        graphics.drawLine(n, n2, n, n2 + n4);
        graphics.setColor(b ? new Color(15394525) : Color.black);
        graphics.drawLine(n + 1, n2 + 1, n + n3, n2 + 1);
        graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + n4);
        graphics.setColor(b ? Color.black : new Color(15394525));
        graphics.drawLine(n, n2 + n4, n + n3, n2 + n4);
        graphics.drawLine(n + n3, n2, n + n3, n2 + n4);
        graphics.setColor(b ? new Color(10653032) : new Color(14011579));
        graphics.drawLine(n + 1, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
        graphics.drawLine(n + n3 - 1, n2 + 1, n + n3 - 1, n2 + n4 - 1);
    }
    
    private void e(final Graphics graphics) {
        final StringBuffer sb = new StringBuffer("move# ");
        final Point location = this.bs.getLocation();
        final Dimension size = this.bs.getSize();
        final Point point = location;
        point.x -= 70;
        graphics.setColor(new Color(16777200));
        graphics.fillRect(location.x, location.y, 70, size.height - 1);
        this.a(graphics, location.x, location.y, 70, size.height, false);
        graphics.setFont(new Font("Dialog", 0, 11));
        graphics.setColor(new Color(12605440));
        graphics.getFontMetrics();
        final Point point2 = location;
        point2.x += 6;
        final Point point3 = location;
        point3.y += 16;
        graphics.drawString("Select from", location.x, location.y);
        final Point point4 = location;
        point4.y += 14;
        graphics.drawString("the list of all", location.x, location.y);
        final Point point5 = location;
        point5.y += 14;
        graphics.drawString("variations at", location.x, location.y);
        final Point point6 = location;
        point6.y += 14;
        this.bx.b(sb, this.bx.F());
        graphics.drawString(sb.toString(), location.x, location.y);
    }
    
    private void a(final Graphics graphics, final int n, final boolean b) {
        final String c = this.bx.c(n);
        final int f = this.bx.f(n + 1);
        final int n2 = ((n % 40 - n % 2) / 2 + 1) * 15 + 106;
        final int n3 = n % 80;
        int n4;
        if (n3 % 2 == 0) {
            n4 = ((n3 < 40) ? 432 : 537);
        }
        else {
            n4 = ((n3 < 40) ? 476 : 581);
        }
        if (b) {
            graphics.fillRect(n4 + 1, n2 - 15 + 1, 43, 14);
        }
        if (this.bx.e(n + 1)) {
            graphics.setColor(Color.blue);
        }
        else if (this.bx.d(n).g()) {
            graphics.setColor(Color.red);
        }
        else {
            graphics.setColor(Color.darkGray);
        }
        int n5;
        if (this.bx.d(n).h()) {
            n5 = 0;
        }
        else {
            n5 = 6;
        }
        graphics.setFont(new Font("SansSerif", 0, 12));
        this.a(graphics, n4, n2 - 15, 44, 15, c, n5, -3);
        if (this.bx.F() == n + 1) {
            return;
        }
        n4 += 37;
        if (this.bx.d(n).l()) {
            graphics.setColor(Color.red);
            final int n6 = n2 - 6;
            graphics.drawLine(n4, n6, n4, n6 + 4);
            graphics.drawLine(n4 + 1, n6 - 1, n4 + 3, n6 - 1);
            graphics.drawLine(n4 + 4, n6, n4 + 4, n6 + 4);
            graphics.drawLine(n4 + 1, n6 + 2, n4 + 3, n6 + 2);
        }
        graphics.setColor(Color.blue);
        int n7 = n2 - 13;
        for (int n8 = 0; n8 < 3 && n8 < f; ++n8) {
            graphics.drawLine(n4, n7, n4 + 4, n7);
            graphics.drawLine(n4 + 1, n7 + 1, n4 + 3, n7 + 1);
            graphics.drawLine(n4 + 2, n7 + 2, n4 + 2, n7 + 2);
            n7 += 4;
        }
    }
    
    private void f(final Graphics graphics) {
        final int n = this.bx.F() - 1;
        if (n % 80 < 2) {
            this.c(graphics);
            return;
        }
        graphics.setColor(new Color(16777200));
        this.a(graphics, n - 1, true);
        graphics.setColor(new Color(16300132));
        this.a(graphics, n, true);
        this.b(graphics);
    }
    
    private void g(final Graphics graphics) {
        graphics.setColor(this.bw.S());
        graphics.fillRect(0, 410, 631, 150);
        graphics.setColor(new Color(0));
        graphics.drawLine(0, 410, 630, 410);
        final int n = 475;
        final int n2 = 470;
        final int n3 = 24;
        graphics.setColor(new Color(15394525));
        graphics.drawRect(n - 42, n2 + 36, 175, n3 + 5);
        if (this.getComponentAt(n, n2) != this.bt) {
            this.bs.setLocation(410 + (220 - this.bs.getSize().width) / 2 + 39, n2 - n3 - 22);
            (this.bt = new Button("Show Answer")).addActionListener(this);
            this.add(this.bt);
            this.bt.setBounds(n, n2, 94, n3);
            this.bt.setFont(new Font("SanSerif", 0, 12));
            this.bt.setVisible(this.aY);
            (this.bu = new Label("Chess Viewer Deluxe (ver 3.5a)", 0)).setFont(new Font("SanSerif", 0, 12));
            this.bu.setForeground(this.bw.R());
            this.bu.setBackground(this.bw.S());
            this.bu.setBounds(this.bt.getLocation().x - 41, n2 + n3 + 47, 174, 16);
            this.bu.addMouseListener(this);
            this.bu.setCursor(Cursor.getPredefinedCursor(12));
            this.add(this.bu);
            this.bw.a(this, n - 24, n2 + 46, 28, n3 - 14);
        }
        if (this.ba) {
            String s = "to Move";
            final int n4 = this.bt.getLocation().x - 11;
            final int y = this.bs.getLocation().y;
            if (this.bd) {
                graphics.setColor(Color.white);
                if (this.bF.get("Result").equals("1-0")) {
                    s += " & Win";
                }
            }
            else {
                graphics.setColor(Color.black);
                if (this.bF.get("Result").equals("0-1")) {
                    s += " & Win";
                }
            }
            if (this.bF.get("Result").equals("1/2-1/2")) {
                s += " & Draw";
            }
            graphics.fillRect(n4, y, 115, n3 + 10);
            this.a(graphics, n4, y, 115, n3 + 10, false);
            graphics.setFont(new Font("Serif", 1, 14));
            final int n5 = 410 + (220 - graphics.getFontMetrics().stringWidth(s)) / 2 + 2;
            graphics.setColor(this.bd ? Color.gray : Color.white);
            graphics.drawString(s, n5, y + 22);
        }
        else if (this.bs.isVisible()) {
            this.e(graphics);
        }
    }
    
    public void a(final Graphics graphics, char upperCase, final int n, final int n2) {
        int n3;
        if (Character.isLowerCase(upperCase)) {
            n3 = 6;
            upperCase = Character.toUpperCase(upperCase);
        }
        else {
            n3 = 0;
        }
        for (int n4 = 0; upperCase != this.bm[n4]; ++n4, ++n3) {}
        graphics.drawImage(this.bn[n3], n + this.aW, n2 + this.aX, this);
    }
    
    private void b(final int n, final String s) {
        final Image a = this.bB.a(n, s, 365);
        final Graphics graphics = a.getGraphics();
        this.a(graphics, 0, 0, a.getWidth(this.bB), a.getHeight(this.bB), true);
        graphics.dispose();
        this.bB.setBounds((630 - a.getWidth(this)) / 2, (410 - a.getHeight(this)) / 2, a.getWidth(this), a.getHeight(this));
        this.bB.setVisible(true);
    }
    
    private void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final String s, final int n5, final int n6) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final Shape clip = graphics.getClip();
        int n7 = (n3 - fontMetrics.stringWidth(s) + 1) / 2;
        graphics.setClip(n, n2, n3, n4);
        if (n7 > n5 && n5 > 0) {
            n7 = n5;
        }
        else if (n7 < 0) {
            n7 = 0;
        }
        graphics.drawString(s, n + n7, n2 + n4 + n6);
        graphics.setClip(clip);
    }
    
    private void n(final int n) {
        final MenuItem menuItem = (MenuItem)this.bK[n][0];
        this.actionPerformed(new ActionEvent(menuItem, 1001, menuItem.getActionCommand()));
    }
    
    private void f(final boolean enabled) {
        ((CheckboxMenuItem)this.bK[13][0]).setState(enabled);
        ((Menu)this.bK[16][0]).setEnabled(enabled);
        ((MenuItem)this.bK[24][0]).setEnabled(enabled);
        ((MenuItem)this.bK[15][0]).setEnabled(enabled);
        ((MenuItem)this.bK[14][0]).setEnabled(enabled);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final Component component = mouseEvent.getComponent();
        if (this.bc || this.bf) {
            return;
        }
        if ((!this.aY || this.aZ) && this.c(mouseEvent.getX(), mouseEvent.getY()) != -1) {
            this.bE = Cursor.getPredefinedCursor(12);
        }
        else if (component == this.bA && this.bA.b(component, mouseEvent.getX(), mouseEvent.getY())) {
            this.bE = Cursor.getPredefinedCursor(12);
        }
        else {
            this.bE = Cursor.getPredefinedCursor(0);
        }
        this.setCursor(this.bE);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        mouseEvent.getComponent();
        if (this.bc || this.bf || !this.bv.b(2)) {
            return;
        }
        this.bv.a(mouseEvent.getPoint(), 25, 25, 410, 410);
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        final Component component = mouseEvent.getComponent();
        final Dimension size = component.getSize();
        if (component == this.bu || this.bz.c(component)) {
            final Graphics graphics = component.getGraphics();
            final Color color = (component == this.bu) ? new Color(3355647) : this.bw.Q();
            component.setForeground(color);
            graphics.setColor(color);
            graphics.drawLine(1, size.height - 1, size.width - 1, size.height - 1);
            graphics.dispose();
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        final Component component = mouseEvent.getComponent();
        if (component == this.bu) {
            component.setForeground(this.bw.R());
        }
        else if (this.bz.c(component)) {
            component.setForeground(Color.white);
        }
        else if (component == this.bA) {
            this.bA.a(component, 0, 0);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Component component = mouseEvent.getComponent();
        final int modifiers = mouseEvent.getModifiers();
        if (this.bc || this.bf) {
            mouseEvent.consume();
            return;
        }
        if (component == this.bA) {
            final int a = this.bA.a(component, mouseEvent.getX(), mouseEvent.getY());
            if ((modifiers & 0x4) == 0x0) {
                final int a2 = this.bA.a(mouseEvent.getX(), mouseEvent.getY());
                if (a != -1) {
                    this.n(this.bL[a]);
                }
                else if (a2 != -1) {
                    this.bA.a(a2, this.br, this.bC);
                }
            }
            else if (a == -1) {
                final Point location = component.getLocation();
                this.bJ.show(this, mouseEvent.getX() + location.x, mouseEvent.getY() + location.y - 1);
            }
            return;
        }
        this.bi = true;
        if (component == this.bu) {
            if ((modifiers & 0x4) == 0x0) {
                String s;
                if (this.bF.containsKey("GameUrl") && this.bF.get("GameUrl").length() > 0) {
                    s = this.bF.get("GameUrl");
                }
                else {
                    s = "http://chesstuff.blogspot.com/2008/11/chess-viewer-deluxe.html";
                }
                try {
                    this.getAppletContext().showDocument(new URL(s), "_self");
                }
                catch (MalformedURLException ex) {}
            }
            return;
        }
        if (this.bw.a(component)) {
            if ((modifiers & 0x4) == 0x0 && this.bv.a(component)) {
                this.bw.b(component);
                this.by.a(45, 45, this.bw.Q(), this.bw.R());
                this.by.g(this.bb);
                this.bA.a(this.bw.S());
                this.bA.repaint();
                this.bu.setForeground(this.bw.R());
                this.bu.setBackground(this.bw.S());
                this.bz.b(this.bw.R());
                this.repaint();
            }
            return;
        }
        if (this.bz.c(component)) {
            if ((modifiers & 0x4) == 0x0) {
                final int d = this.bz.d(component);
                if (this.bv.a(new Integer(d))) {
                    this.bx.k(d);
                    this.repaint();
                }
            }
            return;
        }
        if ((modifiers & 0x4) != 0x0) {
            final Point point = mouseEvent.getPoint();
            if (component instanceof Canvas) {
                final Point location2 = component.getLocation();
                final Point point2 = point;
                point2.x += location2.x;
                final Point point3 = point;
                point3.y += location2.y;
            }
            if (point.x < 410) {
                if (point.y < 410) {
                    this.bH.show(this, point.x, point.y);
                }
            }
            else if (point.y < 410) {
                this.bI.show(this, point.x, point.y);
            }
            return;
        }
        if (this.by.e(component)) {
            this.n(0);
            return;
        }
        final int c = this.c(mouseEvent.getX(), mouseEvent.getY());
        if ((!this.aY || this.aZ) && c != -1) {
            if (this.bv.a(new Integer(c))) {
                this.be = 0;
                this.bB.setVisible(false);
                this.bx.k(c);
                this.repaint();
            }
            return;
        }
        if (this.bv.b(3)) {
            mouseEvent.consume();
            return;
        }
        if (mouseEvent.getX() >= 410 && mouseEvent.getX() < 630 && mouseEvent.getY() >= 0 && mouseEvent.getY() <= 5) {
            this.g(this.bx.D());
            return;
        }
        if (((CheckboxMenuItem)this.bK[13][0]).getState() && this.bv.a(mouseEvent.getPoint(), this.bx.C())) {
            this.bE = Cursor.getPredefinedCursor(12);
            this.repaint();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!this.bv.b(2)) {
            return;
        }
        this.bE = Cursor.getPredefinedCursor(0);
        final int a = this.bx.a(this.bv.a(), this.bv.b(), this.bm[this.bl - 17]);
        this.bv.B();
        if (a > 0) {
            this.bz.a(this.bx, a - 1);
        }
        else if (a < 0) {
            this.bj = true;
        }
        this.repaint();
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        final int modifiers = keyEvent.getModifiers();
        if (keyEvent.getID() != 401 || (modifiers & 0x8) != 0x0 || (modifiers & 0x1) != 0x0 || keyCode == 17 || keyCode == 16 || keyCode == 18 || keyEvent.isConsumed()) {
            super.processKeyEvent(keyEvent);
            return;
        }
        int length = this.bK.length;
        while (length-- > 0) {
            final int intValue = (int)this.bK[length][1];
            final int n = intValue & 0xFFF;
            int n2 = intValue >> 12;
            if (n2 > 0) {
                n2 = 2;
            }
            if (keyCode == n) {
                if (modifiers != n2) {
                    continue;
                }
                final MenuItem menuItem = (MenuItem)this.bK[length][0];
                if (!menuItem.isEnabled()) {
                    continue;
                }
                keyEvent.consume();
                if (menuItem == this.bK[2][0]) {
                    ((CheckboxMenuItem)menuItem).setState(!((CheckboxMenuItem)menuItem).getState());
                    return;
                }
                this.actionPerformed(new ActionEvent(menuItem, 1001, menuItem.getActionCommand()));
                return;
            }
        }
        super.processKeyEvent(keyEvent);
    }
    
    public int b(final int n, final int n2) {
        if (n < 25 || n > 385 || n2 < 25 || n2 > 385) {
            return -1;
        }
        final int n3 = ((n2 - 25) / 45 << 3) + (n - 25) / 45;
        return this.bb ? n3 : (63 - n3);
    }
    
    public Point o(final int n) {
        final int n2 = this.bb ? n : (63 - n);
        return new Point(25 + (n2 & 0x7) * 45, 25 + (n2 >> 3) * 45);
    }
    
    private int c(final int n, final int n2) {
        if (n < 415 || n > 625 || n2 < 106 || n2 >= 406) {
            return -1;
        }
        int n3 = (n2 - 106) / 15 * 2 + 1;
        if (n > 581) {
            n3 += 41;
        }
        else if (n > 520) {
            n3 += 40;
        }
        else if (n > 476) {
            ++n3;
        }
        final int n4 = 80;
        final int n5 = n3 + (this.bx.F() - 1) / n4 * n4;
        if (n5 > this.bx.G()) {
            return -1;
        }
        return n5;
    }
    
    private boolean g(final String text) {
        try {
            final JSObject jsObject = (JSObject)JSObject.getWindow((Applet)this).getMember("clipboardData");
            if (jsObject != null) {
                jsObject.call("setData", new Object[] { "Text", text });
                return true;
            }
        }
        catch (Exception ex) {}
        if (this.bD == null) {
            (this.bD = new TextArea()).setVisible(false);
            this.bD.setBounds(365, 58, 262, 46);
            this.bD.setFont(new Font("SansSerif", 0, 12));
            this.add(this.bD);
        }
        if (this.bD.isVisible()) {
            this.bD.setVisible(false);
            return false;
        }
        this.bD.setText(text);
        this.bD.setVisible(true);
        return true;
    }
    
    private String Y() {
        String s = "";
        try {
            final JSObject jsObject = (JSObject)JSObject.getWindow((Applet)this).getMember("clipboardData");
            if (jsObject != null) {
                s = (String)jsObject.call("getData", new Object[] { "Text" });
            }
        }
        catch (Exception ex) {}
        return s;
    }
    
    public void selectGameByListIndex(final int n, final boolean b) {
        this.br.select(n);
        this.b(n, b);
        this.bz.V();
        this.bs.setVisible(false);
        if (this.aY && !b) {
            this.aZ = false;
            this.ba = true;
            this.bt.setVisible(true);
        }
        this.repaint();
    }
    
    public boolean isBusyDownloading() {
        return this.bc || this.bf;
    }
    
    private void Z() {
        if (this.bx.F() <= 0) {
            return;
        }
        if (this.bx.F() == 2 && this.bx.f(1) == 0 && this.bx.d(0).j() && !this.bx.d(0).l()) {
            this.bx.k(0);
        }
        else {
            this.bx.k(this.bx.F() - 1);
        }
        this.repaint();
    }
    
    private void aa() {
        if (this.bx.F() >= this.bx.G() - 1) {
            return;
        }
        if (this.aY && !this.aZ) {
            this.aZ = true;
            this.ba = false;
            this.bt.setVisible(false);
            if (this.bx.F() == 0 && this.bx.f(0) > 0) {
                this.repaint();
                return;
            }
        }
        if (this.bx.d(0).j() && this.bx.F() == 0 && this.bx.f(1) == 0 && !this.bx.d(0).l()) {
            this.bx.k(1);
        }
        if (!((CheckboxMenuItem)this.bK[2][0]).getState() || !this.bv.a(this.bx.J())) {
            this.bx.k(this.bx.F() + 1);
            this.repaint();
        }
        if (this.bA.N() || this.bv.b(1)) {
            synchronized (this.bu) {
                this.bu.notify();
            }
            this.bv.z();
        }
    }
    
    private void p(final int n) {
        final int f = this.bx.F();
        if (n == this.bx.g(f)) {
            return;
        }
        this.bx.i(n);
        this.bz.a(this.bx, f);
        this.repaint();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (!this.bv.a(actionEvent)) {
            return;
        }
        final Object source = actionEvent.getSource();
        this.be = 0;
        this.bB.setVisible(false);
        if (source == this.bK[8][0]) {
            this.Z();
            return;
        }
        if (source == this.bK[9][0]) {
            this.aa();
            return;
        }
        if (source == this.bK[10][0]) {
            if (this.bx.a(true)) {
                this.repaint();
            }
            return;
        }
        if (source == this.bK[11][0]) {
            if (this.aY && !this.aZ) {
                this.aZ = true;
                this.ba = false;
                this.bt.setVisible(false);
            }
            this.bx.k(this.bx.G() - 1);
            this.repaint();
            return;
        }
        if (source == this.bK[22][0]) {
            if (this.bs.isVisible()) {
                if (this.bs.getSelectedIndex() == this.bs.getItemCount() - 1) {
                    return;
                }
                this.bs.select(this.bs.getSelectedIndex() + 1);
                this.p(this.bs.getSelectedIndex());
            }
            else if (this.br.getSelectedIndex() < this.bp.size() - 1) {
                this.selectGameByListIndex(this.br.getSelectedIndex() + 1, false);
            }
            return;
        }
        if (source == this.bK[23][0]) {
            if (this.bs.isVisible()) {
                if (this.bs.getSelectedIndex() == 0) {
                    return;
                }
                this.bs.select(this.bs.getSelectedIndex() - 1);
                this.p(this.bs.getSelectedIndex());
            }
            else if (this.br.getSelectedIndex() > 0) {
                this.selectGameByListIndex(this.br.getSelectedIndex() - 1, false);
            }
            return;
        }
        if (source == this.bt) {
            this.aZ = true;
            this.ba = false;
            this.bt.setVisible(false);
            this.repaint();
            return;
        }
        if (source == this.bK[0][0]) {
            this.bb = !this.bb;
            this.by.g(this.bb);
            this.repaint();
            return;
        }
        if (source == this.bK[1][0]) {
            if (this.bh == null || !this.bh.isAlive()) {
                (this.bh = new Thread(this, "DownloadPGNThreadName")).start();
            }
            return;
        }
        if (source == this.bK[14][0]) {
            if (this.bx.f(this.bx.F()) == 0 && !this.bx.a(false)) {
                return;
            }
            if (this.bx.K()) {
                this.bz.a(this.bx, this.bx.F());
                this.repaint();
            }
        }
        else if (source == this.bK[15][0]) {
            if (this.bx.f(this.bx.F()) == 0 && !this.bx.a(false)) {
                return;
            }
            if (this.bx.L()) {
                final int f = this.bx.F();
                this.bz.a(this.bx, f);
                if (this.bz.T() == -1) {
                    this.bF.put(this.bG[6], this.bx.d(this.bx.G() - 1).d());
                    if (f == 0) {
                        this.bp.setElementAt(this.bx.I(), this.br.getSelectedIndex());
                    }
                }
                this.repaint();
            }
        }
        else {
            if (source == this.bK[3][0]) {
                this.selectGameByListIndex(this.aV, false);
                return;
            }
            if (source == this.bK[4][0]) {
                this.selectGameByListIndex((int)(Math.random() * (((this.aV > 0) ? this.aV : this.bp.size()) + 1)), false);
                return;
            }
            if (source == this.bK[6][0]) {
                this.g(this.bx.D());
                return;
            }
            if (source == this.bK[7][0]) {
                final StringBuffer sb = new StringBuffer("");
                final Vector<String> vector = new Vector<String>();
                final Enumeration<String> keys = this.bF.keys();
                while (keys.hasMoreElements()) {
                    vector.addElement(keys.nextElement());
                }
                for (int i = 0; i < this.bG.length; ++i) {
                    final String s = this.bG[i];
                    final String s2 = this.bF.get(s);
                    if (i < 7 || s2.length() > 0) {
                        sb.append("[" + s + " \"" + s2 + "\"]" + System.getProperty("line.separator"));
                    }
                    vector.removeElement(s);
                }
                final Enumeration<String> elements = vector.elements();
                while (elements.hasMoreElements()) {
                    final String s3 = elements.nextElement();
                    final String s4 = this.bF.get(s3);
                    if (s4.length() > 0) {
                        sb.append("[" + s3 + " \"" + s4 + "\"]" + System.getProperty("line.separator"));
                    }
                }
                sb.append(System.getProperty("line.separator"));
                this.bx.a(sb);
                this.g(sb.toString());
                return;
            }
            if (source == this.bK[21][0]) {
                final String y = this.Y();
                final StringReader stringReader = new StringReader(y);
                if (y.length() == 0) {
                    return;
                }
                if (y.charAt(0) != '[' || y.indexOf("\"]") == -1) {
                    if (((CheckboxMenuItem)this.bK[12][0]).getState()) {
                        this.b(2, "The Clipboard's content doesn't look like valid PGN data. Operation ignored!");
                        this.be = 2;
                    }
                }
                else {
                    this.a(false, new BufferedReader(stringReader), 0L);
                    this.bg = 0L;
                    this.repaint();
                }
            }
            else if (source == this.bK[24][0]) {
                final String y2 = this.Y();
                if (y2.length() == 0) {
                    return;
                }
                final e e = new e(this.bx.D());
                final boolean b = !e.H();
                if (e.f(y2)) {
                    if (((CheckboxMenuItem)this.bK[12][0]).getState()) {
                        this.b(2, "The Clipboard's content doesn't look like valid chess moves. Operation ignored!");
                        this.be = 2;
                    }
                }
                else {
                    final int a = this.bx.a(e.I(), b);
                    if (a > 0) {
                        this.bz.a(this.bx, a - 1);
                    }
                    if (this.bz.T() == -1) {
                        this.bF.put(this.bG[6], this.bx.d(this.bx.G() - 1).d());
                    }
                    this.repaint();
                }
            }
            else {
                if (source == this.bK[27][0]) {
                    if (!this.bA.N()) {
                        if (this.bx.F() > this.bx.G() - 2) {
                            return;
                        }
                        this.bA.b(true);
                        ((CheckboxMenuItem)this.bK[13][0]).setEnabled(false);
                        this.bA.d(((CheckboxMenuItem)this.bK[13][0]).getState());
                        if (this.bA.P()) {
                            this.f(false);
                        }
                        this.bA.a(this, true);
                        this.n(9);
                    }
                    else {
                        this.bA.b(false);
                        ((CheckboxMenuItem)this.bK[13][0]).setEnabled(true);
                        if (this.bA.P()) {
                            this.f(true);
                        }
                        this.bA.a(this, false);
                        synchronized (this.bu) {
                            this.bu.notify();
                        }
                    }
                    this.bA.repaint();
                    return;
                }
                if (source == this.bK[28][0]) {
                    this.bA.c(true);
                    this.bA.a(this, true);
                    return;
                }
                if (source == this.bK[29][0]) {
                    this.bA.c(false);
                    this.bA.a(this, true);
                }
            }
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final Object source = itemEvent.getSource();
        if (source == this.br) {
            this.selectGameByListIndex(this.br.getSelectedIndex(), false);
            return;
        }
        if (source == this.bs) {
            this.p(this.bs.getSelectedIndex());
            return;
        }
        if (source == this.bK[5][0]) {
            if (((CheckboxMenuItem)source).getState() && this.bC.getText().length() == 0) {
                this.bA.a(0, this.br, this.bC);
            }
            return;
        }
        if (source == this.bK[13][0]) {
            this.f(((CheckboxMenuItem)source).getState());
            return;
        }
        for (int i = 17; i < 21; ++i) {
            final CheckboxMenuItem checkboxMenuItem = (CheckboxMenuItem)this.bK[i][0];
            if (source == checkboxMenuItem) {
                checkboxMenuItem.setState(true);
                ((CheckboxMenuItem)this.bK[this.bl][0]).setState(false);
                this.bl = i;
                return;
            }
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        if (this.bv.b(2)) {
            this.mouseReleased(new MouseEvent(this, 502, 0L, 0, 0, 0, 0, false));
            return;
        }
        this.bi = (this.bi || !focusEvent.isTemporary());
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.bk = true;
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
}
