package com.lagash.chatbot;

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lagash.chatbot.Adapters.CustomAdapter;
import com.lagash.chatbot.Helpers.HttpDataHandler;
import com.lagash.chatbot.Models.ChatModel;
import com.lagash.chatbot.Models.MessageModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    List<ChatModel> list_chat = new ArrayList<>();
    FloatingActionButton btn_send_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.list_of_message);
        editText = (EditText)findViewById(R.id.user_message);
        btn_send_message = (FloatingActionButton)findViewById(R.id.send);

        btn_send_message.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String text = editText.getText().toString();
                ChatModel model =new ChatModel(text,true);
                list_chat.add(model);
                new BotAPI().execute(list_chat);

                editText.setText("");
            }
        });
    }

    private class BotAPI extends AsyncTask<List<ChatModel>, Void, String>{
        String stream =null;
        List<ChatModel> models;

        String text = editText.getText().toString();

        @Override
        protected String doInBackground(List<ChatModel>...params){
            String url = String.format("http://sandbox.api.simsimi.com/request.p?key=%s&lc=en&ft=1&text=%s", getString(R.string.simsimi_api), text);
            models = params[0];
            HttpDataHandler httpDataHandler = new HttpDataHandler();
            stream = httpDataHandler.GetHTTPData(url);

            return stream == null? "": stream;
        }

        @Override
        protected void onPostExecute(String s) {
            Gson gson = new Gson();
            MessageModel response = gson.fromJson(s, MessageModel.class);
            String textResponse = response.getResponse();

            ChatModel chatModel = new ChatModel(textResponse, false);
            models.add(chatModel);
            CustomAdapter customAdapter = new CustomAdapter(models, getApplicationContext());
            listView.setAdapter(customAdapter);
        }
    }
}
