package mms.member.util;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mms.member.svc.MemberListService;
import mms.member.svc.MemberRemoveService;
import mms.member.vo.Member;


public class RemoveGUI extends JFrame{
	
	JButton end, remove;
	JButton[] listButton;
	JPanel p1, p2;
	int index = 0, pageSize = 4;
	MemberListService memberListService = new MemberListService();
	ArrayList<Member> memberList = memberListService.getMemberList(index, pageSize);
	Member oldMember = null;
	String removeTarget;
	public RemoveGUI(){
		setSize(400, 400);
		setLayout(new GridLayout(0, 1));
		p1 = new JPanel();
		p1.setLayout(new GridLayout(0, 5));
		p2 = new JPanel();
		p2.setLayout(new GridLayout(0, 2));
		add(p1);
		add(p2);
		
		listButton = new JButton[memberList.size()];
		
		for(int i = 0; i < listButton.length; i++){
			listButton[i] = new JButton();
			listButton[i].setText(memberList.get(i).getName());
			listButton[i].addActionListener(new AddListeners());
			p1.add(listButton[i]);
		}
				
		end = new JButton("종료");
		end.addActionListener(new AddListeners());
		p2.add(end);
		remove = new JButton("삭제");
		remove.addActionListener(new AddListeners());
		remove.setVisible(false);
		p2.add(remove);
		
		setVisible(true);
	}
	
	class AddListeners implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton target = (JButton)arg0.getSource();
			if(target.equals(end)){dispose();}
			else if(target.getText().equals("삭제")){
				MemberRemoveService memberRemoveService = new MemberRemoveService();
				boolean removeSuccess = memberRemoveService.removeMember(removeTarget);
				dispose();
			}
			else{//일단 가져오자
				removeTarget = target.getText();
				remove.setVisible(true);
			}
		}
		
	}
}
