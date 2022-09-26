package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import modelo.Credito;
import modelo.Cuenta;
import modelo.Movimiento;

public class CuentaTest {
	private Cuenta unaCuenta;
	
	@BeforeEach
	public void setUp() throws Exception {
		unaCuenta=new Cuenta("06-456213-33","Alfredo Hernandez");
		System.out.println("hola mundo");
	}
	
	@Test
	public void testDeCuenta() throws Exception {
		assertNotNull(unaCuenta);
		
		try {
			// Creo una cuenta con 5000 de salgo
			unaCuenta.ingresar(5000.0);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			// Intento ingresar dinero negativo
			unaCuenta.ingresar(-500.0);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			// Intento retirar dinero negativo
			unaCuenta.retirar(-500.0);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			// Intento retirar mas dinero del que tengo
			unaCuenta.retirar(90000);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			// Crear movimiento negativo
			System.out.println(unaCuenta.getSaldo());
			Movimiento mov = new Movimiento();
			mov.setConcepto("Inversion");
			mov.setImporte(-500);
			unaCuenta.addMovimiento(mov);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			unaCuenta.addMovimiento(null);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		unaCuenta.getMovimientos().forEach((mov) -> {
			System.out.println(mov);
			assertTrue(mov != null);
		});
		
		assertTrue(unaCuenta.getMovimientos().size() == 3);
		assertTrue(unaCuenta.getSaldo() == 5000.0);
		assertTrue(unaCuenta.getSaldo() >= 0);
		
		
	}
}
