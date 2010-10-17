/*
 *  Copyright (C) 2010 Stanislav Kogut (stasikos@gmail.com)
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package name.stasikos.diarrhoea;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stanislav Kogut (stasikos@gmail.com)
 */
public class WriterTimer implements Runnable {
    private boolean started;
    private int time;
    private TimedActionListener object;

    public WriterTimer(TimedActionListener object, int time) {
        this.time = time;
        this.object = object;
    }

    public void run() {
        while (!Thread.interrupted()) {
            try {
                if (started) {
                    object.tick();
                    if (time == 0) {
                        stop();
                        object.deadLineReached();
//                        Thread.currentThread().interrupt();
                    }
                    time--;
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(WriterTimer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void start() {
        started = true;
    }

    public void stop() {
        started = false;
    }

    public synchronized int getTime() {
        return time;
    }

    public synchronized void setTime(int time) {
        this.time = time;
    }

}
