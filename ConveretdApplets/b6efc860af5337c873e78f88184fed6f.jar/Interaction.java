import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class Interaction extends Panel
{
    public static final int PANEL_WIDTH = 177;
    public static final int COMPONENT_HEIGHT = 20;
    private Label message;
    private SizedComponent messagePanel;
    private GridBagLayout gridBag;
    private GridBagConstraints constraints;
    
    public Interaction(final PromptPanel prompt, final AnswerPanel answer) {
        this.message = new Label("", 1);
        this.gridBag = new GridBagLayout();
        this.constraints = new GridBagConstraints();
        this.setBackground(Color.gray);
        this.setLayout(this.gridBag);
        this.constraints.weightx = 1.0;
        this.constraints.gridy = 0;
        this.constraints.gridx = 0;
        this.gridBag.setConstraints(prompt, this.constraints);
        this.add(prompt);
        this.constraints.gridx = 1;
        this.gridBag.setConstraints(answer, this.constraints);
        this.add(answer);
        this.constraints.gridx = 2;
        final SizedComponent sizedMessage = new SizedComponent(this.message, 177, 20, "Messages are displayed in this area.", "North");
        this.gridBag.setConstraints(sizedMessage, this.constraints);
        this.add(sizedMessage);
    }
    
    public void displayMessage(final String string) {
        this.message.setText(string);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(546, 24);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(546, 24);
    }
}
