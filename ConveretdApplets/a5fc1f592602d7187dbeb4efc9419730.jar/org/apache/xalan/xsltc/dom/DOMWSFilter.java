// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import org.apache.xalan.xsltc.DOMEnhancedForDTM;
import org.apache.xalan.xsltc.DOM;
import org.apache.xml.dtm.DTM;
import org.apache.xalan.xsltc.runtime.Hashtable;
import org.apache.xalan.xsltc.StripFilter;
import org.apache.xalan.xsltc.runtime.AbstractTranslet;
import org.apache.xml.dtm.DTMWSFilter;

public class DOMWSFilter implements DTMWSFilter
{
    private AbstractTranslet m_translet;
    private StripFilter m_filter;
    private Hashtable m_mappings;
    private DTM m_currentDTM;
    private short[] m_currentMapping;
    
    public DOMWSFilter(final AbstractTranslet translet) {
        this.m_translet = translet;
        this.m_mappings = new Hashtable();
        if (translet instanceof StripFilter) {
            this.m_filter = (StripFilter)translet;
        }
    }
    
    public short getShouldStripSpace(final int node, final DTM dtm) {
        if (this.m_filter == null || !(dtm instanceof DOM)) {
            return 1;
        }
        final DOM dom = (DOM)dtm;
        int type = 0;
        if (!(dtm instanceof DOMEnhancedForDTM)) {
            return 3;
        }
        final DOMEnhancedForDTM mappableDOM = (DOMEnhancedForDTM)dtm;
        short[] mapping;
        if (dtm == this.m_currentDTM) {
            mapping = this.m_currentMapping;
        }
        else {
            mapping = (short[])this.m_mappings.get(dtm);
            if (mapping == null) {
                mapping = mappableDOM.getMapping(this.m_translet.getNamesArray(), this.m_translet.getUrisArray(), this.m_translet.getTypesArray());
                this.m_mappings.put(dtm, mapping);
                this.m_currentDTM = dtm;
                this.m_currentMapping = mapping;
            }
        }
        final int expType = mappableDOM.getExpandedTypeID(node);
        if (expType >= 0 && expType < mapping.length) {
            type = mapping[expType];
        }
        else {
            type = -1;
        }
        if (this.m_filter.stripSpace(dom, node, type)) {
            return 2;
        }
        return 1;
    }
}
