package com.example.poker_randomizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;



public class HandResulter {
	List<Card> cards_boards;
	List<Card> cards_player1;
	List<Card> cards_player2;
	
	private static final String HIGH_CARD="0";
	private static final String PAIR="1";
	private static final String TRIPLE="3";
	private static final String DOUBLE_PAIR="2";
	private static final String FULL="5";
	private static final String POKER="6";
	private static final String STRAIGHT="4";
	private static final String CLUB="c";
	private static final String HEART="h";
	private static final String DIAMOND="d";
	private static final String SHAPE="s";
	private static final String[] typeNumbers={"2","3","4","5","6","7","8",
	                                                 "9","x","j","q","k","a"};
	
	private static final String[] typeColors={CLUB,HEART,DIAMOND,SHAPE};
	
	public HandResulter(){
		cards_boards=new ArrayList<Card>();
		cards_player1=new ArrayList<Card>();
		cards_player2=new ArrayList<Card>();
	}
	public void refresh(){
		cards_boards=new ArrayList<Card>();
		cards_player1=new ArrayList<Card>();
		cards_player2=new ArrayList<Card>();
	}
	
	public void addCardBoard(String strCard){
		cards_boards.add(new Card(strCard));
		
	}
	public void addCardPlayer1(String strCard){
		cards_player1.add(new Card(strCard));
		
	}
	public void addCardplayer2(String strCard){
		cards_player2.add(new Card(strCard));
		
	}
	public List<Card> getCards_boards() {
		return cards_boards;
	}
	public void setCards_boards(List<Card> cards_boards) {
		this.cards_boards = cards_boards;
	}
	public List<Card> getCards_player1() {
		return cards_player1;
	}
	public void setCards_player1(List<Card> cards_player1) {
		this.cards_player1 = cards_player1;
	}
	public List<Card> getCards_player2() {
		return cards_player2;
	}
	public void setCards_player2(List<Card> cards_player2) {
		this.cards_player2 = cards_player2;
	}
	
	public ResultHand getResult(String player){
		ResultHand result;
		String allCards="";
		List<Card> listallCards=new ArrayList<Card>();
		Map<Integer,List<Integer>> mapCounting=new HashMap<Integer,List<Integer>>();
		for(Card c:cards_boards){
			allCards=allCards+c.toString();
			listallCards.add(c);
		}
		if (player=="1")
		{
			for(Card c:cards_player1){
				allCards=allCards+c.toString();
				listallCards.add(c);
			}
		}else{
			for(Card c:cards_player2){
				allCards=allCards+c.toString();
				listallCards.add(c);
			}
			
		}
		//counting the ocurrencies for each card number type
		for(String typeCard:typeNumbers){
			int numOccurrencies=StringUtils.countMatches(allCards, typeCard);
			if(numOccurrencies>0){
				if (mapCounting.containsKey(numOccurrencies))
				{
					List<Integer> value = mapCounting.get(numOccurrencies);
					value.add(Card.valuesCards.get(typeCard));
					mapCounting.put(numOccurrencies,value );
				}
				else
				{
					List<Integer> list=new ArrayList<Integer>();
					mapCounting.put(numOccurrencies,list );
					list.add(Card.valuesCards.get(typeCard));
				}
			}
			
		}
		//count the colors in some way to after work out if it s a flush ir not 
		result=new ResultHand();
		if ((result=isPoker(mapCounting))==null){
			if((result=isFull(mapCounting))==null){
				if((result=isStraight(listallCards))==null){
					if((result=isTriple(mapCounting))==null){
						if((result=isDoublePair(mapCounting))==null){
							if((result=isPair(mapCounting))==null){
								
								result=new ResultHand();
								result.setTypeHand(HIGH_CARD);
								int valueHand=mapCounting.get(1).get(0);
								result.setValue(valueHand);
								
								}
							}
						}
					}
				}
			}
		
		
		return result;
		
	}
	
	private ResultHand isPoker(Map<Integer,List<Integer>> mapCounting){
		ResultHand result=new ResultHand();
		if(mapCounting.containsKey(4)){
			List<Integer> valuesCards=mapCounting.get(4);
			Collections.sort(valuesCards);
			result.setTypeHand(POKER);
			result.setValue(valuesCards.get(valuesCards.size()-1));
			return result;
		}else{
			return null;
		}
	}
	
	private ResultHand isDoublePair(Map<Integer,List<Integer>> mapCounting){
		ResultHand result=new ResultHand();
		if(mapCounting.containsKey(2)&&(mapCounting.get(2).size()>=2)){
			
				List<Integer> valuesCards=mapCounting.get(2);
				Collections.sort(valuesCards);
				result.setTypeHand(DOUBLE_PAIR);
				result.setValue(valuesCards.get(valuesCards.size()-1));
				result.setValue(valuesCards.get(valuesCards.size()-2));
				return result;
			
		}else{
			return null;
		}
	}
	
	private ResultHand isPair(Map<Integer,List<Integer>> mapCounting){
		ResultHand result=new ResultHand();
		if(mapCounting.containsKey(2)){
			
				List<Integer> valuesCards=mapCounting.get(2);
				Collections.sort(valuesCards);
				result.setTypeHand(PAIR);
				result.setValue(valuesCards.get(valuesCards.size()-1));
				return result;
			
		}else{
			return null;
		}
	}
	
	private ResultHand isTriple(Map<Integer,List<Integer>> mapCounting){
		ResultHand result=new ResultHand();
		if (mapCounting.containsKey(3)){
			List<Integer> valuesCards=mapCounting.get(3);
			Collections.sort(valuesCards);
			result.setTypeHand(TRIPLE);
			int valueHand=valuesCards.get(valuesCards.size()-1);
			result.setValue(valueHand);
			return result;
		}else{
			return null;
		}
	}
	
	private ResultHand isStraight(List<Card> cards){
		ResultHand result=new ResultHand();
		Collections.sort(cards);
		int count=0,i=0;
		while(i<cards.size()-1 ){
			if(cards.get(i).diff(cards.get(i+1))==1){
				count++;
				if(count==4)
				{
					result.setTypeHand(STRAIGHT);
					result.setValue(Card.valuesCards.get(cards.get(i+1).getCardNumber()));
				}
			}else{
				count=0;
			}
			i++;
		}
		if (count!=4){
			result=null;
		}
		return result;
	}
	
	private ResultHand isFull(Map<Integer,List<Integer>> mapCounting){
		ResultHand result=new ResultHand();
		if(mapCounting.containsKey(3) && mapCounting.containsKey(2)){
			List<Integer> valuesCards=mapCounting.get(3);
			Collections.sort(valuesCards);
			result.setTypeHand(FULL);
			result.setValue(valuesCards.get(valuesCards.size()-1));
			valuesCards=mapCounting.get(2);
			Collections.sort(valuesCards);
			result.setValue(valuesCards.get(valuesCards.size()-1));
			return result;
		}else{
			return null;
		}
	}

	

}
