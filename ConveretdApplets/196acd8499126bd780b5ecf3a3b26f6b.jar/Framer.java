import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Button;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Framer extends Applet implements ActionListener
{
    private static Choice links;
    private static Choice names;
    private static Choice descriptions;
    private static int currentDes;
    private static TextField display;
    private static boolean credit;
    
    public void init() {
        Framer.currentDes = 0;
        this.setLayout(new BorderLayout());
        int n = 1;
        Integer.parseInt(this.getParameter("height"));
        Integer.parseInt(this.getParameter("width"));
        final int int1 = Integer.parseInt(this.getParameter("gridHeight"));
        final int int2 = Integer.parseInt(this.getParameter("gridWidth"));
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(int1, int2));
        (Framer.display = new TextField("")).setEditable(false);
        this.add(Framer.display, "North");
        this.add(panel, "Center");
        if (!this.getParameter("author").equals("Jake Holmes")) {
            Framer.credit = false;
        }
        if (!this.getParameter("authorURL").equals("http://home.att.net/~blade25/")) {
            Framer.credit = false;
        }
        if (Framer.credit) {
            String parameter;
            while ((parameter = this.getParameter("link_" + n)) != null) {
                Framer.links.add(parameter);
                ++n;
            }
            String parameter2;
            for (int n2 = 1; (parameter2 = this.getParameter("description_" + n2)) != null; ++n2) {
                Framer.descriptions.add(parameter2);
            }
            String parameter3;
            for (int n3 = 1; (parameter3 = this.getParameter("linkName_" + n3)) != null; ++n3) {
                this.addButton(panel, parameter3);
                Framer.names.add(parameter3);
            }
        }
        if (!Framer.credit) {
            Framer.display.setText("Give the author, Jake Holmes, credit for this applet!!!");
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        String actionCommand;
        int currentDes;
        for (actionCommand = actionEvent.getActionCommand(), currentDes = 0; !actionCommand.equals(Framer.names.getItem(currentDes)); ++currentDes) {}
        final String item = Framer.links.getItem(currentDes);
        Framer.display.setText(Framer.descriptions.getItem(currentDes));
        if (!Framer.credit) {
            Framer.display.setText("Give the author, Jake Holmes, credit for this applet!!!");
        }
        Framer.currentDes = currentDes;
        try {
            this.getAppletContext().showDocument(new URL(item), "right");
        }
        catch (Exception ex) {
            this.showStatus("Error " + ex);
        }
    }
    
    public void addButton(final Container container, final String s) {
        final Button button = new Button(s);
        container.add(button);
        button.addActionListener(this);
        button.addMouseListener(new ButtonListener());
    }
    
    public static void mouseEntered(final MouseEvent mouseEvent) {
        String label;
        int n;
        for (label = ((Button)mouseEvent.getComponent()).getLabel(), n = 0; !label.equals(Framer.names.getItem(n)); ++n) {}
        Framer.links.getItem(n);
        Framer.display.setText(Framer.descriptions.getItem(n));
        if (!Framer.credit) {
            Framer.display.setText("Give the author, Jake Holmes, credit for this applet!!!");
        }
    }
    
    public static void mouseExited(final MouseEvent mouseEvent) {
        Framer.display.setText(Framer.descriptions.getItem(Framer.currentDes));
    }
    
    static {
        Framer.links = new Choice();
        Framer.names = new Choice();
        Framer.descriptions = new Choice();
        Framer.credit = true;
    }
}
