import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Mazes {
    private int [][]mazesDemo = new int[30][30];

    public int[][] readFromFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\XUAN HIEU\\Desktop\\Project-AI-Snake\\src\\mazes\\mazes1.txt"));
        String line ="";
        int i=0;
        while (line!=null) {
            line = bufferedReader.readLine();
            String demo = line;
            if (demo != null) {
                String[] abc = demo.split(" ");
                for (int j = 0; j < 30; j++) {
                    mazesDemo[i][j] = Integer.parseInt(abc[j]);
                }
            }
            ++i;
        }
        return mazesDemo;
    }
}
