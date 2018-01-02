import java.awt.event.TextEvent;
import java.awt.event.ItemEvent;
import java.awt.Panel;
import java.awt.CheckboxGroup;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.Checkbox;
import java.awt.event.TextListener;
import java.awt.event.ItemListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class UnicodeApplet extends Applet implements ItemListener, TextListener
{
    private Checkbox htmlCheckbox;
    private TextArea input;
    private TextArea output;
    
    public void init() {
        this.setLayout(new BorderLayout());
        (this.input = new TextArea(2, 50)).addTextListener(this);
        this.add(this.input, "North");
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        final Checkbox checkbox = new Checkbox(this.getParameter("java_escapes", "Java escapes"), true, checkboxGroup);
        checkbox.addItemListener(this);
        (this.htmlCheckbox = new Checkbox(this.getParameter("html_escapes", "HTML escapes"), false, checkboxGroup)).addItemListener(this);
        final Panel panel = new Panel();
        panel.add(checkbox);
        panel.add(this.htmlCheckbox);
        this.add(panel, "Center");
        (this.output = new TextArea(4, 50)).setEditable(false);
        this.add(this.output, "South");
    }
    
    private String getParameter(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return s2;
        }
        return parameter;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (1 == itemEvent.getStateChange()) {
            if (this.htmlCheckbox.getState()) {
                this.showHtmlEscapes();
            }
            else {
                this.showJavaEscapes();
            }
        }
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        if (this.htmlCheckbox.getState()) {
            this.showHtmlEscapes();
        }
        else {
            this.showJavaEscapes();
        }
    }
    
    private void showHtmlEscapes() {
        this.output.setText(UnicodeEscaper.escapeForHtml(this.input.getText()));
    }
    
    private void showJavaEscapes() {
        this.output.setText(UnicodeEscaper.escapeForJava(this.input.getText()));
    }
}
