import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.Color;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class MathML2Box
{
    protected XMLParser XMLElementToCapsa;
    Object[] addElement;
    protected Object I;
    boolean append;
    Stack appendAttributes;
    Attributes arraycopy;
    String attributes;
    private static Class mathml2BoxClass;
    public static int stringBox;
    private Hashtable emptyHashtable;
    private static Hashtable tag2id;
    static final Object[] charAt;
    
    public MathML2Box() {
        this.XMLElementToCapsa = new XMLParser();
        this.append = false;
        this.appendAttributes = new Stack();
        this.arraycopy = new Attributes();
        this.emptyHashtable = new Hashtable();
    }
    
    public static final MathML2Box newMathML2Box() {
        try {
            return MathML2Box.mathml2BoxClass.newInstance();
        }
        catch (Exception ex) {
            throw new Error(ex);
        }
    }
    
    public static final void setMathMLToBox(final Class mathml2BoxClass) {
        MathML2Box.mathml2BoxClass = mathml2BoxClass;
    }
    
    public final synchronized AbstractBox parse(final String s) {
        XMLElement append = this.append(this.XMLElementToCapsa.parseXML(s), s);
        if (append.element_name.length() == 0) {
            if (append.children.length > 1 && (append.children[0].localName().startsWith("?xml") || append.children[0].localName().length() == 0)) {
                final XMLElement xmlElement = new XMLElement("", 1);
                xmlElement.children[0] = append.children[1];
                append = xmlElement;
            }
            if (append.children.length == 0) {
                return new EmptyBox();
            }
        }
        return this.parse(append);
    }
    
    public final synchronized AbstractBox parse(final XMLNode xmlNode) {
        this.attributes = "";
        final XMLElement xmlElement = (XMLElement)xmlNode;
        this.Z(xmlElement);
        return this.I((XMLNode)xmlElement);
    }
    
    protected final int I(final String s) {
        this.addElement = this.attributes().get(s);
        if (this.addElement == null) {
            this.addElement = MathML2Box.charAt;
        }
        return (int)this.addElement[0];
    }
    
    protected final AbstractBox I(final XMLNode xmlNode) {
        AbstractBox setAttributes;
        if (xmlNode instanceof XMLElement) {
            final XMLElement xmlElement = (XMLElement)xmlNode;
            this.pushAttributes(xmlElement);
            final AbstractBox xmlElementToCapsa = this.XMLElementToCapsa(xmlElement);
            if (!this.append) {
                this.arraycopy(xmlElementToCapsa, xmlElement);
            }
            else {
                this.append = false;
            }
            setAttributes = this.setAttributes(xmlElementToCapsa);
            this.popAttributes();
        }
        else {
            setAttributes = new TextBox(xmlNode.getText(), 0);
        }
        return setAttributes;
    }
    
    private AbstractBox XMLElementToCapsa(final XMLElement xmlElement) {
        final int i = this.I(xmlElement.localName());
        final Object[] addElement = this.addElement;
        final AbstractBox j = this.I(xmlElement);
        if (j != null) {
            return j;
        }
        AbstractBox[] array = this.addElement(xmlElement);
        final int length = array.length;
        final int intValue = (int)addElement[1];
        if (intValue == 1) {
            array = appendAttributes(array);
        }
        else if (intValue == 2) {
            final TokensVBox tokensVBox = new TokensVBox();
            tokensVBox.inicialitzaFills(array);
            array = new AbstractBox[] { tokensVBox };
        }
        else if (intValue == 3) {
            for (int k = 0; k < array.length; ++k) {
                array[k] = I(array[k]);
            }
        }
        else if (intValue == 4) {
            if (array.length != 1 || !(array[0] instanceof MultilineBox)) {
                final AbstractBox abstractBox = appendAttributes(array)[0];
                array = new AbstractBox[] { abstractBox };
                (array[0] = new MultilineBox()).inicialitzaFills(abstractBox);
            }
        }
        else if (intValue == 5) {
            if (array.length != 1 || !(array[0] instanceof TokensVBox)) {
                array = appendAttributes(array);
            }
        }
        final AbstractBox composeBox = this.composeBox(i, xmlElement.attributes, addElement, array);
        if (intValue == 0 && length == 1 && array[0] == composeBox && xmlElement.children[0] instanceof XMLElement) {
            final Hashtable attributes = ((XMLElement)xmlElement.children[0]).attributes;
            if (attributes != null) {
                if (xmlElement.attributes != null) {
                    xmlElement.attributes.remove("id");
                    final Enumeration<Object> keys = xmlElement.attributes.keys();
                    while (keys.hasMoreElements()) {
                        final Object nextElement = keys.nextElement();
                        attributes.put(nextElement, xmlElement.attributes.get(nextElement));
                    }
                }
                xmlElement.attributes = attributes;
            }
        }
        return composeBox;
    }
    
    protected final AbstractBox[] addElement(final XMLElement xmlElement) {
        final int n = (xmlElement.children == null) ? 0 : xmlElement.children.length;
        final AbstractBox[] array = new AbstractBox[n];
        for (int i = 0; i < n; ++i) {
            try {
                array[i] = this.I(xmlElement.children[i]);
            }
            catch (Throwable t) {
                t.printStackTrace();
                array[i] = new TextBox(xmlElement.children[i].getText(), 0);
            }
        }
        return array;
    }
    
    private AbstractBox composeBox(final int n, final Hashtable hashtable, final Object[] array, AbstractBox[] array2) {
        final AbstractBox i = this.I(n, hashtable, array, array2);
        if (i != null) {
            return i;
        }
        AbstractBox abstractBox;
        try {
            final Class j = this.I(n);
            if (j != null) {
                abstractBox = j.getConstructor((Class<?>[])null).newInstance((Object[])null);
                if (this.I != null) {
                    abstractBox.inicialitzaModalitat(this.I);
                }
                int k = abstractBox.nombreMinimDeFills() - abstractBox.nfills - array2.length;
                if (k > 0) {
                    final AbstractBox[] array3 = new AbstractBox[array2.length + k];
                    System.arraycopy(array2, 0, array3, 0, array2.length);
                    while (k > 0) {
                        array3[array2.length + --k] = new EmptyBox();
                    }
                    array2 = array3;
                }
                if (array2.length == 0) {
                    abstractBox.inicialitzaFills();
                }
                else {
                    abstractBox.inicialitzaFills(array2);
                }
            }
            else {
                if (array2.length == 0) {
                    return new EmptyBox();
                }
                if (array2.length == 1) {
                    return array2[0];
                }
                abstractBox = new TokensBox();
                abstractBox.inicialitzaFills(array2);
            }
            abstractBox.exchangeProperties(this.emptyHashtable, 0);
            abstractBox.exchangeInheritableProperties(this.emptyHashtable, 0);
        }
        catch (Exception ex) {
            abstractBox = new EmptyBox();
        }
        return abstractBox;
    }
    
    protected AbstractBox I(final XMLElement xmlElement) {
        return null;
    }
    
    protected AbstractBox I(final int n, final Hashtable hashtable, final Object[] array, final AbstractBox[] array2) {
        return null;
    }
    
    protected String I() {
        return "";
    }
    
    protected Class I(final int n) {
        this.I = null;
        return null;
    }
    
    protected XMLNode Z(final XMLElement xmlElement) {
        return xmlElement;
    }
    
    protected final XMLElement append(final XMLElement xmlElement, final String s) {
        return xmlElement;
    }
    
    protected final void I(final XMLNode[] array, final int n, final int n2, final XMLElement xmlElement) {
        for (int i = 0; i < n2; ++i) {
            this.appendAttributes((XMLElement)array[i + n], xmlElement, false);
        }
    }
    
    protected final boolean I(final XMLNode xmlNode, final String s) {
        try {
            if (xmlNode instanceof XMLElement && xmlNode.localName().equals("mo")) {
                return ((XMLElement)xmlNode).children[0].getText().equals(s);
            }
        }
        catch (Throwable t) {}
        return false;
    }
    
    protected final XMLElement Z(final XMLNode xmlNode, final String s) {
        if (xmlNode instanceof XMLElement && xmlNode.localName().equals(s)) {
            return (XMLElement)xmlNode;
        }
        return null;
    }
    
    protected final XMLElement mrow(final XMLNode[] array, final int n, int max) {
        max = Math.max(max, 0);
        final XMLElement xmlElement = new XMLElement("mrow", max);
        System.arraycopy(array, n, xmlElement.children, 0, max);
        return xmlElement;
    }
    
    private static final AbstractBox[] appendAttributes(AbstractBox[] array) {
        if (array.length == 1 && (array[0] instanceof TokensBox || array[0] instanceof EmptyBox || array[0] instanceof MultilineBox)) {
            return array;
        }
        AbstractBox abstractBox;
        if (array.length > 0) {
            abstractBox = new TokensBox();
            abstractBox.inicialitzaFills(array);
        }
        else {
            abstractBox = new EmptyBox();
        }
        array = new AbstractBox[] { abstractBox };
        return array;
    }
    
    protected static final AbstractBox I(final AbstractBox abstractBox) {
        if (!(abstractBox instanceof EmptyBox) && !(abstractBox instanceof TokensBox)) {
            final TokensBox tokensBox = new TokensBox();
            tokensBox.inicialitzaFills(abstractBox);
            return tokensBox;
        }
        return abstractBox;
    }
    
    protected static final AbstractBox Z(AbstractBox i) {
        if (i instanceof MultilineBox) {
            return i;
        }
        i = I(i);
        final MultilineBox multilineBox = new MultilineBox();
        multilineBox.inicialitzaFills(i);
        return multilineBox;
    }
    
    private AbstractBox setAttributes(final AbstractBox abstractBox) {
        abstractBox.setAttributes(Attributes.join(this.arraycopy, abstractBox.getAttributes()));
        return abstractBox;
    }
    
    protected final void Z(final int n) {
        this.appendAttributes.push(new Attributes(this.arraycopy));
        final Attributes arraycopy = this.arraycopy;
        arraycopy.estil |= n;
        final Attributes arraycopy2 = this.arraycopy;
        arraycopy2.quins_estil |= n;
    }
    
    protected final void Z() {
        this.arraycopy = this.appendAttributes.pop();
    }
    
    private void pushAttributes(final XMLElement xmlElement) {
        this.appendAttributes.push(new Attributes(this.arraycopy));
        if (xmlElement.attributes == null) {
            return;
        }
        final String s = xmlElement.attributes.get("mathcolor");
        if (s != null) {
            this.arraycopy.color = new Color(Integer.decode(s));
        }
        final String s2 = xmlElement.attributes.get("style");
        if (s2 != null) {
            final Hashtable string2styles = Attributes.string2styles(s2);
            final String s3 = string2styles.get("color");
            if (s3 != null) {
                this.arraycopy.color = new Color(Integer.decode(s3));
            }
            final String fontName = string2styles.get("font-family");
            final String size = string2styles.get("font-size");
            final String s4 = string2styles.get("font-weight");
            final String s5 = string2styles.get("font-style");
            if (fontName != null || s4 != null || s5 != null) {
                this.arraycopy.setBold(false);
                this.arraycopy.setItalic(false);
            }
            if (fontName != null) {
                this.arraycopy.setFontName(fontName);
            }
            if (s4 != null) {
                this.arraycopy.setBold(s4.equalsIgnoreCase("bold"));
            }
            if (s5 != null) {
                this.arraycopy.setItalic(s5.equalsIgnoreCase("italic"));
            }
            if (size != null) {
                this.arraycopy.setSize(size);
            }
        }
        final String size2 = xmlElement.attributes.get("mathsize");
        if (size2 != null) {
            this.arraycopy.setSize(size2);
        }
        final String s6 = xmlElement.attributes.get("mathvariant");
        if (s6 != null) {
            this.arraycopy.loadMathvariant(s6, false);
        }
        final String s7 = xmlElement.attributes.get("displaystyle");
        if (s7 != null) {
            final Attributes arraycopy = this.arraycopy;
            arraycopy.quins_estil |= 0x100;
            if (!s7.equalsIgnoreCase("true")) {
                final Attributes arraycopy2 = this.arraycopy;
                arraycopy2.estil |= 0x100;
            }
            else {
                final Attributes arraycopy3 = this.arraycopy;
                arraycopy3.estil &= 0xFFFFFEFF;
            }
        }
        final String s8 = xmlElement.attributes.get("display");
        if (s8 != null) {
            final Attributes arraycopy4 = this.arraycopy;
            arraycopy4.quins_estil |= 0x100;
            if (!s8.equalsIgnoreCase("block")) {
                final Attributes arraycopy5 = this.arraycopy;
                arraycopy5.estil |= 0x100;
            }
            else {
                final Attributes arraycopy6 = this.arraycopy;
                arraycopy6.estil &= 0xFFFFFEFF;
            }
        }
    }
    
    private void popAttributes() {
        this.arraycopy = this.appendAttributes.pop();
    }
    
    private final void arraycopy(final AbstractBox abstractBox, final XMLElement xmlElement) {
        Hashtable hashtable = xmlElement.attributes;
        if (hashtable == null) {
            hashtable = this.emptyHashtable;
        }
        final String s = hashtable.get("style");
        if (s != null) {
            Attributes.exchangeStyles(abstractBox, s);
        }
        abstractBox.exchangeProperties(hashtable, 0);
        abstractBox.exchangeInheritableProperties(hashtable, 0);
    }
    
    protected final XMLElement I(final String s, final XMLElement xmlElement) {
        String s2 = null;
        String string = "";
        final Vector vector = new Vector<XMLElement>();
        final int length = xmlElement.children.length;
        int max = 0;
        for (int i = 0; i < length; ++i) {
            max = Math.max(max, ((XMLElement)xmlElement.children[i]).children.length);
        }
        for (int j = 0; j < length; ++j) {
            final XMLElement xmlElement2 = (XMLElement)xmlElement.children[j];
            String s3 = (xmlElement2.attributes != null) ? xmlElement2.attributes.get("height") : null;
            if (s3 == null && string.length() > 0) {
                s3 = s2;
            }
            if (s3 != null) {
                string = string + s3 + " ";
                s2 = s3;
            }
            for (int k = 0; k < xmlElement2.children.length; ++k) {
                final XMLElement xmlElement3 = (XMLElement)xmlElement2.children[k];
                if (xmlElement3.children.length == 1) {
                    vector.addElement((XMLElement)xmlElement3.children[0]);
                }
                else {
                    final XMLElement xmlElement4 = new XMLElement("mrow", xmlElement3.children.length);
                    System.arraycopy(xmlElement3.children, 0, xmlElement4.children, 0, xmlElement4.children.length);
                    vector.addElement(xmlElement4);
                }
            }
            for (int l = xmlElement2.children.length; l < max; ++l) {
                vector.addElement((XMLElement)this.getBlankBox());
            }
        }
        final XMLElement xmlElement5 = new XMLElement(s, vector.size());
        (xmlElement5.attributes = new Hashtable()).put("rows", "" + length);
        xmlElement5.attributes.put("cells", "" + max);
        if (string.length() > 0) {
            xmlElement5.attributes.put("rowheight", string);
        }
        vector.copyInto(xmlElement5.children);
        return xmlElement5;
    }
    
    private XMLNode getBlankBox() {
        final XMLText xmlText = new XMLText();
        xmlText.element_name = "text";
        xmlText.text = " ";
        final XMLElement xmlElement = new XMLElement("mo", 1);
        xmlElement.children[0] = xmlText;
        return xmlElement;
    }
    
    public final void appendAttributes(final XMLElement xmlElement, final XMLElement xmlElement2) {
        this.appendAttributes(xmlElement, xmlElement2, true);
    }
    
    public final void appendAttributes(final XMLElement xmlElement, final XMLElement xmlElement2, final boolean b) {
        if (xmlElement2.attributes == null) {
            return;
        }
        if (xmlElement.attributes == null) {
            xmlElement.attributes = new Hashtable();
        }
        final Enumeration<String> keys = (Enumeration<String>)xmlElement2.attributes.keys();
        final Enumeration<String> elements = xmlElement2.attributes.elements();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final String s2 = elements.nextElement();
            if (!b && xmlElement.attributes.get(s) != null) {
                continue;
            }
            xmlElement.attributes.put(s, s2);
        }
    }
    
    public static final String char2MathML(char c, final String s) {
        if (c < ' ') {
            return "";
        }
        if (c == ' ') {
            return "<mo>&nbsp;</mo>";
        }
        if (Character.isDigit(c)) {
            return "<mn>" + c + "</mn>";
        }
        if ((s != null && s.indexOf("" + c) >= 0) || (Character.isLetter(c) && c != 'º') || c == '_' || FontUtils.isAlwaysText(c)) {
            return "<mi>" + c + "</mi>";
        }
        if (c == 'º') {
            c = '°';
        }
        return "<mo>" + XMLParser.string2CharData("" + c) + "</mo>";
    }
    
    public static final String char2Html(final char c) {
        if (c < ' ') {
            return "";
        }
        if (c == ' ') {
            return "<p>&#32;</p>";
        }
        return "<p>" + XMLParser.string2CharData("" + c) + "</p>";
    }
    
    public final AbstractBox string(final String s) {
        final String s2 = "</math>";
        int n = 0;
        int n2 = 0;
        final Vector vector = new Vector<TextBox>();
        final int length = s.length();
        int i = 0;
        int n3 = 0;
        while (i < length) {
            final char char1 = s.charAt(i);
            if (n == 0 && (char1 == ' ' || char1 == ' ')) {
                if (i > n3) {
                    vector.addElement(new TextBox(s.substring(n3, i), MathML2Box.stringBox));
                }
                vector.addElement((TextBox)new SpaceBox(0));
                n3 = i + 1;
            }
            else if (char1 == '<') {
                if (s.startsWith("<math", i)) {
                    if (n == 0) {
                        n2 = i;
                    }
                    ++n;
                }
                else if (s.startsWith(s2, i) && --n == 0) {
                    final int n4 = i + s2.length();
                    if (n2 > n3) {
                        vector.addElement(new TextBox(s.substring(n3, n2), MathML2Box.stringBox));
                    }
                    vector.addElement((TextBox)this.parse(s.substring(n2, n4)));
                    n3 = n4;
                    i = n4 - 1;
                }
            }
            ++i;
        }
        if (i > n3) {
            vector.addElement(new TextBox(s.substring(n3, i), MathML2Box.stringBox));
        }
        if (vector.size() == 0) {
            return new EmptyBox();
        }
        final AbstractBox[] array = new AbstractBox[vector.size()];
        final TokensBox tokensBox = new TokensBox();
        vector.copyInto(array);
        tokensBox.inicialitzaFills(array);
        return tokensBox;
    }
    
    public final String getVersion() {
        return this.attributes;
    }
    
    protected final void I(final boolean append) {
        this.append = append;
    }
    
    protected final Hashtable attributes() {
        if (MathML2Box.tag2id == null) {
            final String string = "math@100@1@mfrac@101@0@mn@102@0@mi@103@0@mo@104@0@mrow@105@1@cn@106@0@mfenced@107@5@apply@108@3@abs@109@3@norm@110@3@ceiling@111@3@floor@112@3@msqrt@120@3@mroot@121@3@munder@122@0@diff@123@3@bvar@124@0@scalarproduct@125@3@maction@126@0@csymbol@127@0@mover@128@0@semantics@129@3@annotation-xml@130@0@mtable@131@0@menclose@132@3@mmultiscripts@133@0@ms@134@0@merror@135@3@mstyle@136@1@mtext@137@0@mspace@138@0@mphantom@139@0@" + this.I();
            MathML2Box.tag2id = new Hashtable();
            final StringTokenizer stringTokenizer = new StringTokenizer(string, "@");
            while (stringTokenizer.hasMoreElements()) {
                MathML2Box.tag2id.put(stringTokenizer.nextElement(), new Object[] { Integer.decode((String)stringTokenizer.nextElement()), Integer.decode((String)stringTokenizer.nextElement()) });
            }
        }
        return MathML2Box.tag2id;
    }
    
    static {
        MathML2Box.stringBox = 4;
        charAt = new Object[] { new Integer(-1), new Integer(0) };
    }
}
