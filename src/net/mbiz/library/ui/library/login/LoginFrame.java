package net.mbiz.library.ui.library.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.mbiz.library.ui.main.LibraryMain;

public class LoginFrame extends JFrame{
	
	private JPanel pnMain;
	private JPanel idPnl;
	private JPanel pwPnl;
	private JPanel idPwSet;
	private JPanel btnPnl;

	private JLabel idLabel;
	private JLabel pwLabel;

	private JTextField idTf;
	private JPasswordField pwFd;
	
	private JButton loginBtn;
	
	
	Font font = new Font("SanSerif",Font.BOLD, 20);
	
	public LoginFrame() {
		jbInit();
	}
	
	/*
	 *  기본 UI Init
	 */
	private void jbInit() {
		
		getContentPane().setLayout(new BorderLayout());
		setSize(710, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 화면 중앙 설정
		
		/*아이디 패널 생섣*/
		this.idPnl = new JPanel();
		this.idLabel = new JLabel("ID", JLabel.RIGHT);
		this.idTf = new JTextField();
		
		idLabel.setFont(font);
		idLabel.setForeground(Color.BLACK);
		idLabel.setPreferredSize(new Dimension(50, 50));
		idTf.setPreferredSize(new Dimension(300, 50));
		
		idPnl.add(idLabel);
		idPnl.add(idTf);
		
		
		/*비밀번호 패널 생성*/
		this.pwPnl = new JPanel();
		this.pwLabel = new JLabel("PW", JLabel.RIGHT);
		this.pwFd = new JPasswordField();
		pwLabel.setFont(font);
		pwLabel.setPreferredSize(new Dimension(50, 50)); // width, height
		pwFd.setPreferredSize(new Dimension(300, 50));

		pwPnl.add(pwLabel);
		pwPnl.add(pwFd);
		
		/*아이디 & 비밀번호 영역*/
		this.idPwSet = new JPanel();
		idPwSet.setPreferredSize(new Dimension(300, 50));
		idPwSet.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); // 가운데 정렬, 수평 여백, 세로 여백
		idPwSet.add(idPnl);
		idPwSet.add(pwPnl);
		
		/*로그인 버튼*/
		this.btnPnl = new JPanel();
		this.loginBtn = new JButton("Login");
		loginBtn.setPreferredSize(new Dimension(100,100));
		/*버튼 클릭*/
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String userId = "1";
				String userPw = "1";
				
				if (userId.equals(idTf.getText()) && userPw.equals(pwFd.getText())) {
					dispose();
					// 메인 창 켜기
					LibraryMain main = new LibraryMain();
					main.setLocationCenter();
				} else {
					JOptionPane.showMessageDialog(loginBtn, "you Failed to Login");
				}
			}
		});
		
		btnPnl.add(loginBtn);
		
		
		// 메인 패널 보더 레이아웃 설정
		this.pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		pnMain.add(idPwSet, BorderLayout.CENTER);
		pnMain.add(btnPnl, BorderLayout.EAST);
		
		getContentPane().add(pnMain, BorderLayout.CENTER); //프레임에 추가
		pnMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setVisible(true); //화면에 보이도록
	}
	
//	public void paint(Graphics g) {
//		g.drawImage(bookImg, 0, 0, getWidth(), getHeight(), this);
//	}
	
	
	public static void main(String[] args) {
		new LoginFrame();
	}

}
