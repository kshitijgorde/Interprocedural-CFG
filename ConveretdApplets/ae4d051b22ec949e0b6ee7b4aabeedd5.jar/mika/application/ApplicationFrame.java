// 
// Decompiled by Procyon v0.5.30
// 

package mika.application;

import java.awt.Event;
import java.awt.Color;
import java.awt.Frame;

public class ApplicationFrame extends Frame
{
    Application m_Application;
    
    public ApplicationFrame(final String s, final int n, final int n2, final Application application) {
        super(s);
        this.m_Application = application;
        this.setResizable(false);
        this.show();
        this.hide();
        this.setBackground(Color.black);
    }
    
    public boolean gotFocus(final Event event, final Object o) {
        this.m_Application.requestFocus();
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 202:
            case 203:
            case 204:
            case 205: {
                if (this.m_Application != null) {
                    this.m_Application.repositionElements();
                }
                return true;
            }
            case 201: {
                this.dispose();
                return true;
            }
            default: {
                return false;
            }
        }
    }
}
