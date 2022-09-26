package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.Cuenta;
import modelo.Debito;

public class DebitoTest {
	private Date fecha;
	private Debito debito;
	private Cuenta unaCuenta;
	
	@BeforeEach
	public void setUp() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateInString = "2020-09-15";
		dateInString = "2020-09-15";
		try {
			fecha = sdf.parse(dateInString);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		debito = new Debito("06-456213-33","Alfredo Hernandez",fecha);
		unaCuenta=new Cuenta("06-456213-33","Alfredo Hernandez");
	}

	@Test
	public void testComprasConTarjetaDebito(){
	
		assertNotNull(fecha);
		
		//Se crea una cuenta con $7000 de saldo
		try {
			unaCuenta.ingresar(7000.00);
			debito.setCuenta(unaCuenta);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			debito.pagoEnEstablecimiento("MusiMundo",3000.00);
			debito.pagoEnEstablecimiento("Fravega",1000.00);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("cuenta saldo = " + unaCuenta.getSaldo());
		System.out.println("debito.getSaldo() " + debito.getSaldo());
		
		assertTrue(unaCuenta.getSaldo()==3000.00);
		assertTrue(debito.getSaldo()==3000.00);
		assertTrue(debito.getSaldo()>=0);
		assertTrue(unaCuenta.getSaldo()>=0);
		
	}

}
