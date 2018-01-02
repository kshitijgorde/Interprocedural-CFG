// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

public interface IMutableProxyInterface
{
    IProxyInterfaceGetter getProxyInterface(final boolean p0);
    
    public static class DummyMutableProxyInterface implements IMutableProxyInterface
    {
        private IProxyInterfaceGetter m_dummyGetter;
        
        public DummyMutableProxyInterface() {
            this.m_dummyGetter = new IProxyInterfaceGetter() {
                public ImmutableProxyInterface getProxyInterface() {
                    return null;
                }
            };
        }
        
        public IProxyInterfaceGetter getProxyInterface(final boolean b) {
            if (b) {
                return null;
            }
            return this.m_dummyGetter;
        }
    }
}
