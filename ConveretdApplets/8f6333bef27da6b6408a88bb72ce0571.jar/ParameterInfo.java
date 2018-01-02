import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class ParameterInfo
{
    public String markSound;
    public String clickSound;
    public String openSound;
    public String closeSound;
    public boolean hasImages;
    public int imageCount;
    public int imageSize;
    public boolean hasNodes;
    public int nodeCount;
    public String fontName;
    public int fontSize;
    public int fontStyle;
    public Font font;
    public String seFontName;
    public int seFontSize;
    public int seFontStyle;
    public Font seFont;
    public int biggestFontSize;
    public Color bgColor;
    public Color foColor;
    public Color seColor;
    public Color coColor;
    public boolean hasConnectors;
    public int iconXSpacing;
    public int iconYSpacing;
    public int levelIndent;
    public int initialX;
    public int initialY;
    public boolean startWithAnimation;
    public int animationWait;
    public String animationStyle;
    public String bgImage;
    public boolean bgImageStyle;
    public int bgImagePosX;
    public int bgImagePosY;
    
    public ParameterInfo() {
        this.markSound = null;
        this.clickSound = null;
        this.openSound = null;
        this.closeSound = null;
        this.hasImages = false;
        this.imageCount = 0;
        this.imageSize = 32;
        this.hasNodes = false;
        this.nodeCount = 0;
        this.fontName = new String("TimesRoman");
        this.fontSize = 12;
        this.fontStyle = 0;
        this.font = new Font(this.fontName, this.fontStyle, this.fontSize);
        this.seFontName = new String("TimesRoman");
        this.seFontSize = 12;
        this.seFontStyle = 0;
        this.seFont = new Font(this.fontName, this.fontStyle, this.fontSize);
        this.biggestFontSize = 12;
        this.bgColor = new Color(0, 0, 0);
        this.foColor = new Color(255, 255, 255);
        this.seColor = new Color(255, 0, 0);
        this.coColor = new Color(255, 0, 0);
        this.hasConnectors = false;
        this.iconXSpacing = 4;
        this.iconYSpacing = 2;
        this.levelIndent = this.iconXSpacing + this.imageSize;
        this.initialX = 10;
        this.initialY = 10;
        this.startWithAnimation = false;
        this.animationWait = 2;
        this.animationStyle = new String("grow");
        this.bgImage = null;
        this.bgImageStyle = false;
        this.bgImagePosX = 0;
        this.bgImagePosY = 0;
        this.hasImages = false;
        this.imageCount = 0;
        this.imageSize = 32;
        this.hasNodes = false;
        this.nodeCount = 0;
        this.fontName = new String("TimesRoman");
        this.fontSize = 12;
        this.fontStyle = 0;
        this.font = new Font(this.fontName, this.fontStyle, this.fontSize);
        this.seFontName = new String("TimesRoman");
        this.seFontSize = 12;
        this.seFontStyle = 0;
        this.seFont = new Font(this.fontName, this.fontStyle, this.fontSize);
        this.biggestFontSize = 12;
        this.bgColor = new Color(0, 0, 0);
        this.foColor = new Color(255, 255, 255);
        this.seColor = new Color(255, 0, 0);
        this.coColor = new Color(255, 0, 0);
        this.hasConnectors = false;
        this.iconXSpacing = 4;
        this.iconYSpacing = 2;
        this.levelIndent = this.iconXSpacing + this.imageSize;
        this.initialX = 10;
        this.initialY = 10;
        this.startWithAnimation = false;
        this.animationWait = 2;
        this.animationStyle = new String("grow");
        this.bgImage = null;
        this.bgImageStyle = false;
        this.bgImagePosX = 0;
        this.bgImagePosY = 0;
    }
    
    public void proccess(final String s, final String s2) {
        if (s2 == null) {
            return;
        }
        if (s.equalsIgnoreCase("marksound")) {
            this.markSound = s2;
            return;
        }
        if (s.equalsIgnoreCase("clicksound")) {
            this.clickSound = s2;
            return;
        }
        if (s.equalsIgnoreCase("opensound")) {
            this.openSound = s2;
            return;
        }
        if (s.equalsIgnoreCase("closesound")) {
            this.closeSound = s2;
            return;
        }
        if (s.equals("bgimagestyle")) {
            if (s2.equalsIgnoreCase("tile")) {
                this.bgImageStyle = true;
            }
            else {
                this.bgImageStyle = false;
            }
            return;
        }
        if (s.equals("bgimageposx")) {
            try {
                this.bgImagePosX = Integer.parseInt(s2);
            }
            catch (NumberFormatException ex) {
                this.bgImagePosX = 0;
            }
            return;
        }
        if (s.equals("bgimageposy")) {
            try {
                this.bgImagePosY = Integer.parseInt(s2);
            }
            catch (NumberFormatException ex2) {
                this.bgImagePosY = 0;
            }
            return;
        }
        if (s.equals("bgimage")) {
            this.bgImage = s2;
            return;
        }
        if (s.endsWith("color")) {
            final Color color = parseColor(s2);
            if (s.equals("bgcolor")) {
                this.bgColor = color;
                return;
            }
            if (s.equals("focolor")) {
                this.foColor = color;
                return;
            }
            if (s.equals("secolor")) {
                this.seColor = color;
                return;
            }
            if (s.equals("cocolor")) {
                this.coColor = color;
                this.hasConnectors = true;
            }
        }
        else {
            if (s.endsWith("font")) {
                final Font font = parseFont(s2);
                if (s.equals("font")) {
                    this.font = font;
                    this.fontSize = font.getSize();
                    this.fontName = font.getName();
                    this.fontStyle = font.getStyle();
                }
                if (s.equals("sefont")) {
                    this.seFont = font;
                    this.seFontSize = font.getSize();
                    this.seFontName = font.getName();
                    this.seFontStyle = font.getStyle();
                }
                if (this.fontSize > this.seFontSize) {
                    this.biggestFontSize = this.fontSize;
                }
                else {
                    this.biggestFontSize = this.seFontSize;
                }
                return;
            }
            if (s.equals("iconsize")) {
                try {
                    this.imageSize = Integer.parseInt(s2);
                }
                catch (NumberFormatException ex3) {
                    this.imageSize = 32;
                }
                this.levelIndent = this.iconXSpacing + this.imageSize;
                return;
            }
            if (s.equals("animate")) {
                this.startWithAnimation = true;
                if (s2.equalsIgnoreCase("grow") || s2.equalsIgnoreCase("left") || s2.equalsIgnoreCase("right") || s2.equalsIgnoreCase("top") || s2.equalsIgnoreCase("bottom") || s2.equalsIgnoreCase("icongrow")) {
                    this.animationStyle = s2;
                }
                return;
            }
            if (s.equals("animationdelay")) {
                int animationWait;
                try {
                    animationWait = Integer.parseInt(s2);
                }
                catch (NumberFormatException ex4) {
                    animationWait = this.animationWait;
                }
                this.animationWait = animationWait;
            }
        }
    }
    
    public static Font parseFont(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",.;");
        final String s2 = new String("TimesRoman");
        int n = 0;
        String nextToken;
        int int1;
        try {
            nextToken = stringTokenizer.nextToken();
            int1 = Integer.parseInt(stringTokenizer.nextToken());
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken2 = stringTokenizer.nextToken();
                if (nextToken2.equalsIgnoreCase("PLAIN")) {}
                if (nextToken2.equalsIgnoreCase("BOLD")) {
                    ++n;
                }
                if (nextToken2.equalsIgnoreCase("ITALIC")) {
                    n += 2;
                }
            }
        }
        catch (NoSuchElementException ex) {
            return new Font("TimesRoman", 0, 12);
        }
        catch (NumberFormatException ex2) {
            return new Font("TimesRoman", 0, 12);
        }
        return new Font(nextToken, n, int1);
    }
    
    public static Color parseColor(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ,.;");
        int int1;
        int int2;
        int int3;
        try {
            int1 = Integer.parseInt(stringTokenizer.nextToken());
            int2 = Integer.parseInt(stringTokenizer.nextToken());
            int3 = Integer.parseInt(stringTokenizer.nextToken());
        }
        catch (NoSuchElementException ex) {
            return new Color(0, 0, 0);
        }
        catch (NumberFormatException ex2) {
            return new Color(0, 0, 0);
        }
        return new Color(int1, int2, int3);
    }
    
    public int processImageParameter(final String s) {
        int int1;
        try {
            int1 = Integer.parseInt(s.substring(5));
        }
        catch (NumberFormatException ex) {
            return -1;
        }
        this.hasImages = true;
        ++this.imageCount;
        return int1;
    }
    
    public Node processNode(final String s, final URL url) {
        this.hasNodes = true;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",;");
        final String nextToken = stringTokenizer.nextToken();
        final int int1 = Integer.parseInt(stringTokenizer.nextToken());
        final int int2 = Integer.parseInt(stringTokenizer.nextToken());
        final boolean b = !stringTokenizer.nextToken().equals("0");
        final boolean b2 = !stringTokenizer.nextToken().equals("0");
        String nextToken2 = null;
        URL url2 = null;
        try {
            nextToken2 = stringTokenizer.nextToken();
            url2 = new URL(nextToken2);
        }
        catch (MalformedURLException ex) {}
        if (url2 == null) {
            try {
                url2 = new URL(url, nextToken2);
            }
            catch (MalformedURLException ex2) {}
        }
        final String nextToken3 = stringTokenizer.nextToken();
        final boolean b3 = !stringTokenizer.nextToken().equals("0");
        final int int3 = Integer.parseInt(stringTokenizer.nextToken());
        String nextToken4 = null;
        try {
            nextToken4 = stringTokenizer.nextToken();
        }
        catch (NoSuchElementException ex3) {}
        String nextToken5 = null;
        try {
            nextToken5 = stringTokenizer.nextToken();
        }
        catch (NoSuchElementException ex4) {}
        ++this.nodeCount;
        return new Node(nextToken, int1, int2, b, b2, url2, nextToken3, b3, int3, nextToken4, nextToken5);
    }
    
    public Font biggestFont() {
        if (this.seFontSize > this.fontSize) {
            return this.seFont;
        }
        return this.font;
    }
}
