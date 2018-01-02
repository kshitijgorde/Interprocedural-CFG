import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class TabBoard extends MyTab implements ActionListener
{
    private JPanel controlPanel;
    private JLabel boardLabel;
    private JLabel shapeLabel;
    private JButton clearButton;
    private GridEditPanel editPanel;
    
    public TabBoard() {
        this.setLayout(new BorderLayout());
        this.add(this.editPanel = new GridEditPanel(true, false));
        this.editPanel.addActionListener(this);
        this.editPanel.setForeground(Color.LIGHT_GRAY);
        this.boardLabel = new JLabel();
        this.shapeLabel = new JLabel();
        (this.clearButton = new JButton("Clear")).addActionListener(this);
        (this.controlPanel = new JPanel()).add(this.boardLabel);
        this.controlPanel.add(this.shapeLabel);
        this.controlPanel.add(this.clearButton);
        this.add(this.controlPanel, "South");
    }
    
    protected void init() {
        this.puz.clearBoard();
        this.editPanel.setGridType(this.puz.getGridType());
        this.editPanel.setBoard(this.puz.getBoard());
        final int minNumBlocks = this.puz.getMinNumBlocks();
        final int maxNumBlocks = this.puz.getMaxNumBlocks();
        this.shapeLabel.setText("Shapes use " + minNumBlocks + ((minNumBlocks != maxNumBlocks) ? ("-" + maxNumBlocks + " ") : " ") + ((minNumBlocks == 1 && maxNumBlocks == 1) ? "cell" : "cells") + ".");
        this.setLabel();
    }
    
    protected void exit() {
        this.saveBoard();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.clearButton) {
            this.editPanel.setBlockList(true);
            this.setLabel();
        }
        else if (actionEvent.getSource() == this.editPanel) {
            this.setLabel();
        }
    }
    
    private void saveBoard() {
        if (!this.editPanel.hasChanged()) {
            return;
        }
        this.puz.setBoard(this.editPanel.getBoard());
    }
    
    private void setLabel() {
        final int numBlocks = this.editPanel.getBoard().getNumBlocks();
        this.boardLabel.setText("Board contains " + ((numBlocks == 0) ? "no " : (numBlocks + " ")) + ((numBlocks == 1) ? "cell" : "cells") + ".");
    }
}
