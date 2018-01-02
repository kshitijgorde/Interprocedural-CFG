// 
// Decompiled by Procyon v0.5.30
// 

package xpl;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.LayoutManager;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.CheckboxGroup;
import java.applet.Applet;

public class XplApplet extends Applet
{
    XplView xpv;
    XplView xpv2;
    int firstInit;
    int presetId;
    int maxPresets;
    CheckboxGroup checkboxGroup1;
    Panel panel1;
    GridLayout gridLayout1;
    slider speedSlider;
    slider lifeSlider;
    slider radiusSlider;
    slider sizeSlider;
    slider colorSlider;
    Panel panel2;
    GridLayout gridLayout2;
    Label label1;
    Label label2;
    Label label3;
    Label label4;
    Label label5;
    Panel panel3;
    GridLayout gridLayout3;
    slider rslider;
    slider bslider;
    slider gslider;
    Panel panel4;
    GridLayout gridLayout4;
    Label label6;
    Label label7;
    Label label8;
    slider slider1;
    slider slider2;
    TextField textField1;
    String[] paramsStr;
    Button bBtn;
    Button fBtn;
    TextField textField2;
    Label label9;
    slider g2rslider;
    slider g2rYslider3;
    
    public XplApplet() {
        this.xpv = null;
        this.xpv2 = null;
        this.firstInit = 1;
        this.presetId = 10;
        this.checkboxGroup1 = new CheckboxGroup();
        this.panel1 = new Panel();
        this.gridLayout1 = new GridLayout();
        this.speedSlider = new slider();
        this.lifeSlider = new slider();
        this.radiusSlider = new slider();
        this.sizeSlider = new slider();
        this.colorSlider = new slider();
        this.panel2 = new Panel();
        this.gridLayout2 = new GridLayout();
        this.label1 = new Label();
        this.label2 = new Label();
        this.label3 = new Label();
        this.label4 = new Label();
        this.label5 = new Label();
        this.panel3 = new Panel();
        this.gridLayout3 = new GridLayout();
        this.rslider = new slider();
        this.bslider = new slider();
        this.gslider = new slider();
        this.panel4 = new Panel();
        this.gridLayout4 = new GridLayout();
        this.label6 = new Label();
        this.label7 = new Label();
        this.label8 = new Label();
        this.slider1 = new slider();
        this.slider2 = new slider();
        this.textField1 = new TextField();
        this.bBtn = new Button();
        this.fBtn = new Button();
        this.textField2 = new TextField();
        this.label9 = new Label();
        this.g2rslider = new slider();
        this.g2rYslider3 = new slider();
        (this.paramsStr = new String[20])[0] = new String("<Ablation by Mixy7@aol.com>2.118.136.20.179.73.35.14.9.1.157.43.2.324.1.");
        this.paramsStr[1] = new String("<Micky Mouse by David Martin>154.63.30.68.20.60.39.5.5.40.118.40.0.2.1.");
        this.paramsStr[2] = new String("<Vagary>172.74.82.79.56.64.0.5.1.28.103.108.3.180.180.");
        this.paramsStr[3] = new String("<Gluki>10.247.10.247.2.247.21.3.7.3.124.38.2.180.180.");
        this.paramsStr[4] = new String("<Rabid>2.247.2.247.2.247.0.7.7.29.124.76.2.180.180.");
        this.paramsStr[5] = new String("<Dimensional>2.247.55.116.60.60.41.5.9.5.187.23.2.180.180.");
        this.paramsStr[6] = new String("<Zombie>2.247.2.247.2.247.0.36.3.5.212.13.3.180.180.");
        this.paramsStr[7] = new String("<Macrocosm>141.93.5.63.2.247.0.12.5.25.214.17.1.180.180.");
        this.paramsStr[8] = new String("<Big Mac>131.118.141.106.151.98.13.1.1.54.230.16.3.56.1.");
        this.paramsStr[9] = new String("<Imaginary>2.247.2.247.2.247.0.23.2.24.55.293.1.180.180.");
        this.paramsStr[10] = new String("<$>196.53.128.83.176.73.0.0.7.28.128.50.3.1.1.");
        this.paramsStr[11] = new String("<Trace>126.123.128.121.148.100.15.0.2.28.225.17.3.206.181.");
        this.paramsStr[12] = new String("<Lambent>2.123.2.121.154.100.15.0.2.69.231.18.3.34.69.");
        this.paramsStr[13] = new String("<Falusion by Kat =^,,^= >108.88.75.116.191.60.26.9.9.5.282.24.1.180.180.");
        this.paramsStr[14] = new String("<Massaraksh>131.123.2.121.2.100.28.1.3.58.310.6.2.177.192.");
        this.paramsStr[15] = new String("<Butterflies by Ensi Kay>2.242.2.179.2.186.0.22.5.26.183.115.0.1.202.");
        this.paramsStr[16] = new String("<Peachy by Ensi Kay>172.74.82.79.56.64.40.5.4.28.111.109.2.116.111.");
        this.paramsStr[17] = new String("<McDonalds by David Martin>186.63.151.68.2.60.48.0.0.9.11.138.0.1.316.");
        this.maxPresets = 17;
    }
    
