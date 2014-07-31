package com.example.poker_randomizer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class StatisticResultActivity extends ActionBarActivity {
	SharedPreferences prefs = getPreferences(MODE_PRIVATE);
	private final static String data_file_two = "hands_results.txt";
	private final static String data_file_three = "three_hands_results.txt";
	private final static String data_file_four = "four_hands_results.txt";
	private static String NUM_PLAYERS = "num_players";
	Gson gson = new GsonBuilder().create();
	HandRecorder hand_recorder;

	protected void onCreate(Bundle savedInstanceState) {
		hand_recorder = new HandRecorder();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statistics_layout);
		try {
			if (prefs.getInt(NUM_PLAYERS, 0) == 2) {
				hand_recorder = readHandRecorderFromFile(data_file_two);
			}else if(prefs.getInt(NUM_PLAYERS, 0) == 3){
				hand_recorder = readHandRecorderFromFile(data_file_three);
			}else{
				hand_recorder = readHandRecorderFromFile(data_file_four);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		displayHandRecorder();
	}

	private void displayHandRecorder() {

		TextView lh_txt = (TextView) findViewById(R.id.lh_txt);
		TextView ll_txt = (TextView) findViewById(R.id.ll_txt);
		TextView hh_txt = (TextView) findViewById(R.id.hh_txt);
		TextView lp_txt = (TextView) findViewById(R.id.lp_txt);
		TextView hp_txt = (TextView) findViewById(R.id.hp_txt);
		TextView high_card_txt = (TextView) findViewById(R.id.total_highcard_txt);
		TextView pair_txt = (TextView) findViewById(R.id.total_pair_txt);
		TextView double_pair_txt = (TextView) findViewById(R.id.total_double_pair_txt);
		TextView triple_txt = (TextView) findViewById(R.id.total_triple_txt);
		TextView straight_txt = (TextView) findViewById(R.id.total_straight_txt);
		TextView full_txt = (TextView) findViewById(R.id.total_full_txt);
		TextView poker_txt = (TextView) findViewById(R.id.total_poker_txt);

		float percent_win_lh = hand_recorder.getHands().get("lh").getWon()
				/ hand_recorder.getNumber_hands() * 100;
		float percent_lose_lh = hand_recorder.getHands().get("lh").getLose()
				/ hand_recorder.getNumber_hands() * 100;
		lh_txt.setText("LOW & HIGH: WON " + percent_win_lh + "% LOSE: "
				+ percent_lose_lh + "%");

		float percent_win_ll = hand_recorder.getHands().get("ll").getWon()
				/ hand_recorder.getNumber_hands() * 100;
		float percent_lose_ll = hand_recorder.getHands().get("ll").getLose()
				/ hand_recorder.getNumber_hands() * 100;
		ll_txt.setText("LOW & LOW: WON" + percent_win_ll + "% LOSE: "
				+ percent_lose_ll + "%");

		float percent_win_hh = hand_recorder.getHands().get("hh").getWon()
				/ hand_recorder.getNumber_hands() * 100;
		float percent_lose_hh = hand_recorder.getHands().get("hh").getLose()
				/ hand_recorder.getNumber_hands() * 100;
		hh_txt.setText("HIGH & HIGH: WON" + percent_win_hh + "% LOSE: "
				+ percent_lose_hh + "%");

		float percent_win_lp = hand_recorder.getHands().get("lp").getWon()
				/ hand_recorder.getNumber_hands() * 100;
		float percent_lose_lp = hand_recorder.getHands().get("lp").getLose()
				/ hand_recorder.getNumber_hands() * 100;
		lp_txt.setText("LOW PAIR: WON" + percent_win_lp + "% LOSE: "
				+ percent_lose_lp + "%");

		float percent_win_hp = hand_recorder.getHands().get("hp").getWon()
				/ hand_recorder.getNumber_hands() * 100;
		float percent_lose_hp = hand_recorder.getHands().get("hp").getLose()
				/ hand_recorder.getNumber_hands() * 100;
		high_card_txt.setText("" + hand_recorder.getNumber_high_card() + "/"
				+ hand_recorder.getNumber_hands());
		high_card_txt.setBackgroundColor(Color.GREEN);
		double_pair_txt.setText("" + hand_recorder.getNumber_double_pair()
				+ "/" + hand_recorder.getNumber_hands());
		double_pair_txt.setBackgroundColor(Color.YELLOW);
		pair_txt.setText("" + hand_recorder.getNumber_pair() + "/"
				+ hand_recorder.getNumber_hands());
		pair_txt.setBackgroundColor(Color.GREEN);
		triple_txt.setText("" + hand_recorder.getNumber_triple() + "/"
				+ hand_recorder.getNumber_hands());
		straight_txt.setText("" + hand_recorder.getNumber_straight() + "/"
				+ hand_recorder.getNumber_hands());
		full_txt.setText("" + hand_recorder.getNumber_full() + "/"
				+ hand_recorder.getNumber_hands());
		poker_txt.setText("" + hand_recorder.getNumber_poker() + "/"
				+ hand_recorder.getNumber_hands());

	}

	private HandRecorder readHandRecorderFromFile(String data_file) throws IOException {

		FileInputStream fis = openFileInput(data_file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String strJson = br.readLine();

		HandRecorder handRecorder = loadJsonToHandRecorder(strJson);

		br.close();
		return handRecorder;
	}

	private HandRecorder loadJsonToHandRecorder(String strJson) {

		HandRecorder handRecorder = gson.fromJson(strJson, HandRecorder.class);
		return handRecorder;

	}

}
