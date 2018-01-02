// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.wassup;

import com.mindprod.common11.Hybrid;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import com.mindprod.common11.FontFactory;
import com.mindprod.common11.CMPAboutBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Comparator;
import java.util.Arrays;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Container;
import com.mindprod.common11.VersionCheck;
import java.awt.TextArea;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

public final class Wassup extends Applet
{
    private static final int APPLET_HEIGHT = 398;
    private static final int APPLET_WIDTH = 628;
    private static final String appletProperties = "Java System Properties accessible to unsigned Applets";
    private static final String applicationProperties = "Java System Properties accessible to signed Applets and applications";
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1998-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2008-04-03";
    private static final String TITLE_STRING = "CMP Wassup";
    private static final String VERSION_STRING = "2.5";
    private static final Color black;
    private static final Color DARK_GREEN;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Color white;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private Button about;
    private Choice safe;
    private Label captionLabel;
    private Label javaVersion;
    private Label title;
    private Label title2;
    private TextArea keyValuePairs;
    private boolean allowRestricted;
    
    public Wassup() {
        this.allowRestricted = false;
    }
    
    public void destroy() {
        this.about = null;
        this.captionLabel = null;
        this.javaVersion = null;
        this.keyValuePairs = null;
        this.safe = null;
        this.title = null;
        this.title2 = null;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 2, 0, this)) {
            return;
        }
        this.setBackground(Wassup.white);
        this.allowRestricted = true;
        this.setLayout(new GridBagLayout());
        this.buildComponents();
        this.layoutComponents();
        this.showProperties();
        this.validate();
        this.setVisible(true);
    }
    
    private static String displayAllProperties(final String separator) {
        try {
            final Properties sysprops = System.getProperties();
            final int count = sysprops.size();
            final String[][] matrix = new String[count][2];
            int j = 0;
            final Enumeration e = sysprops.propertyNames();
            while (j < count) {
                final String key = e.nextElement();
                final String value = sysprops.getProperty(key);
                matrix[j][0] = key;
                matrix[j][1] = value;
                ++j;
            }
            Arrays.sort(matrix, new ByKey());
            final StringBuffer result = new StringBuffer(4096);
            for (int i = 0; i < count; ++i) {
                final String key2 = matrix[i][0];
                if (key2 != null) {
                    String value2 = matrix[i][1];
                    if (value2 != null) {
                        if (value2.equals("\r\n")) {
                            value2 = "[hex chars: 0x0d 0x0a i.e. CrLf, \\r\\n]";
                        }
                        else if (value2.equals("\n")) {
                            value2 = "[hex char: 0x0a i.e. Lf, \\n]";
                        }
                        else if (value2.equals("\r")) {
                            value2 = "[hex char: 0x0d i.e. Cr, \\r]";
                        }
                        result.append(key2);
                        result.append(" = ");
                        result.append(value2);
                        result.append(separator);
                    }
                }
            }
            return result.toString();
        }
        catch (Exception e2) {
            return "No security clearance to see the restricted System properties.";
        }
    }
    
    private static String displaySafeProperties(final String separator) {
        final String[] safeNames = { "browser", "browser.version", "file.separator", "java.class.version", "java.specification.name", "java.specification.vendor", "java.specification.version", "java.vendor", "java.vendor.url", "java.version", "java.vm.name", "java.vm.specification.name", "java.vm.specification.vendor", "java.vm.specification.version", "java.vm.vendor", "java.vm.version", "line.separator", "os.arch", "os.name", "os.version", "path.separator" };
        final int count = safeNames.length;
        final StringBuffer result = new StringBuffer(4096);
        for (final String key : safeNames) {
            if (key != null) {
                try {
                    String value = System.getProperty(key);
                    if (value != null) {
                        if (value.equals("\r\n")) {
                            value = "[binary chars: 0x0d 0x0a i.e. CrLf, \\r\\n]";
                        }
                        else if (value.equals("\n")) {
                            value = "[binary char: 0x0a i.e. Lf, \\n]";
                        }
                        result.append(key);
                        result.append(" = ");
                        result.append(value);
                        result.append(separator);
                    }
                }
                catch (Exception ex) {}
            }
        }
        return result.toString();
    }
    
    private static void outputProperties(final boolean allowRestricted) {
        System.out.println("CMP Wassup 2.5");
        System.out.println(allowRestricted ? "Java System Properties accessible to signed Applets and applications" : "Java System Properties accessible to unsigned Applets");
        System.out.println();
        String lineSeparator = System.getProperties().getProperty("line.separator");
        lineSeparator += lineSeparator;
        System.out.println(allowRestricted ? displayAllProperties(lineSeparator) : displaySafeProperties(lineSeparator));
    }
    
    private Wassup(final boolean allowRestricted) {
        this.allowRestricted = false;
        this.allowRestricted = allowRestricted;
    }
    
    private void buildComponents() {
        (this.about = new Button("about")).setForeground(Wassup.white);
        this.about.setBackground(Wassup.DARK_GREEN);
        this.about.setFont(Wassup.FONT_FOR_TITLE);
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutBox("CMP Wassup", "2.5", "Shows Java system properties,", "both restricted and unrestricted.", "freeware", "2008-04-03", 1998, "Roedy Green", "WASSUP", "1.2");
            }
        });
        (this.title = new Label("CMP Wassup 2.5")).setFont(Wassup.FONT_FOR_TITLE);
        this.title.setForeground(Wassup.FOREGROUND_FOR_TITLE);
        (this.title2 = new Label("released:2008-04-03 build:9410")).setFont(Wassup.FONT_FOR_TITLE2);
        this.title2.setForeground(Wassup.FOREGROUND_FOR_TITLE);
        (this.safe = new Choice()).setFont(FontFactory.build("Dialog", 0, 12));
        this.safe.setForeground(Wassup.FOREGROUND_FOR_LABEL);
        this.safe.addItem("safe");
        this.safe.select(0);
        if (this.allowRestricted) {
            this.safe.addItem("restricted");
        }
        this.safe.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent event) {
                final Object object = event.getSource();
                if (object == Wassup.this.safe) {
                    Wassup.this.showProperties();
                }
            }
        });
        (this.javaVersion = new Label("Java version " + System.getProperty("java.version"), 2)).setFont(FontFactory.build("Dialog", 1, 16));
        this.javaVersion.setForeground(Wassup.FOREGROUND_FOR_LABEL);
        (this.keyValuePairs = new TextArea("", 0, 0, 1)).setEditable(false);
        this.keyValuePairs.setFont(FontFactory.build("Dialog", 0, 15));
        this.keyValuePairs.setForeground(Wassup.black);
        (this.captionLabel = new Label(this.allowRestricted ? "Java System Properties accessible to signed Applets and applications" : "Java System Properties accessible to unsigned Applets")).setFont(FontFactory.build("Dialog", 1, 11));
        this.captionLabel.setForeground(Wassup.FOREGROUND_FOR_LABEL);
    }
    
    private void layoutComponents() {
        this.add(this.title, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 0, 5), 0, 0));
        this.add(this.title2, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, 17, 0, new Insets(5, 10, 0, 5), 0, 0));
        this.add(this.about, new GridBagConstraints(2, 0, 1, 2, 0.0, 0.0, 13, 0, new Insets(10, 5, 0, 10), 10, 2));
        this.add(this.safe, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 10, 0, 5), 0, 0));
        this.add(this.javaVersion, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, 13, 0, new Insets(2, 5, 0, 10), 0, 0));
        this.add(this.keyValuePairs, new GridBagConstraints(0, 3, 3, 1, 0.0, 0.0, 10, 1, new Insets(5, 10, 0, 10), 0, 0));
        this.add(this.captionLabel, new GridBagConstraints(0, 4, 3, 1, 0.0, 0.0, 10, 1, new Insets(5, 10, 10, 10), 0, 0));
    }
    
    void showProperties() {
        this.allowRestricted = (this.safe.getSelectedIndex() != 0);
        this.captionLabel.setText(this.allowRestricted ? "Java System Properties accessible to signed Applets and applications" : "Java System Properties accessible to unsigned Applets");
        this.keyValuePairs.setText(this.allowRestricted ? displayAllProperties("\n\n") : displaySafeProperties("\n\n"));
    }
    
    public static void main(final String[] args) {
        boolean gui = true;
        if (args.length > 0 && (args[0].equalsIgnoreCase("nogui") || args[0].equalsIgnoreCase("/nogui") || args[0].equalsIgnoreCase("-nogui"))) {
            gui = false;
        }
        if (System.getProperty("GUI", "true").equals("false")) {
            gui = false;
        }
        if (gui) {
            Hybrid.fireup(new Wassup(true), "CMP Wassup 2.5", 628, 398);
        }
        else {
            outputProperties(true);
        }
    }
    
    static {
        black = Color.black;
        DARK_GREEN = new Color(32768);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        white = Color.white;
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
    }
    
    private static class ByKey implements Comparator
    {
        public final int compare(final Object a, final Object b) {
            return ((String[])a)[0].compareToIgnoreCase(((String[])b)[0]);
        }
    }
}
