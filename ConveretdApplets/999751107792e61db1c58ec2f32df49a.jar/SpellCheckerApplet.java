import java.io.InputStream;
import com.wintertree.ssce.WordParser;
import com.wintertree.ssce.SpellingSession;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import java.util.Properties;
import java.net.URL;
import com.wintertree.ssce.PropSpellingSession;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SpellCheckerApplet extends JApplet
{
    protected PropSpellingSession ssce;
    protected URL codeBase;
    SpellingDialogSwing spellDialog;
    String original_text;
    String modified_text;
    int status;
    
    public SpellCheckerApplet() {
        this.spellDialog = null;
        this.original_text = null;
        this.modified_text = null;
        this.status = -1;
    }
    
    public void init() {
    }
    
    public void check() {
        this.codeBase = this.getCodeBase();
        if (this.ssce == null) {
            try {
                final InputStream resourceAsStream = this.getClass().getResourceAsStream("ttemi.properties");
                final Properties properties = new Properties();
                properties.load(resourceAsStream);
                (this.ssce = new PropSpellingSession(properties, this.codeBase)).getLexicons();
            }
            catch (Exception ex) {
                System.out.println("Error loading ttemi.properties" + ex.getMessage());
                ex.printStackTrace();
                return;
            }
        }
        final JFrame frame = new JFrame();
        if (this.original_text != null) {
            final JTextArea textArea = new JTextArea(this.original_text);
            this.status = 0;
            final JTextArea textArea2 = textArea;
            final PropSpellingSession ssce = this.ssce;
            final PropSpellingSession ssce2 = this.ssce;
            this.spellDialog = new SpellingDialogSwing(frame, (SpellingSession)this.ssce, textArea, (WordParser)new TextAreaWordParser(textArea2, ssce.getOption(4096), false), this.ssce.comparator, this.ssce.userLexicons);
            this.spellDialog.minSuggestDepth = this.ssce.minSuggestDepth;
            this.spellDialog.setVisible(true);
        }
    }
    
    public void setText(final String original_text) {
        this.original_text = original_text;
        this.modified_text = null;
    }
    
    public int getStatus() {
        if (this.spellDialog != null) {
            if (this.spellDialog.completed && this.spellDialog.userCanceled()) {
                this.status = 2;
            }
            else if (this.spellDialog.completed && !this.spellDialog.userCanceled()) {
                this.modified_text = this.spellDialog.getText();
                this.status = 1;
            }
            else {
                this.status = 0;
            }
        }
        return this.status;
    }
    
    public String getText() {
        this.original_text = null;
        this.spellDialog = null;
        return this.modified_text;
    }
    
    public void getFocus() {
        this.spellDialog.show();
    }
}
