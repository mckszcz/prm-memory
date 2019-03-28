package pl.mckszcz.prm.memory.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import lombok.Getter;
import lombok.Setter;
import pl.mckszcz.prm.memory.R;

@Getter
@Setter
public class WinActivity extends AppCompatActivity {

    private Long timeElapsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        timeElapsed = getIntent().getLongExtra("timeElapsed", 0);
        TextView textView = findViewById(R.id.timeElapsed);
        textView.setText(String.valueOf(timeElapsed / 1000 + " seconds"));
    }


}
