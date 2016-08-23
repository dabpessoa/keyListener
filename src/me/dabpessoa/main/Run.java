package me.dabpessoa.main;

import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JFrame;

public class Run implements KeySequenceVerifierListener {

	public static void main(String[] args) {
		
		Run run = new Run();
		
		KeySequenceVerifier sequenceVerifier = new KeySequenceVerifier();
		sequenceVerifier.addKeyVerifierListener(run);
		sequenceVerifier.registerSequence("QWE", KeyEvent.VK_Q, KeyEvent.VK_W, KeyEvent.VK_E);
		sequenceVerifier.registerSequence("ASD", KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D);
		sequenceVerifier.registerSequence("ZXC", KeyEvent.VK_Z, KeyEvent.VK_X, KeyEvent.VK_C);
		
		
		JFrame frame = new JFrame("keys");
		frame.setSize(600,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(sequenceVerifier);
        frame.setVisible(true);
		
	}

	
	
	@Override
	public void sequencePressed(String id, List<Integer> keys) {
		System.out.println("PRESSED: "+id);
	}

	@Override
	public void sequenceAtivated(String id, List<Integer> keys) {
		System.out.println("ACTIVATED: "+id);		
	}
	
}
