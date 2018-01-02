// 
// Decompiled by Procyon v0.5.30
// 

package abc.notation;

public class Words implements MusicElement
{
    private String content;
    
    public Words(final String theContent) {
        this.content = null;
        this.content = theContent;
    }
    
    public String getContent() {
        return this.content;
    }
}
