public class TriangleDrawer {
    public static void main(String[] args) {
        int SIZE = 10;
        int row = 1;

        while (row <= SIZE) {
            int col = 1;
            while (col <= row) {
                System.out.print("*");
                col++;
            }
            System.out.println();
            row = row + 1;
        }
    }
}