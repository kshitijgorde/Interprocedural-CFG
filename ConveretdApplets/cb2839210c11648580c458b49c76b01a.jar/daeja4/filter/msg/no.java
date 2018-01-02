// 
// Decompiled by Procyon v0.5.30
// 

package daeja4.filter.msg;

import javax.swing.text.Element;
import javax.swing.text.html.StyleSheet;
import javax.swing.text.html.HTMLDocument;

public class no extends HTMLDocument
{
    public no() {
    }
    
    public no(final StyleSheet styleSheet) {
        super(styleSheet);
    }
    
    public Element a() throws Exception {
        final Element defaultRootElement = this.getDefaultRootElement();
        Element element = null;
        if (defaultRootElement != null) {
            final int elementCount = defaultRootElement.getElementCount();
            if (!"HTML".equalsIgnoreCase(defaultRootElement.getName())) {
                throw new Exception(String.valueOf(String.valueOf(new StringBuffer("Invalid document: no root HTML element.  Found: '").append(defaultRootElement.getName()).append("'"))));
            }
            for (int i = 0; i < elementCount; ++i) {
                element = defaultRootElement.getElement(i);
                if ("BODY".equalsIgnoreCase(element.getName())) {
                    break;
                }
                element = null;
            }
        }
        return element;
    }
    
    public void a(final String s) throws Exception {
        final Element a = this.a();
        if (a != null) {
            this.insertAfterStart(a, s);
            return;
        }
        throw new Exception("Invalid document.");
    }
}
