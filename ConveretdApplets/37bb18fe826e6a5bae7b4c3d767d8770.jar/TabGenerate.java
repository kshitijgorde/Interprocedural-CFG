import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.Component;
import java.awt.Color;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class TabGenerate extends MyTab implements ActionListener, ChangeListener
{
    private JButton clearListButton;
    private JButton addListButton;
    private JButton joinButton;
    private JButton addButton;
    private JButton clearTileButton;
    private JButton delTileButton;
    private JButton addNeighButton;
    private GridEditPanel editPanel;
    private GridEditPanel viewPanel;
    private NumberSpinner polyIndex;
    private JRadioButton[] rbTile;
    private JRadioButton[] rbList;
    private JLabel boardLabel;
    private JLabel shapeLabel;
    private JLabel listLabel;
    private ArrayList polyList;
    
    public TabGenerate() {
        this.polyList = new ArrayList();
        this.setLayout(new BorderLayout());
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        (this.editPanel = new GridEditPanel(true, false)).setForeground(Color.CYAN);
        this.editPanel.addActionListener(this);
        panel.add(this.editPanel);
        (this.viewPanel = new GridEditPanel(false, false)).setForeground(Color.CYAN);
        panel.add(this.viewPanel);
        this.add(panel);
        final JPanel panel2 = new JPanel();
        final JPanel panel3 = new JPanel();
        this.rbTile = new JRadioButton[3];
        this.rbList = new JRadioButton[3];
        panel2.setLayout(new BoxLayout(panel2, 1));
        panel3.setLayout(new BoxLayout(panel3, 1));
        final String[] array = { "None", "Rotations", "Reflections" };
        final ButtonGroup buttonGroup = new ButtonGroup();
        final ButtonGroup buttonGroup2 = new ButtonGroup();
        for (int i = 0; i < 3; ++i) {
            this.rbTile[i] = new JRadioButton(array[i]);
            this.rbList[i] = new JRadioButton(array[i]);
            buttonGroup2.add(this.rbTile[i]);
            buttonGroup.add(this.rbList[i]);
            this.rbTile[i].addActionListener(this);
            this.rbList[i].addActionListener(this);
            panel2.add(this.rbTile[i]);
            panel3.add(this.rbList[i]);
        }
        this.rbList[2].setSelected(true);
        this.rbTile[2].setSelected(true);
        (this.polyIndex = new NumberSpinner()).addChangeListener(this);
        (this.clearTileButton = new JButton("Clear")).addActionListener(this);
        (this.delTileButton = new JButton("Delete Shape")).addActionListener(this);
        (this.addNeighButton = new JButton("Expand")).addActionListener(this);
        (this.clearListButton = new JButton("Clear List")).addActionListener(this);
        (this.addListButton = new JButton("Add List")).addActionListener(this);
        (this.joinButton = new JButton("Join")).addActionListener(this);
        (this.addButton = new JButton("Add")).addActionListener(this);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, 1));
        panel4.add(this.clearTileButton);
        panel4.add(this.addNeighButton);
        panel4.add(this.addButton);
        panel4.add(this.joinButton);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, 1));
        panel5.add(this.delTileButton);
        panel5.add(this.clearListButton);
        panel5.add(this.addListButton);
        final JPanel panel6 = new JPanel();
        panel6.add(new JLabel("Tile:"));
        panel6.add(panel2);
        panel6.add(panel4);
        panel6.add(new JLabel("List:"));
        panel6.add(panel3);
        panel6.add(this.polyIndex);
        panel6.add(panel5);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new BoxLayout(panel7, 1));
        this.boardLabel = new JLabel();
        this.shapeLabel = new JLabel();
        this.listLabel = new JLabel();
        panel7.add(this.boardLabel);
        panel7.add(this.shapeLabel);
        panel7.add(this.listLabel);
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new BoxLayout(panel8, 1));
        panel8.add(panel7);
        panel8.add(panel6);
        this.add(panel8, "South");
    }
    
    protected void init() {
        final GridType gridType = this.editPanel.getGridType();
        if (gridType == null || gridType.getClass() != this.puz.getGridType().getClass()) {
            final GridType gridType2 = this.puz.getGridType();
            this.editPanel.setGridType(gridType2);
            this.viewPanel.setGridType(gridType2);
            this.editPanel.setBlockList(false);
            this.viewPanel.setBlockList(false);
            this.polyList.clear();
            this.polyIndex.setMinimum(1);
            this.setPolyIndex(1);
            final int n = (gridType2.numDim() >= 3) ? 1 : 2;
            this.rbList[n].setSelected(true);
            this.rbTile[n].setSelected(true);
        }
        final int numBlocks = this.puz.getBoard().getNumBlocks();
        this.boardLabel.setText("Board contains " + ((numBlocks == 0) ? "no " : (numBlocks + " ")) + ((numBlocks == 1) ? "cell" : "cells") + ".");
        this.setLabel();
        this.updateButtons();
    }
    
    private void setLabel() {
        final int numPolyomino = this.puz.getNumPolyomino();
        final int minNumBlocks = this.puz.getMinNumBlocks();
        final int maxNumBlocks = this.puz.getMaxNumBlocks();
        this.shapeLabel.setText("Puzzle has " + numPolyomino + ((numPolyomino == 1) ? " shape" : " shapes") + " and uses " + minNumBlocks + ((minNumBlocks != maxNumBlocks) ? ("-" + maxNumBlocks + " ") : " ") + ((minNumBlocks == 1 && maxNumBlocks == 1) ? "cell" : "cells") + ".");
        final int size = this.polyList.size();
        int n = 0;
        for (int i = 0; i < size; ++i) {
            n += ((Polyomino)this.polyList.get(i)).getMinNumBlocks();
        }
        this.listLabel.setText("List has " + size + ((size == 1) ? " shape" : " shapes") + " uses " + n + ((n == 1) ? " cell" : " cells") + ".");
    }
    
    protected void exit() {
    }
    
    private Polyomino getPoly() {
        final int n = this.polyIndex.getValue() - 1;
        return (n >= this.polyList.size()) ? null : ((Polyomino)this.polyList.get(n));
    }
    
    private int getSym(final JRadioButton[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i].isSelected()) {
                return i;
            }
        }
        return 2;
    }
    
    private int getListSym() {
        return this.getSym(this.rbList);
    }
    
    private int getTileSym() {
        return this.getSym(this.rbTile);
    }
    
    private void setPolyIndex(int value) {
        this.polyIndex.removeChangeListener(this);
        final int size = this.polyList.size();
        if (value > size) {
            value = ((size == 0) ? 1 : size);
        }
        this.polyIndex.setMaximum((size == 0) ? 1 : size);
        this.polyIndex.setTail("/" + size);
        this.polyIndex.setValue(value);
        this.polyIndex.addChangeListener(this);
        final Polyomino poly = this.getPoly();
        if (poly != null) {
            this.viewPanel.setBlockList(poly.getUserOrient(0), 1);
        }
        else {
            this.viewPanel.setBlockList(false);
        }
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        if (changeEvent.getSource() == this.polyIndex) {
            this.setPolyIndex(this.polyIndex.getValue());
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.clearListButton) {
            this.polyList.clear();
            this.setPolyIndex(1);
            this.updateButtons();
            this.setLabel();
        }
        else if (actionEvent.getSource() == this.addListButton) {
            for (int i = 0; i < this.polyList.size(); ++i) {
                final Polyomino polyomino = this.polyList.get(i);
                final Polyomino polyomino2 = new Polyomino();
                polyomino2.addUserOrient(polyomino.getUserOrient(0), this.puz.getGridType());
                polyomino2.setNumber(1);
                polyomino2.setType(this.getListSym());
                this.puz.addPolyomino(polyomino2);
            }
            this.updateButtons();
            this.setLabel();
        }
        else if (actionEvent.getSource() == this.joinButton) {
            this.joinTile();
            this.updateButtons();
            this.setPolyIndex(this.polyIndex.getValue());
            this.setLabel();
        }
        else if (actionEvent.getSource() == this.addButton) {
            this.addTile();
            this.updateButtons();
            this.setPolyIndex(this.polyList.size());
            this.setLabel();
        }
        else if (actionEvent.getSource() == this.clearTileButton) {
            this.editPanel.setBlockList(false);
            this.updateButtons();
        }
        else if (actionEvent.getSource() == this.addNeighButton) {
            this.addNeighbours();
        }
        else if (actionEvent.getSource() == this.delTileButton) {
            this.polyList.remove(this.getPoly());
            this.setPolyIndex(this.polyIndex.getValue());
            this.setLabel();
        }
        else if (actionEvent.getSource() == this.editPanel) {
            this.updateButtons();
        }
    }
    
    private void updateButtons() {
        final boolean b = this.editPanel.getBoard().getNumBlocks() != 0;
        this.clearTileButton.setEnabled(b);
        this.addNeighButton.setEnabled(b);
        this.addButton.setEnabled(b);
        this.joinButton.setEnabled(b);
        final boolean enabled = this.polyList.size() != 0;
        this.delTileButton.setEnabled(enabled);
        this.clearListButton.setEnabled(enabled);
        this.addListButton.setEnabled(enabled);
    }
    
    private void addTile() {
        final Coord[] blockArray = this.editPanel.getBoard().getBlockArray();
        final Polyomino polyomino = new Polyomino();
        polyomino.addUserOrient(blockArray, this.puz.getGridType());
        polyomino.setNumber(1);
        polyomino.setType(this.getListSym());
        polyomino.validate(this.puz.getGridType());
        this.polyList.add(polyomino);
    }
    
    private void addNeighbours() {
        final GridType gridType = this.puz.getGridType();
        final Coord[] blockArray = this.editPanel.getBoard().getBlockArray();
        for (int i = 0; i < blockArray.length; ++i) {
            int n = 0;
            while (true) {
                final Coord adjacent = gridType.getAdjacent(blockArray[i], n);
                if (adjacent == null) {
                    break;
                }
                this.editPanel.setBlock(adjacent);
                ++n;
            }
        }
        this.editPanel.repaint();
    }
    
    private void joinTile() {
        if (this.polyList.size() == 0) {
            this.addTile();
            return;
        }
        final Coord[] blockArray = this.editPanel.getBoard().getBlockArray();
        if (blockArray.length == 0) {
            return;
        }
        final Polyomino polyomino = new Polyomino();
        polyomino.addUserOrient(blockArray, this.puz.getGridType());
        polyomino.setNumber(1);
        polyomino.setType(this.getTileSym());
        polyomino.validate(this.puz.getGridType());
        final ArrayList polyList = new ArrayList();
        for (int i = 0; i < this.polyList.size(); ++i) {
            this.joinTile(polyList, polyomino, (Polyomino)this.polyList.get(i));
        }
        this.polyList.clear();
        this.polyList = polyList;
    }
    
    private void joinTile(final ArrayList list, final Polyomino polyomino, final Polyomino polyomino2) {
        for (int i = 0; i < polyomino.getNumOrient(); ++i) {
            for (int j = 0; j < polyomino2.getNumOrient(); ++j) {
                this.joinTile(list, polyomino.getOrient(i), polyomino2.getOrient(j));
            }
        }
    }
    
    private void joinTile(final ArrayList list, final Coord[] array, final Coord[] array2) {
        final GridType gridType = this.puz.getGridType();
        final ArrayList list2 = new ArrayList<Coord>();
        for (int i = 0; i < array2.length; ++i) {
            int n = 0;
            while (true) {
                final Coord adjacent = gridType.getAdjacent(array2[i], n);
                if (adjacent == null) {
                    break;
                }
                if (!list2.contains(adjacent)) {
                    list2.add(adjacent);
                }
                ++n;
            }
        }
        for (int j = 0; j < array2.length; ++j) {
            list2.remove(array2[j]);
        }
        final Coord coord = new Coord();
        for (int k = 0; k < list2.size(); ++k) {
            final Coord coord2 = list2.get(k);
            for (int l = 0; l < array.length; ++l) {
                coord.set(coord2);
                coord.sub(array[l]);
                if (gridType.numDim() == 3 || coord.z == 0) {
                    this.joinTile(list, coord, array, array2);
                }
            }
        }
    }
    
    private void joinTile(final ArrayList list, final Coord coord, final Coord[] array, final Coord[] array2) {
        for (int i = 0; i < array.length; ++i) {
            array[i].add(coord);
        }
        boolean b = false;
        for (int n = 0; n < array.length && !b; ++n) {
            for (int n2 = 0; n2 < array2.length && !b; b |= array[n].equals(array2[n2]), ++n2) {}
        }
        if (!b) {
            final Coord[] array3 = new Coord[array.length + array2.length];
            int n3 = 0;
            for (int j = 0; j < array.length; ++j, ++n3) {
                array3[n3] = new Coord(array[j]);
            }
            for (int k = 0; k < array2.length; ++k, ++n3) {
                array3[n3] = new Coord(array2[k]);
            }
            final Polyomino polyomino = new Polyomino();
            polyomino.addUserOrient(array3, this.puz.getGridType());
            polyomino.setNumber(1);
            polyomino.setType(this.getListSym());
            polyomino.validate(this.puz.getGridType());
            boolean b2 = false;
            for (int n4 = 0; !b2 && n4 < list.size(); ++n4) {
                if (polyomino.sameShape(list.get(n4))) {
                    b2 = true;
                }
            }
            if (!b2) {
                list.add(polyomino);
            }
        }
        for (int l = 0; l < array.length; ++l) {
            array[l].sub(coord);
        }
    }
}
