import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class ListWatcherScrollBar
{
    public static final int SCROLLBAR_WIDTH = 20;
    StretchyBox frameBox;
    StretchyBox nubBox;
    
    ListWatcherScrollBar() {
        (this.frameBox = new StretchyBox()).setFrameImage(Skin.vScrollFrame);
        (this.nubBox = new StretchyBox()).setFrameImage(Skin.vScrollSlider);
    }
    
    public void paint(final Graphics graphics, final int n, final int y, final int n2, final int n3) {
        this.frameBox.x = n - 20;
        this.frameBox.y = y;
        this.frameBox.w = 16;
        this.frameBox.h = n2 + 5;
        this.frameBox.paint(graphics);
        this.nubBox.x = n - 20 + 2;
        this.nubBox.y = n3 + 2;
        this.nubBox.w = 12;
        if (this.nubBox.h < 23) {
            this.nubBox.h = 23;
        }
        this.nubBox.paint(graphics);
    }
}
