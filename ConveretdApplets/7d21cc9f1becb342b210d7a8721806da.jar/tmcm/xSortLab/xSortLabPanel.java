// 
// Decompiled by Procyon v0.5.30
// 

package tmcm.xSortLab;

import java.awt.Container;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Panel;

public class xSortLabPanel extends Panel
{
    Choice panelChoice;
    LogPanel log;
    VisualSortPanel visual;
    TimedSortPanel timed;
    int currentPanel;
    Panel mainPanel;
    CardLayout mainLayout;
    
    public xSortLabPanel() {
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout(5, 5));
        (this.panelChoice = new Choice()).addItem("Visual Sort");
        this.panelChoice.addItem("Timed Sort");
        this.panelChoice.addItem("Log");
        this.add("North", this.panelChoice);
        this.mainPanel = new Panel();
        this.mainLayout = new CardLayout();
        this.mainPanel.setLayout(this.mainLayout);
        this.log = new LogPanel();
        this.visual = new VisualSortPanel(this.log);
        this.timed = new TimedSortPanel(this.log);
        this.mainPanel.add("visual", this.visual);
        this.mainPanel.add("timed", this.timed);
        this.mainPanel.add("log", this.log);
        this.currentPanel = 0;
        this.add("Center", this.mainPanel);
    }
    
    public void start() {
        if (this.currentPanel == 0) {
            this.visual.sorter.doAppletStart();
        }
        else if (this.currentPanel == 1) {
            this.timed.sorter.doAppletStart();
        }
    }
    
    public void stop() {
        if (this.currentPanel == 0) {
            this.visual.sorter.doAppletStop();
        }
        else if (this.currentPanel == 1) {
            this.timed.sorter.doAppletStop();
        }
    }
    
    public void destroy() {
        if (this.visual.sorter.runner != null && this.visual.sorter.runner.isAlive()) {
            this.visual.sorter.runner.stop();
        }
        if (this.timed.sorter.runner != null && this.timed.sorter.runner.isAlive()) {
            this.timed.sorter.runner.stop();
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.panelChoice) {
            this.doPanelChoice();
            return true;
        }
        return super.action(event, o);
    }
    
    void doPanelChoice() {
        final int selectedIndex = this.panelChoice.getSelectedIndex();
        if (selectedIndex == this.currentPanel) {
            return;
        }
        if (this.currentPanel == 0) {
            this.visual.aboutToHide();
        }
        else if (this.currentPanel == 1) {
            this.timed.aboutToHide();
        }
        else if (this.currentPanel == 2) {
            this.log.aboutToHide();
        }
        if (selectedIndex == 0) {
            this.visual.aboutToShow();
            this.mainLayout.show(this.mainPanel, "visual");
        }
        else if (selectedIndex == 1) {
            this.timed.aboutToShow();
            this.mainLayout.show(this.mainPanel, "timed");
        }
        else {
            this.log.aboutToShow();
            this.mainLayout.show(this.mainPanel, "log");
            this.log.shown();
        }
        this.currentPanel = selectedIndex;
    }
}
