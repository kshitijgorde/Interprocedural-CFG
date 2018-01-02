// 
// Decompiled by Procyon v0.5.30
// 

package medusa.example;

import medusa.display.BasicGraphPanel;
import medusa.MedusaSettings;
import medusa.applet.MedusaLite;

public class ExampleApplet extends MedusaLite
{
    @Override
    public void initPanel() {
        System.out.println("Initializing ExampleAppletPanel");
        final MedusaSettings medusaSettings = new MedusaSettings(this.getParameter("settings"));
        final String parameter = this.getParameter("linkStart");
        this.getParameter("linkEnd");
        System.out.println(parameter);
        this.setPanel(new ExamplePanel(medusaSettings));
        System.out.println(this.panel);
    }
}
