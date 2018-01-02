// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier;

public abstract class NativeVerifier
{
    public static void main(final String[] args) {
        if (args.length != 1) {
            System.out.println("Verifier front-end: need exactly one argument.");
            System.exit(1);
        }
        final int dotclasspos = args[0].lastIndexOf(".class");
        if (dotclasspos != -1) {
            args[0] = args[0].substring(0, dotclasspos);
        }
        args[0] = args[0].replace('/', '.');
        try {
            Class.forName(args[0]);
        }
        catch (ExceptionInInitializerError eiie) {
            System.out.println("NativeVerifier: ExceptionInInitializerError encountered on '" + args[0] + "'.");
            System.out.println(eiie);
            System.exit(1);
        }
        catch (LinkageError le) {
            System.out.println("NativeVerifier: LinkageError encountered on '" + args[0] + "'.");
            System.out.println(le);
            System.exit(1);
        }
        catch (ClassNotFoundException cnfe) {
            System.out.println("NativeVerifier: FILE NOT FOUND: '" + args[0] + "'.");
            System.exit(1);
        }
        catch (Throwable t) {
            System.out.println("NativeVerifier: Unspecified verification error on'" + args[0] + "'.");
            System.exit(1);
        }
        System.out.println("NativeVerifier: Class file '" + args[0] + "' seems to be okay.");
        System.exit(0);
    }
}
