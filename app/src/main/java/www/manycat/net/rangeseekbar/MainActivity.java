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

        rangList.add("AText");
        rangList.add("BText");
        rangList.add("CText");
        rangList.add("DText");
        rangList.add("EText");

        rangeseekbar = (RangeSeekBar)findViewById(R.id.rangeseekbar);

        int fontSize = 40;
        rangeseekbar.onSetRangeAndText( rangList, fontSize);
        rangeseekbar.setProgressByText("GText");
        rangeseekbar.getProgressText();
    }
}
