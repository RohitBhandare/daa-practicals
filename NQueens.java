package DAA_Practicals;

public class NQueens {
    public static void main(String[] args) {

        int n=8;
        int board[][]=new int[n][n];
        System.out.println("The solution for "+n+" queens is : ");
        solve(0,n,board);
    }

    private static void solve(int col, int n, int[][] board) {

        if(col==n)
        {
             for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {

                    System.out.print(" " + board[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for(int row=0;row<n;row++)
        {
            if(issafe(col,row,n,board))
            {
                board[row][col]=1;
                solve(col+1, n, board);
                board[row][col]=0;
            }
        }
    }

    private static boolean issafe(int col, int row, int n,int[][] board) {

        int x=row;
        int y=col;

        while(y>=0)
        {
            if(board[x][y]==1)
                return false;
            
            y--;
        }
        y=col;
        while (x >= 0 && y >= 0) // check for upper diagonal
        {
            if (board[x][y] == 1)
                return false;

            y--;
            x--;
        }
        x = row;
        y = col;
        while (x < n && y >= 0) // check for lower diagonal
        {
            if (board[x][y] == 1)
                return false;

            y--;
            x++;
        }
        return true;
    }
    
}

// Output:
// The solution for 4 queens is : 

//  0 0 1 0
//  1 0 0 0
//  0 0 0 1
//  0 1 0 0

//  0 1 0 0
//  0 0 0 1
//  1 0 0 0
//  0 0 1 0