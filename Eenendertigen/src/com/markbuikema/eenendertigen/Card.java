package com.markbuikema.eenendertigen;

import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Card {

	private Value value;
	private Type type;
	private int worth;
	private Button button;
	private boolean hidden;
	public final static GradientDrawable GRADIENT = new GradientDrawable();
	public final static ArrayList<TypeValuePair> INCLUDED_CARDS = new ArrayList<TypeValuePair>();

	public enum Type {
		HEARTS, CLOVER, SPADES, DIAMONDS
	}

	public enum Value {
		N7, N8, N9, N10, A, Q, K, J
	}

	private class TypeValuePair {
		Type type;
		Value value;

		public TypeValuePair(Type type, Value value) {
			this.type = type;
			this.value = value;
		}
	}

	public Card(boolean hidden, final Button button) {
		do {
			type = randomType();
			value = randomValue();
		} while (exists(type,value));
		
		INCLUDED_CARDS.add(new TypeValuePair(type, value));
		
		this.worth = getWorth(value);
		this.button = button;
		this.hidden = hidden;
		this.button.setBackgroundColor(Color.LTGRAY);
		this.button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				((MainActivity) button.getContext()).onCardSelectionChanged(Card.this);
			}

		});
		invalidate();
	}
	
	private boolean exists(Type type, Value value) {
		for (TypeValuePair pair: INCLUDED_CARDS) {
			if (pair.type == type && pair.value == value) {
				return true;
			}
		}
		return false;
	}

	public void setSelected(boolean selected) {
		if (selected) {
			button.setBackground(GRADIENT);
		} else {
			button.setBackgroundColor(Color.LTGRAY);
		}
	}

	public void invalidate() {
		if (hidden) {
			button.setText("?");
		} else {
			switch (type) {
			case SPADES:
				button.setText("♠" + value.toString().replace("N", ""));
				button.setTextColor(Color.BLACK);
				break;

			case HEARTS:
				button.setText("♥" + value.toString().replace("N", ""));
				button.setTextColor(Color.RED);
				break;

			case CLOVER:
				button.setText("♣" + value.toString().replace("N", ""));
				button.setTextColor(Color.BLACK);
				break;

			case DIAMONDS:
				button.setText("♦" + value.toString().replace("N", ""));
				button.setTextColor(Color.RED);
				break;

			}
		}
	}

	public static Type randomType() {
		int random = (int) (Math.random() * 4);
		switch (random) {
		case 0:
			return Type.CLOVER;
		case 1:
			return Type.DIAMONDS;
		case 2:
			return Type.HEARTS;
		case 3:
			return Type.SPADES;
		default:
			return Type.CLOVER;
		}
	}

	public static Value randomValue() {
		int random = (int) (Math.random() * 8);
		switch (random) {
		case 0:
			return Value.N7;
		case 1:
			return Value.N8;
		case 2:
			return Value.N9;
		case 3:
			return Value.N10;
		case 4:
			return Value.A;
		case 5:
			return Value.J;
		case 6:
			return Value.K;
		case 7:
			return Value.Q;
		default:
			return Value.N7;
		}
	}

	public static int getWorth(Value value) {
		int worth = 0;
		switch (value) {
		case N7:
			worth = 7;
			break;
		case N8:
			worth = 8;
			break;
		case N9:
			worth = 9;
			break;
		case N10:
		case Q:
		case K:
		case J:
			worth = 10;
			break;
		case A:
			worth = 11;
			break;
		}
		return worth;
	}

	public Type getType() {
		return type;
	}

	public Value getValue() {
		return value;
	}

	public int getWorth() {
		return worth;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setWorth(int worth) {
		this.worth = worth;
	}

}
