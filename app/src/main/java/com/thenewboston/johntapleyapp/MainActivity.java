package com.thenewboston.johntapleyapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    int playerOne = 100;
    int playerTwo = 100;
    public static final String SAVE = "mySavedGameFile";
    //public static final String PLAYER1_HEALTH ="savedPlayerOneHealth";
    //public static final String PLAYER2_HEALTH ="savedPlayerTwoHealth";

    TextView playerOneHealth, playerTwoHealth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);

        playerOneHealth = (TextView) findViewById(R.id.txtViewPlayerOneHealth);
        playerTwoHealth = (TextView) findViewById(R.id.txtViewPlayerTwoHealth);

        playerOneHealth.setText(String.valueOf(playerOne));

        playerTwoHealth.setText(String.valueOf(playerTwo));
    }

    public void playerOnePunch(View view) {
        playerTwo = playerTwo - 3;
        playerTwoHealth.setText(String.valueOf(playerTwo));
        checkPlayerTwoHealth();
    }

    public void playerOneKick(View view) {
        playerTwo = playerTwo - 5;
        playerTwoHealth.setText(String.valueOf(playerTwo));
        checkPlayerTwoHealth();
    }

    public void playerTwoPunch(View view) {
        playerOne = playerOne - 4;
        playerOneHealth.setText(String.valueOf(playerOne));
        checkPlayerOneHealth();
    }

    public void playerTwoKick(View view) {
        playerOne = playerOne - 5;
        playerOneHealth.setText(String.valueOf(playerOne));
        checkPlayerOneHealth();
    }

    private void checkPlayerTwoHealth() {
        ImageView facePlayerTwo = (ImageView) findViewById(R.id.imagePlayerTwo);

        if (playerTwo < 50) {
            facePlayerTwo.setImageResource(R.drawable.player5);

        } else {
            facePlayerTwo.setImageResource(R.drawable.player2);
        }
    }

    private void checkPlayerOneHealth() {
        ImageView facePlayerOne = (ImageView) findViewById(R.id.imagePlayerOne);
        if (playerOne < 50) {
            facePlayerOne.setImageResource(R.drawable.player6);
        } else {
            facePlayerOne.setImageResource(R.drawable.player3);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){

            case R.id.menu_save:
                SharedPreferences saveGame = getSharedPreferences( SAVE , MODE_PRIVATE);
                SharedPreferences.Editor editor = saveGame.edit();
                editor.putInt("savedPlayerOneHealth" , playerOne).putInt("savedPlayerTwoHealth" , playerTwo);
                editor.apply();

                Toast.makeText(getApplicationContext() ," Now It's saved Now!" ,Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_load:
                SharedPreferences loadGame = getSharedPreferences(SAVE , 0);
                playerOne = loadGame.getInt("savedPlayerOneHealth" , 0);
                playerTwo = loadGame.getInt("savedPlayerTwoHealth" , 0);

                playerOneHealth.setText(String.valueOf(playerOne));
                playerTwoHealth.setText(String.valueOf(playerTwo));
                checkPlayerOneHealth();
                checkPlayerTwoHealth();

                Toast.makeText(getApplicationContext() ," Now It's loaded successfully !" ,Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_new:
                playerOne = 100;
                playerTwo = 100;
                checkPlayerOneHealth();
                checkPlayerTwoHealth();
                playerOneHealth.setText(String.valueOf(playerOne));
                playerTwoHealth.setText(String.valueOf(playerTwo));

                Toast.makeText(getApplicationContext() ," Now It's New Game!" ,Toast.LENGTH_SHORT).show();
                break;
        }

        /*if (id == R.id.action_menu) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}
