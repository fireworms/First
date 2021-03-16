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
	//메인화면의 GUI 구성 부분
	MemGUI(){
		setSize(400, 400);
		p1 = new JPanel();
		add("North", p1);
		
		add = new JButton("등록");
		add.addActionListener(new Listeners());
		list = new JButton("목록");
		list.addActionListener(new Listeners());
		modify = new JButton("수정");
		modify.addActionListener(new Listeners());
		remove = new JButton("삭제");
		remove.addActionListener(new Listeners());
		end = new JButton("종료");
		end.addActionListener(new Listeners());
		p1.add(add);
		p1.add(list);
		p1.add(modify);
		p1.add(remove);
		p1.add(end);
		
		setVisible(true);
	}
	// 액션리스너 부분
	class Listeners implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// javascript의 이벤트타겟 느낌으로 arg0.getSource() <- 반환값이  Object라서 JButton으로 형변환 해준 걸 받아 target라는 JButton형 참조변수에 넣어서
			// 일치하는 버튼 객체가 나올 때 각각의 세부메뉴 GUI객체를 생성하도록 함 
			JButton target = (JButton)arg0.getSource();
			if(target.equals(add)) new AddGUI();//equals는 오브젝트간의 비교에도 사용할 수 있음
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
