package hbtruong.assignment1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hbtruong.assignment1.IO.IOManager;
import hbtruong.assignment1.IO.StatsEntity;

/**
 * Created by huy on 24/09/15.
 *
 * hbtruong-reflex is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * hbtruong-reflex is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with hbtruong-reflex.  If not, see <http://www.gnu.org/licenses/>.
 */
public class ViewStatsActivity extends AppCompatActivity {
    private ArrayList<Integer> onePlayers = new ArrayList<>();

    private StatsEntity stats = new StatsEntity();
    private StatsEntity.TwoPlayers twoPlayers = stats.new TwoPlayers();
    private StatsEntity.ThreePlayers threePlayers = stats.new ThreePlayers();
    private StatsEntity.FourPlayers fourPlayers = stats.new FourPlayers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stats);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Read file
        stats = new IOManager(ViewStatsActivity.this).loadFromFile();
        twoPlayers = stats.getTwoPlayers();
        threePlayers = stats.getThreePlayers();
        fourPlayers = stats.getFourPlayers();

        TextView reaction = (TextView) findViewById(R.id.text_reaction);
        reaction.setText(reactionTime());

        TextView buzzer = (TextView) findViewById(R.id.text_buzzer);
        buzzer.setText(buzzerCount());

        Button clear = (Button) findViewById(R.id.button_clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile("stats.sav");
                recreate();
            }
        });

        final Button email = (Button) findViewById(R.id.button_email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent();
                emailIntent.setAction(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Game statistics");
                emailIntent.putExtra(Intent.EXTRA_TEXT, reactionTime() + buzzerCount());
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
    }

    private String reactionTime() {

        int sum0=0, sum1=0, sum2=0;
        int min0=0, min1=0, min2=0;
        int max0=0, max1=0, max2=0;
        double avg0=0, avg1=0, avg2=0;
        double median0=0, median1=0, median2=0;

        onePlayers = stats.getOnePlayers();
        int size = onePlayers.size();

        if (size == 0) {
            return "Reaction time statistics: Empty\n";
        }

        // Calculate stats
        if (size < 10) {
            Collections.sort(onePlayers);

            min0 = onePlayers.get(0);
            min1 = min0;
            min2 = min0;

            max0 = onePlayers.get(size-1);
            max1 = max0;
            max2 = max0;

            for (int i=0; i<size; i++) {
                sum0+=onePlayers.get(i);
            }
            avg0 = sum0/size;
            avg1 = avg0;
            avg2 = avg0;

            if (size%2 == 0) {
                median0 = (onePlayers.get(size/2 - 1) + onePlayers.get(size/2)) / 2;
            } else {
                median0 = onePlayers.get((size-1) / 2);
            }
            median1 = median0;
            median2 = median0;

        } else if (size>=10 && size<100) {
            // Sublist without modifying original list
            List<Integer> list10 = new ArrayList<>(onePlayers.subList(size-10, size));

            Collections.sort(list10);
            Collections.sort(onePlayers);

            min0 = onePlayers.get(0);
            max0 = onePlayers.get(size -1);

            min1 = list10.get(0);
            max1 = list10.get(9);

            min2 = min0;
            max2 = max0;

            for (int i=0; i<size; i++) {
                sum0+=onePlayers.get(i);
            }
            avg0 = sum0/size;

            for (int i=0; i<10; i++) {
                sum1+=list10.get(i);
            }
            avg1 = sum1/10;

            avg2 = avg0;

            if (size%2 == 0) {
                median0 = (onePlayers.get(size/2 - 1) + onePlayers.get(size/2)) / 2;
            } else {
                median0 = onePlayers.get((size-1) / 2);
            }
            median1 = (list10.get(4) + list10.get(5)) / 2;
            median2 = median0;

        } else {
            List<Integer> list10 = new ArrayList<>(onePlayers.subList(size-10, size));
            List<Integer> list100 = new ArrayList<>(onePlayers.subList(size-100, size));

            Collections.sort(list10);
            Collections.sort(list100);
            Collections.sort(onePlayers);

            min0 = onePlayers.get(0);
            max0 = onePlayers.get(size -1);

            min1 = list10.get(0);
            max1 = list10.get(9);

            min2 = list100.get(0);
            max2 = list100.get(99);

            for (int i=0; i<size; i++) {
                sum0+=onePlayers.get(i);
            }
            avg0 = sum0/size;

            for (int i=0; i<10; i++) {
                sum1+=list10.get(i);
            }
            avg1 = sum1/10;

            for (int i=0; i<100; i++) {
                sum2+=list100.get(i);
            }
            avg2 = sum2/100;

            if (size%2 == 0) {
                median0 = (onePlayers.get(size/2 - 1) + onePlayers.get(size/2)) / 2;
            } else {
                median0 = onePlayers.get((size-1) / 2);
            }
            median1 = (list10.get(4) + list10.get(5)) / 2;
            median2 = (list100.get(49) + list10.get(50)) / 2;
        }

        return "Reaction time statistics:\n" +
                "Minimum time\n     all: " + min0 + "     last 10: " + min1 + "     last 100: " + min2 + "\n" +
                "Maximum time\n     all: " + max0 + "     last 10: " + max1 + "     last 100: " + max2 + "\n" +
                "Average time\n     all: " + avg0 + "     last 10: " + avg1 + "     last 100: " + avg2 + "\n" +
                "Median time\n     all: " + median0 + "     last 10: " + median1 + "     last 100: " + median2 + "\n";
    }

    private String buzzerCount() {
        twoPlayers = stats.getTwoPlayers();
        threePlayers = stats.getThreePlayers();
        fourPlayers = stats.getFourPlayers();

        return "Buzzer counts: \n" +
                "2 players: " + twoPlayers.toString() + "\n" +
                "3 players: " + threePlayers.toString() + "\n" +
                "4 players: " + fourPlayers.toString() + "\n";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_stats, menu);
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
