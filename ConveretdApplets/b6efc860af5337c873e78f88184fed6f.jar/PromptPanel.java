import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.KeyListener;

// 
// Decompiled by Procyon v0.5.30
// 

class PromptPanel extends HelpPanel implements KeyListener
{
    private static final int MAX_TRIES = 3;
    private boolean entryComplete;
    private Label prompt;
    private TextField pickText;
    
    public PromptPanel() {
        super("Prompts are displayed in this area.", "North");
        this.entryComplete = false;
        this.prompt = new Label("", 1);
        this.pickText = new TextField("", 2);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.lightGray);
        this.add("Center", this.prompt);
        this.prompt.addMouseListener(this);
        this.pickText.addKeyListener(this);
    }
    
    public void awaitInput(final String prompt, final int correctAnswer) {
        int tries = 1;
        if (Screen.controls.isAborted()) {
            return;
        }
        this.displayPrompt(prompt);
        Screen.interaction.displayMessage("");
        this.pickText.setText("");
        this.add("East", this.pickText);
        this.doLayout();
        this.pickText.requestFocus();
        int timer;
        for (timer = 0; timer < 50 && !Screen.controls.isAborted(); ++timer) {
            if (Screen.panel.userPicks()) {
                Screen.interaction.displayMessage("OK, I'll Show You");
                break;
            }
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
            final String input = this.pickText.getText();
            if (input.length() > 0 && !input.equals("-")) {
                this.pickText.setText("");
                try {
                    final int value = Integer.parseInt(input);
                    if (value == correctAnswer) {
                        Screen.interaction.displayMessage("Very Good, That's Correct");
                        Screen.progress.oneMoreRight(Screen.thread.button());
                        break;
                    }
                }
                catch (NumberFormatException ex2) {}
                Screen.progress.oneMoreWrong(Screen.thread.button());
                if (tries == 1) {
                    Screen.interaction.displayMessage("Not Correct, Try Again");
                }
                else if (tries == 2) {
                    Screen.interaction.displayMessage("Still Wrong, Try Again");
                }
                else {
                    if (tries != 3) {
                        Screen.interaction.displayMessage("OK, Let Me Show You");
                        break;
                    }
                    Screen.interaction.displayMessage("OK, Try One More Time");
                }
                ++tries;
                this.pickText.setText("");
            }
        }
        if (timer >= 50) {
            Screen.interaction.displayMessage("Time's Up, I'll Show You");
        }
        this.displayPrompt("");
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex3) {}
        this.remove(this.pickText);
        this.doLayout();
    }
    
    public void displayPrompt(final String string) {
        this.prompt.setText(string);
    }
    
    public int getData(final int digits, final int lower, final int upper, final boolean alwaysGet) {
        int tries = 1;
        boolean validPick = false;
        int value = (int)Math.floor((upper - lower + 1) * Math.random() + lower);
        if (!alwaysGet && !Screen.panel.userPicks()) {
            return value;
        }
        this.entryComplete = false;
        this.pickText.setText("");
        this.add("East", this.pickText);
        this.doLayout();
        this.pickText.requestFocus();
        for (int timer = 0; timer < 50 && !Screen.controls.abortInitiated(); ++timer) {
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
            final String text = this.pickText.getText();
            if (text.length() < digits) {
                if (!this.entryComplete) {
                    continue;
                }
            }
            try {
                final int pickedValue = Integer.parseInt(text);
                if (pickedValue < lower) {
                    Screen.interaction.displayMessage("Value Must >= " + lower);
                }
                else {
                    if (pickedValue <= upper) {
                        validPick = true;
                        value = pickedValue;
                        break;
                    }
                    Screen.interaction.displayMessage("Value Must Be <= " + upper);
                }
            }
            catch (NumberFormatException ex2) {
                Screen.interaction.displayMessage("Value Must Be Numeric");
            }
            this.pickText.setText("");
            if (tries++ == 3) {
                break;
            }
        }
        this.remove(this.pickText);
        this.doLayout();
        this.displayPrompt("");
        if (!validPick) {
            Screen.interaction.displayMessage("OK, I'll Pick " + value);
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex3) {}
        }
        return value;
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(177, 20);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(177, 20);
    }
    
    public void keyPressed(final KeyEvent event) {
        if (event.getSource() == this.pickText && event.getKeyCode() == 10) {
            this.entryComplete = true;
        }
    }
    
    public void keyReleased(final KeyEvent event) {
    }
    
    public void keyTyped(final KeyEvent event) {
    }
}
