package br.com.ufpr;
import java.util.*;
import java.util.stream.Collectors;

public class Constraint {
    private List<Variable> variables;
    private ConstraintType constraintType;
    private List<List<Integer>> tuples;


    public Constraint(List<Variable> variables, ConstraintType constraintType, List<List<Integer>> tuples){
        this.variables = variables;
        this.constraintType = constraintType;
        this.tuples = tuples;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public boolean isSatisfied(Map<Variable, Integer> assigment) {
        if(!allVariablesAreAssigned(assigment)){
            return true;
        }

        List<Integer> currentValues = getCurrentValues(assigment);

        switch(constraintType){
            case ALL_DIFERENT:
                return allValuesDifferent(currentValues);
                break;
            case EQUAL:
                return allValuesEqual(currentValues);
                break;
            case GREATHER_THAN:
                return valuesGreatherThan(currentValues);
                break;
            case LESS_THAN:
                return valuesLessThan(currentValues);
                break;
            default:
                throw new IllegalArgumentException("Invalid constraint type");
        }

        }

        private boolean allVariablesAssigned(Map<Variable, Integer> assigment){
            return variables.stream().allMatch(assigment::containsKey);
       }

       private List<Integer> getCurrentValues(Map<Variable, Integer> assigment){
           return variables.stream().map(assigment::get).collect(Collectors.toList());
       }

       private boolean allValuesDifferent(List<Integer> values){
            Set<Integer> uniqueValues = new HashSet<>(values);
            return uniqueValues.size() == values.size();
       }

       private boolean allValuesEqual(List<Integer> values){
          return new HashSet<>(values).size() == 1;
       }




}
