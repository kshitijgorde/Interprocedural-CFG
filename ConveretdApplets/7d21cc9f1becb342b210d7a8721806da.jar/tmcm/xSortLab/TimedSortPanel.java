// 
// Decompiled by Procyon v0.5.30
// 

package tmcm.xSortLab;

import java.awt.Event;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Panel;

class TimedSortPanel extends Panel
{
    static final String[] sortName;
    Choice methodChoice;
    Button goButton;
    TextField arrayCountInput;
    TextField arraySizeInput;
    boolean running;
    TimedSort sorter;
    
    TimedSortPanel(final LogPanel logPanel) {
        this.setLayout(new BorderLayout(5, 5));
        this.add("Center", this.sorter = new TimedSort(this, logPanel));
        final Panel panel = new Panel();
        this.methodChoice = new Choice();
        for (int i = 0; i < TimedSortPanel.sortName.length; ++i) {
            this.methodChoice.addItem(TimedSortPanel.sortName[i]);
        }
        panel.add(this.methodChoice);
        panel.add(this.goButton = new Button("Start Sorting"));
        this.add("South", panel);
        final Panel panel2 = new Panel();
        panel2.add(new Label("Array size:"));
        panel2.add(this.arraySizeInput = new TextField("1000", 6));
        panel2.add(new Label("    Number of arrays:"));
        panel2.add(this.arrayCountInput = new TextField("1", 6));
        this.add("North", panel2);
    }
    
    void aboutToShow() {
        this.sorter.sortMethod = -1;
        this.goButton.setLabel("Start Sorting");
        this.goButton.enable();
        this.arraySizeInput.requestFocus();
        this.running = false;
    }
    
    void aboutToHide() {
        if (this.sorter.getState() == 2) {
            this.sorter.setState(3);
        }
    }
    
    void doneRunning() {
        this.goButton.setLabel("Start Sorting");
        this.goButton.enable();
        this.arraySizeInput.requestFocus();
        this.running = false;
    }
    
    void readyToStart() {
        this.goButton.enable();
    }
    
    void startSorter() {
        final int selectedIndex = this.methodChoice.getSelectedIndex();
        final String text = this.arraySizeInput.getText();
        if (text == null || text.trim().length() == 0) {
            this.sorter.setError("Please enter an array size!");
            this.arraySizeInput.requestFocus();
            return;
        }
        int int1;
        try {
            int1 = Integer.parseInt(text);
        }
        catch (NumberFormatException ex) {
            this.sorter.setError("The array size must be an integer!");
            this.arraySizeInput.requestFocus();
            return;
        }
        if (int1 <= 0) {
            if (int1 == 0) {
                this.sorter.setError("The array size can't be zero!");
            }
            else {
                this.sorter.setError("The array size can't be negative!");
            }
            this.arraySizeInput.requestFocus();
            return;
        }
        final String text2 = this.arrayCountInput.getText();
        int int2;
        if (text2 == null || text2.trim().length() == 0) {
            int2 = 1;
        }
        else {
            try {
                int2 = Integer.parseInt(text2);
            }
            catch (NumberFormatException ex2) {
                this.sorter.setError("The number of arrays must be an integer!");
                this.arrayCountInput.requestFocus();
                return;
            }
            if (int2 <= 0) {
                if (int2 == 0) {
                    this.sorter.setError("The number of arrays can't be zero!");
                }
                else {
                    this.sorter.setError("The number of arrays can't be negative!");
                }
                this.arrayCountInput.requestFocus();
                return;
            }
        }
        if (int1 > 10000000 || int2 > 10000000 || int1 * int2 > 10000000L) {
            this.sorter.setError("No more than ten million items, total, please!");
            this.arraySizeInput.requestFocus();
            return;
        }
        this.running = true;
        this.goButton.disable();
        this.goButton.setLabel("Abort");
        this.sorter.start(selectedIndex, int1, int2);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.goButton) {
            if (this.running) {
                this.goButton.disable();
                this.sorter.setState(3);
            }
            else {
                this.startSorter();
            }
            return true;
        }
        return super.action(event, o);
    }
    
    static {
        sortName = new String[] { "Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "QuickSort" };
    }
}
