// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class au
{
    public ai a(final String s, final ae ae, final ac ac, final boolean b, final boolean b2) {
        ai ai = null;
        Class<?> clazz = null;
        Label_0071: {
            if (ac.ab >= 3) {
                try {
                    clazz = Class.forName("a.a.a.a.bc");
                    break Label_0071;
                }
                catch (Exception ex) {
                    try {
                        clazz = Class.forName("a.a.a.a.bd");
                    }
                    catch (Exception ex2) {
                        try {
                            clazz = Class.forName("a.a.a.a.ai");
                        }
                        catch (Exception ex3) {}
                    }
                }
            }
            try {
                clazz = Class.forName("a.a.a.a.bd");
            }
            catch (Exception ex4) {
                try {
                    clazz = Class.forName("a.a.a.a.ai");
                }
                catch (Exception ex5) {}
            }
            try {
                ai = (ai)clazz.getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
            }
            catch (Exception ex6) {}
        }
        ai.a(s, ae, ac, b, b2);
        return ai;
    }
}
