import java.awt.Graphics;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Frame;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class PlotterDialog extends Dialog
{
    protected Frame add;
    protected PlotterPanel addWindowListener;
    
    public PlotterDialog(final Frame frame, final PlotterPanel plotterPanel) {
        super(frame);
        this.init(frame, plotterPanel);
    }
    
    public final void init(final Frame frame, final PlotterPanel addWindowListener) {
        this.setLayout(new GridLayout(1, 1));
        this.add(addWindowListener);
        this.addWindowListener = addWindowListener;
        this.addWindowListener.mainFrame = frame;
        this.add = frame;
        this.setTitle(addWindowListener.getPlotComp().getName());
        this.addWindowListener(new WindowListenerWithClose());
        this.reset();
    }
    
    public final void reset() {
        if (this.getParent().isVisible()) {
            this.pack();
        }
        this.addWindowListener.reset();
    }
    
    public final void setPlotPanel(final PlotterPanel addWindowListener) {
        this.addWindowListener = addWindowListener;
        this.removeAll();
        this.add(addWindowListener);
    }
    
    public final void dispose() {
        this.addWindowListener.setVisible(false);
        super.dispose();
        this.addWindowListener.dispose();
        try {
            this.finalize();
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
