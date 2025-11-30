package MainGUI;
// 사이드바 참조.
import AlarM.Alarm_main;
import Memo.MeMo;
import CalendeR22.Calenders;
import ChaT.Chatting;
import ShoP.Shopping;
import TimetablE.Time_Table;
// 색바꾸기 참조
import static Memo.MeMo.*;
import static CalendeR22.Calenders.*;
import static TimetablE.Time_Table.*;
import static AlarM.TimeGui.*;          // inputPanel1, inputPanel2
import static CalendeR22.Cal_main_GUI.*;  //horizontalBox, panel
import static CalendeR22.F_main_GUI.*;  //horizontalBox2, panel2
import static CalendeR22.C_main_GUI.*;  //horizontalBox3, panel3
import static ChaT.ChatClient.*;        // timetablePanel
import static TimetablE.TimetableApp.timetablePanel;
// 기타 기능 가져오기.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainGui extends JFrame{
    private static final int NUM_MENU_BUTTONS = 6;  // 숫자 5 고정
    private String textName = "홍길동";
    private JButton[] menuNav = new JButton[NUM_MENU_BUTTONS];;// 버튼 menuNav 배열 (menuNav페널에 넣을 버튼 배열)
    private boolean tema_original = true;           // 기본 테마  유무 기본값 true
    private boolean tema1 = false;                  // 테마 1 유무 기본값 false
    private JButton menuMemo = new JButton("포스트잇");
    private JButton menuAlarm = new JButton("알람");
    private JButton menuCalender = new JButton("스케줄");
    private JButton menuTimetable = new JButton("시간표");
    private JButton menuChat = new JButton("소셜");
    private JButton menuShop = new JButton("M상점");
    private JButton homeButton = new JButton("집");
    private JPanel mainPanel;               // 페널 mainPanel
    private JPanel upPanel = new JPanel();  // 페널 mainPanel 상단 위치 border 사용 예정
    private JPanel centerPanel;             // 페널 centerPanel
    private CardLayout centerCardLayout;    // 카드레이아웃 centerCardLayout
    private JPanel menuNavPanel;            // 페널 menuNavPanel  (BORDER 남쪽 고정 페널)
    private CardLayout sidebarCardLayout;   // 카드레이아웃 sidebarCardLayout
    private JPanel sidebarPanel;            // 페널 sidebarPanel  (menuNav 속 버튼 클릭시 뜨는 BORDER 서쪽 뜨는 페널)
    private CardLayout hMCL = new CardLayout(); // 홈 이미지 바꾸기 시발.
    private JPanel homePanel;    // home Center Panel
//    private JPanel hMPgetP = new JPanel(hMCL);
    private String imageMEMO1 = ""; // 이미지 없음;
    private String hPimg = "src/Zimagees/진홈화면이요.jpg";
    private ImageIcon ichPing1 = new ImageIcon(imageMEMO1);
    private ImageIcon ichPing2 = new ImageIcon(hPimg);
    private JPanel hMP1 = new ImagePanel(ichPing1.getImage());
    private JPanel hMP2 = new ImagePanel(ichPing2.getImage());
    private JPanel j1 = new JPanel(new FlowLayout());   // 홈화면 보더 남쪽
    private JPanel upPanelL = new JPanel(new FlowLayout(FlowLayout.LEFT));  // 홈 버튼
    public static JLabel upStatus[]= new JLabel[2];;  // 상단 페널 용
    public static JPanel upPanelC = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // 이름 마일리지 날짜 시간

    public static int main_mileage = 0;         // 마일리지 초기값 0


    public MainGui() {
// 기존 배경화면 설정 (창 이름 , 창 크기)
        super("Dost it!");              // 타이틀 설정
        setSize(1100, 720);     // form 크기 설정
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    // 창 닫으면 프로그램 종료
        setLocationRelativeTo(null);            // 초기 위치 : 화면 중앙
// 메인 페널 만들고 창에 페널 박기
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
// 상단 페널 만들고 main의 North 박기
        upPanel.setLayout(new BorderLayout());    // 상단 기본 페널 (수정예정)


        for (int i = 0; i < upStatus.length; i++) {
            upStatus[i] = new JLabel();
            upStatus[i].setFont(new Font("",1, 20));
        }

        upStatus[0].setText("이름 : " + textName);
        upStatus[1].setText("\t마일리지 : "+main_mileage);
        for (int i = 0; i < 2; i++) {
            upPanelC.add(upStatus[i]);
            upStatus[i].setOpaque(true);//뒷 배경 투명
        }
        homeButton.setOpaque(true);
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sidebarPanel.setVisible(false);
                centerCardLayout.show(centerPanel,"Home");
            }
        });
        upPanelL.add(homeButton);
        upPanel.add(upPanelL, BorderLayout.WEST);
        upPanel.add(upPanelC, BorderLayout.CENTER);
        mainPanel.add(upPanel,BorderLayout.NORTH);
