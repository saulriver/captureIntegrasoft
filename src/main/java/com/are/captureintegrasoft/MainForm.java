/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.are.captureintegrasoft;

import com.are.captureintegrasoft.entity.Employee;
import com.are.captureintegrasoft.request.FingerprintRequest;
import com.are.captureintegrasoft.response.FingerprintResponse;
import com.are.captureintegrasoft.request.EmployeeRequest;
import com.are.captureintegrasoft.rest.EmployeeRest;
import com.are.captureintegrasoft.rest.FingerprintRest;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.ui.swing.sample.Enrollment.CaptureForm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;

/**
 *
 * @author Laura
 */
public class MainForm extends javax.swing.JFrame {
    
    final private String url = "http://ec2-18-224-134-225.us-east-2.compute.amazonaws.com:8087/";
   
    final private String token = "00000";
    
    ImageIcon icon = new ImageIcon("person.png");
    
         // Variables declaration - do not modify                     
    private javax.swing.JButton findButton;
    private javax.swing.JButton fingerButton1;
    private javax.swing.JButton fingerButton10;
    private javax.swing.JButton fingerButton2;
    private javax.swing.JButton fingerButton3;
    private javax.swing.JButton fingerButton4;
    private javax.swing.JButton fingerButton5;
    private javax.swing.JButton fingerButton6;
    private javax.swing.JButton fingerButton7;
    private javax.swing.JButton fingerButton8;
    private javax.swing.JButton fingerButton9;
    private javax.swing.JLabel headerTitle;
    private javax.swing.JDesktopPane jDesktopPaneTitle;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelTitle;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel photo;
    private javax.swing.JButton quitButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtCriteria;
    private javax.swing.JTextField txtfirstName;
    private javax.swing.JTextField txtlastName;
    private javax.swing.JLabel lblAddress; //new
    private javax.swing.JLabel lblCampus; //new
    private javax.swing.JLabel lblDepartmenet; //new
    private javax.swing.JLabel lblEmployment; //new
    private javax.swing.JLabel lblPhoneNumber1; //new
    private javax.swing.JTextField txtAddress; //new
    private javax.swing.JTextField txtCampus; //new
    private javax.swing.JTextField txtDepartment; //new
    private javax.swing.JTextField txtEmployment; //new
    private javax.swing.JTextField txtPhoneNumber1; //new
    private javax.swing.JButton verifyFingerButton1;
    private javax.swing.JButton verifyFingerButton10;
    private javax.swing.JButton verifyFingerButton2;
    private javax.swing.JButton verifyFingerButton3;
    private javax.swing.JButton verifyFingerButton4;
    private javax.swing.JButton verifyFingerButton5;
    private javax.swing.JButton verifyFingerButton6;
    private javax.swing.JButton verifyFingerButton7;
    private javax.swing.JButton verifyFingerButton8;
    private javax.swing.JButton verifyFingerButton9;
    private javax.swing.JComboBox jComboBox1;
    // End of variables declaration 
    
    // Fingerprint variables
    public static String TEMPLATE_PROPERTY = "template";
    
    private DPFPTemplate template1;
    private DPFPTemplate template2;
    private DPFPTemplate template3;
    private DPFPTemplate template4;
    private DPFPTemplate template5;
    private DPFPTemplate template6;
    private DPFPTemplate template7;
    private DPFPTemplate template8;
    private DPFPTemplate template9;
    private DPFPTemplate template10;
    private DPFPTemplate templatefingerprintDefault;
    
    private FingerprintRequest fingerprintRequest;
    private String EmployeeId;
    private String fingerprintPosition;
    private String fingerprintDefault;
    
    public MainForm() throws HeadlessException {
        initComponent();
        setup();
    
    }
    
