import java.awt.Window;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.Event;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ItemEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Scrollbar;
import java.awt.Button;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.AdjustmentListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class AdvBodyInd extends Applet implements AdjustmentListener
{
    TextArea tMessage;
    private Image bgimg;
    private int aWidth;
    private int aHeight;
    private int bWidth;
    private int bHeight;
    private int bC_o;
    private int bC_d;
    private int bC_z;
    private int bC_v;
    private int bC_k;
    private int bC_a;
    private int bC_n;
    private int bC_b;
    private int bC_w;
    private int bC;
    private int bXC;
    private int bXD;
    private boolean eng;
    private boolean metrisch;
    private Font wFont;
    private FontMetrics wMetrics;
    int groessek;
    int groessei;
    int groesse;
    int bmi;
    int gewichtk;
    int gewicht;
    int gewichti;
    Button info1;
    Button info2;
    Scrollbar sbGewicht;
    Scrollbar sbGroesse;
    CheckboxGroup cgLeft;
    Checkbox cm;
    Checkbox cu;
    TextField comment;
    TextField status;
    TextField infoge;
    TextField alter;
    Label slAlter;
    Label slAltere;
    Label slGre;
    Label slGr;
    Label slGee;
    Label slGe;
    Label slGroessee;
    Label slGroesse;
    Label slGewicht;
    Label slGewichte;
    Label slmetric;
    Label slus;
    Label slZ1;
    Label slZ1e;
    Label slZ2;
    Label slZ2e;
    Label slZ3;
    Label slZ3e;
    float[] hsb;
    int row;
    Insets insets;
    
    public void init() {
        final String parameter = this.getParameter("bodyok");
        this.bC_o = ((parameter == null) ? 15648347 : Integer.parseInt(parameter));
        final String parameter2 = this.getParameter("bodyzu");
        this.bC_z = ((parameter2 == null) ? 16711680 : Integer.parseInt(parameter2));
        final String parameter3 = this.getParameter("zuduen");
        this.bC_d = ((parameter3 == null) ? 16715535 : Integer.parseInt(parameter3));
        final String parameter4 = this.getParameter("kopf");
        this.bC_k = ((parameter4 == null) ? 15648347 : Integer.parseInt(parameter4));
        final String parameter5 = this.getParameter("viel");
        this.bC_v = ((parameter5 == null) ? 0 : Integer.parseInt(parameter5));
        final String parameter6 = this.getParameter("augen");
        this.bC_a = ((parameter6 == null) ? 255 : Integer.parseInt(parameter6));
        final String parameter7 = this.getParameter("nase");
        this.bC_n = ((parameter7 == null) ? 16776960 : Integer.parseInt(parameter7));
        final String parameter8 = this.getParameter("ok");
        this.bC_b = ((parameter8 == null) ? 65280 : Integer.parseInt(parameter8));
        final String parameter9 = this.getParameter("wi");
        this.bC_w = ((parameter9 == null) ? 16777215 : Integer.parseInt(parameter9));
        final String parameter10 = this.getParameter("bxc");
        this.bXC = ((parameter10 == null) ? 65280 : Integer.parseInt(parameter10));
        this.bXD = ((parameter10 == null) ? 16777215 : Integer.parseInt(parameter10));
        final String parameter11 = this.getParameter("lang");
        if (parameter11 != null && parameter11.charAt(0) == 'e') {
            this.eng = true;
            this.metrisch = false;
        }
        System.out.println("eng     =" + this.eng);
        final String parameter12 = this.getParameter("metric");
        System.out.println("metric=" + parameter12);
        if ((parameter12 != null || !this.metrisch) && (!this.metrisch || parameter12.charAt(0) == 'u')) {
            this.metrisch = false;
            this.gewicht = 330;
            this.gewichti = 330;
            this.groessei = 87;
            this.groesse = 87;
            this.sbGewicht.setValues(0, 1, 1, this.gewichti + 1);
            this.sbGroesse.setValues(0, 1, 1, this.groessei + 1);
            this.cu.getCheckboxGroup().setSelectedCheckbox(this.cu);
        }
        this.bgimg = null;
        this.setLayout(null);
        this.setFont(new Font("Helvetica", 0, 14));
        this.wFont = new Font("Arial", 0, 12);
        if (this.eng) {
            this.info1 = new Button("informationes about the bodyindex");
            this.info2 = new Button("english");
        }
        else {
            this.info1 = new Button("Informationen zum Bodymassindex");
            this.info2 = new Button("deutsch");
        }
        this.sbGroesse.addAdjustmentListener(this);
        this.sbGewicht.addAdjustmentListener(this);
        this.insertComponent(this.slmetric);
        this.insertComponent(this.slus);
        this.insertComponent(this.info1);
        this.insertComponent(this.cm);
        this.insertComponent(this.cu);
        this.insertComponent(this.alter);
        this.insertComponent(this.sbGroesse);
        this.insertComponent(this.sbGewicht);
        if (this.eng) {
            this.insertComponent(this.slAltere);
            this.insertComponent(this.slGroessee);
            this.insertComponent(this.slGewichte);
            this.insertComponent(this.slGre);
            this.insertComponent(this.slGee);
            this.insertComponent(this.slZ1e);
            this.insertComponent(this.slZ2e);
            this.insertComponent(this.slZ3e);
        }
        else {
            this.insertComponent(this.slAlter);
            this.insertComponent(this.slGroesse);
            this.insertComponent(this.slGewicht);
            this.insertComponent(this.slGr);
            this.insertComponent(this.slGe);
            this.insertComponent(this.slZ1);
            this.insertComponent(this.slZ2);
            this.insertComponent(this.slZ3);
        }
        this.insertComponent(this.tMessage = new TextArea("", 1, 40));
        this.insertComponent(this.status);
        this.insertComponent(this.infoge);
        this.insertComponent(this.comment);
        if (this.eng) {
            this.slAltere.setBounds(30, 5, 150, 20);
            this.slGroessee.setBounds(100, 30, 60, 20);
            this.slGewichte.setBounds(180, 30, 60, 20);
            this.slGre.setBounds(30, 120, 55, 20);
            this.slGee.setBounds(250, 120, 50, 20);
            this.slZ1e.setBounds(30, 220, 140, 20);
            this.slZ2e.setBounds(30, 240, 140, 20);
            this.slZ3e.setBounds(30, 260, 140, 20);
        }
        else {
            this.slAlter.setBounds(30, 5, 150, 20);
            this.slGroesse.setBounds(100, 30, 60, 20);
            this.slGewicht.setBounds(180, 30, 60, 20);
            this.slGr.setBounds(30, 120, 55, 20);
            this.slGe.setBounds(250, 120, 50, 20);
            this.slZ1.setBounds(30, 220, 140, 20);
            this.slZ2.setBounds(30, 240, 140, 20);
            this.slZ3.setBounds(30, 260, 140, 20);
        }
        this.alter.setBounds(180, 5, 40, 20);
        this.infoge.setBounds(180, 220, 60, 20);
        this.status.setBounds(180, 240, 60, 20);
        this.comment.setBounds(180, 260, 60, 20);
        this.tMessage.setBounds(30, 285, 440, 100);
        this.info1.setBounds(30, 385, 440, 20);
        this.sbGroesse.setBounds(100, 50, 60, 150);
        this.sbGewicht.setBounds(180, 50, 60, 150);
        this.status.setEditable(false);
        this.comment.setEditable(false);
        this.infoge.setEditable(false);
        this.bHeight = this.size().height / 2;
        this.bWidth = this.size().width;
        this.aWidth = this.size().width;
        this.cm.setBounds(30, 50, 15, 15);
        this.cu.setBounds(30, 70, 15, 15);
        this.slmetric.setBounds(45, 50, 50, 15);
        this.slus.setBounds(45, 70, 50, 15);
        this.alter.requestFocus();
        this.show();
    }
    
    public void insertComponent(final Component component) {
        this.add(component);
        final Dimension dimension = new Dimension(component.getPreferredSize());
        component.reshape(this.insets.left, this.row, dimension.width, dimension.height);
        this.row += this.insets.top + dimension.height;
    }
    
    public void paint(final Graphics graphics) {
        if (this.metrisch) {
            this.slGr.setText(" " + this.groesse + " cm");
            this.slGe.setText(" " + this.gewicht + " kg");
            this.slGre.setText(" " + this.groesse + " cm");
            this.slGee.setText(" " + this.gewicht + " kg");
            this.groessek = this.groesse;
            this.gewichtk = this.gewicht;
        }
        else {
            this.slGe.setText(" " + this.gewicht + " lb");
            this.slGre.setText(" " + this.groesse + " in");
            this.slGee.setText(" " + this.gewicht + " lb");
            this.slGr.setText(" " + this.groesse + " in");
            this.groessek = this.groesse * 10000 / 3937;
            this.gewichtk = this.gewicht * 1000 / 2204;
        }
        graphics.setFont(this.wFont);
        String s;
        if (this.eng) {
            s = new String("calculation of your bodyindex: www.ulrich-schmitz.de ");
        }
        else {
            s = new String("Berechnung Ihres Body Index Wertes: www.ulrich-schmitz.de  ");
        }
        this.wMetrics = this.getFontMetrics(this.wFont);
        final int stringWidth = this.wMetrics.stringWidth(s);
        final int n = (this.aWidth - this.wMetrics.stringWidth(s)) / 2;
        this.aHeight = this.bHeight - this.groessek / 2 - 50;
        graphics.setColor(new Color(this.bC_o));
        graphics.fillRect(n - 10, this.bHeight * 2 - 25, stringWidth + 10, 20);
        graphics.draw3DRect(n - 10, this.bHeight * 2 - 25, stringWidth + 10, 20, false);
        graphics.setColor(new Color(this.bC_z));
        graphics.drawString(s, n, this.bHeight * 2 - 13);
        graphics.setColor(new Color(this.bC));
        graphics.fillOval(300, this.aHeight, this.gewichtk + 50, this.groessek);
        graphics.setColor(new Color(this.bC_k));
        graphics.fillOval(300 + (this.gewichtk / 2 + 25) - 20, this.aHeight - 51, 40, 50);
        graphics.setColor(new Color(this.bC_a));
        graphics.fillOval(300 + (this.gewichtk / 2 + 25) - 20 + 8, this.aHeight - 51 + 9, 10, 10);
        graphics.fillOval(300 + (this.gewichtk / 2 + 25) - 20 + 22, this.aHeight - 51 + 9, 10, 10);
        graphics.setColor(new Color(this.bC_n));
        graphics.fillOval(300 + (this.gewichtk / 2 + 25) - 20 + 19, this.aHeight - 51 + 14, 2, 20);
        graphics.setColor(new Color(this.bC_z));
        graphics.fillOval(300 + (this.gewichtk / 2 + 25) - 20 + 10, this.aHeight - 51 + 40 + 1, 20, 3);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        int n = 0;
        final Object source = adjustmentEvent.getSource();
        final String text = this.alter.getText();
        String text2;
        if (text.charAt(0) >= '1' && text.charAt(0) <= '9' && text.charAt(1) >= '1' && text.charAt(1) <= '9') {
            if (text.charAt(0) == '1') {
                n = 10;
            }
            if (text.charAt(0) == '2') {
                n = 20;
            }
            if (text.charAt(0) == '3') {
                n = 30;
            }
            if (text.charAt(0) == '4') {
                n = 40;
            }
            if (text.charAt(0) == '5') {
                n = 50;
            }
            if (text.charAt(0) == '6') {
                n = 60;
            }
            if (text.charAt(0) == '7') {
                n = 70;
            }
            if (text.charAt(0) == '8') {
                n = 80;
            }
            if (text.charAt(0) == '9') {
                n = 90;
            }
            if (text.charAt(1) == '1') {
                ++n;
            }
            if (text.charAt(1) == '2') {
                n += 2;
            }
            if (text.charAt(1) == '3') {
                n += 3;
            }
            if (text.charAt(1) == '4') {
                n += 4;
            }
            if (text.charAt(1) == '5') {
                n += 5;
            }
            if (text.charAt(1) == '6') {
                n += 6;
            }
            if (text.charAt(1) == '7') {
                n += 7;
            }
            if (text.charAt(1) == '8') {
                n += 8;
            }
            if (text.charAt(1) == '9') {
                n += 9;
            }
            if (text.charAt(1) == '0') {
                n = n;
            }
            if (this.metrisch) {
                if (source == this.sbGroesse) {
                    this.groesse = this.groessei - (this.sbGroesse.getValue() - 1);
                    this.bmi = this.gewicht * 10000 / (this.groesse * this.groesse);
                    this.infoge.setText(" " + this.groesse * this.groesse * 25 / 10000 + " kg");
                }
                else if (source == this.sbGewicht) {
                    this.gewicht = this.gewichti - (this.sbGewicht.getValue() - 1);
                    this.bmi = this.gewicht * 10000 / (this.groesse * this.groesse);
                }
                this.repaint();
            }
            if (!this.metrisch) {
                if (source == this.sbGroesse) {
                    this.groesse = this.groessei - (this.sbGroesse.getValue() - 1);
                    this.bmi = this.gewicht * 7045 / 10 / (this.groesse * this.groesse);
                    this.infoge.setText(" " + this.groesse * this.groesse * 250 / 7045 + " lb");
                }
                else if (source == this.sbGewicht) {
                    this.gewicht = this.gewichti - (this.sbGewicht.getValue() - 1);
                    this.bmi = this.gewicht * 704 / (this.groesse * this.groesse);
                }
                this.repaint();
            }
            text2 = new String("AdvBodyInd by www.ulrich-schmitz.de");
            this.bC = this.bC_o;
            boolean b = false;
            this.setBackground(new Color(this.bXD));
            String text3 = new String(" ");
            if (n <= 18) {
                final String s = new String("In dieser Altersstufe sind Sie noch nicht   ausgewachsen");
            }
            else if (n >= 19 && n <= 24) {
                this.comment.setText("19-24");
                if (this.bmi >= 19 && this.bmi <= 24) {
                    b = true;
                }
            }
            else if (n >= 25 && n <= 34) {
                final String s2 = new String("In Ihrem Alter betraegt der akzeptable \nBMI 20-25\n");
                this.comment.setText("20-25");
                if (this.bmi >= 20 && this.bmi <= 25) {
                    b = true;
                }
            }
            else if (n >= 35 && n <= 44) {
                final String s3 = new String("In Ihrem Alter betraegt der akzeptable \nBMI 21-26\n");
                this.comment.setText("21-26");
                if (this.bmi >= 21 && this.bmi <= 26) {
                    b = true;
                }
            }
            else if (n >= 45 && n <= 54) {
                final String s4 = new String("In Ihrem Alter betraegt der akzeptable \nBMI 22-27\n");
                this.comment.setText("22-27");
                if (this.bmi >= 22 && this.bmi <= 27) {
                    b = true;
                }
            }
            else if (n >= 55 && n <= 65) {
                final String s5 = new String("In Ihrem Alter betraegt der akzeptable \nBMI 23-28\n");
                this.comment.setText("23-28");
                if (this.bmi >= 23 && this.bmi <= 28) {
                    b = true;
                }
            }
            else if (n >= 66) {
                final String s6 = new String("In Ihrem Alter betraegt der akzeptable \n BMI 24-29\n");
                this.comment.setText("24-29");
                if (this.bmi >= 24 && this.bmi <= 29) {
                    b = true;
                }
            }
            this.tMessage.setText("");
            if (!b) {
                if (this.bmi > 40) {
                    if (!this.eng) {
                        text3 = new String("Sie sind stark adipoes \nMit diesem Gewicht sind Sie stark gesundheitsgefaehrdet.\nSuchen Sie Ihren ARZT auf  ");
                    }
                    else {
                        text3 = "You are strongly adipoes\nwith this weight are strongly health-endangered\nVisit your PHYSICIAN ";
                    }
                }
                else if (this.bmi >= 30 && this.bmi <= 40) {
                    if (!this.eng) {
                        text3 = new String("Wahrscheinlich wissen Sie, dass Sie stark uebergewichtig sind. \nSie sollten Ihren ARZT diesbezueglich aufsuchen");
                    }
                    else {
                        text3 = new String("Probably you know that you are strongly over weighty\nYou should visit your PHYSICIAN in this connection");
                    }
                }
                else if (this.bmi >= 26 && this.bmi <= 29) {
                    if (!this.eng) {
                        text3 = new String("Ihr BMI ist relativ hoch.\nIn diesem Bereich ist die Beurteilung des BMI aber\nstark altersabhaengig.\nWerfen Sie doch einen Blick auf dasFenster 'akzeptabler BMI'");
                    }
                    else {
                        text3 = new String("Your BMI is relatively high\nIn this area the evaluation of the BMI is however\nstrongly age-dependent.\nThrow nevertheless a view of the window ' acceptable BMI'");
                    }
                }
                else if (this.bmi >= 25 && this.bmi < 26) {
                    if (!this.eng) {
                        text3 = new String("Ihr BMI ist O.K.\nBeachten Sie aber, dass der akzeptabele BMI altersabhaengig ist.\nWerfen Sie daher einen Blick auf das Feld 'akzeptabler BMI' ");
                    }
                    else {
                        text3 = new String("Your BMI is Ok.\nNote however that the akzeptabele BMI is age-dependent.\nThrow therefore a view of the field ' acceptable BMI '");
                    }
                }
                else if (this.bmi >= 20 && this.bmi <= 24) {
                    if (!this.eng) {
                        text3 = new String("Sie haben einen ziemlich guten Bodymass-Index, es sei denn,\nSie sind noch relativ jung.\nWahrscheinlich treiben Sie viel Sport und leben gesund.\nFalls dies nicht so ist, koennen Sie nur ein Kettenraucher sein  ");
                    }
                    else {
                        text3 = new String("They have a rather good Bodymass index, it are,\nyou are still relatively young\nProbably you drive much sport and live healthy.\nIf this like that is not, you can be only one\nchain smoker.");
                    }
                }
                else if (this.bmi >= 19 && this.bmi < 20) {
                    if (!this.eng) {
                        text3 = new String("Mit einem solchen BMI sollten Sie nicht aelter als 19 Jahre sein.\nBeachten Sie aber, dass es in diesem Alter moeglich ist,\ndass Sie noch nicht ausgewachsen sind| ");
                    }
                    else {
                        text3 = new String("With such BMI you should not be older than 19 years\nNote however that it is possible in this age that\nit not yet attained full growth sind.");
                    }
                }
                else if (this.bmi >= 18 && this.bmi < 19) {
                    if (!this.eng) {
                        text3 = new String("Mit einem solchen BMI sollten Sie nicht aelter als 18 Jahre sein.\nBeachten Sie aber, dass es in diesem Alter sein kann, \ndass Sie noch nicht ausgewachsen sind  ");
                    }
                    else {
                        text3 = new String("With such BMI you should not be older than 18 years\nNote however that it can be in this age that you\nare not yet attained full growth");
                    }
                }
                else if (this.bmi >= 17 && this.bmi < 18) {
                    if (!this.eng) {
                        text3 = new String("Ihr Gewicht ist sehr niedrig\nSuchen Sie einen Arzt auf\nOder sind Sie viel juenger als 18 Jahre? \nIn diesem Fall ist der BMI kein sinnvoller Massstab. ");
                    }
                    else {
                        text3 = new String("Your weight is threateningly low\nsearches you please a physician up\nor is much younger you than 18 years?\nIn this case the BMI is not a meaningful yardstick");
                    }
                }
                else if (this.bmi <= 16) {
                    if (!this.eng) {
                        text3 = new String("Ihr Gewicht ist bedrohlich niedrig \nSuchen Sie bitte einen Arzt auf\nOder sind Sie viel juenger als 18 Jahre? \nIn diesem Fall ist der BMI kein sinnvoller Massstab. ");
                    }
                    else {
                        text3 = new String("Your weight is threateningly low\nsearches you please a physician up\nor is much younger you than 18 years?\nIn this case the BMI is not a meaningful yardstick");
                    }
                }
            }
            else {
                this.setBackground(new Color(this.bC_b));
                if (!this.eng) {
                    text3 = new String("Ihr BMI ist O.K.\nBeachten Sie aber, dass der akzeptabele BMI altersabhaengig ist.\nWerfen Sie daher einen Blick auf das Feld 'akzeptabler BMI' ");
                }
                else {
                    text3 = new String("Your BMI is Ok.\nNote however that the akzeptabele BMI is age-dependent.\nThrow therefore a view of the field ' acceptable BMI '");
                }
            }
            this.status.setText(String.valueOf(this.bmi));
            this.tMessage.setText(text3);
        }
        else {
            if (!this.eng) {
                text2 = new String("Bitte geben Sie Ihr Alter ein");
            }
            else {
                text2 = new String("please enter your age");
            }
            this.tMessage.setText(text2);
            this.showStatus(text2);
        }
        this.showStatus(text2);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.setBackground(new Color(this.bC_b));
            return true;
        }
        if (event.target instanceof CheckboxGroup) {
            this.setBackground(new Color(this.bC_b));
            return true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001) {
            if (event.target == this.cm) {
                this.gewicht = 150;
                this.gewichti = 150;
                this.groessei = 220;
                this.groesse = 220;
                this.sbGewicht.setValues(0, 1, 1, this.gewichti + 1);
                this.sbGroesse.setValues(0, 1, 1, this.groessei + 1);
                this.metrisch = true;
                this.repaint();
                return true;
            }
            if (event.target == this.cu) {
                this.gewicht = 330;
                this.gewichti = 330;
                this.groessei = 87;
                this.groesse = 87;
                this.sbGewicht.setValues(0, 1, 1, this.gewichti + 1);
                this.sbGroesse.setValues(0, 1, 1, this.groessei + 1);
                this.metrisch = false;
                this.repaint();
                return true;
            }
            if (event.target == this.info1) {
                if (!this.eng) {
                    new Msg(this, "der Bodymassindex (BMI)", this.information1());
                }
                else {
                    new Msg(this, "about the Bodyindex (BMI)", this.information2());
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    String information1() {
        return "                                                             \nUm die Spannbreite des Normalgewichtes zu erfassen, stehen   \neinige Indizes zur Verfuegung. Der von Brocca entwickelte    \n'Brocca-Index' diente lange diesem Zweck.  Er berechnet sich \naus der Koerperlaenge in cm abzueglich 100. Die so ermittelte \nZahl entspricht dem Normalgewicht.  Ein Uebergewicht liegt ab \neinem Koerpergewicht von ca. 20% ueber dem                   \nBrocca-Normalgewicht vor. Das  Problem des Brocca-Index ist, \ndass er nicht sehr gut mit der Koerperfettmasse korreliert.  \nBesser tut dies der 'Bodymass-Index', der sich aus dem       \ntatsaechlichen Koerpergewicht, geteilt durch das Quadrat der \nKoerperlaenge in Metern berechnet. Ein 170 cm grosser 70 kg  \nschwerer Mensch hat also einen BMI von                       \n  70 : (1,7 m)2 = 24,2.                                      \nAus der Tabelle oben ist zu ersehen, ab wann ein Uebergewicht \nvorliegt.  Da neben dem Ausmass eines Uebergewichtes auch    \nnoch die Lokalisation des ueberschuessigen Fettes bei der    \nBeurteilung des Gesundheitszustandes eine Rolle spielt, wird \noft auch der Quotient des Taillen- und Hueftumfanges, die    \n'WHR' (Waist to hip-ratio) gemessen. Da Bauchfett das        \nAuftreten kardiovaskulaerer Erkrankungen wesentlich mehr     \nbeguenstigt als Hueftfett, kann durch die Masszahl eine      \nRisikoabschaetzung gemacht werden. Als behandlungsbeduerftig \ngilt dabei ein Quotient Bauch zu Huefte von >1,0 bei Maennern\nund 0,85 bei Frauen                                          \n ";
    }
    
    String information2() {
        return "                                                             \nIn order to determine the possible standard weight,    \nsome indices are available. Of Brocca ' Brocca index '       \ndeveloped served for a long time this purpose. It            \ncalculates itself from the body length in cm less 100.       \nThe in such a way determined number corresponds to the       \nstandard weight. A predominance is present starting from     \na body weight of approx. 20% over the Brocca standard        \nweight. The problem Brocca index is that it does not         \ncorrelate very well with the body fat mass. Better this      \ndoes the ' Bodymass index ', which calculates itself         \nfrom the actual body weight, divided by the square of        \nthe body length in meters. 170 cm of large 70 kg heavy       \nhumans has thus a BMI of 70: (1.7 m)2 = 24,2. From the       \ntable above is to be seen, starting from when a              \npredominance is present. Since apart from the extent         \nof a predominance also still the localization of the         \nsurplus fat plays a role with the evaluation of the          \nstate of health, often also the quotient of the waist        \nand Hueft umfanges, the ' WHR ' (Waist to hip ratio) is       \nmeasured. Since antinode bulge fat favours an occurring      \nof cardiovascular illnesses substantially more than          \nhip fat,a risik determination can be made by the       \nyardstick As in need of treatment thereby a quotient         \nantinode bulge is considered to hip of > 1.0 with men        \nand 0.85 with women.                                         \n                                                             \ntranslation from german to englich by Babelfish ";
    }
    
    public AdvBodyInd() {
        this.eng = false;
        this.metrisch = true;
        this.groessei = 220;
        this.groesse = 220;
        this.gewicht = 150;
        this.gewichti = 150;
        this.sbGewicht = new Scrollbar(1, 0, 1, 1, this.gewichti);
        this.sbGroesse = new Scrollbar(1, 0, 1, 1, this.groessei);
        this.cgLeft = new CheckboxGroup();
        this.cm = new Checkbox("m", this.cgLeft, true);
        this.cu = new Checkbox("u", this.cgLeft, false);
        this.comment = new TextField("         ");
        this.status = new TextField("         ");
        this.infoge = new TextField("         ");
        this.alter = new TextField("         ");
        this.slAlter = new Label("Alter eingeben =>");
        this.slAltere = new Label("enter your age =>");
        this.slGre = new Label("heightsl");
        this.slGr = new Label("Groesse");
        this.slGee = new Label("weight");
        this.slGe = new Label("Gewicht");
        this.slGroessee = new Label("heighte");
        this.slGroesse = new Label("Groesse");
        this.slGewicht = new Label("Gewicht");
        this.slGewichte = new Label("weight");
        this.slmetric = new Label("metric");
        this.slus = new Label("U.S.");
        this.slZ1 = new Label("optimales Gewicht ca.");
        this.slZ1e = new Label("optimal weight");
        this.slZ2 = new Label("Ihr Bodyindex");
        this.slZ2e = new Label("your bodyindex");
        this.slZ3 = new Label("akzeptabler BMI");
        this.slZ3e = new Label("akzeptable BMI");
        this.row = 10;
        this.insets = new Insets(5, 5, 0, 0);
    }
    
    class Msg extends Frame
    {
        public Msg(final AdvBodyInd advBodyInd, final String s, final String s2) {
            this.this$0 = advBodyInd;
            this.this$0 = advBodyInd;
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            advBodyInd.getClass();
            final Msg msg = new Msg(advBodyInd, s);
            ((Container)msg).setLayout(layout);
            final TextArea textArea = new TextArea(s2);
            textArea.resize(500, 900);
            final Button button = new Button("CLOSE - BEENDEN");
            gridBagConstraints.fill = 1;
            gridBagConstraints.anchor = 11;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            layout.setConstraints(textArea, gridBagConstraints);
            ((Container)msg).add(textArea);
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 10;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            layout.setConstraints(button, gridBagConstraints);
            ((Container)msg).add(button);
            ((Window)msg).pack();
            ((Window)msg).show();
        }
        
        public Msg(final AdvBodyInd advBodyInd, final String s) {
            super(s);
            this.this$0 = advBodyInd;
            this.this$0 = advBodyInd;
        }
        
        public boolean action(final Event event, final Object o) {
            this.hide();
            this.dispose();
            return true;
        }
    }
}
