import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class HangmanActionListener implements ActionListener
{
    private word word1;
    private hangmanapp a;
    private int maxNumWrong;
    
    public HangmanActionListener(final int maxNumWrong) {
        this.maxNumWrong = 5;
        this.maxNumWrong = maxNumWrong;
    }
    
    public void setWord(final word word1) {
        this.word1 = word1;
    }
    
    public void setApplet(final hangmanapp a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.getGraphics().setColor(Color.red);
        this.a.getGraphics().clearRect(0, 0, 280, 200);
        this.word1.guess(actionEvent.getActionCommand());
        this.a.getGraphics().setColor(Color.black);
        if (this.word1.getNumWrong() == this.maxNumWrong) {
            this.a.init();
            this.a.getGraphics().setColor(Color.blue);
            this.a.getGraphics().drawString("Sorry better luck next time", 70, 110);
            this.a.getGraphics().drawString("the word was: " + this.word1.getWord(), 70, 125);
            return;
        }
        if (this.word1.isCorrect(this.word1.toString())) {
            this.a.init();
            this.a.getGraphics().setColor(Color.gray);
            this.a.getGraphics().drawString("Congratulations!!", 70, 110);
            this.a.getGraphics().drawString("the word was: " + this.word1.getWord(), 70, 125);
            return;
        }
        this.a.getGraphics().drawString(this.word1.toString(), 100, 130);
        this.a.getGraphics().drawString(this.word1.getTried(), 100, 145);
        this.a.getGraphics().drawString("TOPIC: " + this.word1.getTopic(), 100, 115);
    }
}
