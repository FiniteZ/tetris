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

	private final int width;
	private final int height;
	private Grid[][] grids;

	public GridPanel(int rows, int cols, boolean hasBorder, Color borderColor) {
		width = cols;
		height = rows;
		
		setLayout(new GridLayout(rows, cols));
		grids = new Grid[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grids[i][j] = new Grid(hasBorder, borderColor);
				add(grids[i][j]);
			}
		}
	}

	public int getRows() {
		return grids.length;
	}

	public int getCols() {
		return grids[0].length;
	}

	public void setModel(IntMatrix model) {
		reset();
		int colBegin = 0;
		if (model.width < width) {
			colBegin = (width - model.width) / 2;
		}
		int rowBegin = 0;
		if (model.height < height) {
			rowBegin = (height - model.height) / 2;
		}
		for (int i = 0; i < model.height; i++) {
			for (int j = 0; j < model.width; j++) {
				grids[i + rowBegin][j + colBegin].set(model.get(i, j));
			}
		}
		repaint();
	}

	public void reset() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				grids[i][j].set(0);
			}
		}
	}

	public void blink(int row[], int count) {
		try {
			setRowsColor(row, count, Color.RED);
			repaint();
			Thread.sleep(150);
			setRowsColor(row, count, Color.GRAY);
			repaint();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void setRowsColor(int row[], int count, Color color) {
		for (int i = 0; i < count; i++)
			for (int j = 0; j < getCols(); j++) {
				grids[row[i]][j].setColor(color);
			}
	}

	static class Grid extends JComponent {

		private int on = 0;
		private Color color = Color.GRAY;

		public Grid(boolean hasBorder, Color borderColor) {
			if (hasBorder)
				setBorder(new LineBorder(borderColor));
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int w = this.getWidth();
			int h = this.getHeight();

			g.setColor(color);
			if (on > 0) {
				g.fillRect(0, 0, w, h);
			} else {
				g.clearRect(0, 0, w, h);
			}
		}

		public Dimension getPreferredSize() {
			return new Dimension(40, 40);
		}

		public void set(int value) {
			on = value;
		}

		public int get() {
			return on;
		}

		public void setColor(Color color) {
			this.color = color;
		}
	}
}