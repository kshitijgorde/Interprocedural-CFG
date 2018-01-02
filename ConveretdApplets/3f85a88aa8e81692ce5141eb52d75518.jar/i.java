import java.awt.Dimension;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class i extends Dialog
{
    public Button a;
    public static Frame b;
    
    public i(final Frame frame, final String s) {
        super(frame, true);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.a = new Button(zkmToString("0\u001b"));
        this.setLayout(layout);
        int n = 0;
        int i;
        do {
            i = s.indexOf("\r", n);
            String s2;
            if (i >= 0) {
                s2 = s.substring(n, i);
                n = i + 1;
            }
            else {
                s2 = s.substring(n);
            }
            final Label label = new Label(zkmToString("_pC") + s2);
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridx = 20;
            gridBagConstraints.ipadx = 5;
            gridBagConstraints.ipady = 0;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            gridBagConstraints.gridwidth = 0;
            if (i >= 0) {
                gridBagConstraints.gridheight = 11;
            }
            else {
                gridBagConstraints.gridheight = -1;
            }
            layout.setConstraints(label, gridBagConstraints);
            this.add(label);
        } while (i >= 0);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 20;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.insets = new Insets(5, 0, 10, 0);
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        this.pack();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.a) {
            this.hide();
            if (i.b != null) {
                i.b.hide();
            }
        }
        return true;
    }
    
    public static void a(final String s) {
        if (i.b == null) {
            i.b = new Frame(zkmToString(";9\u0002ow\u0018"));
        }
        final i i = new i(i.b, s);
        i.b.resize(i.size().width, i.size().height);
        final Dimension size = i.getSize();
        final Dimension screenSize = i.getToolkit().getScreenSize();
        i.setLocation(Math.round((screenSize.width - size.width) / 2), Math.round((screenSize.height - size.height) / 2));
        i.pack();
        i.toFront();
        i.show();
    }
    
    private static String zkmToString(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '\u007f';
                    break;
                }
                case 1: {
                    c2 = 'P';
                    break;
                }
                case 2: {
                    c2 = 'c';
                    break;
                }
                case 3: {
                    c2 = '\u0003';
                    break;
                }
                default: {
                    c2 = '\u0018';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
