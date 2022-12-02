package dat.controls;

import dat.models.CoursesController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private JTextField textField;
    private DefaultListModel<String>list=new DefaultListModel<>();
    private JList jlist;

    public MainFrame() {
        setTitle("Xếp thời khóa biểu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        this.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));

        JButton btnAdd = new JButton("Add");
        panel.add(btnAdd, BorderLayout.EAST);

        textField = new JTextField();
        panel.add(textField, BorderLayout.CENTER);
        textField.setColumns(10);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.EAST);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton btnClear = new JButton("Clear");
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel_1.setPreferredSize(new Dimension(100, 100));
        panel_1.add(btnDelete);
        panel_1.add(btnClear);

        JButton btnMake = new JButton("Make");
        panel_1.add(btnMake);
        btnAdd.setPreferredSize(new Dimension(100, 30));
        btnClear.setPreferredSize(new Dimension(100, 30));
        btnDelete.setPreferredSize(new Dimension(100, 30));
        btnMake.setPreferredSize(new Dimension(100, 30));
        btnClear.setBorder(new RoundedBorder(10));
        jlist = new JList();
        jlist.setModel(list);
        contentPane.add(jlist, BorderLayout.CENTER);

        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnAddMouseClicked();
            }
        });
        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnDeleteMouseClicked();
            }
        });
        btnClear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnClearMouseClicked();
            }
        });
        btnMake.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnMakeMouseClicked();
            }
        });
    }

    private void btnDeleteMouseClicked() {
        controller.closeSchedules();
        controller.getData().remove(jlist.getSelectedIndex());
        list.remove(jlist.getSelectedIndex());
    }

    private void btnClearMouseClicked() {
        controller.closeSchedules();
        controller.getData().clear();
        list.clear();
    }

    private void btnMakeMouseClicked() {
        controller.arrangeCourse();
    }

    public CoursesController controller=new CoursesController();
    private void btnAddMouseClicked() {
        String courseID=textField.getText();
        if(list.contains(courseID)){
            notify("Mã môn học đã được thêm trước đó");
            return;
        }
        if(!controller.addSubject(courseID)){
            notify("Lấy dữ liệu môn học không thành công");
            return;
        }
        list.add(0,courseID);
    }
    public void notify(String message){
        JOptionPane.showMessageDialog(this.getRootPane(),message);
    }

    private static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }


        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }


        public boolean isBorderOpaque() {
            return true;
        }


        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

}
