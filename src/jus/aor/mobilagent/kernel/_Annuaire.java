package jus.aor.mobilagent.kernel;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */

/**
 * Définit un annuaire téléphonique élémentaire permettant, étant donnée un
 * abonné, d'obtenir son numéro de téléphone.
 * 
 * @author Morat
 */
public interface _Annuaire {
	/**
	 * restitue le numéro de téléphone de l'abonné
	 * 
	 * @param abonne
	 *            l'abonné
	 * @return le numéro de télephone de l'abonné
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public Numero get(String abonne) throws ParserConfigurationException, SAXException, IOException;
}