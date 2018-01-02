// 
// Decompiled by Procyon v0.5.30
// 

package scanner;

public class CharStreamPosition implements Cloneable
{
    private int m_column;
    private int m_line;
    private int m_charactersOffset;
    
    public CharStreamPosition() {
        this.m_column = 1;
        this.m_line = 1;
        this.m_charactersOffset = 0;
    }
    
    public CharStreamPosition(final CharStreamPosition pos) {
        this.m_column = 1;
        this.m_line = 1;
        this.m_charactersOffset = 0;
        this.setPosition(pos);
    }
    
    public CharStreamPosition(final int column, final int line, final int charactersOffset) {
        this.m_column = 1;
        this.m_line = 1;
        this.m_charactersOffset = 0;
        this.m_column = column;
        this.m_line = line;
        this.m_charactersOffset = charactersOffset;
    }
    
    public void setPosition(final CharStreamPosition pos) {
        this.m_column = pos.m_column;
        this.m_line = pos.m_line;
        this.m_charactersOffset = pos.m_charactersOffset;
    }
    
    public void setPosition(final int column, final int line, final int offset) {
        this.m_column = column;
        this.m_line = line;
        this.m_charactersOffset = offset;
    }
    
    public void setColumn(final int column) {
        this.m_column = column;
    }
    
    public void setLine(final int line) {
        this.m_line = line;
    }
    
    public void setCharactersOffset(final int charactersOffset) {
        this.m_charactersOffset = charactersOffset;
    }
    
    public int getColumn() {
        return this.m_column;
    }
    
    public int getLine() {
        return this.m_line;
    }
    
    public int getCharactersOffset() {
        return this.m_charactersOffset;
    }
    
    public Object clone() {
        return new CharStreamPosition(this.m_column, this.m_line, this.m_charactersOffset);
    }
    
    public String toString() {
        return "@(L:" + this.m_line + ", C:" + this.m_column + "; " + this.m_charactersOffset + ")";
    }
}