    public void init() {
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.setBackground(new Color(128, 128, 207));
        this.setForeground(Color.black);
        this.setSize(new Dimension(620, 448));
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(final MouseEvent mouseEvent) {
                XplApplet.this.this_mouseMoved(mouseEvent);
            }
        });
        this.addMouseListener(new MouseAdapter() {});
        this.setLayout(null);
        this.panel1.setLayout(this.gridLayout1);
        this.gridLayout1.setRows(5);
        this.gridLayout1.setVgap(2);
        this.speedSlider.setMax(100);
        this.lifeSlider.setMax(350);
        this.radiusSlider.setMax(50);
        this.sizeSlider.setMin(77);
        this.sizeSlider.setMax(290);
        this.sizeSlider.setSliderPos(0.65f);
        this.sizeSlider.setSliderWidth(0.03f);
        this.colorSlider.setMax(5);
        this.colorSlider.setPos1(45);
        this.panel2.setLayout(this.gridLayout2);
        this.gridLayout2.setRows(5);
        this.gridLayout2.setVgap(2);
        this.label1.setText("Radius");
        this.label2.setText("Speed");
        this.label3.setText("Life");
        this.label4.setText("Shading");
        this.label5.setText("Size");
        this.panel3.setLayout(this.gridLayout3);
        this.gridLayout3.setColumns(3);
        this.rslider.setMax(255);
        this.rslider.setPos1(0);
        this.rslider.setSliderPos(0.11f);
        this.rslider.setSliderWidth(0.2f);
        this.rslider.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
                XplApplet.this.rslider_adjustmentValueChanged(adjustmentEvent);
            }
        });
        this.gslider.setMax(255);
        this.gslider.setSwidth(111);
        this.gslider.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
                XplApplet.this.gslider_adjustmentValueChanged(adjustmentEvent);
            }
        });
        this.bslider.setMax(255);
        this.bslider.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
                XplApplet.this.bslider_adjustmentValueChanged(adjustmentEvent);
            }
        });
        this.panel4.setLayout(this.gridLayout4);
        this.gridLayout4.setColumns(3);
        this.label6.setForeground(Color.red);
        this.label6.setText("red");
        this.label7.setForeground(new Color(0, 250, 0));
        this.label7.setText("green");
        this.label8.setForeground(Color.blue);
        this.label8.setText("blue");
        this.panel2.setBounds(new Rectangle(564, 54, 51, 126));
        this.panel1.setBounds(new Rectangle(299, 55, 254, 126));
        this.panel3.setBounds(new Rectangle(299, 23, 303, 20));
        this.panel4.setBounds(new Rectangle(347, 2, 288, 15));
        this.textField1.setBounds(new Rectangle(370, 276, 245, 17));
        this.textField1.setFont(new Font("Serif", 0, 11));
        this.textField1.setForeground(Color.black);
        this.textField1.setEditable(false);
        this.textField1.setText("Massaraksh");
        this.bBtn.setBounds(new Rectangle(300, 257, 33, 34));
        this.bBtn.setLabel("<<");
        this.bBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                XplApplet.this.bBtn_actionPerformed(actionEvent);
            }
        });
        this.fBtn.setLabel(">>");
        this.fBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                XplApplet.this.fBtn_actionPerformed(actionEvent);
            }
        });
        this.fBtn.setBounds(new Rectangle(333, 257, 33, 34));
        this.textField2.setBounds(new Rectangle(370, 252, 245, 23));
        this.textField2.setFont(new Font("Dialog", 0, 12));
        this.textField2.setForeground(Color.black);
        this.textField2.setEditable(false);
        this.textField2.setText("Massaraksh");
        this.label9.setBounds(new Rectangle(301, 233, 241, 16));
        this.label9.setFont(new Font("Dialog", 0, 13));
        this.label9.setForeground(Color.black);
        this.label9.setText("Select one of LifeExplosion presets");
        this.g2rslider.setMax(360);
        this.g2rslider.setBounds(new Rectangle(298, 191, 254, 18));
        this.g2rslider.setResizable(false);
        this.g2rslider.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
                XplApplet.this.g2rslider_adjustmentValueChanged(adjustmentEvent);
            }
        });
        this.g2rYslider3.setMax(360);
        this.g2rYslider3.setBounds(new Rectangle(298, 211, 254, 18));
        this.g2rYslider3.setResizable(false);
        this.g2rYslider3.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
                XplApplet.this.g2rYslider3_adjustmentValueChanged(adjustmentEvent);
            }
        });
        this.add(this.panel4, null);
        this.panel4.add(this.label6, null);
        this.panel4.add(this.label7, null);
        this.panel4.add(this.label8, null);
        this.add(this.panel3, null);
        this.panel3.add(this.rslider, null);
        this.panel3.add(this.gslider, null);
        this.panel3.add(this.bslider, null);
        this.add(this.panel1, null);
        this.panel1.add(this.radiusSlider, null);
        this.panel1.add(this.speedSlider, null);
        this.panel1.add(this.lifeSlider, null);
        this.panel1.add(this.colorSlider, null);
        this.panel1.add(this.sizeSlider, null);
        this.add(this.panel2, null);
        this.panel2.add(this.label1, null);
        this.panel2.add(this.label2, null);
        this.panel2.add(this.label3, null);
        this.panel2.add(this.label4, null);
        this.panel2.add(this.label5, null);
        this.add(this.textField2, null);
        this.add(this.g2rslider, null);
        this.add(this.g2rYslider3, null);
        this.add(this.label9, null);
        this.add(this.textField1, null);
        this.add(this.bBtn, null);
        this.add(this.fBtn, null);
        this.speedSlider.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
                XplApplet.this.speedSlider_adjustmentValueChanged(adjustmentEvent);
            }
        });
        this.lifeSlider.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
                XplApplet.this.lifeSlider_adjustmentValueChanged(adjustmentEvent);
            }
        });
        this.radiusSlider.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
                XplApplet.this.radiusSlider_adjustmentValueChanged(adjustmentEvent);
            }
        });
        this.sizeSlider.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
                XplApplet.this.sizeSlider_adjustmentValueChanged(adjustmentEvent);
            }
        });
        this.colorSlider.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
                XplApplet.this.colorSlider_adjustmentValueChanged(adjustmentEvent);
            }
        });
        this.initXpl();
    }
    
    public String getAppletInfo() {
        return "Massarksh";
    }
    
    public String[][] getParameterInfo() {
        return null;
    }
    
    void radiusSlider_adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.xpv.minR = this.radiusSlider.getSliderIntPos();
        this.xpv.deltaR = this.radiusSlider.getSliderIntWidth();
        this.xpv.stepsBeforeNew = 0;
        this.fillParams();
    }
    
    void lifeSlider_adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.xpv.lifeTime = this.lifeSlider.getSliderIntPos();
        this.xpv.sleepK = 1 + this.lifeSlider.getSliderIntWidth();
        this.xpv.stepsBeforeNew = 0;
        this.fillParams();
    }
    
    void colorSlider_adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.xpv.shadeK = this.colorSlider.getSliderIntPos();
        this.xpv.stepsBeforeNew = 0;
        this.fillParams();
    }
    
    void speedSlider_adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.xpv.speed = this.speedSlider.getSliderIntPos() / 10.0f;
        this.xpv.spiralK = this.speedSlider.getSliderIntWidth();
        this.xpv.stepsBeforeNew = 0;
        this.fillParams();
    }
    
    void sizeSlider_adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final int destw = this.xpv.destw;
        final int desth = this.xpv.desth;
        if (this.xpv != null) {
            this.xpv.stop();
        }
        this.xpv = null;
        System.gc();
        this.xpv = new XplView(this, 1, 1, this.sizeSlider.getSliderIntPos(), this.sizeSlider.getSliderIntPos(), this.sizeSlider.getSliderIntPos() + this.sizeSlider.getSliderIntWidth(), this.sizeSlider.getSliderIntPos() + this.sizeSlider.getSliderIntWidth());
        final int destw2 = this.xpv.destw;
        final int desth2 = this.xpv.desth;
        if (destw > destw2) {
            this.repaint(333L, 0, desth2, destw2, desth);
            this.repaint(333L, destw2, 0, destw, desth2);
        }
        this.xpv.start();
        this.initByStr(this.textField1.getText());
        this.fillParams();
    }
    
    void rslider_adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.xpv.gammaR = this.rslider.getSliderIntPos();
        this.xpv.gammadR = this.rslider.getSliderIntWidth();
        this.xpv.stepsBeforeNew = 0;
        this.fillParams();
    }
    
    void gslider_adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.xpv.gammaG = this.gslider.getSliderIntPos();
        this.xpv.gammadG = this.gslider.getSliderIntWidth();
        this.xpv.stepsBeforeNew = 0;
        this.fillParams();
    }
    
    void bslider_adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.xpv.gammaB = this.bslider.getSliderIntPos();
        this.xpv.gammadB = this.bslider.getSliderIntWidth();
        this.xpv.stepsBeforeNew = 0;
        this.fillParams();
    }
    
    void initSlidersByParams() {
        this.rslider.setSliderPosWidth(this.xpv.gammaR, this.xpv.gammadR);
        this.gslider.setSliderPosWidth(this.xpv.gammaG, this.xpv.gammadG);
        this.bslider.setSliderPosWidth(this.xpv.gammaB, this.xpv.gammadB);
        this.radiusSlider.setSliderPosWidth(this.xpv.minR, this.xpv.deltaR);
        this.lifeSlider.setSliderPosWidth(this.xpv.lifeTime, this.xpv.sleepK);
        this.colorSlider.setSliderPosWidth(this.xpv.shadeK, 1);
        this.speedSlider.setSliderPosWidth((int)(this.xpv.speed * 10), this.xpv.spiralK);
        this.g2rslider.setSliderPosWidth(this.xpv.xg2r, 33);
        this.g2rYslider3.setSliderPosWidth(this.xpv.yg2r, 33);
        this.xpv.stepsBeforeNew = 0;
    }
    
    void fillParams() {
        this.textField1.setText(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new String("")).concat(String.valueOf(String.valueOf(Integer.toString(this.xpv.gammaR)).concat(String.valueOf("."))))).concat(String.valueOf(String.valueOf(Integer.toString(this.xpv.gammadR)).concat(String.valueOf("."))))).concat(String.valueOf(String.valueOf(Integer.toString(this.xpv.gammaG)).concat(String.valueOf("."))))).concat(String.valueOf(String.valueOf(Integer.toString(this.xpv.gammadG)).concat(String.valueOf("."))))).concat(String.valueOf(String.valueOf(Integer.toString(this.xpv.gammaB)).concat(String.valueOf("."))))).concat(String.valueOf(String.valueOf(Integer.toString(this.xpv.gammadB)).concat(String.valueOf("."))))).concat(String.valueOf(String.valueOf(Integer.toString(this.xpv.minR)).concat(String.valueOf("."))))).concat(String.valueOf(String.valueOf(Integer.toString(this.xpv.deltaR)).concat(String.valueOf("."))))).concat(String.valueOf(String.valueOf(Integer.toString((int)this.xpv.speed)).concat(String.valueOf("."))))).concat(String.valueOf(String.valueOf(Integer.toString(this.xpv.spiralK)).concat(String.valueOf("."))))).concat(String.valueOf(String.valueOf(Integer.toString(this.xpv.lifeTime)).concat(String.valueOf("."))))).concat(String.valueOf(String.valueOf(Integer.toString(this.xpv.sleepK)).concat(String.valueOf("."))))).concat(String.valueOf(String.valueOf(Integer.toString(this.xpv.shadeK)).concat(String.valueOf("."))))).concat(String.valueOf(String.valueOf(Integer.toString(this.xpv.xg2r)).concat(String.valueOf("."))))).concat(String.valueOf(String.valueOf(Integer.toString(this.xpv.yg2r)).concat(String.valueOf(".")))));
    }
    
    void initByStr(final String s) {
        final String s2 = new String("");
        String text = new String("");
        final int[] array = new int[20];
        int i = 0;
        if (s.substring(i, i + 1).equals("<")) {
            ++i;
            String substring;
            do {
                substring = s.substring(i, i + 1);
                if (!substring.equals(">")) {
                    text = String.valueOf(text).concat(String.valueOf(substring));
                }
                ++i;
            } while (!substring.equals(">"));
            this.textField2.setText(text);
            text = "";
        }
        int n = 0;
        while (i < s.length()) {
            final String substring2 = s.substring(i, i + 1);
            if (!substring2.equals(".")) {
                text = String.valueOf(text).concat(String.valueOf(substring2));
            }
            else {
                array[n] = new Integer(text);
                text = "";
                ++n;
            }
            ++i;
        }
        this.xpv.gammaR = array[0];
        this.xpv.gammadR = array[1];
        this.xpv.gammaG = array[2];
        this.xpv.gammadG = array[3];
        this.xpv.gammaB = array[4];
        this.xpv.gammadB = array[5];
        this.xpv.minR = array[6];
        this.xpv.deltaR = array[7];
        this.xpv.speed = array[8];
        this.xpv.spiralK = array[9];
        this.xpv.lifeTime = array[10];
        this.xpv.sleepK = array[11];
        this.xpv.shadeK = array[12];
        this.xpv.xg2r = array[13];
        this.xpv.yg2r = array[14];
    }
    
    void this_mouseMoved(final MouseEvent mouseEvent) {
        if (this.firstInit == 1) {
            this.initSlidersByParams();
            this.firstInit = 0;
        }
    }
    
    public void destroy() {
        if (this.xpv != null) {
            this.xpv.stop();
        }
        this.xpv = null;
        super.destroy();
    }
    
    void initXpl() {
        if (this.xpv != null) {
            this.xpv.stop();
        }
        this.xpv = new XplView(this, 3, 3, this.sizeSlider.getSliderIntPos(), this.sizeSlider.getSliderIntPos(), this.sizeSlider.getSliderIntPos() + this.sizeSlider.getSliderIntWidth(), this.sizeSlider.getSliderIntPos() + this.sizeSlider.getSliderIntWidth());
        this.xpv.stepsBeforeNew = 0;
        this.initByStr(this.paramsStr[this.presetId]);
        this.initSlidersByParams();
        this.fillParams();
        this.xpv.start();
        this.initSlidersByParams();
    }
    
    void bBtn_actionPerformed(final ActionEvent actionEvent) {
        if (this.presetId > 0) {
            --this.presetId;
        }
        else {
            this.presetId = this.maxPresets;
        }
        this.xpv.stepsBeforeNew = 0;
        this.initByStr(this.paramsStr[this.presetId]);
        this.initSlidersByParams();
        this.fillParams();
    }
    
    void fBtn_actionPerformed(final ActionEvent actionEvent) {
        if (this.presetId < this.maxPresets) {
            ++this.presetId;
        }
        else {
            this.presetId = 0;
        }
        this.xpv.stepsBeforeNew = 0;
        this.initByStr(this.paramsStr[this.presetId]);
        this.initSlidersByParams();
        this.fillParams();
    }
    
    void g2rslider_adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.xpv.xg2r = this.g2rslider.getSliderIntPos();
        this.xpv.stepsBeforeNew = 0;
        this.fillParams();
    }
    
    void g2rYslider3_adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.xpv.yg2r = this.g2rYslider3.getSliderIntPos();
        this.xpv.stepsBeforeNew = 0;
        this.fillParams();
    }
}
