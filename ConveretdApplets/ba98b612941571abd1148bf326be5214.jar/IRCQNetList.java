import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Event;
import java.awt.Color;
import java.util.Vector;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetList extends Panel
{
    private int height;
    private int width;
    private int drawAt;
    private int Y;
    private FontMetrics FMatrics;
    private IRCQNet theApp;
    private Font MFont;
    private Vector ArgsList;
    private Vector Colors;
    private Vector Strings;
    private Vector BG;
    private Vector FG;
    private Vector Bold;
    private Vector UnderLine;
    private boolean IsModeWait;
    private int mWidth;
    private int mHeight;
    public int onLine;
    private int top;
    private int listLong;
    private IRCQNetPanel parent;
    private IRCQNetScrollBar SBar;
    private int LHeight;
    public int pointLine;
    private boolean sortedList;
    public boolean searchMode;
    
    public IRCQNetList(final IRCQNetScrollBar sBar, final IRCQNetPanel parent) {
        this.MFont = new Font("Helvetica", 0, 14);
        this.ArgsList = new Vector(100, 100);
        this.Colors = new Vector(17, 1);
        this.Strings = new Vector(10, 10);
        this.BG = new Vector(10, 10);
        this.FG = new Vector(10, 10);
        this.Bold = new Vector(10, 10);
        this.UnderLine = new Vector(10, 10);
        this.IsModeWait = true;
        this.sortedList = false;
        this.searchMode = false;
        this.parent = parent;
        this.SBar = sBar;
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
    
    public int GetListSize() {
        return this.ArgsList.size();
    }
    
    public void openChannel(final int n) {
        if (n <= this.ArgsList.size()) {
            this.postEvent(new Event(this, 10008, "!STATUS!;/JOIN " + this.ArgsList.elementAt(n).str1));
        }
    }
    
    public int whichLine(final int n, final int n2) {
        this.height = this.FMatrics.getHeight();
        this.pointLine = n2 / this.height + this.top;
        --this.pointLine;
        this.onLine = this.pointLine;
        this.repaint();
        return this.pointLine;
    }
    
    public void appendLine(final String s, final String s2, final String s3) {
        if (this.ArgsList.size() == 0) {
            final IRCQNetListLineObj ircqNetListLineObj = new IRCQNetListLineObj();
            ircqNetListLineObj.addObj(s, s2, s3);
            this.ArgsList.addElement(ircqNetListLineObj);
            return;
        }
        for (int i = 0; i < this.ArgsList.size(); ++i) {
            if (Integer.parseInt(s2) > Integer.parseInt(((IRCQNetListLineObj)this.ArgsList.elementAt(i)).str2)) {
                final IRCQNetListLineObj ircqNetListLineObj2 = new IRCQNetListLineObj();
                ircqNetListLineObj2.addObj(s, s2, s3);
                this.ArgsList.insertElementAt(ircqNetListLineObj2, i);
                return;
            }
        }
        final IRCQNetListLineObj ircqNetListLineObj3 = new IRCQNetListLineObj();
        ircqNetListLineObj3.addObj(s, s2, s3);
        this.ArgsList.addElement(ircqNetListLineObj3);
        System.gc();
    }
    
    public int doBeginWithTop(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.parent.theApp.MPanel.getParams().iBeginWithTop, ";");
        for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; ++i) {
            if (s.toLowerCase().indexOf(stringTokenizer.nextToken(";").toLowerCase()) >= 0) {
                return 1;
            }
        }
        return 0;
    }
    
    public void SortVector() {
        int n = 0;
        for (int i = 0; i < this.ArgsList.size(); ++i) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.parent.theApp.MPanel.getParams().iBeginWithTop, ";");
            for (int countTokens = stringTokenizer.countTokens(), j = 0; j < countTokens; ++j) {
                if (this.ArgsList.elementAt(i).str1.toLowerCase().startsWith("#" + stringTokenizer.nextToken(";").toLowerCase())) {
                    this.ArgsList.insertElementAt(this.ArgsList.elementAt(i), n);
                    this.ArgsList.removeElementAt(i + 1);
                    ++n;
                }
            }
            final StringTokenizer stringTokenizer2 = new StringTokenizer(this.parent.theApp.MPanel.getParams().iContainToTop, ";");
            for (int countTokens2 = stringTokenizer2.countTokens(), k = 0; k < countTokens2; ++k) {
                final String nextToken = stringTokenizer2.nextToken(";");
                if (this.ArgsList.elementAt(i).str1.toLowerCase().indexOf(nextToken.toLowerCase()) >= 0 || this.ArgsList.elementAt(i).str3.toLowerCase().indexOf(nextToken.toLowerCase()) >= 0) {
                    this.ArgsList.insertElementAt(this.ArgsList.elementAt(i), n);
                    this.ArgsList.removeElementAt(i + 1);
                    ++n;
                }
            }
        }
        for (int size = this.ArgsList.size(), l = 0; l < size; ++l) {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(this.parent.theApp.MPanel.getParams().iBeginWithBottom, ";");
            final int countTokens3 = stringTokenizer3.countTokens();
            boolean b = false;
            for (int n2 = 0; n2 < countTokens3; ++n2) {
                if (this.ArgsList.elementAt(l).str1.toLowerCase().startsWith("#" + stringTokenizer3.nextToken(";").toLowerCase())) {
                    b = true;
                    this.ArgsList.addElement(this.ArgsList.elementAt(l));
                    this.ArgsList.removeElementAt(l);
                }
            }
            if (l > 0 && b) {
                --l;
                --size;
            }
            final StringTokenizer stringTokenizer4 = new StringTokenizer(this.parent.theApp.MPanel.getParams().iContainToBottom, ";");
            final int countTokens4 = stringTokenizer4.countTokens();
            boolean b2 = false;
            for (int n3 = 0; n3 < countTokens4; ++n3) {
                final String nextToken2 = stringTokenizer4.nextToken(";");
                if (this.ArgsList.elementAt(l).str1.toLowerCase().indexOf(nextToken2.toLowerCase()) >= 0 || this.ArgsList.elementAt(l).str3.toLowerCase().indexOf(nextToken2.toLowerCase()) >= 0) {
                    b2 = true;
                    this.ArgsList.addElement(this.ArgsList.elementAt(l));
                    this.ArgsList.removeElementAt(l);
                }
            }
            if (l > 0 && b2) {
                --l;
                --size;
            }
        }
        int n4 = 0;
        for (int n5 = 0; n5 < this.ArgsList.size(); ++n5) {
            final StringTokenizer stringTokenizer5 = new StringTokenizer(this.parent.theApp.MPanel.getParams().iChannelToTop, ";");
            for (int countTokens5 = stringTokenizer5.countTokens(), n6 = 0; n6 < countTokens5; ++n6) {
                if (this.ArgsList.elementAt(n5).str1.toLowerCase().equalsIgnoreCase(stringTokenizer5.nextToken(";").toLowerCase())) {
                    this.ArgsList.insertElementAt(this.ArgsList.elementAt(n5), n4);
                    this.ArgsList.removeElementAt(n5 + 1);
                    ++n4;
                }
            }
        }
        if (this.searchMode) {
            for (int n7 = 0; n7 < this.ArgsList.size(); ++n7) {
                boolean b3 = false;
                final StringTokenizer stringTokenizer6 = new StringTokenizer(this.parent.theApp.MPanel.getParams().iRoomSearch, ";");
                for (int countTokens6 = stringTokenizer6.countTokens(), n8 = 0; n8 < countTokens6; ++n8) {
                    final String nextToken3 = stringTokenizer6.nextToken(";");
                    if (this.ArgsList.elementAt(n7).str1.toLowerCase().indexOf(nextToken3.toLowerCase()) != -1 || this.ArgsList.elementAt(n7).str3.toLowerCase().indexOf(nextToken3.toLowerCase()) != -1) {
                        b3 = true;
                    }
                }
                if (!b3) {
                    this.ArgsList.removeElementAt(n7);
                    --n7;
                }
            }
        }
        this.searchMode = false;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.isVisible()) {
            return;
        }
        this.setBackground(Color.white);
        if (this.IsModeWait) {
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, this.mWidth, this.mHeight);
            graphics.setFont(this.MFont);
            this.FMatrics = this.getFontMetrics(this.MFont);
            graphics.setColor(Color.blue);
            graphics.drawString("Requesting rooms list from IrCQNet Server, Please wait.", (this.mWidth - this.FMatrics.stringWidth("Requesting rooms list from IrCQNet Server, Please wait.")) / 2, this.mHeight / 2);
            return;
        }
        final int onLine = this.ArgsList.size() - 1;
        if (onLine < 1) {
            return;
        }
        int n = 0;
        this.listLong = this.mHeight / this.LHeight;
        this.listLong -= 2;
        if (this.onLine - this.listLong - 1 >= this.top) {
            while (this.onLine - this.listLong - 1 >= this.top) {
                ++this.top;
            }
        }
        if (this.onLine == this.top - 1) {
            --this.top;
        }
        if (this.onLine < 0) {
            this.onLine = 0;
        }
        if (this.onLine > onLine) {
            this.onLine = onLine;
        }
        if (this.top < 0) {
            this.top = 0;
        }
        if (this.top > onLine - this.listLong) {
            if (onLine > this.listLong) {
                this.top = onLine - this.listLong;
            }
            else {
                this.top = 0;
            }
        }
        Image image;
        Image image2;
        Image image3;
        try {
            image = this.createImage(this.mWidth, this.mHeight);
            image2 = this.createImage(1024, this.LHeight);
            image3 = this.createImage(1024, this.LHeight);
        }
        catch (IllegalArgumentException ex) {
            return;
        }
        final Graphics graphics2 = image2.getGraphics();
        final Graphics graphics3 = image.getGraphics();
        final Graphics graphics4 = image3.getGraphics();
        this.paintSelectedLine(graphics2, this.ArgsList.elementAt(this.onLine).str1, this.ArgsList.elementAt(this.onLine).str2, this.ArgsList.elementAt(this.onLine).str3);
        image2.flush();
        graphics2.dispose();
        int listLong;
        if (onLine < this.listLong) {
            listLong = onLine;
        }
        else {
            listLong = this.listLong;
        }
        for (int i = this.top; i <= this.top + listLong; ++i) {
            if (i != this.onLine) {
                graphics4.setColor(Color.white);
                graphics4.fillRect(0, 0, 1024, this.LHeight);
                this.paintLine(graphics4, ((IRCQNetListLineObj)this.ArgsList.elementAt(i)).str1, ((IRCQNetListLineObj)this.ArgsList.elementAt(i)).str2, ((IRCQNetListLineObj)this.ArgsList.elementAt(i)).str3);
                image3.flush();
                graphics3.drawImage(image3, 0, n, null);
            }
            else {
                graphics3.drawImage(image2, 0, n, null);
            }
            n += this.LHeight;
        }
        graphics4.dispose();
        image.flush();
        graphics3.dispose();
        System.gc();
        graphics.drawImage(image, 0, 0, null);
    }
    
    public boolean keyDown(final Event event, final int n) {
        switch (n) {
            case 1004: {
                return this.lineUp();
            }
            case 1005: {
                return this.lineDown();
            }
            case 1002: {
                return this.pageUp();
            }
            case 1003: {
                return this.pageDown();
            }
            default: {
                return false;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
        System.gc();
    }
    
    public boolean lineUp() {
        --this.onLine;
        if (this.onLine < 0) {
            this.onLine = 0;
        }
        this.SyncScroll();
        return true;
    }
    
    public boolean lineDown() {
        ++this.onLine;
        if (this.onLine > this.ArgsList.size() - 1) {
            this.onLine = this.ArgsList.size() - 1;
        }
        this.SyncScroll();
        return true;
    }
    
    public boolean pageDown() {
        this.onLine += this.listLong;
        if (this.onLine > this.ArgsList.size() - 1) {
            this.onLine = this.ArgsList.size() - 1;
        }
        this.top += this.listLong;
        this.SyncScroll();
        return true;
    }
    
    public boolean pageUp() {
        this.onLine -= this.listLong;
        if (this.onLine < 0) {
            this.onLine = 0;
        }
        this.top -= this.listLong;
        this.SyncScroll();
        return true;
    }
    
    public void reshape(final int n, final int n2, final int mWidth, final int mHeight) {
        super.reshape(n, n2, mWidth, mHeight);
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        this.FMatrics = this.getFontMetrics(this.MFont);
        this.LHeight = this.FMatrics.getHeight();
        this.SyncScroll();
    }
    
    private boolean paintSelectedLine(final Graphics graphics, final String s, final String s2, final String s3) {
        graphics.setFont(this.MFont);
        this.Y = (int)(this.FMatrics.getHeight() / 1.5) + 2;
        this.height = this.FMatrics.getHeight();
        this.width = 1024;
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 1024, this.LHeight);
        graphics.setColor(Color.white);
        this.drawAt = 0;
        if (this.FMatrics.stringWidth(s) > 190) {
            for (int i = 0; i < s.length(); ++i) {
                if (this.drawAt + this.FMatrics.stringWidth(s.substring(i, i + 1)) >= 90) {
                    break;
                }
                graphics.drawString(s.substring(i, i + 1), this.drawAt, this.Y);
                this.drawAt += this.FMatrics.stringWidth(s.substring(i, i + 1));
            }
        }
        else {
            graphics.drawString(s, this.drawAt, this.Y);
        }
        this.drawAt = 200;
        graphics.drawLine(191, 0, 191, this.height);
        if (this.FMatrics.stringWidth(s2) > 20) {
            for (int j = 0; j < s2.length(); ++j) {
                if (this.drawAt + this.FMatrics.stringWidth(s2.substring(j, j + 1)) >= 20) {
                    break;
                }
                graphics.drawString(s2.substring(j, j + 1), this.drawAt, this.Y);
                this.drawAt += this.FMatrics.stringWidth(s2.substring(j, j + 1));
            }
        }
        else {
            graphics.drawString(s2, this.drawAt, this.Y);
        }
        graphics.drawLine(221, 0, 221, this.height);
        this.drawAt = 230;
        this.appendText(s3);
        this.paintInOneSelectedLine(graphics.create(this.drawAt, 0, this.width - this.drawAt, this.height));
        return true;
    }
    
    private boolean paintLine(final Graphics graphics, final String s, final String s2, final String s3) {
        this.Y = (int)(this.FMatrics.getHeight() / 1.5) + 2;
        this.height = this.FMatrics.getHeight();
        this.width = 1024;
        graphics.setColor(Color.black);
        this.drawAt = 0;
        if (this.FMatrics.stringWidth(s) > 190) {
            for (int i = 0; i < s.length(); ++i) {
                if (this.drawAt + this.FMatrics.stringWidth(s.substring(i, i + 1)) >= 90) {
                    break;
                }
                graphics.drawString(s.substring(i, i + 1), this.drawAt, this.Y);
                this.drawAt += this.FMatrics.stringWidth(s.substring(i, i + 1));
            }
        }
        else {
            graphics.drawString(s, this.drawAt, this.Y);
        }
        this.drawAt = 200;
        graphics.drawLine(191, 0, 191, this.height);
        if (this.FMatrics.stringWidth(s2) > 20) {
            for (int j = 0; j < s2.length(); ++j) {
                if (this.drawAt + this.FMatrics.stringWidth(s2.substring(j, j + 1)) >= 20) {
                    break;
                }
                graphics.drawString(s2.substring(j, j + 1), this.drawAt, this.Y);
                this.drawAt += this.FMatrics.stringWidth(s2.substring(j, j + 1));
            }
        }
        else {
            graphics.drawString(s2, this.drawAt, this.Y);
        }
        graphics.drawLine(221, 0, 221, this.height);
        this.drawAt = 230;
        this.appendText(s3);
        this.paintInOneLine(graphics.create(this.drawAt, 0, this.width - this.drawAt, this.height));
        return true;
    }
    
    public boolean cleanUp() {
        this.ArgsList.removeAllElements();
        this.IsModeWait = true;
        System.gc();
        this.repaint();
        return true;
    }
    
    public boolean modeWait(final boolean isModeWait) {
        if (!(this.IsModeWait = isModeWait)) {
            this.SortVector();
        }
        this.SyncScroll();
        this.repaint();
        return true;
    }
    
    public void appendText(final String s) {
        this.Strings.removeAllElements();
        this.BG.removeAllElements();
        this.FG.removeAllElements();
        this.Bold.removeAllElements();
        this.UnderLine.removeAllElements();
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
                        this.appendToLine(string, black, white, b2, b);
                        string = "";
                    }
                    n = 0;
                    break;
                }
                case '\u0002': {
                    if (n != 0) {
                        this.appendToLine(string, black, white, b2, b);
                        string = "";
                    }
                    b = new Boolean(!b);
                    n = 0;
                    break;
                }
                case '\u0003': {
                    if (n != 0) {
                        this.appendToLine(string, black, white, b2, b);
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
                            if (int1 > 0 && int1 < 16) {
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
                                        if (int2 > 0 && int2 < 16) {
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
                        this.appendToLine(string, black, white, b2, b);
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
                        this.appendToLine(string, black, white, b2, b);
                        string = "";
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    public void appendToLine(final String s, final Color color, final Color color2, final Boolean b, final Boolean b2) {
        this.Strings.addElement(s);
        this.BG.addElement(color2);
        this.FG.addElement(color);
        this.UnderLine.addElement(b);
        this.Bold.addElement(b2);
    }
    
    public boolean paintInOneLine(final Graphics graphics) {
        final int n = this.Strings.size() - 1;
        graphics.getFont();
        graphics.getFontMetrics();
        int n2 = 0;
        for (int i = 0; i <= n; ++i) {
            final String s = this.Strings.elementAt(i);
            final Boolean b = this.UnderLine.elementAt(i);
            FontMetrics fontMetrics;
            if (this.Bold.elementAt(i)) {
                graphics.setFont(new Font("Helvetica", 1, 14));
                fontMetrics = graphics.getFontMetrics();
            }
            else {
                graphics.setFont(new Font("Helvetica", 0, 14));
                fontMetrics = graphics.getFontMetrics();
            }
            final int n3 = (int)(fontMetrics.getHeight() / 1.5) + 2;
            graphics.setColor((Color)this.BG.elementAt(i));
            graphics.fillRect(n2, 0, fontMetrics.stringWidth(s), fontMetrics.getHeight() + 2);
            graphics.setColor((Color)this.FG.elementAt(i));
            graphics.drawString(s, n2, n3);
            if (b) {
                graphics.drawLine(n2, n3, n2 + fontMetrics.stringWidth(s), n3);
            }
            n2 += fontMetrics.stringWidth(s);
        }
        return true;
    }
    
    public boolean paintInOneSelectedLine(final Graphics graphics) {
        final int n = this.Strings.size() - 1;
        graphics.getFont();
        graphics.getFontMetrics();
        int n2 = 0;
        graphics.setColor(Color.white);
        for (int i = 0; i <= n; ++i) {
            final String s = this.Strings.elementAt(i);
            final Boolean b = this.UnderLine.elementAt(i);
            FontMetrics fontMetrics;
            if (this.Bold.elementAt(i)) {
                graphics.setFont(new Font("Helvetica", 1, 14));
                fontMetrics = graphics.getFontMetrics();
            }
            else {
                graphics.setFont(new Font("Helvetica", 0, 14));
                fontMetrics = graphics.getFontMetrics();
            }
            final int n3 = (int)(fontMetrics.getHeight() / 1.5) + 2;
            graphics.drawString(s, n2, n3);
            if (b) {
                graphics.drawLine(n2, n3, n2 + fontMetrics.stringWidth(s), n3);
            }
            n2 += fontMetrics.stringWidth(s);
        }
        return true;
    }
    
    public void SBarMove() {
        if (this.LHeight > 0) {
            final int n = this.mHeight / this.LHeight;
            this.onLine = this.SBar.getValue();
            if (this.onLine >= this.top + n) {
                this.top = this.onLine;
            }
            if (this.onLine < this.top + n) {
                this.top = this.onLine;
            }
        }
        else {
            this.onLine = this.SBar.getValue();
        }
        this.SyncScroll();
    }
    
    public void SyncScroll() {
        if (this.LHeight > 0 || !this.IsModeWait) {
            final int n = this.mHeight / this.LHeight;
            if (this.ArgsList.size() >= n) {
                this.SBar.setValues(this.onLine, n, 0, this.ArgsList.size());
            }
            else {
                this.SBar.setValues(0, 0, 0, 0);
            }
        }
        else {
            this.SBar.setValues(0, 0, 0, 0);
        }
        this.repaint();
        System.gc();
    }
    
    public char cast(final char c) {
        final char lowerCase = Character.toLowerCase(c);
        if (lowerCase >= 'a' && lowerCase <= 'z') {
            return (char)(lowerCase + ' ');
        }
        if (lowerCase >= '0' && lowerCase <= '9') {
            return (char)(lowerCase + '\u00c8');
        }
        return (char)(lowerCase + '\u012c');
    }
    
    public int Compare(final String s, final String s2) {
        final int length = s.length();
        final int length2 = s2.length();
        int n;
        if (length < length2) {
            n = length;
        }
        else {
            n = length2;
        }
        for (int i = 0; i < n; ++i) {
            final char cast = this.cast(s.charAt(i));
            final char cast2 = this.cast(s2.charAt(i));
            if (cast < cast2) {
                return 1;
            }
            if (cast > cast2) {
                return -1;
            }
        }
        if (length < length2) {
            return 1;
        }
        if (length <= length2) {
            return 0;
        }
        return -1;
    }
}
