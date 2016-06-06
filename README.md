# RangeSeekBar
### excent自SeekBar的簡易Custom RangeSeekBar
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
    rangeseekbar.setRangeAndText( rangList, fontSize);

    // 設定預設值
    rangeseekbar.setProgressByText("GText");

    // 取得當前選擇的Text
    rangeseekbar.getProgressText();

###Developed by:
David Luo(rurikaxx@gmail.com)

這是繼承於Seekbar的簡單客製化RangeSeekbar

如果您在使用上有發現任何問題,歡迎告知我。

###License:
Copyright 2016 David Luo