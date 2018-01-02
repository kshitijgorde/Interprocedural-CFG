import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class AnswerPanel extends HelpPanel implements ActionListener
{
    private int correctChoice;
    private Label label;
    private Button choice1;
    private Button choice2;
    
    public AnswerPanel() {
        super("Answers are displayed in this area.", "North");
        this.correctChoice = 0;
        this.label = new Label("", 1);
        this.choice1 = new Button("");
        this.choice2 = new Button("");
        this.setLayout(new GridLayout(1, 2, 5, 0));
        this.setBackground(Color.lightGray);
        this.add(this.label);
        this.label.addMouseListener(this);
        this.choice1.addMouseListener(this);
        this.choice2.addMouseListener(this);
        this.choice1.addActionListener(this);
        this.choice2.addActionListener(this);
    }
    
    public void actionPerformed(final ActionEvent event) {
        if (event.getActionCommand().equals(this.choice1.getLabel())) {
            Screen.canvas.buttonClicked(this.correctChoice == 1);
        }
        else if (event.getActionCommand().equals(this.choice2.getLabel())) {
            Screen.canvas.buttonClicked(this.correctChoice == 2);
        }
    }
    
    public void clearAnswer() {
        this.label.setText("");
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(177, 20);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(177, 20);
    }
    
    public boolean oneButton(final boolean buttonCorrect, final String prompt, final String valid, final String invalidShow, final String invalidTry) {
        if (Screen.panel.isInteractive()) {
            this.remove(this.label);
            Screen.prompt.displayPrompt(prompt);
            this.choice1.setLabel(valid);
            this.add(this.choice1);
            this.doLayout();
            if (buttonCorrect) {
                this.correctChoice = 1;
            }
            else {
                this.correctChoice = 0;
            }
            final boolean success = Screen.canvas.awaitClick(prompt, false, buttonCorrect);
            this.remove(this.choice1);
            if (buttonCorrect) {
                this.label.setText(valid);
            }
            else if (Screen.canvas.answerWasShown() || Screen.controls.isAborted()) {
                this.label.setText(invalidShow);
            }
            else {
                this.label.setText(invalidTry);
            }
            this.add(this.label);
            Screen.prompt.displayPrompt("");
            return success;
        }
        Screen.controls.nextStep(true, false);
        if (buttonCorrect) {
            this.label.setText(valid);
        }
        else {
            this.label.setText(invalidShow);
        }
        return true;
    }
    
    public void twoButtons(final String prompt, final String label1, final String label2, final String message1, final String message2, final boolean choice1Correct, final boolean choice2Correct) {
        if (Screen.panel.isInteractive()) {
            this.remove(this.label);
            this.choice1.setLabel(label1);
            this.choice2.setLabel(label2);
            this.add(this.choice1);
            this.add(this.choice2);
            this.doLayout();
            if (choice1Correct) {
                this.correctChoice = 1;
            }
            else if (choice2Correct) {
                this.correctChoice = 2;
            }
            else {
                this.correctChoice = 0;
            }
            final boolean success = Screen.canvas.awaitClick(prompt, false, this.correctChoice > 0);
            this.remove(this.choice1);
            this.remove(this.choice2);
            this.add(this.label);
            Screen.prompt.displayPrompt("");
        }
        if (choice1Correct) {
            this.label.setText(message1);
        }
        else if (choice2Correct) {
            this.label.setText(message2);
        }
        else {
            this.label.setText("");
        }
        Screen.controls.nextStep(true, false);
    }
}
