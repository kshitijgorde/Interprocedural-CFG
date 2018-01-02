import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Event;
import java.util.Date;
import java.awt.FontMetrics;
import java.awt.Polygon;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WordSearch extends Applet implements Runnable
{
    Thread loopThread;
    static final int DELAY = 50;
    static final int INIT = 1;
    static final int PLAY = 2;
    static final int OVER = 3;
    Color scrnFgColor;
    Color scrnBgColor;
    Color scrnBdColor;
    String scrnFontStr;
    Color bttnFgColor;
    Color bttnBgColor;
    String bttnFontStr;
    Color gridFgColor;
    Color gridBgColor;
    Color gridHiColor;
    Color gridFdColor;
    String gridFontStr;
    Color listFgColor;
    Color listBgColor;
    Color listFdColor;
    String listFontStr;
    int gridRows;
    int gridCols;
    int gridSize;
    Vector files;
    static Vector words;
    Font scrnFont;
    Font bttnFont;
    Font gridFont;
    Font listFont;
    WSGrid grid;
    WSList list;
    WSButton newGame;
    WSButton solveGame;
    WSButton scrollUp;
    WSButton scrollDn;
    int fileNum;
    int gap;
    int gameState;
    int scroll;
    int count;
    long startTime;
    String timeText;
    String statText;
    String subjText;
    Dimension offDimension;
    Image offImage;
    Graphics offGraphics;
    
    public String getAppletInfo() {
        return "Word Search\n\nCopyright 1999 by Mike Hall";
    }
    
    public void init() {
        System.out.println("Word Search, Copyright 1999 by Mike Hall.");
        try {
            final String parameter = this.getParameter("screencolors");
            if (parameter != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
                this.scrnFgColor = this.getColorParm(stringTokenizer.nextToken());
                this.scrnBgColor = this.getColorParm(stringTokenizer.nextToken());
                this.scrnBdColor = this.getColorParm(stringTokenizer.nextToken());
            }
        }
        catch (Exception ex) {}
        try {
            final String parameter2 = this.getParameter("buttoncolors");
            if (parameter2 != null) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter2, ",");
                this.bttnFgColor = this.getColorParm(stringTokenizer2.nextToken());
                this.bttnBgColor = this.getColorParm(stringTokenizer2.nextToken());
            }
        }
        catch (Exception ex2) {}
        try {
            final String parameter3 = this.getParameter("gridcolors");
            if (parameter3 != null) {
                final StringTokenizer stringTokenizer3 = new StringTokenizer(parameter3, ",");
                this.gridFgColor = this.getColorParm(stringTokenizer3.nextToken());
                this.gridBgColor = this.getColorParm(stringTokenizer3.nextToken());
                this.gridFdColor = this.getColorParm(stringTokenizer3.nextToken());
                this.gridHiColor = this.getColorParm(stringTokenizer3.nextToken());
            }
        }
        catch (Exception ex3) {}
        try {
            final String parameter4 = this.getParameter("listcolors");
            if (parameter4 != null) {
                final StringTokenizer stringTokenizer4 = new StringTokenizer(parameter4, ",");
                this.listFgColor = this.getColorParm(stringTokenizer4.nextToken());
                this.listBgColor = this.getColorParm(stringTokenizer4.nextToken());
                this.listFdColor = this.getColorParm(stringTokenizer4.nextToken());
            }
        }
        catch (Exception ex4) {}
        this.scrnFont = this.getFontParm(this.scrnFontStr);
        try {
            final String parameter5 = this.getParameter("screenfont");
            final Font fontParm;
            if (parameter5 != null && (fontParm = this.getFontParm(parameter5)) != null) {
                this.scrnFont = fontParm;
            }
        }
        catch (Exception ex5) {}
        this.bttnFont = this.getFontParm(this.bttnFontStr);
        try {
            final String parameter6 = this.getParameter("buttonfont");
            final Font fontParm2;
            if (parameter6 != null && (fontParm2 = this.getFontParm(parameter6)) != null) {
                this.bttnFont = fontParm2;
            }
        }
        catch (Exception ex6) {}
        this.gridFont = this.getFontParm(this.gridFontStr);
        try {
            final String parameter7 = this.getParameter("gridfont");
            final Font fontParm3;
            if (parameter7 != null && (fontParm3 = this.getFontParm(parameter7)) != null) {
                this.gridFont = fontParm3;
            }
        }
        catch (Exception ex7) {}
        this.listFont = this.getFontParm(this.listFontStr);
        try {
            final String parameter8 = this.getParameter("listfont");
            final Font fontParm4;
            if (parameter8 != null && (fontParm4 = this.getFontParm(parameter8)) != null) {
                this.listFont = fontParm4;
            }
        }
        catch (Exception ex8) {}
        try {
            final String parameter9 = this.getParameter("gridsize");
            if (parameter9 != null) {
                final StringTokenizer stringTokenizer5 = new StringTokenizer(parameter9, ",");
                final int int1;
                if ((int1 = Integer.parseInt(stringTokenizer5.nextToken())) > 0) {
                    this.gridRows = int1;
                }
                final int int2;
                if ((int2 = Integer.parseInt(stringTokenizer5.nextToken())) > 0) {
                    this.gridCols = int2;
                }
                final int int3;
                if ((int3 = Integer.parseInt(stringTokenizer5.nextToken())) > 0) {
                    this.gridSize = int3;
                }
            }
        }
        catch (Exception ex9) {}
        try {
            final String parameter10 = this.getParameter("files");
            if (parameter10 != null) {
                final StringTokenizer stringTokenizer6 = new StringTokenizer(parameter10, ",");
                while (stringTokenizer6.hasMoreTokens()) {
                    this.files.addElement(stringTokenizer6.nextToken());
                }
            }
        }
        catch (Exception ex10) {}
        final Graphics graphics = this.getGraphics();
        final Dimension size = this.size();
        (this.grid = new WSGrid(this.gridRows, this.gridCols, this.gridSize, this.gridFont)).setColors(this.gridFgColor, this.gridBgColor, this.scrnBdColor, this.gridFdColor, this.gridHiColor);
        this.grid.clear();
        graphics.setFont(this.listFont);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int x = this.grid.x + this.grid.width + this.gap;
        this.list = new WSList(size.width - x, this.grid.height - 2 * (fontMetrics.getHeight() + this.gap), this.listFont);
        this.list.x = x;
        this.list.y = this.grid.y + fontMetrics.getHeight() + this.gap;
        this.list.setColors(this.listFgColor, this.listBgColor, this.scrnBdColor, this.listFdColor);
        graphics.setFont(this.bttnFont);
        final int width = this.list.width;
        final int height = fontMetrics.getHeight();
        final Polygon polygon = new Polygon();
        polygon.addPoint(0, -height / 2 + 2);
        polygon.addPoint(-fontMetrics.getMaxAdvance() / 2, height / 2 - 2);
        polygon.addPoint(fontMetrics.getMaxAdvance() / 2, height / 2 - 2);
        this.scrollUp = new WSButton(polygon, width, height);
        this.scrollUp.x = this.list.x;
        this.scrollUp.y = this.grid.y;
        this.scrollUp.setColors(this.bttnFgColor, this.bttnBgColor, this.scrnBdColor);
        final Polygon polygon2 = new Polygon();
        polygon2.addPoint(0, height / 2 - 2);
        polygon2.addPoint(-fontMetrics.getMaxAdvance() / 2, -height / 2 + 2);
        polygon2.addPoint(fontMetrics.getMaxAdvance() / 2, -height / 2 + 2);
        this.scrollDn = new WSButton(polygon2, width, height);
        this.scrollDn.x = this.list.x;
        this.scrollDn.y = this.list.y + this.list.height + this.gap;
        this.scrollDn.setColors(this.bttnFgColor, this.bttnBgColor, this.scrnBdColor);
        graphics.setFont(this.bttnFont);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics();
        final String s = "New Game";
        final int n = fontMetrics2.stringWidth(s) + fontMetrics2.getMaxAdvance();
        final int n2 = 3 * fontMetrics2.getHeight() / 2;
        this.newGame = new WSButton(s, this.bttnFont, n, n2);
        final int x2 = 0;
        final int n3 = size.height - n2;
        this.newGame.x = x2;
        this.newGame.y = n3;
        this.newGame.setColors(this.bttnFgColor, this.bttnBgColor, this.scrnBdColor);
        final String s2 = "Solve Game";
        final int x3 = x2 + n + this.gap;
        this.solveGame = new WSButton(s2, this.bttnFont, fontMetrics2.stringWidth(s2) + fontMetrics2.getMaxAdvance(), n2);
        this.solveGame.x = x3;
        this.solveGame.y = n3;
        this.solveGame.setColors(this.bttnFgColor, this.bttnBgColor, this.scrnBdColor);
        this.fileNum = 0;
        this.scroll = 0;
        this.timeText = "";
        this.statText = "";
        this.subjText = "";
        WordSearch.words = new Vector();
        this.grid.fill();
        this.endGame();
        this.gameState = 1;
    }
    
    public Color getColorParm(final String s) {
        if (s.equalsIgnoreCase("black")) {
            return Color.black;
        }
        if (s.equalsIgnoreCase("blue")) {
            return Color.blue;
        }
        if (s.equalsIgnoreCase("cyan")) {
            return Color.cyan;
        }
        if (s.equalsIgnoreCase("darkGray")) {
            return Color.darkGray;
        }
        if (s.equalsIgnoreCase("gray")) {
            return Color.gray;
        }
        if (s.equalsIgnoreCase("green")) {
            return Color.green;
        }
        if (s.equalsIgnoreCase("lightGray")) {
            return Color.lightGray;
        }
        if (s.equalsIgnoreCase("magenta")) {
            return Color.magenta;
        }
        if (s.equalsIgnoreCase("orange")) {
            return Color.orange;
        }
        if (s.equalsIgnoreCase("pink")) {
            return Color.pink;
        }
        if (s.equalsIgnoreCase("red")) {
            return Color.red;
        }
        if (s.equalsIgnoreCase("white")) {
            return Color.white;
        }
        if (s.equalsIgnoreCase("yellow")) {
            return Color.yellow;
        }
        if (s.length() == 7 && s.charAt(0) == '#') {
            return new Color(Integer.parseInt(s.substring(1, 3), 16), Integer.parseInt(s.substring(3, 5), 16), Integer.parseInt(s.substring(5, 7), 16));
        }
        return Color.black;
    }
    
    public Font getFontParm(final String s) {
        String s2 = "";
        int n = -1;
        int n2 = -1;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        final String nextToken = stringTokenizer.nextToken();
        if (nextToken.equalsIgnoreCase("Courier")) {
            s2 = "Courier";
        }
        else if (nextToken.equalsIgnoreCase("Dialog")) {
            s2 = "Dialog";
        }
        else if (nextToken.equalsIgnoreCase("Helvetica")) {
            s2 = "Helvetica";
        }
        else if (nextToken.equalsIgnoreCase("Symbol")) {
            s2 = "Symbol";
        }
        else if (nextToken.equalsIgnoreCase("TimesRoman")) {
            s2 = "TimesRoman";
        }
        final String nextToken2 = stringTokenizer.nextToken();
        if (nextToken2.equalsIgnoreCase("plain")) {
            n = 0;
        }
        else if (nextToken2.equalsIgnoreCase("bold")) {
            n = 1;
        }
        else if (nextToken2.equalsIgnoreCase("italic")) {
            n = 2;
        }
        else if (nextToken2.equalsIgnoreCase("boldItalic")) {
            n = 3;
        }
        final int int1;
        if ((int1 = Integer.parseInt(stringTokenizer.nextToken())) > 0) {
            n2 = int1;
        }
        if (s2 != "" && n >= 0 && n2 >= 0) {
            return new Font(s2, n, n2);
        }
        return null;
    }
    
    public void start() {
        if (this.loopThread == null) {
            (this.loopThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.loopThread != null) {
            this.loopThread.stop();
            this.loopThread = null;
        }
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        long currentTimeMillis = System.currentTimeMillis();
        while (Thread.currentThread() == this.loopThread) {
            final WSList list = this.list;
            list.scroll += this.scroll;
            if (this.gameState == 2) {
                final Date date = new Date(System.currentTimeMillis() - this.startTime);
                String s = new Integer(date.getMinutes()).toString();
                String s2 = new Integer(date.getSeconds()).toString();
                if (s.length() < 2) {
                    s = "0" + s;
                }
                if (s2.length() < 2) {
                    s2 = "0" + s2;
                }
                this.timeText = "Time: " + s + ":" + s2;
                this.statText = "Found: " + this.count + "/" + WordSearch.words.size();
            }
            this.repaint();
            try {
                currentTimeMillis += 50L;
                Thread.sleep(Math.max(0L, currentTimeMillis - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.newGame.inside(n, n2)) {
            this.initGame();
        }
        if (this.gameState == 2 && this.solveGame.inside(n, n2)) {
            this.solveGame();
        }
        if (this.gameState != 1 && this.scrollUp.inside(n, n2)) {
            this.scroll = -1;
        }
        if (this.gameState != 1 && this.scrollDn.inside(n, n2)) {
            this.scroll = 1;
        }
        if (this.gameState == 2 && this.grid.inside(n, n2)) {
            this.grid.select = true;
            this.grid.startX = n;
            this.grid.startY = n2;
            this.grid.endX = n;
            this.grid.endY = n2;
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int endX, final int endY) {
        if (this.gameState == 2 && this.grid.select) {
            this.grid.endX = endX;
            this.grid.endY = endY;
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.scroll = 0;
        if (this.gameState == 2 && this.grid.select) {
            this.grid.select = false;
            if (this.grid.checkSelection() && ++this.count >= WordSearch.words.size()) {
                this.timeText += " Done!";
                this.statText = "Found: " + this.count + "/" + WordSearch.words.size();
                this.endGame();
            }
        }
        return true;
    }
    
    public void initGame() {
        this.setWords();
        this.grid.select = false;
        this.grid.fill();
        this.list.scroll = 0;
        this.count = 0;
        this.startTime = System.currentTimeMillis();
        this.timeText = "";
        this.statText = "";
        this.gameState = 2;
    }
    
    public void solveGame() {
        for (int i = 0; i < WordSearch.words.size(); ++i) {
            ((WSWord)WordSearch.words.elementAt(i)).found = true;
        }
        this.count = WordSearch.words.size();
        this.timeText = "Cheated!";
        this.endGame();
    }
    
    public void endGame() {
        this.gameState = 3;
    }
    
    public void setWords() {
        WordSearch.words.removeAllElements();
        if (this.fileNum >= this.files.size()) {
            this.fileNum = 0;
        }
        final String s = this.files.elementAt(this.fileNum);
        URL url = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (Exception ex) {}
        ++this.fileNum;
        this.subjText = "";
        try {
            String line;
            while ((line = new DataInputStream(url.openStream()).readLine()) != null) {
                if (line.startsWith("#")) {
                    this.subjText = "'" + line.substring(1) + "'";
                }
                else {
                    WordSearch.words.addElement(new WSWord(line));
                }
            }
        }
        catch (IOException ex2) {}
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.offGraphics == null || size.width != this.offDimension.width || size.height != this.offDimension.height) {
            this.offDimension = new Dimension(size.width, size.height);
            this.offImage = this.createImage(size.width, size.height);
            this.offGraphics = this.offImage.getGraphics();
        }
        this.offGraphics.setColor(this.scrnBgColor);
        this.offGraphics.fillRect(0, 0, size.width, size.height);
        this.grid.draw(this.offGraphics);
        this.list.draw(this.offGraphics);
        this.scrollUp.draw(this.offGraphics);
        this.scrollDn.draw(this.offGraphics);
        this.newGame.draw(this.offGraphics);
        this.solveGame.draw(this.offGraphics);
        this.offGraphics.setColor(this.scrnFgColor);
        this.offGraphics.setFont(this.scrnFont);
        final FontMetrics fontMetrics = this.offGraphics.getFontMetrics();
        if (this.gameState == 1) {
            final int n = (this.grid.x + this.grid.width) / 2;
            final int n2 = (this.grid.y + this.grid.height) / 2;
            final String s = "Word Search";
            final String s2 = "Copyright 1999 by Mike Hall";
            final int n3 = Math.max(fontMetrics.stringWidth(s), fontMetrics.stringWidth(s2)) + 2 * fontMetrics.getMaxAdvance();
            final int n4 = 5 * fontMetrics.getHeight();
            this.offGraphics.setColor(this.gridFgColor);
            this.offGraphics.fillRect(n - n3 / 2, n2 - n4 / 2, n3, n4);
            this.offGraphics.setColor(this.gridBgColor);
            this.offGraphics.drawRect(n - n3 / 2 + 1, n2 - n4 / 2 + 1, n3 - 3, n4 - 3);
            this.offGraphics.drawString(s, n - fontMetrics.stringWidth(s) / 2, n2 - n4 / 2 + 2 * fontMetrics.getHeight());
            this.offGraphics.drawString(s2, n - fontMetrics.stringWidth(s2) / 2, n2 + n4 / 2 - fontMetrics.getHeight() - fontMetrics.getDescent());
        }
        final int n5 = (this.grid.x + this.grid.width - fontMetrics.stringWidth(this.subjText)) / 2;
        final int n6 = this.grid.y + this.grid.height + fontMetrics.getHeight();
        this.offGraphics.drawString(this.subjText, n5, n6);
        final String timeText = this.timeText;
        this.offGraphics.drawString(timeText, Math.min(size.width - fontMetrics.stringWidth(timeText), this.list.x), n6);
        final String statText = this.statText;
        this.offGraphics.drawString(statText, Math.min(size.width - fontMetrics.stringWidth(statText), this.list.x), n6 + fontMetrics.getHeight());
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    public WordSearch() {
        this.scrnFgColor = Color.black;
        this.scrnBgColor = Color.white;
        this.scrnBdColor = Color.black;
        this.scrnFontStr = "Helvetica,bold,12";
        this.bttnFgColor = Color.black;
        this.bttnBgColor = Color.lightGray;
        this.bttnFontStr = "Dialog,bold,10";
        this.gridFgColor = Color.black;
        this.gridBgColor = Color.white;
        this.gridHiColor = Color.yellow;
        this.gridFdColor = Color.lightGray;
        this.gridFontStr = "Courier,plain,14";
        this.listFgColor = Color.black;
        this.listBgColor = Color.white;
        this.listFdColor = Color.lightGray;
        this.listFontStr = "Helvetica,plain,12";
        this.gridRows = 15;
        this.gridCols = 15;
        this.gridSize = 20;
        this.files = new Vector();
        this.gap = 4;
    }
}
