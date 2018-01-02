// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dtd.models;

import org.apache.xerces.xni.QName;

public class SimpleContentModel implements ContentModelValidator
{
    public static final short CHOICE = -1;
    public static final short SEQUENCE = -1;
    private QName fFirstChild;
    private QName fSecondChild;
    private int fOperator;
    
    public SimpleContentModel(final short operator, final QName firstChild, final QName secondChild) {
        this.fFirstChild = new QName();
        this.fSecondChild = new QName();
        this.fFirstChild.setValues(firstChild);
        if (secondChild != null) {
            this.fSecondChild.setValues(secondChild);
        }
        else {
            this.fSecondChild.clear();
        }
        this.fOperator = operator;
    }
    
    public int validate(final QName[] children, final int offset, final int length) {
        switch (this.fOperator) {
            case 0: {
                if (length == 0) {
                    return 0;
                }
                if (children[offset].rawname != this.fFirstChild.rawname) {
                    return 0;
                }
                if (length > 1) {
                    return 1;
                }
                break;
            }
            case 1: {
                if (length == 1 && children[offset].rawname != this.fFirstChild.rawname) {
                    return 0;
                }
                if (length > 1) {
                    return 1;
                }
                break;
            }
            case 2: {
                if (length > 0) {
                    for (int index = 0; index < length; ++index) {
                        if (children[offset + index].rawname != this.fFirstChild.rawname) {
                            return index;
                        }
                    }
                    break;
                }
                break;
            }
            case 3: {
                if (length == 0) {
                    return 0;
                }
                for (int index = 0; index < length; ++index) {
                    if (children[offset + index].rawname != this.fFirstChild.rawname) {
                        return index;
                    }
                }
                break;
            }
            case 4: {
                if (length == 0) {
                    return 0;
                }
                if (children[offset].rawname != this.fFirstChild.rawname && children[offset].rawname != this.fSecondChild.rawname) {
                    return 0;
                }
                if (length > 1) {
                    return 1;
                }
                break;
            }
            case 5: {
                if (length == 2) {
                    if (children[offset].rawname != this.fFirstChild.rawname) {
                        return 0;
                    }
                    if (children[offset + 1].rawname != this.fSecondChild.rawname) {
                        return 1;
                    }
                    break;
                }
                else {
                    if (length > 2) {
                        return 2;
                    }
                    return length;
                }
                break;
            }
            default: {
                throw new RuntimeException("ImplementationMessages.VAL_CST");
            }
        }
        return -1;
    }
}
