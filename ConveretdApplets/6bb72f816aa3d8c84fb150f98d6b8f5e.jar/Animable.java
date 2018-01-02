// 
// Decompiled by Procyon v0.5.30
// 

public class Animable extends NodeSub
{
    Class33[] aClass33Array1425;
    public int modelHeight;
    
    public void method443(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        final Model rotatedModel = this.getRotatedModel();
        if (rotatedModel != null) {
            this.modelHeight = rotatedModel.modelHeight;
            rotatedModel.method443(n, n2, n3, n4, n5, n6, n7, n8, n9);
        }
    }
    
    Model getRotatedModel() {
        return null;
    }
    
    Animable() {
        this.modelHeight = 1000;
    }
}
