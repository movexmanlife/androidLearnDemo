package robot.com.rxjava;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 使用Lambda表达式来
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rxbinding_t_toolbar)
    Toolbar rxbindingTToolbar;
    @BindView(R.id.rxbinding_et_usual_approach)
    EditText rxbindingEtUsualApproach;
    @BindView(R.id.rxbinding_et_reactive_approach)
    EditText rxbindingEtReactiveApproach;
    @BindView(R.id.rxbinding_tv_show)
    TextView rxbindingTvShow;
    @BindView(R.id.rxbinding_fab_fab)
    FloatingActionButton rxbindingFabFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /* Zip -----------------------------------------------------------------------test
            ZipTest zipTest = new ZipTest(getApplicationContext());
            RxView.clicks(rxbindingFabFab).subscribe(zipTest::testzip2);
        */

        /* concat -----------------------------------------------------------------------test
            ConcatTest concatTest = new ConcatTest(getApplicationContext());
            RxView.clicks(rxbindingFabFab).subscribe(concatTest::testconcat2);
        */

        /* tolist -----------------------------------------------------------------------test
            ToListTest toListTest = new ToListTest(getApplicationContext());
            RxView.clicks(rxbindingFabFab).subscribe(toListTest::testToList2);
        */

        /* tofilter -----------------------------------------------------------------------test
            FilterTest filterTest = new FilterTest(getApplicationContext());
            RxView.clicks(rxbindingFabFab).subscribe(filterTest::testFilter);
        */

        /* toJustAndFrom -----------------------------------------------------------------------test
            JustAndFromTest justAndFromTest = new JustAndFromTest(getApplicationContext());
            RxView.clicks(rxbindingFabFab).subscribe(justAndFromTest::testFrom);
        */

        /* toDelay -----------------------------------------------------------------------test
            DelayTest delayTest = new DelayTest(getApplicationContext());
            RxView.clicks(rxbindingFabFab).subscribe(delayTest::testDelay2);
        */

        TakeTest takeTest = new TakeTest(getApplicationContext());
        RxView.clicks(rxbindingFabFab).subscribe(takeTest::testTake);

//        RepeatTest repeatTest = new RepeatTest(getApplicationContext());
//        RxView.clicks(rxbindingFabFab).subscribe(repeatTest::testRepeat);
    }
}