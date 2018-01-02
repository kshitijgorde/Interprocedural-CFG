// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.gpassword;

import java.io.IOException;
import zaluc.gparser200.Record;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class GPassword
{
    private static final String paramMsg = "GPassword creates a password file that can be used by InterneTree to     \nprotect some of the data in the that is downloaded.                      \n                                                                         \nThe following parameters are allowable:                                \n\n     filename:      This parameter must be first and must exist.  It is  \n                    the name of the password file that will be created,  \n                    without the .gpw suffix.  It should be the same as   \n                    the name of the GEDCOM file that the password is     \n                    protecting.                                          \n                                                                         \n     password:      This parameter must be second and must exist.  It is \n                    the new password that will be used to protect the    \n                    data in the GEDCOM file.                             \n                                                                         \n                    The password may be any length and may contain any   \n                    printable characters.\n";
    
    public static void main(final String[] array) {
        try {
            if (array.length == 2) {
                final String string = String.valueOf(array[0].toLowerCase()) + ".gpw";
                final String s = array[1];
                final DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(string));
                new Record(dataOutputStream).write(18, s);
                dataOutputStream.close();
                return;
            }
            System.out.println("GPassword creates a password file that can be used by InterneTree to     \nprotect some of the data in the that is downloaded.                      \n                                                                         \nThe following parameters are allowable:                                \n\n     filename:      This parameter must be first and must exist.  It is  \n                    the name of the password file that will be created,  \n                    without the .gpw suffix.  It should be the same as   \n                    the name of the GEDCOM file that the password is     \n                    protecting.                                          \n                                                                         \n     password:      This parameter must be second and must exist.  It is \n                    the new password that will be used to protect the    \n                    data in the GEDCOM file.                             \n                                                                         \n                    The password may be any length and may contain any   \n                    printable characters.\n");
        }
        catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
