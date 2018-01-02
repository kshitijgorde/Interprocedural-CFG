// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.powcost;

import com.mindprod.common15.FontFactory;
import com.mindprod.common11.Build;
import com.mindprod.common13.HybridJ;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.awt.Component;
import com.mindprod.common11.Misc;
import com.mindprod.common13.CMPAboutJBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import com.mindprod.common15.Laf;
import javax.swing.JMenuBar;
import java.awt.Insets;
import java.util.Collection;
import java.util.Vector;
import java.util.Arrays;
import javax.swing.Icon;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.zip.GZIPInputStream;
import com.mindprod.common11.VersionCheck;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Container;
import com.mindprod.currcon.Exch;
import java.util.HashMap;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.text.DecimalFormat;
import java.awt.Color;
import javax.swing.JApplet;

public final class PowCost extends JApplet
{
    private static final boolean DEBUGGING = false;
    private static final int APPLET_HEIGHT = 395;
    private static final int APPLET_WIDTH = 768;
    private static final int EXPECTED_RESOURCE_VERSION = 2;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2010-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String INITIAL_ACTIVITY = "run a 1 kw appliance for an hour (i.e. 1 kwh)";
    private static final String INITIAL_LOCATION = "ca BC British Columbia";
    private static final String RELEASE_DATE = "2011-05-19";
    private static final String TITLE_STRING = "Electric Power Cost Calculator";
    private static final String VERSION_STRING = "1.3";
    private static final Color BACKGROUND_FOR_BODY;
    private static final Color BACKGROUND_FOR_EDITABLE;
    private static final Color BACKGROUND_FOR_INSTRUCTIONS;
    private static final Color BACKGROUND_FOR_LABEL;
    private static final Color BACKGROUND_FOR_RESULT_DARK;
    private static final Color BACKGROUND_FOR_RESULT_MEDIUM;
    private static final Color BACKGROUND_FOR_RESULT_PALE;
    private static final Color FOREGROUND_FOR_EDITABLE;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_RESULT_DARK;
    private static final Color FOREGROUND_FOR_RESULT_MEDIUM;
    private static final Color FOREGROUND_FOR_RESULT_PALE;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final DecimalFormat[] df;
    private static final DecimalFormat TWO_PLACES;
    private static final Font FONT_FOR_INSTRUCTIONS;
    private static final Font FONT_FOR_LABEL;
    private static final Font FONT_FOR_LABEL_BOLD;
    private static final Font FONT_FOR_LABEL_UNITS;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private static final ImageIcon LOGO_ICON;
    private static final double MJ_PER_KWH = 3.6;
    private static HashMap<String, String> countryToCurr;
    private static Exch[] exchs;
    private Container contentPane;
    private Exch userExch;
    private JComboBox activity;
    private JComboBox location;
    private JLabel activityLabel;
    private JLabel costLabel;
    private JLabel durationLabel;
    private JLabel dutyCycleLabel;
    private JLabel energyLabel;
    private JLabel kwhLabel;
    private JLabel locationLabel;
    private JLabel logo;
    private JLabel megajoulesLabel;
    private JLabel powerLabel;
    private JLabel title;
    private JLabel title2;
    private JLabel wattsLabel;
    private JTextField cost;
    private JTextField duration;
    private JTextField dutyCycle;
    private JTextField instructions;
    private JTextField kwh;
    private JTextField megajoules;
    private JTextField watts;
    private String userCurrAbbr;
    
    public void destroy() {
        this.activity = null;
        this.activityLabel = null;
        this.contentPane = null;
        this.cost = null;
        this.costLabel = null;
        this.duration = null;
        this.durationLabel = null;
        this.dutyCycle = null;
        this.dutyCycleLabel = null;
        this.energyLabel = null;
        this.instructions = null;
        this.kwh = null;
        this.kwhLabel = null;
        this.location = null;
        this.locationLabel = null;
        this.logo = null;
        this.megajoules = null;
        this.megajoulesLabel = null;
        this.powerLabel = null;
        this.userExch = null;
        this.title2 = null;
        this.title = null;
        this.userCurrAbbr = null;
        this.watts = null;
        this.wattsLabel = null;
    }
    
