import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.AbstractButton;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class TabSolve extends MyTab implements ActionListener, ChangeListener
{
    private GridEditPanel mainView;
    private JPanel controlPanel;
    private JButton solveButton;
    private JSlider slider;
    private ButtonGroup showButtons;
    private JRadioButton[] rb;
    private JLabel sliderMaxLabel;
    private Timer progressTimer;
    
    public TabSolve() {
        this.setLayout(new BorderLayout());
        (this.mainView = new GridEditPanel(false, false)).setForeground(Color.YELLOW);
        this.add(this.mainView);
        (this.solveButton = new JButton("Solve")).setEnabled(false);
        this.solveButton.addActionListener(this);
        final JPanel panel = new JPanel();
        this.rb = new JRadioButton[2];
        panel.setLayout(new BoxLayout(panel, 1));
        final String[] array = { "Show progress", "Show solution" };
        this.showButtons = new ButtonGroup();
        for (int i = 0; i < 2; ++i) {
            this.rb[i] = new JRadioButton(array[i]);
            this.showButtons.add(this.rb[i]);
            this.rb[i].addActionListener(this);
            if (i == 1) {
                this.rb[i].setSelected(true);
            }
            panel.add(this.rb[i]);
            this.rb[i].setEnabled(false);
        }
        this.slider = new JSlider(0, 0);
        this.sliderMaxLabel = new JLabel();
        this.slider.setMinimum(1);
        this.slider.setMinimum(1);
        this.slider.setPaintTicks(true);
        this.slider.setPaintLabels(false);
        this.slider.setMajorTickSpacing(5);
        this.slider.setMinorTickSpacing(1);
        this.slider.setSnapToTicks(false);
        this.slider.addChangeListener(this);
        this.slider.setEnabled(false);
        this.add(this.controlPanel = new JPanel(), "South");
        this.controlPanel.add(this.solveButton);
        this.controlPanel.add(panel);
        this.controlPanel.add(this.slider);
        this.controlPanel.add(this.sliderMaxLabel);
    }
    
    public Puzzle getPuzzle() {
        return this.puz;
    }
    
    protected void init() {
        if (this.puz.getNumSolutions() == 0 || this.puz.isChanged()) {
            this.puz.prepareSolve();
        }
        this.mainView.setGridType(this.puz.getGridType());
        this.mainView.setBoard(this.puz.getBoard());
        this.mainView.setNumColours(this.puz.getNumPieces());
        this.puz.setActionListener(this);
        this.updateControls();
        this.updateMain();
    }
    
    protected void exit() {
        if (this.puz.isRunning()) {
            this.puz.stopSolve();
        }
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        if (changeEvent.getSource() == this.slider) {
            if (!this.slider.getValueIsAdjusting() && this.rb[1].isSelected()) {
                this.updateMain();
            }
            this.updateLabel();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.solveButton) {
            if (this.puz.isRunning()) {
                this.puz.stopSolve();
                this.updateControls();
                this.updateMain();
            }
            else {
                new Thread(this.puz).start();
                (this.progressTimer = new Timer(700, this)).setInitialDelay(100);
                this.progressTimer.start();
            }
        }
        else if (actionEvent.getSource() == this.puz) {
            this.updateControls();
            if (actionEvent.getID() == 1) {
                this.updateMain();
            }
        }
        else if (actionEvent.getSource() == this.progressTimer) {
            this.updateMain();
        }
        else {
            this.updateControls();
            this.updateMain();
        }
    }
    
    private void updateControls() {
        this.solveButton.setText(this.puz.isRunning() ? "Abort" : "Solve");
        this.solveButton.setEnabled(true);
        final int numSolutions = this.puz.getNumSolutions();
        this.slider.removeChangeListener(this);
        if (numSolutions == 0) {
            this.rb[0].setSelected(true);
            this.rb[1].setEnabled(false);
            this.slider.setEnabled(false);
        }
        else if (!this.puz.isRunning()) {
            this.rb[1].setSelected(true);
            this.rb[0].setEnabled(false);
            this.slider.setEnabled(numSolutions > 1);
        }
        else {
            this.rb[0].setEnabled(true);
            this.rb[1].setEnabled(true);
            this.slider.setEnabled(numSolutions > 1);
        }
        if (numSolutions == 0) {
            this.slider.setMinimum(0);
            this.slider.setMaximum(0);
            this.slider.setValue(0);
        }
        else {
            this.slider.setMinimum(1);
            this.slider.setMaximum(numSolutions);
            if (numSolutions == 1) {
                this.slider.setValue(1);
                this.slider.setMinorTickSpacing(1);
                this.slider.setMajorTickSpacing(1);
            }
            else if (numSolutions == 10) {
                this.slider.setMajorTickSpacing(2);
                this.slider.setMinorTickSpacing(1);
            }
            else if (numSolutions == 20) {
                this.slider.setMajorTickSpacing(5);
                this.slider.setMinorTickSpacing(1);
            }
            else if (numSolutions == 50) {
                this.slider.setMajorTickSpacing(10);
                this.slider.setMinorTickSpacing(2);
            }
            else if (numSolutions == 100) {
                this.slider.setMajorTickSpacing(20);
                this.slider.setMinorTickSpacing(5);
            }
            else if (numSolutions == 200) {
                this.slider.setMajorTickSpacing(50);
                this.slider.setMinorTickSpacing(10);
            }
            else if (numSolutions == 500) {
                this.slider.setMajorTickSpacing(100);
                this.slider.setMinorTickSpacing(20);
            }
            else if (numSolutions == 1000) {
                this.slider.setMajorTickSpacing(200);
                this.slider.setMinorTickSpacing(50);
            }
            else if (numSolutions == 2000) {
                this.slider.setMajorTickSpacing(500);
                this.slider.setMinorTickSpacing(100);
            }
            else if (numSolutions == 5000) {
                this.slider.setMajorTickSpacing(1000);
                this.slider.setMinorTickSpacing(200);
            }
        }
        this.slider.addChangeListener(this);
        this.updateLabel();
    }
    
    private void updateLabel() {
        this.sliderMaxLabel.setText(this.slider.getValue() + "/" + this.puz.getNumSolutions());
    }
    
    private void updateMain() {
        if (this.progressTimer != null && !this.puz.isRunning()) {
            this.progressTimer.stop();
            this.progressTimer = null;
        }
        this.mainView.setBoard(this.puz.getSolution((this.rb[0].isSelected() || this.puz.getNumSolutions() == 0) ? 0 : this.slider.getValue()));
    }
}
