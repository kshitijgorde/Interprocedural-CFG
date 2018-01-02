// 
// Decompiled by Procyon v0.5.30
// 

package mika.system;

import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.Frame;

class S_DebugWindow extends Frame
{
    private TextArea m_text;
    
    public S_DebugWindow() {
        super("Debug");
        this.setLayout(new BorderLayout());
        (this.m_text = new TextArea()).setEditable(false);
        this.add("Center", this.m_text);
        this.resize(600, 500);
        this.show();
    }
    
    public void clear() {
        this.m_text.setText("");
    }
    
    public void appendText(final String s) {
        this.m_text.appendText(s);
    }
    
    public boolean handleEvent(final Event event) {
        Label_0153: {
            switch (event.id) {
                case 402: {
                    switch (event.key) {
                        case 67:
                        case 99: {
                            S_Debug.clear();
                            break Label_0153;
                        }
                        case 83:
                        case 115: {
                            S_Debug.setDebugMode(!S_Debug.isDebugMode());
                            break Label_0153;
                        }
                        case 72:
                        case 104: {
                            S_Debug.print("\nS_DebugWindow Help\n");
                            S_Debug.print(" C - clear window");
                            S_Debug.print(" S - suspend writing");
                            S_Debug.print("");
                            break Label_0153;
                        }
                        default: {
                            break Label_0153;
                        }
                    }
                    break;
                }
                case 201: {
                    S_Debug.closeWindow();
                    break;
                }
                default: {
                    return false;
                }
            }
        }
        return true;
    }
}
