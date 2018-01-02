import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.FontMetrics;

// 
// Decompiled by Procyon v0.5.30
// 

class ocwordw
{
    static String[] WrapText(final String temp, final int width, final FontMetrics fm) {
        final Vector v_the = new Vector();
        String s_build = "";
        int s_length = 0;
        int i = 0;
        String s_temp = "";
        boolean first_run = true;
        boolean last_line = false;
        boolean has_tokens = false;
        temp.trim();
        int num_paras = 0;
        final StringTokenizer p_st = new StringTokenizer(temp, "|");
        num_paras = p_st.countTokens();
        String[] paras;
        if (num_paras == 0) {
            num_paras = 1;
            paras = new String[num_paras];
            paras[0] = temp;
        }
        else {
            paras = new String[num_paras];
            while (p_st.hasMoreTokens()) {
                paras[i] = p_st.nextToken();
                ++i;
            }
        }
        for (i = 0; i < num_paras; ++i) {
            final StringTokenizer st = new StringTokenizer(paras[i], " ");
            while (st.hasMoreTokens()) {
                has_tokens = true;
                s_temp = st.nextToken();
                if (s_temp != null) {}
                s_length += fm.stringWidth(s_temp + " ");
                if (s_length < width) {
                    if (first_run) {
                        s_build = s_temp;
                    }
                    else {
                        s_build = s_build + " " + s_temp;
                    }
                    last_line = true;
                }
                else {
                    v_the.addElement(s_build);
                    if (!st.hasMoreTokens()) {
                        v_the.addElement(s_temp);
                    }
                    else {
                        s_length = fm.stringWidth(s_temp + " ");
                        s_build = s_temp;
                    }
                    last_line = false;
                }
                first_run = false;
            }
            if (last_line && has_tokens) {
                last_line = false;
                v_the.addElement(s_build);
            }
            if (!has_tokens) {
                v_the.addElement(paras[i]);
            }
            else {
                has_tokens = false;
            }
            if (i < num_paras - 1) {
                v_the.addElement(" ");
            }
            first_run = true;
            has_tokens = false;
            s_length = 0;
            s_temp = "";
            s_build = "";
        }
        final String[] the_string = new String[v_the.size()];
        v_the.copyInto(the_string);
        return the_string;
    }
}
