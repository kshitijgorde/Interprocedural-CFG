// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.correl;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Image;
import edu.wise.correl.gui.ViewOptions;
import edu.wise.correl.gui.CorrelOutput;
import edu.wise.correl.gui.AboutWISEOutput;
import edu.wise.correl.gui.MeanLesson;
import edu.wise.correl.gui.Output;
import java.applet.Applet;

public class Cor_app extends Applet
{
    private static boolean isStandalone;
    static int WIDTH;
    static int HEIGHT;
    static int voHeight;
    static int datavWidth;
    static int datavHeight;
    static int outputWidth;
    static int outputHeight;
    static int activePanel;
    public static CorrelData cd;
    public static Output output;
    public static MeanLesson ml;
    public static AboutWISEOutput awo;
    public static CorrelOutput[] co;
    public static dataView datav;
    public static ViewOptions vo;
    public static int panelToShow;
    public static Image os_image;
    public static Image datav_image;
    public static Image output_image;
    public static Graphics graphics;
    public static Graphics datav_graphics;
    
    static {
        Cor_app.isStandalone = false;
        Cor_app.WIDTH = 595;
        Cor_app.HEIGHT = 355;
        Cor_app.voHeight = 55;
        Cor_app.datavWidth = 324;
        Cor_app.datavHeight = Cor_app.HEIGHT - Cor_app.voHeight;
        Cor_app.outputWidth = Cor_app.WIDTH - Cor_app.datavWidth;
        Cor_app.outputHeight = Cor_app.datavHeight;
        Cor_app.activePanel = 0;
        Cor_app.co = new CorrelOutput[3];
        Cor_app.panelToShow = 0;
    }
    
    public static void main(final String[] array) {
        Cor_app.isStandalone = true;
        final CorrelFrame correlFrame = new CorrelFrame("Regression and ANOVA");
        correlFrame.setSize(Cor_app.WIDTH, Cor_app.HEIGHT);
        correlFrame.show();
    }
    
