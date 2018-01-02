// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.xml.sax.SAXException;
import java.util.Vector;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.templates.WhiteSpaceInfo;
import org.apache.xpath.XPath;
import org.apache.xalan.templates.ElemTemplateElement;
import org.xml.sax.Attributes;

class ProcessorStripSpace extends ProcessorPreserveSpace
{
    static final long serialVersionUID = -5594493198637899591L;
    
    public void startElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName, final Attributes attributes) throws SAXException {
        final Stylesheet thisSheet = handler.getStylesheet();
        final WhitespaceInfoPaths paths = new WhitespaceInfoPaths(thisSheet);
        this.setPropertiesFromAttributes(handler, rawName, attributes, paths);
        final Vector xpaths = paths.getElements();
        for (int i = 0; i < xpaths.size(); ++i) {
            final WhiteSpaceInfo wsi = new WhiteSpaceInfo(xpaths.elementAt(i), true, thisSheet);
            wsi.setUid(handler.nextUid());
            thisSheet.setStripSpaces(wsi);
        }
        paths.clearElements();
    }
}
