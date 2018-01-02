// 
// Decompiled by Procyon v0.5.30
// 

class WireFrameChar
{
    private int m_vertice_count;
    private int m_edge_count;
    int[][] m_vertices;
    int[][] m_edges;
    private final int m_exclamation_vertice_count = 5;
    private final int m_exclamation_edge_count = 4;
    private final int[][] m_exclamation_vertices;
    private final int[][] m_exclamation_edges;
    private final int m_0_vertice_count = 4;
    private final int m_0_edge_count = 4;
    private final int[][] m_0_vertices;
    private final int[][] m_0_edges;
    private final int m_1_vertice_count = 2;
    private final int m_1_edge_count = 1;
    private final int[][] m_1_vertices;
    private final int[][] m_1_edges;
    private final int m_2_vertice_count = 8;
    private final int m_2_edge_count = 7;
    private final int[][] m_2_vertices;
    private final int[][] m_2_edges;
    private final int m_3_vertice_count = 8;
    private final int m_3_edge_count = 7;
    private final int[][] m_3_vertices;
    private final int[][] m_3_edges;
    private final int m_4_vertice_count = 5;
    private final int m_4_edge_count = 3;
    private final int[][] m_4_vertices;
    private final int[][] m_4_edges;
    private final int m_5_vertice_count = 8;
    private final int m_5_edge_count = 7;
    private final int[][] m_5_vertices;
    private final int[][] m_5_edges;
    private final int m_6_vertice_count = 7;
    private final int m_6_edge_count = 6;
    private final int[][] m_6_vertices;
    private final int[][] m_6_edges;
    private final int m_7_vertice_count = 4;
    private final int m_7_edge_count = 3;
    private final int[][] m_7_vertices;
    private final int[][] m_7_edges;
    private final int m_8_vertice_count = 6;
    private final int m_8_edge_count = 5;
    private final int[][] m_8_vertices;
    private final int[][] m_8_edges;
    private final int m_9_vertice_count = 7;
    private final int m_9_edge_count = 6;
    private final int[][] m_9_vertices;
    private final int[][] m_9_edges;
    private final int m_A_vertice_count = 5;
    private final int m_A_edge_count = 3;
    private final int[][] m_A_vertices;
    private final int[][] m_A_edges;
    private final int m_B_vertice_count = 9;
    private final int m_B_edge_count = 9;
    private final int[][] m_B_vertices;
    private final int[][] m_B_edges;
    private final int m_C_vertice_count = 6;
    private final int m_C_edge_count = 5;
    private final int[][] m_C_vertices;
    private final int[][] m_C_edges;
    private final int m_D_vertice_count = 6;
    private final int m_D_edge_count = 6;
    private final int[][] m_D_vertices;
    private final int[][] m_D_edges;
    private final int m_E_vertice_count = 6;
    private final int m_E_edge_count = 5;
    private final int[][] m_E_vertices;
    private final int[][] m_E_edges;
    private final int m_F_vertice_count = 5;
    private final int m_F_edge_count = 4;
    private final int[][] m_F_vertices;
    private final int[][] m_F_edges;
    private final int m_G_vertice_count = 7;
    private final int m_G_edge_count = 6;
    private final int[][] m_G_vertices;
    private final int[][] m_G_edges;
    private final int m_H_vertice_count = 6;
    private final int m_H_edge_count = 3;
    private final int[][] m_H_vertices;
    private final int[][] m_H_edges;
    private final int m_I_vertice_count = 2;
    private final int m_I_edge_count = 1;
    private final int[][] m_I_vertices;
    private final int[][] m_I_edges;
    private final int m_J_vertice_count = 4;
    private final int m_J_edge_count = 3;
    private final int[][] m_J_vertices;
    private final int[][] m_J_edges;
    private final int m_K_vertice_count = 6;
    private final int m_K_edge_count = 3;
    private final int[][] m_K_vertices;
    private final int[][] m_K_edges;
    private final int m_L_vertice_count = 3;
    private final int m_L_edge_count = 2;
    private final int[][] m_L_vertices;
    private final int[][] m_L_edges;
    private final int m_M_vertice_count = 5;
    private final int m_M_edge_count = 4;
    private final int[][] m_M_vertices;
    private final int[][] m_M_edges;
    private final int m_N_vertice_count = 4;
    private final int m_N_edge_count = 3;
    private final int[][] m_N_vertices;
    private final int[][] m_N_edges;
    private final int m_P_vertice_count = 5;
    private final int m_P_edge_count = 5;
    private final int[][] m_P_vertices;
    private final int[][] m_P_edges;
    private final int m_R_vertice_count = 7;
    private final int m_R_edge_count = 6;
    private final int[][] m_R_vertices;
    private final int[][] m_R_edges;
    private final int m_O_vertice_count = 4;
    private final int m_O_edge_count = 4;
    private final int[][] m_O_vertices;
    private final int[][] m_O_edges;
    private final int m_Q_vertice_count = 6;
    private final int m_Q_edge_count = 5;
    private final int[][] m_Q_vertices;
    private final int[][] m_Q_edges;
    private final int m_S_vertice_count = 8;
    private final int m_S_edge_count = 7;
    private final int[][] m_S_vertices;
    private final int[][] m_S_edges;
    private final int m_T_vertice_count = 4;
    private final int m_T_edge_count = 2;
    private final int[][] m_T_vertices;
    private final int[][] m_T_edges;
    private final int m_U_vertice_count = 4;
    private final int m_U_edge_count = 3;
    private final int[][] m_U_vertices;
    private final int[][] m_U_edges;
    private final int m_V_vertice_count = 3;
    private final int m_V_edge_count = 2;
    private final int[][] m_V_vertices;
    private final int[][] m_V_edges;
    private final int m_W_vertice_count = 5;
    private final int m_W_edge_count = 4;
    private final int[][] m_W_vertices;
    private final int[][] m_W_edges;
    private final int m_X_vertice_count = 4;
    private final int m_X_edge_count = 2;
    private final int[][] m_X_vertices;
    private final int[][] m_X_edges;
    private final int m_Y_vertice_count = 4;
    private final int m_Y_edge_count = 3;
    private final int[][] m_Y_vertices;
    private final int[][] m_Y_edges;
    private final int m_Z_vertice_count = 4;
    private final int m_Z_edge_count = 3;
    private final int[][] m_Z_vertices;
    private final int[][] m_Z_edges;
    
