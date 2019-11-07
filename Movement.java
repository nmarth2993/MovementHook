import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Movement {
	
	JFrame frame;
	DrawPanel panel;
	
	Random r = new Random();
	
	final int width = 30;
	final int height = 30;
	final int delta = 1;
	final int timing = 5;
	
	final int DOT_SIZE = 29;
	
	final int pixels = 900;
	int dots = 3;
	
	int[] xPos = new int[pixels];
	int[] yPos = new int[pixels];
	
	int x = 0;
	int y = 0;
	
	public Movement() {
//		for (int z = 0; z < dots; z++) {
//            xPos[z] = 50 - z * DOT_SIZE;
//            yPos[z] = 50;
//        }
		frame = new JFrame();
		panel = new DrawPanel();
		
		frame.addKeyListener(new KeyListen());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.setVisible(true);
		panel.setPreferredSize(new Dimension(300, 300));
		frame.pack();
		
		new Thread() {
			public void run() {
				for(;;) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					panel.repaint();
				}
			}
		}.start();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				new Movement();
			}
		});
	}
	
	class KeyListen implements KeyListener {

		boolean upPressed = false;
		boolean downPressed = false;
		boolean leftPressed = false;
		boolean rightPressed = false;
		
		public KeyListen() {
			moveListener();
			new Thread() {
				public void run() {
					for (;;) {
						while (upPressed) {
							y -= delta;
							try {
								Thread.sleep(timing);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}.start();
			new Thread() {
				public void run() {
					for (;;) {
						while (downPressed) {
							y += delta;
							try {
								Thread.sleep(timing);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}.start();
			new Thread() {
				public void run() {
					for (;;) {
						while (leftPressed) {
							x -= delta;
							try {
								Thread.sleep(timing);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}.start();
			new Thread() {
				public void run() {
					for (;;) {
						while (rightPressed) {
							x += delta;
							try {
								Thread.sleep(timing);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
						}
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}.start();
		}
		
		
		public void moveListener() {
			new Thread() {
				public void run() {
					for (;;) {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (leftPressed) {
				            xPos[0] -= DOT_SIZE;
				        }

				        if (rightPressed) {
				            xPos[0] += DOT_SIZE;
				        }

				        if (upPressed) {
				            yPos[0] -= DOT_SIZE;
				        }

				        if (downPressed) {
				            yPos[0] += DOT_SIZE;
				        }
					}
				}
			}.start();
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
			keyPressed(e);
		}
		
	}
	
	class DrawPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.fillOval(x, y, width, height);
		}
	}
}
