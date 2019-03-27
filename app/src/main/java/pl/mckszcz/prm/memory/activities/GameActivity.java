package pl.mckszcz.prm.memory.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

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
    private Vector<Card> selectedCard;
    private Card cardOne;
    private Card cardTwo;
    private boolean isGameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        selectedCard = new Vector<>(2);
        createBoard(6);
    }

    private void createBoard(int pairNumber) {
        TableLayout layout = findViewById(R.id.tableLayout);
        createRows(pairNumber);
        createCards(pairNumber);
        fillRows();
        rowList.forEach(layout::addView);
        addListeners();
        addGameWatcher();
    }

    private void addGameWatcher() {
        new Thread(() -> {
            while (!isGameOver()) {
                if (cardList.stream().allMatch(Card::isMatched)) {
                    setGameOver(true);
                }
            }
            if (isGameOver()) {
                Intent intent = new Intent(this, WinActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();
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
        cardList.forEach(card -> card.setOnClickListener(v -> {
            Thread t;
            t = new Thread(() -> {
                if (!card.isMatched()) {
                    card.setImageResource(card.getImage(card.getCardType()));
                }
                if (selectedCard.size() == 0) {
                    selectedCard.add(card);
                    cardOne = selectedCard.get(0);
                } else if (selectedCard.size() == 1 && selectedCard.get(0) != card) {
                    selectedCard.add(card);
                    cardTwo = selectedCard.get(1);
                }
                if (cardOne != null && cardTwo != null) {
                    if (cardOne.getCardType().equals(cardTwo.getCardType())) {
                        cardOne.setMatched(true);
                        cardTwo.setMatched(true);
                    }
                    cardOne = null;
                    cardTwo = null;
                    selectedCard.clear();
                }
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (!card.isMatched()) {
                            card.setImageResource(R.drawable.unknown);
                        }
                    }
                }, 2000);
            });
            t.start();
        }));
    }
}
