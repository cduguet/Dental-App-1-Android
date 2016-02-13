package com.dentalapp.dentalapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;


public class VideoPlayerActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        final VideoView videoView =
                (VideoView) findViewById(R.id.videoView);
        if (getIntent().getExtras() != null) {
            String uriString = getIntent().getExtras().getString("video");
            videoView.setVideoURI(Uri.parse(uriString));
        } else {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.coming_soon_title))
                    .setMessage(getString(R.string.coming_soon_message))
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(VideoPlayerActivity.this, MainActivity.class));
                        }
                    })
                    .show();
        }

        //videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/"
        //        + R.raw.video1));

        //videoView.setVideoPath("http://www.ebookfrenzy.com/android_book/movie.mp4");

        MediaController mediaController = new
                MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.start();
    }

}