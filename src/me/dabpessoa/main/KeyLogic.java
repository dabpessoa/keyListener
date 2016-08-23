package me.dabpessoa.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class KeyLogic {

	private static Map<Integer, Boolean> keysPressedMap;
	static {
		keysPressedMap = new HashMap<Integer, Boolean>();
	}
	
	public static boolean isSequencePressed(List<Integer> keys) {
		Integer[] as = new Integer[keys.size()];
		as = keys.toArray(as);
		return verifyKeysPressed(as);
	}
	
	public static boolean isSequencePressed(Integer... keys) {
		return verifyKeysPressed(keys);
	}
	
	public static boolean isSequenceActivated(List<Integer> keys) {
		Integer[] as = new Integer[keys.size()];
		as = keys.toArray(as);
		return verifyKeysPressedOnly(as);
	}
	
	public static boolean isSequenceActivated(Integer... keys) {
		return verifyKeysPressedOnly(keys);
	}
	
	/*
	 * Retorna verdadeiro se e somente se as teclas passadas por par�metro corresponderem
	 * exatamente �s teclas apertadas.
	 */
	private static boolean verifyKeysPressedOnly(Integer... keys) {
		int pertenceCount = 0;
		if (keys != null) {
			for (Entry<Integer, Boolean> entry : keysPressedMap.entrySet()) {
				Integer key = entry.getKey();
				Boolean value = entry.getValue();
				if (value == true) {
					boolean pertence = false;
					for (int i = 0 ; i < keys.length ; i++) {
						if (key == keys[i]) {
							pertence = true;
							pertenceCount++;
						}
					}
					if (!pertence) return false;
				}
			}
		} 
		if (pertenceCount == keys.length) return true;
		else return false;
	}
	
	/*
	 * Retorna verdadeiro se as teclas apertadas estiverem dentro do conjunto das teclas passadas por par�metro.
	 */
	private static boolean verifyKeysPressed(Integer... keys) {
		if (keys != null) {
			for (int i = 0 ; i < keys.length ; i++) {
				Integer key = keys[i];
				if (key == null || keysPressedMap.get(key) == null || !keysPressedMap.get(key)) {
					return false;
				}
			}
		} return true;
	}
	
	protected static Map<Integer, Boolean> getKeysPressedMap() {
		return keysPressedMap;
	}
	
}
