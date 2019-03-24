package pl.mckszcz.prm.memory.model;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card extends AppCompatImageView {

    public Card(Context context) {
        super(context);
    }

}
