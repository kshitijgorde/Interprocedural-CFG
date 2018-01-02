// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser;

class AbcTextField
{
    public static final byte AREA = 1;
    public static final byte BOOK = 2;
    public static final byte COMPOSER = 3;
    public static final byte DISCOGRAPHY = 4;
    public static final byte ELEMSKIP = 5;
    public static final byte GROUP = 6;
    public static final byte HISTORY = 7;
    public static final byte INFORMATION = 8;
    public static final byte NOTES = 9;
    public static final byte ORIGIN = 10;
    public static final byte RHYTHM = 11;
    public static final byte SOURCE = 12;
    public static final byte TITLE = 15;
    public static final byte TRANSCRNOTES = 13;
    public static final byte WORDS = 14;
    private byte m_type;
    private String m_text;
    private String m_comment;
    
    public AbcTextField(final byte type, final String text) {
        this.m_type = 0;
        this.m_text = null;
        this.m_comment = null;
        this.m_type = type;
        this.m_text = text;
    }
    
    public AbcTextField(final byte type, final String text, final String comment) {
        this.m_type = 0;
        this.m_text = null;
        this.m_comment = null;
        this.m_type = type;
        this.m_text = text;
        this.m_comment = comment;
    }
    
    public String getText() {
        return this.m_text;
    }
    
    public byte getType() {
        return this.m_type;
    }
    
    public String getComment() {
        return this.m_comment;
    }
    
    public void display() {
    }
}
