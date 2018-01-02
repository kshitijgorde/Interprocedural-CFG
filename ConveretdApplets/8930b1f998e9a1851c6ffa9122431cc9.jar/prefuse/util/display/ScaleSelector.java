// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.display;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import javax.swing.event.ChangeEvent;
import java.awt.Component;
import javax.swing.Box;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.JComponent;

public class ScaleSelector extends JComponent implements ChangeListener
{
    private static final int MAX_SIZE = 135;
    private ImagePanel preview;
    private JLabel value;
    private JLabel size;
    private JSlider slider;
    private Image image;
    private int width;
    private int height;
    
    public ScaleSelector() {
        this.slider = new JSlider(1, 10, 1);
        this.value = new JLabel("x1");
        this.size = new JLabel("   ");
        this.preview = new ImagePanel();
        this.value.setPreferredSize(new Dimension(25, 10));
        this.size.setHorizontalAlignment(0);
        this.slider.setMajorTickSpacing(1);
        this.slider.setSnapToTicks(true);
        this.slider.addChangeListener(this);
        this.setLayout(new BorderLayout());
        final Box box = new Box(0);
        box.add(Box.createHorizontalStrut(5));
        box.add(Box.createHorizontalGlue());
        box.add(this.preview);
        box.add(Box.createHorizontalGlue());
        box.add(Box.createHorizontalStrut(5));
        this.add(box, "Center");
        final Box box2 = new Box(0);
        box2.add(this.slider);
        box2.add(Box.createHorizontalStrut(5));
        box2.add(this.value);
        final Box box3 = new Box(0);
        box3.add(Box.createHorizontalStrut(5));
        box3.add(Box.createHorizontalGlue());
        box3.add(this.size);
        box3.add(Box.createHorizontalGlue());
        box3.add(Box.createHorizontalStrut(5));
        final Box box4 = new Box(1);
        box4.add(box2);
        box4.add(box3);
        this.add(box4, "South");
    }
    
    public void setImage(final Image image) {
        this.image = this.getScaledImage(image);
        this.stateChanged(null);
    }
    
    private Image getScaledImage(final Image image) {
        final int width = image.getWidth(null);
        this.width = width;
        final int n = width;
        final int height = image.getHeight(null);
        this.height = height;
        final int n2 = height;
        final double n3 = n / n2;
        int n4 = 135;
        int n5 = 135;
        if (n > n2) {
            n5 = (int)Math.round(n4 / n3);
        }
        else {
            n4 = (int)Math.round(n5 * n3);
        }
        return image.getScaledInstance(n4, n5, 4);
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final int value = this.slider.getValue();
        this.value.setText("x" + String.valueOf(value));
        this.size.setText("Image Size: " + this.width * value + " x " + this.height * value + " pixels");
        this.preview.repaint();
    }
    
    public double getScale() {
        return this.slider.getValue();
    }
    
    public class ImagePanel extends JComponent
    {
        Dimension d;
        
        public ImagePanel() {
            this.setPreferredSize(this.d = new Dimension(135, 135));
            this.setMinimumSize(this.d);
            this.setMaximumSize(this.d);
        }
        
        public void paintComponent(final Graphics graphics) {
            final double n = 0.4 + 0.06 * ScaleSelector.this.getScale();
            final int n2 = (int)Math.round(n * ScaleSelector.this.image.getWidth(null));
            final int n3 = (int)Math.round(n * ScaleSelector.this.image.getHeight(null));
            graphics.drawImage((n == 1.0) ? ScaleSelector.this.image : ScaleSelector.this.image.getScaledInstance(n2, n3, 1), (135 - n2) / 2, (135 - n3) / 2, null);
        }
    }
}
