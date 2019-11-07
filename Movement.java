import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Movement {

	JFrame frame;
	DrawPanel panel;

	final static int DIAMETER = 25;
	final int delta = 1;
	final int timing = 5;

	int x;
	int y;

	public Movement() {
		x = y = 0;

		frame = new JFrame();
		panel = new DrawPanel();

		frame.addKeyListener(new KeyListen());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.setVisible(true);
		panel.setPreferredSize(new Dimension(300, 300));
		frame.pack();
	}

	class KeyListen implements KeyListener {

		boolean upPressed;
		boolean downPressed;
		boolean leftPressed;
		boolean rightPressed;

		public KeyListen() {
			upPressed = downPressed = leftPressed = rightPressed = false;
			new Thread(() -> {
				for (;;) {
					if (upPressed) {
						y -= delta;
					}
					if (downPressed) {
						y += delta;
					}
					if (leftPressed) {
						x -= delta;
					}
					if (rightPressed) {
						x += delta;
					}
					try {
						Thread.sleep(timing);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					panel.repaint();
				}
			}).start();
		}

		@Override
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = true;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = false;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			keyPressed(e);
		}

	}

	class DrawPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.fillOval(x, y, DIAMETER, DIAMETER);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				new Movement();
			}
		});
	}
}