    public void init() {
        final double[] array = { 1.0, 2.0 };
        final double[] array2 = { 1.0 };
        final double[] array3 = new double[0];
        this.setSize(Cor_app.WIDTH, Cor_app.HEIGHT);
        this.setLayout(null);
        final String[] array4 = { "Var0", "Var1" };
        try {
            final String paramString = this.getParamString("name_of_x");
            final String paramString2 = this.getParamString("name_of_y");
            array4[0] = paramString;
            array4[1] = paramString2;
        }
        catch (Exception ex) {
            System.err.println("got");
            ex.getMessage();
        }
        if (!Cor_app.isStandalone) {
            Cor_app.os_image = this.createImage(270, 270);
            Cor_app.output_image = this.createImage(Cor_app.WIDTH - 200, Cor_app.HEIGHT);
            Cor_app.datav_image = this.createImage(360, Cor_app.HEIGHT);
        }
        if (Cor_app.isStandalone) {
            Cor_app.cd = new CorrelData(2, 10, this);
            Cor_app.datav.setDrawSSErr(true);
            Cor_app.datav.setDrawSSpred(true);
            Cor_app.datav.setDrawSStot(false);
            Cor_app.datav.setDrawRegLine(false);
            Cor_app.datav.setDrawYbar(false);
            Cor_app.datav.setZoom(0.75);
            Cor_app.awo = new AboutWISEOutput(Cor_app.WIDTH - Cor_app.datav.getSize().width, Cor_app.HEIGHT, null, null);
        }
        else if (this.getParameter("X").length() > 0 && this.getParameter("X") != null) {
            double[] arrayParam = new double[0];
            double[] arrayParam2 = new double[0];
            boolean b;
            try {
                arrayParam = this.getArrayParam("X");
                arrayParam2 = this.getArrayParam("Y");
                b = (arrayParam.length == arrayParam2.length);
            }
            catch (NumberFormatException ex2) {
                b = false;
            }
            if (b) {
                Cor_app.cd = new CorrelData(arrayParam, arrayParam2, array4, this);
            }
            if (!b) {
                try {
                    final int paramInt = this.getParamInt("N");
                    if (paramInt > 0) {
                        Cor_app.cd = new CorrelData(2, paramInt, array4, this);
                    }
                }
                catch (NumberFormatException ex3) {
                    Cor_app.cd = new CorrelData(2, 10, array4, this);
                }
            }
        }
        else {
            Cor_app.cd = new CorrelData(2, 10, this);
        }
        (Cor_app.datav = new dataView(this, Cor_app.datavWidth, Cor_app.datavHeight, Cor_app.datav_image, Cor_app.os_image)).setDrawSSErr(this.getParamBool("drawSSErr"));
        Cor_app.datav.setDrawSSpred(this.getParamBool("drawSSpred"));
        Cor_app.datav.setDrawSStot(this.getParamBool("drawSStot"));
        Cor_app.datav.setDrawRegLine(this.getParamBool("drawRegLine"));
        Cor_app.datav.setDrawYbar(this.getParamBool("mean_of_y"));
        Cor_app.datav.setDrawYbar(this.getParamBool("enableGrid"));
        Cor_app.datav.setZoom(this.getParamDouble("zoom"));
        Cor_app.vo = new ViewOptions(Cor_app.WIDTH, Cor_app.voHeight, this);
        try {
            Cor_app.vo.enableRegLine(Boolean.valueOf(this.getParameter("enableRegLine")));
        }
        catch (Exception ex4) {}
        try {
            Cor_app.vo.enableSSPred(Boolean.valueOf(this.getParameter("enableSSpred")));
        }
        catch (Exception ex5) {}
        try {
            Cor_app.vo.enableSSErr(Boolean.valueOf(this.getParameter("enableSSErr")));
        }
        catch (Exception ex6) {}
        try {
            Cor_app.vo.enableSSTot(Boolean.valueOf(this.getParameter("enableSStot")));
        }
        catch (Exception ex7) {}
        try {
            Cor_app.vo.enableYbar(Boolean.valueOf(this.getParameter("enableMean_of_y")));
        }
        catch (Exception ex8) {}
        try {
            Cor_app.vo.enableAsSquaredError(Boolean.valueOf(this.getParameter("enableAsSquaredError")));
        }
        catch (Exception ex9) {}
        try {
            Cor_app.vo.enableRegressResid(Boolean.valueOf(this.getParameter("enableRegressionOrResidualsChoice")));
        }
        catch (Exception ex10) {}
        Cor_app.panelToShow = this.getParamInt("StartUpInterface");
        Cor_app.output = new Output(Cor_app.outputWidth, Cor_app.datavHeight, Cor_app.output_image, this);
        Cor_app.ml = new MeanLesson(Cor_app.outputWidth, Cor_app.datavHeight, Cor_app.output_image, this);
        Cor_app.awo = new AboutWISEOutput(Cor_app.outputWidth, Cor_app.datavHeight, Cor_app.output_image, this);
        Cor_app.co[0] = Cor_app.output;
        Cor_app.co[1] = Cor_app.ml;
        Cor_app.co[2] = Cor_app.awo;
        this.add(Cor_app.datav);
        this.add((Component)Cor_app.co[0]);
        this.add((Component)Cor_app.co[1]);
        this.add((Component)Cor_app.co[2]);
        this.add(Cor_app.vo);
        Cor_app.datav.setBounds(0, 0, Cor_app.datavWidth, Cor_app.datavHeight);
        Cor_app.output.setBounds(Cor_app.datavWidth, 0, Cor_app.outputWidth, Cor_app.outputHeight);
        Cor_app.ml.setBounds(Cor_app.datavWidth, 0, Cor_app.outputWidth, Cor_app.outputHeight);
        Cor_app.awo.setBounds(Cor_app.datavWidth, 0, Cor_app.outputWidth, Cor_app.outputHeight);
        Cor_app.vo.setBounds(0, Cor_app.HEIGHT - Cor_app.voHeight, Cor_app.WIDTH, Cor_app.voHeight);
        this.setPanels(Cor_app.panelToShow);
        this.setPanels(2);
        this.repaint();
    }
    