    public void InitializeWireFrameChar(final char c) {
        switch (Character.toUpperCase(c)) {
            case '!': {
                this.m_vertice_count = 5;
                this.m_edge_count = 4;
                this.m_vertices = this.m_exclamation_vertices;
                this.m_edges = this.m_exclamation_edges;
            }
            case '0': {
                this.m_vertice_count = 4;
                this.m_edge_count = 4;
                this.m_vertices = this.m_0_vertices;
                this.m_edges = this.m_0_edges;
            }
            case '1': {
                this.m_vertice_count = 2;
                this.m_edge_count = 1;
                this.m_vertices = this.m_1_vertices;
                this.m_edges = this.m_1_edges;
            }
            case '2': {
                this.m_vertice_count = 8;
                this.m_edge_count = 7;
                this.m_vertices = this.m_2_vertices;
                this.m_edges = this.m_2_edges;
            }
            case '3': {
                this.m_vertice_count = 8;
                this.m_edge_count = 7;
                this.m_vertices = this.m_3_vertices;
                this.m_edges = this.m_3_edges;
            }
            case '4': {
                this.m_vertice_count = 5;
                this.m_edge_count = 3;
                this.m_vertices = this.m_4_vertices;
                this.m_edges = this.m_4_edges;
            }
            case '5': {
                this.m_vertice_count = 8;
                this.m_edge_count = 7;
                this.m_vertices = this.m_5_vertices;
                this.m_edges = this.m_5_edges;
            }
            case '6': {
                this.m_vertice_count = 7;
                this.m_edge_count = 6;
                this.m_vertices = this.m_6_vertices;
                this.m_edges = this.m_6_edges;
            }
            case '7': {
                this.m_vertice_count = 4;
                this.m_edge_count = 3;
                this.m_vertices = this.m_7_vertices;
                this.m_edges = this.m_7_edges;
            }
            case '8': {
                this.m_vertice_count = 6;
                this.m_edge_count = 5;
                this.m_vertices = this.m_8_vertices;
                this.m_edges = this.m_8_edges;
            }
            case '9': {
                this.m_vertice_count = 7;
                this.m_edge_count = 6;
                this.m_vertices = this.m_9_vertices;
                this.m_edges = this.m_9_edges;
            }
            case 'A': {
                this.m_vertice_count = 5;
                this.m_edge_count = 3;
                this.m_vertices = this.m_A_vertices;
                this.m_edges = this.m_A_edges;
            }
            case 'B': {
                this.m_vertice_count = 9;
                this.m_edge_count = 9;
                this.m_vertices = this.m_B_vertices;
                this.m_edges = this.m_B_edges;
            }
            case 'C': {
                this.m_vertice_count = 6;
                this.m_edge_count = 5;
                this.m_vertices = this.m_C_vertices;
                this.m_edges = this.m_C_edges;
            }
            case 'D': {
                this.m_vertice_count = 6;
                this.m_edge_count = 6;
                this.m_vertices = this.m_D_vertices;
                this.m_edges = this.m_D_edges;
            }
            case 'E': {
                this.m_vertice_count = 6;
                this.m_edge_count = 5;
                this.m_vertices = this.m_E_vertices;
                this.m_edges = this.m_E_edges;
            }
            case 'F': {
                this.m_vertice_count = 5;
                this.m_edge_count = 4;
                this.m_vertices = this.m_F_vertices;
                this.m_edges = this.m_F_edges;
            }
            case 'G': {
                this.m_vertice_count = 7;
                this.m_edge_count = 6;
                this.m_vertices = this.m_G_vertices;
                this.m_edges = this.m_G_edges;
            }
            case 'H': {
                this.m_vertice_count = 6;
                this.m_edge_count = 3;
                this.m_vertices = this.m_H_vertices;
                this.m_edges = this.m_H_edges;
            }
            case 'I': {
                this.m_vertice_count = 2;
                this.m_edge_count = 1;
                this.m_vertices = this.m_I_vertices;
                this.m_edges = this.m_I_edges;
            }
            case 'J': {
                this.m_vertice_count = 4;
                this.m_edge_count = 3;
                this.m_vertices = this.m_J_vertices;
                this.m_edges = this.m_J_edges;
            }
            case 'K': {
                this.m_vertice_count = 6;
                this.m_edge_count = 3;
                this.m_vertices = this.m_K_vertices;
                this.m_edges = this.m_K_edges;
            }
            case 'L': {
                this.m_vertice_count = 3;
                this.m_edge_count = 2;
                this.m_vertices = this.m_L_vertices;
                this.m_edges = this.m_L_edges;
            }
            case 'M': {
                this.m_vertice_count = 5;
                this.m_edge_count = 4;
                this.m_vertices = this.m_M_vertices;
                this.m_edges = this.m_M_edges;
            }
            case 'N': {
                this.m_vertice_count = 4;
                this.m_edge_count = 3;
                this.m_vertices = this.m_N_vertices;
                this.m_edges = this.m_N_edges;
            }
            case 'O': {
                this.m_vertice_count = 4;
                this.m_edge_count = 4;
                this.m_vertices = this.m_O_vertices;
                this.m_edges = this.m_O_edges;
            }
            case 'Q': {
                this.m_vertice_count = 6;
                this.m_edge_count = 5;
                this.m_vertices = this.m_Q_vertices;
                this.m_edges = this.m_Q_edges;
            }
            case 'P': {
                this.m_vertice_count = 5;
                this.m_edge_count = 5;
                this.m_vertices = this.m_P_vertices;
                this.m_edges = this.m_P_edges;
            }
            case 'R': {
                this.m_vertice_count = 7;
                this.m_edge_count = 6;
                this.m_vertices = this.m_R_vertices;
                this.m_edges = this.m_R_edges;
            }
            case 'S': {
                this.m_vertice_count = 8;
                this.m_edge_count = 7;
                this.m_vertices = this.m_S_vertices;
                this.m_edges = this.m_S_edges;
            }
            case 'T': {
                this.m_vertice_count = 4;
                this.m_edge_count = 2;
                this.m_vertices = this.m_T_vertices;
                this.m_edges = this.m_T_edges;
            }
            case 'U': {
                this.m_vertice_count = 4;
                this.m_edge_count = 3;
                this.m_vertices = this.m_U_vertices;
                this.m_edges = this.m_U_edges;
            }
            case 'V': {
                this.m_vertice_count = 3;
                this.m_edge_count = 2;
                this.m_vertices = this.m_V_vertices;
                this.m_edges = this.m_V_edges;
            }
            case 'W': {
                this.m_vertice_count = 5;
                this.m_edge_count = 4;
                this.m_vertices = this.m_W_vertices;
                this.m_edges = this.m_W_edges;
            }
            case 'X': {
                this.m_vertice_count = 4;
                this.m_edge_count = 2;
                this.m_vertices = this.m_X_vertices;
                this.m_edges = this.m_X_edges;
            }
            case 'Y': {
                this.m_vertice_count = 4;
                this.m_edge_count = 3;
                this.m_vertices = this.m_Y_vertices;
                this.m_edges = this.m_Y_edges;
            }
            case 'Z': {
                this.m_vertice_count = 4;
                this.m_edge_count = 3;
                this.m_vertices = this.m_Z_vertices;
                this.m_edges = this.m_Z_edges;
            }
            default: {}
        }
    }
    
