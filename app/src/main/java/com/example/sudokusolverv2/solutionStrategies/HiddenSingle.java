package com.example.sudokusolverv2.solutionStrategies;

import com.example.sudokusolverv2.Solver;

import java.util.ArrayList;
import java.util.HashSet;

public class HiddenSingle {

    private Solver solver;

    public void enterHiddenSingle() {
        int[] pos = getHiddenSingleLocation();
        if (pos != null) {
            int row = pos[0];
            int column = pos[1];
            int solution = pos[2];
            solver.setSelectedRow(row + 1);
            solver.setSelectedColumn(column + 1);
            solver.setNumberPos(solution);
        }
    }

    public int[] getHiddenSingleLocation() {
        int[] pos;

        // Check for hidden singles in rows first
        for (int r = 0; r < 9; r++) {
            if (getHiddenSingleRowLocation(r) != null) {
                pos = getHiddenSingleRowLocation(r);
                return pos;
            }
        }

        // Check for hidden singles in columns
        for (int c = 0; c < 9; c++) {
            if (getHiddenSingleColLocation(c) != null) {
                pos = getHiddenSingleColLocation(c);
                return pos;
            }
        }
        return null;
    }

    public boolean enterHiddenSingleCol(int col) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>(9);
        for (int cap = 0; cap < 9; cap++) {
            list.add(new ArrayList<>());
        }
        for (int r = 0; r < 9; r++) {
            if (solver.board[r][col] == 0) {
                HashSet<Integer> candidates = solver.getCandidates(r, col);
                if (candidates != null) {
                    for (int i : candidates) {
                        list.get(i - 1).add(i);
                    }
                }
            }
        }
        int hiddenSingle = 0;
        for (int i = 0; i < 9; i++) {
            if (list.get(i).size() == 1) {
                hiddenSingle = list.get(i).get(0);
                break;
            }
        }
        if (hiddenSingle > 0) {
            for (int r = 0; r < 9; r++) {
                if (solver.board[r][col] == 0) {
                    HashSet<Integer> candidates = solver.getCandidates(r, col);
                    if (candidates != null) {
                        for (int i : candidates) {
                            if (i == hiddenSingle) {
                                solver.board[r][col] = i;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    // Return the location (row, column) of a hidden single in the row
    public int[] getHiddenSingleRowLocation(int row) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>(9);
        for (int cap = 0; cap < 9; cap++) {
            list.add(new ArrayList<>());
        }
        for (int c = 0; c < 9; c++) {
            if (solver.board[row][c] == 0) {
                HashSet<Integer> candidates = solver.getCandidates(row, c);
                if (candidates != null) {
                    for (int i : candidates) {
                        list.get(i - 1).add(i);
                    }
                }
            }
        }
        int hiddenSingle = 0;
        for (int i = 0; i < 9; i++) {
            if (list.get(i).size() == 1) {
                hiddenSingle = list.get(i).get(0);
                break;
            }
        }
        if (hiddenSingle > 0) {
            for (int c = 0; c < 9; c++) {
                if (solver.board[row][c] == 0) {
                    HashSet<Integer> candidates = solver.getCandidates(row, c);
                    if (candidates != null) {
                        for (int i : candidates) {
                            if (i == hiddenSingle) {
                                int[] pos = new int[3];
                                pos[0] = row;
                                pos[1] = c;
                                pos[2] = hiddenSingle;
                                return pos;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    // Return the location (row, column) of a hidden single in the column
    public int[] getHiddenSingleColLocation(int col) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>(9);
        for (int cap = 0; cap < 9; cap++) {
            list.add(new ArrayList<>());
        }
        for (int r = 0; r < 9; r++) {
            if (solver.board[r][col] == 0) {
                HashSet<Integer> candidates = solver.getCandidates(r, col);
                if (candidates != null) {
                    for (int i : candidates) {
                        list.get(i - 1).add(i);
                    }
                }
            }
        }
        int hiddenSingle = 0;
        for (int i = 0; i < 9; i++) {
            if (list.get(i).size() == 1) {
                hiddenSingle = list.get(i).get(0);
                break;
            }
        }
        if (hiddenSingle > 0) {
            for (int r = 0; r < 9; r++) {
                if (solver.board[r][col] == 0) {
                    HashSet<Integer> candidates = solver.getCandidates(r, col);
                    if (candidates != null) {
                        for (int i : candidates) {
                            if (i == hiddenSingle) {
                                int[] pos = new int[3];
                                pos[0] = r;
                                pos[1] = col;
                                pos[2] = hiddenSingle;
                                return pos;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public void setSolver(Solver solver) {
        this.solver = solver;
    }
}