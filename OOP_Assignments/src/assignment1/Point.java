package assignment1;


/**
* this class represents a 2d point in the plane. <br>
* supports several operations on points in the plane.
*/
public class Point implements Drawable {

// ******** private data ********
	private double _x, _y;
	
// ********* constructors ********
   public Point (double x1, double y1) {
      _x = x1;
      _y = y1;
   }
   
 /** copy constructor:  
       1)here a direct access to a class memeber is performed,
         this will be done only in a constractor to achieve an identical copy
       2) using a call to another constractor code is not written twice
       */  
   public Point (Point p) { this(p._x, p._y);}
 
    // ********** public methodes *********
   public double x() {return _x;}
   public double y() {return _y;}

   /** @return a String contains the Point data*/
   public String toString()  {
      return "name: " + this.getClass() +"[" + _x + "," + _y+"]";
   }

   /**    logical equals 
   @param p other Object (Point).
   @return true iff p instance of Point && logicly the same) */
   public boolean equals (Point p) {
   	  return p!=null && p._x == _x && p._y==_y; }
   
   public double distance(Point p) {
	   return Math.sqrt(Math.pow(p._x-_x, 2) + Math.pow(p._y-_y, 2));
   }

	public void translate(Point p) {
		_x += p._x;
		_y += p._y;
	}

	@Override
	public boolean equals(Drawable d) {
		
		return false;
	}

	@Override
	public boolean contains(Point p) {
		return false;
	}

	@Override
	public double perimeter() {
		return 0;
	}

	@Override
	public double area() {
		return 0;
	}

	public boolean differ_In_2_Coordinates(Point _p2) {
		return this._x != _p2._x && this._y != _p2._y;
	}
}// class Point