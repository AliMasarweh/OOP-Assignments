package tests;

interface function{ double f(double x);  } 

class Zero implements function {
	public Zero() {;} // nothing to do.  
	public double f(double x) {return 0;} 
	public String toString() {return "0*x^0";}
	public Zero(Zero z) {}
} 

class Monom implements function{ 
	private double _a; 
	private int _b; 
	public Monom (double a, int b) {_a=a; _b=b;} 
	public Monom(Monom other) {
		this._a = other._a;
		this._b = other._b;
	}
	public double f(double x) {  double ans = _a * Math.pow(x,_b);  return ans; } 
	public boolean hasTheSamePower(Monom m) {return m._b==_b;}
	public Monom add(Monom other) {return new Monom(_a+other._a,_b);}
	public Monom sub(Monom other) {return new Monom(_a-other._a,_b);}
	public Monom multiply(Monom other) {return new Monom(_a*other._a,_b+other._b);}
	public Monom getNegative() {return new Monom(-_a,_b);}
	public String toString() {return _a+"*x^"+_b;}
} 


class RoundFunction implements function {

	@Override
	public double f(double x) {
		if (x > 0)
			return (int) (x + 0.5d);
		return (int) (x - 0.5d);
	}
}

class Sum implements function{
	
	function _a, _b;
	public Sum(function a, function b) {
		_a = a;
		_b = b;
	}
	
	public Sum(Sum _ans) {
		if(_ans._a instanceof Monom && _ans._b instanceof Sum) {
			this._a = new Monom((Monom) _ans._a);
			this._b = new Sum((Sum) _ans._b);
		}
		else if(_ans._a instanceof Monom && _ans._b instanceof Monom){
			this._a = new Monom((Monom) _ans._a);
			this._b = new Monom((Monom) _ans._b);
		}
		else {
			this._a = new Monom((Monom) _ans._a);
			this._b = new Zero();
		}
	}

	@Override
	public double f(double x) {
		return _a.f(x) + _b.f(x);
	} 
	
	public function getA() { return _a;}
	public function getB() { return _b;}
	public String toString() {return _a.toString() +" + "+ _b.toString();}
}

class Comp implements function{

	function _a, _b;
	public Comp(function a, function b) {
		_a = a;
		_b = b;
	}
	
	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		return _b.f(_a.f(x));
	} 
	public String toString() {return _a.toString()+"("+_b.toString()+")";}
}

public class Polynom implements function {
	private function _ans;  
	public Polynom() {_ans = new Zero();} 
	
	public Polynom(Polynom p) {
		if(p._ans instanceof Zero) {
			_ans = new Zero();
		}
		else if(p._ans instanceof Sum) {
			_ans = new Sum((Sum) p._ans);
		}
	}
	
	public double f(double x) {return _ans.f(x);} 
	public void add(Monom m) { 
		if(_ans instanceof Zero) {
			_ans = new Sum(m,_ans);
		}
		else if(_ans instanceof Sum) {
			function a = ((Sum) _ans).getA();
			if(((Monom) a).hasTheSamePower(m)) {
				//System.out.println("0 "+this);
				a = ((Monom) a).add(m);
				_ans = new Sum(a,((Sum) _ans).getB());
				//System.out.println("1 "+this);
			}
			else{
				//System.out.println("2 "+this);
				function b = ((Sum) _ans).getB();
				Polynom tmp = new Polynom();
				tmp._ans = b;
				tmp.add(m);
				//System.out.println("3 "+this);
				_ans = new Sum(a,tmp._ans);
				//System.out.println("4 "+this);
			}
		}
	} 
	
	public void add(Polynom p) {
		//System.out.println("ADD POLY");
		if(p._ans instanceof Zero) {
			//System.out.println("Returning "+this+" for Testing "+p);
			return;
		}
		else if(p._ans instanceof Sum) {
			this.add((Monom)((Sum)p._ans).getA());
			//System.out.println("5 "+this +" for Testing "+p);
			Polynom tmp = new Polynom();
			tmp._ans = ((Sum) p._ans).getB();
			//System.out.println("6 "+this +" for Testing "+tmp);
			this.add(tmp);
			//System.out.println("7 "+this +" for Testing "+tmp);
		}
		//System.out.println("End "+this);
	} 
	public void sub(Polynom p) {
		if(p._ans instanceof Zero) {
			return;
		}
		if(p._ans instanceof Sum) {
			this.add(((Monom)((Sum)p._ans).getA()).getNegative());
			p._ans = ((Sum)p._ans).getB();
			add(p);
		}
	} 
	public void mul(Monom m) {
		if(_ans instanceof Zero) {
			return;
		}
		else if(_ans instanceof Sum) {
			function a = ((Sum) _ans).getA();
			a = ((Monom) a).multiply(m);
			_ans = new Sum(a,((Sum) _ans).getB());
			function b = ((Sum) _ans).getB();
			Polynom tmp = new Polynom();
			tmp._ans = b;
			tmp.mul(m);
			_ans = new Sum(a,tmp._ans);
		}
	}
	
	public String toString() {
		return _ans.toString();
	}
		 
	public static void main(String[] args) {
		Polynom p = new Polynom();
		System.out.println(p);
		p.add(new Monom(1,2));
		System.out.println(p);
		p.add(new Monom(1,2));
		System.out.println(p);
		p.add(new Monom(1,1));
		p.add(new Monom(3,3));
		System.out.println(p);
		p.mul(new Monom(1,1));
		System.out.println(p);
		p.mul(new Monom(3,3));
		System.out.println(p);
		Polynom tmp = new Polynom(p);
		System.out.println(tmp);
		p.add(tmp);
		System.out.println(p);
	}
		 
}
