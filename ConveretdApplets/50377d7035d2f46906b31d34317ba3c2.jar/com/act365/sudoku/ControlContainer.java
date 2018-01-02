// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

import java.awt.datatransfer.Clipboard;
import java.awt.event.ItemEvent;
import java.util.StringTokenizer;
import java.awt.event.MouseEvent;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Label;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.event.MouseListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import com.act365.awt.Container;

public class ControlContainer extends Container implements ActionListener, ItemListener, MouseListener, ClipboardOwner
{
    static final int defaultMinFilledCellsValue = 32;
    int boxesAcross;
    int boxesDown;
    int cellsInRow;
    int minFilledCellsValue;
    boolean isApplet;
    Class appClass;
    GridContainer grid;
    TextField across;
    TextField down;
    TextField minFilledCells;
    TextArea reasoningArea;
    Button solve;
    Button unsolve;
    Button reset;
    Button resize;
    Button evaluate;
    Button compose;
    Button interrupt;
    Button copy;
    Button paste;
    Label solns;
    Label singleSectorCandidatesEliminations;
    Label disjointSubsetsEliminations;
    Label xWingsEliminations;
    Label swordfishEliminations;
    Label nishioEliminations;
    Label nGuesses;
    Choice format;
    Choice puzzleType;
    Checkbox singleSectorCandidates;
    Checkbox disjointSubsets;
    Checkbox xWings;
    Checkbox swordfish;
    Checkbox nishio;
    Checkbox guess;
    SuDokuClipboard clipboard;
    
    public ControlContainer(final GridContainer grid, final Class appClass) {
        this.grid = grid;
        this.appClass = appClass;
        try {
            final Class suDokuApplet = Class.forName("com.act365.sudoku.SuDokuApplet");
            this.isApplet = appClass.equals(suDokuApplet);
        }
        catch (ClassNotFoundException e) {
            this.isApplet = false;
        }
        if (this.isApplet) {
            this.clipboard = new SuDokuClipboard();
        }
        this.boxesAcross = grid.getBoxesAcross();
        this.boxesDown = grid.getBoxesDown();
        this.cellsInRow = this.boxesAcross * this.boxesDown;
        this.minFilledCellsValue = 32;
        (this.solve = new Button("Solve")).addActionListener(this);
        (this.unsolve = new Button("Unsolve")).addActionListener(this);
        (this.reset = new Button("Reset")).addActionListener(this);
        (this.evaluate = new Button("Evaluate")).addActionListener(this);
        this.solns = new Label();
        this.format = new Choice();
        int i = 0;
        while (i < SuDokuUtils.labels.length) {
            this.format.add(SuDokuUtils.labels[i++]);
        }
        this.format.addItemListener(this);
        (this.copy = new Button("Copy")).addActionListener(this);
        (this.paste = new Button("Paste")).addActionListener(this);
        this.puzzleType = new Choice();
        i = 0;
        while (i < Grid.puzzleTypes.length) {
            this.puzzleType.add(Grid.puzzleTypes[i++]);
        }
        this.puzzleType.addItemListener(this);
        (this.resize = new Button("Resize")).addActionListener(this);
        this.across = new TextField(1);
        this.down = new TextField(1);
        (this.compose = new Button("Compose")).addActionListener(this);
        this.minFilledCells = new TextField(2);
        (this.interrupt = new Button("Break")).addActionListener(this);
        (this.singleSectorCandidates = new Checkbox("Single Sector Candidates", grid.getStrategy().useSingleSectorCandidates)).addItemListener(this);
        this.singleSectorCandidatesEliminations = new Label("0");
        (this.disjointSubsets = new Checkbox("Disjoint Subsets", grid.getStrategy().useDisjointSubsets)).addItemListener(this);
        this.disjointSubsetsEliminations = new Label("0");
        (this.xWings = new Checkbox("X-Wings", grid.getStrategy().useXWings)).addItemListener(this);
        this.xWingsEliminations = new Label("0");
        (this.swordfish = new Checkbox("Swordfish", grid.getStrategy().useSwordfish)).addItemListener(this);
        this.swordfishEliminations = new Label("0");
        (this.nishio = new Checkbox("Nishio", grid.getStrategy().useNishio)).addItemListener(this);
        this.nishioEliminations = new Label("0");
        (this.guess = new Checkbox("Guess", grid.getStrategy().useGuesses)).addItemListener(this);
        this.nGuesses = new Label("0");
        (this.reasoningArea = new TextArea()).setEditable(false);
        this.reasoningArea.addMouseListener(this);
        this.addComponent(this.solve, 0, 0, 3, 1, 1, 0);
        this.addComponent(this.unsolve, 4, 0, 3, 1, 1, 0);
        this.addComponent(this.reset, 8, 0, 3, 1, 1, 0);
        this.addComponent(this.evaluate, 0, 1, 3, 1, 1, 0);
        this.addComponent(new Label("Solutions"), 4, 1, 2, 1, 1, 0);
        this.addComponent(this.solns, 6, 1, 1, 1, 1, 0);
        this.addComponent(this.format, 8, 1, 3, 1, 1, 0);
        this.addComponent(this.copy, 0, 2, 3, 1, 1, 0);
        this.addComponent(this.paste, 4, 2, 3, 1, 1, 0);
        this.addComponent(this.puzzleType, 8, 2, 3, 1, 1, 0);
        this.addComponent(this.resize, 0, 3, 3, 1, 1, 0);
        this.addComponent(new Label("Across"), 4, 3, 2, 1, 1, 0);
        this.addComponent(this.across, 6, 3, 1, 1, 1, 0);
        this.addComponent(new Label("Down"), 8, 3, 2, 1, 1, 0);
        this.addComponent(this.down, 10, 3, 1, 1, 1, 0);
        this.addComponent(this.compose, 0, 4, 3, 1, 1, 0);
        this.addComponent(new Label("Filled Cells"), 4, 4, 2, 1, 1, 0);
        this.addComponent(this.minFilledCells, 6, 4, 1, 1, 1, 0);
        this.addComponent(this.interrupt, 8, 4, 3, 1, 1, 0);
        this.addComponent(new Label("Pattern:"), 0, 5, 2, 1, 1, 0);
        this.addComponent(this.singleSectorCandidates, 4, 5, 3, 1, 1, 0);
        this.addComponent(this.singleSectorCandidatesEliminations, 10, 5, 1, 1, 1, 0);
        this.addComponent(this.disjointSubsets, 4, 6, 3, 1, 1, 0);
        this.addComponent(this.disjointSubsetsEliminations, 10, 6, 1, 1, 1, 0);
        this.addComponent(this.xWings, 4, 7, 3, 1, 1, 0);
        this.addComponent(this.xWingsEliminations, 10, 7, 1, 1, 1, 0);
        this.addComponent(this.swordfish, 4, 8, 3, 1, 1, 0);
        this.addComponent(this.swordfishEliminations, 10, 8, 1, 1, 1, 0);
        this.addComponent(this.nishio, 4, 9, 3, 1, 1, 0);
        this.addComponent(this.nishioEliminations, 10, 9, 1, 1, 1, 0);
        this.addComponent(this.guess, 4, 10, 3, 1, 1, 0);
        this.addComponent(this.nGuesses, 10, 10, 1, 1, 1, 0);
        this.addComponent(this.reasoningArea, 0, 11, 11, 5, 1, 1);
        this.write();
    }
    