    public void setPanels(final int n) {
        if (n != 0) {
            ((Output)Cor_app.co[0]).setVisible(false);
        }
        if (n != 1) {
            ((MeanLesson)Cor_app.co[1]).setVisible(false);
        }
        if (n != 2) {
            ((AboutWISEOutput)Cor_app.co[2]).setVisible(false);
        }
        switch (n) {
            case 0: {
                ((Output)Cor_app.co[0]).setVisible(true);
                Cor_app.activePanel = 0;
                ViewOptions.panelToShow.select(Cor_app.activePanel);
                ViewOptions.showRegLine.setLabel("Show Regression Line");
                Cor_app.vo.update();
                CorrelData.setRegType(Cor_app.activePanel);
                break;
            }
            case 1: {
                ((MeanLesson)Cor_app.co[1]).setVisible(true);
                Cor_app.activePanel = 1;
                ViewOptions.panelToShow.select(Cor_app.activePanel);
                ViewOptions.showRegLine.setLabel("Show Alternative Mean Line");
                Cor_app.vo.update();
                CorrelData.setRegType(Cor_app.activePanel);
                break;
            }
            case 2: {
                ((AboutWISEOutput)Cor_app.co[2]).setVisible(true);
                ((AboutWISEOutput)Cor_app.co[2]).lastPanel(Cor_app.activePanel);
                ViewOptions.panelToShow.select(1);
                Cor_app.activePanel = Cor_app.panelToShow;
                break;
            }
            default: {
                ((Output)Cor_app.co[0]).setVisible(true);
                Cor_app.activePanel = 0;
                ViewOptions.showRegLine.setLabel("Show Regression Line");
                ViewOptions.panelToShow.select(Cor_app.activePanel);
                Cor_app.vo.repaint();
                CorrelData.setRegType(Cor_app.activePanel);
                System.err.println("An error occured while choosing interface.  Defaulted to regression");
                break;
            }
        }
        this.validate();
        update();
    }
    
    public static void update() {
        if (Cor_app.activePanel == 0) {
            ((Output)Cor_app.co[0]).update();
            ((Output)Cor_app.co[0]).repaint();
        }
        else if (Cor_app.activePanel == 1) {
            ((MeanLesson)Cor_app.co[1]).update();
            ((MeanLesson)Cor_app.co[1]).repaint();
        }
        else if (Cor_app.activePanel == 2) {
            ((AboutWISEOutput)Cor_app.co[2]).update();
            ((AboutWISEOutput)Cor_app.co[2]).repaint();
        }
        Cor_app.datav.update();
    }
    
    public int getPanels() {
        return Cor_app.activePanel;
    }
    
    public boolean getParamBool(final String s) {
        try {
            return Boolean.valueOf(this.getParameter(s));
        }
        catch (Exception ex) {
            System.err.println("Cor_app.getParamBool(" + s + ") read error.  Returning null");
            return false;
        }
    }
    
    public double getParamDouble(final String s) {
        try {
            return Double.valueOf(this.getParameter(s));
        }
        catch (NumberFormatException ex) {
            ex.toString();
            return -9.0;
        }
    }
    
    public int getParamInt(final String s) {
        try {
            return Integer.parseInt(this.getParameter(s));
        }
        catch (NumberFormatException ex) {
            System.err.println("Cor_app.getParamInt(" + s + ") read error.  Returning null");
            throw ex;
        }
    }
    
    public double[] getArrayParam(final String s) {
        final double[] array = new double[100];
        final String value = String.valueOf(this.getParameter(s));
        int n = 0;
        int n2 = 1;
        int i = 1;
        try {
            while (i < value.length()) {
                Label_0129: {
                    if (value.charAt(i) != ',') {
                        if (value.charAt(i) != '}') {
                            break Label_0129;
                        }
                    }
                    try {
                        array[n++] = Double.valueOf(value.substring(n2, i));
                        n2 = ++i;
                    }
                    catch (NullPointerException ex2) {
                        System.out.println(" getArrayParam failed to read (front, end, count): " + n2 + ", " + i + ", " + n);
                    }
                }
                ++i;
            }
        }
        catch (NumberFormatException ex) {
            throw ex;
        }
        final double[] array2 = new double[n];
        for (int j = 0; j < array2.length; ++j) {
            array2[j] = array[j];
        }
        return array2;
    }
    
    public String getParamString(final String s) {
        try {
            return String.valueOf(this.getParameter(s));
        }
        catch (Exception ex) {
            System.out.println("string read fail");
            return "var";
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, this.getSize().width, this.getSize().height - 1);
    }
}
