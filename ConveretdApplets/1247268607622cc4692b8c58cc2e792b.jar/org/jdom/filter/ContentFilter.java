// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.filter;

import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.EntityRef;
import org.jdom.ProcessingInstruction;
import org.jdom.Comment;
import org.jdom.Text;
import org.jdom.CDATA;
import org.jdom.Element;

public class ContentFilter extends AbstractFilter
{
    private static final String CVS_ID = "@(#) $RCSfile: ContentFilter.java,v $ $Revision: 1.15 $ $Date: 2007/11/10 05:29:00 $ $Name: jdom_1_1_1 $";
    public static final int ELEMENT = 1;
    public static final int CDATA = 2;
    public static final int TEXT = 4;
    public static final int COMMENT = 8;
    public static final int PI = 16;
    public static final int ENTITYREF = 32;
    public static final int DOCUMENT = 64;
    public static final int DOCTYPE = 128;
    private int filterMask;
    
    public ContentFilter() {
        this.setDefaultMask();
    }
    
    public ContentFilter(final boolean allVisible) {
        if (allVisible) {
            this.setDefaultMask();
        }
        else {
            this.filterMask &= ~this.filterMask;
        }
    }
    
    public ContentFilter(final int mask) {
        this.setFilterMask(mask);
    }
    
    public int getFilterMask() {
        return this.filterMask;
    }
    
    public void setFilterMask(final int mask) {
        this.setDefaultMask();
        this.filterMask &= mask;
    }
    
    public void setDefaultMask() {
        this.filterMask = 255;
    }
    
    public void setDocumentContent() {
        this.filterMask = 153;
    }
    
    public void setElementContent() {
        this.filterMask = 63;
    }
    
    public void setElementVisible(final boolean visible) {
        if (visible) {
            this.filterMask |= 0x1;
        }
        else {
            this.filterMask &= 0xFFFFFFFE;
        }
    }
    
    public void setCDATAVisible(final boolean visible) {
        if (visible) {
            this.filterMask |= 0x2;
        }
        else {
            this.filterMask &= 0xFFFFFFFD;
        }
    }
    
    public void setTextVisible(final boolean visible) {
        if (visible) {
            this.filterMask |= 0x4;
        }
        else {
            this.filterMask &= 0xFFFFFFFB;
        }
    }
    
    public void setCommentVisible(final boolean visible) {
        if (visible) {
            this.filterMask |= 0x8;
        }
        else {
            this.filterMask &= 0xFFFFFFF7;
        }
    }
    
    public void setPIVisible(final boolean visible) {
        if (visible) {
            this.filterMask |= 0x10;
        }
        else {
            this.filterMask &= 0xFFFFFFEF;
        }
    }
    
    public void setEntityRefVisible(final boolean visible) {
        if (visible) {
            this.filterMask |= 0x20;
        }
        else {
            this.filterMask &= 0xFFFFFFDF;
        }
    }
    
    public void setDocTypeVisible(final boolean visible) {
        if (visible) {
            this.filterMask |= 0x80;
        }
        else {
            this.filterMask &= 0xFFFFFF7F;
        }
    }
    
    public boolean matches(final Object obj) {
        if (obj instanceof Element) {
            return (this.filterMask & 0x1) != 0x0;
        }
        if (obj instanceof CDATA) {
            return (this.filterMask & 0x2) != 0x0;
        }
        if (obj instanceof Text) {
            return (this.filterMask & 0x4) != 0x0;
        }
        if (obj instanceof Comment) {
            return (this.filterMask & 0x8) != 0x0;
        }
        if (obj instanceof ProcessingInstruction) {
            return (this.filterMask & 0x10) != 0x0;
        }
        if (obj instanceof EntityRef) {
            return (this.filterMask & 0x20) != 0x0;
        }
        if (obj instanceof Document) {
            return (this.filterMask & 0x40) != 0x0;
        }
        return obj instanceof DocType && (this.filterMask & 0x80) != 0x0;
    }
    
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContentFilter)) {
            return false;
        }
        final ContentFilter filter = (ContentFilter)obj;
        return this.filterMask == filter.filterMask;
    }
    
    public int hashCode() {
        return this.filterMask;
    }
}
