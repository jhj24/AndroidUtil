package jhj.com.androidutil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import jhj.com.androidlibrary.math.LegalityUtil;
import jhj.com.androidlibrary.ui.UIUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LegalityUtil.isTelephoneNum("123 45 6    7");
        LegalityUtil.isTelephoneNum(null);
        LegalityUtil.isTelephoneNum("");
        UIUtil.percentWidth(this);

    }
}
