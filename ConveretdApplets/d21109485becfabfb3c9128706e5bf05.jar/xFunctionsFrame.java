import java.awt.Event;
import java.awt.Component;
import java.applet.Applet;
import xfunctions.xFunctionsPanel;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class xFunctionsFrame extends Frame
{
    private xFunctionsPanel xFunctions;
    private xFunctionsLauncher owner;
    
    public static void main(final String[] array) {
        System.out.println("xFunctions, version of 27 October 1999\nDavid Eck, eck@hws.edu, http://math.hws.edu/eck/");
        if (array.length >= 1) {
            new xFunctionsFrame(array[0], (xFunctionsLauncher)null);
        }
        else {
            new xFunctionsFrame(null, (xFunctionsLauncher)null);
        }
    }
    
    public xFunctionsFrame(final String s, final xFunctionsLauncher owner) {
        super("xFunctions");
        this.owner = owner;
        this.add("Center", this.xFunctions = new xFunctionsPanel(s, owner));
        this.reshape(20, 30, 580, 440);
        this.xFunctions.start();
        this.show();
        if (owner != null) {
            owner.frameHasOpened();
        }
    }
    
    void close() {
        this.xFunctions.stop();
        this.dispose();
        if (this.owner == null) {
            System.exit(0);
        }
        else {
            this.owner.frameHasClosed();
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.close();
            return true;
        }
        return super.handleEvent(event);
    }
}
