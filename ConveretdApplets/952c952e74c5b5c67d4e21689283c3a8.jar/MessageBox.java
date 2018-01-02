import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class MessageBox extends Panel implements Messenger, ActionListener
{
    private Panel messageButtons;
    private TextArea messageBox;
    private Button compile;
    private Button clear;
    private LittleManCompiler compiler;
    
    public MessageBox() {
        this.setBackground(LittleMan.LIGHT_PINK);
        this.setLayout(new BorderLayout());
        this.add(new Label("Message Box:"), "North");
        this.add(this.messageBox = new TextArea(), "Center");
        (this.messageButtons = new Panel()).setBackground(LittleMan.LIGHT_PINK);
        this.clear = new Button("Clear Messages");
        this.messageButtons.add(this.clear);
        this.clear.addActionListener(this);
        (this.compile = new Button("Compile Program")).addActionListener(this);
        this.messageButtons.add(this.compile);
        this.add(this.messageButtons, "South");
    }
    
    public void registerCompiler(final LittleManCompiler compiler) {
        this.compiler = compiler;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.clear) {
            this.messageBox.setText("");
        }
        else if (actionEvent.getSource() == this.compile) {
            final String text = this.messageBox.getText();
            this.messageBox.setText("");
            this.sendMessage("\n----- Trying to compile -----");
            try {
                this.compiler.compile(text);
                this.sendMessage("----- Program Successfully Compiled -----");
            }
            catch (LittleManException ex) {
                this.sendMessage(ex.toString());
            }
        }
    }
    
    public void sendMessage(final String s) {
        this.messageBox.append("\n" + s);
    }
    
    public void clear() {
        this.messageBox.setText("");
    }
}