// [ Meno, Calender, Timetable, Chat, Shop ] 버튼 넣을 아래 페널, 버튼 선언
        menuNavPanel = new JPanel(new GridLayout());
        menuNav[0] = menuMemo;
        menuNav[1] = menuAlarm;
        menuNav[2] = menuCalender;
        menuNav[3] = menuTimetable;
        menuNav[4] = menuChat;
        menuNav[5] = menuShop;
// 버튼[ Meno, Calender, Timetable, Chat, Shop ] 생성 및 이벤트 선언
        for (int i = 0; i < menuNav.length; i++) {
            menuNavPanel.add(menuNav[i]);
            menuNav[i].setFont(new Font("", 1, 25));
            menuNav[i].setBorderPainted(false);
            menuNav[i].setOpaque(false);
            menuNav[i].setFocusPainted(false);
        }
        menuNav[0].addActionListener(new ActionListener() {         // memo
            public void actionPerformed(ActionEvent e) {
                sidebarPanel.setVisible(true);
                centerCardLayout.show(centerPanel,"Memo");
                sidebarCardLayout.show(sidebarPanel,"Memo");
            }
        });
        menuNav[1].addActionListener(new ActionListener() {         //alarm
            public void actionPerformed(ActionEvent e) {
                sidebarPanel.setVisible(false);
                centerCardLayout.show(centerPanel,"Alarm");
            }
        });
        menuNav[2].addActionListener(new ActionListener() {         //calender
            public void actionPerformed(ActionEvent e) {
                sidebarPanel.setVisible(true);
                centerCardLayout.show(centerPanel,"Calender");
                sidebarCardLayout.show(sidebarPanel, "Calender");
            }
        });
        menuNav[3].addActionListener(new ActionListener() {         //timetalbe
            public void actionPerformed(ActionEvent e) {
                sidebarPanel.setVisible(true);
                centerCardLayout.show(centerPanel,"Timetable");
                sidebarCardLayout.show(sidebarPanel, "Timetable");
            }
        });
        menuNav[4].addActionListener(new ActionListener() {         //chat
            public void actionPerformed(ActionEvent e) {
                sidebarPanel.setVisible(false);
                centerCardLayout.show(centerPanel,"Chat");
            }
        });
        menuNav[5].addActionListener(new ActionListener() {         //shop
            public void actionPerformed(ActionEvent e) {
                sidebarPanel.setVisible(false);
                centerCardLayout.show(centerPanel,"Shop");
            }
        });
// 하단 페널을 메인 페널에 넣기.
        mainPanel.add(menuNavPanel, BorderLayout.SOUTH);
// 카드레이아웃 만들기, 사이드바페널 카드레이아웃하기
        sidebarCardLayout = new CardLayout();
        sidebarPanel = new JPanel(sidebarCardLayout);
        sidebarPanel.setVisible(false);
// 사이드바 페널에 넣을 [ Meno, Calender, Timetable, Chat, Shop ]
        MeMo meme = new MeMo();
        JPanel Sidememe_panel = meme.getPanel();
        Alarm_main alal = new Alarm_main();
        Calenders calcal = new Calenders();
        JPanel Sidecalcal_panel = calcal.getPanel();
        Time_Table timetime = new Time_Table();
        JPanel Sidetimetime_panel = timetime.getPanel();
        Chatting chacha = new Chatting();
        Shopping shosho = new Shopping();
