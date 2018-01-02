// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

public interface Profile
{
    public static final Profile ALL = new Profile() {
        public boolean allowBuiltin(final String name) {
            return true;
        }
        
        public boolean allowClass(final String name) {
            return true;
        }
        
        public boolean allowModule(final String name) {
            return true;
        }
        
        public boolean allowLoad(final String name) {
            return true;
        }
        
        public boolean allowRequire(final String name) {
            return true;
        }
    };
    public static final Profile DEBUG_ALLOW = new Profile() {
        public boolean allowBuiltin(final String name) {
            System.err.println("allowBuiltin(" + name + ")");
            return true;
        }
        
        public boolean allowClass(final String name) {
            System.err.println("allowClass(" + name + ")");
            return true;
        }
        
        public boolean allowModule(final String name) {
            System.err.println("allowModule(" + name + ")");
            return true;
        }
        
        public boolean allowLoad(final String name) {
            System.err.println("allowLoad(" + name + ")");
            return true;
        }
        
        public boolean allowRequire(final String name) {
            System.err.println("allowRequire(" + name + ")");
            return true;
        }
    };
    public static final Profile NO_FILE_CLASS = new Profile() {
        public boolean allowBuiltin(final String name) {
            return true;
        }
        
        public boolean allowClass(final String name) {
            return !name.equals("File");
        }
        
        public boolean allowModule(final String name) {
            return true;
        }
        
        public boolean allowLoad(final String name) {
            return true;
        }
        
        public boolean allowRequire(final String name) {
            return true;
        }
    };
    public static final Profile ANY = Profile.ALL;
    public static final Profile DEFAULT = Profile.ALL;
    
    boolean allowBuiltin(final String p0);
    
    boolean allowClass(final String p0);
    
    boolean allowModule(final String p0);
    
    boolean allowLoad(final String p0);
    
    boolean allowRequire(final String p0);
}
