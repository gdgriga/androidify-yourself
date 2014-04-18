Androidify Yourself app for GDG Riga event

## Basic Functionality
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