    private void setup() {
        this.addPropertyChangeListener(TEMPLATE_PROPERTY, new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                verifyFingerButton1.setEnabled(template1 != null);
                verifyFingerButton2.setEnabled(template2 != null);
                verifyFingerButton3.setEnabled(template3 != null);
                verifyFingerButton4.setEnabled(template4 != null);
                verifyFingerButton5.setEnabled(template5 != null);
                verifyFingerButton6.setEnabled(template6 != null);
                verifyFingerButton7.setEnabled(template7 != null);
                verifyFingerButton8.setEnabled(template8 != null);
                verifyFingerButton9.setEnabled(template9 != null);
                verifyFingerButton10.setEnabled(template10 != null);
                
                saveButton.setEnabled((template1 != null) || (template2 != null) || (template3 != null) || (template4 != null) || 
                                        (template5 != null) || (template6 != null) || (template7 != null) || (template8 != null) ||
                                        (template9 != null) || (template10 != null) || (templatefingerprintDefault != null));
               
                if (evt.getNewValue() == evt.getOldValue()) {
                    return;
                }
                
                if (getFingerprintPosition().equals("1") && template1 != null) {                  
                    JOptionPane.showMessageDialog(MainForm.this, "The fingerprint 1 template is ready for fingerprint verification.", "Fingerprint Enrollment", JOptionPane.INFORMATION_MESSAGE);
                }
                if (getFingerprintPosition().equals("2") && template2 != null) {                  
                    JOptionPane.showMessageDialog(MainForm.this, "The fingerprint 2 template is ready for fingerprint verification.", "Fingerprint Enrollment", JOptionPane.INFORMATION_MESSAGE);
                }
                if (getFingerprintPosition().equals("3") && template3 != null) {                  
                    JOptionPane.showMessageDialog(MainForm.this, "The fingerprint 3 template is ready for fingerprint verification.", "Fingerprint Enrollment", JOptionPane.INFORMATION_MESSAGE);
                }
                if (getFingerprintPosition().equals("4") && template4 != null) {                  
                    JOptionPane.showMessageDialog(MainForm.this, "The fingerprint 4 template is ready for fingerprint verification.", "Fingerprint Enrollment", JOptionPane.INFORMATION_MESSAGE);
                }
                if (getFingerprintPosition().equals("5") && template5 != null) {                  
                    JOptionPane.showMessageDialog(MainForm.this, "The fingerprint 5 template is ready for fingerprint verification.", "Fingerprint Enrollment", JOptionPane.INFORMATION_MESSAGE);
                }
                if (getFingerprintPosition().equals("6") && template6 != null) {                  
                    JOptionPane.showMessageDialog(MainForm.this, "The fingerprint 6 template is ready for fingerprint verification.", "Fingerprint Enrollment", JOptionPane.INFORMATION_MESSAGE);
                }
                if (getFingerprintPosition().equals("7") && template7 != null) {                  
                    JOptionPane.showMessageDialog(MainForm.this, "The fingerprint 7 template is ready for fingerprint verification.", "Fingerprint Enrollment", JOptionPane.INFORMATION_MESSAGE);
                }
                if (getFingerprintPosition().equals("8") && template8 != null) {                  
                    JOptionPane.showMessageDialog(MainForm.this, "The fingerprint 8 template is ready for fingerprint verification.", "Fingerprint Enrollment", JOptionPane.INFORMATION_MESSAGE);
                }
                if (getFingerprintPosition().equals("9") && template9 != null) {                  
                    JOptionPane.showMessageDialog(MainForm.this, "The fingerprint 9 template is ready for fingerprint verification.", "Fingerprint Enrollment", JOptionPane.INFORMATION_MESSAGE);
                }
                if (getFingerprintPosition().equals("10") && template10 != null) {                  
                    JOptionPane.showMessageDialog(MainForm.this, "The fingerprint 10 template is ready for fingerprint verification.", "Fingerprint Enrollment", JOptionPane.INFORMATION_MESSAGE);
                }
                if (getFingerprintPosition().equals("fingerprintDefault") && templatefingerprintDefault != null) {                  
                    JOptionPane.showMessageDialog(MainForm.this, "The fingerprintDefault template is ready for fingerprint verification.", "Fingerprint Enrollment", JOptionPane.INFORMATION_MESSAGE);
                }
                
                System.out.println("\n");
                System.out.println("Position: " + getFingerprintPosition());
                System.out.println("Template 1: " + template1);
                System.out.println("Template 2: " + template2);
                System.out.println("Template 3: " + template3);
                System.out.println("Template 4: " + template4);
                System.out.println("Template 5: " + template5);
                System.out.println("Template 6: " + template6);
                System.out.println("Template 7: " + template7);
                System.out.println("Template 8: " + template8);
                System.out.println("Template 9: " + template9);
                System.out.println("Template 10: " + template10);
                System.out.println("FingerprintDefault: " + templatefingerprintDefault);
                System.out.println("\n");
            }
        });
        setTemplate1(null);
        setTemplate2(null);
        setTemplate3(null);
        setTemplate4(null);
        setTemplate5(null);
        setTemplate6(null);
        setTemplate7(null);
        setTemplate8(null);
        setTemplate9(null);
        setTemplate10(null);
        setFingerprintDefault(null);
        
        fingerprintRequest = new FingerprintRequest();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">   
    private void initComponent() {
       
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanelTitle = new javax.swing.JPanel();
        jDesktopPaneTitle = new javax.swing.JDesktopPane();
        headerTitle = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        txtCriteria = new javax.swing.JTextField();
        txtCode = new javax.swing.JTextField();
        label3 = new javax.swing.JLabel();
        txtfirstName = new javax.swing.JTextField();
        label4 = new javax.swing.JLabel();
        txtlastName = new javax.swing.JTextField();
        findButton = new javax.swing.JButton();
        photo = new javax.swing.JLabel();
        quitButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        fingerButton1 = new javax.swing.JButton();
        fingerButton2 = new javax.swing.JButton();
        fingerButton3 = new javax.swing.JButton();
        fingerButton4 = new javax.swing.JButton();
        fingerButton5 = new javax.swing.JButton();
        verifyFingerButton1 = new javax.swing.JButton();
        verifyFingerButton2 = new javax.swing.JButton();
        verifyFingerButton3 = new javax.swing.JButton();
        verifyFingerButton4 = new javax.swing.JButton();
        verifyFingerButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        fingerButton6 = new javax.swing.JButton();
        fingerButton7 = new javax.swing.JButton();
        fingerButton8 = new javax.swing.JButton();
        fingerButton9 = new javax.swing.JButton();
        fingerButton10 = new javax.swing.JButton();
        verifyFingerButton8 = new javax.swing.JButton();
        verifyFingerButton9 = new javax.swing.JButton();
        verifyFingerButton10 = new javax.swing.JButton();
        verifyFingerButton7 = new javax.swing.JButton();
        verifyFingerButton6 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        lblAddress = new javax.swing.JLabel(); //new
        lblPhoneNumber1 = new javax.swing.JLabel(); //new
        lblCampus = new javax.swing.JLabel(); //new
        lblDepartmenet = new javax.swing.JLabel(); //new
        lblEmployment = new javax.swing.JLabel(); //new
        txtCampus = new javax.swing.JTextField(); //new
        txtDepartment = new javax.swing.JTextField(); //new
        txtEmployment = new javax.swing.JTextField(); //new
        txtAddress = new javax.swing.JTextField(); //new
        txtPhoneNumber1 = new javax.swing.JTextField(); //new
        jPanel4 = new javax.swing.JPanel(); //new
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jInternalFrame1.setAlignmentX(50.0F);
        jInternalFrame1.setAlignmentY(50.0F);
        jInternalFrame1.setAutoscrolls(true);
        jInternalFrame1.setEnabled(false);
        jInternalFrame1.setVisible(true);
        jInternalFrame1.setBackground(new java.awt.Color(255, 255, 255));

        jPanelTitle.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, java.awt.Color.lightGray, null, null));
       

        jDesktopPaneTitle.setBackground(new java.awt.Color(23,134,115));
        jDesktopPaneTitle.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 102, 255)));
 
        headerTitle.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        headerTitle.setForeground(new java.awt.Color(247, 247, 247));
        headerTitle.setText("INTEGRASOFT");
        //headerTitle.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jDesktopPaneTitle.setLayer(headerTitle, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPaneTitleLayout = new javax.swing.GroupLayout(jDesktopPaneTitle);
        jDesktopPaneTitle.setLayout(jDesktopPaneTitleLayout);
        jDesktopPaneTitleLayout.setHorizontalGroup(
            jDesktopPaneTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDesktopPaneTitleLayout.setVerticalGroup(
            jDesktopPaneTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPaneTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        headerTitle.getAccessibleContext().setAccessibleParent(headerTitle);

        javax.swing.GroupLayout jPanelTitleLayout = new javax.swing.GroupLayout(jPanelTitle);
        jPanelTitle.setLayout(jPanelTitleLayout);
        jPanelTitleLayout.setHorizontalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPaneTitle)
        );
        jPanelTitleLayout.setVerticalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPaneTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        label1.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        label1.setText("Codigo empleado");

        txtCriteria.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
     
        txtfirstName.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N

        label3.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        label3.setText("Nombre");

        txtlastName.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        

        label4.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        label4.setText("Apellido");

        txtDepartment.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N

