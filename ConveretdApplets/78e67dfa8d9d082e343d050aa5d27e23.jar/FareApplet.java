import java.math.BigDecimal;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.HashMap;
import java.awt.Button;
import java.awt.Label;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class FareApplet extends Applet implements ActionListener, ItemListener
{
    private Choice comboClass;
    private Choice comboEntry;
    private Choice comboExit;
    private Label lblCashFare;
    private Label lblKtagFare;
    private Button btnCalcFare;
    private HashMap cashFareMap;
    private HashMap ktagFareMap;
    private String classSelected;
    private String entrySelected;
    private String exitSelected;
    private String entryPlaza;
    private String exitPlaza;
    private StringBuffer fileName;
    private String hashkey;
    private String[] plazas;
    private static final long serialVersionUID = 1L;
    
    public FareApplet() {
        this.comboClass = new Choice();
        this.comboEntry = new Choice();
        this.comboExit = new Choice();
        this.lblCashFare = new Label();
        this.lblKtagFare = new Label();
        this.btnCalcFare = new Button("Calculate Fare");
        this.cashFareMap = new HashMap();
        this.ktagFareMap = new HashMap();
        this.classSelected = "2";
        this.entrySelected = "004";
        this.exitSelected = "004";
        this.fileName = new StringBuffer();
        this.plazas = new String[] { "004", "019", "033", "039", "042", "045", "050", "053", "057", "071", "076", "092", "127", "147", "177", "182", "197", "202", "204", "212", "236" };
    }
    
    @Override
    public void init() {
        this.setBackground(new Color(255, 249, 236));
        this.comboClass.addItem("2");
        this.comboClass.addItem("3");
        this.comboClass.addItem("4");
        this.comboClass.addItem("5");
        this.comboClass.addItem("6");
        this.comboClass.addItem("7");
        this.comboClass.addItem("8");
        this.comboClass.addItem("9");
        this.comboEntry.addItem("Southern Terminal");
        this.comboEntry.addItem("Wellington: US-160");
        this.comboEntry.addItem("Mulvane: K-53");
        this.comboEntry.addItem("Haysville/Derby: 71st St.");
        this.comboEntry.addItem("Wichita: I-35, I235, 47th Street");
        this.comboEntry.addItem("Wichita: K-15");
        this.comboEntry.addItem("Wichita: US-54/400, Kellogg");
        this.comboEntry.addItem("Wichita: K-96");
        this.comboEntry.addItem("Andover: 21st St.");
        this.comboEntry.addItem("El Dorado: US-254");
        this.comboEntry.addItem("North El Dorado: US-77");
        this.comboEntry.addItem("Cassoday: K-177");
        this.comboEntry.addItem("Emporia: I-35N");
        this.comboEntry.addItem("Council Grove/Osage City: US-56");
        this.comboEntry.addItem("Topeka: I470W, US75, Top. Blvd.");
        this.comboEntry.addItem("Topeka: I-70");
        this.comboEntry.addItem("Lecompton, Lawrence: K-10");
        this.comboEntry.addItem("Lawrence: US-59 South, Iowa St.");
        this.comboEntry.addItem("Lawrence: US-59, US-40");
        this.comboEntry.addItem("Tonganoxie, Eudora: 222nd St.");
        this.comboEntry.addItem("Eastern Terminal");
        this.comboExit.addItem("Southern Terminal");
        this.comboExit.addItem("Wellington: US-160");
        this.comboExit.addItem("Mulvane: K-53");
        this.comboExit.addItem("Haysville/Derby: 71st St.");
        this.comboExit.addItem("Wichita: I-35, I235, 47th Street");
        this.comboExit.addItem("Wichita: K-15");
        this.comboExit.addItem("Wichita: US-54/400, Kellogg");
        this.comboExit.addItem("Wichita: K-96");
        this.comboExit.addItem("Andover: 21st St.");
        this.comboExit.addItem("El Dorado: US-254");
        this.comboExit.addItem("North El Dorado: US-77");
        this.comboExit.addItem("Cassoday: K-177");
        this.comboExit.addItem("Emporia: I-35N");
        this.comboExit.addItem("Council Grove/Osage City: US-56");
        this.comboExit.addItem("Topeka: I470W, US75, Top. Blvd.");
        this.comboExit.addItem("Topeka: I-70");
        this.comboExit.addItem("Lecompton, Lawrence: K-10");
        this.comboExit.addItem("Lawrence: US-59 South, Iowa St.");
        this.comboExit.addItem("Lawrence: US-59, US-40");
        this.comboExit.addItem("Tonganoxie, Eudora: 222nd St.");
        this.comboExit.addItem("Eastern Terminal");
        this.comboClass.addItemListener(this);
        this.comboEntry.addItemListener(this);
        this.comboExit.addItemListener(this);
        this.btnCalcFare.addActionListener(this);
        this.comboClass.select(0);
        this.comboClass.setBackground(Color.white);
        this.comboEntry.select(0);
        this.comboEntry.setBackground(Color.white);
        this.comboExit.select(0);
        this.comboExit.setBackground(Color.white);
        this.lblKtagFare.setVisible(false);
        this.lblCashFare.setVisible(false);
        final Panel panel = new Panel(new GridLayout(6, 1, 1, 2));
        panel.add(new Label("Class:"));
        panel.add(this.comboClass);
        panel.add(new Label("Entry:"));
        panel.add(this.comboEntry);
        panel.add(new Label("Exit:"));
        panel.add(this.comboExit);
        panel.setBackground(new Color(255, 249, 236));
        final Panel panel2 = new Panel(new GridLayout(2, 2, 1, 2));
        panel2.add(new Label("K-TAG fare:"));
        panel2.add(this.lblKtagFare);
        panel2.add(new Label("Cash fare:"));
        panel2.add(this.lblCashFare);
        panel2.setBackground(new Color(255, 249, 236));
        final Panel panel3 = new Panel(new FlowLayout());
        panel3.add(this.btnCalcFare);
        panel3.setBackground(new Color(255, 249, 236));
        this.setLayout(new BorderLayout(5, 5));
        this.add(panel, "North");
        this.add(panel2, "Center");
        this.add(panel3, "South");
        for (int i = 0; i <= this.plazas.length - 1; ++i) {
            this.entryPlaza = this.plazas[i];
            this.fileName.setLength(0);
            this.fileName.append("FareTables/pl".concat(this.entryPlaza).concat("ktag.dat"));
            this.loadHashMap(this.ktagFareMap, this.fileName.toString(), "ktag");
            this.fileName.setLength(0);
            this.fileName.append("FareTables/pl".concat(this.entryPlaza).concat("cash.dat"));
            this.loadHashMap(this.cashFareMap, this.fileName.toString(), "cash");
        }
    }
    
    public void loadHashMap(final HashMap hashMap, final String s, final String s2) {
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
            for (int i = 0; i <= this.plazas.length - 1; ++i) {
                final String line = bufferedReader.readLine();
                this.exitPlaza = line.substring(0, 3);
                int n = 27;
                int n2 = 31;
                for (int j = 2; j <= 9; ++j) {
                    hashMap.put(this.hashkey = s2.concat(this.entryPlaza).concat(this.exitPlaza).concat(String.valueOf(j)), line.substring(n, n2));
                    n += 5;
                    n2 += 5;
                }
            }
            bufferedReader.close();
            resourceAsStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.comboClass) {
            this.classSelected = this.comboClass.getSelectedItem().toString();
        }
        else if (itemEvent.getSource() == this.comboEntry) {
            if (this.comboEntry.getSelectedItem() == "Southern Terminal") {
                this.entrySelected = "004";
            }
            else if (this.comboEntry.getSelectedItem() == "Wellington: US-160") {
                this.entrySelected = "019";
            }
            else if (this.comboEntry.getSelectedItem() == "Mulvane: K-53") {
                this.entrySelected = "033";
            }
            else if (this.comboEntry.getSelectedItem() == "Haysville/Derby: 71st St.") {
                this.entrySelected = "039";
            }
            else if (this.comboEntry.getSelectedItem() == "Wichita: I-35, I235, 47th Street") {
                this.entrySelected = "042";
            }
            else if (this.comboEntry.getSelectedItem() == "Wichita: K-15") {
                this.entrySelected = "045";
            }
            else if (this.comboEntry.getSelectedItem() == "Wichita: US-54/400, Kellogg") {
                this.entrySelected = "050";
            }
            else if (this.comboEntry.getSelectedItem() == "Wichita: K-96") {
                this.entrySelected = "053";
            }
            else if (this.comboEntry.getSelectedItem() == "Andover: 21st St.") {
                this.entrySelected = "057";
            }
            else if (this.comboEntry.getSelectedItem() == "El Dorado: US-254") {
                this.entrySelected = "071";
            }
            else if (this.comboEntry.getSelectedItem() == "North El Dorado: US-77") {
                this.entrySelected = "076";
            }
            else if (this.comboEntry.getSelectedItem() == "Cassoday: K-177") {
                this.entrySelected = "092";
            }
            else if (this.comboEntry.getSelectedItem() == "Emporia: I-35N") {
                this.entrySelected = "127";
            }
            else if (this.comboEntry.getSelectedItem() == "Council Grove/Osage City: US-56") {
                this.entrySelected = "147";
            }
            else if (this.comboEntry.getSelectedItem() == "Topeka: I470W, US75, Top. Blvd.") {
                this.entrySelected = "177";
            }
            else if (this.comboEntry.getSelectedItem() == "Topeka: I-70") {
                this.entrySelected = "182";
            }
            else if (this.comboEntry.getSelectedItem() == "Lecompton, Lawrence: K-10") {
                this.entrySelected = "197";
            }
            else if (this.comboEntry.getSelectedItem() == "Lawrence: US-59 South, Iowa St.") {
                this.entrySelected = "202";
            }
            else if (this.comboEntry.getSelectedItem() == "Lawrence: US-59, US-40") {
                this.entrySelected = "204";
            }
            else if (this.comboEntry.getSelectedItem() == "Tonganoxie, Eudora: 222nd St.") {
                this.entrySelected = "212";
            }
            else if (this.comboEntry.getSelectedItem() == "Eastern Terminal") {
                this.entrySelected = "236";
            }
        }
        else if (itemEvent.getSource() == this.comboExit) {
            if (this.comboExit.getSelectedItem() == "Southern Terminal") {
                this.exitSelected = "004";
            }
            else if (this.comboExit.getSelectedItem() == "Wellington: US-160") {
                this.exitSelected = "019";
            }
            else if (this.comboExit.getSelectedItem() == "Mulvane: K-53") {
                this.exitSelected = "033";
            }
            else if (this.comboExit.getSelectedItem() == "Haysville/Derby: 71st St.") {
                this.exitSelected = "039";
            }
            else if (this.comboExit.getSelectedItem() == "Wichita: I-35, I235, 47th Street") {
                this.exitSelected = "042";
            }
            else if (this.comboExit.getSelectedItem() == "Wichita: K-15") {
                this.exitSelected = "045";
            }
            else if (this.comboExit.getSelectedItem() == "Wichita: US-54/400, Kellogg") {
                this.exitSelected = "050";
            }
            else if (this.comboExit.getSelectedItem() == "Wichita: K-96") {
                this.exitSelected = "053";
            }
            else if (this.comboExit.getSelectedItem() == "Andover: 21st St.") {
                this.exitSelected = "057";
            }
            else if (this.comboExit.getSelectedItem() == "El Dorado: US-254") {
                this.exitSelected = "071";
            }
            else if (this.comboExit.getSelectedItem() == "North El Dorado: US-77") {
                this.exitSelected = "076";
            }
            else if (this.comboExit.getSelectedItem() == "Cassoday: K-177") {
                this.exitSelected = "092";
            }
            else if (this.comboExit.getSelectedItem() == "Emporia: I-35N") {
                this.exitSelected = "127";
            }
            else if (this.comboExit.getSelectedItem() == "Council Grove/Osage City: US-56") {
                this.exitSelected = "147";
            }
            else if (this.comboExit.getSelectedItem() == "Topeka: I470W, US75, Top. Blvd.") {
                this.exitSelected = "177";
            }
            else if (this.comboExit.getSelectedItem() == "Topeka: I-70") {
                this.exitSelected = "182";
            }
            else if (this.comboExit.getSelectedItem() == "Lecompton, Lawrence: K-10") {
                this.exitSelected = "197";
            }
            else if (this.comboExit.getSelectedItem() == "Lawrence: US-59 South, Iowa St.") {
                this.exitSelected = "202";
            }
            else if (this.comboExit.getSelectedItem() == "Lawrence: US-59, US-40") {
                this.exitSelected = "204";
            }
            else if (this.comboExit.getSelectedItem() == "Tonganoxie, Eudora: 222nd St.") {
                this.exitSelected = "212";
            }
            else if (this.comboExit.getSelectedItem() == "Eastern Terminal") {
                this.exitSelected = "236";
            }
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        if (actionEvent.getSource() == this.btnCalcFare) {
            this.hashkey = "ktag".concat(this.entrySelected).concat(this.exitSelected).concat(this.classSelected);
            this.lblKtagFare.setText(currencyInstance.format(new BigDecimal(this.ktagFareMap.get(this.hashkey)).divide(new BigDecimal(100))));
            this.lblKtagFare.setVisible(true);
            this.hashkey = "cash".concat(this.entrySelected).concat(this.exitSelected).concat(this.classSelected);
            this.lblCashFare.setText(currencyInstance.format(new BigDecimal(this.cashFareMap.get(this.hashkey)).divide(new BigDecimal(100))));
            this.lblCashFare.setVisible(true);
        }
    }
}
