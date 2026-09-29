/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nomina;

/**
 *
 * @author Francisco Hernandez Hernandez
 */
public class Trabajador {
    
    //Atributos
    private String nombre;
    private String numeroEmpleado;
    private float salarioPorHora;
    //Arreglo lunes a viernes 
    private float[] horasPorDia;
    
    //Constructores
    public Trabajador()
    {
        nombre = "Desconocido";
        numeroEmpleado = "0000";
        salarioPorHora = 0f;
        horasPorDia = new float []{0f,0f, 0f, 0f, 0f};
        
    }
    public Trabajador(String nombre, String numeroEmpleado, float salarioPoHora, float[] horasPorDia)
    {
        this.nombre = nombre;
        this.numeroEmpleado = numeroEmpleado;
        this.salarioPorHora = (salarioPoHora >= 0) ? salarioPoHora: 0f;
        this.horasPorDia = (horasPorDia !=null && horasPorDia.length== 5)? horasPorDia: new float []{0f,0f,0f,0f,0f};
        
    }
    //constructor parcial un trabajador nuevo sin horarios capturados
    public Trabajador(String nombre, String numeroEmpleado, float salarioPorHora)
    {
        this(nombre, numeroEmpleado, salarioPorHora, new float[]{0f, 0f, 0f, 0f, 0f});
    }
    //Constructor copia 
    public Trabajador(Trabajador copia)
    {
        this.nombre = copia.nombre;
        this.numeroEmpleado = copia.numeroEmpleado;
        this.salarioPorHora = copia.salarioPorHora;
        this.horasPorDia = copia.horasPorDia.clone();
        
    }
    // Getters
    public String getnombre()
    {
        return nombre;
    }
    public String getnumeroEmpleado()
    {
        return numeroEmpleado;
    }
    public float getsalarioPorHora()
    {
        return salarioPorHora;
    }
    public float[]gethorasPorDia()
    {
        return horasPorDia;
    }
    //Setter
    public void setsalarioPorHora(float salario)
    {
        if (salario >=0)
        {
            this.salarioPorHora =  salario;
        }
    }
    
    //Metodos de la logica
    
    //Suma las horas trabajadas dia por dia
    private float sumarHoras(float[] arr, int i) {
        if (i == arr.length) {
            return 0f; // caso base
        }
        return arr[i] + sumarHoras(arr, i + 1);
    }
    //Calcula el total de horas trabajados en la semana
    public float calcularTotalHoras() {
        return sumarHoras(horasPorDia, 0);
    }
    //Horas normales de la semana el cual el maximo es de 48 horas de jornada
    public float calcularHorasNormales() {
        float total = calcularTotalHoras();
        return Math.min(total, 48f);
    }
    //Horas que sobrepasen de 48 horas en la semana se pagan como extra
    public float calcularHorasExtras() {
        float total = calcularTotalHoras();
        return (total > 48f) ? (total - 48f) : 0f;
    }
    //Calcular el sueldo 
    public float calcularSueldoBruto() {
        float pagoNormal = calcularHorasNormales() * salarioPorHora;
        float pagoExtra = calcularHorasExtras() * salarioPorHora * 2;
        return pagoNormal + pagoExtra;
    }
    //Calcular el descuento fijo del 5% sobre el sueldo bruto 
    public float calcularDescuento() {
        return calcularSueldoBruto() * 0.05f;
    }
    //Sueldo final que recibe el trabajador ya con el descuento aplicado
    public float calcularSueldoNeto() {
        return calcularSueldoBruto() - calcularDescuento();
    }
    @Override
    public String toString()
    {
        return numeroEmpleado + "-" + nombre + "-> Sueldo neto: $" + String.format("%.2f", calcularSueldoNeto());
    }
    
}