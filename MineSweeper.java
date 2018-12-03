import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MineSweeper extends JFrame{
	JPanel boardPanel = new JPanel();
	JPanel controlPanel = new JPanel();
	static JTextField counterDisplay = new JTextField(3);
	JButton NewGame = new JButton("New Game");
	MineSquare[][] board;
	static int minecount=0;

	public MineSweeper(int size) {
		board = new MineSquare[size+2][size+2];
		setSize(size*25,size*25+20);
		setTitle("Mine Sweeper");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		boardPanel.setLayout(new GridLayout(size,size));
		controlPanel.add(NewGame);
		counterDisplay.setText(""+minecount);
		controlPanel.add(counterDisplay);
		for (int r=0; r<size+2; r++)
			for (int c=0; c<size+2; c++) {
				board[r][c] = new MineSquare(false,r,c,this);
			}
		for (int i=0; i<size+2; i++) {
			board[0][i].exposed = true;
			board[size+1][i].exposed = true;
			board[i][0].exposed = true;
			board[i][size+1].exposed = true;
		}
		for (int r=1; r<size+1; r++)
			for (int c=1; c<size+1; c++) {
				boardPanel.add(board[r][c]);
				if (Math.random()< 0.15)
					board[r][c].setMine();
					minecount++;
					
			}

		add(boardPanel,BorderLayout.CENTER);
		add(controlPanel,BorderLayout.NORTH);
		setVisible(true);
	}


	public static void main(String[] args) {

		int size;
		size = Integer.parseInt(JOptionPane.showInputDialog("What size board do you want to play on?"));
		if (size<5) size = 5;
		if (size>50) size = 50;
		new MineSweeper(size);
	}
	private class NewGameListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			minecount = 0;
			
		}
	}
}
