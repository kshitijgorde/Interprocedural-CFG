// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;
import edu.wise.exceptions.OutOfRangeException;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import edu.wise.graph.StyleSheet;
import edu.wise.utils.FormatUtils;
import java.awt.LayoutManager;
import java.awt.Dimension;
import edu.wise.stats.distributions.Distribution;

public class ControlPanelHF extends ControlPanel
{
    private int rowHt;
    private int xoff;
    private int yoff;
    private int cellw;
    private int fy;
    
    public ControlPanelHF(final Distribution noiseDist, final Distribution signalDist, final UtilApplet utilApplet) {
        this.rowHt = 20;
        this.xoff = 5;
        this.yoff = 0;
        this.cellw = 55;
        this.fy = 0;
        this.setSize(new Dimension(utilApplet.getSize().width, 50));
        ControlPanel.noiseDist = noiseDist;
        ControlPanel.signalDist = signalDist;
        super.utilApplet = utilApplet;
        this.setLayout(null);
        try {
            this.initComponents();
        }
        catch (Exception ex) {
            System.err.println("ControlPanelHF:initComponents. " + ex);
        }
        this.update();
    }
    
    public void update() {
        super.G1MeanTf.setText(FormatUtils.rounder_str(super.utilApplet.getUtil().getM1(), 2));
        super.G1SdTf.setText(FormatUtils.rounder_str(super.utilApplet.getUtil().getS1(), 2));
        super.G1NTf.setText(FormatUtils.rounder_str(super.utilApplet.getUtil().getn1(), 0));
        super.G2MeanTf.setText(FormatUtils.rounder_str(super.utilApplet.getUtil().getM2(), 2));
        super.G2SdTf.setText(FormatUtils.rounder_str(super.utilApplet.getUtil().getS2(), 2));
        super.G2NTf.setText(FormatUtils.rounder_str(super.utilApplet.getUtil().getn2(), 0));
        super.U11Tf.setText(FormatUtils.rounder_str(super.utilApplet.getUtil().getu11(), 2));
        super.U12Tf.setText(FormatUtils.rounder_str(super.utilApplet.getUtil().getu12(), 2));
        super.U21Tf.setText(FormatUtils.rounder_str(super.utilApplet.getUtil().getu21(), 2));
        super.U22Tf.setText(FormatUtils.rounder_str(super.utilApplet.getUtil().getu22(), 2));
        switch (super.utilApplet.getUtil().getState()) {
            case -2: {
                super.decRule2 = "Utility for a correct classification of an actual case must be";
                super.decRule3 = "larger than utility of an incorrect classification of that case.";
                break;
            }
            case -1: {
                super.decRule2 = "Classification is arbitrary.";
                super.decRule3 = "Weighted distributions are identical.";
                break;
            }
            case 0: {
                super.decRule2 = "There is no cut point.";
                super.decRule3 = "Classify all cases as Group " + super.utilApplet.getUtil().getGroup() + ".";
                break;
            }
            case 1: {
                super.decRule2 = "There is one cut point: " + FormatUtils.rounder_str(super.utilApplet.getUtil().getC(), 2, false);
                super.decRule3 = "Classify cases less than this point as Group " + super.utilApplet.getUtil().getGroup() + ".";
                break;
            }
            case 2: {
                super.decRule2 = "There are two cut points: " + FormatUtils.rounder_str(super.utilApplet.getUtil().getcl(), 2, false) + " and " + FormatUtils.rounder_str(super.utilApplet.getUtil().getcu(), 2, false);
                super.decRule3 = "Classify cases between the cut points as Group " + super.utilApplet.getUtil().getGroup() + ".";
                break;
            }
            default: {
                super.decRule2 = "ControlPanelHF switch default reached";
                super.decRule3 = "Internal error: report to Michael.Healy@cgu.edu";
                break;
            }
        }
        this.repaint();
    }
    
