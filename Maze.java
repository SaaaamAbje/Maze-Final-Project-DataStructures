public class Maze{

    MazeItem[] board = new MazeItem[64];    // the Board is an Array of Maze Items
    Brick brick = new Brick("brick");       // Create the brick
    Navigator nav;
    Camera sony;
    
    Maze(){
        // Load the Board with MazeItems
        brick.image="Wall-75x75.jpg";
                        
        board[0]  = brick; board[15] = brick; board[24] = brick; board[51] = brick; board[42] = brick;
        board[1]  = brick; board[16] = brick; board[32] = brick; board[57] = brick; board[56] = brick;
        board[3]  = brick; board[17] = brick; board[29] = brick; board[45] = brick; board[58] = brick;
        board[4]  = brick; board[19] = brick; board[31] = brick; board[46] = brick; board[59] = brick;
        board[5]  = brick; board[18] = brick; board[34] = brick; board[47] = brick; board[60] = brick;
        board[6]  = brick; board[21] = brick; board[35] = brick; board[48] = brick; board[61] = brick;
        board[7]  = brick; board[23] = brick; board[39] = brick; board[63] = brick; 
        board[8]  = brick; board[40] = brick; board[55] = brick; board[43] = brick;

        sony = new Camera(this);           // Create the Camera Thread
        nav  = new Navigator("navigator"); // Create the Navigator
        nav.image = "Walk.png";
        nav.load(this);
    }
    
    public static void main(String[] arg){
        new Maze();
    }
}
