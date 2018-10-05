package week4;

public class Variable {

    private int[] parents;
    private double cpt[];
    public Variable(int[] parents, double[] cpt){
        this.parents=parents;
        this.cpt=cpt;
    }
    
    public int[] getParents(){
        return this.parents;
    }
    
    public double[] getCPT(){
        return this.cpt;
    }
    
    public double getP(int[] v){
        String s = "";
        for(int i=0;i<this.parents.length;i++){
            s+=v[this.parents[i]];
        }
        return parents.length>0?this.cpt[Integer.parseUnsignedInt(s, 2)]:this.cpt[0];
    }
}
