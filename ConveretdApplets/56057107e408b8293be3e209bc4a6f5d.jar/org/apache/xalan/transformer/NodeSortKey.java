// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import javax.xml.transform.TransformerException;
import javax.xml.transform.SourceLocator;
import org.apache.xml.utils.PrefixResolver;
import java.util.Locale;
import java.text.Collator;
import org.apache.xpath.XPath;

class NodeSortKey
{
    XPath m_selectPat;
    boolean m_treatAsNumbers;
    boolean m_descending;
    boolean m_caseOrderUpper;
    Collator m_col;
    Locale m_locale;
    PrefixResolver m_namespaceContext;
    TransformerImpl m_processor;
    
    NodeSortKey(final TransformerImpl transformer, final XPath selectPat, final boolean treatAsNumbers, final boolean descending, final String langValue, final boolean caseOrderUpper, final PrefixResolver namespaceContext) throws TransformerException {
        this.m_processor = transformer;
        this.m_namespaceContext = namespaceContext;
        this.m_selectPat = selectPat;
        this.m_treatAsNumbers = treatAsNumbers;
        this.m_descending = descending;
        this.m_caseOrderUpper = caseOrderUpper;
        if (langValue != null && !this.m_treatAsNumbers) {
            this.m_locale = new Locale(langValue.toUpperCase(), Locale.getDefault().getDisplayCountry());
            if (this.m_locale == null) {
                this.m_locale = Locale.getDefault();
            }
        }
        else {
            this.m_locale = Locale.getDefault();
        }
        this.m_col = Collator.getInstance(this.m_locale);
        if (this.m_col == null) {
            this.m_processor.getMsgMgr().warn(null, 8, new Object[] { langValue });
            this.m_col = Collator.getInstance();
        }
    }
}
