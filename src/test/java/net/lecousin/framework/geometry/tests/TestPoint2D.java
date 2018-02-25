package net.lecousin.framework.geometry.tests;

import net.lecousin.framework.geometry.Point2DInt;

import org.junit.Assert;
import org.junit.Test;

public class TestPoint2D {

	@Test
	public void test() {
		Point2DInt pt1 = new Point2DInt(10, 100);
		// copy pt1
		Point2DInt pt2 = new Point2DInt(pt1);
		Assert.assertTrue(pt2.equals(pt1));
		Assert.assertFalse(pt2.equals(null));
		Assert.assertFalse(pt2.equals(new Object()));
		Assert.assertTrue(pt2.hashCode() == pt1.hashCode());
		Assert.assertEquals(pt1.x, pt2.getX());
		Assert.assertEquals(pt1.y, pt2.getY());
		Assert.assertEquals(0d, pt2.getDistance(pt1), 0d);
		// translate
		pt2 = pt2.translate(new Point2DInt(10, 20));
		Assert.assertFalse(pt2.equals(pt1));
		Assert.assertEquals(20, pt2.getX());
		Assert.assertEquals(120, pt2.getY());
		Assert.assertEquals(22.36068d, pt2.getDistance(pt1), 0.0001d);
		// translate negative
		pt2 = pt2.translate(new Point2DInt(-100, -200));
		Assert.assertEquals(-80, pt2.getX());
		Assert.assertEquals(-80, pt2.getY());
		Assert.assertEquals(201.24612d, pt2.getDistance(pt1), 0.0001d);
		// translate
		pt2 = pt2.translate(new Point2DInt(90, -10));
		Assert.assertFalse(pt2.equals(pt1));
		pt2 = pt2.translate(new Point2DInt(0, 190));
		Assert.assertEquals(pt2, pt1);
		// coverage
		pt2.toString();
	}
	
}
