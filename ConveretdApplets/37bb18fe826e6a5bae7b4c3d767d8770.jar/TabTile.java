import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class TabTile extends MyTab implements ActionListener, ChangeListener
{
    private JButton clearButton;
    private JButton delDupButton;
    private JButton combDupButton;
    private JLabel boardLabel;
    private JLabel shapeLabel;
    private GridEditPanel editPanel;
    private NumberSpinner polyIndex;
    private NumberSpinner polyOrient;
    private NumberSpinner polyAmount;
    private JRadioButton[] rb;
    private int currentPolyIndex;
    private int currentPolyOrient;
    
    public TabTile() {
        this.currentPolyIndex = 1;
        this.currentPolyOrient = 1;
        this.setLayout(new BorderLayout());
        (this.editPanel = new GridEditPanel(true, false)).setForeground(Color.CYAN);
        this.editPanel.addActionListener(this);
        this.add(this.editPanel);
        final JPanel panel = new JPanel();
        this.rb = new JRadioButton[3];
        panel.setLayout(new BoxLayout(panel, 1));
        final String[] array = { "None", "Rotations", "Reflections" };
        final ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < 3; ++i) {
            buttonGroup.add(this.rb[i] = new JRadioButton(array[i]));
            this.rb[i].addActionListener(this);
            panel.add(this.rb[i]);
        }
        this.polyIndex = new NumberSpinner();
        this.polyOrient = new NumberSpinner();
        this.polyAmount = new NumberSpinner();
        this.polyIndex.addChangeListener(this);
        this.polyOrient.addChangeListener(this);
        this.polyAmount.addChangeListener(this);
        (this.clearButton = new JButton("Delete")).addActionListener(this);
        (this.delDupButton = new JButton("Delete Duplicates")).addActionListener(this);
        (this.combDupButton = new JButton("Combine Duplicates")).addActionListener(this);
        this.boardLabel = new JLabel();
        this.shapeLabel = new JLabel();
        final JPanel panel2 = new JPanel();
        panel2.add(this.boardLabel);
        panel2.add(this.shapeLabel);
        final JPanel panel3 = new JPanel();
        panel3.add(new JLabel("Piece:"));
        panel3.add(this.polyIndex);
        panel3.add(new JLabel("Amount:"));
        panel3.add(this.polyAmount);
        panel3.add(panel);
        panel3.add(new JLabel("Orientation:"));
        panel3.add(this.polyOrient);
        panel3.add(this.clearButton);
        final JPanel panel4 = new JPanel();
        panel4.add(this.delDupButton);
        panel4.add(this.combDupButton);
        final JPanel panel5 = new JPanel();
        this.add(panel5, "South");
        panel5.setLayout(new BoxLayout(panel5, 1));
        panel5.add(panel2);
        panel5.add(panel3);
        panel5.add(panel4);
    }
    
    protected void init() {
        this.editPanel.setGridType(this.puz.getGridType());
        this.polyIndex.setValue(1);
        this.currentPolyIndex = 1;
        this.updatePolyIndex();
        this.loadTile();
        final int numBlocks = this.puz.getBoard().getNumBlocks();
        this.boardLabel.setText("Board contains " + ((numBlocks == 0) ? "no " : (numBlocks + " ")) + ((numBlocks == 1) ? "cell" : "cells") + ".");
        this.setLabel();
    }
    
    private void setLabel() {
        final int minNumBlocks = this.puz.getMinNumBlocks();
        final int maxNumBlocks = this.puz.getMaxNumBlocks();
        this.shapeLabel.setText("Shapes use " + minNumBlocks + ((minNumBlocks != maxNumBlocks) ? ("-" + maxNumBlocks + " ") : " ") + ((minNumBlocks == 1 && maxNumBlocks == 1) ? "cell" : "cells") + ".");
    }
    
    protected void exit() {
        this.saveTile();
    }
    
    private Polyomino getPoly() {
        final int n = this.currentPolyIndex - 1;
        return (n == this.puz.getNumPolyomino()) ? null : this.puz.getPolyomino(n);
    }
    
    private int getType() {
        for (int i = 0; i < this.rb.length; ++i) {
            if (this.rb[i].isSelected()) {
                return i;
            }
        }
        return 2;
    }
    
    private void updatePolyIndex() {
        this.polyIndex.removeChangeListener(this);
        final int numPolyomino = this.puz.getNumPolyomino();
        this.polyIndex.setMinimum(1);
        this.polyIndex.setMaximum(numPolyomino + 2);
        this.polyIndex.setTail("/" + numPolyomino);
        if (this.polyIndex.getValue() >= numPolyomino + 2) {
            this.polyIndex.setValue(numPolyomino + 1);
        }
        this.currentPolyIndex = this.polyIndex.getValue();
        this.polyIndex.addChangeListener(this);
        this.polyOrient.removeChangeListener(this);
        this.polyOrient.setValue(1);
        this.polyOrient.addChangeListener(this);
        this.updatePolyOrient();
    }
    
    private void updatePolyOrient() {
        final Polyomino poly = this.getPoly();
        this.polyOrient.removeChangeListener(this);
        this.polyAmount.removeChangeListener(this);
        this.polyOrient.setMinimum(1);
        final int n = (poly == null) ? 0 : poly.getNumUserOrient();
        this.polyOrient.setMaximum(n + 2);
        this.polyOrient.setTail("/" + n);
        if (this.polyOrient.getValue() >= n + 2) {
            this.polyOrient.setValue(n + 1);
        }
        this.currentPolyOrient = this.polyOrient.getValue();
        if (poly != null) {
            this.rb[poly.getType()].setSelected(true);
            final int number = poly.getNumber();
            this.polyAmount.setMaximum(number + 1);
            this.polyAmount.setValue(number);
            if (this.currentPolyOrient == n + 1) {
                this.editPanel.setBlockList(false);
            }
            else {
                this.editPanel.setBlockList(poly.getUserOrient(this.currentPolyOrient - 1), 0);
            }
        }
        else {
            this.rb[2].setSelected(true);
            this.polyAmount.setValue(1);
            this.editPanel.setBlockList(false);
        }
        this.polyAmount.addChangeListener(this);
        this.polyOrient.addChangeListener(this);
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        if (changeEvent.getSource() == this.polyIndex) {
            this.saveTile();
            this.updatePolyIndex();
            this.loadTile();
        }
        else if (changeEvent.getSource() == this.polyOrient) {
            this.saveTile();
            this.updatePolyOrient();
            this.loadTile();
        }
        else if (changeEvent.getSource() == this.polyAmount) {
            final Polyomino poly = this.getPoly();
            if (poly != null) {
                poly.setNumber(this.polyAmount.getValue());
                this.setLabel();
            }
            this.polyAmount.removeChangeListener(this);
            this.polyAmount.setMaximum(this.polyAmount.getValue() + 1);
            this.polyAmount.addChangeListener(this);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.clearButton) {
            this.editPanel.setBlockList(true);
            this.saveTile();
            this.updatePolyIndex();
        }
        else if (actionEvent.getSource() == this.delDupButton) {
            this.removeDuplicates(false);
        }
        else if (actionEvent.getSource() == this.combDupButton) {
            this.removeDuplicates(true);
        }
        else if (actionEvent.getSource() == this.editPanel) {
            this.saveTile();
        }
        else {
            final Polyomino poly = this.getPoly();
            if (poly != null) {
                poly.setType(this.getType());
            }
        }
    }
    
    private void saveTile() {
        if (!this.editPanel.hasChanged()) {
            return;
        }
        final Polyomino poly = this.getPoly();
        final Coord[] blockArray = this.editPanel.getBoard().getBlockArray();
        if (poly == null) {
            if (blockArray.length != 0) {
                final Polyomino polyomino = new Polyomino();
                polyomino.addUserOrient(blockArray, this.puz.getGridType());
                polyomino.setType(this.getType());
                polyomino.setNumber(this.polyAmount.getValue());
                this.puz.addPolyomino(polyomino);
                this.polyIndex.setTail("/" + this.puz.getNumPolyomino());
                this.polyOrient.setTail("/" + polyomino.getNumUserOrient());
            }
        }
        else {
            poly.setType(this.getType());
            poly.setNumber(this.polyAmount.getValue());
            final int n = this.currentPolyOrient - 1;
            if (n >= poly.getNumUserOrient()) {
                poly.addUserOrient(blockArray, this.puz.getGridType());
                this.polyOrient.setTail("/" + poly.getNumUserOrient());
            }
            else if (blockArray.length == 0) {
                poly.delUserOrient(n);
                this.polyOrient.removeChangeListener(this);
                final int numUserOrient = poly.getNumUserOrient();
                this.polyOrient.setValue(numUserOrient + 1);
                this.polyOrient.setTail("/" + numUserOrient);
                this.polyOrient.addChangeListener(this);
                if (poly.getNumUserOrient() == 0) {
                    this.puz.removePolyomino(poly);
                    this.polyIndex.removeChangeListener(this);
                    final int numPolyomino = this.puz.getNumPolyomino();
                    this.polyIndex.setValue(numPolyomino + 1);
                    this.polyIndex.setTail("/" + numPolyomino);
                    this.polyIndex.addChangeListener(this);
                }
            }
            else {
                poly.setUserOrient(n, blockArray, this.puz.getGridType());
            }
        }
        this.setLabel();
    }
    
    private void loadTile() {
        final Polyomino poly = this.getPoly();
        if (poly == null) {
            this.editPanel.setBlockList(false);
        }
        else {
            this.rb[poly.getType()].setSelected(true);
            final int n = this.polyOrient.getValue() - 1;
            if (n >= poly.getNumUserOrient()) {
                this.editPanel.setBlockList(false);
            }
            else {
                this.editPanel.setBlockList(poly.getUserOrient(n), 0);
            }
        }
    }
    
    private void removeDuplicates(final boolean b) {
        final Polyomino poly = this.getPoly();
        this.puz.removeDuplicates(b);
        int numPolyomino = 0;
        if (poly == null) {
            numPolyomino = this.puz.getNumPolyomino();
        }
        else {
            for (int i = 0; i < this.puz.getNumPolyomino(); ++i) {
                if (this.puz.getPolyomino(i).sameShape(poly)) {
                    numPolyomino = i;
                    break;
                }
            }
        }
        this.polyIndex.removeChangeListener(this);
        this.polyIndex.setValue(numPolyomino + 1);
        this.updatePolyIndex();
    }
}
