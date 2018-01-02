import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import java.awt.Color;
import javax.swing.Box;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class TabPlay extends MyTab implements ActionListener, ChangeListener
{
    private GridEditPanel editPanel;
    private GridEditPanel viewPanel;
    private JButton clearButton;
    private JLabel polyAmount;
    private NumberSpinner polyIndex;
    private NumberSpinner polyOrient;
    private boolean carrying;
    private int floatHandle;
    private boolean changed;
    
    public TabPlay() {
        this.floatHandle = 0;
        this.changed = false;
        this.setLayout(new BorderLayout());
        this.add(this.editPanel = new GridEditPanel(false, true));
        this.viewPanel = new GridEditPanel(false, false);
        this.polyIndex = new NumberSpinner();
        this.polyOrient = new NumberSpinner();
        this.polyAmount = new JLabel();
        this.clearButton = new JButton("Clear All");
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("Piece:"));
        panel.add(this.polyIndex);
        panel.add(new JLabel("Orientation:"));
        panel.add(this.polyOrient);
        panel.add(this.clearButton);
        panel.add(this.polyAmount);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        panel2.add(this.viewPanel);
        panel2.add(panel);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 1));
        panel3.add(Box.createVerticalStrut(10));
        panel3.add(panel2);
        this.add(panel3, "South");
    }
    
    protected void init() {
        final int numPolyomino = this.puz.getNumPolyomino();
        final boolean enabled = numPolyomino > 0;
        this.polyIndex.removeChangeListener(this);
        this.polyIndex.setMaximum(enabled ? numPolyomino : true);
        this.polyIndex.setMinimum(1);
        this.polyIndex.setTail("/" + numPolyomino);
        this.polyIndex.setValue(1);
        this.polyIndex.setEnabled(enabled);
        this.polyOrient.setEnabled(enabled);
        this.clearButton.setEnabled(enabled);
        final boolean b = enabled & !this.puz.prepareSolve();
        this.editPanel.setGridType(this.puz.getGridType());
        this.editPanel.setNumColours(this.puz.getNumPieces());
        this.editPanel.setBoard(this.puz.getBoard());
        this.viewPanel.setGridType(this.puz.getGridType());
        this.viewPanel.setForeground(Color.CYAN);
        this.viewPanel.setBackground(this.getBackground());
        this.changed = false;
        if (b) {
            this.updatePolyIndex();
            this.updatePolyAmount();
            this.updatePolyOrient();
            this.polyIndex.addChangeListener(this);
            this.polyOrient.addChangeListener(this);
            this.clearButton.addActionListener(this);
            this.editPanel.addActionListener(this);
            this.viewPanel.addActionListener(this);
        }
        else {
            this.viewPanel.setBlockList(false);
        }
    }
    
    protected void exit() {
        if (this.changed) {
            this.puz.setStartPosition(this.puz.getSolution(0));
        }
        this.puz.clearBoard();
    }
    
    private Polyomino getPoly() {
        final int n = this.polyIndex.getValue() - 1;
        return (n >= 0) ? this.puz.getPolyomino(n) : null;
    }
    
    private void updatePolyIndex() {
        if (this.carrying) {
            this.dropIt();
        }
        final Polyomino poly = this.getPoly();
        if (poly == null) {
            this.polyIndex.setEnabled(false);
            this.polyOrient.setEnabled(false);
        }
        else {
            final int numOrient = poly.getNumOrient();
            this.polyOrient.removeChangeListener(this);
            this.polyOrient.setMinimum(1);
            this.polyOrient.setMaximum(numOrient);
            this.polyOrient.setTail("/" + numOrient);
            this.polyOrient.setValue(1);
            this.polyOrient.addChangeListener(this);
        }
    }
    
    private void updatePolyAmount() {
        final int available = this.getPoly().getAvailable();
        this.polyAmount.setText("Amount:" + (this.carrying ? (available - 1) : available));
    }
    
    private void updatePolyOrient() {
        this.viewPanel.setBlockList(this.getPoly().getOrient(this.polyOrient.getValue() - 1), 1);
        if (this.carrying) {
            this.carryIt();
        }
    }
    
    private void carryIt() {
        final Polyomino poly = this.getPoly();
        if (poly.getAvailable() > 0) {
            final Coord[] orient = poly.getOrient(this.polyOrient.getValue() - 1);
            int n = 0;
            for (int i = 0; i < orient.length; ++i) {
                if (orient[i].ori == this.floatHandle) {
                    n = i;
                    break;
                }
            }
            this.editPanel.setFloat(orient, n);
            this.viewPanel.setFloat(orient, n);
            this.carrying = true;
        }
    }
    
    private void dropIt() {
        this.editPanel.setFloat(null, 0);
        this.viewPanel.setFloat(null, 0);
        this.carrying = false;
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        if (changeEvent.getSource() == this.polyIndex) {
            final boolean carrying = this.carrying;
            this.updatePolyIndex();
            this.updatePolyOrient();
            this.floatHandle = 0;
            if (carrying) {
                this.carryIt();
            }
            this.updatePolyAmount();
        }
        else if (changeEvent.getSource() == this.polyOrient) {
            this.updatePolyOrient();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.clearButton) {
            this.changed = true;
            this.puz.clearBoard();
            this.editPanel.getBoard().clear();
            this.editPanel.reset();
            this.updatePolyIndex();
            this.updatePolyOrient();
            this.updatePolyAmount();
        }
        else if (actionEvent.getSource() == this.viewPanel) {
            if (this.carrying) {
                this.dropIt();
            }
            else {
                Coord clickedCoord;
                Coord[] orient;
                int n;
                for (clickedCoord = this.viewPanel.getClickedCoord(), orient = this.getPoly().getOrient(this.polyOrient.getValue() - 1), n = 0; n < orient.length && !clickedCoord.equals(orient[n]); ++n) {}
                this.floatHandle = ((n < orient.length) ? orient[n].ori : 0);
                this.carryIt();
            }
            this.updatePolyAmount();
        }
        else if (actionEvent.getSource() == this.editPanel) {
            final int id = actionEvent.getID();
            final Coord clickedCoord2 = this.editPanel.getClickedCoord();
            if (id == 2) {
                final Polyomino poly = this.getPoly();
                clickedCoord2.ori = this.polyOrient.getValue() - 1;
                final Board board = this.puz.getBoard();
                if (poly.canPlace(board, clickedCoord2)) {
                    poly.placeS(clickedCoord2);
                    poly.place(board, clickedCoord2);
                    this.editPanel.setBoard(board);
                    this.dropIt();
                    this.updatePolyAmount();
                    this.changed = true;
                }
            }
            else if (id < 0) {
                final int n2 = -id;
                Polyomino polyomino = null;
                int i;
                for (i = 0; i < this.puz.getNumPolyomino(); ++i) {
                    polyomino = this.puz.getPolyomino(i);
                    if (n2 >= polyomino.getId() && n2 < polyomino.getId() + polyomino.getNumber()) {
                        break;
                    }
                }
                final int n3 = n2 - polyomino.getId();
                final Coord placement = polyomino.getPlacement(n3);
                final int ori = placement.ori;
                clickedCoord2.sub(placement);
                Coord[] orient2;
                int n4;
                for (orient2 = polyomino.getOrient(ori), n4 = 0; n4 < orient2.length && !orient2[n4].equals(clickedCoord2); ++n4) {}
                this.floatHandle = ((n4 >= orient2.length) ? 0 : orient2[n4].ori);
                final Board board2 = this.puz.getBoard();
                polyomino.remove(board2, n3);
                this.editPanel.setBoard(board2);
                this.changed = true;
                this.polyIndex.removeChangeListener(this);
                this.polyIndex.setValue(i + 1);
                this.polyIndex.addChangeListener(this);
                this.updatePolyIndex();
                this.polyOrient.removeChangeListener(this);
                this.polyOrient.setValue(ori + 1);
                this.polyOrient.addChangeListener(this);
                this.updatePolyOrient();
                this.carryIt();
                this.updatePolyAmount();
            }
        }
    }
}
