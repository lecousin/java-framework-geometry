package net.lecousin.framework.geometry.tests;

import net.lecousin.framework.geometry.Path2D;

import org.junit.Test;

public class TestPath2D {

	@Test
	public void test() {
		Path2D path1 = new Path2D();
		path1.add(new Path2D.MoveTo(10d, 10d));
		path1.add(new Path2D.LineTo(20d, 20d));
		Path2D path2 = new Path2D(new Path2D.MoveTo(10d, 10d), new Path2D.LineTo(20d, 20d));
		path2.getSegments();
		path2.removeLastSegment();
		path2.add(new Path2D.CubicTo(0, 0, 10, 10, 2, 2));
		path2.add(new Path2D.QuadTo(10, 10, 20, 20));
		path2.add(new Path2D.Close());
	}
	
}
