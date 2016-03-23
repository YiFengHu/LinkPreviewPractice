package sample.com.testproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.leocardz.link.preview.library.LinkPreviewCallback;
import com.leocardz.link.preview.library.SourceContent;
import com.leocardz.link.preview.library.TextCrawler;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class JsoupActivity extends Activity implements View.OnClickListener, LinkPreviewCallback {

    private List<String> randomUrls;

    private EditText urlEditText;
    private Button getButton;
    private Button randomUrlButton;
    private ImageView thumbImageView;
    private TextView title;
    private TextView desc;

    private TextCrawler textCrawler;
    private Object randomUrl;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);

        randomUrls = new ArrayList<>(5);
        randomUrls.add("https://tw.news.yahoo.com/%E8%B2%9D%E9%BD%8A%E6%A0%BC%E5%BE%97%E8%AB%BE%E7%8D%8E-%E5%83%8F%E8%A2%AB%E5%85%AC%E8%BB%8A%E6%92%9E%E5%88%B0-%E7%8D%8E%E7%AB%A0-%E5%83%8F%E8%B6%85%E4%BA%BA%E8%83%BD%E9%87%8F%E7%9F%B3-192700598.html");
        randomUrls.add("https://tw.news.yahoo.com/%E6%8E%92%E7%A2%B3%E9%87%8F%E5%A2%9E%E5%8D%81%E5%80%8D-%E4%BA%BA%E9%A1%9E%E6%8E%A8%E5%9C%B0%E7%90%83%E5%85%A5%E6%B7%B1%E6%B7%B5-092255828.html");
        randomUrls.add("https://github.com/YiFengHu/SmackTest");
        randomUrls.add("https://www.google.com.tw/maps/place/%E5%8B%9E%E5%B7%A5%E5%85%AC%E5%9C%92/@22.5994714,120.303187,15z/data=!4m2!3m1!1s0x0000000000000000:0xcb3fd571b73a865b");
        randomUrls.add("https://popapp.in/");

        textCrawler = new TextCrawler();
        initLayout();

    }

    private void initLayout() {
        urlEditText = (EditText) findViewById(R.id.urlEditText);
        getButton = (Button) findViewById(R.id.getButton);
        randomUrlButton = (Button) findViewById(R.id.randomButton);
        thumbImageView = (ImageView) findViewById(R.id.imageView);
        title = (TextView) findViewById(R.id.titleTextView);
        desc = (TextView) findViewById(R.id.descTextView);

        randomUrlButton.setOnClickListener(this);
        getButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getButton:

                getData();
                break;

            case R.id.randomButton:

                getRandomUrl();
                break;
        }
    }

    public void getData() {
        String url = urlEditText.getText().toString();
        if(checkValid(url)) {
            textCrawler.makePreview(this, url);
        }
    }

    private boolean checkValid(String s) {
        if(s == null || s.isEmpty()){
            return false;
        }
        return  true;
    }


    @Override
    public void onPre() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Getting data");
        progressDialog.setMessage("Please wait");
        progressDialog.show();
    }

    @Override
    public void onPos(SourceContent sourceContent, boolean isNull) {
        progressDialog.dismiss();

        if(isNull){
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }else {
            title.setText(sourceContent.getTitle());
            desc.setText(sourceContent.getDescription());

            List<String> imgs = sourceContent.getImages();
            if (imgs.size() > 0) {
                String img = imgs.get(0);
                Glide.with((Activity) this)
                        .load(img)
                        .centerCrop()
                        .placeholder(R.drawable.icon_picture)
                        .crossFade()
                        .into(thumbImageView);
            }
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        }
    }

    public void getRandomUrl() {
        int randomIndex = Math.abs(new Random().nextInt() % randomUrls.size());
        String url = randomUrls.get(randomIndex);
        urlEditText.setText(url);
    }
}
