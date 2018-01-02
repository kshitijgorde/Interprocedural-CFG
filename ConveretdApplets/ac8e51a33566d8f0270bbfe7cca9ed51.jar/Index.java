// 
// Decompiled by Procyon v0.5.30
// 

public class Index
{
    public byte ch;
    public int width;
    public boolean[][] letter;
    
    Index(final byte ch, final int width, final int n) {
        this.letter = new boolean[width][n];
        this.width = width;
        this.ch = ch;
    }
}
