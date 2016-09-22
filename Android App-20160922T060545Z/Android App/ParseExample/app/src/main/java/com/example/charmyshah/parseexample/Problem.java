package com.example.charmyshah.parseexample;

/**
 * Created by charmyshah on 5/5/15.
 */
public class Problem {
    private String problem;

    Problem(String prob){
        problem = prob;
    }

    public String getProblem() {
        return problem;
    }
    public void setProblem(String problem) {
        this.problem = problem;
    }

    @Override
    public String toString() {
        return this.getProblem();
    }
}
