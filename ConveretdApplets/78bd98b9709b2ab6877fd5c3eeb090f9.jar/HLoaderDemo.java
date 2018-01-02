import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.util.StringTokenizer;
import java.net.URLConnection;
import java.io.DataInputStream;
import java.awt.Font;
import java.awt.Rectangle;
import java.net.URL;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class HLoaderDemo implements Runnable
{
    private String fileName;
    private String[] headLine;
    private String[] frame;
    private String[] tmp;
    private String stdFont;
    private Color[] fontColor;
    private Color defaultFgColor;
    private int[] fontSize;
    private int[] fontStyle;
    private int numOfHeadline;
    private int header;
    private int[] loc;
    private int changes;
    private int lastRect;
    private int refreshRate;
    private int maxHeadline;
    private URL[] url;
    private HeadlinerDemo nl;
    private long currentTime;
    private Rectangle[] rect;
    private Font[] font;
    
    public HLoaderDemo(final String fileName, final String[] headLine, final Color[] fontColor, final int[] fontSize, final int[] fontStyle, final URL[] url, final String[] frame, final int numOfHeadline, final int header, final HeadlinerDemo nl, final int lastRect, final int refreshRate, final Rectangle[] rect, final int maxHeadline, final String stdFont, final Color defaultFgColor, final Font[] font) {
        this.fileName = fileName;
        this.headLine = headLine;
        this.frame = frame;
        this.fontColor = fontColor;
        this.fontSize = fontSize;
        this.fontStyle = fontStyle;
        this.numOfHeadline = numOfHeadline;
        this.maxHeadline = maxHeadline;
        this.header = header;
        this.url = url;
        this.nl = nl;
        this.lastRect = lastRect;
        this.refreshRate = refreshRate;
        this.rect = rect;
        this.stdFont = stdFont;
        this.defaultFgColor = defaultFgColor;
        this.font = font;
        this.currentTime = System.currentTimeMillis();
    }
    
    public void run() {
        while (this.nl.runner.isAlive() && this.lastRect < this.maxHeadline) {
            if (System.currentTimeMillis() - this.currentTime > this.refreshRate * 1000) {
                this.openConnection();
                this.checkUpdate();
                if (this.changes > 0) {
                    this.informParent();
                }
                this.setTime();
            }
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex) {}
        }
    }
    
    public void setTime() {
        this.currentTime = System.currentTimeMillis();
    }
    
    public void openConnection() {
        try {
            final URLConnection openConnection = new URL(this.nl.getCodeBase(), this.fileName).openConnection();
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            int n = 0;
            this.tmp = new String[this.numOfHeadline];
            for (String s = dataInputStream.readLine(); s != null && n < this.numOfHeadline; this.tmp[n++] = s.trim(), s = dataInputStream.readLine()) {}
            dataInputStream.close();
        }
        catch (Exception ex) {
            this.nl.showStatus("Error opening file...");
        }
    }
    
    public void checkUpdate() {
        try {
            this.changes = 0;
            boolean b;
            do {
                b = false;
                for (int i = 0; i < this.numOfHeadline; ++i) {
                    final String trim = this.tmp[i].substring(0, this.tmp[i].indexOf(124)).trim();
                    boolean b2 = false;
                    for (int numOfHeadline = this.numOfHeadline, lastRect = this.lastRect; numOfHeadline-- > 0 && !b2 && lastRect >= 0; b2 = true) {
                        if (trim.equals(this.headLine[--lastRect])) {}
                    }
                    if (!b2) {
                        ++this.changes;
                        this.updateArray(i);
                        ++this.lastRect;
                        b = true;
                    }
                }
            } while (b);
        }
        catch (Exception ex) {}
    }
    
    public void updateArray(final int n) {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.tmp[n], "|");
        try {
            this.headLine[this.lastRect] = stringTokenizer.nextToken().trim();
        }
        catch (Exception ex) {
            this.headLine[this.lastRect] = "Error..";
        }
        try {
            final String trim = stringTokenizer.nextToken().trim();
            final int index = trim.indexOf(",");
            final int index2 = trim.indexOf(",", index + 1);
            final int int1 = Integer.parseInt(trim.substring(0, index));
            final int int2 = Integer.parseInt(trim.substring(index + 1, index2));
            final int int3 = Integer.parseInt(trim.substring(index2 + 1, trim.length()));
            if (this.defaultFgColor == null) {
                this.fontColor[this.lastRect] = new Color(int1, int2, int3);
            }
            else {
                this.fontColor[this.lastRect] = this.defaultFgColor;
            }
        }
        catch (Exception ex2) {
            this.fontColor[this.lastRect] = new Color(255, 255, 255);
        }
        try {
            this.fontSize[this.lastRect] = Integer.parseInt(stringTokenizer.nextToken().trim().trim());
        }
        catch (Exception ex3) {
            this.fontSize[this.lastRect] = 12;
        }
        try {
            final String trim2 = stringTokenizer.nextToken().trim();
            int n2;
            if (trim2.equalsIgnoreCase("Font.BOLD")) {
                n2 = 1;
            }
            else if (trim2.equalsIgnoreCase("Font.ITALIC")) {
                n2 = 2;
            }
            else if (trim2.equalsIgnoreCase("Font.BOLD+Font.ITALIC")) {
                n2 = 3;
            }
            else if (trim2.equalsIgnoreCase("Font.ITALIC+Font.BOLD")) {
                n2 = 3;
            }
            else {
                n2 = 0;
            }
            this.fontStyle[this.lastRect] = n2;
        }
        catch (Exception ex4) {
            this.fontStyle[this.lastRect] = 0;
        }
        try {
            this.url[this.lastRect] = new URL(stringTokenizer.nextToken().trim());
        }
        catch (Exception ex5) {
            this.url[this.lastRect] = null;
        }
        try {
            this.frame[this.lastRect] = stringTokenizer.nextToken().trim();
        }
        catch (Exception ex6) {
            this.frame[this.lastRect] = "_top";
        }
        try {
            final int n3 = this.rect[this.lastRect - 1].y + this.rect[this.lastRect - 1].height;
            final int x = this.rect[this.lastRect - 1].x;
            this.font[this.lastRect] = new Font(this.stdFont, this.fontStyle[this.lastRect], this.fontSize[this.lastRect]);
            final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(this.font[this.lastRect]);
            this.rect[this.lastRect] = new Rectangle(x, n3, fontMetrics.stringWidth(this.headLine[this.lastRect]), fontMetrics.getHeight());
        }
        catch (Exception ex7) {
            this.rect[this.lastRect] = new Rectangle(0, 0, 0, 0);
        }
    }
    
    public void informParent() {
        this.nl.receive(this.changes);
    }
}
