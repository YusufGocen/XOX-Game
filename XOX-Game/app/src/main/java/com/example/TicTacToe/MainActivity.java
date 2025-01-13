package com.example.TicTacToe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button[][] buttons = new Button[3][3];
    private boolean Oyuncu1Sırası = true;
    private int roundCount;
    private int Oyuncu1Puan;
    private int Oyuncu2Puan;
    private TextView Oyuncu1Skor;
    private TextView Oyuncu2Skor;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Oyuncu1Skor = findViewById(R.id.text_view_p1);
        Oyuncu2Skor = findViewById(R.id.text_view_p2);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }
    @Override

    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (Oyuncu1Sırası) {
            ((Button) v).setText("0");
        } else {
            ((Button) v).setText("X");
        }
        roundCount++;
        if (KimKazandı()) {
            if (Oyuncu1Sırası) {
                Oyuncu1Kazandı();
            } else {
                Oyuncu2Kazandı();
            }
        } else if (roundCount == 9) {
            Berabere();
        } else {
            Oyuncu1Sırası = !Oyuncu1Sırası;
        }
    }
    private boolean KimKazandı() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }
    private void Oyuncu1Kazandı() {
        Oyuncu1Puan++;
        Toast.makeText(this, "Tebrikler!! Oyuncu 1 Kazandı ", Toast.LENGTH_SHORT).show();
        SkorGüncelleme();
        resetBoard();
    }
    private void Oyuncu2Kazandı() {
        Oyuncu2Puan++;
        Toast.makeText(this, "Tebrikler!! Oyuncu 2 Kazandı", Toast.LENGTH_SHORT).show();
        SkorGüncelleme();
        resetBoard();
    }
    private void Berabere() {
        Toast.makeText(this, "Berabere!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void SkorGüncelleme() {
        Oyuncu1Skor.setText("Oyuncu 1: " + Oyuncu1Puan);
        Oyuncu2Skor.setText("Oyuncu 2: " + Oyuncu2Puan);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        Oyuncu1Sırası = true;
    }

    private void resetGame() {
        Oyuncu1Puan = 0;
        Oyuncu2Puan = 0;
        SkorGüncelleme();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount", roundCount);
        outState.putInt("Oyuncu1Puan", Oyuncu1Puan);
        outState.putInt("Oyuncu2Puan", Oyuncu2Puan);
        outState.putBoolean("Oyuncu1Sırası", Oyuncu1Sırası);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundCount = savedInstanceState.getInt("roundCount");
        Oyuncu1Puan = savedInstanceState.getInt("Oyuncu1Puan");
        Oyuncu2Puan = savedInstanceState.getInt("Oyuncu2Puan");
        Oyuncu1Sırası = savedInstanceState.getBoolean("Oyuncu1Sırası");
    }
    }
