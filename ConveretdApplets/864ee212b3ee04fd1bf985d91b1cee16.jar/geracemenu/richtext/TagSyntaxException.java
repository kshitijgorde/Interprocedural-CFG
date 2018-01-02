// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

public class TagSyntaxException extends RuntimeException
{
    protected String tagName;
    
    public String getTagName() {
        return this.tagName;
    }
    
    public TagSyntaxException() {
        this("unknown", "Undetermined tag exception");
    }
    
    public TagSyntaxException(final String tagName, final String s) {
        super(s);
        this.tagName = tagName;
    }
}
