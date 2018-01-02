// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.JTextComponent;
import javax.swing.text.AttributeSet;
import java.awt.Font;
import javax.swing.text.Document;
import javax.swing.event.DocumentListener;
import javax.swing.text.StyledDocument;
import javax.swing.JTextArea;
import java.awt.Component;
import javax.swing.text.Element;
import javax.swing.border.Border;

class CommentView extends HiddenTagView
{
    static final Border CBorder;
    static final int commentPadding = 3;
    static final int commentPaddingD = 9;
    
    static {
        CBorder = new CommentBorder();
    }
    
    CommentView(final Element element) {
        super(element);
    }
    
    protected Component createComponent() {
        final JTextArea textArea = new JTextArea(this.getRepresentedText());
        final Document document = this.getDocument();
        Font font;
        if (document instanceof StyledDocument) {
            font = ((StyledDocument)document).getFont(this.getAttributes());
            textArea.setFont(font);
        }
        else {
            font = textArea.getFont();
        }
        this.updateYAlign(font);
        textArea.setBorder(CommentView.CBorder);
        textArea.getDocument().addDocumentListener(this);
        return textArea;
    }
    
    String getRepresentedText() {
        final AttributeSet attributes = this.getElement().getAttributes();
        if (attributes != null) {
            final Object attribute = attributes.getAttribute(HTML.Attribute.COMMENT);
            if (attribute instanceof String) {
                return (String)attribute;
            }
        }
        return "";
    }
    
    JTextComponent getTextComponent() {
        return (JTextComponent)this.getComponent();
    }
    
    void pushTextToModel() {
        if (!super.isSettingAttributes) {
            final SimpleAttributeSet set = new SimpleAttributeSet();
            final String text = this.getTextComponent().getText();
            super.isSettingAttributes = true;
            try {
                set.addAttribute(HTML.Attribute.COMMENT, text);
                ((StyledDocument)this.getDocument()).setCharacterAttributes(this.getStartOffset(), this.getEndOffset() - this.getStartOffset(), set, false);
            }
            finally {
                super.isSettingAttributes = false;
            }
        }
    }
    
    void resetBorder() {
    }
    
    static class CommentBorder extends LineBorder
    {
        CommentBorder() {
            super(Color.black, 1);
        }
        
        public Insets getBorderInsets(final Component component) {
            final Insets borderInsets;
            final Insets insets = borderInsets = super.getBorderInsets(component);
            borderInsets.left += 3;
            final Insets insets2 = insets;
            insets2.right += 3;
            return insets;
        }
        
        public boolean isBorderOpaque() {
            return false;
        }
        
        public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
            super.paintBorder(component, graphics, n + 3, n2, n3 - 9, n4);
        }
    }
}
