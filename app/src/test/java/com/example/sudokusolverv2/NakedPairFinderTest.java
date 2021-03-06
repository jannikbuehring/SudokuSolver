package com.example.sudokusolverv2;

import com.example.sudokusolverv2.solutionStrategies.nakedSubset.NakedPair;
import com.example.sudokusolverv2.solutionStrategies.nakedSubset.NakedPairFinder;

import junit.framework.TestCase;

public class NakedPairFinderTest extends TestCase {

    public void testGetNakedPairInRow() {
        Solver solver = new Solver();
        NakedPairFinder nakedPairFinder = new NakedPairFinder();
        nakedPairFinder.setSolver(solver);
        solver.board = new int[][]{
                {0, 0, 0, 1, 3, 7, 0, 0, 0},
                {7, 0, 0, 5, 9, 6, 1, 3, 0},
                {0, 0, 9, 0, 8, 0, 0, 6, 0},
                {0, 0, 3, 0, 2, 0, 0, 0, 0},
                {5, 0, 0, 8, 0, 0, 9, 2, 0},
                {0, 2, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 8},
                {8, 7, 4, 0, 0, 0, 0, 0, 0},
                {0, 6, 5, 3, 4, 8, 0, 0, 0}
        };
        solver.calculateInitialCandidates();
        NakedPair nakedPair = nakedPairFinder.getNakedPairInRow();

        //Naked pair is in row 2
        assertEquals(nakedPair.field1.row, 2);
        assertEquals(nakedPair.field2.row, 2);

        //And in columns 3 and 5
        assertEquals(nakedPair.field1.column, 3);
        assertEquals(nakedPair.field2.column, 5);
    }

    public void testGetNakedPairInColumn() {
        Solver solver = new Solver();
        NakedPairFinder nakedPairFinder = new NakedPairFinder();
        nakedPairFinder.setSolver(solver);
        solver.board = new int[][]{
                {0, 0, 1, 4, 9, 0, 7, 0, 0},
                {0, 0, 4, 0, 0, 6, 9, 0, 0},
                {8, 0, 9, 0, 1, 7, 5, 4, 0},
                {2, 8, 0, 0, 7, 0, 0, 5, 9},
                {4, 1, 7, 9, 0, 0, 2, 6, 8},
                {5, 9, 0, 0, 2, 0, 0, 0, 0},
                {0, 4, 5, 0, 8, 0, 0, 0, 3},
                {0, 0, 8, 7, 0, 0, 0, 0, 0},
                {0, 0, 2, 0, 0, 9, 8, 0, 0}
        };
        solver.calculateInitialCandidates();
        NakedPair nakedPair = nakedPairFinder.getNakedPairInColumn();

        //Naked pair is in col 4
        assertEquals(nakedPair.field1.column, 4);
        assertEquals(nakedPair.field2.column, 4);

        //And in rows 1 and 4
        assertEquals(nakedPair.field1.row, 1);
        assertEquals(nakedPair.field2.row, 4);
    }

    public void testGetNakedPairInBlock() {
        Solver solver = new Solver();
        NakedPairFinder nakedPairFinder = new NakedPairFinder();
        nakedPairFinder.setSolver(solver);
        solver.board = new int[][]{
                {0, 0, 1, 4, 9, 0, 7, 0, 0},
                {0, 0, 4, 0, 0, 6, 9, 0, 0},
                {8, 0, 9, 0, 1, 7, 5, 4, 0},
                {2, 8, 0, 0, 7, 0, 0, 5, 9},
                {4, 1, 7, 9, 0, 0, 2, 6, 8},
                {5, 9, 0, 0, 2, 0, 0, 0, 0},
                {0, 4, 5, 0, 8, 0, 0, 0, 3},
                {0, 0, 8, 7, 0, 0, 0, 0, 0},
                {0, 0, 2, 0, 0, 9, 8, 0, 0}
        };
        solver.calculateInitialCandidates();
        NakedPair nakedPair = nakedPairFinder.getNakedPairInBlock();

        //Naked pair is in col 4
        assertEquals(nakedPair.field1.column, 8);
        assertEquals(nakedPair.field2.column, 8);

        //And in rows 1 and 4
        assertEquals(nakedPair.field1.row, 0);
        assertEquals(nakedPair.field2.row, 2);
    }

    public void testApplyNakedPairToCandidates() {
        Solver solver = new Solver();
        NakedPairFinder nakedPairFinder = new NakedPairFinder();
        nakedPairFinder.setSolver(solver);
        solver.board = new int[][]{
                {0, 0, 0, 1, 3, 7, 0, 0, 0},
                {7, 0, 0, 5, 9, 6, 1, 3, 0},
                {0, 0, 9, 0, 8, 0, 0, 6, 0},
                {0, 0, 3, 0, 2, 0, 0, 0, 0},
                {5, 0, 0, 8, 0, 0, 9, 2, 0},
                {0, 2, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 8},
                {8, 7, 4, 0, 0, 0, 0, 0, 0},
                {0, 6, 5, 3, 4, 8, 0, 0, 0}
        };
        solver.calculateInitialCandidates();
        nakedPairFinder.applyNakedPairToRow();
        assertEquals(solver.calculatedCandidates.get(18).size(), 2);
    }
}
