package mms.member.util;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mms.member.svc.MemberListService;
import mms.member.svc.MemberModifyService;
import mms.member.vo.Member;


public class ModifyGUI extends JFrame{
	
	JButton end, modify;
	JButton[] listButton;
	JTextField nationField, addrField, emailField, ageField;
	JPanel p1, p2;
	JLabel nation, addr, email, age;
	int index = 0, pageSize = 4;
	MemberListService memberListService = new MemberListService();
	ArrayList<Member> memberList = memberListService.getMemberList(index, pageSize);
	Member oldMember = null;
	public ModifyGUI(){
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
		
		nation = new JLabel("국가");
		addr = new JLabel("주소");
		email = new JLabel("이메일");
		age = new JLabel("나이");
		
		nationField = new JTextField();
		addrField = new JTextField();
		emailField = new JTextField();
		ageField = new JTextField();
		p2.add(nation);
		p2.add(nationField);
		p2.add(addr);
		p2.add(addrField);
		p2.add(email);
		p2.add(emailField);
		p2.add(age);
		p2.add(ageField);
		
		
		end = new JButton("종료");
		end.addActionListener(new AddListeners());
		p2.add(end);
		modify = new JButton("수정");
		modify.addActionListener(new AddListeners());
		modify.setVisible(false);
		p2.add(modify);
		
		setVisible(true);
	}
	
	class AddListeners implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton target = (JButton)arg0.getSource();
			if(target.equals(end)){
				dispose();}
			else if(target.getText().equals("수정")){
				/*수정*/
				MemberModifyService memberModifyService = new MemberModifyService();
				Member updateMember = new Member(oldMember.getName(), nationField.getText(), addrField.getText(), emailField.getText(), Integer.parseInt(ageField.getText()));
				boolean isModifySuccess = memberModifyService.modifyMember(updateMember);
				dispose();
			}
			else{//일단 가져오자
				modify.setVisible(true);
				for(int i = 0; i < memberList.size(); i++){
					if(memberList.get(i).getName().equals(target.getText())){
						oldMember = memberList.get(i);
					}
				}
				nationField.setText(oldMember.getNation());
				addrField.setText(oldMember.getAddr());
				emailField.setText(oldMember.getEmail());
				ageField.setText(Integer.toString(oldMember.getAge()));
			}
		}
		
	}
}
