import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Choice;
import java.awt.Label;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class an extends a8
{
    Color ae;
    Color af;
    String[] ac;
    Label ag;
    Choice ad;
    
    public an() {
        super(false, false, false, false);
        this.ac = new String[] { new String("\uac70\ub798\ub7c9"), new String("\uc774\ub3d9\ud3c9\uade0Osc"), new String("MACD/Sgl"), new String("MACD Osc"), new String("DMI/ADX"), new String("DI Osc"), new String("RSI"), new String("Stochastic"), new String("Williams%R"), new String("Momentum"), new String("Sonar Mtm"), new String("\uc2ec\ub9ac\ub3c4"), new String("\uc774\uaca9\ub3c4"), new String("OBV"), new String("VR"), new String("CCI"), new String("MFI"), new String("ROC"), new String("EOM"), new String("PDO Osc"), new String("Band%B Osc") };
        this.ae = new Color(153, 153, 153);
        this.af = new Color(160, 160, 154);
        this.ag = new Label("\ubcf4\uc870\ucc28\ud2b8\uc124\uc815", 1);
        this.ad = new Choice();
        this.setBackground(new Color(255, 255, 255));
        for (int i = 0; i < this.ac.length; ++i) {
            this.ad.addItem(this.ac[i]);
        }
        this.setLayout(null);
        this.ag.setBounds(5, 7, 95, 15);
        this.ag.setBackground(this.ae);
        this.ag.setForeground(Color.white);
        this.add(this.ag);
        this.ad.setBounds(5, 30, 95, 35);
        this.add(this.ad);
    }
    
    public void paint(final Graphics graphics) {
        this.getSize();
        super.paint(graphics);
        graphics.setColor(this.ae);
        graphics.fillRect(1, 1, 103, 24);
        graphics.setColor(this.af);
        graphics.drawLine(0, 0, 0, 55);
        graphics.drawLine(104, 0, 104, 55);
    }
}
