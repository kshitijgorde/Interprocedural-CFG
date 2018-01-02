import edu.hws.jcm.awt.Controller;
import edu.hws.jcm.awt.VariableInput;
import edu.hws.jcm.data.Parser;
import java.awt.Component;
import edu.hws.jcm.awt.JCMPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import edu.hws.jcm.awt.DisplayLabel;
import edu.hws.jcm.data.Value;
import java.awt.Color;
import java.awt.Label;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ArithmeticApplet extends Applet
{
    Label makeLabel(final String s) {
        final Label label = new Label(s, 2);
        label.setBackground(new Color(255, 255, 220));
        label.setForeground(Color.blue);
        return label;
    }
    
    Label makeDisplayLabel(final Value value) {
        final DisplayLabel displayLabel = new DisplayLabel("#", value);
        displayLabel.setBackground(new Color(255, 255, 220));
        displayLabel.setForeground(Color.red);
        displayLabel.setAlignment(1);
        return displayLabel;
    }
    
    public void init() {
        this.setBackground(Color.blue);
        this.setLayout(new BorderLayout());
        final JCMPanel jcmPanel = new JCMPanel(6, 2, 2);
        this.add(jcmPanel, "Center");
        jcmPanel.setInsetGap(2);
        final Parser parser = new Parser();
        final VariableInput variableInput = new VariableInput("x", "0", parser);
        final VariableInput variableInput2 = new VariableInput("y", "0", parser);
        jcmPanel.add(this.makeLabel("Input x = "));
        jcmPanel.add(variableInput);
        jcmPanel.add(this.makeLabel("Input y = "));
        jcmPanel.add(variableInput2);
        jcmPanel.add(this.makeLabel("x + y = "));
        jcmPanel.add(this.makeDisplayLabel(parser.parse("x+y")));
        jcmPanel.add(this.makeLabel("x - y = "));
        jcmPanel.add(this.makeDisplayLabel(parser.parse("x-y")));
        jcmPanel.add(this.makeLabel("x * y = "));
        jcmPanel.add(this.makeDisplayLabel(parser.parse("x*y")));
        jcmPanel.add(this.makeLabel("x / y = "));
        jcmPanel.add(this.makeDisplayLabel(parser.parse("x/y")));
        final Controller controller = jcmPanel.getController();
        variableInput.setOnTextChange(controller);
        variableInput2.setOnTextChange(controller);
    }
}
