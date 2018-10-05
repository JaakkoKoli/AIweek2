package week4;

import java.util.List;

public class Model {
    private List<Variable> v;
    public Model(List<Variable> v){
        this.v=v;
    }
    
    public int getVarAmt(){
        return this.v.size();
    }
    
    public List<Variable> getVariables(){
        return this.v;
    }
}
