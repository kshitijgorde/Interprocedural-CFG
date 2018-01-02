import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Player
{
    public final int pos_x;
    public static int pos_y;
    public final int size_x;
    public final int size_y;
    public static int vy;
    private Color paddelcolor;
    
    public Player(final int pos_x, final int pos_y) {
        this.pos_x = pos_x;
        Player.pos_y = pos_y;
        this.size_x = 10;
        this.size_y = 50;
        this.paddelcolor = Color.red;
        Player.vy = 0;
    }
    
    public void PaddelMove(final int n) {
        final int pos_y = n - this.size_y / 2;
        if (pos_y < Player.pos_y) {
            Player.vy = (Player.pos_y - pos_y) / 2;
        }
        else {
            Player.vy = (pos_y - Player.pos_y) / 2;
        }
        Player.pos_y = pos_y;
    }
    
    public void DrawPaddel(final Graphics graphics) {
        graphics.setColor(this.paddelcolor);
        graphics.fillRect(this.pos_x, Player.pos_y, this.size_x, this.size_y);
    }
}
