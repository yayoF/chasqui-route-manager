/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasqui.route.tabu;

import java.util.ArrayList;

/**
 *
 * @author Joca
 */
public class TabuSearch {

    protected int maxIteration = 1000, maxTabuSize = 10;
    protected SolutionCandidate sCandidate, bestCandidate, s, sBest;
    protected ArrayList<SolutionCandidate> tabuList = new ArrayList<>();
    protected ArrayList<SolutionCandidate> candidateList = new ArrayList<>();
    protected ArrayList<SolutionCandidate> sNeighborhood = new ArrayList<>();

    public TabuSearch(int maxIteration) {

        this.maxIteration = maxIteration;

    }

    public void execute () {
        int iteration = 0;

        //generate a optimize initial solutioncandidate
        sCandidate = generateInitialSolution();

        while (! this.stoppingCondition(iteration)) {
            candidateList.clear();
            sNeighborhood = generateNeighborhood(sCandidate);

            //obtain best candidate in the neighborhood of solutions generated
            for (SolutionCandidate actualNeighbor : sNeighborhood) {
                if( (! this.tabuList.contains(actualNeighbor) ) &&
                        (actualNeighbor.fitness() > bestCandidate.fitness() ) ) {

                    bestCandidate = actualNeighbor;

                }
            }

            //evaluate best candidate obtained this interation
            if( bestCandidate.fitness() > sBest.fitness() ) {
                sBest = bestCandidate;
            }

            //update Tabu List
            addCandidateTabuList(bestCandidate);

            iteration ++;

        }

    }

    protected void addCandidateTabuList(SolutionCandidate s) {

        this.tabuList.add(s);

        if( this.tabuList.size() > this.maxTabuSize ) {
            tabuList.remove(0);
        }


    }

    protected ArrayList<SolutionCandidate> generateNeighborhood(SolutionCandidate sCandidate) {

        return new ArrayList<SolutionCandidate>();

    }

    protected SolutionCandidate generateInitialSolution() {

        return new SolutionCandidate();

    }

    protected boolean stoppingCondition(int iteration) {

        return iteration >= this.maxIteration;

    }

    protected boolean evaluateFitnessValue(int fitness1, int fitness2) {

       return fitness1 > fitness2;

    }
}
