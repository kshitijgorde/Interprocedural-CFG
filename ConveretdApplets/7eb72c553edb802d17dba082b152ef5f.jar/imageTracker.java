import java.awt.Color;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class imageTracker
{
    Image image;
    int truePosition;
    int xPos;
    int yPos;
    int currXpos;
    int currYpos;
    Color color;
    
    imageTracker(final Image img, final int truePos, final int xPos, final int yPos) {
        this.image = img;
        this.truePosition = truePos;
        this.xPos = xPos;
        this.yPos = yPos;
        this.currXpos = this.xPos;
        this.currYpos = this.yPos;
        this.color = Color.white;
    }
    
    public void reset() {
        this.currXpos = this.xPos;
        this.currYpos = this.yPos;
        this.color = Color.white;
    }
}
