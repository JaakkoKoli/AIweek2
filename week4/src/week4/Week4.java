package week4;

import java.util.ArrayList;
import java.util.List;

public class Week4 {

    public static void main(String[] args) {
        ex2(1000000);
        // 1. Battery has power when radio works and there's gas, but the car doesn't start
        // result: 1.0
        // 2. The car starts when radio works, ignition works, and there's gas
        // res: around 0.99
        // 3. The car starts when radio doesn't work, but there's gas and ignition works
        // res: around 0.99
        System.out.println("");
        System.out.println("-----");
        System.out.println("");
        ex3(1000000);
        // 1. Approximation for "in what proportion of the times the alarm rings, a burglar breaks into your home"
        // around 0.11
        //
        // 2. Approximation for "in what proportion of the times the alarm rings and there's an earthquake, a burglar also breaks into your home"
        // around 0.003
        // Probabilities do make sense.
        // Higher n = lower variance
    }
    
    private static void ex3(int n){
        int[] p0={};
        double[] d0={110.0/111.0};
        Variable v0=new Variable(p0,d0);
        int[] p1={};
        double[] d1={364.0/365.0};
        Variable v1=new Variable(p1,d1);
        int[] p2={0,1};
        double[] d2={0.9905,0.19,0.08,0.03};
        Variable v2=new Variable(p2,d2);
        ArrayList<Variable> a=new ArrayList<>();
        a.add(v0);
        a.add(v1);
        a.add(v2);
        Model m=new Model(a);
        List<int[]> res = generateTuples(n, m);
        double[] p={0,0};
        double[] t={0,0};
        for(int[] i:res){
            if(i[2]==1){
                t[0]++;
                if(i[1]==1){
                    p[0]++;
                }
            }
            if(i[0]==1&&i[2]==1){
                t[1]++;
                if(i[1]==1){
                    p[1]++;
                }
            }
        }
        double a1=t[0]==0?0:p[0]/t[0];
        double a2=t[1]==0?0:p[1]/t[1];
        System.out.println("P1: "+a1+" -- "+p[0]+" -- "+t[0]);
        System.out.println("P2: "+a2+" -- "+p[1]+" -- "+t[1]);
    }
    
    private static void ex2(int n){
        int[] p0={};
        double[] d0={0.1};
        Variable v0=new Variable(p0,d0);
        int[] p1={0};
        double[] d1={1,0.1};
        Variable v1=new Variable(p1,d1);
        int[] p2={0};
        double[] d2={1,0.05};
        Variable v2=new Variable(p2,d2);
        int[] p3={};
        double[] d3={0.05};
        Variable v3=new Variable(p3,d3);
        int[] p4={2,3};
        double[] d4={1,1,1,0.01};
        Variable v4=new Variable(p4,d4);
        int[] p5={4};
        double[] d5={1,0.01};
        Variable v5=new Variable(p5,d5);
        ArrayList<Variable> a=new ArrayList<>();
        a.add(v0);
        a.add(v1);
        a.add(v2);
        a.add(v3);
        a.add(v4);
        a.add(v5);
        Model m=new Model(a);
        List<int[]> res = generateTuples(n, m);
        double[] p={0,0,0};
        double[] t={0,0,0};
        for(int[] i:res){
            if(i[1]==1&&i[3]==1&&i[4]==0){
                t[0]++;
                if(i[0]==1){
                    p[0]++;
                }
            }
            if(i[1]==1&&i[2]==1&&i[3]==1){
                t[1]++;
                if(i[4]==1){
                    p[1]++;
                }
            }
            if(i[1]==0&&i[2]==1&&i[3]==1){
                t[2]++;
                if(i[4]==1){
                    p[2]++;
                }
            }
        }
        double a1=t[0]==0?0:p[0]/t[0];
        double a2=t[1]==0?0:p[1]/t[1];
        double a3=t[2]==0?0:p[2]/t[2];
        System.out.println("P1: "+a1+" -- "+p[0]+" -- "+t[0]);
        System.out.println("P2: "+a2+" -- "+p[1]+" -- "+t[1]);
        System.out.println("P3: "+a3+" -- "+p[2]+" -- "+t[2]);
    }
    
    private static List<int[]> generateTuples(int n, Model m){
        List<int[]> tuples = new ArrayList<>();
        for(int i=0;i<n;i++){
            int[] tuple = new int[m.getVarAmt()];
            for(int x=0;x<m.getVarAmt();x++){
                tuple[x]=sample(m.getVariables().get(x).getP(tuple));
            }
            tuples.add(tuple);
        }
        return tuples;
    }
    
    private static int sample(double p){
        return Math.random()<p?0:1;
    }
}