// 사이드바페널에 카드레이아웃용 붙이기 붙이기.
        sidebarPanel.add(Sidememe_panel,"Memo");
        sidebarPanel.add(Sidecalcal_panel,"Calender");
        sidebarPanel.add(Sidetimetime_panel, "Timetable");
// 사이드바 페널을 메인페널 서쪽에 위치시킨다.
        mainPanel.add(sidebarPanel, BorderLayout.WEST);
// 센터 레이아웃, 페널 설정.
        centerCardLayout = new CardLayout();
        centerPanel = new JPanel(centerCardLayout);
        centerPanel.setVisible(true);
// 센터 페널에 넣을 각각의 내용물 페널 가온나
        JPanel Centermeme_panel = meme.getPanel2();
        JPanel Centeralal_panel = alal.getPanel2();
        JPanel Centercalcal_panel = calcal.getPanel2();
        JPanel Centertimetime_panel = timetime.getPanel2();
        JPanel Centerchacha_panel = chacha.getPanel2();
        JPanel Centershosho_panel = shosho.getPanel2();
//        home Image

//        hMPgetP.add(hMP1, "1");
//        hMPgetP.add(hMP2,"2");

        homePanel = new ImagePanel(ichPing1.getImage());
        homePanel.setLayout(new BorderLayout());
//        homePanel.add(hMP1);
//        homePanel.add(hMP2);

// 센터 페널 카드레이아웃 붙이기.
        homePanel.setOpaque(true);
        centerPanel.add(homePanel, "Home");
        centerPanel.add(Centermeme_panel, "Memo");
        centerPanel.add(Centeralal_panel, "Alarm");
        centerPanel.add(Centercalcal_panel, "Calender");
        centerPanel.add(Centertimetime_panel, "Timetable");
        centerPanel.add(Centerchacha_panel, "Chat");
        centerPanel.add(Centershosho_panel, "Shop");
// 센터 페널을 메인 페널 중앙위치
        mainPanel.add(centerPanel, BorderLayout.CENTER);
// homeBtn 누르면 뜨는 초기 창
        JTextField textField = new JTextField(10);
        textField.setColumns(14);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setText("이름을 입력하라");
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals("이름을 입력하라")) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText("이름을 입력하라");
                    textField.setForeground(Color.GRAY);
                }
            }
        });
        JButton add = new JButton("이름 설정");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textName = textField.getText();
                upStatus[0].setText("이름: " + textName);
//                ChatClient.nameText = textName;
                nickName_tf.setText(textName);
                textField.setText("");
            }
        });
        j1.add(textField);
        j1.add(add);
        homePanel.add(j1, BorderLayout.SOUTH);
