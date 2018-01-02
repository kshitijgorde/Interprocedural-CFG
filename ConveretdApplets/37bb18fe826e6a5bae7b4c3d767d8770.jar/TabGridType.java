import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.Box;
import java.awt.Color;
import java.awt.Component;
import javax.swing.AbstractButton;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class TabGridType extends MyTab implements ActionListener
{
    private JLabel icon;
    private JLabel shapeLabel;
    private JLabel boardLabel;
    private JRadioButton[] rb;
    private JButton shapeButton;
    private JButton boardButton;
    private ButtonGroup typesButtons;
    private GridType[] gridTypes;
    private final int numTypes;
    
    public TabGridType() {
        this.numTypes = GridType.getNumTypes();
        final JPanel panel = new JPanel();
        this.rb = new JRadioButton[this.numTypes];
        this.gridTypes = new GridType[this.numTypes];
        panel.setLayout(new BoxLayout(panel, 1));
        this.typesButtons = new ButtonGroup();
        for (int i = 0; i < this.numTypes; ++i) {
            this.gridTypes[i] = GridType.factory(i);
            this.rb[i] = new JRadioButton(GridType.names[i]);
            this.typesButtons.add(this.rb[i]);
            this.rb[i].addActionListener(this);
            panel.add(this.rb[i]);
        }
        this.rb[0].setSelected(true);
        (this.icon = new JLabel()).setBackground(Color.WHITE);
        this.shapeLabel = new JLabel();
        (this.shapeButton = new JButton("Delete all shapes")).addActionListener(this);
        this.boardLabel = new JLabel();
        (this.boardButton = new JButton("Delete the board")).addActionListener(this);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 1));
        panel2.add(this.shapeLabel);
        panel2.add(this.shapeButton);
        panel2.add(this.boardLabel);
        panel2.add(this.boardButton);
        this.setLayout(new BoxLayout(this, 0));
        this.add(panel);
        this.add(Box.createHorizontalGlue());
        this.add(this.icon);
        this.add(Box.createHorizontalGlue());
        this.add(panel2);
    }
    
    protected void init() {
        this.setLabels();
        if (this.puz.getGridType() == null) {
            this.setIcon(0);
            this.rb[0].setSelected(true);
        }
        else {
            for (int i = 0; i < this.numTypes; ++i) {
                if (this.gridTypes[i].getClass() == this.puz.getGridType().getClass()) {
                    this.setIcon(i);
                    this.rb[i].setSelected(true);
                    break;
                }
            }
        }
    }
    
    protected void setLabels() {
        final boolean b = true;
        final int numPolyomino = this.puz.getNumPolyomino();
        this.shapeButton.setEnabled(numPolyomino != 0);
        final boolean b2 = b & numPolyomino == 0;
        this.shapeLabel.setText(((numPolyomino == 0) ? "No" : (numPolyomino + "")) + ((numPolyomino == 1) ? " shape" : " shapes") + " defined.");
        final int numBlocks = this.puz.getBoard().getNumBlocks();
        this.boardButton.setEnabled(numBlocks != 0);
        final boolean enabled = b2 & numBlocks == 0;
        this.boardLabel.setText((numBlocks == 0) ? "No board defined." : ("Board with " + numBlocks + ((numBlocks == 1) ? " cell" : " cells") + " defined."));
        for (int i = 0; i < this.numTypes; ++i) {
            this.rb[i].setEnabled(enabled);
        }
    }
    
    protected void exit() {
        for (int i = 0; i < this.numTypes; ++i) {
            if (this.rb[i].isSelected()) {
                this.puz.setGridType(this.gridTypes[i]);
            }
        }
    }
    
    private void setIcon(final int n) {
        this.gridTypes[n].setSize(300, 300);
        this.icon.setIcon(this.gridTypes[n]);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.boardButton) {
            this.puz.getBoard().wipe();
            this.setLabels();
        }
        else if (actionEvent.getSource() == this.shapeButton) {
            while (this.puz.getNumPolyomino() > 0) {
                this.puz.removePolyomino(this.puz.getPolyomino(0));
            }
            this.setLabels();
        }
        else {
            for (int i = 0; i < this.numTypes; ++i) {
                if (this.rb[i].isSelected()) {
                    this.setIcon(i);
                }
            }
        }
    }
}
