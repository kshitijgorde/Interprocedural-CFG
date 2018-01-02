// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.Vector;

public class NFHtmlMultiTag extends Vector
{
    public NFHtmlTag beginTag;
    public NFHtmlTag endTag;
    private static final boolean a = false;
    
    public NFHtmlMultiTag() {
        this.beginTag = null;
        this.endTag = null;
    }
    
    public static void parse(final Vector vector, final String s, final String s2) {
        Vector<NFHtmlTag> vector2 = null;
        int i = 0;
        while (i < vector.size()) {
            final NFHtmlTag element = vector.elementAt(i);
            if (element instanceof NFHtmlTag) {
                final NFHtmlTag nfHtmlTag = element;
                if (vector2 != null) {
                    if (nfHtmlTag.name.equalsIgnoreCase(s2)) {
                        ((NFHtmlMultiTag)vector2).endTag = nfHtmlTag;
                        vector.removeElementAt(i);
                        vector.insertElementAt(vector2, i);
                        ++i;
                        vector2 = null;
                    }
                    else {
                        vector2.addElement(nfHtmlTag);
                        vector.removeElementAt(i);
                    }
                }
                else if (!nfHtmlTag.name.equalsIgnoreCase(s)) {
                    ++i;
                }
                else {
                    vector2 = (Vector<NFHtmlTag>)new NFHtmlMultiTag();
                    ((NFHtmlMultiTag)vector2).beginTag = nfHtmlTag;
                    vector.removeElementAt(i);
                }
            }
            else {
                if (element instanceof NFHtmlMultiTag) {
                    parse((Vector)element, s, s2);
                }
                if (vector2 != null) {
                    vector2.addElement(element);
                    vector.removeElementAt(i);
                }
                else {
                    ++i;
                }
            }
        }
    }
}
