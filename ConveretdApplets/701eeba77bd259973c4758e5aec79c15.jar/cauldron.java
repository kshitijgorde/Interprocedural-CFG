// 
// Decompiled by Procyon v0.5.30
// 

class cauldron
{
    private int[] cItem;
    private int numInC;
    
    public cauldron() {
        (this.cItem = new int[5])[0] = 0;
        this.cItem[1] = 0;
        this.cItem[2] = 0;
        this.cItem[3] = 0;
        this.numInC = 0;
    }
    
    public String whatsIn() {
        final items items = new items();
        String s = "";
        int n = 0;
        do {
            s += items.getName(this.cItem[n]);
            if (n == 0 || n == 1) {
                s += ", ";
            }
            if (n == 2) {
                s += " & ";
            }
            if (n == 3) {
                s += ".";
            }
        } while (++n < 4);
        return s;
    }
    
    public int addItem(final int n) {
        this.cItem[0] = this.cItem[1];
        this.cItem[1] = this.cItem[2];
        this.cItem[2] = this.cItem[3];
        this.cItem[3] = n;
        final int n2 = this.cItem[0];
        final int n3 = this.cItem[1];
        final int n4 = this.cItem[2];
        final int n5 = this.cItem[3];
        if (n3 == 64 && n4 == 69) {
            if (n5 == 68) {
                return 53;
            }
            return 54;
        }
        else {
            if (n4 == 60 && n5 == 61) {
                return 110;
            }
            if (n3 == 60 && n4 == 64 && n5 == 61) {
                return 111;
            }
            if (n4 == 67 && n5 == 60) {
                return 112;
            }
            if (n3 == 61 && n4 == 63) {
                if (n5 == 64) {
                    return 113;
                }
                return 96;
            }
            else {
                if (n5 == 62 && n4 == 67) {
                    if (n3 == 68) {
                        return 114;
                    }
                    if (n3 > 0) {
                        return 94;
                    }
                }
                if (n5 == 69 && n4 == 66) {
                    if (n3 == 62) {
                        return 115;
                    }
                    return 92;
                }
                else if (n5 == 64 && n4 == 69) {
                    if (n3 == 66) {
                        return 116;
                    }
                    return 85;
                }
                else if (n5 == 60 && n4 == 61 && n3 == 63) {
                    if (n2 == 64) {
                        return 117;
                    }
                    return 85;
                }
                else if (n5 == 62 && n4 == 60) {
                    if (n3 == 63) {
                        return 118;
                    }
                    return 94;
                }
                else if (n5 == 66 && n4 == 62) {
                    if (n3 == 67) {
                        return 119;
                    }
                    return 92;
                }
                else if (n5 == 64 && n4 == 68 && n3 == 62) {
                    if (n2 == 69) {
                        return 120;
                    }
                    return 85;
                }
                else if (n5 == 65 && n4 == 63) {
                    if (n3 == 60) {
                        return 121;
                    }
                    return 85;
                }
                else if (n5 == 63 && n4 == 68) {
                    if (n3 == 65) {
                        return 122;
                    }
                    return 87;
                }
                else if (n5 == 66 && n4 == 67) {
                    if (n3 == 65) {
                        return 123;
                    }
                    return 96;
                }
                else {
                    if (n5 == 62 && n4 == 61 && n3 == 68) {
                        return 124;
                    }
                    return 0;
                }
            }
        }
    }
}
