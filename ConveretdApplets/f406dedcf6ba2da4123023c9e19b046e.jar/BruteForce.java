// 
// Decompiled by Procyon v0.5.30
// 

public class BruteForce extends HullAlgorithm
{
    public BruteForce(final Point3dObject3d[] array) {
        super(array);
    }
    
    public Object3dList build2D() {
        final int length = super.pts.length;
        int n = 0;
        final Object3dList list = new Object3dList(20);
        for (int i = 0; i < length - 1; ++i) {
            for (int j = i + 1; j < length; ++j) {
                int n2 = -1;
                for (int k = 0; k < length; ++k) {
                    if (k != i) {
                        if (k != j) {
                            if (n2 == -1) {
                                if (0 == HullAlgorithm.classify(super.pts[i], super.pts[j], super.pts[k])) {
                                    n2 = 0;
                                }
                                else if (1 == HullAlgorithm.classify(super.pts[i], super.pts[j], super.pts[k])) {
                                    n2 = 1;
                                }
                            }
                            else {
                                if (0 == HullAlgorithm.classify(super.pts[i], super.pts[j], super.pts[k]) && n2 == 1) {
                                    n2 = -2;
                                    break;
                                }
                                if (1 == HullAlgorithm.classify(super.pts[i], super.pts[j], super.pts[k]) && n2 == 0) {
                                    n2 = -2;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (n2 == 0 || n2 == 1) {
                    list.addElement(new Edge3d(super.pts[i], super.pts[j], ++n));
                }
            }
        }
        list.lastFrame = ++n;
        return list;
    }
}
