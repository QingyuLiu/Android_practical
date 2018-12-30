package com.example.zhuyuting.tablayout.UI;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.zhuyuting.tablayout.R;

import java.util.ArrayList;
import java.util.List;

public class MediaplayActivity extends AppCompatActivity {
    private ListView mListView;
    private TextView introductionText;
    private List<MediaplayActivity.VideoInfo> videoList=new ArrayList<MediaplayActivity.VideoInfo>();
    private MediaplayActivity.VideoInfo video;
    private MediaplayActivity.myAdapter adapter;
    private int currentIndex=0;
    private String url1="http://ht-mobile.cdn.turner.com/nba/big/teams/kings/2014/12/12/HollinsGlassmov-3462827_8382664.mp4";
    private String url2="http://ht-mobile.cdn.turner.com/nba/big/teams/kings/2014/12/12/VivekSilverIndiamov-3462702_8380205.mp4";
    private VideoView mVideoView;
    MediaController mMediaCtrl;
    private int playPosition=-1;
    //	private boolean isPaused=false;
    private boolean isPlaying=false;
    private int preLast = -1;
    private int ifFirst = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplay);
        //构造视频数据
        /*
        for(int i=0;i<50;i++){
            if(i%2==0){
                video=new VideoInfo(url1,"TEST1"+i,R.drawable.video2);
            }else{
                video=new VideoInfo(url2,"TEST2"+i,R.drawable.video1);
            }
            videoList.add(video);
        }*/
        video=new VideoInfo(url1,"TEST1",R.drawable.video2);
        videoList.add(video);
        video=new VideoInfo(url2,"TEST2",R.drawable.video1);
        videoList.add(video);
        mListView=(ListView) findViewById(R.id.video_listview);
        adapter = new myAdapter();
        mListView.setAdapter(adapter);
        mListView.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                System.out.println(firstVisibleItem + "--" + visibleItemCount);
                if((currentIndex<firstVisibleItem || currentIndex>mListView.getLastVisiblePosition())&&isPlaying){
                    System.out.println("滑动的："+mVideoView.toString());

                    playPosition=mVideoView.getCurrentPosition();
                    mVideoView.pause();
                    mVideoView.setMediaController(null);
                    //	isPaused=true;
                    isPlaying=false;
                    System.out.println("视频已经暂停："+playPosition);


                }else
                {
                    if(preLast==firstVisibleItem)
                    {
                        if(visibleItemCount>2 && currentIndex==0 && ifFirst!=3)
                        {
                            ifFirst =0;
                            currentIndex =firstVisibleItem + 1;
                            adapter.notifyDataSetChanged();
                        }else if(visibleItemCount== 2 && firstVisibleItem==0 && ifFirst==0)
                        {
                            ifFirst = 1;
                            currentIndex =firstVisibleItem ;
                            adapter.notifyDataSetChanged();
                        }

                    }else
                    {
                        preLast = firstVisibleItem;
                        ifFirst = 0;
                        if(visibleItemCount>=2)
                        {

                            currentIndex =firstVisibleItem + 1;
                            adapter.notifyDataSetChanged();

                        }else if(visibleItemCount==1)
                        {
                            currentIndex=firstVisibleItem;

                            adapter.notifyDataSetChanged();
                        }

                    }


                }
            }
        });
