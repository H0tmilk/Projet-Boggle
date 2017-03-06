package JUnit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import boggle.D�;

public class D�Test {

	@Test
	public void testSetFaces() {
		D� d� = new D�("ABCDEF");
		System.out.println(d�.toString());
		assertTrue(d�.toString().equals("ABCDEF"));
	}

	@Test
	public void testM�langer() {
		D� d� = new D�("ABCDEF");
		boolean m�lange = false;
		for(int i = 0; i < 50; ++i) {
			d�.m�langer();
			if(d�.getFaceVisible() == 'C') {
				m�lange = true;
			}
		}
		assertTrue(m�lange);
	}
}
