import java.util.Arrays;
//import java.util.HashMap;
import java.util.LinkedHashMap;
//import java.util.Set;

public class Fast {

    public static void main(String[] args) throws Exception
    {
        if (args.length != 1)
            StdOut.println("Usage: must take input txt file as an argument");

        
        In in = new In(args[0]);
        int ints[] = in.readAllInts();
        int n = ints[0];
        Point[] points1 = new Point[(ints.length - 1) / 2];
        Point[] points2 = new Point[points1.length];
        
        for (int i = 0; i < n; i++)
            points1[i] = new Point(ints[i * 2 + 1], ints[i * 2 + 2]);
        
        Arrays.sort(points1); //sort points1 lexicographically
        
        StdDraw.setCanvasSize(800,800);
        StdDraw.setPenRadius(.0075);
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
        for (int i = 0; i < n; i++)
        {
        	if (i == 0)
        		points1[i].draw();
        	else if (points1[i].compareTo(points1[i - 1]) != 0)
        		points1[i].draw();
        }
        
        if (n < 4)
            return;
        
        StdDraw.setPenRadius(.002);
        StdDraw.setPenColor(StdDraw.GREEN);
        
        for (int i = 0; i < n; i++)
        {
        	for (int x = 0; x < n; x++)
        		points2[x] = points1[x]; //copy the array points1 to points2
        	
        	Arrays.sort(points2, points1[i].SLOPE_ORDER);
        	
//        	if (points1[i].slopeTo(points2[0]) != Double.NEGATIVE_INFINITY)
//        		throw new Exception("What the heck? " + points1[i].slopeTo(points2[0]));
        		
        	int temp = 0;
        	
        	//get duplicate reference points out of the way and continue, not having to check them every time thereafter
        	//first point is the reference point (always slope negative infinity) so skipping that and starting at index 1
        	for (int j = 1; j < n - 2; j++) 
        	{
        		temp = j;
        		if (points1[i].slopeTo(points2[j]) == Double.NEGATIVE_INFINITY)
        			continue;
        		else
        			break;
        	}
        	
        	//the real work begins / core logic
        	for (int j = temp; j < n - 2; j++)
        	{
        		if (points1[i].compareTo(points2[j]) > 0)
        			continue;
        		
        		//start checking for a run if the previous point's slope is not the same as the current one, indicating it was skipped over because not the first lexicographically
        		if (points2[j].slopeTo(points1[i]) != points2[j - 1].slopeTo(points1[i]))
        		{
        			if (points2[j].slopeTo(points1[i]) == points2[j + 1].slopeTo(points1[i]))
        			{
        				LinkedHashMap<Point, Byte> map = new LinkedHashMap<Point, Byte>();
        				map.put(points1[i], Byte.MIN_VALUE);
        				map.put(points2[j], Byte.MIN_VALUE);
	        			do
	        			{
	        				map.put(points2[++j], Byte.MIN_VALUE);
	        			}
	        			while(j < n - 1 && points2[j].slopeTo(points1[i]) == points2[j + 1].slopeTo(points1[i]));
	        			
	        			if (map.size() >= 4)
	        			{
	        				int l = 0;
	        				Point t1 = null;
	        				Point t2 = null;
	        				for (Point point : map.keySet())
	        				{
	        					if (t1 == null)
	        					    t1 = point;
	        					StdOut.print(point.toString());
	        					if (++l < map.size())
	        						StdOut.print(" -> ");
	        					else
	        					{
	        						StdOut.println();
	        						t2 = point;
	        					}
	        				}
	        				t1.drawTo(t2);
	        			}
        			}
        		}
        	}
        }
        //StdOut.println("done");
    }

}