////NEW
        findButton.setIcon(new javax.swing.ImageIcon("research.png")); // NOI18N
        findButton.setBackground(new java.awt.Color(23,134,115));
        findButton.setText("BUSCAR");
        findButton.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        findButton.setForeground(java.awt.Color.white);
        findButton.addFocusListener(new java.awt.event.FocusAdapter() {
        });

        photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        photo.setIcon(new javax.swing.ImageIcon("face.png")); // NOI18N
        photo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(25, 126, 87)));
        photo.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        photo.setMinimumSize(new java.awt.Dimension(0, 0));
        photo.setEnabled(true);
        
        quitButton.setIcon(new javax.swing.ImageIcon("exit.png")); // NOI18N
        quitButton.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        quitButton.setText("SALIR");
        
        saveButton.setIcon(new javax.swing.ImageIcon("save.png")); // NOI18N
        saveButton.setBackground(new java.awt.Color(23,134,115));
        saveButton.setText("GUARDAR");
        saveButton.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        saveButton.setForeground(java.awt.Color.white);
 /////NEW       
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Huella-predeterminada", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Montserrat", 0, 14))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(249, 247, 170));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 153), null, null), "Mano izquierda", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Montserrat", 0, 14))); // NOI18N

        fingerButton1.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        fingerButton1.setText("Pulgar");

        fingerButton2.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        fingerButton2.setText("Indice");

        fingerButton3.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        fingerButton3.setText("Medio");

        fingerButton4.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        fingerButton4.setText("Anular");

        fingerButton5.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        fingerButton5.setText("Meñique");

        verifyFingerButton1.setIcon(new javax.swing.ImageIcon("fingerprint.png")); // NOI18N
        
        verifyFingerButton2.setIcon(new javax.swing.ImageIcon("fingerprint.png")); // NOI18N
        
        verifyFingerButton3.setIcon(new javax.swing.ImageIcon("fingerprint.png")); // NOI18N
        
        verifyFingerButton4.setIcon(new javax.swing.ImageIcon("fingerprint.png")); // NOI18N
        
        verifyFingerButton5.setIcon(new javax.swing.ImageIcon("fingerprint.png")); // NOI18N
        
