// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import javax.swing.event.CaretEvent;
import java.io.Serializable;
import java.beans.PropertyChangeListener;
import javax.swing.event.CaretListener;
import javax.swing.JEditorPane;
import javax.swing.Action;

public class StyledEditorKit extends DefaultEditorKit
{
    private static final ViewFactory defaultFactory;
    Element currentRun;
    Element currentParagraph;
    MutableAttributeSet inputAttributes;
    private AttributeTracker inputAttributeUpdater;
    private static final Action[] defaultActions;
    
    static {
        defaultFactory = new StyledViewFactory();
        defaultActions = new Action[] { new FontFamilyAction("font-family-SansSerif", "SansSerif"), new FontFamilyAction("font-family-Monospaced", "Monospaced"), new FontFamilyAction("font-family-Serif", "Serif"), new FontSizeAction("font-size-8", 8), new FontSizeAction("font-size-10", 10), new FontSizeAction("font-size-12", 12), new FontSizeAction("font-size-14", 14), new FontSizeAction("font-size-16", 16), new FontSizeAction("font-size-18", 18), new FontSizeAction("font-size-24", 24), new FontSizeAction("font-size-36", 36), new FontSizeAction("font-size-48", 48), new AlignmentAction("left-justify", 0), new AlignmentAction("center-justify", 1), new AlignmentAction("right-justify", 2), new BoldAction(), new ItalicAction(), new UnderlineAction() };
    }
    
    public StyledEditorKit() {
        this.inputAttributes = new SimpleAttributeSet() {
            public Object clone() {
                return new SimpleAttributeSet(this);
            }
            
            public AttributeSet getResolveParent() {
                return (StyledEditorKit.this.currentParagraph != null) ? StyledEditorKit.this.currentParagraph.getAttributes() : null;
            }
        };
        this.inputAttributeUpdater = new AttributeTracker();
    }
    
    public Object clone() {
        return new StyledEditorKit();
    }
    
    public Document createDefaultDocument() {
        return new DefaultStyledDocument();
    }
    
    protected void createInputAttributes(final Element element, final MutableAttributeSet set) {
        set.removeAttributes(set);
        set.addAttributes(element.getAttributes());
        set.removeAttribute(StyleConstants.ComponentAttribute);
        set.removeAttribute(StyleConstants.IconAttribute);
        set.removeAttribute("$ename");
        set.removeAttribute(StyleConstants.ComposedTextAttribute);
    }
    
    public void deinstall(final JEditorPane editorPane) {
        editorPane.removeCaretListener(this.inputAttributeUpdater);
        editorPane.removePropertyChangeListener(this.inputAttributeUpdater);
        this.currentRun = null;
        this.currentParagraph = null;
    }
    
    public Action[] getActions() {
        return TextAction.augmentList(super.getActions(), StyledEditorKit.defaultActions);
    }
    
    public Element getCharacterAttributeRun() {
        return this.currentRun;
    }
    
    public MutableAttributeSet getInputAttributes() {
        return this.inputAttributes;
    }
    
    public ViewFactory getViewFactory() {
        return StyledEditorKit.defaultFactory;
    }
    
    public void install(final JEditorPane editorPane) {
        editorPane.addCaretListener(this.inputAttributeUpdater);
        editorPane.addPropertyChangeListener(this.inputAttributeUpdater);
        final Caret caret = editorPane.getCaret();
        if (caret != null) {
            this.inputAttributeUpdater.updateInputAttributes(caret.getDot(), caret.getMark(), editorPane);
        }
    }
    
    class AttributeTracker implements CaretListener, PropertyChangeListener, Serializable
    {
        public void caretUpdate(final CaretEvent caretEvent) {
            this.updateInputAttributes(caretEvent.getDot(), caretEvent.getMark(), (JTextComponent)caretEvent.getSource());
        }
        
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            final Object newValue = propertyChangeEvent.getNewValue();
            final Object source = propertyChangeEvent.getSource();
            if (source instanceof JTextComponent && newValue instanceof Document) {
                this.updateInputAttributes(0, 0, (JTextComponent)source);
            }
        }
        
