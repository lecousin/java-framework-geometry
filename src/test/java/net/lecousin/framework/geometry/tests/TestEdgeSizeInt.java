package net.lecousin.framework.geometry.tests;

import net.lecousin.framework.geometry.EdgeSizeInt;
import net.lecousin.framework.geometry.Rectangle2DInt;

import org.junit.Assert;
import org.junit.Test;

public class TestEdgeSizeInt {

	@Test
	public void test() {
		EdgeSizeInt size = new EdgeSizeInt(1, 2, 3, 4);
		Assert.assertEquals(3, size.width());
		Assert.assertEquals(7, size.height());
		Rectangle2DInt rect = size.inside(new Rectangle2DInt(10, 20, 30, 40));
		Assert.assertEquals(11, rect.position.x);
		Assert.assertEquals(23, rect.position.y);
		Assert.assertEquals(27, rect.size.x);
		Assert.assertEquals(33, rect.size.y);
	}
	
}
