// 
// Decompiled by Procyon v0.5.30
// 

class D_SOLVER extends Thread
{
    public int indexStart;
    public int indexEnd;
    public int solverName;
    public int dydxCount;
    public boolean findingOrbit;
    public static dataORBIT orbit;
    public static dataGraphConstants g;
    private double YlimitMax;
    private double YlimitMin;
    private double XlimitMax;
    private double XlimitMin;
    public static boolean stoppedByUser;
    
    public void run() {
        D_GRAPH.labelMouse.setText("Solving ODE...");
        final double n = (DFIELD.ComputationWindowY - 1) * (D_SOLVER.g.ymax0 - D_SOLVER.g.ymin0) / 2.0;
        this.YlimitMax = D_SOLVER.g.ymax0 + n;
        this.YlimitMin = D_SOLVER.g.ymin0 - n;
        D_SOLVER.g.Scr.setColor(C_TOOL.ForegroundColor);
        for (int i = this.indexStart; i <= this.indexEnd; ++i) {
            this.startSolver(i);
        }
        D_SOLVER.g.myRepaint();
        if (D_SOLVER.stoppedByUser) {
            D_GRAPH.labelMouse.setText("Interrupted.");
        }
        else {
            D_GRAPH.labelMouse.setText("Done.");
        }
        D_SOLVER.g.StopButton.setVisible(false);
        DFIELD.solverThreadActive = false;
    }
    
