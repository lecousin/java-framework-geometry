package net.lecousin.framework.geometry;

/** Line between 2 Point2DInt. */
@SuppressWarnings("squid:ClassVariableVisibilityCheck")
public class Line2DInt {

	/** Constructor. */
	public Line2DInt(Point2DInt point1, Point2DInt point2) {
		this.p1 = point1;
		this.p2 = point2;
	}

	/** Constructor. */
	public Line2DInt(Line2DInt copy) {
		this(new Point2DInt(copy.p1), new Point2DInt(copy.p2));
	}
	
	public Point2DInt p1;
	public Point2DInt p2;
	
	/** return the rectangle defined by the 2 points of this line. */
	public Rectangle2DInt getRectangle() {
		return new Rectangle2DInt(
			p1.x < p2.x ? p1.x : p2.x,
			p1.y < p2.y ? p1.y : p2.y,
			p1.x < p2.x ? p2.x - p1.x + 1 : p1.x - p2.x + 1,
			p1.y < p2.y ? p2.y - p1.y + 1 : p1.y - p2.y + 1
		);
	}
	
	/** return true if the point is on the line. */
	public boolean contains(Point2DInt point, int tolerance) {
		if (!containsAbsolute(point, tolerance)) return false;
		return getRectangle().contains(point, tolerance);
	}
	
	/** return true if the point is on the line considering the line as infinite. */
	public boolean containsAbsolute(Point2DInt point, int tolerance) {
		double[] eq = getEquation();
		return Math.abs(eq[0] * point.x + eq[1] * point.y - eq[2]) <= tolerance;
	}
	
	/** return the intersection point of the lines considering the lines as infinite, or null if they are on the same alignement. */
	@SuppressWarnings("squid:S1244") // comparison with zero is acceptable
	public Point2DInt getAbsoluteIntersection(Line2DInt line) {
		double[] temp = getEquation();
		double a1 = temp[0];
		double b1 = temp[1];
		double c1 = temp[2];

		temp = line.getEquation();
		double a2 = temp[0];
		double b2 = temp[1];
		double c2 = temp[2];
		// Cramer's rule for the system of linear equations
		double det = a1 * b2 - b1 * a2;
		if (det == 0)
			return null; // lines are the same or in the same direction
		return new Point2DInt((int)Math.round((c1 * b2 - b1 * c2) / det), (int)Math.round((a1 * c2 - c1 * a2) / det));
	}
	
	public boolean isHorizontal() { return p1.y == p2.y; }
	
	public boolean isVertical() { return p1.x == p2.x; }
	
	/** return true if this line is on the given line. If they are only in contact with one of their terminal points, it return false. */
	@SuppressWarnings("squid:S1244") // comparison with zero is acceptable
	public boolean isOn(Line2DInt line) {
		if (isVertical()) {
			if (!line.isVertical()) return false;
			if (p1.x != line.p1.x) return false;
			double lmin = Math.min(line.p1.y, line.p2.y);
			double lmax = Math.max(line.p1.y, line.p2.y);
			return (p1.y > lmin && p1.y < lmax) || (p2.y > lmin && p2.y < lmax);
		}
		if (isHorizontal()) {
			if (!line.isHorizontal()) return false;
			if (p1.y != line.p1.y) return false;
			double lmin = Math.min(line.p1.x, line.p2.x);
			double lmax = Math.max(line.p1.x, line.p2.x);
			return (p1.x > lmin && p1.x < lmax) || (p2.x > lmin && p2.x < lmax);
		}
		// check they are on the same infinite line
		double[] temp = getEquation();
		double a1 = temp[0];
		double b1 = temp[1];

		temp = line.getEquation();
		double a2 = temp[0];
		double b2 = temp[1];

		double det = a1 * b2 - b1 * a2;
		if (det != 0) return false;
		// check if they have a part in common
		Rectangle2DInt r = getRectangle().getIntersection(line.getRectangle());
		if (r == null) return false;
		return r.size.x > 1 && r.size.y > 1;
	}
	
	/** return true if this line is on the given line considering the two lines as infinite. */
	@SuppressWarnings("squid:S1244") // comparison with zero is acceptable
	public boolean isOnAbsolute(Line2DInt line) {
		if (isVertical()) return line.isVertical() && p1.x == line.p1.x;
		if (isHorizontal()) return line.isHorizontal() && p1.y == line.p1.y;
		double[] temp = getEquation();
		double a1 = temp[0];
		double b1 = temp[1];

		temp = line.getEquation();
		double a2 = temp[0];
		double b2 = temp[1];

		double det = a1 * b2 - b1 * a2;
		return det == 0;
	}
	
	/**
	 * Returns array with 3 numbers in it, which are the coefficients of the
	 * generalized line equation of the line corresponding to this line segment
	 * a*x+b*y=c is the equation => result[0]=a, result[1]=b, result[2]=c.
	 * 
	 * @return an array with 3 numbers in it, which are the coefficients of the generalized line equation
	 */
	public double[] getEquation() {
		double[] equation = new double[3];
		for (int i = 0; i < 3; i++)
			equation[i] = 0;
		
		if (p1.x == p2.x) {
			if (p1.y == p2.y)
				return equation;
			equation[0] = 1;
			equation[1] = 0;
			equation[2] = p1.x;
			return equation;
		}
		
		equation[0] = ((double)(p1.y - p2.y)) / (p2.x - p1.x);
		equation[1] = 1.0;
		equation[2] = p2.y + equation[0] * p2.x;
		return equation;
	}
	
	public double getLength() { return p1.getDistance(p2); }
	
	/** return the intersection point between this line and the given one, or null if there is no intersection or they are the same. */
	public Point2DInt getIntersection(Line2DInt line) {
		Point2DInt pt = getAbsoluteIntersection(line);
		if (pt == null || !getRectangle().contains(pt) || !line.getRectangle().contains(pt))
			return null;
		return pt;
	}
	
	/** return the intersection points between this line and the given rectangle: it may returns 0 to 2 points,
	 * or null if the line is on a rectangle border. */
	public Point2DInt[] getIntersection(Rectangle2DInt r) {
		Line2DInt line1 = r.getLeftLine();
		Line2DInt line2 = r.getRightLine();
		Line2DInt line3 = r.getTopLine();
		Line2DInt line4 = r.getBottomLine();
		if (isOn(line1) || isOn(line2) || isOn(line3) || isOn(line4)) return null;
		
		Point2DInt[] pts = new Point2DInt[2];
		int i = 0;
		Point2DInt p = getIntersection(line1);
		if (p != null) pts[i++] = p;
		p = getIntersection(line2);
		if (p != null && !search(i, pts, p))
			pts[i++] = p;
		p = getIntersection(line3);
		if (p != null && !search(i, pts, p))
			pts[i++] = p;
		p = getIntersection(line4);
		if (p != null && !search(i, pts, p))
			pts[i++] = p;
		Point2DInt[] result = new Point2DInt[i];
		while (i > 0) result[--i] = pts[i];
		return result;
	}
	
	private static boolean search(int i, Point2DInt[] pts, Point2DInt p) {
		for (int j = 0; j < i; ++j)
			if (pts[j].equals(p))
				return true;
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Line2DInt)) return false;
		Line2DInt l = (Line2DInt)obj;
		return l.p1.equals(p1) && l.p2.equals(p2);
	}
	
	@Override
	public int hashCode() {
		return p1.hashCode() + p2.hashCode() * 1000;
	}
	
	@Override
	public String toString() {
		return "[ " + p1.toString() + " ; " + p2.toString() + " ]";
	}
}
