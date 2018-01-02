// 
// Decompiled by Procyon v0.5.30
// 

class RoundButtonFilter extends ButtonFilter
{
    int Xcenter;
    int Ycenter;
    int Yradsq;
    int innerW;
    int innerH;
    int Yrad2sq;
    private int[] savedranges;
    private int savedy;
    
    public RoundButtonFilter(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super(b, n, n2, n3, n4);
        this.Xcenter = n3 / 2;
        this.Ycenter = n4 / 2;
        this.Yradsq = n4 * n4 / 4;
        this.innerW = n3 - super.border * 2;
        this.innerH = n4 - super.border * 2;
        this.Yrad2sq = this.innerH * this.innerH / 4;
    }
    
    public void buttonRanges(final int n, final int[] array) {
        final int abs = Math.abs(this.Ycenter - n);
        final int n2 = (int)(Math.sqrt(this.Yradsq - abs * abs) * super.width / super.height);
        final int n3 = n * super.width / super.height;
        array[0] = 0;
        array[1] = this.Xcenter - n2;
        array[6] = this.Xcenter + n2;
        array[7] = super.width;
        if (n < super.border) {
            final int n4 = 2;
            final int n5 = 3;
            final int n6 = 4;
            final int xcenter = this.Xcenter;
            array[n6] = xcenter;
            array[n4] = (array[n5] = xcenter);
            array[5] = array[6];
            return;
        }
        if (n + super.border >= super.height) {
            array[2] = array[1];
            final int n7 = 3;
            final int n8 = 4;
            final int n9 = 5;
            final int xcenter2 = this.Xcenter;
            array[n9] = xcenter2;
            array[n7] = (array[n8] = xcenter2);
            return;
        }
        final int n10 = (int)(Math.sqrt(this.Yrad2sq - abs * abs) * this.innerW / this.innerH);
        array[3] = this.Xcenter - n10;
        array[4] = this.Xcenter + n10;
        if (n < this.Ycenter) {
            array[2] = array[3];
            array[5] = array[6];
            return;
        }
        array[2] = array[1];
        array[5] = array[4];
    }
    
    private synchronized int[] getRanges(final int savedy) {
        if (this.savedranges == null || this.savedy != savedy) {
            if (this.savedranges == null) {
                this.savedranges = new int[8];
            }
            this.buttonRanges(savedy, this.savedranges);
            this.savedy = savedy;
        }
        return this.savedranges;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        int[] ranges;
        int n4;
        for (ranges = this.getRanges(n2), n4 = 0; n4 < 7 && (n < ranges[n4] || n >= ranges[n4 + 1]); ++n4) {}
        boolean pressed = false;
        int n5 = 0;
        switch (n4) {
            default: {
                return n3 & 0xFFFFFF;
            }
            case 1: {
                pressed = !super.pressed;
                n5 = super.defpercent;
                break;
            }
            case 5: {
                pressed = super.pressed;
                n5 = super.defpercent;
                break;
            }
            case 2: {
                n5 = super.defpercent - (int)(Math.cos(Math.atan2(n2 - this.Ycenter, this.Xcenter - n)) * 2.0 * super.defpercent);
                if (!super.pressed) {
                    n5 = -n5;
                }
                if (n5 == 0) {
                    return n3;
                }
                if (n5 < 0) {
                    n5 = -n5;
                    pressed = false;
                    break;
                }
                pressed = true;
                break;
            }
            case 4: {
                n5 = super.defpercent - (int)(Math.cos(Math.atan2(this.Ycenter - n2, n - this.Xcenter)) * 2.0 * super.defpercent);
                if (super.pressed) {
                    n5 = -n5;
                }
                if (n5 == 0) {
                    return n3;
                }
                if (n5 < 0) {
                    n5 = -n5;
                    pressed = false;
                    break;
                }
                pressed = true;
                break;
            }
            case 3: {
                if (!super.pressed) {
                    return n3 & 0xFFFFFF;
                }
                pressed = false;
                n5 = super.defpercent;
                break;
            }
        }
        return this.filterRGB(n3, pressed, n5);
    }
}
