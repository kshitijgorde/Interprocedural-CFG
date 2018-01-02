// 
// Decompiled by Procyon v0.5.30
// 

package teletext;

public class TeletextPageRenderCell
{
    public byte foreground;
    public byte background;
    public boolean doubleHeight;
    public char character;
    public char visibleCharacter;
    public boolean graphics;
    public boolean flash;
    public boolean separateGraphics;
    public boolean holdGraphics;
    public boolean conceal;
    
    public void reset() {
        this.foreground = 7;
        this.background = 0;
        this.doubleHeight = false;
        this.character = ' ';
        this.visibleCharacter = ' ';
    }
    
    public void copy(final TeletextPageRenderCell teletextPageRenderCell) {
        this.foreground = teletextPageRenderCell.foreground;
        this.background = teletextPageRenderCell.background;
        this.doubleHeight = teletextPageRenderCell.doubleHeight;
        this.character = teletextPageRenderCell.character;
        this.visibleCharacter = teletextPageRenderCell.visibleCharacter;
    }
    
    public TeletextPageRenderCell() {
        this.reset();
    }
}
