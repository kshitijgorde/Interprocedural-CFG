import java.awt.event.ItemEvent;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Button;
import java.util.Random;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class ScreenPanel extends Panel implements ActionListener, ItemListener
{
    protected static final int RADIO_HEIGHT = 40;
    private static final int COMPUTER = 1;
    private static final int USER = 2;
    protected Random random;
    protected boolean[] shownOrTried;
    protected boolean running;
    protected SizedComponent[] buttonPanels;
    protected String[] showMessages;
    protected String[] tryMessages;
    protected String runningMessage;
    private Button[] buttons;
    private int who;
    private int where;
    private int first;
    private int numberOfButtons;
    private int showButtonNo;
    private int tryButtonNo;
    private int randomButtonNo;
    private int pickButtonNo;
    private int totalButtons;
    private int[] buttonClicks;
    private CheckboxGroup Group1;
    private Checkbox showMe;
    private Checkbox illTry;
    private String showTryHelp;
    private SizedComponentPair showTryGroup;
    private CheckboxGroup Group2;
    private Checkbox randomPick;
    private Checkbox illPick;
    private String randomPickHelp;
    private SizedComponentPair pickGroup;
    private int gridy;
    private GridBagLayout gridBag;
    private GridBagConstraints constraints;
    
    ScreenPanel() {
        this.random = new Random();
        this.running = false;
        this.runningMessage = "This button is inactive while an animation is running.";
        this.who = 1;
        this.where = 1;
        this.Group1 = new CheckboxGroup();
        this.showMe = new Checkbox("Show Me", this.Group1, true);
        this.illTry = new Checkbox("I'll Try", this.Group1, false);
        this.showTryHelp = "In the Show Me mode, you will be shown the correct solution.  In the I'll Try mode you will be asked to provide the correct responses.";
        this.showTryGroup = new SizedComponentPair(this.showMe, this.illTry, 72, 40, this.showTryHelp, "East");
        this.Group2 = new CheckboxGroup();
        this.randomPick = new Checkbox("Random", this.Group2, true);
        this.illPick = new Checkbox("I'll Pick", this.Group2, false);
        this.gridy = 0;
        this.gridBag = new GridBagLayout();
        this.constraints = new GridBagConstraints();
    }
    
    public void actionPerformed(final ActionEvent event) {
        for (int number = 0; number < this.numberOfButtons; ++number) {
            if (event.getActionCommand().equals(this.buttons[number].getLabel())) {
                if (number == 0 && this.first == 1) {
                    for (int button = 0; button < this.numberOfButtons; ++button) {
                        this.shownOrTried[button] = false;
                    }
                }
                else {
                    this.shownOrTried[number] = true;
                }
                Screen.thread.buttonClicked(number);
                final int[] buttonClicks = this.buttonClicks;
                final int n = number;
                ++buttonClicks[n];
            }
        }
    }
    
    private SizedComponent addButton(final Button button, final String helpMessage) {
        final SizedComponent sizedButton = new SizedComponent(button, 72, 24, helpMessage, "East");
        this.constraints.gridy = this.gridy++;
        this.gridBag.setConstraints(sizedButton, this.constraints);
        this.add(sizedButton);
        return sizedButton;
    }
    
    protected void addPanel(final Panel panel) {
        this.constraints.gridy = this.gridy++;
        this.gridBag.setConstraints(panel, this.constraints);
        this.add(panel);
    }
    
    public abstract void buttonClicked(final int p0);
    
    protected void changeMessages() {
        for (int button = 0; button < this.numberOfButtons; ++button) {
            if (this.running) {
                this.buttonPanels[button].changeMessage(this.runningMessage);
            }
            else if (this.isInteractive()) {
                if (this.shownOrTried[button]) {
                    this.buttonPanels[button].changeMessage("This button is inactive because the I'll Try mode is selected and this operation has already been shown or tried.  To reactivate it, switch to the Show Me mode or make a new data set.");
                }
                else {
                    this.buttonPanels[button].changeMessage(this.tryMessages[button]);
                }
            }
            else {
                this.buttonPanels[button].changeMessage(this.showMessages[button]);
            }
        }
        if (this.running) {
            this.showTryGroup.changeMessage(this.runningMessage);
        }
        else {
            this.showTryGroup.changeMessage(this.showTryHelp);
        }
        if (this.pickGroup != null) {
            if (this.running) {
                this.pickGroup.changeMessage(this.runningMessage);
            }
            else {
                this.pickGroup.changeMessage(this.randomPickHelp);
            }
        }
        this.changeMessagesSpecials();
    }
    
    public void changeMessagesSpecials() {
    }
    
    public void disableButtons() {
        this.running = true;
        this.changeMessages();
        for (int number = 0; number < this.numberOfButtons; ++number) {
            this.buttons[number].setEnabled(false);
        }
        this.illTry.setEnabled(false);
        this.showMe.setEnabled(false);
        this.illPick.setEnabled(false);
        this.randomPick.setEnabled(false);
        this.disableSpecials();
    }
    
    public void disableSpecials() {
    }
    
    public void enableButtons() {
        this.running = false;
        this.changeMessages();
        for (int number = 0; number < this.numberOfButtons; ++number) {
            if (this.who == 2 && this.shownOrTried[number] && number != 0 && this.first != 0) {
                this.buttons[number].setEnabled(false);
            }
            else {
                this.buttons[number].setEnabled(true);
            }
        }
        this.illTry.setEnabled(true);
        this.showMe.setEnabled(true);
        this.illPick.setEnabled(true);
        this.randomPick.setEnabled(true);
        this.enableSpecials();
    }
    
    public void enableSpecials() {
    }
    
    public String getLog() {
        String log = "B:";
        for (int button = 0; button < this.totalButtons; ++button) {
            log = String.valueOf(log) + this.buttonClicks[button] + "_";
        }
        return log;
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(72, 270);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(72, 270);
    }
    
    public int getRandom(final int lower, final int upper) {
        return Math.abs(this.random.nextInt() % (upper - lower)) + lower;
    }
    
    public void init(final String[] buttonNames, final String[] showMessages, final String[] tryMessages, final int first, final String randomOrPick, final boolean pickOnly, final Color backgroundColor) {
        (Screen.panel = this).setLayout(this.gridBag);
        this.setBackground(backgroundColor);
        this.first = first;
        this.showMessages = showMessages;
        this.tryMessages = tryMessages;
        this.randomPickHelp = randomOrPick;
        this.numberOfButtons = buttonNames.length;
        this.totalButtons = this.numberOfButtons;
        this.buttons = new Button[this.numberOfButtons];
        this.buttonPanels = new SizedComponent[this.numberOfButtons];
        this.shownOrTried = new boolean[this.numberOfButtons];
        for (int button = 0; button < this.numberOfButtons; ++button) {
            this.shownOrTried[button] = false;
        }
        this.constraints.weighty = 1.0;
        if (randomOrPick != null || pickOnly) {
            this.constraints.gridy = this.gridy++;
        }
        for (int number = 0; number < this.numberOfButtons; ++number) {
            (this.buttons[number] = new Button(buttonNames[number])).addActionListener(this);
            this.buttonPanels[number] = this.addButton(this.buttons[number], showMessages[number]);
        }
        this.addPanel(this.showTryGroup);
        this.showButtonNo = this.totalButtons++;
        this.tryButtonNo = this.totalButtons++;
        this.showMe.addItemListener(this);
        this.illTry.addItemListener(this);
        if (randomOrPick != null) {
            this.addPanel(this.pickGroup = new SizedComponentPair(this.randomPick, this.illPick, 72, 40, randomOrPick, "East"));
            this.randomButtonNo = this.totalButtons++;
            this.pickButtonNo = this.totalButtons++;
            this.randomPick.addItemListener(this);
            this.illPick.addItemListener(this);
        }
        this.buttonClicks = new int[this.totalButtons];
        for (int button2 = 0; button2 < this.totalButtons; ++button2) {
            this.buttonClicks[button2] = 0;
        }
    }
    
    public boolean isInteractive() {
        return this.who == 2;
    }
    
    public void itemStateChanged(final ItemEvent event) {
        if (((Checkbox)event.getItemSelectable()).getLabel().equals(this.showMe.getLabel())) {
            this.who = 1;
            Screen.canvas.showMe();
            this.changeMessages();
            Screen.controls.changeMessages();
            this.enableButtons();
            final int[] buttonClicks = this.buttonClicks;
            final int showButtonNo = this.showButtonNo;
            ++buttonClicks[showButtonNo];
        }
        else if (((Checkbox)event.getItemSelectable()).getLabel().equals(this.illTry.getLabel())) {
            this.who = 2;
            this.changeMessages();
            Screen.controls.changeMessages();
            this.enableButtons();
            final int[] buttonClicks2 = this.buttonClicks;
            final int tryButtonNo = this.tryButtonNo;
            ++buttonClicks2[tryButtonNo];
        }
        else if (((Checkbox)event.getItemSelectable()).getLabel().equals(this.randomPick.getLabel())) {
            this.where = 1;
            final int[] buttonClicks3 = this.buttonClicks;
            final int randomButtonNo = this.randomButtonNo;
            ++buttonClicks3[randomButtonNo];
        }
        else if (((Checkbox)event.getItemSelectable()).getLabel().equals(this.illPick.getLabel())) {
            this.where = 2;
            final int[] buttonClicks4 = this.buttonClicks;
            final int pickButtonNo = this.pickButtonNo;
            ++buttonClicks4[pickButtonNo];
        }
    }
    
    public boolean userPicks() {
        return this.where == 2;
    }
}
