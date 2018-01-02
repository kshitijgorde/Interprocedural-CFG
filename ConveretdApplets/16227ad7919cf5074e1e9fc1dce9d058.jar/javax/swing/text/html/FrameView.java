// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import java.awt.Insets;
import javax.swing.border.Border;
import java.awt.Container;
import javax.swing.text.JTextComponent;
import java.awt.Graphics;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.View;
import javax.swing.JViewport;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.text.AttributeSet;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.text.ViewFactory;
import java.awt.Shape;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Element;
import java.net.URL;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.ComponentView;

class FrameView extends ComponentView implements HyperlinkListener
{
    JEditorPane htmlPane;
    JScrollPane scroller;
    boolean editable;
    float width;
    float height;
    URL src;
    
    public FrameView(final Element element) {
        super(element);
    }
    
    public void changedUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        final Element element = this.getElement();
        final AttributeSet attributes = element.getAttributes();
        final URL src = this.src;
        final String s = (String)attributes.getAttribute(HTML.Attribute.SRC);
        final URL base = ((HTMLDocument)element.getDocument()).getBase();
        try {
            this.src = new URL(base, s);
            if (src.equals(this.src)) {
                return;
            }
            this.htmlPane.setPage(this.src);
            ((HTMLDocument)this.htmlPane.getDocument()).setFrameDocumentState(true);
        }
        catch (MalformedURLException ex) {}
        catch (IOException ex2) {}
    }
    
    protected Component createComponent() {
        final Element element = this.getElement();
        final String s = (String)element.getAttributes().getAttribute(HTML.Attribute.SRC);
        if (s != null && !s.equals("")) {
            try {
                this.src = new URL(((HTMLDocument)element.getDocument()).getBase(), s);
                (this.htmlPane = new JEditorPane(this.src)).addHyperlinkListener(this);
                this.htmlPane.setEditable(this.editable);
                ((HTMLDocument)this.htmlPane.getDocument()).setFrameDocumentState(true);
                this.setMargin();
                this.createScrollPane();
                this.setBorder();
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return this.scroller;
    }
    
    private void createScrollPane() {
        String s = (String)this.getElement().getAttributes().getAttribute(HTML.Attribute.SCROLLING);
        if (s == null) {
            s = "auto";
        }
        if (!s.equals("no")) {
            if (s.equals("yes")) {
                this.scroller = new JScrollPane(22, 32);
            }
            else {
                this.scroller = new JScrollPane();
            }
        }
        else {
            this.scroller = new JScrollPane(21, 31);
        }
        final JViewport viewport = this.scroller.getViewport();
        viewport.add(this.htmlPane);
        viewport.setBackingStoreEnabled(true);
        this.scroller.setVisible(false);
        this.scroller.setMinimumSize(new Dimension(5, 5));
        this.scroller.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }
    
    public float getMaximumSpan(final int n) {
        return 2.14748365E9f;
    }
    
    public float getMinimumSpan(final int n) {
        return 5.0f;
    }
    
    private JEditorPane getOutermostJEditorPane() {
        View view = this.getParent();
        View view2 = null;
        while (view != null) {
            if (view instanceof FrameSetView) {
                view2 = view;
            }
            view = view.getParent();
        }
        if (view2 != null) {
            return (JEditorPane)view2.getContainer();
        }
        return null;
    }
    
    public void hyperlinkUpdate(final HyperlinkEvent hyperlinkEvent) {
        if (!(hyperlinkEvent instanceof HTMLFrameHyperlinkEvent)) {
            return;
        }
        final HTMLFrameHyperlinkEvent htmlFrameHyperlinkEvent = (HTMLFrameHyperlinkEvent)hyperlinkEvent;
        if (htmlFrameHyperlinkEvent.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            final JEditorPane outermostJEditorPane = this.getOutermostJEditorPane();
            String target = htmlFrameHyperlinkEvent.getTarget();
            if (htmlFrameHyperlinkEvent.getTarget().equals("_parent") && !this.inNestedFrameSet()) {
                target = "_top";
            }
            if (outermostJEditorPane != null && !outermostJEditorPane.isEditable()) {
                outermostJEditorPane.fireHyperlinkUpdate(new HTMLFrameHyperlinkEvent(outermostJEditorPane, htmlFrameHyperlinkEvent.getEventType(), htmlFrameHyperlinkEvent.getURL(), htmlFrameHyperlinkEvent.getDescription(), this.getElement(), target));
                if (target.equals("_top")) {
                    try {
                        outermostJEditorPane.setPage(htmlFrameHyperlinkEvent.getURL());
                    }
                    catch (IOException ex) {}
                }
            }
        }
    }
    
    private boolean inNestedFrameSet() {
        return ((FrameSetView)this.getParent()).getParent() instanceof FrameSetView;
    }
    
    public void paint(final Graphics graphics, final Shape shape) {
        final Container container = this.getContainer();
        if (container != null && this.htmlPane != null && this.htmlPane.isEditable() != ((JTextComponent)container).isEditable()) {
            this.editable = ((JTextComponent)container).isEditable();
            this.htmlPane.setEditable(this.editable);
        }
        super.paint(graphics, shape);
    }
    
    private void setBorder() {
        final String s = (String)this.getElement().getAttributes().getAttribute(HTML.Attribute.FRAMEBORDER);
        if (s != null && (s.equals("no") || s.equals("0"))) {
            this.scroller.setBorder(null);
        }
    }
    
    private void setMargin() {
        final Insets margin = this.htmlPane.getMargin();
        final Insets margin2 = new Insets(margin.top, margin.left, margin.right, margin.bottom);
        boolean b = false;
        final AttributeSet attributes = this.getElement().getAttributes();
        final String s = (String)attributes.getAttribute(HTML.Attribute.MARGINWIDTH);
        if (s != null) {
            final int int1 = Integer.parseInt(s);
            if (int1 > 0) {
                margin2.left = int1;
                margin2.right = int1;
                b = true;
            }
        }
        final String s2 = (String)attributes.getAttribute(HTML.Attribute.MARGINHEIGHT);
        if (s2 != null) {
            final int int2 = Integer.parseInt(s2);
            if (int2 > 0) {
                margin2.top = int2;
                margin2.bottom = int2;
                b = true;
            }
        }
        if (b) {
            this.htmlPane.setMargin(margin2);
        }
    }
    
    public void setParent(final View parent) {
        if (parent != null) {
            this.editable = ((JTextComponent)parent.getContainer()).isEditable();
        }
        super.setParent(parent);
    }
}
