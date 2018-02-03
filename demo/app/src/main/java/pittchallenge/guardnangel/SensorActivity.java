package pittchallenge.guardnangel;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.MediaPlayer;

public class SensorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView img;
    private Handler handler;

    boolean emsFlag = false;

    private MediaPlayer alarm;

    private int BPM = 100;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder checkEMS = new AlertDialog.Builder(SensorActivity.this);
                checkEMS.setMessage("Please confirm EMS is urgently needed.");
                checkEMS.setCancelable(true);

                checkEMS.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                emsFlag = true;
                                dialog.cancel();
                            }
                        });

                checkEMS.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        });

                AlertDialog alert11 = checkEMS.create();
                alert11.show();
                if (emsFlag) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    //CALL EMS
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("Sensor");

        // Show sensor
        AlertDialog.Builder checkSensor = new AlertDialog.Builder(this);
        checkSensor.setMessage("Please confirm the sensor is connected and working properly.");
        checkSensor.setCancelable(false);

        checkSensor.setPositiveButton(
                "Ready",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        alarm = MediaPlayer.create(SensorActivity.this, R.raw.alarm);
                        final TextView m_bpm_view = findViewById(R.id.bpm_view);
                        img = findViewById(R.id.indicator);
                        m_bpm_view.setText(String.valueOf(BPM));

                        handler = new Handler();
                        for (int i=30; i<100; i++) {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    BPM--;
                                    m_bpm_view.setText(String.valueOf(BPM));
                                    pumpHeart();
                                    if (BPM <= 30) {
                                        startAlarm();
                                    }
                                }
                            }, 300*i);
                        }
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = checkSensor.create();
        alert11.show();


    }

    void startAlarm(){
        if(!alarm.isPlaying()){
            alarm.start();
            alarm.setLooping(true);

            final View view = findViewById(R.id.bpm_parent);

            final CountDownTimer alarmTimer = new CountDownTimer(60000, 1000) {
                public void onTick(long millisUntilFinished) {
                    Snackbar.make(view, "Seconds remaining: " + millisUntilFinished / 1000, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                public void onFinish() {
                    // ALERT EMS
                }
            }.start();

            AlertDialog.Builder checkAlarm = new AlertDialog.Builder(this);
            checkAlarm.setMessage("ALERT! EMS will be alerted if alarm persists for 60 seconds.");
            checkAlarm.setCancelable(false);

            checkAlarm.setPositiveButton(
                    "I'm okay",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            alarm.stop();
                            alarm.release();
                            alarmTimer.cancel();
                        }
                    });

            AlertDialog userAlert = checkAlarm.create();
            userAlert.show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sensor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_contact) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent myIntent = new Intent(SensorActivity.this, AboutActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_howto) {

        } else if (id == R.id.nav_set_alarm) {

        } else if (id == R.id.nav_help) {
            Intent myIntent = new Intent(SensorActivity.this, HelpActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_EMS) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void pumpHeart() {
        img.animate().scaleXBy(0.2f).scaleYBy(0.2f).setDuration(50).setListener(scaleUpListener);

//        graph.setDrawPulse(true);
    }

    private Animator.AnimatorListener scaleDownListener = new Animator.AnimatorListener() {

        @Override
        public void onAnimationStart(Animator animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            // img.animate().scaleXBy(0.2f).scaleYBy(0.2f).setDuration(100).setListener(scaleUpListener);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            // TODO Auto-generated method stub

        }
    };

    private Animator.AnimatorListener scaleUpListener = new Animator.AnimatorListener() {

        @Override
        public void onAnimationStart(Animator animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            img.animate().scaleXBy(-0.2f).scaleYBy(-0.2f).setDuration(50).setListener(scaleDownListener);

        }

        @Override
        public void onAnimationCancel(Animator animation) {
            // TODO Auto-generated method stub

        }
    };

    class HeartMonitorMessage {
        public boolean alarm;
        public int rate;
    }
}
