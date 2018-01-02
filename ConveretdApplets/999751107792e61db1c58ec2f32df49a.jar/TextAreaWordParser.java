import java.util.NoSuchElementException;
import javax.swing.JTextArea;
import java.awt.TextArea;
import com.wintertree.ssce.HTMLStringWordParser;

// 
// Decompiled by Procyon v0.5.30
// 

public class TextAreaWordParser extends HTMLStringWordParser
{
    private boolean selectCurWord;
    public TextArea textArea;
    
    public TextAreaWordParser(final TextArea textArea, final boolean b, final boolean selectCurWord) {
        super(textArea.getText(), b);
        this.selectCurWord = selectCurWord;
        this.textArea = textArea;
    }
    
    public TextAreaWordParser(final JTextArea textArea, final boolean b, final boolean selectCurWord) {
        super(textArea.getText(), b);
        this.selectCurWord = selectCurWord;
        this.textArea = new TextArea(textArea.getText());
    }
    
    public void deleteText(final int n) throws NoSuchElementException {
        this.textArea.replaceRange("", this.cursor, this.cursor + n);
        super.deleteText(n);
    }
    
    public void highlightWord() {
        if (this.hasMoreElements()) {
            final String word = this.getWord();
            this.textArea.requestFocus();
            this.textArea.select(this.cursor, this.cursor + word.length());
        }
    }
    
    public void insertText(final int n, final String s) {
        super.insertText(n, s);
        this.textArea.insert(s, n);
    }
    
    public void replaceWord(final String s) throws NoSuchElementException {
        this.textArea.replaceRange(s, this.cursor, this.cursor + this.getWord().length());
        super.replaceWord(s);
    }
    
    public void updateText() {
        this.theString.setLength(0);
        this.theString.append(this.textArea.getText());
        this.cursor = 0;
        this.subWordLength = 0;
    }
}
