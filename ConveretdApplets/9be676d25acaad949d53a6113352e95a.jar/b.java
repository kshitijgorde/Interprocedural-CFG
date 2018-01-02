import java.net.URL;
import java.util.Hashtable;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

public class b extends e
{
    private void do(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            hashtable.put(nextToken.substring(0, nextToken.indexOf(":")), nextToken.substring(nextToken.indexOf(":") + 1));
        }
        this.a(hashtable);
    }
    
    public void run() {
        System.out.println("running Overload provider ...");
        Object o = null;
        System.out.println("Using feed: ".concat(String.valueOf(String.valueOf(e.a))));
        while (true) {
            Label_0096: {
                if (o == null) {
                    if (e.a != null) {
                        try {
                            o = new URL(e.a);
                            break Label_0096;
                        }
                        catch (Exception ex2) {
                            System.err.println("Bad URL for feed:".concat(String.valueOf(String.valueOf(o))));
                            return;
                        }
                    }
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (Exception ex3) {}
                    continue;
                }
                try {
                    for (int i = 0; i < super.for.size(); ++i) {
                        final String s = super.for.elementAt(i).get("SYMBOL");
                        String a;
                        try {
                            a = d.a(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(e.a))).append("?sym=").append(s))));
                        }
                        catch (Exception ex4) {
                            break;
                        }
                        this.do(a);
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    continue;
                }
            }
            try {
                Thread.sleep(super.if);
            }
            catch (Exception ex5) {}
        }
    }
}
