package ShoP;

import javax.swing.*;
import java.awt.*;

public class Shopping extends JFrame {
    private JPanel mainShop_panel = new JPanel();
    private JPanel originalImage_panel = new JPanel();// 기본테말 이미지 페멀
    private JPanel tema1Image_panel = new JPanel();// 테마1 이미지 페널
    private JPanel originalBtn_panel = new JPanel();// 기본테마 버튼 페널
    private JPanel tema1Btn_panel = new JPanel();// 테마1 버튼 페널
    private JButton originalAdd_btn = new JButton("적용");// 기본 적용버튼
    private JButton tema1Shop_btn = new JButton("20 마일리지");// 테마1 구매버튼
    private JButton tema1Add_btn = new JButton("적용");// 테마1 적용버튼

    public Shopping() {
        // 이미지 생성 및 페널 적용
        ImageIcon icori = new ImageIcon("src/Zimagees/오리지날테마.png");
        ImageIcon ictema = new ImageIcon("src/Zimagees/테마1.jpg");
        Image imageOri = icori.getImage();
        Image imageTema = ictema.getImage();
        originalImage_panel = new ImagePanel(imageOri);
        tema1Image_panel = new ImagePanel(imageTema);
        // MAIN center SHOP 페널 생성.
        mainShop_panel.setLayout(new GridLayout(2,2));

        originalBtn_panel.setLayout(new GridBagLayout());
        GridBagConstraints gBC1 = new GridBagConstraints();
        gBC1.fill = GridBagConstraints.BOTH;
        originalBtn_panel.add(originalAdd_btn, gBC1);
        originalAdd_btn.setFont(new Font("",1,20));
        originalBtn_panel.add(originalAdd_btn);

        tema1Btn_panel.setLayout(new GridBagLayout());
        GridBagConstraints gBC2 = new GridBagConstraints();
        gBC2.fill = GridBagConstraints.BOTH;
        tema1Btn_panel.setBackground(new Color(255,116,64));
        tema1Shop_btn.setFont(new Font("",1,20));
        tema1Add_btn.setFont(new Font("",1,20));
        tema1Shop_btn.setBackground(new Color(118,213,255));
        tema1Add_btn.setBackground(new Color(118,213,255));
        tema1Btn_panel.add(tema1Shop_btn, gBC2);
        tema1Btn_panel.add(tema1Add_btn, gBC2);

        mainShop_panel.add(originalImage_panel);
        mainShop_panel.add(originalBtn_panel);

        mainShop_panel.add(tema1Image_panel);
        mainShop_panel.add(tema1Btn_panel);
    }
    public JPanel getPanel2() { return mainShop_panel; }    // 메인gui center 카드레이아웃
    public JButton getOriginal2_btn(){ return originalAdd_btn; }    // 기본테마 적용
    public JButton gettema1_btn(){ return tema1Shop_btn; }          // 테마1 구매
    public JButton gettema2_btn(){ return tema1Add_btn; }           // 테마1 적용
}

class ImagePanel extends JPanel{
    Image img;
    public ImagePanel(Image img){ this.img = img; }
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        int fW = getWidth();
        int fH = getHeight();
        int iW = img.getWidth(this);
        int iH = img.getHeight(this);

        double wRatio = (double)fW/iW;
        double hRatio = (double)fH/iH;
        double scaleRatio = Math.min(wRatio,hRatio);

        int scaleW = (int) (iW * scaleRatio);
        int scaleH = (int) (iH * scaleRatio);

        int x = (fW - scaleW) / 2;
        int y = (fH - scaleH) / 2;
        g.drawImage(img, x, y, scaleW, scaleH,this);
    }
}
