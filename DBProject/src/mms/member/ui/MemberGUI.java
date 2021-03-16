package mms.member.ui;

import mms.member.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mms.member.util.AddGUI;

class MemGUI extends JFrame{
	
	JButton add, list, modify, remove, end;
	JPanel p1;
	//����ȭ���� GUI ���� �κ�
	MemGUI(){
		setSize(400, 400);
		p1 = new JPanel();
		add("North", p1);
		
		add = new JButton("���");
		add.addActionListener(new Listeners());
		list = new JButton("���");
		list.addActionListener(new Listeners());
		modify = new JButton("����");
		modify.addActionListener(new Listeners());
		remove = new JButton("����");
		remove.addActionListener(new Listeners());
		end = new JButton("����");
		end.addActionListener(new Listeners());
		p1.add(add);
		p1.add(list);
		p1.add(modify);
		p1.add(remove);
		p1.add(end);
		
		setVisible(true);
	}
	// �׼Ǹ����� �κ�
	class Listeners implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// javascript�� �̺�ƮŸ�� �������� arg0.getSource() <- ��ȯ����  Object�� JButton���� ����ȯ ���� �� �޾� target��� JButton�� ���������� �־
			// ��ġ�ϴ� ��ư ��ü�� ���� �� ������ ���θ޴� GUI��ü�� �����ϵ��� �� 
			JButton target = (JButton)arg0.getSource();
			if(target.equals(add)) new AddGUI();//equals�� ������Ʈ���� �񱳿��� ����� �� ����
			if(target == list) new ListGUI();
			if(target == modify) new ModifyGUI();
			if(target == remove) new RemoveGUI();
			if(target == end) System.exit(0);
		}
	}
}

public class MemberGUI {

	public static void main(String[] args) {
		new MemGUI();
	}

}
