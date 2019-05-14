package assignment1;

public class ShapeContainer {
	
	public static final int INIT_SIZE=10; 
	public static final int RESIZE=10;
	private Drawable[] drawablsArr;
	private int currentIndex;
	private int currentSize;
	private int num_Of_Rectangle;
	private int num_Of_Triangle;
	private static int num = 1;

	/**
	 * Deep copy constructor
	 * @param other
	 */
	public ShapeContainer(ShapeContainer other) {
		// *** Members initiating *** 
		num_Of_Triangle = other.num_Of_Triangle;
		num_Of_Rectangle = other.num_Of_Rectangle;
		currentSize = other.currentSize;
		currentIndex = other.currentIndex;
		drawablsArr = new Drawable[other.currentSize];
		
		// *** Deep copy of drawables array ***
		for (int i = 0; i < other.currentIndex; i++) {
			if(i == currentSize) resize();
			if(other.drawablsArr[i] == null) currentIndex--;
			else {
				if(other.drawablsArr[i] instanceof Triangle) {
					drawablsArr[i] = new Triangle((Triangle) other.drawablsArr[i]);
				}
				else {
					drawablsArr[i] = new Rectangle((Rectangle) other.drawablsArr[i]);
				}
			}
		}
	}

	public ShapeContainer() {
		drawablsArr = new Drawable[INIT_SIZE];
		currentIndex = 0;
		currentSize = INIT_SIZE;
		num_Of_Triangle = 0;
		num_Of_Rectangle = 0;
	}

	/**
	 * Adds a drawable shape to the array, resizing might occur in case insufficient space
	 * @param d
	 */
	public void add(Drawable d) {
		if(currentIndex == currentSize)
			this.resize();
		if(d instanceof Triangle) 
			num_Of_Triangle++;
		else
			num_Of_Rectangle++;
		drawablsArr[currentIndex++] = d;
	}

	private void resize() {
		Drawable[] tmp_drawabls = new Drawable[currentSize = currentSize + RESIZE];
		for (int i = 0; i < this.drawablsArr.length; i++) {
			tmp_drawabls[i] = drawablsArr[i];
		}
		drawablsArr = tmp_drawabls;
	}

	/**
	 * 
	 * @return number of rectangles in this shape
	 */
	public int R_size() {
		return num_Of_Rectangle;
	}
	
	/**
	 * 
	 * @param i index of the rectangle in correlation of rectangles only
	 * @return if 0 <= i < R_Size(), the rectangle at index i, otherwise, null
	 */
	public Rectangle R_at(int i) {
		Rectangle ans = null;
		if(i >= num_Of_Rectangle) 
			return ans;
		for(Drawable d : drawablsArr) {
			if(d instanceof Rectangle) {
				if(i==0) {
					ans = new Rectangle((Rectangle) d);
					break;
				}
				i--;
			}
		}
		return ans;
	}

	/**
	 * 
	 * @return number of triangles in this shape
	 */
	public int T_size() {
		return num_Of_Triangle;
	}

	/**
	 * 
	 * @param i index of the triangle in correlation of triangles only
	 * @return if 0 <= i < R_Size(), the triangle at index i, otherwise, null
	 */
	public Triangle T_at(int i) {
		Triangle ans = null;
		if(i > num_Of_Triangle) return ans;
		for(Drawable d : drawablsArr) {
			if(d instanceof Triangle) {
				if(i==0) {
					ans = new Triangle((Triangle) d);
					break;
				}
				i--;
			}
		}
		return ans;
	}

	/**
	 * removes every shape in the container that contains p
	 * @param p point of 2D space
	 */
	public void remove(Point p) {
		if(p == null) return;
		for (int i = 0; i < currentIndex; i++) {
			if(drawablsArr[i].contains(p)) {
				if(drawablsArr[i] instanceof Triangle)
					num_Of_Triangle--;
				else
					num_Of_Rectangle--;
				for (int j = i; j < drawablsArr.length-1; j++) {
					drawablsArr[j] = drawablsArr[j+1];
				}
				i--;
				currentIndex--;
			}
		}
	}

	/**
	 * translates every shape in the container by point point
	 * @param point of 2D space
	 */
	public void translate(Point point) {
		if(point == null) return;
		for (Drawable drawable : drawablsArr) {
			if(drawable == null) return;
			drawable.translate(point);
		}
	}
	
	/**
	 * 
	 * @return the sum of the areas for every shape in the container
	 */
	public double sumArea() {
		double ans = 0;
		for (Drawable drawable : drawablsArr) {
			if(drawable == null) return ans;
			ans += drawable.area();
		}
		return ans;
	}
	
	/**
	 * 
	 * @return the number of stored shapes in the container  
	 */
	public int size() {
		return currentSize;
	}
	
	/* *** Note: I overloaded minMaxPerimeter because some student claim that it shouldn't have an argument *** */
	
	/**
	 * calculates and prints min and max perimeter of the shapes (triangles & rectangles)
	 * 	and prints the number of comparisons were needed to find them
	 * @param num acts as an Id to prevent misconception of diffrent calls, 
	 * 		though the non-argument overloaded methods does so without explicitly giving an Id.
	 */
	public void minMaxPerimeter(int num) {
		double minPerimeter = Double.MAX_VALUE, maxPerimeter = drawablsArr[0].area();
		int number_Of_Comparisions = 0;
		for (Drawable drawable : drawablsArr) {
			if(drawable == null) break;
			double permieter = drawable.perimeter();
			if(permieter < minPerimeter) {
				minPerimeter = permieter;
			}
			else if(permieter > maxPerimeter) {
				maxPerimeter = permieter;
				number_Of_Comparisions++;
			}
			number_Of_Comparisions++;
		}
		System.out.println("number of comparision"+num+": "+number_Of_Comparisions);
		System.out.println("max perimeter"+num+": " + maxPerimeter);
		System.out.println("min perimeter"+num+": " + minPerimeter);
	}
	/**
	 * calculates and prints min and max perimeter of the shapes (triangles & rectangles) 
	 *  and prints the number of comparisons were needed to find them
	 */
	public void minMaxPerimeter() {
		double minPerimeter = Double.MAX_VALUE, maxPerimeter = drawablsArr[0].area();
		int number_Of_Comparisions = 0;
		for (Drawable drawable : drawablsArr) {
			if(drawable == null) break;
			double permieter = drawable.perimeter();
			if(permieter < minPerimeter) {
				minPerimeter = permieter;
			}
			else if(permieter > maxPerimeter) {
				maxPerimeter = permieter;
				number_Of_Comparisions++;
			}
			number_Of_Comparisions++;
		}
		System.out.println("number of comparision"+ num +": "+number_Of_Comparisions);
		System.out.println("max perimeter"+ num +": " + maxPerimeter);
		System.out.println("min perimeter"+ num++ +": " + minPerimeter);
	}

}
