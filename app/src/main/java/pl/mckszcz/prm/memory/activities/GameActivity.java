package pl.mckszcz.prm.memory.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

import pl.mckszcz.prm.memory.R;

public class GameActivity extends AppCompatActivity {

    Map<CardType, ImageView> cardMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.cardMap = new HashMap<>();
    }

    private void createBoard() {

    }

    private void gameLoop() {

    }

    protected enum CardType {

        BANG, CIRCLE, CROSS, FOUR_STAR, HEART, PENTAGON;

        public CardType getType() {
            return this;
        }
    }

}
