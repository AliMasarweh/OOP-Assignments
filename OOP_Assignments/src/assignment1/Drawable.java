package assignment1;
public interface Drawable {
	
	
	/**
	 * 
	 * @param d another object which is drawable
	 * @return if both object are drawn with the same points, true, otherwise, false
	 */
	public boolean equals(Drawable d);
	
	/**
	 * 
	 * @param p point of 2D space
	 * @return if this object contains the point, true, otherwise, false
	 */
	public boolean contains(Point p);
	
	/**
	 * 
	 * @return the parameter of this shape
	 */
	public double perimeter();  
	
	/**
	 * 
	 * @return the area of this shape
	 */
	public double area();  
	
	/**
	 * moves the shape's points by the given point coordinations
	 * @param p point of 2D space
	 */
	public void translate(Point p); 
} 
 