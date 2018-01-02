// 
// Decompiled by Procyon v0.5.30
// 

public class ParseNode
{
    private String _text;
    private int _index;
    
    public ParseNode(final String text) {
        this._index = 0;
        this._text = text;
    }
    
    public int incr() {
        return ++this._index;
    }
    
    public int decr() {
        return --this._index;
    }
    
    public String getText() {
        return this._text;
    }
    
    public int getIndex() {
        return this._index;
    }
    
    public void setIndex(final int index) {
        this._index = index;
    }
    
    public String toString() {
        return this._text;
    }
    
    public int length() {
        return this._text.length();
    }
    
    public char charAt() {
        return this._text.charAt(this._index);
    }
    
    public boolean notDone() {
        return this._index < this._text.length();
    }
    
    public boolean isDone() {
        return this._index >= this._text.length();
    }
}
