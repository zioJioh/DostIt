//package MainGUI;
//// 사이드바 참조.
//import AlarM.Alarm_main;
//import Memo.MeMo;
//import CalendeR.Calenders;
//import ChaT.Chatting;
//
//import ShoP.Shopping;
//import TimetablE.Time_Table;
//// 기타 기능 가져오기.
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//
//public class MainGui extends JFrame{
//    private static final int NUM_MENU_BUTTONS = 6;  // 숫자 5 고정
//
//    private JButton[] menuNav;// 버튼 menuNav 배열 (menuNav페널에 넣을 버튼 배열)
//    boolean tema_original = true;           // 기본 테마  유무 기본값 true
//    boolean tema1 = false;                  // 테마 1 유무 기본값 false
//    private JButton menuMemo = new JButton("포스트잇");
//    private JButton menuAlarm = new JButton("알람");
//    private JButton menuCalender = new JButton("스케줄");
//    private JButton menuTimetable = new JButton("시간표");
//    private JButton menuChat = new JButton("소셜");
//    private JButton menuShop = new JButton("M상점");
//    private JPanel mainPanel;               // 페널 mainPanel
//    private JPanel upPanel = new JPanel();  // 페널 mainPanel 상단 위치 border 사용 예정
//    private JPanel centerPanel;             // 페널 centerPanel
//    private CardLayout centerCardLayout;    // 카드레이아웃 centerCardLayout
//    private JPanel menuNavPanel;            // 페널 menuNavPanel  (BORDER 남쪽 고정 페널)
//    private CardLayout sidebarCardLayout;   // 카드레이아웃 sidebarCardLayout
//    private JPanel sidebarPanel;            // 페널 sidebarPanel  (menuNav 속 버튼 클릭시 뜨는 BORDER 서쪽 뜨는 페널)
//    private JPanel homePanel = new JPanel();    // home Center Panel
//    public static JLabel upStatus[]= new JLabel[2];;  // 상단 페널 용
//    public static JPanel upPanelC = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // 이름 마일리지 날짜 시간
//
//    public static int main_mileage = 0;         // 마일리지 초기값 0
//
//
//    public MainGui() {
//// 기존 배경화면 설정 (창 이름 , 창 크기)
//        super("Dost it!");              // 타이틀 설정
//        setSize(960, 720);     // form 크기 설정
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    // 창 닫으면 프로그램 종료
//        setLocationRelativeTo(null);            // 초기 위치 : 화면 중앙
//// 메인 페널 만들고 창에 페널 박기
//        mainPanel = new JPanel(new BorderLayout());
//        add(mainPanel);
//// 상단 페널 만들고 main의 North 박기
//        upPanel.setLayout(new BorderLayout());    // 상단 기본 페널 (수정예정)
//        JPanel upPanelL = new JPanel(new FlowLayout(FlowLayout.LEFT));  // 홈 버튼
//
//        for (int i = 0; i < upStatus.length; i++) {
//            upStatus[i] = new JLabel();
//            upStatus[i].setFont(new Font("",0, 20));
//        }
//
//        upStatus[0].setText("이름");
//        upStatus[1].setText("마일리지 : "+main_mileage);
//        for (int i = 0; i < 2; i++) {
//            upPanelC.add(upStatus[i]);
//            upStatus[i].setOpaque(true);//뒷 배경 투명
//        }
//
//
//        JButton homeButton = new JButton();
//        homeButton.setOpaque(true);
////        homeButton.setBackground(Color.lightGray);
//        homeButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                sidebarPanel.setVisible(false);
//                centerCardLayout.show(centerPanel,"Home");
//            }
//        });
//        upPanelL.add(homeButton);
//        upPanel.add(upPanelL, BorderLayout.WEST);
//        upPanel.add(upPanelC, BorderLayout.CENTER);
//        mainPanel.add(upPanel,BorderLayout.NORTH);
//// [ Meno, Calender, Timetable, Chat, Shop ] 버튼 넣을 아래 페널, 버튼 선언
//        menuNavPanel = new JPanel(new GridLayout());
//        menuNav = new JButton[NUM_MENU_BUTTONS];
//        menuNav[0] = menuMemo;
//        menuNav[1] = menuAlarm;
//        menuNav[2] = menuCalender;
//        menuNav[3] = menuTimetable;
//        menuNav[4] = menuChat;
//        menuNav[5] = menuShop;
//// 버튼[ Meno, Calender, Timetable, Chat, Shop ] 생성 및 이벤트 선언
//        for (int i = 0; i < menuNav.length; i++) {
//            menuNavPanel.add(menuNav[i]);
//            menuNav[i].setFont(new Font("", 1, 25));
//            menuNav[i].setOpaque(true);
//        }
//        menuNav[0].addActionListener(new ActionListener() {         // memo
//            public void actionPerformed(ActionEvent e) {
//                sidebarPanel.setVisible(true);
//                centerCardLayout.show(centerPanel,"Memo");
//                sidebarCardLayout.show(sidebarPanel,"Memo");
//            }
//        });
//        menuNav[1].addActionListener(new ActionListener() {         //alarm
//            public void actionPerformed(ActionEvent e) {
//                sidebarPanel.setVisible(false);
//                centerCardLayout.show(centerPanel,"Alarm");
//            }
//        });
//        menuNav[2].addActionListener(new ActionListener() {         //calender
//            public void actionPerformed(ActionEvent e) {
//                sidebarPanel.setVisible(true);
//                centerCardLayout.show(centerPanel,"Calender");
//                sidebarCardLayout.show(sidebarPanel, "Calender");
//            }
//        });
//        menuNav[3].addActionListener(new ActionListener() {         //timetalbe
//            public void actionPerformed(ActionEvent e) {
//                sidebarPanel.setVisible(true);
//                centerCardLayout.show(centerPanel,"Timetable");
//                sidebarCardLayout.show(sidebarPanel, "Timetable");
//            }
//        });
//        menuNav[4].addActionListener(new ActionListener() {         //chat
//            public void actionPerformed(ActionEvent e) {
//                sidebarPanel.setVisible(false);
//                centerCardLayout.show(centerPanel,"Chat");
//            }
//        });
//        menuNav[5].addActionListener(new ActionListener() {         //shop
//            public void actionPerformed(ActionEvent e) {
//                sidebarPanel.setVisible(false);
//                centerCardLayout.show(centerPanel,"Shop");
//            }
//        });
//// 하단 페널을 메인 페널에 넣기.
//        mainPanel.add(menuNavPanel, BorderLayout.SOUTH);
//// 카드레이아웃 만들기, 사이드바페널 카드레이아웃하기
//        sidebarCardLayout = new CardLayout();
//        sidebarPanel = new JPanel(sidebarCardLayout);
//        sidebarPanel.setVisible(false);
//// 사이드바 페널에 넣을 [ Meno, Calender, Timetable, Chat, Shop ]
//        MeMo meme = new MeMo();
//        JPanel Sidememe_panel = meme.getPanel();
//        Alarm_main alal = new Alarm_main();
//        Calenders calcal = new Calenders();
//        JPanel Sidecalcal_panel = calcal.getPanel();
//        Time_Table timetime = new Time_Table();
//        JPanel Sidetimetime_panel = timetime.getPanel();
//        Chatting chacha = new Chatting();
//        Shopping shosho = new Shopping();
//// 사이드바페널에 카드레이아웃용 붙이기 붙이기.
//        sidebarPanel.add(Sidememe_panel,"Memo");
//        sidebarPanel.add(Sidecalcal_panel,"Calender");
//        sidebarPanel.add(Sidetimetime_panel, "Timetable");
//// 사이드바 페널을 메인페널 서쪽에 위치시킨다.
//        mainPanel.add(sidebarPanel, BorderLayout.WEST);
//// 센터 레이아웃, 페널 설정.
//        centerCardLayout = new CardLayout();
//        centerPanel = new JPanel(centerCardLayout);
//        centerPanel.setVisible(true);
//// 센터 페널에 넣을 각각의 내용물 페널 가온나
//        JPanel Centermeme_panel = meme.getPanel2();
//        JPanel Centeralal_panel = alal.getPanel2();
//        JPanel Centercalcal_panel = calcal.getPanel2();
//        JPanel Centertimetime_panel = timetime.getPanel2();
//        JPanel Centerchacha_panel = chacha.getPanel2();
//        JPanel Centershosho_panel = shosho.getPanel2();
//// 센터 페널 카드레이아웃 붙이기.
////        homePanel = new JPanel(bback);
//        homePanel.setOpaque(true);
//        homePanel.setBackground(Color.PINK);
//        centerPanel.add(homePanel, "Home");
//        centerPanel.add(Centermeme_panel, "Memo");
//        centerPanel.add(Centeralal_panel, "Alarm");
//        centerPanel.add(Centercalcal_panel, "Calender");
//        centerPanel.add(Centertimetime_panel, "Timetable");
//        centerPanel.add(Centerchacha_panel, "Chat");
//        centerPanel.add(Centershosho_panel, "Shop");
//// 센터 페널을 메인 페널 중앙위치
//        mainPanel.add(centerPanel, BorderLayout.CENTER);
//// shopping 이벤트 넣기.
////  이미지 불러오기 간단 과정
//        String homeImg = "src/Zimagees/HomeImg.png";
//        String imageMEMO1 = ""; // 이미지 없음;
//        String imageMEMO2 = "src/Zimagees/P_MEMO.png";
//        String imageALARM = "src/Zimagees/P_ALARM.png";
//        String imageCALEN = "src/Zimagees/그림2.png";
//        String imageTIMET = "src/Zimagees/P_TIME.png";
//        String imageCHAT = "src/Zimagees/P_CHAT.png";
//        String imageSHOP = "src/Zimagees/P_SHOP.png";
//        ImageIcon icHome = new ImageIcon(homeImg);
//        ImageIcon icMain = new ImageIcon(imageMEMO1);
//        ImageIcon icMEMO2 = new ImageIcon(imageMEMO2);
//        ImageIcon icALARM = new ImageIcon(imageALARM);
//        ImageIcon icCALEN = new ImageIcon(imageCALEN);
//        ImageIcon icTIMET = new ImageIcon(imageTIMET);
//        ImageIcon icCHAT = new ImageIcon(imageCHAT);
//        ImageIcon icSHOP = new ImageIcon(imageSHOP);
//        int hW = 70, hH = 70;
//        int bW = 160, bH = 80;
//        homeButton.setPreferredSize(new Dimension(hW, hH));
//        Image scaledHome = icHome.getImage().getScaledInstance(hW, hH, Image.SCALE_SMOOTH);
//        ImageIcon icHomeSet = new ImageIcon(scaledHome);
//        homeButton.setIcon(icHomeSet);
//        menuMemo.setPreferredSize(new Dimension(bW, bH));
//        menuAlarm.setPreferredSize(new Dimension(bW, bH));
//        menuCalender.setPreferredSize(new Dimension(bW, bH));
//        menuTimetable.setPreferredSize(new Dimension(bW, bH));
//        menuChat.setPreferredSize(new Dimension(bW, bH));
//        menuShop.setPreferredSize(new Dimension(bW, bH));
//        Image scaledReset = icMain.getImage().getScaledInstance(bW, bH, Image.SCALE_SMOOTH);
//        ImageIcon scaledRRset = new ImageIcon(scaledReset);
//        Image scaledMEMO2 = icMEMO2.getImage().getScaledInstance(bW, bH, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcM2 = new ImageIcon(scaledMEMO2);
//        Image scaledALARM = icALARM.getImage().getScaledInstance(bW, bH, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcA = new ImageIcon(scaledALARM);
//        Image scaledCALEN = icCALEN.getImage().getScaledInstance(bW, bH, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcCAL = new ImageIcon(scaledCALEN);
//        Image scaledTIME = icTIMET.getImage().getScaledInstance(bW, bH, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcT = new ImageIcon(scaledTIME);
//        Image scaledCHAT = icCHAT.getImage().getScaledInstance(bW, bH, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcCHAT = new ImageIcon(scaledCHAT);
//        Image scaledSHOP = icSHOP.getImage().getScaledInstance(bW, bH, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcS = new ImageIcon(scaledSHOP);
////  초기값 = 이미지 없음
//        homeButton.setText("집");
//        homeButton.setFont(new Font("Dialog",1,25));
//        homeButton.setIcon(scaledRRset);
//        menuMemo.setIcon(scaledRRset);
//        menuAlarm.setIcon(scaledRRset);
//        menuCalender.setIcon(scaledRRset);
//        menuTimetable.setIcon(scaledRRset);
//        menuChat.setIcon(scaledRRset);
//        menuShop.setIcon(scaledRRset);
////  기본 구매 + 적용
//        shosho.getOriginal1_btn().addActionListener(new ActionListener() {  // 기본 구매
//            public void actionPerformed(ActionEvent e) {
//                if(tema_original == true){      // 기본 테마는 항시 있기에...
//                    alreadyGetTema();
//                }
//            }
//        });
//        shosho.getOriginal2_btn().addActionListener(new ActionListener() {  // 기본 적용
//            public void actionPerformed(ActionEvent e) {
//                if(tema_original == true){      // 이미 보유중이라면
//                    homeButton.setIcon(scaledRRset);
//                    homeButton.setText("집");
//                    menuMemo.setIcon(scaledRRset);
//                    menuMemo.setText("포스트잇");
//                    menuAlarm.setIcon(scaledRRset);
//                    menuAlarm.setText("알람");
//                    menuCalender.setIcon(scaledRRset);
//                    menuCalender.setText("스케줄");
//                    menuTimetable.setIcon(scaledRRset);
//                    menuTimetable.setText("시간표");
//                    menuChat.setIcon(scaledRRset);
//                    menuChat.setText("소셜");
//                    menuShop.setIcon(scaledRRset);
//                    menuShop.setText("M상점");
//                }
//            }
//        });
////  테마1 구매 + 적용
//        shosho.gettema1_btn().addActionListener(new ActionListener() {  // 테마1 구매
//            public void actionPerformed(ActionEvent e) {
//                if(tema1 == true){      // 기본 테마는 항시 있기에...
//                    alreadyGetTema();
//                }else if(tema1 == false){   // 마일리지 비교 + 구매 불가능 하다면...
//                    if(main_mileage >= 20){
//                        canGetTema();
//                        main_mileage -= 20;
//                        upStatus[1].setText("마일리지 : " + main_mileage);
//                        tema1 = true;
//                    }
//                    else{
//                        notHaveingTema();
//                    }
//
//                }
//            }
//        });
//        shosho.gettema2_btn().addActionListener(new ActionListener() {  // 테마1 적용
//            public void actionPerformed(ActionEvent e) {
//                if(tema1 == true){      // 이미 보유중이라면
//                    homeButton.setIcon(icHomeSet);
//                    homeButton.setText("");
//                    menuMemo.setIcon(scaledIcM2);
//                    menuMemo.setText("");
//                    menuAlarm.setIcon(scaledIcA);
//                    menuAlarm.setText("");
//                    menuCalender.setIcon(scaledIcCAL);
//                    menuCalender.setText("");
//                    menuTimetable.setIcon(scaledIcT);
//                    menuTimetable.setText("");
//                    menuChat.setIcon(scaledIcCHAT);
//                    menuChat.setText("");
//                    menuShop.setIcon(scaledIcS);
//                    menuShop.setText("");
//                }else if(tema1 == false){   // 마일리지 비교 + 구매 불가능 하다면...
//                    notHaveingTema();
//                }
//            }
//        });
//    }
//
//
//    //  이미 테마를 보유하고 있을 떄 띄울 창 (구매버튼)
//    public void alreadyGetTema() {
//        JFrame aaa = new JFrame();
//        JPanel bp = new JPanel(new GridLayout(3, 1));
//        bp.setOpaque(true);
//        bp.setBackground(Color.lightGray);
//        JLabel l1 = new JLabel("이미 보유하고");
//        l1.setFont(new Font("궁서체", 1, 30));
//        l1.setHorizontalAlignment(JLabel.CENTER);
//        JLabel l2 = new JLabel("있는");
//        l2.setFont(new Font("궁서체", 1, 30));
//        l2.setHorizontalAlignment(JLabel.CENTER);
//        JLabel l3 = new JLabel("테마 입니다.");
//        l3.setFont(new Font("궁서체", 1, 30));
//        l3.setHorizontalAlignment(JLabel.CENTER);
//        bp.add(l1);
//        bp.add(l2);
//        bp.add(l3);
//        aaa.add(bp);
//        aaa.setSize(300, 300);
//        aaa.setLocationRelativeTo(null);
//        aaa.setVisible(true);
//    }
//    //  구매 가능 시 띄울 창 (구매버튼)
//    public void canGetTema() {
//        JFrame aaa = new JFrame();
//        JPanel bp = new JPanel(new GridLayout(3, 1));
//        bp.setOpaque(true);
//        bp.setBackground(Color.lightGray);
//        JLabel l1 = new JLabel("마일리지 20점");
//        l1.setFont(new Font("궁서체", 1, 30));
//        l1.setHorizontalAlignment(JLabel.CENTER);
//        JLabel l2 = new JLabel("사용하여");
//        l2.setFont(new Font("궁서체", 1, 30));
//        l2.setHorizontalAlignment(JLabel.CENTER);
//        JLabel l3 = new JLabel("구매 완료.");
//        l3.setFont(new Font("궁서체", 1, 30));
//        l3.setHorizontalAlignment(JLabel.CENTER);
//        bp.add(l1);
//        bp.add(l2);
//        bp.add(l3);
//        aaa.add(bp);
//        aaa.setSize(300, 300);
//        aaa.setLocationRelativeTo(null);
//        aaa.setVisible(true);
//    }
//    //  테마를 구매하기에 마일리지가 부족한 경우 (구매버튼)
//    public void notHaveingTema(){
//        JFrame aaa = new JFrame();
//        JPanel bp = new JPanel(new GridLayout(3,1));
//        bp.setOpaque(true);
//        bp.setBackground(Color.lightGray);
//        JLabel l1 = new JLabel("마일리지 부족..");
//        l1.setFont(new Font("궁서체",1,30));
//        l1.setHorizontalAlignment(JLabel.CENTER);
//        JLabel l2 = new JLabel("닝겐");
//        l2.setFont(new Font("궁서체",1,30));
//        l2.setHorizontalAlignment(JLabel.CENTER);
//        JLabel l3 = new JLabel("구매 불가능하다.");
//        l3.setFont(new Font("궁서체",1,30));
//        l3.setHorizontalAlignment(JLabel.CENTER);
//        bp.add(l1);
//        bp.add(l2);
//        bp.add(l3);
//        aaa.add(bp);
//        aaa.setSize(300,300);
//        aaa.setLocationRelativeTo(null);            // 초기 위치 : 화면 중앙
//        aaa.setVisible(true);
//    }
//}
//
//class RoundedButton extends JButton {
//
//
//    public RoundedButton() {
//        super();
//        decorate();
//    }
//
//    public RoundedButton(String text) {
//        super(text);
//        decorate();
//    }
//
//    public RoundedButton(Action action) {
//        super(action);
//        decorate();
//    }
//
//    public RoundedButton(Icon icon) {
//        super(icon);
//        decorate();
//    }
//
//    public RoundedButton(String text, Icon icon) {
//        super(text, icon);
//        decorate();
//    }
//
//    protected void decorate() {
//        setBorderPainted(false);
//        setOpaque(false);
//    }
//    protected void paintComponent(Graphics g) {
//        int width = getWidth();
//        int height = getHeight();
//
//        Graphics2D graphics = (Graphics2D) g;
//
//        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        if (getModel().isArmed()) {
//            graphics.setColor(getBackground().darker());
//        } else if (getModel().isRollover()) {
//            graphics.setColor(getBackground().brighter());
//        } else {
//            graphics.setColor(getBackground());
//        }
//
//        graphics.fillRoundRect(0, 0, width, height, 10, 10);
//
//        FontMetrics fontMetrics = graphics.getFontMetrics();
//        Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
//
//        int textX = (width - stringBounds.width) / 2;
//        int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
//
//        graphics.setColor(getForeground());
//        graphics.setFont(getFont());
//        graphics.drawString(getText(), textX, textY);
//        graphics.dispose();
//
//        super.paintComponent(g);
//    }
//}
