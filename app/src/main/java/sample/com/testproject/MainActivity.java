package sample.com.testproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener{

    private Button jsoupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();

    }

    private void initLayout() {
        jsoupButton = (Button) findViewById(R.id.jsoupButton);
        jsoupButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jsoupButton:

                goJsoupActivity();
                break;
        }
    }

    private void goJsoupActivity() {
        Intent intent = new Intent();
        intent.setClass(this, JsoupActivity.class);
        startActivity(intent);
    }
}
