import java.util.Random;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Vector;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetTextShow extends Canvas
{
    private IRCQNet theApp;
    private int typeMethod;
    private Image tImage;
    private Vector Colors;
    private int nFG;
    private int n2FG;
    private int nBG;
    private String panelLabel;
    private int width;
    private int height;
    private boolean pFirstTime;
    private String mText;
    private int specialNum;
    
    public IRCQNetTextShow(final IRCQNet theApp) {
        this.Colors = new Vector(17, 1);
        this.nFG = 1;
        this.n2FG = 1;
        this.pFirstTime = true;
        this.theApp = theApp;
        this.Colors.addElement(new Color(255, 255, 255));
        this.Colors.addElement(new Color(0, 0, 0));
        this.Colors.addElement(new Color(0, 0, 123));
        this.Colors.addElement(new Color(0, 146, 0));
        this.Colors.addElement(new Color(255, 0, 0));
        this.Colors.addElement(new Color(123, 0, 0));
        this.Colors.addElement(new Color(156, 0, 156));
        this.Colors.addElement(new Color(255, 125, 0));
        this.Colors.addElement(new Color(255, 255, 0));
        this.Colors.addElement(new Color(0, 255, 0));
        this.Colors.addElement(new Color(0, 146, 148));
        this.Colors.addElement(new Color(0, 255, 255));
        this.Colors.addElement(new Color(0, 0, 255));
        this.Colors.addElement(new Color(255, 0, 255));
        this.Colors.addElement(new Color(123, 125, 123));
        this.Colors.addElement(new Color(214, 211, 214));
    }
    
    public void setSNumber(final int typeMethod) {
        this.typeMethod = typeMethod;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.isVisible()) {
            return;
        }
        final int width = this.width;
        int height = this.height;
        if (this.panelLabel != null) {
            height -= 3;
            graphics.setColor(Color.white);
            graphics.drawRect(1, 4, width - 2, height - 2);
            graphics.setColor(Color.gray);
            graphics.drawRect(0, 3, width - 2, height - 2);
        }
        else {
            graphics.setColor(Color.white);
            graphics.drawRect(1, 1, width - 2, height - 2);
            graphics.setColor(Color.gray);
            graphics.drawRect(0, 0, width - 2, height - 2);
        }
        if (this.panelLabel != null) {
            graphics.setFont(new Font("Helvetica", 0, 10));
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.setColor(IRCQNetColors.controlColor);
            graphics.fillRect(5, 0, fontMetrics.stringWidth(this.panelLabel) + 4, 10);
            graphics.setColor(IRCQNetColors.controlColorFG);
            graphics.drawString(this.panelLabel, 6, 7);
        }
        if (this.pFirstTime && this.mText != null) {
            this.setText(this.mText);
            this.pFirstTime = false;
        }
        if (this.tImage == null) {
            return;
        }
        graphics.drawImage(this.tImage, (width - this.tImage.getWidth(null)) / 2, (height - this.tImage.getHeight(null)) / 2, null);
    }
    
    public void setText(String procText) {
        this.mText = procText;
        procText = this.procText(procText);
        this.appendText(procText);
    }
    
    public void appendToLine(final Line line, final String s, final Color color, final Color color2, final Boolean b, final Boolean b2) {
        line.Strings.addElement(s);
        line.BG.addElement(color2);
        line.FG.addElement(color);
        line.UnderLine.addElement(b);
        line.Bold.addElement(b2);
    }
    
    public void setSpecial(final int specialNum) {
        this.specialNum = specialNum;
    }
    
    public void cleaLine(final Line line) {
        line.Strings.removeAllElements();
        line.BG.removeAllElements();
        line.FG.removeAllElements();
        line.UnderLine.removeAllElements();
        line.Bold.removeAllElements();
    }
    
    public IRCQNetParam getParams() {
        return this.theApp.MPanel.getParams();
    }
    
    public void appendText(final String s) {
        final Line line = new Line();
        line.setFullMode(true, this.Colors.elementAt(this.nBG));
        Color white = Color.white;
        Color black = Color.black;
        Boolean b = new Boolean(false);
        Boolean b2 = new Boolean(false);
        String string = "";
        new StringTokenizer(s);
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            switch (s.charAt(i)) {
                case '\u0001': {
                    if (n != 0) {
                        this.appendToLine(line, string, black, white, b2, b);
                        string = "";
                    }
                    n = 0;
                    break;
                }
                case '\u0002': {
                    if (n != 0) {
                        this.appendToLine(line, string, black, white, b2, b);
                        string = "";
                    }
                    b = new Boolean(!b);
                    n = 0;
                    break;
                }
                case '\u0003': {
                    if (n != 0) {
                        this.appendToLine(line, string, black, white, b2, b);
                        string = "";
                    }
                    n = 0;
                    int index = -1;
                    final String s2 = "";
                    try {
                        if (i + 1 < s.length()) {
                            if (!Character.isDigit(s.charAt(i + 1))) {
                                break;
                            }
                            String s3 = s2 + s.charAt(i + 1);
                            if (++i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                                s3 += s.charAt(i + 1);
                                ++i;
                            }
                            final int int1 = Integer.parseInt(s3);
                            if (int1 >= 0 && int1 < 16) {
                                black = (Color)this.Colors.elementAt(int1);
                            }
                            if (i + 2 < s.length()) {
                                index = s.substring(i + 1, i + 2).indexOf(",");
                            }
                            if (index != -1) {
                                ++i;
                                try {
                                    String s4 = "";
                                    if (i + 1 < s.length()) {
                                        if (Character.isDigit(s.charAt(i + 1))) {
                                            s4 += s.charAt(i + 1);
                                            if (++i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                                                s4 += s.charAt(i + 1);
                                                ++i;
                                            }
                                        }
                                        final int int2 = Integer.parseInt(s4);
                                        if (int2 >= 0 && int2 < 16) {
                                            white = (Color)this.Colors.elementAt(int2);
                                        }
                                    }
                                }
                                catch (NumberFormatException ex) {}
                            }
                        }
                    }
                    catch (NumberFormatException ex2) {}
                    n = 0;
                    break;
                }
                case '\u001f': {
                    if (n != 0) {
                        this.appendToLine(line, string, black, white, b2, b);
                        string = "";
                    }
                    b2 = new Boolean(!b2);
                    n = 0;
                    break;
                }
                default: {
                    n = 1;
                    string += s.charAt(i);
                    if (i + 1 == s.length()) {
                        this.appendToLine(line, string, black, white, b2, b);
                        string = "";
                        break;
                    }
                    break;
                }
            }
        }
        if (line.RenderLine(this.width - 10, this) < 0) {
            return;
        }
        this.tImage = (Image)line.getImages().elementAt(0);
        if (this.isVisible()) {
            this.repaint();
        }
    }
    
    public void setPanelLabel(final String panelLabel) {
        this.panelLabel = panelLabel;
    }
    
    public void reshape(final int n, final int n2, final int width, final int height) {
        super.reshape(n, n2, width, height);
        this.width = width;
        this.height = height;
    }
    
    public void setBG(final int nbg) {
        this.nBG = nbg;
    }
    
    public void setFG(final int nfg) {
        this.nFG = nfg;
    }
    
    public void setFG(final int nfg, final int n2FG) {
        this.nFG = nfg;
        this.n2FG = n2FG;
    }
    
    public String procText(String s) {
        final int typeMethod = this.typeMethod;
        String s2 = "" + "\u00031," + this.nBG;
        switch (typeMethod) {
            case 1: {
                final Random random = new Random();
                for (int i = 0; i < s.length(); ++i) {
                    int j;
                    do {
                        j = Math.abs(random.nextInt()) % 15 + 1;
                    } while (j == this.nBG);
                    if (Character.isLetter(s.charAt(i))) {
                        s2 = s2 + "\u0003" + j + s.charAt(i);
                    }
                    else {
                        s2 += s.charAt(i);
                    }
                }
                break;
            }
            case 2: {
                s = this.removeAllColors(s, true, true);
                final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
                final int countTokens = stringTokenizer.countTokens();
                s2 = "\u0003" + this.nFG + "," + this.nBG;
                for (int k = 0; k < countTokens; ++k) {
                    final String nextToken = stringTokenizer.nextToken();
                    if (nextToken.length() == 0) {
                        s2 += " ";
                    }
                    else if (nextToken.length() == 1) {
                        s2 = s2 + "\u0003" + this.nFG + nextToken + " ";
                    }
                    else if (nextToken.length() > 1) {
                        int l = 0;
                        while (l < nextToken.length()) {
                            if (Character.isLetter(nextToken.charAt(l)) || Character.isDigit(nextToken.charAt(l))) {
                                s2 = s2 + "\u0003" + this.nFG + nextToken.charAt(l);
                                if (l + 1 <= nextToken.length()) {
                                    s2 = s2 + "\u0003" + this.n2FG + nextToken.substring(l + 1) + " ";
                                    break;
                                }
                                break;
                            }
                            else {
                                s2 += nextToken.charAt(l);
                                ++l;
                            }
                        }
                    }
                }
                break;
            }
            case 3: {
                this.getParams().setSpecials(this.specialNum);
                final int size = this.getParams().sColorVector.size();
                s = this.removeAllColors(s);
                final int length = s.length();
                final int n = length / size;
                int n2 = 0;
                int n3 = 0;
                for (int n4 = 0; n2 < length; ++n2, ++n4) {
                    if (n4 == n && n3 < size - 1) {
                        ++n3;
                        n4 = 0;
                    }
                    if (n4 == 0) {
                        if (this.nBG < 10) {
                            s2 = s2 + "\u0003" + (int)this.getParams().sColorVector.elementAt(n3) + ",0" + this.nBG;
                        }
                        else {
                            s2 = s2 + "\u0003" + (int)this.getParams().sColorVector.elementAt(n3) + "," + this.nBG;
                        }
                    }
                    s2 += s.charAt(n2);
                }
                break;
            }
            default: {
                s2 = s;
                break;
            }
        }
        return s2;
    }
    
    public String removeAllColors(final String s) {
        new StringTokenizer(s);
        String string = "";
        int i = 0;
    Label_0228_Outer:
        while (i < s.length()) {
            while (true) {
                switch (s.charAt(i)) {
                    case '\u0003': {
                        try {
                            if (i + 1 >= s.length() || !Character.isDigit(s.charAt(i + 1))) {
                                break Label_0228;
                            }
                            if (++i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                                ++i;
                            }
                            if (i + 2 < s.length() && s.substring(i + 1, i + 2).indexOf(",") != -1) {
                                ++i;
                                try {
                                    if (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                                        ++i;
                                        if (Character.isDigit(s.charAt(i + 1))) {
                                            ++i;
                                        }
                                    }
                                }
                                catch (NumberFormatException ex) {}
                            }
                            break Label_0228;
                        }
                        catch (NumberFormatException ex2) {
                            break Label_0228;
                        }
                        break;
                    }
                    case '\u0001':
                    case '\u0002':
                    case '\u001f': {
                        ++i;
                        continue Label_0228_Outer;
                    }
                }
                string += s.charAt(i);
                continue;
            }
        }
        return string;
    }
    
    public String removeAllColors(final String s, final boolean b, final boolean b2) {
        new StringTokenizer(s);
        String s2 = "";
        int i = 0;
        while (i < s.length()) {
            Label_0307: {
                switch (s.charAt(i)) {
                    case '\u0002': {
                        if (b) {
                            s2 += s.charAt(i);
                        }
                        break Label_0307;
                    }
                    case '\u0003': {
                        try {
                            if (i + 1 >= s.length() || !Character.isDigit(s.charAt(i + 1))) {
                                break Label_0307;
                            }
                            if (++i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                                ++i;
                            }
                            if (i + 2 < s.length() && s.substring(i + 1, i + 2).indexOf(",") != -1) {
                                ++i;
                                try {
                                    if (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                                        ++i;
                                        if (Character.isDigit(s.charAt(i + 1))) {
                                            ++i;
                                        }
                                    }
                                }
                                catch (NumberFormatException ex) {}
                            }
                            break Label_0307;
                        }
                        catch (NumberFormatException ex2) {
                            break Label_0307;
                        }
                    }
                    case '\u001f': {
                        if (b2) {
                            s2 += s.charAt(i);
                        }
                        break Label_0307;
                    }
                    default: {
                        s2 += s.charAt(i);
                    }
                    case '\u0001': {
                        ++i;
                        continue;
                    }
                }
            }
        }
        return s2;
    }
}
