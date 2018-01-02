import java.util.Hashtable;
import java.util.Stack;

// 
// Decompiled by Procyon v0.5.30
// 

public class BoxScripting
{
    public int scriptMode;
    public int scriptVariant;
    private XMLOutput out;
    private ScriptOutput outl;
    Stack XMLTag;
    Stack appendScript;
    Stack appendText;
    int attributeXML;
    public boolean oneChild;
    AbstractBox beginBox;
    public boolean ignoreID;
    private int pos;
    private int[] columns;
    private AbstractBox[] boxes;
    private AbstractBox last;
    private boolean is_top;
    Object c;
    BoxPosition countChildren;
    int[] doInnerFormat;
    static Class doOuterFormat_begin;
    
    public BoxScripting(final int n, final String s, final String s2, final int[] columns) {
        this(n, s, s2);
        this.columns = columns;
        this.boxes = new AbstractBox[columns.length];
    }
    
    public BoxScripting(final int scriptMode, final String s, final String s2) {
        this.scriptVariant = 1;
        this.oneChild = true;
        this.ignoreID = false;
        if (scriptMode == 2 && this.getClass() == ((BoxScripting.doOuterFormat_begin == null) ? (BoxScripting.doOuterFormat_begin = XMLTag("BoxScripting")) : BoxScripting.doOuterFormat_begin)) {
            throw new Error("This class does not implement WIRIS mode.");
        }
        this.scriptMode = scriptMode;
        this.appendText = new Stack();
        if (scriptMode == 1) {
            this.out = new XMLOutput(s, s2);
            this.XMLTag = new Stack();
            this.appendScript = new Stack();
            this.attributeXML = 0;
        }
        else {
            this.outl = new ScriptOutput(scriptMode);
        }
        this.is_top = true;
    }
    
    public BoxScripting(final int n) {
        this(n, "", "");
    }
    
    public final String toString() {
        if (this.scriptMode == 1) {
            return this.out.toString();
        }
        return this.outl.toString();
    }
    
    public final void appendAttribute(final String s, final String s2) {
        if (this.ignoreID && "id".equals(s)) {
            return;
        }
        this.out.attributeXML(s, s2);
    }
    
    public final void appendText(final String s) {
        this.appendText(s, -1);
    }
    
    public final void appendText(final String s, final int n) {
        if (!this.appendText.isEmpty()) {
            this.testPosition(this.appendText.peek());
        }
        if (this.scriptMode == 1) {
            this.out.textXML(XMLParser.string2CharData(s));
        }
        else {
            this.outl.appendText(s, n);
        }
    }
    
    public final void appendScript(final String s) {
        if (this.scriptMode == 1) {
            if (this.XMLTag.size() == 0) {
                final int attributeXML = this.attributeXML;
                final XMLOutput out = this.out;
                this.attributeXML = attributeXML + XMLOutput.countChildren(s);
            }
            this.out.I(s);
        }
        else {
            this.outl.appendScript(s);
        }
    }
    
    public final void closeTag() {
        if (this.scriptMode == 1) {
            final String s = this.XMLTag.pop();
            this.appendScript.pop();
            this.out.XMLTag(1, s);
        }
    }
    
    public final void beginInheritFormat(final AbstractBox abstractBox) {
        if (this.out != null) {
            this.out.pushFormat();
            this.out.setFormat(abstractBox, null);
        }
    }
    
    public final void endInheritFormat(final AbstractBox abstractBox) {
        if (this.out != null) {
            this.out.popFormat();
        }
    }
    
    public final void openTag(final String s) {
        if (this.scriptMode == 1) {
            this.oneChild = false;
            this.is_top = false;
            if (this.XMLTag.size() == 0) {
                ++this.attributeXML;
            }
            else {
                this.appendScript.setElementAt(new Integer(this.appendScript.peek() + 1), this.appendScript.size() - 1);
            }
            this.XMLTag.push(s);
            this.appendScript.push(new Integer(0));
            this.out.XMLTag(0, s);
            if (this.beginBox != null) {
                this.beginBox.scriptId(this);
            }
            this.beginBox = null;
        }
        else {
            this.outl.appendScript(s);
        }
    }
    
    private void testPosition(final AbstractBox abstractBox) {
        if (this.columns != null) {
            int n;
            if (this.scriptMode == 1) {
                n = this.out.I();
            }
            else {
                n = this.outl.getPosition();
            }
            if (this.pos < this.columns.length && this.columns[this.pos] >= n) {
                this.boxes[this.pos] = abstractBox;
            }
            if (this.pos < this.columns.length && this.columns[this.pos] <= n) {
                ++this.pos;
            }
        }
    }
    
