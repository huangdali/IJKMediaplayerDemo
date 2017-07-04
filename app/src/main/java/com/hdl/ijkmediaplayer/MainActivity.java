package com.hdl.ijkmediaplayer;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {
    private static String KEY_URL = "http://dl.live-play.acgvideo.com/live-dl/531764/live_26468931_9099771.flv?wsSecret=d9a28792bd49b1ab806676b03822950d&wsTime=1499154816";
    private IjkMediaPlayer ijkMediaPlayer;
    private SurfaceView playerHolder;
    private SurfaceHolder holder;
    private TextView tvUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        playerHolder = (SurfaceView) findViewById(R.id.sv_player);
        holder = playerHolder.getHolder();
        ijkMediaPlayer = new IjkMediaPlayer();
        ijkMediaPlayer.setDisplay(holder);
        tvUrl = (TextView) findViewById(R.id.tv_url);
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                ijkMediaPlayer.setDisplay(holder);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

    }


    public void onPlaye(View view) {
        if (!TextUtils.isEmpty(tvUrl.getText().toString().trim())) {
            KEY_URL = tvUrl.getText().toString().trim();
        }
        if (ijkMediaPlayer.isPlaying()) {
            ijkMediaPlayer.stop();
            ijkMediaPlayer.reset();
        }
        try {
            ijkMediaPlayer.setDataSource(this, Uri.parse(KEY_URL));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ijkMediaPlayer.prepareAsync();
        ijkMediaPlayer.start();//播放
        ijkMediaPlayer.setKeepInBackground(false);//不在后台运行
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ijkMediaPlayer.release();//释放
    }
}
