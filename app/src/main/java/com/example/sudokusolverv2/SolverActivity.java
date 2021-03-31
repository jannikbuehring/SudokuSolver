package com.example.sudokusolverv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sudokusolverv2.solutionStrategies.HiddenSingle;
import com.example.sudokusolverv2.solutionStrategies.NakedSingle;

import java.util.Timer;
import java.util.TimerTask;

public class SolverActivity extends AppCompatActivity {

    private SudokuBoard gameBoard;
    private Solver gameBoardSolver;
    private final NakedSingle nakedSingle = new NakedSingle();
    private final HiddenSingle hiddenSingle = new HiddenSingle();

    private boolean editCandidatesButtonSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver);
        Bundle b = getIntent().getExtras();
        int[][] board = (int[][]) b.getSerializable("Board");
        gameBoard = findViewById(R.id.SudokuSolvingBoard);
        gameBoardSolver = gameBoard.getSolver();
        gameBoardSolver.board = board;
        gameBoard.invalidate();
        gameBoardSolver.fixNumbers();
        nakedSingle.setSolver(gameBoardSolver);
        hiddenSingle.setSolver(gameBoardSolver);
    }

    public void BTNOnePress(View view) {
        if (this.editCandidatesButtonSelected) {
            gameBoardSolver.setCandidatePos(1);
            gameBoard.invalidate();
        } else {
            gameBoardSolver.setNumberPos(1);
            gameBoardSolver.updatePersonalCandidates();
            gameBoard.invalidate();
        }
    }

    public void BTNTwoPress(View view) {
        if (this.editCandidatesButtonSelected) {
            gameBoardSolver.setCandidatePos(2);
            gameBoard.invalidate();
        } else {
            gameBoardSolver.setNumberPos(2);
            gameBoardSolver.updatePersonalCandidates();
            gameBoard.invalidate();
        }
    }

    public void BTNThreePress(View view) {
        if (this.editCandidatesButtonSelected) {
            gameBoardSolver.setCandidatePos(3);
            gameBoard.invalidate();
        } else {
            gameBoardSolver.setNumberPos(3);
            gameBoardSolver.updatePersonalCandidates();
            gameBoard.invalidate();
        }
    }

    public void BTNFourPress(View view) {
        if (this.editCandidatesButtonSelected) {
            gameBoardSolver.setCandidatePos(4);
            gameBoard.invalidate();
        } else {
            gameBoardSolver.setNumberPos(4);
            gameBoardSolver.updatePersonalCandidates();
            gameBoard.invalidate();
        }
    }

    public void BTNFivePress(View view) {
        if (this.editCandidatesButtonSelected) {
            gameBoardSolver.setCandidatePos(5);
            gameBoard.invalidate();
        } else {
            gameBoardSolver.setNumberPos(5);
            gameBoardSolver.updatePersonalCandidates();
            gameBoard.invalidate();
        }
    }

    public void BTNSixPress(View view) {
        if (this.editCandidatesButtonSelected) {
            gameBoardSolver.setCandidatePos(6);
            gameBoard.invalidate();
        } else {
            gameBoardSolver.setNumberPos(6);
            gameBoardSolver.updatePersonalCandidates();
            gameBoard.invalidate();
        }
    }

    public void BTNSevenPress(View view) {
        if (this.editCandidatesButtonSelected) {
            gameBoardSolver.setCandidatePos(7);
            gameBoard.invalidate();
        } else {
            gameBoardSolver.setNumberPos(7);
            gameBoardSolver.updatePersonalCandidates();
            gameBoard.invalidate();
        }
    }

    public void BTNEightPress(View view) {
        if (this.editCandidatesButtonSelected) {
            gameBoardSolver.setCandidatePos(8);
            gameBoard.invalidate();
        } else {
            gameBoardSolver.setNumberPos(8);
            gameBoardSolver.updatePersonalCandidates();
            gameBoard.invalidate();
        }
    }

    public void BTNNinePress(View view) {
        if (this.editCandidatesButtonSelected) {
            gameBoardSolver.setCandidatePos(9);
            gameBoard.invalidate();
        } else {
            gameBoardSolver.setNumberPos(9);
            gameBoardSolver.updatePersonalCandidates();
            gameBoard.invalidate();
        }
    }

    public void editCandidatesButtonPress(View view) {
        Button editCandidatesButton = findViewById(R.id.editCandidatesButton);
        if (editCandidatesButton.isSelected()) {
            editCandidatesButton.setSelected(false);

            this.editCandidatesButtonSelected = false;
        } else {
            editCandidatesButton.setSelected(true);

            this.editCandidatesButtonSelected = true;
        }

    }

    public void tipButtonPress(View view) {
        if (nakedSingle.getNakedSingleLocation() != null) {
            int[] pos = nakedSingle.getNakedSingleLocation();
            int row = pos[0];
            int column = pos[1];
            BlockLocation tipLocationBlock = gameBoardSolver.calculateTipLocationBlock(row, column);
            gameBoard.setTipLocation(tipLocationBlock);
            gameBoard.invalidate();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    gameBoard.setTipLocation(null);
                    gameBoard.invalidate();
                }
            }, 3000);

            showTooltip("Probiere es hier mit der Naked Single Strategie", 5000);
        } else {
            gameBoard.setTipLocation(null);
            gameBoard.invalidate();

            showTooltip("Kein Tipp verfügbar", 3000);
        }
    }

    public void showAllCandidatesButtonPress(View view) {
        Button showAllCandidatesButton = findViewById(R.id.showAllCandidatesButton);
        if (showAllCandidatesButton.isSelected()) {
            showAllCandidatesButton.setSelected(false);
            gameBoard.setShowAllCandidates(false);
        } else {
            showAllCandidatesButton.setSelected(true);
            gameBoard.setShowAllCandidates(true);
        }
    }

    public void solutionButtonPress(View view) {
        if (nakedSingle.getNakedSingleLocation() != null) {
            nakedSingle.enterNakedSingle();

            showTooltip("Auf dieses Feld konnte die Naked Single Strategie angewendet werden", 5000);
        }
        else if (hiddenSingle.getHiddenSingleLocation() != null) {
            hiddenSingle.enterHiddenSingle();

            showTooltip("Auf dieses Feld konnte die Hidden Single Strategie angewendet werden", 5000);
        }
        else {
            showTooltip("Aktuell keine Lösungsstrategie verfügbar", 5000);
        }
    }

    private void showTooltip(String message, int duration) {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, message, duration);
        toast.setGravity(Gravity.BOTTOM, 0, 200);
        toast.show();
    }
}