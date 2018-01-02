import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

// 
// Decompiled by Procyon v0.5.30
// 

class ProgressFrame extends FrameCloser
{
    private static final int INSET = 20;
    private static final int PADDING_X = 8;
    private static final int PADDING_Y = 5;
    private GridBagLayout gridBag;
    private GridBagConstraints constraints;
    
    public ProgressFrame(final ProgressPanel panel) {
        super("Progress Window");
        this.gridBag = new GridBagLayout();
        this.constraints = new GridBagConstraints();
        this.setFont(Screen.screen.getFont());
        this.setLayout(this.gridBag);
        this.setBackground(Color.lightGray);
        this.constraints.insets = new Insets(20, 20, 20, 20);
        this.constraints.ipadx = 8;
        this.constraints.ipady = 5;
        this.gridBag.setConstraints(panel, this.constraints);
        this.add(panel);
        panel.setVisible(true);
        this.setSize(350, 360);
        this.addWindowListener(this);
    }
    
    public void closeWindow() {
        Screen.screen.clickProgress();
    }
}