    public void initComponents() throws Exception {
        super.G1MeanTf.setSize(this.cellw, this.rowHt);
        super.G1MeanTf.setFont(StyleSheet.REGULAR_FONT);
        super.G1MeanTf.setLocation(this.xoff + this.cellw * 2 + 5, this.yoff + this.rowHt * 2);
        this.add(super.G1MeanTf);
        super.G1SdTf.setSize(this.cellw, this.rowHt);
        super.G1SdTf.setFont(StyleSheet.REGULAR_FONT);
        super.G1SdTf.setLocation(this.xoff + this.cellw * 3 + 5, this.yoff + this.rowHt * 2);
        this.add(super.G1SdTf);
        super.G1NTf.setSize(this.cellw, this.rowHt);
        super.G1NTf.setFont(StyleSheet.REGULAR_FONT);
        super.G1NTf.setLocation(this.xoff + this.cellw * 4 + 5, this.yoff + this.rowHt * 2);
        this.add(super.G1NTf);
        super.G2MeanTf.setSize(this.cellw, this.rowHt);
        super.G2MeanTf.setFont(StyleSheet.REGULAR_FONT);
        super.G2MeanTf.setLocation(this.xoff + this.cellw * 2 + 5, this.yoff + this.rowHt * 3);
        this.add(super.G2MeanTf);
        super.G2SdTf.setSize(this.cellw, this.rowHt);
        super.G2SdTf.setFont(StyleSheet.REGULAR_FONT);
        super.G2SdTf.setLocation(this.xoff + this.cellw * 3 + 5, this.yoff + this.rowHt * 3);
        this.add(super.G2SdTf);
        super.G2NTf.setSize(this.cellw, this.rowHt);
        super.G2NTf.setFont(StyleSheet.REGULAR_FONT);
        super.G2NTf.setLocation(this.xoff + this.cellw * 4 + 5, this.yoff + this.rowHt * 3);
        this.add(super.G2NTf);
        super.U11Tf.setBackground(StyleSheet.TURQUOISE);
        super.U11Tf.setFont(StyleSheet.REGULAR_FONT);
        super.U11Tf.setSize(this.cellw, this.rowHt);
        super.U11Tf.setLocation(this.xoff + this.cellw * 5 + 5, this.yoff + this.rowHt * 2);
        this.add(super.U11Tf);
        super.U21Tf.setBackground(StyleSheet.TURQUOISE);
        super.U21Tf.setFont(StyleSheet.REGULAR_FONT);
        super.U21Tf.setSize(this.cellw, this.rowHt);
        super.U21Tf.setLocation(this.xoff + this.cellw * 6 + 5, this.yoff + this.rowHt * 2);
        this.add(super.U21Tf);
        super.U12Tf.setBackground(StyleSheet.TURQUOISE);
        super.U12Tf.setFont(StyleSheet.REGULAR_FONT);
        super.U12Tf.setSize(this.cellw, this.rowHt);
        super.U12Tf.setLocation(this.xoff + this.cellw * 5 + 5, this.yoff + this.rowHt * 3);
        this.add(super.U12Tf);
        super.U22Tf.setBackground(StyleSheet.TURQUOISE);
        super.U22Tf.setFont(StyleSheet.REGULAR_FONT);
        super.U22Tf.setSize(this.cellw, this.rowHt);
        super.U22Tf.setLocation(this.xoff + this.cellw * 6 + 5, this.yoff + this.rowHt * 3);
        this.add(super.U22Tf);
        super.setButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (actionEvent.getSource() instanceof Button) {
                    try {
                        ControlPanelHF.this.utilApplet.getUtil().setM1(Double.valueOf(ControlPanelHF.this.G1MeanTf.getText()));
                        ControlPanelHF.this.utilApplet.getUtil().setM2(Double.valueOf(ControlPanelHF.this.G2MeanTf.getText()));
                        ControlPanelHF.this.utilApplet.getUtil().setS1(Double.valueOf(ControlPanelHF.this.G1SdTf.getText()));
                        ControlPanelHF.this.utilApplet.getUtil().setS2(Double.valueOf(ControlPanelHF.this.G2SdTf.getText()));
                        ControlPanelHF.this.utilApplet.getUtil().setN1((int)(double)Double.valueOf(ControlPanelHF.this.G1NTf.getText()));
                        ControlPanelHF.this.utilApplet.getUtil().setN2((int)(double)Double.valueOf(ControlPanelHF.this.G2NTf.getText()));
                        ControlPanelHF.this.utilApplet.getUtil().setu11(Double.valueOf(ControlPanelHF.this.U11Tf.getText()));
                        ControlPanelHF.this.utilApplet.getUtil().setu12(Double.valueOf(ControlPanelHF.this.U12Tf.getText()));
                        ControlPanelHF.this.utilApplet.getUtil().setu21(Double.valueOf(ControlPanelHF.this.U21Tf.getText()));
                        ControlPanelHF.this.utilApplet.getUtil().setu22(Double.valueOf(ControlPanelHF.this.U22Tf.getText()));
                        ControlPanelHF.this.utilApplet.update();
                    }
                    catch (OutOfRangeException ex) {
                        System.out.println("An error occured in setButton");
                    }
                }
            }
        });
        super.helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (actionEvent.getSource() instanceof Button) {
                    try {
                        System.out.println("None available...HA!");
                    }
                    catch (Exception ex) {
                        System.out.println("An error occured in setButton");
                    }
                }
            }
        });
        super.G1MeanTf.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (actionEvent.getSource() instanceof TextField) {
                    try {
                        ControlPanelHF.this.utilApplet.getUtil().setM1(Double.valueOf(((TextField)actionEvent.getSource()).getText()));
                        ControlPanelHF.this.utilApplet.update();
                    }
                    catch (Exception ex) {
                        ControlPanelHF.this.G1MeanTf.setText(FormatUtils.rounder_str(ControlPanelHF.this.utilApplet.getUtil().getM1(), 2));
                    }
                }
            }
        });
        super.G2MeanTf.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (actionEvent.getSource() instanceof TextField) {
                    try {
                        ControlPanelHF.this.utilApplet.getUtil().setM2(Double.valueOf(((TextField)actionEvent.getSource()).getText()));
                        ControlPanelHF.this.utilApplet.update();
                    }
                    catch (Exception ex) {
                        ControlPanelHF.this.G2MeanTf.setText(FormatUtils.rounder_str(ControlPanelHF.this.utilApplet.getUtil().getM2(), 2));
                    }
                }
            }
        });
        super.G1SdTf.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (actionEvent.getSource() instanceof TextField) {
                    try {
                        ControlPanelHF.this.utilApplet.getUtil().setS1(Double.valueOf(((TextField)actionEvent.getSource()).getText()));
                        ControlPanelHF.this.utilApplet.update();
                    }
                    catch (Exception ex) {
                        ControlPanelHF.this.G1SdTf.setText(FormatUtils.rounder_str(ControlPanelHF.this.utilApplet.getUtil().getS1(), 2));
                    }
                }
            }
        });
        super.G2SdTf.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (actionEvent.getSource() instanceof TextField) {
                    try {
                        ControlPanelHF.this.utilApplet.getUtil().setS2(Double.valueOf(((TextField)actionEvent.getSource()).getText()));
                        ControlPanelHF.this.utilApplet.update();
                    }
                    catch (Exception ex) {
                        System.out.println("G2sd errror");
                        ControlPanelHF.this.G2SdTf.setText(FormatUtils.rounder_str(ControlPanelHF.this.utilApplet.getUtil().getS2(), 2));
                    }
                }
            }
        });
        super.G1NTf.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (actionEvent.getSource() instanceof TextField) {
                    try {
                        ControlPanelHF.this.utilApplet.getUtil().setN1((int)(double)Double.valueOf(((TextField)actionEvent.getSource()).getText()));
                        ControlPanelHF.this.utilApplet.update();
                    }
                    catch (Exception ex) {
                        ControlPanelHF.this.G1NTf.setText(FormatUtils.rounder_str(ControlPanelHF.this.utilApplet.getUtil().getn1(), 0));
                    }
                }
            }
        });
        super.G2NTf.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (actionEvent.getSource() instanceof TextField) {
                    try {
                        ControlPanelHF.this.utilApplet.getUtil().setN2((int)(double)Double.valueOf(((TextField)actionEvent.getSource()).getText()));
                        ControlPanelHF.this.utilApplet.update();
                    }
                    catch (Exception ex) {
                        ControlPanelHF.this.G2NTf.setText(FormatUtils.rounder_str(ControlPanelHF.this.utilApplet.getUtil().getn2(), 0));
                    }
                }
            }
        });
        super.U11Tf.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (actionEvent.getSource() instanceof TextField) {
                    try {
                        ControlPanelHF.this.utilApplet.getUtil().setu11(Double.valueOf(((TextField)actionEvent.getSource()).getText()));
                        ControlPanelHF.this.utilApplet.update();
                    }
                    catch (Exception ex) {
                        ControlPanelHF.this.U11Tf.setText(FormatUtils.rounder_str(ControlPanelHF.this.utilApplet.getUtil().getu11(), 2));
                    }
                }
            }
        });
        super.U12Tf.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (actionEvent.getSource() instanceof TextField) {
                    try {
                        ControlPanelHF.this.utilApplet.getUtil().setu12(Double.valueOf(((TextField)actionEvent.getSource()).getText()));
                        ControlPanelHF.this.utilApplet.update();
                    }
                    catch (Exception ex) {
                        ControlPanelHF.this.U12Tf.setText(FormatUtils.rounder_str(ControlPanelHF.this.utilApplet.getUtil().getu12(), 2));
                    }
                }
            }
        });
        super.U21Tf.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (actionEvent.getSource() instanceof TextField) {
                    try {
                        ControlPanelHF.this.utilApplet.getUtil().setu21(Double.valueOf(((TextField)actionEvent.getSource()).getText()));
                        ControlPanelHF.this.utilApplet.update();
                    }
                    catch (Exception ex) {
                        ControlPanelHF.this.U21Tf.setText(FormatUtils.rounder_str(ControlPanelHF.this.utilApplet.getUtil().getu21(), 2));
                    }
                }
            }
        });
        super.U22Tf.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (actionEvent.getSource() instanceof TextField) {
                    try {
                        ControlPanelHF.this.utilApplet.getUtil().setu22(Double.valueOf(((TextField)actionEvent.getSource()).getText()));
                        ControlPanelHF.this.utilApplet.update();
                    }
                    catch (Exception ex) {
                        ControlPanelHF.this.U22Tf.setText(FormatUtils.rounder_str(ControlPanelHF.this.utilApplet.getUtil().getu22(), 2));
                    }
                }
            }
        });
    }
    
    private int centerFont(final Graphics graphics, final String s) {
        return this.centerFont(graphics, s, 1);
    }
    
    private int centerFont(final Graphics graphics, final String s, final int n) {
        final int n2 = this.cellw * n - Math.round(graphics.getFontMetrics().stringWidth(s));
        if (n2 < 1) {
            return 0;
        }
        return Math.round(n2 / 2);
    }
    
    private void setFont(final Graphics graphics, final Font font) {
        graphics.setFont(font);
        this.fy = -5;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawRect(0, -1, this.getSize().width, this.getSize().height - 2);
        this.setFont(graphics, StyleSheet.REGULAR_FONT_BOLD);
        graphics.drawString("Utility Values", this.xoff + this.cellw * 5 + 10, this.fy + this.yoff + this.rowHt);
        graphics.drawString("Mean", this.xoff + this.cellw * 2 + this.centerFont(graphics, "Mean"), this.fy + this.yoff + this.rowHt * 2);
        graphics.drawString("SD", this.xoff + this.cellw * 3 + this.centerFont(graphics, "SD"), this.fy + this.yoff + this.rowHt * 2);
        graphics.drawString("Base Rate", this.xoff + this.cellw * 4 + this.centerFont(graphics, "Base Rate"), this.fy + this.yoff + this.rowHt * 2);
        graphics.drawString("Group 1", this.xoff + this.cellw * 5 + this.centerFont(graphics, "Group 1"), this.fy + this.yoff + this.rowHt * 2);
        graphics.drawString("Group 2", this.xoff + this.cellw * 6 + this.centerFont(graphics, "Group 2"), this.fy + this.yoff + this.rowHt * 2);
        graphics.setColor(Color.blue);
        graphics.drawString("Actual Group 1", this.xoff + this.cellw * 0 + this.centerFont(graphics, "Actual Group 1"), this.fy + this.yoff + this.rowHt * 3);
        graphics.setColor(Color.red);
        graphics.drawString("Actual Group 2", this.xoff + this.cellw * 0 + this.centerFont(graphics, "Actual Group 2"), this.fy + this.yoff + this.rowHt * 4);
        graphics.setColor(StyleSheet.YELLOW);
        graphics.fillRect(0, 3 + this.yoff + this.rowHt * 4, this.getSize().width, this.rowHt * 3 + 6);
        graphics.setColor(Color.black);
        graphics.drawRect(0, 3 + this.yoff + this.rowHt * 4, this.getSize().width, this.rowHt * 3 + 6);
        graphics.drawString("Decision Rule:", this.xoff, this.fy + this.yoff + this.rowHt * 5);
        graphics.drawString("     " + super.decRule2, this.xoff, this.fy + this.yoff + this.rowHt * 6);
        graphics.drawString("     " + super.decRule3, this.xoff, this.fy + this.yoff + this.rowHt * 7);
    }
}
