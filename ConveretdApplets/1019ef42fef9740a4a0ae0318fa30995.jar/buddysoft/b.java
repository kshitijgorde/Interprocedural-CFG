// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft;

import java.awt.Event;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Frame;

class b extends Frame
{
    b(final QuickMail quickMail) {
        super("BuddySoft Quick Mail");
        this.setBackground(quickMail.else);
        this.setForeground(quickMail.if);
        this.setIconImage(quickMail.getImage(quickMail.getCodeBase(), "buddysoft/mail_ico.gif"));
        this.add("Center", new a(quickMail, 5));
        this.resize(350, 350);
        this.setResizable(false);
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        this.move((defaultToolkit.getScreenSize().width - this.size().width) / 2, (defaultToolkit.getScreenSize().height - this.size().height) / 2);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.hide();
                this.dispose();
                break;
            }
        }
        return super.handleEvent(event);
    }
}
