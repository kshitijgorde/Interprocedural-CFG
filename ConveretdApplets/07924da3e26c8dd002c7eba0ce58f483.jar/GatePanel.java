import java.awt.Graphics;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class GatePanel extends JPanel implements UpdateEvent
{
    private boolean _boolTable;
    protected TruthTable _truthTable;
    protected BitRadioButton[] _bitRadioInputs;
    protected BitRadioButton[] _bitRadioOutputs;
    protected GateComponent[] _gateComponents;
    
    public GatePanel(final int x, final int y, final int width, final int height, final boolean table) {
        this._boolTable = table;
        this.setBounds(x, y, width, height);
        this.setLayout(null);
        this.setBackground(new Color(192, 192, 192));
        this.setBorder(new BorderUIResource.EtchedBorderUIResource(1));
        this.initComponents();
    }
    
    public boolean hasTable() {
        return this._boolTable;
    }
    
    protected void initComponents() {
    }
    
    public void update() {
        this.repaint();
    }
    
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (this._boolTable && this._truthTable != null) {
            this._truthTable.repaint();
        }
    }
}
