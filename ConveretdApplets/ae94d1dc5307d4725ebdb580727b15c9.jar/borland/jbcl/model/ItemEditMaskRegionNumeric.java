// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import borland.jbcl.util.FastStringBuffer;

class ItemEditMaskRegionNumeric extends ItemEditMaskRegion
{
    ItemEditMaskRegionNumeric(final ItemEditMaskStr ems, final FastStringBuffer str) {
        super(ems, false);
        boolean optional = false;
        for (char c = str.firstChar(); c != '\0'; c = str.nextChar()) {
            switch (c) {
                case '{': {
                    optional = true;
                    break;
                }
                case '}': {
                    optional = false;
                    break;
                }
                case '0': {
                    super.charObjects.addElement(new ItemEditMaskCharDigit(this, optional));
                    ++super.capacity;
                    if (!optional) {
                        ++super.minRequired;
                    }
                    break;
                }
                case '#': {
                    super.charObjects.addElement(new ItemEditMaskCharDigit(this, true));
                    ++super.capacity;
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }
    }
}
