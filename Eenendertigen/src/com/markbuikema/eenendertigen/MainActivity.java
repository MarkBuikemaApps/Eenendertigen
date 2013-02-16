package com.markbuikema.eenendertigen;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

import com.markbuikema.eenendertigen.Card.Type;
import com.markbuikema.eenendertigen.Card.Value;

public class MainActivity extends Activity {

	Card[][] playerCards;

	Card[] player1cards;
	Card[] player2cards;
	Card[] player3cards;

	Card[] stackCards;

	Card selectedCard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Card.GRADIENT.setColors(new int[] { Color.GRAY, Color.LTGRAY });

		playerCards = new Card[3][3];
		playerCards[0] = player1cards;
		playerCards[1] = player2cards;
		playerCards[2] = player3cards;

		player1cards = new Card[3];
		player2cards = new Card[3];
		player3cards = new Card[3];
		stackCards = new Card[4];

		player1cards[0] = new Card( false, (Button) findViewById(R.id.p1card1));
		player1cards[1] = new Card( false, (Button) findViewById(R.id.p1card2));
		player1cards[2] = new Card( false, (Button) findViewById(R.id.p1card3));

		player2cards[0] = new Card( false, (Button) findViewById(R.id.p2card1));
		player2cards[1] = new Card( false, (Button) findViewById(R.id.p2card2));
		player2cards[2] = new Card( false, (Button) findViewById(R.id.p2card3));

		player3cards[0] = new Card( false, (Button) findViewById(R.id.p3card1));
		player3cards[1] = new Card( false, (Button) findViewById(R.id.p3card2));
		player3cards[2] = new Card( false, (Button) findViewById(R.id.p3card3));

		stackCards[0] = new Card( true, (Button) findViewById(R.id.stack0));
		stackCards[1] = new Card( false, (Button) findViewById(R.id.stack1));
		stackCards[2] = new Card( false, (Button) findViewById(R.id.stack2));
		stackCards[3] = new Card( false, (Button) findViewById(R.id.stack3));

		
		
	}
	

	public void onCardSelectionChanged(Card card) {
		if (selectedCard == null) {
			selectedCard = card;
			selectedCard.setSelected(true);
			selectedCard.invalidate();
		} else {


			
			
			swap(selectedCard, card);
			selectedCard.setSelected(false);
			selectedCard = null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public boolean isPlayerCard(Card card) {
		for (Card[] cards : playerCards) {
			for (Card c : cards) {
				if (c == card)
					return true;
			}
		}
		return false;
	}

	public void swap(Card card1, Card card2) {
		int tempWorth = card1.getWorth();
		Type tempType = card1.getType();
		Value tempValue = card1.getValue();

		card1.setWorth(card2.getWorth());
		card1.setType(card2.getType());
		card1.setValue(card2.getValue());

		card2.setWorth(tempWorth);
		card2.setType(tempType);
		card2.setValue(tempValue);

		card1.invalidate();
		card2.invalidate();
	}

}
