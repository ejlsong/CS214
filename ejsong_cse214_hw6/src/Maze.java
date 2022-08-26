import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

class Maze {

    private static String pair(int i, int j) {
        return i + "," + j;
    }

    public static void printMstMaze(int size, HashMap<String, String> mst) {
        final String WALL = "\u25A0";
        final String PATH = "\u00B7";
        for (int i = 0; i < size * 2 + 1; i++)
            System.out.print(WALL);
        System.out.println("");
        for (int i = 0; i < size; i++) {
            System.out.print(WALL);
            for (int j = 0; j < size - 1; j++) {
                if (i == 0 && j == 0)
                    System.out.print('S');
                else
                    System.out.print(PATH);

                if (mst.get(pair(i, j)).equals(pair(i, j + 1)) || mst.get(pair(i, j + 1)).equals(pair(i, j)))
                    System.out.print(PATH);
                else
                    System.out.print(WALL);
            }
            if (i == size - 1)
                System.out.print("E");
            else
                System.out.print(PATH);
            System.out.println(WALL);

            if (i < size - 1) {
                System.out.print(WALL);
                for (int j = 0; j < size; j++) {

                    if (mst.get(pair(i, j)).equals(pair(i + 1, j)) || mst.get(pair(i + 1, j)).equals(pair(i, j)))
                        System.out.print(PATH);
                    else
                        System.out.print(WALL);
                    System.out.print(WALL);
                }
                System.out.println("");

            }

        }
        for (int i = 0; i < size * 2 + 1; i++)
            System.out.print(WALL);
        System.out.println("");
    }

    public static Graph<String> createMazeGraph(int size) {
        Graph<String> graph = new Graph<String>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i > 0) // north
                    graph.addEdge(pair(i, j), pair(i - 1, j), new Random().nextInt(100));
                if (j > 0) // west
                    graph.addEdge(pair(i, j), pair(i, j - 1), new Random().nextInt(100));
                if (i < size - 1) // south
                    graph.addEdge(pair(i, j), pair(i + 1, j), new Random().nextInt(100));
                if (j < size - 1) // east
                    graph.addEdge(pair(i, j), pair(i, j + 1), new Random().nextInt(100));
            }
        }
        return graph;
    }

    public static Graph<String> createMstGraph(HashMap<String, String> parentMap) {

    }

    public static void main(String[] args) {
        int size = 10;
        Graph<String> g = createMazeGraph(size);
        HashMap<String, String> mst = g.primsMST();
        System.out.println(mst);
        printMstMaze(size, mst);
        Graph<String> maze = createMstGraph(mst);
        maze.solveMaze(pair(0,0), pair(size-1, size-1));
    }
}