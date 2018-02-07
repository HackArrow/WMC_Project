package com.example.panagiotisspentzouris.batterystats;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import static com.google.android.youtube.player.YouTubePlayer.Provider;


public class YoutubeActivity extends YouTubeBaseActivity implements OnInitializedListener {
    public static final String API_KEY = "AIzaSyDgnLOc7q9r6bjMeWRH-itRtyUFUkrGAPM";
    //https://www.youtube.com/watch?v=<VIDEO_ID>
    public static final String VIDEO_ID = "-m3V8w_7vhk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // attaching layout xml
        setContentView(R.layout.activity_youtube);

        // Initializing YouTube player view
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        youTubePlayerView.initialize(API_KEY, this);

        final ListView listview =  findViewById(R.id.listview);
        String[] values = new String[] { "2G, 101,00mAh", "2G, 54,20mAh", "3G, 69,20mAh",
                "3G, 103,00mAh", "3G, 90,00mAh", "3G, 61,40mAh", "3G, 50,70mAh", "3G, 50,70mAh",
                "3G, 88,30mAh", "3G, 85,80mAh", "3G, 87,20mAh", "3G, 88,10mAh", "3G, 92,80mAh", "4G, 96,30mAh",
                "4G, 85,20mAh", "4G, 87,60mAh", "4G, 61,90mAh", "4G, 57,00mAh", "4G, 69,80mAh", "4G, 53,00mAh",
                "4G, 46,10mAh", "WIFI, 32,20mAh", "WIFI, 24,80mAh", "WIFI, 102,00mAh", "WIFI, 30,70mAh",
                "WIFI, 39,90mAh", "WIFI, 50,20mAh", "WIFI, 35,40mAh", "WIFI, 28,00mAh", "WIFI, 29,00mAh",
                "WIFI, 3,44mAh", "WIFI, 20,90mAh", "WIFI, 24,10mAh", "WIFI, 15,70mAh", "WIFI, 6,74mAh",
                "WIFI, 41,70mAh", "WIFI, 35,10mAh", "WIFI, 35,40mAh", "WIFI, 13,20mAh", "WIFI, 28,50mAh",
                "WIFI, 39,30mAh", "WIFI, 14,60mAh", "WIFI, 35,80mAh"};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failed to initialize.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {

        player.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {}

            @Override
            public void onLoaded(String s) {}

            @Override
            public void onAdStarted() {}

            @Override
            public void onVideoStarted() {
                Toast.makeText(YoutubeActivity.this, "Video Started", Toast.LENGTH_SHORT).show();
                //DumpsysBatteryStats dbs = new DumpsysBatteryStats();
                //dbs.resetStats();
            }

            @Override
            public void onVideoEnded() {
                Toast.makeText(YoutubeActivity.this, "Video Ended", Toast.LENGTH_SHORT).show();
                //DumpsysBatteryStats dbs = new DumpsysBatteryStats();
                //dbs.getStats();
            }

            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {}
        });

        // Start buffering
        if (!wasRestored) {
            //player.cueVideo(VIDEO_ID);
            player.loadVideo(VIDEO_ID);
            //player.play();
        }

    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}

