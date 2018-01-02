// 
// Decompiled by Procyon v0.5.30
// 

public class Wheels
{
    int ground;
    int mast;
    
    public Wheels() {
        this.ground = 0;
        this.mast = 0;
    }
    
    public void make(final Medium medium, final Trackers trackers, final Plane[] array, int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final int[] array2 = new int[8];
        final int[] array3 = new int[8];
        final int[] array4 = new int[8];
        final int[] array5 = { 75, 75, 75 };
        int n8 = 0;
        final float n9 = n6 / 10.0f;
        final float n10 = n7 / 10.0f;
        if (n5 == 11) {
            n8 = (int)(n2 + 4.0f * n9);
        }
        int n11 = 1;
        if (Math.abs(n2) == 73) {
            n11 = -1;
        }
        int n12 = 0;
        do {
            array2[n12] = (int)(n2 - 4.0f * n9);
        } while (++n12 < 8);
        array3[0] = (int)(n3 - 12.0f * n10);
        array4[0] = (int)(n4 + 5.0f * n10);
        array3[1] = (int)(n3 - 12.0f * n10);
        array4[1] = (int)(n4 - 5.0f * n10);
        array3[2] = (int)(n3 - 5.0f * n10);
        array4[2] = (int)(n4 - 12.0f * n10);
        array3[3] = (int)(n3 + 5.0f * n10);
        array4[3] = (int)(n4 - 12.0f * n10);
        array3[4] = (int)(n3 + 12.0f * n10);
        array4[4] = (int)(n4 - 5.0f * n10);
        array3[5] = (int)(n3 + 12.0f * n10);
        array4[5] = (int)(n4 + 5.0f * n10);
        array3[6] = (int)(n3 + 5.0f * n10);
        array4[6] = (int)(n4 + 12.0f * n10);
        array3[7] = (int)(n3 - 5.0f * n10);
        array4[7] = (int)(n4 + 12.0f * n10);
        array[n] = new Plane(medium, trackers, array2, array4, array3, 8, array5, false, 0, n8, n3, n4, 7, 0, false);
        ++this.mast;
        array[n].master = this.mast;
        ++n;
        array2[0] = (int)(n2 - 4.0f * n9);
        array3[0] = (int)(n3 - 12.0f * n10);
        array4[0] = (int)(n4 + 5.0f * n10);
        array2[1] = (int)(n2 - 4.0f * n9);
        array3[1] = (int)(n3 - 12.0f * n10);
        array4[1] = (int)(n4 - 5.0f * n10);
        array2[2] = (int)(n2 + 4.0f * n9);
        array3[2] = (int)(n3 - 12.0f * n10);
        array4[2] = (int)(n4 - 5.0f * n10);
        array2[3] = (int)(n2 + 4.0f * n9);
        array3[3] = (int)(n3 - 12.0f * n10);
        array4[3] = (int)(n4 + 5.0f * n10);
        array[n] = new Plane(medium, trackers, array2, array4, array3, 4, array5, false, n11, n8, n3, n4, 7, 0, false);
        ++n;
        array2[0] = (int)(n2 - 4.0f * n9);
        array3[0] = (int)(n3 - 5.0f * n10);
        array4[0] = (int)(n4 - 12.0f * n10);
        array2[1] = (int)(n2 - 4.0f * n9);
        array3[1] = (int)(n3 - 12.0f * n10);
        array4[1] = (int)(n4 - 5.0f * n10);
        array2[2] = (int)(n2 + 4.0f * n9);
        array3[2] = (int)(n3 - 12.0f * n10);
        array4[2] = (int)(n4 - 5.0f * n10);
        array2[3] = (int)(n2 + 4.0f * n9);
        array3[3] = (int)(n3 - 5.0f * n10);
        array4[3] = (int)(n4 - 12.0f * n10);
        array[n] = new Plane(medium, trackers, array2, array4, array3, 4, array5, false, n11, n8, n3, n4, 7, 0, false);
        ++n;
        array2[0] = (int)(n2 - 4.0f * n9);
        array3[0] = (int)(n3 - 5.0f * n10);
        array4[0] = (int)(n4 - 12.0f * n10);
        array2[1] = (int)(n2 - 4.0f * n9);
        array3[1] = (int)(n3 + 5.0f * n10);
        array4[1] = (int)(n4 - 12.0f * n10);
        array2[2] = (int)(n2 + 4.0f * n9);
        array3[2] = (int)(n3 + 5.0f * n10);
        array4[2] = (int)(n4 - 12.0f * n10);
        array2[3] = (int)(n2 + 4.0f * n9);
        array3[3] = (int)(n3 - 5.0f * n10);
        array4[3] = (int)(n4 - 12.0f * n10);
        array[n] = new Plane(medium, trackers, array2, array4, array3, 4, array5, false, n11, n8, n3, n4, 7, 0, false);
        ++n;
        array2[0] = (int)(n2 - 4.0f * n9);
        array3[0] = (int)(n3 + 12.0f * n10);
        array4[0] = (int)(n4 - 5.0f * n10);
        array2[1] = (int)(n2 - 4.0f * n9);
        array3[1] = (int)(n3 + 5.0f * n10);
        array4[1] = (int)(n4 - 12.0f * n10);
        array2[2] = (int)(n2 + 4.0f * n9);
        array3[2] = (int)(n3 + 5.0f * n10);
        array4[2] = (int)(n4 - 12.0f * n10);
        array2[3] = (int)(n2 + 4.0f * n9);
        array3[3] = (int)(n3 + 12.0f * n10);
        array4[3] = (int)(n4 - 5.0f * n10);
        array[n] = new Plane(medium, trackers, array2, array4, array3, 4, array5, false, -1, n8, n3, n4, 7, 0, false);
        ++n;
        array2[0] = (int)(n2 - 4.0f * n9);
        array3[0] = (int)(n3 + 12.0f * n10);
        array4[0] = (int)(n4 - 5.0f * n10);
        array2[1] = (int)(n2 - 4.0f * n9);
        array3[1] = (int)(n3 + 12.0f * n10);
        array4[1] = (int)(n4 + 5.0f * n10);
        array2[2] = (int)(n2 + 4.0f * n9);
        array3[2] = (int)(n3 + 12.0f * n10);
        array4[2] = (int)(n4 + 5.0f * n10);
        array2[3] = (int)(n2 + 4.0f * n9);
        array3[3] = (int)(n3 + 12.0f * n10);
        array4[3] = (int)(n4 - 5.0f * n10);
        array[n] = new Plane(medium, trackers, array2, array4, array3, 4, array5, false, -1, n8, n3, n4, 7, 0, false);
        ++n;
        if (this.ground < (int)(n3 + 12.0f * n10 + 1.0f)) {
            this.ground = (int)(n3 + 12.0f * n10 + 1.0f);
        }
        array2[0] = (int)(n2 - 4.0f * n9);
        array3[0] = (int)(n3 + 5.0f * n10);
        array4[0] = (int)(n4 + 12.0f * n10);
        array2[1] = (int)(n2 - 4.0f * n9);
        array3[1] = (int)(n3 + 12.0f * n10);
        array4[1] = (int)(n4 + 5.0f * n10);
        array2[2] = (int)(n2 + 4.0f * n9);
        array3[2] = (int)(n3 + 12.0f * n10);
        array4[2] = (int)(n4 + 5.0f * n10);
        array2[3] = (int)(n2 + 4.0f * n9);
        array3[3] = (int)(n3 + 5.0f * n10);
        array4[3] = (int)(n4 + 12.0f * n10);
        array[n] = new Plane(medium, trackers, array2, array4, array3, 4, array5, false, -1, n8, n3, n4, 7, 0, false);
        ++n;
        array2[0] = (int)(n2 - 4.0f * n9);
        array3[0] = (int)(n3 + 5.0f * n10);
        array4[0] = (int)(n4 + 12.0f * n10);
        array2[1] = (int)(n2 - 4.0f * n9);
        array3[1] = (int)(n3 - 5.0f * n10);
        array4[1] = (int)(n4 + 12.0f * n10);
        array2[2] = (int)(n2 + 4.0f * n9);
        array3[2] = (int)(n3 - 5.0f * n10);
        array4[2] = (int)(n4 + 12.0f * n10);
        array2[3] = (int)(n2 + 4.0f * n9);
        array3[3] = (int)(n3 + 5.0f * n10);
        array4[3] = (int)(n4 + 12.0f * n10);
        array[n] = new Plane(medium, trackers, array2, array4, array3, 4, array5, false, n11, n8, n3, n4, 7, 0, false);
        ++n;
        array2[0] = (int)(n2 - 4.0f * n9);
        array3[0] = (int)(n3 - 12.0f * n10);
        array4[0] = (int)(n4 + 5.0f * n10);
        array2[1] = (int)(n2 - 4.0f * n9);
        array3[1] = (int)(n3 - 5.0f * n10);
        array4[1] = (int)(n4 + 12.0f * n10);
        array2[2] = (int)(n2 + 4.0f * n9);
        array3[2] = (int)(n3 - 5.0f * n10);
        array4[2] = (int)(n4 + 12.0f * n10);
        array2[3] = (int)(n2 + 4.0f * n9);
        array3[3] = (int)(n3 - 12.0f * n10);
        array4[3] = (int)(n4 + 5.0f * n10);
        array[n] = new Plane(medium, trackers, array2, array4, array3, 4, array5, false, n11, n8, n3, n4, 7, 0, false);
        ++n;
    }
}
