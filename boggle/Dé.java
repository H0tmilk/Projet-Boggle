package boggle;

import java.util.Random;

/**
 * Composantes d'un d�
 * @author Kilian CHOLLET, Antoine AUDRIN
 * @version 1
 */

public class D� {
	// Nombre de faces
	private static final int NB_FACES = 6;
	
	// Chaque face du d� est un char (une lettre)
	private char[] faces;
	private char faceVisible;
	
	/**
	 * Constructeur de d�.
	 * @param lettres sous le format "ABCDEF" (un caract�re correspond � une face)
	 */
	public D� (String lettres) {
		this.faces = new char[NB_FACES];
		for(int i = 0; i < NB_FACES; ++i) {
			 this.faces[i] = lettres.charAt(i);
		}
		this.setFaceVisible(0);
	}
	
	/**
	 * Retourne le nombre de faces
	 * @return
	 */
	public int getNbFaces() {
		return NB_FACES;
	}

	/**
	 * Retourne les faces dans un tableau de char
	 * @return faces
	 */
	public char[] getFaces() {
		return faces;
	}
	
	/**
	 * Retourne la face visible d'un d�
	 * @return faceVisible
	 */
	public char getFaceVisible() {
		return faceVisible;
	}

	/**
	 * D�finit la face sp�cifi�e comme face visible
	 * @param i, indice de la face � d�finir comme visible
	 */
	private void setFaceVisible(int i) {
		this.faceVisible = this.faces[i];
	}
	
	/**
	 * M�lange le d� (d�finit une nouvelle face visible al�atoirement
	 */
	public void m�langer() {
		Random rand = new Random();
		int faceAlea = rand.nextInt(NB_FACES); //Entre 0 et NB_FACES-1
		this.setFaceVisible(faceAlea);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < NB_FACES; ++i) {
			str.append(faces[i]);
		}
		return str.toString();
	}
}
