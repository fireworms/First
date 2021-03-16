package mms.member.util;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mms.member.svc.MemberListService;
import mms.member.vo.Member;


public class ListGUI extends JFrame{
	
	JButton exit, leftList, rightList;
	
	JPanel topPanel, botPanel;
	JComboBox<Integer> pageSizeBox;
	int index = 0, pageSize = 3;
	public ListGUI(){
		setSize(800, 400);
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(0, 1));
		add("North", topPanel);
		botPanel = new JPanel();
		add("South", botPanel);
		
		pageSizeBox = new JComboBox<Integer>();
		pageSizeBox.addItem(1);
		pageSizeBox.addItem(3);
		pageSizeBox.addItem(5);
		pageSizeBox.addItem(10);
		pageSizeBox.setSelectedIndex(1);
		pageSizeBox.addItemListener(new ComboBoxListener());
		botPanel.add(pageSizeBox);
		leftList = new JButton("<<");
		leftList.addActionListener(new BtnListeners());
		botPanel.add(leftList);
		exit = new JButton("Á¾·á");
		exit.addActionListener(new BtnListeners());
		botPanel.add(exit);
		rightList = new JButton(">>");
		rightList.addActionListener(new BtnListeners());
		botPanel.add(rightList);
		
		printList(index, pageSize);
		
		
		setVisible(true);
	}
	
	public void printList(int index, int pageSize){
		MemberListService memberListService = new MemberListService();
		ArrayList<Member> memberList = memberListService.getMemberList(index, pageSize);
		JLabel[] memList = new JLabel[memberList.size()];
		topPanel.removeAll();
		for(int i = 0; i < memList.length; i++){
			memList[i] = new JLabel();
			memList[i].setText(memberList.get(i).toString());
			topPanel.add(memList[i]);
		}
	}
	
	class ComboBoxListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			JComboBox<Integer> target = (JComboBox)e.getSource();
			pageSize = (int)target.getSelectedItem();
			index = 0;
			printList(index, pageSize);
			revalidate();
		}
		
	}
	
	class BtnListeners implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton target = (JButton)arg0.getSource();
			if(target == exit){ dispose(); }
			if(target == leftList){
				if(index > 0) {index--;}
				printList(index, pageSize);
				revalidate();
			}
			if(target == rightList){
				if(topPanel.getComponentCount() == pageSize){
					index++;
					printList(index, pageSize);
					revalidate();
				}
			}
		}
		
	}
}
