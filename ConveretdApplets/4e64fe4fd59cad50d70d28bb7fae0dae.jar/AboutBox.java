import java.awt.Event;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class AboutBox extends Frame
{
    final int m_nBoxWidth = 700;
    final int m_nBoxHeight = 500;
    final String abouttext;
    
    public AboutBox(final String title) {
        super("AboutBox");
        this.abouttext = "3D Text Applet v1.05\n\n" + "Copyright (c) Jari Lehtonen 1997, 1998. Freeware.\n" + "http://www.geocities.com/SiliconValley/Bay/5983/index.html\n\n" + "Applet parameters and example values:\n" + "// General parameters\n" + "<param name=color                   value=\"Green\"> // Initial color of texts\n" + "<param name=control_panel_available value=\"true\">  // Is control panel available?\n" + "<param name=startstop_button        value=\"true\">  // Are Start/Stop buttons available in control panel?\n" + "<param name=fps_button              value=\"true\">  // Are FPS buttons available in control panel?\n" + "<param name=color_button            value=\"true\">  // Is Color button available in control panel?\n" + "<param name=hide_button             value=\"true\">  // Is hide button available in control panel?\n" + "<param name=number_of_texts         value=\"1\">     // Number of texts\n\n" + "// These parameters are declared for each text\n" + "<param name=text_0                value=\"+string one\"> // Text to show (chars A-Z and 0-9 accepted)\n" + "<param name=text_0_zoominspeed    value=\"0.01\">        // Speed to zoom in the text at start\n" + "<param name=text_0_destinationz   value=\"3\">           // How close to zoom the text\n" + "<param name=text_0_zoomoutspeed   value=\"-0.02\">       // Speed to zoom out the text at end\n" + "<param name=text_0_timetodiez     value=\"0\">           // When to show the next text when zooming out\n" + "<param name=text_0_delay          value=\"100\">         // How long to show thist text (0 = forever)\n" + "<param name=text_0_spin           value=\"1\">           // How fast to rotate around y-axis\n" + "<param name=text_0_tip            value=\"1\">           // How fast to rotate around x-axis\n";
        final TextArea textArea = new TextArea(this.abouttext);
        textArea.setEditable(false);
        textArea.setFont(new Font("Courier", 0, 12));
        this.add("Center", textArea);
        this.pack();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.reshape(screenSize.width / 2 - 350, screenSize.height / 2 - 250, 700, 500);
        this.setTitle(title);
        this.show();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            return true;
        }
        return false;
    }
}
