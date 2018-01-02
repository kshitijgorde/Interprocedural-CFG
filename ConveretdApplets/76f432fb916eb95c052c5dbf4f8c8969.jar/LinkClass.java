// 
// Decompiled by Procyon v0.5.30
// 

class LinkClass
{
    private String LinkName;
    private String Link;
    private int pos;
    private int width;
    private int otherWidth;
    
    public LinkClass() {
        this.LinkName = null;
        this.Link = null;
        this.pos = 0;
        this.width = 0;
        this.otherWidth = 0;
    }
    
    public void setLinkName(final String linkName) {
        this.LinkName = linkName;
    }
    
    public String getLinkName() {
        return this.LinkName;
    }
    
    public void setOtherWidth(final int otherWidth) {
        this.otherWidth = otherWidth;
    }
    
    public int getOtherWidth() {
        return this.otherWidth;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public void setLink(final String link) {
        this.Link = link;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public String getLink() {
        return this.Link;
    }
    
    public void setPos(final int pos) {
        this.pos = pos;
    }
    
    public int getPos() {
        return this.pos;
    }
}
