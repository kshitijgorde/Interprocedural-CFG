import java.util.Enumeration;
import java.awt.Graphics;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class Group
{
    protected Vector blockVector;
    private String title;
    private boolean isnew;
    private int nop;
    private int rank;
    private int lastOffset;
    private int fontWidth;
    private int groupXco;
    
    public Group(final String title, final NewsPro newsPro) {
        this.isnew = true;
        this.nop = 1;
        this.title = title;
        this.fontWidth = newsPro.getGroupWidth(String.valueOf(title) + " (a)");
        this.groupXco = newsPro.getAppletWidth() / 2 - this.fontWidth / 2;
        this.lastOffset = newsPro.skin.getOffset("textOffset");
    }
    
    public void addBlocks(final Vector blockVector) {
        this.blockVector = blockVector;
    }
    
    public void addPage() {
        ++this.nop;
    }
    
    public void addRank(final int n) {
        if (n == 0) {
            this.rank = 1;
        }
        else {
            ++this.rank;
        }
    }
    
    public void drawGroup(final int n, final int n2, final Graphics graphics, final NewsPro newsPro, final Word word) {
        final Enumeration<TextBlock> elements = this.blockVector.elements();
        while (elements.hasMoreElements()) {
            final TextBlock textBlock = elements.nextElement();
            if (textBlock.getPage() == n2 + 1 && textBlock.getRank() <= n) {
                textBlock.drawBlock(graphics, word);
            }
        }
        graphics.setFont(newsPro.skin.getFont("groupfont"));
        graphics.setColor(newsPro.skin.getColor("groupcolor"));
        graphics.drawString(String.valueOf(this.getTitle()) + " (" + (n2 + 1) + ")", this.groupXco, newsPro.skin.getOffset("groupOffset"));
    }
    
    public int getLastOffset() {
        return this.lastOffset;
    }
    
    public int getPages() {
        return this.nop;
    }
    
    public int getRank() {
        return this.rank;
    }
    
    public int getSize() {
        return this.blockVector.size();
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public boolean hasMoreOnPage(final int n, final int n2) {
        return n <= this.getSize() && this.blockVector.elementAt(n - 1).getRank() == n2;
    }
    
    public boolean hasMorePages(final int n) {
        return n < this.nop;
    }
    
    public boolean isNew() {
        return this.isnew;
    }
    
    public void setLastOffset(final int lastOffset) {
        this.lastOffset = lastOffset;
    }
    
    public void setNew(final boolean isnew) {
        this.isnew = isnew;
    }
}
