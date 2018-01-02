import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.MenuItem;

// 
// Decompiled by Procyon v0.5.30
// 

class nsmenuitem extends MenuItem implements ActionListener
{
    String url;
    String target;
    navwinbutton AppParent;
    
    nsmenuitem() {
        this.url = null;
        this.target = null;
        this.AppParent = null;
    }
    
    nsmenuitem(final String s) {
        super(s);
        this.url = null;
        this.target = null;
        this.AppParent = null;
    }
    
    nsmenuitem(final String s, final String url, final String target, final navwinbutton appParent) {
        super(s);
        this.url = null;
        this.target = null;
        this.AppParent = null;
        this.AppParent = appParent;
        this.target = target;
        this.url = url;
        super.setFont(this.AppParent.menufont);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.url != null && !this.AppParent.callJavascript(this.url)) {
            try {
                this.AppParent.getAppletContext().showDocument(new URL(this.AppParent.getCodeBase(), this.url), this.target);
            }
            catch (MalformedURLException ex) {}
        }
    }
}
