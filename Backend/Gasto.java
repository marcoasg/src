package Backend;

import java.text.SimpleDateFormat;
import java.util.*;

public class Gasto {
	
	private static String BD_SERVER = "localhost";
    private static String BD_NAME = "ACOES";
	
	private double cantidad;
	private String beneficiario;
	private ProyectoLocal proyecto;
	private Date fecha;
	private int codigo;
	private boolean estado;
	
	public static Gasto[] ListaGastos(ProyectoLocal p, Date fi, Date ff){
		List<Gasto> lista = new ArrayList<Gasto>();
		BD miBD = new BD(BD_SERVER,BD_NAME);
		Gasto[] resultado;
		
		for(Object[] tupla : miBD.Select("SELECT * FROM tGasto WHERE proyecto = "+p.getCodigo()+";")) {
			if((fi.compareTo((Date)tupla[3])<=0)&&(ff.compareTo((Date)tupla[3])>=0)){
				lista.add(new Gasto((Integer)tupla[4]));
			}
		}
		resultado = new Gasto[lista.size()];
		int i = 0;
		for(Gasto g : lista) {
			resultado[i] = g;
			i++;
		}
		return resultado;
		
	}
	
	public static Gasto[] ListaGastos(ProyectoLocal p){
		List<Gasto> lista = new ArrayList<Gasto>();
		BD miBD = new BD(BD_SERVER,BD_NAME);
		Gasto[] resultado;
		
		for(Object[] tupla : miBD.Select("SELECT * FROM tGasto WHERE proyecto = "+p.getCodigo()+";")) {
			lista.add(new Gasto((Integer)tupla[4]));	
		}
		resultado = new Gasto[lista.size()];
		int i = 0;
		for(Gasto g : lista) {
			resultado[i] = g;
			i++;
		}
		return resultado;
		
	}
	
	public static Gasto[] ListaGastos(){
		List<Gasto> lista = new ArrayList<Gasto>();
		BD miBD = new BD(BD_SERVER,BD_NAME);
		Gasto[] resultado;
		
		for(Object[] tupla : miBD.Select("SELECT * FROM tGasto WHERE estado = 0;")) {
			lista.add(new Gasto((Integer)tupla[4]));	
		}
		resultado = new Gasto[lista.size()];
		int i = 0;
		for(Gasto g : lista) {
			resultado[i] = g;
			i++;
		}
		return resultado;
		
	}
	public Gasto(int id) {
		BD miBD = new BD(BD_SERVER,BD_NAME); 
		Object[] tupla = miBD.Select("SELECT * FROM tGasto WHERE codigo =" + id +";").get(0);
		
		if(tupla == null) {
			throw new Error("El gasto con codigo "+id+" no esta en la base de datos");
		}
		cantidad = (double)tupla[0];
		beneficiario= (String)tupla[1];
		proyecto = new ProyectoLocal((int)tupla[2]);
		fecha = (Date)tupla[3];
		codigo = (Integer)tupla[4];
		estado = (Integer)tupla[5] == 1 ? true : false;
	}
	
	public Gasto(double cant, String beneficiario, ProyectoLocal proyecto, Date fecha) {
		BD miBD = new BD(BD_SERVER,BD_NAME);
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		miBD.Insert("Insert into tGasto values("+ cant +", '" +beneficiario+"', " +proyecto.getCodigo()+", '"+formatoDelTexto.format(fecha)+"', 0);" );
		this.cantidad=cant;
		this.beneficiario = beneficiario;
		this.proyecto = proyecto;
		this.fecha = fecha;
		this.codigo = (Integer)miBD.SelectEscalar("Select MAX(codigo) from tGasto;");
		this.estado = false;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		BD miBD = new BD(BD_SERVER,BD_NAME);
		miBD.Update("UPDATE tGasto SET cantidad = " +cantidad+" WHERE codigo = " +this.codigo+";");
		this.cantidad = cantidad;
	}

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		BD miBD = new BD(BD_SERVER,BD_NAME);
		miBD.Update("UPDATE tGasto SET beneficiario = '" +beneficiario+"' WHERE codigo = " +this.codigo+";");
		this.beneficiario = beneficiario;
	}

	public ProyectoLocal getProyecto() {
		return proyecto;
	}

	public void setProyecto(ProyectoLocal proyecto) {
		BD miBD = new BD(BD_SERVER,BD_NAME);
		miBD.Update("UPDATE tGasto SET proyecto = " +proyecto.getCodigo()+" WHERE codigo = " +this.codigo+";");
		this.proyecto = proyecto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		BD miBD = new BD(BD_SERVER,BD_NAME);
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		miBD.Update("UPDATE tGasto SET fecha = " +formatoDelTexto.format(fecha)+" WHERE codigo = " +this.codigo+";");
		this.fecha = fecha;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public boolean getEstado() {
		return estado;
	}
	
	public void setEstado(boolean value) {
		BD miBD = new BD(BD_SERVER,BD_NAME);
		int aux = 0;
		if(value == true) {
			aux = 1;
		}
		miBD.Update("UPDATE tGasto SET estado = " +aux+" WHERE codigo = " +this.codigo+";");
		this.estado = value;
	}
	public void eliminarGasto() {
		BD miBD = new BD(BD_SERVER,BD_NAME);
		miBD.Delete("DELETE from tGasto where codigo =" + this.codigo + ";");
		cantidad = -1;
		beneficiario= null;
		proyecto = null;
		fecha = null;
		codigo = -1;
		estado = false;		
	}
	public void validarGasto() {
		BD miBD = new BD(BD_SERVER,BD_NAME);
		miBD.Update("UPDATE tGasto set estado = 1 where codigo = " + codigo + ";");
		estado = true;
	}
}