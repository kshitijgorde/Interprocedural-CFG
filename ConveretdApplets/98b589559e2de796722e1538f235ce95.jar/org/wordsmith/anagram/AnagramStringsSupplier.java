// 
// Decompiled by Procyon v0.5.30
// 

package org.wordsmith.anagram;

public interface AnagramStringsSupplier
{
    String getSourceString();
    
    String getTargetString();
    
    boolean isReady();
    
    void startLoading();
    
    public static class Adapter implements AnagramStringsSupplier
    {
        private final String myTargetString;
        private final String mySourceString;
        
        public Adapter(final String mySourceString, final String myTargetString) {
            this.mySourceString = mySourceString;
            this.myTargetString = myTargetString;
        }
        
        public String getSourceString() {
            return this.mySourceString;
        }
        
        public String getTargetString() {
            return this.myTargetString;
        }
        
        public boolean isReady() {
            return true;
        }
        
        public void startLoading() {
        }
    }
}