        void updateInputAttributes(final int n, final int n2, final JTextComponent textComponent) {
            final Document document = textComponent.getDocument();
            if (!(document instanceof StyledDocument)) {
                return;
            }
            final int min = Math.min(n, n2);
            final StyledDocument styledDocument = (StyledDocument)document;
            StyledEditorKit.this.currentParagraph = styledDocument.getParagraphElement(min);
            Element currentRun;
            if (StyledEditorKit.this.currentParagraph.getStartOffset() == min) {
                currentRun = styledDocument.getCharacterElement(min);
            }
            else {
                currentRun = styledDocument.getCharacterElement(Math.max(min - 1, 0));
            }
            if (currentRun != StyledEditorKit.this.currentRun) {
                StyledEditorKit.this.currentRun = currentRun;
                StyledEditorKit.this.createInputAttributes(StyledEditorKit.this.currentRun, StyledEditorKit.this.getInputAttributes());
            }
        }
    }
    
    static class StyledViewFactory implements ViewFactory
    {
        public View create(final Element element) {
            final String name = element.getName();
            if (name != null) {
                if (name.equals("content")) {
                    return new LabelView(element);
                }
                if (name.equals("paragraph")) {
                    return new ParagraphView(element);
                }
                if (name.equals("section")) {
                    return new BoxView(element, 1);
                }
                if (name.equals("component")) {
                    return new ComponentView(element);
                }
                if (name.equals("icon")) {
                    return new IconView(element);
                }
            }
            return new LabelView(element);
        }
    }
    
    public abstract static class StyledTextAction extends TextAction
    {
        public StyledTextAction(final String s) {
            super(s);
        }
        
        protected final JEditorPane getEditor(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent instanceof JEditorPane) {
                return (JEditorPane)textComponent;
            }
            return null;
        }
        
        protected final StyledDocument getStyledDocument(final JEditorPane editorPane) {
            final Document document = editorPane.getDocument();
            if (document instanceof StyledDocument) {
                return (StyledDocument)document;
            }
            throw new IllegalArgumentException("document must be StyledDocument");
        }
        
        protected final StyledEditorKit getStyledEditorKit(final JEditorPane editorPane) {
            final EditorKit editorKit = editorPane.getEditorKit();
            if (editorKit instanceof StyledEditorKit) {
                return (StyledEditorKit)editorKit;
            }
            throw new IllegalArgumentException("EditorKit must be StyledEditorKit");
        }
        
        protected final void setCharacterAttributes(final JEditorPane editorPane, final AttributeSet set, final boolean b) {
            final int selectionStart = editorPane.getSelectionStart();
            final int selectionEnd = editorPane.getSelectionEnd();
            if (selectionStart != selectionEnd) {
                this.getStyledDocument(editorPane).setCharacterAttributes(selectionStart, selectionEnd - selectionStart, set, b);
            }
            final MutableAttributeSet inputAttributes = this.getStyledEditorKit(editorPane).getInputAttributes();
            if (b) {
                inputAttributes.removeAttributes(inputAttributes);
            }
            inputAttributes.addAttributes(set);
        }
        
        protected final void setParagraphAttributes(final JEditorPane editorPane, final AttributeSet set, final boolean b) {
            final int selectionStart = editorPane.getSelectionStart();
            this.getStyledDocument(editorPane).setParagraphAttributes(selectionStart, editorPane.getSelectionEnd() - selectionStart, set, b);
        }
    }
    
    public static class FontFamilyAction extends StyledTextAction
    {
        private String family;
        
        public FontFamilyAction(final String s, final String family) {
            super(s);
            this.family = family;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JEditorPane editor = ((StyledTextAction)this).getEditor(actionEvent);
            if (editor != null) {
                String family = this.family;
                if (actionEvent != null && actionEvent.getSource() == editor) {
                    final String actionCommand = actionEvent.getActionCommand();
                    if (actionCommand != null) {
                        family = actionCommand;
                    }
                }
                if (family != null) {
                    final SimpleAttributeSet set = new SimpleAttributeSet();
                    StyleConstants.setFontFamily(set, family);
                    ((StyledTextAction)this).setCharacterAttributes(editor, set, false);
                }
                else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        }
    }
    
    public static class FontSizeAction extends StyledTextAction
    {
        private int size;
        
        public FontSizeAction(final String s, final int size) {
            super(s);
            this.size = size;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JEditorPane editor = ((StyledTextAction)this).getEditor(actionEvent);
            if (editor != null) {
                int n = this.size;
                if (actionEvent != null && actionEvent.getSource() == editor) {
                    final String actionCommand = actionEvent.getActionCommand();
                    try {
                        n = Integer.parseInt(actionCommand, 10);
                    }
                    catch (NumberFormatException ex) {}
                }
                if (n != 0) {
                    final SimpleAttributeSet set = new SimpleAttributeSet();
                    StyleConstants.setFontSize(set, n);
                    ((StyledTextAction)this).setCharacterAttributes(editor, set, false);
                }
                else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        }
    }
    
    public static class ForegroundAction extends StyledTextAction
    {
        private Color fg;
        
        public ForegroundAction(final String s, final Color fg) {
            super(s);
            this.fg = fg;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JEditorPane editor = ((StyledTextAction)this).getEditor(actionEvent);
            if (editor != null) {
                Color color = this.fg;
                if (actionEvent != null && actionEvent.getSource() == editor) {
                    final String actionCommand = actionEvent.getActionCommand();
                    try {
                        color = Color.decode(actionCommand);
                    }
                    catch (NumberFormatException ex) {}
                }
                if (color != null) {
                    final SimpleAttributeSet set = new SimpleAttributeSet();
                    StyleConstants.setForeground(set, color);
                    ((StyledTextAction)this).setCharacterAttributes(editor, set, false);
                }
                else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        }
    }
    
    public static class AlignmentAction extends StyledTextAction
    {
        private int a;
        
        public AlignmentAction(final String s, final int a) {
            super(s);
            this.a = a;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JEditorPane editor = ((StyledTextAction)this).getEditor(actionEvent);
            if (editor != null) {
                int n = this.a;
                if (actionEvent != null && actionEvent.getSource() == editor) {
                    final String actionCommand = actionEvent.getActionCommand();
                    try {
                        n = Integer.parseInt(actionCommand, 10);
                    }
                    catch (NumberFormatException ex) {}
                }
                final SimpleAttributeSet set = new SimpleAttributeSet();
                StyleConstants.setAlignment(set, n);
                ((StyledTextAction)this).setParagraphAttributes(editor, set, false);
            }
        }
    }
    
    public static class BoldAction extends StyledTextAction
    {
        public BoldAction() {
            super("font-bold");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JEditorPane editor = ((StyledTextAction)this).getEditor(actionEvent);
            if (editor != null) {
                final boolean b = !StyleConstants.isBold(((StyledTextAction)this).getStyledEditorKit(editor).getInputAttributes());
                final SimpleAttributeSet set = new SimpleAttributeSet();
                StyleConstants.setBold(set, b);
                ((StyledTextAction)this).setCharacterAttributes(editor, set, false);
            }
        }
    }
    
    public static class ItalicAction extends StyledTextAction
    {
        public ItalicAction() {
            super("font-italic");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JEditorPane editor = ((StyledTextAction)this).getEditor(actionEvent);
            if (editor != null) {
                final boolean b = !StyleConstants.isItalic(((StyledTextAction)this).getStyledEditorKit(editor).getInputAttributes());
                final SimpleAttributeSet set = new SimpleAttributeSet();
                StyleConstants.setItalic(set, b);
                ((StyledTextAction)this).setCharacterAttributes(editor, set, false);
            }
        }
    }
    
    public static class UnderlineAction extends StyledTextAction
    {
        public UnderlineAction() {
            super("font-underline");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JEditorPane editor = ((StyledTextAction)this).getEditor(actionEvent);
            if (editor != null) {
                final boolean b = !StyleConstants.isUnderline(((StyledTextAction)this).getStyledEditorKit(editor).getInputAttributes());
                final SimpleAttributeSet set = new SimpleAttributeSet();
                StyleConstants.setUnderline(set, b);
                ((StyledTextAction)this).setCharacterAttributes(editor, set, false);
            }
        }
    }
}
