package com.example.poker_randomizer;

import java.util.ArrayList;
import java.util.List;

public class ResultHand {
	
	private static final String HIGH_CARD="0";
	private static final String PAIR="1";
	private static final String DOUBLE_PAIR="2";
	private static final String TRIPLE="3";
	private static final String STRAIGHT="4";
	private static final String FLUSH="5";
	private static final String FULL="6";
	private static final String POKER="7";
	
	
	private String typeHand;
	private List<Integer> value;
	
	
	
	public ResultHand(String typeHand, Integer value) {
		this.typeHand = typeHand;
		this.value=new ArrayList<Integer>();
	}
	
	public ResultHand() {
		this.value=new ArrayList<Integer>();
		
	}

	public String getTypeHand() {
		return typeHand;
	}
	public void setTypeHand(String typeHand) {
		this.typeHand = typeHand;
	}
	public List<Integer> getValue() {
		return value;
	}
	
	public int getValue(int pos) {
		return value.get(pos);
	}
	
	public void setValue(int value) {
		this.value.add(value);
	}
	
	
	

}