    public void init() {
        this.contentPane = this.getContentPane();
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this.contentPane)) {
            this.stop();
            this.destroy();
        }
        this.buildMenu();
        this.buildComponents();
        this.layoutComponents();
        this.hookListeners();
        this.guessUserCurrency();
        this.calcCost();
        this.validate();
        this.setVisible(true);
    }
    
    private static void fetchCurrencyInfo() {
        try {
            final InputStream is = PowCost.class.getResourceAsStream("exchs.ser");
            final GZIPInputStream gzis = new GZIPInputStream(is, 4096);
            final ObjectInputStream ois = new ObjectInputStream(gzis);
            final long wasResourceVersion = (long)ois.readObject();
            if (wasResourceVersion != Exch.getSerialVersionUID()) {
                throw new IllegalArgumentException("Wrong version of exchs.ser file.  Was:" + wasResourceVersion + " wanted:" + Exch.getSerialVersionUID());
            }
            final String[] country = (String[])ois.readObject();
            final String[] currency = (String[])ois.readObject();
            PowCost.exchs = (Exch[])ois.readObject();
            ois.close();
            PowCost.countryToCurr = new HashMap<String, String>(325);
            for (int i = 0; i < country.length; ++i) {
                PowCost.countryToCurr.put(country[i], currency[i]);
            }
        }
        catch (Exception e) {
            System.err.println("Missing or damaged exchs.ser " + e + "\n");
        }
    }
    
    private static Exch findExchangeInfoForCurrency(final String currAbbr) {
        assert currAbbr != null && currAbbr.length() == 3 : "malformed currency Abbreviation:" + currAbbr;
        for (final Exch exch : PowCost.exchs) {
            if (exch.getCurrAbbr().equals(currAbbr)) {
                return exch;
            }
        }
        return null;
    }
    
    private void buildComponents() {
        this.contentPane.setBackground(PowCost.BACKGROUND_FOR_BODY);
        (this.title = new JLabel("Electric Power Cost Calculator 1.3", 2)).setFont(PowCost.FONT_FOR_TITLE);
        this.title.setForeground(PowCost.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2011-05-19 build:9411", 2)).setFont(PowCost.FONT_FOR_TITLE2);
        this.title2.setForeground(PowCost.FOREGROUND_FOR_TITLE);
        (this.activityLabel = new JLabel("Activity")).setBackground(PowCost.BACKGROUND_FOR_LABEL);
        this.activityLabel.setFont(PowCost.FONT_FOR_LABEL_BOLD);
        this.activityLabel.setForeground(PowCost.FOREGROUND_FOR_LABEL);
        (this.costLabel = new JLabel("C o s t")).setBackground(PowCost.BACKGROUND_FOR_LABEL);
        this.costLabel.setFont(PowCost.FONT_FOR_LABEL_BOLD);
        this.costLabel.setForeground(PowCost.FOREGROUND_FOR_LABEL);
        (this.durationLabel = new JLabel("duration")).setBackground(PowCost.BACKGROUND_FOR_LABEL);
        this.durationLabel.setFont(PowCost.FONT_FOR_LABEL);
        this.durationLabel.setForeground(PowCost.FOREGROUND_FOR_LABEL);
        (this.dutyCycleLabel = new JLabel("duty cycle")).setBackground(PowCost.BACKGROUND_FOR_LABEL);
        this.dutyCycleLabel.setFont(PowCost.FONT_FOR_LABEL);
        this.dutyCycleLabel.setForeground(PowCost.FOREGROUND_FOR_LABEL);
        this.energyLabel = new JLabel("energy");
        this.activityLabel.setBackground(PowCost.BACKGROUND_FOR_LABEL);
        this.energyLabel.setFont(PowCost.FONT_FOR_LABEL);
        this.energyLabel.setForeground(PowCost.FOREGROUND_FOR_LABEL);
        (this.megajoulesLabel = new JLabel("megajoules")).setBackground(PowCost.BACKGROUND_FOR_LABEL);
        this.megajoulesLabel.setFont(PowCost.FONT_FOR_LABEL_UNITS);
        this.megajoulesLabel.setForeground(PowCost.FOREGROUND_FOR_LABEL);
        (this.kwhLabel = new JLabel("kwh")).setBackground(PowCost.BACKGROUND_FOR_LABEL);
        this.kwhLabel.setFont(PowCost.FONT_FOR_LABEL_UNITS);
        this.kwhLabel.setForeground(PowCost.FOREGROUND_FOR_LABEL);
        (this.locationLabel = new JLabel("Location", 2)).setBackground(PowCost.BACKGROUND_FOR_LABEL);
        this.locationLabel.setFont(PowCost.FONT_FOR_LABEL_BOLD);
        this.locationLabel.setForeground(PowCost.FOREGROUND_FOR_LABEL);
        (this.logo = new JLabel("")).setIcon(PowCost.LOGO_ICON);
        (this.powerLabel = new JLabel("power")).setBackground(PowCost.BACKGROUND_FOR_LABEL);
        this.powerLabel.setFont(PowCost.FONT_FOR_LABEL);
        this.powerLabel.setForeground(PowCost.FOREGROUND_FOR_LABEL);
        (this.wattsLabel = new JLabel("watts")).setBackground(PowCost.BACKGROUND_FOR_LABEL);
        this.wattsLabel.setFont(PowCost.FONT_FOR_LABEL_UNITS);
        this.wattsLabel.setForeground(PowCost.FOREGROUND_FOR_LABEL);
        ActivityItem[] activities = null;
        try {
            final InputStream is = PowCost.class.getResourceAsStream("activities.ser");
            final GZIPInputStream gzis = new GZIPInputStream(is, 4096);
            final ObjectInputStream ois = new ObjectInputStream(gzis);
            activities = (ActivityItem[])ois.readObject();
            ois.close();
        }
        catch (Exception e) {
            System.err.println("Missing activities.ser " + e + "\n");
        }
        (this.activity = new JComboBox((Vector<E>)new Vector<Object>((Collection<? extends E>)Arrays.asList(activities)))).setBackground(PowCost.BACKGROUND_FOR_EDITABLE);
        this.activity.setEditable(true);
        this.activity.setForeground(PowCost.FOREGROUND_FOR_EDITABLE);
        this.activity.setMaximumSize(this.activity.getPreferredSize());
        assert activities != null;
        for (int i = 0; i < activities.length; ++i) {
            if (activities[i].toString().equals("run a 1 kw appliance for an hour (i.e. 1 kwh)")) {
                this.activity.setSelectedIndex(i);
                break;
            }
        }
        this.activity.setToolTipText("Select an activity to find out its cost in your region.");
        (this.cost = new JTextField(20)).setBackground(PowCost.BACKGROUND_FOR_RESULT_DARK);
        this.cost.setForeground(PowCost.FOREGROUND_FOR_RESULT_DARK);
        this.cost.setHorizontalAlignment(4);
        (this.duration = new JTextField(20)).setBackground(PowCost.BACKGROUND_FOR_RESULT_PALE);
        this.duration.setForeground(PowCost.FOREGROUND_FOR_RESULT_PALE);
        this.duration.setHorizontalAlignment(4);
        (this.dutyCycle = new JTextField(20)).setBackground(PowCost.BACKGROUND_FOR_RESULT_PALE);
        this.dutyCycle.setForeground(PowCost.FOREGROUND_FOR_RESULT_PALE);
        this.dutyCycle.setHorizontalAlignment(4);
        (this.megajoules = new JTextField(20)).setBackground(PowCost.BACKGROUND_FOR_RESULT_MEDIUM);
        this.megajoules.setForeground(PowCost.FOREGROUND_FOR_RESULT_MEDIUM);
        this.megajoules.setHorizontalAlignment(4);
        (this.kwh = new JTextField(20)).setHorizontalAlignment(4);
        this.kwh.setBackground(PowCost.BACKGROUND_FOR_RESULT_MEDIUM);
        this.kwh.setForeground(PowCost.FOREGROUND_FOR_RESULT_MEDIUM);
        CostLocItem[] costs = null;
        try {
            final InputStream is2 = PowCost.class.getResourceAsStream("costs.ser");
            final GZIPInputStream gzis2 = new GZIPInputStream(is2, 4096);
            final ObjectInputStream ois2 = new ObjectInputStream(gzis2);
            costs = (CostLocItem[])ois2.readObject();
            ois2.close();
        }
        catch (Exception e2) {
            System.err.println("Missing costs.ser " + e2 + "\n");
        }
        (this.location = new JComboBox((Vector<E>)new Vector<Object>((Collection<? extends E>)Arrays.asList(costs)))).setBackground(PowCost.BACKGROUND_FOR_EDITABLE);
        this.location.setEditable(true);
        this.location.setForeground(PowCost.FOREGROUND_FOR_EDITABLE);
        this.location.setMaximumSize(this.location.getPreferredSize());
        assert costs != null;
        for (int j = 0; j < costs.length; ++j) {
            if (costs[j].toString().equals("ca BC British Columbia")) {
                this.location.setSelectedIndex(j);
                break;
            }
        }
        this.location.setToolTipText("Select your location since electricity prices vary wildly by region.");
        (this.watts = new JTextField(20)).setBackground(PowCost.BACKGROUND_FOR_RESULT_PALE);
        this.watts.setForeground(PowCost.FOREGROUND_FOR_RESULT_PALE);
        this.watts.setHorizontalAlignment(4);
        (this.instructions = new JTextField("Select a location, then an electricity-using activity to find out how much that activity will cost in your local currency.")).setBackground(PowCost.BACKGROUND_FOR_INSTRUCTIONS);
        this.instructions.setEditable(false);
        this.instructions.setFont(PowCost.FONT_FOR_INSTRUCTIONS);
        this.instructions.setForeground(PowCost.FOREGROUND_FOR_INSTRUCTIONS);
        this.instructions.setMargin(new Insets(2, 2, 2, 2));
    }
    
    private void buildMenu() {
        final JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);
        menubar.add(Laf.buildLookAndFeelMenu());
        final JMenu menuHelp = new JMenu("Help");
        menubar.add(menuHelp);
        final JMenuItem aboutItem = new JMenuItem("About");
        menuHelp.add(aboutItem);
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox(Misc.getParentFrame(PowCost.this), "Electric Power Cost Calculator", "1.3", "Calculates cost of electric watts for various activities", "", "freeware", "2011-05-19", 2010, "Roedy Green", "POWCOST", "1.5");
            }
        });
    }
    
    private void calcCost() {
        final ActivityItem activityChoice = (ActivityItem)this.activity.getSelectedItem();
        final int powerInWatts = activityChoice.getWatts();
        final int dutyCycleInPercent = activityChoice.getDutyCycle();
        final int seconds = activityChoice.getSeconds();
        final CostLocItem costChoice = (CostLocItem)this.location.getSelectedItem();
        final String currencyAbbrForLocation = costChoice.getCurrencyAbbr();
        final Exch locationExch = findExchangeInfoForCurrency(currencyAbbrForLocation);
        if (locationExch == null) {
            throw new IllegalArgumentException("No exchange rate defined for " + currencyAbbrForLocation);
        }
        int decimalPlaces = this.userExch.getDecimalPlaces();
        final String currencySymbol = this.userExch.getSymbol();
        final double costPerKwh = costChoice.getCostPerKwh();
        final double energyInKwh = powerInWatts * seconds * dutyCycleInPercent / 3.6E8;
        final double exchangeAdjust = locationExch.getExchangeRate() / this.userExch.getExchangeRate();
        final double costOfActivity = energyInKwh * costPerKwh * exchangeAdjust;
        final double energyInMJ = energyInKwh * 3.6;
        this.watts.setText(Integer.toString(powerInWatts));
        this.dutyCycle.setText(Integer.toString(dutyCycleInPercent) + '%');
        this.duration.setText(activityChoice.getDuration());
        this.kwh.setText(PowCost.TWO_PLACES.format(energyInKwh));
        this.megajoules.setText(PowCost.TWO_PLACES.format(energyInMJ));
        if (costOfActivity > 0.0) {
            final int decade = (int)Math.floor(Math.log10(costOfActivity));
            decimalPlaces = Math.min(Math.max(decimalPlaces, -decade), decimalPlaces + 3);
        }
        assert 0 <= decimalPlaces && decimalPlaces <= 6 : "too many decimal places requested. Problem in currency configuration file.";
        this.cost.setText(currencySymbol + PowCost.df[decimalPlaces].format(costOfActivity) + " " + this.userCurrAbbr);
    }
    
    private void guessUserCurrency() {
        try {
            final String countryAbbr = Locale.getDefault().getCountry();
            this.userCurrAbbr = PowCost.countryToCurr.get(countryAbbr);
            this.userExch = findExchangeInfoForCurrency(this.userCurrAbbr);
        }
        catch (Exception e) {
            this.userExch = null;
        }
        if (this.userExch == null) {
            this.userCurrAbbr = "USD";
            this.userExch = findExchangeInfoForCurrency(this.userCurrAbbr);
        }
    }
    
    private void hookListeners() {
        this.location.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                PowCost.this.calcCost();
            }
        });
        this.activity.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                PowCost.this.calcCost();
            }
        });
    }
    
    private void layoutComponents() {
        this.contentPane.setLayout(new GridBagLayout());
        this.contentPane.add(this.title, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0, 17, 0, new Insets(20, 10, 5, 5), 0, 0));
        this.contentPane.add(this.title2, new GridBagConstraints(3, 0, 2, 1, 0.0, 0.0, 17, 0, new Insets(20, 5, 5, 5), 0, 0));
        this.contentPane.add(this.locationLabel, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, 16, 0, new Insets(5, 10, 5, 5), 0, 0));
        this.contentPane.add(this.activityLabel, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, 16, 0, new Insets(5, 5, 5, 5), 0, 0));
        this.contentPane.add(this.logo, new GridBagConstraints(5, 0, 2, 2, 0.0, 0.0, 13, 0, new Insets(10, 5, 5, 10), 0, 0));
        this.contentPane.add(this.location, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, 17, 0, new Insets(5, 10, 5, 5), 5, 0));
        this.contentPane.add(this.activity, new GridBagConstraints(3, 2, 4, 1, 0.0, 0.0, 17, 0, new Insets(5, 5, 5, 10), 5, 0));
        this.contentPane.add(this.dutyCycleLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, 14, 0, new Insets(20, 10, 5, 5), 0, 0));
        this.contentPane.add(this.powerLabel, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, 14, 0, new Insets(20, 10, 5, 5), 0, 0));
        this.contentPane.add(this.durationLabel, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, 14, 0, new Insets(20, 5, 5, 5), 0, 0));
        this.contentPane.add(this.energyLabel, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0, 14, 0, new Insets(20, 5, 5, 5), 0, 0));
        this.contentPane.add(this.costLabel, new GridBagConstraints(6, 3, 1, 1, 0.0, 0.0, 14, 0, new Insets(20, 5, 5, 10), 0, 0));
        this.contentPane.add(this.dutyCycle, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 10, 5, 5), 60, 0));
        this.contentPane.add(this.watts, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 5, 2), 60, 0));
        this.contentPane.add(this.wattsLabel, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 0, 5, 10), 0, 0));
        this.contentPane.add(this.duration, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 5, 5, 5), 120, 0));
        this.contentPane.add(this.kwh, new GridBagConstraints(4, 4, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 5, 5, 2), 75, 0));
        this.contentPane.add(this.kwhLabel, new GridBagConstraints(5, 4, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 0, 5, 5), 0, 0));
        this.contentPane.add(this.cost, new GridBagConstraints(6, 4, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 5, 10), 100, 0));
        this.contentPane.add(this.megajoules, new GridBagConstraints(4, 5, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 5, 5, 2), 75, 0));
        this.contentPane.add(this.megajoulesLabel, new GridBagConstraints(5, 5, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 0, 5, 5), 0, 0));
        this.contentPane.add(this.instructions, new GridBagConstraints(0, 6, 7, 1, 0.0, 0.0, 17, 1, new Insets(20, 10, 20, 10), 0, 0));
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new PowCost(), "Electric Power Cost Calculator 1.3", 768, 395);
    }
    
    static {
        BACKGROUND_FOR_BODY = Build.BACKGROUND_FOR_BLENDING;
        BACKGROUND_FOR_EDITABLE = Color.WHITE;
        BACKGROUND_FOR_INSTRUCTIONS = new Color(16316664);
        BACKGROUND_FOR_LABEL = Build.BACKGROUND_FOR_BLENDING;
        BACKGROUND_FOR_RESULT_DARK = new Color(11075567);
        BACKGROUND_FOR_RESULT_MEDIUM = new Color(12910575);
        BACKGROUND_FOR_RESULT_PALE = new Color(15007727);
        FOREGROUND_FOR_EDITABLE = Color.BLACK;
        FOREGROUND_FOR_INSTRUCTIONS = new Color(3381521);
        FOREGROUND_FOR_LABEL = Color.BLUE;
        FOREGROUND_FOR_RESULT_DARK = Color.BLACK;
        FOREGROUND_FOR_RESULT_MEDIUM = Color.BLACK;
        FOREGROUND_FOR_RESULT_PALE = new Color(2254370);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        df = new DecimalFormat[] { new DecimalFormat("###,##0"), new DecimalFormat("###,##0.0"), new DecimalFormat("###,##0.00"), new DecimalFormat("###,##0.000"), new DecimalFormat("###,##0.0000"), new DecimalFormat("###,##0.00000"), new DecimalFormat("###,##0.000000") };
        TWO_PLACES = new DecimalFormat("###,##0.00");
        FONT_FOR_INSTRUCTIONS = FontFactory.build("Dialog", 0, 12);
        FONT_FOR_LABEL = FontFactory.build("Dialog", 0, 13);
        FONT_FOR_LABEL_BOLD = FontFactory.build("Dialog", 1, 14);
        FONT_FOR_LABEL_UNITS = FontFactory.build("Dialog", 0, 11);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 15);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
        LOGO_ICON = new ImageIcon(PowCost.class.getResource("image/reddy.png"));
        fetchCurrencyInfo();
    }
}
