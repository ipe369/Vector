import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;


public class AddEnemyDialogue extends JFrame implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	JButton b = new JButton("Create");
	JList<String> list;
	Class<? extends Enemy> selectedEnemy;
	Point clickPoint;
	public AddEnemyDialogue(Point _clickPoint) throws HeadlessException 
	{
		clickPoint = _clickPoint;
		String[] enemies = new String[]{"Fighter", "Burst Turret"};
		setSize(100,250);
		list = new JList<String>(enemies);
		getContentPane().add(list);
		getContentPane().add(b,BorderLayout.SOUTH);
		b.addActionListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == b && list.getSelectedValue() != null)
		{
			String s = (String) list.getSelectedValue();

			if (s.compareTo("Fighter") == 0)
			{
				Point pos = VLD.d.calculateNewPointWithZoom(new Point(clickPoint.x,clickPoint.y));
				VLD.d.updateList.add(new Fighter(pos.x,pos.y));
				setVisible(false);
				dispose();
			}
			if (s.compareTo("Burst Turret") == 0)
			{
				new AddBurstTurretDialogue(clickPoint);
				setVisible(false);
				dispose();
			}
		}
	}
}
