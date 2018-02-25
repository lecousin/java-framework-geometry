package net.lecousin.framework.geometry.tests;

import net.lecousin.framework.geometry.Line2DInt;
import net.lecousin.framework.geometry.Point2DInt;
import net.lecousin.framework.geometry.Rectangle2DInt;

import org.junit.Assert;
import org.junit.Test;

public class TestLine2DInt {

	@Test
	public void test() {
		Line2DInt line1 = new Line2DInt(new Point2DInt(10, 100), new Point2DInt(22, 100));
		Assert.assertEquals(10, line1.p1.x);
		Assert.assertEquals(100, line1.p1.y);
		Assert.assertEquals(22, line1.p2.x);
		Assert.assertEquals(100, line1.p2.y);
		Assert.assertEquals(12d, line1.getLength(), 0d);
		// copy
		Line2DInt line2 = new Line2DInt(line1);
		Assert.assertEquals(line1, line2);
		Assert.assertEquals(line1.hashCode(), line2.hashCode());
		Assert.assertEquals(line1.toString(), line2.toString());
		// getRectangle
		Rectangle2DInt rect1 = line1.getRectangle();
		Assert.assertEquals(line1.p1.x, rect1.position.x);
		Assert.assertEquals(line1.p1.y, rect1.position.y);
		Assert.assertEquals(13, rect1.size.x);
		Assert.assertEquals(1, rect1.size.y);
		Rectangle2DInt rect2 = new Line2DInt(new Point2DInt(22, 100), new Point2DInt(10, 100)).getRectangle();
		Assert.assertEquals(rect1, rect2);
		rect2 = new Line2DInt(new Point2DInt(22, 100), new Point2DInt(10, 90)).getRectangle();
		Assert.assertEquals(10, rect2.position.x);
		Assert.assertEquals(90, rect2.position.y);
		Assert.assertEquals(13, rect2.size.x);
		Assert.assertEquals(11, rect2.size.y);
		rect2 = new Line2DInt(new Point2DInt(22, 90), new Point2DInt(10, 100)).getRectangle();
		Assert.assertEquals(10, rect2.position.x);
		Assert.assertEquals(90, rect2.position.y);
		Assert.assertEquals(13, rect2.size.x);
		Assert.assertEquals(11, rect2.size.y);
		// contains
		Assert.assertTrue(line1.contains(new Point2DInt(14, 100), 0));
		line2 = new Line2DInt(new Point2DInt(10, 10), new Point2DInt(20, 20));
		Assert.assertTrue(line2.contains(new Point2DInt(14, 14), 0));
		Assert.assertTrue(line2.contains(new Point2DInt(18, 18), 0));
		Assert.assertTrue(line2.contains(new Point2DInt(19, 19), 0));
		Assert.assertTrue(line2.contains(new Point2DInt(12, 12), 0));
		Assert.assertFalse(line2.contains(new Point2DInt(14, 15), 0));
		Assert.assertFalse(line2.contains(new Point2DInt(14, 13), 0));
		Assert.assertFalse(line2.contains(new Point2DInt(8, 11), 0));
		Assert.assertFalse(line2.contains(new Point2DInt(9, 7), 0));
		// intersection
		Assert.assertEquals(new Point2DInt(13, 13), line2.getAbsoluteIntersection(new Line2DInt(new Point2DInt(13, 8), new Point2DInt(13, 20))));
		Assert.assertNull(line2.getAbsoluteIntersection(new Line2DInt(new Point2DInt(20, 10), new Point2DInt(30, 20))));
		// horiz/vert
		Assert.assertFalse(line2.isHorizontal());
		Assert.assertFalse(line2.isVertical());
		Assert.assertTrue(line1.isHorizontal());
		Assert.assertTrue(new Line2DInt(new Point2DInt(0, 0), new Point2DInt(0, 10)).isVertical());
		// isOn
		// - horiz
		line1 = new Line2DInt(new Point2DInt(10, 100), new Point2DInt(22, 100));
		line2 = new Line2DInt(new Point2DInt(14, 100), new Point2DInt(20, 100));
		Assert.assertTrue(line2.isOn(line1));
		line2 = new Line2DInt(new Point2DInt(14, 101), new Point2DInt(20, 100));
		Assert.assertFalse(line2.isOn(line1));
		Assert.assertFalse(line1.isOn(line2));
		line2 = new Line2DInt(new Point2DInt(14, 101), new Point2DInt(20, 101));
		Assert.assertFalse(line2.isOn(line1));
		line2 = new Line2DInt(new Point2DInt(4, 100), new Point2DInt(6, 100));
		Assert.assertFalse(line2.isOn(line1));
		Assert.assertFalse(line1.isOn(line2));
		line2 = new Line2DInt(new Point2DInt(4, 100), new Point2DInt(12, 100));
		Assert.assertTrue(line2.isOn(line1));
		// - vert
		line1 = new Line2DInt(new Point2DInt(100, 10), new Point2DInt(100, 22));
		line2 = new Line2DInt(new Point2DInt(100, 14), new Point2DInt(100, 20));
		Assert.assertTrue(line2.isOn(line1));
		line2 = new Line2DInt(new Point2DInt(101, 14), new Point2DInt(100, 20));
		Assert.assertFalse(line2.isOn(line1));
		Assert.assertFalse(line1.isOn(line2));
		line2 = new Line2DInt(new Point2DInt(101, 14), new Point2DInt(101, 20));
		Assert.assertFalse(line2.isOn(line1));
		line2 = new Line2DInt(new Point2DInt(100, 4), new Point2DInt(100, 6));
		Assert.assertFalse(line2.isOn(line1));
		Assert.assertFalse(line1.isOn(line2));
		line2 = new Line2DInt(new Point2DInt(100, 4), new Point2DInt(100, 12));
		Assert.assertTrue(line2.isOn(line1));
		// - other
		line1 = new Line2DInt(new Point2DInt(10, 10), new Point2DInt(20, 20));
		line2 = new Line2DInt(new Point2DInt(12, 12), new Point2DInt(16, 16));
		Assert.assertTrue(line2.isOn(line1));
		Assert.assertTrue(line1.isOn(line2));
		line2 = new Line2DInt(new Point2DInt(2, 2), new Point2DInt(16, 16));
		Assert.assertTrue(line2.isOn(line1));
		Assert.assertTrue(line1.isOn(line2));
		line2 = new Line2DInt(new Point2DInt(2, 3), new Point2DInt(3, 3));
		Assert.assertFalse(line2.isOn(line1));
		Assert.assertFalse(line1.isOn(line2));
		line2 = new Line2DInt(new Point2DInt(20, 20), new Point2DInt(30, 30));
		Assert.assertFalse(line2.isOn(line1));
		Assert.assertFalse(line1.isOn(line2));
		// isOnAbsolute
		// - horiz
		line1 = new Line2DInt(new Point2DInt(10, 100), new Point2DInt(22, 100));
		line2 = new Line2DInt(new Point2DInt(14, 100), new Point2DInt(20, 100));
		Assert.assertTrue(line2.isOnAbsolute(line1));
		line2 = new Line2DInt(new Point2DInt(14, 101), new Point2DInt(20, 100));
		Assert.assertFalse(line2.isOnAbsolute(line1));
		Assert.assertFalse(line1.isOnAbsolute(line2));
		line2 = new Line2DInt(new Point2DInt(14, 101), new Point2DInt(20, 101));
		Assert.assertFalse(line2.isOnAbsolute(line1));
		line2 = new Line2DInt(new Point2DInt(4, 100), new Point2DInt(6, 100));
		Assert.assertTrue(line2.isOnAbsolute(line1));
		Assert.assertTrue(line1.isOnAbsolute(line2));
		line2 = new Line2DInt(new Point2DInt(4, 100), new Point2DInt(12, 100));
		Assert.assertTrue(line2.isOnAbsolute(line1));
		// - vert
		line1 = new Line2DInt(new Point2DInt(100, 10), new Point2DInt(100, 22));
		line2 = new Line2DInt(new Point2DInt(100, 14), new Point2DInt(100, 20));
		Assert.assertTrue(line2.isOnAbsolute(line1));
		line2 = new Line2DInt(new Point2DInt(101, 14), new Point2DInt(100, 20));
		Assert.assertFalse(line2.isOnAbsolute(line1));
		Assert.assertFalse(line1.isOnAbsolute(line2));
		line2 = new Line2DInt(new Point2DInt(101, 14), new Point2DInt(101, 20));
		Assert.assertFalse(line2.isOnAbsolute(line1));
		line2 = new Line2DInt(new Point2DInt(100, 4), new Point2DInt(100, 6));
		Assert.assertTrue(line2.isOnAbsolute(line1));
		Assert.assertTrue(line1.isOnAbsolute(line2));
		line2 = new Line2DInt(new Point2DInt(100, 4), new Point2DInt(100, 12));
		Assert.assertTrue(line2.isOnAbsolute(line1));
		// - other
		line1 = new Line2DInt(new Point2DInt(10, 10), new Point2DInt(20, 20));
		line2 = new Line2DInt(new Point2DInt(12, 12), new Point2DInt(16, 16));
		Assert.assertTrue(line2.isOnAbsolute(line1));
		Assert.assertTrue(line1.isOnAbsolute(line2));
		line2 = new Line2DInt(new Point2DInt(2, 2), new Point2DInt(16, 16));
		Assert.assertTrue(line2.isOnAbsolute(line1));
		Assert.assertTrue(line1.isOnAbsolute(line2));
		line2 = new Line2DInt(new Point2DInt(2, 3), new Point2DInt(3, 3));
		Assert.assertFalse(line2.isOnAbsolute(line1));
		Assert.assertFalse(line1.isOnAbsolute(line2));
		line2 = new Line2DInt(new Point2DInt(20, 20), new Point2DInt(30, 30));
		Assert.assertTrue(line2.isOnAbsolute(line1));
		Assert.assertTrue(line1.isOnAbsolute(line2));
		// getIntersection
		line1 = new Line2DInt(new Point2DInt(10, 10), new Point2DInt(20, 20));
		line2 = new Line2DInt(new Point2DInt(20, 10), new Point2DInt(10, 20));
		Assert.assertEquals(new Point2DInt(15, 15), line1.getIntersection(line2));
		line2 = new Line2DInt(new Point2DInt(20, 20), new Point2DInt(30, 30));
		Assert.assertNull(line1.getIntersection(line2));
		rect1 = new Rectangle2DInt(0, 0, 16, 20);
		Assert.assertArrayEquals(new Point2DInt[] {
			new Point2DInt(15, 15)
		}, line1.getIntersection(rect1));
		// equals
		Assert.assertFalse(line1.equals(null));
		line2 = new Line2DInt(new Point2DInt(10, 10), new Point2DInt(10, 20));
		Assert.assertFalse(line1.equals(line2));
		line2 = new Line2DInt(new Point2DInt(20, 10), new Point2DInt(20, 20));
		Assert.assertFalse(line1.equals(line2));
	}
	
}
