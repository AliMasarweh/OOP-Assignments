package assignment1;


public class Rectangle implements Drawable {
	
	private Point _p1;
	private Point _p2;
	private Point _p3;
	private Point _p4;
	
	public Point p1() {
		return _p1;
	}
	public void set_p1(Point _p1) {
		this._p1 = _p1;
	}
	public Point p2() {
		return _p2;
	}
	public void set_p2(Point _p2) {
		this._p2 = _p2;
	}
	public Point p3() {
		return _p3;
	}
	public void set_p3(Point _p3) {
		this._p3 = _p3;
	}
	public Point p4() {
		return _p4;
	}
	public void set_p4(Point _p4) {
		this._p4 = _p4;
	}
	
	public Rectangle(Point p1, Point p2) {
		_p1 = new Point(p1);
		_p2 = new Point(p2);
		_p3 = new Point(p1.x(),p2.y());
		_p4 = new Point(p2.x(),p1.y());
	}
	
	public Rectangle(Rectangle other) {
		this(other._p1,other._p2);
	}
	
	@Override
	public boolean equals(Drawable d) {
		if(d==null) return false;
		if(d instanceof Rectangle) {
			
			//{p1,p2} == {p1`,p2`}?
			if(pairWiseEquals(_p1, _p2, ((Rectangle) d)._p1, ((Rectangle) d)._p2))
				return true;
			
			//{p1,p3} == {p1`,p2`}?
			if(pairWiseEquals(_p1, _p3, ((Rectangle) d)._p1, ((Rectangle) d)._p2))
				return true;
			
			//{p1,p4} == {p1`,p2`}?
			if(pairWiseEquals(_p1, _p4, ((Rectangle) d)._p1, ((Rectangle) d)._p2))
				return true;
			
			//{p2,p3} == {p1`,p2`}?
			if(pairWiseEquals(_p2, _p3, ((Rectangle) d)._p1, ((Rectangle) d)._p2))
				return true;
			
			//{p2,p4} == {p1`,p2`}?
			if(pairWiseEquals(_p2, _p4, ((Rectangle) d)._p1, ((Rectangle) d)._p2))
				return true;
			
			//{p3,p4} == {p1`,p2`}?
			if(pairWiseEquals(_p3, _p4, ((Rectangle) d)._p1, ((Rectangle) d)._p2))
				return true;
		}
		return false;
	}
	
	/**
	 * {p1,p2} == {p3,p4}
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @return if p1 equals either p3 or p4 and p2 equals the other point, true,
	 *  otherwise, false
	 */
	private boolean pairWiseEquals(Point p1, Point p2, Point p3, Point p4) {
		return (p1.equals(p3) && p2.equals(p4)) || (p2.equals(p3) && p1.equals(p4));
	}
	
	@Override
	public boolean contains(Point p) {
		if(p == null) return false;
		double minX, maxX, minY, maxY;
		minX = Math.min(Math.min(_p1.x(), _p2.x()), Math.min(_p3.x(), _p4.x()));
		maxX = Math.max(Math.max(_p1.x(), _p2.x()), Math.max(_p3.x(), _p4.x()));
		minY = Math.min(Math.min(_p1.y(), _p2.y()), Math.min(_p3.y(), _p4.y()));
		maxY = Math.max(Math.max(_p1.y(), _p2.y()), Math.max(_p3.y(), _p4.y()));
		if(p.x() > maxX || p.x() < minX)
			return false;
		if(p.y() > maxY || p.y() < minY)
			return false;
		return true;
	}
	
	@Override
	public double perimeter() {
		return _p1.distance(_p3) + _p3.distance(_p2) + 
				_p2.distance(_p4) + _p4.distance(_p1); 
	}
	
	@Override
	public double area() {
/*		Point p1 = _p1, p2 = null, p3 = null;
		if(!_p1.differ_In_2_Coordinates(_p2) && !_p1.differ_In_2_Coordinates(_p3)) {
			p2 = _p2;
			p3 = _p3;
		}
		else if(!_p1.differ_In_2_Coordinates(_p3) && !_p1.differ_In_2_Coordinates(_p4)) {
			p2 = _p3;
			p3 = _p4;
		}
		else {
			p2 = _p2;
			p3 = _p4;
		}*/
		return _p1.distance(_p3) * _p1.distance(_p4);
	}
	
	@Override
	public void translate(Point p) {
		_p1.translate(p);
		_p2.translate(p);
		_p3.translate(p);
		_p4.translate(p);
	}
	
	/**
	 * 
	 * @return width of this rectangle
	 */
	public double dx() {
		return Math.abs(_p1.x() - _p2.x());
	}
	/**
	 * 
	 * @return height of this rectangle
	 */
	public double dy() {
		return Math.abs(_p1.y() - _p2.y());
	}
	
	public String toString() {
		return "{"+this._p1+","+this._p2+"}";
	}
}