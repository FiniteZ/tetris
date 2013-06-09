package tertis.tetris.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import tertis.tetris.game.board.IntMatrix;

@SuppressWarnings("serial")
public class GridPanel extends JPanel {

    public GridPanel(int rols, int cols, boolean hasBorder, Color borderColor) {
        setLayout(new GridLayout(rols, cols));
        _grids = new Grid[rols][cols];
        for (int i = 0; i < rols; i++)
            for (int j = 0; j < cols; j++) {
                _grids[i][j] = new Grid(hasBorder, borderColor);
                add(_grids[i][j]);
            }
    }

    public int getRows() { return _grids.length; }
    
    public int getCols() { return _grids[0].length; }

    public void setModel(IntMatrix model) {
        reset();
        int colBegin = 0;
        if (model.getWidth() < getCols()) {
            colBegin = (getCols() - model.getWidth()) / 2;
        }
        int rowBegin = 0;
        if (model.getHeight() < getRows()) {
            rowBegin = (getRows() - model.getHeight()) / 2;
        }
        for (int i = 0; i < model.getHeight(); i++)
            for (int j = 0; j < model.getWidth(); j++) {
                _grids[i + rowBegin][j + colBegin].set(model.get(i, j));
            }
        repaint();
    }

    public void reset() {
        for (int i = 0; i < getRows(); i++)
            for (int j = 0; j < getCols(); j++)
                _grids[i][j].set(0);
    }

    public void blink(int row[], int count) {
        try {
            setRowsColor(row, 1, Color.RED);
            repaint();
            Thread.sleep(150);
            setRowsColor(row, 1, Color.GRAY);
//             repaint();
//             Thread.sleep(50);
//             setRowsColor(row, 1, Color.YELLOW);
//             repaint();
//             Thread.sleep(50);
//             setRowsColor(row, 1, Color.GREEN);
//             repaint();
//             Thread.sleep(50);
//             setRowsColor(row, 1, Color.CYAN);
//             repaint();
//             Thread.sleep(50);
//             setRowsColor(row, 1, Color.BLUE);
//             repaint();
//             Thread.sleep(50);
//             setRowsColor(row, 1, Color.MAGENTA);
            repaint();
        } catch(InterruptedException e) {}
    }

    private void setRowsColor(int row[], int count, Color color) {
        for (int i = 0; i < count; i++)
            for (int j = 0; j < getCols(); j++) {
                _grids[row[i]][j].setColor(color);
            }
    }

    static class Grid extends JComponent {

        public Grid(boolean hasBorder, Color borderColor) {
            if (hasBorder) setBorder(new LineBorder(borderColor));
        }
    
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int w = this.getWidth();
            int h = this.getHeight();
            
            g.setColor(_color);
            if (_on > 0)
                g.fillRect(0,0,w,h);
            else
                g.clearRect(0,0,w,h);
        }
        
        public Dimension getPreferredSize() {
            return new Dimension(40, 40);
        }

        public void set(int value) {
            _on = value;
        }    

        public int get() { return _on; }

        public void setColor(Color color) {
            _color = color;
        }
    
        private int _on = 0;
        
        private Color _color = Color.GRAY;
    }

    private Grid[][] _grids;
}