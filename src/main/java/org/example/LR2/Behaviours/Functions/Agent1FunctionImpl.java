package org.example.LR2.Behaviours.Functions;


public class Agent1FunctionImpl implements Functions{
    @Override
    public double Calculate(double x) {
        return Math.pow(Math.E, ((-0.5) * x)) ;
    }
}
