// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import java.util.Locale;
import org.w3c.dom.DOMException;

class DomEx extends DOMException
{
    static String messageString(final Locale locale, final int code) {
        switch (code) {
            case 1: {
                return XmlDocument.catalog.getMessage(locale, "D-000");
            }
            case 2: {
                return XmlDocument.catalog.getMessage(locale, "D-001");
            }
            case 3: {
                return XmlDocument.catalog.getMessage(locale, "D-002");
            }
            case 4: {
                return XmlDocument.catalog.getMessage(locale, "D-003");
            }
            case 5: {
                return XmlDocument.catalog.getMessage(locale, "D-004");
            }
            case 6: {
                return XmlDocument.catalog.getMessage(locale, "D-005");
            }
            case 7: {
                return XmlDocument.catalog.getMessage(locale, "D-006");
            }
            case 8: {
                return XmlDocument.catalog.getMessage(locale, "D-007");
            }
            case 9: {
                return XmlDocument.catalog.getMessage(locale, "D-008");
            }
            case 10: {
                return XmlDocument.catalog.getMessage(locale, "D-009");
            }
            case 11: {
                return XmlDocument.catalog.getMessage(locale, "D-010");
            }
            case 12: {
                return XmlDocument.catalog.getMessage(locale, "D-011");
            }
            case 13: {
                return XmlDocument.catalog.getMessage(locale, "D-012");
            }
            case 14: {
                return XmlDocument.catalog.getMessage(locale, "D-013");
            }
            case 15: {
                return XmlDocument.catalog.getMessage(locale, "D-014");
            }
            default: {
                return XmlDocument.catalog.getMessage(locale, "D-900");
            }
        }
    }
    
    public DomEx(final short code) {
        super(code, messageString(Locale.getDefault(), code));
    }
    
    public DomEx(final Locale locale, final short code) {
        super(code, messageString(locale, code));
    }
}
