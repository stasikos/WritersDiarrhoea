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

import javax.swing.JTextArea;

/**
 * Overriden to simplify copy/paste/select restriction
 * @author Stanislav Kogut (stasikos@gmail.com)
 */
public class SecureTextArea extends JTextArea {

    DisabledCaret scaret = new DisabledCaret();

    public SecureTextArea() {
        setCaret(scaret);
    }

    private boolean copyPaste = false;

    public boolean isCopyPasteEnabled() {
        return copyPaste;
    }

    public void setCopyPaste(boolean cp) {
        this.copyPaste = cp;
        scaret.setSelectionEnabled(cp);
    }

    @Override
    public void copy() {
        if (copyPaste) {
            super.copy();
        }
    }

    @Override
    public void cut() {
        if (copyPaste) {
            super.cut();
        }
    }

    @Override
    public void paste() {
        if (copyPaste) {
            super.paste();
        }
    }

    @Override
    public void select(int selectionStart, int selectionEnd) {
        if (copyPaste) {
            super.select(selectionStart, selectionEnd);
        }
    }

    @Override
    public void selectAll() {
        if (copyPaste) {
            super.selectAll();
        }
    }
}
