// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import java.io.PrintWriter;
import rene.util.list.ListElement;
import jagoclient.Global;
import rene.util.list.ListClass;
import java.awt.TextArea;

public class MyTextArea extends TextArea
{
    ListClass L;
    public int MaxLength;
    int Length;
    
    public MyTextArea() {
        this.setFont(Global.Monospaced);
        this.setBackground(Global.gray);
        this.L = new ListClass();
        this.MaxLength = rene.gui.Global.getParameter("maxlength", 10000);
    }
    
    public MyTextArea(final String text, final int n, final int n2, final int n3) {
        super(text, n, n2, n3);
        this.setFont(Global.Monospaced);
        this.setBackground(Global.gray);
        this.L = new ListClass();
        this.MaxLength = rene.gui.Global.getParameter("maxlength", 10000);
        this.setText(text);
    }
    
    public void append(final String s) {
        this.Length += s.length();
        this.L.append(new ListElement(s));
        if (this.Length > this.MaxLength) {
            this.setVisible(false);
            super.setText("");
            ListElement listElement = this.L.last();
            this.Length = 0;
            while (this.Length < this.MaxLength / 4) {
                this.Length += ((String)listElement.content()).length();
                if (listElement.previous() == null) {
                    break;
                }
                listElement = listElement.previous();
            }
            while (listElement != null) {
                super.append((String)listElement.content());
                listElement = listElement.next();
            }
            this.setVisible(true);
            return;
        }
        super.append(s);
    }
    
    public void save(final PrintWriter printWriter) {
        for (ListElement listElement = this.L.first(); listElement != null; listElement = listElement.next()) {
            printWriter.print((String)listElement.content());
        }
    }
    
    public void setText(final String text) {
        this.Length = text.length();
        super.setText(text);
        (this.L = new ListClass()).append(new ListElement(text));
    }
    
    public void setEditable(final boolean editable) {
        super.setEditable(editable);
        if (!editable) {
            this.setBackground(Global.gray.brighter());
        }
    }
}
