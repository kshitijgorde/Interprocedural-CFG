// 
// Decompiled by Procyon v0.5.30
// 

package gravity.cosmos;

import gravity.gameObjects.GameObject;

public class ProbeUpdateData extends ProbeIncomingData
{
    public GameObject gameObject;
    
    public ProbeUpdateData(final GameObject gameObject, final int n, final int n2, final boolean b) {
        super(n, n2, b);
        this.gameObject = gameObject;
    }
}
