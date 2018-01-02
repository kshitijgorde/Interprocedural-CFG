// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

import geracemenu.Visualizable;
import java.util.Enumeration;
import java.util.Hashtable;
import geracemenu.util.RunArray;

public class Text
{
    public static final String LF = "\n";
    public static final char ATTACHMENT_CHAR = '\ufffe';
    private static /* synthetic */ Class class$Lgeracemenu$richtext$TextStyle;
    String string;
    RunArray runs;
    Hashtable attachments;
    
    public void finalize() {
        this.string = null;
        this.runs.removeAll();
        this.runs = null;
    }
    
    protected final char[] getChars() {
        if (this.string == null || this.string.length() == 0) {
            return null;
        }
        final int length;
        final char[] array = new char[length = this.string.length()];
        this.string.getChars(0, length, array, 0);
        return array;
    }
    
    public final Enumeration getTextStyles() {
        return this.runs.elements();
    }
    
    public final Enumeration getTextStyles(final int n, final int n2) {
        return this.runs.elements(n, n2);
    }
    
    public final TextStyle getTextStyleAt(final int n) {
        return (TextStyle)this.runs.get(n);
    }
    
    public final int getTextStyleCount() {
        return this.runs.getValueCount();
    }
    
    public final TextStyle[] returnStylesAsArray() {
        return (TextStyle[])this.runs.getValues();
    }
    
    public final TextStyle[] returnStylesAsArray(final int n, final int n2) {
        return (TextStyle[])this.runs.getValues(n, n2);
    }
    
    public final Hashtable getAttachments() {
        return this.attachments;
    }
    
    public final int getAttachmentCount() {
        return this.attachments.size();
    }
    
    public final TextAttachment getAttachmentAt(final int n) {
        return (this.attachments == null) ? null : this.attachments.get(new Integer(n));
    }
    
    public final int indexOf(final int n) {
        return this.string.indexOf(n);
    }
    
    public final int indexOf(final int n, final int n2) {
        return this.string.indexOf(n, n2);
    }
    
    public final char charAt(final int n) {
        return this.string.charAt(n);
    }
    
    public final void setCharAt(final int n, final char c) {
        final StringBuffer sb = new StringBuffer(this.string);
        sb.setCharAt(n, c);
        this.string = sb.toString();
    }
    
    public void getChars(final int n, final int n2, final char[] array, final int n3) {
        new StringBuffer(this.string).getChars(n, n2, array, n3);
    }
    
    public char[] getChars(final int n, final int n2) {
        return this.getChars(n, n2, false);
    }
    
    public char[] getChars(final int n, final int n2, final boolean b) {
        final int n3 = (n2 > 0 && b && this.charAt(n2 - 1) == '\n') ? (n2 - 1) : n2;
        final char[] array = new char[n3 - n];
        this.string.getChars(n, n3, array, 0);
        return array;
    }
    
    public final boolean isEmpty() {
        return this.string.length() == 0;
    }
    
    public final int length() {
        return this.string.length();
    }
    
    public void removeAll() {
        this.string = "";
        this.runs = new RunArray(this.length(), new TextStyle("Arial", 0, 14), (Text.class$Lgeracemenu$richtext$TextStyle != null) ? Text.class$Lgeracemenu$richtext$TextStyle : (Text.class$Lgeracemenu$richtext$TextStyle = class$("geracemenu.richtext.TextStyle")));
    }
    
    public Text append(final TextAttachment textAttachment) {
        if (this.attachments == null) {
            this.attachments = new Hashtable();
        }
        this.attachments.put(new Integer(this.length()), textAttachment);
        this.append('\ufffe');
        return this;
    }
    
    public Text append(final char c) {
        return this.append(new String(new char[] { c }), TextStyle.DEFAULT_STYLE);
    }
    
    public Text append(final String s) {
        return this.append(s, TextStyle.DEFAULT_STYLE);
    }
    
    public Text append(final String s, final TextStyle textStyle) {
        this.string += s;
        this.runs.append(s.length(), textStyle);
        return this;
    }
    
    public Text append(final Text text) {
        this.string += new String(text.getChars());
        this.runs.append(text.runs);
        if (text.attachments != null) {
            if (this.attachments == null) {
                this.attachments = new Hashtable();
            }
            final int n = this.length() - text.length();
            final int size = text.attachments.size();
            final Enumeration<Integer> keys = text.attachments.keys();
            final Enumeration<TextAttachment> elements = text.attachments.elements();
            for (int i = 0; i < size; ++i) {
                this.attachments.put(new Integer(n + keys.nextElement()), elements.nextElement());
            }
        }
        return this;
    }
    
    public void stripParagraph() {
        final int index;
        if ((index = this.string.indexOf(10)) < 0) {
            return;
        }
        this.string = this.string.substring(0, index);
        this.runs.remove(0, index);
    }
    
    public int getRunLengthAt(final int n) {
        return this.runs.getRunLengthAt(n);
    }
    
    public int getRunOffsetAt(final int n) {
        return this.runs.getRunOffsetAt(n);
    }
    
    public String toString() {
        return this.string;
    }
    
    public String dump() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.runs.toString());
        sb.append("[TEXT]:\n" + this.string);
        return sb.toString();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public Text(final String s) {
        this(s, TextStyle.DEFAULT_STYLE);
    }
    
    public Text(final String s, final TextStyle textStyle) {
        if (s == null || textStyle == null) {
            throw new NullPointerException("Text(): null s or tStyle");
        }
        this.string = new String(s);
        this.runs = new RunArray(s.length(), textStyle, (Text.class$Lgeracemenu$richtext$TextStyle != null) ? Text.class$Lgeracemenu$richtext$TextStyle : (Text.class$Lgeracemenu$richtext$TextStyle = class$("geracemenu.richtext.TextStyle")));
        this.attachments = null;
    }
    
    public Text(final Visualizable visualizable) {
        this(new TextAttachment(visualizable));
    }
    
    public Text(final TextAttachment textAttachment) {
        this(textAttachment, TextStyle.DEFAULT_STYLE);
    }
    
    public Text(final TextAttachment textAttachment, final TextStyle textStyle) {
        this.string = new String(new char[] { '\ufffe' });
        this.runs = new RunArray(1, textStyle, (Text.class$Lgeracemenu$richtext$TextStyle != null) ? Text.class$Lgeracemenu$richtext$TextStyle : (Text.class$Lgeracemenu$richtext$TextStyle = class$("geracemenu.richtext.TextStyle")));
        (this.attachments = new Hashtable()).put(new Integer(0), textAttachment);
    }
}
