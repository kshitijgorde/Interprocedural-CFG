// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

import geracemenu.VImage;
import java.util.Enumeration;

public class TextEnumerator implements Enumeration
{
    private transient Text text;
    private transient int stylePos;
    private transient int styleLength;
    
    public boolean hasMoreTokens() {
        return this.stylePos < this.text.length();
    }
    
    public RichTextToken nextToken() {
        this.styleLength = this.text.getRunLengthAt(this.stylePos);
        final char[] chars = this.text.getChars(this.stylePos, this.stylePos + this.styleLength);
        RichTextToken richTextToken;
        if (chars[0] == '\ufffe') {
            final TextAttachment attachment = this.text.getAttachmentAt(this.stylePos);
            richTextToken = new ImageToken((VImage)attachment.getVisualizable(), this.text.getTextStyleAt(this.stylePos), attachment.isAnimated());
        }
        else if (chars[0] == '\n') {
            richTextToken = new NewLineToken(this.text.getTextStyleAt(this.stylePos));
        }
        else {
            richTextToken = new StyledTextToken(new String(chars), this.text.getTextStyleAt(this.stylePos));
        }
        this.stylePos += this.styleLength;
        return richTextToken;
    }
    
    public boolean hasMoreElements() {
        return this.hasMoreTokens();
    }
    
    public Object nextElement() {
        return this.nextToken();
    }
    
    public TextEnumerator(final Text text) {
        this.text = text;
        final boolean b = false;
        this.styleLength = (b ? 1 : 0);
        this.stylePos = (b ? 1 : 0);
    }
}
