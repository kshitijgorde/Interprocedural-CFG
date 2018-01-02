// 
// Decompiled by Procyon v0.5.30
// 

final class Class324
{
    byte[] aByteArray2717;
    int[] anIntArray2718;
    int anInt2719;
    int anInt2720;
    int anInt2721;
    int anInt2722;
    byte[] aByteArray2723;
    int anInt2724;
    int anInt2725;
    
    static final Class324[] method3680(final Class207 class207, final int n, final int n2) {
        final byte[] method2745 = class207.method2745(n2, n, false);
        if (method2745 == null) {
            return null;
        }
        return method3690(method2745);
    }
    
    final int method3681() {
        return this.anInt2720 + this.anInt2721 + this.anInt2724;
    }
    
    final void method3682() {
        final byte[] aByteArray2717 = this.aByteArray2717;
        if (this.aByteArray2723 == null) {
            for (int i = (this.anInt2720 >> 1) - 1; i >= 0; --i) {
                int n = i * this.anInt2722;
                int n2 = (this.anInt2720 - i - 1) * this.anInt2722;
                for (int j = -this.anInt2722; j < 0; ++j) {
                    final byte b = aByteArray2717[n];
                    aByteArray2717[n] = aByteArray2717[n2];
                    aByteArray2717[n2] = b;
                    ++n;
                    ++n2;
                }
            }
        }
        else {
            final byte[] aByteArray2718 = this.aByteArray2723;
            for (int k = (this.anInt2720 >> 1) - 1; k >= 0; --k) {
                int n3 = k * this.anInt2722;
                int n4 = (this.anInt2720 - k - 1) * this.anInt2722;
                for (int l = -this.anInt2722; l < 0; ++l) {
                    final byte b2 = aByteArray2717[n3];
                    aByteArray2717[n3] = aByteArray2717[n4];
                    aByteArray2717[n4] = b2;
                    final byte b3 = aByteArray2718[n3];
                    aByteArray2718[n3] = aByteArray2718[n4];
                    aByteArray2718[n4] = b3;
                    ++n3;
                    ++n4;
                }
            }
        }
        final int anInt2721 = this.anInt2721;
        this.anInt2721 = this.anInt2724;
        this.anInt2724 = anInt2721;
    }
    
    static final Class324 method3683(final Class207 class207, final int n) {
        final byte[] method2733 = class207.method2733(n, -117);
        if (method2733 == null) {
            return null;
        }
        return method3690(method2733)[0];
    }
    
    static final Class324[] method3684(final Class207 class207, final int n) {
        final byte[] method2733 = class207.method2733(n, -127);
        if (method2733 == null) {
            return null;
        }
        return method3690(method2733);
    }
    
    static final Class324 method3685(final Class207 class207, final int n, final int n2) {
        final byte[] method2745 = class207.method2745(n2, n, false);
        if (method2745 == null) {
            return null;
        }
        return method3690(method2745)[0];
    }
    
    final int[] method3686() {
        final int method3689 = this.method3689();
        final int[] array = new int[method3689 * this.method3681()];
        if (this.aByteArray2723 != null) {
            for (int i = 0; i < this.anInt2720; ++i) {
                int n = i * this.anInt2722;
                int n2 = this.anInt2725 + (i + this.anInt2721) * method3689;
                for (int j = 0; j < this.anInt2722; ++j) {
                    array[n2++] = (this.aByteArray2723[n] << 24 | this.anIntArray2718[this.aByteArray2717[n] & 0xFF]);
                    ++n;
                }
            }
        }
        else {
            for (int k = 0; k < this.anInt2720; ++k) {
                int n3 = k * this.anInt2722;
                int n4 = this.anInt2725 + (k + this.anInt2721) * method3689;
                for (int l = 0; l < this.anInt2722; ++l) {
                    final int n5 = this.anIntArray2718[this.aByteArray2717[n3++] & 0xFF];
                    if (n5 != 0) {
                        array[n4++] = (0xFF000000 | n5);
                    }
                    else {
                        array[n4++] = 0;
                    }
                }
            }
        }
        return array;
    }
    