// shopping 이벤트 넣기.
        String homeImg = "src/Zimagees/홈홈홈홈홈.jpg";
        String imageMEMO2 = "src/Zimagees/포스트잇.jpg";
        String imageALARM = "src/Zimagees/알람.jpg";
        String imageCALEN = "src/Zimagees/캘린더.jpg";
        String imageTIMET = "src/Zimagees/타임테이블.jpg";
        String imageCHAT = "src/Zimagees/채팅.jpg";
        String imageSHOP = "src/Zimagees/상점.jpg";

        ImageIcon icHome = new ImageIcon(homeImg);
        ImageIcon icMain = new ImageIcon(imageMEMO1);
        ImageIcon icMEMO2 = new ImageIcon(imageMEMO2);
        ImageIcon icALARM = new ImageIcon(imageALARM);
        ImageIcon icCALEN = new ImageIcon(imageCALEN);
        ImageIcon icTIMET = new ImageIcon(imageTIMET);
        ImageIcon icCHAT = new ImageIcon(imageCHAT);
        ImageIcon icSHOP = new ImageIcon(imageSHOP);
        int hW = 70, hH = 70;
        int bW = 160, bH = 80;
        homeButton.setPreferredSize(new Dimension(hW, hH));
        Image scaledHome = icHome.getImage().getScaledInstance(hW, hH, Image.SCALE_SMOOTH);
        ImageIcon icHomeSet = new ImageIcon(scaledHome);
        homeButton.setIcon(icHomeSet);
        menuMemo.setPreferredSize(new Dimension(bW, bH));
        menuAlarm.setPreferredSize(new Dimension(bW, bH));
        menuCalender.setPreferredSize(new Dimension(bW, bH));
        menuTimetable.setPreferredSize(new Dimension(bW, bH));
        menuChat.setPreferredSize(new Dimension(bW, bH));
        menuShop.setPreferredSize(new Dimension(bW, bH));
        Image scaledReset = icMain.getImage().getScaledInstance(bW, bH, Image.SCALE_SMOOTH);
        ImageIcon scaledRRset = new ImageIcon(scaledReset);
        Image scaledMEMO2 = icMEMO2.getImage().getScaledInstance(bW, bH, Image.SCALE_SMOOTH);
        ImageIcon scaledIcM2 = new ImageIcon(scaledMEMO2);
        Image scaledALARM = icALARM.getImage().getScaledInstance(bW, bH, Image.SCALE_SMOOTH);
        ImageIcon scaledIcA = new ImageIcon(scaledALARM);
        Image scaledCALEN = icCALEN.getImage().getScaledInstance(bW, bH, Image.SCALE_SMOOTH);
        ImageIcon scaledIcCAL = new ImageIcon(scaledCALEN);
        Image scaledTIME = icTIMET.getImage().getScaledInstance(bW, bH, Image.SCALE_SMOOTH);
        ImageIcon scaledIcT = new ImageIcon(scaledTIME);
        Image scaledCHAT = icCHAT.getImage().getScaledInstance(bW, bH, Image.SCALE_SMOOTH);
        ImageIcon scaledIcCHAT = new ImageIcon(scaledCHAT);
        Image scaledSHOP = icSHOP.getImage().getScaledInstance(bW, bH, Image.SCALE_SMOOTH);
        ImageIcon scaledIcS = new ImageIcon(scaledSHOP);
//  초기값 = 이미지 없음
        homeButton.setText("집");
        homeButton.setFont(new Font("Dialog",1,25));
        homeButton.setIcon(scaledRRset);
        menuMemo.setIcon(scaledRRset);
        menuAlarm.setIcon(scaledRRset);
        menuCalender.setIcon(scaledRRset);
        menuTimetable.setIcon(scaledRRset);
        menuChat.setIcon(scaledRRset);
        menuShop.setIcon(scaledRRset);
