package assignment1;


public class Triangle implements Drawable{
	
	private Point _p1;
	private Point _p2;
	private Point _p3;
	
	
	public Point p1() {
		return _p1;
	}

	public void setP1(Point p1) {
		this._p1 = p1;
	}

	public Point p2() {
		return _p2;
	}

	public void setP2(Point p2) {
		this._p2 = p2;
	}

	public Point p3() {
		return _p3;
	}

	public void setP3(Point p3) {
		this._p3 = p3;
	}

	/**
	 * Constructor of a triangle object described by the three points
	 * @param p1 first point
	 * @param p2 second point
	 * @param p3 third point
	 */
	public Triangle(Point p1, Point p2, Point p3) {
		_p1 = new Point(p1);
		_p2 = new Point(p2);
		_p3 = new Point(p3);
	}
	/**
	 * Deep copy constructor, duplicating the argument other's points  
	 * @param other
	 */
	public Triangle(Triangle other) {
		this(other._p1, other._p2, other._p3);
	}

	
	@Override
	public boolean equals(Drawable d) {
		if(d == null) return false;
		if(d instanceof Triangle) {
			if(_p1.equals(((Triangle) d)._p1)) {
				if(pairWiseEquals(_p2, _p3, ((Triangle) d)._p2, ((Triangle) d)._p3))
						return true;
			}
			else if(_p2.equals(((Triangle) d)._p1)) {
				if(pairWiseEquals(_p1, _p3, ((Triangle) d)._p2, ((Triangle) d)._p3))
					return true;
			}
			else if(_p3.equals(((Triangle) d)._p1)) {
				if(pairWiseEquals(_p1, _p2, ((Triangle) d)._p2, ((Triangle) d)._p3))
					return true;
			}
		}
		return false;
	}
	
	private boolean pairWiseEquals(Point p1, Point p2, Point p3, Point p4) {
		return (p1.equals(p3) && p2.equals(p4)) || (p2.equals(p3) && p1.equals(p4));
	}

	@Override
	/**
	 * if the point 'p' is contained in this triangle, 
	 * then the 3 constructed triangles of a, b, c and 'p'
	 * have area equal to this triangle area (abc).
	 */
	public boolean contains(Point p) {
		if(p == null)
			return false;
		/*if(p.equals(_p1) || p.equals(_p2) || p.equals(_p3))
			return true;*/
		double t_area = this.area();
		double pab_area = (new Triangle(p, _p1, _p2)).area();
		double pac_area = (new Triangle(p, _p1, _p3)).area();
		double pbc_area = (new Triangle(p, _p2, _p3)).area();
		return t_area == (pab_area + pac_area + pbc_area);
	}

	@Override
	public double perimeter() {
		double[] perimeter = new double[1];
		if(check_For_Nulls(perimeter)) {
			return perimeter[0];
		}
		return _p1.distance(_p2) + _p2.distance(_p3) + _p3.distance(_p1);
	}
	
	/**
	 * checks if any points is null and returns the perimeter accordingly 
	 * @param perimeter helping arugment to return the perimeter in case of null points
	 * @return if any point is null, true, otherwise, false
	 */
	private boolean check_For_Nulls(double[] perimeter) {
		if((_p1 == null && _p2 == null) || (_p2 == null && _p3 == null) || (_p1 == null && _p3 == null)) {
			perimeter[0] = 0;
			return true;
		}
		if(_p1 == null) {
			perimeter[0] = _p2.distance(_p3);
			return true;
		}
		if(_p2 == null) {
			perimeter[0] = _p1.distance(_p3);
			return true;
		}
		if(_p3 == null) {
			perimeter[0] = _p1.distance(_p3);
			return true;
		}
		return false;
	}

	@Override
	/**
	 * returns the area using this formula: (x1*(y2 -y3) + x2*(y3-y1) + x3*(y1 - y2))^2
	 */
	public double area() {
		if(_p1 == null || _p2 == null || _p3 == null) return 0;
		
		return Math.abs(
				(_p1.x() - _p3.x()) * (_p2.y()-_p1.y()) -
				(_p1.x() - _p2.x()) * (_p3.y()-_p1.y())
						)/2.0;
		
		/*return Math.abs(
				(
				_p1.x() * (_p2.y() - _p3.y())
				+ _p2.x() * (_p3.y() - _p1.y())
				+ _p3.x() * (_p1.y() - _p2.y())
				/ 2.0)
				);*/
	}

	@Override
	public void translate(Point p) {
		if(p == null) return;
		_p1.translate(p);
		_p2.translate(p);
		_p3.translate(p);
	}
	
	public String toString() {
		return "{"+_p1+","+_p2+","+_p3+"}";
	}

}
