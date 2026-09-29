/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nomina;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Francisco Hernandez Hernandez
 */
public class PagoTrabajadoresApp extends JFrame{
    private JTextField txtNombre, txtNumEmpleado, txtSalarioPorHora;
    private JTextField txtLun, txtMar, txtMie, txtJue, txtVie;
    private JCheckBox chkConfianza;
    private JTextField txtBono;
 
    private JTextField txtTotalHoras, txtSueldoBruto, txtDescuento, txtSueldoNeto;
 
    public PagoTrabajadoresApp() {
        super("Pago a Trabajadores (TDA Trabajador)");
        construirInterfaz();
        setSize(500, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    private void construirInterfaz() {
        setLayout(new BorderLayout(10, 10));
        JLabel lblAutor = new JLabel("Autor: Francisco Hernández Hernández", SwingConstants.CENTER);
        
        lblAutor.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblAutor, BorderLayout.NORTH);
 
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
 
        //Formulario de captura 
        JPanel panelForm = new JPanel(new GridLayout(10, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del trabajador"));
 
        txtNombre = new JTextField();
        txtNumEmpleado = new JTextField();
        txtSalarioPorHora = new JTextField();
        txtLun = new JTextField();
        txtMar = new JTextField();
        txtMie = new JTextField();
        txtJue = new JTextField();
        txtVie = new JTextField();
        chkConfianza = new JCheckBox("Es trabajador de confianza");
        txtBono = new JTextField();
 
        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Numero de empleado:"));
        panelForm.add(txtNumEmpleado);
        panelForm.add(new JLabel("Salario por hora:"));
        panelForm.add(txtSalarioPorHora);
        panelForm.add(new JLabel("Horas Lunes:"));
        panelForm.add(txtLun);
        panelForm.add(new JLabel("Horas Martes:"));
        panelForm.add(txtMar);
        panelForm.add(new JLabel("Horas Miercoles:"));
        panelForm.add(txtMie);
        panelForm.add(new JLabel("Horas Jueves:"));
        panelForm.add(txtJue);
        panelForm.add(new JLabel("Horas Viernes:"));
        panelForm.add(txtVie);
        panelForm.add(chkConfianza);
        panelForm.add(new JLabel(""));
        panelForm.add(new JLabel("Bono de confianza (si aplica):"));
        panelForm.add(txtBono);
 
        panelCentral.add(panelForm);
 
        //Botones principales
        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 5, 5));
 
        JButton btnHoras = new JButton("Horas");
        btnHoras.addActionListener(e -> calcularHoras());
 
        JButton btnSueldoBruto = new JButton("Sueldo Bruto");
        btnSueldoBruto.addActionListener(e -> calcularSueldoBruto());
 
        JButton btnSueldoNeto = new JButton("Sueldo Neto");
        btnSueldoNeto.addActionListener(e -> calcularSueldoNeto());
 
        panelBotones.add(btnHoras);
        panelBotones.add(btnSueldoBruto);
        panelBotones.add(btnSueldoNeto);
 
        panelCentral.add(panelBotones);
 
        //Resultados 
        JPanel panelResultados = new JPanel(new GridLayout(4, 2, 5, 5));
        panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados"));
 
        txtTotalHoras = new JTextField();
        txtTotalHoras.setEditable(false);
        txtSueldoBruto = new JTextField();
        txtSueldoBruto.setEditable(false);
        txtDescuento = new JTextField();
        txtDescuento.setEditable(false);
        txtSueldoNeto = new JTextField();
        txtSueldoNeto.setEditable(false);
 
        panelResultados.add(new JLabel("Total de horas:"));
        panelResultados.add(txtTotalHoras);
        panelResultados.add(new JLabel("Sueldo Bruto:"));
        panelResultados.add(txtSueldoBruto);
        panelResultados.add(new JLabel("Descuento:"));
        panelResultados.add(txtDescuento);
        panelResultados.add(new JLabel("Sueldo Neto:"));
        panelResultados.add(txtSueldoNeto);
 
        panelCentral.add(panelResultados);
 
        add(panelCentral, BorderLayout.CENTER);
    
    }
    private Trabajador construirTrabajador() {
        String nombre = txtNombre.getText().trim();
        String numEmpleado = txtNumEmpleado.getText().trim();
        float salario = parseSeguro(txtSalarioPorHora.getText());
        float[] horas = 
        {
            parseSeguro(txtLun.getText()),
            parseSeguro(txtMar.getText()),
            parseSeguro(txtMie.getText()),
            parseSeguro(txtJue.getText()),
            parseSeguro(txtVie.getText())
        };
 
        if (chkConfianza.isSelected()) 
        {
            float bono = parseSeguro(txtBono.getText());
            return new TrabajadorConfianza(nombre, numEmpleado, salario, horas, bono);
        }
        return new Trabajador(nombre, numEmpleado, salario, horas);
    }
 
    /** Convierte texto a numero de forma segura si esta vacio o mal regresa 0. */
    private float parseSeguro(String texto) 
    {
        try 
        {
            return Float.parseFloat(texto.trim());
        } catch (NumberFormatException ex) {
            return 0f;
        }
    }
 
    private void calcularHoras() 
    {
        Trabajador t = construirTrabajador();
        txtTotalHoras.setText(String.format("%.2f", t.calcularTotalHoras()));
    }
 
    private void calcularSueldoBruto() 
    {
        Trabajador t = construirTrabajador();
        txtSueldoBruto.setText(String.format("%.2f", t.calcularSueldoBruto()));
        txtDescuento.setText(String.format("%.2f", t.calcularDescuento()));
    }
 
    private void calcularSueldoNeto() 
    {
        Trabajador t = construirTrabajador();
        txtSueldoNeto.setText(String.format("%.2f", t.calcularSueldoNeto()));
    }
 
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> new PagoTrabajadoresApp().setVisible(true));
    }
}
