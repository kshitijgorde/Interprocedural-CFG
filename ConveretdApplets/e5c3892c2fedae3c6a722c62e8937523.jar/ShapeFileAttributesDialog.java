import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class ShapeFileAttributesDialog extends JDialog implements WindowListener, ActionListener
{
    private JTabbedPane tabbedPane;
    private AttributeTable attributeTable;
    
    public ShapeFileAttributesDialog(final JFrame frame) {
        super(frame, "Shapefile Attributes", false);
        this.tabbedPane = new JTabbedPane();
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        (this.attributeTable = new AttributeTable()).setToolTipText("Attribute values");
        panel.add(new JScrollPane(this.attributeTable), "Center");
        this.tabbedPane.addTab("Attributes", panel);
        this.tabbedPane.setMnemonicAt(0, 65);
        this.tabbedPane.setToolTipTextAt(0, "Display attribute values");
        final JPanel panel2 = new JPanel();
        final JButton button = new JButton("Close");
        button.setMnemonic(67);
        button.setToolTipText("Close the shapefile attributes");
        button.addActionListener(this);
        panel2.add(button);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.add(this.tabbedPane, "Center");
        panel3.add(panel2, "South");
        this.getContentPane().add(panel3);
        this.setSize(600, 400);
        this.addWindowListener(this);
    }
    
    @Override
    public void windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    @Override
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public ShapeFileAttributesPanel addShapeFile(final String s, final ShapeFileMapLayer shapeFileMapLayer, final String[] array) {
        final ShapeFileAttributesPanel shapeFileAttributesPanel = new ShapeFileAttributesPanel(shapeFileMapLayer, array, this.attributeTable);
        final int tabCount = this.tabbedPane.getTabCount();
        this.tabbedPane.addTab("" + tabCount + ":" + s, shapeFileAttributesPanel);
        this.tabbedPane.setToolTipTextAt(tabCount, s + " settings");
        int n = 48 + tabCount;
        if (tabCount > 9) {
            n = 65 + tabCount - 10;
        }
        this.tabbedPane.setMnemonicAt(tabCount, n);
        return shapeFileAttributesPanel;
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Close")) {
            this.setVisible(false);
        }
    }
}
