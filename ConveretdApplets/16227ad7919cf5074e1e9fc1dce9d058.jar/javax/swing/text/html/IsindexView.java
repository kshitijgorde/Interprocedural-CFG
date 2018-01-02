// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.text.AttributeSet;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JEditorPane;
import java.net.URLEncoder;
import java.awt.event.ActionEvent;
import javax.swing.text.Element;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import javax.swing.text.ComponentView;

class IsindexView extends ComponentView implements ActionListener
{
    public static final String DEFAULT_PROMPT = "This is a searchable index.  Enter search keywords:";
    JTextField textField;
    
    public IsindexView(final Element element) {
        super(element);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        String s = this.textField.getText();
        if (s != null) {
            s = URLEncoder.encode(s);
        }
        final AttributeSet attributes = this.getElement().getAttributes();
        final HTMLDocument htmlDocument = (HTMLDocument)this.getElement().getDocument();
        String string = (String)attributes.getAttribute(HTML.Attribute.ACTION);
        if (string == null) {
            string = htmlDocument.getBase().toString();
        }
        try {
            ((JEditorPane)this.getContainer()).setPage(new URL(String.valueOf(string) + "?" + s));
        }
        catch (MalformedURLException ex) {}
        catch (IOException ex2) {}
    }
    
    public Component createComponent() {
        final AttributeSet attributes = this.getElement().getAttributes();
        final JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(null);
        String s = (String)attributes.getAttribute(HTML.Attribute.PROMPT);
        if (s == null) {
            s = "This is a searchable index.  Enter search keywords:";
        }
        final JLabel label = new JLabel(s);
        (this.textField = new JTextField()).addActionListener(this);
        panel.add(label, "West");
        panel.add(this.textField, "Center");
        panel.setAlignmentY(1.0f);
        panel.setOpaque(false);
        return panel;
    }
}
