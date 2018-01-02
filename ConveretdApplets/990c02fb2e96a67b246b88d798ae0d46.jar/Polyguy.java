import com.pdx4d.polys.Shape;
import java.awt.Color;
import java.awt.Graphics;
import com.pdx4d.polys.IMGwriter;
import com.pdx4d.polys.Qaxes;
import com.pdx4d.polys.XYZaxes;
import com.pdx4d.polys.Icosahedron;

// 
// Decompiled by Procyon v0.5.30
// 

public class Polyguy extends Artist
{
    Icosahedron oIcosa;
    XYZaxes xyz;
    Qaxes q;
    IMGwriter img;
    
    Polyguy() {
        this.oIcosa = new Icosahedron();
        ((Shape)(this.xyz = new XYZaxes())).setcolor("Green");
        ((Shape)(this.q = new Qaxes())).setcolor("Red");
        this.img = new IMGwriter();
    }
    
    public void compose(final Graphics graphics) {
        graphics.setColor(Color.white);
        ((Shape)this.oIcosa).rotate("X", 1.0);
        ((Shape)this.oIcosa).rotate("Y", 1.0);
        ((Shape)this.oIcosa).rotate("Z", 1.0);
        this.img.writeEdges((Shape)this.oIcosa, graphics, super.maxheight, super.maxwidth);
    }
}
