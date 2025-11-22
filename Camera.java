import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Camera extends Frame implements Runnable, MouseListener, MouseMotionListener {
    private String imagePath;
    private Thread refresher;
    private Maze maze;
    
    private int xBoard=50;
    private int yBoard=80;
    private int xLoc=xBoard+14;
    private int yLoc=yBoard+15;
    private int boardSize;
    
    // Mouse Location Variables cellNo, cellRow, cellCol is updated if Mouse is moved
    private int cellNo;
    private int cellRow;
    private int cellCol;
    

    // Constructor
    public Camera(Maze m) {
	maze = m;
	boardSize=maze.board.length;  


        // Basic frame settings
        setTitle("Java Maze Project");
        setSize(800, 800);
        setVisible(true);

        // Close window action
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                if (refresher != null) refresher.interrupt();
                dispose();
                System.exit(0);
            }
        });

        // Start the refresh thread
        refresher = new Thread(this);
        refresher.start();
	addMouseListener(this);
	addMouseMotionListener(this);
	
    }

    // Paint method to draw images

    public void paint(Graphics g) {

	Image board = Toolkit.getDefaultToolkit().getImage("FloorGrid.jpg"); 	// Load the board image
	g.drawImage(board, xBoard, yBoard, this); 				// Display the Gridded Board

	for(int cell=0;cell<boardSize;cell++){			// Display an image per cell of the board
	 if(maze.board[cell] != null) {
	    int x = cell % 8;   // Calculate the pixel pointer for x
	    int y = cell / 8;   // Calculate the pixel pointer for y
	    Image pic = Toolkit.getDefaultToolkit().getImage(maze.board[cell].image);
	    g.drawImage(pic, (x*75)+xLoc, (y*75)+yLoc,this);    
	 }

	}

    }


    // Thread run method
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                repaint();
                Thread.sleep(200); // refresh every 1 second
            }
        } catch (InterruptedException e) {
            // exit gracefully
        }
    }


public void mouseReleased(MouseEvent e) { // Mouse key is released

}

public void mouseClicked(MouseEvent e) { // Mouse key is pressed/released

	//System.out.println("Mouse Clicked\n");
}

public void mouseExited(MouseEvent e) { // Mouse exited the component
	System.out.println("Mouse Exited\n");

}

public void mouseEntered(MouseEvent e) { // Mouse entered the component
	System.out.println("Mouse Entered\n");

}
public void mousePressed(MouseEvent e) { // Mouse key is pressed
	System.out.println("Mouse Pressed at location: "+cellNo);
	System.out.println("Row: "+cellRow);
	System.out.println("Col: "+cellCol);
	System.out.println();

}





public void mouseMoved(MouseEvent e) { // Mouse Moved
	int col=((e.getX()-xBoard-17)/75);
	int row=((e.getY()-yBoard-19)/75);
	int cell = row*8+col;
	
	cellNo=cell;  // Update the location of the mouse, which cell no. it is currently located
	cellRow=row;
	cellCol=col;
	
	// Uncomment below to test the location
	//System.out.println("Row: " +row+ "\nColumn: "+col);
	//System.out.println("Mouse Location: board["+cell+"]");

}

public void mouseDragged(MouseEvent e) { // Mouse key is pressed


}


}