    public Dimension getBestSize() {
        return new Dimension(300, 500);
    }
    
    public void actionPerformed(final ActionEvent evt) {
        if (evt.getSource() == this.solve) {
            this.grid.solve();
            if (this.grid.getStrategy().explainsReasoning()) {
                this.reasoningArea.setText(null);
                this.reasoningArea.append("START\n");
                for (int i = 0; i < this.grid.getStrategy().getThreadLength(); ++i) {
                    this.reasoningArea.append(String.valueOf(1 + i) + ". " + this.grid.getStrategy().getReason(i));
                }
            }
            this.write();
        }
        else if (evt.getSource() == this.unsolve) {
            this.grid.unsolve();
        }
        else if (evt.getSource() == this.reset) {
            this.grid.reset();
            this.reasoningArea.setText(null);
            this.write();
        }
        else if (evt.getSource() == this.copy) {
            final StringBuffer sb = new StringBuffer();
            switch (Grid.defaultPuzzleType) {
                case 0: {
                    sb.append(this.grid.toString());
                    break;
                }
                case 1: {
                    sb.append(SuDokuUtils.libraryBookHeader(this.appClass.getName(), this.cellsInRow, this.boxesAcross, Grid.featuredGrades));
                    sb.append(this.grid.toString());
                    sb.append(SuDokuUtils.libraryBookFooter());
                    break;
                }
            }
            if (this.isApplet) {
                this.clipboard.show();
                this.clipboard.setText(sb.toString());
            }
            else {
                this.getToolkit().getSystemClipboard().setContents(new StringSelection(sb.toString()), this);
            }
        }
        else if (evt.getSource() == this.paste) {
            String pasteText = null;
            if (this.isApplet) {
                if (this.clipboard instanceof SuDokuClipboard) {
                    pasteText = this.clipboard.getText();
                }
            }
            else {
                final Transferable transferable = this.getToolkit().getSystemClipboard().getContents(this);
                if (transferable instanceof Transferable) {
                    try {
                        pasteText = (String)transferable.getTransferData(DataFlavor.stringFlavor);
                    }
                    catch (Exception ex) {}
                }
            }
            if (pasteText instanceof String) {
                try {
                    this.grid.paste(pasteText);
                    this.boxesAcross = this.grid.getBoxesAcross();
                    this.boxesDown = this.grid.getBoxesDown();
                    this.cellsInRow = this.boxesAcross * this.boxesDown;
                    this.write();
                }
                catch (Exception e) {
                    this.grid.reset();
                }
            }
        }
        else if (evt.getSource() == this.resize) {
            this.read();
            this.grid.setBoxes(this.boxesAcross, this.boxesDown);
            this.write();
        }
        else if (evt.getSource() == this.evaluate) {
            switch (this.grid.evaluate()) {
                case 0: {
                    this.solns.setText("None");
                    break;
                }
                case 1: {
                    this.solns.setText("Unique");
                    break;
                }
                case 2: {
                    this.solns.setText("Multiple");
                    break;
                }
                default: {
                    this.solns.setText("Error");
                    break;
                }
            }
        }
        else if (evt.getSource() == this.compose) {
            this.read();
            this.grid.startComposer(this.minFilledCellsValue);
            this.write();
        }
        else if (evt.getSource() == this.interrupt) {
            this.grid.stopComposer();
        }
    }
    