/*		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				currentIndex=position;
				adapter.notifyDataSetChanged();
			}
		});*/
    }

    class myAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            // TODO Auto-generated method stubs
            return videoList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return videoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            final int mPosition=position;
            if(convertView==null){
                convertView=LayoutInflater.from(MediaplayActivity.this).inflate(R.layout.video_item_layout, null);
                holder=new ViewHolder();
                holder.videoImage=(ImageView) convertView.findViewById(R.id.video_image);
                //holder.videoIntroduction=findViewById(R.id.video_introduction);
                holder.videoPlayBtn=(ImageButton)convertView.findViewById(R.id.video_play_btn);
                holder.mProgressBar=(ProgressBar) convertView.findViewById(R.id.progressbar);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder) convertView.getTag();
            }
            holder.videoImage.setImageDrawable(getResources().getDrawable(videoList.get(position).getVideoImage()));
           // holder.videoIntroduction.setText(videoList.get(position).getVideoName());
            holder.videoPlayBtn.setVisibility(View.VISIBLE);
            holder.videoImage.setVisibility(View.VISIBLE);
           // holder.videoIntroduction.setVisibility(View.VISIBLE);
            mMediaCtrl = new MediaController(MediaplayActivity.this,false);
            if(currentIndex == position){
                holder.videoPlayBtn.setVisibility(View.INVISIBLE);
                holder.videoImage.setVisibility(View.INVISIBLE);
                //holder.videoIntroduction.setVisibility(View.INVISIBLE);

                if(isPlaying || playPosition==-1){
                    if(mVideoView!=null){
                        mVideoView.setVisibility(View.GONE);
                        mVideoView.stopPlayback();
                        holder.mProgressBar.setVisibility(View.GONE);
                    }
                }
                mVideoView=(VideoView) convertView.findViewById(R.id.videoview);
                introductionText=convertView.findViewById(R.id.video_introduction);
                mVideoView.setVisibility(View.VISIBLE);
                introductionText.setVisibility(View.VISIBLE);
                mMediaCtrl.setAnchorView(mVideoView);
                mMediaCtrl.setMediaPlayer(mVideoView);
                mVideoView.setMediaController(mMediaCtrl);
                mVideoView.requestFocus();
                holder.mProgressBar.setVisibility(View.VISIBLE);
//				if(playPosition>0 && isPaused){
//					mVideoView.start();
//					isPaused=false;
//					isPlaying=true;
//					holder.mProgressBar.setVisibility(View.GONE);
//				}else{
                mVideoView.setVideoPath(videoList.get(mPosition).getUrl());
                // isPaused=false;
                isPlaying=true;
                System.out.println("播放新的视频");
                //	}
                mVideoView.setOnCompletionListener(new OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if(mVideoView!=null){
                            mVideoView.seekTo(0);
                            mVideoView.stopPlayback();
                            mVideoView.start();
                            //currentIndex=-1;
                            //	isPaused=false;
                            isPlaying=false;
                            holder.mProgressBar.setVisibility(View.GONE);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

                mVideoView.setOnErrorListener(new OnErrorListener() {

                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        mp.reset();
                        return false;
                    }
                });


                mVideoView.setOnPreparedListener(new OnPreparedListener() {

                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        holder.mProgressBar.setVisibility(View.GONE);
                        mVideoView.start();
                    }
                });

            }else{
                holder.videoPlayBtn.setVisibility(View.VISIBLE);
                holder.videoImage.setVisibility(View.VISIBLE);
               // holder.videoIntroduction.setVisibility(View.VISIBLE);
                holder.mProgressBar.setVisibility(View.GONE);
            }

            holder.videoPlayBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playPosition=mVideoView.getCurrentPosition();
                    mVideoView.pause();
                    mVideoView.setMediaController(null);
                    //isPaused=true;
                    isPlaying=false;
                    if(mPosition==0)
                    {
                        ifFirst = 3;
                    }
                    currentIndex=mPosition;
                    playPosition=-1;
                    adapter.notifyDataSetChanged();
                }
            });
            return convertView;
        };
    }
    static class ViewHolder{
        ImageView videoImage;
        //TextView videoIntroduction;
        ImageButton videoPlayBtn;
        ProgressBar mProgressBar;
    }
    static class VideoInfo {
        private String url;
        private String videoName;
        private int videoImage;
        public VideoInfo(String url,String name,int path) {
            this.videoName=name;
            this.videoImage=path;
            this.url=url;
        }
        public String getVideoName() {
            return videoName;
        }
        public void setVideoName(String videoName) {
            this.videoName = videoName;
        }
        public int getVideoImage() {
            return videoImage;
        }
        public void setVideoImage(int videoImage) {
            this.videoImage = videoImage;
        }
        public String getUrl() {
            return url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
    }
}