//  기본 구매 + 적용
        shosho.getOriginal2_btn().addActionListener(new ActionListener() {  // 기본 적용
            public void actionPerformed(ActionEvent e) {
                if(tema_original == true){      // 이미 보유중이라면
                    homePanel.add(hMP1, BorderLayout.CENTER);
                    hMP1.setBackground(null);
                    j1.setBackground(null);
                    // 버튼 보이기
                    homeButton.setIcon(scaledRRset);
                    homeButton.setText("집");
                    homeButton.setBackground(null);
                    menuMemo.setIcon(scaledRRset);
                    menuMemo.setText("포스트잇");
                    menuMemo.setBackground(null);
                    menuAlarm.setIcon(scaledRRset);
                    menuAlarm.setText("알람");
                    menuAlarm.setBackground(null);
                    menuCalender.setIcon(scaledRRset);
                    menuCalender.setText("스케줄");
                    menuCalender.setBackground(null);
                    menuTimetable.setIcon(scaledRRset);
                    menuTimetable.setText("시간표");
                    menuTimetable.setBackground(null);
                    menuChat.setIcon(scaledRRset);
                    menuChat.setText("소셜");
                    menuChat.setBackground(null);
                    menuShop.setIcon(scaledRRset);
                    menuShop.setText("M상점");
                    menuShop.setBackground(null);
                    // 채팅 페널
                    nickNamePanel.setBackground(null);
                    chatPanel.setBackground(null);
                    inputPanel.setBackground(null);
                    memberPanel.setBackground(null);
                    totalMemberPanel.setBackground(null);
                    optionPanel.setBackground(null);
                    // timetable 패널
                    sideTimetable_panel.setBackground(null);
                    t[0].setBackground(null);
                    t[1].setBackground(null);
                    timetablePanel.setBackground(null);
                    // 캘린더 페널
                    sideCalender_panel.setBackground(null);
                    sideCalender_panel.setBackground(null);
                    b[0].setBackground(null);
                    b[1].setBackground(null);
                    b[2].setBackground(null);
                    horizontalBox.setBackground(null);
                    panel.setBackground(null);
                    horizontalBox2.setBackground(null);
                    panel2.setBackground(null);
                    horizontalBox3.setBackground(null);
                    panel3.setBackground(null);
                    // shop 페널
                    // 메모 페널
                    sideMemo_panel.setBackground(null);
                    a[0].setBackground(null);
                    a[1].setBackground(null);
                    // 알람 페널
                    comboPanel.setBackground(null);
                    btnPanel.setBackground(null);
                    quizPanel.setBackground(null);
                    // 상단페널
                    upPanelL.setBackground(null);
                    upPanelC.setBackground(null);
                    upStatus[0].setBackground(null);
                    upStatus[1].setBackground(null);
                    // 1:1 chat
                    setBackDM.setBackground(null);
                }
            }
        });
