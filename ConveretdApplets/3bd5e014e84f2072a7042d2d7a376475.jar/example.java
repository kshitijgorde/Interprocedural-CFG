import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class example extends Frame
{
    public controlbutton close;
    display dis;
    
    public example(final imgslide imgslide, final picture picture) {
        super("Example " + picture.name);
        this.setLayout(new BorderLayout());
        this.add("North", this.close = new controlbutton(picture.a.game, "Close"));
        this.add("Center", this.dis = new display(imgslide, picture));
        final Dimension size2;
        final Dimension size = size2 = this.dis.getSize();
        size2.height += (imgslide.isapplet ? 60 : 40);
        this.setSize(size);
        this.show();
        this.addWindowListener(imgslide);
    }
}
