// 
// Decompiled by Procyon v0.5.30
// 

package jstella.desktop;

import jstella.runner.InputMaster;
import net.java.games.input.Component;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Controller;

public class JStellaJoystickInput
{
    public static Controller myJoystick;
    
    public static void ControllerTextTest() {
        ControllerEnvironment ce = null;
        ce = ControllerEnvironment.getDefaultEnvironment();
        System.out.println("Controller Env = " + ce.toString());
        final Controller[] ca = ce.getControllers();
        for (int i = 0; i < ca.length; ++i) {
            System.out.println(ca[i].getName());
            System.out.println("Type: " + ca[i].getType().toString());
            final Component[] components = ca[i].getComponents();
            System.out.println("Component Count: " + components.length);
            for (int j = 0; j < components.length; ++j) {
                System.out.println("Component " + j + ": " + components[j].getName());
                System.out.println("    Identifier: " + components[j].getIdentifier().getName());
                System.out.print("    ComponentType: ");
                if (components[j].isRelative()) {
                    System.out.print("Relative");
                }
                else {
                    System.out.print("Absolute");
                }
                if (components[j].isAnalog()) {
                    System.out.print(" Analog");
                }
                else {
                    System.out.print(" Digital");
                }
                System.out.println();
            }
            System.out.println("---------------------------------");
        }
    }
    
    public static void pollJoystick() {
        if (JStellaJoystickInput.myJoystick != null) {
            JStellaJoystickInput.myJoystick.poll();
            final Component zXAxis = JStellaJoystickInput.myJoystick.getComponent((Component.Identifier)Component.Identifier.Axis.X);
            if (zXAxis != null) {
                final float zX = zXAxis.getPollData();
                InputMaster.setXAxis(zX);
            }
            final Component zYAxis = JStellaJoystickInput.myJoystick.getComponent((Component.Identifier)Component.Identifier.Axis.Y);
            if (zYAxis != null) {
                final float zY = zYAxis.getPollData();
                InputMaster.setYAxis(zY);
            }
            final Component zButton1 = JStellaJoystickInput.myJoystick.getComponent((Component.Identifier)Component.Identifier.Button._0);
            if (zButton1 != null) {
                final float zButtonValue = zButton1.getPollData();
                InputMaster.setControllerAButton(zButtonValue == 1.0f);
            }
        }
    }
    
    public static void detectJoystick() {
        final ControllerEnvironment zCE = ControllerEnvironment.getDefaultEnvironment();
        if (zCE != null && zCE.isSupported()) {
            final Controller[] zControllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
            Controller zFirstJoystick = null;
            for (int i = 0; i < zControllers.length && zFirstJoystick == null; ++i) {
                final Controller.Type zType = zControllers[i].getType();
                if (zType == Controller.Type.GAMEPAD || zType == Controller.Type.STICK) {
                    zFirstJoystick = zControllers[i];
                }
            }
            if (zFirstJoystick != null) {
                JStellaJoystickInput.myJoystick = zFirstJoystick;
                System.out.println("Detected joystick: " + zFirstJoystick.getName());
            }
            else {
                System.out.println("Didn't find joystick");
            }
        }
    }
    
    static {
        JStellaJoystickInput.myJoystick = null;
    }
}
