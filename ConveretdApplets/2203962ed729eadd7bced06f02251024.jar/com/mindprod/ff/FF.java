// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.ff;

import com.mindprod.common11.Hybrid;
import com.mindprod.common11.FontFactory;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Container;
import com.mindprod.common11.VersionCheck;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

public final class FF extends Applet
{
    private static final int APPLET_HEIGHT = 240;
    private static final int APPLET_WIDTH = 360;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2001-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2006-03-06";
    private static final String TITLE_STRING = "Fast File Find";
    private static final String VERSION_STRING = "1.1";
    private static final Color DARK_GREEN;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_TITLE;
    private Button go;
    private Choice exthow;
    private Choice filehow;
    private Label extlabel;
    private Label filelabel;
    private Label title;
    private TextField extname;
    private TextField filename;
    
    public void destroy() {
        this.exthow = null;
        this.extlabel = null;
        this.extname = null;
        this.filehow = null;
        this.filelabel = null;
        this.filename = null;
        this.go = null;
        this.title = null;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 1, 0, this)) {
            return;
        }
        this.setVisible(false);
        this.setBackground(Color.white);
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        (this.title = new Label("Fast File Find 1.1", 1)).setFont(FF.FONT_FOR_TITLE);
        this.title.setForeground(FF.FOREGROUND_FOR_TITLE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = 10;
        gbc.fill = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        ((GridBagLayout)this.getLayout()).setConstraints(this.title, gbc);
        this.add(this.title);
        (this.filelabel = new Label("Filename", 0)).setForeground(FF.FOREGROUND_FOR_LABEL);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = 17;
        gbc.fill = 0;
        gbc.insets = new Insets(0, 10, 10, 10);
        ((GridBagLayout)this.getLayout()).setConstraints(this.filelabel, gbc);
        this.add(this.filelabel);
        (this.filehow = new Choice()).add("Contains");
        this.filehow.select("Contains");
        this.filehow.add("Starts With");
        this.filehow.add("Ends With");
        this.filehow.add("Matches");
        this.filehow.add("Wildcard");
        this.filehow.add("Regex");
        this.filehow.add("Ignore");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = 17;
        gbc.fill = 0;
        gbc.insets = new Insets(0, 10, 10, 10);
        ((GridBagLayout)this.getLayout()).setConstraints(this.filehow, gbc);
        this.add(this.filehow);
        this.filename = new TextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = 17;
        gbc.fill = 0;
        gbc.insets = new Insets(0, 10, 10, 10);
        gbc.ipadx = 100;
        ((GridBagLayout)this.getLayout()).setConstraints(this.filename, gbc);
        this.add(this.filename);
        (this.extlabel = new Label("Extension", 0)).setForeground(FF.FOREGROUND_FOR_LABEL);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = 17;
        gbc.fill = 0;
        gbc.insets = new Insets(0, 10, 10, 10);
        ((GridBagLayout)this.getLayout()).setConstraints(this.extlabel, gbc);
        this.add(this.extlabel);
        (this.exthow = new Choice()).add("Contains");
        this.exthow.add("Starts With");
        this.exthow.add("Ends With");
        this.exthow.add("Matches");
        this.exthow.select("Matches");
        this.exthow.add("Wildcard");
        this.exthow.add("Regex");
        this.exthow.add("Ignore");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = 17;
        gbc.fill = 0;
        gbc.insets = new Insets(0, 10, 10, 10);
        ((GridBagLayout)this.getLayout()).setConstraints(this.exthow, gbc);
        this.add(this.exthow);
        this.extname = new TextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = 17;
        gbc.fill = 0;
        gbc.insets = new Insets(0, 10, 10, 10);
        gbc.ipadx = 50;
        ((GridBagLayout)this.getLayout()).setConstraints(this.extname, gbc);
        this.add(this.extname);
        (this.go = new Button("GO")).setFont(FontFactory.build("Dialog", 1, 14));
        this.go.setForeground(Color.white);
        this.go.setBackground(FF.DARK_GREEN);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = 10;
        gbc.fill = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.ipadx = 20;
        ((GridBagLayout)this.getLayout()).setConstraints(this.go, gbc);
        this.add(this.go);
        this.validate();
        this.setVisible(true);
    }
    
    public static void main(final String[] args) {
        Hybrid.fireup(new FF(), "Fast File Find 1.1", 360, 240);
    }
    
    static {
        DARK_GREEN = new Color(32768);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
    }
}
