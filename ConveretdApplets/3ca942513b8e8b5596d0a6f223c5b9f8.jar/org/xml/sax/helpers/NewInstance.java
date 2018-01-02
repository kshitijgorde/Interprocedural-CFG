// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax.helpers;

class NewInstance
{
    private static final boolean DO_FALLBACK = true;
    static /* synthetic */ Class class$org$xml$sax$helpers$NewInstance;
    
    static Object newInstance(ClassLoader classLoader, final String s) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz;
        if (classLoader == null) {
            clazz = Class.forName(s);
        }
        else {
            try {
                clazz = classLoader.loadClass(s);
            }
            catch (ClassNotFoundException ex) {
                classLoader = ((NewInstance.class$org$xml$sax$helpers$NewInstance == null) ? (NewInstance.class$org$xml$sax$helpers$NewInstance = class$("org.xml.sax.helpers.NewInstance")) : NewInstance.class$org$xml$sax$helpers$NewInstance).getClassLoader();
                if (classLoader != null) {
                    clazz = classLoader.loadClass(s);
                }
                else {
                    clazz = Class.forName(s);
                }
            }
        }
        return clazz.newInstance();
    }
    
    static ClassLoader getClassLoader() {
        ClassLoader classLoader = SecuritySupport.getInstance().getContextClassLoader();
        if (classLoader == null) {
            classLoader = ((NewInstance.class$org$xml$sax$helpers$NewInstance == null) ? (NewInstance.class$org$xml$sax$helpers$NewInstance = class$("org.xml.sax.helpers.NewInstance")) : NewInstance.class$org$xml$sax$helpers$NewInstance).getClassLoader();
        }
        return classLoader;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
