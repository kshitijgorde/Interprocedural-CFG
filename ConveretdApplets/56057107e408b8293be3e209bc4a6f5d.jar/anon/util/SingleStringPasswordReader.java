// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

public class SingleStringPasswordReader implements IMiscPasswordReader
{
    private char[] m_password;
    
    public SingleStringPasswordReader(final String s) {
        this.m_password = null;
        if (s == null) {
            this.m_password = null;
            return;
        }
        this.m_password = s.toCharArray();
    }
    
    public SingleStringPasswordReader(final char[] array) {
        this.m_password = null;
        if (array == null) {
            this.m_password = null;
            return;
        }
        System.arraycopy(array, 0, this.m_password = new char[array.length], 0, array.length);
    }
    
    public String readPassword(final Object o) {
        if (this.m_password == null) {
            return null;
        }
        final String s = new String(this.m_password);
        this.clear();
        return s;
    }
    
    public void clear() {
        if (this.m_password != null) {
            for (int i = 0; i < this.m_password.length; ++i) {
                this.m_password[i] = '\0';
            }
            this.m_password = null;
        }
    }
}
