## Androidify Yourself app for GDG Riga event
![screenshot](androidify-yourself-screenshot.png)

## Basic Functionality
* Change Application name to Androidify Yourself!
* Rename helloworld.xml layout to main.xml
* Split screen into parts
``` xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:weightSum="130">

<TextView
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="20"
    android:text="Create your Android" />

    ....

</LinearLayout>
```
*  Add Layout with Android parts selectors
``` xml
<LinearLayout
    android:layout_weight="90"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:padding="1dp"
    android:orientation="vertical"
    android:background="@drawable/border">

    <android.support.v4.view.ViewPager
        android:id="@+id/viewPagerHead"
        android:layout_weight="30"
        android:layout_width="match_parent"
        android:layout_height="0dp" />

    <android.support.v4.view.ViewPager
        android:id="@+id/viewPagerBody"
        android:layout_weight="30"
        android:layout_width="match_parent"
        android:layout_height="0dp" />

    <android.support.v4.view.ViewPager
        android:id="@+id/viewPagerLegs"
        android:layout_weight="30"
        android:layout_width="match_parent"
        android:layout_height="0dp" />
</LinearLayout>
```
* Bind Views in MainActivity
``` java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.helloworld);

    ViewPager headViewPager = (ViewPager) findViewById(R.id.viewPagerHead);
    ViewPager bodyViewPager = (ViewPager) findViewById(R.id.viewPagerBody);
    ViewPager legsViewPager = (ViewPager) findViewById(R.id.viewPagerLegs);
    ....
}
```
* Create AndroidifyViewPagerAdapter
``` java
public class AndroidifyViewPagerAdapter extends FragmentPagerAdapter {

    private List<Integer> imgIds;

    public AndroidifyViewPagerAdapter(FragmentManager fm, List<Integer> imgIds) {
        super(fm);
        this.imgIds = imgIds;
    }

    @Override
    public Fragment getItem(int position) {
        ...
    }

    @Override
    public int getCount() {
        return imgIds.size();
    }
}
```
* Create AndroidifyViewPagerItemFragment
``` java
public class AndroidifyViewPagerItemFragment extends Fragment {

    private int imgId;

    public AndroidifyViewPagerItemFragment(int imgId) {
        this.imgId = imgId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ....
    }
}
```
* Create androidify_part.xml layout
``` xml
 <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
     android:layout_width="match_parent"
     android:layout_height="match_parent">

     <ImageView
         android:id="@+id/android_part"
         android:layout_width="match_parent"
         android:layout_height="match_parent" />
 </LinearLayout>
```
* Add View inflating in AndroidifyViewPagerItemFragment onCreateView()
``` java
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.androidify_part, container, false);
    ImageView imageView = (ImageView) rootView.findViewById(R.id.android_part);
    imageView.setImageResource(imgId);
    return rootView;
}
```
* Add Fragment creating in AndroidifyViewPagerAdapter getItem(int position)
``` java
@Override
public Fragment getItem(int position) {
    return new AndroidifyViewPagerItemFragment(imgIds.get(position));
}
```
* Extend MainActivity from android.support.v4.app.FragmentActivity
* Set Adapter to ViewPagers in MainActivity
``` java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.helloworld);

    ViewPager headViewPager = (ViewPager) findViewById(R.id.viewPagerHead);
    ViewPager bodyViewPager = (ViewPager) findViewById(R.id.viewPagerBody);
    ViewPager legsViewPager = (ViewPager) findViewById(R.id.viewPagerLegs);

    FragmentManager fm = getSupportFragmentManager();
    headViewPager.setAdapter(new AndroidifyViewPagerAdapter(fm, AndroidDrawables.getHeads()));
    bodyViewPager.setAdapter(new AndroidifyViewPagerAdapter(fm, AndroidDrawables.getBodies()));
    legsViewPager.setAdapter(new AndroidifyViewPagerAdapter(fm, AndroidDrawables.getLegs()));
}
```
* Create and register all Drawables
``` java
public class AndroidDrawables {

    private static final List<Integer> bodies = new ArrayList<Integer>(Arrays.asList(
        R.drawable.body1,
        R.drawable.body2,
        R.drawable.body3,
        R.drawable.body4,
        R.drawable.body5,
        R.drawable.body6,
        R.drawable.body7,
        R.drawable.body8,
        R.drawable.body9,
        R.drawable.body10,
        R.drawable.body11,
        R.drawable.body12,
        R.drawable.body13,
        R.drawable.body14,
        R.drawable.body15,
        R.drawable.body16,
        R.drawable.body17,
        R.drawable.body18,
        R.drawable.body19
    ));

    private static final List<Integer> heads = new ArrayList<Integer>(Arrays.asList(
        R.drawable.head1,
        R.drawable.head2,
        R.drawable.head3,
        R.drawable.head4,
        R.drawable.head5,
        R.drawable.head6,
        R.drawable.head7,
        R.drawable.head8,
        R.drawable.head9,
        R.drawable.head10,
        R.drawable.head11,
        R.drawable.head12,
        R.drawable.head13,
        R.drawable.head14,
        R.drawable.head15,
        R.drawable.head16,
        R.drawable.head17,
        R.drawable.head18,
        R.drawable.head19
    ));

    private static final List<Integer> legs = new ArrayList<Integer>(Arrays.asList(
        R.drawable.legs1,
        R.drawable.legs2,
        R.drawable.legs3,
        R.drawable.legs4,
        R.drawable.legs5,
        R.drawable.legs6,
        R.drawable.legs7,
        R.drawable.legs8,
        R.drawable.legs10,
        R.drawable.legs11,
        R.drawable.legs12,
        R.drawable.legs13,
        R.drawable.legs14,
        R.drawable.legs15,
        R.drawable.legs16,
        R.drawable.legs17,
        R.drawable.legs18,
        R.drawable.legs19
    ));

    public static List<Integer> getBodies() {
        return bodies;
    }

    public static List<Integer> getHeads() {
        return heads;
    }

    public static List<Integer> getLegs() {
        return legs;
    }
}
```
### Bug fixes
* Try to flip your device ;) Lets try to fix AndroidifyViewPagerItemFragment:
``` java
public class AndroidifyViewPagerItemFragment extends Fragment {

    public static final String IMG_ID = "IMG_ID";

    private int imgId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            imgId = savedInstanceState.getInt(IMG_ID);
        }

        View rootView = inflater.inflate(R.layout.androidify_part, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.android_part);
        imageView.setImageResource(imgId);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(IMG_ID, imgId);
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
```
* And fix getItem() in AndroidifyViewPagerAdapter
``` java
@Override
public Fragment getItem(int position) {
    AndroidifyViewPagerItemFragment fragment = new AndroidifyViewPagerItemFragment();
    fragment.setImgId(imgIds.get(position));
    return fragment;
}
```
## Extended Functionality
* Lets add a control panel in main.xml layout
``` xml
<LinearLayout
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:weightSum="3"
        android:layout_weight="20">

    <LinearLayout
            android:layout_width="0dp"
            android:layout_weight="1"
            android:gravity="center"
            android:layout_height="wrap_content">

        <ImageButton
                android:id="@+id/button_share"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:src="@drawable/android_icon"
                android:background="@null"
                android:adjustViewBounds="true"
                android:scaleType="centerInside" />
    </LinearLayout>

    <LinearLayout
            android:layout_width="0dp"
            android:layout_weight="1"
            android:gravity="center"
            android:layout_height="wrap_content">

        <ImageButton
                android:id="@+id/button_record_sound"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:src="@drawable/record_off"
                android:background="@null"
                android:adjustViewBounds="true"
                android:scaleType="centerInside" />
    </LinearLayout>

    <LinearLayout
            android:layout_width="0dp"
            android:layout_weight="1"
            android:gravity="center"
            android:layout_height="wrap_content">

        <ImageButton
                android:id="@+id/button_play_sound"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:src="@drawable/play"
                android:background="@null"
                android:adjustViewBounds="true"
                android:scaleType="centerInside" />
    </LinearLayout>
</LinearLayout>
```
* Lets implement Androidify Share intent in MainActivity
``` java
@Override
protected void onCreate(Bundle savedInstanceState) {
    ...
    initShareButton(headViewPager, bodyViewPager, legsViewPager);
}

private void initShareButton(final ViewPager viewPagerHead, final ViewPager viewPagerBody, final ViewPager viewPagerLegs) {
    View shareButton = findViewById(R.id.button_share);
    shareButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ...
        }
    });
}
```
* Create BitmapUtils for creating an image Bitmap
``` java
public class BitmapUtils {

    public static Bitmap getBitmap(Resources resources, int drawableResourceId) {
        return BitmapFactory.decodeResource(resources,
                drawableResourceId);
    }

    public static Bitmap combineDrawables(Resources resources, int head, int body, int legs) {
        Bitmap headBitmap = getBitmap(resources, head);
        Bitmap bodyBitmap = getBitmap(resources, body);
        Bitmap legsBitmap = getBitmap(resources, legs);

        int height = headBitmap.getHeight() + bodyBitmap.getHeight() + legsBitmap.getHeight();
        int width = Math.max(headBitmap.getWidth(), Math.max(bodyBitmap.getWidth(), legsBitmap.getWidth()));

        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas comboImage = new Canvas(result);
        comboImage.drawBitmap(headBitmap, 0f, 0f, null);
        comboImage.drawBitmap(bodyBitmap, 0f, headBitmap.getHeight(), null);
        comboImage.drawBitmap(legsBitmap, 0f, headBitmap.getHeight() + bodyBitmap.getHeight(), null);

        return result;
    }
}
```
* Add share intent invocation onClick
``` java
private void initShareButton(final ViewPager viewPagerHead, final ViewPager viewPagerBody, final ViewPager viewPagerLegs) {
    View shareButton = findViewById(R.id.button_share);
    shareButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Integer head = AndroidDrawables.getHeads().get(viewPagerHead.getCurrentItem());
            Integer body = AndroidDrawables.getBodies().get(viewPagerBody.getCurrentItem());
            Integer legs = AndroidDrawables.getLegs().get(viewPagerLegs.getCurrentItem());

            Bitmap bitmap = BitmapUtils.combineDrawables(getResources(), head, body, legs);

            String imagePath = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Android Avatar", null);
            Uri imageURI = Uri.parse(imagePath);
            startShareActivity(imageURI);
        }
    });
}

private void startShareActivity(Uri imageURI) {
    Intent shareIntent = new Intent(Intent.ACTION_SEND);

    shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    shareIntent.putExtra(Intent.EXTRA_STREAM, imageURI);
    shareIntent.setType("image/png");

    startActivity(shareIntent);
}
```
* Oops we have an exception. Lets fix it
``` xml
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```
* Next stop - sound recording. Create AndroidSoundRecorder
``` java
public class AndroidSoundRecorder {

    private MediaRecorder mediaRecorder;
    private String mFileName;

    public AndroidSoundRecorder(String mFileName) {
        this.mFileName = Environment.getExternalStorageDirectory().
                getAbsolutePath() + "/" + mFileName;
    }

    public void startRecording() {
        if (mediaRecorder != null) stopRecording();

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setOutputFile(mFileName);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mediaRecorder.setAudioSamplingRate(44100);
        mediaRecorder.setAudioEncodingBitRate(16);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            Log.e(AndroidSoundRecorder.class.getSimpleName(), "prepare() failed");
        }

        mediaRecorder.start();

    }

    public void stopRecording() {
        if (mediaRecorder == null) return;

        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    public boolean isRecording() {
        return mediaRecorder != null;
    }

}
```
* Initiate RecordButton in MainActivity
``` java
...
private final String mySound = "my_recorded_sound";
private AndroidSoundRecorder soundRecorder;
...

@Override
protected void onCreate(Bundle savedInstanceState) {
    ...
    initRecordButton();
}

private void initRecordButton() {
    soundRecorder = new AndroidSoundRecorder(mySound);

    View recordButton = findViewById(R.id.button_record_sound);
    recordButton.setOnTouchListener(new View.OnTouchListener() {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            ImageButton recordButton = (ImageButton) view;

            if (MotionEvent.ACTION_UP == motionEvent.getAction()) {
                if (soundRecorder.isRecording()) {
                    soundRecorder.stopRecording();
                    recordButton.setImageDrawable(getResources().getDrawable(R.drawable.record_off));
                } else {
                    soundRecorder.startRecording();
                    recordButton.setImageDrawable(getResources().getDrawable(R.drawable.record_on));
                }
            }

            return false;
        }
    });
}
```
* Create AndroidSoundPlayer
```
public class AndroidSoundPlayer {

    private final String fileName;
    private MediaPlayer mediaPlayer;

    public AndroidSoundPlayer(String fileName) {
        this.fileName = Environment.getExternalStorageDirectory().
                getAbsolutePath() + "/" + fileName;
    }

    public void startPlaying() {
        if (mediaPlayer != null) stopPlaying();

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(fileName);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Log.e("SoundPlayer", "prepare() failed");
        }
    }

    public void stopPlaying() {
        if (mediaPlayer == null) return;
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }
}
```
* Initiate PlayButton in MainActivity
``` java
private AndroidSoundPlayer soundPlayer;
...

@Override
protected void onCreate(Bundle savedInstanceState) {
    ...
    initPlayButton();
}

private void initPlayButton() {
    soundPlayer = new AndroidSoundPlayer(mySound);

    View playButton = findViewById(R.id.button_play_sound);
    playButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (soundPlayer.isPlaying()) {
                soundPlayer.stopPlaying();
            } else {
                soundPlayer.startPlaying();
            }
        }
    });
}
```
* Add permission to AndroidManifest.xml
``` xml
<uses-permission android:name="android.permission.RECORD_AUDIO"/>
```
