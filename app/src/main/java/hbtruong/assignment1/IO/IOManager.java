package hbtruong.assignment1.IO;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by huy on 04/10/15.
 *
 * hbtruong-reflex is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * hbtruong-reflex is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with hbtruong-reflex.  If not, see <http://www.gnu.org/licenses/>.
 */
public class IOManager {
    private Context ctx;

    public IOManager(Context ctx) {
        this.ctx = ctx;
    }

    // from CMPUT301 lab
    public StatsEntity loadFromFile() {
        StatsEntity stats = new StatsEntity();

        try {
            FileInputStream fis = ctx.openFileInput("stats.sav");
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Following line based on https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html retrieved 2015-09-21
            //Type listType = new TypeToken<ArrayList<String>>() {}.getType();
            stats = gson.fromJson(in, StatsEntity.class);

        } catch (FileNotFoundException e) {
            stats = new StatsEntity();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stats;
    }

    // from CMPUT301 lab
    public void writeToFile(StatsEntity stats) {
        try {
            FileOutputStream fos = ctx.openFileOutput("stats.sav", 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(stats, writer);
            Log.d("gson", gson.toJson(stats));
            writer.flush();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
