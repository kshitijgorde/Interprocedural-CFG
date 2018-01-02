import java.awt.event.TextEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Color;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.Canvas;
import java.awt.event.TextListener;
import java.awt.event.AdjustmentListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class ColorDialog extends MessageBox implements AdjustmentListener, TextListener
{
    Formula I;
    Canvas add;
    Scrollbar addAdjustmentListener;
    Label addTextListener;
    Scrollbar append;
    Label black;
    Scrollbar colorToString;
    Label decode;
    TextField doLayout;
    public Color color;
    boolean getBlue;
    
    public ColorDialog(final Frame frame) {
        super(frame, "Colors", 3);
        this.getBlue = true;
        (this.addAdjustmentListener = new Scrollbar(0, 0, 10, 0, 265)).doLayout();
        this.addAdjustmentListener.addAdjustmentListener(this);
        this.addTextListener = new Label("0");
        (this.append = new Scrollbar(0, 0, 10, 0, 265)).addAdjustmentListener(this);
        this.black = new Label("0");
        (this.colorToString = new Scrollbar(0, 0, 10, 0, 265)).addAdjustmentListener(this);
        this.decode = new Label("0");
        (this.add = new Canvas()).setSize(70, 70);
        final Panel panel = new Panel(new FlowLayout());
        final LightPanel lightPanel = new LightPanel(new GridLayout(0, 2, 2, 2));
        lightPanel.add(this.addAdjustmentListener);
        lightPanel.add(this.addTextListener);
        lightPanel.add(this.append);
        lightPanel.add(this.black);
        lightPanel.add(this.colorToString);
        lightPanel.add(this.decode);
        panel.add(this.add);
        panel.add(lightPanel);
        this.add(panel);
        this.add("Color number", this.doLayout = new TextField("", 10));
        this.doLayout.addTextListener(this);
        this.color = Color.black;
    }
    
    public final void setColor(final Color color) {
        this.setColor(color.getRed(), color.getGreen(), color.getBlue());
    }
    
    public final void setColor(final int n, final int n2, final int n3) {
        this.color = new Color(n, n2, n3);
        this.setScroll();
        this.setText();
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.color = new Color(this.addAdjustmentListener.getValue(), this.append.getValue(), this.colorToString.getValue());
        this.setText();
    }
    
    public final void textValueChanged(final TextEvent textEvent) {
        try {
            this.color = Color.decode(this.doLayout.getText());
            this.setScroll();
        }
        catch (NumberFormatException ex) {}
    }
    
    public final void setScroll() {
        final int red = this.color.getRed();
        final int green = this.color.getGreen();
        final int blue = this.color.getBlue();
        this.addAdjustmentListener.setValue(red);
        this.append.setValue(green);
        this.colorToString.setValue(blue);
        this.addTextListener.setText("r: " + red);
        this.black.setText("g: " + green);
        this.decode.setText("b: " + blue);
        this.add.setBackground(this.color);
        String text = this.addTextListener.getText();
        for (int i = text.length(); i < 20; ++i) {
            text += " ";
        }
        this.addTextListener.setText(text);
    }
    
    public final void setText() {
        this.doLayout.setText(GraphicsUtils.colorToString(this.color));
    }
    
    public void mostra(final Formula i) {
        this.I = i;
        this.show();
        this.I = null;
    }
}
