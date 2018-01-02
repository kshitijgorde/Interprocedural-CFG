// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;

public class XSGrammarBucket
{
    Hashtable fGrammarRegistry;
    SchemaGrammar fNoNSGrammar;
    
    public XSGrammarBucket() {
        this.fGrammarRegistry = new Hashtable();
        this.fNoNSGrammar = null;
    }
    
    public SchemaGrammar getGrammar(final String namespace) {
        if (namespace == null) {
            return this.fNoNSGrammar;
        }
        return this.fGrammarRegistry.get(namespace);
    }
    
    public void putGrammar(final SchemaGrammar grammar) {
        if (grammar.getTargetNamespace() == null) {
            this.fNoNSGrammar = grammar;
        }
        else {
            this.fGrammarRegistry.put(grammar.getTargetNamespace(), grammar);
        }
    }
    
    public boolean putGrammar(final SchemaGrammar grammar, final boolean deep) {
        final SchemaGrammar sg = this.getGrammar(grammar.fTargetNamespace);
        if (sg != null) {
            return sg == grammar;
        }
        if (!deep) {
            this.putGrammar(grammar);
            return true;
        }
        final Vector currGrammars = grammar.getImportedGrammars();
        if (currGrammars == null) {
            this.putGrammar(grammar);
            return true;
        }
        final Vector grammars = (Vector)currGrammars.clone();
        for (int i = 0; i < grammars.size(); ++i) {
            final SchemaGrammar sg2 = grammars.elementAt(i);
            SchemaGrammar sg3 = this.getGrammar(sg2.fTargetNamespace);
            if (sg3 == null) {
                final Vector gs = sg2.getImportedGrammars();
                if (gs != null) {
                    for (int j = gs.size() - 1; j >= 0; --j) {
                        sg3 = gs.elementAt(j);
                        if (!grammars.contains(sg3)) {
                            grammars.addElement(sg3);
                        }
                    }
                }
            }
            else if (sg3 != sg2) {
                return false;
            }
        }
        this.putGrammar(grammar);
        for (int k = grammars.size() - 1; k >= 0; --k) {
            this.putGrammar(grammars.elementAt(k));
        }
        return true;
    }
    
    public SchemaGrammar[] getGrammars() {
        final int count = this.fGrammarRegistry.size() + ((this.fNoNSGrammar != null) ? 1 : 0);
        final SchemaGrammar[] grammars = new SchemaGrammar[count];
        final Enumeration enum1 = this.fGrammarRegistry.elements();
        int i = 0;
        while (enum1.hasMoreElements()) {
            grammars[i++] = enum1.nextElement();
        }
        if (this.fNoNSGrammar != null) {
            grammars[count - 1] = this.fNoNSGrammar;
        }
        return grammars;
    }
    
    public void reset() {
        this.fNoNSGrammar = null;
        this.fGrammarRegistry.clear();
    }
}
