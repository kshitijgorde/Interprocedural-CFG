import java.awt.Rectangle;
import netscape.javascript.JSObject;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class ActL3 implements ActionListener
{
    private final int inp1;
    private final int inp2;
    private final DropMenuII ParentApp;
    
    public ActL3(final DropMenuII parentApp, final int inp1, final int inp2) {
        this.inp1 = inp1;
        this.inp2 = inp2;
        this.ParentApp = parentApp;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.ParentApp.IsActive[this.inp2]) {
            if (this.ParentApp.ItemsInfo[this.inp2].length() > 0) {
                this.ParentApp.showStatus(this.ParentApp.ItemsInfo[this.inp2]);
            }
            else if (this.ParentApp.ItemsLink[this.inp2].length() > 0) {
                this.ParentApp.showStatus(this.ParentApp.ItemsLink[this.inp2]);
            }
            if (actionEvent.getID() == 501) {
                if (actionEvent.getActionCommand() == "menu") {
                    final Rectangle bounds = this.ParentApp.m_MenuItems[this.inp1].getBounds();
                    switch (this.ParentApp.PWay) {
                        case 0: {
                            this.ParentApp.m[this.inp1].show(this.ParentApp.a, bounds.x - bounds.width, bounds.y);
                            break;
                        }
                        case 1: {
                            this.ParentApp.m[this.inp1].show(this.ParentApp.a, bounds.x, bounds.y - bounds.height);
                            break;
                        }
                        case 2: {
                            this.ParentApp.m[this.inp1].show(this.ParentApp.a, bounds.x + bounds.width, bounds.y);
                            break;
                        }
                        default: {
                            this.ParentApp.m[this.inp1].show(this.ParentApp.a, bounds.x, bounds.y + bounds.height);
                            break;
                        }
                    }
                }
                else if (this.ParentApp.ItemsLink[this.inp2].length() > 0) {
                    if (this.ParentApp.ItemsLink[this.inp2].length() > 11 && this.ParentApp.ItemsLink[this.inp2].substring(0, 11).toLowerCase().compareTo("javascript:") == 0) {
                        JSObject.getWindow(this.ParentApp.a).eval(this.ParentApp.ItemsLink[this.inp2].substring(11));
                    }
                    else {
                        this.ParentApp.a.getAppletContext().showDocument(this.ParentApp.GetURL(this.ParentApp.ItemsLink[this.inp2]), this.ParentApp.ItemsTargets[this.inp2]);
                    }
                }
            }
        }
        else if (this.ParentApp.NotActiveInfo.length() > 0) {
            this.ParentApp.showStatus(this.ParentApp.NotActiveInfo);
        }
    }
}
