package JUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import boggle.Plateau;

public class PlateauTest {

	@Test
	public void testSetD�s() {
		Plateau.setD�s(
				"TUPSEL MASROI GITNEV YUNLEG "
				+ "DECPAM KEOTUN SERLAC LIREWU "
				+ "EAATOD DESTON SIHFEE NEHRIS "
				+ "TIBRAL BOQMAJ ZENVAD FIXROA"
		);
		assertEquals(Plateau.getD�s()[0].toString(), "TUPSEL");
		assertEquals(Plateau.getD�s()[9].toString(), "DESTON");
		assertEquals(Plateau.getD�s()[15].toString(), "FIXROA");
	}

	@Test
	public void testCr�erPlateauPersonnalis�() {
		Plateau.cr�erPlateauPersonnalis�("ABCD EFGH IJKL MNOP");
		assertEquals(Plateau.getPlateau()[0][0], 'A');
		assertEquals(Plateau.getPlateau()[3][0], 'M');
		assertEquals(Plateau.getPlateau()[0][3], 'D');
		assertEquals(Plateau.getPlateau()[3][3], 'P');
	}

	@Test
	public void testContient() {
		Plateau.cr�erPlateauPersonnalis�("FCET AIOC LERA IMNE");
		assertTrue(Plateau.contient("face"));
		assertFalse(Plateau.contient("AIOLi"));
		assertTrue(Plateau.contient("ail"));
		assertFalse(Plateau.contient("CAROTTE"));
		assertEquals(Plateau.getMots().size(), 240);
	}

}
