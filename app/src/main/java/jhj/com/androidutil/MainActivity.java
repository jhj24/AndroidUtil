package jhj.com.androidutil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jhj.com.library.math.LegalityUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LegalityUtil.isTelephoneNum("123 45 6    7");
        LegalityUtil.isTelephoneNum(null);
        LegalityUtil.isTelephoneNum("");
    }
}
