package paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Paint extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//field
	JMenuBar mainMenuBar = new JMenuBar();
	JMenu fileMenu = new JMenu ("File");
	JMenu bgMenu = new JMenu ("BackGround Color");
	JMenuItem newMenuItem = new JMenuItem("New");
	JMenuItem exitMenuItem = new JMenuItem("Exit");
	
	JMenuItem black = new JMenuItem("black");
	JMenuItem white = new JMenuItem("white");
	JMenuItem red = new JMenuItem("red");
	
	
	JPanel drawPanel = new JPanel();
	JLabel leftColorLabel = new JLabel();
	JLabel rightColorLabel = new JLabel();
	
	JPanel colorPanel = new JPanel();
	JPanel penPanel = new JPanel();
	
	JLabel[] colorLabel = new JLabel[8];
	JLabel[] penLabel = new JLabel[3];
	
	
	Graphics2D g2D;
	double xPrevious, yPrevious;
	Color drawColor, leftColor, rightColor;
	JButton small = new JButton("Small");
	JButton medium = new JButton("Medium");
	JButton large = new JButton("Large");
	
	//constructor
	public Paint(){
		setTitle("Paint");
		setResizable(false);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				exitForm(e);
			}
		});
		
		getContentPane().setLayout(new GridBagLayout());
		
		setJMenuBar(mainMenuBar);
		fileMenu.setMnemonic('F');
		mainMenuBar.add(fileMenu);
		fileMenu.add(newMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		newMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				newMenuItemActionPerformed(e);	
			}			
		});
		exitMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				exitMenuitemActionPerformed(e);
			}
		});
		
		setJMenuBar(mainMenuBar);
		bgMenu.setMnemonic('B');
		mainMenuBar.add(bgMenu);
		bgMenu.add(white);
		bgMenu.addSeparator();
		bgMenu.add(black);
		bgMenu.addSeparator();
		bgMenu.add(red);
			white.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				whiteActionPerformed(e);	
			}
		});
			black.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					blackActionPerformed(e);	
				}
			});
			red.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					redActionPerformed(e);	
				}

				
			});

		
		drawPanel.setPreferredSize(new Dimension(500,400));
		drawPanel.setBackground(Color.BLACK);
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx =0;
		gridConstraints.gridy=0;
		gridConstraints.gridheight=2;
		gridConstraints.insets = new Insets(10,10,10,10);
		getContentPane().add(drawPanel, gridConstraints);
		drawPanel.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				drawPanelMousePressed(e);
			}
		});
		drawPanel.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				drawPanelMouseDragged(e);
			}
		});
		drawPanel.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){
				drawPanelMouseReleased(e);
			}
		});
		leftColorLabel.setPreferredSize(new Dimension(40,40));
		leftColorLabel.setBackground(Color.BLUE);
		leftColorLabel.setOpaque(true);
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 0;
		
		gridConstraints.anchor = GridBagConstraints.NORTH;
		gridConstraints.insets = new Insets(10,5,10,5);
		getContentPane().add(leftColorLabel, gridConstraints);
		
		rightColorLabel.setPreferredSize(new Dimension(40,40));
		rightColorLabel.setBackground(Color.RED);
		rightColorLabel.setOpaque(true);
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 2;
		gridConstraints.gridy = 0;
		gridConstraints.anchor = GridBagConstraints.NORTH;
		gridConstraints.insets = new Insets(10,5,10,5);
		getContentPane().add(rightColorLabel, gridConstraints);
		
		colorPanel.setPreferredSize(new Dimension(80,160));
		colorPanel.setBackground(Color.CYAN);
		colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=1;
		gridConstraints.gridwidth = 2;
		gridConstraints.anchor = GridBagConstraints.NORTH;
		gridConstraints.insets = new Insets(10,10,10,10);
		getContentPane().add(colorPanel, gridConstraints);
		
		colorPanel.setLayout(new GridBagLayout());
		int j = 0;
		for (int i =0; i<colorLabel.length; i++){
			colorLabel[i]= new JLabel();
			colorLabel[i].setPreferredSize(new Dimension(30,30));
			colorLabel[i].setOpaque(true);
			gridConstraints = new GridBagConstraints();
			gridConstraints.gridx = j;
			gridConstraints.gridy = i - j*4;
			colorPanel.add(colorLabel[i], gridConstraints);
			if (i==3){
				j++;
			}
			colorLabel[i].addMouseListener(new MouseAdapter(){
				public void mousePressed (MouseEvent e){
					colorMousePressed(e);
				}	
			});
		}
				
		colorLabel[0].setBackground(Color.YELLOW);
		colorLabel[1].setBackground(Color.MAGENTA);
		colorLabel[2].setBackground(Color.RED);
		colorLabel[3].setBackground(Color.GRAY);
		colorLabel[4].setBackground(Color.GREEN);
		colorLabel[5].setBackground(Color.BLUE);
		colorLabel[6].setBackground(Color.ORANGE);
		colorLabel[7].setBackground(Color.WHITE);
		leftColor= colorLabel[0].getBackground();
		leftColorLabel.setBackground(leftColor);
		rightColor = colorLabel[7].getBackground();
		rightColorLabel.setBackground(rightColor);
		
		penPanel.setPreferredSize(new Dimension(100,120));
		penPanel.setBackground(Color.CYAN);
		penPanel.setBorder(BorderFactory.createTitledBorder("Size"));
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx=3;
		gridConstraints.gridy=1;
		gridConstraints.gridwidth = 2;
		gridConstraints.anchor = GridBagConstraints.NORTH;
		gridConstraints.insets = new Insets(10,10,10,10);
		getContentPane().add(penPanel, gridConstraints);
		
		small.setLocation(0, 0);
		small.setSize(120, 30);
		penPanel.add(small);
		small.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				smallActionPerformed(e);
			}

		});
		
		medium.setLocation(0, 0);
		medium.setSize(120, 30);
		penPanel.add(medium);
		medium.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mediumActionPerformed(e);
			}

			
		});
		
		large.setLocation(0, 0);
		large.setSize(120, 30);
		penPanel.add(large);
		large.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				largeActionPerformed(e);
			}

		

		});
		
		pack();
		//setSize(800,600);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(0.5 * (screenSize.width -getWidth())),(int)(0.5 * (screenSize.height - getHeight())),getWidth() , getHeight());
		g2D= (Graphics2D) drawPanel.getGraphics();
		
	}//end of constructor
	
	//method
	private void colorMousePressed(MouseEvent e) {
		Component clickedColor = e.getComponent();
		Toolkit.getDefaultToolkit().beep();
		if(e.getButton()==MouseEvent.BUTTON1){
			leftColor=clickedColor.getBackground();
			leftColorLabel.setBackground(leftColor);
		}else if (e.getButton()== MouseEvent.BUTTON3){
			rightColor = clickedColor.getBackground();
			rightColorLabel.setBackground(rightColor);
		}
		
	}
	private void drawPanelMouseReleased(MouseEvent e) {
		if (e.getButton()== MouseEvent.BUTTON1 || e.getButton()==MouseEvent.BUTTON3);{
			Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, e.getX(), e.getY());
			g2D.setPaint(drawColor);;
			g2D.draw(myLine);
		}
	}
	private void drawPanelMouseDragged(MouseEvent e) {	
		Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, e.getX(), e.getY());
		g2D.setPaint(drawColor);
		g2D.setPaint(drawColor);
		g2D.draw(myLine);
		xPrevious = e.getX();
		yPrevious=e.getY();
	}
	private void drawPanelMousePressed(MouseEvent e){
		if(e.getButton()== MouseEvent.BUTTON1 ||  e.getButton() == MouseEvent.BUTTON3)
			xPrevious = e.getX();
			yPrevious = e.getY();
			if (e.getButton()==MouseEvent.BUTTON1){
				drawColor = leftColor;
			}
			else{
				drawColor = rightColor;
			}
			
		
	}
	private void exitMenuitemActionPerformed(ActionEvent e) {
		int response;
		response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?");
		if(response == JOptionPane.NO_OPTION){
			return;
		}else{
			exitForm(null);
		}
				
		
	}
	
	private void newMenuItemActionPerformed(ActionEvent e) {
		
		int response;
		response = JOptionPane.showConfirmDialog(null, "Are you sure you want to start new?");
		if (response==JOptionPane.YES_OPTION){
			g2D.setPaint(drawPanel.getBackground());
			g2D.fill(new Rectangle2D.Double(0,0,drawPanel.getWidth(),drawPanel.getHeight()));
		}
				
	}
	
	private void exitForm(WindowEvent e) {
		System.exit(0);
		
	}
	private void blackActionPerformed(ActionEvent e) {
		drawPanel.setBackground(Color.BLACK);		
	}	
	private void whiteActionPerformed(ActionEvent e) {
		drawPanel.setBackground(Color.WHITE);
		
	}
	private void redActionPerformed(ActionEvent e) {
		drawPanel.setBackground(Color.RED);
		
	}


	private void smallActionPerformed(ActionEvent e) {
		 
         g2D.setStroke(new BasicStroke(1));
        // g2D.draw(new Line2D.Float(30, 20, 80, 90));
	}
	private void mediumActionPerformed(ActionEvent e) {
		  g2D.setStroke(new BasicStroke(10));
	        // g2D.draw(new Line2D.Float(30, 20, 80, 90));
	}
	private void largeActionPerformed(ActionEvent e) {
		g2D.setStroke(new BasicStroke(20));
        //g2D.draw(new Line2D.Float(30, 20, 80, 90));
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	//@Oveerrid
	//public void actionPerformed(ActionEvent arg0) {
		//// TODO Auto-generated method stub
		
	
	
}
