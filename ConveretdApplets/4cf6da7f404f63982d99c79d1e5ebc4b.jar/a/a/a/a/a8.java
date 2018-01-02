// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class a8
{
    public aw a(final String s, final aq aq, final an an, final boolean b, final boolean b2) {
        aw aw = null;
        Class<?> clazz = null;
        Label_0071: {
            if (an.aj >= 3) {
                try {
                    clazz = Class.forName("a.a.a.a.bt");
                    break Label_0071;
                }
                catch (Exception ex) {
                    try {
                        clazz = Class.forName("a.a.a.a.bu");
                    }
                    catch (Exception ex2) {
                        try {
                            clazz = Class.forName("a.a.a.a.aw");
                        }
                        catch (Exception ex3) {}
                    }
                }
            }
            try {
                clazz = Class.forName("a.a.a.a.bu");
            }
            catch (Exception ex4) {
                try {
                    clazz = Class.forName("a.a.a.a.aw");
                }
                catch (Exception ex5) {}
            }
            try {
                aw = (aw)clazz.getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
            }
            catch (Exception ex6) {}
        }
        aw.a(s, aq, an, b, b2);
        return aw;
    }
}
