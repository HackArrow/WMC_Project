package com.example.panagiotisspentzouris.batterystats;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

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
}

