Androidify Yourself app for GDG Riga event

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

