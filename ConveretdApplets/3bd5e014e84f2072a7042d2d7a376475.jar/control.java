import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class control extends Panel
{
    imgslide a;
    controlbutton reset;
    controlbutton shuffle;
    controlbutton stand;
    controlbutton vb;
    controlbutton jbf;
    
    public control(final imgslide a) {
        this.a = a;
        this.setLayout(new GridLayout(1, 5));
        this.reset = new controlbutton(a.game, "Reset");
        this.shuffle = new controlbutton(a.game, "Shuffle");
        this.stand = new controlbutton(a.game, "Score");
        this.vb = new controlbutton(a.game, "Example");
        this.jbf = new controlbutton(a.game, "Info");
        this.add(this.reset);
        this.add(this.shuffle);
        this.add(this.stand);
        this.add(this.vb);
        this.add(this.jbf);
    }
}
