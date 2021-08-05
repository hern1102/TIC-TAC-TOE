/*
Description: This program below is a TIC-TAC-TOE game. It basically creates a JPanel
with a grid layout of 3x3 with mouselistener. Everytime you click on a grid, it fills
the grid you clicked on with an X or an O. Everytime a grid is clicked, it will 
also check to see if you have three in a row. If you do, the game will be over, 
but fear no more, there is a reset button in the southern portion of the borderlayout 
for you to click on if you would like to restart the game. ENJOY!
 */

package tictactoe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener, MouseListener{
    
    Container content = this.getContentPane();
    JLabel[][] grid = new JLabel[3][3]; 
    char[][] game = new char[3][3]; 
    JButton restart = new JButton("Restart");
    JLabel status = new JLabel("Welcome!", JLabel.CENTER);
    int numClicks = 0;
    boolean isDone = false;
    boolean isXTurn = true;
    
   
    public TicTacToe() {
        
        this.setVisible(true);
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tic-Tac-Toe");
        JPanel p = new JPanel();
        
        content.add(status, BorderLayout.NORTH);
        status.setOpaque(true);
        status.setBackground(Color.yellow);
        status.setForeground(Color.blue);
        status.setFont(new Font("Helvetica",Font.BOLD, 12));
          
        p.setLayout(new GridLayout(3,3,3,3));
        p.setBackground(Color.black);
        content.add(p, BorderLayout.CENTER);
        
        for(int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                game[row][col] = ' ';
                grid[row][col] = new JLabel(" ", JLabel.CENTER);
                grid[row][col].addMouseListener(this);
                grid[row][col].setOpaque(true);
                grid[row][col].setBackground(Color.white);
                grid[row][col].setFont(new Font("Helvetica", Font.BOLD, 32));
                p.add(grid[row][col]);
                
                }
            }
        
        restart.addActionListener(this);
        content.add(restart, BorderLayout.SOUTH);
    }
      
    
    public void gameOver() {
        
        char winner = ' ';
        
        if(game[0][0] == game[1][1] && game[0][0] == game[2][2]){
            winner = game[0][0];
        }
        if(game[2][0] == game[1][1] && game[1][1] == game[0][2]){
            winner = game[2][0];
        } 
        
        if(winner == ' '){
            for (int row = 0; row < 3; row++)
            {
          if(game[row][0] != ' ')               
              if(game[row][0] == game[row][1] && game[row][0] == game[row][2])                   
                        winner = game[row][0];
            }

        }

        if(winner == ' '){

            for (int row = 0; row < 3; row++)
            {
          if(game[0][row] != ' ')               
               if(game[0][row] == game[1][row] && game[0][row] == game[2][row])                   
                        winner = game[0][row];
            }

        }
        
        isDone = true;
        
        if(winner == ' ' && numClicks == 9){
            status.setText("tie game");
        } else if (winner != ' '){
            status.setText("Game Over: " + winner + " Won!!!");
        } else {
        
            isDone = false;
        
            if (isXTurn){
                status.setText("X's Turn");
            } else {
                status.setText("O's Turn");
               }
            }
       
    }
    
    public void resetGame(){
        
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                grid[row][col].setText(" ");
                game[row][col] = ' ';
            }
        }
        numClicks = 0;
        isXTurn = true;
        status.setText("X's Turn");
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.resetGame();
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        
        if (isDone) resetGame();
    
    JLabel clicked = (JLabel) arg0.getSource();
    
    next:
        for (int row = 0; row < 3; row++)
        {
          for (int col = 0; col < 3; col++)
          {
            if (clicked == grid[row][col])
            {              
              if(clicked.getText()  != " ")
              {
                  status.setText("Invalid Move");
                  break next;
              }             
              else if(clicked.getText()  == " "  && isXTurn == true)
              {
                clicked.setText("X");
                clicked.setForeground(Color.RED);
                this.game[row][col] = 'X';

              }             
              else
              {
                clicked.setText("O");
                clicked.setForeground(Color.BLUE);
                this.game[row][col] = 'O';
              }
              
              isXTurn = !isXTurn;
              numClicks++;
              
              this.gameOver();
            }
          }
        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }
    
    public static void main(String[] args) {
        
        TicTacToe GUI = new TicTacToe();
        
    }
    
}