//  테마1 구매 + 적용
        shosho.gettema1_btn().addActionListener(new ActionListener() {  // 테마1 구매
            public void actionPerformed(ActionEvent e) {
                if(tema1 == true){      // 기본 테마는 항시 있기에...
                    alreadyGetTema();
                }else if(tema1 == false){   // 마일리지 비교 + 구매 불가능 하다면...
                    if(main_mileage >= 20){
                        canGetTema();
                        main_mileage -= 20;
                        upStatus[1].setText("마일리지 : " + main_mileage);
                        tema1 = true;
                    }
                    else{
                        notHaveingTema();
                    }

                }
            }
        });
        shosho.gettema2_btn().addActionListener(new ActionListener() {  // 테마1 적용
            public void actionPerformed(ActionEvent e) {
                if(tema1 == true){      // 이미 보유중이라면
                    homePanel.add(hMP2, BorderLayout.CENTER);
                    hMP2.setBackground(Color.BLACK);
                    j1.setBackground(Color.BLACK);

                    homeButton.setIcon(icHomeSet);
                    homeButton.setText("");
                    menuMemo.setIcon(scaledIcM2);
                    menuMemo.setText("");
                    menuMemo.setOpaque(true);
                    menuMemo.setBackground(new Color(0, 124,209));
                    menuAlarm.setIcon(scaledIcA);
                    menuAlarm.setText("");
                    menuAlarm.setOpaque(true);
                    menuAlarm.setBackground(new Color(0, 124,209));
                    menuCalender.setIcon(scaledIcCAL);
                    menuCalender.setText("");
                    menuCalender.setOpaque(true);
                    menuCalender.setBackground(new Color(0, 124,209));
                    menuTimetable.setIcon(scaledIcT);
                    menuTimetable.setText("");
                    menuTimetable.setOpaque(true);
                    menuTimetable.setBackground(new Color(0, 124,209));
                    menuChat.setIcon(scaledIcCHAT);
                    menuChat.setText("");
                    menuChat.setOpaque(true);
                    menuChat.setBackground(new Color(0, 124,209));
                    menuShop.setIcon(scaledIcS);
                    menuShop.setText("");
                    menuShop.setOpaque(true);
                    menuShop.setBackground(new Color(0, 124,209));
                    // 체팅 페널
                    nickNamePanel.setBackground(new Color(255, 116, 64));
                    chatPanel.setBackground(new Color(255, 116, 64));
                    inputPanel.setBackground(new Color(255, 116, 64));
                    memberPanel.setBackground(new Color(255, 116, 64));
                    totalMemberPanel.setBackground(new Color(255, 116, 64));
                    optionPanel.setBackground(new Color(255, 116, 64));
                    // timetable 패널
                    sideTimetable_panel.setOpaque(true);
                    sideTimetable_panel.setBackground(new Color(0, 124,209));
                    t[0].setOpaque(true);
                    t[1].setOpaque(true);
                    t[0].setBackground(new Color(118, 213, 255));
                    t[1].setBackground(new Color(118, 213, 255));
                    timetablePanel.setOpaque(true);
                    timetablePanel.setBackground(new Color(255, 116, 64));
                    // 캘린더 페널
                    sideCalender_panel.setOpaque(true);
                    sideCalender_panel.setBackground(new Color(0, 124,209));
                    b[0].setOpaque(true);
                    b[1].setOpaque(true);
                    b[2].setOpaque(true);
                    b[0].setBackground(new Color(118, 213, 255));
                    b[1].setBackground(new Color(118, 213, 255));
                    b[2].setBackground(new Color(118, 213, 255));
                    horizontalBox.setOpaque(true);
                    panel.setOpaque(true);
                    horizontalBox.setBackground(new Color(255, 116, 64));
                    panel.setBackground(new Color(255, 116, 64));
                    horizontalBox2.setOpaque(true);
                    panel2.setOpaque(true);
                    horizontalBox2.setBackground(new Color(255, 116, 64));
                    panel2.setBackground(new Color(255, 116, 64));
                    horizontalBox3.setOpaque(true);
                    panel3.setOpaque(true);
                    horizontalBox3.setBackground(new Color(255, 116, 64));
                    panel3.setBackground(new Color(255, 116, 64));
                    // shop 페널
                    // 메모 페널
                    sideMemo_panel.setOpaque(true);
                    sideMemo_panel.setBackground(new Color(0, 124,209));
                    a[0].setOpaque(true);
                    a[1].setOpaque(true);
                    a[0].setBackground(new Color(118, 213, 255));
                    a[1].setBackground(new Color(118, 213, 255));
                    // 알람 페널
                    comboPanel.setOpaque(true);
                    btnPanel.setOpaque(true);
                    quizPanel.setOpaque(true);
                    comboPanel.setBackground(new Color(255, 116, 64));
                    btnPanel.setBackground(new Color(255, 116, 64));
                    quizPanel.setBackground(new Color(255, 116, 64));
                    // 상단페널
                    upPanelL.setOpaque(true);
                    upPanelL.setBackground(new Color(0, 124,209));
                    upPanelC.setOpaque(true);
                    upPanelC.setBackground(new Color(0, 124,209));
                    upStatus[0].setOpaque(true);
                    upStatus[1].setOpaque(true);
                    upStatus[0].setBackground(new Color(255, 116, 64));
                    upStatus[1].setBackground(new Color(255, 116, 64));
                    //1:1 chat
                    setBackDM.setOpaque(true);
                    setBackDM.setBackground(new Color(255,116,64));
                }else if(tema1 == false){   // 마일리지 비교 + 구매 불가능 하다면...
                    notGetTema();
                }
            }
        });
    }
    //  이미 테마를 보유하고 있을 떄 띄울 창 (구매버튼)
    public void alreadyGetTema() {
        JFrame aaa = new JFrame();
        JPanel bp = new JPanel(new GridLayout(3, 1));
        bp.setOpaque(true);
        bp.setBackground(Color.lightGray);
        JLabel l1 = new JLabel("이미 보유하고");
        l1.setFont(new Font("궁서체", 1, 30));
        l1.setHorizontalAlignment(JLabel.CENTER);
        JLabel l2 = new JLabel("있는");
        l2.setFont(new Font("궁서체", 1, 30));
        l2.setHorizontalAlignment(JLabel.CENTER);
        JLabel l3 = new JLabel("테마 입니다.");
        l3.setFont(new Font("궁서체", 1, 30));
        l3.setHorizontalAlignment(JLabel.CENTER);
        bp.add(l1);
        bp.add(l2);
        bp.add(l3);
        aaa.add(bp);
        aaa.setSize(300, 300);
        aaa.setLocationRelativeTo(null);
        aaa.setVisible(true);
    }
    //  구매 가능 시 띄울 창 (구매버튼)
    public void canGetTema() {
        JFrame aaa = new JFrame();
        JPanel bp = new JPanel(new GridLayout(3, 1));
        bp.setOpaque(true);
        bp.setBackground(Color.lightGray);
        JLabel l1 = new JLabel("마일리지 20점");
        l1.setFont(new Font("궁서체", 1, 30));
        l1.setHorizontalAlignment(JLabel.CENTER);
        JLabel l2 = new JLabel("사용하여");
        l2.setFont(new Font("궁서체", 1, 30));
        l2.setHorizontalAlignment(JLabel.CENTER);
        JLabel l3 = new JLabel("구매 완료.");
        l3.setFont(new Font("궁서체", 1, 30));
        l3.setHorizontalAlignment(JLabel.CENTER);
        bp.add(l1);
        bp.add(l2);
        bp.add(l3);
        aaa.add(bp);
        aaa.setSize(300, 300);
        aaa.setLocationRelativeTo(null);
        aaa.setVisible(true);
    }
    //  테마를 보유하고 있지 않는 경우 (적용버튼)
    public void notGetTema() {
        JFrame aaa = new JFrame();
        JPanel bp = new JPanel(new GridLayout(3, 1));
        bp.setOpaque(true);
        bp.setBackground(Color.lightGray);
        JLabel l1 = new JLabel("상품");
        l1.setFont(new Font("궁서체", 1, 30));
        l1.setHorizontalAlignment(JLabel.CENTER);
        JLabel l2 = new JLabel("구매 후");
        l2.setFont(new Font("궁서체", 1, 30));
        l2.setHorizontalAlignment(JLabel.CENTER);
        JLabel l3 = new JLabel("적용 해주세요");
        l3.setFont(new Font("궁서체", 1, 30));
        l3.setHorizontalAlignment(JLabel.CENTER);
        bp.add(l1);
        bp.add(l2);
        bp.add(l3);
        aaa.add(bp);
        aaa.setSize(300, 300);
        aaa.setLocationRelativeTo(null);
        aaa.setVisible(true);
    }
    //  테마를 구매하기에 마일리지가 부족한 경우 (구매버튼)
    public void notHaveingTema(){
        JFrame aaa = new JFrame();
        JPanel bp = new JPanel(new GridLayout(3,1));
        bp.setOpaque(true);
        bp.setBackground(Color.lightGray);
        JLabel l1 = new JLabel("마일리지 부족..");
        l1.setFont(new Font("궁서체",1,30));
        l1.setHorizontalAlignment(JLabel.CENTER);
        JLabel l2 = new JLabel("마일리지를");
        l2.setFont(new Font("궁서체",1,30));
        l2.setHorizontalAlignment(JLabel.CENTER);
        JLabel l3 = new JLabel("모아주세요");
        l3.setFont(new Font("궁서체",1,30));
        l3.setHorizontalAlignment(JLabel.CENTER);
        bp.add(l1);
        bp.add(l2);
        bp.add(l3);
        aaa.add(bp);
        aaa.setSize(300,300);
        aaa.setLocationRelativeTo(null);            // 초기 위치 : 화면 중앙
        aaa.setVisible(true);
    }
}

class ImagePanel extends JPanel {
    public Image img;

    public ImagePanel(Image img) {
        this.img = img;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int fW = getWidth();
        int fH = getHeight();
        int iW = img.getWidth(this);
        int iH = img.getHeight(this);

        double wRatio = (double) fW / iW;
        double hRatio = (double) fH / iH;
        double scaleRatio = Math.min(wRatio, hRatio);

        int scaleW = (int) (iW * scaleRatio);
        int scaleH = (int) (iH * scaleRatio);

        int x = (fW - scaleW) / 2;
        int y = (fH - scaleH) / 2;
        g.drawImage(img, x, y, scaleW, scaleH, this);
    }
}

