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

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.text.DefaultCaret;

/**
 * Overriden to prevent text selection
 * @author Stanislav Kogut (stasikos@gmail.com)
 */
public class DisabledCaret extends DefaultCaret {

    public DisabledCaret() {
        setVisible(true);
    }

    @Override
    public int getMark() {
        return super.getDot();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // super.mouseMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //
    }



    @Override
    public void mousePressed(MouseEvent e) {
       positionCaret(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //super.mouseReleased(e);
    }

    // ----




}
