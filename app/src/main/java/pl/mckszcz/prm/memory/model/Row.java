package pl.mckszcz.prm.memory.model;

import android.content.Context;
import android.view.Gravity;
import android.widget.TableRow;

import java.util.List;

public class Row extends TableRow {
    public Row(Context context) {
        super(context);
        LayoutParams params = new LayoutParams();
        params.width = LayoutParams.WRAP_CONTENT;
        params.height = LayoutParams.WRAP_CONTENT;
        setGravity(Gravity.CENTER_HORIZONTAL);
        setPadding(5, 0, 5, 5);
    }

    public void addToRow(List<Card> list) {
        list.forEach(this::addView);
    }

}
