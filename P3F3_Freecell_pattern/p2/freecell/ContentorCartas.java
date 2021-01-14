package p2.freecell;

import java.awt.Graphics;
import java.awt.Point;

public interface ContentorCartas extends Cloneable{
	
	public boolean estaDentro( Point pt );
	public boolean podeReceber( Carta c );	
	public boolean receber( Carta c );
	
	public void colocar( Carta c );

	public Carta getCarta( );
	public Carta retirar( );		

	public boolean estaVazio();
	public void limpar();
	
	public void setSeleccionado( boolean sel );
	public boolean getSeleccionado( );
	
	public void desenhar( Graphics g );
	
	public ContentorCartas clone();
	
}
