package net.mbiz.library.ui.common;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyCalenderDialog extends JDialog implements ActionListener, MouseListener{

	private JPanel pnTop; 
	private JPanel pnCnt; 
	private JPanel pnBottom; 

	// in pnTop
	private JPanel pnYM; 
	private JPanel pnYear; 
	private JPanel pnMonth; 
	
	// in pnCnt
	private JPanel pnWeek; 
	private JPanel pnDate; 
	
	private JButton prevBtn; 
	private JButton nextBtn; 
	private JButton pickBtn; 
	
	private JLabel lblYear;
	private JLabel lblMonth;

	private JComboBox<Integer> cbbYear;
	private JComboBox<Integer> cbbMonth;
	private DefaultComboBoxModel<Integer> yearModel;
	private DefaultComboBoxModel<Integer> monthModel;
	
	private String selectDay = "";
	private String selectMonth = "";
	private String selectYear = "";
	
	
	Calendar now = null;
	int year = 0;
	int month = 0;
	int day = 0;

	String arrWeek[] = { "일", "월", "화", "수", "목", "금", "토" };

	// 선택된 날짜.
	public static String rsltDate = "";
	
	public MyCalenderDialog() {
		jbInit();
	}

	/**
	 * 기본 UI Init.
	 */
	private void jbInit() {
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setSize(new Dimension(400,350));
//		setDefaultCloseOperation(); //자원 해제 후 종료??
		setTitle("날짜를 선택하세요.");
		setModal(true);
		
		this.pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		pnTop.setPreferredSize(new Dimension(0,40));
		pnTop.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		pnTop.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		
		this.pnCnt = new JPanel();
		pnCnt.setLayout(new BorderLayout());
		pnCnt.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		this.pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		pnBottom.setPreferredSize(new Dimension(0,40));
		pnBottom.setBorder(BorderFactory.createEmptyBorder(5,5,10,5));
		pnBottom.setBackground(Color.blue);
		this.pickBtn = new JButton("확인");
		pickBtn.setFont(CommonConstants.FONT_BASE_15);
		pickBtn.setPreferredSize(new Dimension(20,0));
		
		pnBottom.add(pickBtn, BorderLayout.EAST);
		
		
		
		/*pnTop*/
		this.pnYM = new JPanel();
		pnYM.setLayout(new BorderLayout());
		pnYM.setPreferredSize(new Dimension(450,0));
		pnYM.setBorder(BorderFactory.createEmptyBorder(0,50,0,50));
		pnYM.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		this.pnYear = new JPanel();
		pnYear.setLayout(new BorderLayout());
		pnYear.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		this.lblYear = new JLabel("년");
		lblYear.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		this.pnMonth = new JPanel();
		pnMonth.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		pnMonth.setLayout(new BorderLayout());
		this.lblMonth = new JLabel("월");
		lblMonth.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		
		this.cbbYear = new JComboBox<Integer>();
		cbbYear.setPreferredSize(new Dimension(70,0));
		this.cbbMonth = new JComboBox<Integer>();
		cbbMonth.setPreferredSize(new Dimension(70,0));
		
		this.prevBtn = new JButton("◀");
		prevBtn.setPreferredSize(new Dimension(20,0));
		this.nextBtn = new JButton("▶");
		prevBtn.setPreferredSize(new Dimension(20,0));
		
		

		
		pnYear.add(cbbYear, BorderLayout.WEST);
		pnYear.add(lblYear, BorderLayout.EAST);
		pnMonth.add(cbbMonth, BorderLayout.WEST);
		pnMonth.add(lblMonth, BorderLayout.EAST);
		pnYM.add(pnYear, BorderLayout.WEST);
		pnYM.add(pnMonth, BorderLayout.EAST);

		pnTop.add(pnYM, BorderLayout.CENTER);
		pnTop.add(prevBtn, BorderLayout.WEST);
		pnTop.add(nextBtn, BorderLayout.EAST);
		
		monthYearInit();

		
		
		/*pnCnt*/
		this.pnWeek = new JPanel();
		pnWeek.setLayout(new GridLayout(1,7));
		this.pnDate = new JPanel();
		pnDate.setLayout(new GridLayout(0,7));
		
		pnCnt.add(pnWeek, BorderLayout.NORTH);
		pnCnt.add(pnDate, BorderLayout.CENTER);
		
		
		
		this.add(pnTop, BorderLayout.NORTH);
		this.add(pnCnt, BorderLayout.CENTER);
		this.add(pnBottom, BorderLayout.SOUTH);
		

		
		
		for (int i = 0; i < arrWeek.length; i++) {
			JLabel lbl = new JLabel(arrWeek[i], JLabel.CENTER);
			if (i == 0) {
				lbl.setForeground(Color.red);
			} else if (i == 6) {
				lbl.setForeground(Color.blue);
			}
			pnWeek.add(lbl);
		}
		
		// 날짜 출력
		dayPrint(year, month);
		
		
		prevBtn.addActionListener(this);
		nextBtn.addActionListener(this);
//		pickBtn.addActionListener(this);
//		cbbYear.addActionListener(this);
//		cbbMonth.addActionListener(this);
		
		
		/*콤보박스 선택 이벤트*/
		cbbMonth.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectMonth = cbbMonth.getSelectedItem().toString();
				System.out.println("콤보박스 선택!!! " + selectMonth);
			}
		});
		
		cbbYear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectYear = cbbYear.getSelectedItem().toString();
				System.out.println("콤보박스 선택!!! " + selectYear);
			}
		});
		
		pickBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!selectDay.equals("") || !selectMonth.equals("") || !selectYear.equals("")) {
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "날짜를 선택해주세요.", "", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
	}

	/**
	 * 일자를 계산하여 출력하는 메서드.
	 * @param y year
	 * @param m month
	 */
	private void dayPrint(int y, int m) {
		Calendar cal = Calendar.getInstance();
		cal.set(y, m - 1, 1);  // 출력할 첫날의 객체.
		
		int week = cal.get(Calendar.DAY_OF_WEEK); // 1일에 대한 요일
		int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); //그 달의 마지막 날.
		
		// 첫날 출력 전까지의 공백 출력.
		for (int i = 1; i < week; i++) {
			pnDate.add(new JLabel(" "));
		}
		
		// 1~마지막 날짜까지 라벨 생성.
		for (int i = 1; i < lastDate; i++) {
			JPanel pnDay = new JPanel();
			pnDay.setLayout(new CardLayout());
			
			JButton dayBtn = new JButton(String.valueOf(i));
			dayBtn.setBorder(BorderFactory.createEmptyBorder());
			dayBtn.setBackground(CommonConstants.COLOR_BASE_BACKGROUND);
			int outWeek = cal.get(Calendar.DAY_OF_WEEK); 
		
			if (outWeek == 1) {
				dayBtn.setForeground(Color.red);
			} else if(outWeek == 7) {
				dayBtn.setForeground(Color.blue);
			}
			
			pnDate.add(pnDay);
			pnDay.add(dayBtn);
			dayBtn.addMouseListener(this);
		}

	}

	/**
	 * 현재 날짜를 기준으로 날짜 데이터를 setting하는 메서드. 
	 */
	private void monthYearInit() {
		
		/* int field.
		 * Calendar.YEAR : 1
		 * Calendar.MONTH : 2
		 * Calendar.DATE : 5
		 * 
		 * Calendar.get(int field) :전달된 Calendar 필드에 저장된 값을 반환.
		 *  -> Calendar.get(Calendar.YEAR): 현재 날짜의 년도를 반환.
		 *  	- month의 경우 0부터 시작하므로 +1 해주어야 함.
		 * */
		
		now = Calendar.getInstance();
		year = now.get(Calendar.YEAR);
		month = now.get(Calendar.MONTH) + 1;
		day = now.get(Calendar.DATE);
		
		this.yearModel = new DefaultComboBoxModel<>();
		this.monthModel = new DefaultComboBoxModel<>();
		
		// (현재 년도-100)년 부터 현재 년도까지 모델에 추가. 
		for (int i = year - 100; i <= year; i++) {
			yearModel.addElement(i);
		}
		
		// 1-12월 모델에 추가
		for (int i = 1; i <=12; i++) {
			monthModel.addElement(i);
		}
		
		cbbYear.setModel(yearModel);
		cbbYear.setSelectedItem(year);    // 현재 년도 선택
		cbbMonth.setModel(monthModel);
		cbbMonth.setSelectedItem(month);  // 현재 월 선택
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/* 이벤트 객체가 버튼인 경우 */
		if (e.getSource() instanceof JButton) {
			JButton eventBtn = (JButton) e.getSource();
			int yy = (Integer) cbbYear.getSelectedItem();
			int mm = (Integer) cbbMonth.getSelectedItem();
			
			// 이전/다음 버튼 Click 
			if (eventBtn.equals(prevBtn)) {
				if (mm == 1) { // 1월에서 이전버튼 --> 12월
					yy--;
					mm = 12;
				} else {
					mm--;
				}
			} else if(eventBtn.equals(nextBtn)) {
				if (mm == 12) {
					yy++;
					mm = 1;
				} else {
					mm++;
				}
			}
			cbbYear.setSelectedItem(yy);
			cbbMonth.setSelectedItem(mm);
		
		/* 이벤트 객체가 콤보 박스인 경우 */	
		} else if (e.getSource() instanceof JComboBox) {
			createDayStart();
		}
	}

	/**
	 * 선택된 월에 해당하는 날짜 패널을 보여주는 메서드.
	 */
	private void createDayStart() {
		pnDate.setVisible(false); //날짜 패널 숨김
		
		pnDate.removeAll();
		dayPrint((Integer)cbbYear.getSelectedItem(), (Integer)cbbMonth.getSelectedItem()); //(day, month)
		
		pnDate.setVisible(true); // 다시 보이게
	}
	
	public void setLocationCenter() {
		Dimension d = this.getToolkit().getScreenSize(); 
		this.setLocation((int) d.getWidth() / 2 - this.getWidth() / 2, (int) d.getHeight() / 2 - this.getHeight() / 2);
		this.setVisible(true);
	}

	
	/**
	 * 선택한 날짜를 문자열로 반환하는 메서드.
	 * @param day
	 * @return
	 */
	private String addStrDate(String day) {
		
		if (selectYear.equals("") || selectYear.isEmpty()) {
			selectYear = Integer.toString(year);
		}
		if (selectMonth.equals("") || selectMonth.isEmpty()) {
			selectMonth = Integer.toString(month);
			if (selectMonth.length() < 2) {
				selectMonth = "0" + selectMonth;
			}
		}
		
		
		rsltDate = selectYear + "." + selectMonth + "." + day;

		System.out.println("여기는 getSelectedDate ----> 선택한 날짜는?" + rsltDate);
		return rsltDate;
	}
	

//---------------------MouseListener Override------------------------
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof JButton) {

			JButton eventBtn = (JButton) e.getSource(); 
			eventBtn.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
			
			selectDay = eventBtn.getText();
			addStrDate(selectDay);
		} 
	}

	// 마우스가 컴포넌트 위에 올라갈 때
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton eventBtn =  (JButton) e.getSource(); 
			eventBtn.setBackground(CommonConstants.COLOR_CONTENT_BACKGROUND);
		}
		
	}
	// 마우스가 컴포넌트 위에서 내려갈 때
	public void mouseExited(MouseEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton eventBtn =  (JButton) e.getSource(); 
			eventBtn.setBackground(CommonConstants.COLOR_BASE_BACKGROUND);
		}
	}
	
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}

	
	public String getDay(JLabel lbl) {
		selectDay = lbl.getText();
		return selectDay;
	}
}
