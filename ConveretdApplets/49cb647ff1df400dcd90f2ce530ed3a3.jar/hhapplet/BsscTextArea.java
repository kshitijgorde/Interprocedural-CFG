// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.awt.FontMetrics;
import java.awt.AWTEvent;
import java.awt.TextArea;

public class BsscTextArea extends TextArea
{
    private String m_text;
    
    protected void processEvent(final AWTEvent awtEvent) {
        if (awtEvent.getID() == 101 || awtEvent.getID() == 102) {
            super.setText(this.parseText());
        }
    }
    
    public BsscTextArea() {
        this.m_text = "";
        this.enableEvents(1L);
    }
    
    private String parseText() {
        final int n = this.getBounds().width - 20;
        final FontMetrics fontMetrics = this.getGraphics().getFontMetrics();
        final String s = new String(this.m_text);
        final StringBuffer sb = new StringBuffer();
        final StringBuffer sb2 = new StringBuffer();
        final StringBuffer sb3 = new StringBuffer();
        final int charWidth = fontMetrics.charWidth(' ');
        int i = 0;
        do {
            final int index = s.indexOf(10, i);
            String s2;
            if (index != -1) {
                s2 = s.substring(i, index);
                i = index + 1;
            }
            else {
                s2 = s.substring(i);
                i = -1;
            }
            int n2 = 1;
            int n3 = 0;
            int j = 0;
            do {
                final int index2 = s2.indexOf(32, j);
                String s3;
                if (index2 != -1) {
                    s3 = s2.substring(j, index2);
                    j = index2 + 1;
                }
                else {
                    s3 = s2.substring(j);
                    j = -1;
                }
                final int charsWidth = fontMetrics.charsWidth(s3.toCharArray(), 0, s3.length());
                if (n2 != 0) {
                    n3 = charsWidth;
                    sb3.append(s3);
                    n2 = 0;
                }
                else if (n3 == 0 || n3 + charsWidth + charWidth < n) {
                    n3 += charsWidth + charWidth;
                    sb3.append(' ');
                    sb3.append(s3);
                }
                else {
                    sb2.append((Object)sb3);
                    sb2.append("\n");
                    sb3.setLength(0);
                    n3 = charsWidth;
                    sb3.append(s3);
                }
            } while (j != -1);
            if (sb3.length() != 0) {
                sb2.append((Object)sb3);
                sb3.setLength(0);
            }
            sb.append((Object)sb2);
            sb2.setLength(0);
            sb.append("\n");
        } while (i != -1);
        return sb.toString();
    }
    
    public void setText(final String text) {
        this.m_text = text;
        super.setText(this.parseText());
    }
}
