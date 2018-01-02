// 
// Decompiled by Procyon v0.5.30
// 

package Go;

public class Move
{
    public int x;
    public int y;
    public int color;
    
    public Move() {
    }
    
    public Move(final int color) {
        this.color = color;
    }
    
    public Move(final int X, final int Y, final int Color) {
        this.x = X;
        this.y = Y;
        this.color = Color;
    }
    
    public Move(final GobanLocation location) {
        this.x = location.x;
        this.y = location.y;
        this.color = location.state;
    }
    
    public Move(final GobanLocation location, final int newColor) {
        this.x = location.x;
        this.y = location.y;
        this.color = newColor;
    }
    
    public boolean equals(final Object anObject) {
        return anObject instanceof Move && ((Move)anObject).x == this.x && ((Move)anObject).y == this.y && ((Move)anObject).color == this.color;
    }
    
    public boolean isPass() {
        return this.x == -100 || this.y == -100;
    }
    
    public String toString() {
        final String answer = String.valueOf(String.valueOf(String.valueOf("").concat(String.valueOf(this.x))).concat(String.valueOf(this.y))).concat(String.valueOf(this.color));
        return answer;
    }
}
