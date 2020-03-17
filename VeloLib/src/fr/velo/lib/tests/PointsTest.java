package fr.velo.lib.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.velo.lib.utils.Point3D;
import fr.velo.lib.utils.Point4D;

public class PointsTest {

	@Test
	public void checkPoints3DCreation() {
		Point3D p3d = new Point3D(1.5, 2.0, 9);
		assertTrue(p3d.getX() == 1.5);
		assertTrue(p3d.getY() == 2.0);
		assertTrue(p3d.getZ() == 9.0);
	}

	@Test
	public void checkPoints4DCreation() {
		Point4D p4d = new Point4D(1.5, 2.0, 9.0, 1.5);
		assertTrue(p4d.getX() == 1.5);
		assertTrue(p4d.getY() == 2.0);
		assertTrue(p4d.getZ() == 9.0);
		assertTrue(p4d.getT() == 1.5);
	}

	@Test
	public void points3DModification() {
		Point3D p3d = new Point3D(1, 1, 1);
		p3d.setX(2);
		p3d.setY(9.4);
		p3d.setZ(90.548);
		
		assertTrue(p3d.getX() == 2);
		assertTrue(p3d.getY() == 9.4);
		assertTrue(p3d.getZ() == 90.548);
	}


	@Test
	public void points4DModification() {
		Point4D p4d = new Point4D(1, 1, 1, 1);
		p4d.setX(2);
		p4d.setY(9.4);
		p4d.setZ(90.548);
		p4d.setT(948);
		
		assertTrue(p4d.getX() == 2);
		assertTrue(p4d.getY() == 9.4);
		assertTrue(p4d.getZ() == 90.548);
		assertTrue(p4d.getT() == 948);
	}

	@Test
	public void points3DToString() {
		Point3D p3d = new Point3D(1, 1.5, 2);
		assert(p3d.toString().equals("1,000000+1,500000+2,000000"));
	}
	
	@Test
	public void points4DToString() {
		Point4D p4d = new Point4D(1, 1.5, 2, 2.5);
		assert(p4d.toString().equals("1,000000+1,500000+2,000000+2,500000"));
	}
	
}
