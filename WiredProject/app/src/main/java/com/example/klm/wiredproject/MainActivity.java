package com.example.klm.wiredproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends Activity implements View.OnTouchListener {

    private ProgressDialog progressDialog;
    private WebView webviev, webviev1;
    //private String url = "",url1 = "", url2 = "", url3 = "",url4 = "";
    private String[ ] url = new String[5];
    private CheckBox checkBox, checkBox2, checkBox3, checkBox4, checkBox5;
    private String UseUrl="";
    private String article="";
    private TextView textView;
    private String dataText = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webviev = (WebView) findViewById(R.id.webView1);
       // webviev1 = (WebView) findViewById(R.id.webView2);

        checkBox = (CheckBox)findViewById(R.id.checkBox);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox)findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox)findViewById(R.id.checkBox4);
        checkBox5 = (CheckBox)findViewById(R.id.checkBox5);

        textView = (TextView)findViewById(R.id.textView);


       // webviev.setOnTouchListener(this);

        url[0] = "https://www.wired.com/story/turn-off-your-push-notifications/"; //"https://www.wired.com/story/ap-computer-science-2017/";// "http://alifuatkaya.com/ubuntu-openssh-port-degistirme";

        url[1] = "https://www.wired.com/story/ap-computer-science-2017/";
        url[2] = "https://www.wired.com/story/infrastructure-hyperloop-nope/";
        url[3] = "https://www.wired.com/story/spacexs-mars-plans-hit-a-pothole-up-next-the-moon/";
        url[4] = "https://www.wired.com/story/netflix-genre-movies-bright-death-note/";


        webviev.getSettings().setJavaScriptEnabled(true);
        webviev.getSettings().setDefaultTextEncodingName("utf-8");

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox.isChecked())
                {
                    UseUrl = "";
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);

                    UseUrl =url[0] ;
                    article="1";
                    new VeriCek().execute();
                }
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox2.isChecked())
                {
                    UseUrl = "";
                    checkBox.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);

                    UseUrl =url[1] ;

                    new VeriCek().execute();
                }
            }
        });

        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox3.isChecked())
                {
                    UseUrl = "";
                    checkBox.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);

                    UseUrl =url[2] ;
                    new VeriCek().execute();
                }
            }
        });

        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox4.isChecked())
                {
                    UseUrl = "";
                    checkBox.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox5.setChecked(false);

                    UseUrl =url[3] ;

                    new VeriCek().execute();
                }
            }
        });

        checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox5.isChecked())
                {
                    UseUrl = "";
                    checkBox.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                    checkBox2.setChecked(false);

                    UseUrl =url[4] ;

                    new VeriCek().execute();
                    textView.setText(dataText);
                }
            }
        });
        textView.setText("dfsdrbvdfgvddgvredfg");


    }

  @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //
            case MotionEvent.ACTION_UP:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wired.com/story/ap-computer-science-2017/"));
                startActivity(intent);
        }
        return false;
    }

    public class VeriCek extends AsyncTask<Void, Void, Void> {

        String data = "";
        Document doc = null;
        Element style =null;
        Elements alldivs = null;

        @Override
        protected Void doInBackground(Void... params) {
                          //**

                try {

                    doc = Jsoup.connect(UseUrl).timeout(1000).get();
                    style = doc.getElementsByClass("title").first();// doc.head();
                    //Elements alldivs = doc.select("div#app-root"); //("div#main-content");

                    //Class seçmek için doc.getElementsByClass.("article-main-component__content");///("clas-adi");
                    alldivs = doc.getElementsByClass("article-body-component");//("article-main-component__content");///("clas-adi");

                    data += style;
                    Element element;
                    for (int i = 0; i < alldivs.size(); i++) {

                        element = alldivs.get(i);
                        data += element.outerHtml();
                        data += "<br/>";
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            return null;

        }

        @Override
        protected void onPreExecute() {

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMessage("İçerik Yükleniyor..");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            webviev.loadDataWithBaseURL(null, data, "text/html", "UTF-8", null);
           // webviev1.loadDataWithBaseURL(null, data, "text/html", "UTF-8", null);
            dataText = data;
        }


    }

}