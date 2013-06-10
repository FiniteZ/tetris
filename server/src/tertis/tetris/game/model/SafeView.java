package tertis.tetris.game.model;

import java.rmi.RemoteException;

import tertis.tetris.game.view.TetrisView;

public class SafeView implements TetrisView {

	private final TetrisView proxy;
	
	public SafeView(TetrisView v) {
		proxy = v;
	}
	
	@Override
	public void setModel(TetrisModel model) {
		try {
			proxy.setModel(model);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void boardChanged() {
		try {
			proxy.boardChanged();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void scoreChanged() {
		try {
			proxy.scoreChanged();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void previewChanged() {
		try {
			proxy.previewChanged();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void queueChanged() {
		try {
			proxy.queueChanged();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void yourTurn(boolean isYourTurn) {
		try {
			proxy.yourTurn(isYourTurn);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void rowsToDelete(int[] row, int count) {
		try {
			proxy.rowsToDelete(row, count);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void gameOver() {
		try {
			proxy.gameOver();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}