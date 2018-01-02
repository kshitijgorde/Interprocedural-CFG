import javax.swing.event.ChangeEvent;
import javax.swing.Icon;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JApplet;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class MainPanel extends JPanel implements ChangeListener
{
    private int currentTab;
    private TabInput inputTab;
    private TabGridType gridTypeTab;
    private TabTile tileTab;
    private TabGenerate generateTab;
    private TabBoard boardTab;
    private TabPlay playTab;
    private TabSolve solveTab;
    private MyTab[] tabPanels;
    private JTabbedPane tabSet;
    private Puzzle puz;
    
    public MainPanel(final JApplet applet) {
        this.puz = new Puzzle();
        this.setPreferredSize(new Dimension(600, 500));
        this.inputTab = new TabInput(applet);
        this.gridTypeTab = new TabGridType();
        this.tileTab = new TabTile();
        this.generateTab = new TabGenerate();
        this.boardTab = new TabBoard();
        this.playTab = new TabPlay();
        this.solveTab = new TabSolve();
        this.tabSet = new JTabbedPane();
        this.tabPanels = new MyTab[] { this.inputTab, this.gridTypeTab, this.tileTab, this.generateTab, this.boardTab, this.playTab, this.solveTab };
        this.setLayout(new BorderLayout());
        this.add(this.tabSet);
        final JPanel panel = new JPanel();
        final JLabel label = new JLabel(" Written by Jaap Scherphuis, © 2004-2005");
        label.setToolTipText(" email: jaapsch@yahoo.com   website: geocities.com/jaapsch/puzzles ");
        panel.setLayout(new BorderLayout());
        panel.add(label, "East");
        this.add(panel, "South");
        final String[] array = { "Import", "Grid type", "Shapes", "Generate", "Board", "Place", "Solve" };
        final String[] array2 = { "Textual import/export of complete puzzle.", "Choose the type of grid", "Edit the tile shapes", "Generate tile sets", "Edit the board", "Place shapes on or remove them from the board.", "Let program solve the puzzle." };
        for (int i = 0; i < this.tabPanels.length; ++i) {
            this.tabSet.addTab(array[i], null, this.tabPanels[i], array2[i]);
        }
        this.currentTab = 1;
        this.tabSet.setSelectedIndex(this.currentTab);
        this.tabSet.addChangeListener(this);
        this.tabPanels[this.currentTab].setPuzzle(this.puz);
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        this.tabPanels[this.currentTab].exit();
        if (this.currentTab == 0) {
            final Puzzle puzzle = this.inputTab.getPuzzle();
            if (puzzle != null && puzzle != this.puz) {
                this.puz = puzzle;
            }
        }
        this.puz.validate();
        final int selectedIndex = this.tabSet.getSelectedIndex();
        this.tabPanels[selectedIndex].setPuzzle(this.puz);
        this.currentTab = selectedIndex;
    }
}
