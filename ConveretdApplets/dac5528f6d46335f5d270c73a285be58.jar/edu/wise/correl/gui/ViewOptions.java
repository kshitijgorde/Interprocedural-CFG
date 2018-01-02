// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.correl.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import edu.wise.correl.InnerDataView;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import edu.wise.correl.Cor_app;
import java.awt.Label;
import java.awt.Choice;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.Panel;

public class ViewOptions extends Panel
{
    Panel inPan;
    private static Button showDE;
    private static Button aboutWISE;
    public static Checkbox showSSErr;
    public static Checkbox showSSTot;
    public static Checkbox showRegLine;
    public static Checkbox showRegress;
    public static Checkbox showResid;
    public static Checkbox showSSPred;
    public static Checkbox showYbar;
    public static Checkbox showAsSquaredError;
    CheckboxGroup cbg;
    public static Choice panelToShow;
    Label moduleLabel;
    private static Cor_app ca;
    GridBagLayout gbl;
    GridBagConstraints gbc;
    
    public ViewOptions(final int n, final int n2, final Cor_app ca) {
        ViewOptions.ca = ca;
        this.setSize(n, n2);
        this.setBackground(StyleSheet.BACKGROUND);
        this.gbl = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.setLayout(this.gbl);
        this.setFont(StyleSheet.f_reg);
        ViewOptions.showDE = new Button("Show Data Editor");
        ViewOptions.aboutWISE = new Button("Help...");
        (ViewOptions.showSSErr = new Checkbox("Show Error Deviations")).setState(Cor_app.datav.getIDV().getDrawSSErr());
        (ViewOptions.showSSPred = new Checkbox("Show Predicted Deviations")).setState(Cor_app.datav.getIDV().getDrawSSpred());
        (ViewOptions.showSSTot = new Checkbox("Show Total Deviations")).setState(Cor_app.datav.getIDV().getDrawSStot());
        (ViewOptions.showRegLine = new Checkbox("Show Regression Line")).setState(Cor_app.datav.getIDV().getDrawRegLine());
        (ViewOptions.showYbar = new Checkbox("Show Mean of Y")).setState(Cor_app.datav.getIDV().getDrawYbar());
        (ViewOptions.showAsSquaredError = new Checkbox("Show Squared Deviations")).setState(Cor_app.datav.getIDV().getDrawAsSquaredError());
        this.cbg = new CheckboxGroup();
        ViewOptions.showRegress = new Checkbox("Show Regression", this.cbg, true);
        ViewOptions.showResid = new Checkbox("Show Residuals", this.cbg, false);
        (ViewOptions.panelToShow = new Choice()).add("Regression");
        ViewOptions.panelToShow.add("The Mean");
        ViewOptions.panelToShow.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                if (itemEvent.getSource() instanceof Choice) {
                    final String s = (String)itemEvent.getItem();
                    if (s.equals("Regression")) {
                        ViewOptions.this.setPanels(0);
                    }
                    else if (s.equals("The Mean")) {
                        ViewOptions.this.setPanels(1);
                    }
                    else {
                        System.out.println("Choice assignment error");
                    }
                    ViewOptions.panelToShow.select(((Choice)itemEvent.getSource()).getSelectedIndex());
                    Cor_app.datav.getIDV();
                    InnerDataView.getActiveLine().getRegLine();
                }
            }
        });
        (this.moduleLabel = new Label("Select a Lesson:")).setFont(StyleSheet.labelFont);
        final Button button = new Button("Zoom in");
        final Button button2 = new Button("Zoom out");
        final Button button3 = new Button("Reset");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                Cor_app.datav.getIDV().setZoom(1.0);
            }
        });
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                Cor_app.datav.getIDV().setZoom(1.1111);
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                Cor_app.datav.getIDV().setZoom(0.9);
            }
        });
        this.gbc.weightx = 1.0;
        this.gbc.weighty = 1.0;
        this.gbc.anchor = 10;
        this.gbc.fill = 2;
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.weightx = 1.0;
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.insets = new Insets(2, 10, 0, 10);
        this.gbl.setConstraints(this.add(button3), this.gbc);
        this.gbc.weightx = 1.0;
        this.gbc.gridx = 0;
        this.gbc.gridy = 1;
        this.gbc.insets = new Insets(0, 10, 0, 10);
        this.gbl.setConstraints(this.add(button), this.gbc);
        this.gbc.gridx = 0;
        this.gbc.gridy = 2;
        this.gbc.insets = new Insets(0, 10, 2, 10);
        this.gbl.setConstraints(this.add(button2), this.gbc);
        this.gbc.gridx = 1;
        this.gbc.gridy = 0;
        this.gbc.insets = new Insets(2, 10, 2, 10);
        this.gbl.setConstraints(this.add(ViewOptions.showSSErr), this.gbc);
        this.gbc.gridy = 1;
        this.gbl.setConstraints(this.add(ViewOptions.showSSPred), this.gbc);
        this.gbc.gridy = 2;
        this.gbl.setConstraints(this.add(ViewOptions.showSSTot), this.gbc);
        this.gbc.gridx = 2;
        this.gbc.gridy = 0;
        this.gbl.setConstraints(this.add(ViewOptions.showRegLine), this.gbc);
        this.gbc.gridy = 1;
        this.gbl.setConstraints(this.add(ViewOptions.showYbar), this.gbc);
        this.gbc.gridy = 2;
        this.gbl.setConstraints(this.add(ViewOptions.showAsSquaredError), this.gbc);
        this.gbc.gridx = 3;
        this.gbc.gridy = 0;
        this.gbc.insets = new Insets(2, 0, 0, 0);
        this.gbl.setConstraints(this.add(this.moduleLabel), this.gbc);
        this.gbc.gridy = 1;
        this.gbc.insets = new Insets(0, 0, 0, 20);
        this.gbl.setConstraints(this.add(ViewOptions.panelToShow), this.gbc);
        this.gbc.gridy = 2;
        this.gbc.insets = new Insets(0, 0, 2, 20);
        this.gbl.setConstraints(this.add(ViewOptions.aboutWISE), this.gbc);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001 && event.target.equals(ViewOptions.showSSErr)) {
            Cor_app.datav.getIDV().setDrawSSErr(ViewOptions.showSSErr.getState());
        }
        else if (event.id == 1001 && event.target.equals(ViewOptions.showSSPred)) {
            Cor_app.datav.getIDV().setDrawSSpred(ViewOptions.showSSPred.getState());
        }
        else if (event.id == 1001 && event.target.equals(ViewOptions.showSSTot)) {
            Cor_app.datav.getIDV().setDrawSStot(ViewOptions.showSSTot.getState());
        }
        else if (event.id == 1001 && event.target.equals(ViewOptions.showRegLine)) {
            Cor_app.datav.getIDV().setDrawRegLine(ViewOptions.showRegLine.getState());
        }
        else if (event.id == 1001 && event.target.equals(ViewOptions.showYbar)) {
            Cor_app.datav.getIDV().setDrawYbar(ViewOptions.showYbar.getState());
        }
        else if (event.id == 1001 && event.target.equals(ViewOptions.showAsSquaredError)) {
            Cor_app.datav.getIDV().setDrawAsSquaredError(ViewOptions.showAsSquaredError.getState());
            Cor_app.update();
        }
        else if ((event.id != 1001 || !event.target.equals(ViewOptions.showDE)) && event.id == 1001 && event.target.equals(ViewOptions.aboutWISE)) {
            this.getPanels();
            this.setPanels(2);
        }
        return super.handleEvent(event);
    }
    
    public void update() {
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
    }
    
    public void enableSSErr(final boolean enabled) {
        ViewOptions.showSSErr.setEnabled(enabled);
    }
    
    public void enableSSPred(final boolean enabled) {
        ViewOptions.showSSPred.setEnabled(enabled);
    }
    
    public void enableSSTot(final boolean enabled) {
        ViewOptions.showSSTot.setEnabled(enabled);
    }
    
    public void enableYbar(final boolean enabled) {
        ViewOptions.showYbar.setEnabled(enabled);
    }
    
    public void enableRegLine(final boolean enabled) {
        ViewOptions.showRegLine.setEnabled(enabled);
    }
    
    public void enableAsSquaredError(final boolean enabled) {
        ViewOptions.showAsSquaredError.setEnabled(enabled);
    }
    
    public void enableRegressResid(final boolean b) {
        ViewOptions.showRegress.setEnabled(b);
        ViewOptions.showResid.setEnabled(b);
    }
    
    public boolean getShowRegLine() {
        return ViewOptions.showRegLine.getState();
    }
    
    public int getHeight() {
        return this.getSize().height;
    }
    
    public int getWidth() {
        return this.getSize().width;
    }
    
    public void setPanels(final int panels) {
        ViewOptions.ca.setPanels(panels);
    }
    
    public int getPanels() {
        return ViewOptions.ca.getPanels();
    }
    
    public int getCurrentChoice() {
        return ViewOptions.panelToShow.getSelectedIndex();
    }
}
