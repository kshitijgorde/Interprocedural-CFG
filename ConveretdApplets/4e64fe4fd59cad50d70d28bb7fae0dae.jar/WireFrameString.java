import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class WireFrameString
{
    private final int CHARACTER_WIDTH = 7;
    private final int CHARACTER_HEIGHT = 13;
    private final String LINE_SEPARATOR = "+";
    private WireFrameChar[] m_wireframe_string;
    private int m_line_count;
    private int m_string_length;
    private int m_max_vertice_count;
    
    public WireFrameString(String upperCase) {
        upperCase = upperCase.toUpperCase();
        final StringTokenizer stringTokenizer = new StringTokenizer(upperCase, "+", false);
        this.m_line_count = stringTokenizer.countTokens();
        this.m_string_length = upperCase.length();
        this.m_string_length -= this.m_line_count;
        this.m_wireframe_string = new WireFrameChar[this.m_string_length];
        this.m_max_vertice_count = 0;
        int n = 0;
        int n2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String s = new String(stringTokenizer.nextToken());
            final int[] array = new int[s.length()];
            this.CalculateCharOffsetsX(s.length(), array);
            int n3 = 0;
            for (int i = n2; i < n2 + s.length(); ++i) {
                this.m_wireframe_string[i] = new WireFrameChar(s.charAt(n3), array[n3]);
                if (this.m_wireframe_string[i].GetVerticeCount() > this.m_max_vertice_count) {
                    this.m_max_vertice_count = this.m_wireframe_string[i].GetVerticeCount();
                }
                ++n3;
            }
            final int calculateCharOffsetY = this.CalculateCharOffsetY(n, this.m_line_count);
            for (int j = n2; j < n2 + s.length(); ++j) {
                this.m_wireframe_string[j].MoveCharY(calculateCharOffsetY);
            }
            n2 += s.length();
            ++n;
        }
    }
    
    public void MoveStringY(final int[] array) {
        for (int i = 0; i < this.m_string_length; ++i) {
            this.m_wireframe_string[i].MoveCharY(array[i]);
        }
    }
    
    public int GetMaxVerticeCount() {
        return this.m_max_vertice_count;
    }
    
    public int Length() {
        return this.m_string_length;
    }
    
    public WireFrameChar CharAt(final int n) {
        return this.m_wireframe_string[n];
    }
    
    public void MoveStringX(final int[] array) {
        for (int i = 0; i < this.m_string_length; ++i) {
            this.m_wireframe_string[i].MoveCharX(array[i]);
        }
    }
    
    private int CalculateCharOffsetY(final int n, final int n2) {
        final int n3 = n2 / 2;
        if (n < n3) {
            return (n - n3) * -13;
        }
        return (n3 - n) * 13;
    }
    
    private void CalculateCharOffsetsX(final int n, final int[] array) {
        final int n2 = n / 2;
        for (int i = 0; i < n; ++i) {
            if (i < n2) {
                array[i] = (n2 - i) * -7;
            }
            else {
                array[i] = (i - n2) * 7;
            }
        }
    }
}
