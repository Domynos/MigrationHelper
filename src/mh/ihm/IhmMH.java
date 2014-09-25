package mh.ihm;

import javax.swing.*;

public class IhmMH{

	public IhmMH(){
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				IhmMHWindowIndex fenetre = new IhmMHWindowIndex();
				fenetre.setVisible(true);
			}
		});
	}

}
