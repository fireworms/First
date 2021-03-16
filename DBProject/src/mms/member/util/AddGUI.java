package mms.member.util;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mms.member.svc.MemberAddService;
import mms.member.vo.Member;


public class AddGUI extends JFrame{
	
	JButton confirmBtn;
	JLabel name, nation, addr, email, age;
	JTextField nameField, addrField, nationField, emailField, ageField;
	JPanel p1;
	// 기본 GUI구성
	public AddGUI(){
		setSize(400, 400);
		p1 = new JPanel();
		p1.setLayout(new GridLayout(0, 2));
		add("North", p1);
		
		nameField = new JTextField();
		addrField = new JTextField();
		nationField = new JTextField();
		emailField = new JTextField();
		ageField = new JTextField();
		name = new JLabel("이름");
		addr = new JLabel("주소");
		nation = new JLabel("국가");
		email = new JLabel("이메일");
		age = new JLabel("나이");
		
		
		p1.add(name);
		p1.add(nameField);
		p1.add(addr);
		p1.add(addrField);
		p1.add(nation);
		p1.add(nationField);
		p1.add(email);
		p1.add(emailField);
		p1.add(age);
		p1.add(ageField);
		
		confirmBtn = new JButton("확인");
		confirmBtn.addActionListener(new AddListeners());
		add("South", confirmBtn);
		
		setVisible(true);
	}
	//각 필드의 내용을 받아서 Member변수로 반환하는 메소드
	public Member getNewMember(){
		
		return new Member(nameField.getText(), addrField.getText(), nationField.getText(), emailField.getText(), Integer.parseInt(ageField.getText()));
	}
	
	class AddListeners implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String emailCheckStr = "[^-_][\\w-_]+[^-_]@[^-_][a-z0-9-.]+[^-_.]";
			
			if(nameField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "값을 입력 해주세요");
				nameField.requestFocus();
			}else if(addrField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "값을 입력 해주세요");
				addrField.requestFocus();
			}else if(nationField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "값을 입력 해주세요");
				nationField.requestFocus();
			}else if(emailField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "값을 입력 해주세요");
				emailField.requestFocus();
			}else if(ageField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "값을 입력 해주세요");
				ageField.requestFocus();
			}else if(nameField.getText().length() > 12){
				JOptionPane.showMessageDialog(null, "이름이 너무 깁니다");
				nameField.requestFocus();
			}else if(addrField.getText().length() > 50){
				JOptionPane.showMessageDialog(null, "주소가 너무 깁니다");
				addrField.requestFocus();
			}else if(nationField.getText().length() > 12){
				JOptionPane.showMessageDialog(null, "국가명이 너무 깁니다");
				nationField.requestFocus();
			}else if(emailField.getText().length() > 30){
				JOptionPane.showMessageDialog(null, "이메일이 너무 깁니다");
				emailField.requestFocus();
			}else if(!Pattern.matches(emailCheckStr, emailField.getText())){
				JOptionPane.showMessageDialog(null, "이메일 형식으로 입력하세요");
				emailField.requestFocus();
			}else if(ageField.getText().length() > 4){
				JOptionPane.showMessageDialog(null, "미쳤습니까 휴먼");
				ageField.requestFocus();
			}else if(!Pattern.matches("[0-9]+", ageField.getText())){
				JOptionPane.showMessageDialog(null, "정수 형식으로 입력하세요");
				ageField.requestFocus();
			}else {
				Member newMember = getNewMember();
				MemberAddService memberAddService = new MemberAddService();
				try {
					memberAddService.addMember(newMember);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
