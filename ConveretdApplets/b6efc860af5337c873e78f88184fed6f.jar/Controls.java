import java.awt.event.ItemEvent;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Button;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class Controls extends Panel implements ActionListener, ItemListener
{
    private static final int NO_OF_CONTROLS = 6;
    private static final int RADIO_WIDTH = 185;
    private static final int SPEED_WIDTH = 80;
    private static final int BUTTON_WIDTH = 60;
    private static final int CONTROLS_HEIGHT = 20;
    private int gridx;
    private int[] controlClicks;
    private boolean aborted;
    private boolean singleSteps;
    private boolean paused;
    private boolean resumeClicked;
    private boolean stepClicked;
    private boolean abortClicked;
    private boolean running;
    private boolean showingHelp;
    private Button pause;
    private Button resume;
    private Button step;
    private Button abort;
    private CheckboxGroup group;
    private Checkbox singleStep;
    private Checkbox continuous;
    private String stepRunHelp;
    private String stepOffHelp;
    private String pauseResumeOffHelp;
    private String notRunningHelp;
    private SizedComponent stepPanel;
    private SizedComponent pausePanel;
    private SizedComponent resumePanel;
    private SizedComponent abortPanel;
    private SizedComponentPair stepRunPanel;
    private SpeedControl speedControl;
    private String speedHelp;
    private SizedComponent speedPanel;
    private GridBagLayout gridBag;
    private GridBagConstraints constraints;
    
    public Controls() {
        this.gridx = 0;
        this.controlClicks = new int[6];
        this.aborted = false;
        this.singleSteps = false;
        this.paused = false;
        this.resumeClicked = false;
        this.stepClicked = false;
        this.abortClicked = false;
        this.running = false;
        this.showingHelp = false;
        this.group = new CheckboxGroup();
        this.singleStep = new Checkbox("Single Step", this.group, false);
        this.continuous = new Checkbox("Continuous", this.group, true);
        this.stepRunHelp = "These buttons determine whether the animation runs continously or in single steps.  In the single step mode, the Step button to the left must be clicked to move to the next step.";
        this.stepOffHelp = "This button is inactive because the Continuous mode is selected.";
        this.pauseResumeOffHelp = "This button is inactive because the Single Step mode is selected.";
        this.notRunningHelp = "This button is inactive because no animation is running.";
        this.stepRunPanel = new SizedComponentPair(this.singleStep, this.continuous, 185, 20, this.stepRunHelp, "South");
        this.speedControl = new SpeedControl();
        this.speedHelp = "This scrollbar controls the speed of the animation, move it left for slower, right for faster.";
        this.speedPanel = new SizedComponent(this.speedControl, 80, 20, this.speedHelp, "South");
        this.gridBag = new GridBagLayout();
        this.constraints = new GridBagConstraints();
        this.setBackground(Color.lightGray);
        this.setLayout(this.gridBag);
        this.constraints.weightx = 1.0;
        this.constraints.gridy = 0;
        (this.step = new Button("Step")).addActionListener(this);
        this.stepPanel = this.addButton(this.step, this.stepOffHelp);
        this.constraints.gridx = this.gridx++;
        this.gridBag.setConstraints(this.stepRunPanel, this.constraints);
        this.add(this.stepRunPanel);
        (this.pause = new Button("Pause")).addActionListener(this);
        this.pausePanel = this.addButton(this.pause, this.notRunningHelp);
        (this.resume = new Button("Resume")).addActionListener(this);
        this.resumePanel = this.addButton(this.resume, this.notRunningHelp);
        this.speedControl.setBackground(Color.gray);
        this.constraints.gridx = this.gridx++;
        this.gridBag.setConstraints(this.speedPanel, this.constraints);
        this.add(this.speedPanel);
        (this.abort = new Button("Abort")).addActionListener(this);
        this.abortPanel = this.addButton(this.abort, this.notRunningHelp);
        this.step.setEnabled(false);
        this.pause.setEnabled(false);
        this.resume.setEnabled(false);
        this.abort.setEnabled(false);
        this.singleStep.addItemListener(this);
        this.continuous.addItemListener(this);
        for (int control = 0; control < 6; ++control) {
            this.controlClicks[control] = 0;
        }
    }
    
    public void abort() {
        this.aborted = true;
    }
    
    public boolean abortInitiated() {
        return this.aborted || this.abortClicked;
    }
    
    public void actionPerformed(final ActionEvent event) {
        if (event.getActionCommand().equals(this.step.getLabel())) {
            this.clickStep();
            final int[] controlClicks = this.controlClicks;
            final int n = 0;
            ++controlClicks[n];
        }
        if (event.getActionCommand().equals(this.pause.getLabel())) {
            this.pause.setEnabled(false);
            this.resume.setEnabled(true);
            this.paused = true;
            this.changeMessages();
            final int[] controlClicks2 = this.controlClicks;
            final int n2 = 3;
            ++controlClicks2[n2];
        }
        else if (event.getActionCommand().equals(this.resume.getLabel())) {
            this.resume.setEnabled(false);
            this.clickResume();
            this.changeMessages();
            final int[] controlClicks3 = this.controlClicks;
            final int n3 = 4;
            ++controlClicks3[n3];
        }
        else if (event.getActionCommand().equals(this.abort.getLabel())) {
            this.abort.setEnabled(false);
            Screen.prompt.displayPrompt("");
            if (Screen.panel.isInteractive()) {
                this.aborted = true;
            }
            else {
                this.abortClicked = true;
                if (this.singleSteps) {
                    this.clickStep();
                }
                else if (this.paused) {
                    this.resume.setEnabled(false);
                    this.clickResume();
                }
            }
            final int[] controlClicks4 = this.controlClicks;
            final int n4 = 5;
            ++controlClicks4[n4];
        }
    }
    
    private SizedComponent addButton(final Button button, final String helpMessage) {
        final SizedComponent sizedButton = new SizedComponent(button, 60, 20, helpMessage, "South");
        this.constraints.gridx = this.gridx++;
        this.gridBag.setConstraints(sizedButton, this.constraints);
        this.add(sizedButton);
        return sizedButton;
    }
    
    public void changeMessages() {
        if (this.singleSteps) {
            if (this.running) {
                if (Screen.panel.isInteractive()) {
                    this.stepPanel.changeMessage("This button is inactive because the I'll Try mode is selected.");
                }
                else {
                    this.stepPanel.changeMessage("Takes you the the next step of the animation.");
                }
            }
            else {
                this.stepPanel.changeMessage(this.notRunningHelp);
            }
            this.pausePanel.changeMessage(this.pauseResumeOffHelp);
            this.resumePanel.changeMessage(this.pauseResumeOffHelp);
        }
        else {
            this.stepPanel.changeMessage(this.stepOffHelp);
            if (this.running) {
                if (this.paused) {
                    this.pausePanel.changeMessage("This button is inactive because the animation is already paused.  Click the Resume button to the right, to resume the animation.");
                    this.resumePanel.changeMessage("Resumes the animation.");
                }
                else {
                    this.pausePanel.changeMessage("Pauses the animation. The Resume button to the right resumes it.");
                    this.resumePanel.changeMessage("This button is inactive because the animation is not paused.  Click the Pause button to the left, to pause the animation.");
                }
            }
            else {
                this.pausePanel.changeMessage(this.notRunningHelp);
                this.resumePanel.changeMessage(this.notRunningHelp);
            }
        }
        if (this.running) {
            this.abortPanel.changeMessage("Terminates the animation.");
            this.stepRunPanel.changeMessage("These buttons are inactive while an animation is running.");
        }
        else {
            this.abortPanel.changeMessage(this.notRunningHelp);
            this.stepRunPanel.changeMessage(this.stepRunHelp);
        }
    }
    
    private synchronized void clickResume() {
        while (this.resumeClicked) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
        this.paused = false;
        this.resumeClicked = true;
        this.notify();
        this.pause.setEnabled(true);
    }
    
    private synchronized void clickStep() {
        while (this.stepClicked) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
        this.stepClicked = true;
        this.notify();
    }
    
    public String getLog() {
        String log = "C:";
        for (int control = 0; control < 6; ++control) {
            log = String.valueOf(log) + this.controlClicks[control] + "_";
        }
        return log;
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(546, 24);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(546, 24);
    }
    
    public int getSpeed() {
        return this.speedControl.getSpeed();
    }
    
    public boolean isAborted() {
        return this.aborted;
    }
    
    public void itemStateChanged(final ItemEvent event) {
        if (((Checkbox)event.getItemSelectable()).getLabel().equals(this.singleStep.getLabel())) {
            this.singleSteps = true;
            this.changeMessages();
            final int[] controlClicks = this.controlClicks;
            final int n = 1;
            ++controlClicks[n];
        }
        else if (((Checkbox)event.getItemSelectable()).getLabel().equals(this.continuous.getLabel())) {
            this.singleSteps = false;
            this.changeMessages();
            final int[] controlClicks2 = this.controlClicks;
            final int n2 = 2;
            ++controlClicks2[n2];
        }
    }
    
    public synchronized void nextStep(final boolean counts, final boolean doStep) {
        if (this.aborted) {
            return;
        }
        if (counts && !Screen.panel.isInteractive()) {
            Screen.progress.oneMoreShown(Screen.thread.button());
        }
        if (!doStep || Screen.panel.isInteractive()) {
            return;
        }
        if (this.paused) {
            while (!this.resumeClicked) {
                try {
                    this.wait();
                }
                catch (InterruptedException ex) {}
            }
            this.resumeClicked = false;
            this.notify();
        }
        if (this.singleSteps) {
            this.step.setEnabled(true);
            Screen.canvas.repaint();
            Screen.prompt.displayPrompt("Click Step For Next Step");
            while (!this.stepClicked) {
                try {
                    this.wait();
                }
                catch (InterruptedException ex2) {}
            }
            this.stepClicked = false;
            this.notify();
            this.step.setEnabled(false);
            Screen.prompt.displayPrompt("");
        }
        if (this.abortClicked) {
            this.aborted = true;
        }
    }
    
    public void start() {
        this.running = true;
        this.changeMessages();
        if (!Screen.panel.isInteractive() && !this.singleSteps) {
            this.pause.setEnabled(true);
        }
        this.singleStep.setEnabled(false);
        this.continuous.setEnabled(false);
        this.abort.setEnabled(true);
    }
    
    public void stop() {
        this.running = false;
        this.changeMessages();
        this.aborted = false;
        this.abortClicked = false;
        this.step.setEnabled(false);
        this.pause.setEnabled(false);
        this.resume.setEnabled(false);
        this.abort.setEnabled(false);
        this.singleStep.setEnabled(true);
        this.continuous.setEnabled(true);
    }
}
