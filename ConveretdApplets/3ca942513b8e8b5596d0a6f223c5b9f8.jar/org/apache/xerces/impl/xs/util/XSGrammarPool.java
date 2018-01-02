// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.util;

import org.apache.xerces.impl.xs.XSModelImpl;
import org.apache.xerces.impl.xs.SchemaGrammar;
import java.util.Vector;
import org.apache.xerces.xs.XSModel;
import org.apache.xerces.util.XMLGrammarPoolImpl;

public class XSGrammarPool extends XMLGrammarPoolImpl
{
    public XSModel toXSModel() {
        final Vector<SchemaGrammar> vector = new Vector<SchemaGrammar>();
        for (int i = 0; i < super.fGrammars.length; ++i) {
            for (Entry next = super.fGrammars[i]; next != null; next = next.next) {
                if (next.desc.getGrammarType().equals("http://www.w3.org/2001/XMLSchema")) {
                    vector.addElement((SchemaGrammar)next.grammar);
                }
            }
        }
        final int size = vector.size();
        if (size == 0) {
            return null;
        }
        final SchemaGrammar[] array = new SchemaGrammar[size];
        for (int j = 0; j < size; ++j) {
            array[j] = vector.elementAt(j);
        }
        return new XSModelImpl(array);
    }
}
