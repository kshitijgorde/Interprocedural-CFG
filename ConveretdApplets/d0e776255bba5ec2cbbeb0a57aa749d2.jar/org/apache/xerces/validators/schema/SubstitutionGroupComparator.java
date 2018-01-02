// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.schema;

import org.xml.sax.SAXException;
import org.apache.xerces.utils.QName;
import org.apache.xerces.validators.common.GrammarResolver;
import org.apache.xerces.utils.StringPool;

public class SubstitutionGroupComparator
{
    private final int TOP_LEVEL_SCOPE = -1;
    private StringPool fStringPool;
    private GrammarResolver fGrammarResolver;
    
    private SubstitutionGroupComparator() {
        this.fStringPool = null;
        this.fGrammarResolver = null;
    }
    
    public SubstitutionGroupComparator(final GrammarResolver fGrammarResolver, final StringPool fStringPool) {
        this.fStringPool = null;
        this.fGrammarResolver = null;
        this.fGrammarResolver = fGrammarResolver;
        this.fStringPool = fStringPool;
    }
    
    public boolean isEquivalentTo(final QName qName, final QName qName2) throws Exception {
        if (qName.localpart == qName2.localpart && qName.uri == qName2.uri) {
            return true;
        }
        if (this.fGrammarResolver == null || this.fStringPool == null) {
            throw new SAXException("Try to check substitutionGroup against a substitutionGroup, but no GrammarResolver is defined");
        }
        int i = 16;
        int n = qName.uri;
        int n2 = qName.localpart;
        String s = this.fStringPool.toString(qName.uri);
        this.fStringPool.toString(qName.localpart);
        while (i >= 0) {
            if (s == null) {
                return false;
            }
            SchemaGrammar schemaGrammar;
            try {
                schemaGrammar = (SchemaGrammar)this.fGrammarResolver.getGrammar(s);
            }
            catch (ClassCastException ex) {
                return false;
            }
            if (schemaGrammar == null) {
                return false;
            }
            final int elementDeclIndex = schemaGrammar.getElementDeclIndex(n, n2, -1);
            if (elementDeclIndex == -1) {
                return false;
            }
            final String elementDeclSubstitutionGroupElementFullName = schemaGrammar.getElementDeclSubstitutionGroupElementFullName(elementDeclIndex);
            if (elementDeclSubstitutionGroupElementFullName == null) {
                return false;
            }
            final int index = elementDeclSubstitutionGroupElementFullName.indexOf(",");
            s = "";
            String substring = elementDeclSubstitutionGroupElementFullName;
            if (index >= 0) {
                if (index > 0) {
                    s = elementDeclSubstitutionGroupElementFullName.substring(0, index);
                }
                substring = elementDeclSubstitutionGroupElementFullName.substring(index + 1);
            }
            n = this.fStringPool.addSymbol(s);
            n2 = this.fStringPool.addSymbol(substring);
            if (n == qName2.uri && n2 == qName2.localpart) {
                return true;
            }
            --i;
        }
        return false;
    }
}
