package me.dabpessoa.main;

import java.util.List;

public interface KeySequenceVerifierListener {

	void sequencePressed(String id, List<Integer> keys);
	void sequenceAtivated(String id, List<Integer> keys);
	
}
