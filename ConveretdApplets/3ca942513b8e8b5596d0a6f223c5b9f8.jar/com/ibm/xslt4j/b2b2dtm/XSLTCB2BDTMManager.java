// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.b2b2dtm;

import org.apache.xml.utils.XMLStringFactory;
import javax.xml.transform.stream.StreamSource;
import org.apache.xml.dtm.DTM;
import org.apache.xml.dtm.DTMWSFilter;
import javax.xml.transform.Source;
import org.apache.xalan.xsltc.dom.XSLTCDTMManager;

public class XSLTCB2BDTMManager extends XSLTCDTMManager
{
    public DTM getDTM(Source source, final boolean unique, final DTMWSFilter whiteSpaceFilter, final boolean incremental, final boolean doIndexing, final boolean hasUserReader, final int size, final boolean buildIdIndex, final boolean newNameTable) {
        if (source != null && source instanceof StreamSource) {
            final StreamSource strSource = (StreamSource)source;
            final StreamSource newSource = new StreamSource(strSource.getInputStream(), strSource.getSystemId());
            newSource.setPublicId(strSource.getPublicId());
            newSource.setReader(strSource.getReader());
            if (B2B2DTM.checkSource(newSource)) {
                final int dtmPos = this.getFirstFreeDTMID();
                final int documentID = dtmPos << 16;
                final B2B2DTM dtm = new B2B2DTM(this, newSource, documentID, whiteSpaceFilter, null, doIndexing);
                this.addDTM(dtm, dtmPos, 0);
                dtm.parse(newSource);
                return dtm;
            }
            source = newSource;
        }
        return super.getDTM(source, unique, whiteSpaceFilter, incremental, doIndexing, hasUserReader, size, buildIdIndex, newNameTable);
    }
}
