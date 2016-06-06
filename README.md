# RangeSeekBar
### excent自SeekBar的Custom RangeSeekBar
![Opps! Screen shot has missed](https://github.com/rurikaxx/RangeSeekBar/blob/master/rangseekbar.png)

使用範例
--------------------------------------------------
    ArrayList<String> rangList = new ArrayList<String>();

    rangList.add("AText");
    rangList.add("BText");
    rangList.add("CText");
    rangList.add("DText");
    rangList.add("EText");

    rangeseekbar = (RangeSeekBar)findViewById(R.id.rangeseekbar);

    int fontSize = 40;
    rangeseekbar.onSetRangeAndText( rangList, fontSize);

    // 設定預設值
    rangeseekbar.setProgressByText("GText");

    // 取得當前選擇的Text
    rangeseekbar.getProgressText();