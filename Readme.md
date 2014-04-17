Androidify Yourself app for GDG Riga event

## Basic Functionality
* Rename helloworld.xml layout to main.xml
* Split screen into parts
```
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
```
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
```
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
```
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
```
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
```
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
```
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.androidify_part, container, false);
    ImageView imageView = (ImageView) rootView.findViewById(R.id.android_part);
    imageView.setImageResource(imgId);
    return rootView;
}
```
* Add Fragment creating in AndroidifyViewPagerAdapter getItem(int position)
```
@Override
public Fragment getItem(int position) {
    return new AndroidifyViewPagerItemFragment(imgIds.get(position));
}
```
* Set Adapter to ViewPagers in MainActivity
```
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.helloworld);

    ViewPager headViewPager = (ViewPager) findViewById(R.id.viewPagerHead);
    ViewPager bodyViewPager = (ViewPager) findViewById(R.id.viewPagerBody);
    ViewPager legsViewPager = (ViewPager) findViewById(R.id.viewPagerLegs);

    FragmentManager fm = getActivity().getSupportFragmentManager();
    headViewPager.setAdapter(new AndroidifyViewPagerAdapter(fm, AndroidDrawables.getHeads()));
    bodyViewPager.setAdapter(new AndroidifyViewPagerAdapter(fm, AndroidDrawables.getBodies()));
    legsViewPager.setAdapter(new AndroidifyViewPagerAdapter(fm, AndroidDrawables.getLegs()));
}
```
* Create and register all Drawables
```
public class AndroidDrawables {

    private static final List<Integer> bodies = new ArrayList<Integer>() {{
        add(R.drawable.body1);
        add(R.drawable.body2);
        add(R.drawable.body3);
        add(R.drawable.body4);
        add(R.drawable.body5);
        add(R.drawable.body6);
        add(R.drawable.body7);
        add(R.drawable.body8);
        add(R.drawable.body9);
        add(R.drawable.body10);
        add(R.drawable.body11);
        add(R.drawable.body12);
        add(R.drawable.body13);
        add(R.drawable.body14);
        add(R.drawable.body15);
        add(R.drawable.body16);
        add(R.drawable.body17);
        add(R.drawable.body18);
        add(R.drawable.body19);
    }};

    private static final List<Integer> heads = new ArrayList<Integer>() {{
        add(R.drawable.head1);
        add(R.drawable.head2);
        add(R.drawable.head3);
        add(R.drawable.head4);
        add(R.drawable.head5);
        add(R.drawable.head6);
        add(R.drawable.head7);
        add(R.drawable.head8);
        add(R.drawable.head9);
        add(R.drawable.head10);
        add(R.drawable.head11);
        add(R.drawable.head12);
        add(R.drawable.head13);
        add(R.drawable.head14);
        add(R.drawable.head15);
        add(R.drawable.head16);
        add(R.drawable.head17);
        add(R.drawable.head18);
        add(R.drawable.head19);
    }};

    private static final List<Integer> legs = new ArrayList<Integer>() {{
        add(R.drawable.legs1);
        add(R.drawable.legs2);
        add(R.drawable.legs3);
        add(R.drawable.legs4);
        add(R.drawable.legs5);
        add(R.drawable.legs6);
        add(R.drawable.legs7);
        add(R.drawable.legs8);
        add(R.drawable.legs10);
        add(R.drawable.legs11);
        add(R.drawable.legs12);
        add(R.drawable.legs13);
        add(R.drawable.legs14);
        add(R.drawable.legs15);
        add(R.drawable.legs16);
        add(R.drawable.legs17);
        add(R.drawable.legs18);
        add(R.drawable.legs19);
    }};

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
## Extended Functionalityasic