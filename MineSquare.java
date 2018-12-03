import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class MineSquare extends JButton{
	boolean mine=false;
	int row,col;
	boolean exposed = false;
	int count;
	MineSweeper parent=null;
	
	public MineSquare(boolean mine, int row, int col, MineSweeper p) {
		this.mine = mine;
		this.row = row;
		this.col = col;
		parent = p;
		setText("");
		addActionListener(new SquareListener());
		//'addMouseListener(new SquareListener());
	}
	
	public void setMine() {
		mine = true;
		//setText("*");
		//setBackground(Color.RED);
	}
	
	public void expose() {
		if (mine) {
			setText("*");
			setBackground(Color.RED);
		}
		else {
			setBackground(Color.WHITE);
			// set text to number of bordering squares that are mines
			for (int i=-1; i<=+1; i++)
				for (int j=-1; j<=+1; j++)
					if (parent.board[row+i][col+j].mine) count++;
			
			// if there are no bordering mines, expose all adjacent squares;
		}
	}
	
	private class SquareListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if (exposed) return;
			exposed = true;
			if (mine) {
				setText("*");
				setBackground(Color.RED);
				JOptionPane.showMessageDialog(null, "Boom! Game Over");
				System.exit(0);
			}
			else {
				int count = 0;
				setBackground(Color.WHITE);
				for (int i=-1; i<=+1; i++)
					for (int j=-1; j<=+1; j++)
						if (parent.board[row+i][col-j].mine) count++;
				
				if (count>0) setText(""+count);
				if (count==0) {
					for (int i=-1; i<=+1; i++)
						for (int j=-1; j<=+1; j++)
							if (!parent.board[row+i][col+j].exposed)
								parent.board[row+i][col+j].doClick();
				}
			}	
		}
	}
/*
	private class SquareListener implements MouseListener {
		public void mouseClicked(MouseEvent arg0) {
			if (mine) {
				setText("*");
				setBackground(Color.RED);
				JOptionPane.showMessageDialog(null, "BOOOOOM!!!");
			}
			else {
				int count = 0;
				setBackground(Color.WHITE);
				if (parent.board[row][col-1].mine) count++;
				
				setText(""+count);
			}
				
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
*/	
}
