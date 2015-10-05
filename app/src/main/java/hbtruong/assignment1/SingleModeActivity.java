package hbtruong.assignment1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import hbtruong.assignment1.IO.IOManager;
import hbtruong.assignment1.IO.StatsEntity;

/**
 * Created by huy on 24/09/15.
 *
 * hbtruong-reflex is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * hbtruong-reflex is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with hbtruong-reflex.  If not, see <http://www.gnu.org/licenses/>.
 */
public class SingleModeActivity extends AppCompatActivity {

    private long start, end;

    private StatsEntity stats = new StatsEntity();
    private ArrayList<Integer> onePlayers = new ArrayList<>();

    private TextView textView;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_mode);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Read file
        stats = new IOManager(SingleModeActivity.this).loadFromFile();
        onePlayers = stats.getOnePlayers();

        textView = (TextView) findViewById(R.id.text_ready);

        // AlertDialog from developer.android.com
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You are entering Single user mode that measures reaction times");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                timer = new Time().countDownTimer;
                timer.start();
            }
        });
        builder.show();

        Button button = (Button) findViewById(R.id.button_click);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check = ((TextView) findViewById(R.id.text_ready)).getText().toString();
                if (check.equals("Click")) {

                    Date date = new Date();
                    end = date.getTime();
                    int delay = (int) (end - start);

                    onePlayers.add(delay);

                    stats.setOnePlayers(onePlayers);
                    textView.setText(Integer.toString(delay));

                } else {
                    textView.setText("You clicked too early");
                    timer.cancel();
                }

                // Game restarts after 1 sec
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        textView.setText("Ready");
                        timer = new Time().countDownTimer;
                        timer.start();
                    }
                }, 1000);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Write file
        new IOManager(SingleModeActivity.this).writeToFile(stats);
    }

    private class Time {
        private Random random = new Random();
        private int number = random.nextInt(2000 - 10) + 10;

        // from developer.android.com
        public CountDownTimer countDownTimer = new CountDownTimer(number, 10) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                textView.setText("Click");
                Date date = new Date();
                start = date.getTime();
            }
        };
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