    public void MoveCharX(final int n) {
        for (int i = 0; i < this.m_vertice_count; ++i) {
            final int[] array = this.m_vertices[i];
            final int n2 = 0;
            array[n2] += n;
        }
    }
    
    public int GetVerticeCount() {
        return this.m_vertice_count;
    }
    
    public WireFrameChar(final char c, final int n) {
        this.m_exclamation_vertices = new int[][] { { -1, 4, 0 }, { 1, 4, 0 }, { 0, -2, 0 }, { 0, -3, 0 }, { 0, -4, 0 } };
        this.m_exclamation_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 0 }, { 3, 4 } };
        this.m_0_vertices = new int[][] { { -2, -4, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, -4, 0 } };
        this.m_0_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } };
        this.m_1_vertices = new int[][] { { 0, -4, 0 }, { 0, 4, 0 } };
        this.m_1_edges = new int[][] { { 0, 1 } };
        this.m_2_vertices = new int[][] { { -2, 2, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, 0, 0 }, { -2, 0, 0 }, { -2, -4, 0 }, { 2, -4, 0 }, { 2, -2, 0 } };
        this.m_2_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 }, { 6, 7 } };
        this.m_3_vertices = new int[][] { { -2, 2, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, 0, 0 }, { -1, 0, 0 }, { -2, -2, 0 }, { -2, -4, 0 }, { 2, -4, 0 } };
        this.m_3_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 5, 6 }, { 6, 7 }, { 7, 3 } };
        this.m_4_vertices = new int[][] { { -2, 4, 0 }, { -2, 0, 0 }, { 2, 0, 0 }, { 2, 4, 0 }, { 2, -4, 0 } };
        this.m_4_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 3, 4 } };
        this.m_5_vertices = new int[][] { { -2, -2, 0 }, { -2, -4, 0 }, { 2, -4, 0 }, { 2, 0, 0 }, { -2, 0, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, 2, 0 } };
        this.m_5_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 }, { 6, 7 } };
        this.m_6_vertices = new int[][] { { 2, 2, 0 }, { 2, 4, 0 }, { -2, 4, 0 }, { -2, -4, 0 }, { 2, -4, 0 }, { 2, 0, 0 }, { -2, 0, 0 } };
        this.m_6_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 } };
        this.m_7_vertices = new int[][] { { -2, 2, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { -1, -4, 0 } };
        this.m_7_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 } };
        this.m_8_vertices = new int[][] { { -2, -4, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, -4, 0 }, { -2, 0, 0 }, { 2, 0, 0 } };
        this.m_8_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 }, { 4, 5 } };
        this.m_9_vertices = new int[][] { { -2, -2, 0 }, { -2, -4, 0 }, { 2, -4, 0 }, { 2, 4, 0 }, { -2, 4, 0 }, { -2, 0, 0 }, { 2, 0, 0 } };
        this.m_9_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 } };
        this.m_A_vertices = new int[][] { { -2, -4, 0 }, { 0, 4, 0 }, { 2, -4, 0 }, { 1, 0, 0 }, { -1, 0, 0 } };
        this.m_A_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 3, 4 } };
        this.m_B_vertices = new int[][] { { -2, 4, 0 }, { 1, 4, 0 }, { 2, 3, 0 }, { 2, 1, 0 }, { 1, 0, 0 }, { 2, -1, 0 }, { 2, -3, 0 }, { 1, -4, 0 }, { -2, -4, 0 } };
        this.m_B_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 }, { 6, 7 }, { 7, 8 }, { 8, 0 } };
        this.m_C_vertices = new int[][] { { 2, 2, 0 }, { 2, 4, 0 }, { -2, 4, 0 }, { -2, -4, 0 }, { 2, -4, 0 }, { 2, -2, 0 } };
        this.m_C_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
        this.m_D_vertices = new int[][] { { -2, 4, 0 }, { 1, 4, 0 }, { 2, 3, 0 }, { 2, -3, 0 }, { 1, -4, 0 }, { -2, -4, 0 } };
        this.m_D_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 0 } };
        this.m_E_vertices = new int[][] { { 2, 4, 0 }, { -2, 4, 0 }, { -2, 0, 0 }, { 1, 0, 0 }, { -2, -4, 0 }, { 2, -4, 0 } };
        this.m_E_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 2, 4 }, { 4, 5 } };
        this.m_F_vertices = new int[][] { { -2, -4, 0 }, { -2, 0, 0 }, { 1, 0, 0 }, { -2, 4, 0 }, { 2, 4, 0 } };
        this.m_F_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 1, 3 }, { 3, 4 } };
        this.m_G_vertices = new int[][] { { 2, 3, 0 }, { 2, 4, 0 }, { -2, 4, 0 }, { -2, -4, 0 }, { 2, -4, 0 }, { 2, -1, 0 }, { 0, -1, 0 } };
        this.m_G_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 } };
        this.m_H_vertices = new int[][] { { -2, -4, 0 }, { -2, 4, 0 }, { -2, 0, 0 }, { 2, 0, 0 }, { 2, 4, 0 }, { 2, -4, 0 } };
        this.m_H_edges = new int[][] { { 0, 1 }, { 2, 3 }, { 4, 5 } };
        this.m_I_vertices = new int[][] { { 0, -4, 0 }, { 0, 4, 0 } };
        this.m_I_edges = new int[][] { { 0, 1 } };
        this.m_J_vertices = new int[][] { { 2, 4, 0 }, { 2, -4, 0 }, { -2, -4, 0 }, { -2, -3, 0 } };
        this.m_J_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 } };
        this.m_K_vertices = new int[][] { { -2, -4, 0 }, { -2, 4, 0 }, { -2, -1, 0 }, { 2, 4, 0 }, { -1, 0, 0 }, { 2, -4, 0 } };
        this.m_K_edges = new int[][] { { 0, 1 }, { 2, 3 }, { 4, 5 } };
        this.m_L_vertices = new int[][] { { -2, 4, 0 }, { -2, -4, 0 }, { 2, -4, 0 } };
        this.m_L_edges = new int[][] { { 0, 1 }, { 1, 2 } };
        this.m_M_vertices = new int[][] { { -2, -4, 0 }, { -2, 4, 0 }, { 0, 2, 0 }, { 2, 4, 0 }, { 2, -4, 0 } };
        this.m_M_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 } };
        this.m_N_vertices = new int[][] { { -2, -4, 0 }, { -2, 4, 0 }, { 2, -4, 0 }, { 2, 4, 0 } };
        this.m_N_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 } };
        this.m_P_vertices = new int[][] { { -2, -4, 0 }, { -2, 0, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, 0, 0 } };
        this.m_P_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 1 } };
        this.m_R_vertices = new int[][] { { -2, -4, 0 }, { -2, 0, 0 }, { 2, -4, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, 0, 0 }, { 0, 0, 0 } };
        this.m_R_edges = new int[][] { { 0, 1 }, { 6, 2 }, { 1, 3 }, { 3, 4 }, { 4, 5 }, { 5, 1 } };
        this.m_O_vertices = new int[][] { { -2, -4, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, -4, 0 } };
        this.m_O_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } };
        this.m_Q_vertices = new int[][] { { -2, -4, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, -4, 0 }, { 0, -2, 0 }, { 1, -5, 0 } };
        this.m_Q_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 }, { 4, 5 } };
        this.m_S_vertices = new int[][] { { -2, -2, 0 }, { -2, -4, 0 }, { 2, -4, 0 }, { 2, 0, 0 }, { -2, 0, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, 2, 0 } };
        this.m_S_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 }, { 6, 7 } };
        this.m_T_vertices = new int[][] { { 0, -4, 0 }, { 0, 4, 0 }, { -2, 4, 0 }, { 2, 4, 0 } };
        this.m_T_edges = new int[][] { { 0, 1 }, { 2, 3 } };
        this.m_U_vertices = new int[][] { { -2, -4, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, -4, 0 } };
        this.m_U_edges = new int[][] { { 0, 1 }, { 2, 3 }, { 3, 0 } };
        this.m_V_vertices = new int[][] { { -2, 4, 0 }, { 0, -4, 0 }, { 2, 4, 0 } };
        this.m_V_edges = new int[][] { { 0, 1 }, { 1, 2 } };
        this.m_W_vertices = new int[][] { { -2, 4, 0 }, { -1, -4, 0 }, { 0, 0, 0 }, { 1, -4, 0 }, { 2, 4, 0 } };
        this.m_W_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 } };
        this.m_X_vertices = new int[][] { { -2, 4, 0 }, { 2, -4, 0 }, { -2, -4, 0 }, { 2, 4, 0 } };
        this.m_X_edges = new int[][] { { 0, 1 }, { 2, 3 } };
        this.m_Y_vertices = new int[][] { { -2, 4, 0 }, { 2, 4, 0 }, { 0, 0, 0 }, { 0, -4, 0 } };
        this.m_Y_edges = new int[][] { { 0, 2 }, { 1, 2 }, { 2, 3 } };
        this.m_Z_vertices = new int[][] { { -2, 4, 0 }, { 2, 4, 0 }, { -2, -4, 0 }, { 2, -4, 0 } };
        this.m_Z_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 } };
        this.InitializeWireFrameChar(c);
        this.MoveCharX(n);
    }
    
    public WireFrameChar(final char c) {
        this.m_exclamation_vertices = new int[][] { { -1, 4, 0 }, { 1, 4, 0 }, { 0, -2, 0 }, { 0, -3, 0 }, { 0, -4, 0 } };
        this.m_exclamation_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 0 }, { 3, 4 } };
        this.m_0_vertices = new int[][] { { -2, -4, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, -4, 0 } };
        this.m_0_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } };
        this.m_1_vertices = new int[][] { { 0, -4, 0 }, { 0, 4, 0 } };
        this.m_1_edges = new int[][] { { 0, 1 } };
        this.m_2_vertices = new int[][] { { -2, 2, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, 0, 0 }, { -2, 0, 0 }, { -2, -4, 0 }, { 2, -4, 0 }, { 2, -2, 0 } };
        this.m_2_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 }, { 6, 7 } };
        this.m_3_vertices = new int[][] { { -2, 2, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, 0, 0 }, { -1, 0, 0 }, { -2, -2, 0 }, { -2, -4, 0 }, { 2, -4, 0 } };
        this.m_3_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 5, 6 }, { 6, 7 }, { 7, 3 } };
        this.m_4_vertices = new int[][] { { -2, 4, 0 }, { -2, 0, 0 }, { 2, 0, 0 }, { 2, 4, 0 }, { 2, -4, 0 } };
        this.m_4_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 3, 4 } };
        this.m_5_vertices = new int[][] { { -2, -2, 0 }, { -2, -4, 0 }, { 2, -4, 0 }, { 2, 0, 0 }, { -2, 0, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, 2, 0 } };
        this.m_5_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 }, { 6, 7 } };
        this.m_6_vertices = new int[][] { { 2, 2, 0 }, { 2, 4, 0 }, { -2, 4, 0 }, { -2, -4, 0 }, { 2, -4, 0 }, { 2, 0, 0 }, { -2, 0, 0 } };
        this.m_6_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 } };
        this.m_7_vertices = new int[][] { { -2, 2, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { -1, -4, 0 } };
        this.m_7_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 } };
        this.m_8_vertices = new int[][] { { -2, -4, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, -4, 0 }, { -2, 0, 0 }, { 2, 0, 0 } };
        this.m_8_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 }, { 4, 5 } };
        this.m_9_vertices = new int[][] { { -2, -2, 0 }, { -2, -4, 0 }, { 2, -4, 0 }, { 2, 4, 0 }, { -2, 4, 0 }, { -2, 0, 0 }, { 2, 0, 0 } };
        this.m_9_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 } };
        this.m_A_vertices = new int[][] { { -2, -4, 0 }, { 0, 4, 0 }, { 2, -4, 0 }, { 1, 0, 0 }, { -1, 0, 0 } };
        this.m_A_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 3, 4 } };
        this.m_B_vertices = new int[][] { { -2, 4, 0 }, { 1, 4, 0 }, { 2, 3, 0 }, { 2, 1, 0 }, { 1, 0, 0 }, { 2, -1, 0 }, { 2, -3, 0 }, { 1, -4, 0 }, { -2, -4, 0 } };
        this.m_B_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 }, { 6, 7 }, { 7, 8 }, { 8, 0 } };
        this.m_C_vertices = new int[][] { { 2, 2, 0 }, { 2, 4, 0 }, { -2, 4, 0 }, { -2, -4, 0 }, { 2, -4, 0 }, { 2, -2, 0 } };
        this.m_C_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
        this.m_D_vertices = new int[][] { { -2, 4, 0 }, { 1, 4, 0 }, { 2, 3, 0 }, { 2, -3, 0 }, { 1, -4, 0 }, { -2, -4, 0 } };
        this.m_D_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 0 } };
        this.m_E_vertices = new int[][] { { 2, 4, 0 }, { -2, 4, 0 }, { -2, 0, 0 }, { 1, 0, 0 }, { -2, -4, 0 }, { 2, -4, 0 } };
        this.m_E_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 2, 4 }, { 4, 5 } };
        this.m_F_vertices = new int[][] { { -2, -4, 0 }, { -2, 0, 0 }, { 1, 0, 0 }, { -2, 4, 0 }, { 2, 4, 0 } };
        this.m_F_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 1, 3 }, { 3, 4 } };
        this.m_G_vertices = new int[][] { { 2, 3, 0 }, { 2, 4, 0 }, { -2, 4, 0 }, { -2, -4, 0 }, { 2, -4, 0 }, { 2, -1, 0 }, { 0, -1, 0 } };
        this.m_G_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 } };
        this.m_H_vertices = new int[][] { { -2, -4, 0 }, { -2, 4, 0 }, { -2, 0, 0 }, { 2, 0, 0 }, { 2, 4, 0 }, { 2, -4, 0 } };
        this.m_H_edges = new int[][] { { 0, 1 }, { 2, 3 }, { 4, 5 } };
        this.m_I_vertices = new int[][] { { 0, -4, 0 }, { 0, 4, 0 } };
        this.m_I_edges = new int[][] { { 0, 1 } };
        this.m_J_vertices = new int[][] { { 2, 4, 0 }, { 2, -4, 0 }, { -2, -4, 0 }, { -2, -3, 0 } };
        this.m_J_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 } };
        this.m_K_vertices = new int[][] { { -2, -4, 0 }, { -2, 4, 0 }, { -2, -1, 0 }, { 2, 4, 0 }, { -1, 0, 0 }, { 2, -4, 0 } };
        this.m_K_edges = new int[][] { { 0, 1 }, { 2, 3 }, { 4, 5 } };
        this.m_L_vertices = new int[][] { { -2, 4, 0 }, { -2, -4, 0 }, { 2, -4, 0 } };
        this.m_L_edges = new int[][] { { 0, 1 }, { 1, 2 } };
        this.m_M_vertices = new int[][] { { -2, -4, 0 }, { -2, 4, 0 }, { 0, 2, 0 }, { 2, 4, 0 }, { 2, -4, 0 } };
        this.m_M_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 } };
        this.m_N_vertices = new int[][] { { -2, -4, 0 }, { -2, 4, 0 }, { 2, -4, 0 }, { 2, 4, 0 } };
        this.m_N_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 } };
        this.m_P_vertices = new int[][] { { -2, -4, 0 }, { -2, 0, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, 0, 0 } };
        this.m_P_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 1 } };
        this.m_R_vertices = new int[][] { { -2, -4, 0 }, { -2, 0, 0 }, { 2, -4, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, 0, 0 }, { 0, 0, 0 } };
        this.m_R_edges = new int[][] { { 0, 1 }, { 6, 2 }, { 1, 3 }, { 3, 4 }, { 4, 5 }, { 5, 1 } };
        this.m_O_vertices = new int[][] { { -2, -4, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, -4, 0 } };
        this.m_O_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } };
        this.m_Q_vertices = new int[][] { { -2, -4, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, -4, 0 }, { 0, -2, 0 }, { 1, -5, 0 } };
        this.m_Q_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 }, { 4, 5 } };
        this.m_S_vertices = new int[][] { { -2, -2, 0 }, { -2, -4, 0 }, { 2, -4, 0 }, { 2, 0, 0 }, { -2, 0, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, 2, 0 } };
        this.m_S_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 }, { 6, 7 } };
        this.m_T_vertices = new int[][] { { 0, -4, 0 }, { 0, 4, 0 }, { -2, 4, 0 }, { 2, 4, 0 } };
        this.m_T_edges = new int[][] { { 0, 1 }, { 2, 3 } };
        this.m_U_vertices = new int[][] { { -2, -4, 0 }, { -2, 4, 0 }, { 2, 4, 0 }, { 2, -4, 0 } };
        this.m_U_edges = new int[][] { { 0, 1 }, { 2, 3 }, { 3, 0 } };
        this.m_V_vertices = new int[][] { { -2, 4, 0 }, { 0, -4, 0 }, { 2, 4, 0 } };
        this.m_V_edges = new int[][] { { 0, 1 }, { 1, 2 } };
        this.m_W_vertices = new int[][] { { -2, 4, 0 }, { -1, -4, 0 }, { 0, 0, 0 }, { 1, -4, 0 }, { 2, 4, 0 } };
        this.m_W_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 } };
        this.m_X_vertices = new int[][] { { -2, 4, 0 }, { 2, -4, 0 }, { -2, -4, 0 }, { 2, 4, 0 } };
        this.m_X_edges = new int[][] { { 0, 1 }, { 2, 3 } };
        this.m_Y_vertices = new int[][] { { -2, 4, 0 }, { 2, 4, 0 }, { 0, 0, 0 }, { 0, -4, 0 } };
        this.m_Y_edges = new int[][] { { 0, 2 }, { 1, 2 }, { 2, 3 } };
        this.m_Z_vertices = new int[][] { { -2, 4, 0 }, { 2, 4, 0 }, { -2, -4, 0 }, { 2, -4, 0 } };
        this.m_Z_edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 } };
        this.InitializeWireFrameChar(c);
    }
    
    public int GetEndEdge(final int n) {
        return this.m_edges[n][1];
    }
    
    public int GetEdgeCount() {
        return this.m_edge_count;
    }
    
    public int GetStartEdge(final int n) {
        return this.m_edges[n][0];
    }
    
    public void MoveCharY(final int n) {
        for (int i = 0; i < this.m_vertice_count; ++i) {
            final int[] array = this.m_vertices[i];
            final int n2 = 1;
            array[n2] += n;
        }
    }
}
