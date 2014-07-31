package com.example.poker_randomizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NumberPlayerActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_number_players);
		addButtonListener();
	}

	private void addButtonListener() {
		Button btn_two_players = (Button) findViewById(R.id.btn_two_players);
		btn_two_players.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(NumberPlayerActivity.this,MainActivity.class));
			}
		});
		Button btn_three_players = (Button) findViewById(R.id.btn_three_players);
		btn_three_players.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(NumberPlayerActivity.this,ThreePlayersActivity.class));
			}
		});
		Button btn_four_players = (Button) findViewById(R.id.btn_four_players);
		btn_four_players.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(NumberPlayerActivity.this,FourPlayersActivity.class));
			}
		});
	}
}
