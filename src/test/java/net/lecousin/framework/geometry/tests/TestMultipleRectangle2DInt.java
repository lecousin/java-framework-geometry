package net.lecousin.framework.geometry.tests;

import net.lecousin.framework.geometry.MultipleRectangle2DInt;
import net.lecousin.framework.geometry.Rectangle2DInt;

import org.junit.Test;

public class TestMultipleRectangle2DInt {

	@Test
	public void test() {
		MultipleRectangle2DInt r1 = new MultipleRectangle2DInt();
		r1.add(new Rectangle2DInt(10, 10, 20, 20));
		r1.add(new Rectangle2DInt(12, 12, 10, 10));
		r1.add(new Rectangle2DInt(8, 8, 10, 10));
		r1.add(new Rectangle2DInt(6, 6, 30, 30));
		r1.add(0, 0, 1, 1);
		r1.getRectangles();
	}
	
}
