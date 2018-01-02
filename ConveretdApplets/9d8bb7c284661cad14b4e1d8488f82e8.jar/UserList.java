// 
// Decompiled by Procyon v0.5.30
// 

public class UserList
{
    int \u0298B;
    int[] \u0140;
    String[] \u0299B;
    
    public UserList(final int \u0298b) {
        this.\u0298B = \u0298b;
        this.\u0140 = new int[\u0298b];
        this.\u0299B = new String[\u0298b];
        this.clearList();
    }
    
    public boolean addUser(final int n, final String s) {
        for (int i = 0; i < this.\u0298B; ++i) {
            if (this.\u0140[i] == 0 && this.\u0299B[i].equals("")) {
                this.\u0140[i] = n;
                this.\u0299B[i] = s;
                return true;
            }
        }
        return false;
    }
    
    public void removeUser(final int n, final String s) {
        for (int i = 0; i < this.\u0298B; ++i) {
            if (this.\u0140[i] == n && this.\u0299B[i].equals(s)) {
                this.\u0140[i] = 0;
                this.\u0299B[i] = "";
            }
        }
    }
    
    public int getPosition(final int n, final String s) {
        for (int i = 0; i < this.\u0298B; ++i) {
            if (this.\u0140[i] == n && this.\u0299B[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }
    
    public int getPosition(final String s) {
        int n = -1;
        for (int i = 0; i < this.\u0298B; ++i) {
            if (this.\u0299B[i].equals(s)) {
                n = i;
            }
        }
        return n;
    }
    
    public void removeUserAtPosition(final int n) {
        this.\u0140[n] = 0;
        this.\u0299B[n] = "";
    }
    
    public boolean userExists(final int n, final String s) {
        for (int i = 0; i < this.\u0298B; ++i) {
            if (this.\u0140[i] == n && this.\u0299B[i].equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    public void clearList() {
        for (int i = 0; i < this.\u0298B; ++i) {
            this.\u0140[i] = 0;
            this.\u0299B[i] = "";
        }
    }
}
