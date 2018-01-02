// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.pws;

import com.mindprod.common11.Build;
import com.mindprod.common11.Hybrid;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.mindprod.common11.FontFactory;
import com.mindprod.common11.CMPAboutBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Container;
import com.mindprod.common11.VersionCheck;
import java.awt.TextArea;
import java.awt.List;
import java.awt.Label;
import java.awt.Button;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

public final class Pws extends Applet
{
    private static final int APPLET_HEIGHT = 720;
    private static final int APPLET_WIDTH = 564;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1998-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2008-04-06";
    private static final String TITLE_STRING = "Living Love Pathways Amanuensis";
    private static final String VERSION_STRING = "2.0";
    private static final Color BACKGROUND_FOR_ADVICE;
    private static final Color BACKGROUND_FOR_BODY;
    private static final Color BACKGROUND_FOR_BUTTON;
    private static final Color BACKGROUND_FOR_INSTRUCTIONS;
    private static final Color BACKGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_ADVICE;
    private static final Color FOREGROUND_FOR_BUTTON;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_BUTTON;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private Button about;
    private Button advise;
    private Label adviceLabel;
    private Label dummy;
    private Label emotionLabel;
    private Label title;
    private Label title2;
    private Label whatLabel;
    private Label whenLabel;
    private Label whereLabel;
    private Label whoLabel;
    private List emotion;
    private List when;
    private List where;
    private List who;
    private TextArea advice;
    private TextArea instructions;
    private TextArea what;
    
