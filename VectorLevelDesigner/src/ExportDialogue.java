import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class ExportDialogue extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	JTextField name = new JTextField();
	JButton OK = new JButton("Ok");
	public ExportDialogue()
	{
		super("Export");
		setSize(300,200);
		setResizable(false);
		setVisible(true);
		getContentPane().add(new JLabel("Name"));
		getContentPane().add(name);
		getContentPane().add(OK,BorderLayout.SOUTH);
		OK.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == OK && name.getText().compareTo("") != 0)
		{
			FileHandler.export(name.getText());
		}
	}
}