    final void method3687() {
        final byte[] array = new byte[this.anInt2722 * this.anInt2720];
        int n = 0;
        if (this.aByteArray2723 == null) {
            for (int i = 0; i < this.anInt2722; ++i) {
                for (int j = this.anInt2720 - 1; j >= 0; --j) {
                    array[n++] = this.aByteArray2717[i + j * this.anInt2722];
                }
            }
            this.aByteArray2717 = array;
        }
        else {
            final byte[] aByteArray2723 = new byte[this.anInt2722 * this.anInt2720];
            for (int k = 0; k < this.anInt2722; ++k) {
                for (int l = this.anInt2720 - 1; l >= 0; --l) {
                    array[n] = this.aByteArray2717[k + l * this.anInt2722];
                    aByteArray2723[n++] = this.aByteArray2723[k + l * this.anInt2722];
                }
            }
            this.aByteArray2717 = array;
            this.aByteArray2723 = aByteArray2723;
        }
        final int anInt2721 = this.anInt2721;
        this.anInt2721 = this.anInt2725;
        this.anInt2725 = this.anInt2724;
        this.anInt2724 = this.anInt2719;
        this.anInt2719 = this.anInt2721;
        final int anInt2722 = this.anInt2720;
        this.anInt2720 = this.anInt2722;
        this.anInt2722 = anInt2722;
    }
    
    final void method3688(final int n) {
        final int method3689 = this.method3689();
        final int method3690 = this.method3681();
        if (this.anInt2722 != method3689 || this.anInt2720 != method3690) {
            int anInt2725 = n;
            if (anInt2725 > this.anInt2725) {
                anInt2725 = this.anInt2725;
            }
            int n2 = n;
            if (n2 + this.anInt2725 + this.anInt2722 > method3689) {
                n2 = method3689 - this.anInt2725 - this.anInt2722;
            }
            int anInt2726 = n;
            if (anInt2726 > this.anInt2721) {
                anInt2726 = this.anInt2721;
            }
            int n3 = n;
            if (n3 + this.anInt2721 + this.anInt2720 > method3690) {
                n3 = method3690 - this.anInt2721 - this.anInt2720;
            }
            final int anInt2727 = this.anInt2722 + anInt2725 + n2;
            final int anInt2728 = this.anInt2720 + anInt2726 + n3;
            final byte[] aByteArray2717 = new byte[anInt2727 * anInt2728];
            if (this.aByteArray2723 == null) {
                for (int i = 0; i < this.anInt2720; ++i) {
                    int n4 = i * this.anInt2722;
                    int n5 = (i + anInt2726) * anInt2727 + anInt2725;
                    for (int j = 0; j < this.anInt2722; ++j) {
                        aByteArray2717[n5++] = this.aByteArray2717[n4++];
                    }
                }
            }
            else {
                final byte[] aByteArray2718 = new byte[anInt2727 * anInt2728];
                for (int k = 0; k < this.anInt2720; ++k) {
                    int n6 = k * this.anInt2722;
                    int n7 = (k + anInt2726) * anInt2727 + anInt2725;
                    for (int l = 0; l < this.anInt2722; ++l) {
                        aByteArray2718[n7] = this.aByteArray2723[n6];
                        aByteArray2717[n7++] = this.aByteArray2717[n6++];
                    }
                }
                this.aByteArray2723 = aByteArray2718;
            }
            this.anInt2725 -= anInt2725;
            this.anInt2721 -= anInt2726;
            this.anInt2719 -= n2;
            this.anInt2724 -= n3;
            this.anInt2722 = anInt2727;
            this.anInt2720 = anInt2728;
            this.aByteArray2717 = aByteArray2717;
        }
    }
    
    final int method3689() {
        return this.anInt2722 + this.anInt2725 + this.anInt2719;
    }
    
