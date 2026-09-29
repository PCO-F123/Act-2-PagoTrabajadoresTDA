/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nomina;

/**
 *
 * @author Francisco Hernandez Hernandez
 */
public class TrabajadorConfianza extends Trabajador{
    //Atributo
    private float bonoConfianza;
    
    public TrabajadorConfianza(String nombre, String numeroEmpleado, float salarioPorHora, float [] horasPorDia, float bonoConfianza)
    {
        super(nombre, numeroEmpleado, salarioPorHora, horasPorDia);
        this.bonoConfianza = (bonoConfianza >=0) ? bonoConfianza: 0f;
    }
    public float getbonoConfianza()
    {
        return bonoConfianza;
    }
    public void setbonoConfianza(float bono)
    {
        if (bono >=0)
        {
        this.bonoConfianza=bono;
        }
    }
    @Override
    public float calcularSueldoNeto() 
    {
        float pagoNormal = calcularHorasNormales() * getsalarioPorHora();
        float descuento = pagoNormal * 0.05f; // mismo 5%
        return (pagoNormal - descuento) + bonoConfianza;
    }
    @Override
    public String toString() 
    {
        return super.toString() + "-> Bono de confianza: $" + String.format("%.2f", bonoConfianza);
    }
}

