// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.test;

import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.awt.TextArea;
import com.magelang.tabsplitter.TabSplitter;
import com.magelang.tabsplitter.TabPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.List;
import java.awt.Label;
import java.awt.Choice;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.Button;
import com.magelang.tabsplitter.TabNamePanel;
import java.applet.Applet;

public class TabSplitterDemo extends Applet
{
    private TabNamePanel ivja;
    private TabNamePanel ivjb;
    private Button ivjButton10;
    private Button ivjButton11;
    private Button ivjButton12;
    private Button ivjButton13;
    private Button ivjButton14;
    private Button ivjButton15;
    private Button ivjButton16;
    private Button ivjButton17;
    private Button ivjButton18;
    private Button ivjButton19;
    private Button ivjButton2;
    private Button ivjButton20;
    private Button ivjButton21;
    private Button ivjButton22;
    private Button ivjButton23;
    private Button ivjButton24;
    private Button ivjButton25;
    private Button ivjButton26;
    private Button ivjButton27;
    private Button ivjButton28;
    private Button ivjButton29;
    private Button ivjButton3;
    private Button ivjButton30;
    private Button ivjButton31;
    private Button ivjButton32;
    private Button ivjButton33;
    private Button ivjButton34;
    private Button ivjButton35;
    private Button ivjButton36;
    private Button ivjButton37;
    private Button ivjButton38;
    private Button ivjButton39;
    private Button ivjButton4;
    private Button ivjButton5;
    private Button ivjButton51;
    private Button ivjButton58;
    private Button ivjButton59;
    private Button ivjButton6;
    private Button ivjButton60;
    private Button ivjButton63;
    private Button ivjButton64;
    private Button ivjButton66;
    private Button ivjButton67;
    private Button ivjButton69;
    private Button ivjButton7;
    private Button ivjButton70;
    private Button ivjButton8;
    private Button ivjButton9;
    private TabNamePanel ivjc;
    private Checkbox ivjCheckbox1;
    private Checkbox ivjCheckbox2;
    private Checkbox ivjCheckbox3;
    private CheckboxGroup ivjCheckboxGroup1;
    private Choice ivjChoice1;
    private TabNamePanel ivjd;
    private TabNamePanel ivje;
    private TabNamePanel ivjf;
    private TabNamePanel ivjg;
    private TabNamePanel ivjh;
    private TabNamePanel ivji;
    private TabNamePanel ivjj;
    private Label ivjLabel1;
    private List ivjList1;
    private Scrollbar ivjScrollbar1;
    private ScrollPane ivjScrollPane1;
    private TabNamePanel ivjTabNamePanel1;
    private TabNamePanel ivjTabNamePanel2;
    private GridLayout ivjTabNamePanel2GridLayout;
    private TabNamePanel ivjTabNamePanel3;
    private BorderLayout ivjTabNamePanel3BorderLayout;
    private TabNamePanel ivjTabNamePanel4;
    private TabNamePanel ivjTabNamePanel5;
    private TabNamePanel ivjTabNamePanel6;
    private TabPanel ivjTabPanel1;
    private TabSplitter ivjTabSplitter1;
    private TextArea ivjTextArea1;
    private TextField ivjTextField1;
    
    public String getAppletInfo() {
        return "com.magelang.test.TabSplitterDemo created using VisualAge for Java.";
    }
    
