import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ColorChooser extends Panel implements MouseListener
{
    private Canvas[] box;
    private Color[] color;
    private Slate slate;
    private DrawingPanel draw;
    
    public ColorChooser(final DrawingPanel draw) {
        this.box = new Canvas[12];
        this.color = new Color[] { Color.gray, Color.darkGray, Color.black, Color.red, Color.white, Color.pink, Color.orange, Color.yellow, Color.green, Color.magenta, Color.cyan, Color.blue };
        this.setBackground(Color.white);
        this.draw = draw;
        this.setLayout(new GridLayout(4, 3, 2, 2));
        for (int i = 0; i < this.box.length; ++i) {
            (this.box[i] = new Canvas()).setBackground(this.color[i]);
            this.box[i].addMouseListener(this);
            this.box[i].setSize(30, 30);
            this.add(this.box[i]);
        }
        this.addMouseListener(this);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final Object source = mouseEvent.getSource();
        if (source instanceof Canvas) {
            this.draw.setColor(((Canvas)source).getBackground());
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
}
