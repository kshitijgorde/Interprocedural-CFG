// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.util.ResourceBundle;

class ItemEditMaskRegionERA extends ItemEditMaskRegionAlpha
{
    ItemEditMaskRegionERA(final ItemEditMaskStr ems, final char c, final boolean optional) {
        super(ems, 1, optional);
        super.c = c;
        super.charCount = 1;
        final ResourceBundle resource = SystemResourceBundle.getBundle("java.text.resources.LocaleElements", ems.locale);
        final String elements = resource.getStringArray("Eras")[0];
        super.capacity = elements.length();
        int i;
        for (i = 0; i < super.capacity && elements.charAt(i) != ';'; ++i) {}
        if (i < super.capacity) {
            super.capacity = Math.max(i, super.capacity - 2 - i);
        }
        super.minRequired = super.capacity;
        super.charObjects.addElement(new ItemEditMaskCharAlpha(this, optional));
    }
}
