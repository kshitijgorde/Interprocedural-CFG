import jagdx.VertexElementCollection;
import jagdx.IDirect3DVertexDeclaration;

// 
// Decompiled by Procyon v0.5.30
// 

public class dxVertexLayout extends Class256
{
    IDirect3DVertexDeclaration anIDirect3DVertexDeclaration5155;
    
    dxVertexLayout(final ha_Sub3_Sub1 ha_Sub3_Sub1, final Class49[] array) {
        try {
            final VertexElementCollection collection = new VertexElementCollection(ha_Sub3_Sub1.aJaa6108);
            int n = 0;
            for (int n2 = 0; array.length > n2; ++n2) {
                int n3 = 0;
                final Class49 class49 = array[n2];
                for (int i = 0; i < class49.method480((byte)(-105)); ++i) {
                    final Class169 method479 = class49.method479(i, (byte)(-98));
                    if (Class169.aClass169_1294 == method479) {
                        collection.addElement(n2, 2, 0, 0, 0, n3);
                    }
                    else if (method479 == Class169.aClass169_1297) {
                        collection.addElement(n2, 2, 0, 3, 0, n3);
                    }
                    else if (method479 != Class169.aClass169_1298) {
                        if (method479 == Class169.aClass169_1299) {
                            collection.addElement(n2, 0, 0, 5, n++, n3);
                        }
                        else if (method479 != Class169.aClass169_1301) {
                            if (method479 != Class169.aClass169_1302) {
                                if (method479 == Class169.aClass169_1303) {
                                    collection.addElement(n2, 3, 0, 5, n++, n3);
                                }
                            }
                            else {
                                collection.addElement(n2, 2, 0, 5, n++, n3);
                            }
                        }
                        else {
                            collection.addElement(n2, 1, 0, 5, n++, n3);
                        }
                    }
                    else {
                        collection.addElement(n2, 4, 0, 10, 0, n3);
                    }
                    n3 += method479.anInt1295;
                }
            }
            collection.finish();
            this.anIDirect3DVertexDeclaration5155 = ha_Sub3_Sub1.anIDirect3DDevice6098.a(collection, null);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