    private CheckboxGroup getCheckboxGroup1() {
        if (this.ivjCheckboxGroup1 == null) {
            try {
                this.ivjCheckboxGroup1 = new CheckboxGroup();
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjCheckboxGroup1;
    }
    
    private Checkbox getCheckbox3() {
        if (this.ivjCheckbox3 == null) {
            try {
                (this.ivjCheckbox3 = new Checkbox()).setName("Checkbox3");
                this.ivjCheckbox3.setLabel("Checkbox3");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjCheckbox3;
    }
    
    private Button getButton35() {
        if (this.ivjButton35 == null) {
            try {
                (this.ivjButton35 = new Button()).setName("Button35");
                this.ivjButton35.setLabel("w");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton35;
    }
    
    private Button getButton15() {
        if (this.ivjButton15 == null) {
            try {
                (this.ivjButton15 = new Button()).setName("Button15");
                this.ivjButton15.setLabel("k");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton15;
    }
    
    private Button getButton25() {
        if (this.ivjButton25 == null) {
            try {
                (this.ivjButton25 = new Button()).setName("Button25");
                this.ivjButton25.setLabel("a");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton25;
    }
    
    private Checkbox getCheckbox2() {
        if (this.ivjCheckbox2 == null) {
            try {
                (this.ivjCheckbox2 = new Checkbox()).setName("Checkbox2");
                this.ivjCheckbox2.setLabel("Checkbox2");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjCheckbox2;
    }
    
    private Checkbox getCheckbox1() {
        if (this.ivjCheckbox1 == null) {
            try {
                (this.ivjCheckbox1 = new Checkbox()).setName("Checkbox1");
                this.ivjCheckbox1.setLabel("Checkbox1");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjCheckbox1;
    }
    
    private Choice getChoice1() {
        if (this.ivjChoice1 == null) {
            try {
                (this.ivjChoice1 = new Choice()).setName("Choice1");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjChoice1;
    }
    
    private Button getButton32() {
        if (this.ivjButton32 == null) {
            try {
                (this.ivjButton32 = new Button()).setName("Button32");
                this.ivjButton32.setLabel("A silly example");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton32;
    }
    
    private Button getButton12() {
        if (this.ivjButton12 == null) {
            try {
                (this.ivjButton12 = new Button()).setName("Button12");
                this.ivjButton12.setLabel("j");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton12;
    }
    
    private Button getButton22() {
        if (this.ivjButton22 == null) {
            try {
                (this.ivjButton22 = new Button()).setName("Button22");
                this.ivjButton22.setLabel("d");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton22;
    }
    
    private void initConnections() {
        this.conn0SetTarget();
        this.conn1SetTarget();
    }
    
    private TextArea getTextArea1() {
        if (this.ivjTextArea1 == null) {
            try {
                (this.ivjTextArea1 = new TextArea()).setName("TextArea1");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTextArea1;
    }
    
    private List getList1() {
        if (this.ivjList1 == null) {
            try {
                (this.ivjList1 = new List()).setName("List1");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjList1;
    }
    
    private Button getButton36() {
        if (this.ivjButton36 == null) {
            try {
                (this.ivjButton36 = new Button()).setName("Button36");
                this.ivjButton36.setLabel("q");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton36;
    }
    
    private Button getButton66() {
        if (this.ivjButton66 == null) {
            try {
                (this.ivjButton66 = new Button()).setName("Button66");
                this.ivjButton66.setBounds(174, 3, 125, 30);
                this.ivjButton66.setLabel("Button66");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton66;
    }
    
    public void init() {
        super.init();
        try {
            this.setName("TabSplitterDemo");
            this.setLayout(new BorderLayout());
            this.setSize(440, 250);
            this.add("Center", this.getTabSplitter1());
            this.initConnections();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private Button getButton16() {
        if (this.ivjButton16 == null) {
            try {
                (this.ivjButton16 = new Button()).setName("Button16");
                this.ivjButton16.setLabel("j");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton16;
    }
    
    private Button getButton26() {
        if (this.ivjButton26 == null) {
            try {
                (this.ivjButton26 = new Button()).setName("Button26");
                this.ivjButton26.setLabel("GridLayout");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton26;
    }
    
    private Button getButton31() {
        if (this.ivjButton31 == null) {
            try {
                (this.ivjButton31 = new Button()).setName("Button31");
                this.ivjButton31.setLabel("East");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton31;
    }
    
    private Button getButton11() {
        if (this.ivjButton11 == null) {
            try {
                (this.ivjButton11 = new Button()).setName("Button11");
                this.ivjButton11.setLabel("i");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton11;
    }
    
    private TextField getTextField1() {
        if (this.ivjTextField1 == null) {
            try {
                (this.ivjTextField1 = new TextField()).setName("TextField1");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTextField1;
    }
    
    private Button getButton21() {
        if (this.ivjButton21 == null) {
            try {
                (this.ivjButton21 = new Button()).setName("Button21");
                this.ivjButton21.setLabel("e");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton21;
    }
    
    private Button getButton51() {
        if (this.ivjButton51 == null) {
            try {
                (this.ivjButton51 = new Button()).setName("Button51");
                this.ivjButton51.setLabel("b");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton51;
    }
    
    private Button getButton58() {
        if (this.ivjButton58 == null) {
            try {
                (this.ivjButton58 = new Button()).setName("Button58");
                this.ivjButton58.setBounds(195, 132, 125, 30);
                this.ivjButton58.setLabel("Button58");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton58;
    }
    
    private Button getButton34() {
        if (this.ivjButton34 == null) {
            try {
                (this.ivjButton34 = new Button()).setName("Button34");
                this.ivjButton34.setLabel("qqqqqq");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton34;
    }
    
    private Button getButton64() {
        if (this.ivjButton64 == null) {
            try {
                (this.ivjButton64 = new Button()).setName("Button64");
                this.ivjButton64.setBounds(104, 21, 125, 30);
                this.ivjButton64.setLabel("Button64");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton64;
    }
    
    private Button getButton38() {
        if (this.ivjButton38 == null) {
            try {
                (this.ivjButton38 = new Button()).setName("Button38");
                this.ivjButton38.setLabel("fooble");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton38;
    }
    
    private Button getButton14() {
        if (this.ivjButton14 == null) {
            try {
                (this.ivjButton14 = new Button()).setName("Button14");
                this.ivjButton14.setLabel("l");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton14;
    }
    
    private Button getButton37() {
        if (this.ivjButton37 == null) {
            try {
                (this.ivjButton37 = new Button()).setName("Button37");
                this.ivjButton37.setLabel("y");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton37;
    }
    
    private Button getButton67() {
        if (this.ivjButton67 == null) {
            try {
                (this.ivjButton67 = new Button()).setName("Button67");
                this.ivjButton67.setBounds(231, 18, 125, 30);
                this.ivjButton67.setLabel("Button67");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton67;
    }
    
    private Button getButton24() {
        if (this.ivjButton24 == null) {
            try {
                (this.ivjButton24 = new Button()).setName("Button24");
                this.ivjButton24.setLabel("b");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton24;
    }
    
    private TabPanel getTabPanel1() {
        if (this.ivjTabPanel1 == null) {
            try {
                (this.ivjTabPanel1 = new TabPanel()).setName("TabPanel1");
                this.ivjTabPanel1.setTabColors(new Color[] { Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta });
                this.ivjTabPanel1.setFont(new Font("serif", 2, 24));
                this.ivjTabPanel1.add(this.geta(), this.geta().getName());
                this.ivjTabPanel1.add(this.getb(), this.getb().getName());
                this.ivjTabPanel1.add(this.getc(), this.getc().getName());
                this.ivjTabPanel1.add(this.getd(), this.getd().getName());
                this.ivjTabPanel1.add(this.gete(), this.gete().getName());
                this.ivjTabPanel1.add(this.getf(), this.getf().getName());
                this.ivjTabPanel1.add(this.getg(), this.getg().getName());
                this.ivjTabPanel1.add(this.geth(), this.geth().getName());
                this.ivjTabPanel1.add(this.geti(), this.geti().getName());
                this.ivjTabPanel1.add(this.getj(), this.getj().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTabPanel1;
    }
    
    private Button getButton18() {
        if (this.ivjButton18 == null) {
            try {
                (this.ivjButton18 = new Button()).setName("Button18");
                this.ivjButton18.setLabel("h");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton18;
    }
    
    private Button getButton17() {
        if (this.ivjButton17 == null) {
            try {
                (this.ivjButton17 = new Button()).setName("Button17");
                this.ivjButton17.setLabel("i");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton17;
    }
    
    private Button getButton33() {
        if (this.ivjButton33 == null) {
            try {
                (this.ivjButton33 = new Button()).setName("Button33");
                this.ivjButton33.setLabel("Crunchy");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton33;
    }
    
    private Button getButton63() {
        if (this.ivjButton63 == null) {
            try {
                (this.ivjButton63 = new Button()).setName("Button63");
                this.ivjButton63.setBounds(59, 34, 125, 30);
                this.ivjButton63.setLabel("Button63");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton63;
    }
    
    private Button getButton13() {
        if (this.ivjButton13 == null) {
            try {
                (this.ivjButton13 = new Button()).setName("Button13");
                this.ivjButton13.setLabel("k");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton13;
    }
    
    private Button getButton23() {
        if (this.ivjButton23 == null) {
            try {
                (this.ivjButton23 = new Button()).setName("Button23");
                this.ivjButton23.setLabel("c");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton23;
    }
    
    private TabNamePanel getTabNamePanel4() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
        if (this.ivjTabNamePanel4 == null) {
            try {
                (this.ivjTabNamePanel4 = new TabNamePanel()).setName("TabNamePanel4");
                this.ivjTabNamePanel4.setLayout(new GridBagLayout());
                this.ivjTabNamePanel4.setTabName("Big Bad GridBagLayout");
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 0;
                gridBagConstraints.gridwidth = 1;
                gridBagConstraints.gridheight = 4;
                gridBagConstraints.fill = 1;
                gridBagConstraints.anchor = 10;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 0.0;
                ((GridBagLayout)this.getTabNamePanel4().getLayout()).setConstraints(this.getButton32(), gridBagConstraints);
                this.getTabNamePanel4().add(this.getButton32());
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 0;
                gridBagConstraints2.gridwidth = 1;
                gridBagConstraints2.gridheight = 1;
                gridBagConstraints2.fill = 3;
                gridBagConstraints2.anchor = 10;
                gridBagConstraints2.weightx = 1.0;
                gridBagConstraints2.weighty = 1.0;
                ((GridBagLayout)this.getTabNamePanel4().getLayout()).setConstraints(this.getButton33(), gridBagConstraints2);
                this.getTabNamePanel4().add(this.getButton33());
                gridBagConstraints3.gridx = 2;
                gridBagConstraints3.gridy = 0;
                gridBagConstraints3.gridwidth = 1;
                gridBagConstraints3.gridheight = 1;
                gridBagConstraints3.anchor = 10;
                gridBagConstraints3.weightx = 0.0;
                gridBagConstraints3.weighty = 0.0;
                ((GridBagLayout)this.getTabNamePanel4().getLayout()).setConstraints(this.getButton34(), gridBagConstraints3);
                this.getTabNamePanel4().add(this.getButton34());
                gridBagConstraints4.gridx = 1;
                gridBagConstraints4.gridy = 1;
                gridBagConstraints4.gridwidth = 1;
                gridBagConstraints4.gridheight = 2;
                gridBagConstraints4.fill = 1;
                gridBagConstraints4.anchor = 10;
                gridBagConstraints4.weightx = 0.0;
                gridBagConstraints4.weighty = 0.0;
                ((GridBagLayout)this.getTabNamePanel4().getLayout()).setConstraints(this.getButton35(), gridBagConstraints4);
                this.getTabNamePanel4().add(this.getButton35());
                gridBagConstraints5.gridx = 2;
                gridBagConstraints5.gridy = 2;
                gridBagConstraints5.gridwidth = 2;
                gridBagConstraints5.gridheight = 1;
                gridBagConstraints5.anchor = 10;
                gridBagConstraints5.weightx = 1.0;
                gridBagConstraints5.weighty = 0.0;
                ((GridBagLayout)this.getTabNamePanel4().getLayout()).setConstraints(this.getButton36(), gridBagConstraints5);
                this.getTabNamePanel4().add(this.getButton36());
                gridBagConstraints6.gridx = 2;
                gridBagConstraints6.gridy = 1;
                gridBagConstraints6.gridwidth = 1;
                gridBagConstraints6.gridheight = 1;
                gridBagConstraints6.anchor = 10;
                gridBagConstraints6.weightx = 0.0;
                gridBagConstraints6.weighty = 0.0;
                ((GridBagLayout)this.getTabNamePanel4().getLayout()).setConstraints(this.getButton37(), gridBagConstraints6);
                this.getTabNamePanel4().add(this.getButton37());
                gridBagConstraints7.gridx = 1;
                gridBagConstraints7.gridy = 3;
                gridBagConstraints7.gridwidth = 3;
                gridBagConstraints7.gridheight = 1;
                gridBagConstraints7.fill = 1;
                gridBagConstraints7.anchor = 10;
                gridBagConstraints7.weightx = 0.0;
                gridBagConstraints7.weighty = 0.0;
                ((GridBagLayout)this.getTabNamePanel4().getLayout()).setConstraints(this.getButton39(), gridBagConstraints7);
                this.getTabNamePanel4().add(this.getButton39());
                gridBagConstraints8.gridx = 4;
                gridBagConstraints8.gridy = 0;
                gridBagConstraints8.gridwidth = 1;
                gridBagConstraints8.gridheight = 3;
                gridBagConstraints8.fill = 2;
                gridBagConstraints8.anchor = 10;
                gridBagConstraints8.weightx = 1.0;
                gridBagConstraints8.weighty = 0.0;
                ((GridBagLayout)this.getTabNamePanel4().getLayout()).setConstraints(this.getButton38(), gridBagConstraints8);
                this.getTabNamePanel4().add(this.getButton38());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTabNamePanel4;
    }
    
    private TabNamePanel getTabNamePanel5() {
        if (this.ivjTabNamePanel5 == null) {
            try {
                (this.ivjTabNamePanel5 = new TabNamePanel()).setName("TabNamePanel5");
                this.ivjTabNamePanel5.setLayout(null);
                this.ivjTabNamePanel5.setTabName("Messy!!!  No layout!");
                this.ivjTabNamePanel5.add(this.getButton58(), this.getButton58().getName());
                this.ivjTabNamePanel5.add(this.getButton59(), this.getButton59().getName());
                this.ivjTabNamePanel5.add(this.getButton60(), this.getButton60().getName());
                this.ivjTabNamePanel5.add(this.getButton63(), this.getButton63().getName());
                this.ivjTabNamePanel5.add(this.getButton64(), this.getButton64().getName());
                this.ivjTabNamePanel5.add(this.getButton66(), this.getButton66().getName());
                this.ivjTabNamePanel5.add(this.getButton67(), this.getButton67().getName());
                this.ivjTabNamePanel5.add(this.getButton69(), this.getButton69().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTabNamePanel5;
    }
    
    private TabNamePanel getTabNamePanel3() {
        if (this.ivjTabNamePanel3 == null) {
            try {
                (this.ivjTabNamePanel3 = new TabNamePanel()).setName("TabNamePanel3");
                this.ivjTabNamePanel3.setLayout(this.getTabNamePanel3BorderLayout());
                this.ivjTabNamePanel3.setTabName("A BorderLayout");
                this.getTabNamePanel3().add("North", this.getButton27());
                this.getTabNamePanel3().add("South", this.getButton28());
                this.getTabNamePanel3().add("West", this.getButton29());
                this.getTabNamePanel3().add("Center", this.getButton30());
                this.getTabNamePanel3().add("East", this.getButton31());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTabNamePanel3;
    }
    
    private Button getButton70() {
        if (this.ivjButton70 == null) {
            try {
                (this.ivjButton70 = new Button()).setName("Button70");
                this.ivjButton70.setLabel("Button70");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton70;
    }
    
    private Button getButton30() {
        if (this.ivjButton30 == null) {
            try {
                (this.ivjButton30 = new Button()).setName("Button30");
                this.ivjButton30.setLabel("Center");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton30;
    }
    
    private TabNamePanel getTabNamePanel2() {
        if (this.ivjTabNamePanel2 == null) {
            try {
                (this.ivjTabNamePanel2 = new TabNamePanel()).setName("TabNamePanel2");
                this.ivjTabNamePanel2.setLayout(this.getTabNamePanel2GridLayout());
                this.ivjTabNamePanel2.setTabName("A GridLayout");
                this.ivjTabNamePanel2.add(this.getButton26(), this.getButton26().getName());
                this.ivjTabNamePanel2.add(this.getButton25(), this.getButton25().getName());
                this.ivjTabNamePanel2.add(this.getButton24(), this.getButton24().getName());
                this.ivjTabNamePanel2.add(this.getButton23(), this.getButton23().getName());
                this.ivjTabNamePanel2.add(this.getButton22(), this.getButton22().getName());
                this.ivjTabNamePanel2.add(this.getButton21(), this.getButton21().getName());
                this.ivjTabNamePanel2.add(this.getButton20(), this.getButton20().getName());
                this.ivjTabNamePanel2.add(this.getButton19(), this.getButton19().getName());
                this.ivjTabNamePanel2.add(this.getButton18(), this.getButton18().getName());
                this.ivjTabNamePanel2.add(this.getButton17(), this.getButton17().getName());
                this.ivjTabNamePanel2.add(this.getButton16(), this.getButton16().getName());
                this.ivjTabNamePanel2.add(this.getButton15(), this.getButton15().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTabNamePanel2;
    }
    
    private Button getButton60() {
        if (this.ivjButton60 == null) {
            try {
                (this.ivjButton60 = new Button()).setName("Button60");
                this.ivjButton60.setBounds(76, 121, 125, 30);
                this.ivjButton60.setLabel("Button60");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton60;
    }
    
    private Button getButton4() {
        if (this.ivjButton4 == null) {
            try {
                (this.ivjButton4 = new Button()).setName("Button4");
                this.ivjButton4.setLabel("e");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton4;
    }
    
    private Button getButton5() {
        if (this.ivjButton5 == null) {
            try {
                (this.ivjButton5 = new Button()).setName("Button5");
                this.ivjButton5.setLabel("a");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton5;
    }
    
    private Button getButton8() {
        if (this.ivjButton8 == null) {
            try {
                (this.ivjButton8 = new Button()).setName("Button8");
                this.ivjButton8.setLabel("This is a FlowLayout!");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton8;
    }
    
    private Button getButton9() {
        if (this.ivjButton9 == null) {
            try {
                (this.ivjButton9 = new Button()).setName("Button9");
                this.ivjButton9.setLabel("g");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton9;
    }
    
    private Button getButton6() {
        if (this.ivjButton6 == null) {
            try {
                (this.ivjButton6 = new Button()).setName("Button6");
                this.ivjButton6.setLabel("d");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton6;
    }
    
    private void conn0SetTarget() {
        try {
            this.getCheckbox2().setCheckboxGroup(this.getCheckboxGroup1());
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private TabNamePanel getTabNamePanel6() {
        if (this.ivjTabNamePanel6 == null) {
            try {
                (this.ivjTabNamePanel6 = new TabNamePanel()).setName("TabNamePanel6");
                this.ivjTabNamePanel6.setLayout(new GridLayout());
                this.ivjTabNamePanel6.setTabName("A nested tab panel, even");
                this.ivjTabNamePanel6.add(this.getTabPanel1(), this.getTabPanel1().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTabNamePanel6;
    }
    
    private TabNamePanel getTabNamePanel1() {
        if (this.ivjTabNamePanel1 == null) {
            try {
                (this.ivjTabNamePanel1 = new TabNamePanel()).setName("TabNamePanel1");
                this.ivjTabNamePanel1.setTabName("A FlowLayout");
                this.ivjTabNamePanel1.add(this.getButton8(), this.getButton8().getName());
                this.ivjTabNamePanel1.add(this.getButton5(), this.getButton5().getName());
                this.ivjTabNamePanel1.add(this.getButton51(), this.getButton51().getName());
                this.ivjTabNamePanel1.add(this.getButton7(), this.getButton7().getName());
                this.ivjTabNamePanel1.add(this.getButton6(), this.getButton6().getName());
                this.ivjTabNamePanel1.add(this.getButton4(), this.getButton4().getName());
                this.ivjTabNamePanel1.add(this.getButton3(), this.getButton3().getName());
                this.ivjTabNamePanel1.add(this.getButton9(), this.getButton9().getName());
                this.ivjTabNamePanel1.add(this.getButton10(), this.getButton10().getName());
                this.ivjTabNamePanel1.add(this.getButton11(), this.getButton11().getName());
                this.ivjTabNamePanel1.add(this.getButton12(), this.getButton12().getName());
                this.ivjTabNamePanel1.add(this.getButton13(), this.getButton13().getName());
                this.ivjTabNamePanel1.add(this.getButton14(), this.getButton14().getName());
                this.ivjTabNamePanel1.add(this.getButton2(), this.getButton2().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTabNamePanel1;
    }
    
    private Button getButton2() {
        if (this.ivjButton2 == null) {
            try {
                (this.ivjButton2 = new Button()).setName("Button2");
                this.ivjButton2.setLabel("m");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton2;
    }
    
    private ScrollPane getScrollPane1() {
        if (this.ivjScrollPane1 == null) {
            try {
                (this.ivjScrollPane1 = new ScrollPane()).setName("ScrollPane1");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjScrollPane1;
    }
    
    private Label getLabel1() {
        if (this.ivjLabel1 == null) {
            try {
                (this.ivjLabel1 = new Label()).setName("Label1");
                this.ivjLabel1.setText("Label1");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabel1;
    }
    
    public TabSplitterDemo() {
        this.ivja = null;
        this.ivjb = null;
        this.ivjButton10 = null;
        this.ivjButton11 = null;
        this.ivjButton12 = null;
        this.ivjButton13 = null;
        this.ivjButton14 = null;
        this.ivjButton15 = null;
        this.ivjButton16 = null;
        this.ivjButton17 = null;
        this.ivjButton18 = null;
        this.ivjButton19 = null;
        this.ivjButton2 = null;
        this.ivjButton20 = null;
        this.ivjButton21 = null;
        this.ivjButton22 = null;
        this.ivjButton23 = null;
        this.ivjButton24 = null;
        this.ivjButton25 = null;
        this.ivjButton26 = null;
        this.ivjButton27 = null;
        this.ivjButton28 = null;
        this.ivjButton29 = null;
        this.ivjButton3 = null;
        this.ivjButton30 = null;
        this.ivjButton31 = null;
        this.ivjButton32 = null;
        this.ivjButton33 = null;
        this.ivjButton34 = null;
        this.ivjButton35 = null;
        this.ivjButton36 = null;
        this.ivjButton37 = null;
        this.ivjButton38 = null;
        this.ivjButton39 = null;
        this.ivjButton4 = null;
        this.ivjButton5 = null;
        this.ivjButton51 = null;
        this.ivjButton58 = null;
        this.ivjButton59 = null;
        this.ivjButton6 = null;
        this.ivjButton60 = null;
        this.ivjButton63 = null;
        this.ivjButton64 = null;
        this.ivjButton66 = null;
        this.ivjButton67 = null;
        this.ivjButton69 = null;
        this.ivjButton7 = null;
        this.ivjButton70 = null;
        this.ivjButton8 = null;
        this.ivjButton9 = null;
        this.ivjc = null;
        this.ivjCheckbox1 = null;
        this.ivjCheckbox2 = null;
        this.ivjCheckbox3 = null;
        this.ivjCheckboxGroup1 = null;
        this.ivjChoice1 = null;
        this.ivjd = null;
        this.ivje = null;
        this.ivjf = null;
        this.ivjg = null;
        this.ivjh = null;
        this.ivji = null;
        this.ivjj = null;
        this.ivjLabel1 = null;
        this.ivjList1 = null;
        this.ivjScrollbar1 = null;
        this.ivjScrollPane1 = null;
        this.ivjTabNamePanel1 = null;
        this.ivjTabNamePanel2 = null;
        this.ivjTabNamePanel2GridLayout = null;
        this.ivjTabNamePanel3 = null;
        this.ivjTabNamePanel3BorderLayout = null;
        this.ivjTabNamePanel4 = null;
        this.ivjTabNamePanel5 = null;
        this.ivjTabNamePanel6 = null;
        this.ivjTabPanel1 = null;
        this.ivjTabSplitter1 = null;
        this.ivjTextArea1 = null;
        this.ivjTextField1 = null;
    }
    
    private Button getButton10() {
        if (this.ivjButton10 == null) {
            try {
                (this.ivjButton10 = new Button()).setName("Button10");
                this.ivjButton10.setLabel("h");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton10;
    }
    
    private Button getButton28() {
        if (this.ivjButton28 == null) {
            try {
                (this.ivjButton28 = new Button()).setName("Button28");
                this.ivjButton28.setLabel("South");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton28;
    }
    
    private Button getButton29() {
        if (this.ivjButton29 == null) {
            try {
                (this.ivjButton29 = new Button()).setName("Button29");
                this.ivjButton29.setLabel("West");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton29;
    }
    
    private Button getButton59() {
        if (this.ivjButton59 == null) {
            try {
                (this.ivjButton59 = new Button()).setName("Button59");
                this.ivjButton59.setBounds(134, 125, 125, 30);
                this.ivjButton59.setLabel("Button59");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton59;
    }
    
    private TabNamePanel getj() {
        if (this.ivjj == null) {
            try {
                (this.ivjj = new TabNamePanel()).setName("j");
                this.ivjj.setLayout(new GridLayout());
                this.ivjj.setTabName("j");
                this.ivjj.add(this.getButton70(), this.getButton70().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjj;
    }
    
    private TabNamePanel getf() {
        if (this.ivjf == null) {
            try {
                (this.ivjf = new TabNamePanel()).setName("f");
                this.ivjf.setLayout(new GridLayout());
                this.ivjf.setTabName("f");
                this.ivjf.add(this.getList1(), this.getList1().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjf;
    }
    
    private TabNamePanel gete() {
        if (this.ivje == null) {
            try {
                (this.ivje = new TabNamePanel()).setName("e");
                this.ivje.setLayout(new GridLayout());
                this.ivje.setTabName("e");
                this.ivje.add(this.getTextArea1(), this.getTextArea1().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivje;
    }
    
    public static void main(final String[] array) {
        try {
            Frame frame;
            try {
                frame = (Frame)Class.forName("uvm.abt.edit.TestFrame").newInstance();
            }
            catch (Throwable t) {
                frame = new Frame();
            }
            final TabSplitterDemo tabSplitterDemo = new TabSplitterDemo();
            frame.add("Center", tabSplitterDemo);
            frame.setSize(tabSplitterDemo.getSize());
            tabSplitterDemo.init();
            tabSplitterDemo.start();
            frame.setVisible(true);
            tabSplitterDemo.destroy();
        }
        catch (Throwable t2) {
            System.err.println("Exception occurred in main() of java.applet.Applet");
        }
    }
    
    private TabNamePanel geta() {
        if (this.ivja == null) {
            try {
                (this.ivja = new TabNamePanel()).setName("a");
                this.ivja.setLayout(new GridLayout());
                this.ivja.setTabName("a");
                this.ivja.add(this.getCheckbox1(), this.getCheckbox1().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivja;
    }
    
    private TabNamePanel getg() {
        if (this.ivjg == null) {
            try {
                (this.ivjg = new TabNamePanel()).setName("g");
                this.ivjg.setLayout(new GridLayout());
                this.ivjg.setTabName("g");
                this.ivjg.add(this.getChoice1(), this.getChoice1().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjg;
    }
    
    private TabNamePanel geti() {
        if (this.ivji == null) {
            try {
                (this.ivji = new TabNamePanel()).setName("i");
                this.ivji.setLayout(new GridLayout());
                this.ivji.setTabName("i");
                this.ivji.add(this.getScrollPane1(), this.getScrollPane1().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivji;
    }
    
    private TabNamePanel getc() {
        if (this.ivjc == null) {
            try {
                (this.ivjc = new TabNamePanel()).setName("c");
                this.ivjc.setLayout(new GridLayout());
                this.ivjc.setTabName("c");
                this.ivjc.add(this.getTextField1(), this.getTextField1().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjc;
    }
    
    private Button getButton39() {
        if (this.ivjButton39 == null) {
            try {
                (this.ivjButton39 = new Button()).setName("Button39");
                this.ivjButton39.setLabel("1");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton39;
    }
    
    private BorderLayout getTabNamePanel3BorderLayout() {
        BorderLayout borderLayout = null;
        try {
            borderLayout = new BorderLayout();
            borderLayout.setVgap(10);
            borderLayout.setHgap(10);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
        return borderLayout;
    }
    
    private Button getButton69() {
        if (this.ivjButton69 == null) {
            try {
                (this.ivjButton69 = new Button()).setName("Button69");
                this.ivjButton69.setBounds(281, 69, 125, 30);
                this.ivjButton69.setLabel("Button69");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton69;
    }
    
    private TabNamePanel geth() {
        if (this.ivjh == null) {
            try {
                (this.ivjh = new TabNamePanel()).setName("h");
                this.ivjh.setLayout(new GridLayout());
                this.ivjh.setTabName("h");
                this.ivjh.add(this.getCheckbox2(), this.getCheckbox2().getName());
                this.ivjh.add(this.getCheckbox3(), this.getCheckbox3().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjh;
    }
    
    private TabNamePanel getd() {
        if (this.ivjd == null) {
            try {
                (this.ivjd = new TabNamePanel()).setName("d");
                this.ivjd.setLayout(new GridLayout());
                this.ivjd.setTabName("d");
                this.ivjd.add(this.getLabel1(), this.getLabel1().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjd;
    }
    
    private void conn1SetTarget() {
        try {
            this.getCheckbox3().setCheckboxGroup(this.getCheckboxGroup1());
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private Button getButton27() {
        if (this.ivjButton27 == null) {
            try {
                (this.ivjButton27 = new Button()).setName("Button27");
                this.ivjButton27.setLabel("North");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton27;
    }
    
    private Button getButton3() {
        if (this.ivjButton3 == null) {
            try {
                (this.ivjButton3 = new Button()).setName("Button3");
                this.ivjButton3.setLabel("f");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton3;
    }
    
    private Button getButton7() {
        if (this.ivjButton7 == null) {
            try {
                (this.ivjButton7 = new Button()).setName("Button7");
                this.ivjButton7.setLabel("c");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton7;
    }
    
    private TabNamePanel getb() {
        if (this.ivjb == null) {
            try {
                (this.ivjb = new TabNamePanel()).setName("b");
                this.ivjb.setLayout(new GridLayout());
                this.ivjb.setTabName("b");
                this.ivjb.add(this.getScrollbar1(), this.getScrollbar1().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjb;
    }
    
    private Button getButton20() {
        if (this.ivjButton20 == null) {
            try {
                (this.ivjButton20 = new Button()).setName("Button20");
                this.ivjButton20.setLabel("f");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton20;
    }
    
    private Scrollbar getScrollbar1() {
        if (this.ivjScrollbar1 == null) {
            try {
                (this.ivjScrollbar1 = new Scrollbar()).setName("Scrollbar1");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjScrollbar1;
    }
    
    private void handleException(final Throwable t) {
        System.out.println("--------- UNCAUGHT EXCEPTION ---------");
        t.printStackTrace(System.out);
    }
    
    private Button getButton19() {
        if (this.ivjButton19 == null) {
            try {
                (this.ivjButton19 = new Button()).setName("Button19");
                this.ivjButton19.setLabel("g");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButton19;
    }
    
    private GridLayout getTabNamePanel2GridLayout() {
        GridLayout gridLayout = null;
        try {
            gridLayout = new GridLayout();
            gridLayout.setRows(3);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
        return gridLayout;
    }
    
    private TabSplitter getTabSplitter1() {
        if (this.ivjTabSplitter1 == null) {
            try {
                (this.ivjTabSplitter1 = new TabSplitter()).setName("TabSplitter1");
                this.ivjTabSplitter1.add(this.getTabNamePanel1(), this.getTabNamePanel1().getName());
                this.ivjTabSplitter1.add(this.getTabNamePanel2(), this.getTabNamePanel2().getName());
                this.ivjTabSplitter1.add(this.getTabNamePanel3(), this.getTabNamePanel3().getName());
                this.ivjTabSplitter1.add(this.getTabNamePanel4(), this.getTabNamePanel4().getName());
                this.ivjTabSplitter1.add(this.getTabNamePanel5(), this.getTabNamePanel5().getName());
                this.ivjTabSplitter1.add(this.getTabNamePanel6(), this.getTabNamePanel6().getName());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTabSplitter1;
    }
}
