// 
// Decompiled by Procyon v0.5.30
// 

package ChatRoom;

import java.io.IOException;
import java.awt.TextArea;

public class h extends Thread
{
    public TextArea a;
    public boolean b;
    public ChatRoomApplet c;
    public b d;
    public boolean e;
    public boolean f;
    
    public boolean a(final String[] array) {
        if (array == null) {
            return false;
        }
        if (array.length < 2 || array[1] == null) {
            return true;
        }
        final String upperCase = array[1].toUpperCase();
        if (upperCase.equals("PRIVMSG") || upperCase.equals("NOTICE")) {
            if (array.length >= 4 && array[3] != null && array[0] != null) {
                this.c.AppendToMainWindow("<" + b(array[0]) + "> " + array[3].substring(1) + "\n");
            }
        }
        else if (upperCase.equals("JOIN")) {
            if (array.length >= 3 && array[0] != null) {
                final String b = b(array[0]);
                if (b.equals(this.c.b)) {
                    this.c.AppendToMainWindow("You have joined the chat room as " + b + ".\n");
                    this.f = true;
                }
                else {
                    this.c.AppendToMainWindow(b + " has joined the chat room.\n");
                }
            }
        }
        else if (upperCase.equals("QUIT") || upperCase.equals("PART")) {
            if (array[0] != null) {
                this.c.AppendToMainWindow(b(array[0]) + " has left the chat room.\n");
            }
        }
        else if (upperCase.equals("KICK")) {
            if (array.length >= 4 && array[3] != null) {
                final String a = a(array[3]);
                if (a.equals(this.c.b)) {
                    this.c.AppendToMainWindow("You have been removed from the chat room.\n");
                    this.f = false;
                    try {
                        this.d.c("QUIT");
                        return true;
                    }
                    catch (IOException ex) {
                        this.c.Error("quit", ex);
                        return false;
                    }
                }
                this.c.AppendToMainWindow(a + " has been removed from the chat room.\n");
            }
        }
        else if (upperCase.equals("KILL")) {
            if (array.length >= 3 && array[2] != null) {
                final String a2 = a(array[2]);
                if (a2.equals(this.c.b)) {
                    this.c.AppendToMainWindow("You have been removed from the chat room.\n");
                    this.f = false;
                }
                else {
                    this.c.AppendToMainWindow(a2 + " has been removed from the chat room.\n");
                }
                this.f = false;
            }
        }
        else {
            if (upperCase.equals("PING")) {
                String s = null;
                if (array.length >= 4) {
                    s = array[3];
                }
                else if (array.length == 3) {
                    s = array[2];
                }
                if (s == null) {
                    return true;
                }
                try {
                    this.d.b("PONG", s);
                    return true;
                }
                catch (IOException ex2) {
                    this.c.Error("pong", ex2);
                    return false;
                }
            }
            if (upperCase.equals("NICK")) {
                if (array.length >= 3 && array[0] != null && array[2] != null) {
                    final String b2 = b(array[0]);
                    final String a3 = a(array[2]);
                    if (b2.equals(this.c.g)) {
                        this.c.AppendToMainWindow("You have changed your name to " + a3 + ".\n");
                    }
                    else {
                        this.c.AppendToMainWindow(b2 + " is now known as " + a3 + ".\n");
                    }
                }
            }
            else if (upperCase.equals("433")) {
                if (array.length >= 4 && array[2] != null && array[3] != null) {
                    final String a4 = a(array[2]);
                    final String a5 = a(array[3]);
                    if (a4.equals("*")) {
                        this.c.a();
                    }
                    else {
                        this.c.AppendToMainWindow("The name " + a5 + " is already in use on the server.  Please choose another name.\n");
                        this.c.a(a4);
                    }
                }
            }
            else if (upperCase.equals("432")) {
                if (array.length >= 3 && array[2] != null) {
                    this.c.AppendToMainWindow("The name " + a(array[2]) + " is invalid. Please try using a name that contains only letters, numbers, and spaces.\n");
                    this.c.a(this.c.g);
                }
            }
            else {
                if (upperCase.equals("471")) {
                    this.c.AppendToMainWindow("This chat room is currently full.  Please try again later.  You can press reload to reconnect.\n");
                    this.f = false;
                    try {
                        this.d.c("QUIT");
                        return true;
                    }
                    catch (IOException ex3) {
                        this.c.Error("quit", ex3);
                        return false;
                    }
                }
                if (upperCase.equals("332")) {
                    if (array.length >= 3 && array[2] != null) {
                        this.c.AppendToMainWindow("The topic of this room is " + array[2].substring(1) + ".\n");
                    }
                }
                else if (upperCase.equals("353")) {
                    String s2 = null;
                    if (array.length >= 6 && array[5] != null) {
                        s2 = array[5].substring(1);
                    }
                    else if (array.length >= 5 && array[4] != null) {
                        s2 = array[4].substring(1);
                    }
                    if (s2 != null) {
                        String s3;
                        if (this.e) {
                            s3 = "     " + a(s2);
                        }
                        else {
                            s3 = "Members of room: " + a(s2);
                            this.e = true;
                        }
                        this.c.AppendToMainWindow(s3);
                    }
                }
                else if (upperCase.equals("366")) {
                    this.e = false;
                }
                else if (upperCase.equals("375") || upperCase.equals("372")) {
                    if (array.length >= 4 && array[3] != null) {
                        this.c.AppendToMainWindow(array[3].substring(3) + "\n");
                    }
                }
                else if (upperCase.equals("376")) {
                    this.c.AppendToMainWindow("\n");
                }
                else if (upperCase.equals("001") || upperCase.equals("002") || upperCase.equals("003") || upperCase.equals("004") || upperCase.equals("005") || upperCase.equals("006") || upperCase.equals("251") || upperCase.equals("252") || upperCase.equals("253") || upperCase.equals("254") || upperCase.equals("255") || upperCase.equals("265") || upperCase.equals("266") || upperCase.equals("404") || upperCase.equals("422") || upperCase.equals("451") || upperCase.equals("800") || upperCase.equals("MODE") || upperCase.equals("DATA")) {}
            }
        }
        return true;
    }
    
