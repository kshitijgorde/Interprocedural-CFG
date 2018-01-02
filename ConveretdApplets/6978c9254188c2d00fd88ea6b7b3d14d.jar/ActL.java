import netscape.javascript.JSObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class ActL implements ActionListener
{
    private final DropMenuII ParentApp;
    
    public ActL(final DropMenuII parentApp) {
        this.ParentApp = parentApp;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final int int1 = Integer.parseInt(actionEvent.getActionCommand());
        if (this.ParentApp.IsActive[int1]) {
            if (this.ParentApp.ItemsInfo[int1].length() > 0) {
                this.ParentApp.showStatus(this.ParentApp.ItemsInfo[int1]);
            }
            if (this.ParentApp.ItemsLink[int1].length() > 11 && this.ParentApp.ItemsLink[int1].substring(0, 11).toLowerCase().compareTo("javascript:") == 0) {
                JSObject.getWindow(this.ParentApp.a).eval(this.ParentApp.ItemsLink[int1].substring(11));
            }
            else {
                this.ParentApp.a.getAppletContext().showDocument(this.ParentApp.GetURL(this.ParentApp.ItemsLink[int1]), this.ParentApp.ItemsTargets[int1]);
            }
        }
        else if (this.ParentApp.NotActiveInfo.length() > 0) {
            this.ParentApp.showStatus(this.ParentApp.NotActiveInfo);
        }
    }
}
