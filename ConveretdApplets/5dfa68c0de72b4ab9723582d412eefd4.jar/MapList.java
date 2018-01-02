// 
// Decompiled by Procyon v0.5.30
// 

class MapList
{
    Object[] tokens;
    int offset;
    
    MapList(final Object[] tokens) {
        this.offset = 0;
        this.tokens = tokens;
    }
    
    Object next() {
        return this.tokens[this.offset++];
    }
    
    Object peek() {
        return this.tokens[this.offset];
    }
    
    boolean eof() {
        return this.offset == this.tokens.length;
    }
}
