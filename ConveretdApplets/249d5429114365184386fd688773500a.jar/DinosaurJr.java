import java.awt.Container;
import java.awt.Event;
import java.awt.event.ItemEvent;
import java.awt.Component;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;
import java.awt.TextArea;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.TextField;
import java.awt.CheckboxGroup;
import java.awt.Button;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class DinosaurJr extends Panel implements ItemListener
{
    Santeria region;
    bikeCAD2000 parent;
    Choice modcho1;
    Choice fo;
    Choice rw;
    Choice fw;
    Choice bb;
    Choice stdia;
    Choice ttdia;
    Choice dtdia;
    Choice htdia;
    Button[] bList;
    CheckboxGroup cbg;
    TextField frontcenterTF;
    TextField rweelDtf;
    TextField rweelWtf;
    TextField fweelDtf;
    TextField fweelWtf;
    TextField angleHtf;
    TextField angleStf;
    TextField SThtf;
    TextField BBhtf;
    TextField BBdtf;
    TextField dropTF;
    TextField headtubeTF;
    TextField chainstayTF;
    TextField forklengthTF;
    TextField forkrakeTF;
    TextField lowstackTF;
    TextField seatstayTF;
    TextField seatsangTF;
    TextField stDiamTF;
    TextField ttDiamTF;
    TextField dtDiamTF;
    TextField htDiamTF;
    TextField headlowextTF;
    TextField headupextTF;
    TextField STexthtf;
    TextField seatxTF;
    TextField seatyTF;
    TextField seatzTF;
    TextField chainxTF;
    TextField chainyTF;
    TextField chainzTF;
    TextField seatsbendTF;
    TextField seatstopTF;
    TextField chainsbendTF;
    TextField chainsfrontTF;
    TextField seatsradTF;
    TextField ssdTF;
    TextField chainstaperTF;
    TextField chainsposTF;
    TextField chainsradTF;
    TextField cefronthTF;
    TextField cefrontvTF;
    TextField cebackdiaTF;
    TextField sagTF;
    TextField ftravelTF;
    Label m1m;
    Label m2m;
    Label m3m;
    Label m4m;
    Label m5m;
    Label m6m;
    Label m7m;
    Label m8m;
    Label m9m;
    Label m10m;
    Label m11m;
    Label m12m;
    Label m13m;
    Label m14m;
    Label m15m;
    Label m16m;
    Label m17m;
    Label m18m;
    Label m19m;
    Label m20m;
    Label m21m;
    Label m22m;
    Label m23m;
    Label m24m;
    Label m25m;
    Label m26m;
    Label m27m;
    Label m28m;
    Label m29m;
    Label m30m;
    Label m31m;
    Label m32m;
    Label m33m;
    Label m34m;
    Label m35m;
    Label m36m;
    Label m37m;
    Label m38m;
    Label m39m;
    Label m40m;
    Label m41m;
    Label m42m;
    Label m43m;
    Label m44m;
    Label m45m;
    Label m46m;
    Label m47m;
    Label m48m;
    Label m49m;
    Label m50m;
    Label m51m;
    Label m52m;
    Label m53m;
    Label m54m;
    Label m55m;
    Label m56m;
    Label m57m;
    Label m58m;
    Label m59m;
    Label m60m;
    Label m61m;
    Label m62m;
    Label m63m;
    Label m64m;
    Label m65m;
    Label m66m;
    Label m67m;
    Label m68m;
    Label m69m;
    Label m70m;
    Label m71m;
    Label m72m;
    Label m73m;
    Label m74m;
    Label m75m;
    Label m76m;
    Label m77m;
    Label m78m;
    Label m79m;
    Label m80m;
    Label m81m;
    Label m82m;
    Label m83m;
    Label m84m;
    Label m85m;
    Label m86m;
    Label m87m;
    Label m88m;
    Label m89m;
    Label m90m;
    Label m91m;
    Label m92m;
    Label m93m;
    Label m94m;
    Checkbox sideview;
    Checkbox csview;
    Checkbox ssview;
    Checkbox stabview;
    Checkbox sbend;
    Checkbox cbend;
    Checkbox fanim;
    TextArea Framestats;
    double headangle;
    double seatangle;
    double frontwd;
    double rearwd;
    double dropoutspace;
    double rearww;
    double frontww;
    double BBd;
    double BBh;
    double stlength;
    double htlength;
    double htlowext;
    double htupext;
    double CSlen;
    double FCdist;
    double rakelen;
    double forklen;
    double lowerstack;
    double stdiameter;
    double ttdiameter;
    double dtdiameter;
    double htdiameter;
    double stextension;
    double chainx;
    double chainy;
    double chainz;
    double seatx;
    double seaty;
    double seatz;
    double staypos;
    double stayang;
    double sbendang;
    double cbendang;
    double sstoplen;
    double csfrontlen;
    double sradius;
    double sebackdia;
    double chtaper;
    double chbackdia;
    double cradius;
    double chvdia;
    double chhdia;
    double chpos;
    double ftravel;
    double sag;
    int whereto;
    Panel optionCards;
    CardLayout cardLO;
    
    void buildConst(final GridBagConstraints gridBagConstraints, final int gridx, final int gridy, final int gridwidth, final int gridheight, final int n, final int n2) {
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagConstraints.weightx = n;
        gridBagConstraints.weighty = n2;
    }
    
    public DinosaurJr(final Santeria region, final bikeCAD2000 parent) {
        this.modcho1 = new Choice();
        this.fo = new Choice();
        this.rw = new Choice();
        this.fw = new Choice();
        this.bb = new Choice();
        this.stdia = new Choice();
        this.ttdia = new Choice();
        this.dtdia = new Choice();
        this.htdia = new Choice();
        this.bList = new Button[5];
        this.cbg = new CheckboxGroup();
        this.cardLO = new CardLayout();
        (this.optionCards = new Panel()).setLayout(this.cardLO);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        final Panel panel3 = new Panel();
        final Panel panel4 = new Panel();
        final Panel panel5 = new Panel();
        final Panel panel6 = new Panel();
        final Panel panel7 = new Panel();
        final Panel panel8 = new Panel();
        this.parent = parent;
        this.region = region;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(new FlowLayout());
        panel.setLayout(gridBagLayout);
        panel2.setLayout(gridBagLayout);
        panel3.setLayout(gridBagLayout);
        panel4.setLayout(gridBagLayout);
        panel6.setLayout(gridBagLayout);
        panel5.setLayout(gridBagLayout);
        panel7.setLayout(gridBagLayout);
        panel8.setLayout(gridBagLayout);
        gridBagConstraints.fill = 0;
        this.modcho1.addItem("Major dimensions");
        this.modcho1.addItem("Tube diameters");
        this.modcho1.addItem("Wheel sizes");
        this.modcho1.addItem("Fork dimensions");
        this.modcho1.addItem("Minor dimensions");
        this.modcho1.addItem("Seatstays");
        this.modcho1.addItem("Chainstays");
        this.modcho1.addItem("Building info");
        this.modcho1.select(0);
        final Color foreground = new Color(0, 110, 0);
        this.modcho1.setForeground(foreground);
        this.add(this.modcho1);
        this.add(this.sideview = new Checkbox("Side view", this.cbg, true));
        this.sideview.addItemListener(this);
        this.sideview.setForeground(foreground);
        this.add(this.csview = new Checkbox("Chainstay view", this.cbg, false));
        this.csview.addItemListener(this);
        this.csview.setForeground(foreground);
        this.add(this.ssview = new Checkbox("Seatstay view", this.cbg, false));
        this.ssview.addItemListener(this);
        this.ssview.setForeground(foreground);
        this.add(this.stabview = new Checkbox("Stability view", this.cbg, false));
        this.stabview.addItemListener(this);
        this.stabview.setForeground(foreground);
        this.m3m = new Label("Seat tube angle ", 2);
        this.buildConst(gridBagConstraints, 0, 0, 1, 2, 100, 2);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m3m, gridBagConstraints);
        panel.add(this.m3m);
        this.angleStf = new TextField("73.0", 3);
        this.buildConst(gridBagConstraints, 1, 0, 1, 2, 100, 2);
        gridBagLayout.setConstraints(this.angleStf, gridBagConstraints);
        panel.add(this.angleStf);
        final Button button = new Button("+");
        this.buildConst(gridBagConstraints, 2, 0, 1, 1, 0, 1);
        gridBagConstraints.fill = 2;
        gridBagConstraints.ipady = -9;
        gridBagConstraints.anchor = 15;
        gridBagLayout.setConstraints(button, gridBagConstraints);
        panel.add(button);
        this.bList[2] = (Button)panel.add(button);
        final Button button2 = new Button("-");
        this.buildConst(gridBagConstraints, 2, 1, 1, 1, 0, 1);
        gridBagConstraints.fill = 2;
        gridBagConstraints.ipady = -9;
        gridBagConstraints.anchor = 11;
        gridBagLayout.setConstraints(button2, gridBagConstraints);
        panel.add(button2);
        this.bList[3] = (Button)panel.add(button2);
        this.m4m = new Label(" degrees", 0);
        this.buildConst(gridBagConstraints, 3, 0, 1, 2, 100, 2);
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m4m, gridBagConstraints);
        panel.add(this.m4m);
        this.m1m = new Label("Head tube angle ", 2);
        this.buildConst(gridBagConstraints, 4, 0, 1, 2, 100, 2);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m1m, gridBagConstraints);
        panel.add(this.m1m);
        this.angleHtf = new TextField("71.0", 3);
        this.buildConst(gridBagConstraints, 5, 0, 1, 2, 100, 2);
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(this.angleHtf, gridBagConstraints);
        panel.add(this.angleHtf);
        final Button button3 = new Button("+");
        this.buildConst(gridBagConstraints, 6, 0, 1, 1, 0, 1);
        gridBagConstraints.fill = 2;
        gridBagConstraints.ipady = -9;
        gridBagConstraints.anchor = 15;
        gridBagLayout.setConstraints(button3, gridBagConstraints);
        panel.add(button3);
        this.bList[0] = (Button)panel.add(button3);
        final Button button4 = new Button("-");
        this.buildConst(gridBagConstraints, 6, 1, 1, 1, 0, 1);
        gridBagConstraints.fill = 2;
        gridBagConstraints.ipady = -9;
        gridBagConstraints.anchor = 11;
        gridBagLayout.setConstraints(button4, gridBagConstraints);
        panel.add(button4);
        this.bList[1] = (Button)panel.add(button4);
        this.m2m = new Label(" degrees", 0);
        this.buildConst(gridBagConstraints, 7, 0, 1, 2, 100, 2);
        gridBagConstraints.fill = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m2m, gridBagConstraints);
        panel.add(this.m2m);
        this.m5m = new Label("Seat tube length ", 2);
        this.buildConst(gridBagConstraints, 0, 2, 1, 1, 100, 2);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m5m, gridBagConstraints);
        panel.add(this.m5m);
        this.SThtf = new TextField("482", 3);
        this.buildConst(gridBagConstraints, 1, 2, 1, 1, 100, 2);
        gridBagLayout.setConstraints(this.SThtf, gridBagConstraints);
        panel.add(this.SThtf);
        this.m6m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 2, 2, 1, 100, 2);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m6m, gridBagConstraints);
        panel.add(this.m6m);
        this.m9m = new Label("Bottom bracket height ", 2);
        this.buildConst(gridBagConstraints, 0, 3, 1, 1, 100, 2);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m9m, gridBagConstraints);
        panel.add(this.m9m);
        this.BBhtf = new TextField("298", 3);
        this.buildConst(gridBagConstraints, 1, 3, 1, 1, 100, 2);
        gridBagLayout.setConstraints(this.BBhtf, gridBagConstraints);
        panel.add(this.BBhtf);
        this.m10m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 3, 2, 1, 100, 2);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m10m, gridBagConstraints);
        panel.add(this.m10m);
        this.m11m = new Label("Front center Distance ", 2);
        this.buildConst(gridBagConstraints, 4, 3, 1, 1, 100, 2);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m11m, gridBagConstraints);
        panel.add(this.m11m);
        this.frontcenterTF = new TextField("637", 3);
        this.buildConst(gridBagConstraints, 5, 3, 1, 1, 100, 2);
        gridBagLayout.setConstraints(this.frontcenterTF, gridBagConstraints);
        panel.add(this.frontcenterTF);
        this.m12m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 6, 3, 2, 1, 100, 2);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m12m, gridBagConstraints);
        panel.add(this.m12m);
        this.m13m = new Label("Head tube length ", 2);
        this.buildConst(gridBagConstraints, 4, 2, 1, 1, 100, 2);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m13m, gridBagConstraints);
        panel.add(this.m13m);
        this.headtubeTF = new TextField("111", 3);
        this.buildConst(gridBagConstraints, 5, 2, 1, 1, 100, 2);
        gridBagLayout.setConstraints(this.headtubeTF, gridBagConstraints);
        panel.add(this.headtubeTF);
        this.m14m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 6, 2, 2, 1, 100, 2);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m14m, gridBagConstraints);
        panel.add(this.m14m);
        this.m35m = new Label("Chainstay length ", 2);
        this.buildConst(gridBagConstraints, 0, 4, 1, 1, 100, 2);
        gridBagConstraints.anchor = 12;
        gridBagLayout.setConstraints(this.m35m, gridBagConstraints);
        panel.add(this.m35m);
        this.chainstayTF = new TextField("425", 3);
        this.buildConst(gridBagConstraints, 1, 4, 1, 1, 100, 2);
        gridBagConstraints.anchor = 12;
        gridBagLayout.setConstraints(this.chainstayTF, gridBagConstraints);
        panel.add(this.chainstayTF);
        this.m36m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 4, 2, 1, 100, 50);
        gridBagConstraints.anchor = 18;
        gridBagLayout.setConstraints(this.m36m, gridBagConstraints);
        panel.add(this.m36m);
        this.m19m = new Label("Rear tire diameter ", 2);
        this.buildConst(gridBagConstraints, 0, 0, 1, 1, 100, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m19m, gridBagConstraints);
        panel3.add(this.m19m);
        this.rweelDtf = new TextField("660", 3);
        this.buildConst(gridBagConstraints, 1, 0, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.rweelDtf, gridBagConstraints);
        panel3.add(this.rweelDtf);
        this.m20m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 0, 1, 1, 10, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m20m, gridBagConstraints);
        panel3.add(this.m20m);
        this.rw.addItem("20 X 1.75\"");
        this.rw.addItem("24 X 1\"");
        this.rw.addItem("24\" tubular");
        this.rw.addItem("26\" tubular");
        this.rw.addItem("26 X 1\"");
        this.rw.addItem("26 X 1.25\"");
        this.rw.addItem("26 X 1.5\"");
        this.rw.addItem("26 X 1.9/1.95\"");
        this.rw.addItem("26 X 2.125/2.2\"");
        this.rw.addItem("26 X 1 3/8\"");
        this.rw.addItem("700c tubular");
        this.rw.addItem("700 X 20c");
        this.rw.addItem("700 X 21c");
        this.rw.addItem("700 X 23c");
        this.rw.addItem("700 X 25c");
        this.rw.addItem("700 X 28c");
        this.rw.addItem("700 X 32c");
        this.rw.addItem("700 X 35c");
        this.rw.addItem("27 X 7/8\"");
        this.rw.addItem("27 X 1\"");
        this.rw.addItem("27 X 1 1/8\"");
        this.rw.addItem("27 X 1 1/4\"");
        this.rw.addItem("Custom");
        this.rw.select(22);
        this.buildConst(gridBagConstraints, 3, 0, 1, 1, 100, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.rw, gridBagConstraints);
        panel3.add(this.rw);
        this.m21m = new Label("Rear tire width ", 2);
        this.buildConst(gridBagConstraints, 0, 1, 1, 1, 100, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m21m, gridBagConstraints);
        panel3.add(this.m21m);
        this.rweelWtf = new TextField("55.8", 3);
        this.buildConst(gridBagConstraints, 1, 1, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.rweelWtf, gridBagConstraints);
        panel3.add(this.rweelWtf);
        this.m22m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 1, 1, 1, 10, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m22m, gridBagConstraints);
        panel3.add(this.m22m);
        this.m23m = new Label("Front tire diameter ", 2);
        this.buildConst(gridBagConstraints, 0, 2, 1, 1, 100, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m23m, gridBagConstraints);
        panel3.add(this.m23m);
        this.fweelDtf = new TextField("660", 3);
        this.buildConst(gridBagConstraints, 1, 2, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.fweelDtf, gridBagConstraints);
        panel3.add(this.fweelDtf);
        this.m24m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 2, 1, 1, 10, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m24m, gridBagConstraints);
        panel3.add(this.m24m);
        this.fw.addItem("20 X 1.75\"");
        this.fw.addItem("24 X 1\"");
        this.fw.addItem("24\" tubular");
        this.fw.addItem("26\" tubular");
        this.fw.addItem("26 X 1\"");
        this.fw.addItem("26 X 1.25\"");
        this.fw.addItem("26 X 1.5\"");
        this.fw.addItem("26 X 1.9/1.95\"");
        this.fw.addItem("26 X 2.125/2.2\"");
        this.fw.addItem("26 X 1 3/8\"");
        this.fw.addItem("700c tubular");
        this.fw.addItem("700 X 20c");
        this.fw.addItem("700 X 21c");
        this.fw.addItem("700 X 23c");
        this.fw.addItem("700 X 25c");
        this.fw.addItem("700 X 28c");
        this.fw.addItem("700 X 32c");
        this.fw.addItem("700 X 35c");
        this.fw.addItem("27 X 7/8\"");
        this.fw.addItem("27 X 1\"");
        this.fw.addItem("27 X 1 1/8\"");
        this.fw.addItem("27 X 1 1/4\"");
        this.fw.addItem("Custom");
        this.fw.select(22);
        this.buildConst(gridBagConstraints, 3, 2, 1, 1, 100, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.fw, gridBagConstraints);
        panel3.add(this.fw);
        this.m25m = new Label("Front tire width ", 2);
        this.buildConst(gridBagConstraints, 0, 3, 1, 1, 100, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m25m, gridBagConstraints);
        panel3.add(this.m25m);
        this.fweelWtf = new TextField("55.8", 3);
        this.buildConst(gridBagConstraints, 1, 3, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.fweelWtf, gridBagConstraints);
        panel3.add(this.fweelWtf);
        this.m26m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 3, 1, 1, 10, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m26m, gridBagConstraints);
        panel3.add(this.m26m);
        this.m27m = new Label("Rear Dropout Spacing ", 2);
        this.buildConst(gridBagConstraints, 0, 4, 1, 1, 100, 1);
        gridBagConstraints.anchor = 12;
        gridBagLayout.setConstraints(this.m27m, gridBagConstraints);
        panel3.add(this.m27m);
        this.dropTF = new TextField("135", 3);
        this.buildConst(gridBagConstraints, 1, 4, 1, 1, 20, 1);
        gridBagConstraints.anchor = 12;
        gridBagLayout.setConstraints(this.dropTF, gridBagConstraints);
        panel3.add(this.dropTF);
        this.m81m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 4, 1, 1, 10, 100);
        gridBagConstraints.anchor = 18;
        gridBagLayout.setConstraints(this.m81m, gridBagConstraints);
        panel3.add(this.m81m);
        this.m28m = new Label("BB shell length ", 2);
        this.buildConst(gridBagConstraints, 0, 0, 1, 1, 50, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m28m, gridBagConstraints);
        panel5.add(this.m28m);
        this.bb.addItem("68 mm");
        this.bb.addItem("70 mm");
        this.bb.addItem("73 mm");
        this.bb.select(0);
        this.buildConst(gridBagConstraints, 1, 0, 2, 1, 20, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.bb, gridBagConstraints);
        panel5.add(this.bb);
        this.m7m = new Label("Seat tube extension above top tube ", 2);
        this.buildConst(gridBagConstraints, 0, 1, 1, 1, 50, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m7m, gridBagConstraints);
        panel5.add(this.m7m);
        this.STexthtf = new TextField("40", 3);
        this.buildConst(gridBagConstraints, 1, 1, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.STexthtf, gridBagConstraints);
        panel5.add(this.STexthtf);
        this.m8m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 1, 1, 1, 50, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m8m, gridBagConstraints);
        panel5.add(this.m8m);
        this.m15m = new Label("Head tube extension below downtube ", 2);
        this.buildConst(gridBagConstraints, 0, 2, 1, 1, 50, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m15m, gridBagConstraints);
        panel5.add(this.m15m);
        this.headlowextTF = new TextField("10", 3);
        this.buildConst(gridBagConstraints, 1, 2, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.headlowextTF, gridBagConstraints);
        panel5.add(this.headlowextTF);
        this.m16m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 2, 1, 1, 50, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m16m, gridBagConstraints);
        panel5.add(this.m16m);
        this.m17m = new Label("Head tube extension above toptube ", 2);
        this.buildConst(gridBagConstraints, 0, 3, 1, 1, 50, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m17m, gridBagConstraints);
        panel5.add(this.m17m);
        this.headupextTF = new TextField("8", 3);
        this.buildConst(gridBagConstraints, 1, 3, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.headupextTF, gridBagConstraints);
        panel5.add(this.headupextTF);
        this.m18m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 3, 1, 1, 50, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m18m, gridBagConstraints);
        panel5.add(this.m18m);
        this.m29m = new Label("Chainstay end in front of rear axle ", 2);
        this.buildConst(gridBagConstraints, 3, 0, 1, 1, 50, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m29m, gridBagConstraints);
        panel5.add(this.m29m);
        this.chainxTF = new TextField("20", 3);
        this.buildConst(gridBagConstraints, 4, 0, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.chainxTF, gridBagConstraints);
        panel5.add(this.chainxTF);
        this.m85m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 5, 0, 1, 1, 50, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m85m, gridBagConstraints);
        panel5.add(this.m85m);
        this.m30m = new Label("Chainstay end above rear axle ", 2);
        this.buildConst(gridBagConstraints, 3, 1, 1, 1, 50, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m30m, gridBagConstraints);
        panel5.add(this.m30m);
        this.chainyTF = new TextField("-2", 3);
        this.buildConst(gridBagConstraints, 4, 1, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.chainyTF, gridBagConstraints);
        panel5.add(this.chainyTF);
        this.m86m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 5, 1, 1, 1, 50, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m86m, gridBagConstraints);
        panel5.add(this.m86m);
        this.m31m = new Label("Chainstay center offset from dropout face ", 2);
        this.buildConst(gridBagConstraints, 3, 2, 1, 1, 50, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m31m, gridBagConstraints);
        panel5.add(this.m31m);
        this.chainzTF = new TextField("6", 3);
        this.buildConst(gridBagConstraints, 4, 2, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.chainzTF, gridBagConstraints);
        panel5.add(this.chainzTF);
        this.m87m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 5, 2, 1, 1, 50, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m87m, gridBagConstraints);
        panel5.add(this.m87m);
        this.m32m = new Label("Seatstay end in front of rear axle ", 2);
        this.buildConst(gridBagConstraints, 3, 3, 1, 1, 50, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m32m, gridBagConstraints);
        panel5.add(this.m32m);
        this.seatxTF = new TextField("10", 3);
        this.buildConst(gridBagConstraints, 4, 3, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.seatxTF, gridBagConstraints);
        panel5.add(this.seatxTF);
        this.m88m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 5, 3, 1, 1, 50, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m88m, gridBagConstraints);
        panel5.add(this.m88m);
        this.m33m = new Label("Seatstay end above rear axle ", 2);
        this.buildConst(gridBagConstraints, 3, 4, 1, 1, 50, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m33m, gridBagConstraints);
        panel5.add(this.m33m);
        this.seatyTF = new TextField("15", 3);
        this.buildConst(gridBagConstraints, 4, 4, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.seatyTF, gridBagConstraints);
        panel5.add(this.seatyTF);
        this.m89m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 5, 4, 1, 1, 50, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m89m, gridBagConstraints);
        panel5.add(this.m89m);
        this.m34m = new Label("Seatstay center offset from dropout face ", 2);
        this.buildConst(gridBagConstraints, 3, 5, 1, 1, 50, 1);
        gridBagConstraints.anchor = 12;
        gridBagLayout.setConstraints(this.m34m, gridBagConstraints);
        panel5.add(this.m34m);
        this.seatzTF = new TextField("6", 3);
        this.buildConst(gridBagConstraints, 4, 5, 1, 1, 20, 1);
        gridBagConstraints.anchor = 12;
        gridBagLayout.setConstraints(this.seatzTF, gridBagConstraints);
        panel5.add(this.seatzTF);
        this.m90m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 5, 5, 1, 1, 50, 100);
        gridBagConstraints.anchor = 18;
        gridBagLayout.setConstraints(this.m90m, gridBagConstraints);
        panel5.add(this.m90m);
        this.m37m = new Label("Seatstay junction from top of seat tube ", 2);
        this.buildConst(gridBagConstraints, 0, 1, 1, 1, 100, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m37m, gridBagConstraints);
        panel6.add(this.m37m);
        this.seatstayTF = new TextField("40", 3);
        this.buildConst(gridBagConstraints, 1, 1, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.seatstayTF, gridBagConstraints);
        panel6.add(this.seatstayTF);
        this.m38m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 1, 1, 1, 10, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m38m, gridBagConstraints);
        panel6.add(this.m38m);
        this.m39m = new Label("Seatstay mounting points on seat tube ", 2);
        this.buildConst(gridBagConstraints, 0, 2, 1, 1, 100, 1);
        gridBagConstraints.ipady = -8;
        gridBagConstraints.anchor = 14;
        gridBagLayout.setConstraints(this.m39m, gridBagConstraints);
        panel6.add(this.m39m);
        this.m40m = new Label("(0° = rear of seat tube, 90° = side of seat tube) ", 2);
        this.buildConst(gridBagConstraints, 0, 3, 1, 1, 100, 1);
        gridBagConstraints.ipady = -8;
        gridBagConstraints.anchor = 12;
        gridBagLayout.setConstraints(this.m40m, gridBagConstraints);
        panel6.add(this.m40m);
        this.seatsangTF = new TextField("45", 3);
        this.buildConst(gridBagConstraints, 1, 2, 1, 2, 20, 1);
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.seatsangTF, gridBagConstraints);
        panel6.add(this.seatsangTF);
        this.m41m = new Label(" degrees", 0);
        this.buildConst(gridBagConstraints, 2, 2, 1, 2, 10, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m41m, gridBagConstraints);
        panel6.add(this.m41m);
        this.m68m = new Label("Seatstay diameter ", 2);
        this.buildConst(gridBagConstraints, 0, 4, 1, 1, 100, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m68m, gridBagConstraints);
        panel6.add(this.m68m);
        this.ssdTF = new TextField("12", 3);
        this.buildConst(gridBagConstraints, 1, 4, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.ssdTF, gridBagConstraints);
        panel6.add(this.ssdTF);
        this.m69m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 4, 1, 1, 10, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m69m, gridBagConstraints);
        panel6.add(this.m69m);
        this.sbend = new Checkbox("Enable seatstay bend", true);
        this.buildConst(gridBagConstraints, 0, 5, 1, 1, 10, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.sbend, gridBagConstraints);
        this.sbend.addItemListener(this);
        panel6.add(this.sbend);
        this.m59m = new Label("Angle and radius of seatstay bend ", 2);
        this.buildConst(gridBagConstraints, 0, 6, 1, 1, 100, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m59m, gridBagConstraints);
        panel6.add(this.m59m);
        this.seatsbendTF = new TextField("10", 3);
        this.buildConst(gridBagConstraints, 1, 6, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.seatsbendTF, gridBagConstraints);
        panel6.add(this.seatsbendTF);
        this.m60m = new Label(" degrees", 0);
        this.buildConst(gridBagConstraints, 2, 6, 1, 1, 10, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m60m, gridBagConstraints);
        panel6.add(this.m60m);
        this.seatsradTF = new TextField("50", 3);
        this.buildConst(gridBagConstraints, 3, 6, 1, 1, 0, 1);
        gridBagLayout.setConstraints(this.seatsradTF, gridBagConstraints);
        panel6.add(this.seatsradTF);
        this.m67m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 4, 6, 1, 1, 100, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m67m, gridBagConstraints);
        panel6.add(this.m67m);
        this.m61m = new Label("Seatstay length above bend ", 2);
        this.buildConst(gridBagConstraints, 0, 7, 1, 1, 100, 1);
        gridBagConstraints.anchor = 12;
        gridBagLayout.setConstraints(this.m61m, gridBagConstraints);
        panel6.add(this.m61m);
        this.seatstopTF = new TextField("135", 3);
        this.buildConst(gridBagConstraints, 1, 7, 1, 1, 20, 1);
        gridBagConstraints.anchor = 12;
        gridBagLayout.setConstraints(this.seatstopTF, gridBagConstraints);
        panel6.add(this.seatstopTF);
        this.m62m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 7, 1, 1, 10, 100);
        gridBagConstraints.anchor = 18;
        gridBagLayout.setConstraints(this.m62m, gridBagConstraints);
        panel6.add(this.m62m);
        this.m70m = new Label("Chainstay taper length ", 2);
        this.buildConst(gridBagConstraints, 0, 0, 1, 1, 100, 2);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m70m, gridBagConstraints);
        panel7.add(this.m70m);
        this.chainstaperTF = new TextField("220", 3);
        this.buildConst(gridBagConstraints, 1, 0, 1, 1, 20, 2);
        gridBagLayout.setConstraints(this.chainstaperTF, gridBagConstraints);
        panel7.add(this.chainstaperTF);
        this.m71m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 0, 1, 1, 10, 2);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m71m, gridBagConstraints);
        panel7.add(this.m71m);
        this.m72m = new Label("Center of chainstay from outer face of BB ", 2);
        this.buildConst(gridBagConstraints, 0, 1, 1, 1, 100, 2);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m72m, gridBagConstraints);
        panel7.add(this.m72m);
        this.chainsposTF = new TextField("15", 3);
        this.buildConst(gridBagConstraints, 1, 1, 1, 1, 20, 2);
        gridBagLayout.setConstraints(this.chainsposTF, gridBagConstraints);
        panel7.add(this.chainsposTF);
        this.m73m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 1, 1, 1, 10, 2);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m73m, gridBagConstraints);
        panel7.add(this.m73m);
        this.m75m = new Label("Chainstay diameter at narrow end ", 2);
        this.buildConst(gridBagConstraints, 0, 2, 1, 1, 100, 2);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m75m, gridBagConstraints);
        panel7.add(this.m75m);
        this.cebackdiaTF = new TextField("13", 3);
        this.buildConst(gridBagConstraints, 1, 2, 1, 1, 20, 2);
        gridBagLayout.setConstraints(this.cebackdiaTF, gridBagConstraints);
        panel7.add(this.cebackdiaTF);
        this.m76m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 2, 1, 1, 10, 2);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m76m, gridBagConstraints);
        panel7.add(this.m76m);
        this.m77m = new Label("Chainstay front cross-section in horizontal plane ", 2);
        this.buildConst(gridBagConstraints, 0, 3, 1, 1, 100, 2);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m77m, gridBagConstraints);
        panel7.add(this.m77m);
        this.cefronthTF = new TextField("20.8", 3);
        this.buildConst(gridBagConstraints, 1, 3, 1, 1, 20, 2);
        gridBagLayout.setConstraints(this.cefronthTF, gridBagConstraints);
        panel7.add(this.cefronthTF);
        this.m78m = new Label(" mm,", 0);
        this.buildConst(gridBagConstraints, 2, 3, 1, 1, 10, 2);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m78m, gridBagConstraints);
        panel7.add(this.m78m);
        this.m79m = new Label("in vertical plane ", 2);
        this.buildConst(gridBagConstraints, 3, 3, 2, 1, 0, 2);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m79m, gridBagConstraints);
        panel7.add(this.m79m);
        this.cefrontvTF = new TextField("29.7", 3);
        this.buildConst(gridBagConstraints, 5, 3, 1, 1, 20, 2);
        gridBagLayout.setConstraints(this.cefrontvTF, gridBagConstraints);
        panel7.add(this.cefrontvTF);
        this.m80m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 6, 3, 1, 1, 100, 2);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m80m, gridBagConstraints);
        panel7.add(this.m80m);
        this.cbend = new Checkbox("Enable chainstay bend", true);
        this.buildConst(gridBagConstraints, 0, 4, 1, 1, 10, 2);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.cbend, gridBagConstraints);
        panel7.add(this.cbend);
        this.cbend.addItemListener(this);
        this.m63m = new Label("Angle and radius of chainstay bend ", 2);
        this.buildConst(gridBagConstraints, 0, 5, 1, 1, 100, 2);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m63m, gridBagConstraints);
        panel7.add(this.m63m);
        this.chainsbendTF = new TextField("12", 3);
        this.buildConst(gridBagConstraints, 1, 5, 1, 1, 20, 2);
        gridBagLayout.setConstraints(this.chainsbendTF, gridBagConstraints);
        panel7.add(this.chainsbendTF);
        this.m64m = new Label(" degrees", 0);
        this.buildConst(gridBagConstraints, 2, 5, 1, 1, 10, 2);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m64m, gridBagConstraints);
        panel7.add(this.m64m);
        this.chainsradTF = new TextField("50", 3);
        this.buildConst(gridBagConstraints, 3, 5, 1, 1, 0, 2);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.chainsradTF, gridBagConstraints);
        panel7.add(this.chainsradTF);
        this.m74m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 4, 5, 1, 1, 0, 2);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m74m, gridBagConstraints);
        panel7.add(this.m74m);
        this.m65m = new Label("Chainstay length in front of bend ", 2);
        this.buildConst(gridBagConstraints, 0, 6, 1, 1, 100, 2);
        gridBagConstraints.anchor = 12;
        gridBagLayout.setConstraints(this.m65m, gridBagConstraints);
        panel7.add(this.m65m);
        this.chainsfrontTF = new TextField("70", 3);
        this.buildConst(gridBagConstraints, 1, 6, 1, 1, 20, 2);
        gridBagConstraints.anchor = 12;
        gridBagLayout.setConstraints(this.chainsfrontTF, gridBagConstraints);
        panel7.add(this.chainsfrontTF);
        this.m66m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 6, 1, 1, 10, 100);
        gridBagConstraints.anchor = 18;
        gridBagLayout.setConstraints(this.m66m, gridBagConstraints);
        panel7.add(this.m66m);
        this.m42m = new Label("Fork length ", 2);
        this.buildConst(gridBagConstraints, 0, 0, 1, 1, 100, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m42m, gridBagConstraints);
        panel4.add(this.m42m);
        this.forklengthTF = new TextField("443", 3);
        this.buildConst(gridBagConstraints, 1, 0, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.forklengthTF, gridBagConstraints);
        panel4.add(this.forklengthTF);
        this.m43m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 0, 1, 1, 0, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m43m, gridBagConstraints);
        panel4.add(this.m43m);
        this.fo.addItem("1998 Kinesis Maxlight (MTN)");
        this.fo.addItem("1998 Kinesis Crosslight (Cross) 45mm Offset");
        this.fo.addItem("1998 Kinesis Crosslight (Cross) 40mm Offset");
        this.fo.addItem("1998 Kinesis Road 700c 45mm Offset");
        this.fo.addItem("1998 Kinesis Road 700c 40mm Offset");
        this.fo.addItem("1998 Kinesis Road 650c");
        this.fo.addItem("1998 RockShox Judy T2/XC/SL 63mm Travel");
        this.fo.addItem("1998 RockShox Judy T2/XC/SL 80mm Travel");
        this.fo.addItem("1998 RockShox Sid");
        this.fo.addItem("1998 RockShox JXL 80mm Travel");
        this.fo.addItem("1998 RockShox JXL 101.6mm Travel");
        this.fo.addItem("1998 RockShox Boxxer");
        this.fo.addItem("1998 RockShox Ruby");
        this.fo.addItem("1999 RockShox Jett C");
        this.fo.addItem("1999 RockShox Jett T2 63mm Travel");
        this.fo.addItem("1999 RockShox Jett T2 75mm Travel");
        this.fo.addItem("1999 RockShox Jett XC 60mm (+12neg) Travel");
        this.fo.addItem("1999 RockShox Jett XC 75mm (+12neg) Travel");
        this.fo.addItem("1999 RockShox Judy C/XC 63mm Travel");
        this.fo.addItem("1999 RockShox Judy C/XC 80mm Travel");
        this.fo.addItem("1999 RockShox Judy 100");
        this.fo.addItem("1999 RockShox Judy SL 63mm Travel");
        this.fo.addItem("1999 RockShox Judy SL 80mm Travel");
        this.fo.addItem("1999 RockShox Judy XLC 80mm Travel");
        this.fo.addItem("1999 RockShox Judy XLC 97mm Travel");
        this.fo.addItem("1999 RockShox Judy XL");
        this.fo.addItem("1999 RockShox Sid XC/SLTi 63mm Travel");
        this.fo.addItem("1999 RockShox Sid XC/SLTi 80mm Travel");
        this.fo.addItem("1999 RockShox Sid XL 80mm Travel");
        this.fo.addItem("1999 RockShox Sid XL 101.6mm Travel");
        this.fo.addItem("1999 RockShox Boxxer");
        this.fo.addItem("1999 RockShox Ruby");
        this.fo.addItem("1999 RockShox Ruby Metro");
        this.fo.addItem("Custom");
        this.fo.select(33);
        this.buildConst(gridBagConstraints, 3, 0, 1, 1, 50, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.fo, gridBagConstraints);
        panel4.add(this.fo);
        this.m44m = new Label("Fork offset ", 2);
        this.buildConst(gridBagConstraints, 0, 1, 1, 1, 100, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m44m, gridBagConstraints);
        panel4.add(this.m44m);
        this.forkrakeTF = new TextField("42.3", 3);
        this.buildConst(gridBagConstraints, 1, 1, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.forkrakeTF, gridBagConstraints);
        panel4.add(this.forkrakeTF);
        this.m45m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 1, 1, 1, 0, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m45m, gridBagConstraints);
        panel4.add(this.m45m);
        this.m82m = new Label("Fork travel (non suspension=0) ", 2);
        this.buildConst(gridBagConstraints, 0, 2, 1, 1, 100, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m82m, gridBagConstraints);
        panel4.add(this.m82m);
        this.ftravelTF = new TextField("80", 3);
        this.buildConst(gridBagConstraints, 1, 2, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.ftravelTF, gridBagConstraints);
        panel4.add(this.ftravelTF);
        this.m83m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 2, 1, 1, 0, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m83m, gridBagConstraints);
        panel4.add(this.m83m);
        this.fanim = new Checkbox("Animate suspension");
        this.buildConst(gridBagConstraints, 3, 2, 1, 1, 50, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.fanim, gridBagConstraints);
        this.fanim.addItemListener(this);
        panel4.add(this.fanim);
        this.m84m = new Label("Fork sag (non suspension=0) ", 2);
        this.buildConst(gridBagConstraints, 0, 3, 1, 1, 100, 1);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m84m, gridBagConstraints);
        panel4.add(this.m84m);
        this.sagTF = new TextField("16", 3);
        this.buildConst(gridBagConstraints, 1, 3, 1, 1, 20, 1);
        gridBagLayout.setConstraints(this.sagTF, gridBagConstraints);
        panel4.add(this.sagTF);
        this.m83m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 3, 1, 1, 0, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m83m, gridBagConstraints);
        panel4.add(this.m83m);
        this.m46m = new Label("Lower stack height ", 2);
        this.buildConst(gridBagConstraints, 0, 4, 1, 1, 100, 1);
        gridBagConstraints.anchor = 12;
        gridBagLayout.setConstraints(this.m46m, gridBagConstraints);
        panel4.add(this.m46m);
        this.lowstackTF = new TextField("11", 3);
        this.buildConst(gridBagConstraints, 1, 4, 1, 1, 20, 1);
        gridBagConstraints.anchor = 12;
        gridBagLayout.setConstraints(this.lowstackTF, gridBagConstraints);
        panel4.add(this.lowstackTF);
        this.m47m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 4, 1, 1, 0, 100);
        gridBagConstraints.anchor = 18;
        gridBagLayout.setConstraints(this.m47m, gridBagConstraints);
        panel4.add(this.m47m);
        this.m48m = new Label("BB Shell diameter", 2);
        this.buildConst(gridBagConstraints, 0, 0, 1, 1, 100, 10);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m48m, gridBagConstraints);
        panel2.add(this.m48m);
        this.BBdtf = new TextField("38.1", 3);
        this.buildConst(gridBagConstraints, 1, 0, 1, 1, 20, 10);
        gridBagLayout.setConstraints(this.BBdtf, gridBagConstraints);
        panel2.add(this.BBdtf);
        this.m49m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 0, 1, 1, 10, 10);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m49m, gridBagConstraints);
        panel2.add(this.m49m);
        this.m50m = new Label("Seat tube diameter", 2);
        this.buildConst(gridBagConstraints, 0, 1, 1, 1, 100, 10);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m50m, gridBagConstraints);
        panel2.add(this.m50m);
        this.stDiamTF = new TextField("28.6", 3);
        this.buildConst(gridBagConstraints, 1, 1, 1, 1, 20, 10);
        gridBagLayout.setConstraints(this.stDiamTF, gridBagConstraints);
        panel2.add(this.stDiamTF);
        this.m51m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 1, 1, 1, 10, 10);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m51m, gridBagConstraints);
        panel2.add(this.m51m);
        this.stdia.addItem("1 1/8 inch");
        this.stdia.addItem("1 1/4 inch");
        this.stdia.addItem("32.5 mm");
        this.stdia.addItem("1 1/2 inch");
        this.stdia.addItem("Custom");
        this.stdia.select(0);
        this.buildConst(gridBagConstraints, 3, 1, 1, 1, 100, 10);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.stdia, gridBagConstraints);
        panel2.add(this.stdia);
        this.m52m = new Label("Top tube diameter", 2);
        this.buildConst(gridBagConstraints, 0, 2, 1, 1, 100, 10);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m52m, gridBagConstraints);
        panel2.add(this.m52m);
        this.ttDiamTF = new TextField("28.6", 3);
        this.buildConst(gridBagConstraints, 1, 2, 1, 1, 20, 10);
        gridBagLayout.setConstraints(this.ttDiamTF, gridBagConstraints);
        panel2.add(this.ttDiamTF);
        this.m53m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 2, 1, 1, 10, 10);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m53m, gridBagConstraints);
        panel2.add(this.m53m);
        this.ttdia.addItem("1 inch");
        this.ttdia.addItem("26 mm");
        this.ttdia.addItem("1 1/8 inch");
        this.ttdia.addItem("1 1/4 inch");
        this.ttdia.addItem("1 3/8 inch");
        this.ttdia.addItem("1 1/2 inch");
        this.ttdia.addItem("Custom");
        this.ttdia.select(2);
        this.buildConst(gridBagConstraints, 3, 2, 1, 1, 100, 10);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.ttdia, gridBagConstraints);
        panel2.add(this.ttdia);
        this.m54m = new Label("Down tube diameter", 2);
        this.buildConst(gridBagConstraints, 0, 3, 1, 1, 100, 10);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m54m, gridBagConstraints);
        panel2.add(this.m54m);
        this.dtDiamTF = new TextField("28.6", 3);
        this.buildConst(gridBagConstraints, 1, 3, 1, 1, 20, 10);
        gridBagLayout.setConstraints(this.dtDiamTF, gridBagConstraints);
        panel2.add(this.dtDiamTF);
        this.m55m = new Label(" mm", 0);
        this.buildConst(gridBagConstraints, 2, 3, 1, 1, 10, 10);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.m55m, gridBagConstraints);
        panel2.add(this.m55m);
        this.dtdia.addItem("28 mm");
        this.dtdia.addItem("1 1/8 inch");
        this.dtdia.addItem("1 3/8 inch");
        this.dtdia.addItem("1 3/4 inch");
        this.dtdia.addItem("2 inch");
        this.dtdia.addItem("Custom");
        this.dtdia.select(2);
        this.buildConst(gridBagConstraints, 3, 3, 1, 1, 100, 10);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.dtdia, gridBagConstraints);
        panel2.add(this.dtdia);
        this.m56m = new Label("Head tube diameter", 2);
        this.buildConst(gridBagConstraints, 0, 4, 1, 1, 100, 10);
        gridBagConstraints.anchor = 12;
        gridBagLayout.setConstraints(this.m56m, gridBagConstraints);
        panel2.add(this.m56m);
        this.htDiamTF = new TextField("36.4", 3);
        this.buildConst(gridBagConstraints, 1, 4, 1, 1, 20, 10);
        gridBagConstraints.anchor = 12;
        gridBagLayout.setConstraints(this.htDiamTF, gridBagConstraints);
        panel2.add(this.htDiamTF);
        this.m57m = new Label("mm", 0);
        this.buildConst(gridBagConstraints, 2, 4, 1, 1, 10, 10);
        gridBagConstraints.anchor = 18;
        gridBagLayout.setConstraints(this.m57m, gridBagConstraints);
        panel2.add(this.m57m);
        this.htdia.addItem("1 1/4 inch");
        this.htdia.addItem("33.0 mm");
        this.htdia.addItem("1 3/8 inch");
        this.htdia.addItem("36.4 mm");
        this.htdia.addItem("37.0 mm");
        this.htdia.addItem("1 5/8 inch");
        this.htdia.addItem("42.9 mm");
        this.htdia.addItem("43.4 mm");
        this.htdia.addItem("Custom");
        this.htdia.select(3);
        this.buildConst(gridBagConstraints, 3, 4, 1, 1, 100, 10);
        gridBagConstraints.anchor = 18;
        gridBagLayout.setConstraints(this.htdia, gridBagConstraints);
        panel2.add(this.htdia);
        this.m91m = new Label("The metric tube diameter value will change automatically when a tube diameter is selected", 2);
        this.buildConst(gridBagConstraints, 0, 5, 4, 1, 100, 0);
        gridBagLayout.setConstraints(this.m91m, gridBagConstraints);
        panel2.add(this.m91m);
        this.m91m.setForeground(Color.red);
        this.m92m = new Label("on the right. If you want to change the metric value directly, be sure to change the selection", 2);
        this.buildConst(gridBagConstraints, 0, 6, 4, 1, 100, 0);
        gridBagLayout.setConstraints(this.m92m, gridBagConstraints);
        panel2.add(this.m92m);
        this.m92m.setForeground(Color.red);
        this.m93m = new Label("menu to read \"Custom\".", 2);
        this.buildConst(gridBagConstraints, 0, 7, 4, 1, 100, 100);
        gridBagLayout.setConstraints(this.m93m, gridBagConstraints);
        panel2.add(this.m93m);
        this.m93m.setForeground(Color.red);
        final Button button5 = new Button("Update");
        this.buildConst(gridBagConstraints, 0, 0, 1, 1, 1, 1);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(button5, gridBagConstraints);
        panel8.add(button5);
        this.bList[4] = (Button)panel8.add(button5);
        this.m58m = new Label("Click \"Update\" after modifications", 2);
        this.buildConst(gridBagConstraints, 1, 0, 1, 1, 100, 100);
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.m58m, gridBagConstraints);
        panel8.add(this.m58m);
        this.Framestats = new TextArea("Click on \"Update\" to show all frame building info.\nThis text can be cut and pasted into a text document for\nfuture reference.\n\nCopywrite ©2000 The Bicycle Forest Inc.", 10, 53);
        this.buildConst(gridBagConstraints, 0, 1, 5, 1, 100, 100);
        gridBagConstraints.fill = 1;
        gridBagLayout.setConstraints(this.Framestats, gridBagConstraints);
        this.Framestats.setEditable(false);
        panel8.add(this.Framestats);
        this.optionCards.add("MajorDims", panel);
        this.optionCards.add("Tubediameters", panel2);
        this.optionCards.add("Wheelsizes", panel3);
        this.optionCards.add("Forkdimensions", panel4);
        this.optionCards.add("Rearend", panel6);
        this.optionCards.add("MinorDims", panel5);
        this.optionCards.add("Cstay", panel7);
        this.optionCards.add("Buildinfo", panel8);
        this.add(this.optionCards);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() instanceof Checkbox) {
            final Object source = itemEvent.getSource();
            if (source == this.sideview) {
                this.region.showwhat = 1;
            }
            if (source == this.csview) {
                this.region.showwhat = 2;
            }
            if (source == this.ssview) {
                this.region.showwhat = 3;
            }
            if (source == this.stabview) {
                this.region.showwhat = 4;
            }
        }
        final Object source2 = itemEvent.getSource();
        final boolean fanimy = itemEvent.getStateChange() == 1;
        if (source2 == this.cbend) {
            this.region.cbendy = fanimy;
            return;
        }
        if (source2 == this.sbend) {
            this.region.sbendy = fanimy;
            return;
        }
        if (source2 == this.fanim) {
            this.region.fanimy = fanimy;
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Choice) {
            this.whereto = this.modcho1.getSelectedIndex();
            if (this.whereto == 0) {
                this.cardLO.show(this.optionCards, "MajorDims");
            }
            if (this.whereto == 1) {
                this.cardLO.show(this.optionCards, "Tubediameters");
            }
            if (this.whereto == 2) {
                this.cardLO.show(this.optionCards, "Wheelsizes");
            }
            if (this.whereto == 3) {
                this.cardLO.show(this.optionCards, "Forkdimensions");
            }
            if (this.whereto == 4) {
                this.cardLO.show(this.optionCards, "MinorDims");
            }
            if (this.whereto == 5) {
                this.cardLO.show(this.optionCards, "Rearend");
            }
            if (this.whereto == 6) {
                this.cardLO.show(this.optionCards, "Cstay");
            }
            if (this.whereto == 7) {
                this.cardLO.show(this.optionCards, "Buildinfo");
            }
            this.whereto = this.fo.getSelectedIndex();
            if (this.whereto == 0) {
                this.region.ftravel = 0.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 0.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 45.0;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 398.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 1) {
                this.region.ftravel = 0.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 0.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 45.0;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 403.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 2) {
                this.region.ftravel = 0.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 0.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 40.0;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 403.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 3) {
                this.region.ftravel = 0.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 0.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 45.0;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 365.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 4) {
                this.region.ftravel = 0.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 0.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 40.0;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 365.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 5) {
                this.region.ftravel = 0.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 0.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 35.0;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 340.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 6) {
                this.region.ftravel = 63.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 12.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 41.9;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 426.5;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 7) {
                this.region.ftravel = 80.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 16.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 42.3;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 443.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 8) {
                this.region.ftravel = 60.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 10.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 41.9;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 422.1;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 9) {
                this.region.ftravel = 80.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 16.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 42.3;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 443.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 10) {
                this.region.ftravel = 101.6;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 20.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 42.3;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 464.6;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 11) {
                this.region.ftravel = 152.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 45.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 41.9;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 516.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 12) {
                this.region.ftravel = 30.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 5.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 45.0;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 395.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 13) {
                this.region.ftravel = 48.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 8.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 38.6;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 424.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 14) {
                this.region.ftravel = 63.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 12.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 38.6;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 424.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 15) {
                this.region.ftravel = 75.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 15.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 39.4;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 437.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 16) {
                this.region.ftravel = 60.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 12.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 38.6;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 424.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 17) {
                this.region.ftravel = 75.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 15.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 39.4;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 437.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 18) {
                this.region.ftravel = 63.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 12.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 41.9;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 426.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 19) {
                this.region.ftravel = 80.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 16.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 42.3;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 443.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 20) {
                this.region.ftravel = 97.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 20.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 42.7;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 460.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 21) {
                this.region.ftravel = 63.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 12.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 41.9;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 426.5;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 22) {
                this.region.ftravel = 80.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 16.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 42.3;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 443.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 23) {
                this.region.ftravel = 80.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 16.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 42.3;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 446.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 24) {
                this.region.ftravel = 97.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 20.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 42.3;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 460.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 25) {
                this.region.ftravel = 101.6;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 20.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 42.3;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 464.6;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 26) {
                this.region.ftravel = 63.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 12.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 41.9;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 434.2;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 27) {
                this.region.ftravel = 80.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 16.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 42.3;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 451.2;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 28) {
                this.region.ftravel = 80.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 16.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 42.3;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 443.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 29) {
                this.region.ftravel = 101.6;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 20.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 42.3;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 464.6;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 30) {
                this.region.ftravel = 148.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 45.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 41.9;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 516.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 31) {
                this.region.ftravel = 30.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 6.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 45.0;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 395.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            if (this.whereto == 32) {
                this.region.ftravel = 30.0;
                this.ftravelTF.setText(String.valueOf(this.region.ftravel));
                this.region.sag = 6.0;
                this.sagTF.setText(String.valueOf(this.region.sag));
                this.region.rakelen = 44.3;
                this.forkrakeTF.setText(String.valueOf(this.region.rakelen));
                this.region.forklen = 428.0;
                this.forklengthTF.setText(String.valueOf(this.region.forklen));
            }
            this.whereto = this.rw.getSelectedIndex();
            if (this.whereto == 0) {
                this.region.rearwd = 486.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 44.4;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 1) {
                this.region.rearwd = 558.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 25.4;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 2) {
                this.region.rearwd = 560.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 23.0;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 3) {
                this.region.rearwd = 614.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 23.0;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 4) {
                this.region.rearwd = 609.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 25.4;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 5) {
                this.region.rearwd = 626.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 31.8;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 6) {
                this.region.rearwd = 628.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 38.1;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 7) {
                this.region.rearwd = 652.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 49.5;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 8) {
                this.region.rearwd = 660.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 55.8;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 9) {
                this.region.rearwd = 658.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 10) {
                this.region.rearwd = 663.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 23.0;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 11) {
                this.region.rearwd = 655.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 20.0;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 12) {
                this.region.rearwd = 658.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 21.0;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 13) {
                this.region.rearwd = 662.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 23.0;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 14) {
                this.region.rearwd = 664.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 25.0;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 15) {
                this.region.rearwd = 667.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 28.0;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 16) {
                this.region.rearwd = 673.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 32.0;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 17) {
                this.region.rearwd = 681.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 35.0;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 18) {
                this.region.rearwd = 661.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 22.2;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 19) {
                this.region.rearwd = 670.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 25.4;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 20) {
                this.region.rearwd = 676.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 28.6;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            if (this.whereto == 21) {
                this.region.rearwd = 682.0;
                this.rweelDtf.setText(String.valueOf(this.region.rearwd));
                this.region.rearww = 31.8;
                this.rweelWtf.setText(String.valueOf(this.region.rearww));
            }
            this.whereto = this.fw.getSelectedIndex();
            if (this.whereto == 0) {
                this.region.frontwd = 486.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 44.4;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 1) {
                this.region.frontwd = 558.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 25.4;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 2) {
                this.region.frontwd = 560.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 23.0;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 3) {
                this.region.frontwd = 614.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 23.0;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 4) {
                this.region.frontwd = 609.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 25.4;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 5) {
                this.region.frontwd = 626.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 31.8;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 6) {
                this.region.frontwd = 628.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 38.1;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 7) {
                this.region.frontwd = 652.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 49.5;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 8) {
                this.region.frontwd = 660.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 55.8;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 9) {
                this.region.frontwd = 658.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 34.9;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 10) {
                this.region.frontwd = 663.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 23.0;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 11) {
                this.region.frontwd = 655.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 20.0;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 12) {
                this.region.frontwd = 658.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 21.0;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 13) {
                this.region.frontwd = 662.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 23.0;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 14) {
                this.region.frontwd = 664.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 25.0;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 15) {
                this.region.frontwd = 667.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 28.0;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 16) {
                this.region.frontwd = 673.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 32.0;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 17) {
                this.region.frontwd = 681.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 35.0;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 18) {
                this.region.frontwd = 661.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 22.2;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 19) {
                this.region.frontwd = 670.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 25.4;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 20) {
                this.region.frontwd = 676.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 28.6;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            if (this.whereto == 21) {
                this.region.frontwd = 682.0;
                this.fweelDtf.setText(String.valueOf(this.region.frontwd));
                this.region.frontww = 31.8;
                this.fweelWtf.setText(String.valueOf(this.region.frontww));
            }
            this.whereto = this.stdia.getSelectedIndex();
            if (this.whereto == 0) {
                this.region.stdiameter = 28.6;
                this.stDiamTF.setText(String.valueOf(this.region.stdiameter));
            }
            if (this.whereto == 1) {
                this.region.stdiameter = 31.8;
                this.stDiamTF.setText(String.valueOf(this.region.stdiameter));
            }
            if (this.whereto == 2) {
                this.region.stdiameter = 32.5;
                this.stDiamTF.setText(String.valueOf(this.region.stdiameter));
            }
            if (this.whereto == 3) {
                this.region.stdiameter = 38.1;
                this.stDiamTF.setText(String.valueOf(this.region.stdiameter));
            }
            this.whereto = this.ttdia.getSelectedIndex();
            if (this.whereto == 0) {
                this.region.ttdiameter = 25.4;
                this.ttDiamTF.setText(String.valueOf(this.region.ttdiameter));
            }
            if (this.whereto == 1) {
                this.region.ttdiameter = 26.0;
                this.ttDiamTF.setText(String.valueOf(this.region.ttdiameter));
            }
            if (this.whereto == 2) {
                this.region.ttdiameter = 28.6;
                this.ttDiamTF.setText(String.valueOf(this.region.ttdiameter));
            }
            if (this.whereto == 3) {
                this.region.ttdiameter = 31.8;
                this.ttDiamTF.setText(String.valueOf(this.region.ttdiameter));
            }
            if (this.whereto == 4) {
                this.region.ttdiameter = 34.9;
                this.ttDiamTF.setText(String.valueOf(this.region.ttdiameter));
            }
            if (this.whereto == 5) {
                this.region.ttdiameter = 38.1;
                this.ttDiamTF.setText(String.valueOf(this.region.ttdiameter));
            }
            this.whereto = this.dtdia.getSelectedIndex();
            if (this.whereto == 0) {
                this.region.dtdiameter = 28.0;
                this.dtDiamTF.setText(String.valueOf(this.region.dtdiameter));
            }
            if (this.whereto == 1) {
                this.region.dtdiameter = 28.6;
                this.dtDiamTF.setText(String.valueOf(this.region.dtdiameter));
            }
            if (this.whereto == 2) {
                this.region.dtdiameter = 34.9;
                this.dtDiamTF.setText(String.valueOf(this.region.dtdiameter));
            }
            if (this.whereto == 3) {
                this.region.dtdiameter = 44.5;
                this.dtDiamTF.setText(String.valueOf(this.region.dtdiameter));
            }
            if (this.whereto == 4) {
                this.region.dtdiameter = 50.8;
                this.dtDiamTF.setText(String.valueOf(this.region.dtdiameter));
            }
            this.whereto = this.htdia.getSelectedIndex();
            if (this.whereto == 0) {
                this.region.htdiameter = 31.8;
                this.htDiamTF.setText(String.valueOf(this.region.htdiameter));
            }
            if (this.whereto == 1) {
                this.region.htdiameter = 33.0;
                this.htDiamTF.setText(String.valueOf(this.region.htdiameter));
            }
            if (this.whereto == 2) {
                this.region.htdiameter = 34.9;
                this.htDiamTF.setText(String.valueOf(this.region.htdiameter));
            }
            if (this.whereto == 3) {
                this.region.htdiameter = 36.4;
                this.htDiamTF.setText(String.valueOf(this.region.htdiameter));
            }
            if (this.whereto == 4) {
                this.region.htdiameter = 37.0;
                this.htDiamTF.setText(String.valueOf(this.region.htdiameter));
            }
            if (this.whereto == 5) {
                this.region.htdiameter = 41.3;
                this.htDiamTF.setText(String.valueOf(this.region.htdiameter));
            }
            if (this.whereto == 6) {
                this.region.htdiameter = 42.9;
                this.htDiamTF.setText(String.valueOf(this.region.htdiameter));
            }
            if (this.whereto == 7) {
                this.region.htdiameter = 43.4;
                this.htDiamTF.setText(String.valueOf(this.region.htdiameter));
            }
            this.whereto = this.bb.getSelectedIndex();
            if (this.whereto == 0) {
                this.region.BBlen = 68.0;
            }
            if (this.whereto == 1) {
                this.region.BBlen = 70.0;
            }
            if (this.whereto == 2) {
                this.region.BBlen = 73.0;
            }
            return true;
        }
        if (event.target instanceof Button) {
            if (event.target == this.bList[0]) {
                double headangle = Double.valueOf(this.angleHtf.getText()) + 0.5;
                if (headangle < 10.0) {
                    headangle = 10.0;
                }
                if (headangle > 170.0) {
                    headangle = 170.0;
                }
                this.region.headangle = headangle;
                this.angleHtf.setText(String.valueOf((float)headangle));
            }
            if (event.target == this.bList[1]) {
                double headangle2 = Double.valueOf(this.angleHtf.getText()) - 0.5;
                if (headangle2 < 10.0) {
                    headangle2 = 10.0;
                }
                if (headangle2 > 170.0) {
                    headangle2 = 170.0;
                }
                this.region.headangle = headangle2;
                this.angleHtf.setText(String.valueOf((float)headangle2));
            }
            if (event.target == this.bList[2]) {
                double seatangle = Double.valueOf(this.angleStf.getText()) + 0.5;
                if (seatangle < 10.0) {
                    seatangle = 10.0;
                }
                if (seatangle > 170.0) {
                    seatangle = 170.0;
                }
                this.region.seatangle = seatangle;
                this.angleStf.setText(String.valueOf((float)seatangle));
            }
            if (event.target == this.bList[3]) {
                double seatangle2 = Double.valueOf(this.angleStf.getText()) - 0.5;
                if (seatangle2 < 10.0) {
                    seatangle2 = 10.0;
                }
                if (seatangle2 > 170.0) {
                    seatangle2 = 170.0;
                }
                this.region.seatangle = seatangle2;
                this.angleStf.setText(String.valueOf((float)seatangle2));
            }
            if (event.target == this.bList[4]) {
                this.Framestats.setText("Frame Angles\n\t*Headangle\t= " + String.valueOf((float)this.region.headangle) + "°\n\t*Seatangle\t= " + String.valueOf((float)this.region.seatangle) + "°\n\nMajor Lengths\n\tWheelbase\t\t= " + String.valueOf((float)this.region.wheelbase) + " mm\n\t*Seat tube length (c-t)\t= " + String.valueOf((float)this.region.stlength) + " mm\n\tSeat tube length (c-c)\t= " + String.valueOf((float)this.region.stlength2) + " mm\n\tTop tube length (effective)\t= " + String.valueOf((float)this.region.ttlength) + " mm\n\tTop tube length (c-c)\t= " + String.valueOf((float)this.region.ttlength2) + " mm\n\t*Head tube length\t\t= " + String.valueOf(this.region.htlength) + " mm\n\t*Bottom bracket height\t= " + String.valueOf(this.region.BBh) + " mm\n\tBottom bracket drop\t= " + String.valueOf(this.region.rearwd / 2.0 - this.region.BBh) + " mm\n\t*Chainstay length\t\t= " + String.valueOf(this.region.CSlen) + " mm\n\t*Front Center\t\t= " + String.valueOf(this.region.FCdist) + " mm\n\nWheel Specs\n\t*Rear wheel diameter\t= " + String.valueOf(this.region.rearwd) + " mm\n\t*Rear tire width\t\t= " + String.valueOf(this.region.rearww) + " mm\n\t*Front wheel diameter\t= " + String.valueOf(this.region.frontwd) + " mm\n\t*Front tire width\t\t= " + String.valueOf(this.region.frontww) + " mm\n\tGap between rear tire and seat tube\t= " + String.valueOf((float)this.region.wgap) + " mm\n\t*Dropout spacing\t\t= " + String.valueOf(this.region.dropoutspace) + " mm\n\nFork and Headset Specs\n\t*Fork offset\t= " + String.valueOf(this.region.rakelen) + " mm\n\t" + this.region.forkstring + "\n\t*Lower stack height\t= " + String.valueOf(this.region.lowerstack) + " mm\n\nStability Info\n\tTrail\t= " + String.valueOf((float)this.region.trailmm) + " mm\n\tJones stability criterion\t= " + String.valueOf((float)this.region.jonestab) + "\n\tRelative front projection\t= " + String.valueOf((float)this.region.relfrontproj) + "\n\nTube Diameters\n\t*Seat tube diameter\t= " + String.valueOf(this.region.stdiameter) + " mm\n\t*Top tube diameter\t= " + String.valueOf(this.region.ttdiameter) + " mm\n\t*Head tube diameter\t\t= " + String.valueOf(this.region.htdiameter) + " mm\n\t*Down tube diameter\t\t= " + String.valueOf(this.region.dtdiameter) + " mm\n\nSeatstay Info\n\t*Seatstay diameter\t= " + String.valueOf(this.region.sebackdia) + " mm\n\tDirect distance from rear axle to center of seatstay at seat tube junction\t= " + String.valueOf((float)this.region.SSlen2) + " mm\n\t" + this.region.sbendstring + "\n\t*Distance from top of seat tube to center of seatstay\t= " + String.valueOf(this.region.staypos) + " mm\n\t*Location of center of seatstay at seat tube junction (0° = rear of seat tube, 90° = side of seat tube)\t= " + String.valueOf(this.region.stayang) + "°\n\nChainstay Info\n\t*Chainstay diameter at dropout\t= " + String.valueOf(this.region.chbackdia) + " mm\n\t*Chainstay taper length\t= " + String.valueOf(this.region.chtaper) + " mm\n\t*Chainstay front width in vertical plane\t= " + String.valueOf(this.region.chvdia) + " mm\n\t*Chainstay front width in horizontal plane\t= " + String.valueOf(this.region.chhdia) + " mm\n\t*Offset of chainstay center from outer face of BB\t = " + String.valueOf(this.region.chpos) + " mm\n\t" + this.region.cbendstring + "\n\nBottom Bracket Info\n\t*Bottom bracket length\t= " + String.valueOf(this.region.BBlen) + " mm\n\t*Bottom bracket diameter\t= " + String.valueOf(this.region.BBd) + " mm\n\nTube Mitring Info\n\tMitre to mitre distance along top of top tube\t= " + String.valueOf((float)this.region.tttcut) + " mm\n\tMitre to mitre distance along bottom of top tube\t= " + String.valueOf((float)this.region.ttbcut) + " mm\n\tMitre to center of BB measured along top of down tube\t= " + String.valueOf((float)this.region.dttcut) + " mm\n\tMitre to center of BB measured along bottom of down tube\t= " + String.valueOf((float)this.region.dtbcut) + " mm\n\tTop face of seat tube to BB mitre (measured along side of frame)\t= " + String.valueOf(this.region.stcut) + " mm\n\tDown tube length (c-c)\t\t= " + String.valueOf((float)this.region.dtlength) + " mm\n\tSeat tube, top tube interior angle\t= " + String.valueOf((float)this.region.stttangle2) + "°\n\tTop tube angle relative to horizontal\t= " + String.valueOf((float)this.region.ttangle2) + "°\n\tTop tube, head tube interior angle\t= " + String.valueOf((float)this.region.tthtangle2) + "°\n\tHead tube, down tube interior angle\t= " + String.valueOf((float)this.region.htdtangle2) + "°\n\tDown tube angle relative to horizontal\t= " + String.valueOf((float)this.region.dtangle2) + "°\n\tChainstay/seatstay angle\t= " + String.valueOf((float)this.region.sscsangle2) + "°\n\tChainstay angle relative to horizontal\t= " + String.valueOf((float)this.region.csangle2) + "°\n\tSeatstay angle relative to horizontal\t= " + String.valueOf((float)this.region.ssangle2) + "°\n\nWelding Clearance Info\n\t*Head tube extension above top edge of top tube\t= " + String.valueOf(this.region.htupext) + " mm\n\t*Head tube extension below bottom edge of down tube\t= " + String.valueOf(this.region.htlowext) + " mm\n\tGap between bottom of top tube and top of down tube at head tube junction\t= " + String.valueOf((float)this.region.ttdtgap) + " mm\n\t*Seat tube extension above top edge of top tube\t = " + String.valueOf(this.region.stextension) + " mm\n\tExtension of outer face of BB from side edge of chainstay\t= " + String.valueOf((float)this.region.BBext) + " mm\n\tGap between inside edges of chainstays at BB junction\t= " + String.valueOf((float)this.region.csgap) + " mm\n\nRear Dropout Info\n\t*Horizontal distance of chainstay end in front of rear axle\t= " + String.valueOf(this.region.chainx) + " mm\n\t*Vertical distance of chainstay end above rear axle\t= " + String.valueOf(this.region.chainy) + " mm\n\t*Offset of chainstay center from rear dropout inner face\t= " + String.valueOf(this.region.chainz) + " mm\n\t*Horizontal distance of seatstay end in front of rear axle\t= " + String.valueOf(this.region.seatx) + " mm\n\t*Vertical distance of seatstay end above rear axle\t= " + String.valueOf(this.region.seaty) + " mm\n\t*Offset of seatstay center from rear dropout inner face\t= " + String.valueOf(this.region.seatz) + " mm\n\n* = value defined by the user");
            }
            return true;
        }
        if (event.target instanceof TextField) {
            this.headangle = Double.valueOf(this.angleHtf.getText());
            if (this.headangle < 5.0) {
                this.angleHtf.setText(String.valueOf(5.0));
                this.headangle = 5.0;
            }
            if (this.headangle > 170.0) {
                this.angleHtf.setText(String.valueOf(170.0));
                this.headangle = 170.0;
            }
            this.region.headangle = this.headangle;
            this.seatangle = Double.valueOf(this.angleStf.getText());
            if (this.seatangle < 5.0) {
                this.angleStf.setText(String.valueOf(5.0));
                this.seatangle = 5.0;
            }
            if (this.seatangle > 170.0) {
                this.angleStf.setText(String.valueOf(170.0));
                this.seatangle = 170.0;
            }
            this.region.seatangle = this.seatangle;
            this.frontwd = Double.valueOf(this.fweelDtf.getText());
            if (this.frontwd < 5.0) {
                this.fweelDtf.setText(String.valueOf(5.0));
                this.frontwd = 5.0;
            }
            if (this.frontwd > 3000.0) {
                this.fweelDtf.setText(String.valueOf(3000));
                this.frontwd = 3000.0;
            }
            this.region.frontwd = this.frontwd;
            this.rearwd = Double.valueOf(this.rweelDtf.getText());
            if (this.rearwd < 5.0) {
                this.rweelDtf.setText(String.valueOf(5.0));
                this.rearwd = 5.0;
            }
            if (this.rearwd > 3000.0) {
                this.rweelDtf.setText(String.valueOf(3000));
                this.rearwd = 3000.0;
            }
            this.region.rearwd = this.rearwd;
            this.dropoutspace = Double.valueOf(this.dropTF.getText());
            if (this.dropoutspace < 5.0) {
                this.dropTF.setText(String.valueOf(5.0));
                this.dropoutspace = 5.0;
            }
            if (this.dropoutspace > 1000.0) {
                this.dropTF.setText(String.valueOf(1000));
                this.dropoutspace = 1000.0;
            }
            this.region.dropoutspace = this.dropoutspace;
            this.rearww = Double.valueOf(this.rweelWtf.getText());
            if (this.rearww < 1.0) {
                this.rweelWtf.setText(String.valueOf(1.0));
                this.rearww = 1.0;
            }
            if (this.rearww > this.dropoutspace) {
                this.rweelWtf.setText(String.valueOf(this.dropoutspace));
                this.rearww = this.dropoutspace;
            }
            this.region.rearww = this.rearww;
            this.frontww = Double.valueOf(this.fweelWtf.getText());
            if (this.frontww < 1.0) {
                this.fweelWtf.setText(String.valueOf(1.0));
                this.frontww = 1.0;
            }
            if (this.frontww > this.dropoutspace) {
                this.fweelWtf.setText(String.valueOf(this.dropoutspace));
                this.frontww = this.dropoutspace;
            }
            this.region.frontww = this.frontww;
            this.BBd = Double.valueOf(this.BBdtf.getText());
            if (this.BBd < 5.0) {
                this.BBdtf.setText(String.valueOf(5.0));
                this.BBd = 5.0;
            }
            if (this.BBd > 500.0) {
                this.BBdtf.setText(String.valueOf(500.0));
                this.BBd = 500.0;
            }
            this.region.BBd = this.BBd;
            this.BBh = Double.valueOf(this.BBhtf.getText());
            if (this.BBh < 5.0) {
                this.BBhtf.setText(String.valueOf(5.0));
                this.BBh = 5.0;
            }
            if (this.BBh > 5000.0) {
                this.BBhtf.setText(String.valueOf(5000));
                this.BBh = 5000.0;
            }
            this.region.BBh = this.BBh;
            this.stlength = Double.valueOf(this.SThtf.getText());
            if (this.stlength < 0.0) {
                this.SThtf.setText(String.valueOf(0));
                this.stlength = 0.0;
            }
            if (this.stlength > 5000.0) {
                this.SThtf.setText(String.valueOf(5000));
                this.stlength = 5000.0;
            }
            this.region.stlength = this.stlength;
            this.htlength = Double.valueOf(this.headtubeTF.getText());
            if (this.htlength < 0.0) {
                this.headtubeTF.setText(String.valueOf(0));
                this.htlength = 0.0;
            }
            if (this.htlength > 5000.0) {
                this.headtubeTF.setText(String.valueOf(5000));
                this.htlength = 5000.0;
            }
            this.region.htlength = this.htlength;
            this.htlowext = Double.valueOf(this.headlowextTF.getText());
            if (this.htlowext < 0.0) {
                this.headlowextTF.setText(String.valueOf(0));
                this.htlowext = 0.0;
            }
            if (this.htlowext > 5000.0) {
                this.headlowextTF.setText(String.valueOf(5000));
                this.htlowext = 5000.0;
            }
            this.region.htlowext = this.htlowext;
            this.htupext = Double.valueOf(this.headupextTF.getText());
            if (this.htupext < 0.0) {
                this.headupextTF.setText(String.valueOf(0));
                this.htupext = 0.0;
            }
            if (this.htupext > 5000.0) {
                this.headupextTF.setText(String.valueOf(5000));
                this.htupext = 5000.0;
            }
            this.region.htupext = this.htupext;
            this.CSlen = Double.valueOf(this.chainstayTF.getText());
            if (this.CSlen < this.rearwd / 2.0 + this.BBd / 2.0) {
                this.chainstayTF.setText(String.valueOf(this.rearwd / 2.0 + this.BBd / 2.0));
                this.CSlen = this.rearwd / 2.0 + this.BBd / 2.0;
            }
            if (this.CSlen > 5000.0) {
                this.chainstayTF.setText(String.valueOf(5000));
                this.CSlen = 5000.0;
            }
            this.region.CSlen = this.CSlen;
            this.FCdist = Double.valueOf(this.frontcenterTF.getText());
            if (this.FCdist < this.frontwd / 2.0 + this.BBd / 2.0) {
                this.frontcenterTF.setText(String.valueOf(this.frontwd / 2.0 + this.BBd / 2.0));
                this.FCdist = this.frontwd / 2.0 + this.BBd / 2.0;
            }
            if (this.FCdist > 5000.0) {
                this.frontcenterTF.setText(String.valueOf(5000));
                this.FCdist = 5000.0;
            }
            this.region.FCdist = this.FCdist;
            this.rakelen = Double.valueOf(this.forkrakeTF.getText());
            if (this.rakelen < -500.0) {
                this.forkrakeTF.setText(String.valueOf(-500));
                this.rakelen = -500.0;
            }
            if (this.rakelen > 5000.0) {
                this.forkrakeTF.setText(String.valueOf(5000));
                this.rakelen = 5000.0;
            }
            this.region.rakelen = this.rakelen;
            this.forklen = Double.valueOf(this.forklengthTF.getText());
            if (this.forklen < this.frontwd / 2.0) {
                this.forklengthTF.setText(String.valueOf(this.frontwd / 2.0));
                this.forklen = this.frontwd / 2.0;
            }
            if (this.forklen > 5000.0) {
                this.forklengthTF.setText(String.valueOf(5000));
                this.forklen = 5000.0;
            }
            this.region.forklen = this.forklen;
            this.lowerstack = Double.valueOf(this.lowstackTF.getText());
            if (this.lowerstack < 0.0) {
                this.lowstackTF.setText(String.valueOf(0));
                this.lowerstack = 0.0;
            }
            if (this.lowerstack > 5000.0) {
                this.lowstackTF.setText(String.valueOf(5000));
                this.lowerstack = 5000.0;
            }
            this.region.lowerstack = this.lowerstack;
            this.stdiameter = Double.valueOf(this.stDiamTF.getText());
            if (this.stdiameter < 0.0) {
                this.stDiamTF.setText(String.valueOf(0));
                this.stdiameter = 0.0;
            }
            if (this.stdiameter > 500.0) {
                this.stDiamTF.setText(String.valueOf(500));
                this.stdiameter = 500.0;
            }
            this.region.stdiameter = this.stdiameter;
            this.ttdiameter = Double.valueOf(this.ttDiamTF.getText());
            if (this.ttdiameter < 0.0) {
                this.ttDiamTF.setText(String.valueOf(0));
                this.ttdiameter = 0.0;
            }
            if (this.ttdiameter > 500.0) {
                this.ttDiamTF.setText(String.valueOf(500));
                this.ttdiameter = 500.0;
            }
            this.region.ttdiameter = this.ttdiameter;
            this.dtdiameter = Double.valueOf(this.dtDiamTF.getText());
            if (this.dtdiameter < 0.0) {
                this.dtDiamTF.setText(String.valueOf(0));
                this.dtdiameter = 0.0;
            }
            if (this.dtdiameter > 500.0) {
                this.dtDiamTF.setText(String.valueOf(500));
                this.dtdiameter = 500.0;
            }
            this.region.dtdiameter = this.dtdiameter;
            this.htdiameter = Double.valueOf(this.htDiamTF.getText());
            if (this.htdiameter < 0.0) {
                this.htDiamTF.setText(String.valueOf(0));
                this.htdiameter = 0.0;
            }
            if (this.htdiameter > 500.0) {
                this.htDiamTF.setText(String.valueOf(500));
                this.htdiameter = 500.0;
            }
            this.region.htdiameter = this.htdiameter;
            this.stextension = Double.valueOf(this.STexthtf.getText());
            if (this.stextension < 0.0) {
                this.STexthtf.setText(String.valueOf(0));
                this.stextension = 0.0;
            }
            if (this.stextension > this.stlength + this.ttdiameter) {
                this.STexthtf.setText(String.valueOf(this.stlength + this.ttdiameter));
                this.stextension = this.stlength + this.ttdiameter;
            }
            this.region.stextension = this.stextension;
            this.chainx = Double.valueOf(this.chainxTF.getText());
            if (this.chainx < -300.0) {
                this.chainxTF.setText(String.valueOf(-300));
                this.chainx = -300.0;
            }
            if (this.chainx > 300.0) {
                this.chainxTF.setText(String.valueOf(300));
                this.chainx = 300.0;
            }
            this.region.chainx = this.chainx;
            this.chainy = Double.valueOf(this.chainyTF.getText());
            if (this.chainy < -300.0) {
                this.chainyTF.setText(String.valueOf(-300));
                this.chainy = -300.0;
            }
            if (this.chainy > 300.0) {
                this.chainyTF.setText(String.valueOf(300));
                this.chainy = 300.0;
            }
            this.region.chainy = this.chainy;
            this.chainz = Double.valueOf(this.chainzTF.getText());
            if (this.chainz < -300.0) {
                this.chainzTF.setText(String.valueOf(-300));
                this.chainz = -300.0;
            }
            if (this.chainz > 300.0) {
                this.chainzTF.setText(String.valueOf(300));
                this.chainz = 300.0;
            }
            this.region.chainz = this.chainz;
            this.seatx = Double.valueOf(this.seatxTF.getText());
            if (this.seatx < -300.0) {
                this.seatxTF.setText(String.valueOf(-300));
                this.seatx = -300.0;
            }
            if (this.seatx > 300.0) {
                this.seatxTF.setText(String.valueOf(300));
                this.seatx = 300.0;
            }
            this.region.seatx = this.seatx;
            this.seaty = Double.valueOf(this.seatyTF.getText());
            if (this.seaty < -300.0) {
                this.seatyTF.setText(String.valueOf(-300));
                this.seaty = -300.0;
            }
            if (this.seaty > 300.0) {
                this.seatyTF.setText(String.valueOf(300));
                this.seaty = 300.0;
            }
            this.region.seaty = this.seaty;
            this.seatz = Double.valueOf(this.seatzTF.getText());
            if (this.seatz < -300.0) {
                this.seatzTF.setText(String.valueOf(-300));
                this.seatz = -300.0;
            }
            if (this.seatz > 300.0) {
                this.seatzTF.setText(String.valueOf(300));
                this.seatz = 300.0;
            }
            this.region.seatz = this.seatz;
            this.staypos = Double.valueOf(this.seatstayTF.getText());
            if (this.staypos < 0.0) {
                this.seatstayTF.setText(String.valueOf(0));
                this.staypos = 0.0;
            }
            if (this.staypos > this.stlength) {
                this.seatstayTF.setText(String.valueOf(this.stlength));
                this.staypos = this.stlength;
            }
            this.region.staypos = this.staypos;
            this.stayang = Double.valueOf(this.seatsangTF.getText());
            if (this.stayang < 0.0) {
                this.seatsangTF.setText(String.valueOf(0));
                this.stayang = 0.0;
            }
            if (this.stayang > 120.0) {
                this.seatsangTF.setText(String.valueOf(120));
                this.stayang = 120.0;
            }
            this.region.stayang = this.stayang;
            this.sbendang = Double.valueOf(this.seatsbendTF.getText());
            if (this.sbendang < -45.0) {
                this.seatsbendTF.setText(String.valueOf(-45));
                this.sbendang = -45.0;
            }
            if (this.sbendang > 120.0) {
                this.seatsbendTF.setText(String.valueOf(120));
                this.sbendang = 120.0;
            }
            this.region.sbendang = this.sbendang;
            this.sstoplen = Double.valueOf(this.seatstopTF.getText());
            if (this.sstoplen < 1.0) {
                this.seatstopTF.setText(String.valueOf(1));
                this.sstoplen = 1.0;
            }
            if (this.sstoplen > this.region.SSlen2) {
                this.seatstopTF.setText(String.valueOf(this.region.SSlen2));
                this.sstoplen = this.region.SSlen2;
            }
            this.region.sstoplen = this.sstoplen;
            this.sradius = Double.valueOf(this.seatsradTF.getText());
            if (this.sradius < this.region.sebackdia) {
                this.seatsradTF.setText(String.valueOf(this.region.sebackdia));
                this.sradius = this.region.sebackdia;
            }
            if (this.sradius > 5000.0) {
                this.seatsradTF.setText(String.valueOf(5000));
                this.sradius = 5000.0;
            }
            this.region.sradius = this.sradius;
            this.sebackdia = Double.valueOf(this.ssdTF.getText());
            if (this.sebackdia < 2.0) {
                this.ssdTF.setText(String.valueOf(2));
                this.sebackdia = 2.0;
            }
            if (this.sebackdia > 300.0) {
                this.ssdTF.setText(String.valueOf(300));
                this.sebackdia = 300.0;
            }
            this.region.sebackdia = this.sebackdia;
            this.chtaper = Double.valueOf(this.chainstaperTF.getText());
            if (this.chtaper < 5.0) {
                this.chainstaperTF.setText(String.valueOf(5));
                this.chtaper = 5.0;
            }
            if (this.chtaper > 1000.0) {
                this.chainstaperTF.setText(String.valueOf(1000));
                this.chtaper = 1000.0;
            }
            this.region.chtaper = this.chtaper;
            this.chpos = Double.valueOf(this.chainsposTF.getText());
            if (this.chpos < 0.0) {
                this.chainsposTF.setText(String.valueOf(0));
                this.chpos = 0.0;
            }
            if (this.chpos > this.region.BBlen) {
                this.chainsposTF.setText(String.valueOf(this.region.BBlen));
                this.chpos = this.region.BBlen;
            }
            this.region.chpos = this.chpos;
            this.chbackdia = Double.valueOf(this.cebackdiaTF.getText());
            if (this.chbackdia < 3.0) {
                this.cebackdiaTF.setText(String.valueOf(3));
                this.chbackdia = 3.0;
            }
            if (this.chbackdia > 300.0) {
                this.cebackdiaTF.setText(String.valueOf(300));
                this.chbackdia = 300.0;
            }
            this.region.chbackdia = this.chbackdia;
            this.chvdia = Double.valueOf(this.cefrontvTF.getText());
            if (this.chvdia < 3.0) {
                this.cefrontvTF.setText(String.valueOf(3));
                this.chvdia = 3.0;
            }
            if (this.chvdia > 300.0) {
                this.cefrontvTF.setText(String.valueOf(300));
                this.chvdia = 300.0;
            }
            this.region.chvdia = this.chvdia;
            this.chhdia = Double.valueOf(this.cefronthTF.getText());
            if (this.chhdia < 3.0) {
                this.cefronthTF.setText(String.valueOf(3));
                this.chhdia = 3.0;
            }
            if (this.chhdia > 300.0) {
                this.cefronthTF.setText(String.valueOf(300));
                this.chhdia = 300.0;
            }
            this.region.chhdia = this.chhdia;
            this.chhdia = Double.valueOf(this.cefronthTF.getText());
            if (this.chhdia < 3.0) {
                this.cefronthTF.setText(String.valueOf(3));
                this.chhdia = 3.0;
            }
            if (this.chhdia > 300.0) {
                this.cefronthTF.setText(String.valueOf(300));
                this.chhdia = 300.0;
            }
            this.region.chhdia = this.chhdia;
            this.cbendang = Double.valueOf(this.chainsbendTF.getText());
            if (this.cbendang < -45.0) {
                this.chainsbendTF.setText(String.valueOf(-45));
                this.cbendang = -45.0;
            }
            if (this.cbendang > 90.0) {
                this.chainsbendTF.setText(String.valueOf(90));
                this.cbendang = 90.0;
            }
            this.region.cbendang = this.cbendang;
            this.cradius = Double.valueOf(this.chainsradTF.getText());
            if (this.cradius < this.region.chhdia) {
                this.chainsradTF.setText(String.valueOf(this.region.chhdia));
                this.cradius = this.region.chhdia;
            }
            if (this.cradius > 1000.0) {
                this.chainsradTF.setText(String.valueOf(1000));
                this.cradius = 1000.0;
            }
            this.region.cradius = this.cradius;
            this.csfrontlen = Double.valueOf(this.chainsfrontTF.getText());
            if (this.csfrontlen < 5.0) {
                this.chainsfrontTF.setText(String.valueOf(5));
                this.csfrontlen = 5.0;
            }
            if (this.csfrontlen > this.region.CSlen) {
                this.chainsfrontTF.setText(String.valueOf(this.region.CSlen));
                this.csfrontlen = this.region.CSlen;
            }
            this.region.csfrontlen = this.csfrontlen;
            this.ftravel = Double.valueOf(this.ftravelTF.getText());
            if (this.ftravel < 0.0) {
                this.ftravelTF.setText(String.valueOf(0));
                this.ftravel = 0.0;
            }
            if (this.ftravel > 300.0) {
                this.ftravelTF.setText(String.valueOf(300));
                this.ftravel = 300.0;
            }
            this.region.ftravel = this.ftravel;
            this.sag = Double.valueOf(this.sagTF.getText());
            if (this.sag < 0.0) {
                this.sagTF.setText(String.valueOf(0));
                this.sag = 0.0;
            }
            if (this.sag > this.region.ftravel) {
                this.sagTF.setText(String.valueOf(this.region.ftravel));
                this.sag = this.region.ftravel;
            }
            this.region.sag = this.sag;
            return true;
        }
        return false;
    }
}
