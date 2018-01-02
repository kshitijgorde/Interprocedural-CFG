// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.Event;
import javax.swing.text.EditorKit;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JFrame;

public class ShowHtml extends JFrame
{
    public ShowHtml(final String s, final String text) {
        super(s);
        this.setSize(550, 500);
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        this.getContentPane().add(new JScrollPane(editorPane), "Center");
        this.setVisible(true);
        editorPane.setEditorKit(new HTMLEditorKit());
        editorPane.setText(text);
        editorPane.setCaretPosition(0);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.setVisible(false);
            this.dispose();
        }
        return super.handleEvent(event);
    }
}
