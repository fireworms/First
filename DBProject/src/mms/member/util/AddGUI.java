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
	// �⺻ GUI����
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
		name = new JLabel("�̸�");
		addr = new JLabel("�ּ�");
		nation = new JLabel("����");
		email = new JLabel("�̸���");
		age = new JLabel("����");
		
		
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
		
		confirmBtn = new JButton("Ȯ��");
		confirmBtn.addActionListener(new AddListeners());
		add("South", confirmBtn);
		
		setVisible(true);
	}
	//�� �ʵ��� ������ �޾Ƽ� Member������ ��ȯ�ϴ� �޼ҵ�
	public Member getNewMember(){
		
		return new Member(nameField.getText(), addrField.getText(), nationField.getText(), emailField.getText(), Integer.parseInt(ageField.getText()));
	}
	
	class AddListeners implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String emailCheckStr = "[^-_][\\w-_]+[^-_]@[^-_][a-z0-9-.]+[^-_.]";
			
			if(nameField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "���� �Է� ���ּ���");
				nameField.requestFocus();
			}else if(addrField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "���� �Է� ���ּ���");
				addrField.requestFocus();
			}else if(nationField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "���� �Է� ���ּ���");
				nationField.requestFocus();
			}else if(emailField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "���� �Է� ���ּ���");
				emailField.requestFocus();
			}else if(ageField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "���� �Է� ���ּ���");
				ageField.requestFocus();
			}else if(nameField.getText().length() > 12){
				JOptionPane.showMessageDialog(null, "�̸��� �ʹ� ��ϴ�");
				nameField.requestFocus();
			}else if(addrField.getText().length() > 50){
				JOptionPane.showMessageDialog(null, "�ּҰ� �ʹ� ��ϴ�");
				addrField.requestFocus();
			}else if(nationField.getText().length() > 12){
				JOptionPane.showMessageDialog(null, "�������� �ʹ� ��ϴ�");
				nationField.requestFocus();
			}else if(emailField.getText().length() > 30){
				JOptionPane.showMessageDialog(null, "�̸����� �ʹ� ��ϴ�");
				emailField.requestFocus();
			}else if(!Pattern.matches(emailCheckStr, emailField.getText())){
				JOptionPane.showMessageDialog(null, "�̸��� �������� �Է��ϼ���");
				emailField.requestFocus();
			}else if(ageField.getText().length() > 4){
				JOptionPane.showMessageDialog(null, "���ƽ��ϱ� �޸�");
				ageField.requestFocus();
			}else if(!Pattern.matches("[0-9]+", ageField.getText())){
				JOptionPane.showMessageDialog(null, "���� �������� �Է��ϼ���");
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
