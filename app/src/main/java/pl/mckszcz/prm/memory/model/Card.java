package pl.mckszcz.prm.memory.model;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TableRow;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import pl.mckszcz.prm.memory.R;

@Getter
@Setter
public class Card extends AppCompatImageView {

    private CardType cardType;
    private boolean isMatched = false;

    public Card(Context context) {
        super(context);
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.width = 0;
        params.height = TableRow.LayoutParams.WRAP_CONTENT;
        params.weight = 1;
        setAdjustViewBounds(true);
        setPadding(2, 0, 2, 2);
        setScaleType(ScaleType.FIT_XY);
        setLayoutParams(params);
        setImageResource(R.drawable.unknown);
    }

    public int getImage(CardType cardType) {
        switch (cardType) {
            case BANG:
                return R.drawable.bang1;
            case CIRCLE:
                return R.drawable.circle1;
            case CROSS:
                return R.drawable.cross1;
            case FOUR_STAR:
                return R.drawable.four_star1;
            case HEART:
                return R.drawable.heart1;
            case PENTAGON:
                return R.drawable.pentagon1;
        }
        return R.drawable.unknown;
    }

    public enum CardType {

        BANG(0), CIRCLE(1), CROSS(2), FOUR_STAR(3), HEART(4), PENTAGON(5);

        private static Map map = new HashMap<>();

        static {
            for (CardType cardType : CardType.values()) {
                map.put(cardType.value, cardType);
            }
        }

        private int value;

        CardType(int value) {
            this.value = value;
        }

        public static CardType valueOf(int cardType) {
            return (CardType) map.get(cardType);
        }

    }

}
