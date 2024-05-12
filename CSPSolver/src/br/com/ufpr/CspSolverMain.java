package br.com.ufpr;
import java.io.IOException; // Correct import statement
import java.util.ArrayList;
import java.util.*;

import br.com.ufpr.Input;

public class CspSolverMain {
    private List<Constraint> constraintList = new ArrayList<>();
    private List<Variable> variables = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        Input input = new Input();
        input.lerArquivo();

        CspSolverMain cspSolverMain = new CspSolverMain();
    }

    public boolean backTrack(Map<Variable, Integer> assigment){
        if(isComplete(assigment)){
            return true;
        }
        Variable var = selectUnassignedVariable(assigment);
        if(var != null){
            for(Integer value : var.getDomain()){
                if(isConsistent(var, value, assigment)){
                    assigment.put(var, value);
                    if(backTrack(assigment)){
                        return true;
                    }
                    assigment.remove(var);
                }
            }
        }
        return false;
    }

    private Variable selectUnassignedVariable(Map<Variable, Integer> assigment){
        for(Variable var : variables){
            if(!assigment.containsKey(var)){
                return var;
            }
        }
        return null;
    }

    private boolean isComplete(Map<Variable, Integer> assigment){
        return assigment.size() == variables.size();
    }

    private boolean isConsistent(Variable var, Integer value, Map<Variable, Integer> assigment){
        for(Constraint constraint : constraintList){
            if(constraint.getVariables().contains(var)){
                if(!constraint.isSatisfied(assigment)){
                    return false;
                }
            }
        }
        return true;
    }
}