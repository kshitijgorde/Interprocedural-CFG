// 
// Decompiled by Procyon v0.5.30
// 

package tmcm.xSortLab;

import java.awt.Event;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Label;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Panel;

class VisualSortPanel extends Panel
{
    static final String[] sortName;
    Choice sortMethodChoice;
    int currentSortMethod;
    Checkbox fastBox;
    Button goButton;
    Button stepButton;
    Button newButton;
    Label comparisons;
    Label copies;
    SortCanvas sorter;
    MessageCanvas comment1;
    MessageCanvas comment2;
    Panel messagePanel;
    Panel controlPanel;
    Panel statPanel;
    LogPanel log;
    
    VisualSortPanel(final LogPanel log) {
        this.currentSortMethod = 0;
        this.log = log;
        this.setBackground(Color.lightGray);
        this.setLayout(null);
        (this.messagePanel = new Panel()).setBackground(Color.lightGray);
        this.messagePanel.setLayout(new GridLayout(2, 1, 5, 5));
        this.add(this.messagePanel);
        (this.comment1 = new MessageCanvas("Click \"Go\" or \"Step\" to begin sorting.")).setBackground(Color.white);
        this.comment1.setForeground(Color.red);
        this.messagePanel.add(this.comment1);
        (this.comment2 = new MessageCanvas()).setBackground(Color.white);
        this.comment2.setForeground(Color.red);
        this.messagePanel.add(this.comment2);
        (this.controlPanel = new Panel()).setLayout(new GridLayout(5, 1, 3, 3));
        this.add(this.controlPanel);
        this.sortMethodChoice = new Choice();
        for (int i = 0; i < VisualSortPanel.sortName.length; ++i) {
            this.sortMethodChoice.addItem(VisualSortPanel.sortName[i]);
        }
        this.controlPanel.add(this.sortMethodChoice);
        this.fastBox = new Checkbox("Fast");
        final Panel panel = new Panel();
        panel.add(this.fastBox);
        this.controlPanel.add(panel);
        this.goButton = new Button("Go");
        this.controlPanel.add(this.goButton);
        this.stepButton = new Button("Step");
        this.controlPanel.add(this.stepButton);
        this.newButton = new Button("Start Again");
        this.controlPanel.add(this.newButton);
        (this.statPanel = new Panel()).setLayout(new GridLayout(4, 1, 5, 5));
        this.statPanel.setBackground(Color.white);
        this.add(this.statPanel);
        this.statPanel.add(new Label("  Comparisons:"));
        (this.comparisons = new Label("0", 1)).setForeground(Color.red);
        this.statPanel.add(this.comparisons);
        this.statPanel.add(new Label("  Copies:"));
        (this.copies = new Label("0", 1)).setForeground(Color.red);
        this.statPanel.add(this.copies);
        this.add(this.sorter = new SortCanvas(this, this.comparisons, this.copies, this.comment1, this.comment2));
    }
    
    public void reshape(final int n, final int n2, int n3, int n4) {
        super.reshape(n, n2, n3, n4);
        if (n3 < 300) {
            n3 = 300;
        }
        if (n4 < 200) {
            n4 = 200;
        }
        this.messagePanel.reshape(0, n4 - 45, n3, 45);
        this.sorter.reshape(0, 0, n3 - 125, n4 - 50);
        final int n5 = (n4 - 55) * 5 / 9;
        this.controlPanel.reshape(n3 - 120, 0, 120, n5);
        this.statPanel.reshape(n3 - 120, n5 + 5, 120, n4 - 55 - n5);
    }
    
    void aboutToShow() {
        this.goButton.setLabel("Go");
        this.stepButton.enable();
        this.sorter.newSort(this.currentSortMethod + 1);
    }
    
    void aboutToHide() {
        this.sorter.stopRunning();
    }
    
    void doneRunning(final int n, final int n2, final int n3) {
        this.goButton.enable();
        this.goButton.setLabel("Go");
        this.stepButton.disable();
        this.log.addLine(VisualSortPanel.sortName[n - 1] + " applied to 1 array containing 16 items:");
        this.log.addLine("   Number of comparisons: " + n2);
        this.log.addLine("   Number of copies: " + n3);
        this.log.addEoln();
    }
    
    void runnerStopped() {
        this.goButton.enable();
        this.goButton.setLabel("Go");
        this.stepButton.enable();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.sortMethodChoice) {
            final int selectedIndex = this.sortMethodChoice.getSelectedIndex();
            if (selectedIndex == this.currentSortMethod) {
                return true;
            }
            this.currentSortMethod = selectedIndex;
            this.sorter.newSort(this.currentSortMethod + 1);
            return true;
        }
        else {
            if (event.target == this.fastBox) {
                this.sorter.setFast((boolean)o);
                return true;
            }
            if (event.target == this.goButton) {
                synchronized (this.sorter) {
                    if (this.sorter.getState() == 4) {
                        this.goButton.disable();
                        this.sorter.stopRunning();
                    }
                    else {
                        this.goButton.setLabel("Stop");
                        this.stepButton.disable();
                        this.sorter.startRunning();
                    }
                }
                return true;
            }
            if (event.target == this.stepButton) {
                this.sorter.doStep();
                synchronized (this.sorter) {
                    if (this.sorter.getState() == 3) {
                        this.stepButton.disable();
                        this.goButton.disable();
                    }
                }
                return true;
            }
            if (event.target == this.newButton) {
                this.sorter.newSort(this.currentSortMethod + 1);
                this.goButton.setLabel("Go");
                this.stepButton.enable();
                return true;
            }
            return super.action(event, o);
        }
    }
    
    static {
        sortName = new String[] { "Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "QuickSort" };
    }
}
