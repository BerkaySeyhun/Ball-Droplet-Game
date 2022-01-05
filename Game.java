
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.event.MouseInputListener;
//we import the necessary things

//I implemented the necessary things to provide the required activities

@SuppressWarnings("serial")

public class Game extends JComponent implements ActionListener, MouseMotionListener, KeyListener, MouseInputListener {

	// I defined variables and initialized some of them
	private int ballx = 150;
	private int bally = 30;
	private int ballx1 = 100;
	private int bally1 = 10;
	private int paddlex = 0;
	private int ballySpeed = 7;
	private int ballxSpeed = 5;
	private int bally1Speed = 14;
	private int ballx1Speed = 10;
	public int score = 0;
	public int score1 = 0;
	private int scorefinal;
	public int bestscore;
	public int bestscore1;
	public int live = 21;
	private Timer timer = new Timer(10, this);
	private JMenu menuGame, menuDebug;
	private JMenuItem menuItemStart, menuItemStop, menuItemIncChange, menuItemDecChange, menuItemExit, menuItemAddLife;
	public boolean gameOver, started;
	private JMenuBar menuBar;
	// I used for using images
	BufferedImage imageBall;
	BufferedImage droplet;
	// I used for using droplets
	public Droplet droplets[];

	// Here we provide the required values ​​for the JFrame
	public Game() {
		// TODO Auto-generated constructor stub

		// add mouse listener

		this.addMouseListener(this);

		// try catch for droplet and imageBall is exist
		try {
			imageBall = ImageIO.read(new File("src/", "ImageBall.png"));
			droplet = ImageIO.read(new File("src/", "droplet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// initalizing random for droplets location
		Random ran = new Random();

		// using droplet class
		droplets = new Droplet[9];

		// using for displaying droplets and creating theirs x and y location
		for (int i = 0; i < droplets.length; i++) {
			droplets[i] = new Droplet();

			droplets[i].x = ran.nextInt(800);
			droplets[i].y = ran.nextInt(29);
			droplets[i].speedY = ran.nextInt(1) + 1;
		}

		// initialize menu items...
		setLayout(new BorderLayout());
		menuBar = new JMenuBar();

		// I wrote the menu keys and variables
		menuGame = new JMenu("Game");
		menuDebug = new JMenu("Debug");

		// I wrote the menu keys and variables
		menuItemStart = new JMenuItem("Start");
		menuItemExit = new JMenuItem("Exit");
		menuItemStop = new JMenuItem("Stop");
		menuItemIncChange = new JMenuItem("Increase Difficulty");
		menuItemDecChange = new JMenuItem("Decrease Difficulty");
		menuItemAddLife = new JMenuItem("Increase Life by 5");

		// I wrote the menu keys and variables
		// I wrote action listener for buttons
		menuItemExit.addActionListener(this);
		menuItemIncChange.addActionListener(this);
		menuItemDecChange.addActionListener(this);
		menuItemStart.addActionListener(this);
		menuItemStop.addActionListener(this);
		menuItemAddLife.addActionListener(this);

		// I added buttons
		menuGame.add(menuItemStart);
		menuGame.add(menuItemStop);
		menuGame.add(menuItemExit);
		menuDebug.add(menuItemIncChange);
		menuDebug.add(menuItemDecChange);
		menuDebug.add(menuItemAddLife);

		// I added buttons
		menuBar.setLayout(new GridLayout(0, 2));
		menuBar.add(menuGame);
		menuBar.add(menuDebug);

		add(menuBar, BorderLayout.NORTH); // add the menu bar to application...

		// setting size and visible
		setSize(800, 600);
		setVisible(true);
		setBackground(Color.BLACK);

	}

	// helper method for gain life and score mechanism
	public double calculateDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}

	// main method
	public static void main(String[] args) {

		// setting our JFrame name to our game name
		JFrame window = new JFrame(" *__* Game of Ball/Droplet *__*");
		Game g = new Game();

		// add parameters on window
		window.add(g);
		window.pack();
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setResizable(false);

	}

	// creating our newball variables
	public void newball(int ballx, int bally, int ballxspeed, int ballyspeed) {

		ballx = 150;
		bally = 30;
		ballxspeed = 5;
		ballyspeed = 7;

		JOptionPane.showMessageDialog(null, "New ball !");

		return;
	}

	@Override
	// setting our borders
	public Dimension getPreferredSize() {

		return new Dimension(800, 600);
	}

	@Override
	// using for drawing our imageBall and droplets
	protected void paintComponent(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);

		g.setColor(Color.RED);
		g.fillRect(0, 600, 800, 600);

		// draw our ball
		g.drawImage(imageBall, ballx, bally, null);
		g.setColor(Color.RED);

		// draw our droplets
		for (int i = 0; i < droplets.length; i++) {
			g.drawImage(droplet, droplets[i].x, droplets[i].y, null);
		}

		// checking our score
		if (score >= 99999) {
			g.setColor(Color.red);
			g.setFont(new Font("Times New Roman", 8, 50));
			g.drawString(String.valueOf("Score :" + score + score1), 30 / 1 - 15, 80);
		} else {
			g.setColor(Color.white);
			g.setFont(new Font("Times New Roman", 8, 50));
			g.drawString(String.valueOf("Score : " + score), 30 / 1 - 15, 80);
		}

		// displaying our life numbers
		g.drawString(String.valueOf("Life : " + live), 600 / 1 - 15, 80);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 8, 50));

