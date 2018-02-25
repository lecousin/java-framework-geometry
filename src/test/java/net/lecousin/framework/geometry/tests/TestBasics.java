package net.lecousin.framework.geometry.tests;

import net.lecousin.framework.geometry.HorizontalAlignment;
import net.lecousin.framework.geometry.VerticalAlignment;

import org.junit.Assert;
import org.junit.Test;

public class TestBasics {

	@Test
	public void test() {
		HorizontalAlignment h = HorizontalAlignment.CENTER;
		Assert.assertEquals(HorizontalAlignment.CENTER, h);
		VerticalAlignment v = VerticalAlignment.MIDDLE;
		Assert.assertEquals(VerticalAlignment.MIDDLE, v);
	}
	
}