///////NEW       
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(verifyFingerButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(verifyFingerButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(verifyFingerButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(verifyFingerButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(verifyFingerButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fingerButton5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(fingerButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fingerButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fingerButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(fingerButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fingerButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(verifyFingerButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(verifyFingerButton2)
                    .addComponent(fingerButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fingerButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verifyFingerButton3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fingerButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verifyFingerButton4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fingerButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(verifyFingerButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48))
        );

        jPanel3.setBackground(new java.awt.Color(249, 247, 170));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 153), null, null), "Mano derecha", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Montserrat", 0, 14))); // NOI18N

        fingerButton6.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        fingerButton6.setText("Pulgar");

        fingerButton7.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        fingerButton7.setText("Indice");

        fingerButton8.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        fingerButton8.setText("Medio");

        fingerButton9.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        fingerButton9.setText("Anular");

        fingerButton10.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        fingerButton10.setText("Meñique");

        verifyFingerButton8.setIcon(new javax.swing.ImageIcon("fingerprint.png")); // NOI18N
               
        verifyFingerButton9.setIcon(new javax.swing.ImageIcon("fingerprint.png")); // NOI18N
               
        verifyFingerButton10.setIcon(new javax.swing.ImageIcon("fingerprint.png")); // NOI18N
                
        verifyFingerButton7.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        verifyFingerButton7.setIcon(new javax.swing.ImageIcon("fingerprint.png")); // NOI18N
       
        verifyFingerButton6.setIcon(new javax.swing.ImageIcon("fingerprint.png")); // NOI18N
 ///////NEW      
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(fingerButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(42, 42, 42))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(fingerButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                .addGap(64, 64, 64)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(verifyFingerButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(verifyFingerButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(fingerButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(verifyFingerButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(fingerButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fingerButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(verifyFingerButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(verifyFingerButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fingerButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verifyFingerButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(verifyFingerButton7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fingerButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fingerButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verifyFingerButton8))
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(verifyFingerButton9)
                    .addComponent(fingerButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fingerButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verifyFingerButton10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jComboBox1.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pulgar izquierdo", "Índice izquierdo", "Medio izquierdo", "Anular izquierdo", "Meñique izquierdo", "Pulgar derecho", "Índice derecho", "Medio derecho", "Anular derecho", "Meñique derecho" }));
     

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        
        jPanel2.getAccessibleContext().setAccessibleName("Mano izquierda");

        lblAddress.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        lblAddress.setText("Dirección");

        txtAddress.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N

        lblCampus.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        lblCampus.setText("Sede");

        txtPhoneNumber1.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N

        lblPhoneNumber1.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        lblPhoneNumber1.setText("Telefono 1");

        txtCampus.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N

        lblDepartmenet.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        lblDepartmenet.setText("Departamento");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(txtCriteria, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(findButton))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtlastName, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhoneNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPhoneNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDepartment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDepartmenet))
                        .addGap(18, 18, 18)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(saveButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(quitButton))
                            .addComponent(photo, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(label1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jPanelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(photo, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(30, Short.MAX_VALUE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(findButton)
                            .addComponent(txtCriteria, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtlastName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPhoneNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhoneNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDepartmenet, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        label1.getAccessibleContext().setAccessibleParent(this);
        lblAddress.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(jInternalFrame1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 710));

//////////NEW
   
      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
 // </editor-fold>
        
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findButtonClick(evt);
            }
        });
        
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonClick(evt);
            }
        });
        
        fingerButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setFingerprintPosition("1");
                captureButtonClick(evt);
            }
        });
        
        fingerButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setFingerprintPosition("2");
                captureButtonClick(evt);
            }
        });
        
        fingerButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setFingerprintPosition("3");
                captureButtonClick(evt);
            }
        });
        
        fingerButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setFingerprintPosition("4");
                captureButtonClick(evt);
            }
        });
        
        fingerButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setFingerprintPosition("5");
                captureButtonClick(evt);
            }
        });
        
        fingerButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setFingerprintPosition("6");
                captureButtonClick(evt);
            }
        });
        
        fingerButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setFingerprintPosition("7");
                captureButtonClick(evt);
            }
        });
        
        fingerButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setFingerprintPosition("8");
                captureButtonClick(evt);
            }
        });
        
        fingerButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setFingerprintPosition("9");
                captureButtonClick(evt);
            }
        });
        
        fingerButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setFingerprintPosition("10");
                captureButtonClick(evt);
            }
        });
        
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                setFingerprintPosition("fingerprintDefault");
 //               captureButtonClick(evt);
    
            }
        });
        
        verifyFingerButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifyButtonClick(evt);
            }
        });
        
        verifyFingerButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifyButtonClick(evt);
            }
        });
        
        verifyFingerButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifyButtonClick(evt);
            }
        });
        
        verifyFingerButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifyButtonClick(evt);
            }
        });
        
        verifyFingerButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifyButtonClick(evt);
            }
        });
        
        verifyFingerButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifyButtonClick(evt);
            }
        });
        
        verifyFingerButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifyButtonClick(evt);
            }
        });
        
        verifyFingerButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifyButtonClick(evt);
            }
        });
        
        verifyFingerButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifyButtonClick(evt);
            }
        });
        
        verifyFingerButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifyButtonClick(evt);
            }
        });
        
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonClick(evt);
            }
        });
        
        pack();
        setTitle("Capture Employee Fingerprint");
        setVisible( true );      
    }
    
    public void findButtonClick(java.awt.event.ActionEvent evt) {
        try {
            if (txtCriteria.getText().equals("")) {
                JOptionPane.showMessageDialog(MainForm.this, "Entry Employee please.", "Error", JOptionPane.ERROR_MESSAGE);
                txtCriteria.requestFocus();
                cleanForm();
                return;
            }
            
            String criteria = txtCriteria.getText().trim();
            String url = "http://ec2-18-224-134-225.us-east-2.compute.amazonaws.com:8087/employee/object/" + criteria;
            
            ClientRestService client = new ClientRestService(url);
            
            Employee employee = client.consumeRestEmployee();
            
            if (employee != null) {
                System.out.println("State: " + employee.getState());
                if(employee.getState().equals("ENABLED")){
                    
                    txtfirstName.setText(employee.getFirstName());
                    txtlastName.setText(employee.getLastName());
                    txtAddress.setText(employee.getAddress());                
                    txtPhoneNumber1.setText(employee.getPhoneNumber1());
                    txtCampus.setText(employee.getCampus());
                    txtDepartment.setText(employee.getDepartment());
                    //txtEmployment.setText(employee.getEmployment());
                    
                    setEmployeeId(employee.getId());
                    
                    
                    if (employee.getPhoto() != null) {
                            photo.setIcon(getIcon(employee.getPhoto(), photo.getWidth(), photo.getHeight()));
                    }else {
                        ImageIcon icon = new ImageIcon("person.png");
                        photo.setIcon(icon);
                    }
                    
                    fingerButton1.setEnabled(true);
                    fingerButton2.setEnabled(true);
                    fingerButton3.setEnabled(true);
                    fingerButton4.setEnabled(true);
                    fingerButton5.setEnabled(true);
                    fingerButton6.setEnabled(true);
                    fingerButton7.setEnabled(true);
                    fingerButton8.setEnabled(true);
                    fingerButton9.setEnabled(true);
                    fingerButton10.setEnabled(true);
                    jComboBox1.setEnabled(true);
                    saveButton.setEnabled(true);
                    /*
                    if (employee.getFingerprint() != null) {
                        DPFPTemplate t = DPFPGlobal.getTemplateFactory().createTemplate();
                            t.deserialize(employee.getFingerprint());
                            setTemplate(t);

                            saveButton.setEnabled(true);
                            verifyButton.setEnabled(true);
                    }
                    */
                
                }else{
                    JOptionPane.showMessageDialog(MainForm.this, "Inactive employee.", "Error", JOptionPane.ERROR_MESSAGE);
                    cleanForm();
                }
                
            }else {
                JOptionPane.showMessageDialog(MainForm.this, "Employee not found.", "Error", JOptionPane.ERROR_MESSAGE);
                cleanForm();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(MainForm.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
            
        
    }
    
    public void cleanForm() {
        txtCode.setText("");
        txtfirstName.setText("");
        txtlastName.setText("");
        txtAddress.setText("");      
        txtPhoneNumber1.setText(""); 
        txtCampus.setText("");
        txtDepartment.setText("");
        txtEmployment.setText("");
        ImageIcon icon = new ImageIcon("person.png");
        photo.setIcon(icon);
        fingerButton1.setEnabled(false);
        fingerButton2.setEnabled(false);
        fingerButton3.setEnabled(false);
        fingerButton4.setEnabled(false);
        fingerButton5.setEnabled(false);
        fingerButton6.setEnabled(false);
        fingerButton7.setEnabled(false);
        fingerButton8.setEnabled(false);
        fingerButton9.setEnabled(false);
        fingerButton10.setEnabled(false);
        jComboBox1.setEnabled(false);
        saveButton.setEnabled(false);
        verifyFingerButton1.setEnabled(false);
        verifyFingerButton2.setEnabled(false);
        verifyFingerButton3.setEnabled(false);
        verifyFingerButton4.setEnabled(false);
        verifyFingerButton5.setEnabled(false);
        verifyFingerButton6.setEnabled(false);
        verifyFingerButton7.setEnabled(false);
        verifyFingerButton8.setEnabled(false);
        verifyFingerButton9.setEnabled(false);
        verifyFingerButton10.setEnabled(false);
        
    }
    
    public ImageIcon getIcon(byte[] data, int width, int height) {
        ImageIcon _photo = new ImageIcon(data);
        Image image = _photo.getImage();
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        return  new ImageIcon(newimg);  // transform it back
    }
    
    public void saveButtonClick(java.awt.event.ActionEvent evt) {
        
        Employee employee = new Employee();
        System.out.println("Save employee: " + getEmployeeId());
        employee.setId(getEmployeeId());
        
        FingerprintRequest fingerprintRequest = new FingerprintRequest();
        
        fingerprintRequest.setEmployee(employee);        
        fingerprintRequest.setFingerprintDefault("fingerprintDefault");
        if (getTemplate1() != null) {
            fingerprintRequest.setFingerprint1(getTemplate1().serialize());
        }
        if (getTemplate2() != null) {
            fingerprintRequest.setFingerprint2(getTemplate2().serialize());
        }
        if (getTemplate3() != null) {
            fingerprintRequest.setFingerprint3(getTemplate3().serialize());
        }
        if (getTemplate4() != null) {
            fingerprintRequest.setFingerprint4(getTemplate4().serialize());
        }
        if (getTemplate5() != null) {
            fingerprintRequest.setFingerprint5(getTemplate5().serialize());
        }
        if (getTemplate6() != null) {
            fingerprintRequest.setFingerprint6(getTemplate6().serialize());
        }
        if (getTemplate7() != null) {
            fingerprintRequest.setFingerprint7(getTemplate7().serialize());
        }
        if (getTemplate8() != null) {
            fingerprintRequest.setFingerprint8(getTemplate8().serialize());
        }
        if (getTemplate9() != null) {
            fingerprintRequest.setFingerprint9(getTemplate9().serialize());
        }
        if (getTemplate10() != null) {
            fingerprintRequest.setFingerprint10(getTemplate10().serialize());
        }
        if (getFingerprintDefault() != null) {
            fingerprintRequest.setFingerprintDefault(getFingerprintDefault());
        }
        
        fingerprintRequest.setToken("00000");
        fingerprintRequest.setLang("es");
                
         try {
             
            String url = "http://ec2-18-224-134-225.us-east-2.compute.amazonaws.com:8087/fingerprintEmployee/save";
            
            ClientRestService client = new ClientRestService(url);
            
            FingerprintResponse fingerprintResponse = client.consumeRestSaveFingerprintEmployee(fingerprintRequest);
            
            if (!fingerprintResponse.isError()) {
                
                System.out.println("Result: " + fingerprintResponse.isResult());
                System.out.println("Error: " + fingerprintResponse.isError());
                System.out.println("Message: " + fingerprintResponse.getMessage());
                
            }else {
                JOptionPane.showMessageDialog(MainForm.this, "Could not be saved.", "Error", JOptionPane.ERROR_MESSAGE);
                cleanForm();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(MainForm.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void captureButtonClick(java.awt.event.ActionEvent evt) {
        onEnroll();
    }
    
    public void verifyButtonClick(java.awt.event.ActionEvent evt) {
        onVerify();
    }
    
    public void quitButtonClick(java.awt.event.ActionEvent evt) {
        dispose();
    }
    
    private void onEnroll() {
        EnrollmentForm form = new EnrollmentForm(this);
        form.setVisible(true);
    }

    private void onVerify() {
        VerificationForm form = new VerificationForm(this);
        form.setVisible(true);
    }
    
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }
    
    public static void main( String args[] ) {
        // Instancia un objeto de tipo Interfaz Hombre-Maquina
        MainForm mainForm = new MainForm();
        
    }

    public DPFPTemplate getTemplate1() {
        return template1;
    }

    public void setTemplate1(DPFPTemplate template1) {
        DPFPTemplate old = this.template1;
        this.template1 = template1;
        firePropertyChange(TEMPLATE_PROPERTY, old, template1);
    }

    public DPFPTemplate getTemplate2() {
        return template2;
    }

    public void setTemplate2(DPFPTemplate template2) {
        DPFPTemplate old = this.template2;
        this.template2 = template2;
        firePropertyChange(TEMPLATE_PROPERTY, old, template2);
    }

    public DPFPTemplate getTemplate3() {
        return template3;
    }

    public void setTemplate3(DPFPTemplate template3) {
        DPFPTemplate old = this.template3;
        this.template3 = template3;
        firePropertyChange(TEMPLATE_PROPERTY, old, template3);
    }

    public DPFPTemplate getTemplate4() {
        return template4;
    }

    public void setTemplate4(DPFPTemplate template4) {
       DPFPTemplate old = this.template4;
        this.template4 = template4;
        firePropertyChange(TEMPLATE_PROPERTY, old, template4);
    }

    public DPFPTemplate getTemplate5() {
        return template5;
    }

    public void setTemplate5(DPFPTemplate template5) {
        DPFPTemplate old = this.template5;
        this.template5 = template5;
        firePropertyChange(TEMPLATE_PROPERTY, old, template5);
    }

    public DPFPTemplate getTemplate6() {
        return template6;
    }

    public void setTemplate6(DPFPTemplate template6) {
       DPFPTemplate old = this.template6;
        this.template6 = template6;
        firePropertyChange(TEMPLATE_PROPERTY, old, template6);
    }

    public DPFPTemplate getTemplate7() {
        return template7;
    }

    public void setTemplate7(DPFPTemplate template7) {
        DPFPTemplate old = this.template7;
        this.template7 = template7;
        firePropertyChange(TEMPLATE_PROPERTY, old, template7);
    }

    public DPFPTemplate getTemplate8() {
        return template8;
    }

    public void setTemplate8(DPFPTemplate template8) {
       DPFPTemplate old = this.template8;
        this.template8 = template8;
        firePropertyChange(TEMPLATE_PROPERTY, old, template8);
    }

    public DPFPTemplate getTemplate9() {
        return template9;
    }

    public void setTemplate9(DPFPTemplate template9) {
        DPFPTemplate old = this.template9;
        this.template9 = template9;
        firePropertyChange(TEMPLATE_PROPERTY, old, template9);
    }

    public DPFPTemplate getTemplate10() {
        return template10;
    }

    public void setTemplate10(DPFPTemplate template10) {
        DPFPTemplate old = this.template10;
        this.template10 = template10;
        firePropertyChange(TEMPLATE_PROPERTY, old, template10);
    }
    
    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String EmployeeId) {
        this.EmployeeId = EmployeeId;
    }
    
    public String getFingerprintDefault() {
        return fingerprintDefault;
    }
    
    public void setFingerprintDefault(String fingerprintDefault) {
        this.fingerprintDefault = fingerprintDefault;
    }
    
    public String getFingerprintPosition() {
        return fingerprintPosition;
    }

    public void setFingerprintPosition(String fingerprintPosition) {
        this.fingerprintPosition = fingerprintPosition;
    }
         
    private void txtCriteriaFocusGained(java.awt.event.FocusEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void findButtonFocusLost(java.awt.event.FocusEvent evt) {                                     
        // TODO add your handling code here:
    } 
    
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt, Employee employee) {                                           
        // TODO add your handling code here:
        fingerprintRequest.setEmployee(employee);        
        fingerprintRequest.setFingerprintDefault("fingerprintDefault");
        
        
        fingerprintRequest.setToken("00000");
        fingerprintRequest.setLang("es");
                
         try {
            
            String url = "http://ec2-18-224-134-225.us-east-2.compute.amazonaws.com:8087/fingerprintEmployee/save";
            
            ClientRestService client = new ClientRestService(url);
            
            FingerprintResponse fingerprintResponse = client.consumeRestSaveFingerprintEmployee(fingerprintRequest);
            
            if (!fingerprintResponse.isError()) {
                
                System.out.println("Result: " + fingerprintResponse.isResult());
                System.out.println("Error: " + fingerprintResponse.isError());
                System.out.println("Message: " + fingerprintResponse.getMessage());
                
            }else {
                JOptionPane.showMessageDialog(MainForm.this, "Could not be saved.", "Error", JOptionPane.ERROR_MESSAGE);
                cleanForm();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(MainForm.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static BufferedImage byteArrayToImage(byte[] bytes) {
        BufferedImage bufferedImage = null;
        //ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            //bos.write(bytes);
            //byte [] data = bos.toByteArray();
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            bufferedImage = ImageIO.read(bis);
        } catch (IOException ex) {
            Logger.getLogger(CaptureForm.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(CaptureForm.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }

        return bufferedImage;
    }

    DPFPTemplate getTemplatefingerprintDefault() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setTemplatefingerprintDefault(DPFPTemplate template) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void mostrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