    public void startSolver(final int n) {
        final double n2 = D_SOLVER.orbit.initialX[n];
        final double n3 = D_SOLVER.orbit.initialY[n];
        D_SOLVER.orbit.startPointer[n] = D_SOLVER.orbit.dataFree;
        D_SOLVER.orbit.X[D_SOLVER.orbit.dataFree] = (float)n2;
        D_SOLVER.orbit.Y[D_SOLVER.orbit.dataFree] = (float)n3;
        final dataORBIT orbit = D_SOLVER.orbit;
        ++orbit.dataFree;
        if (D_SOLVER.orbit.specifyRange[n]) {
            this.XlimitMax = D_SOLVER.orbit.rangeMax[n];
            this.XlimitMin = D_SOLVER.orbit.rangeMin[n];
        }
        else {
            final double n4 = (DFIELD.ComputationWindowX - 1) * (D_SOLVER.g.xmax0 - D_SOLVER.g.xmin0) / 2.0;
            this.XlimitMax = D_SOLVER.g.xmax0 + n4;
            this.XlimitMin = D_SOLVER.g.xmin0 - n4;
        }
        D_SOLVER.stoppedByUser = false;
        this.solverName = D_SOLVER.orbit.solver[n];
        D_SOLVER.g.Buf.setColor(D_GRAPH.colorSolver[this.solverName]);
        this.findingOrbit = true;
        final int n5 = D_SOLVER.orbit.direction[n];
        final int screenX = D_SOLVER.g.screenX(n2);
        final int screenY = D_SOLVER.g.screenY(n3);
        if (D_GRAPH.cbox[26].getState()) {
            D_SOLVER.g.Buf.fillOval(screenX - 3, screenY - 3, 6, 6);
            D_SOLVER.g.pointType = 1;
        }
        else {
            D_SOLVER.g.Buf.drawOval(screenX - 2, screenY - 2, 4, 4);
            D_SOLVER.g.pointType = 0;
        }
        String s;
        double n6;
        if (n5 == 1) {
            s = "Foward Orbit from ";
            n6 = this.XlimitMax;
        }
        else {
            s = "Backward Orbit from ";
            n6 = this.XlimitMin;
        }
        C_MSG.MSG_append(String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("(").append(Float.toString((float)n2)).append(" , ").append(Float.toString((float)n3)).append(")\n")))))), false);
        this.dydxCount = 0;
        if (this.solverName == 0) {
            this.Euler(n2, n3, n6);
        }
        else if (this.solverName == 1) {
            this.RungeKutta2(n2, n3, n6);
        }
        else if (this.solverName == 2) {
            this.RungeKutta4(n2, n3, n6);
        }
        else if (this.solverName == 3) {
            this.RungeKuttaFehlberg(n2, n3, n6);
        }
        else {
            this.Dormand(n2, n3, n6);
        }
        D_SOLVER.orbit.endPointer[n] = D_SOLVER.orbit.dataFree - 1;
        C_MSG.MSG_append(String.valueOf(String.valueOf(new StringBuffer("   evaluations of ODE (").append(D_GRAPH.cboxName[this.solverName]).append(") = ").append(Integer.toString(this.dydxCount)).append("\n"))), false);
    }
    
    public void Euler(final double n, final double n2, final double n3) {
        double n4;
        if (n < n3) {
            n4 = 1.0;
        }
        else {
            n4 = -1.0;
        }
        final double n5 = DFIELD.EulerStepSize * n4;
        double n6 = n;
        double n7 = n2;
        while (this.findingOrbit) {
            final double n8 = n6 + n5;
            final double n9 = n7 + DFIELD.diffyQ.functionValue(n6, n7) * n5;
            ++this.dydxCount;
            if (Double.isNaN(n9)) {
                C_MSG.MSG_append(DFIELD.diffyQ.errorString, true);
                return;
            }
            if (sign(n3 - n8) != n4) {
                this.findingOrbit = false;
            }
            if (n9 > this.YlimitMax || n9 < this.YlimitMin) {
                this.findingOrbit = false;
            }
            if (n8 > this.XlimitMax || n8 < this.XlimitMin) {
                this.findingOrbit = false;
            }
            plot(n6, n7, n8, n9);
            n6 = n8;
            n7 = n9;
        }
    }
    
    public void RungeKutta2(final double n, final double n2, final double n3) {
        double n4;
        if (n < n3) {
            n4 = 1.0;
        }
        else {
            n4 = -1.0;
        }
        final double n5 = DFIELD.RungeKutta2StepSize * n4;
        final double n6 = n5 / 2;
        double n7 = n;
        double n8 = n2;
        while (this.findingOrbit) {
            final double functionValue = DFIELD.diffyQ.functionValue(n7, n8);
            if (Double.isNaN(functionValue)) {
                C_MSG.MSG_append(DFIELD.diffyQ.errorString, true);
                return;
            }
            final double functionValue2 = DFIELD.diffyQ.functionValue(n7 + n5, n8 + n5 * functionValue);
            if (Double.isNaN(functionValue2)) {
                C_MSG.MSG_append(DFIELD.diffyQ.errorString, true);
                return;
            }
            this.dydxCount += 2;
            final double n9 = n7 + n5;
            final double n10 = n8 + n6 * (functionValue + functionValue2);
            if (sign(n3 - n9) != n4) {
                this.findingOrbit = false;
            }
            if (n10 > this.YlimitMax || n10 < this.YlimitMin) {
                this.findingOrbit = false;
            }
            if (n9 > this.XlimitMax || n9 < this.XlimitMin) {
                this.findingOrbit = false;
            }
            plot(n7, n8, n9, n10);
            n7 = n9;
            n8 = n10;
        }
    }
    
    public void RungeKutta4(final double n, final double n2, final double n3) {
        double n4;
        if (n < n3) {
            n4 = 1.0;
        }
        else {
            n4 = -1.0;
        }
        final double n5 = DFIELD.RungeKutta4StepSize * n4;
        final double n6 = n5 / 2;
        final double n7 = n5 / 6;
        double n8 = n;
        double n9 = n2;
        while (this.findingOrbit) {
            final double functionValue = DFIELD.diffyQ.functionValue(n8, n9);
            final double functionValue2 = DFIELD.diffyQ.functionValue(n8 + n6, n9 + n6 * functionValue);
            final double functionValue3 = DFIELD.diffyQ.functionValue(n8 + n6, n9 + n6 * functionValue2);
            final double functionValue4 = DFIELD.diffyQ.functionValue(n8 + n5, n9 + n5 * functionValue3);
            this.dydxCount += 4;
            final double n10 = n8 + n5;
            final double n11 = n9 + n7 * (functionValue + 2 * functionValue2 + 2 * functionValue3 + functionValue4);
            if (Double.isNaN(n11)) {
                showerr(n8, n9);
                return;
            }
            if (sign(n3 - n10) != n4) {
                this.findingOrbit = false;
            }
            if (n11 > this.YlimitMax || n11 < this.YlimitMin) {
                this.findingOrbit = false;
            }
            if (n10 > this.XlimitMax || n10 < this.XlimitMin) {
                this.findingOrbit = false;
            }
            plot(n8, n9, n10, n11);
            n8 = n10;
            n9 = n11;
        }
    }
    
    public void RungeKuttaFehlberg(final double n, final double n2, final double n3) {
        double n4 = n;
        double n5 = n2;
        double n6 = 0.0;
        double n7 = 0.0;
        double n8;
        if (n < n3) {
            n8 = 1.0;
        }
        else {
            n8 = -1.0;
        }
        double n9 = DFIELD.RKF_Maxh * n8;
        while (this.findingOrbit) {
            int i = 1;
            while (i != 0) {
                final double functionValue = DFIELD.diffyQ.functionValue(n4, n5);
                final double functionValue2 = DFIELD.diffyQ.functionValue(n4 + 0.25 * n9, n5 + 0.25 * n9 * functionValue);
                final double functionValue3 = DFIELD.diffyQ.functionValue(n4 + 0.375 * n9, n5 + n9 * (0.09375 * functionValue + 0.28125 * functionValue2));
                final double functionValue4 = DFIELD.diffyQ.functionValue(n4 + 0.9230769230769231 * n9, n5 + n9 * (0.8793809740555303 * functionValue + -3.277196176604461 * functionValue2 + 3.3208921256258535 * functionValue3));
                final double functionValue5 = DFIELD.diffyQ.functionValue(n4 + n9, n5 + n9 * (2.0324074074074074 * functionValue + -8.0 * functionValue2 + 7.173489278752436 * functionValue3 + -0.20589668615984405 * functionValue4));
                final double functionValue6 = DFIELD.diffyQ.functionValue(n4 + 0.5 * n9, n5 + n9 * (-0.2962962962962963 * functionValue + 2.0 * functionValue2 + -1.3816764132553607 * functionValue3 + 0.4529727095516569 * functionValue4 + -0.275 * functionValue5));
                this.dydxCount += 5;
                n7 = n5 + n9 * (0.11851851851851852 * functionValue + 0.5189863547758284 * functionValue3 + 0.5061314903420167 * functionValue4 + -0.18 * functionValue5 + 0.03636363636363636 * functionValue6);
                n6 = n4 + n9;
                final double abs = Math.abs(n9 * (0.002777777777777778 * functionValue + -0.02994152046783626 * functionValue3 + -0.029199893673577886 * functionValue4 + 0.02 * functionValue5 + 0.03636363636363636 * functionValue6));
                final double abs2 = Math.abs(n9 * DFIELD.RKF_Epsilon);
                double n10;
                if (Double.isNaN(n7) || Double.isNaN(abs)) {
                    n10 = n9 / 2.0;
                }
                else {
                    n10 = 0.9 * n9 * Math.pow(abs2 / abs, 0.25);
                    if (abs <= abs2) {
                        i = 0;
                    }
                }
                if (i != 0 && Math.abs(n9) <= DFIELD.RKF_Minh) {
                    showerr(n4, n5);
                    return;
                }
                if (Math.abs(n10) > Math.abs(4.0 * n9)) {
                    n10 = n9 * 4.0;
                }
                else if (Math.abs(n10) < Math.abs(0.1 * n9)) {
                    n10 = n9 * 0.1;
                }
                if (Math.abs(n10) > DFIELD.RKF_Maxh) {
                    n10 = DFIELD.RKF_Maxh * n8;
                }
                else if (Math.abs(n10) < DFIELD.RKF_Minh) {
                    n10 = DFIELD.RKF_Minh * n8;
                }
                n9 = n10;
            }
            plot(n4, n5, n6, n7);
            n4 = n6;
            n5 = n7;
            if (sign(n3 - n6) != n8) {
                this.findingOrbit = false;
            }
            if (n7 > this.YlimitMax || n7 < this.YlimitMin) {
                this.findingOrbit = false;
            }
            if (n6 > this.XlimitMax || n6 < this.XlimitMin) {
                this.findingOrbit = false;
            }
        }
    }
    
    public void Dormand(final double n, final double n2, final double n3) {
        final double[][] array = { { 1.0, -2.859375, 3.0833333333333335, -1.1328125 }, { 0.0, 0.0, 0.0, 0.0 }, { 0.0, 4.0431266846361185, -6.289308176100629, 2.6954177897574123 }, { 0.0, -3.90625, 10.416666666666666, -5.859375 }, { 0.0, 2.7939268867924527, -6.877358490566038, 3.761055424528302 }, { 0.0, -1.5714285714285714, 3.6666666666666665, -1.9642857142857142 }, { 0.0, 1.5, -4.0, 2.5 } };
        final int dormand_refine = DFIELD.Dormand_refine;
        final double[][] array2 = new double[7][dormand_refine - 1];
        final double[] array3 = new double[dormand_refine - 1];
        if (dormand_refine > 1) {
            for (int i = 0; i < dormand_refine - 1; ++i) {
                array3[i] = (i + 1) / dormand_refine;
            }
            final double[][] array4 = new double[4][dormand_refine - 1];
            for (int j = 0; j < dormand_refine - 1; ++j) {
                array4[0][j] = array3[j];
                for (int k = 1; k < 4; ++k) {
                    array4[k][j] = array4[k - 1][j] * array3[j];
                }
            }
            for (int l = 0; l < 7; ++l) {
                for (int n4 = 0; n4 < dormand_refine - 1; ++n4) {
                    array2[l][n4] = array[l][0] * array4[0][n4] + array[l][1] * array4[1][n4] + array[l][2] * array4[2][n4] + array[l][3] * array4[3][n4];
                }
            }
        }
        final double n5 = D_SOLVER.g.xmax - D_SOLVER.g.xmin;
        double functionValue = 0.0;
        double functionValue2 = 0.0;
        double functionValue3 = 0.0;
        double functionValue4 = 0.0;
        double functionValue5 = 0.0;
        double functionValue6 = 0.0;
        double n6 = n;
        double n7 = n2;
        double n8 = 0.0;
        double n9 = 0.0;
        double n10;
        if (n < n3) {
            n10 = 1.0;
        }
        else {
            n10 = -1.0;
        }
        double n12;
        double n11 = n12 = DFIELD.Dormand_Maxh * n10;
        double functionValue7 = DFIELD.diffyQ.functionValue(n, n2);
        while (this.findingOrbit) {
            int n13 = 1;
            while (n13 != 0) {
                n11 = n12;
                functionValue = DFIELD.diffyQ.functionValue(n6 + 0.2 * n11, n7 + 0.2 * n11 * functionValue7);
                functionValue2 = DFIELD.diffyQ.functionValue(n6 + 0.3 * n11, n7 + n11 * (0.075 * functionValue7 + 0.225 * functionValue));
                functionValue3 = DFIELD.diffyQ.functionValue(n6 + 0.8 * n11, n7 + n11 * (0.9777777777777777 * functionValue7 + -3.7333333333333334 * functionValue + 3.5555555555555554 * functionValue2));
                functionValue4 = DFIELD.diffyQ.functionValue(n6 + 0.8888888888888888 * n11, n7 + n11 * (2.9525986892242035 * functionValue7 + -11.595793324188385 * functionValue + 9.822892851699436 * functionValue2 + -0.2908093278463649 * functionValue3));
                functionValue5 = DFIELD.diffyQ.functionValue(n6 + 1.0 * n11, n7 + n11 * (2.8462752525252526 * functionValue7 + -10.757575757575758 * functionValue + 8.906422717743473 * functionValue2 + 0.2784090909090909 * functionValue3 + -0.2735313036020583 * functionValue4));
                n9 = n7 + n11 * (0.09114583333333333 * functionValue7 + 0.44923629829290207 * functionValue2 + 0.6510416666666666 * functionValue3 + -0.322376179245283 * functionValue4 + 0.13095238095238096 * functionValue5);
                n8 = n6 + n11;
                functionValue6 = DFIELD.diffyQ.functionValue(n8, n9);
                this.dydxCount += 5;
                final double pow = Math.pow(2 * Math.abs(n11 * (0.0012326388888888888 * functionValue7 + -0.0042527702905061394 * functionValue2 + 0.03697916666666667 * functionValue3 + -0.05086379716981132 * functionValue4 + 0.0419047619047619 * functionValue5 + -0.025 * functionValue6)) / ((Math.abs(n7) + Math.abs(n9) + n5 * 0.001) * DFIELD.Dormand_Epsilon), 0.2);
                if (Double.isNaN(n9) || Double.isNaN(pow)) {
                    n12 = n11 / 2.0;
                }
                else {
                    n12 = 0.8 * n11 / pow;
                    if (pow < 1) {
                        n13 = 0;
                    }
                }
                if (n13 != 0 && Math.abs(n11) <= DFIELD.Dormand_Minh) {
                    showerr(n6, n7);
                    return;
                }
                if (Math.abs(n12) > Math.abs(10.0 * n11)) {
                    n12 = n11 * 10.0;
                }
                else if (Math.abs(n12) < Math.abs(0.1 * n11)) {
                    n12 = n11 * 0.1;
                }
                if (Math.abs(n12) > DFIELD.Dormand_Maxh) {
                    n12 = DFIELD.Dormand_Maxh * n10;
                }
                else {
                    if (Math.abs(n12) >= DFIELD.Dormand_Minh) {
                        continue;
                    }
                    n12 = DFIELD.Dormand_Minh * n10;
                }
            }
            double n14 = n6;
            double n15 = n7;
            if (dormand_refine > 1) {
                for (int n16 = 0; n16 < dormand_refine - 1; ++n16) {
                    final double n17 = n6 + n11 * array3[n16];
                    final double n18 = n7 + n11 * (functionValue7 * array2[0][n16] + functionValue * array2[1][n16] + functionValue2 * array2[2][n16] + functionValue3 * array2[3][n16] + functionValue4 * array2[4][n16] + functionValue5 * array2[5][n16] + functionValue6 * array2[6][n16]);
                    plot(n14, n15, n17, n18);
                    n14 = n17;
                    n15 = n18;
                }
            }
            plot(n14, n15, n8, n9);
            n6 = n8;
            n7 = n9;
            functionValue7 = functionValue6;
            if (sign(n3 - n8) != n10) {
                this.findingOrbit = false;
            }
            if (n9 > this.YlimitMax || n9 < this.YlimitMin) {
                this.findingOrbit = false;
            }
            if (n8 > this.XlimitMax || n8 < this.XlimitMin) {
                this.findingOrbit = false;
            }
        }
    }
    
    public static void showerr(final double n, final double n2) {
        C_MSG.MSG_append(String.valueOf(String.valueOf(new StringBuffer("     ODE solver can not stay within specified error tolerance.\n     Last Point: (").append(Float.toString((float)n)).append(" , ").append(Float.toString((float)n2)).append("\n"))), true);
    }
    
    public static boolean plot(final double n, final double n2, final double n3, final double n4) {
        D_SOLVER.orbit.X[D_SOLVER.orbit.dataFree] = (float)n3;
        D_SOLVER.orbit.Y[D_SOLVER.orbit.dataFree] = (float)n4;
        D_SOLVER.g.plotXY(D_SOLVER.orbit.dataFree - 1, D_SOLVER.orbit.dataFree, true, D_SOLVER.orbit);
        final dataORBIT orbit = D_SOLVER.orbit;
        ++orbit.dataFree;
        if (DFIELD.timeStep > 0) {
            try {
                Thread.sleep(DFIELD.timeStep);
            }
            catch (InterruptedException ex) {}
        }
        else {
            Thread.yield();
        }
        return true;
    }
    
    public static double sign(final double n) {
        if (n < 0.0) {
            return -1.0;
        }
        if (n > 0.0) {
            return 1.0;
        }
        return 0.0;
    }
}
