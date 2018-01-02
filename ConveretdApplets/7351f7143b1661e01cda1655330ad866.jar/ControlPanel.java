import java.util.StringTokenizer;
import java.awt.Event;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class ControlPanel extends Panel
{
    Panel mandelPanel;
    Panel juliaPanel;
    Button stop_button;
    Button clear_button;
    JuliaCanvas juliaCanvas;
    MandelCanvas mandelCanvas;
    static TextField positionDisplay;
    Thread juliaTextRunner;
    
    ControlPanel(final JuliaCanvas juliaCanvas, final MandelCanvas mandelCanvas) {
        try {
            this.setLayout(new GridLayout(1, 2, 0, 0));
        }
        catch (IllegalArgumentException ex) {}
        this.mandelPanel = new Panel();
        this.juliaPanel = new Panel();
        this.add(this.mandelPanel);
        this.add(this.juliaPanel);
        (ControlPanel.positionDisplay = new TextField(25)).setEditable(true);
        this.mandelPanel.add(ControlPanel.positionDisplay);
        this.stop_button = new Button("Stop");
        this.clear_button = new Button("Clear");
        this.juliaPanel.add(this.stop_button);
        this.juliaPanel.add(this.clear_button);
        this.juliaCanvas = juliaCanvas;
        this.mandelCanvas = mandelCanvas;
        this.setBackground(Color.gray);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.choose((String)o);
        }
        if (event.target instanceof TextField && event.id == 1001) {
            JuliaCanvas.c = this.parseInput(ControlPanel.positionDisplay.getText());
            if (this.juliaTextRunner == null) {
                (this.juliaTextRunner = new Thread(this.juliaCanvas)).setPriority(1);
                this.juliaTextRunner.start();
            }
            if (!this.juliaTextRunner.isAlive()) {
                this.juliaTextRunner.stop();
                (this.juliaTextRunner = new Thread(this.juliaCanvas)).setPriority(1);
                this.juliaTextRunner.start();
            }
        }
        return true;
    }
    
    void choose(final String s) {
        if (s.equals("Stop")) {
            JuliaCanvas.juliaRunner.stop();
            return;
        }
        this.mandelCanvas.update_positionQ = true;
        JuliaCanvas.juliaRunner.stop();
        this.juliaCanvas.clear();
        this.juliaCanvas.repaint();
    }
    
    public static void update_position(final ComplexNumber complexNumber) {
        final String string = new Double(complexNumber.a).toString();
        if (complexNumber.b >= 0.0) {
            ControlPanel.positionDisplay.setText("c = " + string + " + " + new Double(complexNumber.b).toString() + " i");
            return;
        }
        ControlPanel.positionDisplay.setText("c = " + string + " - " + new Double(-complexNumber.b).toString() + " i");
    }
    
    ComplexNumber parseInput(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        final int countTokens = stringTokenizer.countTokens();
        int n = 0;
        double doubleValue = 0.0;
        double doubleValue2 = 0.0;
        if (countTokens != 6) {
            inputError();
        }
        if (!stringTokenizer.nextToken().equalsIgnoreCase("c")) {
            inputError();
        }
        if (!stringTokenizer.nextToken().equals("=")) {
            inputError();
        }
        try {
            doubleValue = new Double(stringTokenizer.nextToken());
        }
        catch (NumberFormatException ex) {
            inputError();
        }
        final String s2 = new String(stringTokenizer.nextToken());
        if (!s2.equals("+") && !s2.equals("-")) {
            if (s2.equals("=")) {
                n = 1;
            }
            else {
                inputError();
            }
        }
        try {
            doubleValue2 = new Double(stringTokenizer.nextToken());
        }
        catch (NumberFormatException ex2) {
            inputError();
        }
        if (s2.equals("-")) {
            doubleValue2 = -doubleValue2;
        }
        if (!stringTokenizer.nextToken().equalsIgnoreCase("i")) {
            inputError();
        }
        if (n == 1) {
            ControlPanel.positionDisplay.setText("c = " + doubleValue + " + " + doubleValue2 + " i");
        }
        return new ComplexNumber(doubleValue, doubleValue2);
    }
    
    static void inputError() {
        ControlPanel.positionDisplay.setText("Input Error");
    }
}
