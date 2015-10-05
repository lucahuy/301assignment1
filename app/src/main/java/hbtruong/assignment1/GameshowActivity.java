package hbtruong.assignment1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import hbtruong.assignment1.IO.IOManager;
import hbtruong.assignment1.IO.StatsEntity;

/**
 * Created by huy on 24/09/15.
 *
 * hbtruong-reflex is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * hbtruong-reflex is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with hbtruong-reflex.  If not, see <http://www.gnu.org/licenses/>.
 */
public class GameshowActivity extends AppCompatActivity {

    private LinearLayout gameShow;

    private StatsEntity stats = new StatsEntity();
    private StatsEntity.TwoPlayers twoPlayers = stats.new TwoPlayers();
    private StatsEntity.ThreePlayers threePlayers = stats.new ThreePlayers();
    private StatsEntity.FourPlayers fourPlayers = stats.new FourPlayers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameshow);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Read file
        stats = new IOManager(GameshowActivity.this).loadFromFile();
        twoPlayers = stats.getTwoPlayers();
        threePlayers = stats.getThreePlayers();
        fourPlayers = stats.getFourPlayers();

        gameShow = (LinearLayout) findViewById(R.id.gameshow);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String[] selections = {"2", "3", "4"};
        builder.setTitle("Please select number of players")
                .setItems(selections, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        createButtons(which + 2);
                    }
                });
        builder.show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Write file
        new IOManager(GameshowActivity.this).writeToFile(stats);
    }

    private void createButtons(final int mode) {
        // from developer.android.com
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, gameShow.getHeight()/mode);

        for (int i = 0; i < mode; i++) {
            Button button = new Button(GameshowActivity.this);
            button.setLayoutParams(params);
            button.setText("Player " + (i+1));
            gameShow.addView(button);

            final int j = i;

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (mode) {
                        case 2:
                            if (j == 0) {
                                twoPlayers.setPlayer1(twoPlayers.getPlayer1() + 1);
                            } else {
                                twoPlayers.setPlayer2(twoPlayers.getPlayer2() + 1);
                            }

                            stats.setTwoPlayers(twoPlayers);
                            break;

                        case 3:
                            if (j == 0) {
                                threePlayers.setPlayer1(threePlayers.getPlayer1() + 1);
                            } else if (j == 1){
                                threePlayers.setPlayer2(threePlayers.getPlayer2() + 1);
                            } else {
                                threePlayers.setPlayer3(threePlayers.getPlayer3() + 1);
                            }

                            stats.setThreePlayers(threePlayers);
                            break;

                        case 4:
                            if (j == 0) {
                                fourPlayers.setPlayer1(fourPlayers.getPlayer1() + 1);
                            } else if (j == 1) {
                                fourPlayers.setPlayer2(fourPlayers.getPlayer2() + 1);
                            } else if (j == 2) {
                                fourPlayers.setPlayer3(fourPlayers.getPlayer3() + 1);
                            } else {
                                fourPlayers.setPlayer4(fourPlayers.getPlayer4() + 1);
                            }

                            stats.setFourPlayers(fourPlayers);
                            break;
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(GameshowActivity.this);
                    builder.setMessage("Player " + (j+1) + " pressed the button first");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                }

            });
        }
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