    public void mouseClicked(final MouseEvent evt) {
        int lastMove = 0;
        final String reasonText = this.reasoningArea.getText().substring(0, this.reasoningArea.getCaretPosition());
        String reason = "";
        final StringTokenizer st = new StringTokenizer(reasonText, "\n");
        while (st.hasMoreTokens()) {
            try {
                reason = st.nextToken();
                final int fullStopIndex;
                if ((fullStopIndex = reason.indexOf(46)) < 0) {
                    continue;
                }
                lastMove = Integer.parseInt(reason.substring(0, fullStopIndex));
            }
            catch (NumberFormatException ex) {}
        }
        this.grid.unsolve(lastMove);
    }
    
    public void mouseEntered(final MouseEvent evt) {
    }
    
    public void mouseExited(final MouseEvent evt) {
    }
    
    public void mousePressed(final MouseEvent evt) {
    }
    
    public void mouseReleased(final MouseEvent evt) {
    }
    
    public void itemStateChanged(final ItemEvent evt) {
        if (evt.getSource() == this.singleSectorCandidates) {
            this.grid.getStrategy().useSingleSectorCandidates = this.singleSectorCandidates.getState();
        }
        else if (evt.getSource() == this.disjointSubsets) {
            this.grid.getStrategy().useDisjointSubsets = this.disjointSubsets.getState();
        }
        else if (evt.getSource() == this.xWings) {
            this.grid.getStrategy().useXWings = this.xWings.getState();
        }
        else if (evt.getSource() == this.swordfish) {
            this.grid.getStrategy().useSwordfish = this.swordfish.getState();
        }
        else if (evt.getSource() == this.nishio) {
            this.grid.getStrategy().useNishio = this.nishio.getState();
        }
        else if (evt.getSource() == this.guess) {
            this.grid.getStrategy().useGuesses = this.guess.getState();
        }
        else if (evt.getSource() == this.format) {
            SuDokuUtils.defaultFormat = this.format.getSelectedIndex();
        }
        else if (evt.getSource() == this.puzzleType) {
            Grid.defaultPuzzleType = this.puzzleType.getSelectedIndex();
        }
    }
    
    void read() {
        try {
            this.boxesAcross = Integer.parseInt(this.across.getText());
        }
        catch (NumberFormatException ex) {}
        try {
            this.boxesDown = Integer.parseInt(this.down.getText());
        }
        catch (NumberFormatException ex2) {}
        try {
            this.minFilledCellsValue = Integer.parseInt(this.minFilledCells.getText());
        }
        catch (NumberFormatException ex3) {}
        this.cellsInRow = this.boxesAcross * this.boxesDown;
    }
    
    void write() {
        this.across.setText(Integer.toString(this.boxesAcross));
        this.down.setText(Integer.toString(this.boxesDown));
        this.minFilledCells.setText(Integer.toString(this.minFilledCellsValue));
        this.singleSectorCandidatesEliminations.setText(Integer.toString(this.grid.getStrategy().singleSectorCandidatesEliminations));
        this.disjointSubsetsEliminations.setText(Integer.toString(this.grid.getStrategy().disjointSubsetsEliminations));
        this.xWingsEliminations.setText(Integer.toString(this.grid.getStrategy().xWingsEliminations));
        this.swordfishEliminations.setText(Integer.toString(this.grid.getStrategy().swordfishEliminations));
        this.nishioEliminations.setText(Integer.toString(this.grid.getStrategy().nishioEliminations));
        this.nGuesses.setText(Integer.toString(this.grid.getStrategy().nGuesses));
    }
    
    public void lostOwnership(final Clipboard clipboard, final Transferable transferable) {
    }
}