    private void testCaret(final AbstractBox abstractBox) {
        if (this.scriptVariant == 2 && this.countChildren != null && abstractBox == this.countChildren.c) {
            final int size = this.XMLTag.size();
            this.doInnerFormat = new int[size];
            for (int i = 0; i < size; ++i) {
                this.doInnerFormat[i] = (int)this.appendScript.elementAt(i);
            }
            this.doInnerFormat[size - 1] = this.countChildren.x;
        }
        if (this.scriptVariant == 3 && this.doInnerFormat != null) {
            final boolean b = abstractBox instanceof TextBox;
            int length = this.doInnerFormat.length;
            if (this.appendScript.size() == length) {
                --length;
                int n;
                for (n = 0; n < length && this.doInnerFormat[n] == (int)this.appendScript.elementAt(n); ++n) {}
                if (n == length) {
                    if (abstractBox instanceof TextBox || abstractBox instanceof TokensBox || abstractBox instanceof EmptyBox) {
                        this.countChildren = new BoxPosition(abstractBox, this.doInnerFormat[length]);
                    }
                    else {
                        this.countChildren = new BoxPosition(abstractBox.fill[this.doInnerFormat[length]], 0);
                    }
                }
            }
        }
    }
    
    public final void beginBox(final AbstractBox abstractBox, final String s) {
        this.appendText.push(abstractBox);
        this.testPosition(abstractBox);
        if (this.scriptMode == 1) {
            XMLBoxUtils.beginBox(this, abstractBox, s);
        }
        else {
            this.outl.beginBox(this, abstractBox, s);
        }
        this.testCaret(abstractBox);
    }
    
    public final void endBox(final AbstractBox last, final String s) {
        if (this.scriptMode == 1) {
            XMLBoxUtils.endBox(this, last, s);
        }
        else {
            this.outl.endBox(this, last, s);
        }
        this.appendText.pop();
        this.last = last;
    }
    
    public final boolean isTop() {
        return this.is_top;
    }
    
    private static final String middle(final BoxComponent boxComponent, final AbstractBox abstractBox, final int n, final int n2) {
        final BoxScripting boxScripting = boxComponent.getBoxScripting();
        abstractBox.script(boxScripting, n, n2);
        return boxScripting.toString();
    }
    
    public static final String left(final BoxComponent boxComponent, final AbstractBox abstractBox, final int n) {
        return middle(boxComponent, abstractBox, 0, n);
    }
    
    public static final String right(final BoxComponent boxComponent, final AbstractBox abstractBox, final int n) {
        return middle(boxComponent, abstractBox, n, abstractBox.nPosicions());
    }
    
    public final AbstractBox[] getBoxes() {
        return this.boxes;
    }
    
    public final void properties(final Hashtable hashtable) {
        Attributes.propertiesXML(this.out, hashtable);
    }
    
    public final void beginOuterFormat(final AbstractBox abstractBox, final String s) {
        if (this.scriptMode == 1) {
            this.out.pushFormat();
            this.out.doOuterFormat_begin(abstractBox, s);
        }
    }
    
    public final void endOuterFormat(final AbstractBox abstractBox, final String s) {
        if (this.scriptMode == 1) {
            this.out.doOuterFormat_end(s);
            this.out.popFormat();
        }
    }
    
    public final void beginInnerFormat(final AbstractBox abstractBox, final String s) {
        if (this.scriptMode == 1) {
            this.out.doInnerFormat(abstractBox, s);
            this.out.setFormat(abstractBox, s);
        }
    }
    
    public final void endInnerFormat(final AbstractBox abstractBox, final String s) {
        if (this.scriptMode == 1) {}
    }
    
    public final void setNameSpace(final String namespaceURL) {
        if (this.scriptMode == 1) {
            this.out.namespaceURL = namespaceURL;
        }
    }
    
    public void onScript(final AbstractBox abstractBox, final AbstractBox[] array, final int n, final int n2) {
        if (this.scriptMode == 1) {
            XMLBoxUtils.onScript(this, array, n, n2, true);
        }
        else {
            ScriptOutput.onScript(this, array, n, n2);
        }
    }
    
    public final AbstractBox parse(final String s) {
        if (this.c == null) {
            if (this.scriptMode == 1) {
                this.c = MathML2Box.newMathML2Box();
            }
            else {
                this.c = Latex2Box.newLatex2Box();
            }
        }
        AbstractBox abstractBox;
        if (this.scriptMode == 1) {
            abstractBox = ((MathML2Box)this.c).parse(s);
        }
        else {
            abstractBox = ((Latex2Box)this.c).parse(s);
        }
        return abstractBox;
    }
    
    public final void findCoordinates(final BoxPosition countChildren) {
        this.scriptVariant = 2;
        this.countChildren = countChildren;
    }
    
    public final BoxPosition getPosition() {
        return this.countChildren;
    }
    
    public final void findPosition(final int[] doInnerFormat) {
        this.scriptVariant = 3;
        this.doInnerFormat = doInnerFormat;
    }
    
    public final int[] getCoordinates() {
        return this.doInnerFormat;
    }
    
    public final String getVersion() {
        if (this.c instanceof MathML2Box) {
            return ((MathML2Box)this.c).getVersion();
        }
        return "";
    }
    
    private static final Class XMLTag(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