    private static final Class324[] method3690(final byte[] array) {
        final Class98_Sub22 class98_Sub22 = new Class98_Sub22(array);
        class98_Sub22.anInt3991 = array.length - 2;
        final int short1 = class98_Sub22.readShort((byte)127);
        final Class324[] array2 = new Class324[short1];
        for (int i = 0; i < short1; ++i) {
            array2[i] = new Class324();
        }
        class98_Sub22.anInt3991 = array.length - 7 - short1 * 8;
        final int short2 = class98_Sub22.readShort((byte)127);
        final int short3 = class98_Sub22.readShort((byte)127);
        final int n = (class98_Sub22.readUnsignedByte((byte)48) & 0xFF) + 1;
        for (int j = 0; j < short1; ++j) {
            array2[j].anInt2725 = class98_Sub22.readShort((byte)127);
        }
        for (int k = 0; k < short1; ++k) {
            array2[k].anInt2721 = class98_Sub22.readShort((byte)127);
        }
        for (int l = 0; l < short1; ++l) {
            array2[l].anInt2722 = class98_Sub22.readShort((byte)127);
        }
        for (int n2 = 0; n2 < short1; ++n2) {
            array2[n2].anInt2720 = class98_Sub22.readShort((byte)127);
        }
        for (final Class324 class324 : array2) {
            class324.anInt2719 = short2 - class324.anInt2722 - class324.anInt2725;
            class324.anInt2724 = short3 - class324.anInt2720 - class324.anInt2721;
        }
        class98_Sub22.anInt3991 = array.length - 7 - short1 * 8 - (n - 1) * 3;
        final int[] anIntArray2718 = new int[n];
        for (int n4 = 1; n4 < n; ++n4) {
            anIntArray2718[n4] = class98_Sub22.method1186(-124);
            if (anIntArray2718[n4] == 0) {
                anIntArray2718[n4] = 1;
            }
        }
        for (int n5 = 0; n5 < short1; ++n5) {
            array2[n5].anIntArray2718 = anIntArray2718;
        }
        class98_Sub22.anInt3991 = 0;
        for (final Class324 class325 : array2) {
            final int n7 = class325.anInt2722 * class325.anInt2720;
            class325.aByteArray2717 = new byte[n7];
            final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-117));
            if ((unsignedByte & 0x2) == 0x0) {
                if ((unsignedByte & 0x1) == 0x0) {
                    for (int n8 = 0; n8 < n7; ++n8) {
                        class325.aByteArray2717[n8] = class98_Sub22.readSignedByte((byte)(-19));
                    }
                }
                else {
                    for (int n9 = 0; n9 < class325.anInt2722; ++n9) {
                        for (int n10 = 0; n10 < class325.anInt2720; ++n10) {
                            class325.aByteArray2717[n9 + n10 * class325.anInt2722] = class98_Sub22.readSignedByte((byte)(-19));
                        }
                    }
                }
            }
            else {
                boolean b = false;
                class325.aByteArray2723 = new byte[n7];
                if ((unsignedByte & 0x1) == 0x0) {
                    for (int n11 = 0; n11 < n7; ++n11) {
                        class325.aByteArray2717[n11] = class98_Sub22.readSignedByte((byte)(-19));
                    }
                    for (int n12 = 0; n12 < n7; ++n12) {
                        final byte[] aByteArray2723 = class325.aByteArray2723;
                        final int n13 = n12;
                        final byte signedByte = class98_Sub22.readSignedByte((byte)(-19));
                        aByteArray2723[n13] = signedByte;
                        b |= (signedByte != -1);
                    }
                }
                else {
                    for (int n14 = 0; n14 < class325.anInt2722; ++n14) {
                        for (int n15 = 0; n15 < class325.anInt2720; ++n15) {
                            class325.aByteArray2717[n14 + n15 * class325.anInt2722] = class98_Sub22.readSignedByte((byte)(-19));
                        }
                    }
                    for (int n16 = 0; n16 < class325.anInt2722; ++n16) {
                        for (int n17 = 0; n17 < class325.anInt2720; ++n17) {
                            final byte[] aByteArray2724 = class325.aByteArray2723;
                            final int n18 = n16 + n17 * class325.anInt2722;
                            final byte signedByte2 = class98_Sub22.readSignedByte((byte)(-19));
                            aByteArray2724[n18] = signedByte2;
                            b |= (signedByte2 != -1);
                        }
                    }
                }
                if (!b) {
                    class325.aByteArray2723 = null;
                }
            }
        }
        return array2;
    }
    
    final void method3691() {
        final byte[] aByteArray2717 = this.aByteArray2717;
        if (this.aByteArray2723 == null) {
            for (int i = this.anInt2720 - 1; i >= 0; --i) {
                byte b;
                for (int j = i * this.anInt2722, n = (i + 1) * this.anInt2722; j < n; --n, b = aByteArray2717[j], aByteArray2717[j] = aByteArray2717[n], aByteArray2717[n] = b, ++j) {}
            }
        }
        else {
            final byte[] aByteArray2718 = this.aByteArray2723;
            for (int k = this.anInt2720 - 1; k >= 0; --k) {
                byte b2;
                byte b3;
                for (int l = k * this.anInt2722, n2 = (k + 1) * this.anInt2722; l < n2; --n2, b2 = aByteArray2717[l], aByteArray2717[l] = aByteArray2717[n2], aByteArray2717[n2] = b2, b3 = aByteArray2718[l], aByteArray2718[l] = aByteArray2718[n2], aByteArray2718[n2] = b3, ++l) {}
            }
        }
        final int anInt2725 = this.anInt2725;
        this.anInt2725 = this.anInt2719;
        this.anInt2719 = anInt2725;
    }
    
    final void method3692() {
        final int method3689 = this.method3689();
        final int method3690 = this.method3681();
        if (this.anInt2722 != method3689 || this.anInt2720 != method3690) {
            final byte[] aByteArray2717 = new byte[method3689 * method3690];
            if (this.aByteArray2723 != null) {
                final byte[] aByteArray2718 = new byte[method3689 * method3690];
                for (int i = 0; i < this.anInt2720; ++i) {
                    int n = i * this.anInt2722;
                    int n2 = (i + this.anInt2721) * method3689 + this.anInt2725;
                    for (int j = 0; j < this.anInt2722; ++j) {
                        aByteArray2717[n2] = this.aByteArray2717[n];
                        aByteArray2718[n2++] = this.aByteArray2723[n++];
                    }
                }
                this.aByteArray2723 = aByteArray2718;
            }
            else {
                for (int k = 0; k < this.anInt2720; ++k) {
                    int n3 = k * this.anInt2722;
                    int n4 = (k + this.anInt2721) * method3689 + this.anInt2725;
                    for (int l = 0; l < this.anInt2722; ++l) {
                        aByteArray2717[n4++] = this.aByteArray2717[n3++];
                    }
                }
            }
            final boolean b = false;
            this.anInt2724 = (b ? 1 : 0);
            this.anInt2721 = (b ? 1 : 0);
            this.anInt2719 = (b ? 1 : 0);
            this.anInt2725 = (b ? 1 : 0);
            this.anInt2722 = method3689;
            this.anInt2720 = method3690;
            this.aByteArray2717 = aByteArray2717;
        }
    }
    
    final void method3693(final int n) {
        int length = -1;
        if (this.anIntArray2718.length < 255) {
            for (int i = 0; i < this.anIntArray2718.length; ++i) {
                if (this.anIntArray2718[i] == n) {
                    length = i;
                    break;
                }
            }
            if (length == -1) {
                length = this.anIntArray2718.length;
                final int[] anIntArray2718 = new int[this.anIntArray2718.length + 1];
                Class236.method2891(this.anIntArray2718, 0, anIntArray2718, 0, this.anIntArray2718.length);
                (this.anIntArray2718 = anIntArray2718)[length] = n;
            }
        }
        else {
            int n2 = Integer.MAX_VALUE;
            final int n3 = n >> 16 & 0xFF;
            final int n4 = n >> 8 & 0xFF;
            final int n5 = n & 0xFF;
            for (int j = 0; j < this.anIntArray2718.length; ++j) {
                final int n6 = this.anIntArray2718[j];
                final int n7 = n6 >> 16 & 0xFF;
                final int n8 = n6 >> 8 & 0xFF;
                final int n9 = n6 & 0xFF;
                int n10 = n3 - n7;
                if (n10 < 0) {
                    n10 = -n10;
                }
                int n11 = n4 - n8;
                if (n11 < 0) {
                    n11 = -n11;
                }
                int n12 = n5 - n9;
                if (n12 < 0) {
                    n12 = -n12;
                }
                final int n13 = n10 + n11 + n12;
                if (n13 < n2) {
                    n2 = n13;
                    length = j;
                }
            }
        }
        for (int k = this.anInt2720 - 1; k > 0; --k) {
            final int n14 = k * this.anInt2722;
            for (int l = this.anInt2722 - 1; l > 0; --l) {
                if (this.anIntArray2718[this.aByteArray2717[l + n14] & 0xFF] == 0 && this.anIntArray2718[this.aByteArray2717[l + n14 - 1 - this.anInt2722] & 0xFF] != 0) {
                    this.aByteArray2717[l + n14] = (byte)length;
                }
            }
        }
    }
    
    final void method3694(final int n) {
        int length = -1;
        if (this.anIntArray2718.length < 255) {
            for (int i = 0; i < this.anIntArray2718.length; ++i) {
                if (this.anIntArray2718[i] == n) {
                    length = i;
                    break;
                }
            }
            if (length == -1) {
                length = this.anIntArray2718.length;
                final int[] anIntArray2718 = new int[this.anIntArray2718.length + 1];
                Class236.method2891(this.anIntArray2718, 0, anIntArray2718, 0, this.anIntArray2718.length);
                (this.anIntArray2718 = anIntArray2718)[length] = n;
            }
        }
        else {
            int n2 = Integer.MAX_VALUE;
            final int n3 = n >> 16 & 0xFF;
            final int n4 = n >> 8 & 0xFF;
            final int n5 = n & 0xFF;
            for (int j = 0; j < this.anIntArray2718.length; ++j) {
                final int n6 = this.anIntArray2718[j];
                final int n7 = n6 >> 16 & 0xFF;
                final int n8 = n6 >> 8 & 0xFF;
                final int n9 = n6 & 0xFF;
                int n10 = n3 - n7;
                if (n10 < 0) {
                    n10 = -n10;
                }
                int n11 = n4 - n8;
                if (n11 < 0) {
                    n11 = -n11;
                }
                int n12 = n5 - n9;
                if (n12 < 0) {
                    n12 = -n12;
                }
                final int n13 = n10 + n11 + n12;
                if (n13 < n2) {
                    n2 = n13;
                    length = j;
                }
            }
        }
        int n14 = 0;
        final byte[] aByteArray2717 = new byte[this.anInt2722 * this.anInt2720];
        for (int k = 0; k < this.anInt2720; ++k) {
            for (int l = 0; l < this.anInt2722; ++l) {
                int n15 = this.aByteArray2717[n14] & 0xFF;
                if (this.anIntArray2718[n15] == 0) {
                    if (l > 0 && this.anIntArray2718[this.aByteArray2717[n14 - 1] & 0xFF] != 0) {
                        n15 = length;
                    }
                    else if (k > 0 && this.anIntArray2718[this.aByteArray2717[n14 - this.anInt2722] & 0xFF] != 0) {
                        n15 = length;
                    }
                    else if (l < this.anInt2722 - 1 && this.anIntArray2718[this.aByteArray2717[n14 + 1] & 0xFF] != 0) {
                        n15 = length;
                    }
                    else if (k < this.anInt2720 - 1 && this.anIntArray2718[this.aByteArray2717[n14 + this.anInt2722] & 0xFF] != 0) {
                        n15 = length;
                    }
                }
                aByteArray2717[n14++] = (byte)n15;
            }
        }
        this.aByteArray2717 = aByteArray2717;
    }
}
