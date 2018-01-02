import java.awt.Event;
import java.awt.Point;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class SpellClient extends Frame
{
    Button checkIt;
    TextArea text;
    
    public static void main(final String[] array) {
        new SpellClient();
    }
    
    public SpellClient() {
        super("Spelling Client Test Driver");
        this.checkIt = new Button("Check Spelling");
        this.text = new TextArea("Type your text here", 24, 80);
        this.setLayout(new BorderLayout());
        this.add("North", this.checkIt);
        this.add("Center", this.text);
        this.pack();
        SpellEngine.initChecker();
        SpellEngine.port = 5317;
        SpellEngine.host = "localhost";
        SpellEngine.setProgPos(new Point(this.location().x + 30, this.location().y + 30));
        SpellEngine.setBatchSize(50);
        this.show();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.checkIt) {
                    this.setTitle("Checking Spelling");
                    SpellEngine.check(this.text);
                    this.setTitle("Spelling Test Driver");
                }
                return true;
            }
            case 201: {
                this.dispose();
                System.exit(0);
                break;
            }
        }
        return super.handleEvent(event);
    }
}
