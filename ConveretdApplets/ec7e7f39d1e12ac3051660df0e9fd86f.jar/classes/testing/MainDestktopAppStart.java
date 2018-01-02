// 
// Decompiled by Procyon v0.5.30
// 

package classes.testing;

import classes.anaxee.desktop.DesktopApplication;

public class MainDestktopAppStart
{
    public static void main(final String[] array) {
        final DesktopApplication desktopApplication = new DesktopApplication(true, "enrollment", "ISO_TEMPLATE");
        try {
            Thread.sleep(30000L);
        }
        catch (Exception ex) {}
        desktopApplication.getData();
        System.out.println("data came press close");
    }
}
