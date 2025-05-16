package taskUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener , ActionListener {

    int [][]date = new int[4][4];
    int x = 0,y = 0;

    int [][]win = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};

    String path = "puzzlegame\\image\\animal\\animal3\\";

    int step = 0;
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");
    JMenuItem replay = new JMenuItem("重新游戏");
    JMenuItem relogin = new JMenuItem("重新登录");
    JMenuItem closegame = new JMenuItem("关闭游戏");
    JMenuItem gzh = new JMenuItem("公众号");
    public GameJFrame() {
        //界面初始化
        init();
        //菜单初始化
        menu();
        //初始化数据
        initDate();
        //初始化图片
        initImage();
        this.setVisible(true);
    }
//初始化界面
    private void init() {
        this.setSize(603,680);
        this.setTitle("拼图单机版 v1.0");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.addKeyListener(this);
    }
/**
 * 初始化菜单栏
 *
 * 此方法创建并配置应用程序的菜单栏它定义了两个菜单“功能”和“关于我们”，
 * 并为这些菜单添加了相应的菜单项这些菜单项包括重新播放、重新登录、关闭游戏和公众号
 * 每个菜单项都注册了当前类作为事件监听器，以便处理用户的操作
 */
private void menu() {
    // 创建菜单栏对象
    JMenuBar jMenuBar = new JMenuBar();

    //创建更换图片
    JMenu changeImage = new JMenu("更换图片");
    // 创建“功能”菜单
    JMenu functionJMenu = new JMenu("功能");
    // 创建“关于我们”菜单
    JMenu aboutusJMenu = new JMenu("关于我们");

    // 为各个菜单项添加事件监听器
    girl.addActionListener(this);
    animal.addActionListener(this);
    sport.addActionListener(this);
    replay.addActionListener(this);
    relogin.addActionListener(this);
    closegame.addActionListener(this);
    gzh.addActionListener(this);
    
    // 将菜单项添加到“功能”菜单中
    changeImage.add(girl);
    changeImage.add(animal);
    changeImage.add(sport);
    functionJMenu.add(changeImage);
    functionJMenu.add(replay);
    functionJMenu.add(relogin);
    functionJMenu.add(closegame);
    // 将菜单项添加到“关于我们”菜单中
    aboutusJMenu.add(gzh);

    // 将菜单添加到菜单栏中
    jMenuBar.add(functionJMenu);
    jMenuBar.add(aboutusJMenu);
    // 设置当前窗口的菜单栏
    this.setJMenuBar(jMenuBar);
}

// 初始化数据，用于打乱数组并设置初始状态
private void initDate() {
    // 创建一个有序的整数数组
    int []arr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    // 创建一个随机数生成器
    Random r = new Random();
    // 遍历数组，使用随机数交换数组元素，以打乱数组顺序
    for (int i = 0; i < arr.length; i++) {
        // 生成一个随机索引
        int index = r.nextInt(arr.length);
        // 交换当前索引i和随机索引index的值
        int templ = arr[i];
        arr[i] = arr[index];
        arr[index] = templ;
    }
    // 初始化num变量用于遍历打乱后的数组
    int num = 0;
    // 遍历二维数组date，填充以打乱后的一维数组arr的值
    for(int j = 0;j < 4;j++)
        for(int i = 0;i < 4;i++) {
            // 将一维数组的值赋给二维数组
            date[j][i] = arr[num];
            num++;
            // 当遇到值为0的元素时，记录其在二维数组中的位置
            if(date[j][i] == 0){
                x = j;
                y = i;
            }
        }
}


//初始化图片
    private void initImage () {
        this.getContentPane().removeAll();

        if(isWin()){
            JLabel win = new JLabel(new ImageIcon("puzzlegame\\image\\win.png"));
            win.setBounds(203,283,197,73);
            this.getContentPane().add(win);
        }

        for(int j = 0;j < 4;j++)
            for(int i = 0;i < 4;i++) {
                JLabel jLabel = new JLabel(new ImageIcon(path + date[j][i] +".jpg"));
                jLabel.setBounds(105*i + 83,105*j + 134,105,105);
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(jLabel);
            }

        //背景图片
        ImageIcon bg = new ImageIcon("puzzlegame\\image\\background.png");
        JLabel jbg = new JLabel(bg);
        jbg.setBounds(40,40,508,560);
        this.getContentPane().add(jbg);

        JLabel stepJF = new JLabel("步数：" + step);
        stepJF.setBounds(50,30,100,20);
        this.getContentPane().add(stepJF);

        this.getContentPane().repaint();
    }
    //键盘监听
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 65){
            this.getContentPane().removeAll();

            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);

            ImageIcon bg = new ImageIcon("puzzlegame\\image\\background.png");
            JLabel jbg = new JLabel(bg);
            jbg.setBounds(40,40,508,560);
            this.getContentPane().add(jbg);

            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(isWin()){
            return;
        }
        int code = e.getKeyCode();
        if(code == 37){
            System.out.println("向左移动");
            if(y == 3) return;
            date[x][y] = date[x][y + 1];
            date[x][y + 1] = 0;
            y++;
            step++;
            initImage();
        } else if (code == 38) {
            System.out.println("向上移动");
            if(x == 3) return;
            date[x][y] = date[x + 1][y];
            date[x + 1][y] = 0;
            x++;
            step++;
            initImage();
        }else if(code == 39){
            System.out.println("向右移动");
            if(y == 0) return;
            date[x][y] = date[x][y - 1];
            date[x][y - 1] = 0;
            y--;
            step++;
            initImage();
        }else if(code == 40){
            System.out.println("向下移动");
            if(x == 0) return;
            date[x][y] = date[x - 1][y];
            date[x - 1][y] = 0;
            x--;
            step++;
            initImage();
        }
        else if(code == 65){    //恢复原状
            initImage();
        } else if (code == 87) {
            date =new int [][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,0}
            };
            initImage();
        }
    }

    public boolean isWin (){
        for (int i = 0; i < date.length; i++) {
            for(int j = 0;j < date[i].length;j++){
                if(date[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    //动作监听
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == replay){
            System.out.println("重新游戏");
            //重新加载步数
            step = 0;
            //打乱图片
            initDate();
            //重新加载图片
            initImage();
        } else if (obj == relogin) {
            System.out.println("重新登录");
            //关闭当前界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();
        } else if (obj == closegame) {
            System.out.println("关闭游戏");
            System.exit(0);
        } else if (obj == gzh) {
            System.out.println("公众号");
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("puzzlegame\\image\\about.png"));
            jLabel.setBounds(0,0,258,258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344,344);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        } else if (obj == girl) {
            System.out.println("美女");
            Random r = new Random();
            path = "puzzlegame\\image\\girl\\girl" + r.nextInt(13) + "\\";
            step = 0;
            initDate();
            initImage();
        } else if (obj == animal) {
            System.out.println("动物");
            Random r = new Random();
            path = "puzzlegame\\image\\animal\\animal" + r.nextInt(8) + "\\";
            step = 0;
            initDate();
            initImage();
        } else if (obj == sport) {
            System.out.println("运动");
            Random r = new Random();
            path = "puzzlegame\\image\\sport\\sport" + r.nextInt(10) + "\\";
            step = 0;
            initDate();
            initImage();
        }
    }
}
