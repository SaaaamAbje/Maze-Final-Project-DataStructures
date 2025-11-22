import java.util.Stack;

public class Navigator extends MazeItem implements Runnable {

    Maze maze;
    int loc;

    Stack<Integer> stack = new Stack<>();
    boolean[] visited = new boolean[64];

    Navigator(String d){
        super(d);
        loc = 2;   // START at cell 2
    }

    public void load(Maze m){
        maze = m;
        maze.board[loc] = this;
        stack.push(loc);
        visited[loc] = true;

        new Thread(this).start();
    }

    // Check if a position is valid to move into
    private boolean canMove(int next){
        if(next < 0 || next >= 64) return false;         // Out of bounds
        if(maze.board[next] instanceof Brick) return false; // Is a wall
        if(visited[next]) return false;                   // Already visited
        return true;
    }

    // Move diagnostic
    private void moveTo(int next){
        maze.board[next] = this;
        maze.board[loc] = null;
        loc = next;
        visited[loc] = true;
        stack.push(loc);

        System.out.println("Moved to: " + loc);
    }

    public void run(){

        while(!stack.isEmpty()){

            // ✅ FINISH at cell 62
            if(loc == 62){
                System.out.println("\n✅ FINISHED AT 62");
                break;
            }

            boolean moved = false;

            // D I R E C T I O N   P R I O R I T Y
            // Up (-8)
            if(canMove(loc - 8)){
                moveTo(loc - 8);
                moved = true;
            }
            // Right (+1)
            else if(canMove(loc + 1)){
                moveTo(loc + 1);
                moved = true;
            }
            // Down (+8)
            else if(canMove(loc + 8)){
                moveTo(loc + 8);
                moved = true;
            }
            // Left (-1)
            else if(canMove(loc - 1)){
                moveTo(loc - 1);
                moved = true;
            }

            // DEAD END — BACKTRACK
            if(!moved){
                stack.pop();

                if(stack.isEmpty()) break;

                int back = stack.peek();
                maze.board[loc] = null;
                loc = back;
                maze.board[loc] = this;

                System.out.println("Backtracking to: " + loc);
            }

            try{
                Thread.sleep(500);  // ✅ DELAY (change if you want)
            }
            catch(Exception e){}
        }
    }
}
