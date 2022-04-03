package BattleShips;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Board extends JPanel {
	
	public static JPanel currentBox;
	public static JPanel[][] playerPanels = new JPanel[10][10];
	public static JPanel[][] cpuPanels = new JPanel[10][10];
	public static ClickListener[][] clickers = new ClickListener[10][10];
	public static JLabel playerText;
	public static JLabel cpuText;
	public static JButton confirmListened;
	
	public Board() throws IOException {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(600, 550));
		//frame.getContentPane().setLayout(new BorderLayout());
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		
		//fill1 player
		JPanel fill1 = new JPanel();
		JLabel label = new JLabel("<html><h1><br></br><div style='text-align: center;'>Player</div>Battleships: 5</h1></html>");
		playerText = label;
		label.setFont(new Font("Arial", Font.BOLD, 30));
		fill1.add(label);
		fill1.setBackground(Color.CYAN);
		fill1.setMinimumSize(new Dimension(300, 200));
		fill1.setMaximumSize(new Dimension(300, 200));
		fill1.setPreferredSize(new Dimension(300, 200));

		JPanel fill2 = new JPanel();
		JLabel label2 = new JLabel("<html><h1><br></br><div style='text-align: center;'>CPU</div>Battleships: 5</h1></html>", SwingConstants.CENTER);
		cpuText = label2;
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setFont(new Font("Arial", Font.BOLD, 30));
		fill2.add(label2);
		fill2.setBackground(Color.RED);
		fill2.setMinimumSize(new Dimension(300, 200));
		fill2.setMaximumSize(new Dimension(300, 200));
		fill2.setPreferredSize(new Dimension(300, 200));
		
		
		frame.add(fill1,c);
		c.gridx = 1;
		c.gridy = 0;
		frame.add(fill2,c);

		frame.getContentPane().setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel playerPanes = new JPanel(new GridLayout(10, 10, 1, 1));
		playerPanes.setBackground(Color.CYAN);
		
		for (int i=0;i<100;i++){
				JPanel labels= new JPanel(new GridLayout(1,1));
				labels.setPreferredSize(new Dimension(32, 32));
				labels.setMaximumSize(new Dimension(32, 32));
				labels.setMinimumSize(new Dimension(32, 32));
			    labels.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			    labels.setBackground(Color.white);
			    playerPanels[i / 10][i % 10] = labels;
				playerPanes.add(labels, i);
		}
		
		c.gridx = 0;
		c.gridy = 1;
		frame.add(playerPanes, c);
		
		JPanel cpuPanes = new JPanel(new GridLayout(10,10, 1, 1));
		cpuPanes.setBackground(Color.RED);
		
		for (int i=0;i<100;i++){
				JPanel labels= new JPanel(new GridLayout(1,1));
				labels.setPreferredSize(new Dimension(32, 32));
				labels.setMaximumSize(new Dimension(32, 32));
				labels.setMinimumSize(new Dimension(32, 32));
			    labels.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			    labels.setBackground(Color.white);
			    ClickListener newBow = new ClickListener();
			    labels.addMouseListener(newBow);
			    cpuPanels[i / 10][i % 10] = labels;
			    clickers[i / 10][i % 10] = newBow;
				cpuPanes.add(labels, i);
		}
		
		c.gridx = 1;
		c.gridy = 1;
		frame.add(cpuPanes, c);
		
		JButton confirm = new JButton("confirm");
		//OKButton.setMaximumSize(new Dimension(10, 10));
		//OKButton.setPreferredSize(new Dimension(10, 10));
		c.gridx = 1;
		c.gridy = 2;
		confirm.setFocusable(false);
		confirm.setPreferredSize(new Dimension(100, 50));
		confirm.setMaximumSize(new Dimension(100, 50));
		confirm.setMinimumSize(new Dimension(100, 50));
		BattleShips.listen = new BattleShips.ConfirmListener();
		confirm.addMouseListener(BattleShips.listen);
		confirmListened = confirm;
		c.fill = GridBagConstraints.NONE;
		frame.add(confirm, c);
		frame.setVisible(true);
	}
	
    //Class that defines what happens (i.e: the color changes) when a panel is clicked
    public static class ClickListener extends MouseAdapter
    {
    	
    	public void mouseClicked(MouseEvent me)
    	{
            JPanel clickedBox =(JPanel)me.getSource(); // get the reference to the box that was clicked 
            if(currentBox != null && clickedBox.equals(currentBox)) {
            	clickedBox.setBackground(Color.WHITE);
            	currentBox = null;
            }
            else {
            	clickedBox.setBackground(Color.green);
	    		if(currentBox != null) {
	    			if(currentBox.getBackground() != Color.GRAY || currentBox.getBackground() != Color.RED)
	    				currentBox.setBackground(Color.WHITE);
	    		}
	    		currentBox = clickedBox;
	        }
        }
    }
    
}