    public void destroy() {
        this.about = null;
        this.advice = null;
        this.adviceLabel = null;
        this.advise = null;
        this.dummy = null;
        this.emotion = null;
        this.emotionLabel = null;
        this.instructions = null;
        this.title2 = null;
        this.title = null;
        this.what = null;
        this.whatLabel = null;
        this.when = null;
        this.whenLabel = null;
        this.where = null;
        this.whereLabel = null;
        this.who = null;
        this.whoLabel = null;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 1, 0, this)) {
            return;
        }
        this.setBackground(Pws.BACKGROUND_FOR_BODY);
        this.setLayout(new GridBagLayout());
        this.buildComponents();
        this.layoutComponents();
        final TheListener theListener = new TheListener();
        this.advise.addActionListener(theListener);
        this.validate();
        this.setVisible(true);
    }
    
    private void buildComponents() {
        (this.title = new Label("Living Love Pathways Amanuensis 2.0", 0)).setFont(Pws.FONT_FOR_TITLE);
        this.title.setForeground(Pws.FOREGROUND_FOR_TITLE);
        (this.title2 = new Label("released:2008-04-06 build:9411")).setFont(Pws.FONT_FOR_TITLE2);
        this.title2.setForeground(Pws.FOREGROUND_FOR_TITLE);
        (this.about = new Button("about")).setForeground(Pws.FOREGROUND_FOR_BUTTON);
        this.about.setBackground(Pws.BACKGROUND_FOR_BUTTON);
        this.about.setFont(Pws.FONT_FOR_BUTTON);
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutBox("Living Love Pathways Amanuensis", "2.0", "Pathways Amanuensis:", "Help with personal problems using the Ken Keyes 12 pathways.", "freeware", "2008-04-06", 1995, "Roedy Green", "PWS", "1.1");
            }
        });
        (this.whenLabel = new Label("when?", 0)).setFont(FontFactory.build("Dialog", 1, 15));
        this.whenLabel.setForeground(Pws.FOREGROUND_FOR_LABEL);
        (this.when = new List(4)).setMultipleMode(false);
        (this.emotionLabel = new Label("emotions?", 2)).setFont(FontFactory.build("Dialog", 1, 15));
        this.emotionLabel.setForeground(Pws.FOREGROUND_FOR_LABEL);
        (this.emotion = new List(4)).setMultipleMode(true);
        this.dummy = new Label("");
        (this.whereLabel = new Label("where?", 0)).setFont(FontFactory.build("Dialog", 1, 15));
        this.whereLabel.setForeground(Pws.FOREGROUND_FOR_LABEL);
        (this.where = new List(4)).setMultipleMode(false);
        (this.whoLabel = new Label("who?", 2)).setFont(FontFactory.build("Dialog", 1, 15));
        this.whoLabel.setForeground(Pws.FOREGROUND_FOR_LABEL);
        (this.who = new List(4)).setMultipleMode(true);
        (this.whatLabel = new Label("what?", 1)).setFont(FontFactory.build("Dialog", 1, 15));
        this.whatLabel.setForeground(Pws.FOREGROUND_FOR_LABEL);
        (this.what = new TextArea("Tersely describe what happened/is happening as objectively as possible, no justifications or judgements, Sargeant Joe Friday style,  (e.g. Jim hung up the phone without saying goodbye) here:", 4, 60, 3)).setEditable(true);
        this.what.setFont(FontFactory.build("Dialog", 0, 16));
        this.what.setForeground(Pws.FOREGROUND_FOR_LABEL);
        this.what.setBackground(Pws.BACKGROUND_FOR_LABEL);
        (this.adviceLabel = new Label("advice", 1)).setFont(FontFactory.build("Dialog", 1, 15));
        this.adviceLabel.setForeground(Pws.FOREGROUND_FOR_LABEL);
        this.adviceLabel.setBackground(Pws.BACKGROUND_FOR_LABEL);
        (this.advice = new TextArea("Advice based on the Twelve Pathways from Ken Keyes' Handbook to Higher Consciousness ISBN 0-09600688-8-0.", 4, 60, 3)).setEditable(false);
        this.advice.setFont(FontFactory.build("Dialog", 0, 16));
        this.advice.setForeground(Pws.FOREGROUND_FOR_LABEL);
        this.advice.setBackground(Pws.BACKGROUND_FOR_ADVICE);
        (this.instructions = new TextArea("Think of a troubling event, and select where, when, and who was involved; describe what happened; then click 'advise'.", 2, 50, 3)).setBackground(Pws.BACKGROUND_FOR_INSTRUCTIONS);
        this.instructions.setForeground(Pws.FOREGROUND_FOR_INSTRUCTIONS);
        this.instructions.setFont(FontFactory.build("Dialog", 0, 13));
        this.instructions.setEditable(false);
        (this.advise = new Button("advise")).setBackground(Pws.BACKGROUND_FOR_BUTTON);
        this.advise.setForeground(Pws.FOREGROUND_FOR_BUTTON);
        this.advise.setFont(Pws.FONT_FOR_BUTTON);
        for (int i = 0; i < PwsText.times.length; ++i) {
            this.when.add(PwsText.times[i]);
        }
        this.when.select(4);
        for (int i = 0; i < PwsText.emotions.length; ++i) {
            this.emotion.add(PwsText.emotions[i]);
        }
        for (int i = 0; i < PwsText.people.length; ++i) {
            this.who.add(PwsText.people[i]);
        }
        for (int i = 0; i < PwsText.locations.length; ++i) {
            this.where.add(PwsText.locations[i]);
        }
    }
    
    private void layoutComponents() {
        this.add(this.title, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 5, 5), 0, 0));
        this.add(this.title2, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, 17, 0, new Insets(5, 10, 5, 5), 0, 0));
        this.add(this.about, new GridBagConstraints(2, 0, 1, 2, 0.0, 0.0, 13, 0, new Insets(10, 5, 5, 10), 10, 2));
        this.add(this.whenLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, 16, 0, new Insets(5, 10, 0, 5), 0, 0));
        this.add(this.when, new GridBagConstraints(0, 3, 1, 2, 40.0, 0.0, 18, 2, new Insets(5, 10, 5, 5), 0, 0));
        this.add(this.dummy, new GridBagConstraints(1, 3, 1, 1, 30.0, 1.0, 10, 1, new Insets(5, 5, 5, 5), 0, 0));
        this.add(this.emotionLabel, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, 14, 0, new Insets(5, 5, 0, 10), 0, 0));
        this.add(this.emotion, new GridBagConstraints(2, 3, 1, 2, 40.0, 0.0, 12, 2, new Insets(5, 5, 5, 10), 0, 0));
        this.add(this.whereLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, 16, 0, new Insets(5, 10, 0, 5), 0, 0));
        this.add(this.where, new GridBagConstraints(0, 6, 1, 3, 0.0, 0.0, 18, 0, new Insets(5, 10, 5, 10), 0, 0));
        this.add(this.whoLabel, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, 14, 0, new Insets(5, 5, 0, 10), 0, 0));
        this.add(this.who, new GridBagConstraints(2, 6, 1, 2, 0.0, 0.0, 12, 0, new Insets(5, 5, 5, 10), 0, 0));
        this.add(this.whatLabel, new GridBagConstraints(0, 8, 3, 1, 0.0, 0.0, 15, 0, new Insets(5, 10, 0, 5), 0, 0));
        this.add(this.what, new GridBagConstraints(0, 9, 3, 3, 100.0, 40.0, 11, 1, new Insets(5, 10, 5, 10), 0, 0));
        this.add(this.adviceLabel, new GridBagConstraints(0, 12, 3, 1, 0.0, 0.0, 15, 0, new Insets(5, 10, 0, 5), 0, 0));
        this.add(this.advice, new GridBagConstraints(0, 13, 3, 3, 100.0, 40.0, 11, 1, new Insets(5, 10, 5, 10), 0, 0));
        this.add(this.instructions, new GridBagConstraints(0, 16, 2, 1, 0.0, 0.0, 17, 0, new Insets(5, 10, 10, 5), 0, 0));
        this.add(this.advise, new GridBagConstraints(2, 16, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 10, 10), 0, 0));
    }
    
    public static void main(final String[] args) {
        Hybrid.fireup(new Pws(), "Living Love Pathways Amanuensis 2.0", 564, 720);
    }
    
    static {
        BACKGROUND_FOR_ADVICE = new Color(16185087);
        BACKGROUND_FOR_BODY = Build.BACKGROUND_FOR_BLENDING;
        BACKGROUND_FOR_BUTTON = Color.white;
        BACKGROUND_FOR_INSTRUCTIONS = Color.white;
        BACKGROUND_FOR_LABEL = Build.BACKGROUND_FOR_BLENDING;
        FOREGROUND_FOR_ADVICE = Color.red;
        FOREGROUND_FOR_BUTTON = new Color(14423100);
        FOREGROUND_FOR_INSTRUCTIONS = new Color(32768);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_BUTTON = FontFactory.build("Dialog", 1, 18);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
    }
    
    private final class TheListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent event) {
            final Object object = event.getSource();
            if (object == Pws.this.advise) {
                Pws.this.advice.setForeground(Pws.FOREGROUND_FOR_ADVICE);
                Pws.this.advice.setText(PwsText.getRandomPathway());
            }
        }
    }
}
