package net.lecousin.framework.geometry.tests;

import net.lecousin.framework.geometry.Line2DInt;
import net.lecousin.framework.geometry.Point2DInt;
import net.lecousin.framework.geometry.Rectangle2DInt;

import org.junit.Assert;
import org.junit.Test;

public class TestRectangle2DInt {

	@Test
	public void test() {
		Rectangle2DInt rect1 = new Rectangle2DInt(10, 10, 20, 20);
		// copy
		Rectangle2DInt rect2 = new Rectangle2DInt(rect1);
		Assert.assertEquals(rect1, rect2);
		Assert.assertEquals(rect1.hashCode(), rect2.hashCode());
		Assert.assertEquals(rect1.toString(), rect2.toString());
		// equals
		Assert.assertFalse(rect1.equals(null));
		Assert.assertFalse(rect1.equals(new Object()));
		rect2 = new Rectangle2DInt(10, 11, 20, 20);
		Assert.assertFalse(rect1.equals(rect2));
		rect2 = new Rectangle2DInt(10, 10, 20, 21);
		Assert.assertFalse(rect1.equals(rect2));
		// contains
		Assert.assertTrue(rect1.contains(12, 13));
		Assert.assertFalse(rect1.contains(8, 13));
		Assert.assertFalse(rect1.contains(12, 8));
		Assert.assertFalse(rect1.contains(50, 13));
		Assert.assertFalse(rect1.contains(12, 50));
		// contains point
		Assert.assertTrue(rect1.contains(new Point2DInt(12, 13)));
		Assert.assertFalse(rect1.contains(new Point2DInt(8, 13)));
		Assert.assertFalse(rect1.contains(new Point2DInt(12, 8)));
		Assert.assertFalse(rect1.contains(new Point2DInt(50, 13)));
		Assert.assertFalse(rect1.contains(new Point2DInt(12, 50)));
		// contains point with tolerance
		Assert.assertTrue(rect1.contains(new Point2DInt(12, 13), 0));
		Assert.assertFalse(rect1.contains(new Point2DInt(8, 13), 0));
		Assert.assertFalse(rect1.contains(new Point2DInt(12, 8), 0));
		Assert.assertFalse(rect1.contains(new Point2DInt(50, 13), 0));
		Assert.assertFalse(rect1.contains(new Point2DInt(12, 50), 0));
		// contains rectangle
		Assert.assertTrue(rect1.contains(new Rectangle2DInt(15, 15, 5, 5)));
		Assert.assertFalse(rect1.contains(new Rectangle2DInt(0, 15, 5, 5)));
		Assert.assertFalse(rect1.contains(new Rectangle2DInt(15, 0, 5, 5)));
		Assert.assertFalse(rect1.contains(new Rectangle2DInt(150, 15, 5, 5)));
		Assert.assertFalse(rect1.contains(new Rectangle2DInt(15, 150, 5, 5)));
		// hasCommonArea
		Assert.assertTrue(rect1.hasCommonArea(new Rectangle2DInt(15, 15, 5, 5)));
		Assert.assertFalse(rect1.hasCommonArea(new Rectangle2DInt(0, 15, 5, 5)));
		Assert.assertFalse(rect1.hasCommonArea(new Rectangle2DInt(15, 0, 5, 5)));
		Assert.assertFalse(rect1.hasCommonArea(new Rectangle2DInt(150, 15, 5, 5)));
		Assert.assertFalse(rect1.hasCommonArea(new Rectangle2DInt(15, 150, 5, 5)));
		// getIntersection
		Assert.assertArrayEquals(new Point2DInt[] {
			new Point2DInt(15, 10),
			new Point2DInt(15, 29),
		}, rect1.getIntersection(new Line2DInt(new Point2DInt(15, 0), new Point2DInt(15, 40))));
		rect2 = new Rectangle2DInt(15, 0, 50, 50);
		Assert.assertEquals(new Rectangle2DInt(15, 10, 15, 20), rect1.getIntersection(rect2));
		// extendToContain
		rect1.extendToContain(rect2);
		Assert.assertEquals(new Rectangle2DInt(10, 0, 55, 50), rect1);
		rect1.extendToContain(rect2);
		Assert.assertEquals(new Rectangle2DInt(10, 0, 55, 50), rect1);
		rect1 = new Rectangle2DInt(20, 20, 10, 10);
		rect2 = new Rectangle2DInt(0, 0, 10, 10);
		rect1.extendToContain(rect2);
		Assert.assertEquals(new Rectangle2DInt(0, 0, 30, 30), rect1);
		rect1 = new Rectangle2DInt(20, 20, 10, 10);
		rect2 = new Rectangle2DInt(30, 0, 10, 10);
		rect1.extendToContain(rect2);
		Assert.assertEquals(new Rectangle2DInt(20, 0, 20, 30), rect1);
		rect1 = new Rectangle2DInt(20, 20, 10, 10);
		rect2 = new Rectangle2DInt(30, 0, 10, 10);
		rect2.extendToContain(rect1);
		Assert.assertEquals(new Rectangle2DInt(20, 0, 20, 30), rect2);
	}
	
}
