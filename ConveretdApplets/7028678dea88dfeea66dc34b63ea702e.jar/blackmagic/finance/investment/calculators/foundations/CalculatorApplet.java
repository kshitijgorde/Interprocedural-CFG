// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.foundations;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JApplet;

public abstract class CalculatorApplet extends JApplet
{
    public void init() {
        this.getContentPane().add(this.getButton(), "Center");
    }
    
    public String getAppletInfo() {
        return "A calculator applet.";
    }
    
    private JButton getButton() {
        final JButton button = new JButton(this.getButtonString());
        final CachedActionListenerThread cachedActionListenerThread = new CachedActionListenerThread();
        cachedActionListenerThread.run();
        final CachedActionListener listener = cachedActionListenerThread.getListener();
        listener.setFactoryType(this.getCalculatorClass());
        button.setForeground(this.getForegroundParameter());
        button.setBackground(this.getBackgroundParameter());
        button.addActionListener(listener);
        return button;
    }
    
    private Color getForegroundParameter() {
        return this.getParameterColor("foreground", this.getForeground());
    }
    
    private Color getBackgroundParameter() {
        return this.getParameterColor("background", this.getBackground());
    }
    
    private Color getParameterColor(final String s, final Color color) {
        final String string = "0x" + this.getParameter(s);
        Color decode;
        try {
            decode = Color.decode(string);
        }
        catch (NumberFormatException ex) {
            decode = color;
        }
        return decode;
    }
    
    protected abstract String getButtonString();
    
    protected abstract Class getCalculatorClass();
}
