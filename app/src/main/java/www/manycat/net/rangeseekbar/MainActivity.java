package www.manycat.net.rangeseekbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RangeSeekBar rangeseekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> rangList = new ArrayList<String>();

        rangList.add("普通級");
        rangList.add("保護級");
        rangList.add("輔導級(12)");
        rangList.add("輔導級(15)");
        rangList.add("限制級");

//        rangList.add("A");
//        rangList.add("B");
//        rangList.add("C");
//        rangList.add("D");
//        rangList.add("E");
//        rangList.add("F");
//        rangList.add("G");
//        rangList.add("H");
//        rangList.add("I");
//        rangList.add("J");
//        rangList.add("K");

        rangeseekbar = (RangeSeekBar)findViewById(R.id.rangeseekbar);

        rangeseekbar.onSetRangeAndText( rangList, 40);
        rangeseekbar.setProgressByText("輔導級(12)");
    }
}
