// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import borland.jbcl.util.FastStringBuffer;

class ItemEditMaskRegionText extends ItemEditMaskRegion
{
    int caseConvert;
    boolean password;
    private char[] passwordStr;
    
    ItemEditMaskRegionText(final ItemEditMaskStr ems, final FastStringBuffer str, final int caseConvert, final boolean password, final boolean optional) {
        super(ems, false);
        this.caseConvert = caseConvert;
        this.password = password;
        this.passwordStr = null;
        if (password) {
            final int iLen = str.length();
            this.passwordStr = new char[iLen];
            for (int i = 0; i < iLen; ++i) {
                this.passwordStr[i] = ems.blankChar;
            }
        }
        super.capacity = str.length();
        for (char c = str.firstChar(); c != '\0'; c = str.nextChar()) {
            switch (c) {
                case '0': {
                    super.charObjects.addElement(new ItemEditMaskCharDigit(this, optional));
                    break;
                }
                case '9': {
                    super.charObjects.addElement(new ItemEditMaskCharDigit(this, true));
                    break;
                }
                case '#': {
                    super.charObjects.addElement(new ItemEditMaskCharDigitSign(this, true));
                    break;
                }
                case 'L': {
                    super.charObjects.addElement(new ItemEditMaskCharAlpha(this, optional));
                    break;
                }
                case '?':
                case 'l': {
                    super.charObjects.addElement(new ItemEditMaskCharAlpha(this, true));
                    break;
                }
                case 'A': {
                    super.charObjects.addElement(new ItemEditMaskCharAlphaNum(this, optional));
                    break;
                }
                case 'a': {
                    super.charObjects.addElement(new ItemEditMaskCharAlphaNum(this, true));
                    break;
                }
                case '&':
                case 'C': {
                    super.charObjects.addElement(new ItemEditMaskCharCharOrSpace(this, optional));
                    break;
                }
                case 'c': {
                    super.charObjects.addElement(new ItemEditMaskCharCharOrSpace(this, true));
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }
    }
    
    public char setCharAt(final StringBuffer str, final int charPosition, char c) {
        if (this.caseConvert < 0) {
            c = Character.toLowerCase(c);
        }
        else if (this.caseConvert > 0) {
            c = Character.toUpperCase(c);
        }
        if (this.password) {
            this.passwordStr[charPosition - super.offset] = c;
            c = '*';
        }
        str.setCharAt(charPosition, c);
        return c;
    }
    
    public char getCharAt(final StringBuffer str, final int charPosition) {
        char c;
        if (this.password) {
            c = this.passwordStr[charPosition - super.offset];
        }
        else {
            c = str.charAt(charPosition);
        }
        return c;
    }
    
    public void deleteCharAt(final StringBuffer str, final int charPosition, final char blankChar) {
        if (this.password) {
            this.passwordStr[charPosition - super.offset] = blankChar;
        }
        str.setCharAt(charPosition, blankChar);
    }
}
