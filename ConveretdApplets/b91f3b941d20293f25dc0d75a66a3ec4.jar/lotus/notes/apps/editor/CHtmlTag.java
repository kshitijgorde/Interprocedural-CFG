// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.util.Enumeration;
import java.util.Vector;

final class CHtmlTag
{
    private String cTag;
    private int cID;
    private boolean cIsEndTag;
    private Vector cParams;
    private static int cBehavior;
    
    CHtmlTag() {
        this.cParams = new Vector(5, 5);
    }
    
    CHtmlTag(final int cBehavior) {
        this();
        CHtmlTag.cBehavior = cBehavior;
    }
    
    CHtmlTag(final String tag) {
        this();
        this.setTag(tag);
    }
    
    CHtmlTag(final String s, final String s2, final String s3) {
        this(s);
        this.addParam(s2, s3);
    }
    
    String getTag() {
        return this.cTag;
    }
    
    void setTag(final String s) {
        this.cTag = s.toUpperCase();
        this.identifyTag();
    }
    
    int getID() {
        return this.cID;
    }
    
    void setID(final int cid) {
        this.cID = cid;
    }
    
    boolean isEndTag() {
        return this.cIsEndTag;
    }
    
    void setEndTag(final boolean cIsEndTag) {
        this.cIsEndTag = cIsEndTag;
    }
    
    Enumeration getParams() {
        return this.cParams.elements();
    }
    
    void addParam(final String s, final String s2) {
        this.cParams.addElement(new CHtmlTagParam(s, s2));
    }
    
    String getParamValue(final String s) {
        final Enumeration<CHtmlTagParam> elements = this.cParams.elements();
        while (elements.hasMoreElements()) {
            final CHtmlTagParam cHtmlTagParam = elements.nextElement();
            if (cHtmlTagParam.getParam().equals(s)) {
                return cHtmlTagParam.getValue();
            }
        }
        return null;
    }
    
    private void identifyTag() {
        this.cID = 0;
        switch (this.cTag.charAt(0)) {
            case 'A': {
                if (this.cTag.equals("A")) {
                    this.cID = 44;
                    break;
                }
                if (this.cTag.equals("ADDRESS")) {
                    this.cID = 1;
                    break;
                }
                break;
            }
            case 'B': {
                if (this.cTag.equals("B")) {
                    this.cID = 2;
                    break;
                }
                if (this.cTag.equals("BIG")) {
                    this.cID = 3;
                    break;
                }
                if (this.cTag.equals("BLOCKQUOTE")) {
                    this.cID = 4;
                    break;
                }
                if (this.cTag.equals("BODY")) {
                    this.cID = 5;
                    break;
                }
                if (this.cTag.equals("BR")) {
                    this.cID = 6;
                    break;
                }
                if (this.cTag.equals("BASEFONT")) {
                    this.cID = 42;
                    break;
                }
                if (this.cTag.equals("BASE") && CHtmlTag.cBehavior == 1) {
                    this.cID = 46;
                    break;
                }
                break;
            }
            case 'C': {
                if (this.cTag.equals("CENTER")) {
                    this.cID = 7;
                    break;
                }
                if (this.cTag.equals("CITE")) {
                    this.cID = 8;
                    break;
                }
                if (this.cTag.equals("CODE")) {
                    this.cID = 9;
                    break;
                }
                break;
            }
            case 'D': {
                if (this.cTag.equals("DD")) {
                    this.cID = 10;
                    break;
                }
                if (this.cTag.equals("DFN")) {
                    this.cID = 11;
                    break;
                }
                if (this.cTag.equals("DIV")) {
                    this.cID = 13;
                    break;
                }
                if (this.cTag.equals("DL")) {
                    this.cID = 14;
                    break;
                }
                if (this.cTag.equals("DT")) {
                    this.cID = 15;
                    break;
                }
                break;
            }
            case 'E': {
                if (this.cTag.equals("EM")) {
                    this.cID = 16;
                    break;
                }
                break;
            }
            case 'F': {
                if (this.cTag.equals("FONT")) {
                    this.cID = 17;
                    break;
                }
                break;
            }
            case 'H': {
                if (this.cTag.equals("H1")) {
                    this.cID = 18;
                    break;
                }
                if (this.cTag.equals("H2")) {
                    this.cID = 19;
                    break;
                }
                if (this.cTag.equals("H3")) {
                    this.cID = 20;
                    break;
                }
                if (this.cTag.equals("H4")) {
                    this.cID = 21;
                    break;
                }
                if (this.cTag.equals("H5")) {
                    this.cID = 22;
                    break;
                }
                if (this.cTag.equals("H6")) {
                    this.cID = 23;
                    break;
                }
                break;
            }
            case 'I': {
                if (this.cTag.equals("I")) {
                    this.cID = 24;
                    break;
                }
                if (this.cTag.equals("IMG")) {
                    this.cID = 45;
                    break;
                }
                break;
            }
            case 'K': {
                if (this.cTag.equals("KBD")) {
                    this.cID = 25;
                    break;
                }
                break;
            }
            case 'L': {
                if (this.cTag.equals("LI")) {
                    this.cID = 26;
                    break;
                }
                if (this.cTag.equals("LISTING")) {
                    this.cID = 27;
                    break;
                }
                break;
            }
            case 'M': {
                if (this.cTag.equals("MENU")) {
                    this.cID = 28;
                    break;
                }
                break;
            }
            case 'O': {
                if (this.cTag.equals("OL")) {
                    this.cID = 29;
                    break;
                }
                break;
            }
            case 'P': {
                if (this.cTag.equals("P")) {
                    this.cID = 30;
                    break;
                }
                if (this.cTag.equals("PLAINTEXT")) {
                    this.cID = 31;
                    break;
                }
                if (this.cTag.equals("PRE")) {
                    this.cID = 32;
                    break;
                }
                break;
            }
            case 'S': {
                if (this.cTag.equals("SAMP")) {
                    this.cID = 33;
                    break;
                }
                if (this.cTag.equals("SMALL")) {
                    this.cID = 34;
                    break;
                }
                if (this.cTag.equals("STRIKE")) {
                    this.cID = 35;
                    break;
                }
                if (this.cTag.equals("STRONG")) {
                    this.cID = 36;
                    break;
                }
                break;
            }
            case 'T': {
                if (this.cTag.equals("TT")) {
                    this.cID = 37;
                    break;
                }
                if (this.cTag.equals("TABLE")) {
                    this.cID = 47;
                    break;
                }
                if (this.cTag.equals("TD")) {
                    this.cID = 49;
                    break;
                }
                if (this.cTag.equals("TR")) {
                    this.cID = 48;
                    break;
                }
                if (this.cTag.equals("TH")) {
                    this.cID = 50;
                    break;
                }
                break;
            }
            case 'U': {
                if (this.cTag.equals("U")) {
                    this.cID = 38;
                    break;
                }
                if (this.cTag.equals("UL")) {
                    this.cID = 39;
                    break;
                }
                break;
            }
            case 'V': {
                if (this.cTag.equals("VAR")) {
                    this.cID = 40;
                    break;
                }
                break;
            }
            case 'X': {
                if (this.cTag.equals("XMP")) {
                    this.cID = 41;
                    break;
                }
                break;
            }
        }
    }
}