    public h(final b d, final TextArea a, final ChatRoomApplet c) {
        this.b = false;
        this.e = false;
        this.f = false;
        this.a = a;
        this.c = c;
        this.d = d;
    }
    
    public static String a(final String s) {
        final StringBuffer sb = new StringBuffer(s.length());
        for (int length = s.length(), i = 0; i < length; ++i) {
            char char1 = s.charAt(i);
            if (char1 == '\\') {
                if (++i >= length) {
                    break;
                }
                final char char2 = s.charAt(i);
                switch (char2) {
                    case 98: {
                        char1 = ' ';
                        break;
                    }
                    case 99: {
                        char1 = ',';
                        break;
                    }
                    case 92: {
                        char1 = '\\';
                        break;
                    }
                    case 114: {
                        char1 = '\r';
                        break;
                    }
                    case 110: {
                        char1 = '\n';
                        break;
                    }
                    case 116: {
                        char1 = '\t';
                        break;
                    }
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                    case 69:
                    case 70:
                    case 71:
                    case 72:
                    case 73:
                    case 74:
                    case 75:
                    case 76:
                    case 77:
                    case 78:
                    case 79: {
                        char1 = (char)(char2 - ' ');
                        break;
                    }
                    case 80:
                    case 81:
                    case 82:
                    case 83:
                    case 84:
                    case 85:
                    case 86: {
                        char1 = (char)(char2 - '\u0016');
                        break;
                    }
                }
            }
            else {
                if ("!\"#$%&'()+,./:;<=>?@".indexOf(char1) != -1) {
                    continue;
                }
                if (char1 >= '\u00e0') {
                    i += 2;
                    if (i >= length) {
                        break;
                    }
                    char1 = (char)((char1 & '\u000f') << 12 | (s.charAt(i - 1) & '?') << 6 | (s.charAt(i) & '?'));
                }
                else if (char1 >= '\u00c0') {
                    if (++i >= length) {
                        break;
                    }
                    char1 = (char)((char1 & '\u001f') << 6 | (s.charAt(i) & '?'));
                }
            }
            sb.append(char1);
        }
        return sb.toString();
    }
    
    public void run() {
        System.err.println("Starting listener thread");
        this.b = true;
        String[] a;
        do {
            try {
                a = this.d.a();
            }
            catch (IOException ex) {
                synchronized (this.c) {
                    if (this.b && this.c.t != null) {
                        this.b = false;
                        if (ex.getMessage().indexOf("closed") != -1) {
                            this.c.AppendToMainWindow("Connection closed.  Press reload to reconnect.");
                            System.err.println("IO Error: connect: " + ex);
                            this.c.CleanUp();
                        }
                        else {
                            this.c.Error("receive", ex);
                        }
                    }
                    // monitorexit(this.c)
                    return;
                }
            }
        } while (this.a(a));
    }
    
    public static String b(final String s) {
        int n = s.indexOf(33);
        if (n == -1) {
            n = s.length();
        }
        return a(s.substring(1, n));
    }
    
    public static String c(final String s) {
        final StringBuffer sb = new StringBuffer(s.length());
        sb.append('\'');
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            switch (char1) {
                case 32: {
                    sb.append("\\b");
                    break;
                }
                case 44: {
                    sb.append("\\c");
                    break;
                }
                case 92: {
                    sb.append("\\\\");
                    break;
                }
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 46:
                case 47: {
                    sb.append('\\');
                    sb.append((char)(char1 + ' '));
                    break;
                }
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64: {
                    sb.append('\\');
                    sb.append((char)(char1 + '\u0016'));
                    break;
                }
                default: {
                    if (char1 >= '\u0800') {
                        sb.append((char)('\u00e0' | (char1 >> 12 & '\u000f')));
                        sb.append((char)('\u0080' | (char1 >> 6 & '?')));
                        sb.append((char)('\u0080' | (char1 & '?')));
                        break;
                    }
                    if (char1 >= '\u0080') {
                        sb.append((char)('\u00c0' | (char1 >> 6 & '\u001f')));
                        sb.append((char)('\u0080' | (char1 & '?')));
                        break;
                    }
                    sb.append(char1);
                    break;
                }
            }
        }
        return sb.toString();
    }
}
