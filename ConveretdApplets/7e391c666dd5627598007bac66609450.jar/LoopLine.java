import java.util.Collection;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class LoopLine
{
    private int _intIndent;
    private String _strAnswer;
    private Vector _vecChoices;
    
    public LoopLine() {
        this._intIndent = 0;
        this._strAnswer = "";
        this._vecChoices = new Vector();
    }
    
    public LoopLine(final int indent, final String answer) {
        this._intIndent = indent;
        this._strAnswer = answer;
        (this._vecChoices = new Vector()).add(answer);
    }
    
    public LoopLine(final int indent, final String answer, final Vector choices) {
        this._intIndent = indent;
        this._strAnswer = answer;
        if (choices == null || choices.size() == 0) {
            (this._vecChoices = new Vector()).add(answer);
        }
        else {
            this._vecChoices = choices;
        }
    }
    
    public int getIndent() {
        return this._intIndent;
    }
    
    public String getAnswer() {
        return this._strAnswer;
    }
    
    public void setIndent(final int indent) {
        this._intIndent = indent;
    }
    
    public void setAnswer(final String answer) {
        this._strAnswer = answer;
    }
    
    public void setChoices(final Vector choices) {
        this._vecChoices = choices;
    }
    
    public void addChoice(final String choice) {
        this._vecChoices.add(choice);
    }
    
    public boolean isAnswer(final String guess) {
        return this._strAnswer.equalsIgnoreCase(guess);
    }
    
    public Vector getChoices() {
        if (this._vecChoices == null || this._vecChoices.size() < 2) {
            return new Vector();
        }
        if (!this._vecChoices.contains(this._strAnswer)) {
            this._vecChoices.add(this._strAnswer);
        }
        final Vector results = new Vector();
        results.add("");
        results.addAll(this._vecChoices);
        for (int i = 1; i < results.size(); ++i) {
            final int intSwap = UI_LoopConversion._random.nextInt(results.size() - i) + i;
            if (i != intSwap) {
                final Object element = results.get(i);
                results.setElementAt(results.get(intSwap), i);
                results.setElementAt(element, intSwap);
            }
        }
        return results;
    }
}
