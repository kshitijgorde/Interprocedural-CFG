import java.util.StringTokenizer;
import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class TextBlock
{
    private String title;
    private Vector text;
    private int rank;
    private int page;
    private int xco;
    private int yco;
    private NewsPro npro;
    int titlexco;
    boolean newLine;
    Group g;
    private int offset;
    String linktext;
    Vector linkPage;
    Vector linkVector;
    Vector linkTarget;
    int ct;
    int imgct;
    Word tmp;
    boolean containslink;
    private int fontHeight;
    private int maxDescent;
    
    public TextBlock(final String title, String s, final NewsPro npro, final Group g) {
        this.newLine = false;
        this.linkPage = new Vector();
        this.linkVector = new Vector();
        this.linkTarget = new Vector();
        this.ct = 0;
        this.imgct = 0;
        this.g = g;
        if (g.isNew()) {
            g.setLastOffset(npro.skin.getOffset("textOffset"));
        }
        this.offset = g.getLastOffset();
        this.title = title;
        this.npro = npro;
        g.addRank(1);
        this.rank = g.getRank();
        this.page = g.getPages();
        this.yco = 20;
        this.titlexco = 20;
        this.fontHeight = npro.getFontHeight("textfont") + 2;
        this.maxDescent = npro.getFontDescent("textfont");
        while (s.indexOf("<link") != -1) {
            s = this.extractPart("<link=", "</link>", s);
            this.containslink = true;
        }
        while (s.indexOf("<image") != -1) {
            s = this.extractImages(s);
        }
        this.text = this.generateWords(s);
    }
    
    public boolean containsLink() {
        return this.containslink;
    }
    
    public void drawBlock(final Graphics graphics, final Word word) {
        graphics.setFont(this.npro.skin.getFont("titlefont"));
        graphics.setColor(this.npro.skin.getColor("titlecolor"));
        graphics.drawString(this.getTitle(), this.titlexco, this.offset + 5);
        final Enumeration<Word> elements = this.text.elements();
        while (elements.hasMoreElements()) {
            boolean b = false;
            final Word word2 = elements.nextElement();
            if (word2.isImage()) {
                int n;
                if (word2.getW() - 5 > 8 && word2.getW() - 5 < 14) {
                    n = 10;
                }
                else {
                    n = word2.getW() - 5;
                }
                graphics.drawImage(this.npro.getIcon(word2.getText()), word2.getXco() + 20, word2.getYco() + this.offset - 10, n, n, this.npro);
            }
            else if (word2.isLink()) {
                graphics.setColor(this.npro.skin.getColor("ilinkcolor"));
                if (word != null && word2.getLink().equals(word.getLink())) {
                    graphics.setColor(this.npro.skin.getColor("alinkcolor"));
                    b = true;
                }
            }
            else {
                graphics.setColor(this.npro.skin.getColor("textcolor"));
            }
            if (!word2.isImage()) {
                graphics.setFont(this.npro.skin.getFont("textfont"));
                graphics.drawString(word2.getText(), word2.getXco() + 20, word2.getYco() + this.offset);
            }
            if (b) {
                graphics.drawLine(word2.getXco() + 20, word2.getYco() + this.offset + this.maxDescent, word2.getXco() + 20 + word2.getW(), word2.getYco() + this.offset + this.maxDescent);
            }
        }
    }
    
    private String extractImages(String string) {
        final int index = this.getIndex("<image=\"", string);
        final int index2 = this.getIndex("\">", string);
        string = String.valueOf(string.substring(0, index)) + (String.valueOf("") + " $$" + string.substring(index + 8, index2)) + " " + string.substring(index2 + 2, string.length());
        return string;
    }
    
    private String extractPart(final String s, final String s2, String string) {
        final int index = this.getIndex(s, string);
        final int index2 = this.getIndex(s2, string);
        final int length = s.length();
        final int length2 = s2.length();
        final int index3 = string.toUpperCase().indexOf("\">", index);
        final StringTokenizer stringTokenizer = new StringTokenizer(string.substring(index + length + 1, index3));
        String s3 = stringTokenizer.nextToken();
        String substring = "_self";
        if (stringTokenizer.hasMoreTokens()) {
            s3 = s3.substring(0, s3.length() - 1);
            final String nextToken = stringTokenizer.nextToken();
            substring = nextToken.substring(8, nextToken.length());
        }
        this.linktext = string.substring(index3 + 2, index2);
        final String substring2 = string.substring(0, index);
        final String substring3 = string.substring(index2 + length2, string.length());
        String string2 = "";
        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.linktext);
        while (stringTokenizer2.hasMoreTokens()) {
            ++this.ct;
            this.linkPage.addElement(s3);
            this.linkTarget.addElement(substring);
            this.linkVector.addElement(stringTokenizer2.nextToken());
            string2 = String.valueOf(string2) + " %%" + this.ct;
        }
        string = String.valueOf(substring2) + string2 + " " + substring3;
        return string;
    }
    
    private Vector generateWords(final String s) {
        final Vector<Word> vector = new Vector<Word>();
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        while (stringTokenizer.hasMoreTokens()) {
            boolean b = false;
            boolean b2 = false;
            final String nextToken = stringTokenizer.nextToken();
            String s2 = "";
            String s3 = nextToken.trim();
            if (this.hasBreak(s3)) {
                s3 = this.removeTags(s3, "<BR>");
                this.newLine = true;
            }
            if (s3.length() > 0) {
                if (this.isCode(s3)) {
                    b = true;
                    s2 = s3;
                    s3 = this.replaceWithWord(s3);
                }
                int xco = this.npro.getFontWidth(s3) + 5;
                if (this.isImage(s3)) {
                    b2 = true;
                    s2 = s3;
                    xco = this.npro.getFontHeight("textfont") + 5;
                }
                if (this.xco + xco < this.npro.getMaxWidth() && this.yco + this.offset <= this.npro.getMaxHeight() && !this.newLine) {
                    this.xco += xco;
                }
                else {
                    this.newLine = false;
                    this.xco = xco;
                    this.yco += this.fontHeight;
                    if (this.yco + this.offset > this.npro.getMaxHeight()) {
                        this.g.addPage();
                        this.page = this.g.getPages();
                        this.g.addRank(0);
                        this.rank = this.g.getRank();
                        this.offset = this.npro.skin.getOffset("textOffset");
                    }
                }
                final Word word = new Word(s3.trim(), this.xco - xco, this.yco, xco);
                if (b) {
                    word.setLink(this.linkPage.elementAt(this.getLinkRank(s2) - 1));
                    word.setTarget(this.linkTarget.elementAt(this.getLinkRank(s2) - 1));
                }
                if (b2) {
                    word.setImage(true);
                }
                vector.addElement(word);
            }
        }
        this.g.setLastOffset(this.offset + this.yco + this.fontHeight + 6);
        return vector;
    }
    
    public Word getActionItem() {
        return this.tmp;
    }
    
    private int getIndex(final String s, final String s2) {
        return s2.toUpperCase().indexOf(s.toUpperCase());
    }
    
    private int getLinkRank(final String s) {
        return Integer.parseInt(s.substring(2, s.length()));
    }
    
    public int getPage() {
        return this.page;
    }
    
    public int getRank() {
        return this.rank;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    private boolean hasBreak(final String s) {
        return s.toUpperCase().indexOf("<BR>") >= 0;
    }
    
    private boolean isCode(final String s) {
        return s.indexOf("%%") != -1;
    }
    
    private boolean isImage(final String s) {
        return s.indexOf("$$") != -1;
    }
    
    public boolean isOnLink(final int n, final int n2) {
        final Enumeration<Word> elements = this.text.elements();
        while (elements.hasMoreElements()) {
            final Word tmp = elements.nextElement();
            if (tmp.isLink() && tmp.getXco() + 15 < n && tmp.getXco() + tmp.getW() + 20 > n && tmp.getYco() + this.offset - this.fontHeight / 2 < n2 && tmp.getYco() + this.offset + this.fontHeight / 2 > n2) {
                tmp.setActiveLink(true);
                this.tmp = tmp;
                return true;
            }
        }
        return false;
    }
    
    private String removeTags(String string, final String s) {
        final int index = this.getIndex(s, string);
        string = String.valueOf(string.substring(0, index)) + string.substring(index + s.length(), string.length());
        return string;
    }
    
    private String replaceWithWord(final String s) {
        return this.linkVector.elementAt(this.getLinkRank(s) - 1);
    }
}
