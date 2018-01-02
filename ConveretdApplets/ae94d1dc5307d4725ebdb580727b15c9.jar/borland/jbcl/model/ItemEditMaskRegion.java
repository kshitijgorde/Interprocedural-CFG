// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.util.ResourceBundle;
import java.util.Vector;

public class ItemEditMaskRegion implements ItemEditMaskRegionChar
{
    ItemEditMaskStr ems;
    boolean optional;
    int capacity;
    int minRequired;
    int offset;
    Vector charObjects;
    boolean rightToLeft;
    int charCount;
    char c;
    
    public ItemEditMaskRegion(final ItemEditMaskStr ems, final boolean optional) {
        this.ems = ems;
        this.optional = optional;
        this.charObjects = new Vector(0);
        this.capacity = 0;
        this.rightToLeft = true;
        this.charCount = 0;
        this.minRequired = 0;
        ItemEditMaskRegion emr = null;
        this.offset = 0;
        if (ems.editRegions != null) {
            final int iLen = ems.editRegions.size();
            if (iLen > 0) {
                emr = ems.editRegions.elementAt(iLen - 1);
                if (emr != null) {
                    this.offset = emr.offset + emr.capacity;
                }
            }
        }
    }
    
    public boolean isValid(final int charPosition, final char c) {
        final ItemEditMaskCharObj emo = this.emoFromPosition(charPosition);
        return emo != null && emo.isValid(c);
    }
    
    public boolean isOptional(final int charPosition) {
        boolean bResult = true;
        if (this.optional) {
            return bResult;
        }
        final ItemEditMaskCharObj emo = this.emoFromPosition(charPosition);
        if (emo != null && emo.isOptional()) {
            bResult = true;
        }
        else if (this.minRequired > 0) {
            final int relPos = charPosition - this.offset;
            if (relPos < 0) {
                return true;
            }
            if (relPos >= this.capacity) {
                return true;
            }
            if (this.rightToLeft) {
                bResult = (this.capacity - relPos > this.minRequired);
            }
            else {
                bResult = (relPos > this.minRequired - 1);
            }
        }
        else {
            bResult = false;
        }
        return bResult;
    }
    
    public char setCharAt(final StringBuffer str, final int charPosition, final char c) {
        str.setCharAt(charPosition, c);
        return c;
    }
    
    public char getCharAt(final StringBuffer str, final int charPosition) {
        final char c = str.charAt(charPosition);
        return c;
    }
    
    public boolean isLiteral(final int charPosition) {
        return false;
    }
    
    public void deleteCharAt(final StringBuffer str, final int charPosition, final char blankChar) {
        this.setCharAt(str, charPosition, blankChar);
    }
    
    protected final ItemEditMaskCharObj emoFromPosition(final int charPosition) {
        int pos = charPosition - this.offset;
        if (this.charObjects == null) {
            return null;
        }
        if (pos < 0) {
            return null;
        }
        if (pos >= this.capacity) {
            return null;
        }
        if (pos >= this.charObjects.size()) {
            pos = 0;
        }
        if (pos >= this.charObjects.size()) {}
        return this.charObjects.elementAt(pos);
    }
    
    protected final void extentsOfResource(final String resourceName) {
        final ResourceBundle resource = SystemResourceBundle.getBundle("java.text.resources.LocaleElements", this.ems.locale);
        final String[] elements = resource.getStringArray(resourceName);
        final int iMax = elements.length;
        this.capacity = 0;
        this.minRequired = 30000;
        for (int i = 0; i < iMax; ++i) {
            final int iLen = elements[i].length();
            if (iLen != 0) {
                this.capacity = Math.max(this.capacity, iLen);
                this.minRequired = Math.min(this.minRequired, iLen);
            }
        }
    }
}