		if (gameOver) {
			g.drawString(String.valueOf(" Best Score :" + scorefinal), 50 / 1 - 15, 200);
		}

		// stopping our game when when there is no live
		if (live < 0) {
			live = 0;
			g.setColor(Color.red);
			gameOver = true;
			timer.stop();

			// displaying our score on the screen
			g.setColor(Color.blue);

			// gameover
			g.drawString(String.valueOf("...Game Over..."), 50 / 1 - 15, 350);
			g.setColor(Color.blue);

			// displaying when the game finished
			g.drawString(String.valueOf(" Best Score :" + score), 50 / 1 - 15, 200);
		}
	}

	@Override

	// Here we use our buttons to code what happens when I press them.
	public void actionPerformed(ActionEvent e) {

		// start button
		if (e.getSource().equals(menuItemStart)) {
			System.out.println("starting...");
			timer.start();
		}

		// stop button
		if (e.getSource().equals(menuItemStop)) {
			System.out.println("stopping...");
			timer.stop();
		}

		// exit button
		if (e.getSource().equals(menuItemExit))
			// exit
			System.exit(1);

		// add life button
		if (e.getSource().equals(menuItemAddLife)) {
			live += 5;
		}

		// we increase droplet speed
		if (e.getSource().equals(menuItemIncChange)) {

			for (int i = 0; i < droplets.length; i++) {
				droplets[i].speedY++;

			}
			System.out.println("Increase Difficulty");
		}

		// we decrease droplet speed
		if (e.getSource().equals(menuItemDecChange)) {

			for (int i = 0; i < droplets.length; i++) {
				droplets[i].speedY--;

				if (droplets[i].speedY == 0) {
					droplets[i].speedY = 1;
				}
			}

			System.out.println("Decrease Difficulty");
		}

		// when the y reaches the max height decrease our live by 3
		for (int i = 0; i < droplets.length; i++) {
			droplets[i].y += droplets[i].speedY;
			if (droplets[i].y > 600) {
				droplets[i].y = 0;
				live -= 3;
			}
		}

		// we write the x and y values ​​needed to move our ball
		ballx = ballx + ballxSpeed;
		bally = bally + ballySpeed;
		if (ballx >= paddlex && ballx <= paddlex + 800 && bally >= 475) {
			ballySpeed = -7;
		}

		// we write the if conditions necessary for our ball to stay on the screen
		if (bally >= 700) {
			ballxSpeed = -7;
			score = 0;
			bally = 30;
		}
		// we write the if conditions necessary for our ball to stay on the screen
		if (bally <= 0) {
			ballySpeed = 7;
		}
		// we write the if conditions necessary for our ball to stay on the screen
		if (ballx >= 775) {
			ballxSpeed = -5;
		}
		// we write the if conditions necessary for our ball to stay on the screen
		if (ballx <= 0) {
			ballxSpeed = 5;
		}

		ballx1 = ballx1 + ballx1Speed;
		bally1 = bally1 + bally1Speed;

		// we write the if conditions necessary for our ball to stay on the screen
		if (ballx1 >= paddlex && ballx1 <= paddlex + 100 && bally1 >= 475) {
			bally1Speed = -14;
		}
		// we write the if conditions necessary for our ball to stay on the screen
		if (bally1 >= 700) {
			score1 = 0;
			bally1 = 10;
		}
		if (bally1 <= 0) {

			bally1Speed = 14;
		}
		// we write the if conditions necessary for our ball to stay on the screen
		if (ballx1 >= 775) {

			ballx1Speed = -10;
		}
		// we write the if conditions necessary for our ball to stay on the screen
		if (ballx1 <= 0) {

			ballx1Speed = 10;
		}

		// using score variables for keeping it updated
		bestscore = score;
		bestscore1 = score1;

		// using for updating score
		if (scorefinal > bestscore) {
			scorefinal = scorefinal;
		} else {
			scorefinal = bestscore1;
			scorefinal = score + score1;
		}

		// using for updating score
		if (scorefinal > bestscore) {

			scorefinal = scorefinal;

		} else {

			// using for updating score

			scorefinal = bestscore;
			scorefinal = score + score1;
		}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	// where mouse events happen
	public void mousePressed(MouseEvent e) {

		// getting our mouse x and y variables
		int mouseX = e.getX();
		int mouseY = e.getY();

		// check our x and y variables
		String str = "x = " + mouseX + ",y = " + mouseY;
		System.out.println(str);

		// When clicked, it calculates the distance to the ball using x and y values,
		// and increases health and points if it is within a certain value range.
		double distancedroplet = calculateDistanceBetweenPoints(mouseX, mouseY, droplets[0].x, droplets[0].y);
		int idx = 0;
		for (int c = 1; c < droplets.length; c++) {
			double cdistance = calculateDistanceBetweenPoints(mouseX, mouseY, droplets[c].x, droplets[c].y);
			if (cdistance < distancedroplet) {
				idx = c;
				distancedroplet = cdistance;
			}
		}

		// setting nearest droplets number
		Droplet theNumber = droplets[idx];
		System.out.println("droplet distance " + idx);

		// When clicked, it calculates the distance to the ball using x and y values,
		// and increases health and points if it is within a certain value range.
		
		if (distancedroplet < 60) {
			// setting new droplet y value
			theNumber.y = -50;
			score++;
			live += 3;
		}

		// When clicked, it calculates the distance to the ball using x and y values,
		// and increases health and points if it is within a certain value range.
		int idx2;
		double distancedroball = calculateDistanceBetweenPoints(ballx, bally, droplets[0].x, droplets[0].y);
		for (int c2 = 1; c2 < droplets.length; c2++) {
			double cdistance2 = calculateDistanceBetweenPoints(ballx, bally, droplets[c2].x, droplets[c2].y);
			if (cdistance2 < distancedroball) {
				idx2 = c2;
				distancedroball = cdistance2;
			}
		}

		// When clicked, it calculates the distance to the ball using x and y values,
		// and increases health and points if it is within a certain value range.
		if (distancedroball < 60) {
			live -= 3;
		}

		// When clicked, it calculates the distance to the ball using x and y values,
		// and increases health and points if it is within a certain value range.
		double distance = calculateDistanceBetweenPoints(mouseX, mouseY, ballx, bally);
		if (distance < 70) {
			score++;
			live++;
		}
		System.out.println("ball distance is " + distance);

		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {

		// TODO Auto-generated method stub
	}

}