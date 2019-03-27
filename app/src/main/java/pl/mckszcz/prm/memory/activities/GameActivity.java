package pl.mckszcz.prm.memory.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import lombok.Getter;
import lombok.Setter;
import pl.mckszcz.prm.memory.R;
import pl.mckszcz.prm.memory.model.Card;
import pl.mckszcz.prm.memory.model.Row;

@Getter
@Setter
public class GameActivity extends AppCompatActivity {

    private List<Card> cardList;
    private List<Row> rowList;
    private Card selectedCard;
    private Card cardOne;
    private Card cardTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        createBoard(6);
    }

    private void createBoard(int pairNumber) {
        TableLayout layout = findViewById(R.id.tableLayout);
        createRows(pairNumber);
        createCards(pairNumber);
        fillRows();
        rowList.forEach(layout::addView);
        addListeners();
    }

    private void createCards(int pairNumber) {
        cardList = new ArrayList<>();
        for (int i = 0; i < pairNumber; i++) {
            Card cardOne = new Card(this);
            cardOne.setCardType(Card.CardType.valueOf(i));
            Card cardTwo = new Card(this);
            cardTwo.setCardType(Card.CardType.valueOf(i));
            cardList.add(cardOne);
            cardList.add(cardTwo);
        }
        Collections.shuffle(cardList);
    }

    private void createRows(int pairNumber) {
        rowList = new ArrayList<>();
        if (pairNumber < 3) {
            rowList.add(new Row(this));
            rowList.add(new Row(this));
        } else {
            rowList.add(new Row(this));
            rowList.add(new Row(this));
            rowList.add(new Row(this));
        }
    }

    private void fillRows() {
        int cardsInRow = Math.round(cardList.size() / rowList.size());
        List<List<Card>> list = Lists.partition(cardList, cardsInRow);
        for (int i = 0; i < rowList.size(); i++) {
            rowList.get(i).addToRow(list.get(i));
        }
    }

    private void addListeners() {
        cardList.forEach(card -> {
            card.setOnClickListener(l -> {
                selectedCard = card;
                card.setImageResource(card.getImage(card.getCardType()));
                Timer t = new Timer();
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (cardOne == null && cardTwo == null) {
                            cardOne = selectedCard;
                            System.out.println("Card one: " + cardOne.getCardType());
                        } else if (cardOne != null && cardOne != selectedCard && cardTwo == null) {
                            cardTwo = selectedCard;
                            System.out.println("Card two: " + cardTwo.getCardType());
                            if (cardOne.getCardType() == cardTwo.getCardType()) {
                                card.setMatched(true);
                                System.out.println(card.getCardType() + " " + card.isMatched());
                            }
                        }
                        if (!card.isMatched())
                            card.setImageResource(R.drawable.unknown);
                        cardOne = null;
                        cardTwo = null;
                    }
                }, 2000);
            });
        });
    }

}
