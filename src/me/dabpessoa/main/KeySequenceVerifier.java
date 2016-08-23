package me.dabpessoa.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeySequenceVerifier implements KeyListener {

	private List<KeySequenceVerifierListener> keyVerifierListeners;
	private Map<String, List<Integer>> registeredSequences;
	private Integer activateKey;
	
	public KeySequenceVerifier() {
		this.keyVerifierListeners = new ArrayList<KeySequenceVerifierListener>();
		this.registeredSequences = new HashMap<String, List<Integer>>();
		this.activateKey = KeyEvent.VK_ENTER; // Default Activate Key.
	}
	
	public void setActivateKey(Integer activateKey) {
		this.activateKey = activateKey;
	}
	
	public void registerSequence(String id, Integer... keys) {
		registeredSequences.put(id, Arrays.asList(keys));
	}
	
	public void addKeyVerifierListener(KeySequenceVerifierListener listener) {
		keyVerifierListeners.add(listener);
	}
	
	public void removeKeyVerifierListener(KeySequenceVerifierListener listener) {
		keyVerifierListeners.remove(listener);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		KeyLogic.getKeysPressedMap().put(e.getKeyCode(), true);
		verify();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		KeyLogic.getKeysPressedMap().put(e.getKeyCode(), false);
	}
	
	private void verify() {
		for (Map.Entry<String,List<Integer>> entry  : registeredSequences.entrySet()) {
			
			List<Integer> activatedKeyList = new ArrayList<Integer>();
			activatedKeyList.addAll(entry.getValue());
			activatedKeyList.add(activateKey);
			if (KeyLogic.isSequenceActivated(activatedKeyList)) {
				adviseActivatedListeners(entry.getKey(), entry.getValue());
				break;
			}
			
			if (KeyLogic.isSequencePressed(entry.getValue())) {
				advisePressedListeners(entry.getKey(), entry.getValue());
				break;
			}
			
		}
	}
	
	public void advisePressedListeners(String id, List<Integer> keys) {
		for (KeySequenceVerifierListener keySequenceVerifierListener : keyVerifierListeners) {
			keySequenceVerifierListener.sequencePressed(id, keys);
		}
	}
	
	public void adviseActivatedListeners(String id, List<Integer> keys) {
		for (KeySequenceVerifierListener keySequenceVerifierListener : keyVerifierListeners) {
			keySequenceVerifierListener.sequenceAtivated(id, keys);
		}
	}

}
