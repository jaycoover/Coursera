import java.util.Arrays;

public class Brute {

    public static void main(String[] args) 
    {
        if (args.length != 1)
            StdOut.println("Usage: must take input txt file as an argument");

        
        In in = new In(args[0]);
        int ints[] = in.readAllInts();
        int n = ints[0];
        Point[] points = new Point[(ints.length - 1) / 2];
        
        for (int i = 0; i < n; i++)
            points[i] = new Point(ints[i * 2 + 1], ints[i * 2 + 2]);
        
        Arrays.sort(points);
        
        StdDraw.setCanvasSize(800,800);
        StdDraw.setPenRadius(.0075);
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
        for (int i = 0; i < n; i++)
            points[i].draw();
        
        if (n < 4)
            return;
        
        StdDraw.setPenRadius(.002);
        StdDraw.setPenColor(StdDraw.GREEN);
        
        for (int a = 0; a < n - 3; a++)
            for (int b = a + 1; b < n - 2; b++)
                for (int c = b + 1; c < n - 1; c++)
                    for (int d = c + 1; d < n; d++)
                    {
                        if (points[a].slopeTo(points[b]) == points[a].slopeTo(points[c]) && points[a].slopeTo(points[c]) == points[a].slopeTo(points[d]))
                        {
                            StdOut.println(points[a].toString() + " -> " + points[b].toString() + " -> " + points[c].toString() + " -> " + points[d].toString());
                            //StdOut.println((points[a].slopeTo(points[b]) + " -> " + points[a].slopeTo(points[c]) + " -> " + points[a].slopeTo(points[d])));
                            points[a].drawTo(points[d]);
                        }
                    }
        //StdOut.println("done");
    }

}
