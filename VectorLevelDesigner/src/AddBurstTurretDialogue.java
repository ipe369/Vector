import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddBurstTurretDialogue extends JFrame implements ActionListener 
{
	Point pointToAdd;
	private JTextField offsetTiming = new JTextField();
	private JButton add = new JButton("Add Burst Turret");
	public AddBurstTurretDialogue(Point _pointToAdd)
	{
		pointToAdd = _pointToAdd;
		add.addActionListener(this);
		
		setSize(250,120);
		setResizable(false);
		getContentPane().add(new JLabel("Adding burst turret; choose offset timing."), BorderLayout.NORTH);
		getContentPane().add(offsetTiming);
		getContentPane().add(add,BorderLayout.SOUTH);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == add)
		{
			if (offsetTiming.getText().isEmpty())
			{
				VLD.d.updateList.add(new BurstTurret(pointToAdd.x,pointToAdd.y));
				setVisible(false);
				dispose();
			}
			else
			{
				VLD.d.updateList.add(new BurstTurret(pointToAdd.x,pointToAdd.y, Integer.parseInt(offsetTiming.getText())));
				setVisible(false);
				dispose();
			}
		}
	}
}
