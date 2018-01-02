import java.awt.Event;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Button;

// 
// Decompiled by Procyon v0.5.30
// 

class ControlFramedArea extends FramedArea implements TimeThreadable
{
    Button scrambleButton;
    long startTime;
    TimeThread timeThread;
    boolean freeze;
    Label textLabel;
    Label timeLabel;
    Label moveLabel;
    
    public ControlFramedArea(final SlidePuzzle slidePuzzle, final String s) {
        super(slidePuzzle, s);
        this.freeze = true;
        this.scrambleButton = new Button(slidePuzzle.scrambleText);
        this.textLabel = new Label(slidePuzzle.imageStillLoadingText);
        this.timeLabel = new Label("0");
        this.moveLabel = new Label(new Integer(slidePuzzle.getCount()).toString());
        final Label label = new Label(slidePuzzle.timeText);
        final Label label2 = new Label(slidePuzzle.movesText);
        (this.timeThread = new TimeThread(this, 1000)).start();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        layout.setConstraints(this.scrambleButton, gridBagConstraints);
        this.add(this.scrambleButton);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0, 3, 0, 3);
        layout.setConstraints(label, gridBagConstraints);
        this.add(label);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.timeLabel, gridBagConstraints);
        this.add(this.timeLabel);
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(label2, gridBagConstraints);
        this.add(label2);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.moveLabel, gridBagConstraints);
        this.add(this.moveLabel);
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        layout.setConstraints(this.textLabel, gridBagConstraints);
        this.add(this.textLabel);
        this.validate();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.scrambleButton) {
            super.controller.scramble();
            return true;
        }
        return false;
    }
    
    protected void finalize() throws Throwable {
        this.timeThread.stopThread();
    }
    
    public void increaseMoves() {
        this.moveLabel.setText(new Integer(super.controller.getCount()).toString());
    }
    
    public void resetMoves() {
        this.timeLabel.setText("0");
        this.moveLabel.setText(new Integer(super.controller.getCount()).toString());
    }
    
    public void setFreeze(final boolean freeze) {
        if (!freeze) {
            this.setStartTime();
        }
        this.freeze = freeze;
    }
    
    public void setStartTime() {
        this.startTime = System.currentTimeMillis();
    }
    
    public void updateLabel(final String text) {
        this.textLabel.setText(text);
    }
    
    public void updateTime() {
        if (this.freeze) {
            return;
        }
        this.timeLabel.setText(new Long((System.currentTimeMillis() - this.startTime) / 1000L).toString());
    }
}
