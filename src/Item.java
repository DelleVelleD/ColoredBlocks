
// Project created by Ibrahim Manfoud

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Item implements ActionListener, KeyListener, MouseListener, MouseMotionListener, Serializable {
	JFrame frame = new JFrame();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private static final long serialVersionUID = -12345679L;
	private Color c;
	public static Item item;
	private Color Player;
	private Renderer renderer;
	private Rectangle player;
	
	private ArrayList<Rectangle> whiteBox;
	private ArrayList<Rectangle> redBox;
	private ArrayList<Rectangle> blueBox;
	private ArrayList<Rectangle> greenBox;
	private ArrayList<Rectangle> orangeBox;
	private ArrayList<Rectangle> orangeLongBox;
	private ArrayList<Rectangle> redLongBox;
	private ArrayList<Rectangle> greenLongBox;
	private ArrayList<Rectangle> blueLongBox;
	
	public int mx;
	public int my;
	private static int speed;
	private static int score = 0;
	private static double colorPlayer;
	private static double boxLocation;
	private static double XorY;
	private static double orangeLocation;
	public static int classicEasyScore = 0;
	public static int classicMediumScore = 0;
	public static int classicHardScore = 0;
	public static int classicInsaneScore = 0;
	public static int fourWaysEasyScore = 0;
	public static int fourWaysMediumScore = 0;
	public static int fourWaysHardScore = 0;
	public static int fourWaysInsaneScore = 0;
	public static int storyScore = 0;
	public static int temp = 0;
	public int frameWidth = 600;
	public int frameHeight = 600;
	
	private Random rand;
	
	private static BufferedImage iHowtoplay;
	private static BufferedImage iGamemodes;
	private static BufferedImage iSettings;
	private static BufferedImage iColor;
	private static BufferedImage iBlocks;
	private static BufferedImage iClassicGame;
	private static BufferedImage iBorderSetting;
	private static BufferedImage iOn;
	private static BufferedImage iOff;
	private static BufferedImage iExit;
	private static BufferedImage iScoreMessage;
	private static BufferedImage iHscore;
	private static BufferedImage iPlayAgain;
	private static BufferedImage iEasy;
	private static BufferedImage iMedium;
	private static BufferedImage iHard;
	private static BufferedImage iInsane;
	private static BufferedImage iGameStartImage;
	private static BufferedImage iControls;
	private static BufferedImage iWasd;
	private static BufferedImage iArrows;
	private static BufferedImage iAlldir;
	private static BufferedImage iStorymode;
	private static BufferedImage iPogchamp; // added by delle 10/20/17
	
	private Rectangle rHowToPlay = new Rectangle(frameWidth/3, frameHeight/2 - frameHeight/60, frameWidth/3, frameHeight/10);
	private Rectangle rGamemodes = new Rectangle(frameWidth/3, frameHeight - frameHeight/5 , frameWidth/3, frameHeight/10);
	private Rectangle rSettings = new Rectangle(frameWidth/3, frameHeight/2 + frameHeight/7 , frameWidth/3, frameHeight/10);
	private Rectangle rColor = new Rectangle(frameWidth/4, frameHeight/10, frameWidth/2, frameHeight/8);
	private Rectangle rBlocks = new Rectangle(frameWidth/4, frameHeight/4 , frameWidth/2, frameHeight/8);
	private Rectangle rBorder = new Rectangle(frameWidth/4 + frameWidth/40, frameHeight/12, frameWidth/2 - frameWidth/20, frameHeight/6);
	private Rectangle rControls = new Rectangle(frameWidth/5, frameHeight/2, frameWidth/2 + frameWidth/10, frameHeight/6);
	private Rectangle rScore = new Rectangle(frameWidth - frameWidth/40, frameHeight/24, 1, 1);
	private Rectangle rClassicGame = new Rectangle();
	private Rectangle rOn = new Rectangle(frameWidth/4 - frameWidth/120, frameHeight/4 + frameHeight/30, frameWidth/5 + frameWidth/30, frameHeight/6);
	private Rectangle rOff = new Rectangle(frameWidth/2 + frameWidth/40, frameHeight/4 + frameHeight/30, frameWidth/5 + frameWidth/30, frameHeight/6);
	private Rectangle rExit = new Rectangle(frameWidth/30, frameHeight/30, frameWidth/10, frameHeight/15);
	private Rectangle rEasy = new Rectangle();
	private Rectangle rMedium = new Rectangle();
	private Rectangle rHard = new Rectangle();
	private Rectangle rInsane = new Rectangle();
	private Rectangle rWasd = new Rectangle(frameWidth/12, frameHeight/2 + frameHeight/6 + frameHeight/20, frameWidth/3, frameHeight/6);
	private Rectangle rArrows = new Rectangle(frameWidth/2, frameHeight/2 + frameHeight/6 + frameHeight/20, frameWidth/2 - frameWidth/12, frameHeight/6);
	private Rectangle rAlldir = new Rectangle();
	private Rectangle rStorymode = new Rectangle();
	
	private static boolean border = true;
	private static boolean control = true;
	private static double r1 = .1;
	private static double r2 = .4;
	public static boolean debugMode = false; //added by delle 10/20/17
	public static boolean playerIsSkinned = false; //added by delle 10/20/17
	
	//list of themes
	public static enum THEME { // added by delle 10/20/17
		defaultTheme, spaceTheme, eyeCancer, colorCopy
	};
	
	public static THEME theme = THEME.defaultTheme;
	
	//list of skins
	public static enum SKIN { // added by delle 10/20/17
		defaultSkin, PogChamp
	};
	
	public static SKIN skin = SKIN.defaultSkin;

	//list of game states
	public static enum STATE {
		mainMenu, settings, gameModes, classicChoices, classicEasy, classicMedium, classicHard, classicEasy2, classicMedium2, classicHard2, classicStarterMedium, classicStarterEasy, classicStarterHard, fourWaysChoices, fourWaysEasy, fourWaysMedium, fourWaysHard, fourWaysEasy2, fourWaysMedium2, fourWaysHard2, fourWaysStarterEasy, fourWaysStarterMedium, fourWaysStarterHard, storyGame, storyGame2, storyGameStarter, fourWaysStarterInsane, classicStarterInsane, fourWaysInsane, fourWaysInsane2, classicInsane, classicInsane2, boxGameState, boxGameState2
	};
	
	public static STATE state = STATE.mainMenu;

	public Item() {
		rand = new Random();
		whiteBox = new ArrayList<Rectangle>();
		redBox = new ArrayList<Rectangle>();
		blueBox = new ArrayList<Rectangle>();
		greenBox = new ArrayList<Rectangle>();
		orangeBox = new ArrayList<Rectangle>();
		orangeLongBox = new ArrayList<Rectangle>();
		greenLongBox = new ArrayList<Rectangle>();
		blueLongBox = new ArrayList<Rectangle>();
		redLongBox = new ArrayList<Rectangle>();
		renderer = new Renderer();
		Timer timer = new Timer(20, this);
		frame.addKeyListener((KeyListener) this);
		frame.addMouseListener((MouseListener) this);
		frame.addMouseMotionListener((MouseMotionListener) this);
		frame.add(renderer);
		frame.setTitle("Color Blocks");
		frame.setSize(frameWidth, frameHeight);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.black);
		player = new Rectangle(0, 0, frameWidth/30, frameHeight/30);
		timer.start();
		rf();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		setImages();
		item = new Item();
	}
	
	//read file method
	public static void rf() {
		ReadFile r = new ReadFile();
		r.openFile();
		ArrayList<String> list = r.readFile();
		r.closeFile();
		classicEasyScore = Integer.parseInt(list.get(1));
	}

	public void actionPerformed(ActionEvent e) {
		classic();
		fourWays();
		boxGame();
		renderer.repaint();
	}

	//loads all images in ColorImages folder
	public static void setImages() {
		try {
			System.out.println(">Loading textures");
			iHowtoplay = ImageIO.read((Item.class.getResource("howtoplay.png")));
			iGamemodes = ImageIO.read((Item.class.getResource("gamemodes.png")));
			iColor = ImageIO.read((Item.class.getResource("color.png")));
			iBlocks = ImageIO.read((Item.class.getResource("blocks.png")));
			iClassicGame = ImageIO.read((Item.class.getResource("classic.png")));
			iSettings = ImageIO.read((Item.class.getResource("Settings.png")));
			iBorderSetting = ImageIO.read((Item.class.getResource("border.png")));
			iOn = ImageIO.read((Item.class.getResource("On.png")));
			iOff = ImageIO.read((Item.class.getResource("Off.png")));
			iExit = ImageIO.read((Item.class.getResource("exit.png")));
			iScoreMessage = ImageIO.read((Item.class.getResource("scoreText.png")));
			iHscore = ImageIO.read((Item.class.getResource("highscore.png")));
			iPlayAgain = ImageIO.read((Item.class.getResource("playAgain.png")));
			iEasy = ImageIO.read((Item.class.getResource("easy.png")));
			iMedium = ImageIO.read((Item.class.getResource("medium.png")));
			iHard = ImageIO.read((Item.class.getResource("hard.png")));
			iGameStartImage = ImageIO.read((Item.class.getResource("startGame.png")));
			iControls = ImageIO.read((Item.class.getResource("controls.png")));
			iArrows = ImageIO.read((Item.class.getResource("arrows.png")));
			iWasd = ImageIO.read((Item.class.getResource("wasd.png")));
			iAlldir = ImageIO.read((Item.class.getResource("alldir.png")));
			iStorymode = ImageIO.read((Item.class.getResource("storymode.png")));
			iInsane = ImageIO.read((Item.class.getResource("insane.png")));
			iPogchamp = ImageIO.read((Item.class.getResource("pogchamp.png"))); // added by delle 10/20/17
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	// the repaint method uses game states and variables to determine what
	// should be displayed on the jframe
	public void repaint(Graphics g) {
		
		//System.out.println(">REPAINTING");
		//creates the black background
		g.setColor(Color.black);
		g.fillRect(0, 0, frameWidth, frameHeight);
		//System.out.println(">Filling background with black");
		
		
		//builds the box gamemode
		if (state == STATE.boxGameState) {
			buildTheme(g);
			buildBorder(g);
			buildPlayer(g);
			
			//draws colored gates (columns)
			for (Rectangle red : redLongBox) {
				paintRedColumn(g, red);
			}
			for (Rectangle blue : blueLongBox) {
				paintBlueColumn(g, blue);
			}
			for (Rectangle green : greenLongBox) {
				paintGreenColumn(g, green);
			}
			for (Rectangle orange : orangeLongBox) {
				paintOrangeColumn(g, orange);
			}
		}

		
		if (state == STATE.classicMedium || state == STATE.classicEasy || state == STATE.classicHard
				|| state == STATE.fourWaysMedium || state == STATE.fourWaysEasy || state == STATE.fourWaysHard
				|| state == STATE.classicInsane || state == STATE.fourWaysInsane || state == STATE.storyGame) {
			
			buildTheme(g); // added by delle 10/20/17
			buildBorder(g);
			buildPlayer(g);
			
			for (Rectangle white : whiteBox) {
				paintWhiteColumn(g, white);
			}
			for (Rectangle red : redBox) {
				paintRedColumn(g, red);
			}
			for (Rectangle blue : blueBox) {
				paintBlueColumn(g, blue);
			}
			for (Rectangle green : greenBox) {
				paintGreenColumn(g, green);
			}
			for (Rectangle orange : orangeBox) {
				paintOrangeColumn(g, orange);
			}
			g.setColor(Color.white);
			g.drawString("Score: " + score, frameWidth/40, frameHeight/24);
			g.drawString("Speed: " + speed, frameWidth/40, frameHeight/24 + frameHeight/60);
			if (state == STATE.fourWaysEasy) {
				g.drawString("High Score: " + fourWaysEasyScore, frameWidth - frameWidth/40, frameHeight/24);
			}
			if (state == STATE.fourWaysMedium) {
				g.drawString("High Score: " + fourWaysMediumScore, frameWidth - frameWidth/40, frameHeight/24);
			}
			if (state == STATE.fourWaysHard) {
				g.drawString("High Score: " + fourWaysHardScore, frameWidth - frameWidth/40, frameHeight/24);
			}
			if (state == STATE.fourWaysInsane) {
				g.drawString("High Score: " + fourWaysInsaneScore, frameWidth - frameWidth/40, frameHeight/24);
			}
			if (state == STATE.classicEasy) {
				g.drawString("High Score: " + classicEasyScore, frameWidth - frameWidth/40, frameHeight/24);
			}
			if (state == STATE.classicMedium) {
				g.drawString("High Score: " + classicMediumScore, frameWidth - frameWidth/40, frameHeight/24);
			}
			if (state == STATE.classicHard) {
				g.drawString("High Score: " + classicHardScore, frameWidth - frameWidth/40, frameHeight/24);
			}
			if (state == STATE.classicInsane) {
				g.drawString("High Score: " + classicInsaneScore, frameWidth - frameWidth/40, frameHeight/24);
			}
			if (state == STATE.storyGame) {
				g.drawString("High Score: " + storyScore, frameWidth - frameWidth/40, frameHeight/24);
			}
		}
		
		if (state == STATE.mainMenu) {
			buildTheme(g);
			buildBorder(g);
			g.drawImage(iHowtoplay, rHowToPlay.x, rHowToPlay.y, rHowToPlay.width, rHowToPlay.height, null);
			g.drawImage(iGamemodes, frameWidth/3, frameHeight - frameHeight/5 , frameWidth/3, frameHeight/10, null);
			g.drawImage(iSettings, frameWidth/3, frameHeight/2 + frameHeight/7 , frameWidth/3, frameHeight/10, null);
			g.drawImage(iColor, frameWidth/4, frameHeight/10, frameWidth/2, frameHeight/8, null);
			g.drawImage(iBlocks, frameWidth/4, frameHeight/4 , frameWidth/2, frameHeight/8, null);
		}
		
		if (state == STATE.settings) {
			buildTheme(g);
			buildBorder(g);
			if (control) {
				//arrows white border
				g.setColor(Color.white);
				g.fillRect(frameWidth/2 - frameWidth/120, frameHeight/2 + frameHeight/6 + frameHeight/20 - frameHeight/120, frameWidth/2 - frameWidth/12 + frameWidth/60, frameHeight/6 + frameHeight/60);
			} else {
				//wasd white border
				g.setColor(Color.white);
				g.fillRect(frameWidth/12 - frameWidth/120, frameHeight/2 + frameHeight/6 + frameHeight/20 - frameHeight/120, frameWidth/3 + frameWidth/60, frameHeight/6 + frameHeight/60);
			}
			if(border) {
				//on white border
				g.setColor(Color.white);
				g.fillRect(frameWidth/4 - frameWidth/60, frameHeight/4 + frameHeight/30 - frameHeight/120, frameWidth/5 + frameWidth/30 + frameWidth/60, frameHeight/6 + frameHeight/60);
			} else {
				//off white border
				g.setColor(Color.white);
				g.fillRect(frameWidth/2 + frameWidth/40 - frameWidth/120, frameHeight/4 + frameHeight/30 - frameHeight/120, frameWidth/5 + frameWidth/30 + frameWidth/60, frameHeight/6 + frameHeight/60);
			}
			g.drawImage(iBorderSetting, frameWidth/4 + frameWidth/40, frameHeight/12, frameWidth/2 - frameWidth/20, frameHeight/6, null);
			g.drawImage(iOn, frameWidth/4 - frameWidth/120, frameHeight/4 + frameHeight/30, frameWidth/5 + frameWidth/30, frameHeight/6, null);
			g.drawImage(iOff, frameWidth/2 + frameWidth/40, frameHeight/4 + frameHeight/30, frameWidth/5 + frameWidth/30, frameHeight/6, null);
			g.drawImage(iExit, 20, 20, frameWidth/10, frameHeight/15, null);
			g.drawImage(iControls, frameWidth/5, frameHeight/2, frameWidth/2 + frameWidth/10, frameHeight/6, null);
			g.drawImage(iWasd, frameWidth/12, frameHeight/2 + frameHeight/6 + frameHeight/20, frameWidth/3, frameHeight/6, null);
			g.drawImage(iArrows, frameWidth/2, frameHeight/2 + frameHeight/6 + frameHeight/20, frameWidth/2 - frameWidth/12, frameHeight/6, null);
		}
		
		if (state == STATE.gameModes) {
			buildTheme(g);
			buildBorder(g);
			g.drawImage(iClassicGame, frameWidth/4 + frameWidth/24, frameHeight/4 + frameHeight/15, frameWidth/3 + frameWidth/12, frameHeight/6, null);
			g.drawImage(iExit, rExit.x, rExit.y, rExit.width, rExit.height, null);
			g.drawImage(iAlldir, frameWidth/6 - frameWidth/24, frameHeight/2 + frameHeight/30, frameWidth/2 + frameWidth/4, frameHeight/6, null);
			g.drawImage(iStorymode, frameWidth/6, frameHeight/12, frameWidth/2 + frameWidth/6, frameWidth/6, null);
		}
		
		if (state == STATE.classicMedium2 || state == STATE.classicEasy2 || state == STATE.classicHard2
				|| state == STATE.fourWaysMedium2 || state == STATE.fourWaysEasy2 || state == STATE.fourWaysHard2
				|| state == STATE.storyGame2 || state == STATE.classicInsane2 || state == STATE.fourWaysInsane2) {
			buildTheme(g);
			buildBorder(g);
			g.drawImage(iExit, 20, 20, 78, 44, null);
			g.drawImage(iScoreMessage, 40, 200, null);
			g.drawImage(iHscore, 35, 300, null);
			Font font = new Font("times", 10, 50);
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("" + score, 472, 250);
			if (state == STATE.fourWaysEasy2) {
				g.drawString("" + fourWaysEasyScore, 478, 350);
			}
			if (state == STATE.fourWaysMedium2) {
				g.drawString("" + fourWaysMediumScore, 478, 350);
			}
			if (state == STATE.fourWaysHard2) {
				g.drawString("" + fourWaysHardScore, 478, 350);
			}
			if (state == STATE.fourWaysInsane2) {
				g.drawString("" + fourWaysInsaneScore, 478, 350);
			}
			if (state == STATE.classicEasy2) {
				g.drawString("" + classicEasyScore, 478, 350);
			}
			if (state == STATE.classicMedium2) {
				g.drawString("" + classicMediumScore, 478, 350);
			}
			if (state == STATE.classicHard2) {
				g.drawString("" + classicHardScore, 478, 350);
			}
			if (state == STATE.classicInsane2) {
				g.drawString("" + classicInsaneScore, 478, 350);
			}
			if (state == STATE.storyGame2) {
				g.drawString("" + storyScore, 478, 350);
			}
			g.drawImage(iPlayAgain, 34, 420, null);
		}
		
		if (state == STATE.classicChoices || state == STATE.fourWaysChoices) {
			buildTheme(g);
			buildBorder(g);
			g.drawImage(iEasy, 208, 30, null);
			g.drawImage(iMedium, 160, 165, null);
			g.drawImage(iHard, 204, 300, null);
			g.drawImage(iInsane, 181, 435, null);
			g.drawImage(iExit, 20, 20, 78, 44, null);
		}
		
		if (state == STATE.classicStarterEasy || state == STATE.classicStarterMedium
				|| state == STATE.classicStarterHard || state == STATE.fourWaysStarterHard
				|| state == STATE.fourWaysStarterEasy || state == STATE.fourWaysStarterMedium
				|| state == STATE.storyGameStarter || state == STATE.classicStarterInsane
				|| state == STATE.fourWaysStarterInsane) {
			buildTheme(g);
			buildBorder(g);
			g.drawImage(iGameStartImage, 52, 227, null);
		}
	}

	//build border method
	public void buildBorder(Graphics g) {
		if (border) {
			//left border
			g.setColor(Color.RED);
			g.fillRect(0, 0, 10, frameHeight);
			//System.out.println(">Drawing red border at: " + 0 + "," + 0 + " with size " + frameWidth/60 + "x" + frameHeight);
			//bottom border
			g.setColor(Color.green);
			g.fillRect(0, frameHeight - 10, frameWidth, 10);
			//System.out.println(">Drawing green border at: " + 0 + "," + (frameHeight - frameHeight/60) + " with size " + frameWidth + "x" + frameHeight/60);
			//right border
			g.setColor(Color.ORANGE);
			g.fillRect(frameWidth - 10, 0, 10, frameHeight);
			//System.out.println(">Drawing orange border at: " + (frameHeight - frameHeight/60) + "," + 0 + " with size " + frameWidth/60 + "x" + frameHeight);
			//top border
			g.setColor(Color.blue);
			g.fillRect(0, 0, frameWidth, 10);
			//System.out.println(">Drawing blue border at: " + 0 + "," + 0 + " with size " + frameWidth + "x" + frameHeight/60);
			//top left corner
			g.setColor(Color.RED);
			g.fillRect(0, 0, 10, 10);
		}
	}
	
	//added by delle 10/20/17
	public void buildTheme(Graphics g){	
		Color randomColor = new Color((int)(Math.random() * 0x1000000));
		//theme = THEME.defaultTheme;
		
		if(theme == THEME.spaceTheme){
			g.setColor(Color.white);
			for(int i=0; i<50; i++){
				g.fillRect((int) (Math.random()* frameWidth), (int) (Math.random() * frameHeight), 5, 5);
			}
		}
		if(theme == THEME.eyeCancer){
			g.setColor(randomColor);
			for(int i=0; i<50; i++){
				g.fillRect((int) (Math.random()* frameWidth), (int) (Math.random() * frameHeight), 5, 5);
			}
		}
		if(theme == THEME.colorCopy){
			g.setColor(c);
			for(int i=0; i<25000; i++){
				g.fillRect((int) (Math.random()* frameWidth), (int) (Math.random() * frameHeight), frameWidth/120, frameHeight/120);
			}
		}
	}
	
	// added by delle 10/20/17
	public void buildSkin(Graphics g){
		skin = SKIN.PogChamp;
		
		if(playerIsSkinned){
			if(skin == SKIN.PogChamp){
				g.drawImage(iPogchamp, player.x + 3, player.y, null);
			}
		}
	}
	
	public void buildPlayer(Graphics g) {
		if (colorPlayer <= .2) {
			c = Color.white;
		}
		if (colorPlayer <= 1 && colorPlayer > .8) {
			c = Color.red;
		}
		if (colorPlayer <= .4 && colorPlayer > .2) {
			c = Color.blue;
		}
		if (colorPlayer <= .6 && colorPlayer > .4) {
			c = Color.green;
		}
		if (colorPlayer <= .8 && colorPlayer > .6) {
			c = Color.orange;
		}
		
		g.setColor(c);
		if(playerIsSkinned){
			g.fillArc(player.x, player.y, player.width+10, player.height+10, 0, 360);
		}else{
			g.fillRect(player.x, player.y, player.width, player.height);
		}
		buildSkin(g);
		Player = g.getColor();
	}
	
	// the keyPressed method uses keycodes received from user input to determine
	// if a certain action should be done.
	@Override
	public void keyPressed(KeyEvent e) {
		
		//{0} starts box gamemode
		if ((state == STATE.mainMenu) && (e.getKeyCode() == KeyEvent.VK_0)) {
			state = STATE.boxGameState;
			speed = 3;
			addLongOrangeColumn();
			addLongBlueColumn();
			addLongRedColumn();
			addLongGreenColumn();
			
		}
		
		//{B} enters debug mode
		if ((debugMode == false) && (e.getKeyCode() == KeyEvent.VK_B)) { // added by delle 10/20/17
			System.out.println(">Opening debug mode");
			debugMode = true;
			speed = 0;
			frame.setResizable(true);
			renderer.repaint();
		}
		
		//{1} while in debug mode, set width to 600, height to 600
		if ((debugMode == true) && (e.getKeyCode() == KeyEvent.VK_1)) {
			System.out.println(">Setting Frame to 600x600");
			frameWidth = 600;
			frameHeight = 600;
			frame.setExtendedState(JFrame.NORMAL); 
			frame.setSize(frameWidth, frameHeight);
			frame.setVisible(true);
			renderer.repaint();
		}
		
		//{2} while in debug mode, set width to 800, height to 800
		if ((debugMode == true) && (e.getKeyCode() == KeyEvent.VK_2)) {
			System.out.println(">Setting Frame to 800x800");
			frameWidth = 800;
			frameHeight = 800;
			frame.setExtendedState(JFrame.NORMAL); 
			frame.setSize(frameWidth, frameHeight);
			frame.setVisible(true);
			renderer.repaint();
		}
		
		//{3} while in debug mode, set width to 300, height to 300
		if ((debugMode == true) && (e.getKeyCode() == KeyEvent.VK_3)) {
			System.out.println(">Setting Frame to 300x300");
			frameWidth = 300;
			frameHeight = 300;
			frame.setExtendedState(JFrame.NORMAL); 
			frame.setSize(frameWidth, frameHeight);
			frame.setVisible(true);
			renderer.repaint();
		}
		
		//{4} while in debug mode, set to fullscreen
		if ((debugMode == true) && (e.getKeyCode() == KeyEvent.VK_4)) {
			System.out.println(">Setting Frame to Fullscreen");
			frameWidth = screenSize.width;
			frameHeight = screenSize.height;
			frame.dispose();
			frame.setUndecorated(true);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			frame.setVisible(true);
			renderer.repaint();
		}
		
		//{NUMPAD 1} while in debug mode, draws default theme
		if ((debugMode == true) && (e.getKeyCode() == KeyEvent.VK_NUMPAD1)) {
			System.out.println(">Setting theme to default");
			theme = THEME.defaultTheme;
		}
		
		//{NUMPAD 2} while in debug mode, draws default theme
		if ((debugMode == true) && (e.getKeyCode() == KeyEvent.VK_NUMPAD2)) {
			System.out.println(">Setting theme to space");
			theme = THEME.spaceTheme;
		}
		
		//{NUMPAD 3} while in debug mode, draws default theme
		if ((debugMode == true) && (e.getKeyCode() == KeyEvent.VK_NUMPAD3)) {
			System.out.println(">Setting theme to eye cancer");
			theme = THEME.eyeCancer;
		}
		
		//{NUMPAD 4} while in debug mode, draws default theme
		if ((debugMode == true) && (e.getKeyCode() == KeyEvent.VK_NUMPAD4)) {
			System.out.println(">Setting theme to color copy");
			theme = THEME.colorCopy;
		}
		
		//{P} exits debug mode
		if ((debugMode == true) && (e.getKeyCode() == KeyEvent.VK_P)) { // added by delle 10/20/17
			System.out.println(">Closing debug mode");
			debugMode = false;
			speed = 5;
			frame.setResizable(false);
			renderer.repaint();
		}
		
		//player movement controls
		if (state == STATE.fourWaysEasy || state == STATE.fourWaysMedium || state == STATE.fourWaysHard
				|| state == STATE.classicEasy || state == STATE.classicMedium || state == STATE.classicHard
				|| state == STATE.classicInsane || state == STATE.fourWaysInsane || state == STATE.storyGame
				|| state == STATE.boxGameState) {
			//{ARROW KEYS} controls to move player
			if (control) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (player.x >= frameWidth/20) {
						player.x -= frameWidth/20;
					}
					renderer.repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (player.x <= frameWidth - frameWidth/20) {
						player.x += frameWidth/20;
					}
					renderer.repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (player.y <= frameHeight - frameHeight/20) {
						player.y += frameHeight/20;
					}
					renderer.repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					if (player.y >= frameHeight/20) {
						player.y -= frameHeight/20;
					}
					renderer.repaint();
				}
			}
			
			//{WASD} controls to move player
			if (!control) {
				if (e.getKeyCode() == KeyEvent.VK_A) {
					if (player.x >= frameWidth/20) {
						player.x -= frameWidth/20;
					}
					renderer.repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {
					if (player.x <= frameWidth - frameWidth/20) {
						player.x += frameWidth/20;
					}
					renderer.repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					if (player.y <= frameHeight - frameHeight/20) {
						player.y += frameHeight/20;
					}
					renderer.repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_W) {
					if (player.y >= frameHeight/20) {
						player.y -= frameHeight/20;
					}
					renderer.repaint();
				}
			}
		}
		
		//{F} start/restart game button
		if (e.getKeyCode() == KeyEvent.VK_F) {
			//start story gamemode
			if (state == STATE.storyGameStarter || state == STATE.storyGame2) {
				state = STATE.storyGame;
				score = 0;
				speed = 5;
				if (whiteBox.size() == 1) {
					whiteBox.remove(0);
					redBox.remove(0);
					blueBox.remove(0);
					orangeBox.remove(0);
					greenBox.remove(0);
				}
				addWhiteColumn();
				addRedColumn();
				addBlueColumn();
				addgreenColumn();
				addOrangeColumn();
				player.x = frameWidth/2;
				player.y = frameHeight/2;
			}
			
			//start easy fourways gamemode
			if (state == STATE.fourWaysStarterEasy || state == STATE.fourWaysEasy2) {
				state = STATE.fourWaysEasy;
				score = 0;
				speed = 5;
				if (whiteBox.size() == 1) {
					whiteBox.remove(0);
					redBox.remove(0);
					blueBox.remove(0);
					orangeBox.remove(0);
					greenBox.remove(0);
				}
				addWhiteColumn();
				addRedColumn();
				addBlueColumn();
				addgreenColumn();
				addOrangeColumn();
				player.x = frameWidth/2;
				player.y = frameHeight/2;
			}
			//start medium fourways gamemode
			if (state == STATE.fourWaysStarterMedium || state == STATE.fourWaysMedium2) {
				state = STATE.fourWaysMedium;
				score = 0;
				speed = 6;
				if (whiteBox.size() == 1) {
					whiteBox.remove(0);
					redBox.remove(0);
					blueBox.remove(0);
					orangeBox.remove(0);
					greenBox.remove(0);
				}
				addWhiteColumn();
				addRedColumn();
				addBlueColumn();
				addgreenColumn();
				addOrangeColumn();
				player.x = frameWidth/2;
				player.y = frameHeight/2;
			}
			
			//start hard fourways gamemode
			if (state == STATE.fourWaysStarterHard || state == STATE.fourWaysHard2) {
				speed = 7;
				r1 = Math.random();
				r2 = Math.random();
				state = STATE.fourWaysHard;
				score = 0;
				if (whiteBox.size() == 1) {
					whiteBox.remove(0);
					redBox.remove(0);
					blueBox.remove(0);
					orangeBox.remove(0);
					greenBox.remove(0);
				}
				addWhiteColumn();
				addRedColumn();
				addBlueColumn();
				addgreenColumn();
				addOrangeColumn();
				player.x = frameWidth/2;
				player.y = frameHeight/2;
			}
			
			//start insane fourways gamemode
			if (state == STATE.fourWaysStarterInsane || state == STATE.fourWaysInsane2) {
				speed = 8;
				r1 = Math.random();
				r2 = Math.random();
				state = STATE.fourWaysInsane;
				score = 0;
				if (whiteBox.size() == 1) {
					whiteBox.remove(0);
					redBox.remove(0);
					blueBox.remove(0);
					orangeBox.remove(0);
					greenBox.remove(0);
				}
				addWhiteColumn();
				addRedColumn();
				addBlueColumn();
				addgreenColumn();
				addOrangeColumn();
				player.x = 300;
				player.y = 300;
			}
			
			//start easy classic gamemode
			if (state == STATE.classicStarterEasy || state == STATE.classicEasy2) {
				speed = 5;
				r1 = Math.random();
				r2 = Math.random();
				state = STATE.classicEasy;
				score = 0;
				if (whiteBox.size() == 1) {
					whiteBox.remove(0);
					redBox.remove(0);
					blueBox.remove(0);
					orangeBox.remove(0);
					greenBox.remove(0);
				}
				addWhiteColumn();
				addRedColumn();
				addBlueColumn();
				addgreenColumn();
				addOrangeColumn();
				player.x = 300;
				player.y = 300;
			}
			
			//start medium classic gamemode
			if (state == STATE.classicStarterMedium || state == STATE.classicMedium2) {
				speed = 6;
				r1 = Math.random();
				r2 = Math.random();
				state = STATE.classicMedium;
				score = 0;
				if (whiteBox.size() == 1) {
					whiteBox.remove(0);
					redBox.remove(0);
					blueBox.remove(0);
					orangeBox.remove(0);
					greenBox.remove(0);
				}
				addWhiteColumn();
				addRedColumn();
				addBlueColumn();
				addgreenColumn();
				addOrangeColumn();
				player.x = 300;
				player.y = 300;
			}
			
			//start hard classic gamemode
			if (state == STATE.classicStarterHard || state == STATE.classicHard2) {
				speed = 7;
				r1 = Math.random();
				r2 = Math.random();
				state = STATE.classicHard;
				score = 0;
				if (whiteBox.size() == 1) {
					whiteBox.remove(0);
					redBox.remove(0);
					blueBox.remove(0);
					orangeBox.remove(0);
					greenBox.remove(0);
				}
				addWhiteColumn();
				addRedColumn();
				addBlueColumn();
				addgreenColumn();
				addOrangeColumn();
				player.x = 300;
				player.y = 300;
			}
			
			//start insane classic gamemode
			if (state == STATE.classicStarterInsane || state == STATE.classicInsane2) {
				speed = 8;
				r1 = Math.random();
				r2 = Math.random();
				state = STATE.classicInsane;
				score = 0;
				if (whiteBox.size() == 1) {
					whiteBox.remove(0);
					redBox.remove(0);
					blueBox.remove(0);
					orangeBox.remove(0);
					greenBox.remove(0);
				}
				addWhiteColumn();
				addRedColumn();
				addBlueColumn();
				addgreenColumn();
				addOrangeColumn();
				player.x = 300;
				player.y = 300;
			}
		}
	}

	// The mousePressed method gets the location of where on screen the mouse
	// was pressed, and if it is in the appropriate location of a button during
	// the appropriate game state, an action will be performed.
	@Override
	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		System.out.println(mx + " " + my);
		System.out.println(state);
		
		if(state == STATE.mainMenu) {
			if (mx <= frameWidth/3 + frameWidth/3 && mx >= frameWidth/3 && my <= frameHeight - frameHeight/5 + frameHeight/10 && my >= frameHeight - frameHeight/5) {
				state = STATE.gameModes;
				System.out.println(">Changing state to gameModes");
			}
			if (mx <= frameWidth/3 + frameWidth/3 && mx >= frameWidth/3 && my <= frameHeight/2 + frameHeight/7 + frameHeight/10 && my >= frameHeight/2 + frameHeight/7) {
				state = STATE.settings;
				System.out.println(">Changing state to settings");
			}
		}
		
		if(state == STATE.settings) {
			if (mx <= 448 && mx >= 311 && my <= 269 && my >= 184) {
					border = false;
			}
			if (mx <= 273 && mx >= 157 && my <= 269 && my >= 184) {
					border = true;
			}
			if (mx <= 263 && mx >= 55 && my <= 511 && my >= 427) {
					control = false;
			}
			if (mx <= 550 && mx >= 295 && my <= 511 && my >= 427) {
					control = true;
			}
		}
		
		if (mx <= 495 && mx >= 112 && my <= 172 && my >= 76) {
			if (state == STATE.gameModes) {
				state = STATE.storyGameStarter;
			}
		}
		if (mx <= 393 && mx >= 212 && my <= 153 && my >= 56) {
			if (state == STATE.classicChoices) {
				state = STATE.classicStarterEasy;
			}
			if (state == STATE.fourWaysChoices) {
				state = STATE.fourWaysStarterEasy;
			}
		}
		if (mx <= 441 && mx >= 164 && my <= 271 && my >= 192) {
			if (state == STATE.classicChoices) {
				state = STATE.classicStarterMedium;
			}
			if (state == STATE.fourWaysChoices) {
				state = STATE.fourWaysStarterMedium;
			}
		}
		if (mx <= 396 && mx >= 208 && my <= 412 && my >= 325) {
			if (state == STATE.classicChoices) {
				state = STATE.classicStarterHard;
			}
			if (state == STATE.fourWaysChoices) {
				state = STATE.fourWaysStarterHard;
			}
		}
		if (mx <= 419 && mx >= 184 && my <= 546 && my >= 461) {
			if (state == STATE.classicChoices) {
				state = STATE.classicStarterInsane;
			}
			if (state == STATE.fourWaysChoices) {
				state = STATE.fourWaysStarterInsane;
			}
		}
		
		if (mx <= 100 && mx >= 25 && my <= 88 && my >= 47) {
			if (state == STATE.settings || state == STATE.gameModes || state == STATE.classicEasy2
					|| state == STATE.classicMedium2 || state == STATE.classicHard2 || state == STATE.fourWaysEasy2
					|| state == STATE.fourWaysMedium2 || state == STATE.fourWaysHard2 || state == STATE.classicInsane2
					|| state == STATE.storyGame2 || state == STATE.fourWaysInsane2) {
				state = STATE.mainMenu;
			}
		}
		if (mx <= 422 && mx >= 180 && my <= 281 && my >= 197) {
			if (state == STATE.gameModes) {
				state = STATE.classicChoices;
			}
		}
		if (mx <= 100 && mx >= 25 && my <= 88 && my >= 47) {
			if (state == STATE.classicChoices || state == STATE.fourWaysChoices) {
				state = STATE.gameModes;
			}
		}
		if (mx <= 524 && mx >= 80 && my <= 402 && my >= 316) {
			if (state == STATE.gameModes) {
				state = STATE.fourWaysChoices;
			}
		}
	}
	
	//virtual frame
	public void mouseDragged(MouseEvent e) {
		int mxS = e.getXOnScreen();
		int myS = e.getYOnScreen();
		
		if (my <= 10) {
			frame.setLocation(mxS-mx, myS-my);
		}
	}

	// The following five methods draw the 5 colored rectangles in their
	// appropriate locations.
	public void paintWhiteColumn(Graphics g, Rectangle white) {
		g.setColor(Color.white);
		g.fillRect(white.x, white.y, white.width, white.height);
	}

	public void paintOrangeColumn(Graphics g, Rectangle orange) {
		g.setColor(Color.orange);
		g.fillRect(orange.x, orange.y, orange.width, orange.height);
	}

	public void paintRedColumn(Graphics g, Rectangle red) {
		g.setColor(Color.red);
		g.fillRect(red.x, red.y, red.width, red.height);
	}

	public void paintBlueColumn(Graphics g, Rectangle blue) {
		g.setColor(Color.blue);
		g.fillRect(blue.x, blue.y, blue.width, blue.height);
	}

	public void paintGreenColumn(Graphics g, Rectangle green) {
		g.setColor(Color.green);
		g.fillRect(green.x, green.y, green.width, green.height);
	}

	public void paintLongOrangeColumn(Graphics g, Rectangle orange) {
		g.setColor(Color.orange);
		g.fillRect(orange.x, orange.y, orange.width, orange.height);
	}

	public void paintLongBlueColumn(Graphics g, Rectangle blue) {
		g.setColor(Color.blue);
		g.fillRect(blue.x, blue.y, blue.width, blue.height);
	}

	public void paintLongRedColumn(Graphics g, Rectangle red) {
		g.setColor(Color.red);
		g.fillRect(red.x, red.y, red.width, red.height);
	}

	public void paintLongGreenColumn(Graphics g, Rectangle green) {
		g.setColor(Color.green);
		g.fillRect(green.x, green.y, green.width, green.height);
	}

	// Creates a new orange box in a location that is based off of randomly
	// generated numbers.
	public void addOrangeColumn() {
		if (state == STATE.classicEasy || state == STATE.classicMedium || state == STATE.classicHard
				|| state == STATE.classicInsane || state == STATE.storyGame) {
			if (XorY < .5) {
				if (boxLocation <= .2) {
					orangeBox.add(new Rectangle(600, 120, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					orangeBox.add(new Rectangle(600, 240, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					orangeBox.add(new Rectangle(600, 360, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					orangeBox.add(new Rectangle(600, 480, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					orangeBox.add(new Rectangle(600, 0, 20, 120));
				}
			}
			if (XorY >= .5) {
				if (boxLocation <= .2) {
					orangeBox.add(new Rectangle(120, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					orangeBox.add(new Rectangle(240, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					orangeBox.add(new Rectangle(360, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					orangeBox.add(new Rectangle(480, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					orangeBox.add(new Rectangle(0, 600, 120, 20));
				}
			}
		}
		if (state == STATE.fourWaysEasy || state == STATE.fourWaysMedium || state == STATE.fourWaysHard
				|| state == STATE.fourWaysInsane) {
			if (XorY < .25) {
				if (boxLocation <= .2) {
					orangeBox.add(new Rectangle(600, 120, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					orangeBox.add(new Rectangle(600, 240, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					orangeBox.add(new Rectangle(600, 360, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					orangeBox.add(new Rectangle(600, 480, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					orangeBox.add(new Rectangle(600, 0, 20, 120));
				}
			}
			if (XorY >= .25 && XorY < .5) {
				if (boxLocation <= .2) {
					orangeBox.add(new Rectangle(120, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					orangeBox.add(new Rectangle(240, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					orangeBox.add(new Rectangle(360, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					orangeBox.add(new Rectangle(480, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					orangeBox.add(new Rectangle(0, 600, 120, 20));
				}
			}
			if (XorY >= .5 && XorY < .75) {
				if (boxLocation <= .2) {
					orangeBox.add(new Rectangle(120, -30, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					orangeBox.add(new Rectangle(240, -30, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					orangeBox.add(new Rectangle(360, -30, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					orangeBox.add(new Rectangle(480, -30, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					orangeBox.add(new Rectangle(0, -30, 120, 20));
				}
			}
			if (XorY >= .75 && XorY < 1) {
				if (boxLocation <= .2) {
					orangeBox.add(new Rectangle(-30, 120, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					orangeBox.add(new Rectangle(-30, 240, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					orangeBox.add(new Rectangle(-30, 360, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					orangeBox.add(new Rectangle(-30, 480, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					orangeBox.add(new Rectangle(-30, 0, 20, 120));
				}
			}
		}
	}

	// Creates a new green box in a location that is based off of randomly
	// generated numbers.
	public void addgreenColumn() {
		if (state == STATE.classicEasy || state == STATE.classicMedium || state == STATE.classicHard
				|| state == STATE.classicInsane || state == STATE.storyGame) {
			colorPlayer = rand.nextDouble();
			if (XorY < .5) {
				if (boxLocation <= .2) {
					greenBox.add(new Rectangle(600, 240, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					greenBox.add(new Rectangle(600, 360, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					greenBox.add(new Rectangle(600, 480, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					greenBox.add(new Rectangle(600, 0, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					greenBox.add(new Rectangle(600, 120, 20, 120));
				}
			}
			if (XorY >= .5) {
				if (boxLocation <= .2) {
					greenBox.add(new Rectangle(240, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					greenBox.add(new Rectangle(360, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					greenBox.add(new Rectangle(480, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					greenBox.add(new Rectangle(0, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					greenBox.add(new Rectangle(120, 600, 120, 20));
				}
			}
		}
		if (state == STATE.fourWaysEasy || state == STATE.fourWaysMedium || state == STATE.fourWaysHard
				|| state == STATE.fourWaysInsane) {
			colorPlayer = rand.nextDouble();
			if (XorY < .25) {
				if (boxLocation <= .2) {
					greenBox.add(new Rectangle(600, 240, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					greenBox.add(new Rectangle(600, 360, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					greenBox.add(new Rectangle(600, 480, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					greenBox.add(new Rectangle(600, 0, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					greenBox.add(new Rectangle(600, 120, 20, 120));
				}
			}
			if (XorY >= .25 && XorY < .5) {
				if (boxLocation <= .2) {
					greenBox.add(new Rectangle(240, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					greenBox.add(new Rectangle(360, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					greenBox.add(new Rectangle(480, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					greenBox.add(new Rectangle(0, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					greenBox.add(new Rectangle(120, 600, 120, 20));
				}
			}
			if (XorY >= .5 && XorY < .75) {
				if (boxLocation <= .2) {
					greenBox.add(new Rectangle(240, -30, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					greenBox.add(new Rectangle(360, -30, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					greenBox.add(new Rectangle(480, -30, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					greenBox.add(new Rectangle(0, -30, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					greenBox.add(new Rectangle(120, -30, 120, 20));
				}
			}
			if (XorY >= .75 && XorY < 1) {
				if (boxLocation <= .2) {
					greenBox.add(new Rectangle(-30, 240, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					greenBox.add(new Rectangle(-30, 360, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					greenBox.add(new Rectangle(-30, 480, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					greenBox.add(new Rectangle(-30, 0, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					greenBox.add(new Rectangle(-30, 120, 20, 120));
				}
			}
		}
	}

	// Creates a new blue box in a location that is based off of randomly
	// generated numbers.
	public void addBlueColumn() {
		if (state == STATE.classicEasy || state == STATE.classicMedium || state == STATE.classicHard
				|| state == STATE.classicInsane || state == STATE.storyGame) {
			if (XorY < .5) {
				if (boxLocation <= .2) {
					blueBox.add(new Rectangle(600, 360, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					blueBox.add(new Rectangle(600, 480, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					blueBox.add(new Rectangle(600, 0, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					blueBox.add(new Rectangle(600, 120, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					blueBox.add(new Rectangle(600, 240, 20, 120));
				}
			}
			if (XorY >= .5) {
				if (boxLocation <= .2) {
					blueBox.add(new Rectangle(360, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					blueBox.add(new Rectangle(480, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					blueBox.add(new Rectangle(0, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					blueBox.add(new Rectangle(120, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					blueBox.add(new Rectangle(240, 600, 120, 20));
				}
			}
		}
		if (state == STATE.fourWaysEasy || state == STATE.fourWaysMedium || state == STATE.fourWaysHard
				|| state == STATE.fourWaysInsane) {
			if (XorY < .25) {
				if (boxLocation <= .2) {
					blueBox.add(new Rectangle(600, 360, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					blueBox.add(new Rectangle(600, 480, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					blueBox.add(new Rectangle(600, 0, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					blueBox.add(new Rectangle(600, 120, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					blueBox.add(new Rectangle(600, 240, 20, 120));
				}
			}
			if (XorY >= .25 && XorY < .5) {
				if (boxLocation <= .2) {
					blueBox.add(new Rectangle(360, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					blueBox.add(new Rectangle(480, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					blueBox.add(new Rectangle(0, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					blueBox.add(new Rectangle(120, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					blueBox.add(new Rectangle(240, 600, 120, 20));
				}
			}
			if (XorY >= .5 && XorY < .75) {
				if (boxLocation <= .2) {
					blueBox.add(new Rectangle(360, -30, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					blueBox.add(new Rectangle(480, -30, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					blueBox.add(new Rectangle(0, -30, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					blueBox.add(new Rectangle(120, -30, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					blueBox.add(new Rectangle(240, -30, 120, 20));
				}
			}
			if (XorY >= .75 && XorY < 1) {
				if (boxLocation <= .2) {
					blueBox.add(new Rectangle(-30, 360, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					blueBox.add(new Rectangle(-30, 480, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					blueBox.add(new Rectangle(-30, 0, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					blueBox.add(new Rectangle(-30, 120, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					blueBox.add(new Rectangle(-30, 240, 20, 120));
				}
			}
		}
	}

	// Creates a new red box in a location that is based off of randomly
	// generated numbers.
	public void addRedColumn() {
		if (state == STATE.classicEasy || state == STATE.classicMedium || state == STATE.classicHard
				|| state == STATE.classicInsane || state == STATE.storyGame) {
			if (XorY < .5) {
				if (boxLocation <= .2) {
					redBox.add(new Rectangle(600, 480, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					redBox.add(new Rectangle(600, 0, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					redBox.add(new Rectangle(600, 120, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					redBox.add(new Rectangle(600, 240, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					redBox.add(new Rectangle(600, 360, 20, 120));
				}
			}
			if (XorY >= .5) {
				if (boxLocation <= .2) {
					redBox.add(new Rectangle(480, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					redBox.add(new Rectangle(0, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					redBox.add(new Rectangle(120, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					redBox.add(new Rectangle(240, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					redBox.add(new Rectangle(360, 600, 120, 20));
				}
			}
		}
		if (state == STATE.fourWaysEasy || state == STATE.fourWaysMedium || state == STATE.fourWaysHard
				|| state == STATE.fourWaysInsane) {
			if (XorY < .25) {
				if (boxLocation <= .2) {
					redBox.add(new Rectangle(600, 480, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					redBox.add(new Rectangle(600, 0, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					redBox.add(new Rectangle(600, 120, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					redBox.add(new Rectangle(600, 240, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					redBox.add(new Rectangle(600, 360, 20, 120));
				}
			}
			if (XorY >= .25 && XorY < .5) {
				if (boxLocation <= .2) {
					redBox.add(new Rectangle(480, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					redBox.add(new Rectangle(0, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					redBox.add(new Rectangle(120, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					redBox.add(new Rectangle(240, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					redBox.add(new Rectangle(360, 600, 120, 20));
				}
			}
			if (XorY >= .5 && XorY < .75) {
				if (boxLocation <= .2) {
					redBox.add(new Rectangle(480, -30, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					redBox.add(new Rectangle(0, -30, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					redBox.add(new Rectangle(120, -30, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					redBox.add(new Rectangle(240, -30, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					redBox.add(new Rectangle(360, -30, 120, 20));
				}
			}
			if (XorY >= .75 && XorY < 1) {
				if (boxLocation <= .2) {
					redBox.add(new Rectangle(-30, 480, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					redBox.add(new Rectangle(-30, 0, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					redBox.add(new Rectangle(-30, 120, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					redBox.add(new Rectangle(-30, 240, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					redBox.add(new Rectangle(-30, 360, 20, 120));
				}
			}
		}
	}

	// Creates a new white box in a location that is based off of randomly
	// generated numbers.
	public void addWhiteColumn() {
		XorY = rand.nextDouble();
		boxLocation = rand.nextDouble();
		if (state == STATE.classicEasy || state == STATE.classicMedium || state == STATE.classicHard
				|| state == STATE.classicInsane || state == STATE.storyGame) {
			if (XorY < .5) {
				if (boxLocation <= .2) {
					whiteBox.add(new Rectangle(600, 0, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					whiteBox.add(new Rectangle(600, 120, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					whiteBox.add(new Rectangle(600, 240, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					whiteBox.add(new Rectangle(600, 360, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					whiteBox.add(new Rectangle(600, 480, 20, 120));
				}
			}
			if (XorY >= .5) {
				if (boxLocation <= .2) {
					whiteBox.add(new Rectangle(0, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					whiteBox.add(new Rectangle(120, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					whiteBox.add(new Rectangle(240, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					whiteBox.add(new Rectangle(360, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					whiteBox.add(new Rectangle(480, 600, 120, 20));
				}
			}
		}
		if (state == STATE.fourWaysEasy || state == STATE.fourWaysMedium || state == STATE.fourWaysHard
				|| state == STATE.fourWaysInsane) {
			if (XorY < .25) {
				if (boxLocation <= .2) {
					whiteBox.add(new Rectangle(600, 0, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					whiteBox.add(new Rectangle(600, 120, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					whiteBox.add(new Rectangle(600, 240, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					whiteBox.add(new Rectangle(600, 360, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					whiteBox.add(new Rectangle(600, 480, 20, 120));
				}
			}
			if (XorY >= .25 && XorY < .5) {
				if (boxLocation <= .2) {
					whiteBox.add(new Rectangle(0, 600, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					whiteBox.add(new Rectangle(120, 600, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					whiteBox.add(new Rectangle(240, 600, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					whiteBox.add(new Rectangle(360, 600, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					whiteBox.add(new Rectangle(480, 600, 120, 20));
				}
			}
			if (XorY >= .5 && XorY < .75) {
				if (boxLocation <= .2) {
					whiteBox.add(new Rectangle(0, -30, 120, 20));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					whiteBox.add(new Rectangle(120, -30, 120, 20));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					whiteBox.add(new Rectangle(240, -30, 120, 20));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					whiteBox.add(new Rectangle(360, -30, 120, 20));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					whiteBox.add(new Rectangle(480, -30, 120, 20));
				}
			}
			if (XorY >= .75 && XorY < 1) {
				if (boxLocation <= .2) {
					whiteBox.add(new Rectangle(-30, 0, 20, 120));
				}
				if (boxLocation <= .4 && boxLocation > .2) {
					whiteBox.add(new Rectangle(-30, 120, 20, 120));
				}
				if (boxLocation <= .6 && boxLocation > .4) {
					whiteBox.add(new Rectangle(-30, 240, 20, 120));
				}
				if (boxLocation <= .8 && boxLocation > .6) {
					whiteBox.add(new Rectangle(-30, 360, 20, 120));
				}
				if (boxLocation <= 1 && boxLocation > .8) {
					whiteBox.add(new Rectangle(-30, 480, 20, 120));
				}
			}
		}
	}

	public void addLongOrangeColumn() {
		orangeLocation = Math.random();
		if (orangeLocation < .25) {
			orangeLongBox.add(new Rectangle(20, 560, 560, 20));
		} else if (orangeLocation >= .25 && orangeLocation < .5) {
			orangeLongBox.add(new Rectangle(0, 0, 560, 20));
		} else if (orangeLocation >= .5 && orangeLocation < .75) {
			orangeLongBox.add(new Rectangle(0, 20, 20, 560));
		} else {
			orangeLongBox.add(new Rectangle(560, 0, 20, 560));
		}
	}

	public void addLongBlueColumn() {
		if (orangeLocation < .25) {
			blueLongBox.add(new Rectangle(560, 0, 20, 560));
		} else if (orangeLocation >= .25 && orangeLocation < .5) {
			blueLongBox.add(new Rectangle(20, 560, 560, 20));
		} else if (orangeLocation >= .5 && orangeLocation < .75) {
			blueLongBox.add(new Rectangle(0, 0, 560, 20));
		} else {
			blueLongBox.add(new Rectangle(0, 20, 20, 560));
		}
	}

	public void addLongRedColumn() {
		if (orangeLocation < .25) {
			redLongBox.add(new Rectangle(0, 0, 20, 580));
		} else if (orangeLocation >= .25 && orangeLocation < .5) {
			redLongBox.add(new Rectangle(560, 0, 20, 560));
		} else if (orangeLocation >= .5 && orangeLocation < .75) {
			redLongBox.add(new Rectangle(20, 560, 560, 20));
		} else {
			redLongBox.add(new Rectangle(0, 0, 560, 20));
		}
	}

	public void addLongGreenColumn() {
		if (orangeLocation < .25) {
			greenLongBox.add(new Rectangle(0, 0, 560, 20));
		} else if (orangeLocation >= .25 && orangeLocation < .5) {
			greenLongBox.add(new Rectangle(0, 0, 20, 580));
		} else if (orangeLocation >= .5 && orangeLocation < .75) {
			greenLongBox.add(new Rectangle(560, 0, 20, 560));
		} else {
			greenLongBox.add(new Rectangle(20, 560, 560, 20));
		}
	}
	
	// code for the classic and story gamemodes that checks whether the player
	// is in a location that will cause the gamemode to end, moves the location
	// of the colored boxes, compares the current score to the highscore and
	// changes the highscore if need be, removes the row of colored boxes once
	// it reaches the end of the screen, and creates a new row.
	public void classic() {
		if (state == STATE.storyGame) {
			if (score == 0) {
				speed = 5;
			} else if (score == 8) {
				speed = 6;
			} else if (score == 16) {
				speed = 7;
			} else if (score == 24) {
				speed = 8;
			} else if (score == 32) {
				speed = 9;
			} else if (score == 40) {
				speed = 10;
			} else if (score == 48) {
				speed = 11;
			} else if (score == 56) {
				speed = 12;
			} else if (score == 64) {
				speed = 13;
			} else if (score == 72) {
				speed = 14;
			}
		}
		if (state == STATE.classicMedium || state == STATE.classicEasy || state == STATE.classicHard
				|| state == STATE.storyGame || state == STATE.classicInsane) {
			if (XorY < .5) {
				for (int i = 0; i < whiteBox.size(); i++) {
					Rectangle rect = new Rectangle();
					Rectangle rectw = whiteBox.get(i);
					Rectangle rectb = blueBox.get(i);
					Rectangle rectr = redBox.get(i);
					Rectangle recto = orangeBox.get(i);
					Rectangle rectp = greenBox.get(i);
					rectw.x -= speed;
					rectb.x -= speed;
					rectr.x -= speed;
					recto.x -= speed;
					rectp.x -= speed;
					if (Player == Color.white) {
						rect = whiteBox.get(i);
					}
					if (Player == Color.blue) {
						rect = blueBox.get(i);
					}
					if (Player == Color.red) {
						rect = redBox.get(i);
					}
					if (Player == Color.orange) {
						rect = orangeBox.get(i);
					}
					if (Player == Color.green) {
						rect = greenBox.get(i);
					}
					if (player.x - rect.x <= 20 && player.x - rect.x > -20) {
						if (!(player.y - rect.y <= 101) || !(player.y - rect.y >= 0)) {
							if (state == STATE.classicEasy) {
								state = STATE.classicEasy2;
							}
							if (state == STATE.classicMedium) {
								state = STATE.classicMedium2;
							}
							if (state == STATE.classicHard) {
								state = STATE.classicHard2;
							}
							if (state == STATE.classicInsane) {
								state = STATE.classicInsane2;
							}
							if (state == STATE.storyGame) {
								state = STATE.storyGame2;
							}
						}
					}
					if (rectw.x <= 0
							&& (state == STATE.classicMedium || state == STATE.classicEasy || state == STATE.classicHard
									|| state == STATE.classicInsane || state == STATE.storyGame)) {
						if (whiteBox.size() >= 1) {
							whiteBox.remove(i);
							redBox.remove(i);
							orangeBox.remove(i);
							blueBox.remove(i);
							greenBox.remove(i);
						}
						addWhiteColumn();
						addBlueColumn();
						addRedColumn();
						addOrangeColumn();
						addgreenColumn();
						score++;
						if (state == STATE.classicEasy) {
							if (score > classicEasyScore) {
								classicEasyScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.classicMedium) {
							if (score > classicMediumScore) {
								classicMediumScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.classicHard) {
							if (score > classicHardScore) {
								classicHardScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.classicInsane) {
							if (score > classicInsaneScore) {
								classicInsaneScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.storyGame) {
							if (score > storyScore) {
								storyScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
					}
				}
			}
			if (XorY >= .5) {
				for (int x = 0; x < whiteBox.size(); x++) {
					Rectangle rect = new Rectangle();
					Rectangle rectw = whiteBox.get(x);
					Rectangle rectb = blueBox.get(x);
					Rectangle rectr = redBox.get(x);
					Rectangle recto = orangeBox.get(x);
					Rectangle rectp = greenBox.get(x);
					rectw.y -= speed;
					rectb.y -= speed;
					rectr.y -= speed;
					recto.y -= speed;
					rectp.y -= speed;
					if (Player == Color.white) {
						rect = whiteBox.get(x);
					}
					if (Player == Color.blue) {
						rect = blueBox.get(x);
					}
					if (Player == Color.red) {
						rect = redBox.get(x);
					}
					if (Player == Color.orange) {
						rect = orangeBox.get(x);
					}
					if (Player == Color.green) {
						rect = greenBox.get(x);
					}
					if (rect.y - player.y <= 20 && rect.y - player.y >= -20) {
						if (!(player.x - rect.x <= 101) || !(player.x - rect.x >= 0)) {
							if (state == STATE.classicEasy) {
								state = STATE.classicEasy2;
							}
							if (state == STATE.classicMedium) {
								state = STATE.classicMedium2;
							}
							if (state == STATE.classicHard) {
								state = STATE.classicHard2;
							}
							if (state == STATE.classicInsane) {
								state = STATE.classicInsane2;
							}
							if (state == STATE.storyGame) {
								state = STATE.storyGame2;
							}
						}
					}
					if (rectw.y <= 0
							&& (state == STATE.classicMedium || state == STATE.classicEasy || state == STATE.classicHard
									|| state == STATE.classicInsane || state == STATE.storyGame)) {
						if (whiteBox.size() >= 1) {
							whiteBox.remove(x);
							redBox.remove(x);
							orangeBox.remove(x);
							blueBox.remove(x);
							greenBox.remove(x);
						}
						addWhiteColumn();
						addBlueColumn();
						addRedColumn();
						addOrangeColumn();
						addgreenColumn();
						score++;
						if (state == STATE.classicEasy) {
							if (score > classicEasyScore) {
								classicEasyScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.classicMedium) {
							if (score > classicMediumScore) {
								classicMediumScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.classicHard) {
							if (score > classicHardScore) {
								classicHardScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.classicInsane) {
							if (score > classicInsaneScore) {
								classicInsaneScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.storyGame) {
							if (score > storyScore) {
								storyScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
					}
				}
			}
		}
	}

	// Code does the same as in the classic method, but this code is written for
	// the all directions gamemode instead.
	public void fourWays() {
		if (state == STATE.fourWaysMedium || state == STATE.fourWaysEasy || state == STATE.fourWaysHard
				|| state == STATE.fourWaysInsane) {
			if (XorY < .25) {
				for (int i = 0; i < whiteBox.size(); i++) {
					Rectangle rect = new Rectangle();
					Rectangle rectw = whiteBox.get(i);
					Rectangle rectb = blueBox.get(i);
					Rectangle rectr = redBox.get(i);
					Rectangle recto = orangeBox.get(i);
					Rectangle rectp = greenBox.get(i);
					rectw.x -= speed;
					rectb.x -= speed;
					rectr.x -= speed;
					recto.x -= speed;
					rectp.x -= speed;
					if (Player == Color.white) {
						rect = whiteBox.get(i);
					}
					if (Player == Color.blue) {
						rect = blueBox.get(i);
					}
					if (Player == Color.red) {
						rect = redBox.get(i);
					}
					if (Player == Color.orange) {
						rect = orangeBox.get(i);
					}
					if (Player == Color.green) {
						rect = greenBox.get(i);
					}
					if (player.x - rect.x <= 20 && player.x - rect.x > -20) {
						if (!(player.y - rect.y <= 101) || !(player.y - rect.y >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (rectw.x <= 0 && (state == STATE.fourWaysMedium || state == STATE.fourWaysEasy
							|| state == STATE.fourWaysHard || state == STATE.fourWaysInsane)) {
						if (whiteBox.size() >= 1) {
							whiteBox.remove(i);
							redBox.remove(i);
							orangeBox.remove(i);
							blueBox.remove(i);
							greenBox.remove(i);
						}
						addWhiteColumn();
						addBlueColumn();
						addRedColumn();
						addOrangeColumn();
						addgreenColumn();
						score++;
						if (state == STATE.fourWaysEasy) {
							if (score > fourWaysEasyScore) {
								fourWaysEasyScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.fourWaysMedium) {
							if (score > fourWaysMediumScore) {
								fourWaysMediumScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.fourWaysHard) {
							if (score > fourWaysHardScore) {
								fourWaysHardScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.fourWaysInsane) {
							if (score > fourWaysInsaneScore) {
								fourWaysInsaneScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
					}
				}
			}
			if (XorY >= .25 && XorY < .5) {
				for (int x = 0; x < whiteBox.size(); x++) {
					Rectangle rect = new Rectangle();
					Rectangle rectw = whiteBox.get(x);
					Rectangle rectb = blueBox.get(x);
					Rectangle rectr = redBox.get(x);
					Rectangle recto = orangeBox.get(x);
					Rectangle rectp = greenBox.get(x);
					rectw.y -= speed;
					rectb.y -= speed;
					rectr.y -= speed;
					recto.y -= speed;
					rectp.y -= speed;
					if (Player == Color.white) {
						rect = whiteBox.get(x);
					}
					if (Player == Color.blue) {
						rect = blueBox.get(x);
					}
					if (Player == Color.red) {
						rect = redBox.get(x);
					}
					if (Player == Color.orange) {
						rect = orangeBox.get(x);
					}
					if (Player == Color.green) {
						rect = greenBox.get(x);
					}
					if (rect.y - player.y <= 20 && rect.y - player.y >= -20) {
						if (!(player.x - rect.x <= 101) || !(player.x - rect.x >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (rectw.y <= 0 && (state == STATE.fourWaysMedium || state == STATE.fourWaysEasy
							|| state == STATE.fourWaysHard || state == STATE.fourWaysInsane)) {
						if (whiteBox.size() >= 1) {
							whiteBox.remove(x);
							redBox.remove(x);
							orangeBox.remove(x);
							blueBox.remove(x);
							greenBox.remove(x);
						}
						addWhiteColumn();
						addBlueColumn();
						addRedColumn();
						addOrangeColumn();
						addgreenColumn();
						score++;
						if (state == STATE.fourWaysEasy) {
							if (score > fourWaysEasyScore) {
								fourWaysEasyScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.fourWaysMedium) {
							if (score > fourWaysMediumScore) {
								fourWaysMediumScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.fourWaysHard) {
							if (score > fourWaysHardScore) {
								fourWaysHardScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.fourWaysInsane) {
							if (score > fourWaysInsaneScore) {
								fourWaysInsaneScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
					}
				}
			}
			if (XorY >= .5 && XorY < .75) {
				for (int x = 0; x < whiteBox.size(); x++) {
					Rectangle rect = new Rectangle();
					Rectangle rectw = whiteBox.get(x);
					Rectangle rectb = blueBox.get(x);
					Rectangle rectr = redBox.get(x);
					Rectangle recto = orangeBox.get(x);
					Rectangle rectp = greenBox.get(x);
					rectw.y += speed;
					rectb.y += speed;
					rectr.y += speed;
					recto.y += speed;
					rectp.y += speed;
					if (Player == Color.white) {
						rect = whiteBox.get(x);
					}
					if (Player == Color.blue) {
						rect = blueBox.get(x);
					}
					if (Player == Color.red) {
						rect = redBox.get(x);
					}
					if (Player == Color.orange) {
						rect = orangeBox.get(x);
					}
					if (Player == Color.green) {
						rect = greenBox.get(x);
					}
					if (rect.y - player.y <= 20 && rect.y - player.y >= -20) {
						if (!(player.x - rect.x <= 101) || !(player.x - rect.x >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (rectw.y >= 600 && (state == STATE.fourWaysMedium || state == STATE.fourWaysEasy
							|| state == STATE.fourWaysHard || state == STATE.fourWaysInsane)) {
						if (whiteBox.size() >= 1) {
							whiteBox.remove(x);
							redBox.remove(x);
							orangeBox.remove(x);
							blueBox.remove(x);
							greenBox.remove(x);
						}
						addWhiteColumn();
						addBlueColumn();
						addRedColumn();
						addOrangeColumn();
						addgreenColumn();
						score++;
						if (state == STATE.fourWaysEasy) {
							if (score > fourWaysEasyScore) {
								fourWaysEasyScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.fourWaysMedium) {
							if (score > fourWaysMediumScore) {
								fourWaysMediumScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.fourWaysHard) {
							if (score > fourWaysHardScore) {
								fourWaysHardScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.fourWaysInsane) {
							if (score > fourWaysInsaneScore) {
								fourWaysInsaneScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
					}
				}
			}
			if (XorY >= .75) {
				for (int i = 0; i < whiteBox.size(); i++) {
					Rectangle rect = new Rectangle();
					Rectangle rectw = whiteBox.get(i);
					Rectangle rectb = blueBox.get(i);
					Rectangle rectr = redBox.get(i);
					Rectangle recto = orangeBox.get(i);
					Rectangle rectp = greenBox.get(i);
					rectw.x += speed;
					rectb.x += speed;
					rectr.x += speed;
					recto.x += speed;
					rectp.x += speed;
					if (Player == Color.white) {
						rect = whiteBox.get(i);
					}
					if (Player == Color.blue) {
						rect = blueBox.get(i);
					}
					if (Player == Color.red) {
						rect = redBox.get(i);
					}
					if (Player == Color.orange) {
						rect = orangeBox.get(i);
					}
					if (Player == Color.green) {
						rect = greenBox.get(i);
					}
					if (player.x - rect.x <= 20 && player.x - rect.x > -20) {
						if (!(player.y - rect.y <= 101) || !(player.y - rect.y >= 0)) {
							if (state == STATE.fourWaysEasy) {
								state = STATE.fourWaysEasy2;
							}
							if (state == STATE.fourWaysMedium) {
								state = STATE.fourWaysMedium2;
							}
							if (state == STATE.fourWaysHard) {
								state = STATE.fourWaysHard2;
							}
							if (state == STATE.fourWaysInsane) {
								state = STATE.fourWaysInsane2;
							}
						}
					}
					if (rectw.x >= 600 && (state == STATE.fourWaysMedium || state == STATE.fourWaysEasy
							|| state == STATE.fourWaysHard || state == STATE.fourWaysInsane)) {
						if (whiteBox.size() >= 1) {
							whiteBox.remove(i);
							redBox.remove(i);
							orangeBox.remove(i);
							blueBox.remove(i);
							greenBox.remove(i);
						}
						addWhiteColumn();
						addBlueColumn();
						addRedColumn();
						addOrangeColumn();
						addgreenColumn();
						score++;
						if (state == STATE.fourWaysEasy) {
							if (score > fourWaysEasyScore) {
								fourWaysEasyScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.fourWaysMedium) {
							if (score > fourWaysMediumScore) {
								fourWaysMediumScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.fourWaysHard) {
							if (score > fourWaysHardScore) {
								fourWaysHardScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
						if (state == STATE.fourWaysInsane) {
							if (score > fourWaysInsaneScore) {
								fourWaysInsaneScore = score;
								CreateFile c = new CreateFile();
								c.openFile();
								c.addRecords();
								c.closeFile();
							}
						}
					}
				}

			}
		}
		CreateFile c = new CreateFile();
		c.openFile();
		c.addRecords();
		c.closeFile();
	}

	//the box gamemode where there is a box that goes towards center
	public void boxGame() {
		if (orangeLocation < .25) {
			for (int i = 0; i < orangeLongBox.size(); i++) {
				Rectangle rect = orangeLongBox.get(i);
				Rectangle rectb = blueLongBox.get(i);
				Rectangle rectr = redLongBox.get(i);
				Rectangle rectg = greenLongBox.get(i);
				Rectangle rect1 = new Rectangle();
				Rectangle rect2 = new Rectangle();
				Rectangle rect3 = new Rectangle();
				rect.y -= speed;
				rectb.x -= speed;
				rectr.x += speed;
				rectg.y += speed;

				if (Player == Color.red) {
					rect1 = orangeLongBox.get(i);
					rect2 = blueLongBox.get(i);
					rect3 = greenLongBox.get(i);
				}
				if (Player == Color.orange) {
					rect1 = redLongBox.get(i);
					rect2 = blueLongBox.get(i);
					rect3 = greenLongBox.get(i);
				}
				if (Player == Color.blue) {
					rect1 = orangeLongBox.get(i);
					rect2 = redLongBox.get(i);
					rect3 = greenLongBox.get(i);
				}
				if (Player == Color.green) {
					rect1 = orangeLongBox.get(i);
					rect2 = blueLongBox.get(i);
					rect3 = redLongBox.get(i);
				}
				if (player.y - rect1.y <= 20 && player.y - rect1.y > -20) {
					if ((player.x <= rect1.x + rect1.width) || (player.x >= rect1.x)) {
						state = STATE.mainMenu;
					}
				}
				if (player.y - rect2.y <= 20 && player.y - rect2.y > -20) {
					if ((player.x <= rect2.x + rect2.width) || (player.x >= rect2.x)) {
						state = STATE.mainMenu;
					}
				}
				if (player.y - rect3.y <= 20 && player.y - rect3.y > -20) {
					if ((player.x <= rect3.x + rect3.width) || (player.x >= rect3.x)) {
						state = STATE.mainMenu;
					}
				}

				orangeLongBox.get(i).width -= 6;
				blueLongBox.get(i).height -= 6;
				redLongBox.get(i).height -= 6;
				greenLongBox.get(i).width -= 6;
				orangeLongBox.get(i).x += 3;
				blueLongBox.get(i).y += 3;
				redLongBox.get(i).y += 3;
				greenLongBox.get(i).x += 3;

				if (orangeLongBox.get(i).y <= 280) {
					orangeLongBox.remove(i);
					blueLongBox.remove(i);
					redLongBox.remove(i);
					greenLongBox.remove(i);
					addLongOrangeColumn();
					addLongBlueColumn();
					addLongRedColumn();
					addLongGreenColumn();
				}
			}
		} else if (orangeLocation >= .25 && orangeLocation < .5) {
			for (int i = 0; i < orangeLongBox.size(); i++) {
				Rectangle rect = orangeLongBox.get(i);
				Rectangle rectb = blueLongBox.get(i);
				Rectangle rectr = redLongBox.get(i);
				Rectangle rectg = greenLongBox.get(i);
				Rectangle rect1 = new Rectangle();
				Rectangle rect2 = new Rectangle();
				Rectangle rect3 = new Rectangle();
				rect.y += speed;
				rectb.y -= speed;
				rectr.x -= speed;
				rectg.x += speed;

				if (Player == Color.red) {
					rect1 = orangeLongBox.get(i);
					rect2 = blueLongBox.get(i);
					rect3 = greenLongBox.get(i);
				}
				if (Player == Color.orange) {
					rect1 = redLongBox.get(i);
					rect2 = blueLongBox.get(i);
					rect3 = greenLongBox.get(i);
				}
				if (Player == Color.blue) {
					rect1 = orangeLongBox.get(i);
					rect2 = redLongBox.get(i);
					rect3 = greenLongBox.get(i);
				}
				if (Player == Color.green) {
					rect1 = orangeLongBox.get(i);
					rect2 = blueLongBox.get(i);
					rect3 = redLongBox.get(i);
				}
				if (player.y - rect1.y <= 20 && player.y - rect1.y > -20) {
					if ((player.x <= rect1.x + rect1.width) || (player.x >= rect1.x)) {
						state = STATE.mainMenu;
					}
				}
				if (player.y - rect2.y <= 20 && player.y - rect2.y > -20) {
					if ((player.x <= rect2.x + rect2.width) || (player.x >= rect2.x)) {
						state = STATE.mainMenu;
					}
				}
				if (player.y - rect3.y <= 20 && player.y - rect3.y > -20) {
					if ((player.x <= rect3.x + rect3.width) || (player.x >= rect3.x)) {
						state = STATE.mainMenu;
					}
				}

				orangeLongBox.get(i).width -= 6;
				blueLongBox.get(i).width -= 6;
				redLongBox.get(i).height -= 6;
				greenLongBox.get(i).height -= 6;
				orangeLongBox.get(i).x += 3;
				blueLongBox.get(i).x += 3;
				redLongBox.get(i).y += 3;
				greenLongBox.get(i).y += 3;

				if (blueLongBox.get(i).y <= 280) {
					orangeLongBox.remove(i);
					blueLongBox.remove(i);
					redLongBox.remove(i);
					greenLongBox.remove(i);
					addLongOrangeColumn();
					addLongBlueColumn();
					addLongRedColumn();
					addLongGreenColumn();
				}
			}
		} else if (orangeLocation >= .5 && orangeLocation < .75) {
			for (int i = 0; i < orangeLongBox.size(); i++) {
				Rectangle rect = orangeLongBox.get(i);
				Rectangle rectb = blueLongBox.get(i);
				Rectangle rectr = redLongBox.get(i);
				Rectangle rectg = greenLongBox.get(i);
				Rectangle rect1 = new Rectangle();
				Rectangle rect2 = new Rectangle();
				Rectangle rect3 = new Rectangle();
				rect.x += speed;
				rectb.y += speed;
				rectr.y -= speed;
				rectg.x -= speed;

				if (Player == Color.red) {
					rect1 = orangeLongBox.get(i);
					rect2 = blueLongBox.get(i);
					rect3 = greenLongBox.get(i);
				}
				if (Player == Color.orange) {
					rect1 = redLongBox.get(i);
					rect2 = blueLongBox.get(i);
					rect3 = greenLongBox.get(i);
				}
				if (Player == Color.blue) {
					rect1 = orangeLongBox.get(i);
					rect2 = redLongBox.get(i);
					rect3 = greenLongBox.get(i);
				}
				if (Player == Color.green) {
					rect1 = orangeLongBox.get(i);
					rect2 = blueLongBox.get(i);
					rect3 = redLongBox.get(i);
				}
				if (player.y - rect1.y <= 20 && player.y - rect1.y > -20) {
					if ((player.x <= rect1.x + rect1.width) || (player.x >= rect1.x)) {
						state = STATE.mainMenu;
					}
				}
				if (player.y - rect2.y <= 20 && player.y - rect2.y > -20) {
					if ((player.x <= rect2.x + rect2.width) || (player.x >= rect2.x)) {
						state = STATE.mainMenu;
					}
				}
				if (player.y - rect3.y <= 20 && player.y - rect3.y > -20) {
					if ((player.x <= rect3.x + rect3.width) || (player.x >= rect3.x)) {
						state = STATE.mainMenu;
					}
				}

				orangeLongBox.get(i).height -= 6;
				blueLongBox.get(i).width -= 6;
				redLongBox.get(i).width -= 6;
				greenLongBox.get(i).height -= 6;
				orangeLongBox.get(i).y += 3;
				blueLongBox.get(i).x += 3;
				redLongBox.get(i).x += 3;
				greenLongBox.get(i).y += 3;

				if (redLongBox.get(i).y <= 280) {
					orangeLongBox.remove(i);
					blueLongBox.remove(i);
					redLongBox.remove(i);
					greenLongBox.remove(i);
					addLongOrangeColumn();
					addLongBlueColumn();
					addLongRedColumn();
					addLongGreenColumn();
				}
			}
		} else {
			for (int i = 0; i < orangeLongBox.size(); i++) {
				Rectangle rect = orangeLongBox.get(i);
				Rectangle rectb = blueLongBox.get(i);
				Rectangle rectr = redLongBox.get(i);
				Rectangle rectg = greenLongBox.get(i);
				Rectangle rect1 = new Rectangle();
				Rectangle rect2 = new Rectangle();
				Rectangle rect3 = new Rectangle();
				rect.x -= speed;
				rectb.x += speed;
				rectr.y += speed;
				rectg.y -= speed;

				if (Player == Color.red) {
					rect1 = orangeLongBox.get(i);
					rect2 = blueLongBox.get(i);
					rect3 = greenLongBox.get(i);
				}
				if (Player == Color.orange) {
					rect1 = redLongBox.get(i);
					rect2 = blueLongBox.get(i);
					rect3 = greenLongBox.get(i);
				}
				if (Player == Color.blue) {
					rect1 = orangeLongBox.get(i);
					rect2 = redLongBox.get(i);
					rect3 = greenLongBox.get(i);
				}
				if (Player == Color.green) {
					rect1 = orangeLongBox.get(i);
					rect2 = blueLongBox.get(i);
					rect3 = redLongBox.get(i);
				}
				if (player.y - rect1.y <= 20 && player.y - rect1.y > -20) {
					if ((player.x <= rect1.x + rect1.width) || (player.x >= rect1.x)) {
						state = STATE.mainMenu;
					}
				}
				if (player.y - rect2.y <= 20 && player.y - rect2.y > -20) {
					if ((player.x <= rect2.x + rect2.width) || (player.x >= rect2.x)) {
						state = STATE.mainMenu;
					}
				}
				if (player.y - rect3.y <= 20 && player.y - rect3.y > -20) {
					if ((player.x <= rect3.x + rect3.width) || (player.x >= rect3.x)) {
						state = STATE.mainMenu;
					}
				}

				orangeLongBox.get(i).height -= 6;
				blueLongBox.get(i).height -= 6;
				redLongBox.get(i).width -= 6;
				greenLongBox.get(i).width -= 6;
				orangeLongBox.get(i).y += 3;
				blueLongBox.get(i).y += 3;
				redLongBox.get(i).x += 3;
				greenLongBox.get(i).x += 3;

				if (greenLongBox.get(i).y <= 280) {
					orangeLongBox.remove(i);
					blueLongBox.remove(i);
					redLongBox.remove(i);
					greenLongBox.remove(i);
					addLongOrangeColumn();
					addLongBlueColumn();
					addLongRedColumn();
					addLongGreenColumn();
				}
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}